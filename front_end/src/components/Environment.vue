<script setup>
import { ref } from "vue";
import { searchHeFengCityWeather, searchHeFengCityAirQuality, searchHeFengCityDailyWeather } from "../apis/UseHeFengApis";

import { nothingInputError } from "../utils/ErrorMessage";

import { useQueryStore } from "../store/Query";
import { useLoadingStore } from "../store/loading";
import { storeToRefs } from "pinia";
import LoadingDialog from "./LoadingDialog.vue";


const queryStore = useQueryStore();
const { query, cityId } = storeToRefs(queryStore);

const loadingStore = useLoadingStore();
const { loading } = storeToRefs(loadingStore);


const HeFengCityWeather = ref('');
const HeFengCityWeatherVisible = ref(false);
const getHeFengCityWeather = async () => {
    if (!nothingInputError(query)) {
        HeFengCityWeather.value = '';
        HeFengCityWeatherVisible.value = !HeFengCityWeatherVisible.value;
        loading.value = true;
        HeFengCityWeather.value = await searchHeFengCityWeather(cityId.value);
        loading.value = false;
    }
}


const HeFengAirQuality = ref('');
const HeFengAirQualityVisible = ref(false);
const getHeFengAirQuality = async () => {
    if (!nothingInputError(query)) {
        HeFengAirQuality.value = '';
        HeFengAirQualityVisible.value = !HeFengAirQualityVisible.value;
        loading.value = true;
        HeFengAirQuality.value = await searchHeFengCityAirQuality(cityId.value);
        loading.value = false;
    }
}


const HeFengDailyWeather = ref('');
const HeFengDailyWeatherVisible = ref(false);
const getHeFengDailyWeather = async () => {
    if (!nothingInputError(query)) {
        HeFengDailyWeather.value = [];
        HeFengDailyWeatherVisible.value = !HeFengDailyWeatherVisible.value;
        loading.value = true;
        HeFengDailyWeather.value = await searchHeFengCityDailyWeather(cityId.value);
        loading.value = false;
    }
}

</script>

<template>
    <el-dialog v-model="HeFengCityWeatherVisible" title="City Weather">
        <LoadingDialog>
            <div v-if="!HeFengCityWeather && !loading">
                未找到相关内容。
            </div>
            <div v-else>
                <h3>{{ query }}城市天气</h3>
                <p>时间: {{ HeFengCityWeather.obsTime }}</p>
                <p>温度: {{ HeFengCityWeather.temp }} °C</p>
                <p>体感温度: {{ HeFengCityWeather.feelsLike }} °C</p>
                <p>天气状况: {{ HeFengCityWeather.text }}</p>
                <p>风速: {{ HeFengCityWeather.windSpeed }} km/h</p>
                <p>湿度: {{ HeFengCityWeather.humidity }} %</p>
                <p>能见度: {{ HeFengCityWeather.vis }} km</p>
            </div>
        </LoadingDialog>
    </el-dialog>
    <el-card shadow="hover" @click="getHeFengCityWeather">City Weather from HeFeng</el-card>

    <br>

    <el-dialog v-model="HeFengDailyWeatherVisible" title="Daily Weather">
        <LoadingDialog>
            <div v-if="!HeFengDailyWeather && !loading">
                未找到相关内容。
            </div>
            <div v-else>
                <el-space wrap>
                    <el-card v-for="(weather, index) in HeFengDailyWeather" :key="index">
                        <h3>{{ weather.fxDate }}</h3> <!-- 预报日期 -->
                        <p>最高温度: {{ weather.tempMax }} °C</p> <!-- 最高温度 -->
                        <p>最低温度: {{ weather.tempMin }} °C</p> <!-- 最低温度 -->
                        <p>白天气象描述: {{ weather.textDay }}</p> <!-- 白天气象描述 -->
                        <p>夜间气象描述: {{ weather.textNight }}</p> <!-- 夜间气象描述 -->
                        <p>白天风向: {{ weather.windDirDay }}</p> <!-- 白天风向 -->
                        <p>白天风速: {{ weather.windSpeedDay }} km/h</p> <!-- 白天风速 -->
                        <p>湿度: {{ weather.humidity }} %</p> <!-- 湿度 -->
                        <p>气压: {{ weather.pressure }} hPa</p> <!-- 气压 -->
                        <p>紫外线指数: {{ weather.uvIndex }}</p> <!-- 紫外线指数 -->
                    </el-card>
                </el-space>
            </div>
        </LoadingDialog>
    </el-dialog>
    <el-card shadow="hover" @click="getHeFengAirQuality">City AirQuality from HeFeng</el-card>

    <br>

    <el-dialog v-model="HeFengAirQualityVisible" title="Air Quality">
        <LoadingDialog>
            <div v-if="!HeFengAirQuality && !loading">
                未找到相关内容。
            </div>
            <div v-else>
                <h3>城市空气质量</h3>
                <p>发布时间: {{ HeFengAirQuality.pubTime }}</p>
                <p>空气质量指数: {{ HeFengAirQuality.aqi }}</p>
                <p>等级: {{ HeFengAirQuality.level }}</p>
                <p>分类: {{ HeFengAirQuality.category }}</p>
                <p>主要成分: {{ HeFengAirQuality.primary }}</p>
                <p>PM10 颗粒物浓度: {{ HeFengAirQuality.pm10 }} µg/m³</p>
                <p>PM2.5 颗粒物浓度: {{ HeFengAirQuality.pm2p5 }} µg/m³</p>
                <p>二氧化氮浓度: {{ HeFengAirQuality.no2 }} µg/m³</p>
                <p>二氧化硫浓度: {{ HeFengAirQuality.so2 }} µg/m³</p>
                <p>一氧化碳浓度: {{ HeFengAirQuality.co }} µg/m³</p>
                <p>臭氧浓度: {{ HeFengAirQuality.o3 }} µg/m³</p>
            </div>
        </LoadingDialog>
    </el-dialog>
    <el-card shadow="hover" @click="getHeFengDailyWeather">City Daily Weather from HeFeng</el-card>
</template>