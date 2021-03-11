### elasticsearch 数据迁移
> a. 创建索引如下
> put /test-v1
  ```
  {
	"settings": {
		"index": {
			"number_of_shards": "2",
			"number_of_replicas": "1"
		}
	},
	"mappings": {
		"properties": {
			"user_id": {
				"type": "long"
			},
			"id": {
				"type": "long"
			}
		}
	}
  }
  ```
> b.现在把上面一个索引的user_id类型变成string
> c.创建新索引 put /test-v2
> "number_of_replicas": "0" 副本先设置为0，防止我们迁移文档的同时又发送到副本节点，影响性能
> "refresh_interval": "-1" 限制其刷新
  ```
  {
	"settings": {
		"index": {
			"number_of_shards": "2",
			"number_of_replicas": "0",
			"refresh_interval": "-1"
		}
	},
	"mappings": {
		"properties": {
			"user_id": {
				"type": "keyword"
			},
			"id": {
				"type": "long"
			}
		}
	}
  }
  ```
> d. 开始数据迁移
  ```
  POST /_reindex
  {
    "source": {
      "index": "test-v1"
    },
    "dest": {
      "index": "test-v2"
    }
  }
  ```
> e. 配置test-v2的settings，让其开启刷新,然后就可以看到test-v2的数据已经存在
  ```
  PUT /test-v2/_settings
  {
    "refresh_interval": "1s",
    "number_of_replicas": 1
  }
  ```
> f. 数据迁移完成之后，索引test-v1已经废弃，但是接口都是指向之前的索引，我们就在新索引添加别名即可。不过要先删除废弃的索引test-v1
  ```
  DELETE /test-v1
  
  新索引添加别名(最好每次创建索引都创建别名，这样在数据迁移时线上可以丝滑的切换
  POST _aliases
  {
	"actions": [
		{"add": {"index": "test-v2", "alias": "test-v1"}}
	]
  }
  
  获取别名
  GET /test-v2/_alias
  
  删除别名
  POST /_aliases
  {
	"actions": [
		{"remove": {"index": "test-v2", "alias": "test-v1"}}
	]
  }
  ```
> g. 前面属于同集群数据迁移，也是可以跨集群迁移的，只需要在迁移时elasticsearch.yml配置白名单即可，其他步骤都一样
  ```
  reindex.remote.whitelist: x.x.x.x:9200
  
  数据迁移参数有点不一样（样例取至官网）
  POST _reindex
  {
    "source": {
      "remote": {
        "host": "http://oldhost:9200",
        "username": "user",
        "password": "pass"
      },
      "index": "source",
      "query": {
        "match": {
          "test": "data"
        }
      }
    },
    "dest": {
      "index": "dest"
    }
  }
  ```
> h. 参考[官方文档](https://www.elastic.co/guide/en/elasticsearch/reference/7.9/reindex-upgrade-remote.html)
  