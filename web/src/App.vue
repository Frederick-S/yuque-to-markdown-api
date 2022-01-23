<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import axios from './axios'
import toast from './toast'

export default {
  name: 'App',
  created() {
    axios.get('/api/me')
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
  }
}
</script>
