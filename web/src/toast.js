import Vue from 'vue'

const toast = new Vue({
  methods: {
    danger(message) {
      this.$buefy.toast.open({
        duration: 3000,
        message,
        type: 'is-danger'
      })
    }
  }
})

export default toast
