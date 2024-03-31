import React, { useState, useEffect } from 'react';
import EditPetCSS from './EditPet.module.css';
import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from "react-router-dom";
import { Link, useParams } from "react-router-dom";





export default function EditPet() {



  let navigate = useNavigate()
  const [inputValue, setInputValue] = useState('');
  // const [selectedImageCount, setSelectedImageCount] = useState(0);
  const { pet_id } = useParams(); // lấy id từ trang manage product
  const removeImage = (index) => {
    const updatedImages = [...product.img_petList];
    updatedImages.splice(index, 1);
    setPet({ ...product, img_petList: updatedImages });
  }; // xóa ảnh hiện tại

  const [product, setPet] = useState({
    pet_name: '',
    pet_price: '',
    img_petList: [],
    description: '',
    development_stage: '',
    type: '',
    phone: '',
    title: '',
    address: ''

  })

  const { pet_name, pet_price, description,  phone, title, address,development_stage,type } = product

 

  const onFilesChange = (e) => {
    const files = Array.from(e.target.files);
    const remainingSlots = 5 - product.img_petList.length;

    if (remainingSlots > 0) {
      const newImages = files.slice(0, remainingSlots);

      const newImageArray = Array.from(product.img_petList); // Copy the existing images

      newImages.forEach((file) => {
        const reader = new FileReader();
        reader.onload = (e) => {
          newImageArray.push(e.target.result); // Store the base64 image data
          if (newImageArray.length === product.img_petList.length + newImages.length) {
            // After all images are processed, update the product state
            setPet({
              ...product,
              img_petList: newImageArray,
            });
          }
        };
        reader.readAsDataURL(file);
      });
    }
  };
  const onInputChange = (event) => {
    setPet({ ...product, [event.target.name]: event.target.value })
    setInputValue(...product.description, event.target.value);

  } // nhận dữ liệu từ user input vào

  useEffect(() => {
    loadUser();
  }, []);
  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8080/api/v1/user/pets/one/${pet_id}`);
    setPet(result.data);
    // setPet({ ...product, img_petList: result.data.img_petList });
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
    for (let i = 0; i < product.img_petList.length; i++) {
      formData.append('img_petList[]', product.img_petList[i]);
    }

    // Append other fields to formData
    formData.append('pet_name', product.pet_name);
    formData.append('pet_price', product.pet_price);
    formData.append('description', product.description);
    formData.append('type', product.type);
    formData.append('development_stage', product.development_stage);
    formData.append('phone', product.phone);
    formData.append('title', product.title);
    formData.append('address', product.address);


    try {

      const account = Cookies.get('sub');
      // Send formData to the server for updating
      const update = axios.put(`http://localhost:8080/api/v1/user/pets/${account}-${pet_id}`, formData, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      alert("Product update successfully");
      navigate("/managepet");
window.location.reload();
    } catch (err) {
      alert(err);
    }
  }







  const rows = inputValue.split('\n').length;
  return (
    <div className={EditPetCSS['body-container']}>
      <div className={EditPetCSS['container']}>
        <form className={EditPetCSS['row']} onSubmit={onSubmit}>
          <div className={EditPetCSS['column']}>
            <div className={EditPetCSS['left-column']}>
              <h5>Hình ảnh và Video sản phẩm</h5>
              <div className={EditPetCSS['input-image']}>
                <div className={EditPetCSS['headinbox']} role='presentation'>
                  <div className={EditPetCSS['headinbox-btn']} >
                    <div className={EditPetCSS['infoinbox']}><i className={EditPetCSS['fa-solid fa-download']} ></i>  Hình ảnh hợp lệ</div>
                  </div>
                </div>
                <div role='presentation' className={EditPetCSS['below-box']} >
                  <div className={EditPetCSS['below-box1']}>
                    <input type="file"
                      name='img_petList'

                      onChange={onFilesChange}

                    />
                    <div className={EditPetCSS['image-below']}>

                      <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSavjEwxKwAe4CU2n-gyuj-xhIsiI35hqQTYQ&usqp=CAU" />

                    </div>
                    <div>
                      <p>Đăng ít nhất 2 image</p>
                    </div>
                  </div>
                  <div className={EditPetCSS['image-below1']} >
                    {product.img_petList.map((image, index) => (

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
          <div className={EditPetCSS['column']}>
            <div className={EditPetCSS['right-column']} >
              <div className={EditPetCSS['form-group']} >
                <label>Danh mục đăng tin</label>
                <select onChange>

                  <option    >Sản phẩm cho chó mèo</option>
           
                </select>
              </div>

              <div className={EditPetCSS['form-group']}>
                <label>Thông tin chi tiết</label>
                <select
                                    type={"text"}
                                    name="type"
                                    value={type}
                                    onChange={onInputChange}
                                >
            
                                    <option>Chó </option>
                                    <option>Mèo</option>
                                </select>
                                <select
                                    type={"text"}
                                    name="development_stage"
                                   value={product.development_stage}
                                    onChange={(e) => onInputChange(e)}
                                >
                                  
                                    <option   >Sơ sinh (dưới 3 tháng tuổi)</option>
                                    <option   >Trẻ (dưới 1 tuổi)</option>
                                    <option   >Trưởng thành (trên 1 tuổi)</option>
                                    <option   >Khác</option>
                                </select>
                <input type="text" name='pet_name' value={pet_name} onChange={onInputChange} placeholder="Tên sản phẩm" />

                <input type="text" name='pet_price' value={pet_price} onChange={onInputChange} placeholder="Giá bán" />

              </div>

              <div className={EditPetCSS['form-group']} >
                <label>Tiêu đề tin đăng và Mô tả chi tiết</label>
                <input type="text" name='title' value={title} onChange={onInputChange} placeholder="Tiêu đề tin đăng" />
                Tối đa 50 kí tự
                <div className={EditPetCSS['input-container']}>
                  <label>Mô tả chi tiết</label>
                  <textarea
                    name='description'
                    value={description}
                    onChange={onInputChange}

                    className={EditPetCSS['auto-expanding-input']}
                    placeholder="Mô tả chi tiết"
                    rows={rows}
                    maxLength="1500"

                  />
                  Tối đa 1500 kí tự
                </div>

              </div>

              <div className={EditPetCSS['form-group']} >
                <label>Thông tin người bán</label>
                <input type="text" name='address' value={address} onChange={onInputChange} placeholder="Địa chỉ" />
             
                <input type="text" name='phone' value={phone} onChange={onInputChange} placeholder="Số điện thoại" />
              </div>
              <div className={EditPetCSS['button']} >

                <input type="submit" value="Lưu" className={EditPetCSS['button-btn']}></input>
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
