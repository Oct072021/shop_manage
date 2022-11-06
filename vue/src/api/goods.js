import axios from "axios";

export const findAllGoods =
    (size, searchGoods) => axios.post('http://localhost:8080/goods' + '/0' + '/' + size, searchGoods)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addGoods => axios.put('http://localhost:8080/addGoods', addGoods)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('http://localhost:8080/deleteGoods?id=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const update =
    goods => axios.put("http://localhost:8080/updateGoods", goods)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const findGoodsById =
    id => axios.get("http://localhost:8080/findGoods?id=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })