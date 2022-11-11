<template>
    <div>
        <el-row class="header">
            <el-col :offset="1" :span="3.5" class="header-left">在线商城管理系统V2.0</el-col>
            <el-col :offset="14" :span="2" class="header-right">
                <span>
                    <i class="el-icon-chat-dot-square" style="color: #E9EEF3"></i>&nbsp;
                    我的消息
                </span>
            </el-col>
            <el-col :offset="1" :span="2.5" class="header-right">
                <el-dropdown>
                    <span>
                        <i class="el-icon-user-solid"></i>&nbsp;
                        我的信息
                        <i class="el-icon-caret-bottom el-icon--right"></i>
                    </span>
                    <el-dropdown-menu>
                        <el-dropdown-item>
                            <i class="el-icon-user"></i>&nbsp;
                            欢迎:&nbsp;&nbsp;
                            <span>{{ adminInfo.userName }}</span>
                        </el-dropdown-item>
                        <el-dropdown-item divided>
                            <span @click="logout()" style="color: #333333">
                                <i class="el-icon-platform-eleme"></i>&nbsp;
                                退出登录
                            </span>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "top_header",
        data() {
            return {}
        },
        computed: {
            ...mapState([
                'adminInfo'
            ])
        },
        methods: {
            logout() {
                let that = this
                this.$confirm("是否退出登录？", "提示", {confirmButtonText: "确定", cancelButtonText: "取消"})
                    .then(() => {
                        localStorage.removeItem("access-username")
                        that.$router.replace({path: '/'})
                    })
            },
        }
    }
</script>

<style lang="less" scoped>
  .header {
    height: 65px;
    background-color: rgb(50, 64, 87);

    .header-left {
      height: 100%;
      font-size: 20px;
      color: #E9EEF3;
      line-height: 60px;
    }

    .header-right {
      height: 100%;
      line-height: 60px;

      span {
        color: #E9EEF3;
        font-size: 20px;
      }
    }
  }

</style>