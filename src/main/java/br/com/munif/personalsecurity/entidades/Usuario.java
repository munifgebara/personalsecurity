package br.com.munif.personalsecurity.entidades;

import br.com.impactit.framework.ImpactitEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends ImpactitEntity {

    @Column(length = 50)
    protected String nome;
    @Column(length = 50)
    protected String email;
    @Column(length = 50)
    protected String pid;
    @JsonIgnore
    @Column(length = 50)
    protected String senha;
    @Column(length = 20)
    protected String perfil;

    public Usuario() {
        perfil = "USUARIO";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        setPid();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String willBeIgnored) {
        setPid();
    }

    public void setPid() {
        if (pid == null) {
            this.pid = email != null ? (email.replaceAll("\\.", "_") + ".") : null;
        }
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + ", pi=" + pid + ", senha=" + senha + ", perfil=" + perfil + '}';
    }

    

}
