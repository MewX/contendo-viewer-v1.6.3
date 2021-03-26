/*     */ package org.apache.fontbox.pfb;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
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
/*     */ public class PfbParser
/*     */ {
/*     */   private static final int PFB_HEADER_LENGTH = 18;
/*     */   private static final int START_MARKER = 128;
/*     */   private static final int ASCII_MARKER = 1;
/*     */   private static final int BINARY_MARKER = 2;
/*  61 */   private static final int[] PFB_RECORDS = new int[] { 1, 2, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int BUFFER_SIZE = 65535;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] pfbdata;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] lengths;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PfbParser(String filename) throws IOException {
/*  91 */     this(new BufferedInputStream(new FileInputStream(filename), 65535));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PfbParser(InputStream in) throws IOException {
/* 101 */     byte[] pfb = readPfbInput(in);
/* 102 */     parsePfb(pfb);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PfbParser(byte[] bytes) throws IOException {
/* 112 */     parsePfb(bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parsePfb(byte[] pfb) throws IOException {
/* 123 */     ByteArrayInputStream in = new ByteArrayInputStream(pfb);
/* 124 */     this.pfbdata = new byte[pfb.length - 18];
/* 125 */     this.lengths = new int[PFB_RECORDS.length];
/* 126 */     int pointer = 0;
/* 127 */     for (int records = 0; records < PFB_RECORDS.length; records++) {
/*     */       
/* 129 */       if (in.read() != 128)
/*     */       {
/* 131 */         throw new IOException("Start marker missing");
/*     */       }
/*     */       
/* 134 */       if (in.read() != PFB_RECORDS[records])
/*     */       {
/* 136 */         throw new IOException("Incorrect record type");
/*     */       }
/*     */       
/* 139 */       int size = in.read();
/* 140 */       size += in.read() << 8;
/* 141 */       size += in.read() << 16;
/* 142 */       size += in.read() << 24;
/* 143 */       this.lengths[records] = size;
/* 144 */       if (pointer >= this.pfbdata.length)
/*     */       {
/* 146 */         throw new EOFException("attempted to read past EOF");
/*     */       }
/* 148 */       int got = in.read(this.pfbdata, pointer, size);
/* 149 */       if (got < 0)
/*     */       {
/* 151 */         throw new EOFException();
/*     */       }
/* 153 */       pointer += got;
/*     */     } 
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
/*     */   private byte[] readPfbInput(InputStream in) throws IOException {
/* 166 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 167 */     byte[] tmpbuf = new byte[65535];
/* 168 */     int amountRead = -1;
/* 169 */     while ((amountRead = in.read(tmpbuf)) != -1)
/*     */     {
/* 171 */       out.write(tmpbuf, 0, amountRead);
/*     */     }
/* 173 */     return out.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getLengths() {
/* 182 */     return this.lengths;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getPfbdata() {
/* 191 */     return this.pfbdata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() {
/* 200 */     return new ByteArrayInputStream(this.pfbdata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 209 */     return this.pfbdata.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSegment1() {
/* 218 */     return Arrays.copyOfRange(this.pfbdata, 0, this.lengths[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getSegment2() {
/* 227 */     return Arrays.copyOfRange(this.pfbdata, this.lengths[0], this.lengths[0] + this.lengths[1]);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/pfb/PfbParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */