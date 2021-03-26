/*     */ package net.a.a.e.c.b;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.b;
/*     */ import net.a.a.c.e;
/*     */ import net.a.a.g.i;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLElement;
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
/*     */ public final class h
/*     */   extends a
/*     */ {
/*     */   public static final String r = "mroot";
/*     */   private static final long s = 1L;
/*     */   
/*     */   public h(String paramString, AbstractDocument paramAbstractDocument) {
/*  56 */     super(paramString, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  62 */     return (Node)new h(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<i> a() {
/*  68 */     ArrayList<i> arrayList = new ArrayList(1);
/*  69 */     arrayList.add((i)getRadicand());
/*  70 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement getIndex() {
/*  75 */     return (MathMLElement)a(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement getRadicand() {
/*  80 */     return (MathMLElement)a(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIndex(MathMLElement paramMathMLElement) {
/*  85 */     a(1, paramMathMLElement);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRadicand(MathMLElement paramMathMLElement) {
/*  90 */     a(0, paramMathMLElement);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c a(int paramInt, c paramc) {
/*  97 */     c c1 = b(paramc);
/*  98 */     if (paramInt == 0) {
/*  99 */       return c1;
/*     */     }
/*     */     
/* 102 */     return (c)new e((c)new b(c1), 2);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */