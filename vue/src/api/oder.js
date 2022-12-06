import request from "@/utils/request";

export const findAllOrders =
    (size, searchOrders) => {
        return request({
            url: `/findOrder/0/${size}`,
            method: 'post',
            data: searchOrders
        })
    }

export const add =
    addOrders => {
        return request({
            url: '/addOrder',
            method: 'put',
            data: addOrders
        })
    }

export const editById =
    id => {
        return request({
            url: '/OrderById',
            method: 'get',
            params: {id}
        })
    }

export const deleteById =
    id => {
        return request({
            url: '/deleteOrder',
            method: 'delete',
            params: {id}
        })
    }

export const update =
    editOrders => {
        return request({
            url: '/updateOrderById',
            method: 'put',
            data: editOrders
        })
    }