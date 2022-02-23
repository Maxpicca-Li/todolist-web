<template>
  <div>
    <div>
      <el-page-header @back="goBack" content="修改密码" style="margin: 10px"></el-page-header>
      <hr/>
    </div>
    <Password></Password>
  </div>
</template>

<script>
import Password from "@/components/Password";
export default {
  name: "pwdEdit",
  components:{Password},
  methods:{
    goBack() {
      console.log('go back');
      this.$router.replace({path: '/my'})
    },
  }
}
</script>

<style scoped>

</style>