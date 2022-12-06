import request from "@/utils/request";

export const findAllShopCar =
    (size, searchCar) => {
        return request({
            url: `/shopCart/0/${size}`,
            method: 'post',
            data: searchCar
        })
    }

export const add =
    addCar => {
        return request({
            url: `/addShop`,
            method: 'put',
            data: addCar
        })
    }

export const editById =
    id => {
        return request({
            url: '/shopCartById',
            method: 'get',
            params: {id}
        })
    }

export const deleteById =
    id => {
        return request({
            url: `/deleteShopCart`,
            method: 'delete',
            params: {id}
        })
    }

export const update =
    editShop => {
        return request({
            url: `/updateShopCartId`,
            method: 'put',
            data: editShop
        })
    }