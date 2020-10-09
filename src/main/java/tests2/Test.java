package tests2;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args){
        String textResumeTwo="Duana. Razmikovna";
        String pattern = "([A-Z][a-z]{1,}[.]?\\s){2,3}";
        Pattern patternFIO = Pattern.compile(pattern);
        Matcher matcher = patternFIO.matcher(textResumeTwo);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }















    public static <E> E getTreeRoot(TreeSet<E> ts) {
        try {
            Field mField = TreeSet.class.getDeclaredField("m");
            ((Field) mField).setAccessible(true);
            return getTreeRoot((TreeMap<E, Object>) mField.get(ts));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Internals of TreeSet has changed", e);
        }
    }
    public static <K,V> K getTreeRoot(TreeMap<K,V> tm) {
        try {
            Field rootField = TreeMap.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Map.Entry<K,V> root = (Map.Entry<K,V>) rootField.get(tm);
            return (root == null ? null : root.getKey());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Internals of TreeMap has changed", e);
        }
    }
}
