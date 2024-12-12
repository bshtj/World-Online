import axios from "axios";
export async function searchBaidu(query) {
 try {
    const response = await axios.get(`http://localhost:8080/api/Baidu?query=${query}`)
    const data = response.data;
    // console.log("!!!",data);
    return data;
  
  } catch (error) {
    console.error('Error fetching data:', error);
    return null;
  }
}


