/*    */ package org.apache.batik.util.gui.resource;
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
/*    */ public class MissingListenerException
/*    */   extends RuntimeException
/*    */ {
/*    */   private String className;
/*    */   private String key;
/*    */   
/*    */   public MissingListenerException(String s, String className, String key) {
/* 48 */     super(s);
/* 49 */     this.className = className;
/* 50 */     this.key = key;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 57 */     return this.className;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getKey() {
/* 64 */     return this.key;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     return super.toString() + " (" + getKey() + ", bundle: " + getClassName() + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/resource/MissingListenerException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */