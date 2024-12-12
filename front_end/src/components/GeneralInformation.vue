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



const WikiResults = ref([]);
const BaiDuResults = ref('');



const WiKiCityInfoVisible = ref(false)
const searchCityInfoFromWiki = async () => {
    if (!nothingInputError(query)) {
        WikiResults.value = [];
        WiKiCityInfoVisible.value = !WiKiCityInfoVisible.value;
        loading.value = true;
        WikiResults.value = await searchWikipedia(query.value);
        loading.value = false;
    }

};


const BaiDuCityInfoVisible = ref(false)
const searchCityInfoFromBaiDu = async () => {
    if (!nothingInputError(query)) {
        BaiDuResults.value = '';
        BaiDuCityInfoVisible.value = !BaiDuCityInfoVisible.value;
        loading.value = true;
        BaiDuResults.value = await searchBaidu(query.value);
        loading.value = false;
    }

};


const viewDetails = (url) => {
    window.open(url, '_blank'); // 打开新链接
};
</script>


<template>
    <el-dialog v-model="WiKiCityInfoVisible" title="WiKi">
        <LoadingDialog>
            <div>
                <div v-if="WikiResults.length === 0 && !loading">未找到相关内容。</div>
                <div v-else>
                    <el-space fill wrap>
                        <el-card v-for="(result, index) in WikiResults" :key="index">
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
    <el-card shadow="hover" @click="searchCityInfoFromWiki">City General Information from Wikipedia</el-card>

    <br>


    <el-dialog v-model="BaiDuCityInfoVisible" title="BaiDU">
        <LoadingDialog>
            <div>
                <div v-if="!BaiDuResults.title && !loading">
                    未找到相关内容。
                </div>
                <div v-else>
                    <el-space fill wrap>
                        <el-card>
                            <template #header>
                                <div class="card-header">
                                    <span>{{ BaiDuResults.title }}</span>
                                    <el-button class="button" text
                                        @click="viewDetails(BaiDuResults.url)">查看详情</el-button>
                                </div>
                            </template>
                            <div>
                                <strong>ABSTRACT</strong>
                                <p>{{ BaiDuResults.abstract }}</p>
                            </div>
                        </el-card>
                    </el-space>
                </div>

            </div>
        </LoadingDialog>

    </el-dialog>
    <el-card shadow="hover" @click="searchCityInfoFromBaiDu">City General Information from BaiDu</el-card>
</template>
