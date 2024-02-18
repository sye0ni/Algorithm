meetings=[]
n=int(input())

for i in range(n):
    s,e=map(int,input().split())
    meetings.append([s,e])

meetings.sort(key=lambda x:(x[1],x[0]))  # 두번째 원소로 오름차순, 동일 값에 대해서는 첫번째 원소로 오름차순 정렬 

cnt=0
end=0 

for i in range(0,n):
    if meetings[i][0] < end:
        continue
    cnt+=1
    end=meetings[i][1]

print(cnt)