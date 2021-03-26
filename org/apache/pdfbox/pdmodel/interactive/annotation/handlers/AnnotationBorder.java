/*    */ package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSArray;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSNumber;
/*    */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*    */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
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
/*    */ class AnnotationBorder
/*    */ {
/* 32 */   float[] dashArray = null;
/*    */   boolean underline = false;
/* 34 */   float width = 0.0F;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static AnnotationBorder getAnnotationBorder(PDAnnotation annotation, PDBorderStyleDictionary borderStyle) {
/* 41 */     AnnotationBorder ab = new AnnotationBorder();
/* 42 */     if (borderStyle == null) {
/*    */       
/* 44 */       COSArray border = annotation.getBorder();
/* 45 */       if (border.size() >= 3 && border.getObject(2) instanceof COSNumber)
/*    */       {
/* 47 */         ab.width = ((COSNumber)border.getObject(2)).floatValue();
/*    */       }
/* 49 */       if (border.size() > 3)
/*    */       {
/* 51 */         COSBase base3 = border.getObject(3);
/* 52 */         if (base3 instanceof COSArray)
/*    */         {
/* 54 */           ab.dashArray = ((COSArray)base3).toFloatArray();
/*    */         }
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 60 */       ab.width = borderStyle.getWidth();
/* 61 */       if (borderStyle.getStyle().equals("D"))
/*    */       {
/* 63 */         ab.dashArray = borderStyle.getDashStyle().getDashArray();
/*    */       }
/* 65 */       if (borderStyle.getStyle().equals("U"))
/*    */       {
/* 67 */         ab.underline = true;
/*    */       }
/*    */     } 
/* 70 */     if (ab.dashArray != null) {
/*    */       
/* 72 */       boolean allZero = true;
/* 73 */       for (float f : ab.dashArray) {
/*    */         
/* 75 */         if (Float.compare(f, 0.0F) != 0) {
/*    */           
/* 77 */           allZero = false;
/*    */           break;
/*    */         } 
/*    */       } 
/* 81 */       if (allZero)
/*    */       {
/* 83 */         ab.dashArray = null;
/*    */       }
/*    */     } 
/* 86 */     return ab;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/handlers/AnnotationBorder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */