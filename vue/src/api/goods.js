import axios from "axios";

export const findAllGoods =
    (size, searchGoods) => axios.post('/api/goods' + '/0' + '/' + size, searchGoods)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addGoods => axios.put('/api/addGoods', addGoods)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('/api/deleteGoods?id=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const update =
    goods => axios.put("/api/updateGoods", goods)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const findGoodsById =
    id => axios.get("/api/findGoods?id=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })