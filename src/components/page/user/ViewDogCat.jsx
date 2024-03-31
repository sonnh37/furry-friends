import ViewDogCatCSS from './ViewDogCat.module.css'
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';


import Navbar from '../../layout/Navbar';
import Footer from '../../layout/Footer';
const ViewDogCat = () => {
  const [data, setData] = useState([]);
  const [data1, setData1] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [currentData, setCurrentData] = useState([]);
  const itemsPerPage = 7;

 

  //--------------------------------------------
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

  const conchoson = Cookies.get('productAccount')
  useEffect(() => {
    loadPet();
    axios.get(`http://localhost:8080/api/v1/user/products/${conchoson}`).then((response) => {
      setData1(response.data); // Lấy dữ liệu từ API và lưu vào state
    });

  }, []);
  const loadPet = async () => {
    axios.get(`http://localhost:8080/api/v1/user/pets/${conchoson}`).then((response) => {
      setData(response.data); // Lấy dữ liệu từ API và lưu vào state
    });
  }



  useEffect(() => {
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const pageData = data.slice(startIndex, endIndex);
    setCurrentData(pageData);
  }, [data, currentPage]); //lọc dữ liệu trang hiện tại

  const nextPage = () => {
    if (currentPage * itemsPerPage < data.length) {
      setCurrentPage(currentPage + 1);
    }
  };

  const prevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  return (


    <div className={ViewDogCatCSS['container2']}>
      <Navbar />
      <div className={ViewDogCatCSS['container']} >
      <div className={ViewDogCatCSS['box1']} >
          <div className={ViewDogCatCSS['navbar']}>
            <div className={ViewDogCatCSS['dropdown']}> <a href='/viewallproduct'>Sản phẩm cho chó mèo :  ( {data1.length} ) </a></div>
            <div className={ViewDogCatCSS['dropdown']}> <a href='/viewalldogcat'>Chó/Mèo: ( {data.length} )</a></div>
          </div>
        </div>

        {currentData.map((product, index) => (

          <div key={index} className={ViewDogCatCSS['box']}>

            {product.img_petList.length > 0 && (
              <img

                src={product.img_petList[0]}

              />
            )}

            <div className={ViewDogCatCSS['info']}>
              <div className={ViewDogCatCSS['top-box']}>
                <label>Tên sản phẩm: {product.pet_name}</label>

                <div className={ViewDogCatCSS['price']}>
                  <label>Giá: {product.pet_price} VNĐ</label>
                </div>
              </div>
              <div className={ViewDogCatCSS['below-box']}>
                <div>
                  <label>Địa chỉ: {product.address}</label>

                </div>
                <div>
                  <label>Thời gian : {product.date}</label>

                </div>
              </div>

            </div>
          

          </div>

        ))}



        <div className={ViewDogCatCSS['IntersectBox']}>
          <div className={ViewDogCatCSS['box2']}>
            <button onClick={prevPage} disabled={currentPage === 1}>
              Previous Page
            </button>
            <button onClick={nextPage} disabled={currentPage * itemsPerPage >= data.length}>
              Next Page
            </button>
          </div>
        </div>
       
      </div>
      <Footer />
    </div>

  );
};

export default ViewDogCat;