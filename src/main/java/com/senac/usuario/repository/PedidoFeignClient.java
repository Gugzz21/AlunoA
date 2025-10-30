package com.senac.usuario.repository;

import com.senac.usuario.dto.PedidoDto;
import com.senac.usuario.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "ObterPedidos", url = "192.168.144.1:8080")
public interface PedidoFeignClient {

    @GetMapping(value = "/api/pedido/buscar/{id}")
    public ResponseEntity<PedidoDto> encontrarPodID(@PathVariable Integer id);

}
