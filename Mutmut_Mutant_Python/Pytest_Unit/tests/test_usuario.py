from src.leilao.dominio import Usuario, Leilao
import pytest

from src.leilao.excecoes import LanceInvalido


@pytest.fixture
def fulano():
    return Usuario('Fulano', 5000.0)


@pytest.fixture
def sicrano():
    return Usuario('Sicrano', 1000.0)


@pytest.fixture
def leilao():
    return Leilao('Quadro')


def test_deve_subtrair_valor_da_carteira_do_usuario_quando_ele_propor_lance(fulano, sicrano, leilao):

    fulano.propoe_lance(leilao, 500.0)
    sicrano.propoe_lance(leilao, 600.0)

    assert fulano.carteira == 4500.0 and sicrano.carteira == 400.0


def test_deve_permitir_proposicao_de_lance_quando_valor_proposicao_menor_que_valor_carteira(fulano, sicrano, leilao):

    fulano.propoe_lance(leilao, 1.0)
    sicrano.propoe_lance(leilao, 2.0)

    assert fulano.carteira == 4999.0 and sicrano.carteira == 998.0


def test_deve_permitir_proposicao_de_lance_quando_valor_proposicao_igual_valor_carteira(fulano, sicrano, leilao):

    sicrano.propoe_lance(leilao, 1000.0)
    fulano.propoe_lance(leilao, 5000.0)

    assert fulano.carteira == 0.0 and sicrano.carteira == 0.0


def test_nao_deve_permitir_proposicao_de_lance_quando_valor_proposicao_maior_valor_carteira(fulano, sicrano, leilao):

    with pytest.raises(LanceInvalido):
        fulano.propoe_lance(leilao, 6000.0)
        sicrano.propoe_lance(leilao, 6000.0)


def test_nao_deve_permitir_dois_lances_seguidos_mesmo_usuario(fulano, leilao):

    with pytest.raises(LanceInvalido):
        fulano.propoe_lance(leilao, 1000.0)
        fulano.propoe_lance(leilao, 1200.0)
