import axios from "axios";
import store from "../store";
import { useNavigate } from "react-router";
// const navigate = useNavigate();

// axios 객체 생성
const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_NAVER_API_KEY,
  headers: {
    // "Content-type": "application/json",
    "X-Custom-Header": "foobar",
  },
});

// 요청 인터셉터 추가.
axiosInstance.interceptors.request.use(
  (config) => {
    const token = store.getState().jwt.value;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    alert("재로그인을 해 주세요");
    useNavigate("/login");
  }
);

// 응답 인터셉터 추가
axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    // if (error.response && error.response.data.status === 401) {
    alert("재로그인을 해 주세요");
    useNavigate("/login");
    // } else {
    //   useNavigate("/error");
    // }
  }
);

export default axiosInstance;
