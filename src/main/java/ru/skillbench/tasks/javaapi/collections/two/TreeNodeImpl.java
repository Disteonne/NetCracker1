package ru.skillbench.tasks.javaapi.collections.two;

import java.util.*;


public class TreeNodeImpl implements TreeNode {
    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    //private boolean remove = false;
    private boolean setExpended = false;
    private Object data = null;

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode getRoot() {
        if (this.parent == null) {
            return null;
        } else {
            TreeNode tmpPar = this.parent;
            TreeNode res = this;
            boolean b = true;
            while (b) {
                if (tmpPar.getParent() != null) {
                    tmpPar = tmpPar.getParent();
                } else {
                    b = false;
                }
            }
            return tmpPar;
        }
    }

    @Override
    public boolean isLeaf() {
        return children.size() == 0 ? true : false;
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return children.iterator();
    }

    @Override
    public void addChild(TreeNode child) {
        TreeNode newChild = child;
        newChild.setParent(this);
        children.add(newChild);
    }

    @Override
    public boolean removeChild(TreeNode child) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).equals(child)) {
                children.remove(i);
                child.setParent(null);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExpanded() {
        return setExpended;
    }

    @Override
    public void setExpanded(boolean expanded) {
        setExpended = expanded;
        if (children.size() != 0) {
            for (TreeNode t : children
            ) {
                t.setExpanded(expanded);
            }
        }
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getTreePath() {
        String str = "";
        boolean notIsEnd = true;
        TreeNode obj = this;
        //StringBuffer stringBuffer = new StringBuffer();
        while (notIsEnd) {
            if (obj.getRoot() == null) {
                if (obj.getData() == null) {
                    str = "empty->" + str;
                    notIsEnd = false;
                } else {
                    str = obj.getData().toString() + "->" + str;

                    notIsEnd = false;
                }
            } else {
                if (obj.getData() == null) {
                    str = "empty->" + str;
                    obj = obj.getParent();
                } else {
                    str = obj.getData().toString() + "->" + str;
                    obj = obj.getParent();
                }
            }
        }

        return str.substring(0, str.length() - 2);
    }

    @Override
    public TreeNode findParent(Object data) {
        if (data == null) {
            if (parent.getData() != null) {
                if (parent.getParent() != null) { //есть ли еще родитель?
                    return parent.findParent(data);
                } else //т.е не нашли эл-т
                    return null;
            } else {
                return parent;
            }
        } else {
            if (parent.getData() != data) {
                if (parent.getParent() != null) {
                    return parent.findParent(data);
                } else //т.е не нашли эл-т
                    return null;
            } else {
                return parent;
            }
        }

    }

    TreeNode obj = this;

    @Override
    public TreeNode findChild(Object data) {
        TreeNode tmp=null;
        if (data == null) {
            for (TreeNode s : children
            ) {
                if (s.getData() != null) {
                    if(s.getChildCount()!=0) {
                        return s.findChild(data);
                    }
                    else
                        continue;
                }else {
                    return s;
                }
            }
        }else {
            for (TreeNode s : children
            ) {
                if (s.getData()!=data) {
                    if(s.getChildCount()!=0) {
                        return s.findChild(data);
                    }
                    else
                        continue;
                }else {
                    return s;
                }
            }
        }
        return tmp;
        /*
        while (childIterator.hasNext()){
            TreeNode child=childIterator.next();
            if(data!=null){
                if(child.getData().equals(data)){
                    return child;
                }else {
                    if(child.getChildCount()==0){
                        childIterator.remove();
                    }else {
                        obj=child.findChild(data);
                        return obj;
                    }
                }
            }else {
                if(child.getData()==null){
                    return child;
                }else {
                    if(child.getChildCount()==0){
                        childIterator.remove();
                    }else {
                        obj=child.findChild(data);
                        return obj;
                    }
                }
            }
        }

         */
    }
}

