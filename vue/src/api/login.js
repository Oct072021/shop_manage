import axios from "axios";

export const login =
    user => axios.get("http://localhost:8080/UsePhoneLogin", user)
        .then(response => {
            if (response.data !== null) {
                return {code: 200, userInfo: response.data};
            }
        })