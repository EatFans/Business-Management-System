<!-- 项目侧边栏物品组件 -->
<template>
  <router-link :to="item.link">
    <div class="project-list-sidebar-item">
      <div class="project-list-sidebar-item-main">
        <p>{{item.code}}</p>
        <h3>{{item.name}}</h3>

        <div class="check-details" :class="{display:isActive(item.link)}">
          <router-link :to="item.link">查看详情</router-link>
        </div>
      </div>

      <div class="color-board" ></div>
    </div>
  </router-link>


</template>

<script>
export default {
  name: "ProjectListSideBarItem",
  data() {
    return {
      item: {
        code: 'PN0001235',
        name: '送餐服务系统',
        link: '/project/test',
      }
    }

  },
  methods: {
    isActive(link){
      console.log(link);
      console.log(this.$route.path);
      return this.$route.path === link;
    }
  }
}
</script>


<style scoped>
a {
  text-decoration: none;
  color: black;
}

.project-list-sidebar-item {
  width: 100%;
  height: 90px;
  margin-top: 10px;
  background: #fff;
  display: flex;
  justify-content: space-between;
}

/* 项目列表侧边栏主要内容样式 */
.project-list-sidebar-item-main {
  width: 93%;
  height: 98%;
  margin-left: 8px;
  margin-right: 8px;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-left: 20px;
}

.project-list-sidebar-item-main p {
  font-size: 12px;
  color: #78818e;
  margin-bottom: 3px;
}

.project-list-sidebar-item-main h3 {
  font-size: 16px;
  font-weight: 400;
  margin-bottom: 10px;
}

/* 颜色板样式 */
.color-board {
  width: 5px;
  height: 100%;
}

/* 当项目列表物品被鼠标覆盖时候的样式 */
.project-list-sidebar-item:hover .project-list-sidebar-item-main {
  background: #e3eaf6;
}

.project-list-sidebar-item:hover .color-board {
  background: #2f7cee;
}

/* 查看更多详情 */
.check-details {
  display: none;
}

.check-details a {
  color: #2f7cee;
}

.check-details.display {
  display: block;
  color: #2f7cee;

}
</style>