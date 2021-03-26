/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.xpath.XPath;
/*     */ import javax.xml.xpath.XPathConstants;
/*     */ import javax.xml.xpath.XPathExpressionException;
/*     */ import javax.xml.xpath.XPathFactory;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class FDFAnnotationInk
/*     */   extends FDFAnnotation
/*     */ {
/*  45 */   private static final Log LOG = LogFactory.getLog(FDFAnnotationInk.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String SUBTYPE = "Ink";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationInk() {
/*  57 */     this.annot.setName(COSName.SUBTYPE, "Ink");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationInk(COSDictionary a) {
/*  67 */     super(a);
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
/*     */   public FDFAnnotationInk(Element element) throws IOException {
/*  79 */     super(element);
/*  80 */     this.annot.setName(COSName.SUBTYPE, "Ink");
/*     */     
/*  82 */     XPath xpath = XPathFactory.newInstance().newXPath();
/*     */     
/*     */     try {
/*  85 */       NodeList gestures = (NodeList)xpath.evaluate("inklist/gesture", element, XPathConstants.NODESET);
/*     */       
/*  87 */       if (gestures.getLength() == 0)
/*     */       {
/*  89 */         throw new IOException("Error: missing element 'gesture'");
/*     */       }
/*  91 */       List<float[]> inklist = (List)new ArrayList<float>();
/*  92 */       for (int i = 0; i < gestures.getLength(); i++) {
/*     */         
/*  94 */         Node node = gestures.item(i);
/*  95 */         if (node instanceof Element) {
/*     */           
/*  97 */           String gesture = node.getFirstChild().getNodeValue();
/*  98 */           String[] gestureValues = gesture.split(",|;");
/*  99 */           float[] values = new float[gestureValues.length];
/* 100 */           for (int j = 0; j < gestureValues.length; j++)
/*     */           {
/* 102 */             values[j] = Float.parseFloat(gestureValues[j]);
/*     */           }
/* 104 */           inklist.add(values);
/*     */         } 
/*     */       } 
/* 107 */       setInkList(inklist);
/*     */     }
/* 109 */     catch (XPathExpressionException e) {
/*     */       
/* 111 */       LOG.debug("Error while evaluating XPath expression for inklist gestures");
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
/*     */   public final void setInkList(List<float[]> inklist) {
/* 125 */     COSArray newInklist = new COSArray();
/* 126 */     for (float[] array : inklist) {
/*     */       
/* 128 */       COSArray newArray = new COSArray();
/* 129 */       newArray.setFloatArray(array);
/* 130 */       newInklist.add((COSBase)newArray);
/*     */     } 
/* 132 */     this.annot.setItem(COSName.INKLIST, (COSBase)newInklist);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<float[]> getInkList() {
/* 143 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.INKLIST);
/* 144 */     if (array != null) {
/*     */       
/* 146 */       List<float[]> retval = (List)new ArrayList<float>();
/* 147 */       for (COSBase entry : array)
/*     */       {
/* 149 */         retval.add(((COSArray)entry).toFloatArray());
/*     */       }
/* 151 */       return retval;
/*     */     } 
/*     */ 
/*     */     
/* 155 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationInk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */