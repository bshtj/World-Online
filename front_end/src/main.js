import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router'; // 引入 router
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App);
const pinia = createPinia();

app.use(pinia);
app.use(ElementPlus);
app.use(router); // 使用 router
app.mount('#app');
