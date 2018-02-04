package br.com.tourdreams.app;

/**
 * Created by 15251365 on 28/09/2017.
 */

public class Caracteristicas {
    private String descricao;
    private boolean possui;
    private int imagem;

    public Caracteristicas(String descricao,boolean possui,int imagem){
        this.descricao = descricao;
        this.possui = possui;
        this.imagem=imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPossui() {
        return possui;
    }

    public void setPossui(boolean possui) {
        this.possui = possui;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}