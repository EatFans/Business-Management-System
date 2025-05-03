<!-- 项目页面布局三级路由索引组件 -->
<template>
  <div class="project-index-main-content">
    <div class="project-index-header">
      <h2>任务列表</h2>
      <div class="task-filter-controls">
        <div class="filter-dropdown">
          <button class="filter-btn" @click="toggleFilterDropdown">
            <i class='bx bx-filter'></i>
            <span>筛选</span>
          </button>
          <div class="filter-dropdown-content" v-if="showFilterDropdown">
            <div class="filter-option">
              <input type="checkbox" id="my-tasks" v-model="filterMyTasks">
              <label for="my-tasks">只看我的任务</label>
            </div>
            <div class="filter-option">
              <select v-model="filterStatus">
                <option value="">所有状态</option>
                <option value="进行中">进行中</option>
                <option value="已完成">已完成</option>
                <option value="待开始">待开始</option>
                <option value="验收中">验收中</option>
              </select>
            </div>
            <div class="filter-option">
              <select v-model="filterPriority">
                <option value="">所有优先级</option>
                <option value="高">高</option>
                <option value="中">中</option>
                <option value="低">低</option>
              </select>
            </div>
          </div>
        </div>
        <div class="view-toggle">
          <button class="view-btn active">
            <i class='bx bx-list-ul'></i>
          </button>
          <button class="view-btn">
            <i class='bx bx-calendar'></i>
          </button>
        </div>
      </div>
    </div>
    
    <!-- 任务分类标签 -->
    <div class="task-category-tabs">
      <div class="task-tab" :class="{ active: activeTab === 'current' }" @click="activeTab = 'current'">当前任务</div>
      <div class="task-tab" :class="{ active: activeTab === 'pending' }" @click="activeTab = 'pending'">待开始任务</div>
    </div>
    
    <!-- 任务列表表格 -->
    <div class="task-table-container">
      <table class="task-table">
        <thead>
          <tr>
            <th width="5%"></th>
            <th width="20%">任务名称</th>
            <th width="15%">预估时间</th>
            <th width="15%">实际时间</th>
            <th width="10%">执行人</th>
            <th width="15%">优先等级</th>
            <th width="10%">状态</th>
            <th width="10%"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(task, index) in filteredTasksByTab" :key="index">
            <td>
              <input type="checkbox" v-model="task.completed" :disabled="!isMyTask(task)" />
            </td>
            <td>{{ task.name }}</td>
            <td>{{ task.estimatedTime }}</td>
            <td>{{ task.actualTime }}</td>
            <td class="executor-cell">
              <div class="executor-avatar" :style="{ backgroundColor: getAvatarColor(task.executor) }">
                {{ getInitials(task.executor) }}
              </div>
            </td>
            <td>
              <div class="priority-indicator" :class="getPriorityClass(task.priority)">
                <i class="bx" :class="getPriorityIcon(task.priority)"></i>
                <span>{{ task.priority }}</span>
              </div>
            </td>
            <td>
              <div class="status-badge" :class="getStatusClass(task.status)">
                {{ task.status }}
              </div>
            </td>
            <td class="action-cell">
              <button class="action-btn" @click="editTask(task)" v-if="isMyTask(task)">
                <i class="bx bx-edit"></i>
              </button>
              <button class="complete-btn" @click="completeTask(task)" v-if="isMyTask(task) && task.status !== '已完成'">
                完成
              </button>
            </td>
          </tr>
          <tr v-if="filteredTasksByTab.length === 0">
            <td colspan="8" class="no-data">
              <div class="empty-message">
                <i class='bx bx-info-circle'></i>
                <p>{{ filterMyTasks ? '没有找到您的任务' : '没有找到任务' }}</p>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    
    <!-- 任务编辑对话框 -->
    <div class="dialog-overlay" v-if="taskDialogVisible" @click.self="taskDialogVisible = false">
      <div class="dialog-content">
        <div class="dialog-header">
          <h3>{{ editingTask ? '编辑任务' : '添加任务' }}</h3>
          <button class="close-btn" @click="taskDialogVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="dialog-body">
          <div class="form-group">
            <label>任务名称</label>
            <input type="text" v-model="taskForm.name" placeholder="请输入任务名称" />
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>预估时间</label>
              <input type="text" v-model="taskForm.estimatedTime" placeholder="例如：2天4小时" />
            </div>
            <div class="form-group">
              <label>实际时间</label>
              <input type="text" v-model="taskForm.actualTime" placeholder="例如：1天2小时" />
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>优先级</label>
              <select v-model="taskForm.priority">
                <option value="高">高</option>
                <option value="中">中</option>
                <option value="低">低</option>
              </select>
            </div>
            <div class="form-group">
              <label>状态</label>
              <select v-model="taskForm.status">
                <option value="进行中">进行中</option>
                <option value="已完成">已完成</option>
                <option value="待开始">待开始</option>
                <option value="验收中">验收中</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label>任务描述</label>
            <textarea v-model="taskForm.description" placeholder="请输入任务描述"></textarea>
          </div>
        </div>
        <div class="dialog-footer">
          <button class="cancel-btn" @click="taskDialogVisible = false">取消</button>
          <button class="submit-btn" @click="saveTask">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  components: {

  },
  created() {
    this.getProjectCode();
    this.getProjectTasks();
  },
  computed: {
    projectCode(){
      return this.$route.params.projectCode;
    },
    // 根据当前选中的标签页过滤任务
    filteredTasksByTab() {
      let tasks = [];
      
      if (this.activeTab === 'current') {
        tasks = this.tasks.filter(task => 
          task.status === '进行中' || task.status === '验收中' || task.status === '已完成'
        );
      } else if (this.activeTab === 'pending') {
        tasks = this.tasks.filter(task => task.status === '待开始');
      }
      
      // 应用筛选条件
      return this.applyFilters(tasks);
    }
  },
  data() {
    return {
      // 当前用户信息
      currentUser: {
        id: 1,
        name: '张三'
      },
      // 所有任务列表
      tasks: [],
      // 当前选中的标签页
      activeTab: 'current',
      // 筛选条件
      filterMyTasks: false,
      filterStatus: '',
      filterPriority: '',
      showFilterDropdown: false,
      // 任务编辑
      taskDialogVisible: false,
      editingTask: null,
      taskForm: {
        name: '',
        estimatedTime: '',
        actualTime: '',
        executor: '',
        priority: '中',
        status: '待开始',
        completed: false,
        description: ''
      }
    }
  },
  methods: {
    getProjectCode(){
      console.log(this.projectCode);
    },
    // 获取项目任务列表
    getProjectTasks() {
      // 这里应该根据projectCode从后端获取数据
      // 模拟数据，实际应该通过API获取
      if (this.projectCode === 'test') {
        // 保留原有任务
        const originalTasks = [
          {
            id: 1,
            name: '用户调研',
            estimatedTime: '2天4小时',
            actualTime: '1天2小时',
            executor: '张三',
            priority: '中',
            status: '已完成',
            completed: true,
            description: '进行用户调研，收集用户需求'
          },
          {
            id: 2,
            name: '思维导图',
            estimatedTime: '2天4小时',
            actualTime: '1天2小时',
            executor: '李四',
            priority: '中',
            status: '进行中',
            completed: false,
            description: '绘制产品思维导图'
          },
          {
            id: 3,
            name: '用户体验草图',
            estimatedTime: '4天',
            actualTime: '2天8小时',
            executor: '王五',
            priority: '低',
            status: '进行中',
            completed: false,
            description: '设计用户体验草图'
          },
          {
            id: 4,
            name: '用户体验登录+注册',
            estimatedTime: '2天',
            actualTime: '4小时',
            executor: '张三',
            priority: '低',
            status: '待在做',
            completed: false,
            description: '设计用户登录和注册界面'
          },
          {
            id: 5,
            name: 'UI登录+注册',
            estimatedTime: '1天2小时',
            actualTime: '4小时',
            executor: '赵六',
            priority: '中',
            status: '验收中',
            completed: false,
            description: '实现UI登录和注册界面'
          },
          {
            id: 6,
            name: '其他界面UI',
            estimatedTime: '4天',
            actualTime: '2天2小时',
            executor: '钱七',
            priority: '低',
            status: '进行中',
            completed: false,
            description: '设计其他界面UI'
          },
          {
            id: 7,
            name: '按钮动画',
            estimatedTime: '6小时',
            actualTime: '0小时',
            executor: '张三',
            priority: '中',
            status: '待开始',
            completed: false,
            description: '实现按钮动画效果'
          },
          {
            id: 8,
            name: '预加载动画',
            estimatedTime: '2天',
            actualTime: '0小时',
            executor: '孙八',
            priority: '低',
            status: '待开始',
            completed: false,
            description: '实现预加载动画效果'
          }
        ];
        
        // 添加更多测试任务
        const additionalTasks = [];
        for (let i = 1; i <= 30; i++) {
          additionalTasks.push({
            id: 8 + i,
            name: `测试任务 ${i}`,
            estimatedTime: '1天',
            actualTime: '0小时',
            executor: i % 3 === 0 ? '张三' : (i % 3 === 1 ? '李四' : '王五'),
            priority: i % 3 === 0 ? '高' : (i % 3 === 1 ? '中' : '低'),
            status: i % 4 === 0 ? '已完成' : (i % 4 === 1 ? '进行中' : (i % 4 === 2 ? '待开始' : '验收中')),
            completed: i % 4 === 0,
            description: `这是测试任务 ${i} 的描述内容`
          });
        }
        
        this.tasks = [...originalTasks, ...additionalTasks];
      } else if (this.projectCode === 'test2') {
        this.tasks = [
          {
            id: 1,
            name: '内部项目系统需求分析',
            estimatedTime: '3天',
            actualTime: '2天',
            executor: '张三',
            priority: '高',
            status: '进行中',
            completed: false,
            description: '进行内部项目系统需求分析'
          },
          {
            id: 2,
            name: '内部项目系统设计',
            estimatedTime: '5天',
            actualTime: '0小时',
            executor: '李四',
            priority: '中',
            status: '待开始',
            completed: false,
            description: '设计内部项目系统架构'
          }
        ];
      } else {
        // 默认数据或其他项目的数据
        this.tasks = [];
      }
    },
    // 应用筛选条件
    applyFilters(tasks) {
      return tasks.filter(task => {
        // 筛选我的任务
        if (this.filterMyTasks && task.executor !== this.currentUser.name) {
          return false;
        }
        
        // 筛选状态
        if (this.filterStatus && task.status !== this.filterStatus) {
          return false;
        }
        
        // 筛选优先级
        if (this.filterPriority && task.priority !== this.filterPriority) {
          return false;
        }
        
        return true;
      });
    },
    // 切换筛选下拉框
    toggleFilterDropdown() {
      this.showFilterDropdown = !this.showFilterDropdown;
    },
    // 判断是否是当前用户的任务
    isMyTask(task) {
      return task.executor === this.currentUser.name;
    },
    // 完成任务
    completeTask(task) {
      if (this.isMyTask(task)) {
        task.status = '已完成';
        task.completed = true;
        // 这里应该调用API保存任务状态
        alert('任务已标记为完成！');
      }
    },
    // 编辑任务
    editTask(task) {
      if (this.isMyTask(task)) {
        this.editingTask = task;
        this.taskForm = { ...task };
        this.taskDialogVisible = true;
      }
    },
    // 保存任务
    saveTask() {
      if (this.editingTask) {
        // 更新现有任务
        const index = this.tasks.findIndex(t => t.id === this.editingTask.id);
        if (index !== -1) {
          this.tasks[index] = { ...this.taskForm };
        }
      } else {
        // 添加新任务
        const newTask = {
          id: Math.max(0, ...this.tasks.map(t => t.id)) + 1,
          ...this.taskForm,
          executor: this.currentUser.name // 默认为当前用户
        };
        this.tasks.push(newTask);
      }
      
      this.taskDialogVisible = false;
      this.editingTask = null;
    },
    // 获取优先级样式类
    getPriorityClass(priority) {
      switch(priority) {
        case '高':
          return 'priority-high';
        case '中':
          return 'priority-medium';
        case '低':
          return 'priority-low';
        default:
          return '';
      }
    },
    // 获取优先级图标
    getPriorityIcon(priority) {
      switch(priority) {
        case '高':
          return 'bxs-up-arrow';
        case '中':
          return 'bx-minus';
        case '低':
          return 'bxs-down-arrow';
        default:
          return '';
      }
    },
    // 获取状态样式类
    getStatusClass(status) {
      switch(status) {
        case '已完成':
          return 'status-completed';
        case '进行中':
          return 'status-in-progress';
        case '待开始':
          return 'status-pending';
        case '验收中':
          return 'status-reviewing';
        default:
          return '';
      }
    },
    // 获取头像颜色
    getAvatarColor(name) {
      const colors = [
        '#1890ff', '#52c41a', '#faad14', '#f5222d', 
        '#722ed1', '#eb2f96', '#fa8c16', '#a0d911'
      ];
      
      // 使用名字的第一个字符的ASCII码作为索引
      const index = name.charCodeAt(0) % colors.length;
      return colors[index];
    },
    // 获取名字首字母
    getInitials(name) {
      return name.charAt(0);
    }
  },
  // 监听路由参数变化，切换项目时重新获取任务
  watch: {
    projectCode() {
      this.getProjectTasks();
    }
  }
}
</script>

<style scoped>
.project-index-main-content{
  width: 96%;
  height: 100%;
  margin-left: 20px;
  overflow: hidden; /* 修改：防止整个容器滚动 */
  background-color: #f5f7fa;
  border-radius: 10px;
  padding: 20px;
  display: flex;
  flex-direction: column; /* 添加：使用flex布局 */
}

.project-index-header {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.project-index-header h2 {
  font-weight: 400;
  font-size: 24px;
  color: #333;
}

.task-filter-controls {
  display: flex;
  align-items: center;
}

.filter-dropdown {
  position: relative;
  margin-right: 15px;
}

.filter-btn {
  display: flex;
  align-items: center;
  background: #fff;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 8px 12px;
  cursor: pointer;
}

.filter-btn i {
  margin-right: 5px;
  font-size: 18px;
  color: #606266;
}

.filter-dropdown-content {
  position: absolute;
  top: 100%;
  right: 0;
  background: #fff;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
  padding: 10px;
  z-index: 10;
  min-width: 180px;
}

.filter-option {
  margin-bottom: 10px;
  display: flex;
  align-items: center;
}

.filter-option label {
  margin-left: 5px;
}

.filter-option select {
  width: 100%;
  padding: 6px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.view-toggle {
  display: flex;
}

.view-btn {
  background: #fff;
  border: 1px solid #dcdfe6;
  padding: 8px;
  cursor: pointer;
}

.view-btn:first-child {
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
}

.view-btn:last-child {
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  border-left: none;
}

.view-btn.active {
  background: #409eff;
  color: #fff;
  border-color: #409eff;
}

/* 任务分类标签 */
.task-category-tabs {
  display: flex;
  margin-bottom: 20px;
  background: #fff;
  border-radius: 4px;
  overflow: hidden;
}

.task-tab {
  padding: 12px 20px;
  cursor: pointer;
  position: relative;
  font-size: 14px;
  color: #606266;
  border-bottom: 2px solid transparent;
}

.task-tab.active {
  color: #409eff;
  border-bottom-color: #409eff;
}

/* 任务表格 */
.task-table-container {
  background: #fff;
  border-radius: 4px;
  overflow: hidden; /* 修改：不允许整个容器滚动 */
  flex: 1; /* 保持：占用剩余空间 */
  max-height: calc(100vh - 220px); /* 保持：设置最大高度 */
  display: flex;
  flex-direction: column; /* 添加：使用flex布局 */
}

.task-table {
  width: 100%;
  border-collapse: collapse;
  table-layout: fixed; /* 添加：固定表格布局 */
}

.task-table thead {
  position: sticky; /* 添加：使表头固定 */
  top: 0; /* 添加：固定在顶部 */
  z-index: 1; /* 添加：确保表头在内容之上 */
  background-color: #f5f7fa; /* 保持：表头背景色 */
}

.task-table tbody {
  overflow-y: auto; /* 添加：只有表格内容可滚动 */
  display: block; /* 添加：将tbody设为块级元素 */
  height: calc(100vh - 280px); /* 添加：设置tbody高度 */
}

.task-table thead tr {
  display: table; /* 添加：表头行显示为表格 */
  width: 100%; /* 添加：表头行宽度100% */
  table-layout: fixed; /* 添加：固定表头布局 */
}

.task-table tbody tr {
  display: table; /* 添加：表格行显示为表格 */
  width: 100%; /* 添加：表格行宽度100% */
  table-layout: fixed; /* 添加：固定表格行布局 */
}

.task-table th {
  padding: 12px 8px;
  text-align: left;
  font-weight: 500;
  color: #909399;
  background-color: #f5f7fa;
  border-bottom: 1px solid #ebeef5;
}

.task-table td {
  padding: 12px 8px;
  border-bottom: 1px solid #ebeef5;
}

.executor-cell {
  display: flex;
  align-items: center;
}

.executor-avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-weight: 500;
}

.priority-indicator {
  display: flex;
  align-items: center;
}

.priority-indicator i {
  margin-right: 5px;
  font-size: 14px;
}

.priority-high {
  color: #f56c6c;
}

.priority-medium {
  color: #e6a23c;
}

.priority-low {
  color: #67c23a;
}

.status-badge {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 12px;
}

.status-in-progress {
  background-color: #e1f3d8;
  color: #67c23a;
}

.status-completed {
  background-color: #d9ecff;
  color: #409eff;
}

.status-pending {
  background-color: #fdf6ec;
  color: #e6a23c;
}

.status-reviewing {
  background-color: #f0f9eb;
  color: #67c23a;
}

.action-cell {
  text-align: center;
}

.action-btn {
  border: none;
  background: none;
  color: #409eff;
  cursor: pointer;
  font-size: 16px;
  margin-right: 5px;
}

.complete-btn {
  background: #67c23a;
  color: white;
  border: none;
  border-radius: 4px;
  padding: 4px 8px;
  font-size: 12px;
  cursor: pointer;
}

.no-data {
  text-align: center;
  padding: 30px 0;
}

.empty-message {
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #909399;
}

.empty-message i {
  font-size: 24px;
  margin-bottom: 10px;
}

/* 对话框样式 */
.dialog-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.dialog-content {
  background: white;
  border-radius: 4px;
  width: 500px;
  max-width: 90%;
  max-height: 90vh;
  overflow-y: auto;
}

.dialog-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  border-bottom: 1px solid #ebeef5;
}

.dialog-header h3 {
  margin: 0;
  font-size: 18px;
  color: #303133;
}

.close-btn {
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: #909399;
}

.dialog-body {
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #606266;
}

.form-group input,
.form-group select,
.form-group textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}

.form-group textarea {
  height: 100px;
  resize: vertical;
}

.form-row {
  display: flex;
  gap: 15px;
}

.form-row .form-group {
  flex: 1;
}

.dialog-footer {
  padding: 10px 20px 20px;
  text-align: right;
}

.cancel-btn {
  background: #fff;
  border: 1px solid #dcdfe6;
  color: #606266;
  padding: 8px 15px;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
}

.submit-btn {
  background: #409eff;
  color: white;
  border: none;
  padding: 8px 15px;
  border-radius: 4px;
  cursor: pointer;
}
</style>