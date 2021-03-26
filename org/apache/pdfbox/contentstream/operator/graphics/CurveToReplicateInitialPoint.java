/*    */ package org.apache.pdfbox.contentstream.operator.graphics;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
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
/*    */ public class CurveToReplicateInitialPoint
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/* 38 */   private static final Log LOG = LogFactory.getLog(CurveToReplicateInitialPoint.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 43 */     if (operands.size() < 4)
/*    */     {
/* 45 */       throw new MissingOperandException(operator, operands);
/*    */     }
/* 47 */     if (!checkArrayTypesClass(operands, COSNumber.class)) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     COSNumber x2 = (COSNumber)operands.get(0);
/* 52 */     COSNumber y2 = (COSNumber)operands.get(1);
/* 53 */     COSNumber x3 = (COSNumber)operands.get(2);
/* 54 */     COSNumber y3 = (COSNumber)operands.get(3);
/*    */     
/* 56 */     Point2D currentPoint = this.context.getCurrentPoint();
/*    */     
/* 58 */     Point2D.Float point2 = this.context.transformedPoint(x2.floatValue(), y2.floatValue());
/* 59 */     Point2D.Float point3 = this.context.transformedPoint(x3.floatValue(), y3.floatValue());
/*    */     
/* 61 */     if (currentPoint == null) {
/*    */       
/* 63 */       LOG.warn("curveTo (" + point3.x + "," + point3.y + ") without initial MoveTo");
/* 64 */       this.context.moveTo(point3.x, point3.y);
/*    */     }
/*    */     else {
/*    */       
/* 68 */       this.context.curveTo((float)currentPoint.getX(), (float)currentPoint.getY(), point2.x, point2.y, point3.x, point3.y);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 77 */     return "v";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/CurveToReplicateInitialPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */