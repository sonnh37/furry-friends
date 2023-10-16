import React, { useState } from 'react';
import PostViewDetailCSS from '../UI/Layout/PostViewDetailCSS.module.css'


export default function PostViewDetail() {
return (

    <div className={PostViewDetailCSS["body"]}>
  <a href="">
  <div className={PostViewDetailCSS["body-blog1"]}>
    <div className={PostViewDetailCSS["body-blog1-detail"]}>
      <div className={PostViewDetailCSS["name"]}>
        <div ><i><i class="fa fa-user" aria-hidden="true"></i></i> Minh Khôi</div>
        <div className={PostViewDetailCSS["time"]}>9:35 4/10/2023</div>
      </div>
      <div className={PostViewDetailCSS["body-blog1-title"]}>
        <p className={PostViewDetailCSS["body-blog1-title-detail"]}> Tựa đề </p>
      </div>
      <div className={PostViewDetailCSS["body-blog1-describe"]}>
        <p className={PostViewDetailCSS["body-blog1-describe-detail"]}>Đây là mô tả của blog, vd như
            chú chó của tôi rất vui khi được chụp hình, nó thường cười nhe răng
            khi thấy máy ảnh quay về hướng của nó, tôi cũng thấy đó là niềm vui của tôi
            khi nhìn nó vui vẻ.
        </p>
    </div>
    </div>
    <div className={PostViewDetailCSS["body-blog1-img"]}>
      <img className={PostViewDetailCSS["body-blog1-img-detail"]} src="smileofdog.jpg" alt="The dog is smiling"/>
    </div>
  
    <div className={PostViewDetailCSS["body-blog1-button"]}>
        <button className={PostViewDetailCSS["body-blog1-button-likeButton"]} aria-hidden="true">
          100 <i class="far fa-thumbs-up fa-1x"></i>
        </button>
        <button className={PostViewDetailCSS["body-blog1-button-commentButton"]} aria-hidden="true">
         100 <i class="far fa-comment fa-1x"></i>
        </button>
    </div>

    <div className={PostViewDetailCSS["body-blog-1-otherComments"]}>
      <div className={PostViewDetailCSS["body-blog1-ortherComments-userName"]}>
          <h4 className={PostViewDetailCSS["body-blog1-ortherComments-userName-detail"]}>Hoàng Sơn</h4>
      </div>
      <div className={PostViewDetailCSS["body-blog1-ortherComments-theComment"]}>
          <p class="body-blog1-ortherComments-theComment-detail">Comment số 1</p>
      </div>
      <hr>
      <div className={PostViewDetailCSS["body-blog1-ortherComments-userName"]}>
          <h4 class="body-blog1-ortherComments-userName-detail">Minh Quân</h4>
      </div>
      <div className={PostViewDetailCSS["body-blog1-ortherComments-theComment"]}>
          <p className={PostViewDetailCSS["body-blog1-ortherComments-theComment-detail"]}>Comment số 2</p>
      </div>
      </hr>
      <div className={PostViewDetailCSS["body-blog1-ortherComments-userName"]}>
          <h4 className={PostViewDetailCSS["body-blog1-ortherComments-userName-detail"]}>Minh Vương</h4>
      </div>
      <div className={PostViewDetailCSS["body-blog1-ortherComments-theComment"]}>
          <p className={PostViewDetailCSS["body-blog1-ortherComments-theComment-detail"]}>Comment số 3</p>
      </div>
  </div>
  </div>
</a>
</div>

);

}