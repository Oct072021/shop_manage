import axios from "axios";

export const getGoods =
    () => axios.get("/api/whereGoods")
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })

export const getUsers =
    () => axios.get("/api/whereUser")
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })