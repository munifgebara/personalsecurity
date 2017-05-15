package br.com.munif.personalsecurity.configuracao;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Bootstrap extends AbstractAnnotationConfigDispatcherServletInitializer{

    public Bootstrap() {
        System.out.println("------------>BootStrap");
    }
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
         return new Class<?>[]{Application.class};   
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
       return new Class<?>[]{};
    }

    @Override
    protected String[] getServletMappings() {
       return new String[]{"/api"};
    }
    
}
