/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ import net.zamasoft.a.c.b;
/*    */ import net.zamasoft.a.c.c;
/*    */ import net.zamasoft.a.c.d;
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
/*    */ 
/*    */ public class o
/*    */   implements aa
/*    */ {
/*    */   private i a;
/*    */   private RandomAccessFile b;
/*    */   private F c;
/*    */   
/*    */   protected o(i de, RandomAccessFile raf) throws IOException {
/* 40 */     this.a = de;
/* 41 */     this.b = raf;
/*    */   }
/*    */   
/*    */   public void a(F loca) {
/* 45 */     this.c = loca;
/*    */   }
/*    */   public c a(int j) {
/*    */     b b;
/* 49 */     c desc = null;
/*    */     try {
/* 51 */       int len = this.c.a(j + 1) - this.c.a(j);
/* 52 */       if (len <= 0) {
/* 53 */         return null;
/*    */       }
/* 55 */       synchronized (this.b) {
/* 56 */         this.b.seek((this.a.c() + this.c.a(j)));
/* 57 */         int numberOfContours = this.b.readShort();
/* 58 */         if (numberOfContours >= 0) {
/* 59 */           d d = new d(this, numberOfContours, this.b);
/*    */         } else {
/* 61 */           b = new b(this, this.b);
/*    */         } 
/*    */       } 
/* 64 */     } catch (IOException e) {
/* 65 */       throw new RuntimeException(e);
/*    */     } 
/* 67 */     return (c)b;
/*    */   }
/*    */   
/*    */   public int a() {
/* 71 */     return 1735162214;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/o.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */