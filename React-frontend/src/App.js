import { BrowserRouter,Routes,Route } from "react-router-dom";
import Register from "./components/Register";
import Login from "./components/Login";
import ProductPage from "./components/ProductPage";
import ProductUpPage from "./components/ProductUpPage";
import ProductListPage from "./components/ProductListPage";
function App() {
  return (
    <div>

      <BrowserRouter>
            <Routes>
              {/* <Route path="/home" element= { <Home/>} /> */}
              <Route path="/register" element= { <Register/>} />
              <Route path="/product" element= { <ProductPage/>} />
              <Route path="/product/up" element= { <ProductUpPage/>} />
              <Route path="/product/list" element= { <ProductListPage/>} />
              <Route path="/login" element= { <Login/>} />
            </Routes>
        </BrowserRouter>
      
    </div>
  );
}

export default App;