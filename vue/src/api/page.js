export const page =
    (currentPage,size, user) => axios.post('http://localhost:8080/findUserAll/' + (currentPage - 1) + '/' + size, user)
        .then(response => {
            if (response.data) {
                return response.data
            } else {
                return null
            }
        })