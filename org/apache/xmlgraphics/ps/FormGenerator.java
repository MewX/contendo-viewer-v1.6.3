/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FormGenerator
/*     */ {
/*     */   private String formName;
/*     */   private String title;
/*     */   private Dimension2D dimensions;
/*     */   
/*     */   public FormGenerator(String formName, String title, Dimension2D dimensions) {
/*  43 */     this.formName = formName;
/*  44 */     this.title = title;
/*  45 */     this.dimensions = dimensions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormName() {
/*  53 */     return this.formName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  61 */     return this.title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension2D getDimensions() {
/*  69 */     return this.dimensions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void generatePaintProc(PSGenerator paramPSGenerator) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateAdditionalDataStream(PSGenerator gen) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AffineTransform getMatrix() {
/*  94 */     return new AffineTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Rectangle2D getBBox() {
/* 102 */     return new Rectangle2D.Double(0.0D, 0.0D, this.dimensions.getWidth(), this.dimensions.getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PSResource generate(PSGenerator gen) throws IOException {
/* 112 */     if (gen.getPSLevel() < 2) {
/* 113 */       throw new UnsupportedOperationException("Forms require at least Level 2 PostScript");
/*     */     }
/*     */     
/* 116 */     gen.writeDSCComment("BeginResource", new Object[] { "form", getFormName() });
/*     */     
/* 118 */     if (this.title != null) {
/* 119 */       gen.writeDSCComment("Title", this.title);
/*     */     }
/* 121 */     gen.writeln("/" + this.formName);
/* 122 */     gen.writeln("<< /FormType 1");
/* 123 */     gen.writeln("  /BBox " + gen.formatRectangleToArray(getBBox()));
/* 124 */     gen.writeln("  /Matrix " + gen.formatMatrix(getMatrix()));
/* 125 */     gen.writeln("  /PaintProc {");
/* 126 */     gen.writeln("    pop");
/* 127 */     gen.writeln("    gsave");
/* 128 */     generatePaintProc(gen);
/* 129 */     gen.writeln("    grestore");
/* 130 */     gen.writeln("  } bind");
/* 131 */     gen.writeln(">> def");
/* 132 */     generateAdditionalDataStream(gen);
/* 133 */     gen.writeDSCComment("EndResource");
/* 134 */     PSResource res = new PSResource("form", this.formName);
/* 135 */     gen.getResourceTracker().registerSuppliedResource(res);
/* 136 */     return res;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/FormGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */