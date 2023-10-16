
import PostUpFormCSS from '../UI/Layout/PostUpForm.module.css'
import Inputform from './FormInput';
import React from 'react'

export default function PostUpForm() {
   
    
        return( 

            <div className={PostUpFormCSS["container"]}>
                <div className={PostUpFormCSS["row"]}>

                    <div className={PostUpFormCSS["right-column"]}>


                        <div className={PostUpFormCSS["form-group"]}>
                            <label>Tiêu đề tin đăng và Mô tả chi tiết</label>
                            <input type="text" placeholder="Tiêu đề tin đăng" />
                            Tối đa 50 kí tự
                            <label>Hình ảnh</label>
                            <input type="file" placeholder="link ảnh" />
                            
                           
                            <Inputform/>
                          
                        </div>


                        <div className={PostUpFormCSS['button']}>
                            <div className={PostUpFormCSS["button-btn"]}>
                          <a  href='#' className={PostUpFormCSS['link']}> <i class="fa-regular fa-paper-plane"></i>  ĐĂNG TIN</a>
                            </div>

                        </div>


                    </div>
                </div>
            </div>
        
        );
    
        }
