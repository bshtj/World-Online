<script setup>
import { ref } from "vue";
import { searchBitcoin } from "../apis/UseCoinDeskApis";
import { fetchCampaignFinanceData } from "../apis/UseNewYorkTimesApis";

import { nothingInputError } from "../utils/ErrorMessage";

import { useQueryStore } from "../store/Query";
import { useLoadingStore } from "../store/loading";
import { storeToRefs } from "pinia";
import LoadingDialog from "./LoadingDialog.vue";

const queryStore = useQueryStore();
const { query } = storeToRefs(queryStore);

const loadingStore = useLoadingStore();
const { loading } = storeToRefs(loadingStore);

const bitcoinData = ref(null);
const NewYorkFinanceResults = ref([]);


const BitcoinPriceVisible = ref(false);
const getBitcoinPrice = async () => {
    bitcoinData.value = '';
    BitcoinPriceVisible.value = !BitcoinPriceVisible.value;
    loading.value = true;
    const data = await searchBitcoin();
    if (data) {
        bitcoinData.value = data; // 更新比特币数据
    }
    loading.value = false;
};


const CampaignFinanceDataVisible = ref(false)
const getCampaignFinanceData = async () => {
    if (!nothingInputError(query)) {
        NewYorkFinanceResults.value = [];
        CampaignFinanceDataVisible.value = !CampaignFinanceDataVisible.value;
        loading.value = true;

        NewYorkFinanceResults.value = await fetchCampaignFinanceData(query.value);
        loading.value = false;
    }

};


const viewDetails = (url) => {
    window.open(url, '_blank'); // 打开新链接
};
</script>


<template>
    <el-dialog v-model="BitcoinPriceVisible" title="Bitcoin">
        <LoadingDialog>
            <div>
                <p v-if="!bitcoinData && !loading">未能获取Bitcoin</p>
                <p v-else>Current Price: ${{ bitcoinData.usd }}</p>
            </div>
        </LoadingDialog>

    </el-dialog>
    <el-card shadow="hover" @click="getBitcoinPrice">Present Bitcoin Price</el-card>

    <br>

    <el-dialog v-model="CampaignFinanceDataVisible" title="NewYorkTimesFinance">
        <LoadingDialog>
            <div>
                <div v-if="NewYorkFinanceResults.length === 0 && !loading">
                    未找到相关内容。
                </div>
                <el-space fill wrap>
                    <el-card v-for="(result, index) in NewYorkFinanceResults" :key="index">
                        <template #header>
                            <div class="card-header">
                                <span>结果 {{ index + 1 }}</span>
                                <el-button class="button" text @click="viewDetails(result.web_url)">查看详情</el-button>
                            </div>
                        </template>
                        <div>
                            <strong>ABSTRACT</strong>
                            <p>{{ result.abstract }}</p>
                        </div>
                    </el-card>
                </el-space>
            </div>
        </LoadingDialog>
    </el-dialog>
    <el-card shadow="hover" @click="getCampaignFinanceData">City relevant economic News from NewYorkTimes
        <strong>(better
            search in
            English)</strong></el-card>
</template>