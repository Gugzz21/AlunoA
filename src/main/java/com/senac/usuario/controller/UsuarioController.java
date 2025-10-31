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
@CrossOrigin(origins = "*") // ANOTAÇÃO DEVE IR NA CLASSE
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){this.usuarioService=usuarioService;}

    // REQUISITO: Endpoint ConsultarPedidosDosUsuarios
    @GetMapping("/pedidos") // Novo endpoint consolidado
    public ResponseEntity<List<UsuarioComPedidosDto>> listarUsuariosComPedidos(){
        return ResponseEntity.ok(usuarioService.listarUsuariosComPedidos());
    }

    // Endpoint original (listar todos)
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // Endpoint listar por ID (retorna apenas o Usuário)
    // CORREÇÃO: Chamada simplificada, o Service retorna a Entidade Usuario diretamente.
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    // Endpoint CadastrarUsuario
    @PostMapping("/criar") // Ajustei o nome do endpoint para /cadastrar (melhor prática)
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.ok(usuarioService.criarUsuario(usuarioRequest));
    }

    @GetMapping("/{id}/pedidos") // Ex: /api/usuario/1/pedidos
    public ResponseEntity<?> listarUsuarioComPedidos(@PathVariable Integer id) {
        try {
            UsuarioComPedidosDto usuario = usuarioService.buscarUsuarioComPedidos(id);
            return ResponseEntity.ok(usuario);
        } catch (NoSuchElementException e) {
            // Retorna 404 Not Found se o usuário não for encontrado no BD local
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Trata outros erros (ex: erro de sistema)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao buscar usuário: " + e.getMessage());
        }
    }
}