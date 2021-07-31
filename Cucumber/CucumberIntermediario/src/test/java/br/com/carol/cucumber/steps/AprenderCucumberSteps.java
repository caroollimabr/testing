package br.com.carol.cucumber.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AprenderCucumberSteps {

    private int contador = 0;
    Date entrega = new Date();
    Date data = new Date();

    @Dado("que criei o arquivo corretamente")
    public void queCrieiOArquivoCorretamente() throws Throwable{
    }

    @Dado("que o valor do contador é {int}")
    public void queOValorDoContadorÉ(int arg1) throws Throwable{
        contador = arg1;
    }

    @Quando("eu incrementar em {int}")
    public void euIncrementarEm(int arg1) throws Throwable{
        contador = contador + arg1;
    }

    @Então("o valor do contador será {int}")
    public void oValorDoContadorSerá(int arg1) throws Throwable{
        System.out.println(arg1);
        System.out.println(contador);
        //Assert.assertTrue(arg1 == contador);
        Assert.assertEquals(arg1, contador);
    }

    @Dado("que a entrega é {int}\\/{int}\\/{int}")
    public void queAEntregaE(int dia, int mes, int ano) throws Throwable{
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dia);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.YEAR, ano);
        entrega = cal.getTime();
    }

    @Quando("a entrega atrasar {int} {string}")
    public void aEntregaAtrasar(int qte, String tempo) throws Throwable{
        Calendar cal = Calendar.getInstance();
        cal.setTime(entrega);
        if (tempo.equals("dia") || tempo.equals("dias")) {
            cal.add(Calendar.DAY_OF_MONTH, qte);
        }
        if (tempo.equals("meses") || tempo.equals("mês")){
            cal.add(Calendar.MONTH, qte);
        }
        entrega = cal.getTime();
    }

    @Então("a entrega será efetuada em {int}\\/{int}\\/{int}")
    public void aEntregaSeraEfetuadaEm(int dia, int mes, int ano) throws Throwable{
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dia);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.YEAR, ano);
        data = cal.getTime();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataEntregaFormatada = format.format(entrega);
        String dataFormatada = format.format(data);
        Assert.assertEquals(dataFormatada, dataEntregaFormatada);
    }

    @Dado("que o ticket {string} é {string}")
    public void queOTicketÉ(String string, String string2) throws Throwable{

    }

    @Dado("que o valor da passagem é R$ {string}")
    public void queOValorDaPassagemÉR$(String string) throws Throwable{

    }

    @Dado("que o nome do passageiro é {string}")
    public void queONomeDoPassageiroÉ(String string) throws Throwable{

    }

    @Dado("que o telefone do passageiro é {string}")
    public void queOTelefoneDoPassageiroÉ(String string) throws Throwable{

    }

    @Quando("eu executá-lo")
    public void euExecutáLo() throws Throwable{

    }

    @Quando("criar os steps")
    public void criarOsSteps() throws Throwable{

    }

    @Então("a especificação deve finalizar com sucesso")
    public void aEspecificaçãoDeveFinalizarComSucesso() throws Throwable{

    }

    @Então("o teste vai funcionar")
    public void oTesteVaiFuncionar() throws Throwable{

    }

    @Então("o teste não vai funcionar")
    public void oTesteNãoVaiFuncionar() throws Throwable{

    }

}
