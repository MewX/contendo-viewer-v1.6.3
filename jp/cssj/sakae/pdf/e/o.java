/*    */ package jp.cssj.sakae.pdf.e;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ import jp.cssj.sakae.pdf.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ class o
/*    */ {
/* 15 */   private final Map<String, j> c = new TreeMap<>();
/* 16 */   private final List<j> d = new ArrayList<>();
/* 17 */   private final Map<String, b> e = new HashMap<>();
/*    */   
/*    */   public o(j flow) throws IOException {
/* 20 */     flow.g();
/* 21 */     flow.a("ProcSet");
/* 22 */     flow.i();
/* 23 */     flow.a("PDF");
/* 24 */     flow.a("Text");
/* 25 */     flow.a("ImageB");
/* 26 */     flow.a("ImageC");
/* 27 */     flow.a("ImageI");
/* 28 */     flow.j();
/* 29 */     flow.k();
/* 30 */     this.b = flow.c();
/* 31 */     flow.h();
/*    */   }
/*    */   private final j b;
/*    */   private j b(String type) throws IOException {
/* 35 */     j flow = this.c.get(type);
/* 36 */     if (flow == null) {
/* 37 */       flow = this.b.c();
/* 38 */       this.c.put(type, flow);
/* 39 */       this.d.add(flow);
/* 40 */       flow.a(type);
/* 41 */       flow.g();
/*    */     } 
/* 43 */     return flow;
/*    */   }
/*    */   
/*    */   public boolean a(String name) {
/* 47 */     return this.e.containsKey(name);
/*    */   }
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
/*    */   public void a(String type, String name, b objectRef) throws IOException {
/* 61 */     if (!a && a(name)) throw new AssertionError(); 
/* 62 */     j flow = b(type);
/* 63 */     flow.a(name);
/* 64 */     flow.b(objectRef);
/* 65 */     this.e.put(name, objectRef);
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 69 */     for (int i = 0; i < this.d.size(); i++) {
/* 70 */       try (j flow = (j)this.d.get(i)) {
/* 71 */         flow.h();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/o.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */