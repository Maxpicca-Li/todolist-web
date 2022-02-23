<template>
  <!--父路由-->
  <div>
    <NavMenu></NavMenu>
    <router-view/>
  </div>
</template>

<script>
import NavMenu from "@/components/NavMenu";
export default {
  name: "Home",
  components: {NavMenu}
}
</script>

<style scoped>

</style>