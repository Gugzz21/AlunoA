package com.senac.usuario.service;

import com.senac.usuario.dto.PedidoDto;
import com.senac.usuario.dto.UsuarioComPedidosDto; // Importado o novo DTO
import com.senac.usuario.dto.UsuarioRequest;
import com.senac.usuario.dto.UsuarioResponse;
import com.senac.usuario.entity.Usuario;
import com.senac.usuario.repository.PedidoFeignClient;
import com.senac.usuario.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PedidoFeignClient pedidoFeignClient;

    public UsuarioService(UsuarioRepository usuarioRepository, PedidoFeignClient pedidoFeignClient){
        this.usuarioRepository = usuarioRepository;
        this.pedidoFeignClient = pedidoFeignClient;
    }


    public List<UsuarioComPedidosDto> listarUsuariosComPedidos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::montarUsuarioComPedidos)
                .collect(Collectors.toList());
    }

    private UsuarioComPedidosDto montarUsuarioComPedidos(Usuario usuario) {
        UsuarioComPedidosDto dto = new UsuarioComPedidosDto();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setStatus(usuario.getStatus());

        try {
            ResponseEntity<List<PedidoDto>> response = pedidoFeignClient.buscarPedidosPorUsuario(usuario.getId());
            if (response.getStatusCode() == HttpStatus.OK) {
                dto.setPedidos(response.getBody());
            } else {
                dto.setPedidos(List.of());
            }
        } catch (Exception e) {
            dto.setPedidos(List.of());
        }

        return dto;
    }


    public Usuario listarPorId(Integer id){

        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado: " + id)
        );
    }
    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest){
        Usuario usuarioSalvo = new Usuario();

        usuarioSalvo.setCpf(usuarioRequest.getCpf());
        usuarioSalvo.setNome(usuarioRequest.getNome());
        usuarioSalvo.setStatus(usuarioRequest.getStatus());

        Usuario usuarioTemp = usuarioRepository.save(usuarioSalvo);

        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuarioTemp.getId());
        usuarioResponse.setCpf(usuarioTemp.getCpf());
        usuarioResponse.setNome(usuarioTemp.getNome());
        usuarioResponse.setStatus(usuarioTemp.getStatus());

        return usuarioResponse;
    }
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
    public UsuarioComPedidosDto buscarUsuarioComPedidos(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + id));
        List<PedidoDto> pedidos = new ArrayList<>();

        try {
            ResponseEntity<List<PedidoDto>> response = pedidoFeignClient.buscarPedidosPorUsuario(id);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                pedidos = response.getBody();
            }
        } catch (Exception e) {
            System.err.println("Falha ao comunicar com o serviço de pedidos para o usuário " + id + ": " + e.getMessage());
        }

        UsuarioComPedidosDto dto = new UsuarioComPedidosDto();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setStatus(usuario.getStatus());
        dto.setPedidos(pedidos);
        return dto;
    }
}