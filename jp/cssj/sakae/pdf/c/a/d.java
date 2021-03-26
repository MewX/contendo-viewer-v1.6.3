/*     */ package jp.cssj.sakae.pdf.c.a;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URISyntaxException;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.b.a;
/*     */ import jp.cssj.e.c;
/*     */ import jp.cssj.sakae.e.c;
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
/*     */ 
/*     */ class d
/*     */ {
/*     */   private Reader a;
/*     */   private int b;
/*     */   private c c;
/*     */   
/*     */   public int[] a(b source) throws IOException, g {
/*  32 */     c toCID = new c();
/*  33 */     a(source, toCID);
/*  34 */     return toCID.a();
/*     */   }
/*     */   
/*     */   private void a(b source, c toCID) throws IOException, g {
/*  38 */     this.a = new InputStreamReader(new BufferedInputStream(source.h()), "ISO-8859-1");
/*     */     try {
/*     */       try {
/*  41 */         String ptoken = null;
/*     */         while (true) {
/*  43 */           String token = c();
/*  44 */           if (token.equals("usecmap")) {
/*  45 */             d parser = new d();
/*  46 */             String href = ptoken.substring(1);
/*  47 */             if (this.c == null) {
/*  48 */               this.c = (c)a.a();
/*     */             }
/*  50 */             b source2 = this.c.b(jp.cssj.e.e.d.a("UTF-8", source.d(), href));
/*     */             try {
/*  52 */               parser.a(source2, toCID);
/*     */             } finally {
/*  54 */               this.c.a(source2);
/*     */             } 
/*  56 */           } else if (token.equals("begincidrange")) {
/*  57 */             b(toCID);
/*  58 */           } else if (token.equals("begincidchar")) {
/*  59 */             a(toCID);
/*  60 */           } else if (token.equals("/CIDSystemInfo")) {
/*  61 */             a();
/*     */           } 
/*  63 */           ptoken = token;
/*     */         } 
/*  65 */       } catch (EOFException eOFException) {
/*     */       
/*  67 */       } catch (URISyntaxException e) {
/*  68 */         IOException ioe = new IOException();
/*  69 */         ioe.initCause(e);
/*  70 */         throw ioe;
/*     */       } 
/*     */     } finally {
/*  73 */       this.a.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(c toCID) throws IOException, g {
/*     */     while (true) {
/*  79 */       String a = c();
/*  80 */       if (a.equals("endcidchar")) {
/*     */         break;
/*     */       }
/*  83 */       String b = c();
/*  84 */       a = a.substring(1, a.length() - 1).trim();
/*     */       try {
/*  86 */         int code = Integer.parseInt(a, 16);
/*  87 */         int offset = Integer.parseInt(b);
/*  88 */         toCID.a(code, offset);
/*  89 */       } catch (NumberFormatException numberFormatException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void b(c toCID) throws IOException, g {
/*     */     while (true) {
/*  97 */       String a = c();
/*  98 */       if (a.equals("endcidrange")) {
/*     */         break;
/*     */       }
/* 101 */       String b = c();
/* 102 */       String str1 = c();
/*     */       
/* 104 */       a = a.substring(1, a.length() - 1).trim();
/* 105 */       b = b.substring(1, b.length() - 1).trim();
/*     */       try {
/* 107 */         int start = Integer.parseInt(a, 16);
/* 108 */         int end = Integer.parseInt(b, 16);
/* 109 */         if (a.length() != b.length() || a.length() % 2 != 0) {
/* 110 */           throw new g("開始位置と終了位置のキャラクターコードのバイト数が一致しないか、偶数桁の１16進数になっていません");
/*     */         }
/* 112 */         int offset = Integer.parseInt(str1);
/*     */         
/* 114 */         int len = end - start + 1;
/* 115 */         for (int j = 0; j < len; j++) {
/* 116 */           toCID.a(start + j, offset + j);
/*     */         }
/* 118 */       } catch (NumberFormatException numberFormatException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a() throws IOException {
/* 125 */     c();
/* 126 */     String dict = c();
/* 127 */     if (!dict.equals("dict")) {
/*     */       return;
/*     */     }
/* 130 */     String dup = c();
/* 131 */     if (!dup.equals("dup")) {
/*     */       return;
/*     */     }
/* 134 */     String begin = c();
/* 135 */     if (!begin.equals("begin")) {
/*     */       return;
/*     */     }
/*     */     while (true) {
/* 139 */       String key = c();
/* 140 */       if (key.equals("/Registry")) {
/* 141 */         c();
/* 142 */         String def = c();
/* 143 */         if (!def.equals("def"))
/*     */           return;  continue;
/*     */       } 
/* 146 */       if (key.equals("/Ordering")) {
/* 147 */         c();
/* 148 */         String def = c();
/* 149 */         if (!def.equals("def"))
/*     */           return;  continue;
/*     */       } 
/* 152 */       if (key.equals("/Supplement")) {
/* 153 */         c();
/* 154 */         String def = c();
/* 155 */         if (!def.equals("def"))
/*     */           return;  continue;
/*     */       } 
/* 158 */       if (key.equals("end")) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void b() throws IOException {
/* 165 */     for (; this.b != -1 && Character.isWhitespace((char)this.b); this.b = this.a.read());
/*     */ 
/*     */     
/* 168 */     if (this.b == -1) {
/* 169 */       throw new EOFException();
/*     */     }
/*     */   }
/*     */   
/*     */   private String c() throws IOException {
/* 174 */     b();
/* 175 */     StringBuffer buff = new StringBuffer();
/* 176 */     for (; this.b != -1 && !Character.isWhitespace((char)this.b); this.b = this.a.read()) {
/* 177 */       buff.append((char)this.b);
/*     */     }
/* 179 */     if (this.b == -1) {
/* 180 */       throw new EOFException();
/*     */     }
/* 182 */     String s = buff.toString();
/* 183 */     return a.b(s, "MS932");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */