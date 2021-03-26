/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.xml.xpath.XPath;
/*     */ import javax.xml.xpath.XPathExpressionException;
/*     */ import javax.xml.xpath.XPathFactory;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FDFAnnotationFreeText
/*     */   extends FDFAnnotation
/*     */ {
/*  39 */   private static final Log LOG = LogFactory.getLog(FDFAnnotationFreeText.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String SUBTYPE = "FreeText";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationFreeText() {
/*  51 */     this.annot.setName(COSName.SUBTYPE, "FreeText");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationFreeText(COSDictionary a) {
/*  61 */     super(a);
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
/*     */   public FDFAnnotationFreeText(Element element) throws IOException {
/*  73 */     super(element);
/*  74 */     this.annot.setName(COSName.SUBTYPE, "FreeText");
/*     */     
/*  76 */     setJustification(element.getAttribute("justification"));
/*     */     
/*  78 */     XPath xpath = XPathFactory.newInstance().newXPath();
/*     */     
/*     */     try {
/*  81 */       setDefaultAppearance(xpath.evaluate("defaultappearance", element));
/*  82 */       setDefaultStyle(xpath.evaluate("defaultstyle", element));
/*     */     }
/*  84 */     catch (XPathExpressionException ex) {
/*     */       
/*  86 */       LOG.debug("Error while evaluating XPath expression");
/*     */     } 
/*  88 */     initCallout(element);
/*  89 */     String rotation = element.getAttribute("rotation");
/*  90 */     if (rotation != null && !rotation.isEmpty())
/*     */     {
/*  92 */       setRotation(Integer.parseInt(rotation));
/*     */     }
/*  94 */     initFringe(element);
/*  95 */     String lineEndingStyle = element.getAttribute("head");
/*  96 */     if (lineEndingStyle != null && !lineEndingStyle.isEmpty())
/*     */     {
/*  98 */       setLineEndingStyle(lineEndingStyle);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void initFringe(Element element) throws IOException {
/* 104 */     String fringe = element.getAttribute("fringe");
/* 105 */     if (fringe != null && !fringe.isEmpty()) {
/*     */       
/* 107 */       String[] fringeValues = fringe.split(",");
/* 108 */       if (fringeValues.length != 4)
/*     */       {
/* 110 */         throw new IOException("Error: wrong amount of numbers in attribute 'fringe'");
/*     */       }
/* 112 */       PDRectangle rect = new PDRectangle();
/* 113 */       rect.setLowerLeftX(Float.parseFloat(fringeValues[0]));
/* 114 */       rect.setLowerLeftY(Float.parseFloat(fringeValues[1]));
/* 115 */       rect.setUpperRightX(Float.parseFloat(fringeValues[2]));
/* 116 */       rect.setUpperRightY(Float.parseFloat(fringeValues[3]));
/* 117 */       setFringe(rect);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void initCallout(Element element) throws IOException {
/* 123 */     String callout = element.getAttribute("callout");
/* 124 */     if (callout != null && !callout.isEmpty()) {
/*     */       
/* 126 */       String[] calloutValues = callout.split(",");
/* 127 */       float[] values = new float[calloutValues.length];
/* 128 */       for (int i = 0; i < calloutValues.length; i++)
/*     */       {
/* 130 */         values[i] = Float.parseFloat(calloutValues[i]);
/*     */       }
/* 132 */       setCallout(values);
/*     */     } 
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
/*     */   public void setCallout(float[] callout) {
/* 146 */     COSArray newCallout = new COSArray();
/* 147 */     newCallout.setFloatArray(callout);
/* 148 */     this.annot.setItem(COSName.CL, (COSBase)newCallout);
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
/*     */   public float[] getCallout() {
/* 161 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.CL);
/* 162 */     if (array != null)
/*     */     {
/* 164 */       return array.toFloatArray();
/*     */     }
/*     */ 
/*     */     
/* 168 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setJustification(String justification) {
/* 179 */     int quadding = 0;
/* 180 */     if ("centered".equals(justification)) {
/*     */       
/* 182 */       quadding = 1;
/*     */     }
/* 184 */     else if ("right".equals(justification)) {
/*     */       
/* 186 */       quadding = 2;
/*     */     } 
/* 188 */     this.annot.setInt(COSName.Q, quadding);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getJustification() {
/* 198 */     return "" + this.annot.getInt(COSName.Q, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setRotation(int rotation) {
/* 208 */     this.annot.setInt(COSName.ROTATE, rotation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRotation() {
/* 218 */     return this.annot.getString(COSName.ROTATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setDefaultAppearance(String appearance) {
/* 228 */     this.annot.setString(COSName.DA, appearance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultAppearance() {
/* 238 */     return this.annot.getString(COSName.DA);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setDefaultStyle(String style) {
/* 249 */     this.annot.setString(COSName.DS, style);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultStyle() {
/* 259 */     return this.annot.getString(COSName.DS);
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
/*     */   public final void setFringe(PDRectangle fringe) {
/* 271 */     this.annot.setItem(COSName.RD, (COSObjectable)fringe);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getFringe() {
/* 282 */     COSArray rd = (COSArray)this.annot.getDictionaryObject(COSName.RD);
/* 283 */     if (rd != null)
/*     */     {
/* 285 */       return new PDRectangle(rd);
/*     */     }
/*     */ 
/*     */     
/* 289 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setLineEndingStyle(String style) {
/* 300 */     this.annot.setName(COSName.LE, style);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLineEndingStyle() {
/* 310 */     return this.annot.getNameAsString(COSName.LE);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationFreeText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */