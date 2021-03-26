/*    */ package org.apache.commons.collections;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Comparator;
/*    */ import java.util.SortedMap;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreeBag
/*    */   extends DefaultMapBag
/*    */   implements SortedBag
/*    */ {
/*    */   public TreeBag() {
/* 40 */     super(new TreeMap());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TreeBag(Comparator comparator) {
/* 50 */     super(new TreeMap(comparator));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TreeBag(Collection coll) {
/* 60 */     this();
/* 61 */     addAll(coll);
/*    */   }
/*    */   
/*    */   public Object first() {
/* 65 */     return ((SortedMap)getMap()).firstKey();
/*    */   }
/*    */   
/*    */   public Object last() {
/* 69 */     return ((SortedMap)getMap()).lastKey();
/*    */   }
/*    */   
/*    */   public Comparator comparator() {
/* 73 */     return ((SortedMap)getMap()).comparator();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/TreeBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */