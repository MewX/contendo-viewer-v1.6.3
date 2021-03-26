/*     */ package org.apache.xmlgraphics.java2d.ps;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Date;
/*     */ import org.apache.xmlgraphics.ps.DSCConstants;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PSDocumentGraphics2D
/*     */   extends AbstractPSDocumentGraphics2D
/*     */ {
/*     */   public PSDocumentGraphics2D(boolean textAsShapes) {
/*  51 */     super(textAsShapes);
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
/*     */   
/*     */   public PSDocumentGraphics2D(boolean textAsShapes, OutputStream stream, int width, int height) throws IOException {
/*  70 */     this(textAsShapes);
/*  71 */     setupDocument(stream, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public void nextPage() throws IOException {
/*  76 */     closePage();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void writeFileHeader() throws IOException {
/*  81 */     Long pagewidth = Long.valueOf(this.width);
/*  82 */     Long pageheight = Long.valueOf(this.height);
/*     */ 
/*     */     
/*  85 */     this.gen.writeln("%!PS-Adobe-3.0");
/*  86 */     this.gen.writeDSCComment("Creator", (Object[])new String[] { "Apache XML Graphics Commons: PostScript Generator for Java2D" });
/*     */ 
/*     */     
/*  89 */     this.gen.writeDSCComment("CreationDate", new Object[] { new Date() });
/*     */     
/*  91 */     this.gen.writeDSCComment("Pages", DSCConstants.ATEND);
/*  92 */     this.gen.writeDSCComment("BoundingBox", new Object[] { ZERO, ZERO, pagewidth, pageheight });
/*     */     
/*  94 */     this.gen.writeDSCComment("LanguageLevel", Integer.valueOf(this.gen.getPSLevel()));
/*  95 */     this.gen.writeDSCComment("EndComments");
/*     */ 
/*     */     
/*  98 */     this.gen.writeDSCComment("BeginDefaults");
/*  99 */     this.gen.writeDSCComment("EndDefaults");
/*     */ 
/*     */     
/* 102 */     this.gen.writeDSCComment("BeginProlog");
/* 103 */     this.gen.writeDSCComment("EndProlog");
/*     */ 
/*     */     
/* 106 */     this.gen.writeDSCComment("BeginSetup");
/* 107 */     writeProcSets();
/* 108 */     if (this.customTextHandler instanceof PSTextHandler) {
/* 109 */       ((PSTextHandler)this.customTextHandler).writeSetup();
/*     */     }
/* 111 */     this.gen.writeDSCComment("EndSetup");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void writePageHeader() throws IOException {
/* 116 */     Integer pageNumber = Integer.valueOf(this.pagecount);
/* 117 */     this.gen.writeDSCComment("Page", new Object[] { pageNumber.toString(), pageNumber });
/*     */     
/* 119 */     this.gen.writeDSCComment("PageBoundingBox", new Object[] { ZERO, ZERO, Integer.valueOf(this.width), Integer.valueOf(this.height) });
/*     */     
/* 121 */     this.gen.writeDSCComment("BeginPageSetup");
/* 122 */     this.gen.writeln("<<");
/* 123 */     this.gen.writeln("/PageSize [" + this.width + " " + this.height + "]");
/* 124 */     this.gen.writeln("/ImagingBBox null");
/* 125 */     this.gen.writeln(">> setpagedevice");
/* 126 */     if (this.customTextHandler instanceof PSTextHandler) {
/* 127 */       ((PSTextHandler)this.customTextHandler).writePageSetup();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void writePageTrailer() throws IOException {
/* 133 */     this.gen.showPage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PSDocumentGraphics2D(PSDocumentGraphics2D g) {
/* 141 */     super(g);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/ps/PSDocumentGraphics2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */