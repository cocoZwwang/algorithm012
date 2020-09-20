# 学习笔记

## 不同路径II

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？



网格中的障碍物和空位置分别用 `1` 和 `0` 来表示。

**说明：**m和 *n* 的值均不超过 100。

> 示例 1:
>
> 输入:
> [
>   [0,0,0],
>   [0,1,0],
>   [0,0,0]
> ]
> 输出: 2
> 解释:
> 3x3 网格的正中间有一个障碍物。
> 从左上角到右下角一共有 2 条不同的路径：
>
> 1. 向右 -> 向右 -> 向下 -> 向下
> 2. 向下 -> 向下 -> 向右 -> 向右
>



**解法：动态规划**

状态方程定义dp\[i]\[j]：表示从第一个格子走到第i行第j列最多有多少条不同的路径。

状态方程转移：

- 如果当前是障碍物，dp\[i]\[j] = 0。
- 如果当前不是障碍物，dp\[i]\[j] =dp\[i - 1]\[j] + dp\[i]\[j - 1]。
- 最后结果是dp\[m - 1]\[n - 1]

初始状态：

- ​	dp\[0]\[0] = obstacleGrid\[0]\[0] == 0 ? 1 : 0。
- ​	for i in (1...n)：dp[i] = obstacleGrid\[0]\[i] == 0 ? dp\[0][i - 1] : 0。



代码：

```java
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0){
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //初始化
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for(int i = 1; i < n; i++){     
            dp[0][i] = obstacleGrid[0][i] == 0 ? dp[0][i - 1] : 0;
        }
        //状态转移
        for(int row = 1; row < m; row++){
            dp[row][0] = obstacleGrid[row][0] == 0 ? dp[row - 1][0] : 0;      
            for(int col = 1; col < n; col++){
                dp[row][col] = obstacleGrid[row][col] == 0 ? 
                                dp[row][col - 1] + dp[row - 1][col]: 0;
            }
        }
        return dp[m - 1][n - 1];
    }
```





由于实际上我们每次只需要用到上一层的结果，所有状态转移方程可以优化成一维数组

```java
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0] == null || obstacleGrid[0].length == 0){
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        //初始化
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for(int i = 1; i < n; i++){    
            dp[i] = obstacleGrid[0][i] == 0 ? dp[i - 1] : 0;
        }
        //状态转移
        for(int row = 1; row < m; row++){
            dp[0] = obstacleGrid[row][0] == 0 ? dp[0] : 0;      
            for(int col = 1; col < n; col++){
                dp[col] = obstacleGrid[row][col] == 0 ? dp[col - 1] + dp[col]: 0;
            }
        }
        return dp[n - 1];
    }
```

