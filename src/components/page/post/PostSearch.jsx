

import PostSearchCSS from '../../UI/Layout/PostSearch.module.css'
import axios from 'axios';
import Cookies from 'js-cookie';
import React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Navbar from '../../layout/NavbarPost'
import Footer from '../../layout/Footer'
import NotFounder from "../../../img/search_no_found_keyword.png"
export default function PostSearch() {

    const account = Cookies.get("sub");
    const [posts, SetPost] = useState([]);
    const savedSearchQuery = localStorage.getItem('searchQuery');
    const queryParams = {
      q: `${savedSearchQuery}`, // Ví dụ về tham số truy vấn "q"
    };
    useEffect(() => {
const LoadPost = async () => {
        axios.get(`http://localhost:8080/api/v1/user/posts/getSearch_desc`, {
            params: queryParams,
            
                  }).then((response) => {
            SetPost(response.data); // Lấy dữ liệu từ API và lưu vào state
        });
    }
    LoadPost();
    }, []);


    return (
 <div>
    <Navbar/>
        <div className={PostSearchCSS["body1"]}>


            {account ? (
                <a className={PostSearchCSS['clickpost']} href="/post/up">
                    <div className={PostSearchCSS["body-blog2"]}>
                        <div className={PostSearchCSS["body-blog1-detail"]}>
                            <div className={PostSearchCSS["name"]}>
                                <div class=""><i><i class="fa fa-user" aria-hidden="true"></i></i> {account}</div>
                                <div className={PostSearchCSS["input11"]}> <input placeholder='Bạn đang nghĩ gì ?'>
                                </input></div>
                            </div>

                        </div>

                    </div>
                </a>
            ) : null
            }
          
          {posts.length >0 ? (
            posts.map((post) => (
                <Link className={PostSearchCSS['clickpost']} to={`/post/detail/${post.post_id}`}>
                    <div className={PostSearchCSS["body-blog1"]}>
                        <div className={PostSearchCSS["body-blog1-detail"]}>
                            <div className={PostSearchCSS["name"]}>
                                <div class=""><i><i class="fa fa-user" aria-hidden="true"></i></i>{post.user_name}</div>
                                <div className={PostSearchCSS["time"]}> {post.publishdate}</div>
                            </div>
                            <div className={PostSearchCSS["body-blog1-title"]}>
                                <p className={PostSearchCSS["body-blog1-title-detail"]}> {post.title} </p>
                            </div>
                        </div>
                        <div className={PostSearchCSS["body-blog1-img"]}>
                            <img src={post.image} className={PostSearchCSS["body-blog1-img-detail"]} />
                        </div>

                        <div className={PostSearchCSS["body-blog1-button"]}>
                            <button className={PostSearchCSS["body-blog1-button-likeButton"]} aria-hidden="true">
                                {post.total_like} <i class="fa fa-thumbs-up "></i>
                            </button>
                            <button className={PostSearchCSS["body-blog1-button-commentButton"]} aria-hidden="true">
                                {post.total_comment} <i class="fa fa-comment "></i>
                            </button>
                        </div>
                    </div>


                </Link>
            ))
        
          ) : (
            <div className={PostSearchCSS['clickpost1']}>
                <div><img src= {NotFounder} /></div>
                <div>   <p>Không tìm thấy sản phẩm.</p></div>
         <button><a href='/post'>Quay lại trang chủ</a> </button>
            </div>
          )
        }
         
        </div>
        <Footer/>
        </div>

    );
}
