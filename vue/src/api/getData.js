import axios from "axios";

export const getGoods =
    () => axios.get("http://localhost:8080/whereGoods")
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const getUsers =
    () => axios.get("http://localhost:8080/whereUser")
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })