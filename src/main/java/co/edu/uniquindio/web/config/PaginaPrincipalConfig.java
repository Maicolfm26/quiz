package co.edu.uniquindio.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * PaginaPrincipalConfig es el encargado de definir el archivo index del servidor
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Configuration
public class PaginaPrincipalConfig implements WebMvcConfigurer {

    /**
     * Se define la ruta principal con el archivo /index.xhtml
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/index.xhtml");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
