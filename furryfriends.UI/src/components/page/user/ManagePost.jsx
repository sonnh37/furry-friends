

import ManagePostCSS from './ManagePost.module.css'
import axios from 'axios';
import Cookies from 'js-cookie';
import React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import NavbarPost from '../../layout/NavbarPost'
import Footer from '../../layout/Footer'

export default function PostView() {

    const account = Cookies.get("sub");
    const [posts, SetPost] = useState([]);
    const [selectedPostId, setSelectedPostId] = useState(null);
    const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);
    const openDeleteModal = () => {
        setDeleteModalOpen(true);
    };
    // Function to close the delete confirmation modal
    const closeDeleteModal = () => {
        setDeleteModalOpen(false);
    };

    const jwtToken = Cookies.get('jwtToken');
    axios.interceptors.request.use(
        config => {
            config.headers.Authorization = `Bearer ${jwtToken}`;
            return config;
        },
        error => {
            return Promise.reject(error);
        }
    );

    const deletePost = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/v1/user/posts/${account}-${id}`).then((res) => {
                console.log(res.data);
                if (res.data == "Xoa thanh cong") {
                    LoadPosts();
                }
                else if (res.data == "not exist") {
                    LoadPosts();
                }

            }, fail => {
                console.error(fail); // Error!
            });
        }
        catch (err) {
            alert(err);
        }

    };

    const LoadPosts = async () => {
        // truyen token---------------------------
        axios.get(`http://localhost:8080/api/v1/user/posts/getALL/${account}`).then((response) => {
            SetPost(response.data); // Lấy dữ liệu từ API và lưu vào state
        });
    }
    useEffect(() => {
        LoadPosts();

    }, []);



    const handleLikeOrUnline = async (id) => {

        axios.post(`http://localhost:8080/api/v1/user/likes/${account}-${id}`).then(response => {
            if (response.data == 'Them like thanh cong') {
                LoadPosts();
            } else if (response.data == 'Da like') {
                axios.delete(`http://localhost:8080/api/v1/user/likes/${account}-${id}`).then(response => {
                    if (response.data == 'Unlike thanh cong') {
                        LoadPosts();
                    }
                }
                )

            }

        }
        )

    }





    return (
        <div>
            <NavbarPost />
            <div className={ManagePostCSS["container"]}>



                <a className={ManagePostCSS['clickpost']} href="/post/up">
                    <div className={ManagePostCSS["body-blog2"]}>
                        <div className={ManagePostCSS["body-blog1-detail"]}>
                            <div className={ManagePostCSS["name"]}>
                                <div class=""><i><i class="fa fa-user" aria-hidden="true"></i></i> {account}</div>
                                <div className={ManagePostCSS["input11"]}> <input placeholder='Bạn đang nghĩ gì ?'>
                                </input></div>
                            </div>

                        </div>

                    </div>
                </a>


                {posts.map((post, index) => (
                    <div key={index} className={ManagePostCSS['clickpost']} >
                        <div className={ManagePostCSS["body-blog1"]}>
                            <div className={ManagePostCSS["body-blog1-detail"]}>
                                <div className={ManagePostCSS["name"]}>
                                    <div className={ManagePostCSS["name-btn"]}>

                                        <div ><i><i class="fa fa-user" aria-hidden="true"></i></i>{post.user_name}</div>
                                        <div className={ManagePostCSS["dropdown"]}>
                                            <button className={ManagePostCSS["dropbtn"]}><i class="fa-solid fa-ellipsis"></i></button>
                                            <div className={ManagePostCSS["dropdown-content"]}>
                                                <Link to={`/editpost/${post.post_id}`}>Edit</Link>

                                                <button className=""
                                                    onClick={() => {
                                                        setSelectedPostId(post.post_id); // Lưu ID của người dùng sẽ bị xóa
                                                        openDeleteModal(); // Mở modal xác nhận
                                                    }}  >Delete</button>
                                                <button href="#">Ẩn</button>
                                            </div>
                                        </div>
                                    </div>


                                    <div className={ManagePostCSS["time"]}> {post.publishdate}</div>
                                </div>
                                <div className={ManagePostCSS["body-blog1-title"]}>
                                    <p className={ManagePostCSS["body-blog1-title-detail"]}> {post.title} </p>
                                </div>
                            </div>
                            <div className={ManagePostCSS["body-blog1-img"]}>
                                <img src={post.image} className={ManagePostCSS["body-blog1-img-detail"]} />
                            </div>

                            <div className={ManagePostCSS["body-blog1-button"]}>
                                <button className={ManagePostCSS["body-blog1-button-likeButton"]} aria-hidden="true" onClick={() => {handleLikeOrUnline(post.post_id);}}>
                                    {post.total_like} <i class="fa fa-thumbs-up "></i>
                                </button>
                                <button className={ManagePostCSS["body-blog1-button-commentButton"]} aria-hidden="true">

                                    {post.total_comment} <i class="fa fa-comment "></i>
                                </button>
                            </div>
                        </div>




                    </div>
                ))}
                {isDeleteModalOpen && ( // Render the modal if isDeleteModalOpen is true
                    <div className={ManagePostCSS["delete-modal"]}>

                        <div>
                            <p>Bạn có chắc chắn muốn xóa?</p>


                            <div className={ManagePostCSS["cancel-yes"]} >
                                <button onClick={() => { deletePost(selectedPostId); closeDeleteModal(); }}>
                                    Có
                                </button>
                                <button onClick={closeDeleteModal}>
                                    Không
                                </button>
                            </div>
                        </div>
                    </div>
                )}





            </div>
            <Footer />
        </div>
    );
}
