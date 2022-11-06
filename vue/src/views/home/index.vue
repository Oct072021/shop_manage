<template>
    <el-container>
        <el-header style="padding: 0">
            <top_header></top_header>
        </el-header>
        <el-container>
            <el-aside width="200px">
                <aside_menu></aside_menu>
            </el-aside>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import top_header from "@/components/top_header";
    import aside_menu from "@/components/aside_menu";
    import {mapActions, mapState} from "vuex";

    export default {
        name: "home",
        data() {
            return {
                admin: null
            }
        },
        components: {
            top_header,
            aside_menu,
        },
        computed: {
            ...mapState([
                'adminInfo'
            ])
        },
        methods: {
            ...mapActions([
                'getAdminInfo'
            ])
        },
        created() {
            this.admin = JSON.parse(localStorage.getItem('access'))
            this.getAdminInfo(this.admin)
        }
    }
</script>

<style>
    .el-container {
        height: 100%;
    }
</style>