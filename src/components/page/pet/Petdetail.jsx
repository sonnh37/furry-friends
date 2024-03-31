import React, { useState, useEffect } from 'react';
import PetdetailCSS from './Petdetail.module.css';
import { useParams , Link} from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
import Gallery from 'react-image-gallery';
import 'react-image-gallery/styles/css/image-gallery.css';
import Navbar from '../../layout/Navbar';
import Footer from '../../layout/Footer';


function Petdetail() {
  const { pet_id } = useParams(); // lấy id từ user bấm vào  product
  const account = Cookies.get('sub')
  const [pet, setPetData] = useState([]);

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

  const [product, setPet] = useState({
    user_name: '',
    pet_name: '',
    pet_price: '',
    img_petList: [],
    description: '',
    date: '',
    phone: '',
    title: '',
    address: '',
    development_stage: '',
    type: ''

  })

  useEffect(() => {
    loadUser();

    axios.get(`http://localhost:8080/api/v1/user/pets/getAll_desc`).then((response) => {
      setPetData(response.data); // Lấy dữ liệu từ API và lưu vào state
    });
  }, [pet_id]);


  const loadUser = async () => {
    axios.get(`http://localhost:8080/api/v1/user/pets/one/${pet_id}`)
      .then((response) => {
        setPet(response.data)
      })
  };


  // Sắp xếp mảng data theo trường "date" (hoặc trường khác thích hợp) theo thứ tự giảm dần (sản phẩm mới nhất đầu tiên)
  const sortedPet = [...pet].sort((a, b) => new Date(b.date) - new Date(a.date));

  // Lấy ra 4 dogcat mới nhất
  const newestPet = sortedPet.slice(0, 4);


  return (
    <div>
      <Navbar/>
      <div className={PetdetailCSS['container']}>
        <div className={PetdetailCSS['rowbase']}>
          <div className={PetdetailCSS['box']}>
            <div className={PetdetailCSS['box1']}>
              <div className={PetdetailCSS['box1-1']}>


                <ImageGallery images={product.img_petList} />


                <div className={PetdetailCSS['info-box1']}>
                  <div className={PetdetailCSS['headerbox1']}>{product.pet_name}</div>
                  <div className={PetdetailCSS['price']}> Price: {product.pet_price} đ</div>
                  <div className={PetdetailCSS['address']}><i className="fa-solid fa-location-dot"></i> Địa chỉ: {product.address}</div>
                  <div className={PetdetailCSS['date']}> <i className="fa-solid fa-calendar-days"></i> Date: {product.date}</div>
                </div>
              </div>
              <div className={PetdetailCSS['box1-1']}>
                <div className={PetdetailCSS['info-box2']}>
                  <div className={PetdetailCSS['headerbox1']}>Miêu tả </div>
                  <div className={PetdetailCSS['info-detail']}>
                    <div className={PetdetailCSS['description']}>
                      {product.description}
                    </div>
                    <div className={PetdetailCSS['detail']}>
                    <div className={PetdetailCSS['Birh']}> <i className="fa-solid fa-paw"></i> Giống thú cưng: {product.pet_name} </div>
                      <div className={PetdetailCSS['Type']}> <i className="fa-solid fa-paw"></i> Loài: {product.type}</div>
                     
                    </div>
                    <div className={PetdetailCSS['Birh']}> <i className="fa-solid fa-calendar-days"></i> Tình trạng: {product.development_stage} </div>
                  </div>
                </div>
              </div>
            </div>

            {account? (
              <div className={PetdetailCSS['box2']}>
                <div className={PetdetailCSS['box2-1']}>
                  <div className={PetdetailCSS['top-box2']}>
                    <div><i className="fa-regular fa-user"></i> {product.user_name}</div>
                    <button>  <a href='/ViewAllProductUser'>Xem trang</a></button>
                  </div>
                  <div className={PetdetailCSS['below-box2']}>
                    <button>Bấm số ĐT: {product.phone}</button>
                  </div>
                </div>

              </div>
            ):null
}
          </div>
          <div className={PetdetailCSS['box-body1']}>
            <div className={PetdetailCSS['top-body2']}>
              <div><h3>Tin đăng chó mèo mới</h3></div>
              <div><a href="/petlist">xem thêm</a></div>
            </div>
            <div className={PetdetailCSS['listing-box']} >
     
                {newestPet.map((product, index) => (
                  <Link key={index} to={`/pet/detail/${product.pet_id}`} className={PetdetailCSS['listing']} 
                  onClick={() => {
                    window.scrollTo(0, 0); // Scroll to the top of the page
                
                  }}
                  >
                    <div className={PetdetailCSS['post']}>
                      <img src={product.img_petList[0]  } alt={`Hình ảnh sản phẩm ${index + 1}`} />
                    </div>
                  <h3>  {product.pet_name}</h3>
                    <p> Địa chỉ: {product.address}</p>
                    <p> Giá :{`${product.pet_price} VNĐ`} </p>
                    <p> Thời gian :{product.date} </p>
                  </Link>
                ))}
        

           
            </div>

          </div>
        </div>
      </div>
      <Footer/>
    </div>
  );
}

export default Petdetail;