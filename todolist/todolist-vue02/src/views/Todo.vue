<template>
<div>
<!--<TodoComp></TodoComp>-->
  <TodoComp02></TodoComp02>
  <div>
    <i class="el-icon-circle-plus" @click="toEdit" ></i>
  </div>

</div>
</template>

<script>
import TodoComp from "@/components/TodoComp";
import TodoComp02 from "@/components/TodoComp02";
export default {
  name: "Todo",
  components:{TodoComp,TodoComp02},
  methods:{
    toEdit(){
      this.$router.replace({path: '/todoedit'})
    },
  }
}
</script>

<style scoped>
.el-icon-circle-plus{
  font-size: 50px;
  color: #42b983;
  float:right;
}

.el-icon-circle-plus:hover{
  -webkit-box-shadow: #ccc 10px 10px 10px;
  -moz-box-shadow: #ccc 10px 10px 10px;
  box-shadow: #ccc 10px 10px 10px;
}
</style>