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
 *
 * @author munif
 */
///                                                security-api/publicoperations/token/authorize/br.com.gumgademo.exemplo/no token/127.0.0.1/ALL_OPERATIONS/
@WebServlet(name = "Autorizador", urlPatterns = {"/security-api/publicoperations/token/authorize/*"})
public class Autorizador extends HttpServlet {

    protected ObjectMapper mapper;

    protected SimpleDateFormat dateFormat;

    @Autowired
    protected UsuarioService service;

    protected String url;

    public Autorizador() {

        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        mapper.setDateFormat(dateFormat);
        url = "/security-api/publicoperations/token/authorize";

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] urlParameters = getUrlParameters(request);
        System.out.println("---->" + request.getRequestURL());
        System.out.println("---->" + Arrays.toString(urlParameters));
        response.setContentType("application/json;charset=UTF-8");
        Usuario usuario = service.view(urlParameters[1]);
        mapper.writeValue(response.getOutputStream(), TokenAdapter.getAutorizationData(usuario,urlParameters[3]));

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
