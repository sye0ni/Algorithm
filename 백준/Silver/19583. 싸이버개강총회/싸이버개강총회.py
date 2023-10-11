import sys 

s,e,q=sys.stdin.readline().rstrip().split()
dict={}

while True:
    try:
        time,name=sys.stdin.readline().rstrip().split()

        if time<=s: # 시작 전에 출석한사람 체크 
            if name not in dict: # 여러번 채팅해도 한 번만 등록 
                dict[name]=1

        if time>=e and time<=q: # 종료 후에 퇴장한사람 체크 
            if name in dict and dict[name]==1: # 시작 전에 출석한사람만 받아줌 
                dict[name]=2

    except:
        break


cnt=0
for key,value in dict.items():
    if value==2:
        cnt+=1
print(cnt)