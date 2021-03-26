/*    */ package org.apache.pdfbox.contentstream.operator.graphics;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSNumber;
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
/*    */ public final class MoveTo
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 39 */     if (operands.size() < 2)
/*    */     {
/* 41 */       throw new MissingOperandException(operator, operands);
/*    */     }
/* 43 */     COSBase base0 = operands.get(0);
/* 44 */     if (!(base0 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 48 */     COSBase base1 = operands.get(1);
/* 49 */     if (!(base1 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 53 */     COSNumber x = (COSNumber)base0;
/* 54 */     COSNumber y = (COSNumber)base1;
/* 55 */     Point2D.Float pos = this.context.transformedPoint(x.floatValue(), y.floatValue());
/* 56 */     this.context.moveTo(pos.x, pos.y);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 62 */     return "m";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/MoveTo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */