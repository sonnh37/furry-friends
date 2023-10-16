import React from 'react'
import Navbar from '../../layout/Navbar';
import Footer from '../../layout/Footer';
import ProductPageCSS from '../../UI/Layout/ProductPage.module.css'
import Slideshow from "../../layout/Slideshow";
import img1 from "../../../img/slide1.webp";
import img2 from "../../../img/Screenshot (208).png";
import img3 from "../../../img/Screenshot (209).png";

const collection = [
  { src: img1, caption: "" },
  { src: img2, caption: "" },
  { src: img3, caption: "" },
];


export default function ProductPage() {
  return (

    <div>
      <Navbar />
      <div className={ProductPageCSS['body-container']} >
        <div className={ProductPageCSS['container2']} >
          <div className={ProductPageCSS['box-body1']}>
            <div className={ProductPageCSS['top-body1']}>

              <div className={ProductPageCSS["slider"]}>

                <div className={ProductPageCSS["slider-content"]}>
                  <Slideshow
                    input={collection}
                    ratio={`3:2`}
                    mode={`automatic`}
                    timeout={`3000`}
                  />
                </div>
              </div>



            </div>
            <div className={ProductPageCSS['below-body1']} >
              <div className={ProductPageCSS['top-bl-bd1']}>
                <h3>Khám phá danh mục</h3>
              </div>
              <div className={ProductPageCSS['category-container']}>
     
                <div className={ProductPageCSS['category']} >
                  <div> <img src="https://i.pinimg.com/564x/cf/e6/6c/cfe66c20d118d57ff423014e06e224b1.jpg" /></div>
                  <div> <a href="/product/list">Chó</a></div>

                </div>
             
                <div className={ProductPageCSS['category']} >
                  <div><img src="https://i.pinimg.com/236x/7c/ea/c6/7ceac6be8e135bdfa43ff4880b3e9a02.jpg" /></div>
                  <div> <a href="/product/list">Mèo</a>
                  </div>

                </div>
                <div className={ProductPageCSS['category']} >
                  <div>
                    <img src="https://i.pinimg.com/236x/ca/c0/0c/cac00c5cb2431b0117202b35c99ff1ef.jpg"
                    />
                  </div>
                  <div className={ProductPageCSS['title']}>
                    <a href="/product/list">Sản phâm cho chó mèo</a>
                  </div>
                </div>
                <div className={ProductPageCSS['category']} >
                  <div>
                    <img src="https://zoipet.com/wp-content/uploads/2019/04/tam-cho-dung-cach.jpg" />
                  </div>
                  <div>
                    <a href="/product/list">Bảng Servive</a>
                  </div>
                </div>
              </div>


            </div>
          </div>


          <div className={ProductPageCSS['box-body1']}>
            <div className={ProductPageCSS['top-body2']}>
              <div><h3>Tin đăng chó mèo</h3></div>
              <div><a href="/product/list">xem thêm</a></div>
            </div>
            <div className={ProductPageCSS['listing-box']} >
              <a href="/product/detail" className={ProductPageCSS['listing']} >
                <div className={ProductPageCSS['post']} >
                  <img src="https://i.pinimg.com/564x/69/a8/2a/69a82a563703fc99d79af166cadebdf6.jpg" alt="Hình ảnh sản phẩm 1" />
                </div>
                <h3>Corgi</h3>
                <p>TP.Hồ Chí Minh</p>
                <p>xxx đồng</p>

              </a>
              <a href="/product/detail" className={ProductPageCSS['listing']} >
                <div className={ProductPageCSS['post']} >
                  <img src="https://i.pinimg.com/236x/da/a6/d0/daa6d0c2e1f940fc6d2fd4307373d85d.jpg" alt="Hình ảnh sản phẩm 2" />
                </div>
                <h3>Tên sản phẩm 2</h3>
                <p>Địa chỉ sản phẩm 2</p>
                <p>Giá sản phẩm 2</p>
              </a>
              <a href="/product/detail" className={ProductPageCSS['listing']} >
                <div className={ProductPageCSS['post']} >
                  <img src="https://i.pinimg.com/564x/f6/bd/ea/f6bdea6dc8146897d445254f8833de3f.jpg" alt="Hình ảnh sản phẩm 3" />
                </div>
                <h3>Tên sản phẩm 3</h3>
                <p>Địa chỉ sản phẩm 3</p>
                <p>Giá sản phẩm 3</p>
              </a>
              <a href="/product/detail" className={ProductPageCSS['listing']} >
                <div className={ProductPageCSS['post']} >
                  <img src="https://i.pinimg.com/236x/bf/a9/b1/bfa9b174f912ef465ceb29e6dd69c8a6.jpg" alt="Hình ảnh sản phẩm 4" />
                </div>
                <h3>Tên sản phẩm 4</h3>
                <p>Địa chỉ sản phẩm 4</p>
                <p>Giá sản phẩm 4</p>
              </a>
            </div>

          </div>
          <div className={ProductPageCSS['box-body1']} >
            <div className={ProductPageCSS['top-body2']}>
              <div><h3>Tin đăng sản phẩm mới</h3></div>
              <div><a href="/product/list">xem thêm</a></div>
            </div>
            <div className={ProductPageCSS['listing-box']} >
              <a href="/product/detail" className={ProductPageCSS['listing']} >
                <div className={ProductPageCSS['post']} >
                  <img src="https://i.pinimg.com/236x/28/f0/88/28f08803811ca9742f3c2163fbf404e9.jpg" alt="Hình ảnh sản phẩm 1" />
                </div>
                <h3>Tên sản phẩm 1</h3>
                <p>Địa chỉ sản phẩm 1</p>
                <p>Giá sản phẩm 1</p>
              </a>
              <a href="/product/detail" className={ProductPageCSS['listing']} >
                <div className={ProductPageCSS['post']} >
                  <img src="https://i.pinimg.com/236x/f8/dd/e3/f8dde38d3e77fb686a9dd09f35065ddd.jpg" alt="Hình ảnh sản phẩm 2" />
                </div>
                <h3>Tên sản phẩm 2</h3>
                <p>Địa chỉ sản phẩm 2</p>
                <p>Giá sản phẩm 2</p>
              </a>
              <a href="/product/detail" className={ProductPageCSS['listing']} >
                <div className={ProductPageCSS['post']} >
                  <img src="https://i.pinimg.com/236x/1b/66/17/1b6617cf347d13acab4a1815223f1225.jpg" alt="Hình ảnh sản phẩm 3" />
                </div>
                <h3>Tên sản phẩm 3</h3>
                <p>Địa chỉ sản phẩm 3</p>
                <p>Giá sản phẩm 3</p>
              </a>
              <a href="/product/detail" className={ProductPageCSS['listing']} >
                <div className={ProductPageCSS['post']} >
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
      <Footer/>
    </div>
  );
}
