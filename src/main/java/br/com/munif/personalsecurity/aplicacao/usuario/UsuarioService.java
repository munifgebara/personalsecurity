package br.com.munif.personalsecurity.aplicacao.usuario;

import br.com.munif.personalsecurity.entidades.Usuario;
import br.com.impactit.framework.ImpactitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService extends ImpactitService<Usuario> {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    public UsuarioService(JpaRepository<Usuario, String> repository) {
        super(repository);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        List<Usuario> result = repository.findAllByOrderByNomeAsc();
        return result;
    }

    public List<Usuario> listaFiltrando(String filtro) {
        return repository.findByNomeLikeOrEmailLikeOrderByNomeAsc("%" + filtro + "%", "%" + filtro + "%");
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            Usuario qs = repository.findOne(usuario.getId());
            if (qs != null) {
                usuario.setSenha(qs.getSenha());
            }
        }
        return super.save(usuario); //To change body of generated methods, choose Tools | Templates.
    }

}
