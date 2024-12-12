<script setup>
import MapContainer from "../components/MapContainer.vue";
import GeneralInformation from "../components/GeneralInformation.vue";
import Economy from "../components/Economy.vue";
import Society from "../components/Society.vue";
import Environment from "../components/Environment.vue";
import Technology from "../components/Technology.vue";
import GoogleData from "../components/GoogleData.vue";

import { useQueryStore } from "../store/Query";
import { storeToRefs } from "pinia";

import { searchHeFengCityId } from "../apis/UseHeFengApis";

import { ref } from "vue";

const queryStore = useQueryStore();
const { query, location, cityId } = storeToRefs(queryStore);

const suggestions = ref([]);
const showSuggestions = ref(false);

const fetchCityData = async () => {
    //初始化
    location.value = [116.397428, 39.90923];
    cityId.value = '';
    if (query.value.length >= 1) { // 输入字符超过1个时才发起请求
        const results = await searchHeFengCityId(query.value);
        suggestions.value = results.map(location => ({
            name: location.name,
            lon: location.lon,
            lat: location.lat,
            id: location.id,
        }));

    } else {
        suggestions.value = []; // 清空建议
    }
};

const selectCity = (cityName, lon, lat, id) => {
    query.value = cityName; // 设置选中的城市名到输入框
    location.value = [lon, lat];
    cityId.value = id;
    suggestions.value = []; // 清空建议
    showSuggestions.value = false; // 隐藏建议列表
};

const hideSuggestions = () => {
    setTimeout(() => {
        showSuggestions.value = false;
        suggestions.value = []; // 清空建议
    }, 200); // 延迟隐藏以避免点击事件被忽略
};

</script>

<template>

    <div class="header">
        <h1>World City Online</h1>
    </div>

    <div class="search-bar">
        <input v-model="query" type="text" placeholder="Type in city name" id="Input" @input="fetchCityData"
            @focus="showSuggestions = true" @blur="hideSuggestions">
        <ul v-if="showSuggestions && suggestions.length" class="suggestions-list">
            <li v-for="(location, index) in suggestions" :key="index"
                @click="selectCity(location.name, location.lon, location.lat, location.id)">
                {{ location.name }} (经纬度: {{ location.lon }}, {{ location.lat }})
            </li>
        </ul>
        <!-- <el-button @click="searchCities" type="primary" :icon="Search">Search</el-button> -->

    </div>

    <div class="container">
        <div class="box">
            <h2>General Information</h2>
            <GeneralInformation />
        </div>

        <div class="box">
            <h2>Economy</h2>
            <Economy />
        </div>

        <div class="box">
            <h2>Society</h2>
            <Society />
            <!-- <el-card shadow="hover">City latest societal news ...from Site 2</el-card> -->
        </div>

        <div class="box">
            <h2>Environment</h2>
            <Environment />
        </div>

        <div class="box">
            <h2>Technology</h2>
            <Technology />
        </div>

        <div class="box">
            <h2>City Data</h2>
            <GoogleData />
            <!-- <el-card shadow="hover">Google Dataset Search</el-card> -->
        </div>

        <div class="map">
            <MapContainer />
        </div>
    </div>
</template>

<style>
.suggestions-list {
    border: 1px solid #ccc;
    list-style-type: none;
    padding: 0;
    margin: 0;
    position: absolute;
    /* 改为绝对定位 */
    background: white;
    z-index: 1000;
    max-height: 200px;
    max-width: 300px;
    overflow-y: auto;
    text-align: center;
    left: 50%;
    /* 设置左边距为50% */
    transform: translateX(-50%);
    /* 将元素向左移动自身宽度的一半 */
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    /* 添加阴影以增强视觉效果 */
}

.suggestions-list li {
    padding: 10px;
    cursor: pointer;
}

.suggestions-list li:hover {
    background-color: #f0f0f0;
}

body {
    font-family: Arial, sans-serif;
}



el-button {
    padding: 10px;
    margin-top: 20px;
}


.header {
    background-color: #4a92cf;
    color: white;
    padding: 10px;
    text-align: center;
}

.search-bar {
    margin: 20px auto;
    text-align: center;
}

input[type="text"] {
    width: 300px;
    padding: 10px;
    margin-top: 20px;
}

.container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    margin: 20px;
    border: 2px solid #4a92cf;
}

.box {
    border: 1px solid #ccc;
    padding: 10px;
    margin: 10px;
    width: 250px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.map {
    flex: 1;
    min-width: 400px;
    height: 300px;
    border: 2px solid #4a92cf;
    margin: 10px;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>
