
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
        <div class="column left-column">
          <h5>Hình ảnh và Video sản phẩm</h5>
          <div class='input-image'>
            <div class="headinbox" role='presentation'>
            <div class="headinbox-btn">
              <div class="infoinbox"><i class="fa-solid fa-download"></i>  Hình ảnh hợp lệ</div>
            </div>
            </div>
         <div role='presentation' class="below-box">
{/* <input type="file"
        accept="image/*"
        // onChange={handleFileChange}
        style={{ display: 'none' }}
        ref={(fileInput) => (this.fileInput = fileInput)} />  */}
        <div class="image-below">
 <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSavjEwxKwAe4CU2n-gyuj-xhIsiI35hqQTYQ&usqp=CAU" ></img>
        </div>
        <div>
          <p>Đăng tối đa 3 image</p>
        </div>
       
         </div>
          </div>
        </div>
        <div class="column right-column">
        <div class="form-group">
    <label>Danh mục đăng tin</label>
    <select>
      <option value="thu-cung-cho">Thú cưng - Chó</option>
      <option value="thu-cung-meo">Thú cưng - Mèo</option>
      <option value="san-pham-cho-cho-meo">Sản phẩm cho chó mèo</option>
    </select>
  </div>

  <div class="form-group">
    <label>Thông tin chi tiết</label>
    <input type="text" placeholder="Giống thú cưng"/>
    <input type="text" placeholder="Độ tuổi"/>
    <input type="text" placeholder="Kích cỡ thú cưng"/>
    <input type="text" placeholder="Giá bán"/>
  </div>

  <div class="form-group">
    <label>Tiêu đề tin đăng và Mô tả chi tiết</label>
    <input type="text" placeholder="Tiêu đề tin đăng"/>
    Tối đá 50 kí tự
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

  <div class="form-group">
    <label>Thông tin người bán</label>
    <input type="text" placeholder="Địa chỉ"/>
   
    <input type="text" placeholder="Số điện thoại"/>
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
