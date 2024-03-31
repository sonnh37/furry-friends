

import PostViewCSS from '../UI/Layout/PostView.module.css'
import axios from 'axios';
import Cookies from 'js-cookie';
import React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

export default function PostView() {

    const account = Cookies.get("sub");
    const [posts, SetPost] = useState([]);
    useEffect(() => {

        axios.get(`http://localhost:8080/api/v1/user/posts/getALL_desc`).then((response) => {
            SetPost(response.data); // Lấy dữ liệu từ API và lưu vào state
        });
    }, []);


    return (

        <div className={PostViewCSS["body1"]}>


            {account ? (
                <a className={PostViewCSS['clickpost']} href="/post/up">
                    <div className={PostViewCSS["body-blog2"]}>
                        <div className={PostViewCSS["body-blog1-detail"]}>
                            <div className={PostViewCSS["name"]}>
                                <div class=""><i><i class="fa fa-user" aria-hidden="true"></i></i> {account}</div>
                                <div className={PostViewCSS["input11"]}> <input placeholder='Bạn đang nghĩ gì ?'>
                                </input></div>
                            </div>

                        </div>

                    </div>
                </a>
            ) : null
            }
            {posts.map((post) => (
                <Link className={PostViewCSS['clickpost']} to={`/post/detail/${post.post_id}`}>
                    <div className={PostViewCSS["body-blog1"]}>
                        <div className={PostViewCSS["body-blog1-detail"]}>
                            <div className={PostViewCSS["name"]}>
                                <div class=""><i><i class="fa fa-user" aria-hidden="true"></i></i>{post.user_name}</div>
                                <div className={PostViewCSS["time"]}> {post.publishdate}</div>
                            </div>
                            <div className={PostViewCSS["body-blog1-title"]}>
                                <p className={PostViewCSS["body-blog1-title-detail"]}> {post.title} </p>
                            </div>
                        </div>
                        <div className={PostViewCSS["body-blog1-img"]}>
                            <img src={post.image} className={PostViewCSS["body-blog1-img-detail"]} />
                        </div>

                        <div className={PostViewCSS["body-blog1-button"]}>
                            <button className={PostViewCSS["body-blog1-button-likeButton"]} aria-hidden="true">
                                {post.total_like} <i class="fa fa-thumbs-up "></i>
                            </button>
                            <button className={PostViewCSS["body-blog1-button-commentButton"]} aria-hidden="true">
                                {post.total_comment} <i class="fa fa-comment "></i>
                            </button>
                        </div>
                    </div>


                </Link>
            ))}

        </div>


    );
}
