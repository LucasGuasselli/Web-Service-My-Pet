/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 8csm-guasselli
 */
public class TipoServico {
    private int codTipoServico;
    private String nome;
    private String descricao;
    private double valor;

    public TipoServico() {
        
    }

    
    
    

    public TipoServico(int codTipoServico, String nome, String descricao, double valor) {
        this.codTipoServico = codTipoServico;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getCodTipoServico() {
        return codTipoServico;
    }

    public void setCodTipoServico(int codTipoServico) {
        this.codTipoServico = codTipoServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
