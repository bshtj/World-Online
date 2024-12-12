// import { useQueryStore } from "../store/Query";
// import { storeToRefs } from "pinia";
import { ElMessage } from 'element-plus'; // 引入 ElMessage

// const queryStore = useQueryStore();
// const { query } = storeToRefs(queryStore);

export const nothingInputError=(query)=>{
     if (!query.value) {
        ElMessage.error("Please input a city first!");
        return true;  // 返回 true 表示有错误
    }
    return false; // 返回 false 表示没有错误
}