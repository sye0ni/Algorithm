import sys

# https://www.acmicpc.net/problem/12865 
# 골드5 평범한 배낭 

n,k=map(int,sys.stdin.readline().rstrip().split())
dp=[[0]*(k+1) for _ in range(n+1)]
goods=[]


def knapsack(goods):

    for i in range(1,n+1):
        w,v=goods[i-1][0],goods[i-1][1]
        for j in range(1,k+1):
            if w>j:
                dp[i][j]=dp[i-1][j]
            else:
                dp[i][j]=max(dp[i-1][j],dp[i-1][j-w]+v)
    print(dp[n][k])

for i in range(n): # 배열 초기화 
    t=list(map(int,sys.stdin.readline().rstrip().split()))
    goods.append(t) # (무게, 가치) 쌍 저장 

knapsack(goods)