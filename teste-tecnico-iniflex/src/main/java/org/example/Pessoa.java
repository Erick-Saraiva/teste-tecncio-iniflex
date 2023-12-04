package org.example;

import java.time.LocalDate;

public class Pessoa {
    String nome;
    LocalDate dataNascimento;

    Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
}