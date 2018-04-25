package com.learning.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * http://www.baeldung.com/spring-xml-vs-java-config
 *
 * Some pictures and details about MVC basics:
 * http://www.mkyong.com/spring-mvc/spring-mvc-hello-world-example/
 */
public class WebAppInitializer implements WebApplicationInitializer {

  public void onStartup(ServletContext container) {
    // Use Java based configuration
    AnnotationConfigWebApplicationContext context =
      new AnnotationConfigWebApplicationContext();

    // Specify the package where configuration classes are located
    context.setConfigLocation("com.learning.web.config");

    // https://stackoverflow.com/a/11817368/7464024
    // Use Java based configuration instead [servletname]-servlet.xml
    container.addListener(new ContextLoaderListener(context));

    // DispatcherServlet is the entry point of every Spring MVC application.
    // Its purpose is to intercept HTTP requests and to dispatch them to the right component
    // that will know how to handle it.
    ServletRegistration.Dynamic dispatcher =
      container.addServlet("dispatcher", new DispatcherServlet(context));

    // load-on-startup is an integer value
    // that specifies the order for multiple servlets to be loaded.
    // So if you need to declare more than one servlet
    // you can define in which order they will be initialized.
    // Servlets marked with lower integers are loaded before servlets marked with higher integers.
    dispatcher.setLoadOnStartup(1);

    // The Servlet is mapped to / – meaning it becomes the default Servlet of the application.
    // It will pick up every pattern
    // that doesn’t have another exact match defined by another Servlet
    dispatcher.addMapping("/");
  }

}
