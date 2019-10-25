//
//package com.fpx.pds.domain;
//
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.regex.Pattern;
//
///**
// * @Author: cuiwy
// * @Date: 2019/9/24 09:07
// * @version: v1.0.0
// */
//public class RBMap<K, V> {
//
//    private transient Entry<K, V> root;
//    /**
//     * The number of entries in the tree
//     */
//    private transient int size = 0;
//
//    /**
//     * The number of structural modifications to the tree.
//     */
//    private transient int modCount = 0;
//    /**
//     * The comparator used to maintain order in this tree map, or
//     * null if it uses the natural ordering of its keys.
//     *
//     * @serial
//     */
//    private final Comparator<? super K> comparator;
//
//    public RBMap() {
//        comparator = null;
//    }
//
//    // Red-black mechanics
//
//    private static final boolean RED = false;
//    private static final boolean BLACK = true;
//
//    /**
//     * Node in the Tree.  Doubles as a means to pass key-value pairs back to
//     * user (see Map.Entry).
//     */
//
//    static final class Entry<K, V> implements Map.Entry<K, V> {
//        K key;
//        V value;
//        Entry<K, V> left;
//        Entry<K, V> right;
//        Entry<K, V> parent;
//        boolean color = BLACK;
//
//        /**
//         * Make a new cell with given key, value, and parent, and with
//         * {@code null} child links, and BLACK color.
//         */
//        Entry(K key, V value, Entry<K, V> parent) {
//            this.key = key;
//            this.value = value;
//            this.parent = parent;
//        }
//
//        /**
//         * Returns the key.
//         *
//         * @return the key
//         */
//        public K getKey() {
//            return key;
//        }
//
//        /**
//         * Returns the value associated with the key.
//         *
//         * @return the value associated with the key
//         */
//        public V getValue() {
//            return value;
//        }
//
//        /**
//         * Replaces the value currently associated with the key with the given
//         * value.
//         *
//         * @return the value associated with the key before this method was
//         * called
//         */
//        public V setValue(V value) {
//            V oldValue = this.value;
//            this.value = value;
//            return oldValue;
//        }
//
//        public boolean equals(Object o) {
//            if (!(o instanceof Map.Entry))
//                return false;
//            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
//
//            return valEquals(key, e.getKey()) && valEquals(value, e.getValue());
//        }
//
//        public int hashCode() {
//            int keyHash = (key == null ? 0 : key.hashCode());
//            int valueHash = (value == null ? 0 : value.hashCode());
//            return keyHash ^ valueHash;
//        }
//
//        public String toString() {
//            return key + "=" + value;
//        }
//    }
//
//    /**
//     * Test two values for equality.  Differs from o1.equals(o2) only in
//     * that it copes with {@code null} o1 properly.
//     */
//    static final boolean valEquals(Object o1, Object o2) {
//        return (o1 == null ? o2 == null : o1.equals(o2));
//    }
//
//    /**
//     * Removes the mapping for this key from this TreeMap if present.
//     *
//     * @param key key for which mapping should be removed
//     * @return the previous value associated with {@code key}, or
//     * {@code null} if there was no mapping for {@code key}.
//     * (A {@code null} return can also indicate that the map
//     * previously associated {@code null} with {@code key}.)
//     * @throws ClassCastException   if the specified key cannot be compared
//     *                              with the keys currently in the map
//     * @throws NullPointerException if the specified key is null
//     *                              and this map uses natural ordering, or its comparator
//     *                              does not permit null keys
//     */
//    public V remove(Object key) {
//        Entry<K, V> p = getEntry(key);
//        if (p == null)
//            return null;
//
//        V oldValue = p.value;
//        deleteEntry(p);
//        return oldValue;
//    }
//
//    /**
//     * Delete node p, and then rebalance the tree.
//     */
//    private void deleteEntry(Entry<K, V> p) {
//        modCount++;
//        size--;
//
//        // If strictly internal, copy successor's element to p and then make p
//        // point to successor.
//        if (p.left != null && p.right != null) {
//            Entry<K, V> s = successor(p);
//            p.key = s.key;
//            p.value = s.value;
//            p = s;
//        } // p has 2 children
//
//        // Start fixup at replacement node, if it exists.
//        Entry<K, V> replacement = (p.left != null ? p.left : p.right);
//
//        if (replacement != null) {
//            // Link replacement to parent
//            replacement.parent = p.parent;
//            if (p.parent == null)
//                root = replacement;
//            else if (p == p.parent.left)
//                p.parent.left = replacement;
//            else
//                p.parent.right = replacement;
//
//            // Null out links so they are OK to use by fixAfterDeletion.
//            p.left = p.right = p.parent = null;
//
//            // Fix replacement
//            if (p.color == BLACK)
//                fixAfterDeletion(replacement);
//        } else if (p.parent == null) { // return if we are the only node.
//            root = null;
//        } else { //  No children. Use self as phantom replacement and unlink.
//            if (p.color == BLACK)
//                fixAfterDeletion(p);
//
//            if (p.parent != null) {
//                if (p == p.parent.left)
//                    p.parent.left = null;
//                else if (p == p.parent.right)
//                    p.parent.right = null;
//                p.parent = null;
//            }
//        }
//    }
//
//    /**
//     * Returns the successor of the specified Entry, or null if no such.
//     */
//    static <K, V> Entry<K, V> successor(Entry<K, V> t) {
//        if (t == null)
//            return null;
//        else if (t.right != null) {
//            Entry<K, V> p = t.right;
//            while (p.left != null)
//                p = p.left;
//            return p;
//        } else {
//            Entry<K, V> p = t.parent;
//            Entry<K, V> ch = t;
//            while (p != null && ch == p.right) {
//                ch = p;
//                p = p.parent;
//            }
//            return p;
//        }
//    }
//
//    /**
//     * Returns this map's entry for the given key, or {@code null} if the map
//     * does not contain an entry for the key.
//     *
//     * @return this map's entry for the given key, or {@code null} if the map
//     * does not contain an entry for the key
//     * @throws ClassCastException   if the specified key cannot be compared
//     *                              with the keys currently in the map
//     * @throws NullPointerException if the specified key is null
//     *                              and this map uses natural ordering, or its comparator
//     *                              does not permit null keys
//     */
//    final Entry<K, V> getEntry(Object key) {
//        // Offload comparator-based version for sake of performance
//        if (comparator != null)
//            return getEntryUsingComparator(key);
//        if (key == null)
//            throw new NullPointerException();
//        @SuppressWarnings("unchecked")
//        Comparable<? super K> k = (Comparable<? super K>) key;
//        Entry<K, V> p = root;
//        while (p != null) {
//            int cmp = k.compareTo(p.key);
//            if (cmp < 0)
//                p = p.left;
//            else if (cmp > 0)
//                p = p.right;
//            else
//                return p;
//        }
//        return null;
//    }
//
//    /**
//     * Version of getEntry using comparator. Split off from getEntry
//     * for performance. (This is not worth doing for most methods,
//     * that are less dependent on comparator performance, but is
//     * worthwhile here.)
//     */
//    final Entry<K, V> getEntryUsingComparator(Object key) {
//        @SuppressWarnings("unchecked")
//        K k = (K) key;
//        Comparator<? super K> cpr = comparator;
//        if (cpr != null) {
//            Entry<K, V> p = root;
//            while (p != null) {
//                int cmp = cpr.compare(k, p.key);
//                if (cmp < 0)
//                    p = p.left;
//                else if (cmp > 0)
//                    p = p.right;
//                else
//                    return p;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * From CLR
//     */
//    private void fixAfterDeletion(Entry<K, V> x) {
//        while (x != root && colorOf(x) == BLACK) {
//            if (x == leftOf(parentOf(x))) {
//                Entry<K, V> sib = rightOf(parentOf(x));
//
//                if (colorOf(sib) == RED) {
//                    setColor(sib, BLACK);
//                    setColor(parentOf(x), RED);
//                    rotateLeft(parentOf(x));
//                    sib = rightOf(parentOf(x));
//                }
//
//                if (colorOf(leftOf(sib)) == BLACK &&
//                        colorOf(rightOf(sib)) == BLACK) {
//                    setColor(sib, RED);
//                    x = parentOf(x);
//                } else {
//                    if (colorOf(rightOf(sib)) == BLACK) {
//                        setColor(leftOf(sib), BLACK);
//                        setColor(sib, RED);
//                        rotateRight(sib);
//                        sib = rightOf(parentOf(x));
//                    }
//                    setColor(sib, colorOf(parentOf(x)));
//                    setColor(parentOf(x), BLACK);
//                    setColor(rightOf(sib), BLACK);
//                    rotateLeft(parentOf(x));
//                    x = root;
//                }
//            } else { // symmetric
//                Entry<K, V> sib = leftOf(parentOf(x));
//
//                if (colorOf(sib) == RED) {
//                    setColor(sib, BLACK);
//                    setColor(parentOf(x), RED);
//                    rotateRight(parentOf(x));
//                    sib = leftOf(parentOf(x));
//                }
//
//                if (colorOf(rightOf(sib)) == BLACK &&
//                        colorOf(leftOf(sib)) == BLACK) {
//                    setColor(sib, RED);
//                    x = parentOf(x);
//                } else {
//                    if (colorOf(leftOf(sib)) == BLACK) {
//                        setColor(rightOf(sib), BLACK);
//                        setColor(sib, RED);
//                        rotateLeft(sib);
//                        sib = leftOf(parentOf(x));
//                    }
//                    setColor(sib, colorOf(parentOf(x)));
//                    setColor(parentOf(x), BLACK);
//                    setColor(leftOf(sib), BLACK);
//                    rotateRight(parentOf(x));
//                    x = root;
//                }
//            }
//        }
//
//        setColor(x, BLACK);
//    }
//
//    /**
//     * Balancing operations.
//     * <p>
//     * Implementations of rebalancings during insertion and deletion are
//     * slightly different than the CLR version.  Rather than using dummy
//     * nilnodes, we use a set of accessors that deal properly with null.  They
//     * are used to avoid messiness surrounding nullness checks in the main
//     * algorithms.
//     */
//
//    private static <K, V> boolean colorOf(Entry<K, V> p) {
//        return (p == null ? BLACK : p.color);
//    }
//
//    private static <K, V> Entry<K, V> parentOf(Entry<K, V> p) {
//        return (p == null ? null : p.parent);
//    }
//
//    private static <K, V> void setColor(Entry<K, V> p, boolean c) {
//        if (p != null)
//            p.color = c;
//    }
//
//    private static <K, V> Entry<K, V> leftOf(Entry<K, V> p) {
//        return (p == null) ? null : p.left;
//    }
//
//    private static <K, V> Entry<K, V> rightOf(Entry<K, V> p) {
//        return (p == null) ? null : p.right;
//    }
//
//    /**
//     * From CLR
//     */
//    private void rotateLeft(Entry<K, V> p) {
//        if (p != null) {
//            Entry<K, V> r = p.right;
//            p.right = r.left;
//            if (r.left != null)
//                r.left.parent = p;
//            r.parent = p.parent;
//            if (p.parent == null)
//                root = r;
//            else if (p.parent.left == p)
//                p.parent.left = r;
//            else
//                p.parent.right = r;
//            r.left = p;
//            p.parent = r;
//        }
//    }
//
//    /**
//     * From CLR
//     */
//    private void rotateRight(Entry<K, V> p) {
//        if (p != null) {
//            Entry<K, V> l = p.left;
//            p.left = l.right;
//            if (l.right != null) l.right.parent = p;
//            l.parent = p.parent;
//            if (p.parent == null)
//                root = l;
//            else if (p.parent.right == p)
//                p.parent.right = l;
//            else p.parent.left = l;
//            l.right = p;
//            p.parent = l;
//        }
//    }
//
//
//    ////////////////////////////////以下是插入代码//////////////////////////////////////////
//
//    /**
//     * Associates the specified value with the specified key in this map.
//     * If the map previously contained a mapping for the key, the old
//     * value is replaced.
//     *
//     * @param key   key with which the specified value is to be associated
//     * @param value value to be associated with the specified key
//     * @return the previous value associated with {@code key}, or
//     * {@code null} if there was no mapping for {@code key}.
//     * (A {@code null} return can also indicate that the map
//     * previously associated {@code null} with {@code key}.)
//     * @throws ClassCastException   if the specified key cannot be compared
//     *                              with the keys currently in the map
//     * @throws NullPointerException if the specified key is null
//     *                              and this map uses natural ordering, or its comparator
//     *                              does not permit null keys
//     */
//    public V put(K key, V value) {
//        Entry<K, V> t = root;
//        if (t == null) {
//            compare(key, key); // type (and possibly null) check
//
//            root = new Entry<>(key, value, null);
//            size = 1;
//            modCount++;
//            return null;
//        }
//        int cmp;
//        Entry<K, V> parent;
//        // split comparator and comparable paths
//        Comparator<? super K> cpr = comparator;
//        if (cpr != null) {
//            do {
//                parent = t;
//                cmp = cpr.compare(key, t.key);
//                if (cmp < 0)
//                    t = t.left;
//                else if (cmp > 0)
//                    t = t.right;
//                else
//                    return t.setValue(value);
//            } while (t != null);
//        } else {
//            if (key == null)
//                throw new NullPointerException();
//            @SuppressWarnings("unchecked")
//            Comparable<? super K> k = (Comparable<? super K>) key;
//            do {
//                parent = t;
//                cmp = k.compareTo(t.key);
//                if (cmp < 0)
//                    t = t.left;
//                else if (cmp > 0)
//                    t = t.right;
//                else
//                    return t.setValue(value);
//            } while (t != null);
//        }
//        Entry<K, V> e = new Entry<>(key, value, parent);
//        if (cmp < 0)
//            parent.left = e;
//        else
//            parent.right = e;
//        fixAfterInsertion(e);
//        size++;
//        modCount++;
//        return null;
//    }
//
//    /**
//     * Compares two keys using the correct comparison method for this TreeMap.
//     */
//    @SuppressWarnings("unchecked")
//    final int compare(Object k1, Object k2) {
//        return comparator == null ? ((Comparable<? super K>) k1).compareTo((K) k2)
//                : comparator.compare((K) k1, (K) k2);
//    }
//
//    /**
//     * From CLR
//     */
//    private void fixAfterInsertion(Entry<K, V> x) {
//        x.color = RED;
//
//        while (x != null && x != root && x.parent.color == RED) {
//            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
//                Entry<K, V> y = rightOf(parentOf(parentOf(x)));
//                if (colorOf(y) == RED) {
//                    setColor(parentOf(x), BLACK);
//                    setColor(y, BLACK);
//                    setColor(parentOf(parentOf(x)), RED);
//                    x = parentOf(parentOf(x));
//                } else {
//                    if (x == rightOf(parentOf(x))) {
//                        x = parentOf(x);
//                        rotateLeft(x);
//                    }
//                    setColor(parentOf(x), BLACK);
//                    setColor(parentOf(parentOf(x)), RED);
//                    rotateRight(parentOf(parentOf(x)));
//                }
//            } else {
//                Entry<K, V> y = leftOf(parentOf(parentOf(x)));
//                if (colorOf(y) == RED) {
//                    setColor(parentOf(x), BLACK);
//                    setColor(y, BLACK);
//                    setColor(parentOf(parentOf(x)), RED);
//                    x = parentOf(parentOf(x));
//                } else {
//                    if (x == leftOf(parentOf(x))) {
//                        x = parentOf(x);
//                        rotateRight(x);
//                    }
//                    setColor(parentOf(x), BLACK);
//                    setColor(parentOf(parentOf(x)), RED);
//                    rotateLeft(parentOf(parentOf(x)));
//                }
//            }
//        }
//        root.color = BLACK;
//    }
//
//    //////////////////获取/////////////////////////
//    public Entry<K, V> get(Object key) {
//        Entry<K, V> p = getEntry(key);
//        return (p == null ? null : p);
//    }
//
//    /**
//     * 层次遍历
//     *
//     * @param p
//     */
//    public static void iterativeLevelOrder(Entry p) {
//        if (p == null) return;
//        LinkedList<Entry> queue = new LinkedList<Entry>();
//        queue.offer(p);
//        while (!queue.isEmpty()) {
//            int levelNum = queue.size();
//            for (int i = 0; i < levelNum; i++) {
//                p = queue.poll();
//                if (p.left != null) queue.offer(p.left);
//                if (p.right != null) queue.offer(p.right);
//                if (p.color) {
//                    if (p.parent == null) {
//                        System.out.print(p.key + "根黑" + " ");
//                    } else if (p.parent.left == p) {
//                        System.out.print(p.key + "左黑" + " ");
//                    } else {
//                        System.out.print(p.key + "右黑" + " ");
//                    }
//                } else {
//                    if (p.parent.left == p) {
//                        System.out.print(p.key + "左红" + " ");
//                    } else {
//                        System.out.print(p.key + "右红" + " ");
//                    }
//                }
//            }
//            System.out.println();
//        }
//    }
//
//    public static void main(String[] args) {
//        RBMap<Object, Integer> rbMap = new RBMap<>();
//        for (int i = 1; i <= 8; i++) {
//            rbMap.put(i, i);
//        }
//        for (int i = -8; i < 0; i++) {
//            rbMap.put(i, i);
//        }
////        iterativeLevelOrder(rbMap.root);
////        rbMap.remove(4);
////        show(rbMap.root, rbMap);
//        PrintTree.print(rbMap.root);
//    }
//
//
//    ///////////////////树的操作///////////////////////
//    public static int getTreeDepth(Entry root) {
//        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
//    }
//
//    private static void writeArray(Entry currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
//        // 保证输入的树不为空
//        if (currNode == null) return;
//        // 先将当前节点保存到二维数组中
//        res[rowIndex][columnIndex] = String.valueOf(currNode.key);
//
//        // 计算当前位于树的第几层
//        int currLevel = ((rowIndex + 1) / 2);
//        // 若到了最后一层，则返回
//        if (currLevel == treeDepth) return;
//        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
//        int gap = treeDepth - currLevel - 1;
//
//        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
//        if (currNode.left != null) {
//            res[rowIndex + 1][columnIndex - gap] = "/";
//            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
//        }
//
//        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
//        if (currNode.right != null) {
//            res[rowIndex + 1][columnIndex + gap] = "\\";
//            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
//        }
//    }
//
//    public static void show(Entry root, RBMap<Object, Integer> rbMap) {
//        if (root == null) System.out.println("EMPTY");
//        // 得到树的深度
//        int treeDepth = getTreeDepth(root);
//
//        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
//        // 作为整个二维数组的宽度
//        int arrayHeight = treeDepth * 2 - 1;
//        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
//        // 用一个字符串数组来存储每个位置应显示的元素
//        String[][] res = new String[arrayHeight][arrayWidth];
//        // 对数组进行初始化，默认为一个空格
//        for (int i = 0; i < arrayHeight; i++) {
//            for (int j = 0; j < arrayWidth; j++) {
//                res[i][j] = " ";
//            }
//        }
//
//        // 从根节点开始，递归处理整个树
//        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
//        writeArray(root, 0, arrayWidth / 2, res, treeDepth);
//
//        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
//        for (String[] line : res) {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < line.length; i++) {
//                sb.append(line[i]);
//                if (isInteger(line[i])) {
//                    Entry<Object, Integer> entry = rbMap.get(Integer.valueOf(line[i]));
//                    if (entry.color) {
//                        sb.append("黑");
//                    } else {
//                        sb.append("红");
//                    }
//                }
//                if (line[i].length() > 1 && i <= line.length - 1) {
//                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
//                }
//            }
//            System.out.println(sb.toString());
//        }
//    }
//
//    public static boolean isInteger(String str) {
//        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//        return pattern.matcher(str).matches();
//    }
//    /////////////////////////////////////////////
//
//
//    static class PrintTree {
//        private static class Trunk {
//            Trunk prev;
//            String str;
//
//            private Trunk(Trunk prev, String str) {
//                this.prev = prev;
//                this.str = str;
//            }
//        }
//
//        // Helper function to print branches of the binary tree
//        private static void showTrunks(Trunk p) {
//            if (p == null)
//                return;
//            showTrunks(p.prev);
//            System.out.print(p.str);
//        }
//
//        // 使用中序遍历方式打印二叉树
//        private static void traversalPrint(Entry root, Trunk prev, boolean isLeft) {
//            if (root == null)
//                return;
//
//            String ROOT_PREV = "   ";
//            String CHILD_PREV = "       ";
//
//            String LEFT_CHILD_CURVED_EDGE = ".-----左";
//            String LEFT_CHILD_STRAIGHT_EDGE = "      |";
//
//            String RIGHT_CHILD_CURVED_EDGE = "`-----右";
//            String RIGHT_CHILD_STRAIGHT_EDGE = "      |";
//
//
//            String prev_str = CHILD_PREV;
//            Trunk trunk = new Trunk(prev, prev_str);
//
//            // 遍历左子树
//            traversalPrint(root.left, trunk, true);
//
//            if (prev == null)
//                trunk.str = ROOT_PREV;
//            else if (isLeft) {
//                trunk.str = LEFT_CHILD_CURVED_EDGE;
//                prev_str = LEFT_CHILD_STRAIGHT_EDGE;
//            } else {
//                trunk.str = RIGHT_CHILD_CURVED_EDGE;
//                prev.str = prev_str;
//            }
//
//            showTrunks(trunk);
//
//            // 打印当前节点
//            if (root.color) {
//                System.out.println(root.key + "黑");
//            } else {
//                System.out.println(root.key + "红");
//            }
//            if (prev != null)
//                prev.str = prev_str;
//
//            trunk.str = RIGHT_CHILD_STRAIGHT_EDGE;
//
//            // 遍历右子树
//            traversalPrint(root.right, trunk, false);
//        }
//
//        public static void print(Entry root) {
//            traversalPrint(root, null, false);
//        }
//
//    }
//
//
//}
