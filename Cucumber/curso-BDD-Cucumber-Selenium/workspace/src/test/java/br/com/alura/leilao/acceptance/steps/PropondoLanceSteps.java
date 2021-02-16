package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.qos.logback.core.BasicStatusManager;
import org.junit.Assert;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {
    private Lance lance;
    private Usuario usuario;
    private Leilao leilao;
    private ArrayList<Lance> lista;

    @Before // roda antes de testes de outras classes
    public void setup(){
        this.lista = new ArrayList<Lance>();
        leilao = new Leilao("Tablet Bla bla");
    }

    @After // roda depois de testes de outras classes
    public void tearDown(){
        System.out.println("after");
    }

    @Dado("um lance valido")
    public void um_lance_valido() {
        Usuario usuario = new Usuario("fulano");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @Dado("um lance de {double} reais do usuario {string}")
    public void um_lance_de_reais_do_usuario(Double valor, String nomeUsuario) {
        Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
        lista.add(lance);
    }

    @Quando("propoe ao leilao")
    public void propoe_ao_leilao() {
        leilao.propoe(lance);
    }
    @Entao("o lance e aceito")
    public void o_lance_e_aceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }

    @Quando("propoe varios lances ao leilao")
    public void propoe_varios_lances_ao_leilao() {
        this.lista.forEach(lance -> leilao.propoe(lance));
    }

    @Entao("os lances sao aceitos")
    public void os_lances_sao_aceitos() {
        Assert.assertEquals(this.lista.size(), leilao.getLances().size());
        Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
        Assert.assertEquals(this.lista.get(1).getValor(), leilao.getLances().get(1).getValor());
    }

    @Dado("um lance invalido de {double} reais do usuario {string}")
    public void um_lance_invalido_de_reais_do_usuario(Double valor, String nomeUsuario) {
        this.lance = new Lance(new BigDecimal(valor));
        this.usuario = new Usuario(nomeUsuario);
    }

    @Dado("dois lances")
    public void dois_lances(DataTable dataTable) {
        List<Map<String, String>>valores = dataTable.asMaps(); //maps = dicionario
        for (Map<String, String> mapa: valores) {
            String valor = mapa.get("valor");
            String nome = mapa.get("nomeUsuario");
            Lance lance = new Lance(new Usuario(nome), new BigDecimal(valor));
            lista.add(lance);

            //System.out.println(mapa.keySet());
        }
    }

    @Entao("o lance nao e aceito")
    public void o_lance_nao_e_aceito() {
        Assert.assertEquals(0, leilao.getLances().size());
    }

    @Entao("o segundo lance nao e aceito")
    public void o_segundo_lance_nao_e_aceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(this.lista.get(0).getValor(), leilao.getLances().get(0).getValor());
    }
}
