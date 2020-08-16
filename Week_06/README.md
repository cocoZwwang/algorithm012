# 学习笔记

# 动态规划的定义
动态规划（英语：Dynamic programming，简称DP）是一种在数学、管理科学、计算机科学、经济学和生物信息学中使用的，通过把原问题分解为相对简单的子问题的方式求解复杂问题的方法。

动态规划常常适用于有重叠子问题[1]和最优子结构性质的问题，动态规划方法所耗时间往往远少于朴素解法。

动态规划背后的基本思想非常简单。大致上，若要解一个给定问题，我们需要解其不同部分（即子问题），再根据子问题的解以得出原问题的解。

通常许多子问题非常相似，为此动态规划法试图仅仅解决每个子问题一次，从而减少计算量：一旦某个给定子问题的解已经算出，则将其记忆化存储，以便下次需要同一个子问题解之时直接查表。这种做法在重复子问题的数目关于输入的规模呈指数增长时特别有用。
# 动态规划和递归分治的异同
- 动态规划和递归 分治没有本质上的区别（关键是看有没有最优的子结构）
- 共性：找到重复的子问题
- 差异性：最优子结构，中途可以淘汰次优解
# 动态规划的关键点
- 找到最优的子结构：opt[n] = best_of(opt[n - 1],opt[n - 2]...)
- 存储中间状态：opt[i]
- 递推公式（DP方程或者动态规划）
  例如：
  - 斐波那契：opt[n] = opt[n - 1] +  opt[n - 2];
  - 二维路径：opt[i][j] = opt[i + 1][j] + opt[i][j + 1];
# 动态规划例子
## 斐波那契数列
Fibonacci公式：f(n) = f(n - 1)+ f(n -2)
#### 递归实现方式
```java
public int fib(n){
    return n <= 1 ? n : fib(n - 1) + fib(n - 2);
}
```
这个写法是非常初级的递归方式，时间复杂度是O(2^n)。

下面可以通过中间缓存，把时间复杂度减低到O(n)：
```java
public int fib(int n,int[] meno){
    if(n <= 1) return n;
    if(meno[n] == 0)
        meno[n] = fib(n - 1,meno) + fib(n - 2,meno);
    return meno[n];
}
```
#### 使用动态规划
```java
public int fib(n){
    int[] da = new int[n+1];
    da[0] = 0;
    da[1] = 1;
    for(int i = 2; i <= n; i++){
        da[i] = da[i - 1] + da[i - 2];
    }
    return da[n];
}
```
## 路径计数

如下图所示，计算绿色小人从最左上角走到最右下角一共有多少条路径？
- 小人一次只能向右边移动一格或者向下移动一格。
- 黄色表示是障碍物不能通过

![路径计数](AAF3F23565E24117991F9EA1C15380D8)

分析：
从上图可以看出每个格子只有两种走法，向下或者向右，例如B只有E和C两个格子可以走，那么B的路径总数，就是从E出发的路径总数 + 从C出发的路径总数，依次类推。

### 递归分治
最简单的可以使用递归分治来解决，时间复杂度和斐波那契数列类似：O(2^n)。
```java
 public int countPaths(int[][] grid,int row,int col){
     int rowCount = grid.length;
     int colCount = grid[0].length;
     if(row < 0 || row >= rowCount || col < 0 || col >= colCount){
         return 0;
     }
     
     if(row == rowCount - 1 && col == colCount - 1){
         return 1;
     }
     
     return countPaths(grid,row + 1,col) + countPaths(grid,row,col + 1);
 }
```

### 动态规划
- 我们用da[i][j]来表示从[i,j]出发到达终点的路径总数，从上面图可以看出最后一列和最后一行的每一个格子的的路径总数都是1。假如是个n*m的方格。
- 初始化,我们从右下角往上数，最后一行和列da[0][0...m - 1] = 1，da[0...n -1][0]=1。
- 状态转移方程 da[i][j] = da[i + 1][j] + da[i][j + 1]。
- 最后结果是da[n - 1][m - 1]。

完整逻辑：
```java
if(a[i][j] == "空地"){
    da[i][j] = da[i + 1][j] + da[i][j + 1];
}else{
    da[i][j] = 0;
}
```

## 最长公共子序列

