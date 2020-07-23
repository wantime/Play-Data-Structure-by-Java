## 哈希函数的设计

#### 整型
	膜一个素数
#### 浮点型
	移动小数点后膜一个素数（当整型处理）
#### 字符串
	转成整型处理
	166 = 1 * 10^2 + 6 * 10^1 + 6 * 10^0
	code = c * 26^3 + o * 26^2 + d * 26^1 + e * 26^0

	hash(code) = (c*B^3 + o*B^2 + d*B^1 + e*B^0) % M
		= ((((c * B) + o) * B + d) * B + e) % M
		= ((((c % M) * B + o) % M * B + d) % M * B + e) % M
```java
	int hash = 0
	for(int i = 0; i < s.length(); i ++)
		hash = (hash * B + s.charAt(i)) % M;
```
### 复合类型
	转成整型处理