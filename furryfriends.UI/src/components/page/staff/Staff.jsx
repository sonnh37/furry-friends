import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link} from "react-router-dom";
import "../../../../node_modules/bootstrap/dist/css/bootstrap.min.css"
import NavbarStaff from "../../navbaradmin/NavbarStaff";
import StaffCss from './Staff.module.css';
import Cookies from 'js-cookie';



export default function Staff() {
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
    const result = await axios.get("http://localhost:8080/api/v1/user/staff");
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
    <div className={StaffCss["container"]}>
      <NavbarStaff />
      <div className={StaffCss["py-4"]}>

        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.N</th>
              <th scope="col">MemberID</th>
              <th scope="col">Account</th>
              <th scope="col">email</th>
              <th scope="col">Tên</th>
              <th scope="col">Họ</th>
              <th scope="col">Số điện thoại</th>
              <th scope="col">Địa chỉ</th>
              <th scope="col">Sinh nhật</th>
              <th scope="col">Giới tính</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {currentData.map((user, index) => (
              <tr>
                <th scope="row" key={index}>
                  {index + 1}
                </th>
                <td>{user.user_id}</td>
                <td>{user.account}</td>
                <td>{user.email}</td>
                <td>{user.first_name}</td>
                <td>{user.last_name}</td>
                <td>{user.phone}</td>
                <td>{user.address}</td>
                <td>{user.birth}</td>
                <td>{user.sex}</td>
                <td className={StaffCss['btnbutton']}>

                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/editmember/${user.user_id}`}
                  >
                    Sửa
                  </Link>
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
          <div className={StaffCss["delete-modal"]}>
            <p>Bạn có chắc chắn muốn xóa?</p>
            <div  className={StaffCss["cancel-yes"]} >
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
      <div className={StaffCss['IntersectBox']}>
          <div className={StaffCss['box2']}>
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