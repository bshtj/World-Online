import axios from "axios"
import { ref } from "vue"
export const searchJuHeNews=async(query)=>{
    const results = ref([]);
    try{
        const response = await axios.get(`http://localhost:8080/api/JuHe/News?query=${query}`)
        results.value = response.data.result.list;
        // console.log("!!!",results.value);
        return results.value; 
    }
    catch(error){
        console.log("error fetching data:",error);
        return [];
    }
}

export const searchJuHeTouristAttraction=async(query)=>{
    const results = ref([]);
    try{
        const response = await axios.get(`http://localhost:8080/api/JuHe/TouristAttraction?query=${query}`)
        results.value = response.data.result.list;
        // console.log("!!!",results.value);
        return results.value; 
    }
    catch(error){
        console.log("error fetching data:",error);
        return [];
    }
}