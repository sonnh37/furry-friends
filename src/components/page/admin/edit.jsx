import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import EditCss from './edit.module.css';
import React, { useEffect, useState } from "react";
import Cookies from "js-cookie";
export default function EditUser() {
  let navigate = useNavigate();

  const {user_id} = useParams();
  const jwtToken = Cookies.get('jwtToken');
  axios.interceptors.request.use(
    config => {
      config.headers.Authorization = `Bearer ${jwtToken}`;
      return config;
    },
    error => {
      return Promise.reject(error);
    }
  );

  const [user, setUser] = useState({
    email: '',
    first_name: '',
    last_name: '',
    phone: '',
    address: '',
    birth: '',
    sex: '',
  });

  const { email, first_name,last_name, phone,  address, birth, sex } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadUser();
  }, []);
  const checkrole = Cookies.get('role')
  const onSubmit = async (e) => {
    e.preventDefault();

    const errors = [];
    if (!user.first_name.trim()) {
      errors.push("Tên bạn chưa nhập.");
    }
    if (!user.last_name.trim()) {
      errors.push("Họ bạn chưa nhập.");
    }
    if (!user.phone.trim()) {
      errors.push("Số điện thoại tiết bạn chưa nhập.");
    }else if (!/^[0-9]+$/.test(user.phone)) {
      errors.push("Số điện thoại phải nhập bằng số.");
    }
    if (!user.address.trim()) {
      errors.push("Địa chỉ bạn chưa nhập.");
    }
    if (!user.email.trim()) {
      errors.push("Email bạn chưa nhập.");
    }
    if (!user.birth.trim()) {
      errors.push("Sinh nhật bạn chưa nhập.");
    }
    if (!user.sex.trim()) {
      errors.push("Giới tính bạn chưa nhập.");
    }
  
    if (errors.length > 0) {
      // Display error messages and prevent form submission
      alert(errors.join('\n'));
      return;
    }
    
    
    await axios.put(`http://localhost:8080/api/v1/user/staff/${user_id}`, user);
    if (checkrole == '2' ) {
      alert("Cập nhập thông tin người dùng thành công")
      navigate('/staff');
  } else if ( checkrole =='3'){
      navigate('/admin');
  }

  };

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8080/api/v1/user/staff/singleuser/${user_id}`);
    setUser(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit User</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className={EditCss['hoten']}>
              <div className={EditCss['inputinfo3']}>
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
              <div className={EditCss['inputinfo3']}>
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
            <div className={EditCss['inputinfo3']}>
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
            <div className={EditCss['inputinfo3']}>
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
            <div className={EditCss['inputinfo3']}>
              <label >
                Địa chỉ:
              </label>
              <input
                type={"text"}
                name="address"
                value={address}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div >
              <label> Giới tính:</label>
              <select
                type={"text"}
                name="sex"
                value={sex}
                onChange={(e) => onInputChange(e)}

              >
                <option>Nữ</option>
                <option>Nam</option>
                <option>Khác</option>
              </select>
            </div>
            <div c>
              <label >
                Sinh nhật:
              </label>
              <input
                type={"text"}
                name="birth"
                value={birth}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            < div className={EditCss['btn-1']}>
              <div className={EditCss['btn-2']}>
                <button type="submit" className="btn btn-outline-primary">
                  Lưu
                </button>
                <Link className="btn btn-outline-danger mx-2" to="/admin">
                  Quay lại
                </Link>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}   