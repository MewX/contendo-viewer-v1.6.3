/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class GlyfSimpleDescript
/*     */   extends GlyfDescript
/*     */ {
/*  35 */   private static final Log LOG = LogFactory.getLog(GlyfSimpleDescript.class);
/*     */ 
/*     */   
/*     */   private int[] endPtsOfContours;
/*     */ 
/*     */   
/*     */   private byte[] flags;
/*     */ 
/*     */   
/*     */   private short[] xCoordinates;
/*     */ 
/*     */   
/*     */   private short[] yCoordinates;
/*     */   
/*     */   private final int pointCount;
/*     */ 
/*     */   
/*     */   public GlyfSimpleDescript(short numberOfContours, TTFDataStream bais, short x0) throws IOException {
/*  53 */     super(numberOfContours, bais);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     if (numberOfContours == 0) {
/*     */       
/*  62 */       this.pointCount = 0;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  67 */     this.endPtsOfContours = bais.readUnsignedShortArray(numberOfContours);
/*     */     
/*  69 */     int lastEndPt = this.endPtsOfContours[numberOfContours - 1];
/*  70 */     if (numberOfContours == 1 && lastEndPt == 65535) {
/*     */ 
/*     */       
/*  73 */       this.pointCount = 0;
/*     */       
/*     */       return;
/*     */     } 
/*  77 */     this.pointCount = lastEndPt + 1;
/*     */     
/*  79 */     this.flags = new byte[this.pointCount];
/*  80 */     this.xCoordinates = new short[this.pointCount];
/*  81 */     this.yCoordinates = new short[this.pointCount];
/*     */     
/*  83 */     int instructionCount = bais.readUnsignedShort();
/*  84 */     readInstructions(bais, instructionCount);
/*  85 */     readFlags(this.pointCount, bais);
/*  86 */     readCoords(this.pointCount, bais, x0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndPtOfContours(int i) {
/*  95 */     return this.endPtsOfContours[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getFlags(int i) {
/* 104 */     return this.flags[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getXCoordinate(int i) {
/* 113 */     return this.xCoordinates[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getYCoordinate(int i) {
/* 122 */     return this.yCoordinates[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPointCount() {
/* 140 */     return this.pointCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readCoords(int count, TTFDataStream bais, short x0) throws IOException {
/* 148 */     short x = x0;
/* 149 */     short y = 0; int i;
/* 150 */     for (i = 0; i < count; i++) {
/*     */       
/* 152 */       if ((this.flags[i] & 0x10) != 0) {
/*     */         
/* 154 */         if ((this.flags[i] & 0x2) != 0)
/*     */         {
/* 156 */           x = (short)(x + (short)bais.readUnsignedByte());
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 161 */       else if ((this.flags[i] & 0x2) != 0) {
/*     */         
/* 163 */         x = (short)(x + (short)-((short)bais.readUnsignedByte()));
/*     */       }
/*     */       else {
/*     */         
/* 167 */         x = (short)(x + bais.readSignedShort());
/*     */       } 
/*     */       
/* 170 */       this.xCoordinates[i] = x;
/*     */     } 
/*     */     
/* 173 */     for (i = 0; i < count; i++) {
/*     */       
/* 175 */       if ((this.flags[i] & 0x20) != 0) {
/*     */         
/* 177 */         if ((this.flags[i] & 0x4) != 0)
/*     */         {
/* 179 */           y = (short)(y + (short)bais.readUnsignedByte());
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 184 */       else if ((this.flags[i] & 0x4) != 0) {
/*     */         
/* 186 */         y = (short)(y + (short)-((short)bais.readUnsignedByte()));
/*     */       }
/*     */       else {
/*     */         
/* 190 */         y = (short)(y + bais.readSignedShort());
/*     */       } 
/*     */       
/* 193 */       this.yCoordinates[i] = y;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readFlags(int flagCount, TTFDataStream bais) throws IOException {
/* 202 */     for (int index = 0; index < flagCount; index++) {
/*     */       
/* 204 */       this.flags[index] = (byte)bais.readUnsignedByte();
/* 205 */       if ((this.flags[index] & 0x8) != 0) {
/*     */         
/* 207 */         int repeats = bais.readUnsignedByte();
/* 208 */         for (int i = 1; i <= repeats && index + i < this.flags.length; i++)
/*     */         {
/* 210 */           this.flags[index + i] = this.flags[index];
/*     */         }
/* 212 */         index += repeats;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyfSimpleDescript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */