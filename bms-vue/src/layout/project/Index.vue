<!-- 项目页面布局三级路由索引组件 -->
<template>
  <div class="project-index-main-content">
    <div class="project-index-header">
      <h2>任务列表</h2>

      .
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  components: {

  },
  created() {
    this.getProjectCode();
  },
  computed: {
    projectCode(){
      return this.$route.params.projectCode;
    }
  },
  data() {
    return {
    }
  },
  methods: {
    getProjectCode(){
      console.log(this.projectCode);
    }
  },

}
</script>


<style scoped>
.project-index-main-content{
  width: 96%;
  height: 100%;
  border: red solid 1px;
  margin-left: 20px;
}

.project-index-header {
  width: 100%;
  height: 60px;
  border: green solid 1px;
  display: flex;
  justify-content: space-between;
}

.project-index-header h2 {
  font-weight: 400;
  margin-left: 20px;
  margin-top: 5px;
}
</style>