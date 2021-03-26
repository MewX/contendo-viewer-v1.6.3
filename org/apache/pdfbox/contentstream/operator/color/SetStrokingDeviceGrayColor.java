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
/*    */ public class SetStrokingDeviceGrayColor
/*    */   extends SetStrokingColor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 38 */     PDColorSpace cs = this.context.getResources().getColorSpace(COSName.DEVICEGRAY);
/* 39 */     this.context.getGraphicsState().setStrokingColorSpace(cs);
/* 40 */     super.process(operator, arguments);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 46 */     return "G";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/color/SetStrokingDeviceGrayColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */