import React, { useState } from 'react';
import ProductUpFormCSS from '../UI/Layout/ProductUpForm.module.css';
export default function ProductUpForm() {
  const [inputValue, setInputValue] = useState('');
  const getClassExpandedName = (inputValue) => {
    return inputValue.length > 0 ? 'auto-expanding-input-expanded' : 'auto-expanding-input';
  }
  const handleChange = (e) => {
    const textarea = e.target;
    textarea.style.height = 'auto';
    setInputValue(e.target.value);
  };

  return (
    <div className={ProductUpFormCSS['container']}>
      <div className={ProductUpFormCSS['row']}>
        <div className={ProductUpFormCSS['column']}>
          <div className={ProductUpFormCSS['left-column']}>
            <h5>Hình ảnh và Video sản phẩm</h5>
            <div className={ProductUpFormCSS['input-image']}>
              <div className={ProductUpFormCSS['headinbox']} role='presentation'>
                <div className={ProductUpFormCSS['headinbox-btn']} >
                  <div className={ProductUpFormCSS['infoinbox']}><i className={ProductUpFormCSS['fa-solid fa-download']} ></i>  Hình ảnh hợp lệ</div>
                </div>
              </div>
              <div role='presentation' className={ProductUpFormCSS['below-box']} >
                {/* <input type="file"
          accept="image/*"
          // onChange={handleFileChange}
          style={{ display: 'none' }}
          ref={(fileInput) => (this.fileInput = fileInput)} />  */}
                <div className={ProductUpFormCSS['image-below']}>
                  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSavjEwxKwAe4CU2n-gyuj-xhIsiI35hqQTYQ&usqp=CAU" ></img>
                </div>
                <div>
                  <p>Đăng tối đa 3 image</p>
                </div>

              </div>
            </div>
          </div>
        </div>
        <div className={ProductUpFormCSS['column']}>
          <div className={ProductUpFormCSS['right-column']} >
            <div className={ProductUpFormCSS['form-group']} >
              <label>Danh mục đăng tin</label>
              <select>
                <option value="thu-cung-cho">Thú cưng - Chó</option>
                <option value="thu-cung-meo">Thú cưng - Mèo</option>
                <option value="san-pham-cho-cho-meo">Sản phẩm cho chó mèo</option>
              </select>
            </div>

            <div className={ProductUpFormCSS['form-group']}>
              <label>Thông tin chi tiết</label>
              <input type="text" placeholder="Giống thú cưng" />
              <input type="text" placeholder="Độ tuổi" />
              <input type="text" placeholder="Kích cỡ thú cưng" />
              <input type="text" placeholder="Giá bán" />
            </div>

            <div className={ProductUpFormCSS['form-group']} >
              <label>Tiêu đề tin đăng và Mô tả chi tiết</label>
              <input type="text" placeholder="Tiêu đề tin đăng" />
              Tối đá 50 kí tự
              <div className={ProductUpFormCSS['input-container']}>
                <label>Mô tả chi tiết</label>
                <textarea
                  value={inputValue}
                  onChange={handleChange}
                  className={`${getClassExpandedName(inputValue)}`}
                  placeholder="Mô tả chi tiết"
                  rows="1" // Số dòng khởi tạo
                  maxLength="1500" // Độ dài tối đa
                />
              </div>
              Tối đa 1500 kí tự
            </div>

            <div className={ProductUpFormCSS['form-group']} >
              <label>Thông tin người bán</label>
              <input type="text" placeholder="Địa chỉ" />

              <input type="text" placeholder="Số điện thoại" />
            </div>
            <div className={ProductUpFormCSS['button']} >
              <div className={ProductUpFormCSS['button-btn']} >
                Đăng tin
              </div>

            </div>


          </div>
        </div>
      </div>
    </div>

  );
}
