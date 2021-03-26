/*     */ package net.a.a.e.c.e;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.font.TextLayout;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.e.c;
/*     */ import net.a.a.e.d;
/*     */ import net.a.a.e.d.c.e;
/*     */ import net.a.a.g.b;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import net.a.a.g.g;
/*     */ import net.a.a.g.i;
/*     */ import net.a.a.g.k;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLPresentationToken;
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
/*     */ public abstract class a
/*     */   extends c
/*     */   implements MathMLPresentationToken
/*     */ {
/*     */   private static final long r = 1L;
/*     */   
/*     */   public a(String paramString, AbstractDocument paramAbstractDocument) {
/*  62 */     super(paramString, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(g paramg, d paramd, f paramf, c paramc) {
/*  70 */     Graphics2D graphics2D = paramg.d();
/*  71 */     TextLayout textLayout = c(graphics2D, paramc);
/*  72 */     if (textLayout != null) {
/*  73 */       e.a a1 = e.a(textLayout, false);
/*     */       
/*  75 */       paramd.c(a1.a(), paramf);
/*  76 */       paramd.d(a1.b(), paramf);
/*  77 */       float f1 = a1.d();
/*  78 */       paramd.a(f1 / 2.0F, paramf);
/*  79 */       paramd.e(f1, paramf);
/*  80 */       paramd.a((b)new k(textLayout, a1.c(), (Color)
/*  81 */             b(paramc)
/*  82 */             .a(d.i)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private TextLayout c(Graphics2D paramGraphics2D, c paramc) {
/*  89 */     c c1 = b(paramc);
/*     */ 
/*     */     
/*  92 */     AttributedCharacterIterator attributedCharacterIterator = e.a(c1, (d)this, (Node)this, 1.0F);
/*     */     
/*  94 */     if (attributedCharacterIterator.getBeginIndex() == attributedCharacterIterator.getEndIndex()) {
/*  95 */       return null;
/*     */     }
/*  97 */     return e.a(paramGraphics2D, new AttributedString(attributedCharacterIterator), c1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<i> b() {
/* 106 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<i> c() {
/* 112 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/e/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */