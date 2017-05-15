/*
 * Gerado automaticamente em 20/02/2017 01:37:30 por munif.
 */
package br.com.munif.personalsecurity.aplicacao.configuracao;

import br.com.munif.personalsecurity.entidades.Configuracao;
import br.com.impactit.framework.ImpactitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfiguracaoService extends ImpactitService<Configuracao> {

    @Autowired
    private ConfiguracaoRepository repository;

    @Autowired
    public ConfiguracaoService(JpaRepository<Configuracao, String> repository) {
        super(repository);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Configuracao> findAll() {
        List<Configuracao> result = repository.findAll();
        return result;
    }

    public List<Configuracao> listaFiltrando(String filtro) {
        return repository.findByEmailAdminOrderByEmailAdminAsc("%" + filtro + "%");
    }

}
