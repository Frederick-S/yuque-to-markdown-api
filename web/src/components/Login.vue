<template>
  <b-button type="is-primary" @click="login">Login</b-button>
</template>

<script>
import axios from '../axios'
import queryString from 'query-string'

export default {
  methods: {
    login() {
      const url = process.env.VUE_APP_API_SERVER_URL + '/oauth2/authorize'
      const popup = window.open(url, 'Authorize', 'popup')
      const interval = setInterval(() => {
        const parameters = queryString.parse(popup.location.search)

        if (parameters.tokenId) {
          axios.get('/api/me', {
            headers: {
              'X-tokenId': parameters.tokenId
            }
          })
          .then(() => {
            clearInterval(interval)
            popup.close()

            localStorage.setItem('yuque-tokenId', parameters.tokenId)
            this.$router.push('/repos')
          })
        }
      }, 1000);
    }
  }
}
</script>