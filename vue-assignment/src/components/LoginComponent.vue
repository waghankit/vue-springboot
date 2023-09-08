<template>
    <div class="box center-div" style="height: 400px; width: 300px">
      <p style="text-align: center; font-weight: 600; font-size: 30px;">Welcome</p>
      <div class="field" style="margin-top: 10px;">
        <label class="label has-tooltip-multiline has-tooltip-info" style="font-size: 0.75rem; font-weight: 600;"> Enter Email</label>
        <div class="control">
          <input v-model="email" class="input" placeholder="abc@gmail.com" type="text" @keyup="updateValue" />
        </div>
      </div>
      <div class="field">
        <label class="label has-tooltip-multiline has-tooltip-info" style="font-size: 0.75rem; font-weight: 600;">Enter Password</label>
        <div class="control">
          <input v-model="password" class="input" placeholder="Enter your password"  type="text" @keyup="updateValue" />
        </div>
      </div>
      <button class="button is-primary" @click="onLogin" style="width: 260px; margin-top: 10px;">Login</button>
      <div @click="onSignIn" style="margin-top: 10px;">
        <img src="../assets/googlelogin.png" style="cursor: pointer; margin-top: 20px;"/>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
import '../../node_modules/bulma/bulma.sass';
import '../assets/styles.css';

export default {
  data() {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    onLogin() {
       if (!this.email || !this.password) {
          this.$toast.error('Please enter all fields');
       }
       const requestBody = {
          'email': this.email,
          'password': this.password
       }
       const options = {
          headers: {"content-type": "application/json"}
        }
       axios.post('http://localhost:8086/newUserRegistration', requestBody , options)
        .then(response => {
          console.log('response', response)
          if (response.data === 'registered' || response.data == 'duplicate') {
            this.$toast.success('Logged In Successfully')
            localStorage.setItem('session', true)
            this.$router.push('/home')
          }
        })
        .catch(error => {
          console.error('Error verifying token:', error);
        });
       console.log(requestBody)
    },
    onSignIn() {
      /* eslint-disable */
      gapi.load('auth2', () => {
        gapi.auth2.init({
          clientId: '314273525069-dbeumrev25hv56181ai4iqql3c4dern6.apps.googleusercontent.com',
          scope: 'email',
          plugin_name: 'vue-app'
        });
        gapi.auth2.getAuthInstance().signIn().then(googleUser => {
          const id_token = googleUser.getAuthResponse().id_token;
          console.log('id_token', id_token)
          //sending token to backend for verification
          this.verifyToken(id_token);
        });
      });
    },
    verifyToken(idToken) {
      const options = {
        headers: {"content-type": "application/json"}
      }
      axios.post('http://localhost:8086/verifyToken', { idToken }, options)
        .then(response => {
          console.log('response', response)
          if (response.data === 'verified') {
            localStorage.setItem('token', idToken)
            this.$router.push('/home')
          }
        })
        .catch(error => {
          console.error('Error verifying token:', error);
        });
    },
  },
};
</script>
