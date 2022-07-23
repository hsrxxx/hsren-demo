package com.hsren.algorithm.sort;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 猴子排序
 *      无限猴子定理最早是由埃米尔·博雷尔在1909年出版的一本谈概率的书籍中提到的，
 *      此书中介绍了“打字的猴子”的概念。无限猴子定理是概率论中的柯尔莫哥洛夫的零一律的其中一个命题的例子。
 *      大概意思是，如果让一只猴子在打字机上随机地进行按键，如果一直不停的这样按下去，只要时间达到无穷时，
 *      这只猴子就几乎必然可以打出任何给定的文字，甚至是莎士比亚的全套著作也可以打出来。
 *
 *      根据猴子定理，如果我们不断用随机的数字组成一个数列，那么在无限长的时间里，这个数列在某次肯定会变成有序。
 */
public class MonkeySort {

    private static int [] array = new int[10];

    private static int [] result = new int[10];

    private static int count = 0;

    private static void generate(){
        array[0]=8;
        array[1]=5;
        array[2]=3;
        array[3]=6;
        array[4]=4;
        array[5]=9;
        array[6]=7;
        array[7]=10;
        array[8]=1;
        array[9]=2;
    }

    static{
        generate();
    }

    public static void main(String[] args){
        Random random = new Random();

        int num = 0;
        long startTime = System.currentTimeMillis();

        do {
            sort(num++, random);
        } while (!checkOrder(result));

        long endTime = System.currentTimeMillis();
        System.out.println("排序完成，耗时：" + count + " " + (float) (endTime - startTime) / 1000.0 + " S");

    }

    /**
     * 检查数组是否有序
     * @return true/false
     */
    private static boolean checkOrder (int[] array){
        for (int i = 1; i < 10; i++){
            if (array[i] <= array[i-1]){
                return false;
            }
        }
        return true;
    }
 
    private static void sort(int num, Random random){
        Set<Integer> numberSet = new HashSet<Integer>();
        count++;
        for (int i = 0; i < 10; i++){
            int index = random.nextInt(10);
            while (numberSet.contains(index)){
                index = random.nextInt(10);
            }
            numberSet.add(index);
            int number = array[index];
            result[i] = number;
        }
    }
 
}