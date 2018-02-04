package br.com.tourdreams.app;

/**
 * Created by Ellen on 22/10/2017.
 */

public class base {
    private String nome;
    private int imagem;

    //construtor da classe
    public base(String nome, int imagem){
        this.nome = nome;
        this.imagem = imagem;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
