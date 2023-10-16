

import PostViewCSS from '../UI/Layout/PostView.module.css'


export default function PostView() {
return (

    <div class="body">
        <a className={PostViewCSS['clickpost']} href="">
            <div className={PostViewCSS["body-blog1"]}>
                <div  className={PostViewCSS["body-blog1-detail"]}>
                    <div className={PostViewCSS["name"]}>
                        <div class=""><i><i class="fa fa-user" aria-hidden="true"></i></i> Minh Khôi</div>
                        <div className={PostViewCSS["time"]}>9:35 4/10/2023</div>
                    </div>
                    <div className={PostViewCSS["body-blog1-title"]}>
                        <p className={PostViewCSS["body-blog1-title-detail"]}> Tựa đề </p>
                    </div>
                </div>
                <div className={PostViewCSS["body-blog1-img"]}>
                    <img className={PostViewCSS["body-blog1-img-detail"]} src="https://qph.cf2.quoracdn.net/main-qimg-8c67f62ce025bb072a4173292d22ae82-lq" alt="The dog is smiling" />
                </div>

                <div className={PostViewCSS["body-blog1-button"]}>
                    <button className={PostViewCSS["body-blog1-button-likeButton"]} aria-hidden="true">
                        100 <i class="fa fa-thumbs-up fa-2x"></i>
                    </button>
                    <button className={PostViewCSS["body-blog1-button-commentButton"]} aria-hidden="true">
                        100 <i class="fa fa-comment fa-2x"></i>
                    </button>
                </div>
            </div>
        </a>
       
        <a className={PostViewCSS['clickpost']} href="">
            <div className={PostViewCSS["body-blog1"]}>
                <div  className={PostViewCSS["body-blog1-detail"]}>
                    <div className={PostViewCSS["name"]}>
                        <div class=""><i><i class="fa fa-user" aria-hidden="true"></i></i> Minh Khôi</div>
                        <div className={PostViewCSS["time"]}>9:35 4/10/2023</div>
                    </div>
                    <div className={PostViewCSS["body-blog1-title"]}>
                        <p className={PostViewCSS["body-blog1-title-detail"]}> Tựa đề </p>
                    </div>
                </div>
                <div className={PostViewCSS["body-blog1-img"]}>
                    <img className={PostViewCSS["body-blog1-img-detail"]} src="https://qph.cf2.quoracdn.net/main-qimg-8c67f62ce025bb072a4173292d22ae82-lq" alt="The dog is smiling" />
                </div>

                <div className={PostViewCSS["body-blog1-button"]}>
                    <button className={PostViewCSS["body-blog1-button-likeButton"]} aria-hidden="true">
                        100 <i class="fa fa-thumbs-up fa-2x"></i>
                    </button>
                    <button className={PostViewCSS["body-blog1-button-commentButton"]} aria-hidden="true">
                        100 <i class="fa fa-comment fa-2x"></i>
                    </button>
                </div>
            </div>
        </a>
        <a className={PostViewCSS['clickpost']} href="">
            <div className={PostViewCSS["body-blog1"]}>
                <div  className={PostViewCSS["body-blog1-detail"]}>
                    <div className={PostViewCSS["name"]}>
                        <div class=""><i><i class="fa fa-user" aria-hidden="true"></i></i> Minh Khôi</div>
                        <div className={PostViewCSS["time"]}>9:35 4/10/2023</div>
                    </div>
                    <div className={PostViewCSS["body-blog1-title"]}>
                        <p className={PostViewCSS["body-blog1-title-detail"]}> Tựa đề </p>
                    </div>
                </div>
                <div className={PostViewCSS["body-blog1-img"]}>
                    <img className={PostViewCSS["body-blog1-img-detail"]} src="https://qph.cf2.quoracdn.net/main-qimg-8c67f62ce025bb072a4173292d22ae82-lq" alt="The dog is smiling" />
                </div>

                <div className={PostViewCSS["body-blog1-button"]}>
                    <button className={PostViewCSS["body-blog1-button-likeButton"]} aria-hidden="true">
                        100 <i class="fa fa-thumbs-up fa-2x"></i>
                    </button>
                    <button className={PostViewCSS["body-blog1-button-commentButton"]} aria-hidden="true">
                        100 <i class="fa fa-comment fa-2x"></i>
                    </button>
                </div>
            </div>
        </a>
    </div>


);
}
