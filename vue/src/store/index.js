import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const SAVE_ADMIN_INFO = 'SAVE_ADMIN_INFO'

export default new Vuex.Store({
    state: {
        adminInfo: null
    },
    getters: {},
    mutations: {
        [SAVE_ADMIN_INFO](state, adminInfo) {
            state.adminInfo = adminInfo
        }
    },
    actions: {
        async getAdminInfo({commit}, data) {
            commit('SAVE_ADMIN_INFO', data)
        }
    },
    modules: {}
})
