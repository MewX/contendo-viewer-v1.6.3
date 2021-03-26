/*    */ package org.apache.pdfbox.contentstream.operator.color;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
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
/*    */ 
/*    */ public class SetNonStrokingDeviceCMYKColor
/*    */   extends SetNonStrokingColor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 39 */     PDColorSpace cs = this.context.getResources().getColorSpace(COSName.DEVICECMYK);
/* 40 */     this.context.getGraphicsState().setNonStrokingColorSpace(cs);
/* 41 */     super.process(operator, arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 47 */     return "k";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/color/SetNonStrokingDeviceCMYKColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */