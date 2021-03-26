/*    */ package jp.cssj.sakae.pdf.e;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.SortedMap;
/*    */ import java.util.TreeMap;
/*    */ import jp.cssj.sakae.pdf.b;
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
/*    */ abstract class e
/*    */ {
/*    */   public final j a;
/*    */   private final n b;
/*    */   private final d c;
/*    */   private final String d;
/* 25 */   private SortedMap<String, Object> e = null;
/*    */   
/*    */   public e(n pdfWriter, String key) throws IOException {
/* 28 */     this.b = pdfWriter;
/* 29 */     this.d = key;
/*    */     
/* 31 */     j mainFlow = pdfWriter.k;
/* 32 */     this.a = mainFlow.c();
/* 33 */     this.c = pdfWriter.o;
/*    */   }
/*    */   
/*    */   public void a(String name, Object entry) {
/* 37 */     if (this.e == null) {
/* 38 */       this.e = new TreeMap<>();
/*    */     }
/* 40 */     this.e.put(name, entry);
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 44 */     if (this.e != null) {
/* 45 */       b destsRef = this.b.i.a();
/* 46 */       this.c.a(this.d, destsRef);
/*    */       
/* 48 */       this.a.a(destsRef);
/* 49 */       this.a.g();
/*    */ 
/*    */       
/* 52 */       if (this.b.h.h() <= 1200) {
/* 53 */         this.a.a("Kids");
/* 54 */         this.a.i();
/* 55 */         b destsKidRef = this.b.i.a();
/* 56 */         this.a.b(destsKidRef);
/* 57 */         this.a.j();
/* 58 */         this.a.k();
/*    */         
/* 60 */         this.a.h();
/* 61 */         this.a.a();
/*    */         
/* 63 */         this.a.a(destsKidRef);
/* 64 */         this.a.g();
/*    */         
/* 66 */         this.a.a("Limits");
/* 67 */         this.a.i();
/* 68 */         this.a.e(this.e.firstKey());
/* 69 */         this.a.e(this.e.lastKey());
/* 70 */         this.a.j();
/* 71 */         this.a.k();
/*    */       } 
/*    */       
/* 74 */       this.a.a("Names");
/* 75 */       this.a.i();
/* 76 */       for (Iterator<Map.Entry<String, Object>> i = this.e.entrySet().iterator(); i.hasNext(); ) {
/* 77 */         Map.Entry<String, Object> entry = i.next();
/* 78 */         this.a.e(entry.getKey());
/* 79 */         a(entry.getValue());
/*    */       } 
/* 81 */       this.a.j();
/* 82 */       this.a.k();
/*    */       
/* 84 */       this.a.h();
/* 85 */       this.a.a();
/*    */     } 
/* 87 */     this.a.close();
/*    */   }
/*    */   
/*    */   protected abstract void a(Object paramObject) throws IOException;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */