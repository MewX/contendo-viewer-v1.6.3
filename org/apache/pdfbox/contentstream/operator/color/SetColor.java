/*    */ package org.apache.pdfbox.contentstream.operator.color;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSArray;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSNumber;
/*    */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*    */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
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
/*    */ public abstract class SetColor
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 42 */     PDColorSpace colorSpace = getColorSpace();
/* 43 */     if (!(colorSpace instanceof org.apache.pdfbox.pdmodel.graphics.color.PDPattern)) {
/*    */       
/* 45 */       if (arguments.size() < colorSpace.getNumberOfComponents())
/*    */       {
/* 47 */         throw new MissingOperandException(operator, arguments);
/*    */       }
/* 49 */       if (!checkArrayTypesClass(arguments, COSNumber.class)) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */     
/* 54 */     COSArray array = new COSArray();
/* 55 */     array.addAll(arguments);
/* 56 */     setColor(new PDColor(array, colorSpace));
/*    */   }
/*    */   
/*    */   protected abstract PDColor getColor();
/*    */   
/*    */   protected abstract void setColor(PDColor paramPDColor);
/*    */   
/*    */   protected abstract PDColorSpace getColorSpace();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/color/SetColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */