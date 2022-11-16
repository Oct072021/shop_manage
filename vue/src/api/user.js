import axios from "axios";

export const findAllUser =
    (size, searchUser) => axios.post('/api/findUserAll' + '/0' + '/' + size, searchUser)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addUser => axios.put('/api/insertUser', addUser)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const editById =
    id => axios.get("/api/findById?userId=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('/api/deleteUserById?userId=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const edit =
    editUser => axios.put("/api/editUserFindById", editUser)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })