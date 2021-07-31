package br.com.carol.entidades;

public class Filme {
    private int estoque;
    private int aluguel;

    public void setEstoque(int estoque){
        this.estoque = estoque;
    }

    public void setAluguel(int valor) {
        this.aluguel = valor;
    }

    public int getAluguel() {
        return aluguel;
    }
}
