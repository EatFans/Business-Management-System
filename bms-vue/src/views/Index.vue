<template>
  <div class="index-page-container">
    <SideBar :menuItemList="itemList"/>

    <!-- index页面主要内容 -->
    <div class="index-page-content">
      <HeaderBox />
    </div>


  </div>
</template>

<script>
import SideBar from "@/layout/components/SideBar/SideBar.vue";
import HeaderBox from "@/layout/components/HeaderBox.vue";

export default {
  name: 'Index',
  components: {
    SideBar,
    HeaderBox
  },
  data() {
    return {
      itemList: [
        {
          icon: 'bx bx-home-alt',
          text: '仪表盘',
          link: '/dashboard',
          isHighlight: true
        },
        {
          icon: 'bx bx-user',
          text: '项目',
          link: '/project',
          isHighlight: false
        },
        {
          icon: 'bx bx-dock-bottom',
          text: '日历',
          link: '/calendar',
          isHighlight: false
        },
        {
          icon: 'bx bx-buildings',
          text: '假期',
          link: '/position',
          isHighlight: false
        },
        {
          icon: 'bx bx-calendar-check',
          text: '考勤',
          link: '/home',
          isHighlight: false
        },
        {
          icon: 'bx bx-task',
          text: '消息',
          link: '/home',
          isHighlight: false
        },
      ]
    }
  },
  methods: {

  }
}
</script>

<style scoped>
.index-page-content {
  width: 100%;
  height: 100vh;
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