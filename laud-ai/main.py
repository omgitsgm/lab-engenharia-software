from flask import Flask
from controller.PacienteController import pacienteController

app = Flask(__name__)
app.register_blueprint(pacienteController)

@app.route("/")
def hello():
    return "<h1 style='color:blue'>Hello There!</h1>"

if __name__ == '__main__':
    app.debug = True
    app.run()
