/*    */ package jp.cssj.sakae.pdf.c.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Serializable;
/*    */ import jp.cssj.e.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   implements Serializable
/*    */ {
/*    */   private static final long f = 0L;
/*    */   protected final c a;
/*    */   protected String b;
/*    */   protected String c;
/*    */   protected String d;
/*    */   protected int e;
/*    */   
/*    */   public f(b source, String javaEncoding) throws IOException {
/* 26 */     this.a = new c(source, javaEncoding);
/* 27 */     h parser = new h();
/* 28 */     parser.a(source.h(), this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public c a() {
/* 37 */     return this.a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String b() {
/* 46 */     return this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String c() {
/* 55 */     return this.c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String d() {
/* 64 */     return this.d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int e() {
/* 73 */     return this.e;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */