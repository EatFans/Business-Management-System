<!-- 项目列表侧边栏菜单容器 -->
<template>
  <div class="project-list-sidebar-menu">
    <ProjectListSideBarItem v-for="(item, index) in itemList" :key="item" :item="item" />
  </div>
</template>

<script>

import ProjectListSideBarItem from "@/components/project/ProjectListSideBarItem.vue";

export default {
  name: "ProjectListSideBarMenu",
  components: {
    ProjectListSideBarItem
  },
  props: {
    itemList: {
      type: Array,
      required: true,
    }
  }

}
</script>


<style scoped>
.project-list-sidebar-menu {
  width: 100%;
  height: 100%;
  overflow-y: auto;
}

</style>