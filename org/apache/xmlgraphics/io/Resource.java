/*    */ package org.apache.xmlgraphics.io;
/*    */ 
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.InputStream;
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
/*    */ public class Resource
/*    */   extends FilterInputStream
/*    */ {
/*    */   private final String type;
/*    */   
/*    */   public Resource(String type, InputStream inputStream) {
/* 38 */     super(inputStream);
/* 39 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Resource(InputStream inputStream) {
/* 48 */     this("unknown", inputStream);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getType() {
/* 55 */     return this.type;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/io/Resource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */