package br.com.impactit.framework;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Super Api
 *
 * @author munif
 */
public abstract class ImpactitApi<T extends ImpactitEntity> extends HttpServlet {

    protected ObjectMapper mapper;

    protected SimpleDateFormat dateFormat;

    @Autowired
    protected ImpactitService<T> service;

    protected String url;

    public abstract Class<T> getClazz();

    public ImpactitApi() {

        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        mapper.setDateFormat(dateFormat);
        //mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

    }

    protected String[] getUrlParameters(HttpServletRequest request) {
        url = this.getServletContext().getContextPath() + this.getClass().getAnnotation(WebServlet.class).urlPatterns()[0].replace("/*", "").trim();
        String paramters = request.getRequestURI().replaceFirst(url, "");
        paramters = paramters.replaceFirst("/", "");
        if (paramters.isEmpty()) {
            return new String[0];
        }
        return paramters.split("/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        String[] urlParameters = getUrlParameters(request);
        if (urlParameters.length == 0) {
            List<T> findAll = findAll();
            mapper.writeValue(response.getOutputStream(), findAll);
        } else if (urlParameters.length == 1) {
            T recuperado = service.view(urlParameters[0]);
            if (recuperado == null) {
                response.setStatus(400);
                mapper.writeValue(response.getOutputStream(), "Objeto inexistente");
            } else {
                mapper.writeValue(response.getOutputStream(), recuperado);
            }
        } else {
            String result = java.net.URLDecoder.decode(urlParameters[1], "UTF-8");
            mapper.writeValue(response.getOutputStream(), service.find10primeiros(getClazz(), result));
        }
    }

    protected List<T> findAll() {
        System.out.println("---->Find ALl da Super Classe");
        List<T> findAll = service.findAll();
        return findAll;
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String[] urlParameters = getUrlParameters(request);
        if (urlParameters.length == 1) {
            T recuperado = service.view(urlParameters[0]);
            if (recuperado == null) {
                response.setStatus(400);
                mapper.writeValue(response.getOutputStream(), "Objeto inexistente");
            } else {
                mapper.writeValue(response.getOutputStream(), recuperado);
                service.delete(recuperado);
            }
        } else {
            response.setStatus(400);
            mapper.writeValue(response.getOutputStream(), "Número de parâmetros inválido");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String[] urlParameters = getUrlParameters(request);
        if (urlParameters.length == 1) {
            T doJson = mapper.readValue(request.getInputStream(), getClazz());
            T salva = service.save(doJson);
            mapper.writeValue(response.getOutputStream(), salva);
        } else {
            response.setStatus(400);
            mapper.writeValue(response.getOutputStream(), "Número de parâmetros inválido");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String[] urlParameters = getUrlParameters(request);
        if (urlParameters.length == 0) {
            T doJson = mapper.readValue(request.getInputStream(), getClazz());
            T salva = saveNew(doJson);
            mapper.writeValue(response.getOutputStream(), salva);
        } else {
            response.setStatus(400);
            mapper.writeValue(response.getOutputStream(), "Número de parâmetros inválidos");
        }
    }

    protected T saveNew(T obj) {
        T salva = service.save(obj);
        return salva;
    }

    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String[] urlParameters = getUrlParameters(request);
        super.doHead(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        initSpring();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        initSpring();
    }

    protected void initSpring() {
        System.out.println("---------> Injetando spring na API");
        WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
    }
    
    protected void methodNotAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("---->methodNotAllowed from "+request.getRemoteHost());
        response.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(), "Método não permitido");
    }

}
