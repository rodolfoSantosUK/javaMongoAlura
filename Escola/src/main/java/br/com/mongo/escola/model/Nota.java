package br.com.mongo.escola.model;

public class Nota {

    private  Double valor;

    public Nota(Double valor) {
        this.valor = valor;
    }

    public Nota() {

    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
