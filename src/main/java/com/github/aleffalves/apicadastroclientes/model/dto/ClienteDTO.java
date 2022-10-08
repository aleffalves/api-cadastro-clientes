package com.github.aleffalves.apicadastroclientes.model.dto;

import com.github.aleffalves.apicadastroclientes.enums.TipoPessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDTO {

    private Integer id;
    private String nome;
    private TipoPessoa tipo;
    private String cpfCnpj;
    private String rg;
    private Date dataCadastro;
    private Boolean ativo;
    private List<ClienteTelefoneDTO> telefones = new ArrayList<>();

    public ClienteDTO() {
    }

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

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<ClienteTelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<ClienteTelefoneDTO> telefones) {
        this.telefones = telefones;
    }
}
