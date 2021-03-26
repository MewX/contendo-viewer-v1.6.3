/*    */ package jp.cssj.sakae.pdf;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import jp.cssj.sakae.pdf.b.a;
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
/*    */ public abstract class i
/*    */   extends d
/*    */ {
/*    */   protected i(j pdfWriter, OutputStream out, double width, double height) throws IOException {
/* 24 */     super(pdfWriter, out, width, height);
/*    */   }
/*    */   
/*    */   public abstract void a(a parama) throws IOException;
/*    */   
/*    */   public abstract void a(String paramString, Point2D paramPoint2D) throws IOException;
/*    */   
/*    */   public abstract void b(String paramString, Point2D paramPoint2D) throws IOException;
/*    */   
/*    */   public abstract void c() throws IOException;
/*    */   
/*    */   public abstract void a(Rectangle2D paramRectangle2D);
/*    */   
/*    */   public abstract void b(Rectangle2D paramRectangle2D);
/*    */   
/*    */   public abstract void c(Rectangle2D paramRectangle2D);
/*    */   
/*    */   public abstract void d(Rectangle2D paramRectangle2D);
/*    */   
/*    */   public abstract void e(Rectangle2D paramRectangle2D);
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */