//让我们一起来玩扫雷游戏！ 
//
// 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）
//地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。 
//
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 
// 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。 
// 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回面板。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 示例 2： 
//
// 输入: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 
//
// 注意： 
//
// 
// 输入矩阵的宽和高的范围为 [1,50]。 
// 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。 
// 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 
// 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 86 👎 0


package com.shuzijun.leetcode.editor.en;

import com.sun.rowset.internal.Row;

import java.util.*;

public class Minesweeper {
    public static void main(String[] args) {
        Solution solution = new Minesweeper().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //dfs
        public char[][] updateBoard(char[][] board, int[] click) {
            dfs(board, click[0], click[1]);
            return board;
        }

        private void dfs(char[][] board, int row, int col) {
            int rowCount = board.length;
            int colCount = board[0].length;
            if (row < 0 || row >= rowCount || col < 0 || col >= colCount) {
                return;
            }

            if (board[row][col] == 'E') {
                board[row][col] = 'B';
                int count = countM(board, row, col);
                if (count == 0) {
                    for (int r = row - 1; r < row + 2; r++) {
                        for (int c = col - 1; c < col + 2; c++) {
                            if (r == row && c == col) {
                                continue;
                            }
                            dfs(board, r, c);
                        }
                    }
                } else {
                    board[row][col] = (char) (count + '0');
                }
            } else if (board[row][col] == 'M') {
                board[row][col] = 'X';
            }
        }

        private int countM(char[][] board, int row, int col) {
            int rowCount = board.length;
            int colCount = board[0].length;
            int count = 0;
            for (int r = row - 1; r < row + 2; r++) {
                for (int c = col - 1; c < col + 2; c++) {
                    if (r == row && c == col) {
                        continue;
                    }
                    if (r < 0 || c < 0 || r >= rowCount || c >= colCount) {
                        continue;
                    }
                    if (board[r][c] == 'M') {
                        count++;
                    }
                }
            }
            return count;
        }

        //bfs
//        public char[][] updateBoard(char[][] board, int[] click) {
//            int rowCount = board.length;
//            int colCount = board[0].length;
//
//            //点击的是雷
//            if (board[click[0]][click[1]] == 'M') {
//                board[click[0]][click[1]] = 'X';
//                return  board;
//            }
//
//            Deque<int[]> deque = new LinkedList<>();
//            deque.offer(click);
//            Set<String> visited = new HashSet<>();
//            visited.add(getKey(click));
//            while (!deque.isEmpty()) {
//                int[] pos = deque.poll();
//                int row = pos[0];
//                int col = pos[1];
//                List<int[]> nextList = new ArrayList<>();
//                int count = 0;
//                //shang
//                count = process(row - 1, col, count, board, nextList,visited);
//                //xia
//                count = process(row + 1, col, count, board, nextList,visited);
//                //zuo
//                count = process(row, col - 1, count, board, nextList,visited);
//                //you
//                count = process(row, col + 1, count, board, nextList,visited);
//                //zuo shang
//                count = process(row - 1, col - 1, count, board, nextList,visited);
//                //zuo xia
//                count = process(row + 1, col - 1, count, board, nextList,visited);
//                //you shang
//                count = process(row - 1, col + 1, count, board, nextList,visited);
//                //you xia
//                count = process(row + 1, col + 1, count, board, nextList,visited);
//                if (count == 0) {
//                    board[row][col] = 'B';
//                    for (int[] p : nextList){
//                        String key = getKey(p);
//                        if(!visited.contains(key)){
//                            deque.offer(p);
//                            visited.add(key);
//                        }
//                    }
//                } else {
//                    board[row][col] = (char) (count + '0');
//                }
//            }
//            return board;
//        }
//
//        private String getKey(int[] pos) {
//            return pos[0] + "," + pos[1];
//        }
//
//        private int process(int row, int col, int count, char[][] board, List<int[]> list,Set<String> visited) {
//            int rowCount = board.length;
//            int colCount = board[0].length;
//            int[] p = new int[]{row, col};
//            String key = getKey(p);
//            if (row < 0 || row >= rowCount || col < 0 || col >= colCount) {
//                return count;
//            }else if(visited.contains(key)){
//                return count;
//            } else if (board[row][col] == 'E') {
//                list.add(p);
//            } else if (board[row][col] == 'M') {
//                count++;
//            }
//            return count;
//        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}