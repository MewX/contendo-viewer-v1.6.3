/*     */ package net.a.a.e.c.a;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.a.a.e.a;
/*     */ import net.a.a.g.i;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLActionElement;
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
/*     */ public final class a
/*     */   extends a
/*     */   implements MathMLActionElement
/*     */ {
/*     */   public static final String r = "maction";
/*     */   private static final String s = "actiontype";
/*     */   private static final String t = "selection";
/*     */   private static final long u = 1L;
/*     */   
/*     */   public a(String paramString, AbstractDocument paramAbstractDocument) {
/*  61 */     super(paramString, paramAbstractDocument);
/*  62 */     a("selection", "1");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  68 */     return (Node)new a(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getActiontype() {
/*  73 */     return a("actiontype");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSelection() {
/*  78 */     return a("selection");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setActiontype(String paramString) {
/*  83 */     setAttribute("actiontype", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelection(String paramString) {
/*  88 */     setAttribute("selection", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<i> a() {
/*     */     i i;
/*     */     try {
/*  96 */       int j = Integer.parseInt(getSelection());
/*  97 */       i = (i)a(j - 1);
/*  98 */     } catch (NumberFormatException numberFormatException) {
/*  99 */       i = null;
/*     */     } 
/*     */     
/* 102 */     if (i == null) {
/* 103 */       return Collections.emptyList();
/*     */     }
/* 105 */     return Collections.singletonList(i);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */