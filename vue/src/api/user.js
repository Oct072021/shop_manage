import axios from "axios";

export const findAllUser =
    (size, searchUser) => axios.post('http://localhost:8080/findUserAll' + '/0' + '/' + size, searchUser)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addUser => axios.put('http://localhost:8080/insertUser', addUser)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const editById =
    id => axios.get("http://localhost:8080/findById?userId=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('http://localhost:8080/deleteUserById?userId=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const edit =
    editUser => axios.put("http://localhost:8080/editUserFindById", editUser)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })