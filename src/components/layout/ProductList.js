import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import ProductListCSS from '../UI/Layout/ProductList.module.css'
import { Link } from 'react-router-dom';

const ProductList = () => {
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [currentData, setCurrentData] = useState([]);
  const itemsPerPage = 7;


  const loadData = async (sortingOrder) => {
    try {
      // Reset to the first page when changing the sorting order
      setCurrentPage(1);
  
      // Clear the old data
      setData([]);
  
      // Fetch data from the API based on the sorting order
      const response = await axios.get(`http://localhost:8080/api/v1/user/products/getAll_${sortingOrder}`);
      
      // Update the data state with the new data from the API
      setData(response.data);
    } catch (error) {
      // Handle errors if necessary
      console.error("Error fetching data:", error);
    }
  };
  
  useEffect(() => {
    // Load data initially with descending order
    loadData("desc");
  }, []);
  
  const handleSort = async (sortingOrder) => {
    // Load data based on the sorting order
    loadData(sortingOrder);
  };

  useEffect(() => {
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const pageData = data.slice(startIndex, endIndex);
    setCurrentData(pageData);
  }, [data, currentPage]);

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

    <div className={ProductListCSS['container1']}>

      <div className={ProductListCSS['container']} >
        <div className={ProductListCSS['box1']} >
          <div className={ProductListCSS['navbar']}>
            <div className={ProductListCSS['dropdown']}>
              <div className={ProductListCSS['dropbtn1']}>
              <a href='/petlist'> Tất cả</a>
              </div>

            </div>
            <div className={ProductListCSS['dropdown']}>
              <button className={ProductListCSS['dropbtn']}>Toàn quốc  <i class="fa fa-caret-down"></i>
              </button>
              <div className={ProductListCSS['dropdown-content']}>
                <a href="#"> Link1</a>
                <a href="#">Link 2</a>
                <a href="#">Link 3</a>
              </div>
            </div>
            <div className={ProductListCSS['dropdown']}>
              <button className={ProductListCSS['dropbtn']}>Loại sản phẩm <i class="fa fa-caret-down"></i>
              </button>
              <div className={ProductListCSS['dropdown-content']}>
                <a href="#">Link 1</a>
                <a href="#">Link 2</a>
                <a href="#">Link 3</a>
              </div>
            </div>
            <div className={ProductListCSS['dropdown']}>
              <button className={ProductListCSS['dropbtn']}>Tin đăng <i class="fa fa-caret-down"></i>
              </button>
              <div className={ProductListCSS['dropdown-content']}>
                <a href="#" onClick={() => handleSort("desc")} >Mới nhất</a>
                <a href="#" onClick={() => handleSort("asc")}>Cũ nhất</a>

              </div>
            </div>
          </div>
        </div>

        {currentData.map((product, index) => (

          <Link key={index} className={ProductListCSS['box']} to={`/product/detail/${product.product_id}`}>

            {product.img_productList.length > 0 && (
              <img

                src={product.img_productList[0]}

              />
            )}

            <div className={ProductListCSS['info']}>
              <div className={ProductListCSS['top-box']}>
                <label>Name: {product.product_name}</label>

                <div className={ProductListCSS['price']}>
                  <label>Price: {product.price}</label>
                </div>
              </div>
              <div className={ProductListCSS['below-box']}>
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



        <div className={ProductListCSS['IntersectBox']}>
          <div className={ProductListCSS['box2']}>
            <button onClick={prevPage} disabled={currentPage === 1}>
              Previous Page
            </button>
            <button onClick={nextPage} disabled={currentPage * itemsPerPage >= data.length}>
              Next Page
            </button>
          </div>
        </div>

      </div>

    </div>
  );
};

export default ProductList;