cost=[[0]*3 for _ in range(1001)] # i번째 집을 r,g,b 로 칠했을때 각각 비용 
save=[0]*1001 # 입력값 저장 

n=int(input())
for i in range(1,n+1): # 입력 받아 save 리스트에 저장 
    save[i]=list(map(int,input().split()))

for i in range(1,n+1):
    cost[i][0]=min(cost[i-1][1],cost[i-1][2])+save[i][0]
    cost[i][1]=min(cost[i-1][0],cost[i-1][2])+save[i][1]
    cost[i][2]=min(cost[i-1][0],cost[i-1][1])+save[i][2]

print(min(cost[n][0],cost[n][1],cost[n][2]))