<script setup>
import { ref } from "vue";

import { searchJuHeNews, searchJuHeTouristAttraction } from "../apis/UseJuHeApis";

import { nothingInputError } from "../utils/ErrorMessage";

import { useQueryStore } from "../store/Query";
import { useLoadingStore } from "../store/loading";
import { storeToRefs } from "pinia";
import LoadingDialog from "./LoadingDialog.vue";


const queryStore = useQueryStore();
const { query } = storeToRefs(queryStore);

const loadingStore = useLoadingStore();
const { loading } = storeToRefs(loadingStore);

const JuHeNews = ref([]);
const JuHeNewsVisible = ref(false);
const getJuHeNews = async () => {
    if (!nothingInputError(query)) {
        JuHeNews.value = [];
        JuHeNewsVisible.value = !JuHeNewsVisible.value;
        loading.value = true;
        JuHeNews.value = await searchJuHeNews(query.value);
        loading.value = false;
    }
};

const JuHeTouristAttract = ref([]);
const JuHeTouristAttractVisible = ref(false);
const getJuHeTouristAttract = async () => {
    if (!nothingInputError(query)) {
        JuHeTouristAttract.value = [];
        JuHeTouristAttractVisible.value = !JuHeTouristAttractVisible.value;
        loading.value = true;
        JuHeTouristAttract.value = await searchJuHeTouristAttraction(query.value);
        loading.value = false;
    }
};

const viewDetails = (url) => {
    window.open(url, '_blank'); // 打开新链接
};
</script>


<template>
    <el-dialog v-model="JuHeNewsVisible" title="City News">
        <LoadingDialog>
            <div>
                <div v-if="JuHeNews.length === 0 && !loading">
                    未找到相关内容。
                </div>
                <div v-else>
                    <el-space fill wrap>
                        <el-card v-for="(news, index) in JuHeNews" :key="index">

                            <div class="card-header">
                                <span>News {{ index + 1 }}: {{ news.title }}</span>
                                <el-button class="button" text @click="viewDetails(news.url)">查看详情</el-button>
                            </div>

                        </el-card>
                    </el-space>
                </div>

            </div>

        </LoadingDialog>
    </el-dialog>
    <el-card shadow="hover" @click="getJuHeNews">City Society News</el-card>

    <br />
    <el-dialog v-model="JuHeTouristAttractVisible" title="City Tourist Attract">
        <LoadingDialog>
            <div>
                <div v-if="JuHeTouristAttract.length === 0 && !loading">
                    未找到相关内容。
                </div>
                <div v-else>
                    <el-space fill wrap>
                        <el-card v-for="(TouristAttract, index) in JuHeTouristAttract" :key="index">
                            <template #header>
                                <div class="card-header">
                                    <span>Tourist Attract {{ index + 1 }}: {{ TouristAttract.name }}</span>
                                </div>
                            </template>
                            <p>{{ TouristAttract.content }}</p>
                        </el-card>
                    </el-space>
                </div>

            </div>

        </LoadingDialog>
    </el-dialog>
    <el-card shadow="hover" @click="getJuHeTouristAttract">City Society Tourist Attraction</el-card>

</template>
