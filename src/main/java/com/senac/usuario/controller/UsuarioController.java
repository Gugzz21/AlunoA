package com.senac.usuario.controller;

import com.senac.usuario.dto.UsuarioRequest;
import com.senac.usuario.dto.UsuarioResponse;
import com.senac.usuario.entity.Usuario;
import com.senac.usuario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){this.usuarioService=usuarioService;}
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/listar/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(usuarioService.listarPorId(id).getBody());
    }

    @PostMapping("/cadastrarUsuario")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.ok(usuarioService.criarUsuario(usuarioRequest));
    }



}
