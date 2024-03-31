import axios from "axios";

import Cookies from "js-cookie";
const api = axios.create({
    baseURL: 'http://localhost:8080/api/'
})
export const get = async (path) => {
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
    const response = await api.get(path);
    return response.data
}

// export const post = async (path, values) => {
//     const token = localStorage.getItem('token');
//     const config = {
//         headers: {
//             "Authorization": Bearer ${token}
//         }
//     }
//     const response = await api.post(path, values, config);
//     return response
// }

// export const put = async (path, values) => {
//     const token = localStorage.getItem('token');
//     const config = {
//         headers: {
//             "Authorization": Bearer ${token}
//         }
//     }
//     const response = await api.put(path, values, config);
//     return response;
// }

// export const remove = async (path) => {
//     const token = localStorage.getItem('token');
//     const config = {
//         headers: {
//             "Authorization": Bearer ${token}
//         }
//     };
//     const response = await api.delete(path, config);
//     return response;
// };

// export const decode = (token) => {
//     return jwtDecode(token);
// }

export default api;