package com.senac.usuario.repository;

import com.senac.usuario.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Component
@FeignClient(name = "ObterPedidos", url = "192.168.144.1")
public interface ObterPedidosPorUsuario {

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity<Usuario> encontrarPodID(@PathVariable Integer id);

}
