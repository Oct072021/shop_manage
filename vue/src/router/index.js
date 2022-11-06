import Vue from 'vue'
import VueRouter from 'vue-router'
import home from "@/views/home";
import home_admin from "@/views/home/home_admin";
import home_user from "@/views/home/home_user";
import home_goods from "@/views/home/home_goods";
import home_goodsType from "@/views/home/home_goodsType";
import home_order from "@/views/home/home_order";
import home_shopCar from "@/views/home/home_shopCar";
import home_pie from "@/views/home/home_pie";
import edit from "@/views/home/home_goods/edit";
import login from "@/views/login";
import error from "@/views/error";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        component: login
    },
    {
        path: '/home/id=:id/',
        name: 'home',
        component: home,
        children: [
            {
                path: 'admin',
                component: home_admin
            },
            {
                path: 'user',
                component: home_user
            },
            {
                path: 'goods',
                component: home_goods
            },
            {
                path: 'edit',
                component: edit
            },
            {
                path: 'goodsType',
                component: home_goodsType
            },
            {
                path: 'order',
                component: home_order
            },
            {
                path: 'shopCar',
                component: home_shopCar
            },
            {
                path: 'pie',
                component: home_pie
            }
        ],
    },
    {
        path: '/error',
        component: error
    }
]

const router = new VueRouter({
    routes
})

router.beforeEach((to, from, next) => {
    if (to.path.startsWith('/')) {
        next()
    } else {
        let access = JSON.parse(window.localStorage.getItem('access'))
        if (access != null) {
            axios({
                url: 'http://localhost:8080/verifyToke',
                type: 'get',
                headers: {
                    token: access.token
                }
            }).then(response => {
                if (!response.data) {
                    console.log("账户失效,验证失败")
                    next({path: 'error'})
                }
            })
            next()
        } else {
            next('/')
        }
    }
})


export default router
