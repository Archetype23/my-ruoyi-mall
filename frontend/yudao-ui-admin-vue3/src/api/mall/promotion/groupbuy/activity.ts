import request from '@/config/axios'

// 拼团活动
export interface GroupBuyActivityVO {
  id?: number
  name?: string
  spuId?: number
  skuId?: number
  picUrl?: string
  groupPrice?: number
  originalPrice?: number
  userSize?: number
  stockTotal?: number
  stockUsed?: number
  singleLimit?: number
  startTime?: Date | string | number
  endTime?: Date | string | number
  expireMinutes?: number
  status?: number
  createTime?: Date
}

// 查询拼团活动分页
export const getGroupBuyActivityPage = async (params: any) => {
  return await request.get({ url: '/group-buy/activity/page', params })
}

// 查询拼团活动详情
export const getGroupBuyActivity = async (id: number) => {
  return await request.get({ url: `/group-buy/activity/get?id=${id}` })
}

// 获得拼团活动列表
export const getGroupBuyActivityListByIds = (ids: number[]) => {
  return request.get({ url: `/group-buy/activity/list-by-ids?ids=${ids.join(',')}` })
}

// 新增拼团活动
export const createGroupBuyActivity = async (data: GroupBuyActivityVO) => {
  return await request.post({ url: '/group-buy/activity/create', data })
}

// 修改拼团活动
export const updateGroupBuyActivity = async (data: GroupBuyActivityVO) => {
  return await request.put({ url: '/group-buy/activity/update', data })
}

// 关闭拼团活动
export const closeGroupBuyActivity = async (id: number) => {
  return await request.put({ url: `/group-buy/activity/close?id=${id}` })
}

// 删除拼团活动
export const deleteGroupBuyActivity = async (id: number) => {
  return await request.delete({ url: `/group-buy/activity/delete?id=${id}` })
}
