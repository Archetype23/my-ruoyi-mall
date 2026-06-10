import { Layout } from '@/utils/routerHelper'

const { t } = useI18n()

const remainingRouter: AppRouteRecordRaw[] = [
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
    redirect: '/group-buy',
    name: 'Home',
    meta: {},
    children: [
      {
        path: 'group-buy',
        component: () => import('@/views/GroupBuyHome/Index.vue'),
        name: 'GroupBuyHome',
        meta: {
          title: '社区团购',
          icon: 'ep:home-filled',
          noCache: false,
          affix: true
        }
      }
    ]
  },
  {
    path: '/group-buy',
    component: Layout,
    name: 'GroupBuyModule',
    meta: {
      alwaysShow: true,
      title: '团购管理',
      icon: 'ep:shopping-cart-full'
    },
    children: [
      {
        path: 'activity/:id',
        component: () => import('@/views/GroupBuyActivity/Index.vue'),
        name: 'GroupBuyActivity',
        meta: {
          hidden: true,
          title: '活动详情',
          noTagsView: false
        }
      },
      {
        path: 'my-group/:id',
        component: () => import('@/views/GroupBuyDetail/Index.vue'),
        name: 'GroupBuyDetail',
        meta: {
          hidden: true,
          title: '团购详情',
          noTagsView: false
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
