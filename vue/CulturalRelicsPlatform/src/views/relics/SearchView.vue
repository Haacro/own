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

    <div class="artifact-list">

      <div class="pagination-sort">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalArtifacts">
        </el-pagination>

        <!-- 查询框和下拉菜单 -->
        <el-select v-model="queryKey" placeholder="请选择查询条件" class="sort-select">
          <el-option label="文物名" value="name"></el-option>
          <el-option label="类型" value="category"></el-option>
          <el-option label="年代" value="era"></el-option>
        </el-select>
        <el-input v-model="queryValue" placeholder="请输入查询关键字" class="query-input"></el-input>
        <el-button @click="searchArtifacts">查询</el-button>
      </div>

      <div class="card-container">
        <el-row type="flex" justify="center" :gutter="20" class="artifact-row">
          <el-col :span="24" v-for="artifact in displayedArtifacts" :key="artifact.id">
            <el-card :body-style="{ padding: '20px' }" shadow="hover" class="artifact-card">
              <el-row>
                <el-col :span="8">
                  <img preview="0" :src="artifact.imageUrl" alt="文物图片" class="artifact-image" @click="toggleImageZoom(artifact)">
                </el-col>
                <el-col :span="16">
                  <div class="info">
                    <div class="item">朝代：{{ artifact.era ? artifact.era : '暂无数据' }}</div>
                    <div class="item">尺寸：{{ artifact.size ? artifact.size : '暂无数据'}}</div>
                    <div class="item">类型：{{ artifact.category ? artifact.category : '暂无数据'}}</div>
                    <div class="item">所属博物馆：{{ artifact.museum ? artifact.museum : '暂无数据'}}</div>
                    <div class="item">描述：{{ artifact.details ? artifact.details : '暂无数据'}}</div>
                  </div>
                </el-col>
              </el-row>

              <div slot="header" class="clearfix">
                <span class="artifact-name">{{ artifact.name }}</span>
                <el-button style="float: right; padding: auto" type="text" @click="viewArtifactDetails(artifact)">查看详情</el-button>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalArtifacts">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      artifacts: [], // 所有文物列表
      currentPage: 1,
      pageSize: 10,
      totalArtifacts: 0,
      searchClicked: false, // 是否点击了查询按钮
      queryKey: '', // 查询关键字
      queryValue: '' // 查询值
    };
  },
  mounted() {
    // 页面加载时从后端获取文物数据
    this.searchArtifacts();
  },
  computed: {
    displayedArtifacts() {
      // 根据当前页码和每页显示数量，计算展示的文物列表
      const startIndex = (this.currentPage - 1) * this.pageSize;
      return this.artifacts.slice(startIndex, startIndex + this.pageSize);
    }
  },
  methods: {
    viewArtifactDetails(artifact) {
      // 导航至文物详情页面，并传递文物信息
      this.$router.push({ name: 'details', params: { id: artifact.id } });
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.currentPage = 1; // 当每页数量变化时，将当前页码重置为第一页
      this.searchArtifacts();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      // 发送请求到后端获取相应的数据
      this.searchArtifacts();
    },
    searchArtifacts() {
      // 构造查询参数，包括页码和每页数量
      const params = {
        /* page: this.currentPage,
        pageSize: this.pageSize, */
        [this.queryKey]: this.queryValue
      };
      // 发送GET请求到后端，获取数据
      axios.get('http://localhost:8096/browse', { params })
        .then(response => {
          // 请求成功时更新文物数据和总数
          this.artifacts = response.data.data.rows;
          this.totalArtifacts = response.data.data.total;
        })
        .catch(error => {
          // 请求失败时的处理逻辑
          console.error('Error fetching artifacts:', error);
        });
    },
    toggleImageZoom() {
      this.$previewRefresh();
    },
  },
  /* created() {
    // 从后端获取文物数据，并存储在artifacts数组中
    // 示例：this.artifacts = fetchDataFromBackend();
    // 页面加载时初始化加载第一页的数据
    this.fetchArtifacts();
  } */
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

.artifact-list {
  text-align: center;
  padding-bottom: 10px;
}

.card-container {
  display: inline-block;
}

.artifact-row {
  flex-direction: column;
}

.artifact-card {
  /* width: 300px;
  margin: 20px; */
  /* width: 80%; */
  width: 800px; /* 设置固定宽度 */
  height: auto; /* 设置固定高度 */
  margin: 20px auto;
}

.artifact-image {
  width: 100%;
  height: 100%;
  cursor: pointer;
  padding-bottom: 15px;
}

.artifact-name {
  font-weight: bold;
  font-size: 25px;
  font-family: 华文中宋;
}

.info {
  padding-left: 20px;
}

.item {
  text-align: left;
  padding-left: 50px;
  padding-top: 5px;
  margin-bottom: 10px;
  font-size: 17px;
  font-family: 宋体;
}

.pagination-sort {
  margin-top: 25px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.sort-select {
  margin-left: 10px;
  margin-right: 10px;
  width: 150px;
}

.query-input {
  margin-right: 10px;
  width: 150px;
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