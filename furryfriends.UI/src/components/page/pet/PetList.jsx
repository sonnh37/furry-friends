import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import PetListCSS from './PetList.module.css'
import { Link } from 'react-router-dom';
import Footer from '../../layout/Footer';
import Navbar from '../../layout/Navbar';
const PetList = () => {
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
            const response = await axios.get(`http://localhost:8080/api/v1/user/pets/getAll_${sortingOrder}`);

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
        <div>
            <Navbar />
            <div className={PetListCSS['container1']}>

                <div className={PetListCSS['container']} >
                    <div className={PetListCSS['box1']} >
                        <div className={PetListCSS['navbar']}>
                            <div className={PetListCSS['dropdown']}>
                                <div className={PetListCSS['dropbtn1']}>
                                    <a href='/petlist'> Tất cả</a>
                                </div>

                            </div>
                            <div className={PetListCSS['dropdown']}>
                                <button className={PetListCSS['dropbtn']}>Toàn quốc  <i class="fa fa-caret-down"></i>
                                </button>
                                <div className={PetListCSS['dropdown-content']}>
                                    <a href="#"> Link1</a>
                                    <a href="#">Link 2</a>
                                    <a href="#">Link 3</a>
                                </div>
                            </div>
                            <div className={PetListCSS['dropdown']}>
                                <button className={PetListCSS['dropbtn']}>Loại sản phẩm <i class="fa fa-caret-down"></i>
                                </button>
                                <div className={PetListCSS['dropdown-content']}>
                                    <a href="product/list">Loại sản phẩm cho chó mèo</a>
                                    <a href="/petlistdog">Chó </a>
                                    <a href="#">Mèo</a>
                                </div>
                            </div>
                            <div className={PetListCSS['dropdown']}>
                                <button className={PetListCSS['dropbtn']}>Tin đăng <i class="fa fa-caret-down"></i>
                                </button>
                                <div className={PetListCSS['dropdown-content']}>
                                    <a href="#" onClick={() => handleSort("desc")} >Mới nhất</a>
                                    <a href="#" onClick={() => handleSort("asc")}>Cũ nhất</a>

                                </div>
                            </div>
                        </div>
                    </div>

                    {currentData.map((product, index) => (

                        <Link key={index} className={PetListCSS['box']} to={`/pet/detail/${product.pet_id}`}>

                            {product.img_petList.length > 0 && (
                                <img

                                    src={product.img_petList[0]}

                                />
                            )}

                            <div className={PetListCSS['info']}>
                                <div className={PetListCSS['top-box']}>
                                    <label>Name: {product.pet_name}</label>

                                    <div className={PetListCSS['price']}>
                                        <label>Price: {product.pet_price}</label>
                                    </div>
                                </div>
                                <div className={PetListCSS['below-box']}>
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



                    <div className={PetListCSS['IntersectBox']}>
                        <div className={PetListCSS['box2']}>
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

export default PetList;