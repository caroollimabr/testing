package br.com.carol.cucumber.steps;

import br.com.carol.entidades.Filme;
import br.com.carol.entidades.NotaAluguel;
import br.com.carol.servicos.AluguelService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.util.Calendar;
import java.util.Date;

public class AlugarFilmeSteps {
    private Filme filme;
    private AluguelService aluguel = new AluguelService();
    private NotaAluguel nota;

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
        nota = aluguel.alugar(filme);
    }

    @Então("o preço de aluguel será R${int}")
    public void oPreçoDeAluguelSeráR$(int precoAluguel) throws Throwable {
        Assert.assertEquals(precoAluguel, nota.getPreco());
    }
    @Então("a data de entrega será no dia seguinte")
    public void aDataDeEntregaSeráNoDiaSeguinte() throws Throwable{
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date dataRetorno = nota.getDataEntrega();
    }
    @Então("o estoque do filme será {int} unidade")
    public void oEstoqueDoFilmeSeráUnidade(Integer int1) throws Throwable{

    }
}
