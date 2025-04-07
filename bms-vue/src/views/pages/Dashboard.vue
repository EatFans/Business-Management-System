<!-- 仪表片主要内容部分 -->
<template>
 <div class="dashboard-container">
   <!-- 顶部部分，标题内容 -->
   <div class="index-section">

     <div class="page-title">
       <p>欢迎回来！{{username}}！</p>
       <h1>仪表盘</h1>
     </div>

     <div class="date-info">

     </div>
   </div>

   <div class="first-section">

     <!-- 统计栏 -->
     <div class="statistics-box">
        <div class="statistics-box-header">
          <h2>统计</h2>

        </div>
     </div>

     <!-- 重要事件框 -->
     <div class="important-event-box">
      <div class="important-events-box-header">
        <h2>重要事件</h2>

        <div class="look-all">
          <a href="#">查看全部</a>
          <i class='bx bxs-chevron-right'></i>
        </div>
      </div>

       <!-- 重要事件框的主体部分 -->
       <div class="important-events-box-body">
          <ImportantEventMenu />
       </div>
     </div>
   </div>
 </div>
</template>

<script>
import ImportantEventMenu from "@/layout/components/ImportantEventMenu.vue";

export default {
  name: "Dashboard",
  components: {
    ImportantEventMenu
  },
  data(){
    return {
      username: 'EatFan',
    }
  }
}
</script>

<style scoped>
.dashboard-container {
  width: 100%;
  height: 100%;
}

.index-section {
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
}

/* 标题部分 */
.page-title {
  margin-left: 25px;
}

.page-title p{
  font-size: 14px;
  color: #78818e;
}

.page-title h1 {
  font-size: 36px;
  font-weight: 400;
}

/* 第一部分 */
.first-section {
  width: 100%;
  height: 350px;
  margin-top: 10px;
  display: flex;
  flex-direction: row;
}

/* 统计栏 */
.statistics-box {
  width: 75%;
  min-width: 600px;
  height: 100%;
  margin-left: 25px;
  margin-right: 10px;
  border-radius: 20px;
  background: #fff;
}

.statistics-box-header {
}

.statistics-box-header h2{
  font-size: 20px;
  margin-left: 30px;
  margin-top: 20px;
  font-weight: 400;
}

/* 重要事件 */
.important-event-box {
  width: calc(100% - 75% - 50px - 25px);
  min-width: 330px;
  height: 100%;
  background: #fff;
  margin-right: 20px;
  margin-left: 10px;
  border-radius: 20px;
}

.important-events-box-header {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-left: 30px;
  margin-top: 20px;
  justify-content: space-between;
}

.important-events-box-header h2{
  font-size: 20px;
  font-weight: 400;
}

.important-events-box-body {
  width: 100%;
  height: 100%;
}

/* 查看更多样式 */
.look-all {
  margin-right: 40px;
  display: flex;
  align-items: center;
}

.look-all a {
  text-decoration: none;
  color: #448af6;
}

.look-all i {
  color: #448af6;
  margin-left: 5px;
}
</style>