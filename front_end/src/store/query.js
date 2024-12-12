import { defineStore } from "pinia";
import { ref } from "vue";

export const useQueryStore = defineStore('query',()=>{
    const query = ref('');
    const location = ref(null);
    const cityId=ref('');
    
    return {query,location,cityId};
})