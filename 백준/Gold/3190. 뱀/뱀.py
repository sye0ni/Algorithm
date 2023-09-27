import sys 
from collections import deque


size=int(sys.stdin.readline().rstrip())
graph=[[0]*size for _ in range(size)]
now='Right'
time=0
snakes=deque()
snakes.append([0,0])


apples=int(sys.stdin.readline().rstrip())
for i in range(apples): # 사과 위치 지정 
    ga,se=map(int,sys.stdin.readline().rstrip().split())
    graph[ga-1][se-1]=1


counts=int(sys.stdin.readline().rstrip())
dirs=deque()

for i in range(counts):
    temp=list(sys.stdin.readline().rstrip().split())
    dirs.append(temp)



def changeDirection(next): # 방향 change 함수 
    global now
    #print("next:",next)
    if now=='Right':
        if next=='L':
            now='Up'
        else:
            now='Down'
        return

    elif now=='Down':
        if next=='L':
            now='Right'
        else:
            now='Left'
        return

    elif now=='Left':
        if next=='L':
            now='Down'
        else:
            now='Up'
        return

    else: #now=='Up'
        if next=='L':
            now='Left'
        else:
            now='Right'
        return


def collision(head_x,head_y):
    
    if head_x<0 or head_x>=size or head_y<0 or head_y>=size: # 벽을 만나면 out
        #print("벽을 만났어용")
        return True

    if [head_x,head_y] in snakes: # 몸이 겹치면 
        #print("몸이 꼬였어용")
        return True

    return False
        


def game():
    global time
    global now # 현재 방향 
    global dirs
    global snakes

    while True:
        time+=1
        length=len(snakes)
        # 머리 찾기 
        head_x,head_y=snakes[length-1][0],snakes[length-1][1]
 

        # 방향에 따라서 머리 위치부터 옮기기 
        #print("머리 위치: ",head_x,head_y)
        #print("현재 방향: ",now)
        if now=='Right':
            head_y+=1
        elif now=='Left':
            head_y-=1
        elif now=='Up':
            head_x-=1
        else: # now=='Down'
            head_x+=1

        #print("다음 머리 위치: ",head_x,head_y)


        if collision(head_x,head_y): # 충돌나면 종료 ~ 
            print(time)
            break
        
        snakes.append([head_x,head_y]) # 일단 머리 추가 

        if graph[head_x][head_y]==0: # 사과가 없다면 ? 꼬리 이동 
            snakes.popleft()
        else: # 사과 있으면 없음 처리 해주기 
            graph[head_x][head_y]=0
        #print("뱀의 위치:",snakes)


        # 방향 바꿔줘야 하는지 check 
        if len(dirs)!=0:
            a,b=dirs.popleft()
            a=int(a)

            if a==time: # 방향 바꿔주기 
                changeDirection(b)

            else: # 바꿀 타이밍 아니면 다시 넣엉 
                dirs.appendleft([a,b])

        #print("현재 시간: ",time)
        #print('---------------------------------')


game()