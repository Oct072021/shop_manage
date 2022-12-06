<template>
    <div>
        <el-form ref="searchForm" :model="searchUser" style="display: flex;justify-content: center">

            <el-form-item class="form_item" style="width: 400px;margin-right: 10px">
                <el-input v-model="searchUser.userName" placeholder="请输入用户名字"></el-input>
            </el-form-item>

            <el-form-item class="form_item" style="width: 100px">
                <el-button type="primary" @click="search">
                    <i class="el-icon-search"></i>
                    查询
                </el-button>
            </el-form-item>

        </el-form>

        <el-button type="primary" @click="addDialog=true" style="margin-top: 10px">
            <i class="el-icon-plus"></i>
            新建
        </el-button>

        <el-dialog title="添加用户" :visible.sync="addDialog" width="30%" top="8vh">

            <el-form ref="addForm" :model="addUser">

                <el-form-item label="用户名称">
                    <el-input v-model="addUser.userName" placeholder="请输入用户名称"></el-input>
                </el-form-item>

                <el-form-item label="手机号码">
                    <el-input v-model="addUser.phone" placeholder="请输入手机号码"></el-input>
                </el-form-item>

                <el-form-item label="用户密码">
                    <el-input v-model="addUser.password" placeholder="请输入密码"></el-input>
                </el-form-item>

                <el-form-item label="用户邮箱">
                    <el-input v-model="addUser.userEmail" placeholder="请输入邮箱"></el-input>
                </el-form-item>

                <el-form-item style="margin-left: 270px">
                    <el-button type="danger" @click="addDialog=false">取消</el-button>
                    <el-button type="primary" @click="add">确定</el-button>
                </el-form-item>

            </el-form>

        </el-dialog>

        <el-table v-model="loading" :data="user" style="width: 100%" stripe border
                  element-loading-text="拼命加载中"
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="rgba(0, 0, 0, 0.8)">

            <el-table-column prop="userId" label="#" width="180"></el-table-column>

            <el-table-column prop="userName" label="姓名" width="180"></el-table-column>

            <el-table-column prop="phone" label="手机号码"></el-table-column>

            <el-table-column prop="password" label="密码"></el-table-column>

            <el-table-column prop="userEmail" label="邮箱"></el-table-column>

            <el-table-column prop="signInData" label="注册时间"></el-table-column>

            <el-table-column>
                <template slot-scope="scope">
                    <el-button size="mini" type="info" @click="editById(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="deleteById(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="修改用户" :visible.sync="editDialog" width="30%" top="8vh">

            <el-form ref="editForm" :model="editUser">

                <el-form-item label="用户名称">
                    <el-input v-model="editUser.userName" placeholder="请输入用户名称"></el-input>
                </el-form-item>

                <el-form-item label="手机号码">
                    <el-input v-model="editUser.phone" placeholder="请输入手机号码"></el-input>
                </el-form-item>

                <el-form-item label="用户密码">
                    <el-input v-model="editUser.password" placeholder="请输入密码"></el-input>
                </el-form-item>

                <el-form-item label="用户邮箱">
                    <el-input v-model="editUser.userEmail" placeholder="请输入邮箱"></el-input>
                </el-form-item>

                <el-form-item style="margin-left: 270px">
                    <el-button type="danger" @click="editDialog = false">取消</el-button>
                    <el-button type="primary" @click="edit">确定</el-button>
                </el-form-item>

            </el-form>

        </el-dialog>

        <el-pagination background layout="prev, pager, next" :page-size="size" :total="total"
                       @current-change="page"></el-pagination>
    </div>
</template>

<script>
    import {add, deleteById, edit, editById, findAllUser} from "@/api/user";
    import {userPage} from "@/api/page";

    export default {
        name: "home_user",
        data() {
            return {
                searchUser: {
                    userName: ''
                },
                addUser: {
                    userName: '',
                    phone: '',
                    password: '',
                    userEmail: '',
                },
                addDialog: false,
                editDialog: false,
                user: null,
                total: 0,
                size: 8,
                loading: true,
                editUser: {
                    userId: '',
                    userName: '',
                    phone: '',
                    userEmail: '',
                    password: ''
                },
            }
        },
        methods: {
            //查询
            search() {
                this.$refs.searchForm.validate(async valid => {
                    if (valid) {
                        const res = await findAllUser(this.size, this.searchUser)
                        if (res.status === 200) {
                            this.user = res.data.list
                            this.total = res.data.num
                            this.$message({
                                message: '查询成功',
                                type: 'success'
                            })
                        } else {
                            this.$message.error('哎呀!服务器出现错了')
                        }
                    }
                })
            },

            //添加用户
            add() {
                this.$refs.addForm.validate(async valid => {
                    if (valid) {
                        const res = await add(this.addUser)
                        if (res.status === 200) {
                            this.$message({
                                message: '添加用户成功',
                                type: 'success'
                            })
                            location.reload()
                        } else {
                            this.$message.error('添加失败！')
                        }
                    }
                })
            },

            //根据id查询用户数据并渲染
            async editById(obj) {
                this.editDialog = true
                const res = await editById(obj.userId)
                if (res.status === 200) {
                    this.editUser = res.data
                } else {
                    this.$message.error('错了，丢失数据!')
                    this.edituser = false
                }
            },

            //删除用户
            deleteById(obj) {
                this.$confirm('是否确认删除' + obj.userName + '?', 'delete', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                }).then(async () => {
                    const res = await deleteById(obj.userId)
                    if (res.status === 200) {
                        await this.$alert(obj.userName + '删除成功!', '删除数据', {
                            confirmButtonText: '确定',
                            callback: action => {
                                location.reload()
                            }
                        })
                    } else {
                        await this.$alert("删除失败!")
                    }
                })
            },

            //修改用户信息
            edit() {
                this.$refs.editForm.validate(async valid => {
                    if (valid) {
                        const res = await edit(this.editUser)
                        if (res.status === 200) {
                            this.$message({
                                message: '修改数据成功!',
                                type: 'success'
                            })
                            location.reload()
                        } else {
                            this.$message.error('修改用户数据失败,请检查下手机或者邮箱!')
                        }
                    }
                })
            },

            //分页
            async page(currentPage) {
                const res = await userPage(currentPage, this.size, this.user)
                if (res.status === 200) {
                    this.user = res.data.list
                }
            }
        },
        //初始化数据，渲染
        async created() {
            const res = await findAllUser(this.size, this.searchUser)
            if (res.status === 200) {
                this.user = res.data.list
                this.total = res.data.num
                this.loading = false
                this.$message({
                    message: '加载数据成功!',
                    type: 'success'
                })
            } else {
                this.$message.error('错了，服务器与服务端丢失了!')
            }
        }
    }
</script>

<style lang="less" scoped>
  .form_item {
    display: inline-block;
  }
</style>