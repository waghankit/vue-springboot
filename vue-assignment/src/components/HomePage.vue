<template>
    <div class="box center-div" style="height: 180px; width: 300px;">
      <p style="text-align: center; font-weight: 600; font-size: 30px;">Home Page!</p>
      <button class="button is-danger" @click="signOut" style="margin-top: 20px; margin-left: 80px;">Sign out</button>
    </div>
</template>
  
  <script>
  export default {
    methods: {
        signOut() {
            /* eslint-disable */
            localStorage.removeItem('session')
            this.$router.push('/')
            const auth2 = gapi?.auth2?.getAuthInstance();
            if (!auth2) {
                return;
            }
            auth2.signOut().then(() => {
               localStorage.removeItem('token')
               this.$router.push('/')
               console.log('User signed out');
            });
        },
    }
  };
  </script>
  