<template>
  <!--
  :default-active="'/index'" 当前激活的菜单为/index，即默认停留菜单
  -->
  <el-menu
      :default-active="'/index'"
      router
      mode="horizontal"
      background-color="white"
      text-color="#222"
      active-text-color="red">

    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>
<!--    <a href="#nowhere" style="color: #222;float: right;padding: 20px;">更多功能</a>
    <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>-->
    <span style="position: absolute;padding-top: 10px;right: 43%;font-size: 30px;font-weight: bold">TodoList</span>
  </el-menu>

</template>

<script>
export default {
  name: "NavMenu",
  data () {
    return {
      navList: [
        {name: '/index', navItem: '待办'},
        {name: '/my', navItem: '我的'},
      ]
    }
  }
}
</script>

<style scoped>
a{
  text-decoration: none;
}

span {
  pointer-events: none;
}
</style>