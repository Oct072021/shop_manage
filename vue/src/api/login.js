import request from "@/utils/request";

export const login =
    data => {
        return request({
            url: '/UsePhoneLogin',
            method: 'get',
            params: data,
        })
    }