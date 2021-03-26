/*     */ package net.zamasoft.a.c;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import net.zamasoft.a.b.o;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   extends c
/*     */ {
/*     */   private int[] h;
/*     */   private byte[] i;
/*     */   private short[] j;
/*     */   private short[] k;
/*     */   private int l;
/*     */   
/*     */   public d(o parentTable, int numberOfContours, RandomAccessFile raf) throws IOException {
/*  43 */     super(parentTable, numberOfContours, raf);
/*     */ 
/*     */     
/*  46 */     this.h = new int[numberOfContours];
/*  47 */     for (int i = 0; i < numberOfContours; i++) {
/*  48 */       this.h[i] = raf.read() << 8 | raf.read();
/*     */     }
/*     */ 
/*     */     
/*  52 */     this.l = this.h[numberOfContours - 1] + 1;
/*  53 */     this.i = new byte[this.l];
/*  54 */     this.j = new short[this.l];
/*  55 */     this.k = new short[this.l];
/*     */     
/*  57 */     int instructionCount = raf.read() << 8 | raf.read();
/*  58 */     a(raf, instructionCount);
/*  59 */     b(this.l, raf);
/*  60 */     a(this.l, raf);
/*     */   }
/*     */   
/*     */   public int a(int i) {
/*  64 */     return this.h[i];
/*     */   }
/*     */   
/*     */   public byte b(int i) {
/*  68 */     return this.i[i];
/*     */   }
/*     */   
/*     */   public short c(int i) {
/*  72 */     return this.j[i];
/*     */   }
/*     */   
/*     */   public short d(int i) {
/*  76 */     return this.k[i];
/*     */   }
/*     */   
/*     */   public boolean a() {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public int c() {
/*  84 */     return this.l;
/*     */   }
/*     */   
/*     */   public int d() {
/*  88 */     return f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(int count, RandomAccessFile raf) throws IOException {
/*  95 */     short x = 0;
/*  96 */     short y = 0; int i;
/*  97 */     for (i = 0; i < count; i++) {
/*  98 */       if ((this.i[i] & 0x10) != 0) {
/*  99 */         if ((this.i[i] & 0x2) != 0) {
/* 100 */           x = (short)(x + (short)raf.read());
/*     */         }
/*     */       }
/* 103 */       else if ((this.i[i] & 0x2) != 0) {
/* 104 */         x = (short)(x + (short)-((short)raf.read()));
/*     */       } else {
/* 106 */         x = (short)(x + (short)(raf.read() << 8 | raf.read()));
/*     */       } 
/*     */       
/* 109 */       this.j[i] = x;
/*     */     } 
/*     */     
/* 112 */     for (i = 0; i < count; i++) {
/* 113 */       if ((this.i[i] & 0x20) != 0) {
/* 114 */         if ((this.i[i] & 0x4) != 0) {
/* 115 */           y = (short)(y + (short)raf.read());
/*     */         }
/*     */       }
/* 118 */       else if ((this.i[i] & 0x4) != 0) {
/* 119 */         y = (short)(y + (short)-((short)raf.read()));
/*     */       } else {
/* 121 */         y = (short)(y + (short)(raf.read() << 8 | raf.read()));
/*     */       } 
/*     */       
/* 124 */       this.k[i] = y;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(int flagCount, RandomAccessFile raf) throws IOException {
/*     */     try {
/* 133 */       for (int index = 0; index < flagCount; index++) {
/* 134 */         this.i[index] = (byte)raf.read();
/* 135 */         if ((this.i[index] & 0x8) != 0) {
/* 136 */           int repeats = raf.read();
/* 137 */           for (int i = 1; i <= repeats; i++) {
/* 138 */             this.i[index + i] = this.i[index];
/*     */           }
/* 140 */           index += repeats;
/*     */         } 
/*     */       } 
/* 143 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 144 */       System.out.println("error: array index out of bounds");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/c/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */