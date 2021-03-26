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
/*     */ 
/*     */ public class FDFAnnotationPolyline
/*     */   extends FDFAnnotation
/*     */ {
/*  42 */   private static final Log LOG = LogFactory.getLog(FDFAnnotationPolyline.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String SUBTYPE = "Polyline";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationPolyline() {
/*  54 */     this.annot.setName(COSName.SUBTYPE, "Polyline");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationPolyline(COSDictionary a) {
/*  64 */     super(a);
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
/*     */   public FDFAnnotationPolyline(Element element) throws IOException {
/*  76 */     super(element);
/*  77 */     this.annot.setName(COSName.SUBTYPE, "Polyline");
/*     */     
/*  79 */     initVertices(element);
/*  80 */     initStyles(element);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initVertices(Element element) throws IOException {
/*  85 */     XPath xpath = XPathFactory.newInstance().newXPath();
/*     */     
/*     */     try {
/*  88 */       String vertices = xpath.evaluate("vertices[1]", element);
/*  89 */       if (vertices == null || vertices.isEmpty())
/*     */       {
/*  91 */         throw new IOException("Error: missing element 'vertices'");
/*     */       }
/*  93 */       String[] verticesValues = vertices.split(",|;");
/*  94 */       float[] values = new float[verticesValues.length];
/*  95 */       for (int i = 0; i < verticesValues.length; i++)
/*     */       {
/*  97 */         values[i] = Float.parseFloat(verticesValues[i]);
/*     */       }
/*  99 */       setVertices(values);
/*     */     }
/* 101 */     catch (XPathExpressionException e) {
/*     */       
/* 103 */       LOG.debug("Error while evaluating XPath expression for polyline vertices");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void initStyles(Element element) {
/* 109 */     String startStyle = element.getAttribute("head");
/* 110 */     if (startStyle != null && !startStyle.isEmpty())
/*     */     {
/* 112 */       setStartPointEndingStyle(startStyle);
/*     */     }
/* 114 */     String endStyle = element.getAttribute("tail");
/* 115 */     if (endStyle != null && !endStyle.isEmpty())
/*     */     {
/* 117 */       setEndPointEndingStyle(endStyle);
/*     */     }
/*     */     
/* 120 */     String color = element.getAttribute("interior-color");
/* 121 */     if (color != null && color.length() == 7 && color.charAt(0) == '#') {
/*     */       
/* 123 */       int colorValue = Integer.parseInt(color.substring(1, 7), 16);
/* 124 */       setInteriorColor(new Color(colorValue));
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
/* 135 */     COSArray newVertices = new COSArray();
/* 136 */     newVertices.setFloatArray(vertices);
/* 137 */     this.annot.setItem(COSName.VERTICES, (COSBase)newVertices);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getVertices() {
/* 147 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.VERTICES);
/* 148 */     if (array != null)
/*     */     {
/* 150 */       return array.toFloatArray();
/*     */     }
/*     */ 
/*     */     
/* 154 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartPointEndingStyle(String style) {
/* 165 */     if (style == null)
/*     */     {
/* 167 */       style = "None";
/*     */     }
/* 169 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.LE);
/* 170 */     if (array == null) {
/*     */       
/* 172 */       array = new COSArray();
/* 173 */       array.add((COSBase)COSName.getPDFName(style));
/* 174 */       array.add((COSBase)COSName.getPDFName("None"));
/* 175 */       this.annot.setItem(COSName.LE, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 179 */       array.setName(0, style);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStartPointEndingStyle() {
/* 190 */     String retval = "None";
/* 191 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.LE);
/* 192 */     if (array != null)
/*     */     {
/* 194 */       retval = array.getName(0);
/*     */     }
/*     */     
/* 197 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndPointEndingStyle(String style) {
/* 207 */     if (style == null)
/*     */     {
/* 209 */       style = "None";
/*     */     }
/* 211 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.LE);
/* 212 */     if (array == null) {
/*     */       
/* 214 */       array = new COSArray();
/* 215 */       array.add((COSBase)COSName.getPDFName("None"));
/* 216 */       array.add((COSBase)COSName.getPDFName(style));
/* 217 */       this.annot.setItem(COSName.LE, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 221 */       array.setName(1, style);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndPointEndingStyle() {
/* 232 */     String retval = "None";
/* 233 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.LE);
/* 234 */     if (array != null)
/*     */     {
/* 236 */       retval = array.getName(1);
/*     */     }
/*     */     
/* 239 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInteriorColor(Color color) {
/* 249 */     COSArray array = null;
/* 250 */     if (color != null) {
/*     */       
/* 252 */       float[] colors = color.getRGBColorComponents(null);
/* 253 */       array = new COSArray();
/* 254 */       array.setFloatArray(colors);
/*     */     } 
/* 256 */     this.annot.setItem(COSName.IC, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getInteriorColor() {
/* 266 */     Color retval = null;
/* 267 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.IC);
/* 268 */     if (array != null) {
/*     */       
/* 270 */       float[] rgb = array.toFloatArray();
/* 271 */       if (rgb.length >= 3)
/*     */       {
/* 273 */         retval = new Color(rgb[0], rgb[1], rgb[2]);
/*     */       }
/*     */     } 
/* 276 */     return retval;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationPolyline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */