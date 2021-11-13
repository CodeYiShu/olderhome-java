import Vue from 'vue'
import Router from 'vue-router'
//导入所有组件
import Login from "../view/Login"
import Register from "../view/Register";
// 管理员
import AdminHome from "../view/admin/Home"
import AdminOlderManager from "../view/admin/OlderManager"
import AdminBenManager from "../view/admin/BenManager";
import AdminPrivate from "../view/admin/Private"
import AdminGuarderManager from "../view/admin/GuarderManager";
import AdminStaffManager from "../view/admin/StaffManager";
// 医护人员
import StaffHome from "../view/staff/Home"
import StaffOlderManager from "../view/staff/OlderManager"
import StaffBenManager from "../view/staff/BenManager";
import LookGuarder from "../view/staff/LookGuarder";
import StaffPrivate from "../view/staff/Private";
// 监护人员
import GuarderHome from "../view/guarder/Home"
import LookOlder from "../view/guarder/LookOlder"
import GuarderPrivate from "../view/guarder/Private";
import Pay from "../view/guarder/Pay";

import Form from "../view/form/index"
import Table from "../view/form/Table"

Vue.use(Router)

export default new Router({
  mode:'history', //指定为history模式
  routes: [

    {
      path:"/form",
      name:"Form",
      component:Form
    },
    {
      path:"/table",
      name:"Table",
      component:Table
    },

    // 登录
    {
      path:"/login",
      name:"Login",
      component:Login
    },
      //注册
    {
      path:"/register",
      name:"Register",
      component:Register
    },

      // =====================================管理员=========================================
    {
      path:"/admin/home",
      name:"AdminHome",
      component:AdminHome,
      // 定义子路由组件
      children:[
        {path:"/admin/olderManager",name:"AdminOlderManager",component:AdminOlderManager},
        {path: "/admin/benManager",name: "AdminBenManager",component: AdminBenManager},
        {path: "/admin/private",name: "AdminPrivate",component: AdminPrivate},
        {path: "/admin/guarderManager",name: "AdminGuarderManager",component: AdminGuarderManager},
        {path: "/admin/staffManager",name:"AdminStaffManager",component: AdminStaffManager}
      ],
      // 需要有管理员身份才可以访问路由及其子路由
      meta:{
        role:"Admin"
      }
    },

    // =====================================监护人员=====================================
    {
      path:"/guarder/home",
      name:"GuarderHome",
      component:GuarderHome,
      // 定义子路由组件
      children:[
        {path:"/guarder/lookOlder",name:"LookOlder",component:LookOlder},
        {path: "/guarder/private",name:"GuarderPrivate",component: GuarderPrivate},
        {path: "/guarder/pay",name:"Pay",component: Pay}

      ],
      // 需要有监护人员身份才可以访问路由及其子路由
      meta:{
        role:"Guarder"
      }
    },

    // =====================================医护人员=====================================
    {
      path:"/staff/home",
      name:"StaffHome",
      component:StaffHome,
      // 定义子路由组件
      children:[
        {path:"/staff/olderManager",name:"StaffOlderManager",component:StaffOlderManager},
        {path:"/staff/benManager",name:"StaffBenManager",component:StaffBenManager},
        {path:"/staff/private",name:"StaffPrivate",component:StaffPrivate},
        {path:"/staff/lookGuarder",name:"LookGuarder",component:LookGuarder},
      ],
      // 需要有医护人员身份才可以访问路由及其子路由
      meta:{
        role:"Staff"
      }
    }

  ]
})
