<!-- 项目侧边栏物品组件 -->
<template>
  <div class="project-list-sidebar-item">
    <div class="project-list-sidebar-item-main">

    </div>

    <div class="color-board" ></div>
  </div>

</template>

<script>
export default {
  name: "ProjectListSideBarItem",
  data() {
    item: {

    }
  }
}
</script>


<style scoped>
.project-list-sidebar-item {
  width: 100%;
  height: 90px;
  margin-top: 10px;
  background: #fff;
  display: flex;
  justify-content: space-between;
}

.project-list-sidebar-item-main {
  width: 92%;
  height: 100%;
  margin-left: 8px;
  border: black solid 1px;
}

.color-board {
  width: 5px;
  height: 100%;
}
</style>