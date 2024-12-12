export const fetchWeiboData = async (query) => {
    const accessToken = '2.00OCDVaI4BfoxB9d89a67b9da7EP2D'; // 请替换为你的 Access Token
    const url = `https://api.weibo.com/2/search/topics.json?access_token=${accessToken}&q=${encodeURIComponent(query)}`;

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        // 检查响应状态
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        console.log("获取到的数据:", data); // 打印获取到的数据
        return data; // 返回获取到的搜索结果
    } catch (error) {
        console.error('获取微博数据时出错:', error);
        return null; // 返回 null 或者根据需求处理错误
    }
}