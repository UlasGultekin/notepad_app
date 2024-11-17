import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import './SaveForm.css';

function SaveForm() {
    const [path, setPath] = useState('');
    const [note, setNote] = useState('');
    const [message, setMessage] = useState('');

    // API'den veri çekmek için GET isteği
    const fetchData = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/note/${path}`);
            setNote(response.data.response.note);  // API yanıtındaki note alanı
            setMessage(response.data.Message);
        } catch (error) {
            setMessage('Hatalı Veya Eksik Giriş yaptınız');
            console.error('Error:', error);
        }
    };

    // Veriyi kaydetmek için POST isteği
    const saveData = async (e) => {
        e.preventDefault();

        const data = {
            path,
            note
        };

        try {
            const response = await axios.post('http://localhost:8080/api/note', data);
            setMessage('Başarılı Şekilde Kayıt Yapıldı.');
            console.log('Response:', response.data);
        } catch (error) {
            setMessage('Error saving data');
            console.error('Error:', error);
        }
    };

    return (
        <div className="save-form-container">
            <h1 className="title">Not Oluşturma Uygulaması</h1>
            <form onSubmit={saveData} className="save-form">
                <div className="form-group">
                    <label htmlFor="path" className="form-label">Adres</label>
                    <input
                        type="text"
                        className="form-control"
                        id="path"
                        value={path}
                        onChange={(e) => setPath(e.target.value)}
                        required
                    />
                </div>
                <div className="button-group">
                    <button type="submit" className="btn btn-primary">Notu Kaydet</button>
                    <button type="button" className="btn btn-secondary" onClick={fetchData}>
                        Notu Getir
                    </button>
                </div>
            </form>
            {message && <p className="message">{message}</p>}
            <textarea
                className="note-area"
                id="note"
                value={note}
                onChange={(e) => setNote(e.target.value)}
                placeholder="Enter your note here..."
            />
        </div>
    );
}

export default SaveForm;
