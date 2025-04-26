import {createRouter, createWebHistory} from "vue-router"
import {verityToken} from "@/api/login.js";
import ProjectChildrenRouter from "@/router/project/ProjectChildrenRouter.js";



// 主要的二级路由
const childrenRouter = [
    {
        path: '/dashboard',
        name: 'Dashboard',
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
    },
    {
        path: '/employee',
        component: () => import('@/views/pages/Employee.vue'),
        meta: {title: '员工管理'}
    },
    {
        path: '/role',
        component: () => import('@/views/pages/Role.vue'),
        meta: {title: '角色管理'}
    },
    {
        path: '/permission',
        component: () => import('@/views/pages/Permission.vue'),
        meta: {title: '权限管理'}
    },
    {
        path: '/PManager',
        component: () => import('@/views/pages/ProjectManager.vue'),
        meta: {title: '项目管理'}
    },
    {
        path: '/TManager',
        component: () => import('@/views/pages/TaskManager.vue'),
        meta: {title: '任务管理'}
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

    if (!token && to.path !== '/login') {
        // 没有 token 又不是去登录页，强制跳转登录
        return next('/login');
    }

    if (token && to.path === '/login') {
        // 已经登录了，还去登录页？跳回首页或者别的页
        return next('/dashboard');
    }

    // 其他情况，直接放行
    next();
});



export default router;