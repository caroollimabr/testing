package br.com.carol.servicos;

import br.com.carol.entidades.Filme;
import br.com.carol.entidades.NotaAluguel;
import br.com.carol.utils.DateUtils;

import java.util.Calendar;

public class AluguelService {
    public NotaAluguel alugar(Filme filme, String tipoAluguel){
        if (filme.getEstoque() == 0){
            throw new RuntimeException("Filme sem estoque");
        }

        NotaAluguel nota = new NotaAluguel();
        if("estendido".equals(tipoAluguel)){
            nota.setPreco(filme.getAluguel() * 2);
            nota.setDataEntrega(DateUtils.obterDataDiferencaDias(3));
            nota.setPontuacao(2);
        } else {
            nota.setPreco(filme.getAluguel());
            nota.setDataEntrega(DateUtils.obterDataDiferencaDias(1));
            nota.setPontuacao(1);
        }

        filme.setEstoque(filme.getEstoque() - 1);
        return nota;
    }
}
