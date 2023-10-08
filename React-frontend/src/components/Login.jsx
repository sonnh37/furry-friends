import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import logo from "./UI/Img/logo.png";
import LoginCSS from "./UI/Login.module.css";
import React from "react";
function Login() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        email: '',
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
            await axios.post("http://localhost:8080/api/v1/user/login", {
                email: formData.email,
                password: formData.password,
            }).then((res) => {
                console.log(res.data);

                if (res.data.message == "Email not exits") {
                    alert("Email not exits");
                }
                else if (res.data.message == "Login Success") {
                    navigate('/home');
                }
                else {
                    alert("Incorrect Email and Password not match");
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
                        <input type="email"
                            name="email" className={LoginCSS['form-input']} placeholder="Tài khoản"
                            value={formData.email}
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


        /* <div>
            <div class="container">
                <div class="row">
                    <h2>Login</h2>
                    <hr />
                </div>

                <div class="row">
                    <div class="col-sm-6">

                        <form onSubmit={handleSubmit}>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" name="email" placeholder="Enter Name"

                                    value={formData.email}
                                    onChange={handleChange}

                                />

                            </div>

                            <div class="form-group">
                                <label>password</label>
                                <input type="password" class="form-control" name="password" placeholder="Enter Fee"

                                    value={formData.password}
                                    onChange={handleChange}

                                />
                            </div>
                            <button type="submit" class="btn btn-primary" >Login</button>
                        </form>

                    </div>
                </div>
            </div>

        </div> */
    );
}

export default Login;