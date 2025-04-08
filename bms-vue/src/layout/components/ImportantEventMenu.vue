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
  data() {
    return {
      itemList: [
        {
          text: '准备开始新的项目，项目负责人为EatFan',
          day: '今天',
          time: '12:00',
          spendTime: '2h',
          eventStatus: 'medium',  // 高、中、低，high、medium、low
          color: 'pink',
        },
        {
          text: '小明的生日',
          day: '明天',
          time: '12:00',
          spendTime: '0.5h',
          eventStatus: 'low',
          color: 'blue',
        },
        {
          text: '企业管理系统项目开发',
          day: '今天',
          time: '7:00',
          spendTime: '4h',
          eventStatus: 'low',
          color: 'orange'
        }
      ]
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