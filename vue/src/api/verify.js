import request from "@/utils/request";

export const verifyToken =
    () => {
        return request({
            url: '/verifyToken',
            method: 'get'
        })
    }
