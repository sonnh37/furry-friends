import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import AddStaffCss from './edit.module.css';
import Cookies from "js-cookie";





export default function Addstaff() {
  let navigate = useNavigate();

  const { user_id } = useParams();

  const [user, setUser] = useState({
    account: '',
    password: '',
    email: '',
    first_name: '',
    last_name: '',
    phone: '',
    address: '',
    birth: '',
    sex: '',
  });

  const { account, password, email, first_name, last_name, phone, address, birth, sex } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };



  const onSubmit = async (e) => {
    e.preventDefault();
    

    const errors = [];
    
    if (!user.account.trim()) {
      errors.push("Tên tài khoản chưa nhập.");
    }
    if (!user.password.trim()) {
      errors.push("Mật khẩu chưa nhập.");
    }
    
    if (!user.email.trim()) {
      errors.push("Email chưa nhập.");
    }
    if (!user.first_name.trim()) {
      errors.push("Tên chưa nhập.");
    }
    if (!user.last_name.trim()) {
      errors.push("Họ chưa nhập.");
    }
    if (!user.phone.trim()) {
      errors.push("Số điện thoại là trường bắt buộc.");
    }
    if (!/^[0-9]+$/.test(user.phone)) {
      errors.push("Số điện thoại bắt buộc là số.");
    }
   
  
    if (errors.length > 0) {
      // Display error messages and prevent form submission
      alert(errors.join('\n'));
      return;
    }


    const jwtToken = Cookies.get('jwtToken');
  axios.interceptors.request.use(
    config => {
      config.headers.Authorization = `Bearer ${jwtToken}`;
      return config;
    },
    error => {
      return Promise.reject(error);
    }
  ); // truyen token---------------------------
    await axios.post(`http://localhost:8080/api/v1/user/admin`, user);
    navigate("/admin");
  };

  
    return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Add Staff</h2>

          <form onSubmit={(e) => onSubmit(e)}>
          <div className={AddStaffCss['inputinfo3']}>
              <label >
                Account:
              </label>
              <input
                type={"text"}
                name="account"
                value={account}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className={AddStaffCss['inputinfo3']}>
              <label >
                Password:
              </label>
              <input
                type={"text"}
                name="password"
                value={password}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className={AddStaffCss['hoten']}>
              <div className={AddStaffCss['inputinfo3']}>
                <label >
                  Họ:
                </label>
                <input
                  type={"text"}
                  name="last_name"
                  value={last_name}
                  onChange={(e) => onInputChange(e)}
                />
              </div>
              <div className={AddStaffCss['inputinfo3']}>
                <label >
                  Tên:
                </label>
                <input
                  type={"text"}
                  name="first_name"
                  value={first_name}
                  onChange={(e) => onInputChange(e)}
                />
              </div>
            </div>
            <div className={AddStaffCss['inputinfo3']}>
              <label >
                Số điện thoại:
              </label>
              <input
                type={"text"}
                name="phone"
                value={phone}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className={AddStaffCss['inputinfo3']}>
              <label >
                E-mail
              </label>
              <input
                type={"text"}
                name="email"
                value={email}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className={AddStaffCss['inputinfo3']}>
              <label >
                Address:
              </label>
              <input
                type={"text"}
                name="address"
                value={address}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div >
              <label> Gender:</label>
              <select
                type={"text"}
                name="sex"
                value={sex}
                onChange={(e) => onInputChange(e)}

              >
                <option></option>
                <option>Nữ</option>
                <option>Nam</option>
                <option>Khác</option>
              </select>
            </div>
            <div >
              <label >
                Birthday:
              </label>
              <input
                type={"date"}
                name="birth"
                value={birth}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className={AddStaffCss['btn-1']}>
              <div className={AddStaffCss['btn-2']}>
                <button type="submit" className="btn btn-outline-primary">
                  Submit
                </button>
                <Link className="btn btn-outline-danger mx-2" to="/admin">
                  Cancel
                </Link>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}   