export const page =
    (currentPage,size, user) => axios.post('/api/findUserAll/' + (currentPage - 1) + '/' + size, user)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })