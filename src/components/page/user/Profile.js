
// import axios from "axios";
import React, { useEffect, useState } from "react";
// import { Link, useNavigate, useParams } from "react-router-dom";
import ProfileCss from "./Profile.module.css"
import axios from "axios";
import Cookies from 'js-cookie';


const Profile = () => {

  const [isEditing, setIsEditing] = useState(false);
  // let navigate = useNavigate();
  // const { user_id } = useParams();

  const [user, setUser] = useState({
    user_id:'',
    email: '',
    first_name: '',
    last_name: '',
    phone: '',
    address: '',
    birth: '',
    sex: '',
  });

  const { user_id,email, first_name, last_name, phone, address, birth, sex } = user;

  console.log(sex);
  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadUser();
  }, []);
  
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
  const onSubmit = async (e) => {
    e.preventDefault();

    await axios.put(`http://localhost:8080/api/v1/user/member`, user);
    setIsEditing(false);

  };

  const loadUser = async () => {
    const accountuser = Cookies.get('sub')
  

    const result = await axios.get(`http://localhost:8080/api/v1/user/member/singleuser/${accountuser}`, 
    );

    
    setUser(result.data);
  };

  return (
    <div className="container">
   
      <div className="row">
        <div>
          <h2 className="text-center m-4">Edit User</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className={ProfileCss['hoten']}>
              <div className={ProfileCss['inputinfo3']}>
                <label >
                  Họ:
                </label>

                <input
                  type={"text"}
                  name="last_name"
                  value={last_name}
                  onChange={(e) => onInputChange(e)}
                  disabled={!isEditing}
                />

              </div>
              <div className={ProfileCss['inputinfo3']}>
                <label >
                  Tên:
                </label>

                <input
                  type={"text"}
                  name="first_name"
                  value={first_name}
                  onChange={(e) => onInputChange(e)}
                  disabled={!isEditing}
                />

              </div>

            </div>
            <div className={ProfileCss['inputinfo3']}>
              <label >
                UserID
              </label>
              <input
                type={"text"}
                name="user_id"
                value={user_id}
                onChange={(e) => onInputChange(e)}
                disabled={!isEditing}
              />
            </div>
            <div className={ProfileCss['inputinfo3']}>
              <label >
                Số điện thoại:
              </label>
              <input
                type={"text"}
                name="phone"
                value={phone}
                onChange={(e) => onInputChange(e)}
                disabled={!isEditing}
              />
            </div>
            <div className={ProfileCss['inputinfo3']}>
              <label >
                E-mail
              </label>
              <input
                type={"text"}
                name="email"
                value={email}
                onChange={(e) => onInputChange(e)}
                disabled={!isEditing}
              />
            </div>
            <div className={ProfileCss['inputinfo3']}>
              <label >
                Address:
              </label>
              <input
                type={"text"}
                name="address"
                value={address}
                onChange={(e) => onInputChange(e)}
                disabled={!isEditing}
              />
            </div>
            <div >
              <label> Gender:</label>
              <select
                type={"text"}
                name="sex"
                value={sex}
                onChange={(e) => onInputChange(e)}
                disabled={!isEditing}

              >
                <option>Nữ</option>
                <option>Nam</option>
                <option>Khác</option>
              </select>
            </div>
            <div c>
              <label >
                Birthday:
              </label>
              <input
                type={"text"}
                name="birth"
                value={birth}
                onChange={(e) => onInputChange(e)}
                disabled={!isEditing}
              />
            </div>
            <div className={ProfileCss['btn-1']}>
              <div className={ProfileCss['btn-2']}>

                <button type="submit" className="btn btn-outline-primary">
                  Save
                </button>

                <button
                  type="button"
                  className="btn btn-outline-warning"
                  onClick={() => setIsEditing(true)}
                >
                  Edit
                </button>

              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Profile;