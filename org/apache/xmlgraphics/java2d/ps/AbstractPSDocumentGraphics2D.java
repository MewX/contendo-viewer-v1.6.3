/*     */ package org.apache.xmlgraphics.java2d.ps;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.xmlgraphics.ps.PSGenerator;
/*     */ import org.apache.xmlgraphics.ps.PSProcSets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPSDocumentGraphics2D
/*     */   extends PSGraphics2D
/*     */ {
/*  44 */   protected static final Integer ZERO = Integer.valueOf(0);
/*     */ 
/*     */   
/*     */   protected int width;
/*     */ 
/*     */   
/*     */   protected int height;
/*     */ 
/*     */   
/*     */   protected float viewportWidth;
/*     */ 
/*     */   
/*     */   protected float viewportHeight;
/*     */ 
/*     */   
/*     */   protected int pagecount;
/*     */ 
/*     */   
/*     */   protected boolean pagePending;
/*     */ 
/*     */   
/*     */   protected Shape initialClip;
/*     */   
/*     */   protected AffineTransform initialTransform;
/*     */ 
/*     */   
/*     */   AbstractPSDocumentGraphics2D(boolean textAsShapes) {
/*  71 */     super(textAsShapes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setupDocument(OutputStream stream, int width, int height) throws IOException {
/*  83 */     this.width = width;
/*  84 */     this.height = height;
/*  85 */     this.pagecount = 0;
/*  86 */     this.pagePending = false;
/*     */ 
/*     */     
/*  89 */     setPSGenerator(new PSGenerator(stream));
/*     */     
/*  91 */     writeFileHeader();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void writeFileHeader() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPSDocumentGraphics2D(boolean textAsShapes, OutputStream stream, int width, int height) throws IOException {
/* 116 */     this(textAsShapes);
/* 117 */     setupDocument(stream, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setViewportDimension(float w, float h) throws IOException {
/* 131 */     this.viewportWidth = w;
/* 132 */     this.viewportHeight = h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackgroundColor(Color col) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPageCount() {
/* 165 */     return this.pagecount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextPage() throws IOException {
/* 173 */     closePage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void closePage() throws IOException {
/* 181 */     if (!this.pagePending) {
/*     */       return;
/*     */     }
/*     */     
/* 185 */     writePageTrailer();
/* 186 */     this.pagePending = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void writePageHeader() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void writePageTrailer() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeProcSets() throws IOException {
/* 207 */     PSProcSets.writeStdProcSet(this.gen);
/* 208 */     PSProcSets.writeEPSProcSet(this.gen);
/*     */   }
/*     */ 
/*     */   
/*     */   public void preparePainting() {
/* 213 */     if (this.pagePending) {
/*     */       return;
/*     */     }
/*     */     try {
/* 217 */       startPage();
/* 218 */     } catch (IOException ioe) {
/* 219 */       handleIOException(ioe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void startPage() throws IOException {
/*     */     AffineTransform at;
/* 228 */     if (this.pagePending) {
/* 229 */       throw new IllegalStateException("Close page first before starting another");
/*     */     }
/*     */     
/* 232 */     this.pagecount++;
/*     */     
/* 234 */     if (this.initialTransform == null) {
/*     */       
/* 236 */       this.initialTransform = getTransform();
/* 237 */       this.initialClip = getClip();
/*     */     } else {
/*     */       
/* 240 */       setTransform(this.initialTransform);
/* 241 */       setClip(this.initialClip);
/*     */     } 
/*     */     
/* 244 */     writePageHeader();
/*     */     
/* 246 */     if ((this.viewportWidth != this.width || this.viewportHeight != this.height) && this.viewportWidth > 0.0F && this.viewportHeight > 0.0F) {
/*     */ 
/*     */       
/* 249 */       at = new AffineTransform(this.width / this.viewportWidth, 0.0F, 0.0F, -1.0F * this.height / this.viewportHeight, 0.0F, this.height);
/*     */     }
/*     */     else {
/*     */       
/* 253 */       at = new AffineTransform(1.0F, 0.0F, 0.0F, -1.0F, 0.0F, this.height);
/*     */     } 
/*     */ 
/*     */     
/* 257 */     this.gen.writeln(this.gen.formatMatrix(at) + " " + this.gen.mapCommand("concat"));
/* 258 */     this.gen.writeDSCComment("EndPageSetup");
/* 259 */     this.pagePending = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finish() throws IOException {
/* 271 */     if (this.pagePending) {
/* 272 */       closePage();
/*     */     }
/*     */ 
/*     */     
/* 276 */     this.gen.writeDSCComment("Trailer");
/* 277 */     this.gen.writeDSCComment("Pages", Integer.valueOf(this.pagecount));
/* 278 */     this.gen.writeDSCComment("EOF");
/* 279 */     this.gen.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPSDocumentGraphics2D(AbstractPSDocumentGraphics2D g) {
/* 287 */     super(g);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/ps/AbstractPSDocumentGraphics2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */