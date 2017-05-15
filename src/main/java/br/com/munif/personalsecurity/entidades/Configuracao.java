package br.com.munif.personalsecurity.entidades;

import br.com.impactit.framework.ImpactitEntity;
import javax.persistence.Entity;

@Entity
public class Configuracao extends ImpactitEntity {

    public static final String SINGLETON = "singleton";

    private String emailAdmin;

    public Configuracao() {
        this.id = SINGLETON;
        emailAdmin="munifgebara@gmail.com";
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

}
