import React, { useState, useEffect } from 'react';
import EditPostCSS from './EditPost.module.css'
import { useParams, useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import axios from 'axios';
import NavbarPost from '../../layout/NavbarPost';
import Footer from '../../layout/Footer';

export default function EditPost() {
  const navigate = useNavigate();
  const { post_id } = useParams();
  const [comment, Setcomment] = useState('');
  const [cm, SetShow] = useState([]);
  const [post, setPost] = useState({
    user_name: '',
    title: '',
    image: '',
    content: '',
    publishdate: '',
    total_like: '',
    total_comment: '',

  })

  const { title, content, image } = post
const account = Cookies.get('sub')
  const jwtToken = Cookies.get('jwtToken');
  axios.interceptors.request.use(
    (config) => {
      config.headers.Authorization = `Bearer ${jwtToken}`;
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  ); // truyen token---------------------------

  const loadComment = async () => {
    axios.get(`http://localhost:8080/api/v1/user/comments/getALL/${post_id}`)
      .then((response) => {
        SetShow(response.data)
      })
  }
  const onInputChange1 = (event) => {
    Setcomment(event.target.value);
  } // nhận dữ liệu từ user input vào
  const onSubmit1 = async (event) => {
    event.preventDefault();

    const errors = [];
    if (!comment.trim()) {
      errors.push("Nhập nội dung comment.");
    }
  
    if (errors.length > 0) {
      // Display error messages and prevent form submission
      alert(errors.join('\n'));
      return;
    }
    
    try {
      const account = Cookies.get('sub');
      console.log(comment)
      axios.post(`http://localhost:8080/api/v1/user/comments/${account}-${post_id}`,  {
       comment: comment ,
       headers: {
            'Content-Type': 'application/json'
        }
    });
     // Sau khi thêm bình luận thành công, thêm bình luận mới vào danh sách hiện có
     SetShow([...cm, {  comment: comment }]);
      Setcomment('');
      window.location.reload();
    } catch (err) {
      alert("Error");
    }


  }

  const onInputChange = (event) => {
    setPost({ ...post, [event.target.name]: event.target.value })
  } // nhận dữ liệu từ user input vào

  useEffect(() => {
    LoadPosts();
    loadComment();
  }, []);


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


  const LoadPosts = async () => {
    axios.get(`http://localhost:8080/api/v1/user/posts/one/${post_id}`)
      .then((response) => {
        setPost(response.data)
      })
  };

  const onSubmit = async (event) => {
    event.preventDefault();

    const errors = [];
    if (!post.title.trim()) {
      errors.push("Bạn chưa nhập tiêu đề bài post.");
    }
    if (!post.content.trim()) {
      errors.push("Bạn chưa nhập nội dung bài post.");
    }
    if (!post.image.trim()) {
      errors.push("Bạn chưa có hình ảnh trong bài post .");
    }


    if (errors.length > 0) {
      // Display error messages and prevent form submission
      alert(errors.join('\n'));
      return;
    }
    


    const account = Cookies.get('sub')
    axios.put(`http://localhost:8080/api/v1/user/posts/${account}-${post_id}`, post)
      .then(response => {
        if (response.data == 'Cap nhat thanh cong') {
          alert("Bạn đã lưu lại thành công")
          navigate('/managepost')
          window.location.reload();
        }else if(response.data == 'post not exist'){
          alert("Bạn không có quyền hạn này")
          navigate('/home')
        }
      })

  }


  const SubmitDelete = async (id) => {
    try {
axios.delete(`http://localhost:8080/api/v1/user/comments/${post_id}-${id}`).then(response =>{
  if(response.data == 'Xoa thanh cong'){
    alert("Xoa comment thanh cong")
    loadComment();
  }
})
    }catch(err){
      alert(err)
    }
  }

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
      <div className={EditPostCSS["body"]}>

        <div className={EditPostCSS["body-blog1"]}>
          <form className={EditPostCSS["body-blog1-detail"]} onSubmit={onSubmit}>
            <div className={EditPostCSS["name"]}>
              <div className={EditPostCSS["name-btn"]}>
                <div>
                  <i><i class="fa fa-user" aria-hidden="true"></i></i> {post.user_name}
                </div>
                <div><button type="submit">Save</button></div>
              </div>

              <div className={EditPostCSS["time"]}>{post.publishdate}</div>
            </div>
            <div className={EditPostCSS["body-blog1-title"]}>
              <input className={EditPostCSS["body-blog1-title-detail"]} name='title' value={title} onChange={onInputChange} />
            </div>
            <div className={EditPostCSS["body-blog1-describe"]}>
              <textarea className={EditPostCSS["body-blog1-describe-detail"]}
                name='content'
                value={content}
                onChange={onInputChange}
                placeholder="Mô tả chi tiết"
                maxLength="3000"
              />
            </div>
            <div className={EditPostCSS["body-blog1-img"]}>
              {post.image && (
                <div className="image-item">
                  <img
                    src={post.image}
                    alt={`Hình ảnh`}
                  />
                  <button onClick={() => setPost({ ...post, image: '' })}>Xóa</button>
                </div>
              )}

              {!post.image && (
                <div className={EditPostCSS['input-container']}>
                  <label> Đăng hình ảnh</label>
                  <input type="file" name='image' onChange={onFilesChange} />
                </div>
              )}

            </div>
           

          </form>
          <div className={EditPostCSS["body-blog1-button"]}>
              <button className={EditPostCSS["body-blog1-button-likeButton"]} aria-hidden="true" onClick={() => {handleLikeOrUnline(post.post_id);}}>
                {post.total_like} <i class="far fa-thumbs-up fa-2x"></i>
              </button>
              <button className={EditPostCSS["body-blog1-button-commentButton"]} aria-hidden="true">
                {post.total_comment} <i class="far fa-comment fa-2x"></i>
              </button>
            </div>

          <div className={EditPostCSS["comment-input"]}>
            <form onSubmit={onSubmit1}>
              {/* Đây là nơi bạn đặt phần nhập comment */}
              <textarea
                type="text"
                placeholder="Viết bình luận của bạn"
                maxLength="1500"
                name='comment'
                value={comment}
                onInput={onInputChange1} />
              <button type='submit'>Đăng bình luận</button>
            </form>
          </div>

          {cm.map((cmm, index) => (
            <div className={EditPostCSS["body-blog-1-otherComments"]} key={index}>
              <div className={EditPostCSS["haha"]}>
                <div>
                <h4 className={EditPostCSS["body-blog1-ortherComments-userName-detail"]}>{cmm.user_name}</h4>
                </div>
                <div className={EditPostCSS["dropdown"]}>
                                            <button className={EditPostCSS["dropbtn"]}><i class="fa-solid fa-ellipsis"></i></button>
                                            <div className={EditPostCSS["dropdown-content"]}>
                                            <button onClick={() => SubmitDelete(cmm.comment_id)}>Delete</button>
                                                <button href="#">Ẩn</button>
                                            </div>
                                        </div>

              </div>
              <div className={EditPostCSS["body-blog1-ortherComments-theComment"]}>
                <p class="body-blog1-ortherComments-theComment-detail">{cmm.comment}</p>
              </div>

            </div>

          ))}
        </div>
      </div>
      <Footer />
    </div>
  );

}