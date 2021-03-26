/*     */ package jp.cssj.homare.impl.formatter.epub;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.ZipFile;
/*     */ import jp.cssj.b.d;
/*     */ import jp.cssj.c.b;
/*     */ import jp.cssj.d.a.a;
/*     */ import jp.cssj.d.a.a.a;
/*     */ import jp.cssj.d.a.b;
/*     */ import jp.cssj.d.a.d;
/*     */ import jp.cssj.d.a.e;
/*     */ import jp.cssj.d.a.f;
/*     */ import jp.cssj.d.a.g;
/*     */ import jp.cssj.d.a.o;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.b.a;
/*     */ import jp.cssj.e.c;
/*     */ import jp.cssj.e.e.d;
/*     */ import jp.cssj.e.j.a;
/*     */ import jp.cssj.e.j.b;
/*     */ import jp.cssj.homare.a.a;
/*     */ import jp.cssj.homare.css.a;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.formatter.Formatter;
/*     */ import jp.cssj.homare.impl.formatter.document.b;
/*     */ import jp.cssj.homare.ua.a;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.a.b;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.ParserFactory;
/*     */ import jp.cssj.homare.xml.e;
/*     */ import jp.cssj.homare.xml.g;
/*     */ import jp.cssj.homare.xml.i;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.xml.sax.ContentHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EPubFormatter
/*     */   implements Formatter
/*     */ {
/*  59 */   private static final Logger a = Logger.getLogger(EPubFormatter.class.getName());
/*     */   
/*  61 */   public static final b REPLACE_NUMBERS = new b("x.jp.cssj.homare.impl.formatter.epub.replace-numbers", false);
/*     */ 
/*     */   
/*     */   public boolean match(b key) {
/*  65 */     b source = key;
/*     */     try {
/*  67 */       String uri = source.d().toString();
/*  68 */       if (uri.length() >= 5 && uri.substring(uri.length() - 5).equalsIgnoreCase(".epub")) {
/*  69 */         return true;
/*     */       }
/*  71 */       String mimeType = source.c();
/*  72 */       if (mimeType != null && mimeType.equals("application/epub+zip")) {
/*  73 */         return true;
/*     */       }
/*  75 */     } catch (IOException e) {
/*  76 */       a.log(Level.WARNING, "変換元文書のMIME型を取得できませんでした", e);
/*     */     } 
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   private a a(m ua, boolean leftBind) {
/*  82 */     a pageElement = ua.b().b();
/*  83 */     switch (B.x.a(ua)) {
/*     */       
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*  88 */         if (leftBind) {
/*     */           
/*  90 */           if (pageElement == null) {
/*  91 */             pageElement = a.i;
/*  92 */           } else if (pageElement == a.i) {
/*  93 */             pageElement = a.j;
/*  94 */           } else if (pageElement == a.j) {
/*  95 */             pageElement = a.k;
/*  96 */           } else if (pageElement == a.k) {
/*  97 */             pageElement = a.j;
/*     */           }
/*     */         
/*     */         }
/* 101 */         else if (pageElement == null) {
/* 102 */           pageElement = a.l;
/* 103 */         } else if (pageElement == a.l) {
/* 104 */           pageElement = a.k;
/* 105 */         } else if (pageElement == a.k) {
/* 106 */           pageElement = a.j;
/* 107 */         } else if (pageElement == a.j) {
/* 108 */           pageElement = a.k;
/*     */         } 
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
/* 125 */         return pageElement;case 1: if (pageElement == null) { pageElement = a.o; } else { pageElement = a.p; }  return pageElement;
/*     */     } 
/*     */     throw new IllegalStateException();
/*     */   } public void format(b source, m ua) throws a, d {
/*     */     try {
/*     */       File epubFile;
/* 131 */       if (source.k()) {
/* 132 */         epubFile = source.l();
/*     */       } else {
/* 134 */         epubFile = File.createTempFile("epub", ".epub");
/* 135 */         try (OutputStream out = new FileOutputStream(epubFile)) {
/* 136 */           InputStream in = source.h();
/* 137 */           IOUtils.copy(in, out);
/*     */         } 
/*     */       } 
/*     */       
/* 141 */       try (ZipFile zip = new ZipFile(epubFile)) {
/*     */         
/* 143 */         a resolver = new a();
/* 144 */         resolver.a("zip", (c)new b(zip));
/* 145 */         resolver.a(ua.p());
/* 146 */         resolver.c("zip");
/* 147 */         ua.a((c)resolver);
/*     */ 
/*     */         
/* 150 */         e epub = new e((a)new o(epubFile, zip));
/* 151 */         b container = epub.a();
/*     */         
/* 153 */         b.a root = container.a[0];
/* 154 */         d contents = epub.a(root);
/*     */ 
/*     */         
/* 157 */         boolean leftBind = true;
/* 158 */         switch (contents.s) {
/*     */           case 1:
/* 160 */             ua.a(B.x.a(), "left-side");
/*     */             break;
/*     */           case 2:
/* 163 */             ua.a(B.x.a(), "right-side");
/* 164 */             leftBind = false;
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 169 */         Map<URI, f> fullPathToItem = new HashMap<>(); int i;
/* 170 */         for (i = 0; i < contents.r.length; i++) {
/* 171 */           g ir = contents.r[i];
/* 172 */           fullPathToItem.put(URI.create(ir.a.d), ir.a);
/*     */         } 
/*     */ 
/*     */         
/* 176 */         for (i = 0; i < contents.r.length; i++) {
/* 177 */           a e; g ir = contents.r[i];
/* 178 */           switch (ir.e) {
/*     */             case 1:
/* 180 */               e = a(ua, leftBind);
/* 181 */               if (e.a((byte)2)) {
/* 182 */                 String ws = B.s.a(ua);
/* 183 */                 a wl = l.b(ua, false, ws);
/* 184 */                 String hs = B.t.a(ua);
/* 185 */                 a hl = l.b(ua, false, hs);
/* 186 */                 ws = B.v.a(ua);
/* 187 */                 if (ws != null) {
/* 188 */                   wl = l.b(ua, false, ws);
/*     */                 }
/* 190 */                 hs = B.w.a(ua);
/* 191 */                 if (hs != null) {
/* 192 */                   hl = l.b(ua, false, hs);
/*     */                 }
/* 194 */                 b gc = ua.a(wl.c(), hl.c());
/* 195 */                 ua.a(gc);
/*     */               } 
/*     */               break;
/*     */             
/*     */             case 2:
/* 200 */               e = a(ua, leftBind);
/* 201 */               if (e.a((byte)3)) {
/* 202 */                 String ws = B.s.a(ua);
/* 203 */                 a wl = l.b(ua, false, ws);
/* 204 */                 String hs = B.t.a(ua);
/* 205 */                 a hl = l.b(ua, false, hs);
/* 206 */                 ws = B.v.a(ua);
/* 207 */                 if (ws != null) {
/* 208 */                   wl = l.b(ua, false, ws);
/*     */                 }
/* 210 */                 hs = B.w.a(ua);
/* 211 */                 if (hs != null) {
/* 212 */                   hl = l.b(ua, false, hs);
/*     */                 }
/* 214 */                 b gc = ua.a(wl.c(), hl.c());
/* 215 */                 ua.a(gc);
/*     */               } 
/*     */               break;
/*     */           } 
/*     */           
/* 220 */           URI path = d.a("UTF-8", ir.a.d);
/* 221 */           ua.c().a(path);
/* 222 */           a a = new a(zip, path, ir.a.b);
/* 223 */           String mimeType = a.c();
/* 224 */           if (mimeType.equals("application/xhtml+xml")) {
/*     */             
/* 226 */             ParserFactory pf = (ParserFactory)b.a().a(ParserFactory.class, mimeType);
/* 227 */             e parser = pf.createParser();
/* 228 */             b b1 = new b(ua);
/* 229 */             a a1 = new a((g)b1, ir.a, fullPathToItem);
/* 230 */             boolean replaceNumbers = REPLACE_NUMBERS.a(ua);
/* 231 */             a xhandler = new a((ContentHandler)a1, ir.a, replaceNumbers);
/* 232 */             i i1 = new i((ContentHandler)xhandler, null);
/* 233 */             parser.a(ua, (b)a, (g)i1);
/*     */           } else {
/* 235 */             Formatter formatter = (Formatter)b.a().a(Formatter.class, a);
/*     */             
/* 237 */             formatter.format((b)a, ua);
/*     */           } 
/*     */         } 
/*     */       } finally {
/*     */         
/* 242 */         if (!source.k()) {
/* 243 */           epubFile.delete();
/*     */         }
/*     */       } 
/* 246 */     } catch (Exception e) {
/* 247 */       short code = 14591;
/* 248 */       String[] args = { "jp.cssj.plugins.epub", e.getLocalizedMessage() };
/* 249 */       String mes = a.b(code, args);
/* 250 */       ua.a(code, args);
/* 251 */       a.log(Level.WARNING, mes, e);
/* 252 */       throw new d(code, args, mes);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/formatter/epub/EPubFormatter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */