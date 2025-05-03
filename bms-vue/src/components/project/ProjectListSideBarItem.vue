<!-- 项目侧边栏物品组件 -->
<template>
  <router-link :to="item.link">
    <div class="project-list-sidebar-item">
      <div class="project-list-sidebar-item-main" :class="{highlight:isActive(item.link)}">
        <p>{{item.code}}</p>
        <h3>{{item.name}}</h3>

        <div class="check-details" :class="{display:isActive(item.link)}">
          <router-link :to="item.link">查看详情</router-link>
        </div>
      </div>

      <div class="color-board" :class="{highlight:isActive(item.link)}" ></div>
    </div>
  </router-link>


</template>

<script>
export default {
  name: "ProjectListSideBarItem",
  props: {
    item: {
      type: Object,
      required: true,
    }
  },

  methods: {
    isActive(link){
      const urlPath = this.$route.path;
      // console.log('当前link '+link);
      // console.log('当前routerUrl ',this.$route.path);
      return urlPath === link;
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
  padding-left: 25px;
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
/* 选项高亮样式 */
.project-list-sidebar-item-main.highlight {
  background-color: #e3eaf6;
}

.color-board.highlight {
  background: #2f7cee;
}
</style>