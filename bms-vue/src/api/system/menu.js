// 系统菜单相关接口封装

import request from '@/utils/request.js'

// 获取所有的菜单信息
export function getMenuList() {
    return request({
        url: '/system/menu/get',
        method: 'get'
    })
}