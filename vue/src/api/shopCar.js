import axios from "axios";

export const findAllShopCar =
    (size, searchCar) => axios.post('/api/shopCart' + '/0' + '/' + size, searchCar)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addCar => axios.put('/api/addShop', addCar)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const editById =
    id => axios.get("/api/shopCartById?id=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('/api/deleteShopCart?id=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const update =
    editShop => axios.put("/api/updateShopCartId", editShop)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })