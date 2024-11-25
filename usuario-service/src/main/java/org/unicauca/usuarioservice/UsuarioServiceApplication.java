package org.unicauca.usuarioservice;

import aj.org.objectweb.asm.commons.InstructionAdapter;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.unicauca.usuarioservice.tools.envConfig;

@SpringBootApplication
public class UsuarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioServiceApplication.class, args);
    }

}
