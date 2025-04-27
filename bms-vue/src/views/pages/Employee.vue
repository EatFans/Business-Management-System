<template>
  <div class="employee-container">
    <!-- 顶部部分，标题和添加按钮 -->
    <div class="index-section">
      <div class="page-title">
        <h1>员工管理</h1>
      </div>
      <div class="add-employee-button" @click="showAddDialog">
        <i class="bx bx-plus"></i>
        <p>添加员工</p>
      </div>
    </div>

    <!-- 搜索和筛选部分 -->
    <div class="search-filter-section">
      <div class="search-box">
        <i class="bx bx-search"></i>
        <input 
          type="text" 
          v-model="searchQuery" 
          placeholder="搜索员工姓名/工号/邮箱" 
          @input="handleSearch"
        />
      </div>
      <div class="filter-box">
        <select v-model="filterStatus" @change="handleSearch">
          <option value="">全部状态</option>
          <option value="1">在职</option>
          <option value="0">离职</option>
        </select>
        <select v-model="filterDept" @change="handleSearch">
          <option value="">全部部门</option>
          <option v-for="dept in departments" :key="dept.id" :value="dept.id">
            {{ dept.name }}
          </option>
        </select>
      </div>
    </div>

    <!-- 员工列表表格 -->
    <div class="employee-table-container">
      <table class="employee-table">
        <thead>
          <tr>
            <th width="50"><input type="checkbox" /></th>
            <th width="80">头像</th>
            <th width="100">姓名</th>
            <th width="120">工号</th>
            <th width="120">部门</th>
            <th width="120">职位</th>
            <th width="150">邮箱</th>
            <th width="120">手机号</th>
            <th width="80">状态</th>
            <th width="150">操作</th>
          </tr>
        </thead>
        <tbody v-if="paginatedEmployees.length > 0">
          <tr v-for="emp in paginatedEmployees" :key="emp.emp_id">
            <td><input type="checkbox" /></td>
            <td>
              <img 
                :src="emp.avatar || defaultAvatar" 
                alt="头像" 
                class="avatar-img"
              />
            </td>
            <td>{{ emp.nick_name }}</td>
            <td>{{ emp.job_number }}</td>
            <td>{{ getDeptName(emp.dept_id) }}</td>
            <td>{{ getPosName(emp.pos_id) }}</td>
            <td>{{ emp.email }}</td>
            <td>{{ emp.phone_number }}</td>
            <td>
              <span class="status-badge" :class="emp.status === '1' ? 'active' : 'inactive'">
                {{ emp.status === '1' ? '在职' : '离职' }}
              </span>
            </td>
            <td class="action-buttons">
              <button class="btn-edit" @click="handleEdit(emp)">
                <i class="bx bx-edit"></i>
              </button>
              <button class="btn-detail" @click="handleDetail(emp)">
                <i class="bx bx-detail"></i>
              </button>
              <button class="btn-delete" @click="handleDelete(emp)">
                <i class="bx bx-trash"></i>
              </button>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr>
            <td colspan="10" class="no-data">暂无员工数据</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 分页控件 -->
    <div class="pagination-container">
      <div class="page-size">
        <span>每页显示：</span>
        <select v-model="pageSize" @change="handleSizeChange">
          <option value="10">10</option>
          <option value="20">20</option>
          <option value="50">50</option>
          <option value="100">100</option>
        </select>
      </div>
      <div class="pagination">
        <button 
          class="page-btn" 
          :disabled="currentPage === 1" 
          @click="currentPage--"
        >
          <i class="bx bx-chevron-left"></i>
        </button>
        <span class="page-info">{{ currentPage }} / {{ totalPages || 1 }}</span>
        <button 
          class="page-btn" 
          :disabled="currentPage === totalPages || totalPages === 0" 
          @click="currentPage++"
        >
          <i class="bx bx-chevron-right"></i>
        </button>
      </div>
    </div>

    <!-- 添加/编辑员工对话框 -->
    <div class="dialog-overlay" v-if="dialogVisible" @click.self="dialogVisible = false">
      <div class="dialog-content">
        <div class="dialog-header">
          <h3>{{ isEdit ? '编辑员工' : '添加员工' }}</h3>
          <button class="close-btn" @click="dialogVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="dialog-body">
          <div class="form-group avatar-upload">
            <div class="avatar-preview" @click="triggerFileInput">
              <img :src="employeeForm.avatar || defaultAvatar" alt="头像" />
              <div class="upload-overlay">
                <i class="bx bx-upload"></i>
                <span>上传头像</span>
              </div>
            </div>
            <input 
              type="file" 
              ref="fileInput" 
              style="display: none" 
              accept="image/*" 
              @change="handleAvatarUpload"
            />
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>姓名</label>
              <input type="text" v-model="employeeForm.nick_name" placeholder="请输入员工姓名" />
              <div class="error-message" v-if="errors.nick_name">{{ errors.nick_name }}</div>
            </div>
            <div class="form-group">
              <label>工号</label>
              <input type="text" v-model="employeeForm.job_number" placeholder="请输入工号" />
              <div class="error-message" v-if="errors.job_number">{{ errors.job_number }}</div>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>部门</label>
              <select v-model="employeeForm.dept_id">
                <option value="">请选择部门</option>
                <option v-for="dept in departments" :key="dept.id" :value="dept.id">
                  {{ dept.name }}
                </option>
              </select>
              <div class="error-message" v-if="errors.dept_id">{{ errors.dept_id }}</div>
            </div>
            <div class="form-group">
              <label>职位</label>
              <select v-model="employeeForm.pos_id" :disabled="!employeeForm.dept_id">
                <option value="">请选择职位</option>
                <option v-for="pos in filteredPositions" :key="pos.id" :value="pos.id">
                  {{ pos.name }}
                </option>
              </select>
              <div class="error-message" v-if="errors.pos_id">{{ errors.pos_id }}</div>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>邮箱</label>
              <input type="email" v-model="employeeForm.email" placeholder="请输入邮箱地址" />
              <div class="error-message" v-if="errors.email">{{ errors.email }}</div>
            </div>
            <div class="form-group">
              <label>手机号</label>
              <input type="text" v-model="employeeForm.phone_number" placeholder="请输入手机号" />
              <div class="error-message" v-if="errors.phone_number">{{ errors.phone_number }}</div>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>性别</label>
              <div class="radio-group">
                <label>
                  <input type="radio" v-model="employeeForm.sex" value="1" />
                  <span>男</span>
                </label>
                <label>
                  <input type="radio" v-model="employeeForm.sex" value="2" />
                  <span>女</span>
                </label>
              </div>
            </div>
            <div class="form-group">
              <label>状态</label>
              <div class="radio-group">
                <label>
                  <input type="radio" v-model="employeeForm.status" value="1" />
                  <span>在职</span>
                </label>
                <label>
                  <input type="radio" v-model="employeeForm.status" value="0" />
                  <span>离职</span>
                </label>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label>备注</label>
            <textarea v-model="employeeForm.remark" placeholder="请输入备注信息"></textarea>
          </div>
        </div>
        <div class="dialog-footer">
          <button class="cancel-btn" @click="dialogVisible = false">取消</button>
          <button class="submit-btn" @click="submitForm">确定</button>
        </div>
      </div>
    </div>

    <!-- 员工详情抽屉 -->
    <div class="drawer-container" :class="{ 'drawer-open': drawerVisible }">
      <div class="drawer-mask" @click="drawerVisible = false"></div>
      <div class="drawer-content">
        <div class="drawer-header">
          <h3>员工详情</h3>
          <button class="close-btn" @click="drawerVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="drawer-body" v-if="currentEmployee">
          <div class="employee-profile">
            <div class="profile-header">
              <img :src="currentEmployee.avatar || defaultAvatar" alt="头像" class="profile-avatar" />
              <div class="profile-info">
                <h2>{{ currentEmployee.nick_name }}</h2>
                <p>{{ getPosName(currentEmployee.pos_id) }} | {{ getDeptName(currentEmployee.dept_id) }}</p>
                <span class="status-badge" :class="currentEmployee.status === '1' ? 'active' : 'inactive'">
                  {{ currentEmployee.status === '1' ? '在职' : '离职' }}
                </span>
              </div>
            </div>
            <div class="profile-details">
              <div class="detail-item">
                <span class="detail-label">工号</span>
                <span class="detail-value">{{ currentEmployee.job_number }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">邮箱</span>
                <span class="detail-value">{{ currentEmployee.email }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">手机号</span>
                <span class="detail-value">{{ currentEmployee.phone_number }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">性别</span>
                <span class="detail-value">{{ currentEmployee.sex === '1' ? '男' : '女' }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">最后登录</span>
                <span class="detail-value">{{ currentEmployee.login_time || '暂无登录记录' }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">创建时间</span>
                <span class="detail-value">{{ currentEmployee.create_time }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">创建人</span>
                <span class="detail-value">{{ currentEmployee.create_by }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">更新时间</span>
                <span class="detail-value">{{ currentEmployee.update_time }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">更新人</span>
                <span class="detail-value">{{ currentEmployee.update_by }}</span>
              </div>
              <div class="detail-item full-width">
                <span class="detail-label">备注</span>
                <span class="detail-value">{{ currentEmployee.remark || '暂无备注' }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Employee",
  data() {
    return {
      // 默认头像
      defaultAvatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      
      // 部门数据
      departments: [
        { id: 1, name: '技术部' },
        { id: 2, name: '市场部' },
        { id: 3, name: '人事部' },
        { id: 4, name: '财务部' },
        { id: 5, name: '行政部' }
      ],
      
      // 职位数据
      positions: [
        { id: 1, name: '部门经理', dept_id: 1 },
        { id: 2, name: '高级工程师', dept_id: 1 },
        { id: 3, name: '前端工程师', dept_id: 1 },
        { id: 4, name: '后端工程师', dept_id: 1 },
        { id: 5, name: '市场总监', dept_id: 2 },
        { id: 6, name: '市场专员', dept_id: 2 },
        { id: 7, name: 'HR经理', dept_id: 3 },
        { id: 8, name: 'HR专员', dept_id: 3 },
        { id: 9, name: '财务经理', dept_id: 4 },
        { id: 10, name: '会计', dept_id: 4 },
        { id: 11, name: '行政经理', dept_id: 5 },
        { id: 12, name: '行政专员', dept_id: 5 }
      ],
      
      // 员工数据
      employees: [
        {
          emp_id: 1,
          dept_id: 1,
          pos_id: 2,
          job_number: 'BMS20230001',
          nick_name: '张三',
          email: 'zhangsan@example.com',
          phone_number: '13800138001',
          sex: '1',
          avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
          password: '******',
          status: '1',
          del_flag: '0',
          first_login: '0',
          login_time: '2023-05-15 09:30:45',
          create_time: '2023-01-01 08:00:00',
          create_by: 'admin',
          update_time: '2023-05-15 09:30:45',
          update_by: 'admin',
          remark: '技术部核心成员，负责系统架构设计'
        },
        {
          emp_id: 2,
          dept_id: 1,
          pos_id: 3,
          job_number: 'BMS20230002',
          nick_name: '李四',
          email: 'lisi@example.com',
          phone_number: '13800138002',
          sex: '1',
          avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
          password: '******',
          status: '1',
          del_flag: '0',
          first_login: '0',
          login_time: '2023-05-14 14:20:30',
          create_time: '2023-01-05 09:00:00',
          create_by: 'admin',
          update_time: '2023-04-10 11:15:20',
          update_by: 'admin',
          remark: '前端开发工程师，负责用户界面开发'
        },
        {
          emp_id: 3,
          dept_id: 1,
          pos_id: 4,
          job_number: 'BMS20230003',
          nick_name: '王五',
          email: 'wangwu@example.com',
          phone_number: '13800138003',
          sex: '1',
          avatar: '',
          password: '******',
          status: '1',
          del_flag: '0',
          first_login: '0',
          login_time: '2023-05-15 08:45:12',
          create_time: '2023-01-10 10:30:00',
          create_by: 'admin',
          update_time: '2023-03-15 16:40:30',
          update_by: 'admin',
          remark: '后端开发工程师，负责服务端开发'
        }
      ],
      
      // 状态变量
      loading: false,
      searchQuery: '',
      filterStatus: '',
      filterDept: '',
      currentPage: 1,
      pageSize: 10,
      dialogVisible: false,
      drawerVisible: false,
      isEdit: false,
      currentEmployee: null,
      
      // 表单数据
      employeeForm: {
        emp_id: null,
        dept_id: '',
        pos_id: '',
        job_number: '',
        nick_name: '',
        email: '',
        phone_number: '',
        sex: '1',
        avatar: '',
        password: '',
        status: '1',
        remark: ''
      },
      
      // 表单验证错误信息
      errors: {}
    }
  },
  computed: {
    // 过滤后的员工列表
    filteredEmployees() {
      return this.employees.filter(emp => {
        const matchQuery = this.searchQuery ? 
          (emp.nick_name.includes(this.searchQuery) || 
           emp.job_number.includes(this.searchQuery) || 
           emp.email.includes(this.searchQuery)) : true
        
        const matchStatus = this.filterStatus ? emp.status === this.filterStatus : true
        
        const matchDept = this.filterDept ? emp.dept_id === parseInt(this.filterDept) : true
        
        return matchQuery && matchStatus && matchDept
      })
    },
    
    // 分页后的员工列表
    paginatedEmployees() {
      const start = (this.currentPage - 1) * this.pageSize
      const end = start + this.pageSize
      return this.filteredEmployees.slice(start, end)
    },
    
    // 总页数
    totalPages() {
      return Math.ceil(this.filteredEmployees.length / this.pageSize)
    },
    
    // 根据部门ID筛选职位
    filteredPositions() {
      if (!this.employeeForm.dept_id) {
        return []
      }
      return this.positions.filter(pos => pos.dept_id === parseInt(this.employeeForm.dept_id))
    }
  },
  methods: {
    // 获取部门名称
    getDeptName(deptId) {
      const dept = this.departments.find(d => d.id === deptId)
      return dept ? dept.name : '未知部门'
    },
    
    // 获取职位名称
    getPosName(posId) {
      const pos = this.positions.find(p => p.id === posId)
      return pos ? pos.name : '未知职位'
    },
    
    // 搜索处理
    handleSearch() {
      this.currentPage = 1
    },
    
    // 每页显示数量变化
    handleSizeChange() {
      this.currentPage = 1
    },
    
    // 显示添加员工对话框
    showAddDialog() {
      this.isEdit = false
      this.resetForm()
      this.dialogVisible = true
    },
    
    // 编辑员工
    handleEdit(employee) {
      this.isEdit = true
      this.employeeForm = { ...employee }
      this.dialogVisible = true
    },
    
    // 删除员工
    handleDelete(employee) {
      if (confirm(`确定要删除员工 ${employee.nick_name} 吗？`)) {
        // 实际应该调用API删除员工
        const index = this.employees.findIndex(emp => emp.emp_id === employee.emp_id)
        if (index !== -1) {
          this.employees.splice(index, 1)
          alert('删除成功')
        }
      }
    },
    
    // 查看员工详情
    handleDetail(employee) {
      this.currentEmployee = employee
      this.drawerVisible = true
    },
    
    // 触发文件选择
    triggerFileInput() {
      this.$refs.fileInput.click()
    },
    
    // 处理头像上传
    handleAvatarUpload(event) {
      const file = event.target.files[0]
      if (!file) return
      
      // 验证文件类型
      if (!file.type.match('image.*')) {
        alert('请上传图片文件')
        return
      }
      
      // 验证文件大小（最大2MB）
      if (file.size > 2 * 1024 * 1024) {
        alert('图片大小不能超过2MB')
        return
      }
      
      // 读取文件并预览
      const reader = new FileReader()
      reader.readAsDataURL(file)
      reader.onload = () => {
        this.employeeForm.avatar = reader.result
      }
    },
    
    // 验证表单
    validateForm() {
      this.errors = {}
      
      // 验证姓名
      if (!this.employeeForm.nick_name) {
        this.errors.nick_name = '请输入员工姓名'
      } else if (this.employeeForm.nick_name.length < 2 || this.employeeForm.nick_name.length > 20) {
        this.errors.nick_name = '姓名长度在2到20个字符之间'
      }
      
      // 验证工号
      if (!this.employeeForm.job_number) {
        this.errors.job_number = '请输入工号'
      } else if (!/^[A-Za-z0-9]+$/.test(this.employeeForm.job_number)) {
        this.errors.job_number = '工号只能包含字母和数字'
      }
      
      // 验证部门
      if (!this.employeeForm.dept_id) {
        this.errors.dept_id = '请选择部门'
      }
      
      // 验证职位
      if (!this.employeeForm.pos_id) {
        this.errors.pos_id = '请选择职位'
      }
      
      // 验证邮箱
      if (!this.employeeForm.email) {
        this.errors.email = '请输入邮箱地址'
      } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.employeeForm.email)) {
        this.errors.email = '请输入正确的邮箱地址'
      }
      
      // 验证手机号
      if (!this.employeeForm.phone_number) {
        this.errors.phone_number = '请输入手机号'
      } else if (!/^1[3-9]\d{9}$/.test(this.employeeForm.phone_number)) {
        this.errors.phone_number = '请输入正确的手机号'
      }
      
      return Object.keys(this.errors).length === 0
    },
    
    // 提交表单
    submitForm() {
      if (!this.validateForm()) {
        return
      }
      
      if (this.isEdit) {
        // 编辑员工
        const index = this.employees.findIndex(emp => emp.emp_id === this.employeeForm.emp_id)
        if (index !== -1) {
          // 更新员工信息
          const updatedEmployee = { ...this.employees[index], ...this.employeeForm }
          updatedEmployee.update_time = new Date().toLocaleString()
          updatedEmployee.update_by = 'admin' // 实际应该是当前登录用户
          this.employees.splice(index, 1, updatedEmployee)
          alert('员工信息更新成功')
        }
      } else {
        // 添加新员工
        const newEmployee = {
          ...this.employeeForm,
          emp_id: this.employees.length + 1, // 实际应该由后端生成
          del_flag: '0',
          first_login: '1',
          login_time: null,
          create_time: new Date().toLocaleString(),
          create_by: 'admin', // 实际应该是当前登录用户
          update_time: new Date().toLocaleString(),
          update_by: 'admin' // 实际应该是当前登录用户
        }
        this.employees.push(newEmployee)
        alert('员工添加成功')
      }
      this.dialogVisible = false
      this.resetForm()
    },
    
    // 重置表单
    resetForm() {
      this.employeeForm = {
        emp_id: null,
        dept_id: '',
        pos_id: '',
        job_number: '',
        nick_name: '',
        email: '',
        phone_number: '',
        sex: '1',
        avatar: '',
        password: '',
        status: '1',
        remark: ''
      }
      this.errors = {}
    }
  },
  watch: {
    // 监听部门变化，重置职位
    'employeeForm.dept_id'(newVal) {
      this.employeeForm.pos_id = ''
    }
  }
}
</script>

<style scoped>
.employee-container {
  padding: 20px;
  background-color: #eaeff5;
  min-height: 100vh;
}

/* 顶部部分样式 */
.index-section {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  background-color: #fff;
  padding: 15px 20px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

.page-title h1 {
  margin: 0;
  font-size: 22px;
  color: #333;
  font-weight: 600;
}

.add-employee-button {
  display: flex;
  align-items: center;
  background-color: #409eff;
  color: white;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.add-employee-button:hover {
  background-color: #66b1ff;
}

.add-employee-button i {
  margin-right: 5px;
  font-size: 18px;
}

.add-employee-button p {
  margin: 0;
}

/* 搜索和筛选部分样式 */
.search-filter-section {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  background-color: #fff;
  padding: 15px 20px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

.search-box {
  position: relative;
  flex: 1;
  margin-right: 20px;
}

.search-box i {
  position: absolute;
  left: 10px;
  top: 50%;
  transform: translateY(-50%);
  color: #909399;
}

.search-box input {
  width: 100%;
  padding: 10px 10px 10px 35px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}

.search-box input:focus {
  outline: none;
  border-color: #409eff;
}

.filter-box {
  display: flex;
  gap: 10px;
}

.filter-box select {
  padding: 10px 15px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 14px;
  color: #606266;
  background-color: #fff;
  cursor: pointer;
  transition: border-color 0.3s;
}

.filter-box select:focus {
  outline: none;
  border-color: #409eff;
}

/* 表格样式 */
.employee-table-container {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  margin-bottom: 20px;
}

.employee-table {
  width: 100%;
  border-collapse: collapse;
}

.employee-table th,
.employee-table td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #ebeef5;
}

.employee-table th {
  background-color: #f5f7fa;
  color: #606266;
  font-weight: 500;
  font-size: 14px;
}

.employee-table tbody tr:hover {
  background-color: #f5f7fa;
}

.avatar-img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  object-fit: cover;
  border: 1px solid #ebeef5;
}

.status-badge {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 10px;
  font-size: 12px;
}

.status-badge.active {
  background-color: #f0f9eb;
  color: #67c23a;
}

.status-badge.inactive {
  background-color: #fef0f0;
  color: #f56c6c;
}

.action-buttons {
  display: flex;
  gap: 5px;
}

.action-buttons button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 16px;
  padding: 5px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.btn-edit {
  color: #409eff;
}

.btn-detail {
  color: #909399;
}

.btn-delete {
  color: #f56c6c;
}

.action-buttons button:hover {
  background-color: #f5f7fa;
}

.no-data {
  text-align: center;
  padding: 30px;
  color: #909399;
}

/* 分页样式 */
.pagination-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  padding: 15px 20px;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
}

.page-size {
  display: flex;
  align-items: center;
}

.page-size span {
  margin-right: 10px;
  color: #606266;
}

.page-size select {
  padding: 5px 10px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 14px;
}

.pagination {
  display: flex;
  align-items: center;
}

.page-btn {
  background: none;
  border: 1px solid #dcdfe6;
  padding: 6px 12px;
  margin: 0 5px;
  border-radius: 4px;
  cursor: pointer;
  color: #606266;
  transition: all 0.3s;
}

.page-btn:hover:not(:disabled) {
  color: #409eff;
  border-color: #c6e2ff;
}

.page-btn:disabled {
  cursor: not-allowed;
  opacity: 0.5;
}

.page-info {
  margin: 0 10px;
  color: #606266;
}

/* 对话框样式 */
.dialog-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.dialog-content {
  width: 600px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.dialog-header {
  padding: 15px 20px;
  border-bottom: 1px solid #ebeef5;
  display: flex;
  justify-content: space-between;
  align-items: center;
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
  max-height: 60vh;
  overflow-y: auto;
}

.dialog-footer {
  padding: 10px 20px 20px;
  text-align: right;
}

.cancel-btn {
  padding: 8px 20px;
  background-color: #f4f4f5;
  border: none;
  border-radius: 4px;
  color: #606266;
  cursor: pointer;
  margin-right: 10px;
  transition: background-color 0.3s;
}

.cancel-btn:hover {
  background-color: #e9e9eb;
}

.submit-btn {
  padding: 8px 20px;
  background-color: #409eff;
  border: none;
  border-radius: 4px;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-btn:hover {
  background-color: #66b1ff;
}

/* 表单样式 */
.form-row {
  display: flex;
  gap: 20px;
  margin-bottom: 15px;
}

.form-group {
  flex: 1;
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-size: 14px;
  color: #606266;
}

.form-group input,
.form-group select,
.form-group textarea {
  width: 100%;
  padding: 10px 12px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}

.form-group input:focus,
.form-group select:focus,
.form-group textarea:focus {
  outline: none;
  border-color: #409eff;
}

.form-group textarea {
  resize: vertical;
  min-height: 80px;
}

.radio-group {
  display: flex;
  gap: 15px;
}

.radio-group label {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.radio-group input {
  margin-right: 5px;
  width: auto;
}

.error-message {
  color: #f56c6c;
  font-size: 12px;
  margin-top: 5px;
}

/* 头像上传样式 */
.avatar-upload {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.avatar-preview {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  overflow: hidden;
  position: relative;
  cursor: pointer;
  border: 1px dashed #d9d9d9;
}

.avatar-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.upload-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
  opacity: 0;
  transition: opacity 0.3s;
}

.avatar-preview:hover .upload-overlay {
  opacity: 1;
}

.upload-overlay i {
  font-size: 24px;
  margin-bottom: 5px;
}

/* 抽屉样式 */
.drawer-container {
  position: fixed;
  top: 0;
  right: -400px;
  width: 100%;
  height: 100%;
  z-index: 1000;
  transition: right 0.3s;
  pointer-events: none; /* 默认不接收鼠标事件 */
}

.drawer-container.drawer-open {
  right: 0;
  pointer-events: auto; /* 打开时接收鼠标事件 */
}

.drawer-mask {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.drawer-content {
  position: absolute;
  top: 0;
  right: 0;
  width: 400px;
  height: 100%;
  background-color: #fff;
  box-shadow: -2px 0 8px rgba(0, 0, 0, 0.15);
  overflow-y: auto;
  pointer-events: auto; /* 抽屉内容始终接收鼠标事件 */
}

.drawer-header {
  padding: 15px 20px;
  border-bottom: 1px solid #ebeef5;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.drawer-header h3 {
  margin: 0;
  font-size: 18px;
  color: #303133;
}

.drawer-body {
  padding: 20px;
}

/* 员工详情样式 */
.employee-profile {
  display: flex;
  flex-direction: column;
}

.profile-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid #ebeef5;
}

.profile-avatar {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 20px;
  border: 1px solid #ebeef5;
}

.profile-info h2 {
  margin: 0 0 5px 0;
  font-size: 20px;
  color: #303133;
}

.profile-info p {
  margin: 0 0 10px 0;
  color: #606266;
}

.profile-details {
  display: flex;
  flex-wrap: wrap;
}

.detail-item {
  width: 50%;
  margin-bottom: 15px;
  display: flex;
}

.detail-item.full-width {
  width: 100%;
}

.detail-label {
  width: 80px;
  color: #909399;
  flex-shrink: 0;
}

.detail-value {
  color: #303133;
  word-break: break-all;
}
</style>