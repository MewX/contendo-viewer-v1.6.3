/*    */ package org.apache.http.entity.mime;
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
/*    */ public class MinimalField
/*    */ {
/*    */   private final String name;
/*    */   private final String value;
/*    */   
/*    */   public MinimalField(String name, String value) {
/* 42 */     this.name = name;
/* 43 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 47 */     return this.name;
/*    */   }
/*    */   
/*    */   public String getBody() {
/* 51 */     return this.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     StringBuilder buffer = new StringBuilder();
/* 57 */     buffer.append(this.name);
/* 58 */     buffer.append(": ");
/* 59 */     buffer.append(this.value);
/* 60 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/MinimalField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */