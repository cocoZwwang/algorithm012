# 学习笔记

## 贪心算法

### 贪心算法是什么？
- 贪心算法是一种每一步都选择中都采取在当前状态下最好或者最优的选择，从而希望导致结果是全局最好或者最优的算法。
- 贪心算法和动态规划不一样的地方，是贪心算法对每个子问题的解决方案都做出了选择，不能回退；而动态规划则会保存以前的运算结果，并且当前会根据以前的运算结果进行选择，可以回退。

### 贪心算法一般是用来解决什么问题的？
贪心算法可以解决一些最优的问题，如：求图中的最小生成树，求哈夫曼编码等。然而对于工程和生活中的问题，贪心算法一般不能得到我们所要的答案。

一旦一个问题可以通过贪心算法来解决，那么贪心法一般是解决这个问题的最好办法。由于贪心法的高效性以及其所求的答案比较接近最优结果，贪心法也可以用作辅助算法或者直接解决一些要求结果不是特别精确的问题。


### 贪心算法例子
当硬币可选集合固定：Coins=[20,10,5,1];
求最少可以用几个硬币拼出总数，比如total=36;

```
30 - 20 = 16     硬币：20
16 - 10 = 6      硬币：10
6 - 5 = 1        硬币：5、1
```
由于上面的硬币，前面的硬币都是后面硬币的倍数，所以可以使用贪心算法，每次选择最大的即可。

### 贪心算法的反例：
当硬币可选集合固定：Coins=[10,9,1]
total = 18;
```java
18 - 10 = 8     硬币：10
8 * 1 = 8       硬币：1、1、1、1、1、1、1、1
```
这里使用贪心算法无法得到最优的结果：9 + 9 = 18
所以你如果要使用贪心算法寻找最优解的话，你要为其提供相应的数学证明

### 例子1：买卖股票的最佳时机 II （全局最优解）
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
**注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。**

> 示例1：
>
> 输入: [7,1,5,3,6,4]
>
> 输出: 7
>
> 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。


这道题目，我梦只需要把每一次股票提升带来的收益相加就行，这就是股票的收益的最大化。
[具体的数学说明](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode/)

```java
public int maxProfit(int[] prices) {
    int profit = 0;
    for(int i = 1; i < prices.length; i++){
        profit += Math.max(prices[i] - prices[i - 1],0);
    }
    return profit;
}
```
### 例子2：分发饼干（全局最优解）
假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子i，都有一个胃口值gi，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj。如果 sj >= gi，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

**注意：你可以假设胃口值为正。一个小朋友最多只能拥有一块饼干。**

示例1：

>输入: [1,2,3], [1,1]
>
>输出: 1
>
>解释:
>你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
>虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
>所以你应该输出1。

这里就可以使用贪心算法，首先使用尽量小的饼干满足胃口最小的孩子，接着再用次量少的饼干，满足次量胃口的孩子，依次类推，直到饼干分完了或者孩子都有饼干了。

```java
public int findContentChildren(int[] g, int[] s) {
    if(g ==  null || s == null){
        return 0;
    }
    //先对饼干和孩子数组进行排序
    Arrays.sort(g);
    Arrays.sort(s);
    //待分饼干的孩子index
    int i = 0;
    //双指针，从小到大遍历每一个饼干
    for(int j = 0; j < s.length  && i < g.length; j++){
        每次找到一个合适的饼干，就分给当前的孩子，i++
        if(s[j] >= g[i]){
            i++;
        }
    }
    return i;
}
```
### 例子3：跳跃游戏
#### 从前面往后面贪心
每次更新最远可到达的位置，那么这个位置往前的位置肯定是可到达的，对这些位置分别为起点不断更新最远可到达的位置，如果能到达最后表示能成功否则失败！

例如：
- 如果某一个作为起跳点i的格子可以跳跃的距离是3，那么最大位置most=i+3，而(i,most]个格子都可以作为新的起跳点。
- 可以对每一个能作为起跳点的格子都尝试跳一次，如果能跳到更远则更新most。
- 如果可以一直跳到最后，就成功了。

```java
public boolean canJump(int[] nums) {
    if(nums == null){
        return false;
    }

    int most = 0;
    for(int i = 0; i < nums.length && i <= most; i++){
        most = Math.max(most,i + nums[i]);
        if(most >= nums.length - 1){
            return true;
        }
    }
    return false;
}
```
#### 从后面往前面贪心
从后面往前面遍历，把最后的能跳到目的地往前移

例如：

