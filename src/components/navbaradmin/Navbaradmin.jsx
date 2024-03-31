
import React, { useState } from 'react';
import navCss from './navbaradmin.module.css'
import logo from '../UI/Logo/logo.png'
import Cookies from 'js-cookie';
const Navbar = () => {
    const [activeButton, setActiveButton] = useState(null);

    const handleButtonClick = (buttonName) => {
        setActiveButton(buttonName);
    };
    const account = Cookies.get('sub')
    const handleButtonStaff = () => {

        window.location.href = '/admin';

    }
    const handleButtonMember = () => {

        window.location.href = '/managemember';

    }
    const handleButtonAddStaff = () => {

        window.location.href = '/addstaff';

    }
    
    const handleLogout = () => {

        Cookies.remove('jwtToken');
        Cookies.remove('sub');
        Cookies.remove('role');
        window.location.href = '/login';

    }
    return (
        <div className={navCss["navbar"]}>
         
                <div>
                    <div className={navCss["Iconimage"]}> 
                    <a href='/home'>
                    <img src={logo} alt="Icon" />
                    </a>
                    </div >
                    
                    <div className={navCss["quick-menu"]}>   Account: {account} </div>
                    <div className={navCss["quick-menu"]}>

                        <p>Quick menu</p>
                        <button
                            className={activeButton === 'Button1' ? 'active' : ''}
                            onClick={ handleButtonStaff}
                           
                        >
                         Staff
                        </button>
                        <button
                            className={activeButton === 'Button2' ? 'active' : ''}
                            onClick={handleButtonMember}
                        >
                            Member
                        </button>

                        <button
                            className={activeButton === 'Button3' ? 'active' : ''}
                            onClick={handleButtonAddStaff}

                        >
                     Add Staff
                        </button>



                        {/* Thêm các button khác nếu cần */}
                    </div>
                </div>
                 <div className={navCss["LogOut"]}>      <a href="#" onClick={handleLogout}>Đăng Xuất</a></div>
            </div>

           
       
    );
};

export default Navbar;