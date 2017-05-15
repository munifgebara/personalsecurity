/*
 * Gerado automaticamente em 20/02/2017 01:37:30 por munif.
 */
package br.com.munif.personalsecurity.aplicacao.configuracao;

import br.com.munif.personalsecurity.entidades.Configuracao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, String>{
    
    List<Configuracao> findByEmailAdminOrderByEmailAdminAsc(String emailAdmin);
    
    
}
