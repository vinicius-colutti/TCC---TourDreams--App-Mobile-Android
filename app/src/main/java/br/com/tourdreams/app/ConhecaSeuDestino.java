package br.com.tourdreams.app;

/**
 * Created by 15251365 on 16/10/2017.
 */

public class ConhecaSeuDestino extends  Hotel {
    public ConhecaSeuDestino(String nome, double preco, String local, int imagem, String comentario) {
        super(nome, preco, local, imagem);
        this.comentario = comentario;
    }

    private String comentario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
