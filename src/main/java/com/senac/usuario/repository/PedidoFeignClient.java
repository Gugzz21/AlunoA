package com.senac.usuario.repository;

import com.senac.usuario.dto.PedidoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "ObterPedidos", url = "http://10.136.36.205:8080")
public interface PedidoFeignClient {

    @GetMapping(value = "/api/pedido/listar/usuario/{id}")
    ResponseEntity<List<PedidoDto>> buscarPedidosPorUsuario(@PathVariable("id") Integer id);
}
