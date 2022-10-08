package com.github.aleffalves.apicadastroclientes.model;

import com.github.aleffalves.apicadastroclientes.enums.TipoPessoa;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @NotBlank
    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private TipoPessoa tipo;

    @NotBlank
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @NotBlank
    @Column(name = "rg")
    private String rg;

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Column(name = "ativo")
    private Boolean ativo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private List<ClienteTelefone> telefones = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
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

    public List<ClienteTelefone> getTelefones() {
        return telefones;
    }

}
