/*    */ package org.apache.commons.collections.comparators;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections.Transformer;
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
/*    */ public class TransformingComparator
/*    */   implements Comparator
/*    */ {
/*    */   protected Comparator decorated;
/*    */   protected Transformer transformer;
/*    */   
/*    */   public TransformingComparator(Transformer transformer) {
/* 48 */     this(transformer, new ComparableComparator());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TransformingComparator(Transformer transformer, Comparator decorated) {
/* 58 */     this.decorated = decorated;
/* 59 */     this.transformer = transformer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int compare(Object obj1, Object obj2) {
/* 71 */     Object value1 = this.transformer.transform(obj1);
/* 72 */     Object value2 = this.transformer.transform(obj2);
/* 73 */     return this.decorated.compare(value1, value2);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/comparators/TransformingComparator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */