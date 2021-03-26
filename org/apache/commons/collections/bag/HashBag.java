/*    */ package org.apache.commons.collections.bag;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import org.apache.commons.collections.Bag;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HashBag
/*    */   extends AbstractMapBag
/*    */   implements Serializable, Bag
/*    */ {
/*    */   static final long serialVersionUID = -6561115435802554013L;
/*    */   
/*    */   public HashBag() {
/* 53 */     super(new HashMap());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HashBag(Collection coll) {
/* 62 */     this();
/* 63 */     addAll(coll);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 71 */     out.defaultWriteObject();
/* 72 */     doWriteObject(out);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 79 */     in.defaultReadObject();
/* 80 */     doReadObject(new HashMap(), in);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bag/HashBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */