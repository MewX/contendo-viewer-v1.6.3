/*    */ package jp.cssj.sakae.pdf.e;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import jp.cssj.sakae.a.e;
/*    */ import jp.cssj.sakae.a.g;
/*    */ import jp.cssj.sakae.pdf.c.e;
/*    */ import jp.cssj.sakae.pdf.c.f;
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
/*    */ class b
/*    */ {
/*    */   private final p a;
/*    */   private final Map<String, jp.cssj.sakae.pdf.b> b;
/*    */   private final j c;
/* 27 */   private final Map<g, e> d = new HashMap<>();
/* 28 */   private final List<e> e = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public b(Map<String, jp.cssj.sakae.pdf.b> nameToResourceRef, j objectsFlow, p xref) throws IOException {
/* 32 */     this.a = xref;
/* 33 */     this.b = nameToResourceRef;
/* 34 */     this.c = objectsFlow;
/*    */   }
/*    */   
/*    */   public e a(g source) throws IOException {
/* 38 */     e font = this.d.get(source);
/* 39 */     if (font != null) {
/* 40 */       return font;
/*    */     }
/*    */     
/* 43 */     if (source instanceof f) {
/* 44 */       String name = "F" + this.d.size();
/* 45 */       jp.cssj.sakae.pdf.b fontRef = this.a.a();
/* 46 */       this.b.put(name, fontRef);
/*    */       
/* 48 */       e e = ((f)source).a(name, fontRef);
/* 49 */       this.e.add(e);
/*    */     } else {
/*    */       
/* 52 */       font = source.n();
/*    */     } 
/* 54 */     this.d.put(source, font);
/*    */     
/* 56 */     return font;
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 60 */     for (int i = 0; i < this.e.size(); i++) {
/* 61 */       e font = this.e.get(i);
/* 62 */       font.a(this.c, this.a);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */