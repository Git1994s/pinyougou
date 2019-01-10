//品牌控制层
app.controller('brandController', function ($scope, $controller, brandService) {
    //继承
    $controller('baseController', {$scope: $scope});


    //查询品牌列表
    $scope.findAll = function () {
        brandService.findAll().success(
            function (response) {
                $scope.list = response;
            });
    };

    //分页
    $scope.findPage = function (page, rows) {
        brandService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    };
    //保存
    $scope.save = function () {
        var object = null; //方法名称
        if ($scope.entity.id != null) { //如果有ID
            object = brandService.update($scope.entity);
        } else {
            object = brandService.add($scope.entity);
        }
        object.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                }
                else {
                    alert(response.message);
                }
            }
        );
    };
    //查询实体
    $scope.findOne = function (id) {
        brandService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    //批量删除
    $scope.dele = function () {
        if (confirm('确定要删除吗?')) { //获取选中复选框
            brandService.dele($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        $scope.reloadList();//刷新列表
                    }
                }
            );
        }
    };
    //定义搜索对象
    $scope.searchEntity = {};
    //条件查询
    $scope.search = function (page, rows) {
        brandService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.paginationConf.totalItems = response.total;  //总记录数
                $scope.list = response.rows; //给列表变量赋值
            });
    }
});