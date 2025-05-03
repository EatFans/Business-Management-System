
// 项目路径下的二级路由，总的三级路由

const ProjectChildrenRouter = [
    {
        path: ':projectCode',
        component: () => import('@/layout/project/Index.vue')
    },
    {
        path: '',
        component: () => import('@/layout/project/Default.vue')
    }
];


export default ProjectChildrenRouter;