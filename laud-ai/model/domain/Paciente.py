from .Endereco import Endereco

class Paciente:
    
    def __init__(self, nome, cpf, email, telefone, endereco):
        self.nome = nome
        self.cpf = cpf
        self.email = email
        self.telefone = telefone
        self.endereco = endereco
        
    def toString(self):
        return f"Paciente[{self.nome}, {self.cpf}, {self.email}, {self.telefone}, {self.endereco.toString()}]"