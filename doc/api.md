## 接口文档（仅供测试）

#### siege服务接口

1. 根据帖子类别分页查询帖子类别或模糊查询帖子标题
  ```
  GET /siege-server/siege/listSiegePage/{siegeType}
  {
    "searchKey": "",
    "pageNo": 1,
    "pageSize": 10
  } 
  ```
2. 根据id查询帖子详情
  ```
  GET /siege-server/siege/getSiege/{id}
  ```