/*     */ package jp.cssj.sakae.pdf.c;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.Collections;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.xml.parsers.SAXParserFactory;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.d;
/*     */ import jp.cssj.sakae.a.a.b;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.a.h;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.pdf.c.d.a;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.ParserAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends g
/*     */ {
/*  35 */   private static final Logger g = Logger.getLogger(h.class.getName());
/*     */   
/*     */   private final b h;
/*     */   
/*  39 */   private URI i = null;
/*     */   
/*  41 */   private d j = null;
/*     */   
/*  43 */   private static h k = null;
/*     */   
/*     */   public static final synchronized h a() {
/*  46 */     if (k == null) {
/*  47 */       URL url = a.class.getResource("builtin/fonts.xml");
/*     */       try {
/*  49 */         jp.cssj.e.i.a a1 = new jp.cssj.e.i.a(url);
/*  50 */         k = new a((b)a1, null);
/*  51 */       } catch (Exception e) {
/*  52 */         throw new RuntimeException(e);
/*     */       } 
/*     */     } 
/*  55 */     return k;
/*     */   }
/*     */   
/*     */   public a(b config) {
/*  59 */     this(config, (File)null);
/*     */   }
/*     */   
/*     */   public a(b config, File dbFile) {
/*  63 */     this.h = config;
/*  64 */     this.i = this.h.d();
/*  65 */     b();
/*     */   }
/*     */   protected synchronized void b() {
/*     */     XMLReader parser;
/*     */     try {
/*  70 */       if (!this.h.f()) {
/*  71 */         Exception e = new FileNotFoundException(this.h.d().toString());
/*  72 */         g.log(Level.SEVERE, this.h + "がありません", e);
/*  73 */         throw new RuntimeException(e);
/*     */       } 
/*  75 */     } catch (IOException e) {
/*  76 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/*  79 */     if (this.j != null && this.j.a() == 1 && this.i
/*  80 */       .equals(this.h.d())) {
/*     */       return;
/*     */     }
/*     */     
/*  84 */     g.fine(this.h.d() + "からフォントをDBを構築しています...");
/*  85 */     SAXParserFactory parserFactory = SAXParserFactory.newInstance();
/*     */     
/*     */     try {
/*  88 */       parser = new ParserAdapter(parserFactory.newSAXParser().getParser());
/*  89 */     } catch (Exception e) {
/*  90 */       throw new RuntimeException(e);
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*  95 */       h handler = new h(this.h.d());
/*  96 */       try (InputStream in = new BufferedInputStream(this.h.h())) {
/*  97 */         parser.setContentHandler(handler);
/*  98 */         parser.parse(new InputSource(in));
/*     */       } 
/*     */       
/* 101 */       this.i = this.h.d();
/* 102 */       this.j = this.h.m();
/*     */       
/* 104 */       b.a((g)b.i, handler.a);
/* 105 */       b.a((g)b.j, handler.a);
/*     */       
/* 107 */       this.a = a.a(handler.a);
/* 108 */       this.b = Collections.unmodifiableMap(handler.b);
/* 109 */       this.d = handler.c;
/*     */       
/* 111 */       this.e = null;
/*     */       
/* 113 */       g.fine("フォントをDBを構築しました");
/* 114 */     } catch (Exception e) {
/* 115 */       g.log(Level.SEVERE, this.h.d() + "を読み込めませんでした", e);
/* 116 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized g[] a(h fontStyle) {
/* 121 */     b();
/* 122 */     return super.a(fontStyle);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */