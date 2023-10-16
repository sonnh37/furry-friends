import { BrowserRouter,Routes,Route } from "react-router-dom";
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
function App() {
  return (
   
    <div>
  
      <BrowserRouter>
            <Routes>
            <Route path="/1" element= { <User/>} />
            <Route path="/" element= { <Home/>} />
              <Route path="/home" element= { <Home/>} />
              <Route path="/product" element= { <ProductPage/>} />
              <Route path="/register" element= { <Register/>} />
              <Route path="/product/up1" element= { <ProductUpPage/>} />
              <Route path="/product/list" element= { <ProductListPage/>} />
              <Route path="/login" element= { <Login/>} />
              <Route path="/product/detail" element= { <ProductDetailForm/>} />
              <Route path="/post/up" element= { <PostUpForm/>} />
              <Route path="/post" element= { <PostView/>} />
              <Route path="/post/detail" element= { <PostViewDetail/>} />
            </Routes>
        </BrowserRouter>
      
    </div>
  );
}

export default App;