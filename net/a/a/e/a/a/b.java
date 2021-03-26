/*     */ package net.a.a.e.a.a;
/*     */ 
/*     */ import net.a.a.e.c.a;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLElement;
/*     */ import org.w3c.dom.mathml.MathMLSemanticsElement;
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
/*     */ public final class b
/*     */   extends a
/*     */   implements MathMLSemanticsElement
/*     */ {
/*     */   public static final String r = "semantics";
/*     */   private static final long s = 1L;
/*     */   
/*     */   public b(String paramString, AbstractDocument paramAbstractDocument) {
/*  52 */     super(paramString, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  58 */     return (Node)new b(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteAnnotation(int paramInt) {
/*  63 */     removeAnnotation(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement getAnnotation(int paramInt) {
/*  69 */     return (MathMLElement)getChildNodes().item(paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement getBody() {
/*  74 */     return (MathMLElement)getFirstChild();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNAnnotations() {
/*  79 */     return Math.max(0, getChildNodes().getLength() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement insertAnnotation(MathMLElement paramMathMLElement, int paramInt) {
/*  85 */     if (paramInt == 0) {
/*  86 */       if (getNAnnotations() == 0) {
/*  87 */         setAnnotation(paramMathMLElement, 1);
/*     */       } else {
/*  89 */         a(paramMathMLElement);
/*     */       } 
/*     */     } else {
/*  92 */       MathMLElement mathMLElement = getAnnotation(paramInt);
/*  93 */       if (mathMLElement == null) {
/*  94 */         setAnnotation(paramMathMLElement, paramInt);
/*     */       } else {
/*  96 */         insertBefore((Node)paramMathMLElement, (Node)mathMLElement);
/*     */       } 
/*     */     } 
/*  99 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public MathMLElement removeAnnotation(int paramInt) {
/* 104 */     MathMLElement mathMLElement = getAnnotation(paramInt);
/* 105 */     return (MathMLElement)removeChild((Node)mathMLElement);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MathMLElement setAnnotation(MathMLElement paramMathMLElement, int paramInt) {
/* 112 */     a(paramInt, paramMathMLElement);
/* 113 */     return paramMathMLElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBody(MathMLElement paramMathMLElement) {
/* 118 */     a(0, paramMathMLElement);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/a/a/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */