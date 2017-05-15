/*
 * Gerado automaticamente em 20/02/2017 01:37:30 por munif.
 */
package br.com.munif.personalsecurity.aplicacao.configuracao;

import br.com.munif.personalsecurity.entidades.Configuracao;
import br.com.impactit.framework.ImpactitApi;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConfiguracaoAPI", urlPatterns = {"/api/configuracao/*"})
public class ConfiguracaoAPI extends ImpactitApi<Configuracao> {

    @Override
    public Class<Configuracao> getClazz() {
        return Configuracao.class;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        methodNotAllowed(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        methodNotAllowed(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        methodNotAllowed(request, response);

    }

}
