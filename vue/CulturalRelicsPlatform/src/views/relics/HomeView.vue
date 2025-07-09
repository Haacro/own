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
        <a href="http://10.4.126.183:80/" target="_blank">用户信息管理</a>
        <div class="download-app">
          <span>App下载</span>
          <div class="qr-code-container">
            <img src="../../assets/applink.jpg" alt="下载二维码" class="qr-code">
          </div>
        </div>
      </div>
    </div>
    
    <div class="carousel-section">
        <el-carousel :interval="4000" type="card" height="550px">
            <el-carousel-item v-for="(item, index) in carouselItems" :key="index">
                <div class="carousel-item" :style="{ 'background-image': 'url(' + item.image + ')' }"></div>
            </el-carousel-item>
        </el-carousel>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
        carouselItems: [
        { image: 'https://www.shanghaimuseum.net/mu/upload/202312/3ce5c554-2f32-4118-a8d5-15e34f109544.jpg' },
        { image: 'https://www.shanghaimuseum.net/mu/upload/202301/496b946d-2ba8-45da-bb44-1755ee83d5b9.jpg' },
        { image: 'https://www.shanghaimuseum.net/mu/upload/202301/76f0e0a1-92f2-4da3-ba7d-4456fa48ea45.jpg' },
        { image: 'https://www.shanghaimuseum.net/mu/upload/202009/a89beb04-a332-4eb2-8a1b-8a8e2512dc87.jpg' },
        { image: 'https://www.shanghaimuseum.net/mu/upload/202301/e7c3a93a-aad0-4ff5-8c1e-90549f6a1513.jpg' },
        { image: 'https://www.shanghaimuseum.net/mu/upload/202301/05151166-c395-4931-ae60-6beb8ed658e5.jpg' }
      ]
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

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}

.carousel-section {
  margin-top: 35px; /* 间隔 */
}

.carousel-item {
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
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