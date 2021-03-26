/*    */ package org.apache.pdfbox.pdmodel;
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
/*    */ public enum PageMode
/*    */ {
/* 28 */   USE_NONE("UseNone"),
/*    */ 
/*    */   
/* 31 */   USE_OUTLINES("UseOutlines"),
/*    */ 
/*    */   
/* 34 */   USE_THUMBS("UseThumbs"),
/*    */ 
/*    */   
/* 37 */   FULL_SCREEN("FullScreen"),
/*    */ 
/*    */   
/* 40 */   USE_OPTIONAL_CONTENT("UseOC"),
/*    */ 
/*    */   
/* 43 */   USE_ATTACHMENTS("UseAttachments");
/*    */   private final String value;
/*    */   
/*    */   public static PageMode fromString(String value) {
/* 47 */     for (PageMode instance : values()) {
/*    */       
/* 49 */       if (instance.value.equals(value))
/*    */       {
/* 51 */         return instance;
/*    */       }
/*    */     } 
/* 54 */     throw new IllegalArgumentException(value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   PageMode(String value) {
/* 61 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String stringValue() {
/* 71 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PageMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */