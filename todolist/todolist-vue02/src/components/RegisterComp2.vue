<template>
  <div>
    <el-form class="register-container" label-position="left" label-width="80px"
             :model="registerForm" :rules="rules" :ref="registerForm">
      <!--标题-->
      <h3 class="register_title">TodoList 系统注册</h3>
      <!--邮箱-->
      <el-form-item label="邮箱：" prop="email">
        <el-input type="text" v-model="registerForm.email" auto-complete="off" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <!--昵称-->
      <el-form-item label="昵称：" prop="username">
        <el-input type="text" v-model="registerForm.username" auto-complete="off" placeholder="请输入昵称"></el-input>
      </el-form-item>
      <!--密码-->
      <el-form-item label="密码：" prop="password">
        <el-input type="password" v-model="registerForm.password" auto-complete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <!--注册-->
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="register()">注册</el-button>
      </el-form-item>
      <!--超链接-->
      <el-form-item style="width: 100%">
        <el-link type="primary" :href="'/login'">已有账号？立即登录</el-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  name: "RegisterComp2",
  data () {
    const validateEmail = (rule, value, callback) => {
      if (value !== '') {
        if (value.length > 50) {
          callback(new Error('邮箱字符长度不能超过50个字符'));
        }
        var reg = /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}/;
        // var re=new RegExp(reg);
        if (!reg.test(value)) {
          callback(new Error('邮箱格式不正确'));
        }
        return callback()
      } else {
        callback(new Error('请输入邮箱'));
      }
    };
    return {
      registerForm: {
        email:'',
        username: '',  // TODO  记得之后省略该字段
        password: '',
      },
      rules:{
        email:[
          // {required: true,validator: validateEmail,trigger: 'blur'},
          {required: true,message: '请输入邮箱',trigger: 'blur'},
          {validator: validateEmail, trigger: 'blur'}
        ],
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      },
      responseResult: []
    }
  },
  methods:{
    register(){
      const _this = this;
      console.log("hello"+this.$store.state)
      // TODO 提交按钮逻辑前端验证
      // this.$ref.registerForm.validate((valid) => {
      //   if (valid) {
      _this.$axios
          .post('/register', _this.registerForm)  // 传给后端，后端链接已在main.js中设置了
          .then(res => {
            console.log(res.data);
            console.log("==========已传给后端=========")
            if (res.data.code === 200) {
              _this.$store.commit('login',_this.registerForm)
              alert('注册成功!');
              const path = this.$route.query.redirect;
              // TODO DEBUG
              console.log("==========数据正确，页面跳转=========")
              // this.$router.replace({path: '/login'})
              this.$router.replace({path: path === '/' || path === undefined ? '/login' : path})
            }else{
              alert(res.data.msg);
            }
          })
          .catch(failRes => {
            alert("==========表单提交出错=========")
          })
      //   }else{
      //     alert('注册失败!');
      //     return false;
      //   }
      // });
    }
  }
}
</script>

<style scoped>
.register-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.register_title {
  margin: 0 auto;
  text-align: center;
  color: #505458;
}
</style>