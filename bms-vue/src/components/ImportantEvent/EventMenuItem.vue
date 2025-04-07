<!-- 重要事件菜单物品组件样式 -->
<template>
  <div class="important-event-menu-item">
      <div class="color-board" :class="item.color" ></div>
      <div class="important-event-menu-item-content">
        <div class="important-event-menu-item-data">
          <div class="text-box">
            <p>{{item.text}}</p>
          </div>
          <div class="time-box">
            <p>{{item.day}} | {{item.time}}</p>
          </div>
        </div>

        <div class="important-event-menu-item-info">
          <div class="spend-time-box">
            <i class='bx bxs-time' ></i>
            <p>{{item.spendTime}}</p>
          </div>
        </div>
      </div>

  </div>
</template>

<script>
export default {
  name: "EventMenuItem",
  data() {
    return {
      item: {
        text: '准备开始新的项目，项目负责人为EatFan范德萨范德萨范德萨范德萨范德萨发撒放到沙发的',
        day: '今天',
        time: '12:00',
        spendTime: '2h',
        color: 'pink',
      }
    }
  },
  methods: {

  }
}
</script>


<style scoped>
.important-event-menu-item {
  height: 80px;
  width: 300px;
  margin-top: 10px;
  margin-bottom: 5px;
  display: flex;
  flex-direction: row;
}



.important-event-menu-item-content {
  width: 100%;
  height: 100%;
  padding-left: 10px;
  display: flex;
  flex-direction: row;
}

.text-box {
  width: 70%;
  height: 40px;
  margin-top: 10px;
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  display:-webkit-box;
  -webkit-box-orient:vertical;
}

.time-box {
  height: 15px;
  font-size: 12px;
  margin-top: 10px;
  color: #78818e;
}

.important-event-menu-item-info {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: end;
  justify-content: center;

}

/* 花费时间标签 */
.spend-time-box {
  display: flex;
  height: 25px;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background: #eaeff5;
  padding-left: 10px;
  padding-right: 10px;
  border-radius: 5px;
  margin-bottom: 5px;
}

.spend-time-box i{
  margin-right: 5px;
  color: #78818e;
}

/* 颜色板样式 */
.color-board {
  width: 5px;
  height: 100%;
  background: #b0b5b6;
  border-radius: 10px;
}

/* 颜色版各种颜色样式的定义 */
.color-board.red{
  background: red;
}

.color-board.green {
  background: green;
}

.color-board.blue {
  background: blue;
}

.color-board.orange {
  background: orange;
}

.color-board.pink{
  background: pink;
}

.color-board.purple{
  background: mediumpurple;
}
</style>