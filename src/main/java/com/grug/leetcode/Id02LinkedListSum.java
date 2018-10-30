package com.grug.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * Created by feichen on 2018/4/16.
 */
public class Id02LinkedListSum {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node1_2 = new ListNode(5);
        ListNode node1_4 = new ListNode(8);
        ListNode node1_3 = new ListNode(3);

        node1_2.setNext(node1_4);
//        node1_4.setNext(node1_3);


        ListNode node2_5 = new ListNode(5);
        ListNode node2_6 = new ListNode(1);
//        ListNode node2_4 = new ListNode(4);

        node2_5.setNext(node2_6);
//        node2_6.setNext(node2_4);

        Id02LinkedListSum id02LinkedListSum = new Id02LinkedListSum();
        ListNode resultNode = id02LinkedListSum.addTwoNumbersCorrect(node1_2, node2_5);
        System.out.println(resultNode);

    }

    public ListNode addTwoNumbersCorrect(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(-1);
        ListNode result = add(l1, l2, listNode, false);
        return result;
    }

    /**
     * 大数加法
     * @param node1
     * @param node2
     * @param result
     * @param plus
     * @return
     */
    private ListNode add(ListNode node1, ListNode node2, ListNode result, boolean plus) {
        int nodeAddResult = (node1 == null ? 0 : node1.getVal()) + (node2 == null ? 0 : node2.getVal());
        if (plus) {
            nodeAddResult++;
        }

        boolean plusFlag = false;
        int nodeValue;
        if (nodeAddResult >= 10) {
            plusFlag = true;
            char[] charArray = String.valueOf(nodeAddResult).toCharArray();
            nodeValue = charArray[charArray.length - 1] - 48;
        } else {
            nodeValue = nodeAddResult;
        }
        //第一个元素
        if (result.getVal() == -1) {
            result = new ListNode(nodeValue);
        } else {
            result.setNext(new ListNode(nodeValue));
        }
        if ((node1 != null && node1.getNext() != null)
                || (node2 != null && node2.getNext() != null)) {
            add(node1 == null ? null : node1.getNext(),
                    node2 == null ? null : node2.getNext(),
                    result.getNext() == null ? result : result.getNext(),
                    plusFlag);
        } else {
            if (plusFlag) {
                //最高位进1
                if (result.getNext() != null) {
                    result.getNext().setNext(new ListNode(1));
                } else {
                    result.setNext(new ListNode(1));
                }
            }
        }
        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Character> linkedList1 = new LinkedList<>();
        getNodeVal(l1, linkedList1);

        List<Character> linkedList2 = new LinkedList<>();
        getNodeVal(l2, linkedList2);

        long number1 = list2Long(linkedList1);
        long number2 = list2Long(linkedList2);

        long result = number1 + number2;

        char[] resultCharArray = String.valueOf(result).toCharArray();
        ListNode first = new ListNode(resultCharArray[resultCharArray.length - 1] - 48);
        long2List(resultCharArray, first, resultCharArray.length - 2);

        return first;
    }


    /**
     * 递归生成List<Character>
     *
     * @param node
     * @param linkedList
     */
    void getNodeVal(ListNode node, List<Character> linkedList) {
        linkedList.add((char) (node.getVal() + 48));
        if (node.getNext() != null)
            getNodeVal(node.getNext(), linkedList);
    }

    long list2Long(List<Character> linkedList) {
        StringBuilder builder = new StringBuilder();
        for (int i = linkedList.size() - 1; i >= 0; i--) {
            builder.append(linkedList.get(i));
        }
        return Long.valueOf(new String(builder));
    }

    /**
     * 递归生成ListNode
     *
     * @param numChar
     * @param node
     * @param i
     */
    void long2List(final char[] numChar, ListNode node, int i) {
        if (i >= 0 && i < numChar.length) {
            ListNode newNode = new ListNode(((int) numChar[i] - 48));
            node.setNext(newNode);
            long2List(numChar, newNode, i - 1);
        }
    }
}
