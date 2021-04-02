from src.leilao.dominio import Usuario, Leilao
import pytest

from src.leilao.excecoes import LanceInvalido


@pytest.fixture
def fulano():
    return Usuario('Fulano', 5000.0)

@pytest.fixture
def leilao():
    return Leilao('Quadro')


def test_deve_subtrair_valor_da_carteira_do_usuario_quando_ele_propor_lance(fulano, leilao):

    fulano.propoe_lance(leilao, 500.0)

    assert fulano.carteira == 4500.0


def test_deve_permitir_proposicao_de_lance_quando_valor_proposicao_menor_que_valor_carteira(fulano, leilao):

    fulano.propoe_lance(leilao, 1.0)

    assert fulano.carteira == 4999.0


def test_deve_permitir_proposicao_de_lance_quando_valor_proposicao_igual_valor_carteira(fulano, leilao):

    fulano.propoe_lance(leilao, 5000.0)

    assert fulano.carteira == 0.0


def test_nao_deve_permitir_proposicao_de_lance_quando_valor_proposicao_maior_valor_carteira(fulano, leilao):
    with pytest.raises(LanceInvalido):

        fulano.propoe_lance(leilao, 6000.0)




