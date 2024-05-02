from flask import Blueprint, request, Response
from model.domain.Paciente import Paciente
from model.service.PacienteService import PacienteService
from .util.RequestDataValidator import RequestDataValidator

pacienteController = Blueprint('pacienteController', __name__, url_prefix='/paciente')

validator = RequestDataValidator

@pacienteController.route('/', methods=['POST'])
def save_paciente():
    
    # Pega o JSON da requisição
    request_data = request.get_json()
    
    # Atribuo cada atributo do JSON a uma variável
    nome = request_data['nome']
    cpf = request_data['cpf']
    email = request_data['email']
    telefone = request_data['telefone']
    
    # Verifico se todas as variáveis possuem conteúdo
    if not nome or not cpf or not email or not telefone:
        return Response("Campos obrigatórios não preenchidos", status=400)
    
    # Validar formato de cpf, e-mail e telefone
    if not validator.is_valid_cpf(cpf):
        return Response("CPF inválido.", status=400)
    
    if not validator.is_valid_email(email):
        return Response("Email inválido.", status=400)
    
    if not validator.is_valid_phone_number(telefone):
        return Response("Telefone inválido.", status=400)
    
    # Crio um objeto Paciente a partir das variáveis
    paciente = Paciente(nome, cpf, email, telefone)
    
    # Chamo PacienteService para aplicar as regras de negócio e interagir com a camada de dados
    newPaciente = PacienteService.save(paciente)
    
    print(newPaciente.toString())
    
    return Response(newPaciente.toString(), status=201)
