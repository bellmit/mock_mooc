import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Admin from './views/admin'
import Welcome from './views/admin/welcom'
import Chapter from './views/admin/chapter'

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [{
    path: '*',
    redirect: "/login",
  }, {
    path: '/login',
    component: Login
  }, {
    path: '/admin',
    component: Admin,
    children: [{
      path: 'welcome', // no "/"
      component: Welcome,
    }, {
      path: 'chapter',
      component: Chapter,
    }]
  }]
})