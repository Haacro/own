import Vue from 'vue'
import App from './App.vue'
import router from './router'
//引入ElementUI组件
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//图片预览
import preview from 'vue-photo-preview'
import 'vue-photo-preview/dist/skin.css'

Vue.config.productionTip = false
Vue.use(ElementUI);
//图片预览
Vue.use(preview)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
