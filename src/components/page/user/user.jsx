import React from 'react';
import UserCSS from './user.module.css';
import FormInput from '../../layout/FormInput';
import Profile from './Profile';


export default function user() {
 

  return (
    <div className={UserCSS['body-container']}>
      <div className={UserCSS['container']}>
        <div className={UserCSS['row']}>
          <div className={UserCSS['column']}>
        
              <div className={UserCSS['form-group']} >
                <label>Hồ sơ cá nhân</label>
                <Profile/>
               <div className={UserCSS['info-user']}>
              
          
               </div>
              </div>

              <div className={UserCSS['form-group']}>
                <label>Thông tin chi tiết</label>
                <input type="text" placeholder="Giống thú cưng" />
                <input type="text" placeholder="Độ tuổi" />
                <input type="text" placeholder="Kích cỡ thú cưng" />
                <input type="text" placeholder="Giá bán" />
              </div>

              <div className={UserCSS['form-group']} >
                <label>Tiêu đề tin đăng và Mô tả chi tiết</label>
                <input type="text" placeholder="Tiêu đề tin đăng" />
                Tối đa 50 kí tự
                <FormInput />

              </div>

              <div className={UserCSS['form-group']} >
                <label>Thông tin người bán</label>
                <input type="text" placeholder="Địa chỉ" />

                <input type="text" placeholder="Số điện thoại" />
              </div>
              <div className={UserCSS['button']} >
                <div className={UserCSS['button-btn']} >
                  Đăng tin
                </div>

              </div>


          
          </div>
        </div>
      </div>

    </div>
  );
}
