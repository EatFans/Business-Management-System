<template>
  <div class="menu-container">
    <MenuItem v-for="(item,index) in itemList" :key="item.id" :item="item"/>

  </div>
</template>

<script>
import MenuItem from "@/layout/components/SideBar/MenuItem.vue";

export default {
  name: 'Menu',
  components: {MenuItem},
  props:{
    itemList:Array,
    required: true
  },
  data() {
    return {

    }
  },
  // 当菜单组件被创建时候，就去给服务器发送请求，获取数据进行渲染
  created() {
    this.getMenuItem();
  },
  methods:{
    // 获取用户可以使用的菜单选项
    getMenuItem(){
      //TODO: ....
    }
  }
}
</script>

<style scoped>
.menu-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
}
</style>