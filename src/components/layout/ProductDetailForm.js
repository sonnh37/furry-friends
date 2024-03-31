import React, { useState, useEffect } from 'react';
import ProductDetaiCSS from '../UI/Layout/ProductDetail.module.css';
import { useParams, Link   } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
import Gallery from 'react-image-gallery';
import 'react-image-gallery/styles/css/image-gallery.css';



function ProductDetailForm() {
  const { product_id } = useParams(); // lấy id từ user bấm vào  product
  const account = Cookies.get('sub')
  const [data, setData] = useState([]);

  ///// 
  const ImageGallery = ({ images }) => {
    const imageItems = images.map((image, index) => ({
      original: image,
      // thumbnail: image,

    }));

    return (
      <Gallery items={imageItems} />
    );
  }; // slide image get từ axios api

  const [product, setProduct] = useState({
    product_name: '',
    price: '',
    img_productList: [],
    description: '',
    date: '',
    phone: '',
    title: '',
    address: ''

  })


  useEffect(() => {
   
      axios.get(`http://localhost:8080/api/v1/user/products/one/${product_id}`)
        .then((response) => {
          setProduct(response.data)

        })

        axios.get(`http://localhost:8080/api/v1/user/products/getAll_desc`).then((response) => {
          setData(response.data); // Lấy dữ liệu từ API và lưu vào state
        });
  
  }, [product_id]);

   // Sắp xếp mảng data theo trường "date" (hoặc trường khác thích hợp) theo thứ tự giảm dần (sản phẩm mới nhất đầu tiên)
   const sortedData = [...data].sort((a, b) => new Date(b.date) - new Date(a.date));

   // Lấy ra 4 sản phẩm mới nhất
   const newestProducts = sortedData.slice(0, 4);
 

   const handleViewAllProduct = () => {
    // Lưu product.account vào cookies
    Cookies.set('productAccount', product.account);

  };


  return (
    <div>
      <div className={ProductDetaiCSS['container']}>
        <div className={ProductDetaiCSS['rowbase']}>
          <div className={ProductDetaiCSS['box']}>
            <div className={ProductDetaiCSS['box1']}>
              <div className={ProductDetaiCSS['box1-1']}>


                <ImageGallery images={product.img_productList} />


                <div className={ProductDetaiCSS['info-box1']}>
                  <div className={ProductDetaiCSS['headerbox1']}>{product.product_name}</div>
                  <div className={ProductDetaiCSS['price']}> Price: {product.price} đ</div>
                  <div className={ProductDetaiCSS['address']}><i className="fa-solid fa-location-dot"></i> Địa chỉ: {product.address}</div>
                  <div className={ProductDetaiCSS['date']}> <i className="fa-solid fa-calendar-days"></i> Date: {product.date}</div>
                </div>
              </div>
              <div className={ProductDetaiCSS['box1-1']}>
                <div className={ProductDetaiCSS['info-box2']}>
                  <div className={ProductDetaiCSS['headerbox1']}>Miêu tả </div>
                  <div className={ProductDetaiCSS['info-detail']}>
                    <div className={ProductDetaiCSS['description']}>
                      {product.description}
                    </div>
                    <div className={ProductDetaiCSS['detail']}>
                      <div className={ProductDetaiCSS['Type']}> <i className="fa-solid fa-paw"></i> Loại sản phẩm: Phụ kiện, Thức ăn, Dịch vụ</div>
                      <div className={ProductDetaiCSS['Birh']}> <i className="fa-solid fa-calendar-days"></i> Tình trạng: Tốt </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            {account? (
              <div className={ProductDetaiCSS['box2']}>
                <div className={ProductDetaiCSS['box2-1']}>
                  <div className={ProductDetaiCSS['top-box2']}>
                    <div><i className="fa-regular fa-user"></i> {product.user_name}</div>
                    <button onClick={handleViewAllProduct}><a href='/viewallproduct'>Xem trang</a></button>
                    {/* <button>  <Link to={`/viewallproduct/${product.account}`}>Xem trang</Link></button> */}
                  </div>
                  <div className={ProductDetaiCSS['below-box2']}>
                    <button>Bấm số ĐT: {product.phone}</button>
                  </div>
                </div>

              </div>
            ):null
}
          </div>
          <div className={ProductDetaiCSS['box-body1']}>
            <div className={ProductDetaiCSS['top-body2']}>
              <div><h3>Tin đăng sản phẩm mới</h3></div>
              <div><a href="/product/list">xem thêm</a></div>
            </div>
            <div className={ProductDetaiCSS['listing-box']} >
     
                {newestProducts.map((product, index) => (
                  <Link key={index} to={`/product/detail/${product.product_id}`} className={ProductDetaiCSS['listing']} 
                  
                  onClick={() => {
                    window.scrollTo(0, 0); // Scroll to the top of the page
                
                  }}
                  >
                    
                    <div className={ProductDetaiCSS['post']}>
                      <img src={product.img_productList[0]  } alt={`Hình ảnh sản phẩm ${index + 1}`} />
                    </div>
                    <div>
                  <h3>   {product.product_name}</h3>
                  <p> Giá: {product.price}</p>
                    <p> Địa chỉ: {product.address}</p>
                    <p> Giá :{`${product.price} Vnd`} </p>
                    </div>
                  </Link>
                ))}
        

           
            </div>

          </div>
        </div>
      </div>
    </div>
  );
}

export default ProductDetailForm;