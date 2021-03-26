/*    */ package org.apache.xmlgraphics.java2d.ps;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import org.apache.xmlgraphics.ps.DSCConstants;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EPSDocumentGraphics2D
/*    */   extends AbstractPSDocumentGraphics2D
/*    */ {
/*    */   public EPSDocumentGraphics2D(boolean textAsShapes) {
/* 48 */     super(textAsShapes);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void writeFileHeader() throws IOException {
/* 53 */     Long pagewidth = Long.valueOf(this.width);
/* 54 */     Long pageheight = Long.valueOf(this.height);
/*    */ 
/*    */     
/* 57 */     this.gen.writeln("%!PS-Adobe-3.0 EPSF-3.0");
/* 58 */     this.gen.writeDSCComment("Creator", (Object[])new String[] { "Apache XML Graphics Commons: EPS Generator for Java2D" });
/*    */ 
/*    */     
/* 61 */     this.gen.writeDSCComment("CreationDate", new Object[] { new Date() });
/*    */     
/* 63 */     this.gen.writeDSCComment("Pages", DSCConstants.ATEND);
/* 64 */     this.gen.writeDSCComment("BoundingBox", new Object[] { ZERO, ZERO, pagewidth, pageheight });
/*    */     
/* 66 */     this.gen.writeDSCComment("LanguageLevel", Integer.valueOf(this.gen.getPSLevel()));
/* 67 */     this.gen.writeDSCComment("EndComments");
/*    */ 
/*    */     
/* 70 */     this.gen.writeDSCComment("BeginProlog");
/* 71 */     writeProcSets();
/* 72 */     if (this.customTextHandler instanceof PSTextHandler) {
/* 73 */       ((PSTextHandler)this.customTextHandler).writeSetup();
/*    */     }
/* 75 */     this.gen.writeDSCComment("EndProlog");
/*    */   }
/*    */ 
/*    */   
/*    */   protected void writePageHeader() throws IOException {
/* 80 */     Integer pageNumber = Integer.valueOf(this.pagecount);
/* 81 */     this.gen.writeDSCComment("Page", new Object[] { pageNumber.toString(), pageNumber });
/*    */     
/* 83 */     this.gen.writeDSCComment("PageBoundingBox", new Object[] { ZERO, ZERO, Integer.valueOf(this.width), Integer.valueOf(this.height) });
/*    */     
/* 85 */     this.gen.writeDSCComment("BeginPageSetup");
/* 86 */     if (this.customTextHandler instanceof PSTextHandler)
/* 87 */       ((PSTextHandler)this.customTextHandler).writePageSetup(); 
/*    */   }
/*    */   
/*    */   protected void writePageTrailer() throws IOException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/ps/EPSDocumentGraphics2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */