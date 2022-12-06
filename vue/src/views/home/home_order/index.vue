<template>
    <div>
        <el-form ref="searchForm" :model="searchOrders" label-width="15px"
                 style="display: flex;justify-content: center">
            <el-form-item>
                <el-input v-model="searchOrders.userName" placeholder="请输入用户名字"></el-input>
            </el-form-item>

            <el-form-item style="width: 130px">
                <el-select v-model="searchOrders.goodsTypeId" placeholder="商品类型">
                    <el-option v-for="item in options" :label="item.label" :value="item.typeNum"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-input v-model="searchOrders.goodsName" placeholder="请输入商品名字"></el-input>
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

        <el-dialog title="添加订单" :visible.sync="addDialog" width="30%" top="8vh">
            <el-form ref="addForm" :model="addOrders">
                <el-form-item label="选择用户:">
                    <el-select v-model="addOrders.userId" placeholder="请选择用户" @visible-change="getUser()">
                        <el-option v-for="item in users" :label="item.userName" :value="item.userId"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="选择商品:">
                    <el-select v-model="addOrders.goodsId" placeholder="请选择用户" @visible-change="getGoods()">
                        <el-option v-for="item in goods" :label="item.goodsName" :value="item.goodsId"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="商品数量:">
                    <el-input-number v-model="addOrders.orderCount" :min="0" style="width: 194px"></el-input-number>
                </el-form-item>

                <el-form-item label="订单地址:">
                    <el-input v-model="addOrders.orderAddress" placeholder="输入地址"></el-input>
                </el-form-item>

                <el-form-item style="margin-left: 260px">
                    <el-button type="danger" @click="addDialog = false">取 消</el-button>
                    <el-button type="primary" @click="add">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-table v-loading="loading" :data="orders" border stripe
                  element-loading-text="拼命加载中"
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="rgba(0, 0, 0, 0.8)">

            <el-table-column label="#" prop="orderId"></el-table-column>

            <el-table-column label="订单数量" prop="orderCount"></el-table-column>

            <el-table-column label="订单总价" prop="orderPrice"></el-table-column>

            <el-table-column label="用户地址" prop="orderAddress"></el-table-column>

            <el-table-column label="所属用户" prop="usersList">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.usersList">
                        {{ item.userName }}
                    </span>
                </template>
            </el-table-column>

            <el-table-column label="联系方式" prop="usersList">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.usersList">
                        {{ item.phone }}
                    </span>
                </template>
            </el-table-column>


            <el-table-column label="商品名字" prop="goodsList">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.goodsList">
                        {{ item.goodsName }}
                    </span>
                </template>
            </el-table-column>

            <el-table-column label="商品图片" prop="goodsList">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.goodsList">
                        <img :src="item.goodsImg" style="height: 44px; border:1px solid red"/>
                    </span>
                </template>
            </el-table-column>


            <el-table-column label="商品价格" prop="goodsList">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.goodsList">
                        {{ item.price }}
                    </span>
                </template>
            </el-table-column>

            <el-table-column label="是否在售" prop="goodsList">
                <template slot-scope="scope">
                    <p v-for="item in scope.row.goodsList">
                        <span :style="{ color: item.onSale === 'yes' ? '#ff0000': '#808080'}">
                            {{ item.onSale === 'yes' ? '在售' : '售完' }}
                        </span>
                    </p>
                </template>
            </el-table-column>

            <el-table-column width="150">
                <template slot-scope="scope">
                    <el-button size="mini" type="info" @click="editById(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="deleteById(scope.row)">删除</el-button>
                </template>
            </el-table-column>

        </el-table>

        <el-dialog title="修改订单" :visible.sync="editDialog" width="30%" top="8vh">
            <el-form ref="editForm" :model="editOrders">

                <el-form-item label="选择用户:">
                    <el-select v-model="editOrders.userId" placeholder="请选择用户" @visible-change="getUser()">
                        <el-option v-for="item in users" :label="item.userName" :value="item.userId"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="选择商品:">
                    <el-select v-model="editOrders.goodsId" placeholder="请选择用户" @visible-change="getGoods()">
                        <el-option v-for="item in goods" :label="item.goodsName" :value="item.goodsId"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="商品数量:">
                    <el-input-number v-model="editOrders.orderCount" :min="0" style="width: 194px"></el-input-number>
                </el-form-item>

                <el-form-item label="订单地址:">
                    <el-input v-model="editOrders.orderAddress" placeholder="输入地址"></el-input>
                </el-form-item>

                <el-form-item style="margin-left: 260px">
                    <el-button type="danger" @click="editDialog = false">取 消</el-button>
                    <el-button type="primary" @click="update">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-pagination background layout="prev, pager, next" :page-size="size" :total="total"
                       @current-change="page"></el-pagination>
    </div>
</template>

<script>
    import {add, deleteById, editById, findAllOrders, update} from "@/api/oder";
    import {orderPage} from "@/api/page";
    import {getGoods, getUsers} from "@/api/getData";

    export default {
        name: "home_order",
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
                orders: null,
                loading: true,
                total: 0,
                size: 6,
                addDialog: false,
                editDialog: false,
                searchOrders: {
                    userName: '',
                    goodsName: '',
                    minPrice: '',
                    maxPrice: '',
                    goodsTypeId: ''
                },
                addOrders: {
                    orderAddress: '',
                    orderCount: '',
                    userId: '',
                    goodsId: ''
                },
                editOrders: {
                    orderAddress: '',
                    orderCount: '',
                    userId: '',
                    goodsId: ''
                },
                users: {
                    userId: '',
                    userName: ''
                },
                goods: {
                    goodsId: '',
                    goodsName: '',
                    price: '',
                }
            }
        },
        methods: {
            search() {
                this.$refs.searchForm.validate(async valid => {
                    if (valid) {
                        const res = await findAllOrders(this.size, this.searchOrders)
                        if (res.status === 200) {
                            this.orders = res.data.list
                            this.total = res.data.num
                            this.$message({
                                message: '成功为您查询到以下数据!',
                                type: 'success'
                            });
                        } else {
                            this.$message.error('错了，查询失败!')
                        }
                    }
                })
            },

            //选择列表中显示用户
            async getGoods() {
                const res = await getGoods()
                if (res.status === 200) {
                    this.goods = res.data
                } else {
                    this.$message.error('错了，查询失败!')
                }
            },

            //选择列表中显示商品列表
            async getUser() {
                const res = await getUsers()
                if (res.status === 200) {
                    this.users = res.data
                } else {
                    this.$message.error('错了，查询失败!')
                }
            },

            add() {
                this.$refs.addForm.validate(async valid => {
                    if (valid) {
                        const res = await add(this.addOrders)
                        if (res.status === 200) {
                            this.$message({
                                message: '添加成功!',
                                type: 'success'
                            })
                            location.reload()
                        } else {
                            this.$message.error('错了,添加数据失败!');
                        }
                    }
                })
            },

            async editById(obj) {
                this.editDialog = true
                const res = await editById(obj.orderId)
                if (res.status === 200) {
                    this.editOrders = res.data
                } else {
                    this.$message.error('错了，丢失数据!');
                }
            },

            deleteById(obj) {
                //弹出对话框
                this.$confirm('是否确定删除' + obj.orderId + '号订单?', '删除数据', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    const res = await deleteById(obj.orderId)
                    if (res.status === 200) {
                        await this.$alert(obj.orderId + '号订单删除成功!', '删除数据', {
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
                this.$refs.editForm.validate(async valid => {
                    if (valid) {
                        const res = await update(this.editOrders)
                        if (res.status === 200) {
                            this.$message({
                                message: '修改成功啦!',
                                type: 'success'
                            })
                            location.reload()
                        } else {
                            this.$message.error('错了哦');
                        }
                    }
                })
            },

            async page(currentPage) {
                const res = await orderPage(currentPage, this.size, this.searchOrders)
                if (res.status === 200) {
                    this.orders = res.data.list
                }
            }
        },

        async created() {
            const res = await findAllOrders(this.size, this.searchOrders)
            if (res.status === 200) {
                this.orders = res.data.list
                this.total = res.data.num
                this.loading = false
                this.$message({
                    message: '加载数据成功!',
                    type: 'success'
                });
            } else {
                this.$message.error('错了，服务器与服务端丢失,请检查一下网络!')
            }
        }
    }
</script>

<style lang="less" scoped>

</style>