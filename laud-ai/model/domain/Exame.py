class Exame:

    def __init__(self, nomeExame, operador, paciente, data, horario, resultadoExame):
        self.nomeExame = nomeExame
        self.operador = operador
        self.paciente = paciente
        self.data = data
        self.horario = horario
        self.resultadoExame = resultadoExame
    
    def __repr__(self):
        return f"Detalhes do exame: {self.nomeExame}, {self.operador}, {self.paciente}, {self.data}, {self.horario}, {self.resultadoExame}"