/*    */ package jp.cssj.sakae.pdf.e;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.Map;
/*    */ import jp.cssj.sakae.pdf.b;
/*    */ import jp.cssj.sakae.pdf.c;
/*    */ import jp.cssj.sakae.pdf.d.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class k
/*    */   extends b
/*    */ {
/*    */   private final c l;
/*    */   private final c m;
/*    */   private final o n;
/*    */   
/*    */   public k(n pdfWriter, OutputStream out, c groupFlow, o resourceFlow, double width, double height, String name, b objectRef, c formFlow) throws IOException {
/* 25 */     super(pdfWriter, out, width, height, name, objectRef);
/* 26 */     this.l = groupFlow;
/* 27 */     this.m = formFlow;
/* 28 */     this.n = resourceFlow;
/*    */   }
/*    */   
/*    */   private n o() {
/* 32 */     return (n)this.c;
/*    */   }
/*    */   
/*    */   public void a(String type, String name) throws IOException {
/* 36 */     o resourceFlow = this.n;
/* 37 */     if (resourceFlow.a(name)) {
/*    */       return;
/*    */     }
/* 40 */     Map<String, b> nameToResourceRef = (o()).r;
/*    */     
/* 42 */     b objectRef = nameToResourceRef.get(name);
/* 43 */     resourceFlow.a(type, name, objectRef);
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 47 */     if (this.k != 0) {
/* 48 */       n pdfWriter = o();
/* 49 */       if (pdfWriter.a().h() < 1500) {
/* 50 */         throw new UnsupportedOperationException("OCG feature requres PDF >= 1.5.");
/*    */       }
/*    */ 
/*    */       
/* 54 */       this.m.a("OC");
/* 55 */       b ocgRef = pdfWriter.f();
/* 56 */       this.m.b(ocgRef);
/* 57 */       this.m.k();
/* 58 */       this.m.close();
/*    */       
/* 60 */       j objectsFlow = pdfWriter.n;
/* 61 */       objectsFlow.a(ocgRef);
/* 62 */       objectsFlow.g();
/* 63 */       objectsFlow.a("Type");
/* 64 */       objectsFlow.a("OCG");
/* 65 */       objectsFlow.a("Name");
/* 66 */       objectsFlow.e("WATERMARK");
/* 67 */       objectsFlow.a("Usage");
/* 68 */       objectsFlow.g();
/* 69 */       objectsFlow.a("View");
/* 70 */       objectsFlow.g();
/* 71 */       objectsFlow.a("ViewState");
/* 72 */       objectsFlow.a(((this.k & 0x1) != 0) ? "OFF" : "ON");
/* 73 */       objectsFlow.h();
/* 74 */       objectsFlow.a("Print");
/* 75 */       objectsFlow.g();
/* 76 */       objectsFlow.a("PrintState");
/* 77 */       objectsFlow.a(((this.k & 0x2) != 0) ? "OFF" : "ON");
/* 78 */       objectsFlow.h();
/* 79 */       objectsFlow.h();
/* 80 */       objectsFlow.h();
/* 81 */       objectsFlow.a();
/*    */     } 
/*    */     
/* 84 */     super.close();
/* 85 */     this.l.close();
/* 86 */     this.n.a();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */