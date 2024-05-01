class Paciente:
    
    def __init__(self, nome, cpf, email, telefone):
        self.nome = nome
        self.cpf = cpf
        self.email = email
        self.telefone = telefone
        
    def toString(self):
        return f"Paciente[{self.nome}, {self.cpf}, {self.email}, {self.telefone}]"