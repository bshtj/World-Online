
   export const loadPlaceSearch = (map) => {
    // 输入提示
    var autoOptions = {
        input: "Input",
        
    };

    AMap.plugin(['AMap.PlaceSearch', 'AMap.AutoComplete'], function() {
        var auto = new AMap.AutoComplete(autoOptions);
        var placeSearch = new AMap.PlaceSearch({
            map: map // 构造地点查询类
        });

        auto.on("select", select); // 注册监听，当选中某条记录时会触发

        function select(e) {
            placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name); // 关键字查询

            // 设置地图中心到选中的地点
            if (e.poi.location) {
                map.setCenter(e.poi.location); // 移动地图中心到选中的地点
            }
        }
    });
};
