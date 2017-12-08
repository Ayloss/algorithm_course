/*
 * ���ݷ�������ŷ������Ҷ���Թ����⡣
 * ��֦����Ϊɾȥ���ڵ�ǰ��Сת������Ľ����
 */
#include <iostream>
#define MAX_SOLUTION 100
using namespace std;

int** map;// �洢���������еĵ�ͼ
int** solutionMap[MAX_SOLUTION];// �洢���н�������ĵ�ͼ
int m;// �Թ�����
int n;// �Թ�����
int solutions = 0;//���������
int minTimes = 10000000;//��С��ת�����
int beginX;//��ʼλ��X
int beginY;//��ʼλ��Y
int closedRoom;//��յķ�����
int targetX;//Ŀ��λ��X
int targetY;//Ŀ��λ��Y

// �������
int directionX[8] = { -1,1,0,0,-1,-1,1,1 };
int directionY[8] = { 0,0,-1,1,-1,1,-1,1 };

// ��ʼ����ͼ
void initMap();


/**
 * �������
 * \param step ��ǰ�ƶ��Ĳ���
 * \param oldDirection ǰһ�εķ���
 * \param newDirection ��һ�εķ���
 * \param x �ƶ���x����
 * \param y �ƶ���y����
 * \param turnTimes ת��Ĵ���
 */
void move(int step, int oldDirection, int newDirection, int x, int y, int turnTimes);

// �ж��Ƿ�Խ��
bool boundary(int x, int y);
// �ж��Ƿ�����ƶ�
bool moveAble(int x, int y);
// �ж��Ƿ�ﵽ����λ��
bool foundTarget(int x, int y);
// �жϷ����Ƿ�ı�
bool directionChange(int oldD, int newD);
// ���Ƶ�ǰ�Ľ��
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
	
	// �����ʼλ��
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

	// �ж�Խ��򲻿��ƶ�
	if(boundary(x,y) || !moveAble(x,y))
	{
		return;
	}

	map[x][y] = step;

	// ���������
	if(directionChange(oldDirection,newDirection))
	{
		turnTimes++;
	}

	// �޽�,��ת��������ڵ�ǰ��Сֵ�Ľ�����м�֦
	if (turnTimes > minTimes) 
	{
		map[x][y] = 0;
		return;
	}

	// �ҵ�Ŀ��
	if(foundTarget(x,y))
	{
		// ���뱣֤�ҵ�Ŀ���������һ��
		if(step == m*n - closedRoom)
		{
			// ������Сת�����
			if(turnTimes < minTimes)
			{
				solutions = 0;// ���¼�¼���������
				minTimes = turnTimes;
			}

			copyMap(solutions);// ��¼��������ĵ�ͼ
			solutions++;
		}

		map[x][y] = 0;
		return;
	}

	// ����8������
	for(int i=0;i<8;i++)
	{
		move(step + 1, newDirection, i, x + directionX[i], y + directionY[i], turnTimes);
	}
	map[x][y] = 0;
	
}
