import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;

/**
 * axios interceptor
 */
axios.interceptors.request.use(function (request) {
  console.log("request: ", request);
  return request;
}, error => {
});
axios.interceptors.response.use(function (response) {
  console.log("response: ", response)
  return response;
}, error => {
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
