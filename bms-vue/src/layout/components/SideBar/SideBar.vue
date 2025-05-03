<!-- 布局组件 侧边栏组件 -->
<template>
	<div class="side-bar" :class="{ close: !isOpenSideBar }">
    <!-- 侧边栏顶部标题物品 -->
    <div class="side-bar-top">
      <div class="logo-avatar">
        <h3>OA</h3>
      </div>
      <h1 class="system-name">企业管理系统</h1>
    </div>

    <!-- 侧边栏中部菜单部分 -->
    <div class="side-bar-middle">
      <!-- 菜单部分 -->
      <Menu :itemList="menuItemList"/>
    </div>

    <!-- 侧边栏底部部分 -->
    <div class="side-bar-bottom">
      <div class="exit-login-button" @click="logout">
        <i class="bx bxs-exit" />
        <p>退出登录</p>
      </div>
    </div>
  </div>
</template>

<script>
	import Menu from "@/layout/components/SideBar/Menu.vue";
  import {logout} from "@/api/login.js";

  export default {
		name: 'SideBar',
		components: {Menu},
    props: {
      menuItemList: Array,
      required: true
    },
		data() {
			return {
        // 是否打开侧边栏
        isOpenSideBar: true,


			}
		},
    methods: {
      logout() {
        logout().then(res  => {
          if (res.code === 200) {
            localStorage.removeItem("loginToken");
            this.$router.push('/login');
          }
        }).catch(error => {
          localStorage.removeItem("loginToken");
          this.$router.push('/login');
        })


        console.log(localStorage.getItem("loginToken"));
      }
    }
	}
</script>

<style scoped>
/* 侧边栏整体样式 */
.side-bar {
  width: 250px;
  height: 99%;
  background: #fff;
  border-radius: 20px;
  margin-left: 5px;
}

/* 侧边栏顶部部分 */
.side-bar-top {
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
}

.logo-avatar {
  width: 45px;
  height: 45px;
  border-radius: 10px;
  background: rgba(10, 189, 243, 0.65);
  margin-left: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-avatar h3 {
  font-size: 25px;
  color: #fff;
}

.system-name {
  margin-left: 5px;
  font-size: 18px;
  font-weight: bold;
  color: #78818e;
}

.side-bar-top p {
  color: #78818e;
  font-size: 12px;
}



/* 侧边栏中间部分 */
.side-bar-middle {
  width: 100%;
  height: calc(100% - 100px - 200px);
  /*border: white solid 1px;*/
}

/* 侧边栏底部部分 */
.side-bar-bottom {
  width: 100%;
  height: 200px;
  display: flex;
  align-items: end;
}

/* 退出按钮样式 */
.exit-login-button {
  display: flex;
  flex-direction: row;
  height: 50px;
  width: 100%;
  align-items: center;
}

.exit-login-button i{
  font-size: 25px;
  color: #78818e;
  margin-left: 25px;
}

.exit-login-button p {
  font-size: 15px;
  color: #78818e;
  margin-left: 10px;
}

.exit-login-button:hover i, .exit-login-button:hover p {
  color: #2f7cee;
}
</style>