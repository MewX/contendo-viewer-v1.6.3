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
/*    */ public final class CurveToReplicateFinalPoint
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
/* 47 */     COSNumber x1 = (COSNumber)operands.get(0);
/* 48 */     COSNumber y1 = (COSNumber)operands.get(1);
/* 49 */     COSNumber x3 = (COSNumber)operands.get(2);
/* 50 */     COSNumber y3 = (COSNumber)operands.get(3);
/*    */     
/* 52 */     Point2D.Float point1 = this.context.transformedPoint(x1.floatValue(), y1.floatValue());
/* 53 */     Point2D.Float point3 = this.context.transformedPoint(x3.floatValue(), y3.floatValue());
/*    */     
/* 55 */     this.context.curveTo(point1.x, point1.y, point3.x, point3.y, point3.x, point3.y);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 63 */     return "y";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/CurveToReplicateFinalPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */