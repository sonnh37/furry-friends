import React from 'react'
import NavbarCSS from '../UI/Layout/Navbar.module.css';
import logo from "../UI/Img/logo.png"
import { useNavigate } from 'react-router-dom';
export default function Navbar() {
    const navigate = useNavigate();
    const btnConvert = () =>{
        navigate("/product/up");
    }
    return (
        <div className={NavbarCSS['container']}>
            <div className={NavbarCSS['menu']}>
                <div className={NavbarCSS['header_menu']}>

                    <span className={NavbarCSS['info-left']} >
                        <span>
                            <span className={NavbarCSS['cach1']}></span>
                            <a href='' className={NavbarCSS['left']}>Post</a>
                            <span className={NavbarCSS['cach1']}></span>
                        </span>
                        <span>
                            <a href="/product" className={NavbarCSS['left']}>Sản phẩm</a>
                            <span className={NavbarCSS['cach1']}></span>
                        </span>
                        <span>
                            <a href="" className={NavbarCSS['left']}>Đánh giá</a>
                            <span className={NavbarCSS['cach1']}></span>
                        </span>
                        <span>
                            <a href="" className={NavbarCSS['left']}>Liên hệ</a>
                        </span>
                    </span>
                </div>


                <div className={NavbarCSS['container1']}>
                    <div className={NavbarCSS['box1']}>
                        <div className={NavbarCSS['logo-f']}>
                            <a href='/product'>
                                <img src={logo} alt='' />
                            </a>
                        </div>
                    </div>
                    <div className={NavbarCSS['box2']}>
                        <div className={NavbarCSS['dropdown']}>
                            <button className={NavbarCSS['dropbtn']}><i className="fa-solid fa-bars"></i> Danh mục
                            </button>


                            <div className={NavbarCSS['dropdown-content']}>
                                <a href="#">Chó</a>
                                <a href="#">Mèo</a>
                                <a href="#">Sản phẩm cho chó mèo</a>
                            </div>

                        </div>
                        <i className=""></i>
                    </div>
                    <div className={NavbarCSS['box3']}>
                        <div className={NavbarCSS['input-container']}>
                            <input type="text" className={NavbarCSS['input']} placeholder="Tìm kiếm..." />
                            <button className={NavbarCSS['search-button']} >
                                <i className="fa fa-search" aria-hidden="true"></i> Tìm kiếm
                            </button>
                        </div>
                        {/* <div className={NavbarCSS['quanly']}>
                    <a href='' className={NavbarCSS['dangnhap']} >
                      <i className="fa-solid fa-file"></i> Quản lý tin
                    </a>
                  </div> */}
                    </div>
                    <div className={NavbarCSS['box4']} >
                        <div className={NavbarCSS['login-container']} >
                            <div className={NavbarCSS['login-text']} >
                                <a href="/login" className={NavbarCSS['dangnhap']} >Đăng nhập</a>
                                <span className={NavbarCSS['cach4']}>/</span>
                                <a href="/register" className={NavbarCSS['dangnhap']} >Đăng ký </a>
                            </div>
                            <div className={NavbarCSS['post-button']} >
                                <button className={NavbarCSS['button']} onClick={btnConvert} >
                                    <i className="fa-solid fa-paper-plane" ></i> Đăng bài
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}
