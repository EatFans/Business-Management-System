<template>
  <div class="login-page-container">

    <div class="login-box-container">
      <div class="logo-box">
        <strong class="logo-text">企业管理系统</strong>
      </div>

      <div class="login-box-content">

        <!--登陆方式切换-->
        <div class="change-login">
          <div class="Isolation-panels">
          </div>
          <div class="qrcode">
            <svg @click="toggleChangedLogin"  v-if="!isChangedLogin" t="1743322362228" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1630" width="128" height="128"><path d="M421.639981 0H43.809703A43.943391 43.943391 0 0 0 0 43.809703v377.830278a43.943391 43.943391 0 0 0 43.809703 43.742859h377.830278a43.943391 43.943391 0 0 0 43.809703-43.809703V43.809703A43.943391 43.943391 0 0 0 421.639981 0z m-49.27756 372.362421H93.087263V93.087263h279.275158zM980.176928 0H602.34665a43.930022 43.930022 0 0 0-43.809703 43.809703v377.830278a43.930022 43.930022 0 0 0 43.809703 43.742859h377.830278A43.943391 43.943391 0 0 0 1024.053475 421.639981V43.809703A43.943391 43.943391 0 0 0 980.176928 0z m-49.27756 372.362421H651.637579V93.087263h279.261789zM421.639981 558.536947H43.809703A43.930022 43.930022 0 0 0 0 602.34665v377.843647a43.943391 43.943391 0 0 0 43.809703 43.809703h377.830278a43.943391 43.943391 0 0 0 43.809703-43.809703V602.34665a43.930022 43.930022 0 0 0-43.809703-43.809703z m-49.27756 372.362421H93.087263v-279.275158h279.275158z" fill="#2cbc44" p-id="1631"></path><path d="M930.899368 1023.986631h49.27756A43.943391 43.943391 0 0 0 1024.053475 980.176928v-49.27756h-93.154107zM744.711473 930.899368h93.087263v93.087263h-93.087263z" fill="#2cbc44" p-id="1632"></path><path d="M980.176928 558.536947h-49.27756v186.174526h-93.087263V558.536947H602.34665a43.930022 43.930022 0 0 0-43.809703 43.809703v377.843647a43.930022 43.930022 0 0 0 43.809703 43.809703h49.290929V744.711473h93.087263v93.087263H1024.053475V602.34665a43.930022 43.930022 0 0 0-43.809702-43.809703z m0 0" fill="#2cbc44" p-id="1633"></path><path d="M175.225443 791.261789a57.486031 57.486031 0 1 0 28.743015-49.798945 57.486031 57.486031 0 0 0-28.743015 49.798945z m0 0M175.225443 232.764949a57.486031 57.486031 0 1 0 28.743015-49.798946 57.486031 57.486031 0 0 0-28.743015 49.798946z m0 0M733.76239 232.764949a57.486031 57.486031 0 1 0 28.743015-49.798946 57.486031 57.486031 0 0 0-28.743015 49.798946z m0 0" fill="#2cbc44" p-id="1634"></path></svg>
            <svg @click="toggleChangedLogin" v-else t="1743324659908" class="icon" viewBox="0 0 1025 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3112" width="128" height="128"><path d="M945.4 153v554h-864V153h864m20-80h-904c-33.1 0-60 26.9-60 60v594c0 33.1 26.9 60 60 60h904c33.1 0 60-26.9 60-60V133c0-33.1-26.9-60-60-60z" fill="#2cbc44" p-id="3113"></path><path d="M521 787v71h-15.3v-71H521m20-80h-55.3c-33.1 0-60 26.9-60 60v111c0 33.1 26.9 60 60 60H541c33.1 0 60-26.9 60-60V767c0-33.1-26.9-60-60-60z" fill="#2cbc44" p-id="3114"></path><path d="M724.4 858h-422c-22.1 0-40 17.9-40 40s17.9 40 40 40h422c22.1 0 40-17.9 40-40s-17.9-40-40-40zM513.4 616.5c-22.1 0-40 17.9-40 40s17.9 40 40 40 40-17.9 40-40-17.9-40-40-40z" fill="#2cbc44" p-id="3115"></path></svg>
          </div>
        </div>

        <!--登陆表-->
        <div class="login-form" :class="{ close: isChangedLogin}" >
          <div class="form-item">
            <p class="form-title">账号登录</p>
          </div>

          <div class="form-group">
            <input type="text" placeholder="账号" v-model="loginData.username"/>
            <p v-if="isUsernameError"> * {{ usernameErrorMessage }}</p>
          </div>

          <div class="form-group">
            <input type="password" placeholder="密码" v-model="loginData.password" />
            <p v-if="isPasswordError"> * {{ passwordErrorMessage }}</p>
          </div>

          <div class="form-group-with-code">
            <input type="text" placeholder="验证码" v-model="loginData.code"/>
            <!--  TODO: 图片人机验证码  -->
            <div @click="getCode" class="image-code">
              <img :src="codeUrl" alt="" />
            </div>
          </div>

          <div class="login-button">
            <p>登录</p>
          </div>
        </div>

        <!---微信扫码哦-->
        <div class="wechat-login-form" :class="{ close: !isChangedLogin }" >
          <p>微信扫码</p>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import {getCodeImg} from "@/api/login.js";

