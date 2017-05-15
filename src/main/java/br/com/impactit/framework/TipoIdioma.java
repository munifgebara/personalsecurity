package br.com.impactit.framework;

/**
 *
 * @author anderson
 */
public enum TipoIdioma {

    pt_BR("Português (Brasil)"),
    en_US("Inglês (EUA)"),
    es_ES("Espanhol (Espanha)"),
    de_DE("Alemão");

    private String descricao;

    private TipoIdioma(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String retornarConstante(TipoIdioma tipo) {
        return tipo.name();
    }

}
