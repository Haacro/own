import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: 'home'
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/relics/HomeView.vue')
  },
  {
    path: '/browse',
    name: 'browse',
    component: () => import('../views/relics/BrowseView.vue')
  },
  {
    path: '/details',
    name: 'details',
    component: () => import('../views/relics/DetailsView.vue')
  },
  {
    path: '/recommendation',
    name: 'recommendation',
    component: () => import('../views/relics/RecommendationView.vue')
  },
  {
    path: '/search',
    name: 'search',
    component: () => import('../views/relics/SearchView.vue')
  },
  {
    path: '/visual',
    name: 'visual',
    component: () => import('../views/relics/VisualView.vue')
  },
  {
    path: '/timeline',
    name: 'timeline',
    component: () => import('../views/relics/TimeLineView.vue')
  },
  {
    path: '/question',
    name: 'question',
    component: () => import('../views/relics/QuestionView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/relics/LoginView.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/relics/RegisterView.vue')
  },
  {
    path: '/test',
    name: 'test',
    component: () => import('../views/relics/TestView.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
