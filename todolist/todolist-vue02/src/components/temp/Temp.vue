<template>
  <div>
  <nav-menu></nav-menu>
  <router-view/>
  </div>
</template>

<script>
import NavMenu from "@/components/NavMenu";
export default {
  name: "Temp",
  components:{ NavMenu},
}
</script>

<style scoped>

</style>