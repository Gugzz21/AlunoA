package com.senac.usuario.dto;

import java.util.List;


public class UsuarioComPedidosDto extends UsuarioResponse {
    private List<PedidoDto> pedidos;
    public List<PedidoDto> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<PedidoDto> pedidos) {
        this.pedidos = pedidos;
    }
}