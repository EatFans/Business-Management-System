import request from '@/utils/request'

// 获取图片验证码
export function getCodeImg() {
    return request({
        url: '/captchaCode',
        method: 'get',
        timeout: 20000
    });
}