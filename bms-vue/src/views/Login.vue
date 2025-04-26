<template>
  <!-- 添加消息提示组件 -->
  <transition name="fade">
    <div v-if="tipItem.showMessage" class="message-tip" :class="tipItem.messageType">
      <div class="message-content">
        <i class="message-icon" :class="tipItem.messageType">{{ tipItem.messageType === 'success' ? '✓' : '✕' }}</i>
        {{ tipItem.message }}
      </div>
    </div>
  </transition>

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
            <div @click="getCode" class="image-code">
              <img :src="codeUrl" alt="" />
            </div>
          </div>

          <div class="login-button" @click="login">
            <p>登录</p>
          </div>

          <!-- 其他登录框 -->
          <div class="other-login-box">
            <div class="other-login-box-title">
              <div class="line"></div>
              <p>其他登录</p>
              <div class="line"></div>
            </div>

            <div class="other-login-items">
              <a href="#" id="github-login">
                <svg t="1743484836052" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4189" width="128" height="128"><path d="M512 42.666667A464.64 464.64 0 0 0 42.666667 502.186667 460.373333 460.373333 0 0 0 363.52 938.666667c23.466667 4.266667 32-9.813333 32-22.186667v-78.08c-130.56 27.733333-158.293333-61.44-158.293333-61.44a122.026667 122.026667 0 0 0-52.053334-67.413333c-42.666667-28.16 3.413333-27.733333 3.413334-27.733334a98.56 98.56 0 0 1 71.68 47.36 101.12 101.12 0 0 0 136.533333 37.973334 99.413333 99.413333 0 0 1 29.866667-61.44c-104.106667-11.52-213.333333-50.773333-213.333334-226.986667a177.066667 177.066667 0 0 1 47.36-124.16 161.28 161.28 0 0 1 4.693334-121.173333s39.68-12.373333 128 46.933333a455.68 455.68 0 0 1 234.666666 0c89.6-59.306667 128-46.933333 128-46.933333a161.28 161.28 0 0 1 4.693334 121.173333A177.066667 177.066667 0 0 1 810.666667 477.866667c0 176.64-110.08 215.466667-213.333334 226.986666a106.666667 106.666667 0 0 1 32 85.333334v125.866666c0 14.933333 8.533333 26.88 32 22.186667A460.8 460.8 0 0 0 981.333333 502.186667 464.64 464.64 0 0 0 512 42.666667" fill="#231F20" p-id="4190"></path></svg>
              </a>

              <a href="#" id="gitee-login">
                <svg t="1743484896077" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5165" width="128" height="128"><path d="M512 1024C229.2224 1024 0 794.7776 0 512S229.2224 0 512 0s512 229.2224 512 512-229.2224 512-512 512z m259.1488-568.8832H480.4096a25.2928 25.2928 0 0 0-25.2928 25.2928l-0.0256 63.2064c0 13.952 11.3152 25.2928 25.2672 25.2928h177.024c13.9776 0 25.2928 11.3152 25.2928 25.2672v12.6464a75.8528 75.8528 0 0 1-75.8528 75.8528H366.592a25.2928 25.2928 0 0 1-25.2672-25.2928v-240.1792a75.8528 75.8528 0 0 1 75.8272-75.8528h353.9456a25.2928 25.2928 0 0 0 25.2672-25.2928l0.0768-63.2064a25.2928 25.2928 0 0 0-25.2672-25.2928H417.152a189.6192 189.6192 0 0 0-189.6192 189.6448v353.9456c0 13.9776 11.3152 25.2928 25.2928 25.2928h372.9408a170.6496 170.6496 0 0 0 170.6496-170.6496v-145.408a25.2928 25.2928 0 0 0-25.2928-25.2672z" fill="#C71D23" p-id="5166"></path></svg>
              </a>
            </div>
          </div>
        </div>

        <!---微信扫码哦-->
        <div class="wechat-login-form" :class="{ close: !isChangedLogin }" >
          <div class="form-item">
            <p class="form-title">微信扫码</p>
          </div>

          <div class="wechat-login-content">
            <div class="wechat-qrcode-box">
              <img src="" alt="">
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import {getCodeImg, login} from "@/api/login.js";

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
        username: '20250420001',
        password: '1234@abc',
        code: '',
        uuid: ''
      },

      tipItem: {
        messageType: '',
        showMessage: false,
        message: ''
      },

      // 是否启用图片验证码
      captchaEnabled: true,
      // 图片验证码图片url
      codeUrl: ''

    }
  },
  created() {
    this.getCode(); // 创建时候获取图片验证码
  },
  methods: {
    // 切换登录方式
    toggleChangedLogin() {
      this.isChangedLogin = !this.isChangedLogin;
    },

    // 获取验证码
    getCode() {
      getCodeImg().then(res => {
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/jpeg;base64," + res.img;
          this.loginData.uuid = res.uuid;
        }

      });
    },

    /**
     * 显示提示框
     * @param type 类型
     * @param msg 消息文本
     */
    showTipMessage(type,msg){
      this.tipItem.message = msg;
      this.tipItem.messageType = type;
      this.tipItem.showMessage = true;
      setTimeout(() => {
        this.tipItem.showMessage = false
      }, 3000)
    },

	/**
	 * 登录
	 */
	login() {
		login(this.loginData.username,
        this.loginData.password,
        this.loginData.code,
        this.loginData.uuid).then(res => {
          console.log("login response:", res);
			    // 检查请求结果是否为空
          if (res !== null){
            // 检查请求结果是否成功
            if (res.code === 200){
              const token = res.token;
              console.log(token);
              localStorage.setItem("loginToken", token);
              this.showTipMessage("success","成功登录！");


              setTimeout(() => {
                this.$router.push({
                  path: '/dashboard',
                  name: 'Dashboard'
                });
              },500);
            } else {
              this.showTipMessage("error",res.msg);
            }
        }
		})
	}

  }
}
</script>

