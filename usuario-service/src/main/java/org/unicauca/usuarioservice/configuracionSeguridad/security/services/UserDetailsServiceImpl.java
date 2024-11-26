package org.unicauca.usuarioservice.configuracionSeguridad.security.services;

import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.entidades.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unicauca.usuarioservice.configuracionSeguridad.accesoADatos.repositorios.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  IUserRepository userRepository; //

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

    return UserDetailsImpl.build(user);
  }

}
