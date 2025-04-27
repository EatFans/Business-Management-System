<!-- 角色管理主要布局组件 -->
<template>
  <div class="role-container">
    <!-- 顶部部分，标题和添加按钮 -->
    <div class="index-section">
      <div class="page-title">
        <h1>角色管理</h1>
      </div>
      <div class="add-role-button" @click="showAddDialog">
        <i class="bx bx-plus"></i>
        <p>添加角色</p>
      </div>
    </div>

    <!-- 搜索和筛选部分 -->
    <div class="search-filter-section">
      <div class="search-box">
        <i class="bx bx-search"></i>
        <input 
          type="text" 
          v-model="searchQuery" 
          placeholder="搜索角色名称/关键字" 
          @input="handleSearch"
        />
      </div>
      <div class="filter-box">
        <select v-model="filterStatus" @change="handleSearch">
          <option value="">全部状态</option>
          <option value="1">启用</option>
          <option value="0">禁用</option>
        </select>
      </div>
    </div>

    <!-- 角色列表表格 -->
    <div class="role-table-container">
      <table class="role-table">
        <thead>
          <tr>
            <th width="50"><input type="checkbox" /></th>
            <th width="100">角色ID</th>
            <th width="150">角色名称</th>
            <th width="200">角色标识</th>
            <th width="100">状态</th>
            <th width="150">创建时间</th>
            <th width="150">更新时间</th>
            <th width="150">操作</th>
          </tr>
        </thead>
        <tbody v-if="paginatedRoles.length > 0">
          <tr v-for="role in paginatedRoles" :key="role.role_id">
            <td><input type="checkbox" /></td>
            <td>{{ role.role_id }}</td>
            <td>{{ role.role_name }}</td>
            <td>{{ role.role_key }}</td>
            <td>
              <span class="status-badge" :class="role.status === '1' ? 'active' : 'inactive'">
                {{ role.status === '1' ? '启用' : '禁用' }}
              </span>
            </td>
            <td>{{ role.create_time }}</td>
            <td>{{ role.update_time }}</td>
            <td class="action-buttons">
              <button class="btn-edit" @click="handleEdit(role)">
                <i class="bx bx-edit"></i>
              </button>
              <button class="btn-detail" @click="handleDetail(role)">
                <i class="bx bx-detail"></i>
              </button>
              <button class="btn-delete" @click="handleDelete(role)">
                <i class="bx bx-trash"></i>
              </button>
              <button class="btn-permission" @click="handlePermission(role)">
                <i class="bx bx-key"></i>
              </button>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr>
            <td colspan="8" class="no-data">暂无角色数据</td>
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

    <!-- 添加/编辑角色对话框 -->
    <div class="dialog-overlay" v-if="dialogVisible" @click.self="dialogVisible = false">
      <div class="dialog-content">
        <div class="dialog-header">
          <h3>{{ isEdit ? '编辑角色' : '添加角色' }}</h3>
          <button class="close-btn" @click="dialogVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="dialog-body">
          <div class="form-row">
            <div class="form-group">
              <label>角色名称</label>
              <input type="text" v-model="roleForm.role_name" placeholder="请输入角色名称" />
              <div class="error-message" v-if="errors.role_name">{{ errors.role_name }}</div>
            </div>
            <div class="form-group">
              <label>角色标识</label>
              <input type="text" v-model="roleForm.role_key" placeholder="请输入角色标识" />
              <div class="error-message" v-if="errors.role_key">{{ errors.role_key }}</div>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>状态</label>
              <div class="radio-group">
                <label>
                  <input type="radio" v-model="roleForm.status" value="1" />
                  <span>启用</span>
                </label>
                <label>
                  <input type="radio" v-model="roleForm.status" value="0" />
                  <span>禁用</span>
                </label>
              </div>
            </div>
          </div>
          
          <!-- 关联菜单选择 -->
          <div class="form-group">
            <label>关联菜单</label>
            <div class="checkbox-group">
              <label v-for="menu in menus" :key="menu.menu_id" class="checkbox-item">
                <input 
                  type="checkbox" 
                  :value="menu.menu_id" 
                  v-model="selectedMenus"
                />
                <span>{{ menu.menu_name }}</span>
              </label>
            </div>
          </div>
          
          <!-- 关联员工选择 -->
          <div class="form-group">
            <label>关联员工</label>
            <div class="checkbox-group">
              <label v-for="emp in employees" :key="emp.emp_id" class="checkbox-item">
                <input 
                  type="checkbox" 
                  :value="emp.emp_id" 
                  v-model="selectedEmployees"
                />
                <span>{{ emp.nick_name }}</span>
              </label>
            </div>
          </div>
          
          <div class="form-group">
            <label>备注</label>
            <textarea v-model="roleForm.remark" placeholder="请输入备注信息"></textarea>
          </div>
        </div>
        <div class="dialog-footer">
          <button class="cancel-btn" @click="dialogVisible = false">取消</button>
          <button class="submit-btn" @click="submitForm">确定</button>
        </div>
      </div>
    </div>

    <!-- 角色详情抽屉 -->
    <div class="drawer-container" :class="{ 'drawer-open': drawerVisible }">
      <div class="drawer-mask" v-if="drawerVisible" @click="drawerVisible = false"></div>
      <div class="drawer-content">
        <div class="drawer-header">
          <h3>角色详情</h3>
          <button class="close-btn" @click="drawerVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="drawer-body" v-if="currentRole">
          <div class="role-profile">
            <div class="profile-header">
              <div class="profile-info">
                <h2>{{ currentRole.role_name }}</h2>
                <p>{{ currentRole.role_key }}</p>
                <span class="status-badge" :class="currentRole.status === '1' ? 'active' : 'inactive'">
                  {{ currentRole.status === '1' ? '启用' : '禁用' }}
                </span>
              </div>
            </div>
            <div class="profile-details">
              <div class="detail-item">
                <span class="detail-label">角色ID</span>
                <span class="detail-value">{{ currentRole.role_id }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">创建时间</span>
                <span class="detail-value">{{ currentRole.create_time }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">创建人</span>
                <span class="detail-value">{{ currentRole.create_by }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">更新时间</span>
                <span class="detail-value">{{ currentRole.update_time }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">更新人</span>
                <span class="detail-value">{{ currentRole.update_by }}</span>
              </div>
              <div class="detail-item full-width">
                <span class="detail-label">备注</span>
                <span class="detail-value">{{ currentRole.remark || '暂无备注' }}</span>
              </div>
            </div>

            <!-- 关联菜单列表 -->
            <div class="related-section">
              <h3>关联菜单</h3>
              <div class="related-list" v-if="currentRoleMenus.length > 0">
                <div class="related-item" v-for="menu in currentRoleMenus" :key="menu.menu_id">
                  <span>{{ menu.menu_name }}</span>
                </div>
              </div>
              <div class="no-data" v-else>暂无关联菜单</div>
            </div>

            <!-- 关联员工列表 -->
            <div class="related-section">
              <h3>关联员工</h3>
              <div class="related-list" v-if="currentRoleEmployees.length > 0">
                <div class="related-item" v-for="emp in currentRoleEmployees" :key="emp.emp_id">
                  <span>{{ emp.nick_name }}</span>
                </div>
              </div>
              <div class="no-data" v-else>暂无关联员工</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 权限分配对话框 -->
    <div class="dialog-overlay" v-if="permissionDialogVisible" @click.self="permissionDialogVisible = false">
      <div class="dialog-content permission-dialog">
        <div class="dialog-header">
          <h3>分配权限 - {{ currentRole ? currentRole.role_name : '' }}</h3>
          <button class="close-btn" @click="permissionDialogVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="dialog-body">
          <div class="permission-tree">
            <!-- 这里可以放权限树组件 -->
            <div class="tree-placeholder">
              <p>权限树组件（实际项目中可以使用Element UI的Tree组件等）</p>
            </div>
          </div>
        </div>
        <div class="dialog-footer">
          <button class="cancel-btn" @click="permissionDialogVisible = false">取消</button>
          <button class="submit-btn" @click="savePermissions">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Role",
  data() {
    return {
      // 选中的菜单和员工
      selectedMenus: [],
      selectedEmployees: [],

      // 角色数据
      roles: [
        {
          role_id: 1,
          role_name: '超级管理员',
          role_key: 'admin',
          status: '1',
          del_flag: '0',
          create_time: '2023-01-01 08:00:00',
          create_by: 'system',
          update_time: '2023-05-15 09:30:45',
          update_by: 'admin',
          remark: '系统超级管理员，拥有所有权限'
        },
        {
          role_id: 2,
          role_name: '普通用户',
          role_key: 'user',
          status: '1',
          del_flag: '0',
          create_time: '2023-01-05 09:00:00',
          create_by: 'admin',
          update_time: '2023-04-10 11:15:20',
          update_by: 'admin',
          remark: '普通用户，拥有基本操作权限'
        },
        {
          role_id: 3,
          role_name: '访客',
          role_key: 'visitor',
          status: '1',
          del_flag: '0',
          create_time: '2023-01-10 10:30:00',
          create_by: 'admin',
          update_time: '2023-03-15 16:40:30',
          update_by: 'admin',
          remark: '访客角色，只有查看权限'
        }
      ],
      
      // 角色菜单关联数据
      roleMenus: [
        { role_id: 1, menu_id: 1 },
        { role_id: 1, menu_id: 2 },
        { role_id: 1, menu_id: 3 },
        { role_id: 2, menu_id: 1 },
        { role_id: 2, menu_id: 2 },
        { role_id: 3, menu_id: 1 }
      ],
      
      // 员工角色关联数据
      employeeRoles: [
        { emp_id: 1, role_id: 1 },
        { emp_id: 2, role_id: 2 },
        { emp_id: 3, role_id: 2 }
      ],
      
      // 菜单数据
      menus: [
        { menu_id: 1, menu_name: '仪表盘', menu_key: 'dashboard' },
        { menu_id: 2, menu_name: '员工管理', menu_key: 'employee' },
        { menu_id: 3, menu_name: '角色管理', menu_key: 'role' }
      ],
      
      // 员工数据（简化版）
      employees: [
        { emp_id: 1, nick_name: '张三' },
        { emp_id: 2, nick_name: '李四' },
        { emp_id: 3, nick_name: '王五' },
        { emp_id: 4, nick_name: '赵六' },
        { emp_id: 5, nick_name: '钱七' }
      ],
      
      // 状态变量
      loading: false,
      searchQuery: '',
      filterStatus: '',
      currentPage: 1,
      pageSize: 10,
      dialogVisible: false,
      drawerVisible: false,
      permissionDialogVisible: false,
      isEdit: false,
      currentRole: null,
      
      // 表单数据
      roleForm: {
        role_id: null,
        role_name: '',
        role_key: '',
        status: '1',
        remark: ''
      },
      
      // 表单验证错误信息
      errors: {}
    }
  },
  computed: {
    // 过滤后的角色列表
    filteredRoles() {
      return this.roles.filter(role => {
        const matchQuery = this.searchQuery ? 
          (role.role_name.includes(this.searchQuery) || 
           role.role_key.includes(this.searchQuery)) : true
        
        const matchStatus = this.filterStatus ? role.status === this.filterStatus : true
        
        return matchQuery && matchStatus
      })
    },
    
    // 分页后的角色列表
    paginatedRoles() {
      const start = (this.currentPage - 1) * this.pageSize
      const end = start + this.pageSize
      return this.filteredRoles.slice(start, end)
    },
    
    // 总页数
    totalPages() {
      return Math.ceil(this.filteredRoles.length / this.pageSize)
    },
    
    // 当前角色关联的菜单
    currentRoleMenus() {
      if (!this.currentRole) return []
      
      const menuIds = this.roleMenus
        .filter(rm => rm.role_id === this.currentRole.role_id)
        .map(rm => rm.menu_id)
      
      return this.menus.filter(menu => menuIds.includes(menu.menu_id))
    },
    
    // 当前角色关联的员工
    currentRoleEmployees() {
      if (!this.currentRole) return []
      
      const empIds = this.employeeRoles
        .filter(er => er.role_id === this.currentRole.role_id)
        .map(er => er.emp_id)
      
      return this.employees.filter(emp => empIds.includes(emp.emp_id))
    },

      // 是否全选菜单
    isAllMenusSelected() {
      return this.selectedMenus.length === this.menus.length
    },
    
    // 是否全选员工
    isAllEmployeesSelected() {
      return this.selectedEmployees.length === this.employees.length
    }
  },
  methods: {
    // 全选/取消全选菜单
    selectAllMenus() {
      if (this.isAllMenusSelected) {
        this.selectedMenus = []
      } else {
        this.selectedMenus = this.menus.map(menu => menu.menu_id)
      }
    },

    // 全选/取消全选员工
    selectAllEmployees() {
      if (this.isAllEmployeesSelected) {
        this.selectedEmployees = []
      } else {
        this.selectedEmployees = this.employees.map(emp => emp.emp_id)
      }
    },

    // 更新角色菜单关联
    updateRoleMenus(roleId) {
      // 删除原有关联
      this.roleMenus = this.roleMenus.filter(rm => rm.role_id !== roleId)
      
      // 添加新关联
      this.selectedMenus.forEach(menuId => {
        this.roleMenus.push({
          role_id: roleId,
          menu_id: menuId
        })
      })
    },

    // 更新角色员工关联
    updateRoleEmployees(roleId) {
      // 删除原有关联
      this.employeeRoles = this.employeeRoles.filter(er => er.role_id !== roleId)
      
      // 添加新关联
      this.selectedEmployees.forEach(empId => {
        this.employeeRoles.push({
          role_id: roleId,
          emp_id: empId
        })
      })
    },

        // 搜索处理
    handleSearch() {
      this.currentPage = 1
    },
    
    // 每页显示数量变化
    handleSizeChange() {
      this.currentPage = 1
    },
    
    // 显示添加角色对话框
    showAddDialog() {
      this.isEdit = false
      this.resetForm()
      this.dialogVisible = true
    },
    
    // 编辑角色
    handleEdit(role) {
      this.isEdit = true
      this.roleForm = { ...role }
      
      // 加载角色关联的菜单
      this.selectedMenus = this.roleMenus
        .filter(rm => rm.role_id === role.role_id)
        .map(rm => rm.menu_id)
      
      // 加载角色关联的员工
      this.selectedEmployees = this.employeeRoles
        .filter(er => er.role_id === role.role_id)
        .map(er => er.emp_id)
      
      this.dialogVisible = true
    },
    
    // 删除角色
    handleDelete(role) {
      if (confirm(`确定要删除角色 ${role.role_name} 吗？`)) {
        // 实际应该调用API删除角色
        const index = this.roles.findIndex(r => r.role_id === role.role_id)
        if (index !== -1) {
          this.roles.splice(index, 1)
          alert('删除成功')
        }
      }
    },
    
    // 查看角色详情
    handleDetail(role) {
      this.currentRole = role
      this.drawerVisible = true
    },
    
    // 打开权限分配对话框
    handlePermission(role) {
      this.currentRole = role
      this.permissionDialogVisible = true
    },
    
    // 保存权限设置
    savePermissions() {
      // 实际应该调用API保存权限设置
      alert('权限设置保存成功')
      this.permissionDialogVisible = false
    },
    
        // 验证表单
    validateForm() {
      this.errors = {}
      
      // 验证角色名称
      if (!this.roleForm.role_name) {
        this.errors.role_name = '请输入角色名称'
      }
      
      // 验证角色标识
      if (!this.roleForm.role_key) {
        this.errors.role_key = '请输入角色标识'
      }
      
      return Object.keys(this.errors).length === 0
    },
    
    // 提交表单
    submitForm() {
      if (!this.validateForm()) {
        return
      }
      
      if (this.isEdit) {
        // 编辑角色
        const index = this.roles.findIndex(r => r.role_id === this.roleForm.role_id)
        if (index !== -1) {
          // 更新角色信息
          const updatedRole = { ...this.roles[index], ...this.roleForm }
          updatedRole.update_time = new Date().toLocaleString()
          updatedRole.update_by = 'admin' // 实际应该是当前登录用户
          this.roles.splice(index, 1, updatedRole)
          
          // 更新角色菜单关联
          this.updateRoleMenus(updatedRole.role_id)
          
          // 更新角色员工关联
          this.updateRoleEmployees(updatedRole.role_id)
          
          alert('角色信息更新成功')
        }
      } else {
        // 添加新角色
        const newRole = {
          ...this.roleForm,
          role_id: this.roles.length + 1, // 实际应该由后端生成
          del_flag: '0',
          create_time: new Date().toLocaleString(),
          create_by: 'admin', // 实际应该是当前登录用户
          update_time: new Date().toLocaleString(),
          update_by: 'admin' // 实际应该是当前登录用户
        }
        this.roles.push(newRole)
        
        // 添加角色菜单关联
        this.updateRoleMenus(newRole.role_id)
        
        // 添加角色员工关联
        this.updateRoleEmployees(newRole.role_id)
        
        alert('角色添加成功')
      }
      this.dialogVisible = false
      this.resetForm()
    },
    
    // 重置表单
    resetForm() {
      this.roleForm = {
        role_id: null,
        role_name: '',
        role_key: '',
        status: '1',
        remark: ''
      }
      this.errors = {}
    }
  }
}
</script>

<style scoped>
.role-container {
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

.add-role-button {
  display: flex;
  align-items: center;
  background-color: #409eff;
  color: white;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.add-role-button:hover {
  background-color: #66b1ff;
}

.add-role-button i {
  margin-right: 5px;
  font-size: 18px;
}

.add-role-button p {
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
.role-table-container {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  margin-bottom: 20px;
}

.role-table {
  width: 100%;
  border-collapse: collapse;
}

.role-table th,
.role-table td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #ebeef5;
}

.role-table th {
  background-color: #f5f7fa;
  color: #606266;
  font-weight: 500;
  font-size: 14px;
}

.role-table tbody tr:hover {
  background-color: #f5f7fa;
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

.btn-permission {
  color: #e6a23c;
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

.permission-dialog {
  width: 800px;
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

/* 抽屉样式 */
.drawer-container {
  position: fixed;
  top: 0;
  right: -400px;
  width: 100%;
  height: 100%;
  z-index: 1000;
  transition: right 0.3s;
  pointer-events: none;
}

.drawer-container.drawer-open {
  right: 0;
  pointer-events: auto;
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
  pointer-events: auto;
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

/* 角色详情样式 */
.role-profile {
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
  margin-bottom: 20px;
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

/* 关联数据样式 */
.related-section {
  margin-top: 20px;
  padding-top: 20px;
  border-top: 1px solid #ebeef5;
}

.related-section h3 {
  margin: 0 0 15px 0;
  font-size: 16px;
  color: #303133;
}

.related-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.related-item {
  padding: 5px 10px;
  background-color: #f0f9eb;
  border-radius: 4px;
  color: #67c23a;
  font-size: 14px;
}

/* 权限树样式 */
.permission-tree {
  min-height: 300px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 15px;
}

.tree-placeholder {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 300px;
  color: #909399;
  background-color: #f5f7fa;
  border-radius: 4px;
}

/* 复选框组样式 */
.checkbox-group {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 5px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 10px;
  max-height: 150px;
  overflow-y: auto;
}

.checkbox-item {
  display: flex;
  align-items: center;
  width: calc(33.33% - 10px);
  margin-bottom: 5px;
  cursor: pointer;
}

.checkbox-item input {
  margin-right: 5px;
  width: auto;
}
</style>