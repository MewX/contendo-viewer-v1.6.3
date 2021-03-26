/*    */ package net.zamasoft.a.c;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ import net.zamasoft.a.b.R;
/*    */ import net.zamasoft.a.b.o;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class c
/*    */   extends R
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   public static final byte c = 4;
/*    */   public static final byte d = 8;
/*    */   public static final byte e = 16;
/*    */   public static final byte f = 32;
/*    */   protected o g;
/*    */   private int h;
/*    */   private short i;
/*    */   private short j;
/*    */   private short k;
/*    */   private short l;
/*    */   
/*    */   protected c(o parentTable, int numberOfContours, RandomAccessFile raf) throws IOException {
/* 58 */     this.g = parentTable;
/* 59 */     this.h = numberOfContours;
/* 60 */     this.i = (short)(raf.read() << 8 | raf.read());
/* 61 */     this.j = (short)(raf.read() << 8 | raf.read());
/* 62 */     this.k = (short)(raf.read() << 8 | raf.read());
/* 63 */     this.l = (short)(raf.read() << 8 | raf.read());
/*    */   }
/*    */   
/*    */   public int f() {
/* 67 */     return this.h;
/*    */   }
/*    */   
/*    */   public short g() {
/* 71 */     return this.k;
/*    */   }
/*    */   
/*    */   public short h() {
/* 75 */     return this.i;
/*    */   }
/*    */   
/*    */   public short i() {
/* 79 */     return this.l;
/*    */   }
/*    */   
/*    */   public short j() {
/* 83 */     return this.j;
/*    */   }
/*    */   
/*    */   public abstract int a(int paramInt);
/*    */   
/*    */   public abstract byte b(int paramInt);
/*    */   
/*    */   public abstract short c(int paramInt);
/*    */   
/*    */   public abstract short d(int paramInt);
/*    */   
/*    */   public abstract boolean a();
/*    */   
/*    */   public abstract int c();
/*    */   
/*    */   public abstract int d();
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/c/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */