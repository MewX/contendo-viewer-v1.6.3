/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
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
/*    */ public class M
/*    */   implements aa
/*    */ {
/*    */   private short a;
/*    */   private short b;
/*    */   private L[] c;
/*    */   
/*    */   protected M(i de, RandomAccessFile raf) throws IOException {
/* 36 */     synchronized (raf) {
/* 37 */       raf.seek(de.c());
/* 38 */       raf.readShort();
/* 39 */       this.a = raf.readShort();
/* 40 */       this.b = raf.readShort();
/* 41 */       this.c = new L[this.a];
/*    */ 
/*    */       
/*    */       int j;
/*    */       
/* 46 */       for (j = 0; j < this.a; j++) {
/* 47 */         this.c[j] = new L(raf);
/*    */       }
/*    */ 
/*    */       
/* 51 */       for (j = 0; j < this.a; j++) {
/* 52 */         this.c[j].a(raf, de.c() + this.b);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String a(short nameId) {
/* 60 */     for (int i = 0; i < this.a; i++) {
/* 61 */       if (this.c[i].c() == nameId) {
/* 62 */         return this.c[i].e();
/*    */       }
/*    */     } 
/* 65 */     return "";
/*    */   }
/*    */   
/*    */   public int a() {
/* 69 */     return 1851878757;
/*    */   }
/*    */   
/*    */   public L a(int i) {
/* 73 */     return this.c[i];
/*    */   }
/*    */   
/*    */   public int b() {
/* 77 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/M.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */