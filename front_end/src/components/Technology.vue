<script setup>
import { ref } from "vue";
import { searchWikipedia } from "../apis/UseWiKiApis";
import { searchBaidu } from "../apis/UseBaiDuApis";

import { nothingInputError } from "../utils/ErrorMessage";

import { useQueryStore } from "../store/Query";
import { useLoadingStore } from "../store/loading";
import { storeToRefs } from "pinia";
import LoadingDialog from "./LoadingDialog.vue";


const queryStore = useQueryStore();
const { query } = storeToRefs(queryStore);

const loadingStore = useLoadingStore();
const { loading } = storeToRefs(loadingStore);



const WikiResultsTechnology = ref([]);
const BaiDuResultsTechnology = ref('');



const WiKiCityTechnologyInfoVisible = ref(false)
const searchCityTechnologyInfoFromWiki = async () => {
    if (!nothingInputError(query)) {
        WikiResultsTechnology.value = [];
        WiKiCityTechnologyInfoVisible.value = !WiKiCityTechnologyInfoVisible.value;
        loading.value = true;
        WikiResultsTechnology.value = await searchWikipedia(query.value + " technology");
        loading.value = false;
    }

};


const BaiDuCityTechnologyInfoVisible = ref(false)
const searchCityTechnologyInfoFromBaiDu = async () => {
    if (!nothingInputError(query)) {
        BaiDuResultsTechnology.value = '';
        BaiDuCityTechnologyInfoVisible.value = !BaiDuCityTechnologyInfoVisible.value;
        loading.value = true;
        BaiDuResultsTechnology.value = await searchBaidu(query.value + "科技");
        loading.value = false;
    }

};


const viewDetails = (url) => {
    window.open(url, '_blank'); // 打开新链接
};
</script>


<template>
    <el-dialog v-model="WiKiCityTechnologyInfoVisible" title="WiKi">
        <LoadingDialog>
            <div>
                <div v-if="WikiResultsTechnology.length === 0 && !loading">未找到相关内容。</div>
                <div v-else>
                    <el-space fill wrap>
                        <el-card v-for="(result, index) in WikiResultsTechnology" :key="index">
                            <template #header>
                                <div class="card-header">
                                    <span>{{ result.title }}</span>
                                    <el-button class="button" text
                                        @click="viewDetails('https://en.wikipedia.org/wiki/' + encodeURIComponent(result.title))">查看详情</el-button>
                                </div>
                            </template>
                            <div>
                                <p v-html="result.snippet"></p>
                            </div>
                        </el-card>
                    </el-space>
                </div>
            </div>
        </LoadingDialog>

    </el-dialog>
    <el-card shadow="hover" @click="searchCityTechnologyInfoFromWiki">City Technology Information from
        Wikipedia</el-card>

    <br>


    <el-dialog v-model="BaiDuCityTechnologyInfoVisible" title="BaiDU">
        <LoadingDialog>
            <div>
                <div v-if="!BaiDuResultsTechnology.title && !loading">
                    未找到相关内容。
                </div>
                <div v-else>
                    <el-space fill wrap>
                        <el-card>
                            <template #header>
                                <div class="card-header">
                                    <span>{{ BaiDuResultsTechnology.title }}</span>
                                    <el-button class="button" text
                                        @click="viewDetails(BaiDuResultsTechnology.url)">查看详情</el-button>
                                </div>
                            </template>
                            <div>
                                <strong>ABSTRACT</strong>
                                <p>{{ BaiDuResultsTechnology.abstract }}</p>
                            </div>
                        </el-card>
                    </el-space>
                </div>
            </div>
        </LoadingDialog>

    </el-dialog>
    <el-card shadow="hover" @click="searchCityTechnologyInfoFromBaiDu">City Technology Information from BaiDu</el-card>
</template>
