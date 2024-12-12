import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/HomePage.vue'), // 懒加载组件
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
