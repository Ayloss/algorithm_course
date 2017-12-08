/*
 * 回溯法求罗密欧与朱丽叶的迷宫问题。
 * 剪枝策略为删去大于当前最小转弯次数的结果。
 */
#include <iostream>
#define MAX_SOLUTION 100
using namespace std;

int** map;// 存储遍历过程中的地图
int** solutionMap[MAX_SOLUTION];// 存储所有解决方案的地图
int m;// 迷宫列数
int n;// 迷宫行数
int solutions = 0;//解决方案数
int minTimes = 10000000;//最小的转弯次数
int beginX;//开始位置X
int beginY;//开始位置Y
int closedRoom;//封闭的房间数
int targetX;//目标位置X
int targetY;//目标位置Y

// 方向矩阵
int directionX[8] = { -1,1,0,0,-1,-1,1,1 };
int directionY[8] = { 0,0,-1,1,-1,1,-1,1 };

// 初始化地图
void initMap();


/**
 * 解决方案
 * \param step 当前移动的步数
 * \param oldDirection 前一次的方向
 * \param newDirection 后一次的方向
 * \param x 移动的x坐标
 * \param y 移动的y坐标
 * \param turnTimes 转弯的次数
 */
void move(int step, int oldDirection, int newDirection, int x, int y, int turnTimes);

// 判断是否越界
bool boundary(int x, int y);
// 判断是否可以移动
bool moveAble(int x, int y);
// 判断是否达到最终位置
bool foundTarget(int x, int y);
// 判断方向是否改变
bool directionChange(int oldD, int newD);
// 复制当前的结果
void copyMap(int solutions);
void printResult();

int main(int argc, char* argv[])
{
	cin >> n >> m;

	initMap();
	cin >> closedRoom;

	for(int i = 0;i<closedRoom;i++)
	{
		int x, y;
		cin >> x >> y;
		map[x][y] = -1;
	}

	cin >> beginX >> beginY >> targetX >> targetY;
	
	// 填入初始位置
	map[beginX][beginY] = 1;
	for(int i=0;i<8;i++)
	{
		move(2, i, i, beginX + directionX[i], beginY + directionY[i], 0);
	}

	printResult();

	system("pause");
}

void initMap()
{
	map = new int*[n+1];
	for(int i =1;i<=n;i++)
	{
		map[i] = new int[m+1];
		for(int j =1;j<=m;j++)
		{
			map[i][j] = 0;
		}
	}
}
bool boundary(int x,int y)
{
	return x > n || y > m || x < 1 || y < 1;
}

bool moveAble(int x,int y)
{
	return map[x][y] == 0;
}
bool foundTarget(int x, int y)
{
	return x == targetX && y == targetY;
}

bool directionChange(int oldD,int newD )
{
	return oldD != newD;
}

void copyMap(int solutions)
{
	solutionMap[solutions] = new int*[n+1];
	for(int i =1;i<=n;i++)
	{
		solutionMap[solutions][i] = new int[m+1];
	}

	for(int i=1;i<=n;i++)
	{
		for(int j=1;j<=m;j++)
		{
			solutionMap[solutions][i][j] = map[i][j];
		}
	}
}

void printMap()
{
	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= m; j++)
		{
			cout << map[i][j];
			if (j != m)
			{
				cout << " ";
			}
		}
		cout << endl;
	}
}
void printResult()
{
	if (solutions <= 0) {
		cout << "No Solution!";
		return;
	}

	cout << minTimes << endl << solutions << endl;

	for (int i = 1; i <= n; i++)
	{
		for (int j = 1; j <= m; j++)
		{
			cout << solutionMap[0][i][j];
			if (j != m)
			{
				cout << " ";
			}
		}
		cout << endl;
	}

}

void move(int step,int oldDirection,int newDirection,int x,int y,int turnTimes)
{

	// 判断越界或不可移动
	if(boundary(x,y) || !moveAble(x,y))
	{
		return;
	}

	map[x][y] = step;

	// 方向发生变更
	if(directionChange(oldDirection,newDirection))
	{
		turnTimes++;
	}

	// 限界,对转弯次数多于当前最小值的结果进行剪枝
	if (turnTimes > minTimes) 
	{
		map[x][y] = 0;
		return;
	}

	// 找到目标
	if(foundTarget(x,y))
	{
		// 必须保证找到目标是在最后一步
		if(step == m*n - closedRoom)
		{
			// 更新最小转弯次数
			if(turnTimes < minTimes)
			{
				solutions = 0;// 重新记录解决方案数
				minTimes = turnTimes;
			}

			copyMap(solutions);// 记录解决方案的地图
			solutions++;
		}

		map[x][y] = 0;
		return;
	}

	// 遍历8个方向
	for(int i=0;i<8;i++)
	{
		move(step + 1, newDirection, i, x + directionX[i], y + directionY[i], turnTimes);
	}
	map[x][y] = 0;
	
}
