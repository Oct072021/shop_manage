import request from "@/utils/request";

export const userPage =
    (currentPage, size, user) => {
        return request({
            url: `/findUserAll/${currentPage - 1}/${size}`,
            method: 'post',
            data: user
        })
    }

export const goodsPage =
    (currentPage, size, goods) => {
        return request({
            url: `/goods/${currentPage - 1}/${size}`,
            method: 'post',
            data: goods
        })
    }

export const goodsTypePage =
    (currentPage, size, goodsType) => {
        return request({
            url: `/TypeAll/${currentPage - 1}/${size}`,
            method: 'post',
            data: goodsType
        })
    }

export const orderPage =
    (currentPage, size, order) => {
        return request({
            url: `/findOrder/${currentPage - 1}/${size}`,
            method: 'post',
            data: order
        })
    }

export const shopCartPage =
    (currentPage, size, shopCart) => {
        return request({
            url: `/shopCart/${currentPage - 1}/${size}`,
            method: 'post',
            data: shopCart
        })
    }