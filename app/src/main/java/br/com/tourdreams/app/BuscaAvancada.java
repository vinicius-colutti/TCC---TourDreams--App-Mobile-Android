package br.com.tourdreams.app;

/**
 * Created by Ellen on 19/10/2017.
 */

public class BuscaAvancada {

    private String descricao;
    private int imagem;


    public BuscaAvancada(int imagem,String descricao){

        this.imagem = imagem;
        this.descricao = descricao;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
