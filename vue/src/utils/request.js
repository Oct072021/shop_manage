import axios from "axios";
import {getToken} from "@/api/getData";

//创建axios实例
const service = axios.create({
    baseURL: '/api',
    timeout: 5000,
})

//请求拦截器
service.interceptors.request.use(
    config => {
        config.headers['X-Token'] = getToken()
        return config
    },
    error => {
        console.log(error);
        return Promise.reject(error)
    }
)

//响应拦截器
service.interceptors.response.use(
    response => {
        return response
    },
    error => {
        console.log(error);
        return Promise.reject(error)
    }
)

export default service