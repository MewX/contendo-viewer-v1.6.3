/*    */ package org.apache.batik.util.resources;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResourceFormatException
/*    */   extends RuntimeException
/*    */ {
/*    */   protected String className;
/*    */   protected String key;
/*    */   
/*    */   public ResourceFormatException(String s, String className, String key) {
/* 49 */     super(s);
/* 50 */     this.className = className;
/* 51 */     this.key = key;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 58 */     return this.className;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 65 */     return this.key;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 72 */     return super.toString() + " (" + getKey() + ", bundle: " + getClassName() + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/resources/ResourceFormatException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */