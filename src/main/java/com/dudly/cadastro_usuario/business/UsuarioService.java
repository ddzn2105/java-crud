package com.dudly.cadastro_usuario.business;


import com.dudly.cadastro_usuario.infrastructure.entitys.Usuario;
import com.dudly.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import com.dudly.cadastro_usuario.interfaces.UsuarioInterface;
import com.dudly.cadastro_usuario.security.SenhaSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioInterface{

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        usuario.setSenha(SenhaSecurity.encriptarSenha(usuario.getSenha()));
        repository.saveAndFlush(usuario);
    }

    public boolean validarLogin(String email, String senha){
        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return SenhaSecurity.verificarSenha(senha, usuario.getSenha());
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorEmail(String email, Usuario usuario){
        Usuario usuarioEntity = buscarUsuarioPorEmail(email);
        Usuario usuarioAtualizado = Usuario.builder()
                .email(email)
                .nome(usuario.getNome()!= null ? usuario.getNome() : usuarioEntity.getNome())
                .senha(usuario.getSenha()!= null ? SenhaSecurity.encriptarSenha(usuario.getSenha()) : usuarioEntity.getSenha())
                .id(usuarioEntity.getId())
                .build();
        repository.saveAndFlush(usuarioAtualizado);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
        Usuario usuarioEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail()!= null ? usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome()!= null ? usuario.getNome() : usuarioEntity.getNome())
                .senha(usuario.getSenha()!= null ? usuario.getSenha() : usuarioEntity.getSenha())
                .id(usuarioEntity.getId())
                .build();
        repository.saveAndFlush(usuarioAtualizado);
    }
}
