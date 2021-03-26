/*     */ package com.a.a.b.c;
/*     */ 
/*     */ import com.a.a.a.a;
/*     */ import com.a.a.h.e;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.SequenceInputStream;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ 
/*     */ public class c {
/*     */   private static final byte a = 31;
/*     */   private static final byte b = -117;
/*     */   
/*     */   public static long a(a info, String fname) {
/*  19 */     long size = info.a();
/*  20 */     size += 10L;
/*  21 */     if (fname != null && fname.length() > 0) {
/*  22 */       size += ((fname.getBytes()).length + 1);
/*     */     }
/*  24 */     size += 8L;
/*     */     
/*  26 */     return size;
/*     */   }
/*     */   
/*     */   public static InputStream a(InputStream deflateStream, a info, String fname) {
/*  30 */     ArrayList<InputStream> list = new ArrayList<InputStream>(3);
/*  31 */     a bu = new a(ByteOrder.LITTLE_ENDIAN);
/*  32 */     byte[] tmp = new byte[8];
/*     */     
/*     */     try {
/*  35 */       ByteArrayOutputStream out = null;
/*     */       try {
/*  37 */         out = new ByteArrayOutputStream();
/*     */ 
/*     */         
/*  40 */         out.write(31);
/*     */ 
/*     */         
/*  43 */         out.write(139);
/*     */ 
/*     */         
/*  46 */         out.write(info.c());
/*     */ 
/*     */         
/*  49 */         if (fname != null && fname.length() > 0) {
/*  50 */           out.write(8);
/*     */         } else {
/*  52 */           out.write(0);
/*     */         } 
/*     */ 
/*     */         
/*  56 */         out.write(0);
/*  57 */         out.write(0);
/*  58 */         out.write(0);
/*  59 */         out.write(0);
/*     */ 
/*     */         
/*  62 */         out.write(0);
/*     */ 
/*     */         
/*  65 */         out.write(0);
/*     */ 
/*     */         
/*  68 */         if (fname != null && fname.length() > 0) {
/*  69 */           byte[] b = fname.getBytes();
/*  70 */           out.write(b);
/*  71 */           out.write(0);
/*     */         } 
/*     */         
/*  74 */         out.flush();
/*  75 */         list.add(new ByteArrayInputStream(out.toByteArray()));
/*     */       } finally {
/*  77 */         out.close();
/*  78 */         out = null;
/*     */       } 
/*     */       
/*  81 */       list.add(deflateStream);
/*     */       
/*     */       try {
/*  84 */         out = new ByteArrayOutputStream();
/*     */ 
/*     */         
/*  87 */         bu.a(tmp, (int)(info.d() & 0xFFFFFFFFFFFFFFFFL), 0);
/*  88 */         out.write(tmp, 0, 4);
/*     */ 
/*     */         
/*  91 */         bu.a(tmp, (int)(info.b() & 0xFFFFFFFFFFFFFFFFL), 0);
/*  92 */         out.write(tmp, 0, 4);
/*     */         
/*  94 */         out.flush();
/*  95 */         list.add(new ByteArrayInputStream(out.toByteArray()));
/*     */       } finally {
/*  97 */         if (out != null) out.close();
/*     */       
/*     */       } 
/* 100 */     } catch (IOException e) {
/* 101 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/* 104 */     return new SequenceInputStream(
/* 105 */         (Enumeration<? extends InputStream>)new e(list.iterator()));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/c/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */