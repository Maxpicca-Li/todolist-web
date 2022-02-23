<template>
  <el-form class="login-container" label-position="left" label-width="80px" :model="loginForm" :rules="rules">
<!--  <el-form class="register-container" label-position="left" label-width="80px">-->
<!--    标题-->
    <h3 class="login_title">TodoList 系统登录</h3>
<!--    邮箱-->
    <el-form-item  label="用户名：" prop="username">
      <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
    </el-form-item>
<!--    密码-->
    <el-form-item label="密码：" prop="password">
      <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
    </el-form-item>
<!--    登录按钮-->
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">登录</el-button>
    </el-form-item>
<!--    超链接-->
    <el-form-item style="width: 100%">
      <el-link type="primary" :href="'/register'">没有账号？立即注册</el-link>
    </el-form-item>
  </el-form>
</template>


<script>
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',  // TODO  记得之后省略该字段
        password: ''
      },
      rules:{
        username:[
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      // console.log("hello"+this.$store.state)
      var _this=this
      this.$axios
          .post('/login', this.loginForm)
          .then(res => {
            console.log("====后端返回数据====")
            console.log(res.data);
            if (res.data.code === 200) {
              // 保存响应数据传递给store
              _this.$store.commit('login',res.data.data)

              /* // 方法二
              const token = res.headers['authorization']
              const userInfo = res.data.data
              _this.$store.commit('SET_TOKEN', token)
              _this.$store.commit('SET_USERINFO', userInfo)*/
              // TODO 打印获取当前store用户
              console.log("====当前store用户====\n")
              console.log(_this.$store.getters.getUser)

              alert('登录成功!');
              var path = this.$route.query.redirect
              // this.$router.replace({path: '/index'})
              this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
            }else{
              alert(res.data.msg);
            }
          })
          .catch(failRes => {
            // TODO DEBUG
            alert("登录出错")
          })
    }
  }
}
</script>
<style>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

</style>