- 如果最后一个元素下标是n，最开始目的地end = n
- 往前遍历，如果n - 2个元素能到达n，则end = n - 2
- 依次遍历到第一个元素或者end = 0
- 如果最后end == 0则可以到达，如果不等于0则不可以到达

> 就leetcode上面运行的状况来看，后面一种的效率更加高一点

```java
public boolean canJump(int[] nums) {
    if(nums == null){
        return false;
    }
    
    int end = nums.length - 1;
    for(int i = nums.length - 1; i >= 0 && end > 0; i--){
        if(i + nums[i] >= end){
            end = i;
        }
    }
    return end == 0;
}
```

### 例子4：跳跃游戏II
给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

例子1：
> 输入: [2,3,1,1,4]
>
> 输出: 2
>
> 解释: 跳到最后一个位置的最小跳跃数是 2。从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。

#### 正向查找可到达的最大位置
我们「贪心」地进行正向查找。

只要我每次都跳最远的，那我就是最快的！。

##### 第一种代码写法：
```java
public int jump(int[] nums){
    int n = nums.length;
    int steps = 0;
    int end = 0;
    int maxPostion = 0;
    for(int i = 0; i < n - 1; i++){
        maxPostion = Math.max(maxPostion,nums[i] + i);
        //当遍历到上一次目标点的时候，这时候的最远跳跃点就是下一次的目标点
        //初始时候的上一次目标点就是0
        if(i == end){
            end = maxPostion;
            steps++;
        }
    }
    return steps;
}
```

##### 第二种代码写法：
 ```java
public int jump(int[] nums) {
    int steps = 0;
    int i = 0;
    int end = 0;
    int maxPostion;
    //每次跳跃到end，steps++
    //直到end > nums.length - 1
    while(end < nums.length - 1){
        // 假如上一次跳跃点是lastEnd，当前跳跃点是end
        // 跳跃完后在区间（lastend,end]找到最远的跳跃点作为下一次的跳跃点
        maxPostion = end;
        while(i <= end){
            if(i + nums[i] > maxPostion){
                maxPostion = nums[i] + i;
            }
            i++;
        }
        steps++;
        end = maxPostion;
    }
    return steps;
}
 ```



## 二分查找

### 二分查找的前提条件
- 目标函数的单调性（单调递增或者单调递减）
- 存在上下界
- 可以通过索引访问

### 二分查找的代码模板
#### java
 ```java
 public int binarySearch(int[] array, int target) {
     int left = 0;
     int right = array.length - 1;
     while(left <= right){
         int mid = (right - left) / 2 + left;
         if(array[mid] == target){
             return mid;
         }else if(array[mid] > target){
             right = mid  - 1;
         }else{
             left = mid + 1;
         }
     }
     
     return -1;
 }
 ```

 #### python
 ```python
left, right = 0, len(array) - 1 
while left <= right: 
	  mid = (left + right) / 2 
	  if array[mid] == target: 
		    # find the target!! 
		    break or return result 
	  elif array[mid] < target: 
		    left = mid + 1 
	  else: 
		    right = mid - 1
 ```

#### C/C++
```C/C++
int binarySearch(const vector<int>& nums, int target) {
	int left = 0, right = (int)nums.size()-1;
	
	while (left <= right) {
		int mid = left + (right - left)/ 2;
		if (nums[mid] == target) return mid;
		else if (nums[mid] < target) left = mid + 1;
		else right = mid - 1;
	}
	
	return -1;
}
```

#### javaScript
```javaScript
let left = 0, right = len(array) - 1
while (left <= right) {
  let mid = (left + right) >> 1
  if (array[mid] === target) {
  /*find the target*/; 
    return 
  }
  else if (array[mid] < target) left = mid + 1
  else right = mid - 1
}
```

### 二分法例子：

#### 使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方 

二分法：

- 数组没被旋转，这样第一次中间值t满足(t > nums[0]  && t < nums[n - 1])，这时候直接返回第一个元素即可
- 数组选择，每次二分查找时候，如果旋转点在左边就向左规约，否则向右规约
- 旋转点左边的条件：t < nums[0]

```java
public int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    int n = nums.length;
    while(left <= right){
        int mid = (right -left) / 2 + left;
        //目标值比较
        int t = nums[mid];
        //数组没被旋转(t > nums[0]  && t < nums[n - 1])，直接返回第一个元素
        if(t > nums[0]  && t < nums[n - 1]){
            return nums[0];
        }
        //后面是处理数组发生了旋转的情况
        if(t <= nums[(mid - 1 + n) % n] && t <= nums[(mid + 1) % n]){
            return nums[mid];
        }else if(t < nums[0]){ //左边包含旋转点（t < nums[0]）
            right = mid - 1;
        }else {
            left = mid + 1;
        }
    }
    return -1;
}
```



