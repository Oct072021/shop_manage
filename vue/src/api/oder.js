import axios from "axios";

export const findAllOrders =
    (size, searchOrders) => axios.post('http://localhost:8080/findOrder' + '/0' + '/' + size, searchOrders)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addOrders => axios.put('http://localhost:8080/addOrder', addOrders)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const editById =
    id => axios.get("http://localhost:8080/OrderById?id=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('http://localhost:8080/deleteOrder?id=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const update =
    editOrders => axios.put("http://localhost:8080/updateOrderById", editOrders)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })