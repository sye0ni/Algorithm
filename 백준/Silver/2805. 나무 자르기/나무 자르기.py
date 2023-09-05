import sys 

num,count=list(map(int,sys.stdin.readline().rstrip().split()))
lengths=list(map(int,sys.stdin.readline().rstrip().split()))

left=0
right=max(lengths)

while True:
    
    if left>right:#탈출 조건 !! 
        print(right)
        break
 
    mid=(left+right)//2
    sum=0

    for i in lengths:
        if i>mid:
            sum+=i-mid
    #print("나무 길이: ",mid)
    #print("총 길이: ",sum)

    if sum<count: #더 작게 잘라야함 
        right=mid-1
    
    elif sum>=count: # 더 크게 잘라야할수도 있어  
        left=mid+1

