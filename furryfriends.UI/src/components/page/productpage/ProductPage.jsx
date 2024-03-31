import React, { useState, useEffect } from "react";
import Navbar from '../../layout/Navbar';
import Footer from '../../layout/Footer';
import ProductPageCSS from '../../UI/Layout/ProductPage.module.css'
import Slideshow from "../../layout/Slideshow";
import img1 from "../../../img/slide1.webp";
import img2 from "../../../img/Screenshot (208).png";
import img3 from "../../../img/Screenshot (209).png";
import axios from "axios";
import Cookies from "js-cookie";
import { Link } from "react-router-dom";



const collection = [
  { src: img1, caption: "" },
  { src: img2, caption: "" },
  { src: img3, caption: "" },
];


export default function ProductPage() {


  const [data, setData] = useState([]);
  const [pet, setPet] = useState([]);

  useEffect(() => {

    axios.get(`http://localhost:8080/api/v1/user/products/getAll_desc`).then((response) => {
      setData(response.data); // Lấy dữ liệu từ API và lưu vào state
    });


    axios.get(`http://localhost:8080/api/v1/user/pets/getAll_desc`).then((response) => {
      setPet(response.data); // Lấy dữ liệu từ API và lưu vào state
    });


  }, []);


  // Sắp xếp mảng data theo trường "date" (hoặc trường khác thích hợp) theo thứ tự giảm dần (sản phẩm mới nhất đầu tiên)
  const sortedData = [...data].sort((a, b) => new Date(b.date) - new Date(a.date));

  // Lấy ra 4 sản phẩm mới nhất
  const newestProducts = sortedData.slice(0, 4);



  // Sắp xếp mảng data theo trường "date" (hoặc trường khác thích hợp) theo thứ tự giảm dần (sản phẩm mới nhất đầu tiên)
  const sortedPet = [...pet].sort((a, b) => new Date(b.date) - new Date(a.date));

  // Lấy ra 4 dogcat mới nhất
  const newestPet = sortedPet.slice(0, 4);





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
              <div><h3>Tin đăng sản phẩm mới</h3></div>
              <div><a href="/product/list">xem thêm</a></div>
            </div>
            <div className={ProductPageCSS['listing-box']} >

              {newestProducts.map((product, index) => (
                <Link key={index} to={`/product/detail/${product.product_id}`} className={ProductPageCSS['listing']}>
                  <div className={ProductPageCSS['post']}>
                    <img src={product.img_productList[0]} alt={`Hình ảnh sản phẩm ${index + 1}`} />
                  </div>
                  <div className={ProductPageCSS['haha']}>
                  <h3>    {product.product_name}</h3>
                  <p className={ProductPageCSS['price']}> Giá: {product.price} VNĐ</p>
                  <p> Địa chỉ: {product.address}</p>
                  <p> Thời gian: {product.date} </p>
                  </div>
                </Link>
              ))}



            </div>

          </div>

          <div className={ProductPageCSS['box-body1']}>
            <div className={ProductPageCSS['top-body2']}>
              <div><h3>Tin đăng chó mèo mới</h3></div>
              <div><a href="/petlist">xem thêm</a></div>
            </div>
            <div className={ProductPageCSS['listing-box']} >

              {newestPet.map((product, index) => (
                <Link key={index} to={`/pet/detail/${product.pet_id}`} className={ProductPageCSS['listing']}>
                  <div className={ProductPageCSS['post']}>
                    <img src={product.img_petList[0]} alt={`Hình ảnh sản phẩm ${index + 1}`} />
                  </div>
                  <div>
                    <h3>  {product.pet_name}</h3>
                    <p className={ProductPageCSS['price']}> Giá: {product.pet_price} VNĐ</p>
                    <p> Địa chỉ: {product.address}</p>
                    <p> Thời gian :{product.date}  </p>
                  </div>
                </Link>
              ))}



            </div>

          </div>

        </div>

      </div>
      <Footer />
    </div>
  );
}
