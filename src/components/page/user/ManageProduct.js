import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import ManageAll from './ManageProduc.module.css';
import { Link } from "react-router-dom";
import Navbar from '../../layout/Navbar';
import Footer from '../../layout/Footer';
const ManageProduct = () => {
  const [data, setData] = useState([]);
  const [data1, setData1] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [currentData, setCurrentData] = useState([]);
  const itemsPerPage = 7;

  const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);
  const [selectedProductId, setSelectedProductId] = useState(null);

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

  const conchoson = Cookies.get('sub')
  useEffect(() => {
    loadProducts();

    axios.get(`http://localhost:8080/api/v1/user/pets/${conchoson}`).then((response) => {
      setData1(response.data); // Lấy dữ liệu từ API và lưu vào state
    });

  }, []);
  const loadProducts = async () => {
    axios.get(`http://localhost:8080/api/v1/user/products/${conchoson}`).then((response) => {
      setData(response.data); // Lấy dữ liệu từ API và lưu vào state
    });
  }
  const deleteUser = async (id) => {

    try {
      await axios.delete(`http://localhost:8080/api/v1/user/products/${conchoson}-${id}`).then((res) => {
        console.log(res.data);
        if (res.data == "Xoa thanh cong") {
          loadProducts();
        }
        else if (res.data == "not exist") {
          loadProducts();
        }

      }, fail => {
        console.error(fail); // Error!
      });
    }
    catch (err) {
      alert(err);
    }

  };
  const openDeleteModal = () => {
    setDeleteModalOpen(true);
  };

  // Function to close the delete confirmation modal
  const closeDeleteModal = () => {
    setDeleteModalOpen(false);
  };


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


    <div className={ManageAll['container2']}>
      <Navbar />
      <div className={ManageAll['container']} >
        <div className={ManageAll['box1']} >
          <div className={ManageAll['navbar']}>
            <div className={ManageAll['dropdown']}> <a href='/manageproduct'>Sản phẩm cho chó mèo : ( {data.length} )</a></div>
            <div className={ManageAll['dropdown']}> <a href='/managepet'>Chó/Mèo: ( {data1.length} )</a></div>
          </div>
        </div>

        {currentData.map((product, index) => (

          <div key={index} className={ManageAll['box']}>

            {product.img_productList.length > 0 && (
              <img

                src={product.img_productList[0]}

              />
            )}

            <div className={ManageAll['info']}>
              <div className={ManageAll['top-box']}>
                <label>Name: {product.product_name}</label>

                <div className={ManageAll['price']}>
                  <label>Price: {product.price} VNĐ</label>
                </div>
              </div>
              <div className={ManageAll['below-box']}>
                <div>
                  <label>Address: {product.address}</label>

                </div>
                <div>
                  <label>Date : {product.date}</label>

                </div>
              </div>

            </div>
            <div className={ManageAll['button-e-d']}>

              <button>
                <Link className={ManageAll['btn']}
                  to={`/editproduct/${product.product_id}`} >
                  Edit

                </Link>
              </button>
              <button
                className=""
                onClick={() => {
                  setSelectedProductId(product.product_id); // Lưu ID của người dùng sẽ bị xóa
                  openDeleteModal(); // Mở modal xác nhận
                }}                  >
                Delete
              </button>
              <button>
                <Link className={ManageAll['btn']}
                  to={`/product/detail/${product.product_id}`} >
                  View

                </Link>
              </button>
            </div>

          </div>

        ))}



        <div className={ManageAll['IntersectBox']}>
          <div className={ManageAll['box2']}>
            <button onClick={prevPage} disabled={currentPage === 1}>
              Previous Page
            </button>
            <button onClick={nextPage} disabled={currentPage * itemsPerPage >= data.length}>
              Next Page
            </button>
          </div>
        </div>
        {isDeleteModalOpen && ( // Render the modal if isDeleteModalOpen is true
          <div className={ManageAll["delete-modal"]}>

            <div>
              <p>Bạn có chắc chắn muốn xóa?</p>


              <div className={ManageAll["cancel-yes"]} >
                <button onClick={() => { deleteUser(selectedProductId); closeDeleteModal(); }}>
                  Có
                </button>
                <button onClick={closeDeleteModal}>
                  Không
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
      <Footer />
    </div>

  );
};

export default ManageProduct;