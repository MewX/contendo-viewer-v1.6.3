/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
/*     */ import org.apache.pdfbox.pdmodel.common.filespecification.PDSimpleFileSpecification;
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
/*     */ public class FDFDictionary
/*     */   implements COSObjectable
/*     */ {
/*  47 */   private static final Log LOG = LogFactory.getLog(FDFDictionary.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private COSDictionary fdf;
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFDictionary() {
/*  56 */     this.fdf = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFDictionary(COSDictionary fdfDictionary) {
/*  66 */     this.fdf = fdfDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFDictionary(Element fdfXML) {
/*  76 */     this();
/*  77 */     NodeList nodeList = fdfXML.getChildNodes();
/*  78 */     for (int i = 0; i < nodeList.getLength(); i++) {
/*     */       
/*  80 */       Node node = nodeList.item(i);
/*  81 */       if (node instanceof Element) {
/*     */         
/*  83 */         Element child = (Element)node;
/*  84 */         if (child.getTagName().equals("f")) {
/*     */           
/*  86 */           PDSimpleFileSpecification fs = new PDSimpleFileSpecification();
/*  87 */           fs.setFile(child.getAttribute("href"));
/*  88 */           setFile((PDFileSpecification)fs);
/*     */         }
/*  90 */         else if (child.getTagName().equals("ids")) {
/*     */           
/*  92 */           COSArray ids = new COSArray();
/*  93 */           String original = child.getAttribute("original");
/*  94 */           String modified = child.getAttribute("modified");
/*     */           
/*     */           try {
/*  97 */             ids.add((COSBase)COSString.parseHex(original));
/*     */           }
/*  99 */           catch (IOException e) {
/*     */             
/* 101 */             LOG.warn("Error parsing ID entry for attribute 'original' [" + original + "]. ID entry ignored.", e);
/*     */           } 
/*     */ 
/*     */           
/*     */           try {
/* 106 */             ids.add((COSBase)COSString.parseHex(modified));
/*     */           }
/* 108 */           catch (IOException e) {
/*     */             
/* 110 */             LOG.warn("Error parsing ID entry for attribute 'modified' [" + modified + "]. ID entry ignored.", e);
/*     */           } 
/*     */           
/* 113 */           setID(ids);
/*     */         }
/* 115 */         else if (child.getTagName().equals("fields")) {
/*     */           
/* 117 */           NodeList fields = child.getChildNodes();
/* 118 */           List<FDFField> fieldList = new ArrayList<FDFField>();
/* 119 */           for (int f = 0; f < fields.getLength(); f++) {
/*     */             
/* 121 */             Node currentNode = fields.item(f);
/* 122 */             if (currentNode instanceof Element && ((Element)currentNode)
/* 123 */               .getTagName().equals("field")) {
/*     */               
/*     */               try {
/*     */                 
/* 127 */                 fieldList.add(new FDFField((Element)fields.item(f)));
/*     */               }
/* 129 */               catch (IOException e) {
/*     */                 
/* 131 */                 LOG.warn("Error parsing field entry [" + currentNode.getNodeValue() + "]. Field ignored.", e);
/*     */               } 
/*     */             }
/*     */           } 
/*     */           
/* 136 */           setFields(fieldList);
/*     */         }
/* 138 */         else if (child.getTagName().equals("annots")) {
/*     */           
/* 140 */           NodeList annots = child.getChildNodes();
/* 141 */           List<FDFAnnotation> annotList = new ArrayList<FDFAnnotation>();
/* 142 */           for (int j = 0; j < annots.getLength(); j++) {
/*     */             
/* 144 */             Node annotNode = annots.item(j);
/* 145 */             if (annotNode instanceof Element) {
/*     */ 
/*     */ 
/*     */               
/* 149 */               Element annot = (Element)annotNode;
/* 150 */               String annotationName = annot.getNodeName();
/*     */               
/*     */               try {
/* 153 */                 if (annotationName.equals("text"))
/*     */                 {
/* 155 */                   annotList.add(new FDFAnnotationText(annot));
/*     */                 }
/* 157 */                 else if (annotationName.equals("caret"))
/*     */                 {
/* 159 */                   annotList.add(new FDFAnnotationCaret(annot));
/*     */                 }
/* 161 */                 else if (annotationName.equals("freetext"))
/*     */                 {
/* 163 */                   annotList.add(new FDFAnnotationFreeText(annot));
/*     */                 }
/* 165 */                 else if (annotationName.equals("fileattachment"))
/*     */                 {
/* 167 */                   annotList.add(new FDFAnnotationFileAttachment(annot));
/*     */                 }
/* 169 */                 else if (annotationName.equals("highlight"))
/*     */                 {
/* 171 */                   annotList.add(new FDFAnnotationHighlight(annot));
/*     */                 }
/* 173 */                 else if (annotationName.equals("ink"))
/*     */                 {
/* 175 */                   annotList.add(new FDFAnnotationInk(annot));
/*     */                 }
/* 177 */                 else if (annotationName.equals("line"))
/*     */                 {
/* 179 */                   annotList.add(new FDFAnnotationLine(annot));
/*     */                 }
/* 181 */                 else if (annotationName.equals("link"))
/*     */                 {
/* 183 */                   annotList.add(new FDFAnnotationLink(annot));
/*     */                 }
/* 185 */                 else if (annotationName.equals("circle"))
/*     */                 {
/* 187 */                   annotList.add(new FDFAnnotationCircle(annot));
/*     */                 }
/* 189 */                 else if (annotationName.equals("square"))
/*     */                 {
/* 191 */                   annotList.add(new FDFAnnotationSquare(annot));
/*     */                 }
/* 193 */                 else if (annotationName.equals("polygon"))
/*     */                 {
/* 195 */                   annotList.add(new FDFAnnotationPolygon(annot));
/*     */                 }
/* 197 */                 else if (annotationName.equals("polyline"))
/*     */                 {
/* 199 */                   annotList.add(new FDFAnnotationPolyline(annot));
/*     */                 }
/* 201 */                 else if (annotationName.equals("sound"))
/*     */                 {
/* 203 */                   annotList.add(new FDFAnnotationSound(annot));
/*     */                 }
/* 205 */                 else if (annotationName.equals("squiggly"))
/*     */                 {
/* 207 */                   annotList.add(new FDFAnnotationSquiggly(annot));
/*     */                 }
/* 209 */                 else if (annotationName.equals("stamp"))
/*     */                 {
/* 211 */                   annotList.add(new FDFAnnotationStamp(annot));
/*     */                 }
/* 213 */                 else if (annotationName.equals("strikeout"))
/*     */                 {
/* 215 */                   annotList.add(new FDFAnnotationStrikeOut(annot));
/*     */                 }
/* 217 */                 else if (annotationName.equals("underline"))
/*     */                 {
/* 219 */                   annotList.add(new FDFAnnotationUnderline(annot));
/*     */                 }
/*     */                 else
/*     */                 {
/* 223 */                   LOG.warn("Unknown or unsupported annotation type '" + annotationName + "'");
/*     */                 }
/*     */               
/*     */               }
/* 227 */               catch (IOException e) {
/*     */                 
/* 229 */                 LOG.warn("Error parsing annotation information [" + annot
/*     */                     
/* 231 */                     .getNodeValue() + "]. Annotation ignored", e);
/*     */               } 
/*     */             } 
/*     */           } 
/* 235 */           setAnnotations(annotList);
/*     */         } 
/*     */       } 
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
/*     */   public void writeXML(Writer output) throws IOException {
/* 250 */     PDFileSpecification fs = getFile();
/* 251 */     if (fs != null)
/*     */     {
/* 253 */       output.write("<f href=\"" + fs.getFile() + "\" />\n");
/*     */     }
/* 255 */     COSArray ids = getID();
/* 256 */     if (ids != null) {
/*     */       
/* 258 */       COSString original = (COSString)ids.getObject(0);
/* 259 */       COSString modified = (COSString)ids.getObject(1);
/* 260 */       output.write("<ids original=\"" + original.toHexString() + "\" ");
/* 261 */       output.write("modified=\"" + modified.toHexString() + "\" />\n");
/*     */     } 
/* 263 */     List<FDFField> fields = getFields();
/* 264 */     if (fields != null && fields.size() > 0) {
/*     */       
/* 266 */       output.write("<fields>\n");
/* 267 */       for (FDFField field : fields)
/*     */       {
/* 269 */         field.writeXML(output);
/*     */       }
/* 271 */       output.write("</fields>\n");
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
/*     */   public COSDictionary getCOSObject() {
/* 283 */     return this.fdf;
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
/*     */   public PDFileSpecification getFile() throws IOException {
/* 296 */     return PDFileSpecification.createFS(this.fdf.getDictionaryObject(COSName.F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFile(PDFileSpecification fs) {
/* 306 */     this.fdf.setItem(COSName.F, (COSObjectable)fs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getID() {
/* 316 */     return (COSArray)this.fdf.getDictionaryObject(COSName.ID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setID(COSArray id) {
/* 326 */     this.fdf.setItem(COSName.ID, (COSBase)id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<FDFField> getFields() {
/*     */     COSArrayList cOSArrayList;
/* 336 */     List<FDFField> retval = null;
/* 337 */     COSArray fieldArray = (COSArray)this.fdf.getDictionaryObject(COSName.FIELDS);
/* 338 */     if (fieldArray != null) {
/*     */       
/* 340 */       List<FDFField> fields = new ArrayList<FDFField>();
/* 341 */       for (int i = 0; i < fieldArray.size(); i++)
/*     */       {
/* 343 */         fields.add(new FDFField((COSDictionary)fieldArray.getObject(i)));
/*     */       }
/* 345 */       cOSArrayList = new COSArrayList(fields, fieldArray);
/*     */     } 
/* 347 */     return (List<FDFField>)cOSArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFields(List<FDFField> fields) {
/* 357 */     this.fdf.setItem(COSName.FIELDS, (COSBase)COSArrayList.converterToCOSArray(fields));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/* 367 */     return this.fdf.getString(COSName.STATUS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatus(String status) {
/* 377 */     this.fdf.setString(COSName.STATUS, status);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<FDFPage> getPages() {
/*     */     COSArrayList cOSArrayList;
/* 387 */     List<FDFPage> retval = null;
/* 388 */     COSArray pageArray = (COSArray)this.fdf.getDictionaryObject(COSName.PAGES);
/* 389 */     if (pageArray != null) {
/*     */       
/* 391 */       List<FDFPage> pages = new ArrayList<FDFPage>();
/* 392 */       for (int i = 0; i < pageArray.size(); i++)
/*     */       {
/* 394 */         pages.add(new FDFPage((COSDictionary)pageArray.get(i)));
/*     */       }
/* 396 */       cOSArrayList = new COSArrayList(pages, pageArray);
/*     */     } 
/* 398 */     return (List<FDFPage>)cOSArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPages(List<FDFPage> pages) {
/* 409 */     this.fdf.setItem(COSName.PAGES, (COSBase)COSArrayList.converterToCOSArray(pages));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEncoding() {
/* 419 */     String encoding = this.fdf.getNameAsString(COSName.ENCODING);
/* 420 */     if (encoding == null)
/*     */     {
/* 422 */       encoding = "PDFDocEncoding";
/*     */     }
/* 424 */     return encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncoding(String encoding) {
/* 435 */     this.fdf.setName(COSName.ENCODING, encoding);
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
/*     */   public List<FDFAnnotation> getAnnotations() throws IOException {
/*     */     COSArrayList cOSArrayList;
/* 448 */     List<FDFAnnotation> retval = null;
/* 449 */     COSArray annotArray = (COSArray)this.fdf.getDictionaryObject(COSName.ANNOTS);
/* 450 */     if (annotArray != null) {
/*     */       
/* 452 */       List<FDFAnnotation> annots = new ArrayList<FDFAnnotation>();
/* 453 */       for (int i = 0; i < annotArray.size(); i++)
/*     */       {
/* 455 */         annots.add(FDFAnnotation.create((COSDictionary)annotArray.getObject(i)));
/*     */       }
/* 457 */       cOSArrayList = new COSArrayList(annots, annotArray);
/*     */     } 
/* 459 */     return (List<FDFAnnotation>)cOSArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnnotations(List<FDFAnnotation> annots) {
/* 470 */     this.fdf.setItem(COSName.ANNOTS, (COSBase)COSArrayList.converterToCOSArray(annots));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStream getDifferences() {
/* 480 */     return (COSStream)this.fdf.getDictionaryObject(COSName.DIFFERENCES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDifferences(COSStream diff) {
/* 490 */     this.fdf.setItem(COSName.DIFFERENCES, (COSBase)diff);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTarget() {
/* 500 */     return this.fdf.getString(COSName.TARGET);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(String target) {
/* 510 */     this.fdf.setString(COSName.TARGET, target);
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
/*     */   public List<PDFileSpecification> getEmbeddedFDFs() throws IOException {
/*     */     COSArrayList cOSArrayList;
/* 523 */     List<PDFileSpecification> retval = null;
/* 524 */     COSArray embeddedArray = (COSArray)this.fdf.getDictionaryObject(COSName.EMBEDDED_FDFS);
/* 525 */     if (embeddedArray != null) {
/*     */       
/* 527 */       List<PDFileSpecification> embedded = new ArrayList<PDFileSpecification>();
/* 528 */       for (int i = 0; i < embeddedArray.size(); i++)
/*     */       {
/* 530 */         embedded.add(PDFileSpecification.createFS(embeddedArray.get(i)));
/*     */       }
/* 532 */       cOSArrayList = new COSArrayList(embedded, embeddedArray);
/*     */     } 
/* 534 */     return (List<PDFileSpecification>)cOSArrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEmbeddedFDFs(List<PDFileSpecification> embedded) {
/* 545 */     this.fdf.setItem(COSName.EMBEDDED_FDFS, (COSBase)COSArrayList.converterToCOSArray(embedded));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFJavaScript getJavaScript() {
/* 555 */     FDFJavaScript fs = null;
/* 556 */     COSDictionary dic = (COSDictionary)this.fdf.getDictionaryObject(COSName.JAVA_SCRIPT);
/* 557 */     if (dic != null)
/*     */     {
/* 559 */       fs = new FDFJavaScript(dic);
/*     */     }
/* 561 */     return fs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJavaScript(FDFJavaScript js) {
/* 571 */     this.fdf.setItem(COSName.JAVA_SCRIPT, js);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */