import {createApp} from 'vue'
import App from './App.vue'
import VueToast from 'vue-toast-notification';
import 'vue-toast-notification/dist/theme-sugar.css';



// Router
import router from './router';

const app = createApp(App)

app.use(router)
app.use(VueToast, {
    position: 'top-right',
    duration: 5000,
    dismissible: true,
})
app.mount('#app');
