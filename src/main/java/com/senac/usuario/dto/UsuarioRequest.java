package com.senac.usuario.dto;

import com.senac.usuario.entity.Pedido;

import java.util.Set;

public class UsuarioRequest {

    private String nome;

    private String cpf;

    private Integer status;

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


}
