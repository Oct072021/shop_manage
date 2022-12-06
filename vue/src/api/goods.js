import request from "@/utils/request";

export const findAllGoods =
    (size, searchGoods) => {
        return request({
            url: `/goods/0/${size}`,
            method: 'post',
            data: searchGoods
        })
    }

export const add =
    addGoods => {
        return request({
            url: '/addGoods',
            method: 'put',
            data: addGoods
        })
    }

export const deleteById =
    id => {
        return request({
            url: '/deleteGoods',
            method: 'delete',
            params: {id}
        })
    }

export const update =
    goods => {
        return request({
            url: '/updateGoods',
            method: 'put',
            data: goods
        })
    }

export const findGoodsById =
    id => {
        return request({
            url: '/findGoods',
            method: 'get',
            params: {id}
        })
    }