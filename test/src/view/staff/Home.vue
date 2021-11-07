<template>
  <div>
    医护人员
    <router-link to="/staff/orderManager">老人管理</router-link><br/>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "Home"
}
</script>

<style scoped>

</style>