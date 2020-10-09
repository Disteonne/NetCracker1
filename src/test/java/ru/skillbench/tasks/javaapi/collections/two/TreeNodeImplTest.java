package ru.skillbench.tasks.javaapi.collections.two;

import junit.framework.TestCase;
import org.junit.Test;

public class TreeNodeImplTest extends TestCase {

    @Test
    public void testGetRoot(){
        TreeNode t1=new TreeNodeImpl();
        t1.setData("Node1");
        TreeNode t2=new TreeNodeImpl();
        t2.setData("Node2");
        TreeNode t3=new TreeNodeImpl();
        t3.setData("Node3");
        TreeNode t4=new TreeNodeImpl();
        t4.setData("Node4");
        t1.addChild(t2);
        t1.addChild(t3);
        t2.addChild(t4);
        assertEquals("Node1",t4.getRoot().getData().toString());
    }
    @Test
    public void testRemove(){
        TreeNode t1=new TreeNodeImpl();
        t1.setData("Node1");
        TreeNode t2=new TreeNodeImpl();
        t2.setData("Node2");
        TreeNode t3=new TreeNodeImpl();
        t3.setData("Node3");
        TreeNode t4=new TreeNodeImpl();
        t4.setData("Node4");
        t1.addChild(t2);
        t1.addChild(t3);
        t2.addChild(t4);
        t1.removeChild(t2);
        assertEquals(1,t1.getChildCount());
    }

    public void testGetTreePath() {
        TreeNode t1=new TreeNodeImpl();
        t1.setData("Node1");
        TreeNode t2=new TreeNodeImpl();
        t2.setData("Node2");
        TreeNode t3=new TreeNodeImpl();
        t3.setData("Node3");
        TreeNode t4=new TreeNodeImpl();
        t4.setData("Node4");
        TreeNode t5=new TreeNodeImpl();
        t5.setData("Node5");
        TreeNode t6=new TreeNodeImpl();
        t6.setData("Node6");
        t1.addChild(t2);
        //t1.addChild(t3);
       // t2.addChild(t4);
       // t4.addChild(t5);
        //t5.addChild(t6);
        String path=t2.getTreePath();
        assertEquals("",t2.getTreePath());
    }
    @Test
    public void testFindParent(){
        TreeNode t1=new TreeNodeImpl();
        t1.setData("Node1");
        TreeNode t2=new TreeNodeImpl();
        t2.setData("Node2");
        TreeNode t3=new TreeNodeImpl();
        t3.setData("Node3");
        TreeNode t4=new TreeNodeImpl();
        t4.setData("Node4");
        t1.addChild(t2);
        t1.addChild(t3);
        t2.addChild(t4);
        //String path=t4.getTreePath();
        //String res=t4.findParent("Node3").getData().toString();
        assertEquals(null,t4.findParent("Node3"));
    }

    @Test
    public void testFindChild(){
        TreeNode t1=new TreeNodeImpl();
        t1.setData("Node1");
        TreeNode t2=new TreeNodeImpl();
        t2.setData("Node2");
        TreeNode t3=new TreeNodeImpl();
        t3.setData("Node3");
        TreeNode t4=new TreeNodeImpl();
        t4.setData("Node4");
        TreeNode t5=new TreeNodeImpl();
        t5.setData("Node5");
        TreeNode t6=new TreeNodeImpl();
        t6.setData("Node6");
        t1.addChild(t2);
        t1.addChild(t3);
        //t2.addChild(t4);
        /*
        t4.addChild(t5);
        t5.addChild(t6);
        TreeNode t7=new TreeNodeImpl();
        t7.setData("Node7");
        t5.addChild(t7);
        TreeNode t8=new TreeNodeImpl();
        t7.setData("Node8");
        TreeNode t9=new TreeNodeImpl();
        t7.setData("Node9");
        t7.addChild(t8);
        t7.addChild(t9);
        String obj=t5.findChild("Node9").getData().toString();

         */
        assertEquals(null,t1.findChild("Node4"));
    }
}