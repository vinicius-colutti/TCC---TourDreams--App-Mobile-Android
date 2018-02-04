package br.com.tourdreams.app;

/**
 * Created by 15251365 on 28/09/2017.
 */

public class DadosQuarto {
    private int id_quarto;
    private int id_hotel;
    private int id_reserva;
    private int valor_total;
    private int dias_totais;
    private String telefone;
    private String nome_quarto;
    private String nome_hotel;
    private String numero_quarto;
    private String camas_solteiro;
    private String camas_casal;
    private String preco_quarto;
    private String nome_imagem;
    private Caracteristicas[] caracteristicas ;
    private String[] imagens;

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public int getId_quarto() {
        return id_quarto;
    }

    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNome_quarto() {
        return nome_quarto;
    }

    public void setNome_quarto(String nome_quarto) {
        this.nome_quarto = nome_quarto;
    }

    public String getNumero_quarto() {
        return numero_quarto;
    }

    public void setNumero_quarto(String numero_quarto) {
        this.numero_quarto = numero_quarto;
    }

    public String getCamas_solteiro() {
        return camas_solteiro;
    }

    public void setCamas_solteiro(String camas_solteiro) {
        this.camas_solteiro = camas_solteiro;
    }

    public String getCamas_casal() {
        return camas_casal;
    }

    public void setCamas_casal(String camas_casal) {
        this.camas_casal = camas_casal;
    }

    public String getPreco_quarto() {
        return preco_quarto;
    }

    public void setPreco_quarto(String preco_quarto) {
        this.preco_quarto = preco_quarto;
    }

    public String getNome_imagem() {
        return nome_imagem;
    }

    public void setNome_imagem(String nome_imagem) {
        this.nome_imagem = nome_imagem;
    }

    public Caracteristicas[] getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas[] caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String[] getImagens() {
        return imagens;
    }

    public void setImagens(String[] imagens) {
        this.imagens = imagens;
    }

    public int getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }

    public int getDias_totais() {
        return dias_totais;
    }

    public void setDias_totais(int dias_totais) {
        this.dias_totais = dias_totais;
    }

    public String getNome_hotel() {
        return nome_hotel;
    }

    public void setNome_hotel(String nome_hotel) {
        this.nome_hotel = nome_hotel;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }


}
