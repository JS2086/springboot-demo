import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '../views/Login';
import Home from '../views/Home';
import PersonalCenter from '../views/PersonalCenter';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: '登录',
    component: Login,
    hidden: true
  },
  {
    path: '/home',
    name: '首页',
    component: Home,
    children: [
      {
        path: '/personal_center',
        name: '个人中心',
        component: PersonalCenter
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;
