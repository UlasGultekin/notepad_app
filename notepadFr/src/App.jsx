import React from 'react';
import SaveForm from '../src/components/SaveForm'; // SaveForm bileşenini import ediyoruz
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">

      <SaveForm /> {/* SaveForm bileşenini burada çağırıyoruz */}
    </div>
  );
}

export default App;
