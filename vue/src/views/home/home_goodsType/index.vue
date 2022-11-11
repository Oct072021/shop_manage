<template>
    <div>
        <el-form ref="searchForm" :model="searchTypes" style="display: flex;justify-content: center">

            <el-form-item class="search">
                <el-input v-model="searchTypes.goodsTypeName" placeholder="请输入类型名称"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="search">
                    <i class="el-icon-search"></i>
                    查询
                </el-button>
            </el-form-item>

        </el-form>

        <el-button type="primary" @click="addDialog = true">
            <i class="el-icon-plus"></i>
            新建
        </el-button>

        <el-dialog title="添加商品类型" :visible.sync="addDialog" width="30%" top="8vh">
            <el-form ref="addForm" :model="addTypes">

                <el-form-item label="商品类型">
                    <el-select v-model="addTypes.goodsTypeName" placeholder="请输入类型名称" style="width: 370px">
                        <el-option v-for="item in options" :value="item.label"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item style="margin-left: 270px">
                    <el-button type="danger" @click="addDialog=false">取消</el-button>
                    <el-button type="primary" @click="add">确定</el-button>
                </el-form-item>

            </el-form>
        </el-dialog>

        <el-table v-loading="loading" :data="types" border stripe
                  element-loading-text="拼命加载中"
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="rgba(0, 0, 0, 0.8)">

            <el-table-column prop="goodsTypeId" label="#"></el-table-column>

            <el-table-column prop="goodsTypeName" label="类型"></el-table-column>

            <el-table-column width="200px">
                <template slot-scope="scope">
                    <el-button size="mini" type="info" @click="editById(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="deleteById(scope.row)">删除</el-button>
                </template>
            </el-table-column>

        </el-table>

        <el-dialog title="修改" :visible.sync="editDialog" width="30%" top="8vh">
            <el-form ref="editForm" :model="editTypes">

                <el-form-item label="商品类型">
                    <el-select v-model="editTypes.goodsTypeName" placeholder="请输入类型名称" style="width: 370px">
                        <el-option v-for="item in options" :value="item.label"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button @click="editDialog = false">取 消</el-button>
                    <el-button type="primary" @click="update">确 定</el-button>
                </el-form-item>

            </el-form>
        </el-dialog>

        <el-pagination background layout="prev, pager, next" :page-size="size" :total="total"
                       @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    import {findAllTypes, add, editById, deleteById, update} from "@/api/goodsType";
    import {page} from "@/api/page";

    export default {
        name: "home_goodsType",
        data() {
            return {
                options: [
                    {label: "数码产品", typeNum: 1},
                    {label: "生活用品", typeNum: 2},
                    {label: "衣装服饰", typeNum: 3},
                    {label: "医药保健", typeNum: 4},
                    {label: "图书文娱", typeNum: 5},
                    {label: "食品饮料", typeNum: 6},
                    {label: "水果蔬菜", typeNum: 7},
                    {label: "线上充值", typeNum: 8}
                ],
                types: null,
                loading: true,
                addDialog: false,
                editDialog: false,
                total: 0,
                size: 9,
                searchTypes: {
                    goodsTypeName: ''
                },
                addTypes: {
                    goodsTypeName: ''
                },
                editTypes: {
                    goodsTypeName: ''
                }
            }
        },
        methods: {
            search() {
                let that = this
                this.$refs.searchForm.validate(async (valid) => {
                    const obj = await findAllTypes(this.size, this.searchTypes)
                    if (obj != null) {
                        that.types = obj.list
                        that.total = obj.num
                        that.$message({
                            message: '成功为您查询到以下数据!',
                            type: 'success'
                        })
                    } else {
                        that.$message.error('错了，查询失败!')
                    }
                })
            },

            add() {
                let that = this
                this.$refs.addForm.validate(async (valid) => {
                    const obj = await add(this.addTypes)
                    if (obj != null) {
                        that.$message({
                            message: '添加成功啦!',
                            type: 'success'
                        })
                        location.reload()
                    } else {
                        that.$message.error('错了，添加数据失败!');
                    }
                })
            },

            async editById(obj) {
                let that = this
                this.editDialog = true
                const object = await editById(obj.goodsTypeId)
                if (object != null) {
                    that.editTypes = object
                } else {
                    that.$message.error('错了哦，丢失数据');
                }
            },

            deleteById(obj) {
                console.log(obj)
                let that = this
                this.$confirm('是否确定删除' + obj.title + '?', '删除数据', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    const bool = await deleteById(obj.goodsTypeId)
                    if (bool == true) {
                        that.$alert(obj.name + '删除成功!', '删除数据', {
                            confirmButtonText: '确定',
                            callback: action => {
                                //重载界面,刷新页面数据
                                location.reload()
                            }
                        })
                    }
                })
            },

            update() {
                let that = this
                this.$refs.editForm.validate(async (valid) => {
                    if (valid) {
                        const bool = await update(this.editTypes)
                        if (bool == true) {
                            that.$message({
                                message: '修改成功啦!',
                                type: 'success'
                            })
                            location.reload()
                        } else {
                            that.$message.error('错了,修改失败');
                        }
                    }
                })
            },

            async page(currentPage) {
                let that = this
                const obj = await page(currentPage, this.size, this.searchTypes)
                if (obj != null) {
                    that.types = obj.list
                }
            }
        },
        async created() {
            let that = this
            const obj = await findAllTypes(this.size, this.searchTypes)
            if (obj != null) {
                that.types = obj.list
                that.total = obj.num
                that.loading = false
                that.$message({
                    message: '加载数据成功!',
                    type: 'success'
                })
            } else {
                that.$message.error('服务器与服务端丢失,请检查一下网络!')
            }
        }
    }
</script>

<style lang="less" scoped>
  .search {
    margin-right: 15px;
    width: 400px;
  }
</style>