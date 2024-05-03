from flask_wtf import FlaskForm
from wtform import StringField, IntegerField, SubmitField

class DeleteExam(FlaskForm):

    id = IntegerField("Id number of exam to remove: ")
    submit = SubmitField("Remove Exam")