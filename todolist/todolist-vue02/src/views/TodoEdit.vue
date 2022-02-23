<template>
  <div>
    <div>
      <el-page-header @back="goBack" content="编辑待办" style="margin: 10px"></el-page-header>
      <hr/>
    </div>

    <el-form ref="form" :model="todoform" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="todoform.title" placeholder="请输入标题" style="width: 500px"></el-input>
      </el-form-item>

      <el-form-item label="开始时间">
        <el-date-picker v-model="todoform.startTime" type="datetime" placeholder="请选择任务开始时间" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
      </el-form-item>

      <el-form-item label="截止时间">
        <el-date-picker v-model="todoform.endTime" type="datetime" placeholder="请选择任务截止时间" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
      </el-form-item>

      <el-form-item label="优先级">
        <el-select v-model="todoform.priority" placeholder="请选择任务优先级">
          <el-option label="0" value="0"></el-option>
          <el-option label="1" value="1"></el-option>
          <el-option label="2" value="2"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="备注">
        <el-input type="textarea" v-model="todoform.remark" placeholder="请输入任务备注" style="width: 500px"></el-input>
      </el-form-item>

      <!--附件-->
      <!--<el-form-item label="附件">
        <el-upload
            action="https://jsonplaceholder.typicode.com/posts/"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </el-form-item>-->

      <!--其他-->
      <!--<el-form-item label="开始时间">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="选择日期" v-model="todoform.startTime" style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-time-picker placeholder="选择时间" v-model="todoform.endTime" style="width: 100%;"></el-time-picker>
        </el-col>
      </el-form-item>-->
      <!-- <el-form-item label="即时配送">
        <el-switch v-model="todoform.delivery"></el-switch>
      </el-form-item>
      <el-form-item label="活动性质">
        <el-checkbox-group v-model="todoform.type">
          <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
          <el-checkbox label="地推活动" name="type"></el-checkbox>
          <el-checkbox label="线下主题活动" name="type"></el-checkbox>
          <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="特殊资源">
        <el-radio-group v-model="todoform.resource">
          <el-radio label="线上品牌商赞助"></el-radio>
          <el-radio label="线下场地免费"></el-radio>
        </el-radio-group>
      </el-form-item>-->

      <el-form-item>
        <el-button type="primary" @click="onSubmit('todoform')">保存</el-button>
        <el-button @click="goBack">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "TodoEdit",
  data() {
    return {
      todoform: {
        id:'',
        userid:'',
        title: '',
        startTime: '',
        endTime: '',
        remark: '',
        priority:'',
        // attachments: '',  // TODO 附件可以先搁置处理
        // status:'',
      },
    }
  },
  methods: {
    onSubmit(FormName){
      const _this = this;
      _this.todoform.userid=_this.$store.state.user.id;
      console.log(_this.todoform)
      this.$axios
          .post('/todoadd', _this.todoform)  // 传给后端，后端链接已在main.js中设置了
          .then(succRes => {
            console.log(succRes.data);
            if (succRes.data.code === 200) {
              // _this.$store.commit('login',_this.todoform)
              alert('保存成功!');
              var path = this.$route.query.redirect
              this.$router.replace({path: '/index'})
            }else{
              alert(succRes.data.msg);
            }
          })
          .catch(failRes => {
            alert("==========表单提交出错=========");
          })
    },
    goBack() {
      console.log('go back');
      this.$router.replace({path: '/index'})
    },
    SetForm(form){
      const _this=this
      console.log("跳转到编辑页面的数据")
      console.log(form)
      _this.todoform.title = form.title;
      _this.todoform.startTime = form.startTime
      _this.todoform.endTime = form.endTime
      _this.todoform.remark = form.remark
      _this.todoform.priority = form.priority
      _this.todoform.id = form.id
    }
  },
  created() {
    this.SetForm(this.$route.query.info)
  }

}
</script>

<style scoped>

</style>