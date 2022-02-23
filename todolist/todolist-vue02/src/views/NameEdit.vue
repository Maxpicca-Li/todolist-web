<template>
  <div>
    <div>
      <el-page-header @back="goBack" content="修改昵称" style="margin: 10px"></el-page-header>
      <hr/>
    </div>
    <NameComp></NameComp>
  </div>
</template>

<script>
import NameComp from "@/components/NameComp";
export default {
  name: "NameEdit",
  components:{NameComp},
  methods:{
    goBack() {
      console.log('go back');
      this.$router.replace({path: '/index'})
    },
  }
}
</script>

<style scoped>

</style>