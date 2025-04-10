<!-- 项目列表侧边栏菜单容器 -->
<template>
  <div class="project-list-sidebar-menu">
    <ProjectListSideBarItem />
  </div>
</template>

<script>

import ProjectListSideBarItem from "@/components/project/ProjectListSideBarItem.vue";

export default {
  name: "ProjectListSideBarMenu",
  components: {
    ProjectListSideBarItem
  },
  data() {
    return {

    }
  },

}
</script>


<style scoped>
.project-list-sidebar-menu {
  width: 100%;
  height: 100%;
}

</style>