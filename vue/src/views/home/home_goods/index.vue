<template>
    <div>
        <el-form ref="searchForm" :model="searchGoods" label-width="15px" style="display: flex;justify-content: center">

            <el-form-item>
                <el-switch v-model="searchGoods.onSale" active-text="在售" active-value="yes" inactive-text="售罄"
                           inactive-value="no"></el-switch>
            </el-form-item>

            <el-form-item>
                <el-input v-model="searchGoods.title" placeholder="请输入商品名称"></el-input>
            </el-form-item>

            <el-form-item style="width: 130px">
                <el-select v-model="searchGoods.gId" placeholder="商品类型">
                    <el-option v-for="item in options" :label="item.label" :value="item.typeNum"></el-option>
                </el-select>
            </el-form-item>

            <el-form-item style="width: 110px">
                <el-input v-model="searchGoods.minPrice" placeholder="最低价格"></el-input>
            </el-form-item>

            <el-form-item>¥---</el-form-item>

            <el-form-item style="width: 110px">
                <el-input v-model="searchGoods.maxPrice" placeholder="最高价格"></el-input>
            </el-form-item>

            <el-form-item>¥</el-form-item>

            <el-form-item>
                <el-button type="primary" @click="search('searchForm')">
                    <i class="el-icon-search"></i>
                    查询
                </el-button>
            </el-form-item>

        </el-form>

        <el-button type="primary" @click="addDialog=true" style="margin-top: 10px">
            <i class="el-icon-plus"></i>
            新建
        </el-button>

        <el-dialog title="添加商品" :visible.sync="addDialog" width="30%" top="8vh">

            <el-form ref="addForm" :model="addGoods">

                <el-form-item label="商品名字">
                    <el-input v-model="addGoods.goodsName" placeholder="请输入商品名称" style="width: 370px"></el-input>
                </el-form-item>

                <el-form-item label="商品类型">
                    <el-select v-model="addGoods.goodsTypeId" placeholder="请输入商品类型" style="width: 370px">
                        <el-option v-for="item in options" :label="item.label" :value="item.typeNum"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="商品数量">
                    <el-input-number v-model="addGoods.count" min="0" style="width: 370px"></el-input-number>
                </el-form-item>

                <el-form-item label="商品价格">
                    <el-input v-model="addGoods.price" placeholder="请输入商品价格" style="width: 370px"></el-input>
                </el-form-item>

                <el-form-item style="margin-left: 270px">
                    <el-button type="danger" @click="addDialog = false">取消</el-button>
                    <el-button type="primary" @click="add('addForm')">确定</el-button>
                </el-form-item>

            </el-form>

        </el-dialog>

        <el-table v-model="loading" :data="goods" style="width: 100%" stripe border
                  element-loading-text="拼命加载中"
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="rgba(0, 0, 0, 0.8)">

            <el-table-column type="selection" prop="goodsId"></el-table-column>

            <el-table-column prop="goodsId" label="#"></el-table-column>

            <el-table-column prop="goodsName" label="商品名称"></el-table-column>

            <el-table-column prop="price" label="价格" width="80" sortable>
                <template slot-scope="scope">
                    <span>{{ scope.row.price }}¥</span>
                </template>
            </el-table-column>

            <el-table-column prop="onSale" label="是否在售">
                <template slot-scope="scope">
                    <span :style="{ color: scope.row.onSale === 'yes' ? '#ff0000': '#808080'}">
                     {{ scope.row.onSale === 'yes' ? '在售' : '售完' }}
                     </span>
                </template>
            </el-table-column>

            <el-table-column prop="goodsImg" label="商品图片">
                <template slot-scope="scope">
                    <img :src="scope.row.goodsImg" style="height: 44px; border:1px solid red"/>
                </template>
            </el-table-column>

            <el-table-column label="库存" prop="count"></el-table-column>

            <el-table-column label="商品类型" prop="goodsType">
                <template slot-scope="scope">
                    <span v-for="g in scope.row.goodsType">
                        {{ g.goodsTypeName }}
                    </span>
                </template>
            </el-table-column>

            <el-table-column>
                <template slot-scope="scope">
                    <el-button size="mini" type="info" @click="editById(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="deleteById(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination background layout="prev, pager, next" :page-size="size" :total="total"
                       @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    import {findAllGoods, add, deleteById} from "@/api/goods";
    import {page} from "@/api/page";

    export default {
        name: "home_goods",
        data() {
            return {
                size: 6,
                total: 0,
                searchGoods: {
                    title: '',
                    gId: '',
                    minPrice: '',
                    maxPrice: '',
                    onSale: 'yes'
                },
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
                addDialog: false,
                addGoods: {
                    goodsName: '',
                    price: '',
                    count: 0,
                    goodsTypeId: ''
                },
                loading: false,
                goods: null,
            }
        },
        methods: {
            search(form) {
                let that = this
                this.$refs[form].validate(async (valid) => {
                    if (valid) {
                        const obj = await findAllGoods(this.size, this.searchGoods)
                        if (obj != null) {
                            that.goods = obj.list
                            that.total = obj.num
                            that.loading = false
                            that.$message({
                                message: '查询成功!',
                                type: 'success'
                            })
                        } else {
                            that.$message.error('服务器与服务端丢失,请检查一下网络!')
                        }
                    }
                })
            },

            add(form) {
                let that = this
                this.$refs[form].validate(async (valid) => {
                    if (valid) {
                        const obj = await add(this.addGoods)
                        if (obj != null) {
                            that.$message({
                                message: '添加商品成功',
                                type: 'success'
                            });
                            location.reload()
                        } else {
                            that.$message.error('错了哦，添加商品数据失败');
                        }
                    }
                })
            },

            editById(obj) {
                this.$router.push("edit?id=" + obj.goodsId)
            },

            deleteById(obj) {
                console.log(obj)
                let that = this
                //弹出对话框
                this.$confirm('是否确定删除' + obj.title + '?', '删除数据', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    const object = await deleteById(obj.goodsId)
                    //向后端传数据并删除
                    if (object == true) {
                        that.$alert(obj.goodsName + '删除成功!', '删除数据', {
                            confirmButtonText: '确定',
                            callback: action => {
                                //重载界面,刷新页面数据
                                location.reload()
                            }
                        })
                    } else {
                        that.$alert("删除失败!")
                    }
                }).catch(() => {

                });
            },

            async page(currentPage) {
                const obj = await page(currentPage, this.size, this.searchGoods)
                if (obj != null) {
                    this.goods = obj.list
                }
            },
        },
        async created() {
            let that = this
            const obj = await findAllGoods(this.size, this.searchGoods)
            if (obj != null) {
                that.goods = obj.list
                that.total = obj.num
                that.loading = false
                that.$message({
                    message: '查询成功!',
                    type: 'success'
                })
            } else {
                that.$message.error('服务器与服务端丢失,请检查一下网络!')
            }
        }
    }
</script>

<style lang="less" scoped>

</style>