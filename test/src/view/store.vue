<template>


<div>
  在子组件定义prop属性，需要一个数据a，这个数据在模板中使用a
  在父组件使用他时，在自己data那里定义一个数据b，v-bind:a="b"
  这样就将数据b，传递给子组件a
</div>


</template>

<script>
export default {
  name: "store"
}
</script>

<style scoped>

</style>