package com.senac.usuario.dto;

import com.senac.usuario.entity.Pedido;
import jakarta.persistence.*;

import java.util.Set;

public class UsuarioResponse {

    private Integer id;

    private String nome;

    private String cpf;

    private Integer status;

    private Pedido pedidos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Pedido getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedido pedidos) {
        this.pedidos = pedidos;
    }
}
