<template>
  <div>
    监护人员<br/>
    <router-link to="/guarder/lookOlder">查看老人</router-link><br/>
    <router-link to="/guarder/private">个人资料</router-link><br/>
    <router-link to="/guarder/pay">缴费</router-link><br/>
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