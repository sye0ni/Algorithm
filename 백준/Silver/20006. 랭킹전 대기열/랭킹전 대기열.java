import java.io.*;
import java.util.*;

// 들어갈 방이 없으면 방 생성, 걔 기준 -10 ~ +10
// 들어갈 방이 있으면 들어가고 정원 다 찰때까지 대기
// => 여러개면 맨 앞에 있는 방으로
// 정원이 모두 차면 시작

// 방 : 정원, 입장 가능 레벨, 현재 인원 저장
public class Main {

    static class Player implements Comparable<Player>{
        int level;
        String name;

        Player(int level,String name) {
            this.level=level;
            this.name=name;
        }

        @Override
        public int compareTo (Player p) {
            return this.name.compareTo(p.name);
        }
    }

    static class Room {
        int minLevel;
        int maxLevel;
        PriorityQueue<Player> playerList;

        Room(int minLevel,int maxLevel,PriorityQueue<Player> playerList) {
            this.minLevel=minLevel;
            this.maxLevel=maxLevel;
            this.playerList=playerList;
        }
    }

    static List<Room> rooms=new ArrayList<>();
    static int m; // 방의 정원

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int p=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        int l;
        String n;
        for(int i=0;i<p;i++) {
            st=new StringTokenizer(br.readLine());
            l=Integer.parseInt(st.nextToken());
            n=st.nextToken();

            matching(new Player(l,n));
        }

        StringBuilder sb=new StringBuilder();
        for(Room room:rooms) {
            if(room.playerList.size()==m) {
                sb.append("Started!"+"\n");
                while(!room.playerList.isEmpty()) {
                    Player pop=room.playerList.poll();
                    sb.append(pop.level+" "+pop.name+"\n");
                }
            } else {
                sb.append("Waiting!"+"\n");
                while(!room.playerList.isEmpty()) {
                    Player pop=room.playerList.poll();
                    sb.append(pop.level+" "+pop.name+"\n");
                }
            }
        }
        System.out.println(sb.toString());
    }

    static void matching(Player p) {

        // 들어갈 수 있는 방이 있는지 확인
        // (1) 있으면 바로 넣기
            // 1. 레벨 조건에 맞는지
            // 2. 인원 수가 남았는지
        // (2) 없으면 방 생성

        boolean flag=false; // 들어갈 수 있는 방이 있는지
        for(Room room:rooms) {
            // 인원 수 & 레벨 조건 확인
            if(room.playerList.size()<m && room.minLevel<= p.level && room.maxLevel>=p.level) {
                room.playerList.add(p);
                flag=true;
                break;
            }
        }

        if(!flag) { // 방 생성
            PriorityQueue<Player> pq=new PriorityQueue<>();
            pq.add(p);
            Room newRoom=new Room(p.level-10,p.level+10,pq);
            rooms.add(newRoom);
        }

    }
}