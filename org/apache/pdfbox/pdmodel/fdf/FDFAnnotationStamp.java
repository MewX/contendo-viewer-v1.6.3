/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.xml.xpath.XPath;
/*     */ import javax.xml.xpath.XPathExpressionException;
/*     */ import javax.xml.xpath.XPathFactory;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.util.Hex;
/*     */ import org.apache.pdfbox.util.XMLUtil;
/*     */ import org.w3c.dom.Document;
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
/*     */ 
/*     */ 
/*     */ public class FDFAnnotationStamp
/*     */   extends FDFAnnotation
/*     */ {
/*  53 */   private static final Log LOG = LogFactory.getLog(FDFAnnotationStamp.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String SUBTYPE = "Stamp";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationStamp() {
/*  65 */     this.annot.setName(COSName.SUBTYPE, "Stamp");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationStamp(COSDictionary a) {
/*  75 */     super(a);
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
/*     */   public FDFAnnotationStamp(Element element) throws IOException {
/*  87 */     super(element); String base64EncodedAppearance; byte[] decodedAppearanceXML;
/*  88 */     this.annot.setName(COSName.SUBTYPE, "Stamp");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     XPath xpath = XPathFactory.newInstance().newXPath();
/*     */ 
/*     */     
/*  96 */     LOG.debug("Get the DOM Document for the stamp appearance");
/*     */ 
/*     */     
/*     */     try {
/* 100 */       base64EncodedAppearance = xpath.evaluate("appearance", element);
/*     */     }
/* 102 */     catch (XPathExpressionException e) {
/*     */ 
/*     */       
/* 105 */       LOG.error("Error while evaluating XPath expression for appearance: " + e);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/* 111 */       decodedAppearanceXML = Hex.decodeBase64(base64EncodedAppearance);
/*     */     }
/* 113 */     catch (IllegalArgumentException ex) {
/*     */       
/* 115 */       LOG.error("Bad base64 encoded appearance ignored", ex);
/*     */       return;
/*     */     } 
/* 118 */     if (base64EncodedAppearance != null && !base64EncodedAppearance.isEmpty()) {
/*     */       
/* 120 */       LOG.debug("Decoded XML: " + new String(decodedAppearanceXML));
/*     */ 
/*     */       
/* 123 */       Document stampAppearance = XMLUtil.parse(new ByteArrayInputStream(decodedAppearanceXML));
/*     */       
/* 125 */       Element appearanceEl = stampAppearance.getDocumentElement();
/*     */ 
/*     */       
/* 128 */       if (!"dict".equalsIgnoreCase(appearanceEl.getNodeName()))
/*     */       {
/* 130 */         throw new IOException("Error while reading stamp document, root should be 'dict' and not '" + appearanceEl
/* 131 */             .getNodeName() + "'");
/*     */       }
/* 133 */       LOG.debug("Generate and set the appearance dictionary to the stamp annotation");
/* 134 */       this.annot.setItem(COSName.AP, (COSBase)parseStampAnnotationAppearanceXML(appearanceEl));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSDictionary parseStampAnnotationAppearanceXML(Element appearanceXML) throws IOException {
/* 145 */     COSDictionary dictionary = new COSDictionary();
/*     */     
/* 147 */     dictionary.setItem(COSName.N, (COSBase)new COSStream());
/* 148 */     LOG.debug("Build dictionary for Appearance based on the appearanceXML");
/*     */     
/* 150 */     NodeList nodeList = appearanceXML.getChildNodes();
/* 151 */     String parentAttrKey = appearanceXML.getAttribute("KEY");
/* 152 */     LOG.debug("Appearance Root - tag: " + appearanceXML.getTagName() + ", name: " + appearanceXML
/* 153 */         .getNodeName() + ", key: " + parentAttrKey + ", children: " + nodeList
/* 154 */         .getLength());
/*     */ 
/*     */     
/* 157 */     if (!"AP".equals(appearanceXML.getAttribute("KEY"))) {
/*     */       
/* 159 */       LOG.warn(parentAttrKey + " => Not handling element: " + appearanceXML.getTagName() + " with key: " + appearanceXML
/* 160 */           .getAttribute("KEY"));
/* 161 */       return dictionary;
/*     */     } 
/* 163 */     for (int i = 0; i < nodeList.getLength(); i++) {
/*     */       
/* 165 */       Node node = nodeList.item(i);
/* 166 */       if (node instanceof Element) {
/*     */         
/* 168 */         Element child = (Element)node;
/* 169 */         if ("STREAM".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 171 */           LOG.debug(parentAttrKey + " => Process " + child
/* 172 */               .getAttribute("KEY") + " item in the dictionary after processing the " + child
/*     */               
/* 174 */               .getTagName());
/* 175 */           dictionary.setItem(child.getAttribute("KEY"), (COSBase)parseStreamElement(child));
/* 176 */           LOG.debug(parentAttrKey + " => Set " + child.getAttribute("KEY"));
/*     */         }
/*     */         else {
/*     */           
/* 180 */           LOG.warn(parentAttrKey + " => Not handling element: " + child.getTagName());
/*     */         } 
/*     */       } 
/*     */     } 
/* 184 */     return dictionary;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSStream parseStreamElement(Element streamEl) throws IOException {
/* 189 */     LOG.debug("Parse " + streamEl.getAttribute("KEY") + " Stream");
/* 190 */     COSStream stream = new COSStream();
/*     */     
/* 192 */     NodeList nodeList = streamEl.getChildNodes();
/* 193 */     String parentAttrKey = streamEl.getAttribute("KEY");
/*     */     
/* 195 */     for (int i = 0; i < nodeList.getLength(); i++) {
/*     */       
/* 197 */       Node node = nodeList.item(i);
/* 198 */       if (node instanceof Element) {
/*     */         
/* 200 */         Element child = (Element)node;
/* 201 */         String childAttrKey = child.getAttribute("KEY");
/* 202 */         String childAttrVal = child.getAttribute("VAL");
/* 203 */         LOG.debug(parentAttrKey + " => reading child: " + child.getTagName() + " with key: " + childAttrKey);
/*     */         
/* 205 */         if ("INT".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 207 */           if (!"Length".equals(childAttrKey))
/*     */           {
/* 209 */             stream.setInt(COSName.getPDFName(childAttrKey), Integer.parseInt(childAttrVal));
/* 210 */             LOG.debug(parentAttrKey + " => Set " + childAttrKey + ": " + childAttrVal);
/*     */           }
/*     */         
/* 213 */         } else if ("FIXED".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 215 */           stream.setFloat(COSName.getPDFName(childAttrKey), Float.parseFloat(childAttrVal));
/* 216 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey + ": " + childAttrVal);
/*     */         }
/* 218 */         else if ("NAME".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 220 */           stream.setName(COSName.getPDFName(childAttrKey), childAttrVal);
/* 221 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey + ": " + childAttrVal);
/*     */         }
/* 223 */         else if ("BOOL".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 225 */           stream.setBoolean(COSName.getPDFName(childAttrKey), Boolean.parseBoolean(childAttrVal));
/* 226 */           LOG.debug(parentAttrKey + " => Set " + childAttrVal);
/*     */         }
/* 228 */         else if ("ARRAY".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 230 */           stream.setItem(COSName.getPDFName(childAttrKey), (COSBase)parseArrayElement(child));
/* 231 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey);
/*     */         }
/* 233 */         else if ("DICT".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 235 */           stream.setItem(COSName.getPDFName(childAttrKey), (COSBase)parseDictElement(child));
/* 236 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey);
/*     */         }
/* 238 */         else if ("STREAM".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 240 */           stream.setItem(COSName.getPDFName(childAttrKey), (COSBase)parseStreamElement(child));
/* 241 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey);
/*     */         }
/* 243 */         else if ("DATA".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 245 */           LOG.debug(parentAttrKey + " => Handling DATA with encoding: " + child
/* 246 */               .getAttribute("ENCODING"));
/* 247 */           if ("HEX".equals(child.getAttribute("ENCODING")))
/*     */           {
/* 249 */             OutputStream os = null;
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
/*     */           }
/* 261 */           else if ("ASCII".equals(child.getAttribute("ENCODING")))
/*     */           {
/* 263 */             OutputStream os = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */           else
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 278 */             LOG.warn(parentAttrKey + " => Not handling element DATA encoding: " + child
/* 279 */                 .getAttribute("ENCODING"));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 284 */           LOG.warn(parentAttrKey + " => Not handling child element: " + child.getTagName());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 289 */     return stream;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSArray parseArrayElement(Element arrayEl) throws IOException {
/* 294 */     LOG.debug("Parse " + arrayEl.getAttribute("KEY") + " Array");
/* 295 */     COSArray array = new COSArray();
/*     */     
/* 297 */     NodeList nodeList = arrayEl.getChildNodes();
/* 298 */     String parentAttrKey = arrayEl.getAttribute("KEY");
/*     */     
/* 300 */     if ("BBox".equals(parentAttrKey)) {
/*     */       
/* 302 */       if (nodeList.getLength() < 4)
/*     */       {
/* 304 */         throw new IOException("BBox does not have enough coordinates, only has: " + nodeList
/* 305 */             .getLength());
/*     */       }
/*     */     }
/* 308 */     else if ("Matrix".equals(parentAttrKey)) {
/*     */       
/* 310 */       if (nodeList.getLength() < 6)
/*     */       {
/* 312 */         throw new IOException("Matrix does not have enough coordinates, only has: " + nodeList
/* 313 */             .getLength());
/*     */       }
/*     */     } 
/*     */     
/* 317 */     for (int i = 0; i < nodeList.getLength(); i++) {
/*     */       
/* 319 */       Node node = nodeList.item(i);
/* 320 */       if (node instanceof Element) {
/*     */         
/* 322 */         Element child = (Element)node;
/* 323 */         String childAttrKey = child.getAttribute("KEY");
/* 324 */         String childAttrVal = child.getAttribute("VAL");
/* 325 */         LOG.debug(parentAttrKey + " => reading child: " + child.getTagName() + " with key: " + childAttrKey);
/*     */         
/* 327 */         if ("INT".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 329 */           LOG.debug(parentAttrKey + " value(" + i + "): " + child.getAttribute("VAL"));
/* 330 */           array.add((COSBase)COSFloat.get(childAttrVal));
/*     */         }
/* 332 */         else if ("FIXED".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 334 */           LOG.debug(parentAttrKey + " value(" + i + "): " + child.getAttribute("VAL"));
/* 335 */           array.add((COSBase)COSInteger.get(childAttrVal));
/*     */         }
/* 337 */         else if ("NAME".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 339 */           LOG.debug(parentAttrKey + " value(" + i + "): " + child.getAttribute("VAL"));
/* 340 */           array.add((COSBase)COSName.getPDFName(childAttrVal));
/*     */         }
/* 342 */         else if ("BOOL".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 344 */           LOG.debug(parentAttrKey + " value(" + i + "): " + child.getAttribute("VAL"));
/* 345 */           array.add((COSBase)COSBoolean.getBoolean(Boolean.parseBoolean(childAttrVal)));
/*     */         }
/* 347 */         else if ("DICT".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 349 */           LOG.debug(parentAttrKey + " value(" + i + "): " + child.getAttribute("VAL"));
/* 350 */           array.add((COSBase)parseDictElement(child));
/*     */         }
/* 352 */         else if ("STREAM".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 354 */           LOG.debug(parentAttrKey + " value(" + i + "): " + child.getAttribute("VAL"));
/* 355 */           array.add((COSBase)parseStreamElement(child));
/*     */         }
/* 357 */         else if ("ARRAY".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 359 */           LOG.debug(parentAttrKey + " value(" + i + "): " + child.getAttribute("VAL"));
/* 360 */           array.add((COSBase)parseArrayElement(child));
/*     */         }
/*     */         else {
/*     */           
/* 364 */           LOG.warn(parentAttrKey + " => Not handling child element: " + child.getTagName());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 369 */     return array;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSDictionary parseDictElement(Element dictEl) throws IOException {
/* 374 */     LOG.debug("Parse " + dictEl.getAttribute("KEY") + " Dictionary");
/* 375 */     COSDictionary dict = new COSDictionary();
/*     */     
/* 377 */     NodeList nodeList = dictEl.getChildNodes();
/* 378 */     String parentAttrKey = dictEl.getAttribute("KEY");
/*     */     
/* 380 */     for (int i = 0; i < nodeList.getLength(); i++) {
/*     */       
/* 382 */       Node node = nodeList.item(i);
/* 383 */       if (node instanceof Element) {
/*     */         
/* 385 */         Element child = (Element)node;
/* 386 */         String childAttrKey = child.getAttribute("KEY");
/* 387 */         String childAttrVal = child.getAttribute("VAL");
/*     */         
/* 389 */         if ("DICT".equals(child.getTagName())) {
/*     */           
/* 391 */           LOG.debug(parentAttrKey + " => Handling DICT element with key: " + childAttrKey);
/* 392 */           dict.setItem(COSName.getPDFName(childAttrKey), (COSBase)parseDictElement(child));
/* 393 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey);
/*     */         }
/* 395 */         else if ("STREAM".equals(child.getTagName())) {
/*     */           
/* 397 */           LOG.debug(parentAttrKey + " => Handling STREAM element with key: " + childAttrKey);
/* 398 */           dict.setItem(COSName.getPDFName(childAttrKey), (COSBase)parseStreamElement(child));
/*     */         }
/* 400 */         else if ("NAME".equals(child.getTagName())) {
/*     */           
/* 402 */           LOG.debug(parentAttrKey + " => Handling NAME element with key: " + childAttrKey);
/* 403 */           dict.setName(COSName.getPDFName(childAttrKey), childAttrVal);
/* 404 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey + ": " + childAttrVal);
/*     */         }
/* 406 */         else if ("INT".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 408 */           dict.setInt(COSName.getPDFName(childAttrKey), Integer.parseInt(childAttrVal));
/* 409 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey + ": " + childAttrVal);
/*     */         }
/* 411 */         else if ("FIXED".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 413 */           dict.setFloat(COSName.getPDFName(childAttrKey), Float.parseFloat(childAttrVal));
/* 414 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey + ": " + childAttrVal);
/*     */         }
/* 416 */         else if ("BOOL".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 418 */           dict.setBoolean(COSName.getPDFName(childAttrKey), Boolean.parseBoolean(childAttrVal));
/* 419 */           LOG.debug(parentAttrKey + " => Set " + childAttrVal);
/*     */         }
/* 421 */         else if ("ARRAY".equalsIgnoreCase(child.getTagName())) {
/*     */           
/* 423 */           dict.setItem(COSName.getPDFName(childAttrKey), (COSBase)parseArrayElement(child));
/* 424 */           LOG.debug(parentAttrKey + " => Set " + childAttrKey);
/*     */         }
/*     */         else {
/*     */           
/* 428 */           LOG.warn(parentAttrKey + " => NOT handling child element: " + child.getTagName());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 433 */     return dict;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationStamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */