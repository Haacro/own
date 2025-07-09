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
    
    <div class="card-container">
      <el-col span="24">
        <el-card shadow="hover" class="details-card">
          <div slot="header">
            <span class="card-title">文物详情</span>
            <el-button style="float: left; padding: auto" type="text" @click="goBack">返回</el-button>
            <el-button style="float: right; padding: auto" type="text" @click="viewRecommendations(artifact)">相关推荐</el-button>
          </div>

          <!-- 左侧文物图片 -->
          <el-col :span="8">
            <img preview="0" :src="artifact.imageUrl" alt="文物图片" class="artifact-image" @click="toggleImageZoom(artifact)">
          </el-col>
          <!-- 右侧文物信息 -->
          <el-col :span="16">
            <div class="info">
              <div class="item">
                <span style="font-weight: bold;">文物ID: </span>
                <span>{{ artifact.id ? artifact.id : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">文物名称: </span>
                <span>{{ artifact.name ? artifact.name : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">朝代: </span>
                <span>{{ artifact.era ? artifact.era : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">作者: </span>
                <span>{{ artifact.author ? artifact.author : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">尺寸: </span>
                <span>{{ artifact.size ? artifact.size : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">类型: </span>
                <span>{{ artifact.category ? artifact.category : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">所属博物馆: </span>
                <span>{{ artifact.museum ? artifact.museum : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">库存单: </span>
                <span>{{ artifact.inventory ? artifact.inventory : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">介绍: </span>
                <span>{{ artifact.details ? artifact.details : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">技艺: </span>
                <span>{{ artifact.technique ? artifact.technique : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">卷数: </span>
                <span>{{ artifact.numberOfVolume ? artifact.numberOfVolume : '暂无数据' }}</span>
              </div>
              <div class="item">
                <span style="font-weight: bold;">雕刻或手工复制: </span>
                <span>{{ artifact.engravedOrHandcopied ? artifact.engravedOrHandcopied : '暂无数据' }}</span>
              </div>
            </div>
          </el-col>
        </el-card>
      </el-col>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      artifact: [],
    };
  },
  methods: {
    fetchArtifacts() {
      // 发送GET请求到后端，获取数据
      axios.get('http://localhost:8096/browse/' + this.$route.params.id)
        .then(response => {
          // 请求成功时更新文物数据
          this.artifact = response.data.data;
        })
        .catch(error => {
          // 请求失败时的处理逻辑
          console.error('Error fetching artifacts:', error);
        });
    },
    goBack() {
      this.$router.go(-1);
    },
    viewRecommendations(artifact) {
      // 假设跳转到相关推荐页面的路由名称为 'Recommendations'
      this.$router.push({ name: 'recommendation' , params: { id: artifact.id }});
    }
  },
  mounted() {
    // 页面加载时从后端获取文物数据
    this.fetchArtifacts();
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

.card-container {
  display: inline-block; /* 让容器在一行内水平排列，并且可以根据内容自动调整宽度 */
  margin-top: 10px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.details-card {
  width: 1100px; /* 设置固定宽度 */
  height: auto; /* 设置固定高度 */
  margin: 20px auto;
}

.artifact-image {
  width: 100%;
  height: 100%;
  cursor: pointer;
  padding-bottom: 15px;
}

.card-title {
  font-weight: bold;
  font-size: 25px;
  font-family: 华文中宋;
}

.info {
  padding-left:15px;
  padding-bottom: 15px;
}

.item {
  text-align: left;
  padding-left: 50px;
  padding-top: 5px;
  margin-bottom: 10px;
  font-size: 17px;
  font-family: 宋体;
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