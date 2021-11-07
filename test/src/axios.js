//导入node_modules下的axios模块
import axios from 'axios';
// 引入element-ui模块
import Element from 'element-ui'
// 引入路由
import router from './router'

var s=window.location.toString();   
		    var s1=s.substr(7,s.length);
		    var s2=s1.indexOf("/");
		    s=s.substr(0,8+s2);

//配置url前缀
axios.defaults.baseURL="http://localhost:8081"

//前置拦截：每次发送请求时，都会来到此拦截器，在发送请求之前做些什么
axios.interceptors.request.use(config => { //可以使用config配置请求头等
    console.log("前置")
    return config;  //返回config进行放行
})

//配置后置拦截：每次响应回来时，都会来到此拦截器，对响应数据做点什么
axios.interceptors.response.use(response =>{  //response就是后台返回的结果数据
    let res = response.data;   //获取响应数据
    if(res.code == 200){  //如果状态码是200，则放行
      return response;
    }else{   //非200（后台没抛出异常的情况）
        Element.Message.error("操作错了哦",{duration : 2*1000})  //使用ElementUI的弹框弹出错误信息 
        //以下一行是为了返回一个异常提示就不会继续往下走了，不+的话发送的axios中的响应部分还是会继续走的
        return Promise.reject(response.data.message)
    }
  },error=>{  // 后台抛异常时会走这里，捕获并处理后台异常信息
     // 使得异常信息更加友好
    if (error.response.data) { //data不为空时
      error.message = error.response.data.message
    }
    if(error.response.status == 401){  //可能是用户名不存在或密码不存在
      //清空store保存的token userinfo
      localStorage.clear;
      sessionStorage.clear;
      router.push("/login")  //跳转登录页面
    } 
    //将错误信息弹窗
    Element.Message.error(error.message)
    //以下一行是为了返回一个异常提示就不会继续往下走了，不+的话发送的axios中的响应部分还是会继续走的
    return Promise.reject(error)
})