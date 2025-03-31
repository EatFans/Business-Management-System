import axios from 'axios';

// 创建 Axios 实例
const request = axios.create({
    baseURL: 'http://localhost:8080', // 你的后端接口地址
    timeout: 10000, // 请求超时时间
});

// 请求拦截器
request.interceptors.request.use(config => {
    // 这里可以添加 token 处理逻辑
    return config;
}, error => {
    return Promise.reject(error);
});

// 响应拦截器
request.interceptors.response.use(response => {
    return response.data;
}, error => {
    return Promise.reject(error);
});

export default request;
