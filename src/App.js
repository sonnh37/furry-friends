import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Register from "./components/page/register/Register";
import Login from "./components/page/login/Login";
import ProductPage from "./components/page/productpage/ProductPage";
import ProductUpPage from "./components/page/productpage/ProductUpPage";
import ProductListPage from "./components/page/productpage/ProductListPage";
import ProductDetailForm from "./components/page/productpage/ProductDetailForm";
import Home from "./components/page/home/home";
import PostView from "./components/page/post/PostView";
import PostUpForm from "./components/page/post/PostUpForm";
import PostViewDetail from "./components/page/post/PostDetail";
import User from "./components/page/user/user";
import Admin from "./components/page/admin/Admin";
import EditMember from "./components/page/admin/edit";
import ManageProduct from "./components/page/user/ManageProduct";
import EditProduct from "./components/page/user/EditProduct";
import AddStaff from "./components/page/admin/Addstaff";
import ManageMember from "./components/page/admin/ManageMember";
import ViewProductUser from "./components/page/user/ViewProductUser";
import Cookies from "js-cookie";
import DogCatUpPage from "./components/page/productpage/DogCatUpPage";
import EditPost from "./components/page/user/EditPost";
import ManagePost from "./components/page/user/ManagePost";
import ProductSearch from "./components/layout/ProductSearch";
import PostSearch from "./components/page/post/PostSearch";
import Staff from "./components/page/staff/Staff";
import ManageStaff from "./components/page/staff/StaffManage"
import ManagePet from "./components/page/pet/ManagePet"
import EditPet from "./components/page/pet/EditPet"
import Petdetail from "./components/page/pet/Petdetail";
import PetList from "./components/page/pet/PetList";
import PetListDog from "./components/page/pet/PetListDog";
import PetListCat from "./components/page/pet/PetListCat";
import StaffManageProduct from "./components/page/staff/StaffManageProduct";
import StaffManageDogCat from "./components/page/staff/StaffManageDogCat";
import ViewDogCat from "./components/page/user/ViewDogCat";


function ProtectedRoute({ element, allowedRoles }) {
  // Kiểm tra vai trò của người dùng ở đây
  const userRoles = Cookies.get("role").split(',').map(role => parseInt(role, 10))

  if (userRoles.some(role => allowedRoles.includes(role))) {
    return element;
  } else {
    return <Navigate to="/home" />;
  }
}



export default function App() {




  return (

    <div>

      <BrowserRouter>


        <Routes>
        <Route path="/managepoststaff" element={<ProtectedRoute element={<ManageStaff />} allowedRoles={[2]} />} />
          <Route path="/staff" element={<ProtectedRoute element={<Staff />} allowedRoles={[2]} />} />
          <Route path="/staffmangeproduct" element={<ProtectedRoute element={<StaffManageProduct />} allowedRoles={[2]} />}   />
          <Route path="/staffmanagedogcat"  element={<ProtectedRoute element={<StaffManageDogCat />} allowedRoles={[2]} />}  />
         
          <Route path="/managePet" element={<ManagePet />} />
          <Route path="/petlist" element={<PetList />} />
  
          <Route path="/petlistdog" element={<PetListDog />} />
          <Route path="/petlistcat" element={<PetListCat />} />
          <Route path="/editPet/:pet_id" element={<EditPet />} />
          <Route path="/pet/detail/:pet_id" element={<Petdetail />} />
          <Route path="/postSearch" element={<PostSearch />} />
          <Route path="/productSearch" element={<ProductSearch />} />
          <Route path="/managepost" element={<ManagePost />} />
          <Route path="/editpost/:post_id" element={<EditPost />} />
          <Route path="/viewallproduct" element={<ViewProductUser />} />
          <Route path="/viewalldogcat" element={<ViewDogCat />} />
          <Route path="/editproduct/:product_id" element={<EditProduct />} />
          <Route path="/manageproduct" element={<ManageProduct />} />
          <Route path="/addstaff" element={<ProtectedRoute element={<AddStaff />} allowedRoles={[3]} />} />
          <Route
            path="/admin"
            element={<ProtectedRoute element={<Admin />} allowedRoles={[3]} />} />
          <Route path="/editmember/:user_id" element={<ProtectedRoute element={<EditMember />} allowedRoles={[2,3]} />} />
          <Route path="/managemember" element={<ProtectedRoute element={<ManageMember />} allowedRoles={[2,3]} />} />
          <Route path="/profile" element={<User />} />
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/product" element={<ProductPage />} />
          <Route path="/register" element={<Register />} />
          <Route path="/product/up1" element={<ProductUpPage />} />
          <Route path="/product/up2" element={<DogCatUpPage />} />
          <Route path="/product/list" element={<ProductListPage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/product/detail/:product_id" element={<ProductDetailForm />} />
          <Route path="/post/up" element={<PostUpForm />} />
          <Route path="/post" element={<PostView />} />
          <Route path="/post/detail/:post_id" element={<PostViewDetail />} />
        </Routes>
      </BrowserRouter>

    </div>
  );
}


