<!-- 项目页面布局三级路由索引组件 -->
<template>
 <h1>{{projectCode}}</h1>
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

</style>