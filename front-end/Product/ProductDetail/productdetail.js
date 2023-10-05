
import './App.css';
import React from "react";
import SlideshowGallery from "./component/SlideshowGallery";


export default class App extends React.Component {


  render() {
    const collection = [
      { src: 'https://vienmoitruong5014.org.vn/wp-content/uploads/2023/03/anh-cho-boo-de-thuong_022907414.jpg', caption: "Dễ Yêu" },
      { src: 'https://nhanvietluanvan.com/wp-content/uploads/2023/05/suu-tam-ngay-50-hinh-nen-cho-cute-de-thuong-dang-yeu-nhat_2-1-720x620.jpg', caption: "Dễ Thương" },
      { src: 'https://img.meta.com.vn/Data/image/2021/09/21/hinh-anh-cho-con-3.jpg', caption: "Dễ Ăn" },
      { src: 'https://congtyketoanhanoi.edu.vn/hinh-cun-con-de-thuong/imager_32_13647_700.jpg', caption: "Dễ Ngủ" },

    ];
    return (

      <div class="container">
        <div class="rowbase">
          <div class="box">
            <div class="box1">
              <div class="box1-1">
                <SlideshowGallery input={collection} ratio={`3:2`} mode={`manual`} />
                <div class="info-box1">
                  <div class="headerbox1">Đây là một con chó </div>
                  <div class="price"> Giá tiền iu em 3000</div>
                  <div class="address"><i class="fa-solid fa-location-dot"></i> Địa chỉ trong tim em</div>
                  <div class="date"> <i class="fa-solid fa-calendar-days"></i> ngày tháng năm</div>

                </div>
              </div>
              <div class="box1-1">
                <div class="info-box2">
                  <div class="headerbox1">Miêu tả </div>
                  <div class="info-detail">
                    <div class="description">Sáng nay, lúc ở quê lên, mẹ đã mang về cho em một người bạn bốn chân rất đáng yêu. Đó là một chú chó nhỏ vừa đầy một tháng tuổi. Em đặt tên cho chú là Nấm.

                      Nấm là một chú chó cỏ bình thường, nhưng vẫn rất là dễ thương. Chú to như một cái tô con con của nhà em, với bộ lông vàng nhạt mềm mại. Cái đầu của chú to như cái nắm tay của em, với hai cái tai nhỏ vẫn còn đang cụp xuống. Đôi mắt chú ta thì tròn xoe, đen lay láy, lúc nào cũng nhìn chăm chú vào mọi người với vẻ thơ ngây. Cái mõm của chú ta ngắn và tròn như quả bầu non mới nhú. Cái mũi thì đen bóng, khi nào cũng ươn ướt, củng củng vào tay em để đòi chơi cùng.

                      Nấm tham ăn lắm, nên cái bụng chú lúc nào cũng tròn xoe. Nhìn chú ăn, em thấy vui lắm, nên cứ đến giờ lại lại đem cháo và sữa ra cho chú ăn. Những lúc ấy, cái đuôi nhỏ xíu của Nấm sẽ vẫy vẫy liên hồi như cánh quạt. Yêu nhất ở Nắm, là bốn cái chân ngắn cũn, với bàn chân bụ bẫm, mềm mại. Vì chân ngắn quá, nên chú di chuyển thật ngỗ nghĩnh, khiến cái bụng tròn đôi khi chạm vào cả mặt đất.

                    </div>
                    <div class="detail">
                      <div class="Type"> <i class="fa-solid fa-paw"></i> Loại: Chó con</div>
                      <div class="Birh"> <i class="fa-solid fa-calendar-days"></i> Năm sinh: 1 tuổi</div>
                    </div>
                  </div>
                </div>
              </div>

            </div>

            <div class="box2">
              <div class="box2-1">
                <div class="top-box2">
                  <div><i class="fa-regular fa-user"></i> Nguyễn Thị A</div>
                  <button> Xem trang</button>
                </div>


                <div class="below-box2">
                  <button>Bấm số ĐT: 0908977171</button>
                </div>


              </div>
            </div>
          </div>
          <div class="box-body1">
            <div class="top-body2">
              <div><h3>Tin đăng sản phẩm mới</h3></div>
              <div><a href="">xem thêm</a></div>
            </div>
            <div class="listing-box">
              <a href="link1.html" class="listing">
                <div class="post">
                  <img src="https://i.pinimg.com/236x/28/f0/88/28f08803811ca9742f3c2163fbf404e9.jpg" alt="Hình ảnh sản phẩm 1" />
                </div>
                <h3>Tên sản phẩm 1</h3>
                <p>Địa chỉ sản phẩm 1</p>
                <p>Giá sản phẩm 1</p>
              </a>
              <a href="link2.html" class="listing">
                <div class="post">
                  <img src="https://i.pinimg.com/236x/f8/dd/e3/f8dde38d3e77fb686a9dd09f35065ddd.jpg" alt="Hình ảnh sản phẩm 2" />
                </div>
                <h3>Tên sản phẩm 2</h3>
                <p>Địa chỉ sản phẩm 2</p>
                <p>Giá sản phẩm 2</p>
              </a>
              <a href="link3.html" class="listing">
                <div class="post">
                  <img src="https://i.pinimg.com/236x/1b/66/17/1b6617cf347d13acab4a1815223f1225.jpg" alt="Hình ảnh sản phẩm 3" />
                </div>
                <h3>Tên sản phẩm 3</h3>
                <p>Địa chỉ sản phẩm 3</p>
                <p>Giá sản phẩm 3</p>
              </a>
              <a href="link4.html" class="listing">
                <div class="post">
                  <img src="https://i.pinimg.com/236x/ca/c0/0c/cac00c5cb2431b0117202b35c99ff1ef.jpg" alt="Hình ảnh sản phẩm 4" />
                </div>
                <h3>Tên sản phẩm 4</h3>
                <p>Địa chỉ sản phẩm 4</p>
                <p>Giá sản phẩm 4</p>
              </a>
            </div>

          </div>
        </div>


      </div>

    );
  }
}







