import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import logo from "../../UI/Logo/logo.png";
import LoginCSS from "../../UI/Layout/Login.module.css";
import React from "react";
import Cookies from 'js-cookie';
import jwtDecode from "jwt-decode";



function Login() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        account: '',
        password: '',
    });

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/v1/login", {
                account: formData.account,
                password: formData.password,
            }).then((res) => {
                // console.log(res.data); hiện token

                if (res.data == "Account not exist") {
                    alert("Account not exist");
                }

                else if (res.data == "Password not match") {
                    alert("Incorrect Email and Password not match");
                } else {
                    navigate('/home');
                    Cookies.set('jwtToken', res.data, { expires: 1 });
                    const decodedToken = jwtDecode(res.data)
                    const subValue = decodedToken.sub;
                    Cookies.set('sub', subValue);
                    if (subValue) {
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


                        axios.get(`http://localhost:8080/api/v1/user/member/singleuser/allrole/${subValue}`)
                            .then((response) => {
                                // Kiểm tra xem yêu cầu thành công và có dữ liệu `role_id` hay không
                                if (response.status === 200 && response.data && response.data.role_id) {
                                    const role_id = response.data.role_id;

                                      console.log('role_id:', role_id);
                                    Cookies.set('role', role_id, { expires: 1 })
                                    if (role_id == '2' ) {
                                        navigate('/staff');
                                    } else if ( role_id =='3'){
                                        navigate('/admin');
                                    }

                                } else {
                                    // Xử lý trường hợp nếu không tìm thấy `role_id`
                                    console.log('Không tìm thấy role_id trong dữ liệu phản hồi.');
                                }
                            })
                            .catch((error) => {
                                // Xử lý lỗi nếu yêu cầu thất bại
                                console.error('Lỗi trong quá trình lấy dữ liệu role_id:', error);
                            });

                    }




                }
            }, fail => {
                console.error(fail); // Error!
            });
        }
        catch (err) {
            alert(err);
        }
    };




    return (
        <div className={LoginCSS['wrapper']} id="wrapper">
            <form className={LoginCSS['form-login']} onSubmit={handleSubmit} id="form-login">
                <div className={LoginCSS['image']} ><img src={logo} alt="" /></div>
                <h1 className={LoginCSS['form-heading']} >Đăng nhập</h1>
                <div className={LoginCSS['submit']} >
                    <div className={LoginCSS['form-group']} >
                        <i class="fa fa-user"></i>
                        <input type="text"
                            name="account" className={LoginCSS['form-input']} placeholder="Tài khoản"
                            value={formData.account}
                            onChange={handleChange}
                        />
                    </div>
                </div>
                <div className={LoginCSS['submit']} >
                    <div className={LoginCSS['form-group']} >
                        <i class="fa fa-key"></i>
                        <input type="password"
                            name="password" className={LoginCSS['form-input']} placeholder="Mật khẩu"
                            value={formData.password}
                            onChange={handleChange}
                        />
                        <div className={LoginCSS['eye']} >
                            <i></i>
                        </div>
                    </div>
                </div>
                <div>
                    <p className={LoginCSS['text_1']}>
                        <a href="">Quên mật khẩu?</a>
                    </p>
                </div>
                <div className={LoginCSS['submit']} >
                    <input type="submit" value="Đăng nhập" className={LoginCSS['form-submit']} />
                </div>
                <div>
                    <p className={LoginCSS['text_2']} >Bạn chưa có tài khoản?
                        <span className={LoginCSS['text_span']}>
                            <a href="/register" > Tạo tài khoản mới</a>
                        </span>
                    </p>
                </div>
            </form>
        </div>
    );
}

export default Login;
