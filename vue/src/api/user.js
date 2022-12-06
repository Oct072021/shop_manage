import request from "@/utils/request";

export const findAllUser =
    (size, searchUser) => {
        return request({
            url: `/findUserAll/0/${size}`,
            method: 'post',
            data: searchUser
        })
    }

export const add =
    addUser => {
        return request({
            url: `/insertUser`,
            method: 'put',
            data: addUser
        })
    }

export const editById =
    id => {
        return request({
            url: `/findById`,
            method: 'get',
            params: {userId: id}
        })
    }

export const deleteById =
    id => {
        return request({
            url: `/deleteUserById`,
            method: 'delete',
            params: {userId: id}
        })
    }

export const edit =
    editUser => {
        return request({
            url: `/editUserFindById`,
            method: 'put',
            data: editUser
        })
    }