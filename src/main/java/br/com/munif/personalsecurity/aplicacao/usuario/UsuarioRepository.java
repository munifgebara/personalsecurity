package br.com.munif.personalsecurity.aplicacao.usuario;

import br.com.munif.personalsecurity.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    List<Usuario> findAllByOrderByNomeAsc();
    
    List<Usuario> findByNomeLikeOrEmailLikeOrderByNomeAsc(String nome,String email);
    
    
}
