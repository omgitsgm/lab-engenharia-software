class Endereco:
    
    def __init__(self, cep, rua, numero, complemento, cidade, estado):
        self.cep = cep
        self.rua = rua
        self.numero = numero
        self.complemento = complemento
        self.cidade = cidade
        self.estado = estado
        
    def toString(self):
        return f"Endereco[{self.cep}, {self.rua}, {self.numero}, {self.complemento}, {self.cidade}, {self.estado}]"