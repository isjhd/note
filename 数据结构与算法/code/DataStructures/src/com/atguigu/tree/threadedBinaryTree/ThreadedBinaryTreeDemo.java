package com.atguigu.tree.threadedBinaryTree;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试：以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是= " + leftNode); //3
        System.out.println("10号节点的后继节点是= " + rightNode); //1

        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); //8，3，10，1，14，6

    }

}

//先创建 HeroNode 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明
    //1. 如果leftType == 0 表示指向的是左子树，如果 1 则表示指向前驱节点
    //2. 如果rightType == 0 表示指向的是右子树，如果 1 则表示指向后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}


//定义ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载一把threadedNodes方法
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while(node != null) {
            //循环的找到 leftType == 1 的结点，第一个找到就是8结点
            //后面随着遍历而变化，因为当 leftType == 1 时，说明该结点是按照线索化
            //处理后的有效结点
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //打印当前这个节点
            System.out.println(node);

            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();

        }

    }

    //编写对二叉树进行中序线索化的方法
    /**
     *
     * @param node
     */
    public void threadedNodes(HeroNode node) {

        //如果 node == null，不能线索化
        if(node == null) {
            return;
        }

        //(1)先线索化左子树
        threadedNodes(node.getLeft());

        //(2)线索化当前节点[有难度]
        //处理当前节点的前驱节点
        //以8节点来理解
        //8节点的.left = null，8节点的.leftType = 1
        if(node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点
        if(pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //(3)再线索化右子树
        threadedNodes(node.getRight());

    }



}