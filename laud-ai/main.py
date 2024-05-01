from flask import Flask
from controller.PacienteController import pacienteController

app = Flask(__name__)
app.register_blueprint(pacienteController)

if __name__ == '__main__':
    app.run()
