package com.example.fragment.theoryandpractice.collectionPratice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by xiaolong on 16/6/15.
 * 关于Map的排序 主要使用Collections.sort方法，如果要按自己的一套规则进行排序的话，需要自己实现Comparator
 * 重写compare方法进行排序
 */
public class SortMapTest {

    public static void main(String[] args){

        HashMap<String,List> map=new HashMap<>();

        List<String> colors=new ArrayList<>();
        colors.add("红色");
        colors.add("蓝色");
        colors.add("黑色");
        colors.add("白色");
        map.put("颜色",colors);

        List<String> sizes=new ArrayList<>();
        sizes.add("XL");
        sizes.add("M");
        sizes.add("XXL");
        sizes.add("S");
        sizes.add("ML");
        sizes.add("XXXL");
        sizes.add("X");
        map.put("尺寸",sizes);


        List<String> materials=new ArrayList<>();
        materials.add("真皮");
        materials.add("纯棉");
        materials.add("纤维");
        map.put("材料",materials);


        Iterator<Map.Entry<String,List>> iterator=map.entrySet().iterator();

        System.out.println("--------------排序前-------------------");

        while (iterator.hasNext()){
            Map.Entry<String,List> entry=iterator.next();
            List list=entry.getValue();
            for (int i=0;i<list.size();i++){
                System.out.println(entry.getKey()+":"+list.get(i));
            }
        }


        List<Map.Entry<String, List>> sortList=new ArrayList<>(map.entrySet());
        Collections.sort(sortList, new Comparator<Map.Entry<String, List>>() {
            @Override
            public int compare(Map.Entry<String, List> lhs, Map.Entry<String, List> rhs) {
               return lhs.getKey().compareTo(rhs.getKey());
            }
        });


        List colorSortList=sortList.get(0).getValue();
        Collections.sort(colorSortList,new SizeComparator());


        System.out.println("--------------排序后-------------------");

        iterator=sortList.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,List> entry=iterator.next();
            List list=entry.getValue();
            for (int i=0;i<list.size();i++){
                System.out.println(entry.getKey()+":"+list.get(i));
            }
        }

    }


    static class  SizeComparator implements Comparator<String>{

        String[] colors={"S","M","ML","X","XL","XXL","XXXL"};
        HashMap<String,Integer> sortDicMap=new HashMap<>();

        public SizeComparator(){
            fillSortDicMap();
        }

        private void fillSortDicMap(){
            for (int i=0;i<colors.length;i++){
                sortDicMap.put(colors[i],i);
            }
        }

        @Override
        public int compare(String lhs, String rhs) {
            //0为中介值 大于0则lhs排在后，
            return sortDicMap.get(lhs)-sortDicMap.get(rhs);
        }
    }
}
