// twitterService.js

import axios from 'axios';

// 替换为你的Bearer Token
const BEARER_TOKEN = 'AAAAAAAAAAAAAAAAAAAAANRWwQEAAAAA3qtG4Bdh1FdI%2BJQH%2BgNedibJUYg%3DxiJaJBHxvZYmpUWgcZKj1OF5Fd7W5igzZPgu2kjqG2rstuMa1x';

export const searchTweets = async (query) => {
    try {
        const response = await axios.get(`https://api.twitter.com/2/tweets/search/recent`, {
            headers: {
                'Authorization': `Bearer ${BEARER_TOKEN}`
            },
            params: {
                query: query,
                max_results: 10 // 你可以根据需要设置结果数量
            }
        });
        console.log(response.data.data)
        return response.data.data; // 返回推文数据
    } catch (error) {
        console.error('Error fetching tweets:', error);
        throw error; // 重新抛出错误以便在调用时处理
    }
};
