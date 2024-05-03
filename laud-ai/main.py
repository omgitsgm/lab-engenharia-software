from flask import Flask
from controller.PacienteController import pacienteController
from flask_migrate import Migrate
from flask_sqlalchemy import SQLAlchemy
import os


###### FLASK #####

app = Flask(__name__)

##### DATABASE #####

basedir = os.path.abspath(os.path.dirname(__file__))
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///'+os.path.join(basedir, 'data.sqlite')
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

#Migrate
Migrate(app, db)

##### BLUEPRINT #####
app.register_blueprint(pacienteController)
app.register_blueprint(ExameController)

if __name__ == '__main__':
    app.run()
