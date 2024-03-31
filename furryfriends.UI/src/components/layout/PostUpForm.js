import React, { useState } from 'react';
import PostUpFormCSS from '../UI/Layout/PostUpForm.module.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from "react-router-dom";

export default function PostUpForm() {
    let navigate = useNavigate()
    const [inputValue, setInputValue] = useState('');


    

    const [post, setPost] = useState({
        image: '',
        content: '',
        date: '',
        title: '',
    })

    const { content, title } = post

    const onFilesChange = (e) => {
        const file = e.target.files[0]; // Lấy ra hình ảnh đầu tiên từ danh sách các hình ảnh đã chọn

        if (file) {
            const reader = new FileReader();

            reader.onload = (event) => {
                const base64String = event.target.result;
                setPost({ ...post, image: base64String });
            };

            // Read the file as a Data URL (base64)
            reader.readAsDataURL(file);
        }
    };


    const onInputChange = (event) => {
        setPost({ ...post, [event.target.name]: event.target.value })
        setInputValue(...post.content, event.target.value);

    } // nhận dữ liệu từ user input vào


    const onSubmit = async (event) => {
        event.preventDefault();


        if (!post.title || !post.content || !post.image) {
            alert("Vui lòng điền đầy đủ thông tin");
            return;
        }
        const jwtToken = Cookies.get('jwtToken');
        axios.interceptors.request.use(
            config => {
                config.headers.Authorization = `Bearer ${jwtToken}`;
                return config;
            },
            error => {
                return Promise.reject(error);
            }
        ); // truyen token---------------------------



        try {

            const account = Cookies.get('sub');
            // Send formData to the server
             axios.post(`http://localhost:8080/api/v1/user/posts/${account}`, post, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            alert("Post registration successfully");
            navigate("/post")
            window.location.reload();

            // navigate("/manageproduct");
        } catch (err) {
            alert(err);
        }
    };







    const rows = inputValue.split('\n').length;
    return (
        <div className={PostUpFormCSS['body-container']}>
            <div className={PostUpFormCSS['container']}>
                <form className={PostUpFormCSS['row']} onSubmit={onSubmit}>

                    <div className={PostUpFormCSS['column']}>
                        <div className={PostUpFormCSS['right-column']} >

                            <div className={PostUpFormCSS['form-group1']} >
                                <label>Danh mục đăng Post</label>
                            </div>



                            <div className={PostUpFormCSS['form-group']} >
                                <label>Tiêu đề bài post và Mô tả chi tiết</label>
                                <input type="text" name='title' value={title} onChange={onInputChange} placeholder="Tiêu đề bài post" />
                                Tối đa 50 kí tự
                                <div className={PostUpFormCSS['input-container']}>
                                    <label>Mô tả chi tiết</label>
                                    <textarea
                                        name='content'
                                        value={content}
                                        onChange={onInputChange}

                                        className={PostUpFormCSS['auto-expanding-input']}
                                        placeholder="Mô tả chi tiết"
                                        rows={rows}
                                        maxLength="3000"

                                    />
                                    Tối đa 3000 kí tự

                                </div>
                            </div>
                            <div className={PostUpFormCSS['image-below1']} >
                                {post.image && (
                                    <div className="image-item">
                                        <img
                                            src={post.image}
                                            alt={`Hình ảnh`}
                                        />
                                        <button onClick={() => setPost({ ...post, image: '' })}>Xóa</button>
                                    </div>
                                )}
                            </div>
                            {!post.image && (
                                <div className={PostUpFormCSS['input-container']}>
                                    <label> Đăng hình ảnh</label>
                                    <input type="file" name='image' onChange={onFilesChange} />
                                </div>
                            )}

                            <div className={PostUpFormCSS['button']} >
                                <input type="submit" value="Đăng Post" className={PostUpFormCSS['button-btn']} onChange={onFilesChange}></input>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

        </div>
    );
}
