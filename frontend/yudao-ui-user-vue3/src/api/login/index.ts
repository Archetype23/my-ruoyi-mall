import request from '@/config/axios'
import type { RegisterVO, UserLoginVO } from './types'

export interface SmsCodeVO {
  mobile: string
  scene: number
}

export interface SmsLoginVO {
  mobile: string
  code: string
}

// 用户登录 (Member/Community member login - uses mobile + password)
export const login = (data: UserLoginVO) => {
  return request.post({
    url: '/member/auth/login',
    data: {
      mobile: data.username,
      password: data.password
    },
    headers: {
      isEncrypt: false
    }
  })
}

// 用户注册 (Member registration)
export const register = (data: RegisterVO) => {
  return request.post({
    url: '/member/auth/register',
    data: {
      mobile: data.username,
      password: data.password
    }
  })
}

// 使用租户名，获得租户编号
export const getTenantIdByName = (name: string) => {
  return request.get({ url: '/system/tenant/get-id-by-name?name=' + name })
}

// 使用租户域名，获得租户信息
export const getTenantByWebsite = (website: string) => {
  return request.get({ url: '/system/tenant/get-by-website?website=' + website })
}

// 登出
export const loginOut = () => {
  return request.post({ url: '/member/auth/logout' })
}

// 获取用户权限信息
export const getInfo = async () => {
  try {
    return await request.get({ url: '/member/auth/get-permission-info' })
  } catch (e) {
    console.warn('get-permission-info not available, using defaults')
    return {
      permissions: [],
      roles: [],
      user: { id: 0, nickname: 'User', avatar: '', deptId: 0 },
      menus: []
    }
  }
}

//获取登录验证码
export const sendSmsCode = (data: SmsCodeVO) => {
  return request.post({ url: '/member/auth/send-sms-code', data })
}

// 短信验证码登录
export const smsLogin = (data: SmsLoginVO) => {
  return request.post({ url: '/member/auth/sms-login', data })
}

// 获取验证图片以及 token
export const getCode = (data: any) => {
  return request.postOriginal({ url: 'system/captcha/get', data })
}

// 滑动或者点选验证
export const reqCheck = (data: any) => {
  return request.postOriginal({ url: 'system/captcha/check', data })
}

// 通过短信重置密码
export const smsResetPassword = (data: any) => {
  return request.post({ url: '/member/auth/reset-password', data })
}
