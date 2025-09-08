package com.dudly.cadastro_usuario.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SenhaSecurity {
    @Autowired
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encriptarSenha(String senha){
        return encoder.encode(senha);
    }

    public static boolean verificarSenha(String senha, String hash){
        return encoder.matches(senha, hash);
    }
}
