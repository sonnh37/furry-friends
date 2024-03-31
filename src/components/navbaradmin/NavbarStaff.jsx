
import React, { useState } from 'react';
import navCss from './navbaradmin.module.css'
import logo from '../UI/Logo/logo.png'
import Cookies from 'js-cookie';
const NavbarStaff = () => {
    const [activeButton, setActiveButton] = useState(null);

    const handleButtonClick = (buttonName) => {
        setActiveButton(buttonName);
    };
    const account = Cookies.get('sub')
  
    const handleButtonMember = () => {

        window.location.href = '/staff';

    }
    const handleButtonPost = () => {

        window.location.href = '/managepoststaff';

    }
    const handleButtonProduct = () => {

        window.location.href = '/staffmangeproduct';

    }
    const handleButtonDogCat = () => {

        window.location.href = '/staffmanagedogcat';

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
                            className={activeButton === 'Button2' ? 'active' : ''}
                            onClick={handleButtonMember}
                        >
                            Member
                        </button>

                        <button
                            className={activeButton === 'Button3' ? 'active' : ''}
                            onClick={handleButtonPost}
                        >
                     Post
                        </button>
                        
                        <button
                            className={activeButton === 'Button3' ? 'active' : ''}
                            onClick={handleButtonProduct}
                        >
                     Product
                        </button>
                        
                        <button
                            className={activeButton === 'Button3' ? 'active' : ''}
                            onClick={handleButtonDogCat}
                        >
                     Dog and Cat
                        </button>



                        {/* Thêm các button khác nếu cần */}
                    </div>
                </div>
                 <div className={navCss["LogOut"]}>      <a href="#" onClick={handleLogout}>Đăng Xuất</a></div>
            </div>

           
       
    );
};

export default NavbarStaff;