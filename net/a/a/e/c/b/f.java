/*     */ package net.a.a.e.c.b;
/*     */ 
/*     */ import net.a.a.e.c.a;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLPaddedElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   implements MathMLPaddedElement
/*     */ {
/*     */   public static final String r = "depth";
/*     */   public static final String s = "height";
/*     */   public static final String t = "lspace";
/*     */   public static final String u = "width";
/*     */   public static final String v = "mpadded";
/*     */   private static final long w = 1L;
/*     */   
/*     */   public f(String paramString, AbstractDocument paramAbstractDocument) {
/*  65 */     super(paramString, paramAbstractDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  71 */     return (Node)new f(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDepth() {
/*  76 */     return a("depth");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHeight() {
/*  81 */     return a("height");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLspace() {
/*  86 */     return a("lspace");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWidth() {
/*  91 */     return a("width");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDepth(String paramString) {
/*  96 */     setAttribute("depth", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeight(String paramString) {
/* 101 */     setAttribute("height", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLspace(String paramString) {
/* 106 */     setAttribute("lspace", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWidth(String paramString) {
/* 111 */     setAttribute("width", paramString);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */