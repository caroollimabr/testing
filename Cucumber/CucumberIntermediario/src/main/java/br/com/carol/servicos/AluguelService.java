package br.com.carol.servicos;

import br.com.carol.entidades.Filme;
import br.com.carol.entidades.NotaAluguel;

public class AluguelService {
    public NotaAluguel alugar(Filme filme){
        NotaAluguel nota = new NotaAluguel();
        nota.setPreco(filme.getAluguel());
        return nota;
    }
}
