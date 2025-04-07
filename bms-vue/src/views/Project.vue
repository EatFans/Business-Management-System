<!-- 项目界面 -->
<template>
  <div class="project-page-container">
    <SideBar :menuItemList="itemList"/>

    <!-- index页面主要内容 -->
    <div class="project-page-content">
    </div>
  </div>
</template>

<script>
import SideBar from "@/layout/components/SideBar/SideBar.vue";

export default {
  name: "Project",
  components: {SideBar},
  data() {
    return {
      itemList: [
        {
          icon: 'bx bx-home-alt',
          text: '仪表盘',
          link: '/dashboard',
          isHighlight: false
        },
        {
          icon: 'bx bx-user',
          text: '项目',
          link: '/project',
          isHighlight: true,
        },
        {
          icon: 'bx bx-dock-bottom',
          text: '日历',
          link: '/department',
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

  }


}
</script>


<style scoped>
.project-page-content {
  width: 100%;
  height: 100vh;
}

.project-page-container {
  height: 100vh;
  width: 100%;
  background: #eaeff5;
  padding: 5px;
  display: flex;
  flex-direction: row;
}

</style>