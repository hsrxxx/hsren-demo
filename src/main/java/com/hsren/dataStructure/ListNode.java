package com.hsren.dataStructure;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    public ListNode next;
    public int val;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public void print(){
        List<Integer> result = new ArrayList<Integer>();
        result.add(val);
        while(next != null){
            result.add(next.val);
            next = next.next;
        }
        System.out.println(result.toString());
    }
}
