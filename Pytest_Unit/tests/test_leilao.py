# https://docs.pytest.org/en/stable/

from unittest import TestCase
from src.leilao.dominio import Usuario, Lance, Leilao
from src.leilao.excecoes import LanceInvalido


class TestLeilao(TestCase):

    # cenarios que se repetem
    def setUp(self):  # metodo que cria cenários sem precisar invocá-lo em tds os testes
        self.sicrano = Usuario('Sicrano', 5000.0)
        self.lance_sicrano = Lance(self.sicrano, 1500.0)
        self.leilao = Leilao('Quadro')

    # padrões nomenclatura:
    # test_quando_adicionados_em_ordem_crescente_deve_retornar_maior_e_menor_valor_de_lance(self):
    def test_deve_retornar_maior_e_menor_valor_de_lance_quando_adicionados_em_ordem_crescente(self):
        fulano = Usuario('Fulano', 5000.0)
        lance_fulano = Lance(fulano, 1000.0)

        self.leilao.propoe(lance_fulano)
        self.leilao.propoe(self.lance_sicrano)

        menor_valor_esperado = 1000.0
        maior_valor_esperado = 1500.0

        self.assertEqual(menor_valor_esperado, self.leilao.menor_lance)
        self.assertEqual(maior_valor_esperado, self.leilao.maior_lance)

    def test_nao_deve_permitir_proposicao_de_lance_em_ordem_decrescente(self):
        with self.assertRaises(LanceInvalido):
            fulano = Usuario('Fulano', 5000.0)
            lance_fulano = Lance(fulano, 1000.0)

            self.leilao.propoe(self.lance_sicrano)
            self.leilao.propoe(lance_fulano)

    def test_deve_retornar_mesmo_valor_para_maior_e_menor_lance_quando_leilao_tiver_lance(self):
        self.leilao.propoe(self.lance_sicrano)

        self.assertEqual(1500.0, self.leilao.menor_lance)
        self.assertEqual(1500.0, self.leilao.maior_lance)

    def test_deve_retornar_maior_e_menor_valor_quando_leilao_tiver_tres_lances(self):
        fulano = Usuario('Fulano', 5000.0)
        beltrano = Usuario('Beltrano', 5000.0)

        lance_fulano = Lance(fulano, 1000.0)
        lance_beltrano = Lance(beltrano, 2000.0)

        self.leilao.propoe(lance_fulano)
        self.leilao.propoe(self.lance_sicrano)
        self.leilao.propoe(lance_beltrano)

        menor_valor_esperado = 1000.0
        maior_valor_esperado = 2000.0

        self.assertEqual(menor_valor_esperado, self.leilao.menor_lance)
        self.assertEqual(maior_valor_esperado, self.leilao.maior_lance)

    # se o leilao nao tiver lances, deve permitir propor um lance
    def test_deve_permitir_proposicao_de_lance_caso_leilao_nao_tenha_lances(self):
        self.leilao.propoe(self.lance_sicrano)

        quantidade_lances_recebidos = len(self.leilao.lances)
        self.assertEqual(1, quantidade_lances_recebidos)

    # se o último usuário for diferente, deve permitir propor um lance
    def test_deve_permitir_proposicao_de_lance_caso_usuario_seja_diferente(self):
        fulano = Usuario ('Fulano', 5000.0)
        lance_fulano = Lance(fulano, 2000.0)

        self.leilao.propoe(self.lance_sicrano)
        self.leilao.propoe(lance_fulano)

        quantidade_lances_recebidos = len(self.leilao.lances)
        self.assertEqual(2, quantidade_lances_recebidos)

    # se o último usuário for o mesmo, não deve permitir propor um lance
    def test_nao_deve_permitir_proposicao_de_lance_caso_usuario_consecutivo_seja_igual(self):
        lance_sicrano2000 = Lance(self.sicrano, 2000.0)

        with self.assertRaises(LanceInvalido):  # try/except Value Error
            self.leilao.propoe(lance_sicrano2000)
            self.leilao.propoe(self.lance_sicrano)
            # self.fail(msg='Não lançou exceção')  # precaução caso vc use try/except Value Error




