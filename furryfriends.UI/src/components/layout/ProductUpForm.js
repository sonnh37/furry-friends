import React, { useState } from 'react';
import ProductUpFormCSS from '../UI/Layout/ProductUpForm.module.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from "react-router-dom";

export default function ProductUpForm() {
  let navigate = useNavigate()
  const [inputValue, setInputValue] = useState('');
  // const [selectedImageCount, setSelectedImageCount] = useState(0);

  const removeImage = (index) => {
    const updatedImages = [...product.image];
    updatedImages.splice(index, 1);
    setProduct({ ...product, image: updatedImages });
  }; // xóa ảnh hiện tại

  const [product, setProduct] = useState({
    product_name: '',
    price: '',
    image: [],
    description: '',
    phone: '',
    title: '',
    address: ''

  })

  const { product_name, price, image, description,  phone, title, address } = product

  const onFilesChange = (e) => {
    const files = Array.from(e.target.files);
    const remainingSlots = 5 - product.image.length; // Số lượng hình ảnh còn lại có thể thêm

    if (remainingSlots > 0) {
      const newImages = files.slice(0, remainingSlots);
      setProduct({
        ...product,
        image: [...product.image, ...newImages]
      });
    }
  }; // nhận ảnh upload của người dùng 


  const onInputChange = (event) => {
    setProduct({ ...product, [event.target.name]: event.target.value })
    setInputValue(...product.description, event.target.value);

  } // nhận dữ liệu từ user input vào




  const onSubmit = async (event) => {
    event.preventDefault();

  //   if (!product.title || !product.price || !product.image || !product.description || !product.phone || !product.address || !product.product_name ) {
  //     alert("Vui lòng điền đầy đủ thông tin");
  //     return;
  // }
  const errors = [];
  if (product.image.length <2 || product.image.length >5) {
    errors.push("Ảnh ít nhất 2 và tối đa là 5");
  }
  if (!product.product_name.trim()) {
    errors.push("Tên sản phẩm là trường bắt buộc.");
  }
  if (!product.price.trim()) {
    errors.push("Giá bán là trường bắt buộc.");
  }
  if (!/^[0-9]+$/.test(product.price)) {
    errors.push("Giá bán bắt buộc là số.");
  }

  if (!product.description.trim()) {
    errors.push("Mô tả chi tiết là trường bắt buộc.");
  }
  if (!product.address.trim()) {
    errors.push("Địa chỉ là trường bắt buộc.");
  }
  if (!product.phone.trim()) {
    errors.push("Số điện thoại là trường bắt buộc.");
  }
  if (!/^[0-9]+$/.test(product.phone)) {
    errors.push("Số điện thoại bắt buộc là số.");
  }

  if (errors.length > 0) {
    // Display error messages and prevent form submission
    alert(errors.join('\n'));
    return;
  }
  
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
    const base64Images = [];
  
    for (let i = 0; i < product.image.length; i++) {
      const file = product.image[i];
      const reader = new FileReader();
  
      reader.onload = (event) => {
        const base64String = event.target.result;
        base64Images.push(base64String);
  
        // After all images are converted to base64, you can proceed to create the FormData object
        if (base64Images.length === product.image.length) {
          const formData = new FormData();
          
          // Append base64Images to formData
          for (let j = 0; j < base64Images.length; j++) {
            formData.append('img_productList[]', base64Images[j]);
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
            // Send formData to the server
            axios.post(`http://localhost:8080/api/v1/user/products/${account}`, formData,{
              headers:{
                'Content-Type': 'application/json'
              }
              });
            alert("Product registration successfully");
            navigate("/manageproduct");
            window.location.reload();
          } catch (err) {
            alert(err);
          }
        }
      };
  
      // Read the file as a Data URL (base64)
      reader.readAsDataURL(file);
    }
  };
  




  const handleOptionChange = (event) => {
    const selectedValue = event.target.value;
    if (selectedValue === 'thu-cung') {
      window.location.href = '/product/up2' // Redirect to Mèo page
    }
  }
  const rows = inputValue.split('\n').length;
  return (
    <div className={ProductUpFormCSS['body-container']}>
      <div className={ProductUpFormCSS['container']}>
        <form className={ProductUpFormCSS['row']} onSubmit={onSubmit}>
          <div className={ProductUpFormCSS['column']}>
            <div className={ProductUpFormCSS['left-column']}>
              <h5>Hình ảnh và Video sản phẩm</h5>
              <div className={ProductUpFormCSS['input-image']}>
                <div className={ProductUpFormCSS['headinbox']} role='presentation'>
                  <div className={ProductUpFormCSS['headinbox-btn']} >
                    <div className={ProductUpFormCSS['infoinbox']}><i className={ProductUpFormCSS['fa-solid fa-download']} ></i>  Hình ảnh hợp lệ</div>
                  </div>
                </div>
                <div role='presentation' className={ProductUpFormCSS['below-box']} >
                  <div className={ProductUpFormCSS['below-box1']}>
                    <input type="file"
                      name='image'

                      onChange={onFilesChange}

                    />
                    <div className={ProductUpFormCSS['image-below']}>

                      <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSavjEwxKwAe4CU2n-gyuj-xhIsiI35hqQTYQ&usqp=CAU" />

                    </div>
                    <div>
                      <p>Đăng ít nhất 2 image</p>
                    </div>
                  </div>
                  <div className={ProductUpFormCSS['image-below1']} >
                    {product.image.map((image, index) => (
                      <div key={index} className="image-item">
                        <img
                          src={URL.createObjectURL(image)}
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
          <div className={ProductUpFormCSS['column']}>
            <div className={ProductUpFormCSS['right-column']} >
              <div className={ProductUpFormCSS['form-group']} >
                <label>Danh mục đăng tin</label>
                <select onChange={handleOptionChange}>

                  <option value="san-pham-cho-cho-meo">Sản phẩm cho chó mèo</option>
                  <option value="thu-cung" >Thú cưng</option>
                </select>
              </div>

              <div className={ProductUpFormCSS['form-group']}>
                <label>Thông tin chi tiết</label>
                <input type="text" name='product_name' value={product_name} onChange={onInputChange} placeholder="Tên sản phẩm" />

                <input type="text" name='price' value={price} onChange={onInputChange} placeholder="Giá bán tính theo VND" />
              </div>

              <div className={ProductUpFormCSS['form-group']} >
                <label>Tiêu đề tin đăng và Mô tả chi tiết</label>
                <input type="text" name='title' value={title} onChange={onInputChange} placeholder="Tiêu đề tin đăng" />
                Tối đa 50 kí tự
                <div className={ProductUpFormCSS['input-container']}>
                  <label>Mô tả chi tiết</label>
                  <textarea
                    name='description'
                    value={description}
                    onChange={onInputChange}

                    className={ProductUpFormCSS['auto-expanding-input']}
                    placeholder="Mô tả chi tiết"
                    rows={rows}
                    maxLength="1500"

                  />
                  Tối đa 1500 kí tự
                </div>

              </div>

              <div className={ProductUpFormCSS['form-group']} >
                <label>Thông tin người bán</label>
                <input type="text" name='address' value={address} onChange={onInputChange} placeholder="Địa chỉ" />
               
                <input type="text" name='phone' value={phone} onChange={onInputChange} placeholder="Số điện thoại" />
              </div>
              <div className={ProductUpFormCSS['button']} >

                <input type="submit" value="Đăng ký" className={ProductUpFormCSS['button-btn']}></input>


              </div>


            </div>
          </div>
        </form>
      </div>

    </div>
  );
}
