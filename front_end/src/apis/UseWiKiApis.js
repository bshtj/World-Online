export async function searchWikipedia(query) {
  try {
    const url = `https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=${encodeURIComponent(query)}&format=json&origin=*`;
    const response = await fetch(url);
    const data = await response.json();
    // console.log(111,data.query);
    // console.log(222,data.query.search)
    return data.query.search.map(item => ({
      title: item.title,
      snippet: item.snippet,
    }));
  
  } catch (error) {
    console.error('Error fetching data:', error);
    return [];
  }
}

export async function searchSpecifiedWikipedia(query, category) {

  try {
    const url = `https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=${encodeURIComponent(query)}+${encodeURIComponent(category)}&format=json&origin=*`;
    const response = await fetch(url); // 异步获取数据
    const data = await response.json(); // 将响应解析为 JSON

    return data.query.search.map(item => ({
      title: item.title,
      snippet: item.snippet,
    }));

  } catch (error) {
    console.error('Error fetching data:', error);
    return []; 
  }
}