给定两个字符串text1和text2，返回这两个字符串的最长公共子序列的长度。

一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

若这两个字符串没有公共子序列，则返回 0。

示例 1:

>输入：text1 = "abcde", text2 = "ace" 
>
>输出：3  
>
>解释：最长公共子序列是 "ace"，它的长度为 3。
>

示例 2:

>输入：text1 = "abc", text2 = "abc"
>
>输出：3
>
>解释：最长公共子序列是 "abc"，它的长度为 3。

示例 3:

>输入：text1 = "abc", text2 = "def"
>
>输出：0
>
>解释：两个字符串没有公共子序列，返回 0。

**提示:**

**1 <= text1.length <= 1000**

**1 <= text2.length <= 1000**

**输入的字符串只含有小写英文字符。**

>来源：力扣（LeetCode）
>链接：https://leetcode-cn.com/problems/longest-common-subsequence
>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

初看这题一脸懵逼！

**第一步找出重复的子问题**

  int n = text1.length();

  int m = text2.length();

  text1Sub=text1.substring(0,n-1);

  text2Sub=text2.substring(0,m-1);

  如果

  text1 = text1Sub + "A"

  text2 = text2Sub + "A"

  因为"A"=="A"

  LCS= text1Sub和text2Sub的LCS + 1

  如果

  text1 = text1Sub + "A"

  text2 = text2Sub + "B"

  因为"A" != "B"

  LCS = max(text1Sub和text2的LCS,text2Sub和text1的LCS)

 **转换为DP方程**

 LCS[i][j]表示text1.substring(0,i)和text2.substring(0,j)的LCS
 ```java
  if(text1.charAt(i) == text2.charAt(j)){
     LCS[i][j] = LCS[i - 1][j - 1] + 1; 
  }else{
      LCS[i][j] = Math.max(LCS[i][j - 1],LCS[i - 1][j]);
  }
 ```
 **找出动态递推的初始状态**

 如果text1为空，不管text2多长，他们的最大子序列长度都是0，即LCS[0][0...j]=0;
 反之如果text2为空，不管text1多长，他们的最大子序列长度都是0，即LCS[0...i][0]=0。

 ```java
 //由于数组元素默认值就是0，所以省去了初始化的步骤
 //da下标的意义表示有多少个字符，即:
 //text1.charAt(0)的LCS是da[1], text1=""的LCS是da[0]。
 int[] da = new int[text1.length + 1][text2.length + 1];
 ```
 **具体代码实现**
 ```java
 public int longestCommonSubsequence(String text1, String text2) {
    char[] s = text1.toCharArray();
    char[] t = text2.toCharArray();

    int sn = s.length;
    int tn = t.length;

    int[][] da = new int[sn + 1][tn + 1];
    for (int i = 1; i < sn + 1; i++) {
        for (int j = 1; j < tn + 1; j++) {
            if(s[i - 1] == t[j - 1]){//i，j表示第几个字符，字符串的下标要-1
                da[i][j] = da[i - 1][j - 1] + 1;
            }else{
                da[i][j] = Math.max(da[i - 1][j],da[i][j - 1]);
            }
        }
    }
    return da[sn][tn];
}
 ```

 ### 打家劫舍

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

示例 1：

>输入：[1,2,3,1]
>
>输出：4
>
>解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
>
>偷窃到的最高金额 = 1 + 3 = 4 。


示例 2：

>输入：[2,7,9,3,1]
>
>
>输出：12
>
>解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
>
>偷窃到的最高金额 = 2 + 9 + 1 = 12 。

**提示：**

**0 <= nums.length <= 100**

**0 <= nums[i] <= 400**

>来源：力扣（LeetCode）
>链接：https://leetcode-cn.com/problems/house-robber
>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

**分析**

- 对于每一个房子而言，都存在偷和不偷的问题。

- 第1个房子的财物是a[1]
  - 如果偷sum[1] = a[1]
  - 如果不偷sum[1] = 0.

- 第2个房子的财物是a[2]
  - 如果a[1]偷了，那么a[2]一定不能被偷，sum[2] = sum[1]
  - 如果a[1]没被偷，那么a[2]可以选择偷也可以选择不偷
    - 如果偷sum[2] = sum[1] + a[2]
    - 如果不偷sum[2] = sum[1]

- 第n个房子的财物是a[n]
  - 如果a[n - 1]偷了，那么a[n]一定不能被偷，sum[n] = sum[n - 1]
  - 如果a[n - 1]没被偷，那么a[n]可以选择偷也可以选择不偷
    - 如果偷sum[n] = sum[n - 1] + a[n]
    - 如果不偷sum[n] = sum[n - 1]

#### 暴力递归解法

穷举所有可能性对比出最大值，时间复杂度O(2^n)
```java
public int rob(int[] nums) {
    return rob(nums,0,false);
}

public int rob(int[] nums,int level,boolean preStat){
    if(level == nums.length){
        return 0;
    }
    //上一家房子偷过了
    if(preStat){
        return rob(nums,level + 1,!preStat);
    }else{//上一家没偷
        //没偷
        int n = rob(nums,level + 1,preStat);
        //偷了
        int y = rob(nums,level + 1,!preStat) + nums[level];
        return Math.max(n,y);
    }
}
```
显然这种方法很浪费性能。。。

#### 动态规划

- sum[i][0]表示第i房子不偷的财物总数
- sum[i][1]表示第i房子偷了的财物总数
- sum[i+1][0] = max(sum[i][0],sum[i][1]),如果i+1不偷，那么不用管i偷不偷，只需取之前最大值就行
- sum[i+1][1] = sum[i][0] + a[i+1]，如果i要偷，那么i必须不能偷，只能取sum[i][0]

```java
public int rob(int[] nums) {
    if(nums == null || nums.length == 0){
        return 0;
    }

    int n = nums.length;
    int[][] da = new int[n][2];
    da[0][0] = 0;
    da[0][1] = nums[0];
    for (int i = 1; i < n; i++) {
        da[i][0] = Math.max(da[i - 1][0],da[i - 1][1]);
        da[i][1] = da[i - 1][0] + nums[i];
    }

    return Math.max(da[n - 1][0],da[n - 1][1]);
}
```

#### 动态规划改进
- sum[i]表示第i能偷到的财物
- 如果添加两个房子i+1和i+2，那最后会出现两种结果
  - 一种偷了i+2，这时候最后结果sum[i + 2] = sum[i] + a[i + 2]，不用理会sum[i]是否偷还是不偷,肯定是sum[i + 2] > sum[i]。
  - 一种是偷了i+1，这时候最后结果是sum[i + 1],由于数组全部是整数，所以不管sum[i]是偷还是不偷都会是sum[i + 1] >= sum[i]。
  - 所以最后的最大财物数量就是max(sum[i + 2],sum[i + 1]),即是max(sum[i] + a[i + 2],sum[i + 1])
  - 转换成数组下标i就是max(sum[i - 2] + a[i],sum[i - 1])
- 初始化
  - sum[0] = a[0].
  - sum[1] = max(a[0],a[1])。

代码实现
```java
public int rob(int[] nums) {
  if (nums == null || nums.length == 0) {
      return 0;
  }
  if (nums.length == 1) {
      return nums[0];
  }
    
  int n = nums.length;
  int[] da = new int[n];
  //初始化
  da[0] = nums[0];
  da[1] = Math.max(nums[0],nums[1]);
  for (int i = 2; i < n; i++) {
    //上面第二步的状态转移方程
    da[i] = Math.max(da[i - 2] + nums[i],da[i - 1]);
  }

  return da[n - 1];
}
```

#### 动态规划改进二
在上面的代码中，状态转移方程da[i] = Math.max(da[i - 2]， + nums[i],da[i - 1])，每次只需用到两个变量da[i - 2],da[i - 1]就行。

代码修改
```java
public int rob(int[] nums) {
  if (nums == null || nums.length == 0) {
      return 0;
  }
  if (nums.length == 1) {
      return nums[0];
  }

  int n = nums.length;
  int max0 = nums[0];//da[0] = nums[0];
  int max1 = Math.max(nums[0], nums[1]);//da[1] = Math.max(nums[0], nums[1]);
  for (int i = 2; i < n; i++) {
      int tmp = max1;
      max1 = Math.max(max0 + nums[i], max1);
      max0 = tmp;
  }

  return max1;
}
```