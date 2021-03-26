/*    */ package jp.cssj.sakae.pdf.e;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.Map;
/*    */ import jp.cssj.sakae.pdf.b;
/*    */ import jp.cssj.sakae.pdf.c;
/*    */ import jp.cssj.sakae.pdf.f;
/*    */ import jp.cssj.sakae.pdf.j;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class l
/*    */   extends f
/*    */ {
/*    */   private final c i;
/*    */   private final o j;
/*    */   private final String k;
/*    */   
/*    */   public l(j pdfWriter, OutputStream out, c patternFlow, o resourceFlow, double width, double height, String name) throws IOException {
/* 26 */     super(pdfWriter, out, width, height);
/* 27 */     this.i = patternFlow;
/* 28 */     this.j = resourceFlow;
/* 29 */     this.k = name;
/*    */   }
/*    */   
/*    */   private n c() {
/* 33 */     return (n)this.c;
/*    */   }
/*    */   
/*    */   public void a(String type, String name) throws IOException {
/* 37 */     o resourceFlow = this.j;
/* 38 */     if (resourceFlow.a(name)) {
/*    */       return;
/*    */     }
/* 41 */     Map<String, b> nameToResourceRef = (c()).r;
/*    */     
/* 43 */     b objectRef = nameToResourceRef.get(name);
/* 44 */     resourceFlow.a(type, name, objectRef);
/*    */   }
/*    */   
/*    */   public String e() {
/* 48 */     return this.k;
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 52 */     super.close();
/* 53 */     this.i.close();
/* 54 */     this.j.a();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */