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



export default router;