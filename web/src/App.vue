<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import axios from './axios'
import toast from './toast'
import queryString from 'query-string'

export default {
  name: 'App',
  created() {
    const parameters = queryString.parse(location.search)

    if (parameters.tokenId) {
      axios.get('/api/me', {
        headers: {
          'X-tokenId': parameters.tokenId
        }
      })
      .then(() => {
        this.$router.push('/authorized')
      })
      .catch(error => {
        if (error.status === 401) {
          toast.danger('Unauthorized')
        } else {
          toast.danger('Internal Server Error')
        }
      })

      return
    }

    const tokenId = localStorage.getItem('yuque-tokenId')

    if (tokenId) {
      axios.get('/api/me', {
        headers: {
          'X-tokenId': tokenId
        }
      })
      .then(() => {
        this.$router.push('/repos')
      })
      .catch(error => {
        if (error.status === 401) {
          this.$router.push('/login')
        } else {
          toast.danger('Internal Server Error')
        }
      })
    } else {
      this.$router.push('/login')
    }
  }
}
</script>
