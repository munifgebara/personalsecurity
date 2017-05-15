/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.personalsecurity.aplicacao.autorizacao;

import br.com.munif.personalsecurity.aplicacao.usuario.UsuarioService;
import br.com.munif.personalsecurity.entidades.Usuario;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 *
 * @author munif
 */
@WebServlet(name = "GenericTokenApi", urlPatterns = {"/security-api/publicoperations/token/organizations/*"})
public class GenericTokenApi extends HttpServlet {

        protected ObjectMapper mapper;

    protected SimpleDateFormat dateFormat;

    @Autowired
    protected UsuarioService service;

    protected String url;

    public GenericTokenApi() {

        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        mapper.setDateFormat(dateFormat);
        url = "personalsecurity/security-api/publicoperations/token/";

    }

    protected String[] getUrlParameters(HttpServletRequest request) {
        String paramters = request.getRequestURI().replaceFirst(url, "");
        paramters = paramters.replaceFirst("/", "");
        if (paramters.isEmpty()) {
            return new String[0];
        }
        return paramters.split("/");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] urlParameters = getUrlParameters(request);
        System.out.println("---->" + request.getRequestURL());
        System.out.println("---->" + Arrays.toString(urlParameters));
        Usuario usuario = service.view(urlParameters[1]);
        response.setContentType("application/json;charset=UTF-8");
        //[{"id":1,"oi":null,"name":"GUMGA","subOrganizations":null,"mainOrganization":{"value":true},"hierarchyCode":"1.","internalCode":null,"isSoftwareHouse":false,"securityManager":false,"mainUser":null,"logo":null,"maxShortTokens":0}]
        Map resposta=new HashMap();
        resposta.put("id", usuario.getId());
        resposta.put("name", usuario.getNome());
        resposta.put("hierarchyCode", usuario.getPid());
        mapper.writeValue(response.getOutputStream(), new Object[]{resposta});
        
        
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        List<Usuario> todos = service.findAll();
        Usuario usuario=todos.get(0);
        mapper.writeValue(response.getOutputStream(), TokenAdapter.getLoginData(usuario));
        
    }

    @Override
    public String getServletInfo() {
        return "Responde por login";
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
        System.out.println("---->methodNotAllowed from " + request.getRemoteHost());
        response.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(), "Método não permitido");
    }


}
