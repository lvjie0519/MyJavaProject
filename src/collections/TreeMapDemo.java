package collections;

import java.util.*;

/**
 * TreeMap 整体架构
 * TreeMap 底层的数据结构就是红黑树，和 HashMap 的红黑树结构一样。
 * 因为底层使用的是平衡红黑树的结构，所以 containsKey、get、put、remove
 * 等方法的时间复杂度都是 log(n)。
 * put的数据会根据key做一个排序；
 */
public class TreeMapDemo {

    public static void main(String[] arggs){
        List<DTO> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 5; i > 0; i--) {
            list.add(new DTO(random.nextInt(100)));
        }
        System.out.println("before sort "+list);
        Collections.sort(list);
        System.out.println("after sort "+list);


        System.out.println("===========================");
        TreeMap treeMap = new TreeMap();
        for (int i = 5; i > 0; i--) {
            treeMap.put(new DTO(random.nextInt(100)), "i="+i);
        }
        System.out.println(treeMap);
    }

    static class DTO implements Comparable<DTO> {

        private Integer id;

        public DTO(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        @Override
        public int compareTo(DTO o) {
            //默认从小到大排序
            System.out.println("current id is "+id+",  o.id is"+o.getId());
            return id - o.getId();
        }

        @Override
        public String toString() {
            return "id="+id;
        }
    }

}
