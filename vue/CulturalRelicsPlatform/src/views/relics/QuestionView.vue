<template>
  <div class="home-page">
    
    <!-- 顶部区域 -->
    <div class="top-section">
      <div class="platform-name">海外藏中国文物知识管理与服务平台</div>
      <div class="navigation">
        <router-link to="/home">首页</router-link>
        <router-link to="/browse">文物浏览</router-link>
        <router-link to="/search">查询</router-link>
        <a href="http://101.201.109.37:7474/browser/" target="_blank">可视化</a>
        <router-link to="/timeline">时间轴</router-link>
        <!-- <a href="https://mbd.baidu.com/ma/s/NleJJLfy" target="_blank">知识问答</a> -->
        <router-link to="/question">知识问答</router-link>
        <a href="http://10.4.72.253:80/" target="_blank">用户信息管理</a>
        <div class="download-app">
          <span>App下载</span>
          <div class="qr-code-container">
            <img src="../../assets/applink.jpg" alt="下载二维码" class="qr-code">
          </div>
        </div>
      </div>
    </div>

<iframe src="https://mbd.baidu.com/ma/s/NleJJLfy" width='100%' height='630' webkitallowfullscreen mozallowfullscreen allowfullscreen frameborder='0'></iframe>  </div>
</template>

<script>
export default {
  data() {
    return {
      
    };
  },
}
</script>

<style scoped>
.home-page {
  font-family: Arial, sans-serif;
}

.top-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background-color: #f0f0f0;
}

.platform-name {
  font-size: 24px;
  font-weight: bold;
  padding: 10px;
  padding-left: 80px;
}

.navigation {
  display: flex;
  padding: 10px;
  padding-right: 50px;
}

.navigation a {
  text-decoration: none;
  color: #333;
  margin-left: 20px;
}

.download-app {
  position: relative;
  margin-left: 20px;
  cursor: pointer;
}

.qr-code-container {
  display: none;
  position: absolute;
  top: 30px;
  left: 50%;
  transform: translateX(-50%);
  width: 150px;
  padding: 10px;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
  z-index: 1000;
}

.qr-code {
  width: 100%;
  height: auto;
  border-radius: 4px;
}

.download-app:hover .qr-code-container {
  display: block;
}
</style>