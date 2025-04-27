<template>
  <div class="permission-container">
    <!-- 顶部部分，标题和添加按钮 -->
    <div class="index-section">
      <div class="page-title">
        <h1>权限管理</h1>
      </div>
      <div class="add-permission-button" @click="showAddDialog">
        <i class="bx bx-plus"></i>
        <p>添加权限</p>
      </div>
    </div>

    <!-- 搜索和筛选部分 -->
    <div class="search-filter-section">
      <div class="search-box">
        <i class="bx bx-search"></i>
        <input 
          type="text" 
          v-model="searchQuery" 
          placeholder="搜索权限名称/权限码" 
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

    <!-- 权限列表表格 -->
    <div class="permission-table-container">
      <table class="permission-table">
        <thead>
          <tr>
            <th width="50"><input type="checkbox" /></th>
            <th width="100">权限ID</th>
            <th width="150">权限名称</th>
            <th width="200">权限标识</th>
            <th width="100">状态</th>
            <th width="150">创建时间</th>
            <th width="150">更新时间</th>
            <th width="150">操作</th>
          </tr>
        </thead>
        <tbody v-if="paginatedPermissions.length > 0">
          <tr v-for="permission in paginatedPermissions" :key="permission.permission_id">
            <td><input type="checkbox" /></td>
            <td>{{ permission.permission_id }}</td>
            <td>{{ permission.permission_name }}</td>
            <td>{{ permission.permission_code }}</td>
            <td>
              <span class="status-badge" :class="permission.status === '1' ? 'active' : 'inactive'">
                {{ permission.status === '1' ? '启用' : '禁用' }}
              </span>
            </td>
            <td>{{ permission.create_time }}</td>
            <td>{{ permission.update_time }}</td>
            <td class="action-buttons">
              <button class="btn-edit" @click="handleEdit(permission)">
                <i class="bx bx-edit"></i>
              </button>
              <button class="btn-detail" @click="handleDetail(permission)">
                <i class="bx bx-detail"></i>
              </button>
              <button class="btn-delete" @click="handleDelete(permission)">
                <i class="bx bx-trash"></i>
              </button>
              <button class="btn-role" @click="handleRoleAssociation(permission)">
                <i class="bx bx-link"></i>
              </button>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr>
            <td colspan="8" class="no-data">暂无权限数据</td>
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

    <!-- 添加/编辑权限对话框 -->
    <div class="dialog-overlay" v-if="dialogVisible" @click.self="dialogVisible = false">
      <div class="dialog-content">
        <div class="dialog-header">
          <h3>{{ isEdit ? '编辑权限' : '添加权限' }}</h3>
          <button class="close-btn" @click="dialogVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="dialog-body">
          <div class="form-row">
            <div class="form-group">
              <label>权限名称</label>
              <input type="text" v-model="permissionForm.permission_name" placeholder="请输入权限名称" />
              <div class="error-message" v-if="errors.permission_name">{{ errors.permission_name }}</div>
            </div>
            <div class="form-group">
              <label>权限标识</label>
              <input type="text" v-model="permissionForm.permission_code" placeholder="请输入权限标识" />
              <div class="error-message" v-if="errors.permission_code">{{ errors.permission_code }}</div>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group">
              <label>状态</label>
              <div class="radio-group">
                <label>
                  <input type="radio" v-model="permissionForm.status" value="1" />
                  <span>启用</span>
                </label>
                <label>
                  <input type="radio" v-model="permissionForm.status" value="0" />
                  <span>禁用</span>
                </label>
              </div>
            </div>
          </div>
          <div class="form-row">
            <div class="form-group full-width">
              <label>备注</label>
              <textarea v-model="permissionForm.remark" placeholder="请输入备注信息"></textarea>
            </div>
          </div>
        </div>
        <div class="dialog-footer">
          <button class="cancel-btn" @click="dialogVisible = false">取消</button>
          <button class="submit-btn" @click="savePermission">保存</button>
        </div>
      </div>
    </div>

    <!-- 权限详情抽屉 -->
    <div class="drawer-overlay" v-if="drawerVisible" @click.self="drawerVisible = false">
      <div class="drawer-content">
        <div class="drawer-header">
          <h3>权限详情</h3>
          <button class="close-btn" @click="drawerVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="drawer-body" v-if="currentPermission">
          <div class="profile-section">
            <div class="profile-info">
              <h2>{{ currentPermission.permission_name }}</h2>
              <p>{{ currentPermission.permission_code }}</p>
            </div>
            <div class="profile-details">
              <div class="detail-item">
                <span class="detail-label">权限ID</span>
                <span class="detail-value">{{ currentPermission.permission_id }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">状态</span>
                <span class="detail-value">
                  <span class="status-badge" :class="currentPermission.status === '1' ? 'active' : 'inactive'">
                    {{ currentPermission.status === '1' ? '启用' : '禁用' }}
                  </span>
                </span>
              </div>
              <div class="detail-item">
                <span class="detail-label">创建时间</span>
                <span class="detail-value">{{ currentPermission.create_time }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">创建人</span>
                <span class="detail-value">{{ currentPermission.create_by }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">更新时间</span>
                <span class="detail-value">{{ currentPermission.update_time }}</span>
              </div>
              <div class="detail-item">
                <span class="detail-label">更新人</span>
                <span class="detail-value">{{ currentPermission.update_by }}</span>
              </div>
              <div class="detail-item full-width">
                <span class="detail-label">备注</span>
                <span class="detail-value">{{ currentPermission.remark || '暂无备注' }}</span>
              </div>
            </div>

            <!-- 关联角色列表 -->
            <div class="related-section">
              <h3>关联角色</h3>
              <div class="related-list" v-if="currentPermissionRoles.length > 0">
                <div class="related-item" v-for="role in currentPermissionRoles" :key="role.role_id">
                  <span>{{ role.role_name }}</span>
                </div>
              </div>
              <div class="no-data" v-else>暂无关联角色</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 角色关联对话框 -->
    <div class="dialog-overlay" v-if="roleDialogVisible" @click.self="roleDialogVisible = false">
      <div class="dialog-content role-dialog">
        <div class="dialog-header">
          <h3>关联角色 - {{ currentPermission ? currentPermission.permission_name : '' }}</h3>
          <button class="close-btn" @click="roleDialogVisible = false">
            <i class="bx bx-x"></i>
          </button>
        </div>
        <div class="dialog-body">
          <div class="selector-search">
            <i class="bx bx-search"></i>
            <input type="text" v-model="roleSearchQuery" placeholder="搜索角色" />
          </div>
          
          <!-- 已选角色预览 -->
          <div class="selected-preview" v-if="selectedRoles.length > 0">
            <div class="preview-title">已选择 ({{ selectedRoles.length }})</div>
            <div class="selected-tags">
              <div class="selected-tag" v-for="roleId in selectedRoles" :key="'selected-'+roleId">
                <span>{{ getRoleName(roleId) }}</span>
                <button @click="removeSelectedRole(roleId)">×</button>
              </div>
            </div>
          </div>
          
          <!-- 全选功能 -->
      <div class="select-all-container">
        <label class="select-all-label">
          <input type="checkbox" :checked="isAllRolesSelected" @change="selectAllRoles" />
          <span>全选</span>
        </label>
        <span class="selected-count" v-if="selectedRoles.length > 0">已选择 {{ selectedRoles.length }} 项</span>
      </div>
      
      <div class="role-list">
        <div class="role-item" v-for="role in filteredRoles" :key="role.role_id">
          <label class="role-checkbox-label">
            <input type="checkbox" :value="role.role_id" v-model="selectedRoles" />
            <span class="role-name">{{ role.role_name }}</span>
          </label>
        </div>
        <div class="no-data" v-if="filteredRoles.length === 0">
          没有找到匹配的角色
        </div>
      </div>
        </div>
        <div class="dialog-footer">
          <button class="cancel-btn" @click="roleDialogVisible = false">取消</button>
          <button class="submit-btn" @click="saveRoleAssociations">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Permission",
  data() {
    return {
      // 权限数据
      permissions: [
        {
          permission_id: 1,
          permission_code: 'system:admin',
          permission_name: '系统管理',
          status: '1',
          create_time: '2023-01-01 08:00:00',
          create_by: 'admin',
          update_time: '2023-05-15 09:30:45',
          update_by: 'admin',
          remark: '系统管理权限'
        },
        {
          permission_id: 2,
          permission_code: 'user:view',
          permission_name: '用户查看',
          status: '1',
          create_time: '2023-01-05 09:00:00',
          create_by: 'admin',
          update_time: '2023-04-10 11:15:20',
          update_by: 'admin',
          remark: '用户查看权限'
        },
        {
          permission_id: 3,
          permission_code: 'user:add',
          permission_name: '用户添加',
          status: '1',
          create_time: '2023-01-10 10:30:00',
          create_by: 'admin',
          update_time: '2023-03-15 16:40:30',
          update_by: 'admin',
          remark: '用户添加权限'
        },
        {
          permission_id: 4,
          permission_code: 'user:edit',
          permission_name: '用户编辑',
          status: '1',
          create_time: '2023-01-15 14:20:00',
          create_by: 'admin',
          update_time: '2023-02-20 15:10:25',
          update_by: 'admin',
          remark: '用户编辑权限'
        },
        {
          permission_id: 5,
          permission_code: 'user:delete',
          permission_name: '用户删除',
          status: '1',
          create_time: '2023-01-20 11:45:00',
          create_by: 'admin',
          update_time: '2023-02-25 10:05:15',
          update_by: 'admin',
          remark: '用户删除权限'
        }
      ],
      
      // 角色数据
      roles: [
        {
          role_id: 1,
          role_name: '超级管理员',
          role_key: 'admin',
          status: '1',
          del_flag: '0',
          create_time: '2023-01-01 08:00:00',
          create_by: 'admin',
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
      
      // 角色权限关联数据
      rolePermissions: [
        { role_id: 1, permission_id: 1 },
        { role_id: 1, permission_id: 2 },
        { role_id: 1, permission_id: 3 },
        { role_id: 1, permission_id: 4 },
        { role_id: 1, permission_id: 5 },
        { role_id: 2, permission_id: 2 },
        { role_id: 3, permission_id: 2 }
      ],
      
      // 角色关联对话框
      roleDialogVisible: false,
      roleSearchQuery: '',
      selectedRoles: [],
      
      // 状态变量
      loading: false,
      searchQuery: '',
      filterStatus: '',
      currentPage: 1,
      pageSize: 10,
      dialogVisible: false,
      drawerVisible: false,
      isEdit: false,
      currentPermission: null,
      
      // 表单数据
      permissionForm: {
        permission_id: null,
        permission_name: '',
        permission_code: '',
        status: '1',
        remark: ''
      },
      
      // 表单验证错误信息
      errors: {}
    }
  },
  computed: {
    // 过滤后的权限列表
    filteredPermissions() {
      return this.permissions.filter(permission => {
        const matchQuery = this.searchQuery ? 
          (permission.permission_name.includes(this.searchQuery) || 
           permission.permission_code.includes(this.searchQuery)) : true
        
        const matchStatus = this.filterStatus ? permission.status === this.filterStatus : true
        
        return matchQuery && matchStatus
      })
    },
    
    // 分页后的权限列表
    paginatedPermissions() {
      const start = (this.currentPage - 1) * this.pageSize
      const end = start + this.pageSize
      return this.filteredPermissions.slice(start, end)
    },
    
    // 总页数
    totalPages() {
      return Math.ceil(this.filteredPermissions.length / this.pageSize)
    },
    
    // 当前权限关联的角色
    currentPermissionRoles() {
      if (!this.currentPermission) return []
      
      const roleIds = this.rolePermissions
        .filter(rp => rp.permission_id === this.currentPermission.permission_id)
        .map(rp => rp.role_id)
      
      return this.roles.filter(role => roleIds.includes(role.role_id))
    },
    
    // 过滤后的角色列表
    filteredRoles() {
      if (!this.roleSearchQuery) {
        return this.roles;
      }
      
      return this.roles.filter(role => 
        role.role_name.toLowerCase().includes(this.roleSearchQuery.toLowerCase()) ||
        role.role_key.toLowerCase().includes(this.roleSearchQuery.toLowerCase())
      );
    },
    
    // 是否全部角色都被选中
    isAllRolesSelected() {
      return this.filteredRoles.length > 0 && this.selectedRoles.length === this.filteredRoles.length;
    }
  },
  methods: {
    // 搜索处理
    handleSearch() {
      this.currentPage = 1
    },
    
    // 每页显示数量变化处理
    handleSizeChange() {
      this.currentPage = 1
    },
    
    // 显示添加对话框
    showAddDialog() {
      this.isEdit = false
      this.resetForm()
      this.dialogVisible = true
    },
    
    // 编辑权限
    handleEdit(permission) {
      this.isEdit = true
      this.permissionForm = { ...permission }
      this.dialogVisible = true
    },
    
    // 查看权限详情
    handleDetail(permission) {
      this.currentPermission = permission
      this.drawerVisible = true
    },
    
    // 删除权限
    handleDelete(permission) {
      if (confirm(`确定要删除权限 "${permission.permission_name}" 吗？`)) {
        // 这里应该调用删除API
        this.permissions = this.permissions.filter(p => p.permission_id !== permission.permission_id)
      }
    },
    
    // 保存权限
    savePermission() {
      // 表单验证
      this.errors = {}
      
      if (!this.permissionForm.permission_name) {
        this.errors.permission_name = '权限名称不能为空'
      }
      
      if (!this.permissionForm.permission_code) {
        this.errors.permission_code = '权限标识不能为空'
      }
      
      // 如果有错误，不继续执行
      if (Object.keys(this.errors).length > 0) {
        return
      }
      
      // 模拟API调用
      if (this.isEdit) {
        // 更新现有权限
        const index = this.permissions.findIndex(p => p.permission_id === this.permissionForm.permission_id)
        if (index !== -1) {
          this.permissions[index] = {
            ...this.permissionForm,
            update_time: new Date().toLocaleString(),
            update_by: 'current_user'
          }
        }
      } else {
        // 添加新权限
        const newPermission = {
          ...this.permissionForm,
          permission_id: Math.max(...this.permissions.map(p => p.permission_id)) + 1,
          create_time: new Date().toLocaleString(),
          create_by: 'current_user',
          update_time: new Date().toLocaleString(),
          update_by: 'current_user'
        }
        this.permissions.push(newPermission)
      }
      
      // 关闭对话框
      this.dialogVisible = false
      this.resetForm()
    },
    
    // 重置表单
    resetForm() {
      this.permissionForm = {
        permission_id: null,
        permission_name: '',
        permission_code: '',
        status: '1',
        remark: ''
      }
      this.errors = {}
    },
    
    // 处理角色关联
    handleRoleAssociation(permission) {
      this.currentPermission = permission;
      
      // 获取当前权限关联的角色ID
      this.selectedRoles = this.rolePermissions
        .filter(rp => rp.permission_id === permission.permission_id)
        .map(rp => rp.role_id);
      
      this.roleDialogVisible = true;
    },
    
    // 全选/取消全选角色
    selectAllRoles() {
      if (this.isAllRolesSelected) {
        this.selectedRoles = [];
      } else {
        this.selectedRoles = this.filteredRoles.map(role => role.role_id);
      }
    },
    
    // 获取角色名称
    getRoleName(roleId) {
      const role = this.roles.find(r => r.role_id === roleId);
      return role ? role.role_name : '';
    },
    
    // 移除已选角色
    removeSelectedRole(roleId) {
      this.selectedRoles = this.selectedRoles.filter(id => id !== roleId);
    },
    
    // 保存角色关联
    saveRoleAssociations() {
      if (!this.currentPermission) return;
      
      const permissionId = this.currentPermission.permission_id;
      
      // 删除原有关联
      this.rolePermissions = this.rolePermissions.filter(rp => rp.permission_id !== permissionId);
      
      // 添加新关联
      this.selectedRoles.forEach(roleId => {
        this.rolePermissions.push({
          permission_id: permissionId,
          role_id: roleId
        });
      });
      
      // 关闭对话框
      this.roleDialogVisible = false;
      alert('角色关联保存成功');
    }
  }
}
</script>

<style scoped>
.permission-container {
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

.add-permission-button {
  display: flex;
  align-items: center;
  background-color: #409eff;
  color: white;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.add-permission-button:hover {
  background-color: #66b1ff;
}

.add-permission-button i {
  margin-right: 5px;
  font-size: 18px;
}

.add-permission-button p {
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
}

.filter-box {
  display: flex;
  gap: 10px;
}

.filter-box select {
  padding: 8px 10px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 14px;
  background-color: #fff;
}

/* 表格样式 */
.permission-table-container {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  overflow-x: auto;
}

.permission-table {
  width: 100%;
  border-collapse: collapse;
}

.permission-table th,
.permission-table td {
  padding: 12px 15px;
  text-align: left;
  border-bottom: 1px solid #ebeef5;
}

.permission-table th {
  background-color: #f5f7fa;
  color: #606266;
  font-weight: 500;
}

.permission-table tr:hover {
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
  border: none;
  background: none;
  cursor: pointer;
  padding: 5px;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.action-buttons button:hover {
  background-color: #f0f2f5;
}

.btn-edit i {
  color: #409eff;
}

.btn-detail i {
  color: #909399;
}

.btn-delete i {
  color: #f56c6c;
}

.btn-role i {
  color: #67c23a;
}

.selector-search {
  position: relative;
  margin-bottom: 15px;
}

.selector-search i {
  position: absolute;
  left: 12px;
  top: 50%;
  transform: translateY(-50%);
  color: #909399;
}

.selector-search input {
  width: 100%;
  padding: 10px 10px 10px 35px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 14px;
  transition: border-color 0.3s;
}

.selector-search input:focus {
  outline: none;
  border-color: #409eff;
}

.select-all-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
  padding: 8px 0;
  border-bottom: 1px solid #ebeef5;
}

.select-all-label {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.select-all-label input {
  margin-right: 8px;
}

.selected-count {
  font-size: 13px;
  color: #909399;
}

.selected-preview {
  background-color: #f5f7fa;
  border-radius: 4px;
  padding: 12px;
  margin-bottom: 15px;
}

.preview-title {
  font-size: 14px;
  color: #606266;
  margin-bottom: 10px;
}

.selected-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.selected-tag {
  display: inline-flex;
  align-items: center;
  background-color: #ecf5ff;
  color: #409eff;
  border-radius: 4px;
  padding: 5px 10px;
  font-size: 12px;
  transition: all 0.3s;
}

.selected-tag:hover {
  background-color: #d9ecff;
}

.selected-tag button {
  background: none;
  border: none;
  color: #409eff;
  cursor: pointer;
  margin-left: 5px;
  font-size: 14px;
}

.role-list {
  max-height: 300px;
  overflow-y: auto;
  border: 1px solid #ebeef5;
  border-radius: 4px;
}

.role-item {
  padding: 10px 15px;
  border-bottom: 1px solid #ebeef5;
}

.role-item:last-child {
  border-bottom: none;
}

.role-checkbox-label {
  display: flex;
  align-items: center;
  cursor: pointer;
  width: 100%;
}

.role-checkbox-label input[type="checkbox"] {
  margin-right: 8px;
}

.role-name {
  color: #303133;
  font-size: 14px;
}

.role-dialog {
  width: 500px;
  max-width: 90vw;
}

.no-data {
  text-align: center;
  color: #909399;
  padding: 30px 0;
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
  border: 1px solid #dcdfe6;
  background-color: #fff;
  padding: 5px 10px;
  cursor: pointer;
  transition: all 0.3s;
}

.page-btn:first-child {
  border-radius: 4px 0 0 4px;
}

.page-btn:last-child {
  border-radius: 0 4px 4px 0;
}

.page-btn:disabled {
  cursor: not-allowed;
  color: #c0c4cc;
}

.page-info {
  padding: 0 15px;
  border-top: 1px solid #dcdfe6;
  border-bottom: 1px solid #dcdfe6;
  height: 28px;
  line-height: 28px;
  color: #606266;
}

/* 对话框样式 */
.dialog-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.dialog-content {
  background-color: #fff;
  border-radius: 8px;
  width: 500px;
  max-width: 90%;
  max-height: 90vh;
  display: flex;
  flex-direction: column;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
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
  border: none;
  background: none;
  font-size: 20px;
  cursor: pointer;
  color: #909399;
}

.dialog-body {
  padding: 20px;
  overflow-y: auto;
}

.form-row {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
}

.form-group {
  flex: 1;
}

.form-group.full-width {
  width: 100%;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  color: #606266;
}

.form-group input,
.form-group select,
.form-group textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  font-size: 14px;
}

.form-group textarea {
  min-height: 80px;
  resize: vertical;
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
  width: auto;
  margin-right: 5px;
}

.error-message {
  color: #f56c6c;
  font-size: 12px;
  margin-top: 5px;
}

.dialog-footer {
  padding: 10px 20px 20px;
  text-align: right;
  border-top: 1px solid #ebeef5;
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

/* 抽屉样式 */
.drawer-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  /* background-color: rgba(0, 0, 0, 0.5); */
  z-index: 1000;
}

.drawer-content {
  position: fixed;
  top: 0;
  right: 0;
  width: 400px;
  height: 100%;
  background-color: #fff;
  box-shadow: -2px 0 8px rgba(0, 0, 0, 0.15);
  z-index: 1001;
  display: flex;
  flex-direction: column;
  animation: slide-in 0.3s ease;
}

@keyframes slide-in {
  from { transform: translateX(100%); }
  to { transform: translateX(0); }
}

.drawer-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  border-bottom: 1px solid #ebeef5;
}

.drawer-header h3 {
  margin: 0;
  font-size: 18px;
  color: #303133;
}

.drawer-body {
  padding: 20px;
  overflow-y: auto;
  flex: 1;
}

.profile-section {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.profile-info {
  margin-bottom: 10px;
}

.profile-info h2 {
  margin: 0 0 5px 0;
  font-size: 20px;
  color: #303133;
}

.profile-info p {
  margin: 0;
  color: #909399;
  font-size: 14px;
}

.profile-details {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
  background-color: #f9f9f9;
  padding: 15px;
  border-radius: 8px;
}

.detail-item {
  width: calc(50% - 8px);
  display: flex;
  flex-direction: column;
}

.detail-item.full-width {
  width: 100%;
}

.detail-label {
  font-size: 12px;
  color: #909399;
  margin-bottom: 5px;
}

.detail-value {
  color: #303133;
  font-size: 14px;
}

.related-section {
  margin-top: 20px;
}

.related-section h3 {
  font-size: 16px;
  color: #303133;
  margin-bottom: 10px;
  padding-bottom: 5px;
  border-bottom: 1px solid #ebeef5;
}

.related-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.related-item {
  background-color: #ecf5ff;
  padding: 5px 10px;
  border-radius: 4px;
  font-size: 12px;
  color: #409eff;
}

/* 响应式样式 */
@media screen and (max-width: 768px) {
  .form-row {
    flex-direction: column;
    gap: 10px;
  }
  
  .drawer-content {
    width: 100%;
  }
  
  .selector-item {
    width: 100%;
  }
  
  .detail-item {
    width: 100%;
  }
}
</style>