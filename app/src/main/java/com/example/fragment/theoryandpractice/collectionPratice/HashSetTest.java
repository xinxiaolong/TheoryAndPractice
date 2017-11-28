package com.example.fragment.theoryandpractice.collectionPratice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xiaolong.
 * on 2016-01-28 下午2:49.
 */
public class HashSetTest {

    public static void main(String[] arge){
        HashSet<Person> hashSetA=new HashSet();
        HashSet<Person> hashSetB=new HashSet();

        hashSetA.add(new Person(1,"1"));
        hashSetA.add(new Person(1,"2"));
        hashSetA.add(new Person(1,"3"));
        hashSetA.add(new Person(2,"2"));

        hashSetB.add(null);

        System.out.println("********************************");

        System.out.println(hashSetB.size());


        Iterator<Person> iterator=hashSetA.iterator();
        while (iterator.hasNext()){
            Person person=iterator.next();
            System.out.println(person.name+"  "+person.age);
        }

        System.out.println("********************************");

        //求交集
        List<Person> lsA=new ArrayList<>();
        List<Person> lsB=new ArrayList<>();


        lsA.add(new Person(1,"1"));
        lsA.add(new Person(2,"2"));
        lsA.add(new Person(3, "3"));

        lsB.add(new Person(1,"1"));
        lsB.add(new Person(1, "2"));


        Iterator<Person> iterators=lsB.iterator();
        while (iterators.hasNext()){
            Person person=iterators.next();
            System.out.println(person.age+"  "+person.name);
        }


        System.out.println(hashSetA.contains(new Person(1,"1")));
    }
}


class Person {
    int age;
    String name="";
    Person(int age, String name){
        this.age=age;
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if(o!=null){
            if(o instanceof Person){
                Person p=(Person)o;
                if(p.name.equals(this.name)&&p.age==this.age){
                    return true;
                }
            }
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return (name+age).hashCode();
    }
}
