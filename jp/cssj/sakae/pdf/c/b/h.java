/*     */ package jp.cssj.sakae.pdf.c.b;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.sakae.e.c;
/*     */ import jp.cssj.sakae.e.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class h
/*     */   extends c
/*     */ {
/*  24 */   private static final Logger k = Logger.getLogger(h.class.getName());
/*     */   
/*     */   private static final long l = 0L;
/*     */   
/*     */   private final b m;
/*     */   
/*  30 */   private transient WeakReference<c> n = null;
/*     */   
/*  32 */   private transient WeakReference<e[]> o = null;
/*     */   
/*     */   public h(a fontInfo, b toUnicodeFile) throws IOException {
/*  35 */     super(fontInfo);
/*  36 */     this.m = toUnicodeFile;
/*  37 */     if (k.isLoggable(Level.FINE)) {
/*  38 */       k.fine("new font: " + d());
/*     */     }
/*     */   }
/*     */   
/*     */   protected synchronized e[] p() {
/*  43 */     e[] gidToGi = null;
/*  44 */     if (this.o != null) {
/*  45 */       gidToGi = this.o.get();
/*  46 */       if (gidToGi != null) {
/*  47 */         return gidToGi;
/*     */       }
/*     */     } 
/*  50 */     if (k.isLoggable(Level.FINE)) {
/*  51 */       k.fine("build gidToGi: " + d());
/*     */     }
/*  53 */     gidToGi = new e[256];
/*  54 */     for (Iterator<a.a> i = this.i.m.values().iterator(); i.hasNext(); ) {
/*  55 */       c sgidToLigature; e sgidToKerning; a.a aci = i.next();
/*  56 */       if (aci.a == -1) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/*  61 */       if (aci.d != null) {
/*  62 */         sgidToLigature = new c(-1);
/*  63 */         for (Iterator<Map.Entry<String, String>> j = aci.d.entrySet().iterator(); j.hasNext(); ) {
/*  64 */           Map.Entry<String, String> e = j.next();
/*  65 */           a.a sci = this.i.m.get(e.getKey());
/*  66 */           a.a lci = this.i.m.get(e.getValue());
/*  67 */           sgidToLigature.a(sci.a, lci.a);
/*     */         } 
/*  69 */         if (sgidToLigature.d()) {
/*  70 */           sgidToLigature = null;
/*     */         } else {
/*  72 */           sgidToLigature.c();
/*     */         } 
/*     */       } else {
/*  75 */         sgidToLigature = null;
/*     */       } 
/*     */       
/*  78 */       if (aci.e != null) {
/*  79 */         sgidToKerning = new e();
/*  80 */         for (Iterator<Map.Entry<String, Short>> j = aci.e.entrySet().iterator(); j.hasNext(); ) {
/*  81 */           Map.Entry<String, Short> e = j.next();
/*  82 */           Short kerning = e.getValue();
/*  83 */           a.a sci = this.i.m.get(e.getKey());
/*  84 */           sgidToKerning.a(sci.a, kerning.shortValue());
/*     */         } 
/*  86 */         if (sgidToKerning.d()) {
/*  87 */           sgidToKerning = null;
/*     */         } else {
/*  89 */           sgidToKerning.c();
/*     */         } 
/*     */       } else {
/*  92 */         sgidToKerning = null;
/*     */       } 
/*     */       
/*  95 */       gidToGi[aci.a] = new e(aci.c, (sgidToLigature == null) ? null : sgidToLigature.a(), (sgidToKerning == null) ? null : sgidToKerning
/*  96 */           .a());
/*     */     } 
/*  98 */     this.o = (WeakReference)new WeakReference<>(gidToGi);
/*  99 */     return gidToGi;
/*     */   }
/*     */   
/*     */   private synchronized c s() {
/* 103 */     c charToGid = null;
/* 104 */     synchronized (this) {
/* 105 */       if (this.n != null) {
/* 106 */         charToGid = this.n.get();
/* 107 */         if (charToGid != null) {
/* 108 */           return charToGid;
/*     */         }
/*     */       } 
/*     */     } 
/* 112 */     if (k.isLoggable(Level.FINE)) {
/* 113 */       k.fine("build charToGid: " + d());
/*     */     }
/* 115 */     charToGid = new c(-1);
/*     */     
/* 117 */     try (BufferedReader in = new BufferedReader(new InputStreamReader(this.m
/* 118 */             .h(), "ISO-8859-1"))) {
/* 119 */       for (String line = in.readLine(); line != null; line = in.readLine()) {
/* 120 */         if (!line.startsWith("#")) {
/*     */ 
/*     */           
/* 123 */           String[] pair = line.split("\t");
/* 124 */           if (pair.length >= 2)
/*     */           
/*     */           { 
/* 127 */             int unicode = Integer.parseInt(pair[0].trim(), 16);
/* 128 */             int gid = Integer.parseInt(pair[1].trim(), 16);
/* 129 */             charToGid.a(unicode, gid); } 
/*     */         } 
/* 131 */       }  charToGid.c();
/*     */     }
/* 133 */     catch (Exception e) {
/* 134 */       throw new RuntimeException(e);
/*     */     } 
/* 136 */     this.n = new WeakReference<>(charToGid);
/* 137 */     return charToGid;
/*     */   }
/*     */   
/*     */   int c(int i) {
/* 141 */     int gid = s().b(i);
/* 142 */     return gid;
/*     */   }
/*     */   
/*     */   String r() {
/* 146 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */