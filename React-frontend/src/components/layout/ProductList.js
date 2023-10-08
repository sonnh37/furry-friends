import React from 'react'
import ProductListCSS from '../UI/Layout/ProductList.module.css'
export default function ProductList() {
    return (
        <div className={ProductListCSS['body']}>
            <div className={ProductListCSS['container']} >
                <div className={ProductListCSS['box1']} >
                    <div className={ProductListCSS['navbar']}>
                        <div className={ProductListCSS['dropdown']}>
                            <button  className={ProductListCSS['dropbtn']}>Tất cả

                            </button>

                        </div>
                        <div className={ProductListCSS['dropdown']}>
                            <button className={ProductListCSS['dropbtn']}>Toàn quốc
                                <i class="fa fa-caret-down"></i>
                            </button>
                            <div className={ProductListCSS['dropdown-content']}>
                                <a href="#"> Link1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                        <div className={ProductListCSS['dropdown']}>
                            <button className={ProductListCSS['dropbtn']}>Loại sản phẩm
                                <i class="fa fa-caret-down"></i>
                            </button>
                            <div className={ProductListCSS['dropdown-content']}>
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                        <div className={ProductListCSS['dropdown']}>
                            <button className={ProductListCSS['dropbtn']}>Tin đăng
                                <i class="fa fa-caret-down"></i>
                            </button>
                            <div className={ProductListCSS['dropdown-content']}>
                                <a href="#">Link 1</a>
                                <a href="#">Link 2</a>
                                <a href="#">Link 3</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={ProductListCSS['box']}>
                    <img src="https://via.placeholder.com/150" alt="Placeholder Image" />
                    <div className={ProductListCSS['info']}>
                        <div className={ProductListCSS['top-box']}>
                            <div >Title 1</div>
                            <div className={ProductListCSS['price']}>Price: $100</div>
                        </div>
                        <div className={ProductListCSS['below-box']}>Address: 123 Main St</div>
                    </div>
                </div>
                <div className={ProductListCSS['box']}>
                    <img src="https://via.placeholder.com/150" alt="Placeholder Image" />
                    <div className={ProductListCSS['info']}>
                        <div className={ProductListCSS['top-box']}>
                            <div >Title 1</div>
                            <div className={ProductListCSS['price']}>Price: $100</div>
                        </div>
                        <div className={ProductListCSS['below-box']}>Address: 123 Main St</div>
                    </div>
                </div> <div className={ProductListCSS['box']}>
                    <img src="https://via.placeholder.com/150" alt="Placeholder Image" />
                    <div className={ProductListCSS['info']}>
                        <div className={ProductListCSS['top-box']}>
                            <div >Title 1</div>
                            <div className={ProductListCSS['price']}>Price: $100</div>
                        </div>
                        <div className={ProductListCSS['below-box']}>Address: 123 Main St</div>
                    </div>

                </div> <div className={ProductListCSS['box']}>
                    <img src="https://via.placeholder.com/150" alt="Placeholder Image" />
                    <div className={ProductListCSS['info']}>
                        <div className={ProductListCSS['top-box']}>
                            <div >Title 1</div>
                            <div className={ProductListCSS['price']}>Price: $100</div>
                        </div>
                        <div className={ProductListCSS['below-box']}>Address: 123 Main St</div>
                    </div>
                </div> <div className={ProductListCSS['box']}>
                    <img src="https://via.placeholder.com/150" alt="Placeholder Image" />
                    <div className={ProductListCSS['info']}>
                        <div className={ProductListCSS['top-box']}>
                            <div >Title 1</div>
                            <div className={ProductListCSS['price']}>Price: $100</div>
                        </div>
                        <div className={ProductListCSS['below-box']}>Address: 123 Main St</div>
                    </div>
                </div> <div className={ProductListCSS['box']}>
                    <img src="https://via.placeholder.com/150" alt="Placeholder Image" />
                    <div className={ProductListCSS['info']}>
                        <div className={ProductListCSS['top-box']}>
                            <div >Title 1</div>
                            <div className={ProductListCSS['price']}>Price: $100</div>
                        </div>
                        <div className={ProductListCSS['below-box']}>Address: 123 Main St</div>
                    </div>
                </div> <div className={ProductListCSS['box']}>
                    <img src="https://via.placeholder.com/150" alt="Placeholder Image" />
                    <div className={ProductListCSS['info']}>
                        <div className={ProductListCSS['top-box']}>
                            <div >Title 1</div>
                            <div className={ProductListCSS['price']}>Price: $100</div>
                        </div>
                        <div className={ProductListCSS['below-box']}>Address: 123 Main St</div>
                    </div>
                </div> <div className={ProductListCSS['box']}>
                    <img src="https://via.placeholder.com/150" alt="Placeholder Image" />
                    <div className={ProductListCSS['info']}>
                        <div className={ProductListCSS['top-box']}>
                            <div >Title 1</div>
                            <div className={ProductListCSS['price']}>Price: $100</div>
                        </div>
                        <div className={ProductListCSS['below-box']}>Address: 123 Main St</div>
                    </div>
                </div>
                <div className={ProductListCSS['IntersectBox']}>
                    <div className={ProductListCSS['box2']}>
                        <span className={ProductListCSS['button-btn']}><button><i class="fa-solid fa-angle-left"></i></button></span>
                        <span className={ProductListCSS['button-btn']}><button>1</button></span>
                        <span className={ProductListCSS['button-btn']}><button>2</button></span>
                        <span className={ProductListCSS['button-btn']}><button>3</button></span>
                        <span className={ProductListCSS['button-btn']}><button>4</button></span>
                        <span className={ProductListCSS['button-btn']}><button>5</button></span>
                        <span className={ProductListCSS['button-btn']}><button>6</button></span>
                        <span className={ProductListCSS['button-btn']}><button>7</button></span>
                        <span className={ProductListCSS['button-btn']}><button>8</button></span>
                        <span className={ProductListCSS['button-btn']}><button><i class="fa-solid fa-chevron-right"></i></button></span>



                    </div>




                </div>






            </div>
        </div>
    )
}
