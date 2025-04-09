<!-- 重要事件菜单布局组件 -->
<template>
  <div class="important-event-menu">
    <EventMenuItem v-for="(item, index) in itemList" :key="item" :item="item" />

  </div>
</template>


<script>

import EventMenuItem from "@/components/ImportantEvent/EventMenuItem.vue";

export default {
  name: "ImportantEventMenu",
  components: {
    EventMenuItem
  },
  props: {
    itemList: Array,
    required: true
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
.important-event-menu {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
}
</style>