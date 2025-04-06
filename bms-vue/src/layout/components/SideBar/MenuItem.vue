<!-- 菜单物品组件 -->
<template>
  <a :href="item.link">
    <div class="menu-item" :class="{ highlight:item.isHighlight }">
      <!--装饰板-->
      <div class="decorative-board" :class="{ highlight:item.isHighlight }"></div>
      <i :class='item.icon'></i>
      <p>{{ item.text }}</p>
    </div>
  </a>

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
  }
}
</script>


<style scoped>
a {
  text-decoration: none;
}

.menu-item {
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  color: #c5c3c3;

}

.decorative-board {
  width: 5px;
  height: 100%;
}

.menu-item i {
  margin-left: 20px;
}

.menu-item p {
  font-size: 14px;
  margin-left: 12px;
  font-weight: 400;
}

/* 正常情况下时候，当menu-item类被鼠标覆盖就高亮显示 */
.menu-item:hover {
  background: #2c3137;
  color: #fff;
}

.menu-item:hover .decorative-board {
  background: #219837;
}


/* 如果有highlight类，就始终为高亮显示 */
.menu-item.highlight {
  background: #2c3137;
  color: #fff;
}

.menu-item .decorative-board.highlight {
  background: #219837;
}
</style>