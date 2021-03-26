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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class h
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   private int[] d;
/*    */   
/*    */   public h(RandomAccessFile raf) throws IOException {
/* 40 */     this.a = raf.readUnsignedShort();
/* 41 */     this.b = raf.readUnsignedShort();
/* 42 */     this.c = raf.readUnsignedShort();
/* 43 */     int size = this.a - this.b;
/* 44 */     switch (this.c) {
/*    */       case 1:
/* 46 */         size = (size % 8 == 0) ? (size / 8) : (size / 8 + 1);
/*    */         break;
/*    */       case 2:
/* 49 */         size = (size % 4 == 0) ? (size / 4) : (size / 4 + 1);
/*    */         break;
/*    */       case 3:
/* 52 */         size = (size % 2 == 0) ? (size / 2) : (size / 2 + 1);
/*    */         break;
/*    */     } 
/* 55 */     this.d = new int[size];
/* 56 */     for (int i = 0; i < size; i++)
/* 57 */       this.d[i] = raf.readUnsignedShort(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */