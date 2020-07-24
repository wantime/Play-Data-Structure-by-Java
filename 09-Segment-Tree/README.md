## 各种数据结构的java实现 
#### 09-线段树
	实质：基于区间的统计查询
	使用数组作为底层结构，空间为原数组的4倍（最差情况）-->满二叉树，与堆相似

	使用Merger接口实现了线段树中父节点的取值方式(lambda表达式)

	实现了query方法与set方法
###### 方法:
	
	getSize()
	get(int index)
	leftChild(int index)
	rightChild(int index)
	buildSegmentTree(Merger merger)
	query(int queryL, int queryR)
	set(int index, E e)