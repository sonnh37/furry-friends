import React, { useState, useEffect } from 'react';
import EditProduct from './EditProduct.module.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from "react-router-dom";
import { Link, useParams } from "react-router-dom";





export default function ProductUpForm() {



  let navigate = useNavigate()
  const [inputValue, setInputValue] = useState('');
  // const [selectedImageCount, setSelectedImageCount] = useState(0);
  const { product_id } = useParams(); // lấy id từ trang manage product
  const removeImage = (index) => {
    const updatedImages = [...product.img_productList];
    updatedImages.splice(index, 1);
    setProduct({ ...product, img_productList: updatedImages });
  }; // xóa ảnh hiện tại

  const [product, setProduct] = useState({
    product_name: '',
    price: '',
    img_productList: [],
    description: '',
   
    phone: '',
    title: '',
    address: ''

  })

  const { product_name, price, description,  phone, title, address } = product

  // const onFilesChange = (e) => {
  //   const files = Array.from(e.target.files);
  //   const remainingSlots = 3 - product.img_productList.length; // Số lượng hình ảnh còn lại có thể thêm

  //   if (remainingSlots > 0) {

  //     const newImages = files.slice(0, remainingSlots);

  //     const updatedImages = product.img_productList.slice(); // Copy the existing images

  //     newImages.forEach((file) => {
  //       const reader = new FileReader();
  //       reader.onload = (e) => {
  //         updatedImages.push(e.target.result); // Store the base64 image data
  //         if (updatedImages.length === product.img_productList.length + newImages.length) {
  //           // After all images are processed, update the product state
  //           setProduct({
  //             ...product,
  //             img_productList: updatedImages,
  //           });
  //         }
  //       };
  //       reader.readAsDataURL(file);
  //     });
  //   }
  // }; // nhận ảnh upload của người dùng 

  const onFilesChange = (e) => {
    const files = Array.from(e.target.files);
    const remainingSlots = 5 - product.img_productList.length;

    if (remainingSlots > 0) {
      const newImages = files.slice(0, remainingSlots);

      const newImageArray = Array.from(product.img_productList); // Copy the existing images

      newImages.forEach((file) => {
        const reader = new FileReader();
        reader.onload = (e) => {
          newImageArray.push(e.target.result); // Store the base64 image data
          if (newImageArray.length === product.img_productList.length + newImages.length) {
            // After all images are processed, update the product state
            setProduct({
              ...product,
              img_productList: newImageArray,
            });
          }
        };
        reader.readAsDataURL(file);
      });
    }
  };
  const onInputChange = (event) => {
    setProduct({ ...product, [event.target.name]: event.target.value })
    setInputValue(...product.description, event.target.value);

  } // nhận dữ liệu từ user input vào

  useEffect(() => {
    loadUser();
  }, []);
  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8080/api/v1/user/products/one/${product_id}`);
    setProduct(result.data);
    // setProduct({ ...product, img_productList: result.data.img_productList });
  };


  const onSubmit = async (event) => {
    event.preventDefault();

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

    // Assuming `product.image` is an array of File objects (representing images)

    const formData = new FormData();
    for (let i = 0; i < product.img_productList.length; i++) {
      formData.append('img_productList[]', product.img_productList[i]);
    }

    // Append other fields to formData
    formData.append('product_name', product.product_name);
    formData.append('price', product.price);
    formData.append('description', product.description);
  
    formData.append('phone', product.phone);
    formData.append('title', product.title);
    formData.append('address', product.address);


    try {

      const account = Cookies.get('sub');
      // Send formData to the server for updating
      const update = axios.put(`http://localhost:8080/api/v1/user/products/${account}-${product_id}`, formData, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      alert("Product update successfully");
      navigate("/manageproduct");
window.location.reload();
    } catch (err) {
      alert(err);
    }
  }






  const handleOptionChange = (event) => {
    const selectedValue = event.target.value;
    if (selectedValue === 'thu-cung') {
      window.location.href = '/' // Redirect to Mèo page
    }
  }
  const rows = inputValue.split('\n').length;
  return (
    <div className={EditProduct['body-container']}>
      <div className={EditProduct['container']}>
        <form className={EditProduct['row']} onSubmit={onSubmit}>
          <div className={EditProduct['column']}>
            <div className={EditProduct['left-column']}>
              <h5>Hình ảnh và Video sản phẩm</h5>
              <div className={EditProduct['input-image']}>
                <div className={EditProduct['headinbox']} role='presentation'>
                  <div className={EditProduct['headinbox-btn']} >
                    <div className={EditProduct['infoinbox']}><i className={EditProduct['fa-solid fa-download']} ></i>  Hình ảnh hợp lệ</div>
                  </div>
                </div>
                <div role='presentation' className={EditProduct['below-box']} >
                  <div className={EditProduct['below-box1']}>
                    <input type="file"
                      name='img_productList'

                      onChange={onFilesChange}

                    />
                    <div className={EditProduct['image-below']}>

                      <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSavjEwxKwAe4CU2n-gyuj-xhIsiI35hqQTYQ&usqp=CAU" />

                    </div>
                    <div>
                      <p>Đăng ít nhất 2 image</p>
                    </div>
                  </div>
                  <div className={EditProduct['image-below1']} >
                    {product.img_productList.map((image, index) => (

                      <div key={index} className="image-item">
                        <img
                          src={image}
                          alt={`Hình ảnh ${index + 1}`}
                        />
                        <button onClick={() => removeImage(index)}>Xóa</button>
                      </div>

                    ))}
                  </div>
                </div>

              </div>
            </div>
          </div>
          <div className={EditProduct['column']}>
            <div className={EditProduct['right-column']} >
              <div className={EditProduct['form-group']} >
                <label>Danh mục đăng tin</label>
                <select onChange={handleOptionChange}>

                  <option value="san-pham-cho-cho-meo">Sản phẩm cho chó mèo</option>
                  <option value="thu-cung" >Thú cưng</option>
                </select>
              </div>

              <div className={EditProduct['form-group']}>
                <label>Thông tin chi tiết</label>
                <input type="text" name='product_name' value={product_name} onChange={onInputChange} placeholder="Tên sản phẩm" />

                <input type="text" name='price' value={price} onChange={onInputChange} placeholder="Giá bán" />
              </div>

              <div className={EditProduct['form-group']} >
                <label>Tiêu đề tin đăng và Mô tả chi tiết</label>
                <input type="text" name='title' value={title} onChange={onInputChange} placeholder="Tiêu đề tin đăng" />
                Tối đa 50 kí tự
                <div className={EditProduct['input-container']}>
                  <label>Mô tả chi tiết</label>
                  <textarea
                    name='description'
                    value={description}
                    onChange={onInputChange}

                    className={EditProduct['auto-expanding-input']}
                    placeholder="Mô tả chi tiết"
                    rows={rows}
                    maxLength="1500"

                  />
                  Tối đa 1500 kí tự
                </div>

              </div>

              <div className={EditProduct['form-group']} >
                <label>Thông tin người bán</label>
                <input type="text" name='address' value={address} onChange={onInputChange} placeholder="Địa chỉ" />
             
                <input type="text" name='phone' value={phone} onChange={onInputChange} placeholder="Số điện thoại" />
              </div>
              <div className={EditProduct['button']} >

                <input type="submit" value="Lưu" className={EditProduct['button-btn']}></input>
                <button>
                  <Link to="/manageproduct">
                    Cancel
                  </Link>
                </button>

              </div>


            </div>
          </div>
        </form>
      </div>

    </div>
  );
}
