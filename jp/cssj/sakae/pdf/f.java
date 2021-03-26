/*    */ package jp.cssj.sakae.pdf;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class f
/*    */   extends d
/*    */ {
/*    */   protected f(j pdfWriter, OutputStream out, double width, double height) throws IOException {
/* 15 */     super(pdfWriter, out, width, height);
/*    */   }
/*    */   
/*    */   public abstract String e();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */