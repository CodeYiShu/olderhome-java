import router from "./router";
import el from "element-ui/src/locale/lang/el";

// 每次路由都会经过此函数，路由判断登录 根据路由配置文件的参数
router.beforeEach((to, from, next) => {
    //如果是登录，则放行
    if(to.path=="/login"){
        next(); 
    }else if(localStorage.getItem("jwt")){  //如果已经登录的则判断角色
        //从sessionStorage中获取角色
        const trueRole = JSON.parse(sessionStorage.getItem("userInfo")).role;
            // 判断该路由有meta属性
    if (to.matched.some(record => record.meta.role === "Admin")) {
        //只有管理员身份才可以访问
        if(trueRole === "Admin"){
           next();
        }else{  //如果是其他身份，各自跳转到主页
            alert("无权访问")
            if(trueRole === "Staff"){
                next("/staff/home");
            }else if(trueRole === "Guarder"){
                next("/guarder/home")
            }else {
                next("/login")
            }
        }
    }else if(to.matched.some(record => record.meta.role === "Staff")){
        //只有医护人员和管理员才可以访问
        if(trueRole === "Staff" || trueRole === "Admin"){
            next();
        }else if(trueRole === "Guarder"){ //如果是监护人员，则跳转回他的主页
            alert("无权访问")
            next("/guarder/home")
        }else {
            next("/login")
        }
    }else if(to.matched.some(record => record.meta.role === "Guarder")){
        //只有监护人员和管理员才可以访问
        if(trueRole === "Guarder" || trueRole === "Admin"){
            next();
        }else if(trueRole === "Staff"){  //如果是医护人员，则跳转回他的页面
            alert("无权访问")
            next("/staff/home")
        }else{
            next("/login")
        }
    }
    }else{ //没有登录的进入登录页面
        next("/login");
    }


})