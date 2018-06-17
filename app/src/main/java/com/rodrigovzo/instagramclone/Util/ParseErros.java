package com.rodrigovzo.instagramclone.Util;

import java.util.HashMap;

public class ParseErros {

    private HashMap<Integer, String> erros;

    public ParseErros() {
        this.erros = new HashMap<>();
        this.erros.put(202, "Usuário já existe, escolha um novo nome de cadastro");
        this.erros.put(201, "A senha não foi preenchida");
        this.erros.put(203, "E-Mail já foi utilizado!");
    }

    public String getErro(Integer codigoErro){
        return this.erros.get(codigoErro).toString();
    }
}

