<template>
    <el-form ref="form" :model="goods" label-width="80px" style="width: 420px">
        <el-form-item label="商品名称">
            <el-input v-model="goods.goodsName"></el-input>
        </el-form-item>

        <el-form-item label="是否在售" prop="onSale">
            <el-radio v-model="goods.onSale" label="yes">在售</el-radio>
            <el-radio v-model="goods.onSale" label="no">售完</el-radio>
        </el-form-item>

        <el-form-item label="商品原价" prope="price">
            <el-input v-model.number="goods.price"></el-input>
        </el-form-item>

        <el-form-item label="库存 " prop="count">
            <el-input v-model.number="goods.count"></el-input>
        </el-form-item>

        <el-form-item label="商品样图" prop="goodsImg">
            <!--<img :src="goods.goodsImg" style="height:90px;width: 120px; border: 1px solid red;"/>-->
            <el-input v-model="goods.goodsImg"/>
            <el-upload action="" :auto-upload="false" :data="goods" :before-upload="beforeUpload"
                       :on-change="handleChange" :limit="1">
                <img v-if="goods.goodsImg" :src="goods.goodsImg" style="width:160px; height: 120px;">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>

        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="update">修改</el-button>
            <el-button type="danger">取消</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    import {findGoodsById, update} from "@/api/goods";

    export default {
        name: "edit",
        data() {
            return {
                goods: {
                    goodsName: "",
                    price: "",
                    onSale: "",
                    count: "",
                }
            };
        },
        methods: {
            update() {
                let that = this
                this.$refs.form.validate(async (valid) => {
                    if (valid) {
                        const bool = await update(this.goods)
                        if (bool == true) {
                            that.$alert(that.goods.goodsName + '修改成功！', '修改数据', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    //跳转到/table
                                    that.$router.push('goods')
                                }
                            })
                        }
                    }
                })
            },

            handleChange(file, fileList) {
                this.goods.goodsImg = URL.createObjectURL(file.raw);
            },

            beforeUpload(file) {
                return true;
            },
        },
        async created() {
            let id = this.$route.query.id;
            let that = this;
            const obj = await findGoodsById(id)
            if (obj != null) {
                that.goods = obj
            }
        },
    };
</script>