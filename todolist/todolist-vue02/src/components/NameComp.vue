<template>
  <div>
    <el-form :model="nameForm" label-width="80px">
      <el-form-item label="昵称" prop="nameForm">
        <el-input v-model="nameForm.newName" placeholder="请输入昵称" style="width:300px;"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit()">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "NameComp",
  data(){
    return {
      nameForm:{
        id:this.$store.state.user.id,
        newName:this.$store.state.user.username,
      }
    }
  },
  methods:{
    onSubmit(){
      const _this=this;
      if(_this.nameForm.newName !== this.$store.state.user.username) {
        _this.$axios
            .post("./nameEdit", _this.nameForm)
            .then(res => {
              console.log("修改昵称返回数据")
              console.log(res)
              if (res.data.code == 200) {
                this.$store.commit('setName', _this.nameForm.newName)
                alert('昵称修改成功!');
                this.$router.replace({path: '/my'})
              } else {
                alert(res.data.msg);
              }
            })
            .catch(failRes => {
              alert("==========表单提交出错=========");
            })
      }else{
        this.$router.replace({path: '/my'})
      }
    }

  }
}
</script>

<style scoped>

</style>