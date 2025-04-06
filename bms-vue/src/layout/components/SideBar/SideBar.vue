<!-- 布局组件 侧边栏组件 -->
<template>
	<div class="side-bar" :class="{ close: !isOpenSideBar }">
    <!-- 侧边栏顶部标题物品 -->
    <div class="side-bar-top">
      <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Layer_1" x="0px" y="0px" width="64px" height="64px" viewBox="0 0 64 64" enable-background="new 0 0 64 64" xml:space="preserve">  <image id="image0" width="64" height="64" x="0" y="0" xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAMAAACdt4HsAAAAIGNIUk0AAHomAACAhAAA+gAAAIDo AAB1MAAA6mAAADqYAAAXcJy6UTwAAAIWUExURf////j8+O357eL14djx2NTw1NLv0dXw1Nry2eL1 4u757vn9+eX25MrsybTks6jgp6PeoqDdn5/dnqHdoKngqLbltc3tzej36Pv++9/037rmuaXfpKff pr/ovuT15P3+/fD68MPqwqTeo6bfpcnsyPP78+T25LHjsd7z3arhqa3irK7iren36bDjsPr9+sDo v8jryN3z3bnmuJ/cnp7cncDpwPz+/OD04KLeoafgp8XqxM7tzc/uzsztzMLpwrznu6rgqef35/7+ /sTqxKzhq+z47PT79P7//tny2bDjr9zz3PH68d/z37fltuf25qHeodTv09ry2tvy29bw1vb89tLv 0uP148rsytHu0N3z3M/uz7Pksur36ff897jlt7Lkst/z3qzhrOn36Mvsys7uzur46qfgpr3nvPj8 98brxfL68u347MXqxa/irtfx1tfx177over36rTktOb25rXktNPv0rLjse757avhqtbw1dDuz7Pk s+z467nmucHpwKvhq8jrx7XlteX25fj9+MTqw7HjsNPv0/P78r3ovfD579Hv0dnx2ev46/X79brm uvL68e/5777ovqDdnqLeouH04djx17jmt8nsycPqw97z3tvy2rXltM3tzPv9+7flt8frxtXw1eP1 4ub25aTfo8zty8frx+T148Lpwbvnu63irbvnuuH04Pn9+PD676rhqvX89a/ir+f255OdXBcAAAAB YktHRACIBR1IAAAAB3RJTUUH6QQGBxMNN2UyXAAABAlJREFUWMOtl/9fEmccwB9Tmx6K3OdQ0Qsw 0A3DqVngpSRl4o1I8wvqgKSmyyZrlslY2TdTa6vVRpsr7dtqVjZX+/If7oAD7usD9Orz03H3eb95 7u7zPM/nEPqwUbSjuKR0p2p8VFZOaNRpTUWltkpHkqAaJKXTV9fUGhRxQ109ncjJEVzKLqPJLOcb dltywhmJtbFJyn/8Sd54wkBW14p5W3MhfEKxxyTkTYXynMHekuWJTwvmOUN15jm0tr0Hz0V7+l3s 1b0XD9YKvn468hkAwL79fDnt35cqF3A4k4LOXAPgGOaAvdrYVdntcrkOtve4tYeSF3YdTvC9RwBP M33u7jpPf6uZ5W+ZdWo+Y5LXDiZ+9nsxAiD7eo76nLK6PZYSDCRm1iCtjh/XDjUolD1Cw1Tyup7g jkcoVYF31K8yccdSo9aNc8elKncAdLtPdeZPpCCqjDv+XFkA9oBZlTenS2+U+xFUFEDohPrKgyZD uQQQJjA8IvQ5BHASy6NTDIkVwBdTWB5NA1YA1r14XjOAF1Bf9orynaxEcHqGxAngzFfCbMPwmdmI WLCTxAnA/rUwuf9sKPjNnPgOwoATMOeEyZ6O88T8hQWRwETjRgBu4d5VEa30n4p+K6pJdhYwAqgS /pstVuoPam3imv7uIlYwm802X4rZIj0dp8VPkJ0WpMsEUJWdAobFy+NXrl6TvAFEXAecoC2z+hQt 3WhZ1jauIMwA5AImU4OTqx0e281bstVsSg8YAdi/5/Mixh9uz50MSGsQaYyAGwE4+EfYUG9suHPD hGTxo3gPkN3CWJrv8tWE78r5u3bACpi6ZNq9n679vOSOy3l/vaRspQI62TZoVu/Hl+43yXlDo2Te SQW/VHkSL2oxHO92K/BO1685BJAUrEWnfnugMH5nUL6JSm/BynUdkYEy04MWOV80obAJSwUWEzI/ XI+7N+S8z6G0g0kF1CO08NjzxCWrH7Y4RpJ5CGDxqfHWskO2I/oqZ0BVINzaoGfjWfzsmgSfLPGS KhvoJe7ykFDgDY+u/S6aQGzkeZRR7SBKUHqrT4eX6HohoCeXG+2UegOiK+eS/tgUnIGXg6t8L85q CNurEI1rv0G/g0tsEs4P60LN9LzHU/t6JLgV1TM5mneIFnEC85agFAeImMU6M0PrKMin9Z9IjjXA ZM+4xvNv+rm5l1ow/BcyjOWFqwAe3rTyq0x6CPAnsV2AYPMv/mW9dfMUbBN78hfAeubbab4vhUHb MJ0/H3uXLZiANWWwFMD/PS6s1zlroZ88zZ2i+dJ7tK8gA3jLpTP2xDaTtwJ0bQpd3Mq5UK7STdGg exZoRUrxT8Bht2A+fBNB0f8e6VxBamFuWhi+E8TE2Mh/91j0QeN/4ItEsepo4aUAAAAldEVYdGRh dGU6Y3JlYXRlADIwMjUtMDQtMDZUMDc6MTk6MTMrMDA6MDBARh1lAAAAJXRFWHRkYXRlOm1vZGlm eQAyMDI1LTA0LTA2VDA3OjE5OjEzKzAwOjAwMRul2QAAACh0RVh0ZGF0ZTp0aW1lc3RhbXAAMjAy NS0wNC0wNlQwNzoxOToxMyswMDowMGYOhAYAAAAASUVORK5CYII="/></svg>
      <p>企业管理系统</p>
    </div>

    <!-- 侧边栏中部菜单部分 -->
    <div class="side-bar-middle">
      <!-- 菜单部分 -->
      <Menu />
    </div>

    <!-- 侧边栏底部部分 -->
    <div class="side-bar-bottom">

    </div>
  </div>
</template>

<script>
	import Menu from "@/layout/components/SideBar/Menu.vue";

  export default {
		name: 'SideBar',
		components: {Menu},
		data() {
			return {
        // 是否打开侧边栏
        isOpenSideBar: true,
			}
		}
	}
</script>

<style scoped>
.side-bar {
  width: 164px;
  height: 99%;
  background: #3b434c;
  border-radius: 5px;
}

/* 侧边栏顶部部分 */
.side-bar-top {
  width: 100%;
  height: 53px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.side-bar-top svg {
  width: 25px;
  height: 25px;
  margin-right: 5px;
}

.side-bar-top p {
  color: #fff;
  font-size: 12px;
}

.side-bar-top:hover{
  background: #249f3a;
}

/* 侧边栏中间部分 */
.side-bar-middle {
  width: 100%;
  height: calc(100% - 53px - 54px);
  /*border: white solid 1px;*/
}

/* 侧边栏底部部分 */
.side-bar-bottom {
  width: 100%;
  height: 54px;
}
</style>