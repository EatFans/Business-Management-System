
<template>
  <div class="project-container">
    <!-- 顶部部分，标题内容 -->
    <div class="index-section">
      <div class="page-title">
        <h1>项目</h1>
      </div>
      <div class="add-project-button">
        <i class="bx bx-plus"></i>
        <p>添加项目</p>
      </div>

    </div>

    <div class="project-body">
      <!-- 项目列表侧边栏 -->
      <div class="project-list-sidebar">
        <div class="project-list-header">
          <p>当前项目</p>
          <i class='bx bxs-chevron-down'></i>
        </div>

        <!-- 项目列表侧边栏核心部分 -->
        <div class="project-list-sidebar-content">
          <ProjectListSideBarMenu />
        </div>
      </div>

      <div class="project-body-main">
        <!-- 项目路径下的三级路由 -->
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import ProjectListSideBarMenu from "@/layout/components/ProjectListSideBarMenu.vue";

export default {
  name: "Project",
  components: {ProjectListSideBarMenu},
  data() {
    return {

    }
  },
  methods: {
    addProject(){
      // TODO: 添加项目请求
    }
  }
}
</script>

<style scoped>
.project-container {
  width: 100%;
  height: 94%;
}

.index-section {
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/* 标题部分 */
.page-title {
  margin-left: 25px;
}


.page-title h1 {
  font-size: 36px;
  font-weight: 400;
}

/* 添加项目按钮样式 */
.add-project-button {
  width: 120px;
  height: 40px;
  border-radius: 15px;
  background: #3f88f6;
  margin-right: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.add-project-button i {
  color: #fff;
  font-size: 20px;
  font-weight: bold;
  margin-right: 5px;
}

.add-project-button p {
  color: #fff;
}

.add-project-button:hover {
  background: #709fe8;
}

/* 项目页面主要内容 */
.project-body {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
}

/* 项目列表 */
.project-list-sidebar{
  width: 300px;
  min-width: 200px;
  height: 89%;
  background: #fff;
  margin-left: 25px;
  margin-bottom: 25px;
  border-radius: 20px;
}

.project-list-header {
  width: 100%;
  height: 60px;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
  display: flex;
  align-items: center;
  border-bottom: #c4c8d0 solid 1px;
}

.project-list-header p{
  margin-left: 25px;
  font-size: 16px;
}

.project-list-header i{
  margin-left: 10px;
  font-size: 20px;
}

.project-list-header:hover p, .project-list-header:hover i {
  color: #2f7cee;
}

.project-list-sidebar-content {
  width: 100%;
  height: 92%;
}

/* 项目界面主要部分 */
.project-body-main {
  width: 100%;
  height: 89%;
  border: red solid 1px;
}

</style>