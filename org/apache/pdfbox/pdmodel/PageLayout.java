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
/*    */ public enum PageLayout
/*    */ {
/* 28 */   SINGLE_PAGE("SinglePage"),
/*    */ 
/*    */   
/* 31 */   ONE_COLUMN("OneColumn"),
/*    */ 
/*    */   
/* 34 */   TWO_COLUMN_LEFT("TwoColumnLeft"),
/*    */ 
/*    */   
/* 37 */   TWO_COLUMN_RIGHT("TwoColumnRight"),
/*    */ 
/*    */   
/* 40 */   TWO_PAGE_LEFT("TwoPageLeft"),
/*    */ 
/*    */   
/* 43 */   TWO_PAGE_RIGHT("TwoPageRight");
/*    */   private final String value;
/*    */   
/*    */   public static PageLayout fromString(String value) {
/* 47 */     for (PageLayout instance : values()) {
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
/*    */   PageLayout(String value) {
/* 61 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String stringValue() {
/* 69 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PageLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */