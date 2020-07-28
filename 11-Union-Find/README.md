#### 11-并查集
可以快速回答给定的两个数据是否连接这一类问题
支持两个动作
union(p, q) ->  合并 isConnected(p, q) -> 是否连接


##### UnionFind1 -> Quick Find 下的Union
isConnect操作时间复杂度是O(1)
union操作时间复杂度是O(n)

##### UnionFind2 -> 真正的并查集，由孩子指向父节点的树构成
两种操作的时间复杂度为O(h)

##### UnionFind3 -> 基于集合中元素个数进行优化
在进行合并操作时，元素个数少的指向元素个数多

##### UnionFind4 -> 基于集合的树高度进行优化 
在进行合并操作时，高度小的指向高度大的

##### UnionFind5 -> 路径压缩优化
    parent[p] = parent[parent[p]]
##### UnionFind6 -> 另一种路径压缩方式
    使用了递归
    parent[p] = find(parnent[p])