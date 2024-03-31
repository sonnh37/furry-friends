import React from 'react'
import { useEffect, useState } from "react";
import NavbarPostCSS from '../UI/Layout/NavbarPost.module.css';
import logo from "../UI/Logo/logo.png"
import Cookies from 'js-cookie';
export default function NavbarPost() {
    const account = Cookies.get('sub')
const userRole = Cookies.get('role')
    const handleLogout = () => {

        Cookies.remove('jwtToken');
        Cookies.remove('sub');
        Cookies.remove('role');
        window.location.href = '/login';

    }


    const [isLoggedIn, setIsLoggedIn] = useState(false); // Initially, the user is not logged in
    const [userData, setUserData] = useState(null);
    const [searchQuery, setSearchQuery] = useState('');

    const handleSearch = () => {
      // Lưu dữ liệu tìm kiếm vào LocalStorage
      localStorage.setItem('searchQuery', searchQuery);
  
      // Chuyển đến trang B
      window.location.href = '/postSearch'; // Thay đổi URL để chuyển trang
    }
    useEffect(() => {
        const cookies = document.cookie.split(';');
        let subData = null;

        for (const cookie of cookies) {
            const [name, value] = cookie.split('=');
            if (name.trim() === 'sub') {
                subData = decodeURIComponent(value);
                break;
            }
        }

        if (subData) {
            setIsLoggedIn(!!subData);
            setUserData(subData);
        }
    }, []); // Empty dependency array to run this effect once when the component mounts

    return (
        <div className={NavbarPostCSS['container']}>
            <div className={NavbarPostCSS['menu']}>
                <div className={NavbarPostCSS['header_menu']}>

                    <span className={NavbarPostCSS['info-left']} >
                        <span>
                            <span className={NavbarPostCSS['cach1']}></span>
                            <a href='/post' className={NavbarPostCSS['left']}>Post</a>
                            <span className={NavbarPostCSS['cach1']}></span>
                        </span>
                        <span>
                            <a href="/product" className={NavbarPostCSS['left']}>Sản phẩm</a>
                            <span className={NavbarPostCSS['cach1']}></span>
                        </span>
                        <span>
                            <a href="/" className={NavbarPostCSS['left']}>Trang chủ</a>
                            <span className={NavbarPostCSS['cach1']}></span>
                        </span>
                        <span>
                            <a href="" className={NavbarPostCSS['left']}>Liên hệ</a>
                        </span>
                    </span>
                </div>


                <div className={NavbarPostCSS['container1']}>
                    <div className={NavbarPostCSS['box1']}>
                        <div className={NavbarPostCSS['logo-f']}>
                            <a href='/product'>
                                <img src={logo} alt='' />
                            </a>
                        </div>
                    </div>
                    <div className={NavbarPostCSS['box2']}>
                        <div className={NavbarPostCSS['dropdown']}>
                            <button className={NavbarPostCSS['dropbtn']}><i className="fa-solid fa-bars"></i> Danh mục
                            </button>


                            <div className={NavbarPostCSS['dropdown-content']}>
                                <a href="#">Chó</a>
                                <a href="#">Mèo</a>
                                <a href="#">Sản phẩm cho chó mèo</a>
                            </div>

                        </div>
                        <i className=""></i>
                    </div>
                    <div className={NavbarPostCSS['box3']}>
                        <div className={NavbarPostCSS['input-container']}>
                            <input
                                type="text"
                                className={NavbarPostCSS['input']}
                                placeholder="Tìm kiếm bài post trên dogcat"
                                value={searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}
                            />
                            <button className={NavbarPostCSS['search-button']} onClick={handleSearch}>
                                <i className="fa fa-search" aria-hidden="true"></i> Tìm kiếm
                            </button>
                        </div>

                    </div>
                    <div className={NavbarPostCSS['box4']} >
                        <div className={NavbarPostCSS['login-container']} >
                            <div className={NavbarPostCSS['dropdown']}>
                                <button className={NavbarPostCSS['dropbtn']}><i><i class="fa fa-user" aria-hidden="true"></i></i> Tài Khoản <i class="fa-solid fa-chevron-down"></i>
                                </button>


                                <div className={NavbarPostCSS['dropdown-content']}>
                                    {isLoggedIn ? (
                                        <>

                                            <a> Tài khoản: {userData}</a>
                                            <a href="/profile">Quản lý tài khoản</a>
                                            {userRole == 2 && (
                                                  <a href="/staff">Quản lý member</a>
                                            )}
                                             {userRole == 3 && (
                                                  <a href="/admin">Quản lý chung hệ thống</a>
                                            )}
                                            <a href="/managepost">Quản lý bài post</a>
                                            <a href="/manageproduct">Quản lý tin đăng bán</a>
                                            <a href="#" onClick={handleLogout}>Đăng Xuất</a>
                                        </>
                                    ) : (
                                        <>
                                            <a href="/login">Đăng nhập</a>
                                            <a href="/register">Đăng ký</a>
                                            <a href="/login">Quản lý tài khoản</a>
                                            <a href="/login">Quản lý bài post</a>
                                            <a href="/login">Quản lý tin đăng bán</a>
                                        </>
                                    )}
                                </div>

                            </div>
                            <i className=""></i>

                            <div className={NavbarPostCSS['post-button']}>
                                {account ? ( // Check if there is an account (user is authenticated)
                                    <button className={NavbarPostCSS['button']}>
                                        <a href="/product/up1" className={NavbarPostCSS['button-detail']}>
                                            <i className="fa-solid fa-paper-plane"></i> Đăng bài
                                        </a>
                                    </button>
                                ) : (
                                    // Redirect to the login page if the user is not authenticated
                                    <button className={NavbarPostCSS['button']}>
                                        <a href="/login" className={NavbarPostCSS['button-detail']}>
                                            <i className="fa-solid fa-paper-plane"></i> Đăng bài
                                        </a>
                                    </button>
                                )}
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}
