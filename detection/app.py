from flask import Flask, request, jsonify
from werkzeug.utils import secure_filename
import os
import cv2
import numpy as np
import tensorflow as tf

# Inicializando o app Flask
app = Flask(__name__)

# Configurações
app.config['UPLOAD_FOLDER'] = './static/uploads'
app.config['ALLOWED_EXTENSIONS'] = {'png', 'jpg', 'jpeg', 'gif'}

# Função para checar a extensão do arquivo
def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in app.config['ALLOWED_EXTENSIONS']

# Carregando o modelo
model = tf.keras.models.load_model('./model/model.h5')

@app.route('/predict', methods=['POST'])
def predict():
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'}), 400
    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'No selected file'}), 400
    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        file_path = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(file_path)
        
        img = cv2.imread(file_path)
        X_test = np.asarray(img)
        X_test.reshape(1, 208, 176, 3)
        X_test = np.expand_dims(X_test, axis = 0)

        # Fazendo a predição
        prediction = model.predict(X_test)
        result = np.argmax(prediction, axis=1)[0]  # Supondo que seja classificação
        print(result)
        # Retornando o resultado
        return jsonify({'prediction': int(result)})

    return jsonify({'error': 'File type not allowed'}), 400

if __name__ == '__main__':
    if not os.path.exists(app.config['UPLOAD_FOLDER']):
        os.makedirs(app.config['UPLOAD_FOLDER'])
    app.run(debug=True)