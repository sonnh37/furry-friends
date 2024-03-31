import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';


const ManageProduct = () => {
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [currentData, setCurrentData] = useState([]);
  const itemsPerPage = 7;
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


  useEffect(() => {

    axios.get(`http://localhost:8080/api/v1/user/products/getAll`).then((response) => {
      setData(response.data); // Lấy dữ liệu từ API và lưu vào state
    });
  }, []);

 


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
    <div className="App">
      {currentData.map((product, index) => (
        <a href='/'>
        <form key={index}>
          <div>
            <label>Số:</label>
            <input key={index} readOnly></input>
          </div>
          <div>
            <label>Name:</label>
            <input type="text" value={product.product_name} readOnly />
          </div>
          <div>
            <label>Price:</label>
            <input type="text" value={product.price} readOnly />
          </div>
          <div>
            <label>Address:</label>
            <input type="text" value={product.description} readOnly />
          </div>
          <div>
            <label>Date:</label>
            <input type="text" value={product.date} readOnly />
          </div>
        </form>
        </a>
      ))}

      <button onClick={prevPage} disabled={currentPage === 1}>
        Previous Page
      </button>
      <button onClick={nextPage} disabled={currentPage * itemsPerPage >= data.length}>
        Next Page
      </button>
    </div>
  );
};

export default ManageProduct;