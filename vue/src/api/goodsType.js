import axios from "axios";

export const findAllTypes =
    (size, searchTypes) => axios.post('/api/TypeAll' + '/0' + '/' + size, searchTypes)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addTypes => axios.put('/api/addType', addTypes)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const editById =
    id => axios.get("/api/findByIdType?id=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('/api/deleteType?id=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const update =
    editTypes => axios.put("/api/updateType", editTypes)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })