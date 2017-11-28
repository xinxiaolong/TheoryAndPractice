package com.example.fragment.theoryandpractice.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaolong on 16/6/15.
 * 有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
 */
public class CountOff {

    public static void main(String args[]) {

        System.out.println("----------------------跳跃策略---------------------------");

        SkipStrategy skipStrategy=new SkipStrategy(7900);
        long startTime=System.currentTimeMillis();
        skipStrategy.startGame();
        long endTime=System.currentTimeMillis();
        System.out.println("总耗时为"+(endTime-startTime));

        System.out.println("----------------------链表策略---------------------------");
        LinkStrategy linkStrategy=new LinkStrategy(7900);
        startTime=System.currentTimeMillis();
        linkStrategy.startGame();
        endTime=System.currentTimeMillis();
        System.out.println("总耗时为"+(endTime-startTime));
    }

    public static class SkipStrategy{
        int joinNum;//参加人数

        int countOff;
        int currentIndex;
        int currentJoinNum;

        List<Person> persons = new ArrayList<>();//参加的人集合
        public class Person {
            public int no;//编号
            public boolean isOut;//是否已经出局
        }

        public SkipStrategy(int joinNum){
            this.joinNum=joinNum;
        }

        public void startGame(){
            prepareTeam();

            while (currentJoinNum>1){
                if(!persons.get(currentIndex).isOut){
                    if(countOff==3){
                        countOff=1;
                        persons.get(currentIndex).isOut=true;
                        //System.out.print(persons.get(currentIndex).no+"出局  ");
                        currentJoinNum--;
                    }else {
                        countOff++;
                    }
                }
                currentIndex=nextIndex(currentIndex,joinNum);
            }
            printWinPersonNo();
        }

        private void prepareTeam(){
            countOff=1;
            currentIndex=0;
            currentJoinNum=joinNum;

            persons.clear();
            for (int i = 0; i < joinNum; i++) {
                Person person = new Person();
                person.isOut = false;
                person.no = (i + 1);
                persons.add(person);
            }
        }

        private void printWinPersonNo(){
            for (int i=0;i<persons.size();i++){
                if(!persons.get(i).isOut){
                    System.out.println("最终胜利者："+persons.get(i).no);
                }
            }
        }

        private int nextIndex(int currentIndex,int totalNum){
            if((currentIndex+1)>=totalNum){
                return 0;
            }
            return currentIndex+1;
        }
    }


    public static class LinkStrategy{
        int joinNum;
        List<Person> persons=new ArrayList<>();

        public LinkStrategy(int joinNum){
            this.joinNum=joinNum;
        }

        class Person{
            Person prevPerson;
            Person nextPerson;
            int no;
        }

        private void prepareTeam(){
            persons.clear();
            for (int i=0;i<joinNum;i++){
                Person person=new Person();
                person.no=(i+1);
                persons.add(person);

                if((i-1)>=0){
                    Person prevPerson=persons.get(i-1);
                    person.prevPerson=prevPerson;
                    prevPerson.nextPerson=person;
                }

                if((i+1)==joinNum){
                    person.nextPerson=persons.get(0);
                    persons.get(0).prevPerson=person;
                    person.prevPerson=persons.get(i-1);
                }
            }
        }

        public void startGame(){
            prepareTeam();
            int joinTotalNum=joinNum;
            int countOff=1;
            Person currentPerson=persons.get(0);

            while (joinTotalNum>1){
                if(countOff==3){
                    currentPerson.prevPerson.nextPerson=currentPerson.nextPerson;
                    currentPerson.nextPerson.prevPerson=currentPerson.prevPerson;
                    joinTotalNum--;
                    countOff=1;
                }else{
                    countOff++;
                }
                currentPerson=currentPerson.nextPerson;
            }
            printWinPersonNo(currentPerson);
        }

        private void printWinPersonNo(Person person){
            System.out.println("最终胜利者是："+person.no);
        }
    }
}
