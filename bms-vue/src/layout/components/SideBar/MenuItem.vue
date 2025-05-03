<!-- 菜单物品组件 -->
<template>
  <router-link :to="item.link">
    <div class="menu-item-container" :class="{highlight:isActive(item.link)}">
      <div class="menu-item-content">
        <i :class='item.icon'></i>
        <p>{{item.text}}</p>
      </div>
      <div class="decorative-board" :class="{ highlight:isActive(item.link) }"></div>
    </div>
  </router-link>


</template>

<script>
export default {
  name: "MenuItem",
  components: {},
  props: {
    item: {
      type: Object,
      required: true,
      default: null
    }
  },
  data() {
    return {
      // 菜单按钮物品
      // item: {
      //   icon: 'bx bx-home-alt',
      //   text: '首页',
      //   link: '/home',
      //   isHighlight: false
      // }
    }
  },
  methods: {
    isActive(link){
      const urlPath = this.$route.path;
      return urlPath.indexOf(link) !== -1
    }
  }
}
</script>


<style scoped>
a {
  text-decoration: none;
}

.menu-item-container {
  width: 100%;
  height: 40px;
  display: flex;
  margin-top: 3px;
  margin-bottom: 3px;
}

.menu-item-content {
  width: 80%;
  height: 100%;
  display: flex;
  align-items: center;
  margin-left: 15px;
  border-radius: 10px;

}

.menu-item-content i {
  font-size: 20px;
  color: #78818e;
  margin-left: 20px;
}

.menu-item-content p {
  font-size: 16px;
  color: #78818e;
  margin-left: 10px;

}

/* 当鼠标覆盖选项样式 */
.menu-item-container:hover .menu-item-content {
  background-color: #e3eaf6;
}

.menu-item-container:hover .menu-item-content i, .menu-item-container:hover .menu-item-content p{
  color: #2f7cee;
}

.menu-item-container:hover .decorative-board {
  width: 5px;
  height: 100%;
  background: #2f7cee;
  margin-left: 15px;
}

/* 选项高亮样式 */
.menu-item-container.highlight .menu-item-content {
  background-color: #e3eaf6;
}

.menu-item-container.highlight .menu-item-content i, .menu-item-container.highlight .menu-item-content p{
  color: #2f7cee;
}

.menu-item-container.highlight .decorative-board {
  width: 5px;
  height: 100%;
  background: #2f7cee;
  margin-left: 15px;
}
/**/
</style>