/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.impactit.framework;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Classe pai para todos os controaldores de relatorios, pois essa classe possui
 * caracteristicas comuns entre (quase) todos os relatorios como por exemplo
 * data inicialFinal e uma lista da classes <T>.
 *
 * @param <T> qualquer classe, por exemplo, uma classe de entidade que extenda
 * EntidadePadrao ou um TO, para que possa o controlador possa gerar um atributo
 * lista com ele.
 *
 * @author anderson
 */
public abstract class RelatorioControladorAbastract<T extends Object> {

    protected Date dataInicial;
    protected Date dataFinal;
    protected List<T> lista;

    public RelatorioControladorAbastract() {
        init();
    }

    /**
     * Metodo que inicializa os campos basicos de um relatório.
     *
     */
    private void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getAutowireCapableBeanFactory().autowireBean(this);

        dataInicial = DataUtil.dataInicial();
        dataFinal = DataUtil.dataFinal();
        lista = new ArrayList<>();
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    /**
     * É necessário que a classe que for estender RelatorioControladorSuper
     * implemente o metodo filtrar, para que haja uma maior padronização de
     * código. Esse metodo em geral será utilizado para filtrar (gerar) o
     * relatorio solicitado.
     */
    public abstract void filtrar();

}
