package br.com.impactit.framework.future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface ImpactitRepository<T> extends JpaRepository<T, String>, Repository<T, String> {


}
