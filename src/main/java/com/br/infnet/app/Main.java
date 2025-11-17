package com.br.infnet.app;


import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
        }).start(7000);
    }
}