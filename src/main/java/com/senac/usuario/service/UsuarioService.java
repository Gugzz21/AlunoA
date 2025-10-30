package com.senac.usuario.service;

import com.senac.usuario.dto.PedidoDto;
import com.senac.usuario.dto.UsuarioRequest;
import com.senac.usuario.dto.UsuarioResponse;
import com.senac.usuario.entity.Usuario;
import com.senac.usuario.repository.PedidoFeignClient;
import com.senac.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final PedidoFeignClient pedidoFeignClient;

    public UsuarioService(UsuarioRepository usuarioRepository, PedidoFeignClient pedidoFeignClient){
        this.usuarioRepository = usuarioRepository;
        this.pedidoFeignClient = pedidoFeignClient;
    }



    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest){

        Usuario usuarioSalvo = new Usuario();

        usuarioSalvo.setCpf(usuarioRequest.getCpf());
        usuarioSalvo.setNome(usuarioRequest.getNome());
        usuarioSalvo.setStatus(usuarioRequest.getStatus());

        Usuario usuarioTemp = usuarioRepository.save(usuarioSalvo);
        UsuarioResponse usuarioResponse = new UsuarioResponse();

        usuarioSalvo.setCpf(usuarioTemp.getCpf());
        usuarioSalvo.setNome(usuarioTemp.getNome());
        usuarioSalvo.setStatus(usuarioTemp.getStatus());


        return usuarioResponse;
    }

    public ResponseEntity<PedidoDto> listarPorId(Integer id){
        return pedidoFeignClient.encontrarPodID(id);
    }

}
