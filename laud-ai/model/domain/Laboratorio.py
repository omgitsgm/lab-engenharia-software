from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class Laboratorio(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    nome = db.Column(db.String(100))
    endereco = db.Column(db.String(200))
    telefone = db.Column(db.String(20))
    exames = db.Column(db.String(200))