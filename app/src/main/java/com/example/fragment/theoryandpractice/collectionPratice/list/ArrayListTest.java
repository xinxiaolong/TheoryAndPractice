package com.example.fragment.theoryandpractice.collectionPratice.list;


import com.example.fragment.theoryandpractice.collectionPratice.model.Person;
import com.example.fragment.theoryandpractice.collectionPratice.model.RequestCallBack;
import com.example.fragment.theoryandpractice.collectionPratice.model.User;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by xiaolong on 2017/9/26.
 * email：xinxiaolong123@foxmail.com
 */

public class ArrayListTest {

    public static void main(String[] args){

        toArray();
        retainAll();
        for (int i=0;i<10;i++){
            String uuid= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB").toString();
            String uuidRandom=UUID.randomUUID().toString();
            System.out.println("uuid="+uuid+"\nuuidRandom="+uuidRandom);
        }

        String value="00036021C +00036021C ";

        Person person1=new Person("1");
        Person person2=new Person("1");
        ArrayList<Person> personArrayList=new ArrayList<>();
        personArrayList.add(person1);
        personArrayList.add(person1);
        personArrayList.add(person2);

        ArrayList<Person> personArrayList2=new ArrayList<>();
        personArrayList2.addAll(personArrayList);

        person1.name="xiaolong";
        System.out.println(personArrayList.size()+"   "+personArrayList.get(0).name);
        System.out.println(personArrayList2.size()+"   "+personArrayList2.get(0).name);


        RequestCallBack<List<User>> callBack=new RequestCallBack<List<User>>(){

            @Override
            public void onSuccess(List<User> userList) {

            }

            @Override
            public void onFail(int code, String msg) {

            }
        };

        Type t = callBack.getClass().getGenericSuperclass();

//        //得到泛型类
//        Class entityClass = null;
//        Type t = callBack.getClass().getGenericSuperclass();
//        if (t instanceof ParameterizedType) {
//            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
//
//            if (p[0] instanceof Type) {
//                Type t2 = p[0];
//                if (t2 instanceof ParameterizedType) {
//                    Type[] p2 = ((ParameterizedType) t2).getActualTypeArguments();
//                    entityClass = (Class) p2[0];
//                } else {
//                    entityClass = (Class) p[0];
//                }
//            }
//        }
//
//
//        if(entityClass==List.class){
//            Type chlidType = entityClass.getGenericSuperclass();
//            if (t instanceof ParameterizedType) {
//                Type[] p = ((ParameterizedType) t).getActualTypeArguments();
//                entityClass = (Class) p[0];
//            }
//        }

        System.out.println(getClass(t).toString());


        List<String> list=new ArrayList<>();
        list.iterator();
    }

    private static Class getClass(Type t) {
        Class entityClass = null;
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            Type temp=p[0];
            if (temp instanceof ParameterizedType) {
                entityClass=getClass(temp);
            }else {
                entityClass = (Class) p[0];
            }
        }
        return entityClass;
    }



    public static void toArray(){
        ArrayList<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        String[] strings=new String[8];

        System.arraycopy(list.toArray(), 0, strings, 0, list.size()-1);
        //CollectionUtil.print(strings);

        strings=list.toArray(strings);
        //CollectionUtil.print(strings);
    }


    public static void retainAll(){
        ArrayList<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");


        ArrayList<String> list2=new ArrayList<>();
        list2.add("6");
        list2.add("7");
        list2.add("8");
        list2.add("9");
        list2.add("10");

        list.retainAll(list2);
        //CollectionUtil.print(list);
    }
}


