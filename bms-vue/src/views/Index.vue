<template>
  <div class="index-page-container">
    <SideBar />

    <!-- index页面主要内容 -->
    <div class="index-page-content">

    </div>
  </div>
</template>

<script>
import SideBar from "@/layout/components/SideBar/SideBar.vue";

export default {
  name: 'Index',
  components: {
    SideBar

  },
  data() {
    return {
		
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