import React, { useState, useEffect } from 'react';
import PostViewDetailCSS from '../UI/Layout/PostViewDetailCSS.module.css'
import { useParams, Navigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import axios from 'axios';


export default function PostViewDetail() {
  const navigate = Navigate
  const { post_id } = useParams();
  const [comment, Setcomment] = useState('');
  const [cm, SetShow] = useState([]);

  const account = Cookies.get('sub');
  const [post, setPost] = useState({
    user_name: '',
    title: '',
    image: '',
    content: '',
    publishdate: '',
    total_comment: '',
    total_like: ''
  })

  const { user_name } = post



  useEffect(() => {
    LoadPosts();
    if (account) {
      loadComment();
    }

  }, []);

  const loadComment = async () => {
    if (account) {
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
      axios.get(`http://localhost:8080/api/v1/user/comments/getALL/${post_id}`)
        .then((response) => {
          SetShow(response.data)
        })
    } else {
      window.location.assign('/login')
    }
  }

  const LoadPosts = async () => {

    axios.get(`http://localhost:8080/api/v1/user/posts/one/${post_id}`)
      .then((response) => {
        setPost(response.data)
      })
  };

  const onInputChange = (event) => {
    Setcomment(event.target.value);
  } // nhận dữ liệu từ user input vào


  const onSubmit = async (event) => {
    event.preventDefault();


    if (account) {
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
        console.log(comment)
        axios.post(`http://localhost:8080/api/v1/user/comments/${account}-${post_id}`, {
          comment: comment,
          headers: {
            'Content-Type': 'application/json'
          }
        });
        // Sau khi thêm bình luận thành công, thêm bình luận mới vào danh sách hiện có
        SetShow([...cm, { comment: comment }]);
        window.location.reload();
        Setcomment('');


      } catch (err) {
        alert("Error");
      }


    }
  }


  const handleLikeOrUnline = async (id) => {


    if (account) {
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

  }




  return (

    <div className={PostViewDetailCSS["body"]}>

      <div className={PostViewDetailCSS["body-blog1"]}>
        <div className={PostViewDetailCSS["body-blog1-detail"]}>
          <div className={PostViewDetailCSS["name"]}>
            <div ><i><i class="fa fa-user" aria-hidden="true"></i></i> {post.user_name}</div>
            <div className={PostViewDetailCSS["time"]}>{post.publishdate}</div>
          </div>
          <div className={PostViewDetailCSS["body-blog1-title"]}>
            <h3 className={PostViewDetailCSS["body-blog1-title-detail"]}> {post.title} </h3>
          </div>
          <div className={PostViewDetailCSS["body-blog1-describe"]}>
            <p className={PostViewDetailCSS["body-blog1-describe-detail"]}>
              {post.content}
            </p>
          </div>
        </div>
        <div className={PostViewDetailCSS["body-blog1-img"]}>
          <img className={PostViewDetailCSS["body-blog1-img-detail"]} src={post.image} />
        </div>

        <div className={PostViewDetailCSS["body-blog1-button"]}>
          {account ? (
            <button className={PostViewDetailCSS["body-blog1-button-likeButton"]} aria-hidden="true" onClick={() => { handleLikeOrUnline(post.post_id); }}>
              {post.total_like} <i class="far fa-thumbs-up fa-2x"></i>
            </button>
          ) : (
         
            <button className={PostViewDetailCSS["body-blog1-button-likeButton"]} aria-hidden="true"
            >

             <a href='/login'> {post.total_like} <i class="far fa-thumbs-up fa-2x"></i> </a>
            </button>
          )}
          <button className={PostViewDetailCSS["body-blog1-button-commentButton"]} aria-hidden="true" >
            {post.total_comment} <i class="far fa-comment fa-2x"></i>
          </button>
        </div>

        {account ? (
          <div className={PostViewDetailCSS["comment-input"]}>
            <form onSubmit={onSubmit}>
              {/* Đây là nơi bạn đặt phần nhập comment */}
              <textarea
                type="text"
                placeholder="Viết bình luận của bạn"
                maxLength="1500"
                name='comment'
                value={comment}
                onInput={onInputChange} />
              <button type='submit'>Đăng bình luận</button>
            </form>
          </div>
        ) : (<div className={PostViewDetailCSS["comment-input"]}>
          <a href='/login' >
            <textarea
              type="text"
              placeholder="Viết bình luận của bạn"
              maxLength="1500"
              name='comment'
              value={comment}
              onInput={onInputChange} />
            <button type='submit'>Đăng bình luận</button>
          </a>
        </div>)

        }

        {cm.map((cmm, index) => (
          <div key={index}>
            <div className={PostViewDetailCSS["body-blog-1-otherComments"]}>
              <div className={PostViewDetailCSS["body-blog1-ortherComments-userName"]}>
                <h4 className={PostViewDetailCSS["body-blog1-ortherComments-userName-detail"]}>{cmm.user_name}</h4>
              </div>
              <div className={PostViewDetailCSS["body-blog1-ortherComments-theComment"]}>
                <p class="body-blog1-ortherComments-theComment-detail">{cmm.comment}</p>
              </div>
            </div>
          </div>
        ))}


      </div>

    </div>

  );

}