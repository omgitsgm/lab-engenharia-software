from flask import Flask, render_template, url_for, redirect, Blueprint
from flask_sqlalchemy import SQLAlchemy
from flask_migrate  import Migrate


app = Flask(__name__)

###########################
##### VIEW FUNCTIONS ######
###########################

@app.route('/')
def ExameController():
    pass