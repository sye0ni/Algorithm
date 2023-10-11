import sys 

num,count=list(map(int,sys.stdin.readline().rstrip().split()))
lengths=[]
for i in range(num):
    lengths.append(int(input()))

left=1
right=max(lengths)

while True:
    
    if left>right:#탈출 조건 !! 
        print(right)
        break
 
    mid=(left+right)//2

    cnt=0

    for i in lengths:
        cnt+=i//mid

    if cnt<count: #더 작게 잘라야함 
        right=mid-1
    
    elif cnt>=count: # 더 크게 잘라야할수도 있어  
        left=mid+1

