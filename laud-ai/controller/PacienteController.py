from flask import Blueprint, request, Response
from model.domain.Paciente import Paciente
from model.domain.Endereco import Endereco
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
    
    # Atributos de endereço
    cep = request_data['endereco']['cep']
    rua = request_data['endereco']['rua']
    numero = request_data['endereco']['numero']
    complemento = request_data['endereco']['complemento']
    cidade = request_data['endereco']['cidade']
    estado = request_data['endereco']['estado']
    
    # Verifico se todas as variáveis possuem conteúdo
    if not nome or not cpf or not email or not telefone:
        return Response("Campos obrigatórios não preenchidos", status=400)
    
    # Verifico se atributos de endereço estão preenchidos
    # 'complemento' não é um atributo obrigatório
    if not cep or not rua or not numero or not cidade or not estado:
        return Response("Campos obrigatórios não preenchidos", status=400)
    
    # Validar formato de cpf, e-mail e telefone
    if not validator.is_valid_cpf(cpf):
        return Response("CPF inválido.", status=400)
    
    if not validator.is_valid_email(email):
        return Response("Email inválido.", status=400)
    
    if not validator.is_valid_phone_number(telefone):
        return Response("Telefone inválido.", status=400)
    
    if not validator.is_valid_cep(cep):
        return Response("CEP inválido.", status=400)
    
    
    # Crio um objeto Endereco a partir das variáveis
    endereco = Endereco(cep, rua, numero, complemento, cidade, estado)
    
    # Crio um objeto Paciente a partir das variáveis
    paciente = Paciente(nome, cpf, email, telefone, endereco)
    
    # Chamo PacienteService para aplicar as regras de negócio e interagir com a camada de dados
    newPaciente = PacienteService.save(paciente)
    
    print(newPaciente.toString())
    
    return Response(newPaciente.toString(), status=201)
