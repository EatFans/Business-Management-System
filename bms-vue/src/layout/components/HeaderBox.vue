<!-- 头部盒子 -->
<template>
 <div class="header-box">
   <!-- 搜索框 -->
   <div class="search-input-box">
     <div class="search-button">
       <i class="bx bx-search-alt"></i>
     </div>
     <input type="text" placeholder="搜索"/>
   </div>

   <!-- 可操作容器 -->
   <div class="action-container">
     <!-- 通知物品 -->
     <div class="notify-item">
      <i class="bx bx-bell"></i>
     </div>

     <div class="user-profile">
       <div class="user-profile-content">
         <div class="user-avatar">
           <img :src="user.avatar" alt="">
         </div>

         <div class="user-nick-name">
           <p>{{user.nickName}}</p>
         </div>
       </div>

       <i class="bx bxs-chevron-down"></i>
     </div>

   </div>
 </div>
</template>

<script>
export default {
  name: "HeaderBox",
  components: {},
  created(){
    this.getUserProfile();
  },
  data() {
    return {
      user: {
        avatar: '/image/default_avatar.png',
        nickName: 'EatFan'
      }
    }
  },
  methods: {
    // 获取用户资料
    getUserProfile(){
      // TODO: ...
    }
  }
}
</script>


<style scoped>
/* 顶部栏 */
.header-box {
  width: 100%;
  height: 50px;
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: space-between;
}


/* 搜索框样式 */
.search-input-box {
  width: 20%;
  height: 35px;
  margin-left: 25px;
  background: #fff;
  border-radius: 10px;
  display: flex;
  flex-direction: row;
}

.search-button {
  width: 35px;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.search-button i{
  font-size: 22px;
  color: #78818e;
}

.search-button:hover i{
  color: #2f7cee;
}

.search-input-box input {
  width: calc(100% - 35px);
  height: 100%;
  border: none;
  padding: 0 15px;
  outline: none;
}



/* 用户操作部分框样式 */
.action-container {
  width: calc(100% - 20% - 50%);
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: end;
}

/* 通知物品样式 */
.notify-item {
  width:40px;
  height: 35px;
  border-radius: 13px;
  background: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
}

.notify-item i{
  font-size: 22px;
  color: #78818e;
}

.notify-item:hover i{
  color: #2f7cee;
}

/* 用户资料 */
.user-profile {
  width: 200px;
  height: 35px;
  background: #fff;
  border-radius: 13px;
  margin-left: 20px;
  margin-right: 40px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.user-profile-content{
  display: flex;
  align-items: center;
}

.user-avatar {
  width: 30px;
  height: 30px;
  margin-left: 10px;
}

.user-avatar img {
  width: 100%;
  height: 100%;
}

.user-nick-name {
  height: 100%;
  width: auto;
  display: flex;
  align-items: center;
  color: #78818e;
  margin-left: 10px;
  font-size: 14px;
}

.user-profile i {
  font-size: 18px;
  color: #78818e;
  margin-right: 5px;
}

.user-profile i:hover {
  color: #2f7cee;
}
</style>