import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import ViewProductA from './ViewProductUser.module.css';
import { Link } from "react-router-dom";
import Navbar from '../../layout/Navbar';
import Footer from '../../layout/Footer';
const ViewProductUser = () => {
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
 const account = Cookies.get("productAccount");

  useEffect(() => {
    if(account){
    loadProducts();

    axios.get(`http://localhost:8080/api/v1/user/pets/${account}`).then((response) => {
      setData1(response.data); // Lấy dữ liệu từ API và lưu vào state
    });
  }

  }, [account]);
  const loadProducts = async () => {
    axios.get(`http://localhost:8080/api/v1/user/products/${account}`).then((response) => {
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


    <div className={ViewProductA['container2']}>
      <Navbar />
      <div className={ViewProductA['container']} >
        <div className={ViewProductA['box1']} >
          <div className={ViewProductA['navbar']}>
            <div className={ViewProductA['dropdown']}> <a href='/viewallproduct'>Sản phẩm cho chó mèo : ( {data.length} )</a></div>
            <div className={ViewProductA['dropdown']}> <a href='/viewalldogcat'>Chó/Mèo: ( {data1.length} )</a></div>
          </div>
        </div>

        {currentData.map((product, index) => (
  <Link key={index} className={ViewProductA['box']} to={`/product/detail/${product.product_id}`}>
       

            {product.img_productList.length > 0 && (
              <img

                src={product.img_productList[0]}

              />
            )}

            <div className={ViewProductA['info']}>
              <div className={ViewProductA['top-box']}>
                <label>Name: {product.product_name}</label>

                <div className={ViewProductA['price']}>
                  <label>Price: {product.price} VNĐ</label>
                </div>
              </div>
              <div className={ViewProductA['below-box']}>
                <div>
                  <label>Address: {product.address}</label>

                </div>
                <div>
                  <label>Date : {product.date}</label>

                </div>
              </div>

            </div>
          

        
          </Link>
        ))}

     

        <div className={ViewProductA['IntersectBox']}>
          <div className={ViewProductA['box2']}>
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

export default ViewProductUser;