package br.com.tourdreams.app;

/**
 * Created by Ellen on 07/10/2017.
 */

public class Hotel extends base{

    private double preco;
    private String local;

    //construtor da classe
    public Hotel(String nome, double preco, String local, int imagem){
        super(nome,imagem);
        this.preco = preco;
        this.local = local;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
