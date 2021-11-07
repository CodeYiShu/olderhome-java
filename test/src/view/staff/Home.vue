<template>
  <div>
    医护人员<br/>
    <router-link to="/staff/olderManager">老人管理</router-link><br/>
    <router-link to="/staff/private">个人资料</router-link><br/>
    <router-link to="/staff/benManager">床位管理</router-link><br/>
    <router-link to="/staff/lookGuarder">查看监护人</router-link><br/>
    <router-view/>
    <!-- 退出登录 -->
    <button @click="logout">退出登录</button>
  </div>
</template>

<script>
export default {
  name: "Home",
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