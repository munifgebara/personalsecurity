package br.com.impactit.framework;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("prototype")
public abstract class ImpactitService<T> {

    protected final JpaRepository<T, String> repository;

    protected final ObjectMapper mapper;

    protected final SimpleDateFormat dateFormat;

    @PersistenceContext
    protected EntityManager em;

    public ImpactitService(JpaRepository<T, String> repository) {
        this.repository = repository;
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        mapper.setDateFormat(dateFormat);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        List<T> result = repository.findAll();
        return result;
    }

    public abstract List<T> listaFiltrando(String q);

    @Transactional(readOnly = true)
    public T view(String id) {
        T entity = repository.findOne(id);
        return entity;
    }

    @Transactional
    public void delete(T resource) {
        repository.delete(resource);
    }

    @Transactional
    public T save(T resource) {
        T entity = repository.save(resource);

        return entity;
    }

    public void forceFlush() {
        repository.flush();
    }

    @Transactional(readOnly = true)
    public List<T> find(Class classe, String hql, int maxResults) {
        String q = "from " + classe.getSimpleName() + " obj where " + hql;
        Query query = em.createQuery(q);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<T> find10primeiros(Class classe, String hql) {
        return find(classe, hql, 10);
    }


}
