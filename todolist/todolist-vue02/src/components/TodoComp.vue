<template>
  <!--  <a href="/todoedit">新增代办</a>-->
  <!--  1、选择多行数据时使用 Checkbox-->
  <!--  2、排序-->
  <!--  3、自定义列模板-->
  <!--  4、自定义表头-->
  <div>
      <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55"></el-table-column>

<!--
        <el-table-column label="日期" width="120">
          <template slot-scope="scope">{{ scope.row.title }}</template>
        </el-table-column>
-->

        <el-table-column prop="title" label="事项"></el-table-column>
        <el-table-column prop="endTime" value-format="timestamp" label="截止时间"></el-table-column>
        <el-table-column prop="remark" label="备注" ></el-table-column>
<!--        <el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>-->
      </el-table>
      <!--<el-table
          :data="tableData.filter(data => !search || data.date.toLowerCase().includes(search.toLowerCase()))"
          style="width: 100%">
        <el-table-column
            label="Date"
            prop="date">
        </el-table-column>
        <el-table-column
            label="Name"
            prop="name">
        </el-table-column>
        <el-table-column
            align="right">
          <template slot="header" slot-scope="scope">
            <el-input
                v-model="search"
                size="mini"
                placeholder="输入关键字搜索"/>
          </template>
          <template slot-scope="scope">
            <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>-->

  </div>
</template>

<script>
export default {
  name: "TodoComp",
  data() {
    return {
      showTabCol: false,
      todoForm: [{
        title: '吃饭',
        remark: '与香草肉丝',
        endTime: new Date().toLocaleString(),  //获取日期与时间'
        checked: false,
      }],
      tableData: [],
    }
  },
  methods:{
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

  },

  created() {
    const _this=this;
    if(this.$store.state.user){
      console.log("进入");
      console.log(typeof this.$store.state.user.id)
      console.log(this.$store.state.user.id)
      this.$axios.get("/getTodo", this.$store.state.user.id)
          .then(res=>{
            console.log(res)
            if(res.data.code === 200){
              console.log(res.data.data)
              this.tableData = res.data.data;
              console.log(this.tableData);
            }

          })
    }
  }


}
</script>

<style scoped>

</style>