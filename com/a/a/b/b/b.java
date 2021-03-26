/*     */ package com.a.a.b.b;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.channels.FileChannel;
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
/*     */ public class b
/*     */ {
/*     */   public static OutputStream a(File file) throws IOException {
/*  27 */     return new BufferedOutputStream(new FileOutputStream(file));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InputStream b(File file) throws IOException {
/*  36 */     return new BufferedInputStream(new FileInputStream(file));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedOutputStream a(OutputStream stream) {
/*  44 */     if (stream instanceof BufferedOutputStream) return (BufferedOutputStream)stream; 
/*  45 */     return new BufferedOutputStream(stream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedInputStream a(InputStream stream) {
/*  53 */     if (stream instanceof BufferedInputStream) return (BufferedInputStream)stream; 
/*  54 */     return new BufferedInputStream(stream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long a(InputStream in, OutputStream out) throws IOException {
/*  64 */     long cnt = 0L;
/*     */     
/*  66 */     if (in instanceof FileInputStream && out instanceof FileOutputStream) {
/*  67 */       FileChannel fcIn = ((FileInputStream)in).getChannel();
/*  68 */       FileChannel fcOut = null;
/*     */       
/*     */       try { try {
/*  71 */           fcOut = ((FileOutputStream)out).getChannel();
/*  72 */           long pos = 0L;
/*  73 */           cnt = fcIn.size();
/*  74 */           while (pos < cnt) {
/*  75 */             long n = fcIn.transferTo(pos, fcIn.size(), fcOut);
/*  76 */             if (n == 0L) throw new IOException("File transfer error!!"); 
/*  77 */             pos += n;
/*     */           } 
/*     */         } finally {
/*  80 */           fcOut.close();
/*     */         }  }
/*     */       finally { 
/*  83 */         try { fcIn.close(); } catch (Exception exception) {} }
/*     */     
/*     */     } else {
/*  86 */       byte[] buf = new byte[8192];
/*  87 */       int n = 0;
/*  88 */       while ((n = in.read(buf)) > 0) {
/*  89 */         out.write(buf, 0, n);
/*  90 */         cnt += n;
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     return cnt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(Closeable stream) {
/* 102 */     boolean success = true;
/*     */     try {
/* 104 */       stream.close();
/* 105 */     } catch (IOException e) {
/* 106 */       success = false;
/*     */     } 
/* 108 */     return success;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int a(InputStream is, byte[] arrayOfByte, int len) throws IOException {
/* 117 */     int off = 0;
/* 118 */     while (len > 0) {
/* 119 */       int n = is.read(arrayOfByte, off, len);
/* 120 */       if (n <= 0)
/* 121 */         break;  off += n;
/* 122 */       len -= n;
/*     */     } 
/* 124 */     return (off == 0) ? -1 : off;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int a(InputStream is, byte[] arrayOfByte) throws IOException {
/* 133 */     return a(is, arrayOfByte, arrayOfByte.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(InputStream is, byte[] arrayOfByte) throws EOFException, IOException {
/* 144 */     b(is, arrayOfByte, arrayOfByte.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(InputStream is, byte[] arrayOfByte, int len) throws EOFException, IOException {
/* 155 */     if (a(is, arrayOfByte, len) != len) throw new EOFException();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(InputStream isA, InputStream isB) throws IOException {
/* 167 */     int len = 512;
/* 168 */     byte[] bufA = new byte[512];
/* 169 */     byte[] bufB = new byte[512];
/*     */ 
/*     */     
/*     */     while (true) {
/* 173 */       int lenA = a(isA, bufA);
/* 174 */       int lenB = a(isB, bufB);
/* 175 */       if (lenA != lenB) return false; 
/* 176 */       if (lenA < 0)
/* 177 */         break;  if (lenA == 512) {
/* 178 */         if (!Arrays.equals(bufA, bufB)) return false;  continue;
/*     */       } 
/* 180 */       for (int i = 0; i < lenA; i++) {
/* 181 */         if (bufA[i] != bufB[i]) return false;
/*     */       
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/b/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */