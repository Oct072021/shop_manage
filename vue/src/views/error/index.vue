<template>

</template>

<script>
    export default {
        name: "error",
        created() {
            let that = this
            this.$alert("登录信息失效", "提示", {
                confirmButtonText: '确定'
            }).then(() => {
                localStorage.removeItem("access")
                that.$router.replace({path: '/'})
            })
        }
    }
</script>

<style scoped>

</style>