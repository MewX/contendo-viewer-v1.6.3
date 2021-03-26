/*     */ package jp.cssj.sakae.pdf.c.a;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import jp.cssj.sakae.pdf.g.a;
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
/*     */ public class h
/*     */ {
/*     */   private Reader a;
/*     */   private int b;
/*     */   private f c;
/*     */   
/*     */   public f a(InputStream in, f cmap) throws IOException {
/*  27 */     this.c = cmap;
/*  28 */     this.a = new InputStreamReader(new BufferedInputStream(in), "ISO-8859-1");
/*     */     try {
/*     */       while (true) {
/*     */         try {
/*  32 */           String token = c();
/*  33 */           if (token.equals("begincidrange"))
/*     */             break; 
/*  35 */           if (token.equals("/CIDSystemInfo")) {
/*  36 */             a(); continue;
/*  37 */           }  if (token.equals("/CMapName")) {
/*  38 */             token = c();
/*  39 */             this.c.b = token.substring(1);
/*     */           }
/*     */         
/*  42 */         } catch (EOFException eOFException) {
/*     */           break;
/*     */         } 
/*  45 */       }  return this.c;
/*     */     } finally {
/*  47 */       this.a.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a() throws IOException {
/*  52 */     c();
/*  53 */     String dict = c();
/*  54 */     if (!dict.equals("dict")) {
/*     */       return;
/*     */     }
/*  57 */     String dup = c();
/*  58 */     if (!dup.equals("dup")) {
/*     */       return;
/*     */     }
/*  61 */     String begin = c();
/*  62 */     if (!begin.equals("begin")) {
/*     */       return;
/*     */     }
/*     */     while (true) {
/*  66 */       String key = c();
/*  67 */       if (key.equals("/Registry")) {
/*  68 */         String registry = c();
/*  69 */         this.c.c = a(registry);
/*  70 */         String def = c();
/*  71 */         if (!def.equals("def"))
/*     */           return;  continue;
/*     */       } 
/*  74 */       if (key.equals("/Ordering")) {
/*  75 */         String ordering = c();
/*  76 */         this.c.d = a(ordering);
/*  77 */         String def = c();
/*  78 */         if (!def.equals("def"))
/*     */           return;  continue;
/*     */       } 
/*  81 */       if (key.equals("/Supplement")) {
/*  82 */         String supplement = c();
/*  83 */         this.c.e = Integer.parseInt(supplement.trim());
/*  84 */         String def = c();
/*  85 */         if (!def.equals("def"))
/*     */           return;  continue;
/*     */       } 
/*  88 */       if (key.equals("end")) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String a(String token) {
/*  95 */     token = token.trim();
/*  96 */     token = token.substring(1, token.length() - 1);
/*  97 */     return token;
/*     */   }
/*     */   
/*     */   private void b() throws IOException {
/* 101 */     for (; this.b != -1 && Character.isWhitespace((char)this.b); this.b = this.a.read());
/*     */ 
/*     */     
/* 104 */     if (this.b == -1) {
/* 105 */       throw new EOFException();
/*     */     }
/*     */   }
/*     */   
/*     */   private String c() throws IOException {
/* 110 */     b();
/* 111 */     StringBuffer buff = new StringBuffer();
/* 112 */     for (; this.b != -1 && !Character.isWhitespace((char)this.b); this.b = this.a.read()) {
/* 113 */       buff.append((char)this.b);
/*     */     }
/* 115 */     if (this.b == -1) {
/* 116 */       throw new EOFException();
/*     */     }
/* 118 */     String s = buff.toString();
/* 119 */     return a.b(s, "MS932");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */