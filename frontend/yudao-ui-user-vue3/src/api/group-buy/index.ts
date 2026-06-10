import request from '@/config/axios'

// 获取团购活动列表
export const getGroupBuyActivityList = (pageNo: number = 1, pageSize: number = 10) => {
  return request.get({
    url: `/group-buy/activity/list?pageNo=${pageNo}&pageSize=${pageSize}`
  })
}

// 获取团购活动详情
export const getGroupBuyActivityDetail = (activityId: number) => {
  return request.get({
    url: `/group-buy/activity/${activityId}`
  })
}

// 获取活跃团购组
export const getActiveGroups = (activityId: number, pageNo: number = 1, pageSize: number = 10) => {
  return request.get({
    url: `/group-buy/groups?activityId=${activityId}&pageNo=${pageNo}&pageSize=${pageSize}`
  })
}

// 加入团购组
export const joinGroup = (groupId: number) => {
  return request.post({
    url: `/group-buy/groups/${groupId}/join`,
    data: {}
  })
}

// 获取团购组详情
export const getGroupDetail = (groupId: number) => {
  return request.get({
    url: `/group-buy/groups/${groupId}/detail`
  })
}

// 获取团购组成员列表
export const getGroupMembers = (groupId: number) => {
  return request.get({
    url: `/group-buy/groups/${groupId}/members`
  })
}

// 获取用户的团购列表
export const getUserGroups = (pageNo: number = 1, pageSize: number = 10) => {
  return request.get({
    url: `/group-buy/my-groups?pageNo=${pageNo}&pageSize=${pageSize}`
  })
}

// 获取提货点信息
export const getPickupLocation = (locationId: number) => {
  return request.get({
    url: `/group-buy/pickup-locations/${locationId}`
  })
}

// 创建新团购组（如果没有可用的组）
export const createNewGroup = (activityId: number) => {
  return request.post({
    url: `/group-buy/groups/create`,
    data: {
      activityId
    }
  })
}
