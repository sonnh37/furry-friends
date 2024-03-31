import React, { useEffect,useState } from 'react';
import axios from 'axios';
import './Password.css'
import Cookies from 'js-cookie';

const App = () => {

  const [oldPassword, setOldPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  
  const [errors, setErrors] = useState({
    oldPassword: '',
    newPassword: '',
    confirmPassword: '',
  });
 
  const handleChange = (e, setter) => {
    setter(e.target.value);
    // Clear the error when the user starts typing in the input field
    setErrors({ ...errors, [e.target.name]: '' });
  };

  const handleSave = () => {
    // Validation checks
    let validationPassed = true;
    const newErrors = {};

    if (!oldPassword) {
      newErrors.oldPassword = 'Vui lòng nhập mật khẩu cũ';
      validationPassed = false;
    }

    if (!newPassword) {
      newErrors.newPassword = 'Vui lòng nhập mật khẩu mới';
      validationPassed = false;
    }

    if (!confirmPassword) {
      newErrors.confirmPassword = 'Vui lòng xác nhận mật khẩu mới';
      validationPassed = false;
    }

    if (newPassword !== confirmPassword) {
      newErrors.confirmPassword = 'Mật khẩu mới và xác nhận không khớp';
      validationPassed = false;
    }

    if (!validationPassed) {
      setErrors(newErrors);
      return;
    }


    const account1 = Cookies.get("sub");
    axios.post('http://localhost:8080/api/v1/user/member', 
    { account: account1, 
      password: oldPassword})
    .then(response => {
      // console.log(response.data)
      if (response.data =='password match') {
        // Old password is correct, proceed to update password
        return axios.put('http://localhost:8080/api/v1/user/member/setpass',
         {
          account: account1, 
        password: newPassword })
        .then(response => {
          if (response && response.data=='Set success!') {
            console.log('Thay đổi mật khẩu thành công');
            alert("Bạn đã đổi mật khẩu thành công")
            // Handle success message
          }
        })
        .catch(error => {
          console.error('Lỗi khi thay đổi mật khẩu:', error);
          // Handle error message for API call failure
        });
      } else {
        // Old password is incorrect
        newErrors.oldPassword = 'password not match';
        setErrors(newErrors);
      }
    })
    
  };

  return (
    <div className="App">
      <h1>Thay Đổi Mật Khẩu</h1>
      <div>
        <label>Mật khẩu cũ:</label>
        <input
          type="password"
          name="oldPassword"
          value={oldPassword}
          onChange={(e) => handleChange(e, setOldPassword)}
          className={errors.oldPassword && 'error'}
        />
        <div className="error-message">{errors.oldPassword}</div>
      </div>
      <div>
        <label>Mật khẩu mới:</label>
        <input
          type="password"
          name="newPassword"
          value={newPassword}
          onChange={(e) => handleChange(e, setNewPassword)}
          className={errors.newPassword && 'error'}
        />
        <div className="error-message">{errors.newPassword}</div>
      </div>
      <div>
        <label>Xác nhận lại mật khẩu mới:</label>
        <input
          type="password"
          name="confirmPassword"
          value={confirmPassword}
          onChange={(e) => handleChange(e, setConfirmPassword)}
          className={errors.confirmPassword && 'error'}
        />
        <div className="error-message">{errors.confirmPassword}</div>
      </div>
      <div className='btn'>
        <button onClick={handleSave}>Lưu</button>
      </div>
    </div>
  );
};

export default App;