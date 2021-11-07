import Vue from 'vue'
import App from './App'
//导入ElementUI模块和他的CSS
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
//导入router模块
import router from './router'
//导入node_modules下的axios模块
import axios from 'axios';
//导入permission.js
import "./permission.js"
//引入axios.js配置文件
import "./axios.js"

//显式声明ElementUI模块
Vue.use(ElementUI)
//引用全局
Vue.prototype.$axios = axios
new Vue({
  //挂载id为app的div
  el: '#app',
  //显式声明router模块
  router,
  //渲染App组件
  render: h => h(App)
});     