package br.com.impactit.framework;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.Version;

@MappedSuperclass
public class ImpactitEntity implements Serializable {

    @Id
    protected String id;

    protected String ti;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date cd;

    @Version
    private Integer version;

    public ImpactitEntity() {
        this.id = UIDHelper.getUID();
        this.ti = "PUBLIC";
        this.cd = new Date();
    }

    public Date getCd() {
        return cd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTi() {
        return ti;
    }

    public void setTi(String ti) {
        this.ti = ti;
    }

    public Integer getVersion() {
        return version;
    }
 
    @Override
    public String toString() {
        return "ImpactitEntity{" + "id=" + id + ", ti=" + ti + ", cd=" + cd + ", version=" + version + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ImpactitEntity)) {
            return false;
        }
        final ImpactitEntity other = (ImpactitEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

 
}
