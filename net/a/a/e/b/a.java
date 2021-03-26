/*     */ package net.a.a.e.b;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.c;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.e.d.c;
/*     */ import net.a.a.e.e;
/*     */ import net.a.a.e.f;
/*     */ import net.a.a.g.c;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.h;
/*     */ import net.a.a.g.i;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.GenericDocument;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLDocument;
/*     */ import org.w3c.dom.views.AbstractView;
/*     */ import org.w3c.dom.views.DocumentView;
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
/*     */ public final class a
/*     */   extends GenericDocument
/*     */   implements f, h, MathMLDocument, DocumentView
/*     */ {
/*     */   private static final long a = 1L;
/*     */   
/*     */   public a() {
/*  59 */     this(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a(DocumentType paramDocumentType) {
/*  69 */     super(paramDocumentType, c.a());
/*  70 */     setEventsEnabled(true);
/*  71 */     this.ownerDocument = (AbstractDocument)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDomain() {
/*  76 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReferrer() {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getURI() {
/*  86 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public c a(int paramInt, c paramc) {
/*  92 */     return paramc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c a() {
/*  99 */     return new c((Node)this, 
/* 100 */         c.a(), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<i> b() {
/* 105 */     return c.b((Node)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<i> c() {
/* 110 */     return c.b((Node)this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(g paramg, d paramd, f paramf, c paramc) {
/* 116 */     c.a(paramg, paramd, 
/* 117 */         b(), f.b);
/* 118 */     paramd.a(paramf);
/*     */     
/* 120 */     if (paramc.a(d.j) == null) {
/* 121 */       paramd.a(paramf);
/*     */     } else {
/* 123 */       paramd.a(f.b);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(g paramg, d paramd, c paramc) {
/* 130 */     c.a(paramg, paramd, 
/* 131 */         b(), f.c);
/* 132 */     c.a((Color)paramc
/* 133 */         .a(d.j), paramd, true);
/* 134 */     paramd.a(f.c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 140 */     return (Node)new a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElement(String paramString) {
/* 146 */     return e.a(null, paramString, (Document)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElementNS(String paramString1, String paramString2) {
/*     */     String str;
/* 154 */     if (paramString1 != null && paramString1.length() == 0) {
/* 155 */       str = null;
/*     */     } else {
/* 157 */       str = paramString1;
/*     */     } 
/* 159 */     return e.a(str, paramString2, (Document)this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/b/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */