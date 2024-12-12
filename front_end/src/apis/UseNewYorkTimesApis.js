export async function fetchCampaignFinanceData(query) {
  try {
    const apiKey = 'N5VQegovWRUhzXQUww2cHW9d2CsB2sw4'; // 替换为你的 API 密钥
    const url = `https://api.nytimes.com/svc/search/v2/articlesearch.json?q=${encodeURIComponent(query)}&api-key=${apiKey}`;
    
    const response = await fetch(url);
    
    // 检查响应状态
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
   
    const data = await response.json();
    // console.log("11111", data);
    // 返回所需的数据
    // console.log("11",data.response.docs)
    return data.response.docs;
  
  } catch (error) {
    console.error('Error fetching campaign finance data:', error);
    return [];
  }
}
