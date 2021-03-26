/*     */ package net.a.a.e.c.e;
/*     */ 
/*     */ import net.a.a.c;
/*     */ import net.a.a.e.c;
/*     */ import net.a.a.e.d.a.a;
/*     */ import net.a.a.g.d;
/*     */ import net.a.a.g.f;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLSpaceElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class g
/*     */   extends c
/*     */   implements MathMLSpaceElement
/*     */ {
/*     */   public static final String r = "mspace";
/*     */   public static final String s = "width";
/*     */   public static final String t = "height";
/*     */   public static final String u = "depth";
/*     */   public static final String v = "linebreak";
/*     */   private static final long w = 1L;
/*     */   
/*     */   public g(String paramString, AbstractDocument paramAbstractDocument) {
/*  72 */     super(paramString, paramAbstractDocument);
/*     */     
/*  74 */     a("depth", "0");
/*  75 */     a("height", "0");
/*  76 */     a("width", "0");
/*  77 */     a("linebreak", "auto");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  83 */     return (Node)new g(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWidth() {
/*  90 */     return a("width");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidth(String paramString) {
/*  98 */     setAttribute("width", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHeight() {
/* 105 */     return a("height");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeight(String paramString) {
/* 113 */     setAttribute("height", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDepth() {
/* 120 */     return a("depth");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepth(String paramString) {
/* 128 */     setAttribute("depth", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(net.a.a.g.g paramg, d paramd, f paramf, c paramc) {
/* 136 */     c c1 = b(paramc);
/* 137 */     paramd.c(a.a(getHeight(), c1, "pt"), paramf);
/*     */     
/* 139 */     paramd.d(a.a(getDepth(), c1, "pt"), paramf);
/*     */     
/* 141 */     paramd.e(a.a(getWidth(), c1, "pt"), paramf);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLinebreak() {
/* 147 */     return a("linebreak");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLinebreak(String paramString) {
/* 152 */     setAttribute("linebreak", paramString);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/e/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */