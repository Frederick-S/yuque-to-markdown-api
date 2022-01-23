<template>
  <b-button type="is-primary" @click="login">Login</b-button>
</template>

<script>
export default {
  methods: {
    login() {
      const url = process.env.VUE_APP_API_SERVER_URL + '/oauth2/authorize'

      window.open(url, 'Authorize', 'popup')
    }
  }
}
</script>