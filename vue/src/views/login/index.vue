<template>
    <div class="background">
        <el-card class="card" shadow="hover" :body-style="{ padding: '0px' }">
            <el-container>
                <el-aside width="250px" class="aside">
                    <el-form ref="loginForm" class="login" :model="user">

                        <el-form-item>
                            <i class="el-icon-user icon"></i>
                            <el-input class="input" v-model="user.phone" clearable></el-input>
                        </el-form-item>

                        <el-form-item>
                            <i class="el-icon-lock icon"></i>
                            <el-input class="input" v-model="user.password" show-password></el-input>
                        </el-form-item>

                        <el-form-item>
                            <router-link class="reset" to="/reset">忘记密码?</router-link>
                            <el-button class="button" @click="login">Login</el-button>
                        </el-form-item>

                    </el-form>

                    <div class="bottom">
                        <div class="author">@Vladimir</div>
                        <router-link class="register" to="/register">若无账号请点击注册</router-link>
                    </div>
                </el-aside>
                <el-main class="main">
                    <span class="title">Welcome to Vladimir's Shop Management System</span>
                </el-main>
            </el-container>
        </el-card>
    </div>
</template>

<script>
    import {login} from "@/api/login";
    import {mapActions} from "vuex";

    export default {
        name: "login",
        data() {
            return {
                user: {
                    phone: '',
                    password: ''
                }
            }
        },
        methods: {
            ...mapActions([
                'getAdminInfo'
            ]),

            login() {
                this.$refs.loginForm.validate(async valid => {
                    if (valid) {
                        const res = await login(this.user)
                        if (res.status === 200) {
                            await this.getAdminInfo(res.data)
                            localStorage.setItem("access", JSON.stringify(res.data))
                            localStorage.setItem("token", JSON.stringify(res.data.token))
                            await this.$router.replace({path: '/home/id=' + res.data.userId + '/'})
                        }
                    }
                })
            }
        }
    }
</script>

<style lang="less" scoped>
  .background {
    width: 100%;
    height: 100%;
    background-color: rgb(190, 207, 202);
    display: flex;
    justify-content: center;
    align-items: center;

    .card {
      border-radius: 7px;
      padding: 0;
      width: 1000px;
      height: 500px;

      .aside {
        background-color: rgb(126, 161, 148);

        .login {
          margin-top: 130px;

          .icon {
            font-size: 23px;
            margin-left: 20px;
          }

          .input {
            padding-left: 7px;
            width: 80%;
          }

          /deep/ .el-input__inner {
            background-color: transparent !important;
            border: none;
            border-bottom: 2px solid #E9EEF3;
            border-radius: 0;
          }

          .reset {
            color: #5e7382;
            margin-left: 20px;
          }

          .button {
            color: #E9EEF3;
            border: none;
            border-radius: 15px;
            height: 20px;
            line-height: 3px;
            margin-left: 80px;
            background-color: rgb(84, 119, 107);
          }

          .button:hover {
            background-color: rgba(84, 119, 107, 0.8);
          }
        }

        .bottom {
          margin-top: 210px;

          .author {
            font-weight: 800;
            position: absolute;
            top: 500px;
            left: 300px;
          }

          .register {
            color: #5e7382;
            font-size: 14px;
            position: absolute;
            top: 530px;
            left: 300px;
          }
        }
      }

      .main {
        background: url("../../../public/images/front/login/Christmas_Trees.png") no-repeat;
        background-size: 100% 100%;

        .title {
          font-weight: 600;
          font-size: 20px;
          position: absolute;
          top: 520px;
          right: 400px;
        }
      }
    }
  }
</style>