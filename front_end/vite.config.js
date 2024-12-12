import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      '/api': {
        target: 'https://api.nytimes.com', // 替换为你的目标 API 地址
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''), // 如果需要重写路径
      },
    },
  },
})
