import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link} from "react-router-dom";
import "../../../../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavbarStaff from "../../navbaradmin/NavbarStaff";
import StaffAnimalCSS from './StaffManageDogCat.module.css';
import Cookies from 'js-cookie';




export default function StaffManageDogCat() {
  const [users, setUsers] = useState([]);
  const [isDeleteModalOpen, setDeleteModalOpen] = useState(false);
  const [selectedUserId, setSelectedUserId] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [currentData, setCurrentData] = useState([]);
  const itemsPerPage = 7;
 
  useEffect(() => {
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const pageData = users.slice(startIndex, endIndex);
    setCurrentData(pageData);
  }, [users, currentPage]);


  const nextPage = () => {
    if (currentPage * itemsPerPage < users.length) {
      setCurrentPage(currentPage + 1);
    }
  };

  const prevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };
  useEffect(() => {
    loadUsers();
  }, []);
  

  const jwtToken = Cookies.get('jwtToken');
  axios.interceptors.request.use(
    config => {
      config.headers.Authorization = `Bearer ${jwtToken}`;
      return config;
    },
    error => {
      return Promise.reject(error);
    }
  );


  const loadUsers = async () => {
    const result = await axios.get("http://localhost:8080/api/v1/user/pets/getAll_desc");
    setUsers(result.data);
  };


  const deleteUser = async (id) => {

    try {
      await axios.delete(`http://localhost:8080/api/v1/user/staff/pets/${id}`).then((res) => {
        if (res.data == "Xoa thanh cong") {
          loadUsers();
          alert("Bạn đã xóa thành công")
        }
      
        else if (res.data == "not exist") {
          loadUsers();
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
  return (
    <div className={StaffAnimalCSS["container"]}>
      <NavbarStaff />
      <div className={StaffAnimalCSS["py-4"]}>

        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.N</th>
              <th scope="col">ID sản phẩm</th>
              <th scope="col">Tên người bán</th>
              <th scope="col">Tiêu đề bài đăng</th>
              <th scope="col">Tên sản phẩm</th>
              <th scope="col">Giá tiền</th>
              <th scope="col">Độ tuổi </th>
              <th scope="col">Giống Loài </th>
              <th scope="col">Nội dung bài </th>
              <th scope="col">số điện thoại</th>
              <th scope="col">địa chỉ</th>
              <th scope="col">ngày tháng</th>
              <th scope="col">Image</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {currentData.map((user, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{user.pet_id}</td>
                <td className={StaffAnimalCSS['username']}>{user.user_name}</td>
                <td>{user.title}</td>
                <td>{user.pet_name}</td>
                <td>{user.pet_price }</td>
                <td>{user.development_stage}</td>
                <td>{user.type}</td>
                <td>{user.description }</td>
            
                <td>{user.phone}</td>
                <td>{user.address}</td>
                <td>{user.date}</td>
             

                <td className={StaffAnimalCSS['imgage']}><img src={user.img_petList[0]} alt="" /></td>
           
                <td className={StaffAnimalCSS['btnbutton']}>

               
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => {
                      setSelectedUserId(user.pet_id); // Lưu ID của người dùng sẽ bị xóa
                      openDeleteModal(); // Mở modal xác nhận
                    }}                  >
                    Xóa
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
    
        {isDeleteModalOpen && ( // Render the modal if isDeleteModalOpen is true
          <div className={StaffAnimalCSS["delete-modal"]}>
            <p>Bạn có chắc chắn muốn xóa?</p>
            <div  className={StaffAnimalCSS["cancel-yes"]} >
            <button onClick={() => { deleteUser(selectedUserId); closeDeleteModal(); }}>
              Có
            </button>
            <button onClick={closeDeleteModal}>
              Không
            </button>
            </div>
          </div>
        )}
      </div>
      <div className={StaffAnimalCSS['IntersectBox']}>
          <div className={StaffAnimalCSS['box2']}>
            <button onClick={prevPage} disabled={currentPage === 1}>
              Previous Page
            </button>
            <button onClick={nextPage} disabled={currentPage * itemsPerPage >= users.length}>
              Next Page
            </button>
          </div>
        </div>
    </div>
  );
}