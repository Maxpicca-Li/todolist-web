<template>
  <div>
    <el-form :model="pwdForm" :rules="pwdRules" :refs="pwdForm" label-width="80px">
      <el-form-item label="旧密码" prop="oldPwd">
        <el-input v-model="pwdForm.oldPwd" type="password" placeholder="请输入旧密码"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPwd">
        <el-input v-model="pwdForm.newPwd" type="password" placeholder="请输入新密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="renewPwd">
        <el-input v-model="pwdForm.renewPwd" type="password" placeholder="请再次输入新密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('pwdForm')">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>


export default {
  name: "Password",
  data() {
    const checkRenewPwd = (rule, value, cb) => {
      if (value != '') {
        if (value !== this.pwdForm.newPwd) {
          cb(new Error('两次密码输入不一致'));
        } else {
          return cb()
        }
      } else {
        cb(new Error('请再次输入新密码'));
      }
    };
    return {
      pwdForm: {
        id: '',
        oldPwd:'',
        newPwd:'',
        renewPwd:'',
      },
      pwdRules:{
        oldPwd:[
            {required: true,message: '请输入旧密码',trigger: 'blur'},
        ],
        newPwd:[
            {required: true,message: '请输入新密码',trigger: 'blur'},
        ],
        renewPwd:[
            {required: true,validator: checkRenewPwd,trigger: 'blur'},
        ],
      },
      responseResult: []
    };
  },
  methods: {
    onSubmit(FormName){
      const _this = this;
      _this.pwdForm.id = this.$store.state.user.id;
      if (_this.pwdForm.renewPwd === _this.pwdForm.newPwd){
        console.log("提交的数据====>")
        console.log(_this.pwdForm)
        this.$axios
            .post('/pwdEdit',_this.pwdForm)  // 传给后端，后端链接已在main.js中设置了
            .then(res => {
              console.log("返回的数据====>")
              console.log(res.data);
              if (res.data.code === 200) {
                alert('修改成功!');
                this.$router.replace({path: '/index'})
              }else{
                alert(res.data.msg);
              }
            })
            .catch(failRes => {
              alert("==========表单提交出错=========");
            })
      }else{
        alert("两次输入密码不一致");
      }

    },
  },
/*
  created() {
    this.SetForm(this.$route.query.info)
  }
*/

}
</script>

<style scoped>
el-form{
  margin: 10px;
}
el-input{
  width: 300px;
}
</style>