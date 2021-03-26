/*     */ package org.apache.batik.svggen.font.table;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
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
/*     */ public class CmapFormat4
/*     */   extends CmapFormat
/*     */ {
/*     */   public int language;
/*     */   private int segCountX2;
/*     */   private int searchRange;
/*     */   private int entrySelector;
/*     */   private int rangeShift;
/*     */   private int[] endCode;
/*     */   private int[] startCode;
/*     */   private int[] idDelta;
/*     */   private int[] idRangeOffset;
/*     */   private int[] glyphIdArray;
/*     */   private int segCount;
/*     */   private int first;
/*     */   private int last;
/*     */   
/*     */   protected CmapFormat4(RandomAccessFile raf) throws IOException {
/*  44 */     super(raf);
/*  45 */     this.format = 4;
/*  46 */     this.segCountX2 = raf.readUnsignedShort();
/*  47 */     this.segCount = this.segCountX2 / 2;
/*  48 */     this.endCode = new int[this.segCount];
/*  49 */     this.startCode = new int[this.segCount];
/*  50 */     this.idDelta = new int[this.segCount];
/*  51 */     this.idRangeOffset = new int[this.segCount];
/*  52 */     this.searchRange = raf.readUnsignedShort();
/*  53 */     this.entrySelector = raf.readUnsignedShort();
/*  54 */     this.rangeShift = raf.readUnsignedShort();
/*  55 */     this.last = -1; int i;
/*  56 */     for (i = 0; i < this.segCount; i++) {
/*  57 */       this.endCode[i] = raf.readUnsignedShort();
/*  58 */       if (this.endCode[i] > this.last) this.last = this.endCode[i]; 
/*     */     } 
/*  60 */     raf.readUnsignedShort();
/*  61 */     for (i = 0; i < this.segCount; i++) {
/*  62 */       this.startCode[i] = raf.readUnsignedShort();
/*  63 */       if (i == 0 || this.startCode[i] < this.first) this.first = this.startCode[i]; 
/*     */     } 
/*  65 */     for (i = 0; i < this.segCount; i++) {
/*  66 */       this.idDelta[i] = raf.readUnsignedShort();
/*     */     }
/*  68 */     for (i = 0; i < this.segCount; i++) {
/*  69 */       this.idRangeOffset[i] = raf.readUnsignedShort();
/*     */     }
/*     */ 
/*     */     
/*  73 */     int count = (this.length - 16 - this.segCount * 8) / 2;
/*  74 */     this.glyphIdArray = new int[count];
/*  75 */     for (int j = 0; j < count; j++)
/*  76 */       this.glyphIdArray[j] = raf.readUnsignedShort(); 
/*     */   }
/*     */   
/*     */   public int getFirst() {
/*  80 */     return this.first; } public int getLast() {
/*  81 */     return this.last;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int mapCharCode(int charCode) {
/*     */     try {
/* 100 */       if (charCode < 0 || charCode >= 65534) {
/* 101 */         return 0;
/*     */       }
/* 103 */       for (int i = 0; i < this.segCount; i++) {
/* 104 */         if (this.endCode[i] >= charCode) {
/* 105 */           if (this.startCode[i] <= charCode) {
/* 106 */             if (this.idRangeOffset[i] > 0) {
/* 107 */               return this.glyphIdArray[this.idRangeOffset[i] / 2 + charCode - this.startCode[i] - this.segCount - i];
/*     */             }
/*     */ 
/*     */             
/* 111 */             return (this.idDelta[i] + charCode) % 65536;
/*     */           } 
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 118 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 119 */       System.err.println("error: Array out of bounds - " + e.getMessage());
/*     */     } 
/* 121 */     return 0;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 125 */     return (new StringBuffer(80)).append(super.toString()).append(", segCountX2: ").append(this.segCountX2).append(", searchRange: ").append(this.searchRange).append(", entrySelector: ").append(this.entrySelector).append(", rangeShift: ").append(this.rangeShift).append(", endCode: ").append(intToStr(this.endCode)).append(", startCode: ").append(intToStr(this.startCode)).append(", idDelta: ").append(intToStr(this.idDelta)).append(", idRangeOffset: ").append(intToStr(this.idRangeOffset)).toString();
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
/*     */   private static String intToStr(int[] array) {
/* 153 */     int nSlots = array.length;
/* 154 */     StringBuffer workBuff = new StringBuffer(nSlots * 8);
/* 155 */     workBuff.append('[');
/* 156 */     for (int i = 0; i < nSlots; i++) {
/* 157 */       workBuff.append(array[i]);
/* 158 */       if (i < nSlots - 1) {
/* 159 */         workBuff.append(',');
/*     */       }
/*     */     } 
/* 162 */     workBuff.append(']');
/* 163 */     return workBuff.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CmapFormat4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */