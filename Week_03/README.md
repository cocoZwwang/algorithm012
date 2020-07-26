# 递归的实现、特性以及思维要点

## 递归是什么？
递归就是循坏
通过函数来进行循环


## 递归类比盗梦空间
- 向下进入不同的梦境中，向上又回到原来一层（递归只能一层层向下/向上，不能跳过其中某一层）
- 通过声音同步回到上一层（函数不同层之间传递变量）
- 每一层环境和周围的人都是一份拷贝，主角等几个人穿越不同层的梦境（参数和携带变化）

## 计算n 递归的简单例子
！n = 1\*2\*3...\*n
 ```java
 public int factorial(){
     if(n < 1){
         return 1;
     }
     
     return n * factorial(n - 1);
 }
 ```

 函数运行的示意图

factorial(6)

6 * factorial(5)

6 * (5 * factorial(4))

6 * (5 * (4 * factorial(3)))

6 * (5 * (4 * (3 * factorial(3))))

6 * (5 * (4 * (3 * (2 * factorial(1)))))

6 * (5 * (4 * (3 * (2 * 1))))

6 * (5 * (4 * (3 * 2)))

6 * (5 * (4 * 6))

6 * (5 * 24)

6 * 120

720

## 递归的思维要点
- 不要使用人肉递归
- 找到最近最简的方法，将其拆解成可重复解决的问题
- 数学归纳法

## 递归代码模板

### Java 代码模板

```java
public void recur(int level,int param){
    //终结条件
    if(level > MAX_LEVEL){
        //process result
        // 处理结果
        return;
    }
    
    //process current logic
    //处理当前的逻辑
    process(level,param);
    
    //drill down
    //进入下一层
    recur(level: level + 1,newParam);
    
    //restore current states
    //清理当前层的一些状态，主要是一些全局变量
}
```

### javaScript 代码模板

```javaScript
const recursion = (level, params) =>{
   // recursion terminator
   if(level > MAX_LEVEL){
     process_result
     return 
   }
   // process current level
   process(level, params)
   //drill down
   recursion(level+1, params)
   //clean current level status if needed
   
}
```

### C/C++ 代码模板

```C/C++
void recursion(int level, int param) { 
  // recursion terminator
  if (level > MAX_LEVEL) { 
    // process result 
    return ; 
  }

  // process current logic 
  process(level, param);

  // drill down 
  recursion(level + 1, param);

  // reverse the current level status if needed
}
```

### Python 代码模板

```Python
# Python
def recursion(level, param1, param2, ...): 
    # recursion terminator 
    if level > MAX_LEVEL: 
	   process_result 
	   return 
    # process logic in current level 
    process(level, data...) 
    # drill down 
    self.recursion(level + 1, p1, ...) 
    # reverse the current level status if needed
```





# 分治的实现和特性

## 什么是分治？
分治本质上就是一种递归，简单来说就是每一层递归都可以分成若干个子递归，这些子递归反会结果之后，再对子结果进行组合返回上一层。

## 分治算法的模板

### java

```java
 public int divideConquer(Problem problem,int[] params){
    if(problem == null){
        //process result
        //处理结果
        return result;
    }
    
    // prepare data
    // 准备数据，拆解子问题
    subProblems = spilitProblem(problem,data);
    
    //divide conquer
    //分治，对每个子问题进行递归调用
    subresult1 = divideConquer(subProblems[0],params);
    subresult2 = divideConquer(subProblems[1],params);
    subresult3 = divideConquer(subProblems[2],params);
    ...
    
    //merge result
    //对每个子问题返回的结果进行合并
    result = processResult(subresult1,subresult2,subresult3);
    
    //restore current states
    //清理当前层的状态，一般是针对一些全局变量
    
    return result;
 }
```

### Python
```Python
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 
  if problem is None: 
	print_result 
	return 

  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 

  # conquer subproblems 
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …

  # process and generate the final result 
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # revert the current level states
```

### C/C++

```C/C++
int divide_conquer(Problem *problem, int params) {
  // recursion terminator
  if (problem == nullptr) {
    process_result
    return return_result;
  } 

  // process current problem
  subproblems = split_problem(problem, data)
  subresult1 = divide_conquer(subproblem[0], p1)
  subresult2 = divide_conquer(subproblem[1], p1)
  subresult3 = divide_conquer(subproblem[2], p1)
  ...

  // merge
  result = process_result(subresult1, subresult2, subresult3)
  // revert the current level status
 
  return 0;
}
```

### javaScript

```javaScript
const divide_conquer = (problem, params) => {

  // recursion terminator

  if (problem == null) {

    process_result

    return

  } 

  // process current problem

  subproblems = split_problem(problem, data)

  subresult1 = divide_conquer(subproblem[0], p1)

  subresult2 = divide_conquer(subproblem[1], p1)

  subresult3 = divide_conquer(subproblem[2], p1)

  ...

  // merge

  result = process_result(subresult1, subresult2, subresult3)

  // revert the current level status

}
```



# 回溯的实现和特性

## 什么是回溯
回溯算法实际上一个类似枚举的搜索尝试过程，主要是在搜索尝试过程中寻找问题的解，当发现已不满足求解条件时，就“回溯”返回，尝试别的路径。回溯法是一种选优搜索法，按选优条件向前搜索，以达到目标。但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法，而满足回溯条件的某个状态的点称为“回溯点”。许多复杂的，规模较大的问题都可以使用回溯法，有“通用解题方法”的美称。

回溯法通常使用简单的递归来实现，在反复上述的步骤后可能出现两种情况：
- 找到一个可能存在的正确答案
- 在尝试了所有可能的分步方法后宣布该题没有答案
最坏的情况下，回溯会导致一次时间复杂度为指数时间的运算。

## 例子
### 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
示例：

> 输入：n = 3 
> 输出：[
>     "((()))",
>     "(()())",
>     "(())()",
>     "()(())",
>     "()()()"
>   ]

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(0,0,n,"",res);
        return res;
    }

    private void generate(int left, int right, int n, String s, List<String> res){
        if(left == n && right == n){
            res.add(s);
            return;
        }
        
        if(left < n){
            generate(left + 1,right,n,s+ "(",res);
        }

        if(left > right){
            generate(left,right + 1,n,s + ")",res);
        }
    }
}
```