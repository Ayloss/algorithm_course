# 会场安排问题
## 题目描述
问题描述：  
假设要在足够多的会场里安排一批活动，并希望使用尽可能少的会场。设计一个有效的贪心算法进行安排。(这个问题实际上是著名的图着色问题。若将每一个活动作为图的一个顶点，不相容活动间用边相连。使相邻顶点着有不同颜色的最小着色数，相应于要找的最小会场数。)  
编程任务：   
对于给定的k 个待安排的活动，编程计算使用最少会场的时间表。
## 输入
由文件input.txt 给出输入数据。第一行有1 个正整数k，表示有k 个待安排的活动。接下来的k 行中，每行有2 个正整数，分别表示k 个待安排的活动开始时间和结束时间。时间以0 点开始的分钟计。
## 输出
将编程计算出的最少会场数输出到文件output.txt 。
## 样例输入
```text
5 
1 23 
12 28 
25 35 
27 80 
36 50
```
## 样例输出
```text
3
```