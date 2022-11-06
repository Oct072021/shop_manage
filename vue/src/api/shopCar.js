import axios from "axios";

export const findAllShopCar =
    (size, searchCar) => axios.post('http://localhost:8080/shopCart' + '/0' + '/' + size, searchCar)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addCar => axios.put('http://localhost:8080/addShop', addCar)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const editById =
    id => axios.get("http://localhost:8080/shopCartById?id=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('http://localhost:8080/deleteShopCart?id=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const update =
    editShop => axios.put("http://localhost:8080/updateShopCartId", editShop)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })