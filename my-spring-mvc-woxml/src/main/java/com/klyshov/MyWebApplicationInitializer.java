package com.klyshov;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import static org.springframework.web.servlet.function.RequestPredicates.*;
//import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by 16688641 on 15.11.2019.
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletCxt) {
        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApplicationConfig.class);
        applicationContext.refresh();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");


        SimpleHandler handler = new SimpleHandler();
        RouterFunction<ServerResponse> route = route()
                .GET("/person/{id}", accept(APPLICATION_JSON), handler::simpleHandle)
                .build();

    }

    public class SimpleHandler {

        // ...

        public ServerResponse simpleHandle(ServerRequest request) {
            return ServerResponse.ok().body("Hello World");
        }

    }
}
