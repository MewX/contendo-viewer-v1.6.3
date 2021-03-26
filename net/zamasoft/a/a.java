/*     */ package net.zamasoft.a;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import net.zamasoft.a.d.a;
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
/*     */ public class a
/*     */ {
/*     */   private final d[] a;
/*     */   private final long[] b;
/*     */   private final File c;
/*     */   private final boolean d;
/*     */   
/*     */   public a(File file) throws IOException {
/*  37 */     a a1 = new a(file, "r");
/*     */     try {
/*  39 */       byte[] tagBytes = new byte[4];
/*  40 */       a1.readFully(tagBytes);
/*  41 */       String tag = new String(tagBytes, "ISO-8859-1");
/*  42 */       if ("wOFF".equals(tag)) {
/*     */         
/*  44 */         this.c = File.createTempFile("copper", "dat");
/*  45 */         try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.c)))) {
/*     */           
/*  47 */           int version = a1.readInt();
/*  48 */           a1.skipBytes(4);
/*  49 */           int numTables = a1.readShort();
/*  50 */           a1.skipBytes(30);
/*     */           
/*  52 */           out.writeInt(version);
/*  53 */           out.writeShort(numTables);
/*  54 */           out.writeShort(0);
/*  55 */           out.writeShort(0);
/*  56 */           out.writeShort(0);
/*     */           
/*  58 */           int outOffset = out.size() + numTables * 16;
/*  59 */           for (int i = 0; i < numTables; i++) {
/*  60 */             a1.seek((44 + i * 20));
/*  61 */             a1.readFully(tagBytes);
/*  62 */             tag = new String(tagBytes, "ISO-8859-1");
/*  63 */             a1.skipBytes(8);
/*  64 */             int origLen = a1.readInt();
/*     */             
/*  66 */             out.writeBytes(tag);
/*  67 */             out.writeInt(0);
/*  68 */             out.writeInt(outOffset);
/*  69 */             out.writeInt(origLen);
/*  70 */             outOffset += origLen;
/*     */           } 
/*  72 */           byte[] buff = new byte[1024];
/*  73 */           for (int j = 0; j < numTables; j++) {
/*  74 */             a1.seek((44 + j * 20 + 4));
/*  75 */             int inOffset = a1.readInt();
/*  76 */             int compLen = a1.readInt();
/*  77 */             int origLen = a1.readInt();
/*     */             
/*  79 */             int remainder = origLen;
/*  80 */             try (InputStream in = new FileInputStream(file)) {
/*  81 */               in.skip(inOffset);
/*  82 */               if (compLen == origLen) {
/*     */                 
/*  84 */                 int len = in.read(buff, 0, Math.min(remainder, buff.length));
/*  85 */                 for (; remainder > 0 && len != -1; len = in.read(buff, 0, Math.min(remainder, buff.length))) {
/*  86 */                   out.write(buff, 0, len);
/*  87 */                   remainder -= len;
/*     */                 } 
/*     */               } else {
/*     */                 
/*  91 */                 try (InputStream zin = new InflaterInputStream(in)) {
/*  92 */                   int len = zin.read(buff, 0, Math.min(remainder, buff.length));
/*  93 */                   for (; remainder > 0 && len != -1; len = zin.read(buff, 0, Math.min(remainder, buff.length))) {
/*  94 */                     out.write(buff, 0, len);
/*  95 */                     remainder -= len;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/* 100 */             for (; remainder > 0; remainder--) {
/* 101 */               out.writeByte(0);
/*     */             }
/*     */           } 
/*     */         } 
/* 105 */         this.c.deleteOnExit();
/* 106 */         a1.close();
/* 107 */         a1 = new a(this.c, "r");
/* 108 */         this.d = true;
/*     */       } else {
/* 110 */         this.c = file;
/* 111 */         this.d = false;
/*     */       } 
/* 113 */       if ("ttcf".equals(tag)) {
/*     */         
/* 115 */         a1.skipBytes(4);
/* 116 */         int numFonts = a1.readInt();
/* 117 */         this.b = new long[numFonts];
/* 118 */         this.a = new d[numFonts];
/* 119 */         for (int i = 0; i < numFonts; i++) {
/* 120 */           this.b[i] = a1.readInt();
/*     */         }
/*     */       } else {
/*     */         
/* 124 */         this.b = new long[] { 0L };
/* 125 */         this.a = new d[1];
/*     */       } 
/*     */     } finally {
/* 128 */       a1.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a() {
/* 138 */     return (this.a != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 147 */     return this.a.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d a(int i) throws IOException {
/* 157 */     if (this.a[i] == null) {
/* 158 */       a a1 = new a(this.c, "r");
/* 159 */       a1.seek(this.b[i]);
/* 160 */       if (this.d) {
/* 161 */         this.a[i] = new d(this, (RandomAccessFile)a1) {
/*     */             protected void finalize() throws Throwable {
/* 163 */               super.finalize();
/* 164 */               a.a(this.a).delete();
/*     */             }
/*     */           };
/*     */       } else {
/* 168 */         this.a[i] = new d((RandomAccessFile)a1);
/*     */       } 
/*     */     } 
/* 171 */     return this.a[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d c() throws IOException {
/* 180 */     return a(0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */