import request from "@/utils/request";

export const findAllTypes =
    (size, searchTypes) => {
        return request({
            url: `/TypeAll/0/${size}`,
            method: 'post',
            data: searchTypes
        })
    }

export const add =
    addTypes => {
        return request({
            url: '/addType',
            method: 'put',
            data: addTypes
        })
    }

export const editById =
    id => {
        return request({
            url: '/findByIdType',
            method: 'get',
            params: {id}
        })
    }

export const deleteById =
    id => {
        return request({
            url: '/deleteType',
            method: 'delete',
            params: {id}
        })
    }

export const update =
    editTypes => {
        return request({
            url: '/updateType',
            method: 'put',
            data: editTypes
        })
    }