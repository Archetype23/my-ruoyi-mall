// 社区拼团 API
import request from '@/sheep/request/index'

// ========== App 端 ==========
// 获取拼团活动列表
export const getAppGroupBuyActivityList = () => {
  return request({
    url: '/group-buy/activity/list',
    method: 'GET'
  })
}

// 获取拼团活动详情
export const getAppGroupBuyActivityDetail = (id) => {
  return request({
    url: '/group-buy/activity/get-detail',
    method: 'GET',
    params: { id }
  })
}

// 我的拼团
export const getMyGroupBuyHeads = (status) => {
  return request({
    url: '/group-buy/head/my',
    method: 'GET',
    params: { status }
  })
}

// 开团（团长发起）
export const openGroupBuyHead = (data) => {
  return request({
    url: '/group-buy/head/open',
    method: 'POST',
    data
  })
}

// 参团（加入已有团）
export const joinGroupBuyHead = (data) => {
  return request({
    url: '/group-buy/head/join',
    method: 'POST',
    data
  })
}

// 团单详情
export const getGroupBuyHeadDetail = (id) => {
  return request({
    url: '/group-buy/head/get-detail',
    method: 'GET',
    params: { id }
  })
}
