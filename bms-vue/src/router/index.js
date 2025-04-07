import {createRouter, createWebHistory} from "vue-router"

// 公共路由
const publicRouter =  [
    {
        path: '/login',
        component: () => import('@/views/Login.vue'),
    },
    {
        path: '/dashboard',
        component: () => import('@/views/Index.vue'),
    },
    {
        path: '/',
        component: () => import('@/views/Index.vue'),
    },
    {
        path: '/project',
        component: () => import('@/views/Project.vue')
    },
    {
        path: '/calendar',
        component: () => import('@/views/Calendar.vue')
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: publicRouter

});


export default router;