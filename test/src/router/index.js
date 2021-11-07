import Vue from 'vue'
import Router from 'vue-router'
//导入所有组件
import Login from "../view/Login"
import AdminHome from "../view/admin/Home"
import GuarderHome from "../view/guarder/Home"
import StaffHome from "../view/staff/Home"
import aOrderManager from "../view/admin/OrderManager"
import sOrderManager from "../view/staff/OrderManager"
import aBenManager from "../view/admin/BenManager";
import LookOrder from "../view/guarder/LookOrder"


Vue.use(Router)

export default new Router({
  mode:'history', //指定为history模式
  routes: [
    // 登录
    {
      path:"/login",
      name:"Login",
      component:Login
    },
      // 管理员
    {
      path:"/admin/home",
      name:"AdminHome",
      component:AdminHome,
      // 定义子路由组件
      children:[
        {path:"/admin/orderManager",name:"mOrderManager",component:aOrderManager},
        {path: "/admin/benManager",name: "mBenManager",component: aBenManager}
      ],
      // 需要有管理员身份才可以访问路由及其子路由
      meta:{
        role:"Admin"
      }
    },
    // 监护人员
    {
      path:"/guarder/home",
      name:"GuarderHome",
      component:GuarderHome,
      // 定义子路由组件
      children:[
        {path:"/guarder/lookOrder",name:"LookOrder",component:LookOrder},
      ],
      // 需要有监护人员身份才可以访问路由及其子路由
      meta:{
        role:"Guarder"
      }
    },
    // 医护人员
    {
      path:"/staff/home",
      name:"StaffHome",
      component:StaffHome,
      // 定义子路由组件
      children:[
        {path:"/staff/orderManager",name:"sOrderManager",component:sOrderManager},
      ],
      // 需要有医护人员身份才可以访问路由及其子路由
      meta:{
        role:"Staff"
      }
    },

  ]
})
