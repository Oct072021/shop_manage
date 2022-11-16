import axios from "axios";

export const findAllOrders =
    (size, searchOrders) => axios.post('/api/findOrder' + '/0' + '/' + size, searchOrders)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addOrders => axios.put('/api/addOrder', addOrders)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const editById =
    id => axios.get("/api/OrderById?id=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('/api/deleteOrder?id=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const update =
    editOrders => axios.put("/api/updateOrderById", editOrders)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })