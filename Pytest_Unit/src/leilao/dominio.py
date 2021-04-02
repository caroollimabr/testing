from src.leilao.excecoes import LanceInvalido


class Usuario:

    def __init__(self, nome, carteira):
        self.__nome = nome
        self.__carteira = carteira

    def propoe_lance(self, leilao, valor):
        if not self._valor_valido(valor):
            raise LanceInvalido('Não é possível propor um lance com o valor menor que o da carteira.')

        lance = Lance(self, valor)
        leilao.propoe(lance)

        self.__carteira -= valor

    @property
    def nome(self):
        return self.__nome

    @property
    def carteira(self):
        return  self.__carteira

    def _valor_valido(self, valor):
        return valor <= self.__carteira


class Lance:

    def __init__(self, usuario, valor):
        self.usuario = usuario
        self.valor = valor


class Leilao:

    def __init__(self, descricao):
        self.descricao = descricao
        self.__lances = []  # lista
        self.maior_lance = 0.0  # sys.float_info.min: pega as informações do float (menor valor)
        self.menor_lance = 0.0  # sys.float_info.max: maior valor

    def propoe(self, lance: Lance):  # anotação (type hint), só para ajudar a não se perder no código
        # -1: acesso ao último elemento da lista
        if self._lance_valido(lance):
            if not self._tem_lances():
                self.menor_lance = lance.valor

            self.maior_lance = lance.valor

            self.__lances.append(lance)
        else:
            raise LanceInvalido('Erro ao propor lance.')

    @property
    def lances(self):
        return self.__lances[:]  # devolve copia rasa da lista

    def _tem_lances(self):
        return self.__lances

    def _usuarios_diferentes(self, lance):
        if self.__lances[-1].usuario != lance.usuario:
            return True
        raise LanceInvalido('O mesmo usuário não pode dar dois lances seguidos.')

    def _valor_maior_que_lance_anterior(self, lance):
        if lance.valor > self.__lances[-1].valor:
            return True
        raise LanceInvalido('O valor do lance deve ser maior do que o lance anterior.')

    def _lance_valido(self, lance):
        return not self._tem_lances() or (self._usuarios_diferentes(lance) and
                                          self._valor_maior_que_lance_anterior(lance))





