/*    */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSArray;
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
/*    */ public class PDPageFitDestination
/*    */   extends PDPageDestination
/*    */ {
/*    */   protected static final String TYPE = "Fit";
/*    */   protected static final String TYPE_BOUNDED = "FitB";
/*    */   
/*    */   public PDPageFitDestination() {
/* 45 */     this.array.growToSize(2);
/* 46 */     this.array.setName(1, "Fit");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDPageFitDestination(COSArray arr) {
/* 57 */     super(arr);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean fitBoundingBox() {
/* 67 */     return "FitB".equals(this.array.getName(1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFitBoundingBox(boolean fitBoundingBox) {
/* 77 */     this.array.growToSize(2);
/* 78 */     if (fitBoundingBox) {
/*    */       
/* 80 */       this.array.setName(1, "FitB");
/*    */     }
/*    */     else {
/*    */       
/* 84 */       this.array.setName(1, "Fit");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/destination/PDPageFitDestination.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */