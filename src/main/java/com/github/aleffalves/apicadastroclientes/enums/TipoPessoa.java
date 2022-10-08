package com.github.aleffalves.apicadastroclientes.enums;

public enum TipoPessoa {
    FISICA("Fisica"),
    JURIDICA("Juridica");

    private String value;

    TipoPessoa(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
