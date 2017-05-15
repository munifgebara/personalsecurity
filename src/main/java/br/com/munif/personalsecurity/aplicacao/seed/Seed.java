package br.com.munif.personalsecurity.aplicacao.seed;


import br.com.munif.personalsecurity.aplicacao.configuracao.ConfiguracaoService;

import br.com.munif.personalsecurity.aplicacao.usuario.UsuarioService;
import br.com.munif.personalsecurity.entidades.Configuracao;
import br.com.munif.personalsecurity.entidades.Usuario;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Seed implements ApplicationListener<ContextRefreshedEvent> {

    private AtomicBoolean started = new AtomicBoolean(false);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ConfiguracaoService configuracaoService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        if (started.get()) {
            return;
        }
        System.out.println("----- VERIFICANDO DADOS INICIAIS --------------");
        started.set(true);

        verificaAcionaAdmin();

    }

    private void verificaAcionaAdmin() {
        System.out.println("----- VERIFICANDO USUARIOS --------------");
        if (usuarioService.findAll().isEmpty()) {
            System.out.println("----- INSERINDO USUARIO PADRÃO --------------");
            Usuario usuario = new Usuario();
            usuario.setEmail("munifgebara@gmail.com");
            usuario.setNome("admin");
            usuario.setSenha("qwe123");
            usuario.setPerfil("ADMIN");
            usuario.setTi(usuario.getPid());
            usuarioService.save(usuario);
            Configuracao configuracao=new Configuracao();
            configuracao.setEmailAdmin(usuario.getEmail());
            configuracao.setTi(usuario.getPid());
            configuracaoService.save(configuracao);
            
        } else {
            System.out.println("----- BASE COM USUÁRIOS --------------");
        }
    }

}
