import axios from "axios";

export const findAllTypes =
    (size, searchTypes) => axios.post('http://localhost:8080/TypeAll' + '/0' + '/' + size, searchTypes)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const add =
    addTypes => axios.put('http://localhost:8080/addType', addTypes)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const editById =
    id => axios.get("http://localhost:8080/findByIdType?id=" + id)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const deleteById =
    id => axios.delete('http://localhost:8080/deleteType?id=' + id)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })

export const update =
    editTypes => axios.put("http://localhost:8080/updateType", editTypes)
        .then(response => {
            if (response.data) {
                return true
            } else {
                return false
            }
        })