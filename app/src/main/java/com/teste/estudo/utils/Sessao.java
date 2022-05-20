package com.teste.estudo.utils;

import com.teste.estudo.entidades.User;

public class Sessao {
    private static Sessao singleton = null;
    //Atributos
    private String nome;

    public User getUserLogado() {
        return userLogado;
    }

    public void setUserLogado(User userLogado) {
        this.userLogado = userLogado;
    }

    public User userLogado;
    private Sessao(){

    }
    public static Sessao getInstance(){
        if(singleton == null){
            singleton = new Sessao();
        }
        return singleton;
    }
}
