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

     </div>

     <!-- 重要事件框 -->
     <div class="important-event-box">

     </div>
   </div>
 </div>
</template>

<script>
export default {
  name: "Dashboard",
  components: {
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
  //background: #fff;
  display: flex;
  align-items: center;
  //border: red solid 1px;
}

/* 标题部分 */
.page-title {
  //border: red solid 1px;
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
  //border: red solid 1px;
  margin-top: 10px;
  display: flex;
  flex-direction: row;
}

/* 统计栏 */
.statistics-box {
  width: 75%;
  height: 100%;
  margin-left: 25px;
  margin-right: 10px;
  border-radius: 20px;
  background: #fff;
}

.important-event-box {
  width: calc(100% - 75% - 50px - 25px);
  height: 100%;
  background: #fff;
  margin-right: 20px;
  margin-left: 10px;
  border-radius: 20px;
}
</style>