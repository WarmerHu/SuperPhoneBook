package com.superphonebook.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingQueue;

public class BTreeMap<K extends Comparable<K>,V> implements Map<K, V>{
    
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 3482063191385297354L;

    private static int DEFAULT_M = 2;
    
    private int m;
    
    private BTreeNode root;
    
    private int minKeyNum;
    private int maxKeyNum;
    private int size;
    
    private Set<K> keySet;
    
    private Comparator<K> keyComparator;
    
    public BTreeMap() {
	this(DEFAULT_M,null);
    }
    
    public BTreeMap(int m) {
	this(m,null);
    }
    
    public BTreeMap(Comparator<K> keyComparator) {
	
	this(DEFAULT_M, keyComparator);
    }
    
    public BTreeMap(int m, Comparator<K> keyComparator) {
	
	if (m < 2) {
            this.m = DEFAULT_M;
        }
        this.m = m;
        this.minKeyNum = m - 1;
        this.maxKeyNum = 2 * m - 1;
        BTreeNode node = new BTreeNode();
        this.root = node;
        
        if(keyComparator == null){
            keyComparator = new Comparator<K>() {

		public int compare(K arg0, K arg1) {
		    return arg0.compareTo(arg1);
		} 
            };
        }
        this.keyComparator = keyComparator;
        keySet = new TreeSet<K>(keyComparator);
    }
    
    @SuppressWarnings("unchecked")
    public V get(Object k) {
	K key = (K)k;
	int index = 0;
	BTreeNode node = root;
	
	while (node != null) {
	    index = getFromNode(node,key);
	    
	    if(index >= 0 && index < node.pairs.size() && node.pairs.get(index).k.compareTo(key) == 0) {
		return node.pairs.get(index).v;
	    }else {
		node = node.children.get(index);
	    }
	}
	
	return null;
    }
    
    public V put(K key,V value) {
	BTreeNode r = root;
        if (root.keynum == maxKeyNum) {
            BTreeNode newRoot = new BTreeNode();
            root = newRoot;
            newRoot.isLeaf = false;
            newRoot.insertChild(0, r);
            sliptChild(newRoot, 0);
            insertNoFull(newRoot, key, value);
        } else
            insertNoFull(r, key, value);
        ++size;
        keySet.add(key);
        return value;
    }
    
    @SuppressWarnings("unchecked")
    public V remove(Object key) {
	V value = get(key);
	Pair p = new Pair();
	p.k = (K) key;
	p.v = value;
	delete(root, p);
	return value;
    }
    
    public boolean isEmpty() {
	return size == 0 ? true : false;
    }
    
    public int size() {
	return size;
    }

    public boolean containsKey(Object key) {
	return keySet.contains(key);
    }

    public boolean containsValue(Object value) {
	for(K k : keySet) {
	    if(get(k).equals(value))
		return true;
	}
	return false;
    }

    public Set<java.util.Map.Entry<K, V>> entrySet() {
	//TODO 这个不需要用到
	return null;
    }

    public Set<K> keySet() {
	return keySet;
    }

    public void putAll(Map<? extends K, ? extends V> maps) {
	for(K k : maps.keySet() ){
	    put(k,maps.get(k));
	}
    }

    public Collection<V> values() {
	List<V> values = new ArrayList<V>();
	for(K k : keySet()) {
	    values.add(get(k));
	}
	return values;
    }
    
    public void clear() {
	size = 0;
	root = null;
	keySet.clear();
    }
    
    public void print() {
        Queue<BTreeNode> queue = new LinkedBlockingQueue<BTreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BTreeNode node = queue.poll();
            for (int i = 0; i < node.keynum; i ++) {
                System.out.print(node.pairs.get(i).k + " " + get(node.pairs.get(i).k));
            }
            System.out.println();
            if (!node.isLeaf) {
                for (int i = 0; i < node.keynum + 1; i ++) {
                    queue.add(node.children.get(i));
                }
            }
        }
    }
    
    private class BTreeNode {
	public int keynum;
	public List<Pair> pairs;
	public List<BTreeNode> children;
	public boolean isLeaf;
	
	public BTreeNode(){
	    keynum = 0;
	    pairs = new ArrayList<Pair>(maxKeyNum);
	    children = new ArrayList<BTreeNode>(maxKeyNum+1);
	    isLeaf = true;
	}	
	
	public void insert(int index,K k,V v) {
	    Pair p = new Pair();
	    p.k = k;
	    p.v = v;
            insert(index,p);
	}
	
	public void insert(int index, Pair p) {
	    pairs.add(index, p);
	    keynum ++;
            if (pairs.size() > maxKeyNum) {
        	pairs.remove(maxKeyNum);
            }
	}
	
	public Pair remove(int index) {
	    --keynum;
	    Pair p = pairs.remove(index);
	    return p;
	}
	
	public void insertChild(int index, BTreeNode child) {
            children.add(index, child);
            if (children.size() > maxKeyNum + 1) {
                children.remove(maxKeyNum + 1);
            }
        }
        
        
        public BTreeNode removeChild(int index) {
            BTreeNode child = children.remove(index);
            return child;
        }
	
    }
    
    private class Pair implements Entry<K, V>{
	public K k;
	public V v;
	public K getKey() {
	    return k;
	}
	public V getValue() {
	    return v;
	}
	public V setValue(V value) {
	    v = value;
	    return v;
	}
    }
    
    private int getFromNode(BTreeNode node, K key) {
	int j = 0;  
	for(j = 0; j < node.keynum; j++)  
	  if(keyComparator.compare(node.pairs.get(j).k, key) >= 0) {
	    return j;
	  }
	return node.keynum;
    }
    
    private void sliptChild(BTreeNode x, int index) {
        // �������
	BTreeNode z = new BTreeNode();
	BTreeNode y = x.children.get(index);
        z.isLeaf = y.isLeaf;
        for (int j = 0; j < minKeyNum; j++) {
            z.insert(j, y.pairs.get(j + m));
        }
        if (!y.isLeaf) {
            for (int j = 0; j < m; j++) {
                z.insertChild(j, y.children.get(j + m));
            }
        }
        z.keynum = minKeyNum;
        y.keynum = minKeyNum;
        x.insertChild(index + 1, z);
        x.insert(index, y.pairs.get(minKeyNum));
    }
 
    
    private void insertNoFull(BTreeNode x, K key,V value) {
        int i = x.keynum - 1;
        if (x.isLeaf) {
            while (i >= 0 && keyComparator.compare(key, x.pairs.get(i).k) < 0)
                i--;
            x.insert(i + 1, key, value);
        } else {
            while (i >= 0 && keyComparator.compare(key, x.pairs.get(i).k) < 0)
                i--;
            i = i + 1;
            if (x.children.get(i).keynum == maxKeyNum) {
                sliptChild(x, i);
                if (keyComparator.compare(key, x.pairs.get(i).k) > 0)
                    i = i + 1;
            }
            insertNoFull(x.children.get(i), key, value);
        }
    }
    
    private void delete(BTreeNode x, Pair p) {
        int n = x.keynum;
        int i = 0;
        
        while (i < n && keyComparator.compare(p.k, x.pairs.get(i).k) > 0)
            i ++;
        if (i < n && p.k.equals(x.pairs.get(i).k)) {
            if (x.isLeaf) {
                // 1 �����ǰ�����Ҷ�ӽ�㣬ֱ��ɾ���ؼ���
                x.remove(i);
            } else {
                BTreeNode left = x.children.get(i);
                BTreeNode right = x.children.get(i + 1); 
                if (left.keynum >= m) {
                    // 2.a
                    Pair pre = deleteMaxKey(left);
                    x.pairs.set(i, pre);
                } else if (right.keynum >= m) {
                    // 2.b
                    Pair next = deleteMinKey(right);
                    x.pairs.set(i, next);
                } else {
                    // 2.c
                    int lSize = left.keynum;
                    int rSize = right.keynum;
                    left.insert(lSize, p);
                    lSize ++;
                    boolean isChildLeaf = left.isLeaf;
                    for (int j = 0; j < rSize; j++) {
                	left.insert(lSize, right.pairs.get(j));
                        if (! isChildLeaf) {
                            left.insertChild(lSize, right.children.get(j));
                        }
                        lSize ++;
                    }
                    if (! isChildLeaf) {
                	left.insertChild(lSize, right.children.get(rSize));
                    }
                    x.remove(i);
                    x.removeChild(i + 1);
                    if (x.keynum == 0) {
                        root = left;
                    }
                    delete(left, p);
                }
            }
        } else if (x.isLeaf) {
            // û���ҵ��ùؼ��֣�ֱ�ӷ���
            return;
        } else {
            BTreeNode child = x.children.get(i);
            boolean isChildLeaf = child.isLeaf;
            if (child.keynum >= m) {
                delete(child, p);
            } else if (i > 0 && x.children.get(i - 1).keynum >= m){
                // 3.a ���ֵ���������
                BTreeNode leftBrother = x.children.get(i - 1);
                int leftBrotherKeyNum = leftBrother.keynum;
                Pair leftBrotherLastPair = leftBrother.pairs.get(leftBrotherKeyNum - 1);
                child.insert(0, x.pairs.get(i - 1));
                x.pairs.set(i - 1, leftBrotherLastPair);
                if (!isChildLeaf) {
                    BTreeNode leftBrotherLastChild = leftBrother.children.get(leftBrotherKeyNum);
                    child.insertChild(0, leftBrotherLastChild);
                    leftBrother.removeChild(leftBrotherKeyNum);
                }
                leftBrother.remove(leftBrotherKeyNum - 1);
                delete(child, p);
            } else if (i < x.keynum && x.children.get(i + 1).keynum >= m) {
                // 3.a ���ֵ���������
                BTreeNode rightBrother = x.children.get(i + 1);
                Pair rightBrotherFirstPair = rightBrother.pairs.get(0);
                int childKeyNum = child.keynum;
                child.insert(childKeyNum, x.pairs.get(i));
                x.pairs.set(i, rightBrotherFirstPair);
                if (!isChildLeaf) {
                    BTreeNode rightBrotherFirstChild = rightBrother.children.get(0);
                    child.insertChild(childKeyNum + 1, rightBrotherFirstChild);
                    rightBrother.removeChild(0);
                }
                rightBrother.remove(0);
                delete(child, p);
            } else if (i > 0){
                // 3.b �������ֵܣ��ϲ�
                BTreeNode leftBrother = x.children.get(i - 1);
                int leftBrotherKeyNum = leftBrother.keynum;
                leftBrother.insert(leftBrotherKeyNum, x.pairs.get(i - 1));
                leftBrotherKeyNum ++;
                for (int j = 0; j < m - 1; j ++) {
                    leftBrother.insert(leftBrotherKeyNum, child.pairs.get(j));
                    if (!isChildLeaf) {
                        leftBrother.insertChild(leftBrotherKeyNum, child.children.get(j));
                    }
                    leftBrotherKeyNum ++;
                }
                if (!isChildLeaf) {
                    leftBrother.insertChild(leftBrotherKeyNum, child.children.get(m - 1));
                }
                x.removeChild(i);
                x.remove(i - 1);
                if (x.keynum == 0) {
                    root = leftBrother;
                }
                delete(leftBrother, p);
            } else {
                // 3.b �������ֵܣ��ϲ�
                BTreeNode rightBrother = x.children.get(i + 1);
                int childKeyNum = child.keynum;
                child.insert(childKeyNum, x.pairs.get(i));
                childKeyNum ++;
                for (int j = 0; j < m - 1; j ++) {
                    child.insert(childKeyNum, rightBrother.pairs.get(j));
                    if (!isChildLeaf) {
                        child.insertChild(childKeyNum, rightBrother.children.get(j));
                    }
                    childKeyNum ++;
                }
                if (! isChildLeaf) {
                    child.insertChild(childKeyNum, rightBrother.children.get(m - 1));
                }
                x.remove(i);
                x.removeChild(i + 1);
                if (x.keynum == 0) {
                    root = child;
                }
                delete(child, p);
            }
        }
    }
    
    
    private Pair deleteMinKey(BTreeNode x) {
        if (x.isLeaf) {
            return x.remove(0);
        } else {
            BTreeNode child = x.children.get(0);
            boolean isChildLeaf = child.isLeaf;
            BTreeNode rightBrother = x.children.get(1);
            if (child.keynum >= m) {
                return deleteMinKey(child);
            } else if (rightBrother.keynum >= m){
                // 3.a
        	Pair rightBrotherFirstPair = rightBrother.pairs.get(0);
                int childKeyNum = child.keynum;
                child.insert(childKeyNum, x.pairs.get(0));
                x.pairs.set(0, rightBrotherFirstPair);
                if (!isChildLeaf) {
                    BTreeNode rightBrotherFirstChild = rightBrother.children.get(0);
                    child.insertChild(childKeyNum + 1, rightBrotherFirstChild);
                    rightBrother.removeChild(0);
                }
                rightBrother.remove(0);
                return deleteMinKey(child);
            } else {
                // 3.b
                int childKeyNum = child.keynum;
                child.insert(childKeyNum, x.pairs.get(0));
                childKeyNum ++;
                for (int j = 0; j < m - 1; j ++) {
                    child.insert(childKeyNum, rightBrother.pairs.get(j));
                    if (!isChildLeaf) {
                        child.insertChild(childKeyNum, rightBrother.children.get(j));
                    }
                    childKeyNum ++;
                }
                if (! isChildLeaf) {
                    child.insertChild(childKeyNum, rightBrother.children.get(m - 1));
                }
                x.removeChild(1);
                x.remove(0);
                return deleteMinKey(child);
            }
        }
    }
    
    
    private Pair deleteMaxKey(BTreeNode x) {
        int keyNum = x.keynum;
        if (x.isLeaf) {
            return x.remove(keyNum - 1);
        } else {
            BTreeNode child = x.children.get(keyNum);
            boolean isChildLeaf = child.isLeaf;
            BTreeNode leftBrother = x.children.get(keyNum - 1);
            int leftBrotherKeyNum = leftBrother.keynum;
            
            if (child.keynum >= m) {
                return deleteMaxKey(child);
            } else if (leftBrother.keynum >= m){
                // 3.a
        	Pair leftPair = leftBrother.pairs.get(leftBrotherKeyNum - 1);
                child.insert(0, x.pairs.get(keyNum - 1));
                x.pairs.set(keyNum - 1, leftPair);
                if (!isChildLeaf) {
                    BTreeNode leftBrotherLastChild = leftBrother.children.get(leftBrotherKeyNum);
                    child.insertChild(0, leftBrotherLastChild);
                    leftBrother.removeChild(leftBrotherKeyNum);
                }
                leftBrother.remove(leftBrotherKeyNum - 1);
                return deleteMaxKey(child);
            } else {
                // 3.b
                leftBrother.insert(leftBrotherKeyNum, x.pairs.get(keyNum - 1));
                leftBrotherKeyNum ++;
                for (int j = 0; j < m - 1; j ++) {
                    leftBrother.insert(leftBrotherKeyNum, child.pairs.get(j));
                    if (!isChildLeaf) {
                        leftBrother.insertChild(leftBrotherKeyNum, child.children.get(j));
                    }
                    leftBrotherKeyNum ++;
                }
                if (!isChildLeaf) {
                    leftBrother.insertChild(leftBrotherKeyNum, child.children.get(m - 1));
                }
                // ɾ���ؼ��ֺͺ��ӵĲ�����÷��ں�����ִ��
                x.removeChild(keyNum);
                x.remove(keyNum - 1);
                return deleteMaxKey(leftBrother);
            }
        }
    }
}
