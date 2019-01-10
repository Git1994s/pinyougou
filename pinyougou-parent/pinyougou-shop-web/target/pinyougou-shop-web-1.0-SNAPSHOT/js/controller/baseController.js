//公共控制层
app.controller('baseController', function ($scope) {

    //重新加载列表数据
    $scope.reloadList = function () {
        //切换页码
        $scope.search($scope.paginationConf.currentPage,
            $scope.paginationConf.itemsPerPage);
    };
    //分页控制
    $scope.paginationConf = {
        currentPage: 1,//当前页码
        totalItems: 10, //总条数
        itemsPerPage: 10, //每页记录数
        perPageOptions: [10, 20, 30, 40, 50], //页码选项
        onChange: function () {   //更改页面时触发事件
            $scope.reloadList();//重新加载
        }
    };
    $scope.selectIds = [];//用户勾选的ID集合
    //更新用户勾选复选框
    $scope.updateSelection = function ($event, id) {
        //如果是被选中,则增加到复选框中
        if ($event.target.checked) {
            $scope.selectIds.push(id); //push向集合中添加元素
        } else {
            var index = $scope.selectIds.indexOf(id); //查找值的位置
            $scope.selectIds.splice(index, 1);//参数1:移除值的位置,参数2:移除的个数
        }
    };

    //提取json字符串数据中某个属性,返回拼接字符串,逗号分隔
    $scope.jsonToString = function (jsonString, key) {
        var json = JSON.parse(jsonString);//将json字符串转换为json对象
        var value = "";  //返回的格式
        for (var i = 0; i < json.length; i++) {
            if (i > 0) {
                value += ",";
            }
            value += json[i][key];
        }
        return value;
    };

    //[{"attributeName":"规格名称","attributeValue":["规格选项1","规格选项2"...]}...]
    //在list集合中根据某个key的值查询对象  key:attributeName keyValue:规格名称
    $scope.searchObjectByKey = function (list, key, keyValue) {
        for (var i = 0; i < list.length; i++) {  //遍历集合
            if (list[i][key] == keyValue) {    //如果通过key找到相同的keyValue

                return list[i];     //返回这个集合中的每一个值
            }
        }
        return null;   //否则返回null

    }

});