#### 求评分根
实现int sqrt(int x)函数。

计算并返回x的平方根，其中x是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例：
>
>输入: 4
>
>输出: 2

示例 2:
>
> 输入: 8
>
> 输出: 2
>
> 说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。

##### 二分法的代码实现：

```java
public int mySqrt(int x) {
    if(x == 0 || x == 1){
        return x;
    }
    int left = 0;
    int right = x;
    int ans = -1;
    while(left <= right){
        int mid = (right - left) / 2 + left;
        //这里mid相乘之前要转成long类型，要不相乘的结果是int会溢出
        long squre = (long) mid * mid;
        if(squre <= x){
            left = mid  + 1;
            ans = mid;    
        }else{
            right = mid - 1;
        }
    }
    return ans;
}
```

##### 这道题还有另外一种解法，[牛顿迭代法](https://leetcode-cn.com/problems/sqrtx/solution/er-fen-cha-zhao-niu-dun-fa-python-dai-ma-by-liweiw/)：

```java
public int mySqrt(int a) {
    long x = a;
    while(x * x > a){
        x = (x + a / x) / 2;
    }
    return (int)x;
}
```

#### 搜索旋转排序数组
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组[0,1,2,4,5,6,7]可能变为[4,5,6,7,0,1,2])。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回-1。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是O(logn) 级别。

示例 1:
> 输入: nums = [4,5,6,7,0,1,2], target = 0
>
> 输出: 4

示例2:
> 输入: nums = [4,5,6,7,0,1,2], target = 3
>
> 输出: -1

##### 第一种解法：
- 利用二分法先找出数组旋转点的下标（不能直接遍历，因为题目时间复杂度要求是o(logn)
- 利用二分法找出目标元素的下标
```java
//利用二分法先找出数组旋转点的下标
private int changedPostion(int[] nums,int left,int right){
    int n = nums.length;
    while(left <= right){
        int mid = (right - left ) / 2 + left;
        //如果一个元素大于或者等于上一个元素，同时小于等于 下一个元素，那说明其就是旋转点
        if(nums[mid] <= nums[(mid + n - 1) % n] && nums[mid] <= nums[(mid + 1) % n]){
            return mid;
        //如果mid大于左边同时大于右边，说明旋转点在mid的右边
        } if(nums[mid] >= nums[left] && nums[mid] >= nums[right]){
            left = mid + 1;
        }else {
            right = mid - 1;
        }
    }
    return -1;
}

//利用二分法找出目标元素的下标
public int search(int[] nums, int target) {
    if(nums == null || nums.length == 0){
        return -1;
    }
    int n= nums.length;
    //旋转后的最小值的位置
    int leftP = changedPostion(nums,0,n- 1);
    //旋转后最大值的位置
    int rightP = (leftP +  n) % n - 1;
    int left = 0;
    int right = n - 1;
    while(left <= right){   
        int mid = (right - left) / 2 + left;
        //转换成旋转后的下标
        int midIndex = (mid + leftP) % n;
        if(nums[midIndex] == target){
            return midIndex;
        }else if(nums[midIndex] > target){
            right = mid - 1;
        }else{
            left = mid + 1;
        }
    }
    return -1;
}
```

##### 第二种解法
- 直接用二分法进行一次遍历。
- nums[0] <= nums[mid] (0 - mid)不包含旋转，如果target > nums[mid] || target < nums[0]，表示target在区间(mid,nums.length)，向右半区收敛。
- nums[0] > nums[mid] (0 - mid)包含旋转，如果target < nums[0] && target > nums[mid]表示target在区间(mid,nums.length)，向右半区收敛。
- 其他情况向前规约，向左半区收敛。

```java
public int search(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while(left <= right){
        int mid = (right -left) / 2 + left;
        if(nums[mid] == target){
            return mid;
        }
        // 如果[left,mid]是升序
        if(nums[mid] >= nums[0] &&
            // target在右区间 
            (target > nums[mid] || target < nums[0])){
            //移动到右半区
            left = mid + 1;

        }else if(// 如果[left,mid]不是升序，包含旋转点
            // target在右区间 
             target > nums[mid] && target < nums[0]){
            //移动到右半区
            left = mid + 1;

        }else{
            //移动到左半区
            right = mid  - 1;
        }
    }

    return -1;
}
```