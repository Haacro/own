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

        <el-select v-model="sortKey" placeholder="请选择排序方式" class="sort-select">
          <el-option label="朝代" value="era"></el-option>
          <el-option label="类型" value="category"></el-option>
          <el-option label="博物馆" value="museum"></el-option>
        </el-select>
        <el-button @click="sortArtifacts">排序</el-button>
      </div>

      <div class="card-container">
          <el-col :span="24" v-for="artifact in displayedArtifacts" :key="artifact.id">
            <el-card shadow="hover" class="artifact-card">
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

              <div slot="header" class="clearfix">
                <span class="artifact-name">{{ artifact.name }}</span>
                <el-button style="float: right; padding: auto" type="text" @click="viewArtifactDetails(artifact)">查看详情</el-button>
              </div>
            </el-card>
          </el-col>
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
      sortKey: '', // 排序关键字
      sortClicked: false, // 是否点击了排序按钮
    };
  },
  mounted() {
    // 页面加载时从后端获取文物数据
    this.fetchArtifacts();
  },
  computed: {
    sortedArtifacts() {
      if (this.sortClicked && this.sortKey) {
        return this.artifacts.slice().sort((a, b) => {
          return a[this.sortKey].localeCompare(b[this.sortKey]);
        });
      } else {
        return this.artifacts.slice();
      }
    },
    displayedArtifacts() {
      // 根据当前页码和每页显示数量，计算展示的文物列表
      const startIndex = (this.currentPage - 1) * this.pageSize;
      return this.sortedArtifacts.slice(startIndex, startIndex + this.pageSize);
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
      this.fetchArtifacts();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      // 发送请求到后端获取相应的数据
      this.fetchArtifacts();
    },
    fetchArtifacts() {
      /* // 构造查询参数，包括页码和每页数量
      const params = {
        page: this.currentPage,
        pageSize: this.pageSize
      }; */
      // 发送GET请求到后端，获取数据
      axios.get('http://localhost:8096/browse'/* , { params } */)
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
    sortArtifacts() {
      // 用户点击排序按钮时调用，更新排序关键字，触发computed重新计算
      this.sortClicked = true;
    }
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
  text-align: center; /* 使文物列表中的文物名居中显示 */
  padding-bottom: 10px;
}

.card-container {
  display: inline-block; /* 让容器在一行内水平排列，并且可以根据内容自动调整宽度 */
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