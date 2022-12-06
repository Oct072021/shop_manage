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
import {verifyToken} from "@/api/verify";
import {getToken} from "@/api/getData";

Vue.use(VueRouter)

/*这个错误是vue-router内部错误,导致导航跳转问题,
往同一地址跳转时会报错的情况push和replace 都会导致这个情况的发生*/
const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {

    return originalPush.call(this, location).catch(err => err)

}

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

//验证token是否过期
router.beforeEach(async (to, from, next) => {
    //若为登录则无需判断
    if (to.path.startsWith('/')) {
        next()
    }
    let token =getToken()
    if (token != null) {
        const res = await verifyToken()
        if (!res) {
            console.log("账户失效,验证失败")
            next({path: 'error'})
        }
        next()
    } else {
        next('/')
    }
})

export default router
