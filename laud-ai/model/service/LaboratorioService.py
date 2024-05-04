from flask import Flask, request, jsonify
from Laboratorio import db, Laboratorio

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///laboratorios.db'

# Inicialize o SQLAlchemy com o aplicativo Flask
db.init_app(app)

# Rota para retornar todos os laboratórios
@app.route('/laboratorios', methods=['GET'])
def buscar_laboratorios():
    todos_laboratorios = Laboratorio.query.all()
    return jsonify([laboratorio.__dict__ for laboratorio in todos_laboratorios])

# Rota para buscar laboratórios por exame
@app.route('/laboratorios/exame', methods=['GET'])
def buscar_laboratorios_por_exame():
    nome_exame = request.args.get('exame')
    if not nome_exame:
        return jsonify({'error': 'Por favor, forneça o nome do exame'}), 400

    # Consulta SQL para buscar laboratórios que oferecem o exame solicitado
    laboratorios_encontrados = Laboratorio.query.filter(Laboratorio.exames.contains(nome_exame)).all()

    return jsonify([laboratorio.__dict__ for laboratorio in laboratorios_encontrados])

# Rota para buscar laboratórios por data de disponibilidade
@app.route('/laboratorios/data', methods=['GET'])
def buscar_laboratorios_por_data():
    data_disponibilidade = request.args.get('data')
    if not data_disponibilidade:
        return jsonify({'error': 'Por favor, forneça a data de disponibilidade'}), 400

    # Consulta SQL para buscar laboratórios que estão disponíveis na data especificada
    laboratorios_disponiveis = Laboratorio.query.filter_by(data_disponibilidade=data_disponibilidade).all()

    return jsonify([laboratorio.__dict__ for laboratorio in laboratorios_disponiveis])

if __name__ == '__main__':
    app.run(debug=True)