/*      */ package org.apache.commons.collections;
/*      */ 
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
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
/*      */ public final class DoubleOrderedMap
/*      */   extends AbstractMap
/*      */ {
/*      */   private static final int KEY = 0;
/*      */   private static final int VALUE = 1;
/*      */   private static final int SUM_OF_INDICES = 1;
/*      */   private static final int FIRST_INDEX = 0;
/*      */   private static final int NUMBER_OF_INDICES = 2;
/*  114 */   private static final String[] dataName = new String[] { "key", "value" };
/*      */   
/*  116 */   private Node[] rootNode = new Node[] { null, null };
/*  117 */   private int nodeCount = 0;
/*  118 */   private int modifications = 0;
/*  119 */   private Set[] setOfKeys = new Set[] { null, null };
/*  120 */   private Set[] setOfEntries = new Set[] { null, null };
/*  121 */   private Collection[] collectionOfValues = new Collection[] { null, null };
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
/*      */   public DoubleOrderedMap(Map map) throws ClassCastException, NullPointerException, IllegalArgumentException {
/*  149 */     putAll(map);
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
/*      */   public Object getKeyForValue(Object value) throws ClassCastException, NullPointerException {
/*  167 */     return doGet((Comparable)value, 1);
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
/*      */   public Object removeValue(Object value) {
/*  179 */     return doRemove((Comparable)value, 1);
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
/*      */   public Set entrySetByValue() {
/*  203 */     if (this.setOfEntries[1] == null) {
/*  204 */       this.setOfEntries[1] = new AbstractSet(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/*  208 */             return (Iterator)new Object(this, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean contains(Object o) {
/*  218 */             if (!(o instanceof Map.Entry)) {
/*  219 */               return false;
/*      */             }
/*      */             
/*  222 */             Map.Entry entry = (Map.Entry)o;
/*  223 */             Object key = entry.getKey();
/*  224 */             DoubleOrderedMap.Node node = this.this$0.lookup((Comparable)entry.getValue(), 1);
/*      */ 
/*      */             
/*  227 */             return (node != null && node.getData(0).equals(key));
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean remove(Object o) {
/*  232 */             if (!(o instanceof Map.Entry)) {
/*  233 */               return false;
/*      */             }
/*      */             
/*  236 */             Map.Entry entry = (Map.Entry)o;
/*  237 */             Object key = entry.getKey();
/*  238 */             DoubleOrderedMap.Node node = this.this$0.lookup((Comparable)entry.getValue(), 1);
/*      */ 
/*      */             
/*  241 */             if (node != null && node.getData(0).equals(key)) {
/*  242 */               this.this$0.doRedBlackDelete(node);
/*      */               
/*  244 */               return true;
/*      */             } 
/*      */             
/*  247 */             return false;
/*      */           }
/*      */           
/*      */           public int size() {
/*  251 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public void clear() {
/*  255 */             this.this$0.clear();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*  260 */     return this.setOfEntries[1];
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
/*      */   public Set keySetByValue() {
/*  283 */     if (this.setOfKeys[1] == null) {
/*  284 */       this.setOfKeys[1] = new AbstractSet(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/*  288 */             return (Iterator)new Object(this, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public int size() {
/*  297 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/*  301 */             return this.this$0.containsKey(o);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean remove(Object o) {
/*  306 */             int oldnodeCount = this.this$0.nodeCount;
/*      */             
/*  308 */             this.this$0.remove(o);
/*      */             
/*  310 */             return (this.this$0.nodeCount != oldnodeCount);
/*      */           }
/*      */           
/*      */           public void clear() {
/*  314 */             this.this$0.clear();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*  319 */     return this.setOfKeys[1];
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
/*      */   public Collection valuesByValue() {
/*  342 */     if (this.collectionOfValues[1] == null) {
/*  343 */       this.collectionOfValues[1] = new AbstractCollection(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/*  347 */             return (Iterator)new Object(this, 1);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public int size() {
/*  356 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/*  360 */             return this.this$0.containsValue(o);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean remove(Object o) {
/*  365 */             int oldnodeCount = this.this$0.nodeCount;
/*      */             
/*  367 */             this.this$0.removeValue(o);
/*      */             
/*  369 */             return (this.this$0.nodeCount != oldnodeCount);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean removeAll(Collection c) {
/*  374 */             boolean modified = false;
/*  375 */             Iterator iter = c.iterator();
/*      */             
/*  377 */             while (iter.hasNext()) {
/*  378 */               if (this.this$0.removeValue(iter.next()) != null) {
/*  379 */                 modified = true;
/*      */               }
/*      */             } 
/*      */             
/*  383 */             return modified;
/*      */           }
/*      */           
/*      */           public void clear() {
/*  387 */             this.this$0.clear();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/*  392 */     return this.collectionOfValues[1];
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
/*      */   private Object doRemove(Comparable o, int index) {
/*  407 */     Node node = lookup(o, index);
/*  408 */     Object rval = null;
/*      */     
/*  410 */     if (node != null) {
/*  411 */       rval = node.getData(oppositeIndex(index));
/*      */       
/*  413 */       doRedBlackDelete(node);
/*      */     } 
/*      */     
/*  416 */     return rval;
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
/*      */   private Object doGet(Comparable o, int index) {
/*  431 */     checkNonNullComparable(o, index);
/*      */     
/*  433 */     Node node = lookup(o, index);
/*      */     
/*  435 */     return (node == null) ? null : node.getData(oppositeIndex(index));
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
/*      */   private int oppositeIndex(int index) {
/*  452 */     return 1 - index;
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
/*      */   private Node lookup(Comparable data, int index) {
/*  466 */     Node rval = null;
/*  467 */     Node node = this.rootNode[index];
/*      */     
/*  469 */     while (node != null) {
/*  470 */       int cmp = compare(data, node.getData(index));
/*      */       
/*  472 */       if (cmp == 0) {
/*  473 */         rval = node;
/*      */         
/*      */         break;
/*      */       } 
/*  477 */       node = (cmp < 0) ? node.getLeft(index) : node.getRight(index);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  483 */     return rval;
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
/*  496 */     return o1.compareTo(o2);
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
/*      */   private static Node leastNode(Node node, int index) {
/*  511 */     Node rval = node;
/*      */     
/*  513 */     if (rval != null) {
/*  514 */       while (rval.getLeft(index) != null) {
/*  515 */         rval = rval.getLeft(index);
/*      */       }
/*      */     }
/*      */     
/*  519 */     return rval;
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
/*      */   private Node nextGreater(Node node, int index) {
/*  532 */     Node rval = null;
/*      */     
/*  534 */     if (node == null) {
/*  535 */       rval = null;
/*  536 */     } else if (node.getRight(index) != null) {
/*      */ 
/*      */ 
/*      */       
/*  540 */       rval = leastNode(node.getRight(index), index);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  549 */       Node parent = node.getParent(index);
/*  550 */       Node child = node;
/*      */       
/*  552 */       while (parent != null && child == parent.getRight(index)) {
/*  553 */         child = parent;
/*  554 */         parent = parent.getParent(index);
/*      */       } 
/*      */       
/*  557 */       rval = parent;
/*      */     } 
/*      */     
/*  560 */     return rval;
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
/*      */   private static void copyColor(Node from, Node to, int index) {
/*  574 */     if (to != null) {
/*  575 */       if (from == null) {
/*      */ 
/*      */         
/*  578 */         to.setBlack(index);
/*      */       } else {
/*  580 */         to.copyColor(from, index);
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
/*      */   
/*      */   private static boolean isRed(Node node, int index) {
/*  594 */     return (node == null) ? false : node.isRed(index);
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
/*      */   private static boolean isBlack(Node node, int index) {
/*  608 */     return (node == null) ? true : node.isBlack(index);
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
/*      */   private static void makeRed(Node node, int index) {
/*  621 */     if (node != null) {
/*  622 */       node.setRed(index);
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
/*      */   private static void makeBlack(Node node, int index) {
/*  634 */     if (node != null) {
/*  635 */       node.setBlack(index);
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
/*  647 */     return getParent(getParent(node, index), index);
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
/*      */   private static Node getParent(Node node, int index) {
/*  659 */     return (node == null) ? null : node.getParent(index);
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
/*      */   private static Node getRightChild(Node node, int index) {
/*  673 */     return (node == null) ? null : node.getRight(index);
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
/*      */   private static Node getLeftChild(Node node, int index) {
/*  687 */     return (node == null) ? null : node.getLeft(index);
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
/*      */   private static boolean isLeftChild(Node node, int index) {
/*  705 */     return (node == null) ? true : ((node.getParent(index) == null) ? false : ((node == node.getParent(index).getLeft(index))));
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
/*      */   private static boolean isRightChild(Node node, int index) {
/*  725 */     return (node == null) ? true : ((node.getParent(index) == null) ? false : ((node == node.getParent(index).getRight(index))));
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
/*      */   private void rotateLeft(Node node, int index) {
/*  740 */     Node rightChild = node.getRight(index);
/*      */     
/*  742 */     node.setRight(rightChild.getLeft(index), index);
/*      */     
/*  744 */     if (rightChild.getLeft(index) != null) {
/*  745 */       rightChild.getLeft(index).setParent(node, index);
/*      */     }
/*      */     
/*  748 */     rightChild.setParent(node.getParent(index), index);
/*      */     
/*  750 */     if (node.getParent(index) == null) {
/*      */ 
/*      */       
/*  753 */       this.rootNode[index] = rightChild;
/*  754 */     } else if (node.getParent(index).getLeft(index) == node) {
/*  755 */       node.getParent(index).setLeft(rightChild, index);
/*      */     } else {
/*  757 */       node.getParent(index).setRight(rightChild, index);
/*      */     } 
/*      */     
/*  760 */     rightChild.setLeft(node, index);
/*  761 */     node.setParent(rightChild, index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void rotateRight(Node node, int index) {
/*  772 */     Node leftChild = node.getLeft(index);
/*      */     
/*  774 */     node.setLeft(leftChild.getRight(index), index);
/*      */     
/*  776 */     if (leftChild.getRight(index) != null) {
/*  777 */       leftChild.getRight(index).setParent(node, index);
/*      */     }
/*      */     
/*  780 */     leftChild.setParent(node.getParent(index), index);
/*      */     
/*  782 */     if (node.getParent(index) == null) {
/*      */ 
/*      */       
/*  785 */       this.rootNode[index] = leftChild;
/*  786 */     } else if (node.getParent(index).getRight(index) == node) {
/*  787 */       node.getParent(index).setRight(leftChild, index);
/*      */     } else {
/*  789 */       node.getParent(index).setLeft(leftChild, index);
/*      */     } 
/*      */     
/*  792 */     leftChild.setRight(node, index);
/*  793 */     node.setParent(leftChild, index);
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
/*      */   private void doRedBlackInsert(Node insertedNode, int index) {
/*  805 */     Node currentNode = insertedNode;
/*      */     
/*  807 */     makeRed(currentNode, index);
/*      */ 
/*      */     
/*  810 */     while (currentNode != null && currentNode != this.rootNode[index] && isRed(currentNode.getParent(index), index)) {
/*  811 */       if (isLeftChild(getParent(currentNode, index), index)) {
/*  812 */         Node node = getRightChild(getGrandParent(currentNode, index), index);
/*      */ 
/*      */         
/*  815 */         if (isRed(node, index)) {
/*  816 */           makeBlack(getParent(currentNode, index), index);
/*  817 */           makeBlack(node, index);
/*  818 */           makeRed(getGrandParent(currentNode, index), index);
/*      */           
/*  820 */           currentNode = getGrandParent(currentNode, index); continue;
/*      */         } 
/*  822 */         if (isRightChild(currentNode, index)) {
/*  823 */           currentNode = getParent(currentNode, index);
/*      */           
/*  825 */           rotateLeft(currentNode, index);
/*      */         } 
/*      */         
/*  828 */         makeBlack(getParent(currentNode, index), index);
/*  829 */         makeRed(getGrandParent(currentNode, index), index);
/*      */         
/*  831 */         if (getGrandParent(currentNode, index) != null) {
/*  832 */           rotateRight(getGrandParent(currentNode, index), index);
/*      */         }
/*      */ 
/*      */         
/*      */         continue;
/*      */       } 
/*      */       
/*  839 */       Node y = getLeftChild(getGrandParent(currentNode, index), index);
/*      */ 
/*      */       
/*  842 */       if (isRed(y, index)) {
/*  843 */         makeBlack(getParent(currentNode, index), index);
/*  844 */         makeBlack(y, index);
/*  845 */         makeRed(getGrandParent(currentNode, index), index);
/*      */         
/*  847 */         currentNode = getGrandParent(currentNode, index); continue;
/*      */       } 
/*  849 */       if (isLeftChild(currentNode, index)) {
/*  850 */         currentNode = getParent(currentNode, index);
/*      */         
/*  852 */         rotateRight(currentNode, index);
/*      */       } 
/*      */       
/*  855 */       makeBlack(getParent(currentNode, index), index);
/*  856 */       makeRed(getGrandParent(currentNode, index), index);
/*      */       
/*  858 */       if (getGrandParent(currentNode, index) != null) {
/*  859 */         rotateLeft(getGrandParent(currentNode, index), index);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  865 */     makeBlack(this.rootNode[index], index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void doRedBlackDelete(Node deletedNode) {
/*  876 */     for (int index = 0; index < 2; index++) {
/*      */ 
/*      */ 
/*      */       
/*  880 */       if (deletedNode.getLeft(index) != null && deletedNode.getRight(index) != null)
/*      */       {
/*  882 */         swapPosition(nextGreater(deletedNode, index), deletedNode, index);
/*      */       }
/*      */ 
/*      */       
/*  886 */       Node replacement = (deletedNode.getLeft(index) != null) ? deletedNode.getLeft(index) : deletedNode.getRight(index);
/*      */ 
/*      */ 
/*      */       
/*  890 */       if (replacement != null) {
/*  891 */         replacement.setParent(deletedNode.getParent(index), index);
/*      */         
/*  893 */         if (deletedNode.getParent(index) == null) {
/*  894 */           this.rootNode[index] = replacement;
/*  895 */         } else if (deletedNode == deletedNode.getParent(index).getLeft(index)) {
/*      */           
/*  897 */           deletedNode.getParent(index).setLeft(replacement, index);
/*      */         } else {
/*  899 */           deletedNode.getParent(index).setRight(replacement, index);
/*      */         } 
/*      */         
/*  902 */         deletedNode.setLeft(null, index);
/*  903 */         deletedNode.setRight(null, index);
/*  904 */         deletedNode.setParent(null, index);
/*      */         
/*  906 */         if (isBlack(deletedNode, index)) {
/*  907 */           doRedBlackDeleteFixup(replacement, index);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  912 */       else if (deletedNode.getParent(index) == null) {
/*      */ 
/*      */         
/*  915 */         this.rootNode[index] = null;
/*      */       }
/*      */       else {
/*      */         
/*  919 */         if (isBlack(deletedNode, index)) {
/*  920 */           doRedBlackDeleteFixup(deletedNode, index);
/*      */         }
/*      */         
/*  923 */         if (deletedNode.getParent(index) != null) {
/*  924 */           if (deletedNode == deletedNode.getParent(index).getLeft(index)) {
/*      */ 
/*      */             
/*  927 */             deletedNode.getParent(index).setLeft(null, index);
/*      */           } else {
/*  929 */             deletedNode.getParent(index).setRight(null, index);
/*      */           } 
/*      */ 
/*      */           
/*  933 */           deletedNode.setParent(null, index);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  939 */     shrink();
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
/*      */   private void doRedBlackDeleteFixup(Node replacementNode, int index) {
/*  954 */     Node currentNode = replacementNode;
/*      */ 
/*      */     
/*  957 */     while (currentNode != this.rootNode[index] && isBlack(currentNode, index)) {
/*  958 */       if (isLeftChild(currentNode, index)) {
/*  959 */         Node node = getRightChild(getParent(currentNode, index), index);
/*      */ 
/*      */         
/*  962 */         if (isRed(node, index)) {
/*  963 */           makeBlack(node, index);
/*  964 */           makeRed(getParent(currentNode, index), index);
/*  965 */           rotateLeft(getParent(currentNode, index), index);
/*      */           
/*  967 */           node = getRightChild(getParent(currentNode, index), index);
/*      */         } 
/*      */         
/*  970 */         if (isBlack(getLeftChild(node, index), index) && isBlack(getRightChild(node, index), index)) {
/*      */ 
/*      */           
/*  973 */           makeRed(node, index);
/*      */           
/*  975 */           currentNode = getParent(currentNode, index); continue;
/*      */         } 
/*  977 */         if (isBlack(getRightChild(node, index), index)) {
/*  978 */           makeBlack(getLeftChild(node, index), index);
/*  979 */           makeRed(node, index);
/*  980 */           rotateRight(node, index);
/*      */           
/*  982 */           node = getRightChild(getParent(currentNode, index), index);
/*      */         } 
/*      */ 
/*      */         
/*  986 */         copyColor(getParent(currentNode, index), node, index);
/*      */         
/*  988 */         makeBlack(getParent(currentNode, index), index);
/*  989 */         makeBlack(getRightChild(node, index), index);
/*  990 */         rotateLeft(getParent(currentNode, index), index);
/*      */         
/*  992 */         currentNode = this.rootNode[index];
/*      */         continue;
/*      */       } 
/*  995 */       Node siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       
/*  997 */       if (isRed(siblingNode, index)) {
/*  998 */         makeBlack(siblingNode, index);
/*  999 */         makeRed(getParent(currentNode, index), index);
/* 1000 */         rotateRight(getParent(currentNode, index), index);
/*      */         
/* 1002 */         siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       } 
/*      */       
/* 1005 */       if (isBlack(getRightChild(siblingNode, index), index) && isBlack(getLeftChild(siblingNode, index), index)) {
/*      */         
/* 1007 */         makeRed(siblingNode, index);
/*      */         
/* 1009 */         currentNode = getParent(currentNode, index); continue;
/*      */       } 
/* 1011 */       if (isBlack(getLeftChild(siblingNode, index), index)) {
/* 1012 */         makeBlack(getRightChild(siblingNode, index), index);
/* 1013 */         makeRed(siblingNode, index);
/* 1014 */         rotateLeft(siblingNode, index);
/*      */         
/* 1016 */         siblingNode = getLeftChild(getParent(currentNode, index), index);
/*      */       } 
/*      */ 
/*      */       
/* 1020 */       copyColor(getParent(currentNode, index), siblingNode, index);
/*      */       
/* 1022 */       makeBlack(getParent(currentNode, index), index);
/* 1023 */       makeBlack(getLeftChild(siblingNode, index), index);
/* 1024 */       rotateRight(getParent(currentNode, index), index);
/*      */       
/* 1026 */       currentNode = this.rootNode[index];
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1031 */     makeBlack(currentNode, index);
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
/*      */   private void swapPosition(Node x, Node y, int index) {
/* 1046 */     Node xFormerParent = x.getParent(index);
/* 1047 */     Node xFormerLeftChild = x.getLeft(index);
/* 1048 */     Node xFormerRightChild = x.getRight(index);
/* 1049 */     Node yFormerParent = y.getParent(index);
/* 1050 */     Node yFormerLeftChild = y.getLeft(index);
/* 1051 */     Node yFormerRightChild = y.getRight(index);
/* 1052 */     boolean xWasLeftChild = (x.getParent(index) != null && x == x.getParent(index).getLeft(index));
/*      */ 
/*      */     
/* 1055 */     boolean yWasLeftChild = (y.getParent(index) != null && y == y.getParent(index).getLeft(index));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1060 */     if (x == yFormerParent) {
/* 1061 */       x.setParent(y, index);
/*      */       
/* 1063 */       if (yWasLeftChild) {
/* 1064 */         y.setLeft(x, index);
/* 1065 */         y.setRight(xFormerRightChild, index);
/*      */       } else {
/* 1067 */         y.setRight(x, index);
/* 1068 */         y.setLeft(xFormerLeftChild, index);
/*      */       } 
/*      */     } else {
/* 1071 */       x.setParent(yFormerParent, index);
/*      */       
/* 1073 */       if (yFormerParent != null) {
/* 1074 */         if (yWasLeftChild) {
/* 1075 */           yFormerParent.setLeft(x, index);
/*      */         } else {
/* 1077 */           yFormerParent.setRight(x, index);
/*      */         } 
/*      */       }
/*      */       
/* 1081 */       y.setLeft(xFormerLeftChild, index);
/* 1082 */       y.setRight(xFormerRightChild, index);
/*      */     } 
/*      */     
/* 1085 */     if (y == xFormerParent) {
/* 1086 */       y.setParent(x, index);
/*      */       
/* 1088 */       if (xWasLeftChild) {
/* 1089 */         x.setLeft(y, index);
/* 1090 */         x.setRight(yFormerRightChild, index);
/*      */       } else {
/* 1092 */         x.setRight(y, index);
/* 1093 */         x.setLeft(yFormerLeftChild, index);
/*      */       } 
/*      */     } else {
/* 1096 */       y.setParent(xFormerParent, index);
/*      */       
/* 1098 */       if (xFormerParent != null) {
/* 1099 */         if (xWasLeftChild) {
/* 1100 */           xFormerParent.setLeft(y, index);
/*      */         } else {
/* 1102 */           xFormerParent.setRight(y, index);
/*      */         } 
/*      */       }
/*      */       
/* 1106 */       x.setLeft(yFormerLeftChild, index);
/* 1107 */       x.setRight(yFormerRightChild, index);
/*      */     } 
/*      */ 
/*      */     
/* 1111 */     if (x.getLeft(index) != null) {
/* 1112 */       x.getLeft(index).setParent(x, index);
/*      */     }
/*      */     
/* 1115 */     if (x.getRight(index) != null) {
/* 1116 */       x.getRight(index).setParent(x, index);
/*      */     }
/*      */     
/* 1119 */     if (y.getLeft(index) != null) {
/* 1120 */       y.getLeft(index).setParent(y, index);
/*      */     }
/*      */     
/* 1123 */     if (y.getRight(index) != null) {
/* 1124 */       y.getRight(index).setParent(y, index);
/*      */     }
/*      */     
/* 1127 */     x.swapColors(y, index);
/*      */ 
/*      */     
/* 1130 */     if (this.rootNode[index] == x) {
/* 1131 */       this.rootNode[index] = y;
/* 1132 */     } else if (this.rootNode[index] == y) {
/* 1133 */       this.rootNode[index] = x;
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
/*      */ 
/*      */   
/*      */   private static void checkNonNullComparable(Object o, int index) {
/* 1151 */     if (o == null) {
/* 1152 */       throw new NullPointerException(dataName[index] + " cannot be null");
/*      */     }
/*      */ 
/*      */     
/* 1156 */     if (!(o instanceof Comparable)) {
/* 1157 */       throw new ClassCastException(dataName[index] + " must be Comparable");
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
/*      */   private static void checkKey(Object key) {
/* 1171 */     checkNonNullComparable(key, 0);
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
/* 1183 */     checkNonNullComparable(value, 1);
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
/*      */   private static void checkKeyAndValue(Object key, Object value) {
/* 1198 */     checkKey(key);
/* 1199 */     checkValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void modify() {
/* 1208 */     this.modifications++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void grow() {
/* 1216 */     modify();
/*      */     
/* 1218 */     this.nodeCount++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void shrink() {
/* 1226 */     modify();
/*      */     
/* 1228 */     this.nodeCount--;
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
/*      */   private void insertValue(Node newNode) throws IllegalArgumentException {
/* 1242 */     Node node = this.rootNode[1];
/*      */     
/*      */     while (true) {
/* 1245 */       int cmp = compare(newNode.getData(1), node.getData(1));
/*      */       
/* 1247 */       if (cmp == 0) {
/* 1248 */         throw new IllegalArgumentException("Cannot store a duplicate value (\"" + newNode.getData(1) + "\") in this Map");
/*      */       }
/*      */       
/* 1251 */       if (cmp < 0) {
/* 1252 */         if (node.getLeft(1) != null) {
/* 1253 */           node = node.getLeft(1); continue;
/*      */         } 
/* 1255 */         node.setLeft(newNode, 1);
/* 1256 */         newNode.setParent(node, 1);
/* 1257 */         doRedBlackInsert(newNode, 1);
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/* 1262 */       if (node.getRight(1) != null) {
/* 1263 */         node = node.getRight(1); continue;
/*      */       } 
/* 1265 */       node.setRight(newNode, 1);
/* 1266 */       newNode.setParent(node, 1);
/* 1267 */       doRedBlackInsert(newNode, 1);
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
/*      */   
/*      */   public int size() {
/* 1285 */     return this.nodeCount;
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
/*      */   public boolean containsKey(Object key) throws ClassCastException, NullPointerException {
/* 1304 */     checkKey(key);
/*      */     
/* 1306 */     return (lookup((Comparable)key, 0) != null);
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
/* 1320 */     checkValue(value);
/*      */     
/* 1322 */     return (lookup((Comparable)value, 1) != null);
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
/*      */   public Object get(Object key) throws ClassCastException, NullPointerException {
/* 1340 */     return doGet((Comparable)key, 0);
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
/*      */   public Object put(Object key, Object value) throws ClassCastException, NullPointerException, IllegalArgumentException {
/* 1367 */     checkKeyAndValue(key, value);
/*      */     
/* 1369 */     Node node = this.rootNode[0];
/*      */     
/* 1371 */     if (node == null) {
/* 1372 */       Node root = new Node((Comparable)key, (Comparable)value);
/*      */       
/* 1374 */       this.rootNode[0] = root;
/* 1375 */       this.rootNode[1] = root;
/*      */       
/* 1377 */       grow();
/*      */     } else {
/*      */       while (true) {
/* 1380 */         int cmp = compare((Comparable)key, node.getData(0));
/*      */         
/* 1382 */         if (cmp == 0) {
/* 1383 */           throw new IllegalArgumentException("Cannot store a duplicate key (\"" + key + "\") in this Map");
/*      */         }
/*      */         
/* 1386 */         if (cmp < 0) {
/* 1387 */           if (node.getLeft(0) != null) {
/* 1388 */             node = node.getLeft(0); continue;
/*      */           } 
/* 1390 */           Node node1 = new Node((Comparable)key, (Comparable)value);
/*      */ 
/*      */           
/* 1393 */           insertValue(node1);
/* 1394 */           node.setLeft(node1, 0);
/* 1395 */           node1.setParent(node, 0);
/* 1396 */           doRedBlackInsert(node1, 0);
/* 1397 */           grow();
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/* 1402 */         if (node.getRight(0) != null) {
/* 1403 */           node = node.getRight(0); continue;
/*      */         } 
/* 1405 */         Node newNode = new Node((Comparable)key, (Comparable)value);
/*      */ 
/*      */         
/* 1408 */         insertValue(newNode);
/* 1409 */         node.setRight(newNode, 0);
/* 1410 */         newNode.setParent(node, 0);
/* 1411 */         doRedBlackInsert(newNode, 0);
/* 1412 */         grow();
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1420 */     return null;
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
/*      */   public Object remove(Object key) {
/* 1432 */     return doRemove((Comparable)key, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/* 1440 */     modify();
/*      */     
/* 1442 */     this.nodeCount = 0;
/* 1443 */     this.rootNode[0] = null;
/* 1444 */     this.rootNode[1] = null;
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
/* 1461 */     if (this.setOfKeys[0] == null) {
/* 1462 */       this.setOfKeys[0] = new AbstractSet(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/* 1466 */             return (Iterator)new Object(this, 0);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public int size() {
/* 1475 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/* 1479 */             return this.this$0.containsKey(o);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean remove(Object o) {
/* 1484 */             int oldNodeCount = this.this$0.nodeCount;
/*      */             
/* 1486 */             this.this$0.remove(o);
/*      */             
/* 1488 */             return (this.this$0.nodeCount != oldNodeCount);
/*      */           }
/*      */           
/*      */           public void clear() {
/* 1492 */             this.this$0.clear();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/* 1497 */     return this.setOfKeys[0];
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
/* 1515 */     if (this.collectionOfValues[0] == null) {
/* 1516 */       this.collectionOfValues[0] = new AbstractCollection(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/* 1520 */             return (Iterator)new Object(this, 0);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public int size() {
/* 1529 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public boolean contains(Object o) {
/* 1533 */             return this.this$0.containsValue(o);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean remove(Object o) {
/* 1538 */             int oldNodeCount = this.this$0.nodeCount;
/*      */             
/* 1540 */             this.this$0.removeValue(o);
/*      */             
/* 1542 */             return (this.this$0.nodeCount != oldNodeCount);
/*      */           }
/*      */ 
/*      */           
/*      */           public boolean removeAll(Collection c) {
/* 1547 */             boolean modified = false;
/* 1548 */             Iterator iter = c.iterator();
/*      */             
/* 1550 */             while (iter.hasNext()) {
/* 1551 */               if (this.this$0.removeValue(iter.next()) != null) {
/* 1552 */                 modified = true;
/*      */               }
/*      */             } 
/*      */             
/* 1556 */             return modified;
/*      */           }
/*      */           
/*      */           public void clear() {
/* 1560 */             this.this$0.clear();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/* 1565 */     return this.collectionOfValues[0];
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
/*      */   public Set entrySet() {
/* 1586 */     if (this.setOfEntries[0] == null) {
/* 1587 */       this.setOfEntries[0] = new AbstractSet(this) {
/*      */           private final DoubleOrderedMap this$0;
/*      */           
/*      */           public Iterator iterator() {
/* 1591 */             return (Iterator)new Object(this, 0);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean contains(Object o) {
/* 1601 */             if (!(o instanceof Map.Entry)) {
/* 1602 */               return false;
/*      */             }
/*      */             
/* 1605 */             Map.Entry entry = (Map.Entry)o;
/* 1606 */             Object value = entry.getValue();
/* 1607 */             DoubleOrderedMap.Node node = this.this$0.lookup((Comparable)entry.getKey(), 0);
/*      */ 
/*      */             
/* 1610 */             return (node != null && node.getData(1).equals(value));
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public boolean remove(Object o) {
/* 1616 */             if (!(o instanceof Map.Entry)) {
/* 1617 */               return false;
/*      */             }
/*      */             
/* 1620 */             Map.Entry entry = (Map.Entry)o;
/* 1621 */             Object value = entry.getValue();
/* 1622 */             DoubleOrderedMap.Node node = this.this$0.lookup((Comparable)entry.getKey(), 0);
/*      */ 
/*      */             
/* 1625 */             if (node != null && node.getData(1).equals(value)) {
/* 1626 */               this.this$0.doRedBlackDelete(node);
/*      */               
/* 1628 */               return true;
/*      */             } 
/*      */             
/* 1631 */             return false;
/*      */           }
/*      */           
/*      */           public int size() {
/* 1635 */             return this.this$0.size();
/*      */           }
/*      */           
/*      */           public void clear() {
/* 1639 */             this.this$0.clear();
/*      */           }
/*      */         };
/*      */     }
/*      */     
/* 1644 */     return this.setOfEntries[0];
/*      */   }
/*      */ 
/*      */   
/*      */   public DoubleOrderedMap() {}
/*      */   
/*      */   private abstract class DoubleOrderedMapIterator
/*      */     implements Iterator
/*      */   {
/*      */     private int expectedModifications;
/*      */     protected DoubleOrderedMap.Node lastReturnedNode;
/*      */     private DoubleOrderedMap.Node nextNode;
/*      */     private int iteratorType;
/*      */     private final DoubleOrderedMap this$0;
/*      */     
/*      */     DoubleOrderedMapIterator(DoubleOrderedMap this$0, int type) {
/* 1660 */       this.this$0 = this$0;
/*      */       
/* 1662 */       this.iteratorType = type;
/* 1663 */       this.expectedModifications = this$0.modifications;
/* 1664 */       this.lastReturnedNode = null;
/* 1665 */       this.nextNode = DoubleOrderedMap.leastNode(this$0.rootNode[this.iteratorType], this.iteratorType);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected abstract Object doGetNext();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final boolean hasNext() {
/* 1681 */       return (this.nextNode != null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final Object next() throws NoSuchElementException, ConcurrentModificationException {
/* 1699 */       if (this.nextNode == null) {
/* 1700 */         throw new NoSuchElementException();
/*      */       }
/*      */       
/* 1703 */       if (this.this$0.modifications != this.expectedModifications) {
/* 1704 */         throw new ConcurrentModificationException();
/*      */       }
/*      */       
/* 1707 */       this.lastReturnedNode = this.nextNode;
/* 1708 */       this.nextNode = this.this$0.nextGreater(this.nextNode, this.iteratorType);
/*      */       
/* 1710 */       return doGetNext();
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
/*      */     public final void remove() throws IllegalStateException, ConcurrentModificationException {
/* 1736 */       if (this.lastReturnedNode == null) {
/* 1737 */         throw new IllegalStateException();
/*      */       }
/*      */       
/* 1740 */       if (this.this$0.modifications != this.expectedModifications) {
/* 1741 */         throw new ConcurrentModificationException();
/*      */       }
/*      */       
/* 1744 */       this.this$0.doRedBlackDelete(this.lastReturnedNode);
/*      */       
/* 1746 */       this.expectedModifications++;
/*      */       
/* 1748 */       this.lastReturnedNode = null;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class Node
/*      */     implements Map.Entry, KeyValue
/*      */   {
/*      */     private Comparable[] data;
/*      */ 
/*      */     
/*      */     private Node[] leftNode;
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
/* 1774 */       this.data = new Comparable[] { key, value };
/* 1775 */       this.leftNode = new Node[] { null, null };
/* 1776 */       this.rightNode = new Node[] { null, null };
/* 1777 */       this.parentNode = new Node[] { null, null };
/* 1778 */       this.blackColor = new boolean[] { true, true };
/* 1779 */       this.calculatedHashCode = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Comparable getData(int index) {
/* 1790 */       return this.data[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setLeft(Node node, int index) {
/* 1800 */       this.leftNode[index] = node;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Node getLeft(int index) {
/* 1811 */       return this.leftNode[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setRight(Node node, int index) {
/* 1821 */       this.rightNode[index] = node;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Node getRight(int index) {
/* 1832 */       return this.rightNode[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setParent(Node node, int index) {
/* 1842 */       this.parentNode[index] = node;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Node getParent(int index) {
/* 1853 */       return this.parentNode[index];
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
/*      */     private void swapColors(Node node, int index) {
/* 1865 */       this.blackColor[index] = this.blackColor[index] ^ node.blackColor[index];
/* 1866 */       node.blackColor[index] = node.blackColor[index] ^ this.blackColor[index];
/* 1867 */       this.blackColor[index] = this.blackColor[index] ^ node.blackColor[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean isBlack(int index) {
/* 1878 */       return this.blackColor[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean isRed(int index) {
/* 1889 */       return !this.blackColor[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setBlack(int index) {
/* 1898 */       this.blackColor[index] = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setRed(int index) {
/* 1907 */       this.blackColor[index] = false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void copyColor(Node node, int index) {
/* 1917 */       this.blackColor[index] = node.blackColor[index];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getKey() {
/* 1926 */       return this.data[0];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getValue() {
/* 1933 */       return this.data[1];
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
/*      */ 
/*      */     
/*      */     public Object setValue(Object ignored) throws UnsupportedOperationException {
/* 1948 */       throw new UnsupportedOperationException("Map.Entry.setValue is not supported");
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
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean equals(Object o) {
/* 1964 */       if (this == o) {
/* 1965 */         return true;
/*      */       }
/*      */       
/* 1968 */       if (!(o instanceof Map.Entry)) {
/* 1969 */         return false;
/*      */       }
/*      */       
/* 1972 */       Map.Entry e = (Map.Entry)o;
/*      */       
/* 1974 */       return (this.data[0].equals(e.getKey()) && this.data[1].equals(e.getValue()));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int hashCode() {
/* 1983 */       if (!this.calculatedHashCode) {
/* 1984 */         this.hashcodeValue = this.data[0].hashCode() ^ this.data[1].hashCode();
/*      */         
/* 1986 */         this.calculatedHashCode = true;
/*      */       } 
/*      */       
/* 1989 */       return this.hashcodeValue;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/DoubleOrderedMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */