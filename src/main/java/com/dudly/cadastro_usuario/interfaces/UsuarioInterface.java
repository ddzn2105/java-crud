package com.dudly.cadastro_usuario.interfaces;

import com.dudly.cadastro_usuario.infrastructure.entitys.Usuario;

public interface UsuarioInterface {
    void salvarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorEmail(String email);
    void deletarUsuarioPorEmail(String email);
    void atualizarUsuarioPorEmail(String email, Usuario usuario);
    void atualizarUsuarioPorId(Integer id, Usuario usuario);
    boolean validarLogin(String email, String senha);
}
