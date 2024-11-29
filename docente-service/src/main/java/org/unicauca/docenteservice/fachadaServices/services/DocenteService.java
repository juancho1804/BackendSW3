package org.unicauca.docenteservice.fachadaServices.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.unicauca.docenteservice.capaAccesoADatos.models.Docente;
import org.unicauca.docenteservice.capaAccesoADatos.models.EEstado;
import org.unicauca.docenteservice.capaAccesoADatos.repositories.IDocenteRepository;
import org.unicauca.docenteservice.fachadaServices.DTO.DocenteDTO;
import org.unicauca.docenteservice.fachadaServices.rest.Asignatura;
import org.unicauca.docenteservice.fachadaServices.rest.MessageResponseDTO;
import org.unicauca.docenteservice.fachadaServices.rest.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocenteService implements IDocenteService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IDocenteRepository docenteRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public DocenteDTO agregarDocente(DocenteDTO docente) {
        DocenteDTO docenteDTO = null;

        User usuarioDTO = User.builder()
                .id(docente.getId())
                .tipoIdentificacion(docente.getTipoIdentificacion().toString())
                .identificacion(docente.getIdentificacion())
                .nombres(docente.getNombres())
                .apellidos(docente.getApellidos())
                .email(docente.getEmail())
                .contrasenia(docente.getContrasenia())
                .build();

        if(agregarUsuario(usuarioDTO).getStatusCode() == HttpStatus.OK){
            System.out.println("Usuario agregado exitosamente, procediendo a agregar docente");
            System.out.println("Agregando docente...");
            Docente docenteEntity = this.modelMapper.map(docente, Docente.class);
            System.out.println("Procediendo a validar asignaturas a agregar");

            if(docenteEntity.getAsignaturas()!=null){
                docenteEntity.setAsignaturas(encontrarAsignaturas(docente.getAsignaturas()));
            }
            docenteRepository.save(docenteEntity);
            docenteDTO = modelMapper.map(docenteEntity, DocenteDTO.class);
        }

        return docenteDTO;
    }


    @Override
    public DocenteDTO actualizarDocente(DocenteDTO docente, String id) {

        DocenteDTO docenteDTO = null;
        User usuarioDTO = User.builder()
                        .nombres(docente.getNombres())
                        .apellidos(docente.getApellidos())
                        .email(docente.getEmail())
                        .contrasenia(docente.getContrasenia()).build();


        if(editarUsuario(usuarioDTO,id).getStatusCode() == HttpStatus.CREATED){

            Optional<Docente> optionalDocente=docenteRepository.findByIdentificacion(id);
            if(optionalDocente.isPresent()){
                Docente docenteEntity = optionalDocente.get();
                docenteEntity.setNombres(docente.getNombres());
                docenteEntity.setApellidos(docente.getApellidos());
                docenteEntity.setEmail(docente.getEmail());
                docenteEntity.setTituloAcademico(docente.getTituloAcademico());
                docenteEntity.setEstado(docente.getEstado());
                docenteEntity.setContrasenia(docente.getContrasenia());
                docenteEntity.setTipoDocente(docente.getTipoDocente());
                if(docente.getAsignaturas()!=null){
                    docenteEntity.setAsignaturas(encontrarAsignaturas(docente.getAsignaturas()));
                }
                docenteRepository.save(docenteEntity);
                docenteDTO = modelMapper.map(docenteEntity, DocenteDTO.class);
            }
        }
        return docenteDTO;
    }

    @Override
    public boolean cambiarEstado(int id) {
        boolean bandera = false;
        Optional<Docente> docente = docenteRepository.findById(id);
        if(docente.isPresent()){
            Docente docenteEntity = docente.get();
            if(docenteEntity.getEstado().equals(EEstado.ACTIVO)){
                System.out.println("Cambiando estado a inactivo");
                docenteEntity.setEstado(EEstado.INACTIVO);
            }else {
                System.out.println("Cambiando estado a activo");
                docenteEntity.setEstado(EEstado.ACTIVO);
            }
            docenteRepository.save(docenteEntity);
            bandera = true;
        }
        return bandera;
    }



    @Override
    public List<DocenteDTO> listarDocentes() {
        List<Docente> docentes = docenteRepository.findAll();
        return docentes.stream()
                .map(docente -> modelMapper.map(docente, DocenteDTO.class))
                .collect(Collectors.toList());
    }



    private ResponseEntity<User> editarUsuario(User user, String id) {
        ResponseEntity<User> response = null;

        String url = "http://localhost:8005/api/test/"+id;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        try{
            response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);
            System.out.println(response.getBody());
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
    private ResponseEntity<MessageResponseDTO> agregarUsuario(User usuarioDTO) {

        ResponseEntity<MessageResponseDTO> response = null;
        String url = "http://localhost:8005/api/auth/signup";
        // Configurar encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Crear la entidad HTTP
        HttpEntity<User> requestEntity = new HttpEntity<>(usuarioDTO, headers);

        // Consumir el servicio
        try {
             response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    MessageResponseDTO.class
            );

            // Imprimir la respuesta
            System.out.println("Respuesta del servidor: " + response.getBody().getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }


    public List<Integer> encontrarAsignaturas(List<Integer> ids) {
        List<Integer> idsEncontrados = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        for (Integer id : ids) {
            String url = "http://localhost:8003/asignaturas/" + id;
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            try {
                ResponseEntity<Asignatura> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        requestEntity,
                        Asignatura.class
                );
                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    // Si la asignatura fue encontrada, agregar el ID
                    idsEncontrados.add(id);
                }
            } catch (Exception e) {
                // Log o manejo del error (opcional)
                e.printStackTrace();
            }
        }

        // Si no se encontraron IDs, devolver `null`
        return idsEncontrados.isEmpty() ? null : idsEncontrados;
    }

    @Override
    public DocenteDTO agregarAsignatura(String idDocente, List<Integer> idsAsignaturas) {

        DocenteDTO docenteDTO = null;
        //Capturar el docente
        Optional<Docente> optionalDocente = docenteRepository.findByIdentificacion(idDocente);
        if(optionalDocente.isPresent()){
            Docente docenteEntity = optionalDocente.get();
            List<Integer>asignaturas=encontrarAsignaturas(idsAsignaturas);
            for(Integer id : asignaturas){
                if(!docenteEntity.getAsignaturas().contains(id)){
                    System.out.println("Asignando nueva asignatura a el docente");
                    docenteEntity.getAsignaturas().add(id);
                }
            }
            docenteDTO = modelMapper.map(optionalDocente.get(), DocenteDTO.class);
            docenteRepository.save(docenteEntity);
        }

        return docenteDTO;
    }


}
