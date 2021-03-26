/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
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
/*     */ public class GlyfSimpleDescript
/*     */   extends GlyfDescript
/*     */ {
/*     */   private int[] endPtsOfContours;
/*     */   private byte[] flags;
/*     */   private short[] xCoordinates;
/*     */   private short[] yCoordinates;
/*     */   private int count;
/*     */   
/*     */   public GlyfSimpleDescript(GlyfTable parentTable, short numberOfContours, ByteArrayInputStream bais) {
/*  37 */     super(parentTable, numberOfContours, bais);
/*     */ 
/*     */     
/*  40 */     this.endPtsOfContours = new int[numberOfContours];
/*  41 */     for (int i = 0; i < numberOfContours; i++) {
/*  42 */       this.endPtsOfContours[i] = bais.read() << 8 | bais.read();
/*     */     }
/*     */ 
/*     */     
/*  46 */     this.count = this.endPtsOfContours[numberOfContours - 1] + 1;
/*  47 */     this.flags = new byte[this.count];
/*  48 */     this.xCoordinates = new short[this.count];
/*  49 */     this.yCoordinates = new short[this.count];
/*     */     
/*  51 */     int instructionCount = bais.read() << 8 | bais.read();
/*  52 */     readInstructions(bais, instructionCount);
/*  53 */     readFlags(this.count, bais);
/*  54 */     readCoords(this.count, bais);
/*     */   }
/*     */   
/*     */   public int getEndPtOfContours(int i) {
/*  58 */     return this.endPtsOfContours[i];
/*     */   }
/*     */   
/*     */   public byte getFlags(int i) {
/*  62 */     return this.flags[i];
/*     */   }
/*     */   
/*     */   public short getXCoordinate(int i) {
/*  66 */     return this.xCoordinates[i];
/*     */   }
/*     */   
/*     */   public short getYCoordinate(int i) {
/*  70 */     return this.yCoordinates[i];
/*     */   }
/*     */   
/*     */   public boolean isComposite() {
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   public int getPointCount() {
/*  78 */     return this.count;
/*     */   }
/*     */   
/*     */   public int getContourCount() {
/*  82 */     return getNumberOfContours();
/*     */   }
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
/*     */   private void readCoords(int count, ByteArrayInputStream bais) {
/*  97 */     short x = 0;
/*  98 */     short y = 0; int i;
/*  99 */     for (i = 0; i < count; i++) {
/* 100 */       if ((this.flags[i] & 0x10) != 0) {
/* 101 */         if ((this.flags[i] & 0x2) != 0) {
/* 102 */           x = (short)(x + (short)bais.read());
/*     */         }
/*     */       }
/* 105 */       else if ((this.flags[i] & 0x2) != 0) {
/* 106 */         x = (short)(x + (short)-((short)bais.read()));
/*     */       } else {
/* 108 */         x = (short)(x + (short)(bais.read() << 8 | bais.read()));
/*     */       } 
/*     */       
/* 111 */       this.xCoordinates[i] = x;
/*     */     } 
/*     */     
/* 114 */     for (i = 0; i < count; i++) {
/* 115 */       if ((this.flags[i] & 0x20) != 0) {
/* 116 */         if ((this.flags[i] & 0x4) != 0) {
/* 117 */           y = (short)(y + (short)bais.read());
/*     */         }
/*     */       }
/* 120 */       else if ((this.flags[i] & 0x4) != 0) {
/* 121 */         y = (short)(y + (short)-((short)bais.read()));
/*     */       } else {
/* 123 */         y = (short)(y + (short)(bais.read() << 8 | bais.read()));
/*     */       } 
/*     */       
/* 126 */       this.yCoordinates[i] = y;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readFlags(int flagCount, ByteArrayInputStream bais) {
/*     */     try {
/* 135 */       for (int index = 0; index < flagCount; index++) {
/* 136 */         this.flags[index] = (byte)bais.read();
/* 137 */         if ((this.flags[index] & 0x8) != 0) {
/* 138 */           int repeats = bais.read();
/* 139 */           for (int i = 1; i <= repeats; i++) {
/* 140 */             this.flags[index + i] = this.flags[index];
/*     */           }
/* 142 */           index += repeats;
/*     */         } 
/*     */       } 
/* 145 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 146 */       System.out.println("error: array index out of bounds");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/GlyfSimpleDescript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */