/*      */ package org.apache.commons.collections.bidimap;
/*      */ 
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections.BidiMap;
/*      */ import org.apache.commons.collections.KeyValue;
/*      */ import org.apache.commons.collections.MapIterator;
/*      */ import org.apache.commons.collections.OrderedBidiMap;
/*      */ import org.apache.commons.collections.OrderedIterator;
/*      */ import org.apache.commons.collections.OrderedMapIterator;
/*      */ import org.apache.commons.collections.iterators.EmptyOrderedMapIterator;
/*      */ import org.apache.commons.collections.keyvalue.UnmodifiableMapEntry;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TreeBidiMap
/*      */   implements OrderedBidiMap
/*      */ {
/*      */   private static final int KEY = 0;
/*      */   private static final int VALUE = 1;
/*      */   private static final int MAPENTRY = 2;
/*      */   private static final int INVERSEMAPENTRY = 3;
/*      */   private static final int SUM_OF_INDICES = 1;
/*      */   private static final int FIRST_INDEX = 0;
/*      */   private static final int NUMBER_OF_INDICES = 2;
/*   84 */   private static final String[] dataName = new String[] { "key", "value" };
/*      */   
/*   86 */   private Node[] rootNode = new Node[2];
/*   87 */   private int nodeCount = 0;
/*   88 */   private int modifications = 0;
/*      */   private Set keySet;
/*      */   private Set valuesSet;
/*      */   private Set entrySet;
/*   92 */   private Inverse inverse = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TreeBidiMap(Map map) {
/*  112 */     putAll(map);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  122 */     return this.nodeCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  131 */     return (this.nodeCount == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(Object key) {
/*  145 */     checkKey(key);
/*  146 */     return (lookup((Comparable)key, 0) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*  160 */     checkValue(value);
/*  161 */     return (lookup((Comparable)value, 1) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object get(Object key) {
/*  177 */     return doGet((Comparable)key, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object put(Object key, Object value) {
/*  205 */     return doPut((Comparable)key, (Comparable)value, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putAll(Map map) {
/*  216 */     Iterator it = map.entrySet().iterator();
/*  217 */     while (it.hasNext()) {
/*  218 */       Map.Entry entry = it.next();
/*  219 */       put(entry.getKey(), entry.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object remove(Object key) {
/*  235 */     return doRemove((Comparable)key, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  242 */     modify();
/*      */     
/*  244 */     this.nodeCount = 0;
/*  245 */     this.rootNode[0] = null;
/*  246 */     this.rootNode[1] = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getKey(Object value) {
/*  263 */     return doGet((Comparable)value, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object removeValue(Object value) {
/*  278 */     return doRemove((Comparable)value, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object firstKey() {
/*  289 */     if (this.nodeCount == 0) {
/*  290 */       throw new NoSuchElementException("Map is empty");
/*      */     }
/*  292 */     return leastNode(this.rootNode[0], 0).getKey();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object lastKey() {
/*  302 */     if (this.nodeCount == 0) {
/*  303 */       throw new NoSuchElementException("Map is empty");
/*      */     }
/*  305 */     return greatestNode(this.rootNode[0], 0).getKey();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object nextKey(Object key) {
/*  317 */     checkKey(key);
/*  318 */     Node node = nextGreater(lookup((Comparable)key, 0), 0);
/*  319 */     return (node == null) ? null : node.getKey();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object previousKey(Object key) {
/*  331 */     checkKey(key);
/*  332 */     Node node = nextSmaller(lookup((Comparable)key, 0), 0);
/*  333 */     return (node == null) ? null : node.getKey();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set keySet() {
/*  350 */     if (this.keySet == null) {
/*  351 */       this.keySet = new View(this, 0, 0);
/*      */     }
/*  353 */     return this.keySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection values() {
/*  371 */     if (this.valuesSet == null) {
/*  372 */       this.valuesSet = new View(this, 0, 1);
/*      */     }
/*  374 */     return this.valuesSet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set entrySet() {
/*  393 */     if (this.entrySet == null) {
/*  394 */       return new EntryView(this, 0, 2);
/*      */     }
/*  396 */     return this.entrySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MapIterator mapIterator() {
/*  408 */     if (isEmpty()) {
/*  409 */       return (MapIterator)EmptyOrderedMapIterator.INSTANCE;
/*      */     }
/*  411 */     return (MapIterator)new ViewMapIterator(this, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public OrderedMapIterator orderedMapIterator() {
/*  422 */     if (isEmpty()) {
/*  423 */       return EmptyOrderedMapIterator.INSTANCE;
/*      */     }
/*  425 */     return new ViewMapIterator(this, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BidiMap inverseBidiMap() {
/*  435 */     return (BidiMap)inverseOrderedBidiMap();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public OrderedBidiMap inverseOrderedBidiMap() {
/*  444 */     if (this.inverse == null) {
/*  445 */       this.inverse = new Inverse(this);
/*      */     }
/*  447 */     return this.inverse;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  458 */     return doEquals(obj, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  467 */     return doHashCode(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*  476 */     return doToString(0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object doGet(Comparable obj, int index) {
/*  490 */     checkNonNullComparable(obj, index);
/*  491 */     Node node = lookup(obj, index);
/*  492 */     return (node == null) ? null : node.getData(oppositeIndex(index));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object doPut(Comparable key, Comparable value, int index) {
/*  504 */     checkKeyAndValue(key, value);
/*      */ 
/*      */     
/*  507 */     Object prev = (index == 0) ? doGet(key, 0) : doGet(value, 1);
/*  508 */     doRemove(key, 0);
/*  509 */     doRemove(value, 1);
/*      */     
/*  511 */     Node node = this.rootNode[0];
/*  512 */     if (node == null) {
/*      */       
/*  514 */       Node root = new Node(key, value);
/*  515 */       this.rootNode[0] = root;
/*  516 */       this.rootNode[1] = root;
/*  517 */       grow();
/*      */     } else {
/*      */       
/*      */       while (true) {
/*      */         
/*  522 */         int cmp = compare(key, node.getData(0));
/*      */         
/*  524 */         if (cmp == 0)
/*      */         {
/*  526 */           throw new IllegalArgumentException("Cannot store a duplicate key (\"" + key + "\") in this Map"); } 
/*  527 */         if (cmp < 0) {
/*  528 */           if (node.getLeft(0) != null) {
/*  529 */             node = node.getLeft(0); continue;
/*      */           } 
/*  531 */           Node node1 = new Node(key, value);
/*      */           
/*  533 */           insertValue(node1);
/*  534 */           node.setLeft(node1, 0);
/*  535 */           node1.setParent(node, 0);
/*  536 */           doRedBlackInsert(node1, 0);
/*  537 */           grow();
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/*  542 */         if (node.getRight(0) != null) {
/*  543 */           node = node.getRight(0); continue;
/*      */         } 
/*  545 */         Node newNode = new Node(key, value);
/*      */         
/*  547 */         insertValue(newNode);
/*  548 */         node.setRight(newNode, 0);
/*  549 */         newNode.setParent(node, 0);
/*  550 */         doRedBlackInsert(newNode, 0);
/*  551 */         grow();
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/*  558 */     return prev;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object doRemove(Comparable o, int index) {
/*  572 */     Node node = lookup(o, index);
/*  573 */     Object rval = null;
/*  574 */     if (node != null) {
/*  575 */       rval = node.getData(oppositeIndex(index));
/*  576 */       doRedBlackDelete(node);
/*      */     } 
/*  578 */     return rval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Node lookup(Comparable data, int index) {
/*  590 */     Node rval = null;
/*  591 */     Node node = this.rootNode[index];
/*      */     
/*  593 */     while (node != null) {
/*  594 */       int cmp = compare(data, node.getData(index));
/*  595 */       if (cmp == 0) {
/*  596 */         rval = node;
/*      */         break;
/*      */       } 
/*  599 */       node = (cmp < 0) ? node.getLeft(index) : node.getRight(index);
/*      */     } 
/*      */ 
/*      */     
/*  603 */     return rval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Node nextGreater(Node node, int index) {
/*  614 */     Node rval = null;
/*  615 */     if (node == null) {
/*  616 */       rval = null;
/*  617 */     } else if (node.getRight(index) != null) {
/*      */ 
/*      */       
/*  620 */       rval = leastNode(node.getRight(index), index);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  628 */       Node parent = node.getParent(index);
/*  629 */       Node child = node;
/*      */       
/*  631 */       while (parent != null && child == parent.getRight(index)) {
/*  632 */         child = parent;
/*  633 */         parent = parent.getParent(index);
/*      */       } 
/*  635 */       rval = parent;
/*      */     } 
/*  637 */     return rval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Node nextSmaller(Node node, int index) {
/*  648 */     Node rval = null;
/*  649 */     if (node == null) {
/*  650 */       rval = null;
/*  651 */     } else if (node.getLeft(index) != null) {
/*      */ 
/*      */       
/*  654 */       rval = greatestNode(node.getLeft(index), index);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  662 */       Node parent = node.getParent(index);
/*  663 */       Node child = node;
/*      */       
/*  665 */       while (parent != null && child == parent.getLeft(index)) {
/*  666 */         child = parent;
/*  667 */         parent = parent.getParent(index);
/*      */       } 
/*  669 */       rval = parent;
/*      */     } 
/*  671 */     return rval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int oppositeIndex(int index) {
/*  685 */     return 1 - index;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int compare(Comparable o1, Comparable o2) {
/*  698 */     return o1.compareTo(o2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node leastNode(Node node, int index) {
/*  710 */     Node rval = node;
/*  711 */     if (rval != null) {
/*  712 */       while (rval.getLeft(index) != null) {
/*  713 */         rval = rval.getLeft(index);
/*      */       }
/*      */     }
/*  716 */     return rval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node greatestNode(Node node, int index) {
/*  727 */     Node rval = node;
/*  728 */     if (rval != null) {
/*  729 */       while (rval.getRight(index) != null) {
/*  730 */         rval = rval.getRight(index);
/*      */       }
/*      */     }
/*  733 */     return rval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void copyColor(Node from, Node to, int index) {
/*  745 */     if (to != null) {
/*  746 */       if (from == null) {
/*      */         
/*  748 */         to.setBlack(index);
/*      */       } else {
/*  750 */         to.copyColor(from, index);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isRed(Node node, int index) {
/*  763 */     return (node == null) ? false : node.isRed(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isBlack(Node node, int index) {
/*  774 */     return (node == null) ? true : node.isBlack(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void makeRed(Node node, int index) {
/*  784 */     if (node != null) {
/*  785 */       node.setRed(index);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void makeBlack(Node node, int index) {
/*  796 */     if (node != null) {
/*  797 */       node.setBlack(index);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node getGrandParent(Node node, int index) {
/*  809 */     return getParent(getParent(node, index), index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node getParent(Node node, int index) {
/*  820 */     return (node == null) ? null : node.getParent(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node getRightChild(Node node, int index) {
/*  831 */     return (node == null) ? null : node.getRight(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node getLeftChild(Node node, int index) {
/*  842 */     return (node == null) ? null : node.getLeft(index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isLeftChild(Node node, int index) {
/*  857 */     return (node == null) ? true : ((node.getParent(index) == null) ? false : ((node == node.getParent(index).getLeft(index))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isRightChild(Node node, int index) {
/*  875 */     return (node == null) ? true : ((node.getParent(index) == null) ? false : ((node == node.getParent(index).getRight(index))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void rotateLeft(Node node, int index) {
/*  888 */     Node rightChild = node.getRight(index);
/*  889 */     node.setRight(rightChild.getLeft(index), index);
/*      */     
/*  891 */     if (rightChild.getLeft(index) != null) {
/*  892 */       rightChild.getLeft(index).setParent(node, index);
/*      */     }
/*  894 */     rightChild.setParent(node.getParent(index), index);
/*      */     
/*  896 */     if (node.getParent(index) == null) {
/*      */       
/*  898 */       this.rootNode[index] = rightChild;
/*  899 */     } else if (node.getParent(index).getLeft(index) == node) {
/*  900 */       node.getParent(index).setLeft(rightChild, index);
/*      */     } else {
/*  902 */       node.getParent(index).setRight(rightChild, index);
/*      */     } 
/*      */     
/*  905 */     rightChild.setLeft(node, index);
/*  906 */     node.setParent(rightChild, index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void rotateRight(Node node, int index) {
/*  916 */     Node leftChild = node.getLeft(index);
/*  917 */     node.setLeft(leftChild.getRight(index), index);
/*  918 */     if (leftChild.getRight(index) != null) {
/*  919 */       leftChild.getRight(index).setParent(node, index);
/*      */     }
/*  921 */     leftChild.setParent(node.getParent(index), index);
/*      */     
/*  923 */     if (node.getParent(index) == null) {
/*      */       
/*  925 */       this.rootNode[index] = leftChild;
/*  926 */     } else if (node.getParent(index).getRight(index) == node) {
/*  927 */       node.getParent(index).setRight(leftChild, index);
/*      */     } else {
/*  929 */       node.getParent(index).setLeft(leftChild, index);
/*      */     } 
/*      */     
/*  932 */     leftChild.setRight(node, index);
/*  933 */     node.setParent(leftChild, index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doRedBlackInsert(Node insertedNode, int index) {
/*  944 */     Node currentNode = insertedNode;
/*  945 */     makeRed(currentNode, index);
/*      */ 
/*      */ 
/*      */     
/*  949 */     while (currentNode != null && currentNode != this.rootNode[index] && isRed(currentNode.getParent(index), index)) {
/*  950 */       if (isLeftChild(getParent(currentNode, index), index)) {
/*  951 */         Node node = getRightChild(getGrandParent(currentNode, index), index);
/*      */         
/*  953 */         if (isRed(node, index)) {
/*  954 */           makeBlack(getParent(currentNode, index), index);
/*  955 */           makeBlack(node, index);
/*  956 */           makeRed(getGrandParent(currentNode, index), index);
/*      */           
/*  958 */           currentNode = getGrandParent(currentNode, index); continue;
/*      */         } 
/*  960 */         if (isRightChild(currentNode, index)) {
/*  961 */           currentNode = getParent(currentNode, index);
/*      */           
/*  963 */           rotateLeft(currentNode, index);
/*      */         } 
/*      */         
/*  966 */         makeBlack(getParent(currentNode, index), index);
/*  967 */         makeRed(getGrandParent(currentNode, index), index);
/*      */         
/*  969 */         if (getGrandParent(currentNode, index) != null) {
/*  970 */           rotateRight(getGrandParent(currentNode, index), index);
/*      */         }
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  976 */       Node y = getLeftChild(getGrandParent(currentNode, index), index);
/*      */       
/*  978 */       if (isRed(y, index)) {
/*  979 */         makeBlack(getParent(currentNode, index), index);
/*  980 */         makeBlack(y, index);
/*  981 */         makeRed(getGrandParent(currentNode, index), index);
/*      */         
/*  983 */         currentNode = getGrandParent(currentNode, index); continue;
/*      */       } 
/*  985 */       if (isLeftChild(currentNode, index)) {
/*  986 */         currentNode = getParent(currentNode, index);
/*      */         
/*  988 */         rotateRight(currentNode, index);
/*      */       } 
/*      */       
/*  991 */       makeBlack(getParent(currentNode, index), index);
/*  992 */       makeRed(getGrandParent(currentNode, index), index);
/*      */       
/*  994 */       if (getGrandParent(currentNode, index) != null) {
/*  995 */         rotateLeft(getGrandParent(currentNode, index), index);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1001 */     makeBlack(this.rootNode[index], index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doRedBlackDelete(Node deletedNode) {
/* 1011 */     for (int index = 0; index < 2; index++) {
/*      */ 
/*      */       
/* 1014 */       if (deletedNode.getLeft(index) != null && deletedNode.getRight(index) != null) {
/* 1015 */         swapPosition(nextGreater(deletedNode, index), deletedNode, index);
/*      */       }
/*      */       
/* 1018 */       Node replacement = (deletedNode.getLeft(index) != null) ? deletedNode.getLeft(index) : deletedNode.getRight(index);
/*      */ 
/*      */       
/* 1021 */       if (replacement != null) {
/* 1022 */         replacement.setParent(deletedNode.getParent(index), index);
/*      */         
/* 1024 */         if (deletedNode.getParent(index) == null) {
/* 1025 */           this.rootNode[index] = replacement;
/* 1026 */         } else if (deletedNode == deletedNode.getParent(index).getLeft(index)) {
/* 1027 */           deletedNode.getParent(index).setLeft(replacement, index);
/*      */         } else {
/* 1029 */           deletedNode.getParent(index).setRight(replacement, index);
/*      */         } 
/*      */         
/* 1032 */         deletedNode.setLeft(null, index);
/* 1033 */         deletedNode.setRight(null, index);
/* 1034 */         deletedNode.setParent(null, index);
/*      */         
/* 1036 */         if (isBlack(deletedNode, index)) {
/* 1037 */           doRedBlackDeleteFixup(replacement, index);
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 1042 */       else if (deletedNode.getParent(index) == null) {
/*      */ 
/*      */         
/* 1045 */         this.rootNode[index] = null;
/*      */       }
/*      */       else {
/*      */         
/* 1049 */         if (isBlack(deletedNode, index)) {
/* 1050 */           doRedBlackDeleteFixup(deletedNode, index);
/*      */         }
/*      */         
/* 1053 */         if (deletedNode.getParent(index) != null) {
/* 1054 */           if (deletedNode == deletedNode.getParent(index).getLeft(index)) {
/* 1055 */             deletedNode.getParent(index).setLeft(null, index);
/*      */           } else {
/* 1057 */             deletedNode.getParent(index).setRight(null, index);
/*      */           } 
/*      */           
/* 1060 */           deletedNode.setParent(null, index);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1065 */     shrink();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doRedBlackDeleteFixup(Node replacementNode, int index) {
/* 1078 */     Node currentNode = replacementNode;
/*      */     
/* 1080 */     while (currentNode != this.rootNode[index] && isBlack(currentNode, index)) {
/* 1081 */       if (isLeftChild(currentNode, index)) {
/* 1082 */         Node node = getRightChild(getParent(currentNode, index), index);
/*      */         
/* 1084 */         if (isRed(node, index)) {
/* 1085 */           makeBlack(node, index);
/* 1086 */           makeRed(getParent(currentNode, index), index);
/* 1087 */           rotateLeft(getParent(currentNode, index), index);
/*      */           
/* 1089 */           node = getRightChild(getParent(currentNode, index), index);
/*      */         } 
/*      */         
/* 1092 */         if (isBlack(getLeftChild(node, index), index) && isBlack(getRightChild(node, index), index)) {
/*      */           
/* 1094 */           makeRed(node, index);
/*      */           
/* 1096 */           currentNode = getParent(currentNode, index); continue;
/*      */         } 
/* 1098 */         if (isBlack(getRightChild(node, index), index)) {
/* 1099 */           makeBlack(getLeftChild(node, index), index);
/* 1100 */           makeRed(node, index);
/* 1101 */           rotateRight(node, index);
/*      */           
/* 1103 */           node = getRightChild(getParent(currentNode, index), index);
/*      */         } 
/*      */         
/* 1106 */         copyColor(getParent(currentNode, index), node, index);
/* 1107 */         makeBlack(getParent(currentNode, index), index);
/* 1108 */         makeBlack(getRightChild(node, index), index);
/* 1109 */         rotateLeft(getParent(currentNode, index), index);
/*      */         
/* 1111 */         currentNode = this.rootNode[index];
/*      */         continue;
/*      */       } 
/* 1114 */       Node siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       
/* 1116 */       if (isRed(siblingNode, index)) {
/* 1117 */         makeBlack(siblingNode, index);
/* 1118 */         makeRed(getParent(currentNode, index), index);
/* 1119 */         rotateRight(getParent(currentNode, index), index);
/*      */         
/* 1121 */         siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       } 
/*      */       
/* 1124 */       if (isBlack(getRightChild(siblingNode, index), index) && isBlack(getLeftChild(siblingNode, index), index)) {
/*      */         
/* 1126 */         makeRed(siblingNode, index);
/*      */         
/* 1128 */         currentNode = getParent(currentNode, index); continue;
/*      */       } 
/* 1130 */       if (isBlack(getLeftChild(siblingNode, index), index)) {
/* 1131 */         makeBlack(getRightChild(siblingNode, index), index);
/* 1132 */         makeRed(siblingNode, index);
/* 1133 */         rotateLeft(siblingNode, index);
/*      */         
/* 1135 */         siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       } 
/*      */       
/* 1138 */       copyColor(getParent(currentNode, index), siblingNode, index);
/* 1139 */       makeBlack(getParent(currentNode, index), index);
/* 1140 */       makeBlack(getLeftChild(siblingNode, index), index);
/* 1141 */       rotateRight(getParent(currentNode, index), index);
/*      */       
/* 1143 */       currentNode = this.rootNode[index];
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1148 */     makeBlack(currentNode, index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void swapPosition(Node x, Node y, int index) {
/* 1162 */     Node xFormerParent = x.getParent(index);
/* 1163 */     Node xFormerLeftChild = x.getLeft(index);
/* 1164 */     Node xFormerRightChild = x.getRight(index);
/* 1165 */     Node yFormerParent = y.getParent(index);
/* 1166 */     Node yFormerLeftChild = y.getLeft(index);
/* 1167 */     Node yFormerRightChild = y.getRight(index);
/* 1168 */     boolean xWasLeftChild = (x.getParent(index) != null && x == x.getParent(index).getLeft(index));
/* 1169 */     boolean yWasLeftChild = (y.getParent(index) != null && y == y.getParent(index).getLeft(index));
/*      */ 
/*      */     
/* 1172 */     if (x == yFormerParent) {
/* 1173 */       x.setParent(y, index);
/*      */       
/* 1175 */       if (yWasLeftChild) {
/* 1176 */         y.setLeft(x, index);
/* 1177 */         y.setRight(xFormerRightChild, index);
/*      */       } else {
/* 1179 */         y.setRight(x, index);
/* 1180 */         y.setLeft(xFormerLeftChild, index);
/*      */       } 
/*      */     } else {
/* 1183 */       x.setParent(yFormerParent, index);
/*      */       
/* 1185 */       if (yFormerParent != null) {
/* 1186 */         if (yWasLeftChild) {
/* 1187 */           yFormerParent.setLeft(x, index);
/*      */         } else {
/* 1189 */           yFormerParent.setRight(x, index);
/*      */         } 
/*      */       }
/*      */       
/* 1193 */       y.setLeft(xFormerLeftChild, index);
/* 1194 */       y.setRight(xFormerRightChild, index);
/*      */     } 
/*      */     
/* 1197 */     if (y == xFormerParent) {
/* 1198 */       y.setParent(x, index);
/*      */       
/* 1200 */       if (xWasLeftChild) {
/* 1201 */         x.setLeft(y, index);
/* 1202 */         x.setRight(yFormerRightChild, index);
/*      */       } else {
/* 1204 */         x.setRight(y, index);
/* 1205 */         x.setLeft(yFormerLeftChild, index);
/*      */       } 
/*      */     } else {
/* 1208 */       y.setParent(xFormerParent, index);
/*      */       
/* 1210 */       if (xFormerParent != null) {
/* 1211 */         if (xWasLeftChild) {
/* 1212 */           xFormerParent.setLeft(y, index);
/*      */         } else {
/* 1214 */           xFormerParent.setRight(y, index);
/*      */         } 
/*      */       }
/*      */       
/* 1218 */       x.setLeft(yFormerLeftChild, index);
/* 1219 */       x.setRight(yFormerRightChild, index);
/*      */     } 
/*      */ 
/*      */     
/* 1223 */     if (x.getLeft(index) != null) {
/* 1224 */       x.getLeft(index).setParent(x, index);
/*      */     }
/*      */     
/* 1227 */     if (x.getRight(index) != null) {
/* 1228 */       x.getRight(index).setParent(x, index);
/*      */     }
/*      */     
/* 1231 */     if (y.getLeft(index) != null) {
/* 1232 */       y.getLeft(index).setParent(y, index);
/*      */     }
/*      */     
/* 1235 */     if (y.getRight(index) != null) {
/* 1236 */       y.getRight(index).setParent(y, index);
/*      */     }
/*      */     
/* 1239 */     x.swapColors(y, index);
/*      */ 
/*      */     
/* 1242 */     if (this.rootNode[index] == x) {
/* 1243 */       this.rootNode[index] = y;
/* 1244 */     } else if (this.rootNode[index] == y) {
/* 1245 */       this.rootNode[index] = x;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void checkNonNullComparable(Object o, int index) {
/* 1261 */     if (o == null) {
/* 1262 */       throw new NullPointerException(dataName[index] + " cannot be null");
/*      */     }
/* 1264 */     if (!(o instanceof Comparable)) {
/* 1265 */       throw new ClassCastException(dataName[index] + " must be Comparable");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void checkKey(Object key) {
/* 1278 */     checkNonNullComparable(key, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void checkValue(Object value) {
/* 1290 */     checkNonNullComparable(value, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void checkKeyAndValue(Object key, Object value) {
/* 1304 */     checkKey(key);
/* 1305 */     checkValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void modify() {
/* 1314 */     this.modifications++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void grow() {
/* 1321 */     modify();
/* 1322 */     this.nodeCount++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void shrink() {
/* 1329 */     modify();
/* 1330 */     this.nodeCount--;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void insertValue(Node newNode) throws IllegalArgumentException {
/* 1342 */     Node node = this.rootNode[1];
/*      */     
/*      */     while (true) {
/* 1345 */       int cmp = compare(newNode.getData(1), node.getData(1));
/*      */       
/* 1347 */       if (cmp == 0) {
/* 1348 */         throw new IllegalArgumentException("Cannot store a duplicate value (\"" + newNode.getData(1) + "\") in this Map");
/*      */       }
/* 1350 */       if (cmp < 0) {
/* 1351 */         if (node.getLeft(1) != null) {
/* 1352 */           node = node.getLeft(1); continue;
/*      */         } 
/* 1354 */         node.setLeft(newNode, 1);
/* 1355 */         newNode.setParent(node, 1);
/* 1356 */         doRedBlackInsert(newNode, 1);
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/* 1361 */       if (node.getRight(1) != null) {
/* 1362 */         node = node.getRight(1); continue;
/*      */       } 
/* 1364 */       node.setRight(newNode, 1);
/* 1365 */       newNode.setParent(node, 1);
/* 1366 */       doRedBlackInsert(newNode, 1);
/*      */       break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean doEquals(Object obj, int type) {
/* 1383 */     if (obj == this) {
/* 1384 */       return true;
/*      */     }
/* 1386 */     if (!(obj instanceof Map)) {
/* 1387 */       return false;
/*      */     }
/* 1389 */     Map other = (Map)obj;
/* 1390 */     if (other.size() != size()) {
/* 1391 */       return false;
/*      */     }
/*      */     
/* 1394 */     if (this.nodeCount > 0) {
/*      */       try {
/* 1396 */         for (ViewMapIterator viewMapIterator = new ViewMapIterator(this, type); viewMapIterator.hasNext(); ) {
/* 1397 */           Object key = viewMapIterator.next();
/* 1398 */           Object value = viewMapIterator.getValue();
/* 1399 */           if (!value.equals(other.get(key))) {
/* 1400 */             return false;
/*      */           }
/*      */         } 
/* 1403 */       } catch (ClassCastException ex) {
/* 1404 */         return false;
/* 1405 */       } catch (NullPointerException ex) {
/* 1406 */         return false;
/*      */       } 
/*      */     }
/* 1409 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int doHashCode(int type) {
/* 1419 */     int total = 0;
/* 1420 */     if (this.nodeCount > 0) {
/* 1421 */       for (ViewMapIterator viewMapIterator = new ViewMapIterator(this, type); viewMapIterator.hasNext(); ) {
/* 1422 */         Object key = viewMapIterator.next();
/* 1423 */         Object value = viewMapIterator.getValue();
/* 1424 */         total += key.hashCode() ^ value.hashCode();
/*      */       } 
/*      */     }
/* 1427 */     return total;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String doToString(int type) {
/* 1437 */     if (this.nodeCount == 0) {
/* 1438 */       return "{}";
/*      */     }
/* 1440 */     StringBuffer buf = new StringBuffer(this.nodeCount * 32);
/* 1441 */     buf.append('{');
/* 1442 */     ViewMapIterator viewMapIterator = new ViewMapIterator(this, type);
/* 1443 */     boolean hasNext = viewMapIterator.hasNext();
/* 1444 */     while (hasNext) {
/* 1445 */       Object key = viewMapIterator.next();
/* 1446 */       Object value = viewMapIterator.getValue();
/* 1447 */       buf.append((key == this) ? "(this Map)" : key).append('=').append((value == this) ? "(this Map)" : value);
/*      */ 
/*      */ 
/*      */       
/* 1451 */       hasNext = viewMapIterator.hasNext();
/* 1452 */       if (hasNext) {
/* 1453 */         buf.append(", ");
/*      */       }
/*      */     } 
/*      */     
/* 1457 */     buf.append('}');
/* 1458 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TreeBidiMap() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class View
/*      */     extends AbstractSet
/*      */   {
/*      */     protected final TreeBidiMap main;
/*      */ 
/*      */     
/*      */     protected final int orderType;
/*      */ 
/*      */     
/*      */     protected final int dataType;
/*      */ 
/*      */ 
/*      */     
/*      */     View(TreeBidiMap main, int orderType, int dataType) {
/* 1483 */       this.main = main;
/* 1484 */       this.orderType = orderType;
/* 1485 */       this.dataType = dataType;
/*      */     }
/*      */     
/*      */     public Iterator iterator() {
/* 1489 */       return (Iterator)new TreeBidiMap.ViewIterator(this.main, this.orderType, this.dataType);
/*      */     }
/*      */     
/*      */     public int size() {
/* 1493 */       return this.main.size();
/*      */     }
/*      */     
/*      */     public boolean contains(Object obj) {
/* 1497 */       TreeBidiMap.checkNonNullComparable(obj, this.dataType);
/* 1498 */       return (this.main.lookup((Comparable)obj, this.dataType) != null);
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/* 1502 */       return (this.main.doRemove((Comparable)obj, this.dataType) != null);
/*      */     }
/*      */     
/*      */     public void clear() {
/* 1506 */       this.main.clear();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ViewIterator
/*      */     implements OrderedIterator
/*      */   {
/*      */     protected final TreeBidiMap main;
/*      */ 
/*      */ 
/*      */     
/*      */     protected final int orderType;
/*      */ 
/*      */     
/*      */     protected final int dataType;
/*      */ 
/*      */     
/*      */     protected TreeBidiMap.Node lastReturnedNode;
/*      */ 
/*      */     
/*      */     protected TreeBidiMap.Node nextNode;
/*      */ 
/*      */     
/*      */     protected TreeBidiMap.Node previousNode;
/*      */ 
/*      */     
/*      */     private int expectedModifications;
/*      */ 
/*      */ 
/*      */     
/*      */     ViewIterator(TreeBidiMap main, int orderType, int dataType) {
/* 1540 */       this.main = main;
/* 1541 */       this.orderType = orderType;
/* 1542 */       this.dataType = dataType;
/* 1543 */       this.expectedModifications = main.modifications;
/* 1544 */       this.nextNode = TreeBidiMap.leastNode(main.rootNode[orderType], orderType);
/* 1545 */       this.lastReturnedNode = null;
/* 1546 */       this.previousNode = null;
/*      */     }
/*      */     
/*      */     public final boolean hasNext() {
/* 1550 */       return (this.nextNode != null);
/*      */     }
/*      */     
/*      */     public final Object next() {
/* 1554 */       if (this.nextNode == null) {
/* 1555 */         throw new NoSuchElementException();
/*      */       }
/* 1557 */       if (this.main.modifications != this.expectedModifications) {
/* 1558 */         throw new ConcurrentModificationException();
/*      */       }
/* 1560 */       this.lastReturnedNode = this.nextNode;
/* 1561 */       this.previousNode = this.nextNode;
/* 1562 */       this.nextNode = this.main.nextGreater(this.nextNode, this.orderType);
/* 1563 */       return doGetData();
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1567 */       return (this.previousNode != null);
/*      */     }
/*      */     
/*      */     public Object previous() {
/* 1571 */       if (this.previousNode == null) {
/* 1572 */         throw new NoSuchElementException();
/*      */       }
/* 1574 */       if (this.main.modifications != this.expectedModifications) {
/* 1575 */         throw new ConcurrentModificationException();
/*      */       }
/* 1577 */       this.nextNode = this.lastReturnedNode;
/* 1578 */       if (this.nextNode == null) {
/* 1579 */         this.nextNode = this.main.nextGreater(this.previousNode, this.orderType);
/*      */       }
/* 1581 */       this.lastReturnedNode = this.previousNode;
/* 1582 */       this.previousNode = this.main.nextSmaller(this.previousNode, this.orderType);
/* 1583 */       return doGetData();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object doGetData() {
/* 1591 */       switch (this.dataType) {
/*      */         case 0:
/* 1593 */           return this.lastReturnedNode.getKey();
/*      */         case 1:
/* 1595 */           return this.lastReturnedNode.getValue();
/*      */         case 2:
/* 1597 */           return this.lastReturnedNode;
/*      */         case 3:
/* 1599 */           return new UnmodifiableMapEntry(this.lastReturnedNode.getValue(), this.lastReturnedNode.getKey());
/*      */       } 
/* 1601 */       return null;
/*      */     }
/*      */     
/*      */     public final void remove() {
/* 1605 */       if (this.lastReturnedNode == null) {
/* 1606 */         throw new IllegalStateException();
/*      */       }
/* 1608 */       if (this.main.modifications != this.expectedModifications) {
/* 1609 */         throw new ConcurrentModificationException();
/*      */       }
/* 1611 */       this.main.doRedBlackDelete(this.lastReturnedNode);
/* 1612 */       this.expectedModifications++;
/* 1613 */       this.lastReturnedNode = null;
/* 1614 */       if (this.nextNode == null) {
/* 1615 */         this.previousNode = TreeBidiMap.greatestNode(this.main.rootNode[this.orderType], this.orderType);
/*      */       } else {
/* 1617 */         this.previousNode = this.main.nextSmaller(this.nextNode, this.orderType);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ViewMapIterator
/*      */     extends ViewIterator
/*      */     implements OrderedMapIterator
/*      */   {
/*      */     private final int oppositeType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     ViewMapIterator(TreeBidiMap main, int orderType) {
/* 1637 */       super(main, orderType, orderType);
/* 1638 */       this.oppositeType = TreeBidiMap.oppositeIndex(this.dataType);
/*      */     }
/*      */     
/*      */     public Object getKey() {
/* 1642 */       if (this.lastReturnedNode == null) {
/* 1643 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
/*      */       }
/* 1645 */       return this.lastReturnedNode.getData(this.dataType);
/*      */     }
/*      */     
/*      */     public Object getValue() {
/* 1649 */       if (this.lastReturnedNode == null) {
/* 1650 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
/*      */       }
/* 1652 */       return this.lastReturnedNode.getData(this.oppositeType);
/*      */     }
/*      */     
/*      */     public Object setValue(Object obj) {
/* 1656 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class EntryView
/*      */     extends View
/*      */   {
/*      */     private final int oppositeType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     EntryView(TreeBidiMap main, int orderType, int dataType) {
/* 1676 */       super(main, orderType, dataType);
/* 1677 */       this.oppositeType = TreeBidiMap.oppositeIndex(orderType);
/*      */     }
/*      */     
/*      */     public boolean contains(Object obj) {
/* 1681 */       if (!(obj instanceof Map.Entry)) {
/* 1682 */         return false;
/*      */       }
/* 1684 */       Map.Entry entry = (Map.Entry)obj;
/* 1685 */       Object value = entry.getValue();
/* 1686 */       TreeBidiMap.Node node = this.main.lookup((Comparable)entry.getKey(), this.orderType);
/* 1687 */       return (node != null && node.getData(this.oppositeType).equals(value));
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/* 1691 */       if (!(obj instanceof Map.Entry)) {
/* 1692 */         return false;
/*      */       }
/* 1694 */       Map.Entry entry = (Map.Entry)obj;
/* 1695 */       Object value = entry.getValue();
/* 1696 */       TreeBidiMap.Node node = this.main.lookup((Comparable)entry.getKey(), this.orderType);
/* 1697 */       if (node != null && node.getData(this.oppositeType).equals(value)) {
/* 1698 */         this.main.doRedBlackDelete(node);
/* 1699 */         return true;
/*      */       } 
/* 1701 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class Node
/*      */     implements Map.Entry, KeyValue
/*      */   {
/*      */     private Comparable[] data;
/*      */ 
/*      */     
/*      */     private Node[] leftNode;
/*      */ 
/*      */     
/*      */     private Node[] rightNode;
/*      */     
/*      */     private Node[] parentNode;
/*      */     
/*      */     private boolean[] blackColor;
/*      */     
/*      */     private int hashcodeValue;
/*      */     
/*      */     private boolean calculatedHashCode;
/*      */ 
/*      */     
/*      */     Node(Comparable key, Comparable value) {
/* 1728 */       this.data = new Comparable[] { key, value };
/* 1729 */       this.leftNode = new Node[2];
/* 1730 */       this.rightNode = new Node[2];
/* 1731 */       this.parentNode = new Node[2];
/* 1732 */       this.blackColor = new boolean[] { true, true };
/* 1733 */       this.calculatedHashCode = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Comparable getData(int index) {
/* 1743 */       return this.data[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setLeft(Node node, int index) {
/* 1753 */       this.leftNode[index] = node;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Node getLeft(int index) {
/* 1763 */       return this.leftNode[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setRight(Node node, int index) {
/* 1773 */       this.rightNode[index] = node;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Node getRight(int index) {
/* 1783 */       return this.rightNode[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setParent(Node node, int index) {
/* 1793 */       this.parentNode[index] = node;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Node getParent(int index) {
/* 1803 */       return this.parentNode[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void swapColors(Node node, int index) {
/* 1814 */       this.blackColor[index] = this.blackColor[index] ^ node.blackColor[index];
/* 1815 */       node.blackColor[index] = node.blackColor[index] ^ this.blackColor[index];
/* 1816 */       this.blackColor[index] = this.blackColor[index] ^ node.blackColor[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean isBlack(int index) {
/* 1826 */       return this.blackColor[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean isRed(int index) {
/* 1836 */       return !this.blackColor[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setBlack(int index) {
/* 1845 */       this.blackColor[index] = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setRed(int index) {
/* 1854 */       this.blackColor[index] = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void copyColor(Node node, int index) {
/* 1864 */       this.blackColor[index] = node.blackColor[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getKey() {
/* 1874 */       return this.data[0];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getValue() {
/* 1883 */       return this.data[1];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object setValue(Object ignored) throws UnsupportedOperationException {
/* 1895 */       throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1908 */       if (obj == this) {
/* 1909 */         return true;
/*      */       }
/* 1911 */       if (!(obj instanceof Map.Entry)) {
/* 1912 */         return false;
/*      */       }
/* 1914 */       Map.Entry e = (Map.Entry)obj;
/* 1915 */       return (this.data[0].equals(e.getKey()) && this.data[1].equals(e.getValue()));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 1922 */       if (!this.calculatedHashCode) {
/* 1923 */         this.hashcodeValue = this.data[0].hashCode() ^ this.data[1].hashCode();
/* 1924 */         this.calculatedHashCode = true;
/*      */       } 
/* 1926 */       return this.hashcodeValue;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class Inverse
/*      */     implements OrderedBidiMap
/*      */   {
/*      */     private final TreeBidiMap main;
/*      */ 
/*      */ 
/*      */     
/*      */     private Set keySet;
/*      */ 
/*      */     
/*      */     private Set valuesSet;
/*      */ 
/*      */     
/*      */     private Set entrySet;
/*      */ 
/*      */ 
/*      */     
/*      */     Inverse(TreeBidiMap main) {
/* 1951 */       this.main = main;
/*      */     }
/*      */     
/*      */     public int size() {
/* 1955 */       return this.main.size();
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 1959 */       return this.main.isEmpty();
/*      */     }
/*      */     
/*      */     public Object get(Object key) {
/* 1963 */       return this.main.getKey(key);
/*      */     }
/*      */     
/*      */     public Object getKey(Object value) {
/* 1967 */       return this.main.get(value);
/*      */     }
/*      */     
/*      */     public boolean containsKey(Object key) {
/* 1971 */       return this.main.containsValue(key);
/*      */     }
/*      */     
/*      */     public boolean containsValue(Object value) {
/* 1975 */       return this.main.containsKey(value);
/*      */     }
/*      */     
/*      */     public Object firstKey() {
/* 1979 */       if (this.main.nodeCount == 0) {
/* 1980 */         throw new NoSuchElementException("Map is empty");
/*      */       }
/* 1982 */       return TreeBidiMap.leastNode(this.main.rootNode[1], 1).getValue();
/*      */     }
/*      */     
/*      */     public Object lastKey() {
/* 1986 */       if (this.main.nodeCount == 0) {
/* 1987 */         throw new NoSuchElementException("Map is empty");
/*      */       }
/* 1989 */       return TreeBidiMap.greatestNode(this.main.rootNode[1], 1).getValue();
/*      */     }
/*      */     
/*      */     public Object nextKey(Object key) {
/* 1993 */       TreeBidiMap.checkKey(key);
/* 1994 */       TreeBidiMap.Node node = this.main.nextGreater(this.main.lookup((Comparable)key, 1), 1);
/* 1995 */       return (node == null) ? null : node.getValue();
/*      */     }
/*      */     
/*      */     public Object previousKey(Object key) {
/* 1999 */       TreeBidiMap.checkKey(key);
/* 2000 */       TreeBidiMap.Node node = this.main.nextSmaller(this.main.lookup((Comparable)key, 1), 1);
/* 2001 */       return (node == null) ? null : node.getValue();
/*      */     }
/*      */     
/*      */     public Object put(Object key, Object value) {
/* 2005 */       return this.main.doPut((Comparable)value, (Comparable)key, 1);
/*      */     }
/*      */     
/*      */     public void putAll(Map map) {
/* 2009 */       Iterator it = map.entrySet().iterator();
/* 2010 */       while (it.hasNext()) {
/* 2011 */         Map.Entry entry = it.next();
/* 2012 */         put(entry.getKey(), entry.getValue());
/*      */       } 
/*      */     }
/*      */     
/*      */     public Object remove(Object key) {
/* 2017 */       return this.main.removeValue(key);
/*      */     }
/*      */     
/*      */     public Object removeValue(Object value) {
/* 2021 */       return this.main.remove(value);
/*      */     }
/*      */     
/*      */     public void clear() {
/* 2025 */       this.main.clear();
/*      */     }
/*      */     
/*      */     public Set keySet() {
/* 2029 */       if (this.keySet == null) {
/* 2030 */         this.keySet = new TreeBidiMap.View(this.main, 1, 1);
/*      */       }
/* 2032 */       return this.keySet;
/*      */     }
/*      */     
/*      */     public Collection values() {
/* 2036 */       if (this.valuesSet == null) {
/* 2037 */         this.valuesSet = new TreeBidiMap.View(this.main, 1, 0);
/*      */       }
/* 2039 */       return this.valuesSet;
/*      */     }
/*      */     
/*      */     public Set entrySet() {
/* 2043 */       if (this.entrySet == null) {
/* 2044 */         return new TreeBidiMap.EntryView(this.main, 1, 3);
/*      */       }
/* 2046 */       return this.entrySet;
/*      */     }
/*      */     
/*      */     public MapIterator mapIterator() {
/* 2050 */       if (isEmpty()) {
/* 2051 */         return (MapIterator)EmptyOrderedMapIterator.INSTANCE;
/*      */       }
/* 2053 */       return (MapIterator)new TreeBidiMap.ViewMapIterator(this.main, 1);
/*      */     }
/*      */     
/*      */     public OrderedMapIterator orderedMapIterator() {
/* 2057 */       if (isEmpty()) {
/* 2058 */         return EmptyOrderedMapIterator.INSTANCE;
/*      */       }
/* 2060 */       return new TreeBidiMap.ViewMapIterator(this.main, 1);
/*      */     }
/*      */     
/*      */     public BidiMap inverseBidiMap() {
/* 2064 */       return (BidiMap)this.main;
/*      */     }
/*      */     
/*      */     public OrderedBidiMap inverseOrderedBidiMap() {
/* 2068 */       return this.main;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 2072 */       return this.main.doEquals(obj, 1);
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 2076 */       return this.main.doHashCode(1);
/*      */     }
/*      */     
/*      */     public String toString() {
/* 2080 */       return this.main.doToString(1);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/TreeBidiMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */