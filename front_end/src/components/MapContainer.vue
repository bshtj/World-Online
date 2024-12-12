<script setup>
import { onMounted, onUnmounted } from "vue";
import AMapLoader from "@amap/amap-jsapi-loader";
import { loadPlaceSearch } from "../utils/UseAmapPlugins";

import { useQueryStore } from "../store/Query";
import { storeToRefs } from "pinia";
import { ref } from "vue";

const queryStore = useQueryStore();
const { location } = storeToRefs(queryStore);


queryStore.$subscribe((mutation, state) => {
  // 这里的 mutation 表示发生了什么变化，state 是当前的状态
  // 设置地图中心
  if (state.location) {
    map.setCenter(state.location);
  }
});

let map = null;

onMounted(() => {
  window._AMapSecurityConfig = {
    securityJsCode: "84a5c415561ec7c4ee215e4a77fe15ec",
  };
  AMapLoader.load({
    key: "4ede47e3906dd5d8a2e45126b491b770", // 申请好的Web端开发者Key，首次调用 load 时必填
    version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
    plugins: ["AMap.Scale"], //需要使用的的插件列表，如比例尺'AMap.Scale'，支持添加多个如：['...','...']
  })
    .then((AMap) => {
      map = new AMap.Map("container", {
        // 设置地图容器id
        viewMode: "3D", // 是否为3D地图模式
        zoom: 11, // 初始化地图级别
        center: [116.397428, 39.90923], // 初始化地图中心点位置
        resizeEnable: true,
      });
      // loadPlaceSearch(map);

    })
    .catch((e) => {
      console.log(e);
    });

});

onUnmounted(() => {
  map?.destroy();
});
</script>

<template>
  <div id="container"></div>
</template>

<style scoped>
#container {
  width: 100%;
  height: 100%;
}
</style>
