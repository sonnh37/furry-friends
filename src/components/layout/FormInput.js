import '../UI/Layout/Inputform.css';
import React, { useState } from 'react';



export default function  FormInput() {
  const [inputValue, setInputValue] = useState('');

  const handleChange = (e) => {
    setInputValue(e.target.value);
  };

  return (
    <div>
    <div className="input-container">
    <label>Mô tả chi tiết</label>
    <textarea
      value={inputValue}
      onChange={handleChange}
      className={`auto-expanding-input ${inputValue.length > 0 ? 'expanded' : ''}`}
      placeholder="Mô tả chi tiết"
      rows="1" // Số dòng khởi tạo
      maxLength="1500" // Độ dài tối đa
    />
  </div>
   Tối đa 1500 kí tự
   </div>
  )
}