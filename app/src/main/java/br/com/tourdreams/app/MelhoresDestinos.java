package br.com.tourdreams.app;

/**
 * Created by 15251365 on 30/10/2017.
 */

public class MelhoresDestinos {
    private int id_hotel;
    private String nome_hotel;
    private String cidade_descricao;
    private String imagem_hotel_1;
    private String preco_quarto;

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNome_hotel() {
        return nome_hotel;
    }

    public void setNome_hotel(String nome_hotel) {
        this.nome_hotel = nome_hotel;
    }

    public String getCidade_hotel() {
        return cidade_descricao;
    }

    public void setCidade_hotel(String cidade_hotel) {
        this.cidade_descricao = cidade_hotel;
    }

    public String getImagem_hotel_1() {
        return imagem_hotel_1;
    }

    public void setImagem_hotel_1(String imagem_hotel_1) {
        this.imagem_hotel_1 = imagem_hotel_1;
    }

    public String getPreco_quarto() {
        return preco_quarto;
    }

    public void setPreco_quarto(String preco_quarto) {
        this.preco_quarto = preco_quarto;
    }

    /*public static MelhoresDestinos getDados(Context c){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);

        MelhoresDestinos dados = new MelhoresDestinos();
        dados.setId_hotel(preferences.getInt("idCategoria",id));

        return dados;
    }*/

}
