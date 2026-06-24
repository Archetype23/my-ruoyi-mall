import request from '@/config/axios'

// 拼团团单
export interface GroupBuyHeadVO {
  id?: number
  activityId?: number
  activityName?: string
  activityPicUrl?: string
  leaderUserId?: number
  leaderNickname?: string
  leaderAvatar?: string
  userSize?: number
  currentCount?: number
  status?: number
  statusName?: string
  expireTime?: Date
  successTime?: Date
  createTime?: Date
}

// 团单详情（含成员）
export interface GroupBuyHeadDetailVO extends GroupBuyHeadVO {
  groupPrice?: number
  originalPrice?: number
  failTime?: Date
  failReason?: string
  members?: GroupBuyMemberVO[]
}

export interface GroupBuyMemberVO {
  id?: number
  userId?: number
  nickname?: string
  avatar?: string
  isLeader?: boolean
  orderId?: number
  status?: number
  statusName?: string
  joinTime?: Date
}

// 查询团单分页
export const getGroupBuyHeadPage = async (params: any) => {
  return await request.get({ url: '/group-buy/head/page', params })
}

// 查询团单详情
export const getGroupBuyHeadDetail = async (id: number) => {
  return await request.get({ url: `/group-buy/head/get-detail?id=${id}` })
}

// 团单 ID 列表
export const getGroupBuyHeadListByIds = (ids: number[]) => {
  return request.get({ url: `/group-buy/head/list-by-ids?ids=${ids.join(',')}` })
}