<style scoped>
.wechat-qrcode-box img {
  width: 100%;
  height: 100%;
}

.wechat-qrcode-box {
  width: 200px;
  height: 200px;
  border: black solid 1px;
}

.wechat-login-content {
  width: 100%;
  height: 210px;
  display: flex;
  align-items: center;
  justify-content: center;
}


/* 分割线 */
.line {
  width: 180px;
  height: 1px;
  background: black;
  margin-left: 10px;
  margin-right: 10px;
}

/* 其他登录物品样式 */
#github-login, #gitee-login {
  width: 25px;
  height: 25px;
  margin-left: 10px;
  margin-right: 10px;
  margin-bottom: 15px;
}

/* 其他登录物品图标样式 */
#github-login svg, #gitee-login svg {
  width: 100%;
  height: 100%;
}

/* 其他登录物品们样式 */
.other-login-items {
  width: 100%;
  height: 40px;
  margin-top: 5px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

/* 其他登录选项标题 */
.other-login-box-title {
  text-align: center;
  width: 100%;
  font-size: 12px;
  font-weight: 200;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

/* 其他登录选项盒子 */
.other-login-box {
  width: 100%;
  height: 50px;
  margin-top: 20px;
}

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
  overflow-x: hidden;
}

.form-group input {
  width: 90%;
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
  background: #eaeff5;
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
  height: auto;
  background: #fff;
  border-radius: 15px;
}

/* 登陆方式切换盒样式 */
.change-login {
  width: 100%;
  height: 30px;
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
  margin-top: 5px;
  width: 30px;
  height: 30px;
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
  margin-top: 5px;
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

/* 错误提示框样式 */
.message-tip.error {
  border: 1px solid #ffdce0;
  background-color: #fff2f0;
}

.message-content {
  display: flex;
  align-items: center;
  gap: 12px;
  font-size: 15px;
  font-weight: 500;
}

.message-icon {
  font-style: normal;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 24px;
  height: 24px;
  border-radius: 50%;
}

/* 错误图标样式 */
.message-icon.error {
  color: white;
  background: #ff4d4f;
}

/* 成功图标样式 */
.message-icon.success {
  color: white;
  background: #52c41a;
}

/* 错误消息文字颜色 */
.message-tip.error .message-content {
  color: #cf1322;
}

/* 成功消息文字颜色 */
.message-tip.success {
  border: 1px solid #b7eb8f;
  background-color: #f6ffed;
}

.message-tip.success .message-content {
  color: #52c41a;
}

/* 修改消息提示样式 */
.message-tip {
  position: fixed;
  top: 30px;
  right: 30px;
  z-index: 1000;
  padding: 16px 24px;
  background: white;
  border-radius: 12px;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.12);
  display: flex;
  align-items: center;
  border: 1px solid #ffdce0;
  background-color: #fff2f0;
  min-width: 320px;
}

.message-content {
  display: flex;
  align-items: center;
  gap: 12px;
  color: #cf1322;
  font-size: 15px;
  font-weight: 500;
}

.message-icon {
  color: #ff4d4f;
  font-style: normal;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 24px;
  height: 24px;
  background: rgba(255, 77, 79, 0.1);
  border-radius: 50%;
}

/* 调整动画效果 */
.fade-enter-active,
.fade-leave-active {
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateX(50px);
}
</style>