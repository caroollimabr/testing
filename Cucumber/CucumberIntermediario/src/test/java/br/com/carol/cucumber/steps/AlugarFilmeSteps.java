package br.com.carol.cucumber.steps;

import br.com.carol.entidades.Filme;
import br.com.carol.entidades.NotaAluguel;
import br.com.carol.servicos.AluguelService;
import br.com.carol.utils.DateUtils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlugarFilmeSteps {
    private Filme filme;
    private AluguelService aluguel = new AluguelService();
    private NotaAluguel nota;
    private String erro;
    private String tipoAluguel;

    @Dado("um filme com estoque de {int} unidades")
    public void umFilmeComEstoqueDeUnidades(int estoque) throws Throwable{
        filme = new Filme();
        filme.setEstoque(estoque);
    }
    @Dado("o preço de aluguel seja R${int}")
    public void oPreçoDeAluguelSejaR$(int valor) throws Throwable{
        filme.setAluguel(valor);
    }
    @Quando("eu alugar")
    public void euAlugar() throws Throwable{
        try{
            nota = aluguel.alugar(filme, tipoAluguel);
        } catch (RuntimeException e){
            erro = e.getMessage();
        }

    }

    @Então("o preço de aluguel será R${int}")
    public void oPreçoDeAluguelSeráR$(int precoAluguel) throws Throwable {
        Assert.assertEquals(precoAluguel, nota.getPreco());
    }
    @Então("a data de entrega será em {int} dias")
    public void aDataDeEntregaSeráEm(int dias) throws Throwable{
        Date dataEsperada = DateUtils.obterDataDiferencaDias(dias);
        Date dataReal = nota.getDataEntrega();

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
    }
    @Então("o estoque do filme será {int} unidades")
    public void oEstoqueDoFilmeSeráUnidade(int unidades) throws Throwable{
        Assert.assertEquals(unidades, filme.getEstoque());
    }

    @Então("não será possível por falta de estoque")
    public void nãoSeráPossívelPorFaltaDeEstoque() throws Throwable{
        Assert.assertEquals("Filme sem estoque", erro);
    }

    @Dado("o tipo de aluguel seja {string}")
    public void oTipoDeAluguelSeja(String tipo) throws Throwable{
        tipoAluguel = tipo;
    }

    @Então("a pontuação recebida será de {int} pontos")
    public void aPontuaçãoRecebidaSeráDePontos(int pontos) throws Throwable{
        Assert.assertEquals(pontos, nota.getPontuacao());
    }

}
