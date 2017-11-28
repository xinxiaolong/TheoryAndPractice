package com.example.search;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Arrays;

/**
 * Created by xiaolong on 2017/8/4.
 * email：xinxiaolong123@foxmail.com
 * 二分法查找
 */

public class HalfSearch {

   public static void main(String[] agr){

       int[] list=new int[100000*1000];
       for (int i=0;i<list.length;i++){
           list[i]= (int)(System.currentTimeMillis()*Math.random()*Math.random()*Math.random()*Math.random()*Math.random()*Math.random());
       }

       Arrays.sort(list);


       int tag=(int)(System.currentTimeMillis()*Math.random()*Math.random()*Math.random()*Math.random()*Math.random());

       long time=System.currentTimeMillis();
       System.out.println(rank(list,tag)+"");
       System.out.println("用时"+(System.currentTimeMillis()-time));

       time=System.currentTimeMillis();
       System.out.println(rank1(list,tag));
       System.out.println("用时"+(System.currentTimeMillis()-time));


       time=System.currentTimeMillis();
       System.out.println(rank2(list,tag));
       System.out.println("用时"+(System.currentTimeMillis()-time));


   }

   public static boolean rank(int[]  list,int tag){
       int lowIndex=0;
       int highIndex=list.length-1;


       int num=0;
       while (lowIndex<=highIndex){

           int middleIndex=(lowIndex+highIndex)/2;

           int low=list[lowIndex];
           int high=list[highIndex];
           int middle=list[middleIndex];

           if(tag==low||tag==high||tag==middle){
               System.out.println("rank()遍历了"+num+"次");
               return true;
           }

           if(tag>middle){
               lowIndex=middleIndex+1;
           }else {
               highIndex=middleIndex-1;
           }

           num++;
       }

       System.out.println("rank()遍历了"+num+"次");
       return false;
   }



   public static boolean rank1(int[]  list,int tag){
       int lo=0;
       int hi=list.length-1;

       int num=0;
       while (lo<=hi){
           int mid=lo+(hi-lo)/2;
           if (tag < list[mid]) {
               hi=mid-1;
           }else if(tag > list[mid]){
               lo=mid+1;
           }else {
               System.out.println("rank1():遍历了"+num+"次");
               return true;
           }
           num++;
       }
       System.out.println("rank1():遍历了"+num+"次");
       return false;
   }


   public static boolean rank2(int[] list,int tag){

       int num=0;

       for (int i=0;i<list.length;i++){
           if(list[i]==tag){
               System.out.println("rank2():遍历了"+num+"次");
               return true;
           }
           num++;
       }
       System.out.println("rank2():遍历了"+num+"次");
       return false;
   }

}
