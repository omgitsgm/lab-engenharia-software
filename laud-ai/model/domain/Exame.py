class Exame:

    def __init__(self, id, nomeExame):
        self.id = id
        self.nomeExame = nomeExame
    
    def __repr__(self):
        return f"id: {self.id} \n Detalhes do exame: {self.name}"