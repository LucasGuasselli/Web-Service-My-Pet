/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @since 18/11/2017
 */

@XmlRootElement
public class Funcionario implements Serializable {
    
    private int codFunc;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private String especialidade;

    public Funcionario() {
    }

    public Funcionario(int codFunc, String nome, String cpf, String telefone, LocalDate dataNascimento, String especialidade) {
        this.codFunc = codFunc;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.especialidade = especialidade;
    }

    
    public int getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(int codigo) {
        this.codFunc = codigo;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    
    
}
