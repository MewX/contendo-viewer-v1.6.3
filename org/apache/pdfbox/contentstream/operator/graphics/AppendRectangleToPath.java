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
/*    */ public final class AppendRectangleToPath
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 39 */     if (operands.size() < 4)
/*    */     {
/* 41 */       throw new MissingOperandException(operator, operands);
/*    */     }
/* 43 */     if (!checkArrayTypesClass(operands, COSNumber.class)) {
/*    */       return;
/*    */     }
/*    */     
/* 47 */     COSNumber x = (COSNumber)operands.get(0);
/* 48 */     COSNumber y = (COSNumber)operands.get(1);
/* 49 */     COSNumber w = (COSNumber)operands.get(2);
/* 50 */     COSNumber h = (COSNumber)operands.get(3);
/*    */     
/* 52 */     float x1 = x.floatValue();
/* 53 */     float y1 = y.floatValue();
/*    */ 
/*    */     
/* 56 */     float x2 = w.floatValue() + x1;
/* 57 */     float y2 = h.floatValue() + y1;
/*    */     
/* 59 */     Point2D p0 = this.context.transformedPoint(x1, y1);
/* 60 */     Point2D p1 = this.context.transformedPoint(x2, y1);
/* 61 */     Point2D p2 = this.context.transformedPoint(x2, y2);
/* 62 */     Point2D p3 = this.context.transformedPoint(x1, y2);
/*    */     
/* 64 */     this.context.appendRectangle(p0, p1, p2, p3);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 70 */     return "re";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/AppendRectangleToPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */