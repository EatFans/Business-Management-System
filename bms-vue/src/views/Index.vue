<template>
  <div class="index-page-container">
    <SideBar :menuItemList="itemList"/>
    <!-- index页面主要内容 -->
    <div class="index-page-content">
      <HeaderBox />
      <!-- 二级路由切换不同内容 -->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import SideBar from "@/layout/components/SideBar/SideBar.vue";
import HeaderBox from "@/layout/components/HeaderBox.vue";
import {getMenuList} from "@/api/system/menu.js";

export default {
  name: 'Index',
  components: {
    SideBar,
    HeaderBox,
  },
  data() {
    return {
      itemList: []
    }
  },
  methods: {
    /**
     * 获取菜单物品
     */
    getMenus(){
      getMenuList().then(res=>{
        // console.log(res)
        if (res.code == 200){
          this.itemList = res.menus

        } else {
          this.$router.push('/login');
        }
      }).catch(err=>{
        console.log(err)
      })
    }
  },
  created() {
    this.getMenus();
  }
}
</script>

<style scoped>
.index-page-content {
  width: 100%;
  height: 100%;
}

.index-page-container {
  height: 100vh;
  width: 100%;
  background: #eaeff5;
  padding: 5px;
  display: flex;
  flex-direction: row;
}

</style>