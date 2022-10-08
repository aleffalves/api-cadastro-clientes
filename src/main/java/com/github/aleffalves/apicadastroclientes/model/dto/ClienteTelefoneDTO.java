package com.github.aleffalves.apicadastroclientes.model.dto;

public class ClienteTelefoneDTO {

    private Integer id;
    private Integer clienteId;
    private String telefone;

    public ClienteTelefoneDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
