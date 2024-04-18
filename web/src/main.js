import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from "./store";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import { getRequest } from './utils/api';
import { postRequest } from './utils/api';
import { putRequest } from './utils/api';
import { deleteRequest } from './utils/api';
import { downloadRequest } from './utils/download';
import { initMenu } from "./utils/menu";

Vue.config.productionTip = false;
Vue.use(ElementUI);

// 插件形式使用请求，就不用在每个vue页面中import {xxRequest} from '../utils/api'
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.downloadRequest = downloadRequest;

router.beforeEach((to, from, next) => {
  if (window.sessionStorage.getItem('tokenStr')) {
    initMenu(router, store);
    if (!window.sessionStorage.getItem('user')) {
      return getRequest('/user/info').then(resp => {
        if (resp) {
          //存入用户信息
          window.sessionStorage.setItem('user', JSON.stringify(resp));
          next();
        }
      });
    }
    next();
  } else {
    if (to.path == '/') {
      next();
    } else {
      next('/?redirect=' + to.path);
    }
  }
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
