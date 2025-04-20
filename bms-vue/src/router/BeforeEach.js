import router from './index.js'
import {verityToken} from "@/api/login.js";

// 路由守卫
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('loginToken');

    // 1. 没有 token 且不是访问 /login，则跳转到 /login
    if (!token && to.path !== '/login') {
        return next('/login');
    }

    // 2. 有 token，验证 token 合法性
    if (token) {
        // 如果访问的是登录页，不需要校验 token
        if (to.path === '/login') {
            return next(); // 放行
        }

        // 否则验证 token（异步）
        verityToken().then(res => {
            if (res.code === 200) {
                next(); // token 有效
            } else {
                localStorage.removeItem('loginToken');
                next('/login'); // token 无效
            }
        }).catch(() => {
            next('/login'); // 请求错误也跳登录
        });

        return;
    }

    // 3. 没有 token 且访问的是 /login
    next();
});