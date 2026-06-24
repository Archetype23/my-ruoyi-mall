import { Layout } from '@/utils/routerHelper'

const { t } = useI18n()

// 隐藏路由：商品/订单/售后 的表单详情页面
// 这些路由由列表页按钮通过 router.push({ name }) 导航，无需数据库菜单记录
const hiddenFormRoutes: AppRouteRecordRaw[] = [
  {
    path: '/mall/product/spu/add',
    component: () => import('@/views/mall/product/spu/form/index.vue'),
    name: 'ProductSpuAdd',
    meta: { hidden: true, title: '商品新增', noTagsView: true }
  },
  {
    path: '/mall/product/spu/edit/:id',
    component: () => import('@/views/mall/product/spu/form/index.vue'),
    name: 'ProductSpuEdit',
    meta: { hidden: true, title: '商品修改', noTagsView: true }
  },
  {
    path: '/mall/product/spu/detail/:id',
    component: () => import('@/views/mall/product/spu/form/index.vue'),
    name: 'ProductSpuDetail',
    meta: { hidden: true, title: '商品详情', noTagsView: true }
  },
  {
    path: '/mall/trade/order/detail/:id',
    component: () => import('@/views/mall/trade/order/detail/index.vue'),
    name: 'TradeOrderDetail',
    meta: { hidden: true, title: '订单详情', noTagsView: true }
  },
  {
    path: '/mall/trade/afterSale/detail/:id',
    component: () => import('@/views/mall/trade/afterSale/detail/index.vue'),
    name: 'TradeAfterSaleDetail',
    meta: { hidden: true, title: '售后详情', noTagsView: true }
  },
  {
    path: '/mall/product/property/value/:propertyId',
    component: () => import('@/views/mall/product/property/value/index.vue'),
    name: 'ProductPropertyValue',
    meta: { hidden: true, title: '属性值管理', noTagsView: true }
  }
]

const remainingRouter: AppRouteRecordRaw[] = [
  ...hiddenFormRoutes,
  {
    path: '/redirect',
    component: Layout,
    name: 'RedirectRoot',
    children: [
      {
        path: '/redirect/:path(.*)',
        name: 'Redirect',
        component: () => import('@/views/Redirect/Redirect.vue'),
        meta: {}
      }
    ],
    meta: {
      hidden: true,
      noTagsView: true
    }
  },
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    name: 'Home',
    meta: {},
    children: [
      {
        path: 'index',
        component: () => import('@/views/Home/Index.vue'),
        name: 'Index',
        meta: {
          title: t('router.home'),
          icon: 'ep:home-filled',
          noCache: false,
          affix: true
        }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    name: 'UserInfo',
    meta: {
      hidden: true
    },
    children: [
      {
        path: 'profile',
        component: () => import('@/views/Profile/Index.vue'),
        name: 'Profile',
        meta: {
          canTo: true,
          hidden: true,
          noTagsView: false,
          icon: 'ep:user',
          title: t('common.profile')
        }
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/Login/Login.vue'),
    name: 'Login',
    meta: {
      hidden: true,
      title: t('router.login'),
      noTagsView: true
    }
  },
  {
    path: '/403',
    component: () => import('@/views/Error/403.vue'),
    name: 'NoAccess',
    meta: {
      hidden: true,
      title: '403',
      noTagsView: true
    }
  },
  {
    path: '/404',
    component: () => import('@/views/Error/404.vue'),
    name: 'NoFound',
    meta: {
      hidden: true,
      title: '404',
      noTagsView: true
    }
  },
  {
    path: '/500',
    component: () => import('@/views/Error/500.vue'),
    name: 'Error',
    meta: {
      hidden: true,
      title: '500',
      noTagsView: true
    }
  },
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/views/Error/404.vue'),
    name: '',
    meta: { hidden: true, title: '404', noTagsView: true }
  }
]

export default remainingRouter
