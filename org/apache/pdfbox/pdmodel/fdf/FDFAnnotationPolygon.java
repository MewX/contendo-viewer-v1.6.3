/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.awt.Color;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FDFAnnotationPolygon
/*     */   extends FDFAnnotation
/*     */ {
/*  41 */   private static final Log LOG = LogFactory.getLog(FDFAnnotationPolygon.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String SUBTYPE = "Polygon";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationPolygon() {
/*  53 */     this.annot.setName(COSName.SUBTYPE, "Polygon");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationPolygon(COSDictionary a) {
/*  63 */     super(a);
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
/*     */   public FDFAnnotationPolygon(Element element) throws IOException {
/*  75 */     super(element);
/*  76 */     this.annot.setName(COSName.SUBTYPE, "Polygon");
/*     */     
/*  78 */     initVertices(element);
/*  79 */     String color = element.getAttribute("interior-color");
/*  80 */     if (color != null && color.length() == 7 && color.charAt(0) == '#') {
/*     */       
/*  82 */       int colorValue = Integer.parseInt(color.substring(1, 7), 16);
/*  83 */       setInteriorColor(new Color(colorValue));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void initVertices(Element element) throws IOException {
/*  89 */     XPath xpath = XPathFactory.newInstance().newXPath();
/*     */     
/*     */     try {
/*  92 */       String vertices = xpath.evaluate("vertices", element);
/*  93 */       if (vertices == null || vertices.isEmpty())
/*     */       {
/*  95 */         throw new IOException("Error: missing element 'vertices'");
/*     */       }
/*  97 */       String[] verticesValues = vertices.split(",|;");
/*  98 */       float[] values = new float[verticesValues.length];
/*  99 */       for (int i = 0; i < verticesValues.length; i++)
/*     */       {
/* 101 */         values[i] = Float.parseFloat(verticesValues[i]);
/*     */       }
/* 103 */       setVertices(values);
/*     */     }
/* 105 */     catch (XPathExpressionException e) {
/*     */       
/* 107 */       LOG.debug("Error while evaluating XPath expression for polygon vertices");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVertices(float[] vertices) {
/* 118 */     COSArray newVertices = new COSArray();
/* 119 */     newVertices.setFloatArray(vertices);
/* 120 */     this.annot.setItem(COSName.VERTICES, (COSBase)newVertices);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getVertices() {
/* 130 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.VERTICES);
/* 131 */     if (array != null)
/*     */     {
/* 133 */       return array.toFloatArray();
/*     */     }
/*     */ 
/*     */     
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setInteriorColor(Color color) {
/* 148 */     COSArray array = null;
/* 149 */     if (color != null) {
/*     */       
/* 151 */       float[] colors = color.getRGBColorComponents(null);
/* 152 */       array = new COSArray();
/* 153 */       array.setFloatArray(colors);
/*     */     } 
/* 155 */     this.annot.setItem(COSName.IC, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getInteriorColor() {
/* 165 */     Color retval = null;
/* 166 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.IC);
/* 167 */     if (array != null) {
/*     */       
/* 169 */       float[] rgb = array.toFloatArray();
/* 170 */       if (rgb.length >= 3)
/*     */       {
/* 172 */         retval = new Color(rgb[0], rgb[1], rgb[2]);
/*     */       }
/*     */     } 
/* 175 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationPolygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */