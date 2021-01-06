/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.ud1.dom;

import java.io.Serializable;

/**
 *
 * @author Esther Ferreiro
 */
public class Persona implements Serializable {

    private String nome;
    private int idade;

    public Persona(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public Persona() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        nome = name;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int age) {
        idade = age;
    }
}
