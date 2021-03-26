/*    */ package org.apache.xmlgraphics.xmp;
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
/*    */ public final class XMPArrayType
/*    */ {
/* 26 */   public static final XMPArrayType BAG = new XMPArrayType("Bag");
/*    */   
/* 28 */   public static final XMPArrayType SEQ = new XMPArrayType("Seq");
/*    */   
/* 30 */   public static final XMPArrayType ALT = new XMPArrayType("Alt");
/*    */ 
/*    */ 
/*    */   
/*    */   private String name;
/*    */ 
/*    */ 
/*    */   
/*    */   private XMPArrayType(String name) {
/* 39 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 44 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     return "rdf:" + this.name;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPArrayType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */