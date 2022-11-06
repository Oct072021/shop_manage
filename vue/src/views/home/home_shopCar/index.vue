<template>
    <div>
        <el-form ref="searchForm" :model="searchCar" label-width="15px" style="display: flex;justify-content: center">
            <el-form-item>
                <el-input v-model="searchCar.userName" placeholder="请输入用户名字"></el-input>
            </el-form-item>

            <el-form-item style="width: 130px">
                <el-select v-model="searchCar.goodsTypeId" placeholder="商品类型">
                    <el-option v-for="item in options" :label="item.label" :value="item.typeNum"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-input v-model="searchCar.goodsName" placeholder="请输入商品名字"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="search('searchForm')">
                    <i class="el-icon-search"></i>
                    查询
                </el-button>
            </el-form-item>

        </el-form>

        <el-button type="primary" @click="addDialog = true">
            <i class="el-icon-plus"></i>
            新建
        </el-button>

        <el-dialog title="添加购物车" :visible.sync="addDialog" width="30%" top="8vh">
            <el-form ref="addForm" :model="addCar">

                <el-form-item label="选择用户">
                    <el-select v-model="addCar.userId" placeholder="请选择用户" @visible-change="getUser()">
                        <el-option v-for="item in users" :label="item.userName" :value="item.userId"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="选择商品">
                    <el-select v-model="addCar.goodsId" placeholder="请选择用户" @visible-change="getGoods()">
                        <el-option v-for="item in goods" :label="item.goodsName" :value="item.goodsId"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="商品数量:">
                    <el-input-number v-model="addCar.count" :min="0" style="width: 193px"></el-input-number>
                </el-form-item>

                <el-form-item style="margin-left: 260px">
                    <el-button type="danger" @click="addDialog= false">取 消</el-button>
                    <el-button type="primary" @click="add('addForm')">确 定</el-button>
                </el-form-item>
                <span>购物车中不扣除商品库存数量,只显示该订单价格</span>
            </el-form>
        </el-dialog>

        <el-table v-loading="loading" :data="car" border stripe
                  element-loading-text="拼命加载中"
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="rgba(0, 0, 0, 0.8)">

            <el-table-column prop="shopCartId" label="#"></el-table-column>

            <el-table-column prop="count" label="购买数量"></el-table-column>

            <el-table-column prop="shopPrice" label="订单价格"></el-table-column>

            <el-table-column prop="usesList" label="用户名">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.usersList">
                        {{ item.userName }}
                    </span>
                </template>
            </el-table-column>

            <el-table-column prop="usersList" label="联系方式">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.usersList">
                        {{ item.phone }}
                    </span>
                </template>
            </el-table-column>

            <el-table-column prop="goodsList" label="商品图片">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.goodsList">
                        <img :src="item.goodsImg" style="height: 44px; border:1px solid red"/>
                    </span>
                </template>
            </el-table-column>

            <el-table-column label="商品名称" prop="goodsList">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.goodsList">
                        {{ item.goodsName }}
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

            <el-table-column label="库存" prop="goodsList">
                <template slot-scope="scope">
                    <span v-for="item in scope.row.goodsList">
                        {{ item.count }}
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

            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button size="mini" type="info" @click="editById(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="deleteById(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="修改购物车" :visible.sync="editDialog" width="30%" top="8vh">
            <el-form ref="editForm" :model="editCar">

                <el-form-item label="选择用户">
                    <el-select v-model="editCar.userId" placeholder="请选择用户" @visible-change="getUser()">
                        <el-option v-for="item in users" :label="item.userName" :value="item.userId"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="选择商品">
                    <el-select v-model="editCar.goodsId" placeholder="请选择用户" @visible-change="getGoods()">
                        <el-option v-for="item in goods" :label="item.goodsName" :value="item.goodsId"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="商品数量:">
                    <el-input-number v-model="editCar.count" :min="0"></el-input-number>
                </el-form-item>

                <el-form-item>
                    <el-button type="danger" @click="editDialog = false">取 消</el-button>
                    <el-button type="primary" @click="update('editForm')">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-pagination background layout="prev, pager, next" :page-size="size" :total="total"
                       @current-change="page"></el-pagination>
    </div>
</template>

<script>
    import {add, deleteById, editById, findAllShopCar, update} from "@/api/shopCar";
    import {getGoods, getUsers} from "@/api/getData";
    import {page} from "@/api/page";

    export default {
        name: "home_shopCar",
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
                car: null,
                loading: true,
                addDialog: false,
                editDialog: false,
                total: 0,
                size: 6,
                searchCar: {
                    userName: '',
                    goodsName: '',
                    minPrice: '',
                    maxPrice: '',
                    goodsTypeId: ''
                },
                addCar: {
                    userId: '',
                    goodsId: '',
                    count: ''
                },
                editCar: {
                    userId: '',
                    goodsId: '',
                    count: ''
                },
                users: {
                    userId: '',
                    userName: ''
                },
                goods: {
                    goodsId: '',
                    goodsName: '',
                    price: '',
                },
            }
        },
        methods: {
            //查询
            search(form) {
                let that = this
                this.$refs[form].validate(async (valid) => {
                    const obj = await findAllShopCar(this.size, this.searchCar)
                    if (obj != null) {
                        that.car = obj.list
                        that.total = obj.num
                        that.$message({
                            message: '成功为您查询到以下数据!',
                            type: 'success'
                        });
                    } else {
                        that.$message.error('错了,查询失败!')
                    }
                })
            },

            //选择列表中显示用户
            async getGoods() {
                let that = this
                const obj = await getGoods()
                if (obj != null) {
                    that.goods = obj
                } else {
                    that.$message.error('错了，查询失败!')
                }
            },

            //选择列表中显示商品列表
            async getUser() {
                let that = this
                const obj = await getUsers()
                if (obj != null) {
                    that.users = obj
                } else {
                    that.$message.error('错了，查询失败!')
                }
            },

            //添加购物车中的数据
            add(form) {
                let that = this
                this.$refs[form].validate(async (valid) => {
                    if (valid) {
                        const obj = await add(this.addCar)
                        if (obj != null) {
                            that.$message({
                                message: '添加成功啦!',
                                type: 'success'
                            })
                            location.reload()
                        } else {
                            that.$message.error('错了，添加数据失败!');
                        }
                    }
                })
            },

            //删除购物车
            deleteById(obj) {
                let that = this
                //弹出对话框
                this.$confirm('是否确定删除' + obj.title + '?', '删除数据', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    const bool = await deleteById(obj.shopCartId)
                    if (bool == true) {
                        that.$alert(obj.name + '删除成功!', '删除数据', {
                            confirmButtonText: '确定',
                            callback: action => {
                                //重载界面,刷新页面数据
                                location.reload()
                            }
                        })
                    } else {
                        that.$message.error('错了，删除失败!');
                    }
                })
            },

            //编辑购物车
            async editById(obj) {
                let that = this
                this.editDialog = true
                const object = await editById(obj.shopCartId)
                if (object != null) {
                    that.editCar = obj
                } else {
                    that.$message.error('错了，丢失数据!');
                }
            },

            update(form) {
                let that = this
                this.$refs[form].validate(async (valid) => {
                    if (valid) {
                        const bool = await update(this.editCar)
                        if (bool == true) {
                            that.$message({
                                message: '修改成功啦!',
                                type: 'success'
                            })
                            location.reload()
                        } else {
                            that.$message.error('错了,修改失败!');
                        }
                    }
                })
            },

            //分页事件的处理
            async page(currentPage) {
                let that = this
                const obj = await page(currentPage, this.size, this.searchCar)
                if (obj != null) {
                    that.car = obj.list
                }
            }
        },
        async created() {
            let that = this
            const obj = await findAllShopCar(this.size, this.searchCar)
            if (obj != null) {
                that.car = obj.list
                that.total = obj.num
                that.loading = false
                that.$message({
                    message: '加载数据成功啦!',
                    type: 'success'
                });
            } else {
                that.$message.error('错了，服务器与服务端丢失,请检查一下网络!')
            }
        }
    }
</script>

<style lang="less" scoped>

</style>