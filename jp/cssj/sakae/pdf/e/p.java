/*     */ package jp.cssj.sakae.pdf.e;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jp.cssj.f.a;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.g.b.d;
/*     */ import jp.cssj.sakae.pdf.h;
/*     */ import jp.cssj.sakae.pdf.k;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class p
/*     */   implements k
/*     */ {
/*  22 */   private final List<b> b = new ArrayList<>();
/*     */   
/*     */   private final b c;
/*     */   
/*     */   protected final j a;
/*     */   
/*     */   private Map<String, Object> d;
/*     */   
/*  30 */   private static final byte[] e = new byte[] { 37, 37, 69, 79, 70 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final byte[] f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a() {
/*  44 */     b ref = new f(this.b.size() + 1);
/*  45 */     this.b.add(ref);
/*  46 */     return ref;
/*     */   }
/*     */ 
/*     */   
/*     */   void a(a.a posInfo, b infoRef, byte[][] fileid, d encrypter) throws IOException {
/*  51 */     int xrefPosition = (int)posInfo.a(this.a.n()) + this.a.e();
/*     */ 
/*     */     
/*  54 */     ByteArrayOutputStream buff = new ByteArrayOutputStream();
/*  55 */     try (h trailerFlow = new h(buff, "ISO-8859-1")) {
/*  56 */       trailerFlow.b("trailer");
/*  57 */       trailerFlow.g();
/*     */       
/*  59 */       trailerFlow.a("Size");
/*  60 */       trailerFlow.a(this.b.size() + 1);
/*  61 */       trailerFlow.k();
/*     */       
/*  63 */       trailerFlow.a("Root");
/*  64 */       trailerFlow.b(this.c);
/*  65 */       trailerFlow.k();
/*     */       
/*  67 */       if (infoRef != null) {
/*  68 */         trailerFlow.a("Info");
/*  69 */         trailerFlow.b(infoRef);
/*  70 */         trailerFlow.k();
/*     */       } 
/*     */       
/*  73 */       if (fileid != null) {
/*  74 */         trailerFlow.a("ID");
/*  75 */         trailerFlow.i();
/*  76 */         trailerFlow.a(fileid[0], 0, (fileid[0]).length);
/*  77 */         trailerFlow.a(fileid[1], 0, (fileid[1]).length);
/*  78 */         trailerFlow.j();
/*  79 */         trailerFlow.k();
/*     */       } 
/*     */       
/*  82 */       if (encrypter != null) {
/*  83 */         trailerFlow.a("Encrypt");
/*  84 */         trailerFlow.b(encrypter.a());
/*  85 */         trailerFlow.k();
/*     */       } 
/*     */       
/*  88 */       trailerFlow.h();
/*     */       
/*  90 */       trailerFlow.b("startxref");
/*  91 */       trailerFlow.k();
/*  92 */       trailerFlow.a(xrefPosition);
/*     */       
/*  94 */       trailerFlow.k();
/*  95 */       trailerFlow.write(e);
/*  96 */       trailerFlow.k();
/*     */     } 
/*  98 */     String trailer = new String(buff.toByteArray(), "ISO-8859-1");
/*     */ 
/*     */ 
/*     */     
/* 102 */     this.a.b("xref");
/* 103 */     this.a.k();
/* 104 */     this.a.a(0);
/* 105 */     this.a.a(this.b.size() + 1);
/* 106 */     a(this.a, 0L, 65535, false);
/*     */ 
/*     */ 
/*     */     
/* 110 */     for (int i = 0; i < this.b.size(); i++) {
/* 111 */       f ref = (f)this.b.get(i);
/* 112 */       a(this.a, ref.a(posInfo), ref.b, true);
/*     */     } 
/*     */     
/* 115 */     this.a.g(trailer);
/*     */   }
/*     */   
/* 118 */   p(j mainFlow) throws IOException { this.f = new byte[10];
/*     */     this.a = mainFlow;
/*     */     this.c = a();
/*     */     this.a.a(this.c); } private void a(j out, long byteOffset, int generationNum, boolean inUse) throws IOException {
/* 122 */     out.m();
/*     */     
/* 124 */     String str = String.valueOf(byteOffset);
/* 125 */     int off = 10 - str.length(); int i;
/* 126 */     for (i = 0; i < off; i++) {
/* 127 */       this.f[i] = 48;
/*     */     }
/* 129 */     for (i = 0; i < str.length(); i++) {
/* 130 */       this.f[i + off] = (byte)str.charAt(i);
/*     */     }
/*     */     
/* 133 */     out.write(this.f, 0, 10);
/* 134 */     out.write(32);
/*     */ 
/*     */     
/* 137 */     str = String.valueOf(generationNum);
/* 138 */     off = 5 - str.length();
/* 139 */     for (i = 0; i < off; i++) {
/* 140 */       this.f[i] = 48;
/*     */     }
/* 142 */     for (i = 0; i < str.length(); i++) {
/* 143 */       this.f[i + off] = (byte)str.charAt(i);
/*     */     }
/*     */     
/* 146 */     out.write(this.f, 0, 5);
/* 147 */     out.write(32);
/*     */     
/* 149 */     out.write(inUse ? 110 : 102);
/* 150 */     out.k();
/*     */   }
/*     */   
/*     */   public Object a(String key) {
/* 154 */     if (this.d == null) {
/* 155 */       return null;
/*     */     }
/* 157 */     return this.d.get(key);
/*     */   }
/*     */   
/*     */   public void a(String key, Object value) {
/* 161 */     if (this.d == null) {
/* 162 */       this.d = new HashMap<>();
/*     */     }
/* 164 */     this.d.put(key, value);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/e/p.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */