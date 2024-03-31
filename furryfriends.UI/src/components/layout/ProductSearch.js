import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProductSearchCSS from '../UI/Layout/ProductSearch.module.css'
import Navbar from '../layout/Navbar'
import Footer from '../layout/Footer'
import NotFounder from "../../img/search_no_found_keyword.png"
import { Link } from 'react-router-dom';

const ProductSearch = () => {
    const [data, setData] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [currentData, setCurrentData] = useState([]);
    const itemsPerPage = 7;

    const savedSearchQuery = localStorage.getItem('searchQuery');
    const queryParams = {
        q: `${savedSearchQuery}`, // Ví dụ về tham số truy vấn "q"
    };
    useEffect(() => {
        console.log(queryParams)
        const LoadProduct = async () => {
            axios.get(`http://localhost:8080/api/v1/user/products/getSearch_desc`, {
                params: queryParams,

            }).then(response => {
                setData(response.data)
                localStorage.clear()
                if (!data) {
                    alert("Không có sản phẩm")
                }
            })

        }
        LoadProduct();

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
        <div>
            <Navbar />
            <div className={ProductSearchCSS['container1']}>

                <div className={ProductSearchCSS['container']} >
                    <div className={ProductSearchCSS['box1']} >
                        <div className={ProductSearchCSS['navbar']}>
                            <div className={ProductSearchCSS['dropdown']}>
                                <button className={ProductSearchCSS['dropbtn']}>Tất cả

                                </button>

                            </div>
                            <div className={ProductSearchCSS['dropdown']}>
                                <button className={ProductSearchCSS['dropbtn']}>Toàn quốc  <i class="fa fa-caret-down"></i>
                                </button>
                                <div className={ProductSearchCSS['dropdown-content']}>
                                    <a href="#"> TP Hồ Chí Minh</a>
                                    <a href="#">Hà Nội</a>
                                    <a href="#">Đà Nẵng</a>
                                </div>
                            </div>
                            <div className={ProductSearchCSS['dropdown']}>
                                <button className={ProductSearchCSS['dropbtn']}>Loại sản phẩm <i class="fa fa-caret-down"></i>
                                </button>
                                <div className={ProductSearchCSS['dropdown-content']}>
                                    <a href="#">Chó</a>
                                    <a href="#">Mèo</a>
                                    <a href="#">Sản phẩm chó mèo</a>
                                </div>
                            </div>
                            <div className={ProductSearchCSS['dropdown']}>
                                <button className={ProductSearchCSS['dropbtn']}>Tin đăng <i class="fa fa-caret-down"></i>
                                </button>
                                <div className={ProductSearchCSS['dropdown-content']}>
                                    <a href="#">Mới nhất</a>
                                    <a href="#">Cũ nhất</a>

                                </div>
                            </div>
                        </div>
                    </div>
                    {currentData.length > 0 ? (
                        
                            currentData.map((product, index) => (

                                <Link key={index} className={ProductSearchCSS['box']} to={`/product/detail/${product.product_id}`}>

                                    {product.img_productList.length > 0 && (
                                        <img

                                            src={product.img_productList[0]}

                                        />
                                    )}

                                    <div className={ProductSearchCSS['info']}>
                                        <div className={ProductSearchCSS['top-box']}>
                                            <label>Name: {product.product_name}</label>

                                            <div className={ProductSearchCSS['price']}>
                                                <label>Price: {product.price} VNĐ</label>
                                            </div>
                                        </div>
                                        <div className={ProductSearchCSS['below-box']}>
                                            <div>
                                                <label>Address: {product.description}</label>

                                            </div>
                                            <div>
                                                <label>Date : {product.date}</label>

                                            </div>
                                        </div>

                                    </div>


                                </Link>

                            ))
                        



                    ) : (
                        <div className={ProductSearchCSS['clickpost1']}>
                            <div><img src={NotFounder} /></div>
                            <div>   <p>Không tìm thấy sản phẩm.</p></div>
                            <button><a href='/product'>Quay lại trang chủ</a> </button>
                        </div>
                    )}



                    <div className={ProductSearchCSS['IntersectBox']}>
                        <div className={ProductSearchCSS['box2']}>
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
            <Footer />
        </div>
    );
};

export default ProductSearch;