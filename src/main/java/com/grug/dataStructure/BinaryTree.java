package com.grug.dataStructure;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * Created by feichen on 2018/6/8.
 */
public class BinaryTree {

    static class Node {
        //左结点
        private Node leftNode;
        //右结点
        private Node rightNode;
        //值
        private int value;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static Node init(int value, int level) {
        level++;
        Node node = new Node(value);
        if (level > 6) {
            return node;
        }
        node.setLeftNode(init(new Random().nextInt(10), level));
        node.setRightNode(init(new Random().nextInt(10), level));
        return node;
    }

    private static Node init2() {
        Node node1 = new Node(1);
        Node node0 = new Node(0);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.setLeftNode(node0);
        node1.setRightNode(node8);

        node0.setLeftNode(node2);
        node0.setRightNode(node3);

        node8.setLeftNode(node6);
        node8.setRightNode(node7);

        return node1;
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    private static void preOrder(Node node) {
        System.out.print(node.getValue());
        if (node.getLeftNode() != null) {
            preOrder(node.getLeftNode());
        }
        if (node.getRightNode() != null) {
            preOrder(node.getRightNode());
        }
    }

    /**
     * 前序遍历 非递归
     *
     * @param node
     */
    private static void preOrderWithStack(Node node) {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(node);
        while (!nodeStack.isEmpty()) {
            Node top = nodeStack.pop();
            if (top != null) {
                System.out.print(top.getValue());
                if (top.getRightNode() != null) {
                    nodeStack.push(top.getRightNode());
                }
                if (top.getLeftNode() != null) {
                    nodeStack.push(top.getLeftNode());
                }
            } else {
                break;
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private static void inOrder(Node node) {
        if (node.getLeftNode() != null) {
            inOrder(node.getLeftNode());
        }
        System.out.print(node.getValue());
        if (node.getRightNode() != null) {
            inOrder(node.getRightNode());
        }
    }

    /**
     * 中序遍历 非递归
     *
     * @param node
     */
    private static void inOrderWithStack(Node node) {
        Map<Node, Boolean> nodeExecuteMap = new HashMap<>();
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(node);
        nodeExecuteMap.put(node, false);
        while (!nodeStack.isEmpty()) {
            Node top = nodeStack.pop();
            if (top != null) {
                if (!nodeExecuteMap.get(top)) {
                    nodeExecuteMap.put(top, true);
                    if (top.getRightNode() != null) {
                        nodeStack.push(top.getRightNode());
                        nodeExecuteMap.put(top.getRightNode(), false);
                    }
                    nodeStack.push(top);
                    if (top.getLeftNode() != null) {
                        nodeStack.push(top.getLeftNode());
                        nodeExecuteMap.put(top.getLeftNode(), false);
                    }
                } else {
                    System.out.print(top.getValue());
                }
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    private static void postOrder(Node node) {
        if (node.getLeftNode() != null) {
            postOrder(node.getLeftNode());
        }
        if (node.getRightNode() != null) {
            postOrder(node.getRightNode());
        }
        System.out.print(node.getValue());
    }


    /**
     * 后序遍历 非递归
     *
     * @param node
     */
    private static void postOrderWithStack(Node node) {

        Map<Node, Boolean> nodeExecuteMap = new HashMap<>();
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(node);
        nodeExecuteMap.put(node, false);
        while (!nodeStack.isEmpty()) {
            Node top = nodeStack.pop();
            if (top != null) {
                if (!nodeExecuteMap.get(top)) {
                    nodeExecuteMap.put(top, true);
                    nodeStack.push(top);
                    if (top.getRightNode() != null) {
                        nodeStack.push(top.getRightNode());
                        nodeExecuteMap.put(top.getRightNode(), false);
                    }
                    if (top.getLeftNode() != null) {
                        nodeStack.push(top.getLeftNode());
                        nodeExecuteMap.put(top.getLeftNode(), false);
                    }
                } else {
                    System.out.print(top.getValue());
                }
            }
        }

    }


    /**
     * 层次优先
     *
     * @param node
     */
    private static void levelOrder(Node node) {
        Queue<Node> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(node);
        while (!nodeQueue.isEmpty()) {
            Node root = nodeQueue.poll();
            if (root != null) {
                System.out.print(root.getValue());
                if (root.getLeftNode() != null) {
                    nodeQueue.add(root.getLeftNode());
                }
                if (root.getRightNode() != null) {
                    nodeQueue.add(root.getRightNode());
                }
            } else {
                break;
            }
        }
    }


    public static void main(String[] args) {
        //        Node node = init2();
        Node node = init(5, 1);
        System.out.println(JSON.toJSONString(node));
        System.out.println("前序递归");
        preOrder(node);
        System.out.println();
        System.out.println("前序非递归");
        preOrderWithStack(node);
        System.out.println();
        System.out.println("中序递归");
        inOrder(node);
        System.out.println();
        System.out.println("中序非递归");
        inOrderWithStack(node);
        System.out.println();
        System.out.println("后序递归");
        postOrder(node);
        System.out.println();
        System.out.println("后序非递归");
        postOrderWithStack(node);
        System.out.println();

        System.out.println("层次优化");
        levelOrder(node);
    }
}
