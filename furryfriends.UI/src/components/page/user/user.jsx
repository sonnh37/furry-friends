import React from 'react';
import UserCSS from './user.module.css';
import Profile from './Profile';
import Password from './Password';
import Navbar from '../../layout/Navbar'
import Footer from '../../layout/Footer'

export default function user() {
 

  return (
    <div>
      <Navbar/>
    <div className={UserCSS['body-container']}>
      <div className={UserCSS['container']}>
        <div className={UserCSS['row']}>
          <div className={UserCSS['column']}>
        
              <div className={UserCSS['form-group']} >
           
                <Profile/>
               <div className={UserCSS['info-user']}>
           <Password/>
          
               </div>
              </div>
           
              </div>

            

              


          
          </div>
        </div>
      </div>
  <Footer/>
    </div>
  );
}
