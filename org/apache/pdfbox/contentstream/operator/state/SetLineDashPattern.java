/*    */ package org.apache.pdfbox.contentstream.operator.state;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSArray;
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
/*    */ public class SetLineDashPattern
/*    */   extends OperatorProcessor
/*    */ {
/* 39 */   private static final Log LOG = LogFactory.getLog(SetLineDashPattern.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void process(Operator operator, List<COSBase> arguments) throws MissingOperandException {
/* 44 */     if (arguments.size() < 2)
/*    */     {
/* 46 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 48 */     COSBase base0 = arguments.get(0);
/* 49 */     if (!(base0 instanceof COSArray)) {
/*    */       return;
/*    */     }
/*    */     
/* 53 */     COSBase base1 = arguments.get(1);
/* 54 */     if (!(base1 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 58 */     COSArray dashArray = (COSArray)base0;
/* 59 */     int dashPhase = ((COSNumber)base1).intValue();
/*    */     
/* 61 */     boolean allZero = true;
/* 62 */     for (COSBase base : dashArray) {
/*    */       
/* 64 */       if (base instanceof COSNumber) {
/*    */         
/* 66 */         COSNumber num = (COSNumber)base;
/* 67 */         if (num.floatValue() != 0.0F) {
/*    */           
/* 69 */           allZero = false;
/*    */           
/*    */           break;
/*    */         } 
/*    */         continue;
/*    */       } 
/* 75 */       LOG.warn("dash array has non number element " + base + ", ignored");
/* 76 */       dashArray = new COSArray();
/*    */     } 
/*    */ 
/*    */     
/* 80 */     if (dashArray.size() > 0 && allZero) {
/*    */       
/* 82 */       LOG.warn("dash lengths all zero, ignored");
/* 83 */       dashArray = new COSArray();
/*    */     } 
/* 85 */     this.context.setLineDashPattern(dashArray, dashPhase);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 91 */     return "d";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/state/SetLineDashPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */