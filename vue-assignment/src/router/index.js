
import LoginComponent from '../components/LoginComponent'
import HomePage from '../components/HomePage'
import * as VueRouter from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginComponent
  },
  {
    path: '/home',
    name: 'home',
    component: HomePage,
    meta: {
      requiresAuth: true
    }
  }
]

const router = VueRouter.createRouter({
  history: VueRouter.createWebHistory(),
  routes: routes,
})

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    const token = localStorage.getItem('token');
    const session = localStorage.getItem('session');

    if (token || session) {
      // User is authenticated, proceed to the route
      next();
    } else {
      alert('You need to login first')
      // User is not authenticated, redirect to login
      next('/');
    }
  } else {
    // Non-protected route, allow access
    next();
  }
});

export default router;