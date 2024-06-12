from flask import Flask, render_template, flash, request, redirect, url_for, send_from_directory, jsonify
from flask_socketio import SocketIO
from werkzeug.utils import secure_filename
import os

UPLOAD_FOLDER = 'uploads'  # Ensure this path exists
ALLOWED_EXTENSIONS = {'txt', 'pdf', 'png', 'jpg', 'jpeg', 'gif', 'py', 'html', 'java', 'ipynb', 'pdf', 'docx'}
MESSAGE_FILE = 'messages.txt'  # Path to the message file

app = Flask(__name__)
app.config['SECRET_KEY'] = 'secret'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

socketio = SocketIO(app)

@app.route('/')
def index():
    return render_template('index.html')

@socketio.on('message')
def handle_message(data):
    save_message(f"{data['alias']}: {data['message']}")
    socketio.emit('message', data)

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/', methods=['POST'])
def upload_file():
    if 'file' not in request.files:
        flash('No file part')
        return redirect(request.url)
    file = request.files['file']
    alias = request.form.get('alias', 'Anonymous')  # Get alias from form data
    if file.filename == '':
        flash('No selected file')
        return redirect(request.url)
    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
        file_info = f"{alias} uploaded: {filename} ({url_for('uploaded_file', filename=filename, _external=True)})"
        save_message(file_info)
        # Emit file information to SocketIO
        socketio.emit('file_uploaded', {'alias': alias, 'filename': filename, 'url': url_for('uploaded_file', filename=filename)})
        return redirect(url_for('index'))
    flash('File type not allowed')
    return redirect(request.url)

@app.route('/uploads/<filename>')
def uploaded_file(filename):
    return send_from_directory(app.config['UPLOAD_FOLDER'], filename)

@app.route('/get_messages')
def get_messages():
    if os.path.exists(MESSAGE_FILE):
        with open(MESSAGE_FILE, 'r') as f:
            messages = f.readlines()
        return jsonify(messages)
    return jsonify([])

def save_message(message):
    with open(MESSAGE_FILE, 'a') as f:
        f.write(f"{message}\n")

if __name__ == '__main__':
    if not os.path.exists(app.config['UPLOAD_FOLDER']):
        os.makedirs(app.config['UPLOAD_FOLDER'])
    socketio.run(app, host='0.0.0.0')