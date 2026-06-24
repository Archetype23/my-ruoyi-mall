import request from '@/config/axios'

// 团长核销
export interface GroupBuyVerifyItemVO {
  memberId?: number
  headId?: number
  userId?: number
  userNickname?: string
  userAvatar?: string
  orderId?: number
  pickUpVerifyCode?: string
  orderStatus?: number
  activityId?: number
  activityName?: string
  spuName?: string
  spuPicUrl?: string
  createTime?: Date
}

export interface GroupBuyVerifyReqVO {
  verifyCode?: string
}

// 团长核销列表
export const getGroupBuyVerifyList = async (params: any) => {
  return await request.get({ url: '/group-buy/verify/list', params })
}

// 核销
export const confirmGroupBuyVerify = async (data: GroupBuyVerifyReqVO) => {
  return await request.post({ url: '/group-buy/verify/confirm', data })
}
