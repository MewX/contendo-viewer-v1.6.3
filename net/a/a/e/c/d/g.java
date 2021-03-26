/*     */ package net.a.a.e.c.d;
/*     */ 
/*     */ import net.a.a.e.d;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLTableCellElement;
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
/*     */ public final class g
/*     */   extends a
/*     */   implements MathMLTableCellElement
/*     */ {
/*     */   public static final String r = "mtd";
/*     */   private static final String s = "rowspan";
/*     */   private static final String t = "columnspan";
/*     */   private static final String u = "1";
/*     */   private static final long v = 1L;
/*     */   
/*     */   public g(String paramString, AbstractDocument paramAbstractDocument) {
/*  57 */     super(paramString, paramAbstractDocument);
/*     */     
/*  59 */     a("rowspan", "1");
/*  60 */     a("columnspan", "1");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  66 */     return (Node)new g(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRowspan() {
/*  73 */     return a("rowspan");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRowspan(String paramString) {
/*  81 */     setAttribute("rowspan", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getColumnspan() {
/*  88 */     return a("columnspan");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColumnspan(String paramString) {
/*  96 */     setAttribute("columnspan", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCellindex() {
/* 101 */     return Integer.toString(g().a((d)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getHasaligngroups() {
/* 106 */     return (getGroupalign() != null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/d/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */