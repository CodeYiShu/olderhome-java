<template>
  <div>
    管理员<br/>
    <!--提供路由到子组件的路由链接-->
    <router-link to="/admin/olderManager">老人管理</router-link> <br/>
    <router-link to="/admin/benManager">床位管理</router-link><br/>
    <router-link to="/admin/private">个人资料</router-link><br/>
    <router-link to="/admin/staffManager">医护人员管理</router-link><br/>
    <router-link to="/admin/guarderManager">监护人员管理</router-link><br/>
    <!--子路由组件都会展示在这里-->
   显示路由组件：
   <router-view/><br/>
   <!-- 退出登录 -->
   <button @click="logout">退出登录</button>
  </div>
</template>

<script>
  export default{
    methods:{
      logout(){
        localStorage.removeItem("jwt");
        sessionStorage.clear("userInfo");
        this.$router.push("/login")
      }
    }
  }
</script>

<style scoped>

</style>