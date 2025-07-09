<template>
  <div class="home-page">
    <!-- 顶部区域 -->
    <div class="top-section">
      <div class="platform-name">海外藏中国文物知识管理与服务平台</div>
      <div class="navigation">
        <router-link to="/home">首页</router-link>
        <router-link to="/browse">文物浏览</router-link>
        <router-link to="/search">查询</router-link>
        <router-link to="/visual">可视化</router-link>
        <router-link to="/timeline">时间轴</router-link>
        <router-link to="/login">用户信息管理</router-link>
      </div>
    </div>
  </div>
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
</style>