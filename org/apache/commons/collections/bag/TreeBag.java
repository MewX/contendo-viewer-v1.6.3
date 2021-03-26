/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.collections.SortedBag;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeBag
/*     */   extends AbstractMapBag
/*     */   implements Serializable, SortedBag
/*     */ {
/*     */   static final long serialVersionUID = -7740146511091606676L;
/*     */   
/*     */   public TreeBag() {
/*  58 */     super(new TreeMap());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeBag(Comparator comparator) {
/*  68 */     super(new TreeMap(comparator));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeBag(Collection coll) {
/*  78 */     this();
/*  79 */     addAll(coll);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object first() {
/*  84 */     return ((SortedMap)getMap()).firstKey();
/*     */   }
/*     */   
/*     */   public Object last() {
/*  88 */     return ((SortedMap)getMap()).lastKey();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  92 */     return ((SortedMap)getMap()).comparator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 100 */     out.defaultWriteObject();
/* 101 */     out.writeObject(comparator());
/* 102 */     doWriteObject(out);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 109 */     in.defaultReadObject();
/* 110 */     Comparator comp = (Comparator)in.readObject();
/* 111 */     doReadObject(new TreeMap(comp), in);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/TreeBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */