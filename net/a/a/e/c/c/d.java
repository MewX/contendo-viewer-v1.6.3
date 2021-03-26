/*     */ package net.a.a.e.c.c;
/*     */ 
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.a.a.c;
/*     */ import net.a.a.e.d.b;
/*     */ import net.a.a.e.d.c;
/*     */ import net.a.a.e.d.e;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.mathml.MathMLElement;
/*     */ import org.w3c.dom.mathml.MathMLMultiScriptsElement;
/*     */ import org.w3c.dom.mathml.MathMLNodeList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class d
/*     */   extends a
/*     */   implements MathMLMultiScriptsElement
/*     */ {
/*     */   public static final String t = "mmultiscripts";
/*     */   private static final long u = 1L;
/*     */   private static final int v = 0;
/*     */   private static final int w = 1;
/*     */   private static final int x = 2;
/*     */   private static final int y = 3;
/*  69 */   private final List<net.a.a.e.d> z = new ArrayList<net.a.a.e.d>();
/*     */   
/*  71 */   private final List<net.a.a.e.d> A = new ArrayList<net.a.a.e.d>();
/*     */   
/*  73 */   private final List<net.a.a.e.d> B = new ArrayList<net.a.a.e.d>();
/*     */   
/*  75 */   private final List<net.a.a.e.d> C = new ArrayList<net.a.a.e.d>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d(String paramString, AbstractDocument paramAbstractDocument) {
/*  88 */     super(paramString, paramAbstractDocument);
/*     */     
/*  90 */     this.D = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  96 */     return (Node)new d(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void h() {
/* 102 */     super.h();
/* 103 */     if (!this.D) {
/* 104 */       i();
/*     */     }
/*     */   }
/*     */   
/*     */   private void i() {
/* 109 */     this.B.clear();
/* 110 */     this.C.clear();
/* 111 */     this.z.clear();
/* 112 */     this.A.clear();
/*     */     
/* 114 */     int i = e();
/*     */     
/* 116 */     byte b1 = 0;
/* 117 */     for (byte b2 = 1; b2 < i; b2++) {
/* 118 */       net.a.a.e.d d1 = a(b2);
/* 119 */       if (d1 instanceof f) {
/* 120 */         b1 = 2;
/*     */       }
/* 122 */       else if (b1 == 0) {
/* 123 */         this.z.add(d1);
/* 124 */         b1 = 1;
/* 125 */       } else if (b1 == 1) {
/* 126 */         this.A.add(d1);
/* 127 */         b1 = 0;
/* 128 */       } else if (b1 == 2) {
/* 129 */         this.B.add(d1);
/* 130 */         b1 = 3;
/*     */       } else {
/* 132 */         this.C.add(d1);
/* 133 */         b1 = 2;
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     if (this.A.size() < this.z.size()) {
/* 138 */       this.A.add((net.a.a.e.d)getOwnerDocument()
/* 139 */           .createElement("none"));
/*     */     }
/* 141 */     if (this.C.size() < this.B.size()) {
/* 142 */       this.C.add((net.a.a.e.d)getOwnerDocument()
/* 143 */           .createElement("none"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(g paramg, net.a.a.g.d paramd, f paramf, c paramc) {
/* 152 */     net.a.a.e.d d1 = a();
/* 153 */     net.a.a.g.d d2 = paramg.a((i)d1);
/* 154 */     c c1 = b(paramc);
/*     */     
/* 156 */     String str1 = getSubscriptshift();
/* 157 */     String str2 = getSuperscriptshift();
/*     */ 
/*     */     
/* 160 */     m.a a1 = a(paramg, paramf, d2, c1, str1, str2);
/*     */     
/* 162 */     float f1 = 0.0F;
/* 163 */     float f2 = a1.b();
/* 164 */     float f3 = a1.a(); byte b1;
/* 165 */     for (b1 = 0; b1 < this.B.size(); b1++) {
/* 166 */       net.a.a.g.d d3 = paramg.a((i)this.B.get(b1));
/* 167 */       net.a.a.g.d d4 = paramg.a((i)this.C
/* 168 */           .get(b1));
/* 169 */       d3.a(f1, f2, paramf);
/* 170 */       d4.a(f1, -f3, paramf);
/* 171 */       f1 += 
/* 172 */         Math.max(d3.d(paramf), d4.d(paramf));
/*     */     } 
/* 174 */     d2.a(f1, 0.0F, paramf);
/* 175 */     f1 += d2.d(paramf);
/* 176 */     for (b1 = 0; b1 < this.z.size(); b1++) {
/* 177 */       net.a.a.g.d d3 = paramg.a((i)this.z.get(b1));
/* 178 */       net.a.a.g.d d4 = paramg.a((i)this.A
/* 179 */           .get(b1));
/* 180 */       d3.a(f1, f2, paramf);
/* 181 */       d4.a(f1, -f3, paramf);
/* 182 */       f1 += 
/* 183 */         Math.max(d3.d(paramf), d4.d(paramf));
/*     */     } 
/*     */     
/* 186 */     b b = new b(0.0F, 0.0F);
/* 187 */     c.a(paramg, paramd, (Node)this, paramf, (Dimension2D)b, (Dimension2D)b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private m.a a(g paramg, f paramf, net.a.a.g.d paramd, c paramc, String paramString1, String paramString2) {
/* 195 */     m.a a1 = new m.a(0.0F, 0.0F);
/*     */     
/*     */     byte b;
/* 198 */     for (b = 0; b < this.B.size(); b++) {
/* 199 */       net.a.a.g.d d1 = paramg.a((i)this.B.get(b));
/* 200 */       net.a.a.g.d d2 = paramg.a((i)this.C
/* 201 */           .get(b));
/*     */       
/* 203 */       m.a a2 = m.a(paramf, paramc, paramString1, paramString2, paramd, d1, d2);
/*     */       
/* 205 */       a1.a(a2);
/*     */     } 
/* 207 */     for (b = 0; b < this.z.size(); b++) {
/* 208 */       net.a.a.g.d d1 = paramg.a((i)this.z.get(b));
/* 209 */       net.a.a.g.d d2 = paramg.a((i)this.A
/* 210 */           .get(b));
/*     */       
/* 212 */       m.a a2 = m.a(paramf, paramc, paramString1, paramString2, paramd, d1, d2);
/*     */       
/* 214 */       a1.a(a2);
/*     */     } 
/* 216 */     return a1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean c(net.a.a.e.d paramd) {
/* 222 */     return (paramd.isSameNode((Node)a()) && 
/* 223 */       getNumprescriptcolumns() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(net.a.a.e.d paramd, c paramc) {
/* 230 */     return (paramd.isSameNode((Node)a()) && 
/* 231 */       getNumscriptcolumns() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public net.a.a.e.d a() {
/* 236 */     net.a.a.e.d d1 = a(0);
/* 237 */     if (d1 == null) {
/* 238 */       return (net.a.a.e.d)getOwnerDocument().createElement("none");
/*     */     }
/*     */     
/* 241 */     return d1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBase(MathMLElement paramMathMLElement) {
/* 247 */     a(0, paramMathMLElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumprescriptcolumns() {
/* 252 */     return this.B.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumscriptcolumns() {
/* 257 */     return this.z.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement getPreSubScript(int paramInt) {
/* 262 */     return (MathMLElement)this.B.get(paramInt - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement getPreSuperScript(int paramInt) {
/* 267 */     return (MathMLElement)this.C.get(paramInt - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLNodeList getPrescripts() {
/* 272 */     int i = this.B.size();
/* 273 */     ArrayList arrayList = new ArrayList(2 * i);
/* 274 */     for (byte b = 0; b < i; b++) {
/* 275 */       arrayList.add(this.B.get(b));
/* 276 */       arrayList.add(this.C.get(b));
/*     */     } 
/* 278 */     return (MathMLNodeList)new e(arrayList);
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLNodeList getScripts() {
/* 283 */     int i = this.z.size();
/* 284 */     ArrayList arrayList = new ArrayList(2 * i);
/* 285 */     for (byte b = 0; b < i; b++) {
/* 286 */       arrayList.add(this.z.get(b));
/* 287 */       arrayList.add(this.A.get(b));
/*     */     } 
/* 289 */     return (MathMLNodeList)new e(arrayList);
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement getSubScript(int paramInt) {
/* 294 */     if (paramInt < 1 || paramInt > this.z.size()) {
/* 295 */       return null;
/*     */     }
/* 297 */     return (MathMLElement)this.z.get(paramInt - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement getSuperScript(int paramInt) {
/* 302 */     if (paramInt < 1 || paramInt > this.A.size()) {
/* 303 */       return null;
/*     */     }
/* 305 */     return (MathMLElement)this.A.get(paramInt - 1);
/*     */   }
/*     */   
/*     */   private void j() {
/* 309 */     this.D = true;
/*     */     
/* 311 */     NodeList nodeList = getChildNodes();
/* 312 */     int i = nodeList.getLength();
/*     */     int j;
/* 314 */     for (j = 1; j < i; j++) {
/* 315 */       removeChild(nodeList.item(1));
/*     */     }
/* 317 */     if (i == 0) {
/* 318 */       a((MathMLElement)getOwnerDocument()
/* 319 */           .createElement("none"));
/*     */     }
/* 321 */     for (j = 0; j < this.z.size(); j++) {
/* 322 */       a((MathMLElement)this.z.get(j));
/* 323 */       a((MathMLElement)this.A.get(j));
/*     */     } 
/* 325 */     j = this.B.size();
/* 326 */     if (j > 0) {
/* 327 */       a((MathMLElement)getOwnerDocument()
/* 328 */           .createElement("mprescripts"));
/* 329 */       for (byte b = 0; b < j; b++) {
/* 330 */         a((MathMLElement)this.B.get(b));
/* 331 */         a((MathMLElement)this.C.get(b));
/*     */       } 
/*     */     } 
/* 334 */     this.D = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement insertPreSubScriptBefore(int paramInt, MathMLElement paramMathMLElement) {
/*     */     int i;
/* 341 */     if (paramInt == 0) {
/* 342 */       i = this.B.size();
/*     */     } else {
/* 344 */       i = paramInt - 1;
/*     */     } 
/* 346 */     this.B.add(i, (net.a.a.e.d)paramMathMLElement);
/* 347 */     this.C.add(i, (net.a.a.e.d)
/* 348 */         getOwnerDocument().createElement("none"));
/* 349 */     j();
/* 350 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement insertPreSuperScriptBefore(int paramInt, MathMLElement paramMathMLElement) {
/*     */     int i;
/* 357 */     if (paramInt == 0) {
/* 358 */       i = this.B.size();
/*     */     } else {
/* 360 */       i = paramInt - 1;
/*     */     } 
/* 362 */     this.B.add(i, (net.a.a.e.d)
/* 363 */         getOwnerDocument().createElement("none"));
/* 364 */     this.C.add(i, (net.a.a.e.d)paramMathMLElement);
/* 365 */     j();
/* 366 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement insertSubScriptBefore(int paramInt, MathMLElement paramMathMLElement) {
/*     */     int i;
/* 373 */     if (paramInt == 0) {
/* 374 */       i = this.z.size();
/*     */     } else {
/* 376 */       i = paramInt - 1;
/*     */     } 
/* 378 */     this.z.add(i, (net.a.a.e.d)paramMathMLElement);
/* 379 */     this.A.add(i, (net.a.a.e.d)
/* 380 */         getOwnerDocument().createElement("none"));
/* 381 */     j();
/* 382 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement insertSuperScriptBefore(int paramInt, MathMLElement paramMathMLElement) {
/*     */     int i;
/* 389 */     if (paramInt == 0) {
/* 390 */       i = this.z.size();
/*     */     } else {
/* 392 */       i = paramInt - 1;
/*     */     } 
/* 394 */     this.z.add(i, (net.a.a.e.d)
/* 395 */         getOwnerDocument().createElement("none"));
/* 396 */     this.A.add(i, (net.a.a.e.d)paramMathMLElement);
/* 397 */     j();
/* 398 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement setPreSubScriptAt(int paramInt, MathMLElement paramMathMLElement) {
/* 404 */     int i = paramInt - 1;
/* 405 */     if (i == this.B.size()) {
/* 406 */       return insertPreSubScriptBefore(0, paramMathMLElement);
/*     */     }
/* 408 */     this.B.set(i, (net.a.a.e.d)paramMathMLElement);
/* 409 */     j();
/* 410 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement setPreSuperScriptAt(int paramInt, MathMLElement paramMathMLElement) {
/* 417 */     int i = paramInt - 1;
/* 418 */     if (i == this.C.size()) {
/* 419 */       return insertPreSuperScriptBefore(0, paramMathMLElement);
/*     */     }
/* 421 */     this.C.set(i, (net.a.a.e.d)paramMathMLElement);
/* 422 */     j();
/* 423 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement setSubScriptAt(int paramInt, MathMLElement paramMathMLElement) {
/* 430 */     int i = paramInt - 1;
/* 431 */     if (i == this.z.size()) {
/* 432 */       return insertSubScriptBefore(0, paramMathMLElement);
/*     */     }
/* 434 */     this.z.set(i, (net.a.a.e.d)paramMathMLElement);
/* 435 */     j();
/* 436 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement setSuperScriptAt(int paramInt, MathMLElement paramMathMLElement) {
/* 443 */     int i = paramInt - 1;
/* 444 */     if (i == this.A.size()) {
/* 445 */       return insertSuperScriptBefore(0, paramMathMLElement);
/*     */     }
/* 447 */     this.A.set(i, (net.a.a.e.d)paramMathMLElement);
/* 448 */     j();
/* 449 */     return paramMathMLElement;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/c/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */