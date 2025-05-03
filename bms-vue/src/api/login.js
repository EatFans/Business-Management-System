import request from '@/utils/request'

// 获取图片验证码
export function getCodeImg() {
    return request({
        url: '/captchaCode',
        method: 'get',
        timeout: 20000
    });
}

// 登录请求
export function login(username,password,code,uuid) {
	const data = {
		username,
		password,
		code,
		uuid
	}
	return request({
		url: '/login',
		method: 'post',
		data: data,
		timeout: 20000
	})
}

// 验证token
export function verityToken() {
	return request({
		url: '/verityToken',
		method: 'get',
		timeout: 20000
	})
}

// 退出登录接口
export function logout() {
	return request({
		url: '/logout',
		method: 'post',
		timeout: 20000
	})
}