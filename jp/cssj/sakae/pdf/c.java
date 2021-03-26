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
/*    */ 
/*    */ 
/*    */ public abstract class c
/*    */   extends h
/*    */ {
/*    */   public static final short a = 0;
/*    */   public static final short b = 1;
/*    */   public static final short c = 2;
/*    */   
/*    */   protected c(OutputStream out, String nameEncoding) throws IOException {
/* 21 */     super(out, nameEncoding);
/*    */   }
/*    */   
/*    */   public abstract void a(b paramb) throws IOException;
/*    */   
/*    */   public abstract void a() throws IOException;
/*    */   
/*    */   public abstract OutputStream a(short paramShort) throws IOException;
/*    */   
/*    */   public abstract OutputStream b(short paramShort) throws IOException;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */