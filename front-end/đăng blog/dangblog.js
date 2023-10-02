
import './App.css';
import React, { useState } from 'react';



function App() {
  const [inputValue, setInputValue] = useState('');

  const handleChange = (e) => {
    setInputValue(e.target.value);
  };

  return (

    <div class="container">
      <div class="row">

        <div class="column right-column">


          <div class="form-group">
            <label>Tiêu đề tin đăng và Mô tả chi tiết</label>
            <input type="text" placeholder="Tiêu đề tin đăng" />
            Tối đá 50 kí tự
            <div className="input-container">
              <label>Mô tả chi tiết</label>
              <textarea
                value={inputValue}
                onChange={handleChange}
                className={`auto-expanding-input ${inputValue.length > 0 ? 'expanded' : ''}`}
                placeholder="Mô tả chi tiết"
                rows="1" // Số dòng khởi tạo
                maxLength="5000" // Độ dài tối đa
              />
            </div>
            Tối đa 5000 kí tự
          </div>


          <div class='button'>
            <div class="button-btn">
              Đăng tin
            </div>

          </div>


        </div>
      </div>
    </div>

  );
}

export default App;
