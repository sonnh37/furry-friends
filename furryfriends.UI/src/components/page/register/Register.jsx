import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import logo from "../../UI/Logo/logo.png";
import React from "react";
import RegisterCSS from "../../UI/Layout/Register.module.css"
function Register() {
  let navigate = useNavigate()
  const [user, setUser] = useState({
    email: "",
    password: "",
    first_name: "",
    last_name: "",
    phone: "",
    address: "",
    birth: "",
    sex: "",
    account: "",
  })
  const { account,password, email, first_name, last_name, phone,birth, sex, address } = user

  const onInputChange = (event) => {
    setUser({ ...user, [event.target.name]: event.target.value })
  }

  const onSubmit = async (event) => {

    if(!account || !user.password || !user.email || !user.first_name || !user.last_name || !user.phone || !user.birth || !user.sex || !user.address){

      alert("Điền đủ thông tin")
      return
    }
    event.preventDefault();
    try {
      await axios.post("http://localhost:8080/api/v1/register", user);
      alert("User registation Successfully");
      navigate("/login")
    } catch (err) {
      alert(err);
    }
  }


  return (
    <div className={RegisterCSS['wrapper']} id="wrapper">
      <form className={RegisterCSS['form-login']} onSubmit={onSubmit} id="form-login">
        <div className={RegisterCSS['image']} >
          <img src={logo} alt="" />
        </div>
        <h1 className={RegisterCSS['form-heading']} >Đăng ký tài khoản</h1>
        <div className={RegisterCSS['submit']}>
          <div className={RegisterCSS['form-group_1']}>
            <input type="text"
              name="last_name"
              className={RegisterCSS['form-input']} placeholder="Họ"
              value={last_name}
              onChange={onInputChange}
            />
          </div>
          <div><p>Và</p></div>
          <div className={RegisterCSS['form-group_1']}>
            <input type="text"
              name="first_name"
              className={RegisterCSS['form-input']} placeholder="Tên"
              value={first_name}
              onChange={onInputChange}
            />
          </div>
        </div>
        <div className={RegisterCSS['submit_1']} >
          <div className={RegisterCSS['form-group']}>
            <i className="fa fa-phone"></i>
            <input type="text"
              name="phone"
              className={RegisterCSS['form-input']} placeholder="Số điện thoại"
              value={phone}
              onChange={onInputChange}
            />
          </div>
        </div>
  
        <div className={RegisterCSS['submit_1']} >
          <div className={RegisterCSS['form-group']}>
            <i className="fa fa-envelope"></i>
            <input type="email"
              name="email"
              className={RegisterCSS['form-input']} placeholder="Email"
              value={email}
              onChange={onInputChange}
            />
          </div>
        </div>
        <div className={RegisterCSS['submit_1']} >
          <div className={RegisterCSS['form-group']}>
            <i class="fa fa-user" aria-hidden="true"></i>
            <input type="string"
              name="account"
              className={RegisterCSS['form-input']} placeholder="Account"
              value={account}
              onChange={onInputChange}
            />
          </div>
        </div>

        <div className={RegisterCSS['submit_1']} >
          <div className={RegisterCSS['form-group']}>
            <i className="fa fa-key"></i>
            <input type="password"
              name="password"
              className={RegisterCSS['form-input']} placeholder="Mật khẩu"
              value={password}
              onChange={onInputChange}
            />
            <div className={RegisterCSS['eye']}>
              <i className="fa fa-eye"></i>
            </div>
          </div>
        </div>

        <div className={RegisterCSS['submit_1']} >
          <div className={RegisterCSS['form-group']}>
            <i className="fa fa-calendar"></i>
            <input type="date"
              name="birth"
              className={RegisterCSS['form-input']} placeholder="Năm sinh"
              value={birth}
              onChange={onInputChange}
            />
          </div>
        </div>

        <div className={RegisterCSS['submit_1']} >
          <div className={RegisterCSS['form-group_2']}>
            <div className={RegisterCSS['form-group_2_gender']} >
              <label for="gender">Giới tính: </label>

              <div className={RegisterCSS['from-check']}>
                <input type="radio"
                  name="sex"
                  className="from-check-input"
                  value="Nam"
                  checked={sex === "Nam"} // Kiểm tra nếu sex là male thì checked
                  onChange={onInputChange}
                />
                <label for="" className="from-check-label">Nam</label>
              </div>

              <div className={RegisterCSS['from-check']}>
                <input type="radio"
                  name="sex"
                  className="from-check-input"
                  value="Nữ"
                  checked={sex === "Nữ"} // Kiểm tra nếu sex là female thì checked
                  onChange={onInputChange}
                />
                <label for="" className="from-check-label">Nữ</label>
              </div>
            </div>
          </div>
        </div>

        <div className={RegisterCSS['submit_1']} >
          <div className={RegisterCSS['form-group']}>
            <i className="fa fa-address-card"></i>
            <input type="text"
              name="address"
              className={RegisterCSS['form-input']} placeholder="Địa chỉ"
              value={address}
              onChange={onInputChange}
            />
          </div>
        </div>
        
        <div className={RegisterCSS['submit_check']}>
     
         
        </div>
        <div className={RegisterCSS['submit_1']} >
          <input type="submit" value="Đăng ký" className={RegisterCSS['form-submit']} />
        </div>
        <div>
          <p className={RegisterCSS['text_2']}>Đã có tài khoản?
            <span className={RegisterCSS['text_span']} >
              <a href="/login">Đăng nhập ngay</a>
            </span>
          </p>
        </div>
      </form>
    </div>
  );
}

export default Register;