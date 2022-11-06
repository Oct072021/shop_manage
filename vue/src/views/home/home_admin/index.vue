<template>
    <div>
        {{ adminInfo }}
    </div>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        name: "home_admin",
        data() {
            return {}
        },
        computed: {
            ...mapState([
                'adminInfo'
            ])
        },

    }
</script>

<style scoped>

</style>