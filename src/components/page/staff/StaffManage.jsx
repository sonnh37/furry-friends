import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link} from "react-router-dom";
import "../../../../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavbarStaff from "../../navbaradmin/NavbarStaff";
import StaffCSS from './StaffManage.module.css';
import Cookies from 'js-cookie';
import user from "../user/user";



export default function StaffManage() {
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
    const result = await axios.get("http://localhost:8080/api/v1/user/posts/getALL_desc");
    setUsers(result.data);
  };


  const deleteUser = async (id) => {

    try {
      await axios.delete(`http://localhost:8080/api/v1/user/staff/${id}`).then((res) => {
        if (res.data == "deleted") {
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
    <div className={StaffCSS["container"]}>
      <NavbarStaff />
      <div className={StaffCSS["py-4"]}>

        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.N</th>
              <th scope="col">PostID</th>
              <th scope="col">Họ và tên</th>
              <th scope="col">Tiêu đề</th>
              <th scope="col">Nội dung</th>
              <th scope="col">Số lượng like</th>
              <th scope="col">Số lượng comment</th>
              <th scope="col">Hình ảnh</th>
             
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {currentData.map((user, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{user.post_id}</td>
                <td className={StaffCSS['username']}>{user.user_name}</td>
                <td>{user.title}</td>
                <td>{user.content}</td>
                <td>{user.total_like }</td>
                <td>{user.total_comment}</td>
                <td className={StaffCSS['imgage']}><img src={user.image} alt="" /></td>
           
                <td className={StaffCSS['btnbutton']}>

               
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => {
                      setSelectedUserId(user.user_id); // Lưu ID của người dùng sẽ bị xóa
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
          <div className={StaffCSS["delete-modal"]}>
            <p>Bạn có chắc chắn muốn xóa?</p>
            <div  className={StaffCSS["cancel-yes"]} >
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
      <div className={StaffCSS['IntersectBox']}>
          <div className={StaffCSS['box2']}>
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