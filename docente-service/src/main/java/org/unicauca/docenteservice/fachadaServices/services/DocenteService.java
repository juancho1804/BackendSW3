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
import org.unicauca.docenteservice.fachadaServices.rest.MessageResponseDTO;
import org.unicauca.docenteservice.fachadaServices.rest.User;

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
            System.out.println("Agregando docente");
            Docente docenteEntity = this.modelMapper.map(docente, Docente.class);
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
            Docente docenteEntity=docenteRepository.findByIdentificacion(id);
            docenteEntity.setNombres(docente.getNombres());
            docenteEntity.setApellidos(docente.getApellidos());
            docenteEntity.setEmail(docente.getEmail());
            docenteEntity.setTituloAcademico(docente.getTituloAcademico());
            docenteEntity.setEstado(docente.getEstado());
            docenteEntity.setContrasenia(docente.getContrasenia());
            docenteEntity.setTipoDocente(docente.getTipoDocente());
            docenteRepository.save(docenteEntity);
            docenteDTO = modelMapper.map(docenteEntity, DocenteDTO.class);
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

        String url = "http://localhost:5000/api/test/"+id;
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
        String url = "http://localhost:5000/api/auth/signup";
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
}
