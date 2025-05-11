import axios from 'axios';
import router from '@/router/index.js';

// 创建 Axios 实例
const request = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    timeout: 10000, // 请求超时时间
});

// 请求拦截器
request.interceptors.request.use(config => {
    // TODO: 添加 token 处理逻辑
    const token = localStorage.getItem('loginToken');
    if (token) {
        // 添加 token 到请求头
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// 响应拦截器
request.interceptors.response.use(response => {
    return response.data;
}, error => {
    if (error.response && error.response.status === 403) {
        console.log("未授权，请重新登录")
        localStorage.removeItem('loginToken');
        window.location.href= "/login";
    }
    return Promise.reject(error);
});

export default request;
