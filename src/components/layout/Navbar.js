import React from 'react'
import { useEffect, useState } from "react";
import NavbarCSS from '../UI/Layout/Navbar.module.css';
import logo from "../UI/Logo/logo.png"
import Cookies from 'js-cookie';
import { useGridRowSelection } from '@mui/x-data-grid/internals';
export default function Navbar() {
    const account = Cookies.get('sub')
const userRole = Cookies.get("role")
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
      window.location.href = '/productSearch'; // Thay đổi URL để chuyển trang
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
                            <input
                                type="text"
                                className={NavbarCSS['input']}
                                placeholder="Tìm kiếm sản phẩm trên dogcat"
                                value={searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}
                            />
                            <button className={NavbarCSS['search-button']} onClick={handleSearch}>
                                <i className="fa fa-search" aria-hidden="true"></i> Tìm kiếm
                            </button>
                        </div>

                    </div>
                    <div className={NavbarCSS['box4']} >
                        <div className={NavbarCSS['login-container']} >
                            <div className={NavbarCSS['dropdown']}>
                                <button className={NavbarCSS['dropbtn']}><i><i class="fa fa-user" aria-hidden="true"></i></i> Tài Khoản <i class="fa-solid fa-chevron-down"></i>
                                </button>


                                <div className={NavbarCSS['dropdown-content']}>
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

                            <div className={NavbarCSS['post-button']}>
                                {account ? ( // Check if there is an account (user is authenticated)
                                    <button className={NavbarCSS['button']}>
                                        <a href="/product/up1" className={NavbarCSS['button-detail']}>
                                            <i className="fa-solid fa-paper-plane"></i> Đăng bài
                                        </a>
                                    </button>
                                ) : (
                                    // Redirect to the login page if the user is not authenticated
                                    <button className={NavbarCSS['button']}>
                                        <a href="/login" className={NavbarCSS['button-detail']}>
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
