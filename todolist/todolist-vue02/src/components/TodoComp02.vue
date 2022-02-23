<template>
<div>
  <el-card shadow="hover" v-for="(item,i) in todoList" :key=i style="margin: 10px" :class="setCardName(item)">
    <el-checkbox v-model="item.status" @change="doneTask(todoList[i])">
     <!-- <div>
        <div>
          <div style="font-size: 15px;color: #333333">{{ item.title }}</div>
          <div style="font-size: 3px;color: #cac6c6">{{item.endTime}} &nbsp;&nbsp;&nbsp; {{item.remark}}</div>
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)" align="right">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)" align="right">删除</el-button>
        </div>
      </div>-->
      <el-container >
        <el-main style="width: 700px;height: 80px">
          <div style="font-size: 15px;color: #000;">{{ item.title }}</div>
          <div style="font-size: 3px;color: gray">{{item.endTime | formatDate}} &nbsp;&nbsp;&nbsp; {{item.remark}}</div>
        </el-main>
        <el-aside width="130px" style="left: 10px">
          <el-button size="mini" @click="handleEdit(todoList[i])">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(todoList[i])">删除</el-button>
        </el-aside>
      </el-container>
    </el-checkbox>
  </el-card>
</div>
</template>

<script>
export default {
  name: "TodoComp02",
  data(){
    return{
      todoGetDto:{
        userid:this.$store.state.user.id,
      },
      todoList:[
        /*{status:false,title:"取快递",endTime:new Date().toLocaleString(),remark:"吃饭"},
        {status:true,title:"上体育课",endTime:new Date().toLocaleString(),remark:"喝水"},
        {status:false,title:"打扫房间",endTime:new Date().toLocaleString(),remark:"没关系"},*/
      ]
    }
  },
  methods:{
    handleEdit(todoList){
      this.$router.push({
        path:'/todoedit',
        query:{
          info:todoList
        }
      })
    },
    handleDelete(todo){
      const _this=this;
      console.log("删除测试")
      _this.$axios
          .post("/todoDelete",todo)
          .then(res=>{
            console.log(res)
            alert(res.data.msg)
            location.reload()
          })
    },
    doneTask(todo){
      const _this=this;
      console.log("=====当前任务点击事件====")
      console.log(todo.status)
      _this.$axios
          .post("/todoDone",todo)
          .then(res=>{
          })
    },
    setCardName(item) {
      console.log("===设置背景颜色===")
      if (item.status === false) {
        switch (item.priority){
          case "2": return 'warning-card';
          case "1": return 'important-card';
        }
      }else{
        return 'done-card';
      }
    }
  },
  created() {
    const _this=this;
    if(this.$store.state.user){
      console.log(typeof this.$store.state.user.id)
      console.log(this.$store.state.user.id)
      this.$axios.get("/getTodo", {params:{userid:_this.$store.state.user.id}})
      // this.$axios.post("/getTodo", _this.todoGetDto)
          .then(res=>{
            console.log(res)
            if(res.data.code === 200){
              this.todoList = res.data.data;
              console.log("前端收到数据")
              console.log(this.todoList);
            }

          })
    }
  }
}
</script>

<style scoped>
.mcard{
  margin: 10px;
}
el-checkbox{
  color: #333333;
}

.warning-card{
  background: orange;
  /*color: coral;*/
}
.important-card{
  background: yellow;
  /*color: green;*/
}
.nomal-card{

}
.done-card{
  background: #eaeaea;
  /*color: #cac6c6;*/
}
</style>