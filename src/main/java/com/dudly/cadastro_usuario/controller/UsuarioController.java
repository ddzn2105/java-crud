package com.dudly.cadastro_usuario.controller;

import com.dudly.cadastro_usuario.business.UsuarioService;
import com.dudly.cadastro_usuario.infrastructure.entitys.Usuario;
import com.dudly.cadastro_usuario.interfaces.UsuarioInterface;
import com.dudly.cadastro_usuario.security.SenhaSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioInterface usuarioInterface;
    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario){
        usuarioInterface.salvarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> validarLogin(@RequestParam String email, @RequestParam String senha){
        boolean senhaValida = usuarioInterface.validarLogin(email, senha);
        if(senhaValida){
            return ResponseEntity.ok("Login realizado com sucesso!");
        }
        else{
            return ResponseEntity.status(401).body("Senha invalida!");
        }

    }

    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email){
        return ResponseEntity.ok(usuarioInterface.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email){
        usuarioInterface.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/id")
    public ResponseEntity<Void> atualizarUsuarioPorId(@RequestParam Integer id, @RequestBody Usuario usuario){
        usuarioInterface.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/email")
    public ResponseEntity<Void> atualizarUsuarioPorEmail(@RequestParam String email, @RequestBody Usuario usuario){
        usuarioInterface.atualizarUsuarioPorEmail(email, usuario);
        return ResponseEntity.ok().build();
    }
}
