package org.unicauca.docenteservice.tools;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class envConfig {
    static {
        // Ruta relativa al archivo .env
        String projectRoot = new java.io.File("../BackendSW3").getAbsolutePath();
        System.out.println("Using dotenv path: " + projectRoot);

        // Configurar dotenv para cargar las variables desde el archivo .env
        Dotenv dotenv = Dotenv.configure()
                .directory(projectRoot)
                .load();

        // Registrar cada variable como una propiedad del sistema
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
    }
}
