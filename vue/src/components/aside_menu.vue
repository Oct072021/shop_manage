<template>
    <el-menu class="menu" :default-active="$route.path" router>
        <el-menu-item class="menu_item" v-for="item in urlArr" :index="item.path">
            <i :class="item.icon"></i>
            <span>{{ item.urlItem }}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "aside_menu",
        data() {
            return {
                urlArr: [
                    {path: "admin", urlItem: "后台主页", icon: "el-icon-s-home"},
                    {path: "user", urlItem: "用户管理", icon: "el-icon-user-solid"},
                    {path: "goods", urlItem: "商品管理", icon: "el-icon-present"},
                    {path: "goodsType", urlItem: "类型管理", icon: "el-icon-help"},
                    {path: "order", urlItem: "订单管理", icon: "el-icon-sell"},
                    {path: "shopCar", urlItem: "购物管理", icon: "el-icon-shopping-cart-full"},
                    {path: "pie", urlItem: "饼状图", icon: "el-icon-picture-outline-round"},
                ],
            }
        },
        methods: {}
    }
</script>

<style lang="less" scoped>
  .menu {
    background-color: rgb(50, 64, 87);
    height: 100%;

    .menu_item {
      color: #E9EEF3;
    }

    .menu_item:hover {
      background-color: rgb(72, 87, 106);
    }

    .menu_item:focus {
      background-color: rgb(72, 87, 106);
    }
  }
</style>