import React, { useState } from 'react';
import DogCatUpFormCSS from '../UI/Layout/DogCatUpForm.module.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from "react-router-dom";

export default function DogCatUpForm() {
    let navigate = useNavigate()
    const [inputValue, setInputValue] = useState('');
    // const [selectedImageCount, setSelectedImageCount] = useState(0);

    const removeImage = (index) => {
        const updatedImages = [...product.img_petList];
        updatedImages.splice(index, 1);
        setAnimal({ ...product, img_petList: updatedImages });
    }; // xóa ảnh hiện tại

    const [product, setAnimal] = useState({
        pet_name: '',
        pet_price: '',
        description: '',
        development_stage: '',
        type: '',
        phone: '',
        title: '',
        address: '',
        img_petList: [],
    
    })

    const { pet_name, pet_price, img_petList, description, date, phone, title, address, type, development_stage } = product
    console.log(development_stage);

    const onFilesChange = (e) => {
        const files = Array.from(e.target.files);
        const remainingSlots = 5 - product.img_petList.length; // Số lượng hình ảnh còn lại có thể thêm

        if (remainingSlots > 0) {
            const newImages = files.slice(0, remainingSlots);
            setAnimal({
                ...product,
                img_petList: [...product.img_petList, ...newImages]
            });
        }
    }; // nhận ảnh upload của người dùng 


    const onInputChange = (event) => {
        setAnimal({ ...product, [event.target.name]: event.target.value })
        setInputValue(...product.description, event.target.value);

    } // nhận dữ liệu từ user input vào




    const onSubmit = async (event) => {
        event.preventDefault();

        const errors = [];
        if (product.img_petList.length <2 || product.img_petList.length > 5 ) {
            errors.push("Ảnh ít nhất 2 tối đa là 5");
          }
        if (!product.pet_name.trim()) {
          errors.push("Tên sản phẩm là trường bắt buộc.");
        }
        if (!product.pet_price.trim()) {
          errors.push("Giá bán là trường bắt buộc.");
        }
        if (!/^[0-9]+$/.test(product.pet_price)) {
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
        if (product.type === "Chọn Loại" || product.development_stage === "Chọn Loại") 
            errors.push("Vui lòng chọn loại và giai đoạn phát triển.");
            if (product.development_stage === "Chọn Loại") 
            errors.push("Vui lòng chọn giai đoạn phát triển.");
       
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

        for (let i = 0; i < product.img_petList.length; i++) {
            const file = product.img_petList[i];
            const reader = new FileReader();

            reader.onload = (event) => {
                const base64String = event.target.result;
                base64Images.push(base64String);

                // After all images are converted to base64, you can proceed to create the FormData object
                if (base64Images.length === product.img_petList.length) {
                    const formData = new FormData();

                    // Append base64Images to formData
                    for (let j = 0; j < base64Images.length; j++) {
                        formData.append('img_petList[]', base64Images[j]);
                    }

                    // Append other fields to formData
                    formData.append('pet_name', product.pet_name);
                    formData.append('pet_price', product.pet_price);
                    formData.append('description', product.description);
                    formData.append('development_stage', product.development_stage);
                    formData.append('type', product.type);
                    formData.append('phone', product.phone);
                    formData.append('title', product.title);
                    formData.append('address', product.address);


                    try {

                        const account = Cookies.get('sub');
                        // Send formData to the server
                        axios.post(`http://localhost:8080/api/v1/user/pets/${account}`, formData, {
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        });
                        alert("Product registration successfully");
                        // navigate("/manageproduct");
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
        if (selectedValue === 'san-pham-cho-cho-meo') {
            window.location.href = '/product/up1' // Redirect to Mèo page
        }
    }
    const rows = inputValue.split('\n').length;
    return (
        <div className={DogCatUpFormCSS['body-container']}>
            <div className={DogCatUpFormCSS['container']}>
                <form className={DogCatUpFormCSS['row']} onSubmit={onSubmit}>
                    <div className={DogCatUpFormCSS['column']}>
                        <div className={DogCatUpFormCSS['left-column']}>
                            <h5>Hình ảnh và Video sản phẩm</h5>
                            <div className={DogCatUpFormCSS['input-image']}>
                                <div className={DogCatUpFormCSS['headinbox']} role='presentation'>
                                    <div className={DogCatUpFormCSS['headinbox-btn']} >
                                        <div className={DogCatUpFormCSS['infoinbox']}><i className={DogCatUpFormCSS['fa-solid fa-download']} ></i>  Hình ảnh hợp lệ</div>
                                    </div>
                                </div>
                                <div role='presentation' className={DogCatUpFormCSS['below-box']} >
                                    <div className={DogCatUpFormCSS['below-box1']}>
                                        <input type="file"
                                            name='img_petList'

                                            onChange={onFilesChange}

                                        />
                                        <div className={DogCatUpFormCSS['image-below']}>

                                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSavjEwxKwAe4CU2n-gyuj-xhIsiI35hqQTYQ&usqp=CAU" />

                                        </div>
                                        <div>
                                            <p>Đăng ít nhất 2 hình ảnh</p>
                                        </div>
                                    </div>
                                    <div className={DogCatUpFormCSS['image-below1']} >
                                        {product.img_petList.map((img_petList, index) => (
                                            <div key={index} className="image-item">
                                                <img
                                                    src={URL.createObjectURL(img_petList)}
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
                    <div className={DogCatUpFormCSS['column']}>
                        <div className={DogCatUpFormCSS['right-column']} >
                            <div className={DogCatUpFormCSS['form-group']} >
                                <label>Danh mục đăng tin</label>
                                <select onChange={handleOptionChange}>
                                    <option value="thu-cung" >Thú cưng</option>
                                    <option value="san-pham-cho-cho-meo">Sản phẩm cho chó mèo</option>

                                </select>
                            </div>

                            <div className={DogCatUpFormCSS['form-group']}>
                                <label>Thông tin chi tiết</label>
                                <select
                                    type={"text"}
                                    name="type"
                                    value={product.type}
                                    onChange={onInputChange}
                                >
                                            <option >Chọn Loại</option>
                                    <option>Chó </option>
                                    <option>Mèo</option>
                                </select>
                                <select
                                    type={"text"}
                                    name="development_stage"
                                   value={product.development_stage}
                                    onChange={(e) => onInputChange(e)}
                                >
                                      <option >Chọn Loại</option>
                                    <option   >Sơ sinh (dưới 3 tháng tuổi)</option>
                                    <option   >Trẻ (dưới 1 tuổi)</option>
                                    <option   >Trưởng thành (trên 1 tuổi)</option>
                                    <option   >Khác</option>
                                </select>
                                <input type="text" name='pet_name' value={pet_name} onChange={onInputChange} placeholder="Loại thú cưng" />
                                <br />

                                <input type="text" name='pet_price' value={pet_price} onChange={onInputChange} placeholder="Giá bán" />
                            </div>

                            <div className={DogCatUpFormCSS['form-group']} >
                                <label>Tiêu đề tin đăng và Mô tả chi tiết</label>

                                <input type="text" name='title' value={title} onChange={onInputChange} placeholder="Tiêu đề tin đăng" />
                                Tối đa 50 kí tự
                                <div className={DogCatUpFormCSS['input-container']}>
                                    <label>Mô tả chi tiết</label>
                                    <textarea
                                        name='description'
                                        value={description}
                                        onChange={onInputChange}

                                        className={DogCatUpFormCSS['auto-expanding-input']}
                                        placeholder="Mô tả chi tiết"
                                        rows={rows}
                                        maxLength="1500"

                                    />
                                    Tối đa 1500 kí tự
                                </div>

                            </div>

                            <div className={DogCatUpFormCSS['form-group']} >
                                <label>Thông tin người bán</label>
                                <input type="text" name='address' value={address} onChange={onInputChange} placeholder="Địa chỉ" />

                                <input type="text" name='phone' value={phone} onChange={onInputChange} placeholder="Số điện thoại" />
                            </div>
                            <div className={DogCatUpFormCSS['button']} >

                                <input type="submit" value="Đăng ký" className={DogCatUpFormCSS['button-btn']}></input>


                            </div>


                        </div>
                    </div>
                </form>
            </div>

        </div>
    );
}
