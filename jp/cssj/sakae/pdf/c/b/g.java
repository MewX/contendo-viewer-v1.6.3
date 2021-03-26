/*     */ package jp.cssj.sakae.pdf.c.b;
/*     */ 
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.sakae.e.c;
/*     */ import jp.cssj.sakae.e.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */   extends c
/*     */ {
/*     */   static {
/*  22 */     l = Logger.getLogger(g.class.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   private transient WeakReference<Object[]> p = null; private final d o; private final f n;
/*     */   
/*     */   public g(f unicodeEncoding, d pdfEncoding, a fontInfo) {
/*  33 */     super(fontInfo);
/*  34 */     this.o = pdfEncoding;
/*  35 */     this.n = unicodeEncoding;
/*  36 */     if (l.isLoggable(Level.FINE))
/*  37 */       l.fine("new font: " + d()); 
/*     */   }
/*     */   private static final long m = 0L; private static final Logger l;
/*     */   
/*     */   private synchronized Object[] t() {
/*  42 */     Object[] pair = null;
/*  43 */     if (this.p != null) {
/*  44 */       pair = this.p.get();
/*  45 */       if (pair != null) {
/*  46 */         return pair;
/*     */       }
/*     */     } 
/*  49 */     if (l.isLoggable(Level.FINE)) {
/*  50 */       l.fine("build GlyphInfo: " + d());
/*     */     }
/*     */     
/*  53 */     c charToGid = new c(-1);
/*  54 */     e[] gidToGi = new e[256]; int i;
/*  55 */     for (i = 0; i < this.o.b.length; i++) {
/*  56 */       d.a pc = this.o.b[i];
/*  57 */       f.a uc = this.n.a.get(pc.b);
/*  58 */       for (int j = 0; j < uc.a.length; j++) {
/*  59 */         int code = uc.a[j];
/*  60 */         charToGid.a(code, pc.a);
/*     */       } 
/*     */     } 
/*  63 */     charToGid.c();
/*     */     
/*  65 */     for (i = 0; i < this.o.b.length; i++) {
/*  66 */       c sgidToLigature; e sgidToKerning; d.a codeMap = this.o.b[i];
/*  67 */       int gid = codeMap.a;
/*  68 */       String name = codeMap.b;
/*  69 */       a.a aci = this.i.m.get(name);
/*  70 */       if (!k && aci == null) throw new AssertionError(d() + "/" + name);
/*     */ 
/*     */       
/*  73 */       if (aci.d != null) {
/*  74 */         sgidToLigature = new c(-1);
/*  75 */         for (Iterator<Map.Entry<String, String>> j = aci.d.entrySet().iterator(); j.hasNext(); ) {
/*  76 */           Map.Entry<String, String> e = j.next();
/*  77 */           d.a scm = this.o.c.get(e.getKey());
/*  78 */           if (scm == null) {
/*     */             continue;
/*     */           }
/*  81 */           d.a lcm = this.o.c.get(e.getValue());
/*  82 */           if (lcm == null) {
/*     */             continue;
/*     */           }
/*  85 */           sgidToLigature.a(scm.a, lcm.a);
/*     */         } 
/*  87 */         if (sgidToLigature.d()) {
/*  88 */           sgidToLigature = null;
/*     */         } else {
/*  90 */           sgidToLigature.c();
/*     */         } 
/*     */       } else {
/*  93 */         sgidToLigature = null;
/*     */       } 
/*     */       
/*  96 */       if (aci.e != null) {
/*  97 */         sgidToKerning = new e();
/*  98 */         for (Iterator<Map.Entry<String, Short>> j = aci.e.entrySet().iterator(); j.hasNext(); ) {
/*  99 */           Map.Entry<String, Short> e = j.next();
/* 100 */           String sname = e.getKey();
/* 101 */           Short kerning = e.getValue();
/* 102 */           d.a scm = this.o.c.get(sname);
/* 103 */           if (scm == null) {
/*     */             continue;
/*     */           }
/* 106 */           sgidToKerning.a(scm.a, kerning.shortValue());
/*     */         } 
/* 108 */         if (sgidToKerning.d()) {
/* 109 */           sgidToKerning = null;
/*     */         } else {
/* 111 */           sgidToKerning.c();
/*     */         } 
/*     */       } else {
/* 114 */         sgidToKerning = null;
/*     */       } 
/*     */       
/* 117 */       gidToGi[gid] = new e(aci.c, (sgidToLigature == null) ? null : sgidToLigature.a(), (sgidToKerning == null) ? null : sgidToKerning
/* 118 */           .a());
/*     */     } 
/* 120 */     pair = new Object[] { charToGid, gidToGi };
/* 121 */     this.p = new WeakReference(pair);
/* 122 */     return pair;
/*     */   }
/*     */   
/*     */   protected e[] p() {
/* 126 */     return (e[])t()[1];
/*     */   }
/*     */   
/*     */   protected c s() {
/* 130 */     return (c)t()[0];
/*     */   }
/*     */   
/*     */   int c(int i) {
/* 134 */     return s().b(i);
/*     */   }
/*     */   
/*     */   String r() {
/* 138 */     return this.o.a;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */