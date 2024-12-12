import axios from "axios"
import { ref } from "vue"
export const searchHeFengCityId=async(query)=>{
    const results = ref([]);
    try{
        const response = await axios.get(`http://localhost:8080/api/HeFeng/CityId?query=${query}`)
        results.value = response.data.location;
        // console.log("!!!",results.value);
        return results.value; 
    }
    catch(error){
        console.log("error fetching data:",error);
        return [];
    }
}

export const searchHeFengCityWeather=async(id)=>{
    const result = ref('');
    try{
        const response = await axios.get(`http://localhost:8080/api/HeFeng/CityWeather?id=${id}`)
        result.value = response.data.now;
        // console.log("111",result.value)
        return result.value; 
    }
    catch(error) {
        console.log("error fetching data:",error);
        return '';
    }
}

export const searchHeFengCityAirQuality=async(id)=>{
    const results = ref('');
    try{
        const response = await axios.get(`http://localhost:8080/api/HeFeng/CityAirQuality?id=${id}`)
        results.value = response.data.now;
        // console.log("!!!",results.value);
        return results.value; 
    }
    catch(error){
        console.log("error fetching data:",error);
        return [];
    }
}

export const searchHeFengCityDailyWeather=async(id)=>{
    const results = ref([]);
    try{
        const response = await axios.get(`http://localhost:8080/api/HeFeng/CityDailyWeather?id=${id}`)
        results.value = response.data.daily;
        // console.log("!!!",results.value);
        return results.value; 
    }
    catch(error){
        console.log("error fetching data:",error);
        return [];
    }
}