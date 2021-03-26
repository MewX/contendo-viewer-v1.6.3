/*     */ package net.a.a.e.c.e;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import net.a.a.c;
/*     */ import net.a.a.e.d.a.d;
/*     */ import net.a.a.e.d.c.d;
/*     */ import net.a.a.e.d.c.e;
/*     */ import net.a.a.e.d.c.g;
/*     */ import net.a.a.e.d.d;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLStringLitElement;
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
/*     */ 
/*     */ 
/*     */ public final class f
/*     */   extends a
/*     */   implements g, MathMLStringLitElement
/*     */ {
/*     */   public static final String r = "ms";
/*     */   public static final String s = "lquote";
/*     */   public static final String t = "rquote";
/*     */   private static final String u = "\"";
/*     */   private static final long v = 1L;
/*     */   
/*     */   public f(String paramString, AbstractDocument paramAbstractDocument) {
/*  66 */     super(paramString, paramAbstractDocument);
/*  67 */     a("lquote", "\"");
/*  68 */     a("rquote", "\"");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  74 */     return (Node)new f(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLquote(String paramString) {
/*  82 */     setAttribute("lquote", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLquote() {
/*  89 */     return a("lquote");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRquote(String paramString) {
/*  97 */     setAttribute("rquote", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRquote() {
/* 104 */     return a("rquote");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributedCharacterIterator a(AttributedCharacterIterator paramAttributedCharacterIterator, c paramc) {
/* 113 */     float f1 = d.a(paramc);
/* 114 */     d d = d();
/*     */     
/* 116 */     d d1 = new d();
/* 117 */     d1.a(
/* 118 */         e.a(getLquote(), d, f1, paramc)
/* 119 */         .getIterator());
/* 120 */     d1.a(paramAttributedCharacterIterator);
/* 121 */     d1.a(
/* 122 */         e.a(getRquote(), d, f1, paramc)
/* 123 */         .getIterator());
/* 124 */     return (AttributedCharacterIterator)d1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/e/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */