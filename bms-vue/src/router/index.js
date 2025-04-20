import {createRouter, createWebHistory} from "vue-router"
import {verityToken} from "@/api/login.js";
import ProjectChildrenRouter from "@/router/project/ProjectChildrenRouter.js";



// 主要的二级路由
const childrenRouter = [
    {
        path: '/dashboard',
        component: () => import('@/views/pages/Dashboard.vue'),
        meta: { title: '仪表盘' },
    },
    {
        path: '/project',
        component: () => import('@/views/pages/Project.vue'),
        children: ProjectChildrenRouter,
        meta: {title: '项目'}
    },
    {
        path: '/task',
        component: () => import('@/views/pages/Task.vue'),
        meta: {title: '任务'}
    },
    {
        path: '/calendar',
        component: () => import('@/views/pages/Calendar.vue'),
        meta: {title: '日历'}
    },
    {
        path: '/holiday',
        component: () => import('@/views/pages/Holiday.vue'),
        meta: {title: '假期'}
    },
    {
        path: '/attendance',
        component: () => import('@/views/pages/Attendance.vue'),
        meta: {title: '考勤'}
    },
    {
        path: '/notify',
        component: () => import('@/views/pages/Notify.vue'),
        meta: {title: '消息'}
    }

];

// 公共一级路由
const publicRouter =  [
    {
        path: '/login',
        component: () => import('@/views/Login.vue'),
    },
    {
        path: '',
        component: () => import('@/views/Index.vue'),
        children: childrenRouter,
    },
    {
        path: '/',
        component: () => import('@/views/Index.vue'),
        children: childrenRouter,
    },
    {
        path: '/404',
        component: () => import('@/views/error/404.vue'),
    }

];



const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: publicRouter

});

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



export default router;