export default {
  name: "Login",
  components: {},
  data() {
    return {
      // 是否切换登陆方式
      isChangedLogin: false,
      // 用户名输入是否合法
      isUsernameError: false,
      // 用户名输入错误信息
      usernameErrorMessage: '不存在该用户',
      // 密码输入是否错误
      isPasswordError: false,
      // 密码输入错误消息
      passwordErrorMessage: '密码错误！',

      // 登录数据
      loginData: {
        username: 'admin',
        password: '123456@abc',
        code: '',
        uuid: ''
      },

      // 是否启用图片验证码
      captchaEnabled: true,
      // 图片验证码图片url
      codeUrl: ''

    }
  },
  methods: {
    // 切换登录方式
    toggleChangedLogin() {
      this.isChangedLogin = !this.isChangedLogin;
    },

    // 获取验证码
    getCode() {
      getCodeImg().then(res => {
        console.log(res.img);
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/jpeg;base64," + res.img;
          this.loginData.uuid = res.uuid;
        }

      });
    },


  }
}
</script>

<style scoped>
/* 当鼠标放在登录按钮上面时候的登录按钮样式  */
.login-button:hover {
  background: #38d51e;
}

/* 登录按钮样式 */
.login-button {
  width: 80%;
  height: 40px;
  margin-top:20px;
  background: #2db417;
  border-radius: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-size: 20px
}

/* 图片验证码图片样式 */
.image-code img {
  width: 100%;
  height: 38px;
}

/* 图片验证码 */
.image-code {
  width: auto;
  height: 100%;
  border: none;
}

/* 表验证码输入物品 */
.form-group-with-code {
  width: 80%;
  height: 40px;
  margin-top: 5px;
  display: flex;
  flex-direction: row;
}

/* 验证码输入框样式 */
.form-group-with-code input {
  width: 40%;
  height: 35px;
  padding-left: 10px;
  padding-right: 10px;
}

/* 表标题 */
.form-title {
  font-size: 26px;
  font-weight: 400;
}

/* 表单个物品样式 */
.form-item {
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 表组物品样式 */
.form-group {
  width: 80%;
  height: 60px;
  margin-top: 5px;
  //border: red solid 1px;
}

.form-group input {
  width: 95%;
  height: 35px;
  padding-left: 10px;
  padding-right: 10px;
}

/* 错误文本样式 */
.form-group p {
  font-size: 12px;
  color: red;
  margin-left: 5px;
  padding-top: 2px;
}


/* 登陆页面主要容器样式 */
.login-page-container {
  width: 100%;
  height: 100vh;
  background: #434343;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 登陆盒子容器样式 */
.login-box-container {
  width: 460px;
  height: auto;
}

/* 登陆盒子样式 */
.logo-box {
  width: 100%;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Logo文字样式 */
.logo-text {
  font-size: 45px;
  font-weight: 400;
  color: #25cb8c;
}

/* 登陆盒子主体样式 */
.login-box-content {
  width: 100%;
  background: #f5f5f5;
  border-radius: 8px;
}

/* 登陆方式切换盒样式 */
.change-login {
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 分割板 */
.Isolation-panels {
  height: 100%;
  width: 400px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.qrcode {
  width: 50px;
  height: 50px;
}

.qrcode svg {
  width: 100%;
  height: 100%;
}


/* 账号登陆表 */
.login-form {
  width: 100%;
  height: 340px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

/* 账号登陆表关闭样式 */
.login-form.close {
  display: none;
}

/* 微信扫码登陆框显示样式 */
.wechat-login-form {
  width: 100%;
  height: 300px;
}

/* 微信扫码登陆框关闭样式 */
.wechat-login-form.close {
  display: none;
}
</style>