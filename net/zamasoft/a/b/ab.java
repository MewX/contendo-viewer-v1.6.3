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
/*    */ public class ab
/*    */ {
/* 29 */   private int a = 0;
/*    */   
/* 31 */   private short b = 0;
/*    */   
/* 33 */   private short c = 0;
/*    */   
/* 35 */   private short d = 0;
/*    */   
/* 37 */   private short e = 0;
/*    */   
/*    */   private i[] f;
/*    */   
/*    */   public ab(RandomAccessFile raf) throws IOException {
/* 42 */     this.a = raf.readInt();
/* 43 */     this.b = raf.readShort();
/* 44 */     this.c = raf.readShort();
/* 45 */     this.d = raf.readShort();
/* 46 */     this.e = raf.readShort();
/* 47 */     this.f = new i[this.b];
/* 48 */     for (int j = 0; j < this.b; j++) {
/* 49 */       this.f[j] = new i(raf);
/*    */     }
/*    */ 
/*    */     
/* 53 */     boolean modified = true;
/* 54 */     while (modified) {
/* 55 */       modified = false;
/* 56 */       for (int k = 0; k < this.b - 1; k++) {
/* 57 */         if (this.f[k].c() > this.f[k + 1].c()) {
/* 58 */           i temp = this.f[k];
/* 59 */           this.f[k] = this.f[k + 1];
/* 60 */           this.f[k + 1] = temp;
/* 61 */           modified = true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public i a(int index) {
/* 68 */     return this.f[index];
/*    */   }
/*    */   
/*    */   public i b(int tag) {
/* 72 */     for (int j = 0; j < this.b; j++) {
/* 73 */       if (this.f[j].d() == tag) {
/* 74 */         return this.f[j];
/*    */       }
/*    */     } 
/* 77 */     return null;
/*    */   }
/*    */   
/*    */   public short a() {
/* 81 */     return this.d;
/*    */   }
/*    */   
/*    */   public short b() {
/* 85 */     return this.b;
/*    */   }
/*    */   
/*    */   public short c() {
/* 89 */     return this.e;
/*    */   }
/*    */   
/*    */   public short d() {
/* 93 */     return this.c;
/*    */   }
/*    */   
/*    */   public int e() {
/* 97 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/ab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */