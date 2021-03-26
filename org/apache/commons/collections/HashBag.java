/*    */ package org.apache.commons.collections;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
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
/*    */   extends DefaultMapBag
/*    */   implements Bag
/*    */ {
/*    */   public HashBag() {
/* 36 */     super(new HashMap());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HashBag(Collection coll) {
/* 46 */     this();
/* 47 */     addAll(coll);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/HashBag.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */