/*    */ package net.zamasoft.a.a;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ class e {
/*  7 */   private int b = 0;
/*    */   private final int[] c;
/*    */   
/*    */   public e() {
/* 11 */     int size = 48;
/* 12 */     this.c = new int[size];
/*    */   }
/*    */   
/*    */   public void a(int value) {
/* 16 */     this.c[this.b] = value;
/* 17 */     this.b++;
/*    */   }
/*    */   
/*    */   public int b(int ix) {
/* 21 */     if (!a && ix >= this.b) throw new AssertionError(); 
/* 22 */     return this.c[ix];
/*    */   }
/*    */   
/*    */   public int a() {
/* 26 */     return this.c[--this.b];
/*    */   }
/*    */   
/*    */   public void b() {
/* 30 */     this.b = 0;
/*    */   }
/*    */   
/*    */   public void c(int count) {
/* 34 */     this.b -= count;
/* 35 */     System.arraycopy(this.c, count, this.c, 0, this.b);
/*    */   }
/*    */   
/*    */   public int c() {
/* 39 */     return this.b;
/*    */   }
/*    */   
/*    */   public void a(OutputStream out, int op, int size) throws IOException {
/* 43 */     for (int i = this.b - size; i < this.b; i++) {
/* 44 */       int a = this.c[i];
/* 45 */       if (a >= -107 && a <= 107) {
/* 46 */         out.write(a + 139);
/* 47 */       } else if (a >= 108 && a <= 1131) {
/* 48 */         a -= 108;
/* 49 */         out.write((a >> 8) + 247);
/* 50 */         out.write(a);
/* 51 */       } else if (a >= -1131 && a <= -108) {
/* 52 */         a += 108;
/* 53 */         out.write((-a >> 8) + 251);
/* 54 */         out.write(-a);
/* 55 */       } else if (a >= -32768 && a <= 32767) {
/* 56 */         out.write(28);
/* 57 */         out.write(a >> 8);
/* 58 */         out.write(a);
/*    */       } else {
/* 60 */         out.write(255);
/* 61 */         out.write(a >> 24);
/* 62 */         out.write(a >> 16);
/* 63 */         out.write(a >> 8);
/* 64 */         out.write(a);
/*    */       } 
/*    */     } 
/* 67 */     if ((op & 0xFF00) == 3072) {
/* 68 */       out.write(op >> 8);
/* 69 */       out.write(op);
/*    */     } else {
/* 71 */       out.write(op);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */