import request from "@/utils/request";

export const getGoods =
    () => {
        return request({
            url: '/whereGoods',
            method: 'get'
        })
    }

export const getUsers =
    () => {
        return request({
            url: 'whereUser',
            method: 'get'
        })
    }

export const getToken =
    () => {
        return window.localStorage.getItem("token")
    }