/*    */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*    */ 
/*    */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
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
/*    */ public class PDSoundAppearanceHandler
/*    */   extends PDAbstractAppearanceHandler
/*    */ {
/*    */   public PDSoundAppearanceHandler(PDAnnotation annotation) {
/* 24 */     super(annotation);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void generateAppearanceStreams() {
/* 30 */     generateNormalAppearance();
/* 31 */     generateRolloverAppearance();
/* 32 */     generateDownAppearance();
/*    */   }
/*    */   
/*    */   public void generateNormalAppearance() {}
/*    */   
/*    */   public void generateRolloverAppearance() {}
/*    */   
/*    */   public void generateDownAppearance() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDSoundAppearanceHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */