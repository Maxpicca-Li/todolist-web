<template>
  <div>
    <div style="margin: 10px;text-align: center"><h3>Hello {{username}}!</h3></div>
    <div style="text-align: center"><p>{{ email }}</p></div>
    <div :default-active="'/index'">
      <!--<div>
        &lt;!&ndash;怎么认证？？&ndash;&gt;
        <el-card shadow="hover">邮箱认证</el-card>
      </div>-->
      <div v-on:click="modifyPwd">
        <el-card shadow="hover">修改密码</el-card>
      </div>
      <div v-on:click="modifyName">
        <el-card shadow="hover">修改昵称</el-card>
      </div>
      <div  v-on:click="logout">
        <el-card shadow="hover">退出登录</el-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyMain",
  data () {
    return {
      username: this.$store.state.user.username,
      email:this.$store.state.user.email,
      activeName: 'second',
      responseResult: []
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    logout(){
      var _this = this;
      console.log("====退出登录前====\n")
      console.log(_this.$store.getters.getUser)
      _this.$store.commit('REMOVE_INFO')
      _this.$router.replace({path: '/login'})

      console.log("====退出登录后====\n")
      console.log(_this.$store.getters.getUser)
    },
    modifyPwd(){
      this.$router.replace({path: '/pwdEdit'})
    },
    modifyName(){
      this.$router.replace({path: '/nameEdit'})
    },

  }
}
</script>

<style scoped>
.el-card{
  margin: 10px;
}
</style>