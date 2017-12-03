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
public class Servico {
    
    private int CodServico;
    private Cliente cliente;
    private int codCliente; 
    private String nomePet;
    private int dia;
    private String horario;
    private Funcionario funcionario;
    private int codFunc;
    private TipoServico tipoServico;
    private int codTipoServico;
    private double valorServico;
    private String observacao;
    //private boolean status = false;

    public Servico() {
    }

    public Servico(int CodServico, Cliente cliente, String nomePet, int dia, String horario, 
            Funcionario funcionario, TipoServico tipoServico, String observacao) {
        this.CodServico = CodServico;
        this.cliente = cliente;
        this.codCliente = cliente.getCodCliente();
        this.nomePet = nomePet;
        this.dia = dia;
        this.horario = horario;
        this.funcionario = funcionario;
        this.codFunc = funcionario.getCodFunc();
        this.tipoServico = tipoServico;
        this.codTipoServico = tipoServico.getCodTipoServico();
        this.valorServico = tipoServico.getValor();
        this.observacao = observacao;
    }

    
    public int getCodServico() {
        return CodServico;
    }

    public void setCodServico(int CodServico) {
        this.CodServico = CodServico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(int codFunc) {
        this.codFunc = codFunc;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public int getCodTipoServico() {
        return codTipoServico;
    }

    public void setCodTipoServico(int codTipoServico) {
        this.codTipoServico = codTipoServico;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
/*
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
*/
   
    
    
    
    
}
