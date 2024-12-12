// api.js
export async function searchBitcoin() {
  try {
    const response = await fetch('https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd');
    if (!response.ok) throw new Error('Network response was not ok');
    const data = await response.json();
    return data.bitcoin; // 返回比特币数据
  } catch (error) {
    console.error('Error fetching Bitcoin data:', error);
    return null; // 返回 null 以指示错误
  }
}
