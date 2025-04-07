<!-- 日历页面 -->
<template>
  <div class="calendar-page-container">
    <SideBar :menuItemList="itemList"/>

    <!-- index页面主要内容 -->
    <div class="calendar-page-content">
    </div>
  </div>
</template>

<script>
import SideBar from "@/layout/components/SideBar/SideBar.vue";

export default {
  name: "Calendar",
  components: {SideBar}
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