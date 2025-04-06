<template>
  <div class="index-page-container">
    <SideBar />

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
.index-page-container {
  height: 100vh;
  width: 100%;
  background: #e9e9e9;
  padding: 5px;
}

</style>