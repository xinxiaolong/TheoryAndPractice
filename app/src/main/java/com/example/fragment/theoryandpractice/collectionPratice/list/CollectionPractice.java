package com.example.fragment.theoryandpractice.collectionPratice.list;

import java.util.Collection;
import java.util.Set;

/**
 * Created by xiaolong on 2017/9/26.
 * email：xinxiaolong123@foxmail.com
 * 集合的最高接口 Collection 高度规定了集合的一些行为:
 *
 *         int size();                                          //集合长度
 *         boolean isEmpty();                                   //集合是否为空
 *         boolean contains(Object o);                          //集合是否包含某元素
 *         Iterator<E> iterator();                              //集合迭代器
 *         Object[] toArray();                                  //集合转为数组
 *         public <T> T[] toArray(T[] a)                        //将集合拷贝到指定的数组里
 *         boolean add(E e);                                    //向集合添加元素
 *         boolean remove(Object o);                            //移除某个元素
 *         boolean containsAll(Collection<?> c);                //集合是否完全包含目标集合
 *         boolean addAll(Collection<? extends E> c);           //将目标集合一次全加入
 *         boolean removeAll(Collection<?> c);                  //将目标集合一次全部移除
 *         default boolean removeIf(Predicate<? super E> filter)  //根据规则移除元素
 *         boolean retainAll(Collection<?> c);                  //求与目标集合的并集
 *         void clear();                                        //将集合元素清空
 *         default Spliterator<E> spliterator()                 //得到分片迭代器（版本8的方法）
 *         stream()
 *         parallelStream()
 */

public class CollectionPractice {


    Collection c;
}
