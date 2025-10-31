package com.senac.usuario.controller;

import com.senac.usuario.dto.UsuarioComPedidosDto; // NOVO DTO
import com.senac.usuario.dto.UsuarioRequest;
import com.senac.usuario.dto.UsuarioResponse;
import com.senac.usuario.entity.Usuario;
import com.senac.usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){this.usuarioService=usuarioService;}


    @GetMapping("/pedidos")
    public ResponseEntity<List<UsuarioComPedidosDto>> listarUsuariosComPedidos(){
        return ResponseEntity.ok(usuarioService.listarUsuariosComPedidos());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.ok(usuarioService.criarUsuario(usuarioRequest));
    }

    @GetMapping("/{id}/pedidos")
    public ResponseEntity<?> listarUsuarioComPedidos(@PathVariable Integer id) {
        try {
            UsuarioComPedidosDto usuario = usuarioService.buscarUsuarioComPedidos(id);
            return ResponseEntity.ok(usuario);
        } catch (NoSuchElementException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar usuário: " + e.getMessage());
        }
    }
}