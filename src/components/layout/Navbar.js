import React from 'react'
import NavbarCSS from '../UI/Layout/Navbar.module.css';
import logo from "../UI/Logo/logo.png"

export default function Navbar() {
  
    return (
        <div className={NavbarCSS['container']}>
            <div className={NavbarCSS['menu']}>
                <div className={NavbarCSS['header_menu']}>

                    <span className={NavbarCSS['info-left']} >
                        <span>
                            <span className={NavbarCSS['cach1']}></span>
                            <a href='/post' className={NavbarCSS['left']}>Post</a>
                            <span className={NavbarCSS['cach1']}></span>
                        </span>
                        <span>
                            <a href="/product" className={NavbarCSS['left']}>Sản phẩm</a>
                            <span className={NavbarCSS['cach1']}></span>
                        </span>
                        <span>
                            <a href="/" className={NavbarCSS['left']}>Trang chủ</a>
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
                            <input type="text" className={NavbarCSS['input']} placeholder="Tìm kiếm sản phẩm trên dogcat" />
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
                        <div className={NavbarCSS['dropdown']}>
                            <button className={NavbarCSS['dropbtn']}><i><i class="fa fa-user" aria-hidden="true"></i></i> Tài Khoản <i class="fa-solid fa-chevron-down"></i>
                            </button>


                            <div className={NavbarCSS['dropdown-content']}>
                                <a href="/login">Đăng nhập/Đăng Ký</a>
                                <a href="#">Quản lý tài khoản</a>
                                <a href="#">Quản lý bài post</a>
                                <a href="#">Quản lý tin đăng bán</a>
                                <a href="#">Đăng Xuất</a>
                            </div>

                        </div>
                        <i className=""></i>
                            <div className={NavbarCSS['post-button']} >
                                <button className={NavbarCSS['button']} >
                                    <a href='/product/up1' className={NavbarCSS['button-detail']}><i className="fa-solid fa-paper-plane" ></i> Đăng bài </a>
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}
