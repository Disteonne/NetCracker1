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
        TreeNode obj = this;
        while (obj!=this.getRoot()) {
            if (obj.getData() == null) {
                str += "empty ";
            } else {
                str += obj.getData().toString() + " ";
            }
            obj = obj.getParent();
        }

        String[] path = str.split(" ");
        if(getRoot().getData()==null){
            str="empty";
        }else {
            str = getRoot().getData().toString();
        }
        for (int i = path.length - 1; i >= 0; i--) {
            str += "->" + path[i];
        }
        return str;
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
            if (parent.getData()!=data) {
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
        TreeNode result = null;
        if (data != null) {
            while (obj.getChildrenIterator().hasNext()) {
                TreeNode child = obj.getChildrenIterator().next();
                if (child.getData()!=data) {
                    if (child.getChildCount() == 0) {
                        removeChild(child);
                        return findChild(data);
                    }
                    if (child.getChildCount() != 0) {
                        obj = child;
                        result = obj.findChild(data);
                    }
                } else {
                    result = child;
                    break;
                }
            }
            return result;
        } else {
            while (obj.getChildrenIterator().hasNext()) {
                TreeNode child = obj.getChildrenIterator().next();
                if (child.getData() != data) {
                    if (child.getChildCount() == 0) {
                        removeChild(child);
                        return findChild(data);
                    }
                    if (child.getChildCount() != 0) {
                        obj = child;
                        result = obj.findChild(data);
                    }
                } else {
                    result = child;
                    break;
                }
            }
            return result;
        }
    }
}

