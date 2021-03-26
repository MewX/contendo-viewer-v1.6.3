/*     */ package jp.cssj.homare.css;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.e.d;
/*     */ import jp.cssj.homare.css.c.f;
/*     */ import jp.cssj.homare.css.c.g;
/*     */ import jp.cssj.homare.css.c.h;
/*     */ import jp.cssj.homare.impl.a.c.a.v;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.homare.xml.b.d;
/*     */ import jp.cssj.sakae.sac.css.CSSException;
/*     */ import jp.cssj.sakae.sac.css.DocumentHandler;
/*     */ import jp.cssj.sakae.sac.css.InputSource;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ import jp.cssj.sakae.sac.css.SACMediaList;
/*     */ import jp.cssj.sakae.sac.css.SelectorList;
/*     */ import jp.cssj.sakae.sac.parser.Parser;
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
/*     */ public class e
/*     */   implements DocumentHandler
/*     */ {
/*  43 */   private static final Logger a = Logger.getLogger(e.class.getName());
/*     */ 
/*     */   
/*     */   private static final boolean b = false;
/*     */ 
/*     */   
/*     */   private static final int c = 10;
/*     */ 
/*     */   
/*     */   private static final short d = 1;
/*     */ 
/*     */   
/*     */   private static final short e = 2;
/*     */   
/*     */   private static final short f = 3;
/*     */   
/*     */   private static final short g = 4;
/*     */   
/*     */   private static final short h = 5;
/*     */   
/*     */   private final m i;
/*     */   
/*     */   private final g j;
/*     */   
/*     */   private final g k;
/*     */   
/*     */   private String l;
/*     */   
/*  71 */   private final List<InputSource> m = new ArrayList<>();
/*     */ 
/*     */   
/*  74 */   private final List<URI> n = new ArrayList<>();
/*     */ 
/*     */   
/*  77 */   private final List<Boolean> o = new ArrayList<>();
/*     */ 
/*     */   
/*  80 */   private short p = 1;
/*     */   
/*     */   private d q;
/*     */   
/*     */   public e(m ua) {
/*  85 */     this.i = ua;
/*  86 */     this.j = new g(ua);
/*  87 */     this.j.a(f.b());
/*  88 */     this.k = new g(ua);
/*  89 */     this.k.a(f.b());
/*     */   }
/*     */   
/*     */   public void a(d cssStyleSheet) {
/*  93 */     this.q = cssStyleSheet;
/*     */   }
/*     */   
/*     */   public d a() {
/*  97 */     return this.q;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void comment(String text) throws CSSException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void ignorableAtRule(String atRule) throws CSSException {}
/*     */ 
/*     */   
/*     */   public void namespaceDeclaration(String prefix, String uri) throws CSSException {}
/*     */ 
/*     */   
/*     */   public void property(String name, LexicalUnit lu, boolean important) throws CSSException {
/* 113 */     switch (this.p) {
/*     */       case 1:
/*     */         return;
/*     */       
/*     */       case 5:
/* 118 */         this.k.property(name, lu, important);
/*     */     } 
/*     */     
/* 121 */     this.j.property(name, lu, important);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startDocument(InputSource source) throws CSSException {
/* 127 */     URI uri = URI.create(source.getURI());
/* 128 */     this.m.add(source);
/* 129 */     this.n.add(uri);
/* 130 */     this.j.a(uri);
/* 131 */     this.k.a(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endDocument(InputSource source) throws CSSException {
/* 138 */     this.m.remove(this.m.size() - 1);
/* 139 */     this.n.remove(this.n.size() - 1);
/* 140 */     if (!this.n.isEmpty()) {
/* 141 */       URI uri = this.n.get(this.n.size() - 1);
/* 142 */       this.j.a(uri);
/* 143 */       this.k.a(uri);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected InputSource b() {
/* 151 */     return this.m.get(this.m.size() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void importStyle(String href, SACMediaList media, String defaultNamespaceURI) throws CSSException {
/* 158 */     StringBuffer buff = null;
/* 159 */     for (int i = 0; i < media.getLength(); i++) {
/* 160 */       String medium = media.item(i);
/* 161 */       if (buff == null) {
/* 162 */         buff = new StringBuffer(medium);
/*     */       } else {
/* 164 */         buff.append(' ');
/* 165 */         buff.append(medium);
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     String mediaTypes = buff.toString();
/* 170 */     if (this.i.b(mediaTypes)) {
/* 171 */       URI uri; if (this.m.size() > 10) {
/* 172 */         URI uRI = this.n.get(this.n.size() - 1);
/* 173 */         this.i.a((short)10246, uRI.toString(), String.valueOf(10));
/*     */         return;
/*     */       } 
/* 176 */       URI baseURI = this.j.a();
/*     */       
/*     */       try {
/* 179 */         uri = d.a(this.i.c().c(), baseURI, href);
/* 180 */       } catch (URISyntaxException uRISyntaxException) {
/* 181 */         this.i.a((short)10243, href);
/*     */         return;
/*     */       } 
/* 184 */       for (int j = 0; j < this.n.size(); j++) {
/* 185 */         URI stackURI = this.n.get(j);
/* 186 */         if (stackURI.equals(uri)) {
/* 187 */           this.i.a((short)10247, baseURI.toString(), uri.toString());
/*     */           return;
/*     */         } 
/*     */       } 
/* 191 */       Parser parser = new Parser();
/* 192 */       parser.setDocumentHandler(this);
/*     */       try {
/* 194 */         b source = this.i.b(uri);
/*     */         try {
/* 196 */           InputSource inputSource = d.a(source, b().getEncoding(), mediaTypes, null);
/*     */           
/* 198 */           parser.setDefaultCharset(this.i.c().c());
/* 199 */           parser.parseStyleSheet(inputSource);
/*     */         } finally {
/* 201 */           this.i.a(source);
/*     */         } 
/* 203 */       } catch (CSSException cSSException) {
/* 204 */         this.i.a((short)10241, uri.toString(), cSSException.getMessage());
/* 205 */         a.log(Level.FINE, "CSS文法エラー", (Throwable)cSSException);
/* 206 */       } catch (IOException iOException) {
/* 207 */         this.i.a((short)10243, uri.toString());
/* 208 */         a.log(Level.FINE, "CSS読み込みエラー", iOException);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startMedia(SACMediaList media) throws CSSException {
/* 215 */     for (int i = 0; i < media.getLength(); i++) {
/* 216 */       String medium = media.item(i).toLowerCase();
/* 217 */       if (this.i.b(medium)) {
/* 218 */         this.o.add(Boolean.TRUE);
/*     */         return;
/*     */       } 
/*     */     } 
/* 222 */     this.o.add(Boolean.FALSE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void endMedia(SACMediaList media) throws CSSException {
/* 227 */     this.o.remove(this.o.size() - 1);
/*     */   }
/*     */   
/*     */   protected boolean c() {
/* 231 */     if (this.o.isEmpty()) {
/* 232 */       return true;
/*     */     }
/* 234 */     return ((Boolean)this.o.get(this.o.size() - 1)).booleanValue();
/*     */   }
/*     */   
/*     */   public void startPage(String name, String pseudoPage) throws CSSException {
/* 238 */     if ("-cssj-page-content".equalsIgnoreCase(pseudoPage)) {
/* 239 */       if (c()) {
/* 240 */         this.p = 5;
/*     */       }
/*     */       return;
/*     */     } 
/* 244 */     if (name != null) {
/* 245 */       URI uri = this.n.get(this.n.size() - 1);
/* 246 */       this.i.a((short)10241, uri.toString(), "名前つきページはサポートしていません");
/*     */       return;
/*     */     } 
/* 249 */     if (c()) {
/* 250 */       if (this.p != 1) {
/* 251 */         URI uri = this.n.get(this.n.size() - 1);
/* 252 */         this.i.a((short)10241, uri.toString(), "@pageルールがネストされています。");
/*     */       } 
/* 254 */       this.j.a((f)null);
/* 255 */       this.j.a(h.a());
/* 256 */       this.p = 3;
/* 257 */       this.l = pseudoPage;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endPage(String name, String pseudoPage) throws CSSException {
/* 262 */     if ("-cssj-page-content".equalsIgnoreCase(pseudoPage)) {
/* 263 */       if (c()) {
/* 264 */         this.q.a(name, this.l, this.k.b());
/* 265 */         this.k.a((f)null);
/*     */       } 
/* 267 */       this.p = 1;
/*     */       return;
/*     */     } 
/* 270 */     if (name != null) {
/*     */       return;
/*     */     }
/* 273 */     if (c()) {
/* 274 */       this.q.a(pseudoPage, this.j.b());
/* 275 */       this.p = 1;
/* 276 */       this.j.a((f)null);
/* 277 */       this.j.a(f.b());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startFontFace() throws CSSException {
/* 282 */     if (this.p != 1) {
/* 283 */       URI uri = this.n.get(this.n.size() - 1);
/* 284 */       this.i.a((short)10241, uri.toString(), "@font-faceルールがネストされています。");
/*     */     } 
/* 286 */     this.p = 4;
/* 287 */     this.j.a((f)null);
/* 288 */     this.j.a(g.a());
/*     */   }
/*     */   
/*     */   public void endFontFace() throws CSSException {
/* 292 */     f decl = this.j.b();
/* 293 */     if (decl == null) {
/*     */       return;
/*     */     }
/* 296 */     c style = c.a(this.i, (c)null, (a)null);
/* 297 */     decl.a(style);
/* 298 */     URI[] uris = v.c(style);
/* 299 */     if (uris != null) {
/* 300 */       boolean missing = true;
/* 301 */       for (int i = 0; i < uris.length; i++) {
/* 302 */         URI uri = uris[i];
/*     */         try {
/* 304 */           b src = null;
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
/*     */         }
/* 338 */         catch (Exception exception) {
/* 339 */           a.log(Level.FINE, "Font error", exception);
/*     */         } 
/*     */       } 
/* 342 */       if (missing) {
/* 343 */         this.i.a((short)10270, Arrays.<URI>asList(uris).toString());
/*     */       }
/*     */     } 
/*     */     
/* 347 */     this.p = 1;
/* 348 */     this.j.a((f)null);
/* 349 */     this.j.a(f.b());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startSelector(SelectorList selectors) throws CSSException {
/* 356 */     if (c()) {
/*     */       
/* 358 */       if (this.p != 1) {
/* 359 */         URI uri = this.n.get(this.n.size() - 1);
/* 360 */         this.i.a((short)10241, uri.toString(), "セレクタがネストされています。");
/*     */       } 
/* 362 */       this.j.a((f)null);
/* 363 */       this.p = 2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void endSelector(SelectorList selectors) throws CSSException {
/* 368 */     if (c()) {
/* 369 */       this.q.a(selectors, this.j.b());
/*     */       
/* 371 */       this.p = 1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */