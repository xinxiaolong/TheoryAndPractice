package com.example.fragment.theoryandpractice.collectionPratice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaolong on 16/6/15.
 */
public class ListTest {

    public static void main(String[] agrs) {

        ArrayList<Person> list1 = new ArrayList<>();
        Person person1 = new Person("xiao", 1);
        Person person2 = new Person("xin", 1);
        Person person3 = new Person("long", 1);

        list1.add(person1);
        list1.add(person2);
        list1.add(person3);

        System.out.println(list1.toString());

        Person person4 = new Person("xin22", 1);

        System.out.println(list1.toString());

        System.out.println(list1.remove(person4));

        System.out.println(list1.toString());

        List<Person> list2 = list1.subList(0, 2);
        list2.get(0).name = "llll";
        list2.get(0).age = 100;

        System.out.print(list1.get(0).age);
    }

    public static class Person {
        public String name;
        public int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Person other = (Person) obj;
            if (name.equals(other.name)) {
                return true;
            }
            return false;
        }

        public String toString() {
            return name + "   " + age;
        }
    }
}
