/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGColorProfileElement;
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
/*     */ public class SVGOMColorProfileElement
/*     */   extends SVGOMURIReferenceElement
/*     */   implements SVGColorProfileElement
/*     */ {
/*  45 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(5); static {
/*  46 */     attributeInitializer.addAttribute(null, null, "rendering-intent", "auto");
/*     */ 
/*     */     
/*  49 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  52 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  54 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "other");
/*     */     
/*  56 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
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
/*     */   protected SVGOMColorProfileElement() {}
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
/*     */   public SVGOMColorProfileElement(String prefix, AbstractDocument owner) {
/*  90 */     super(prefix, owner);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  97 */     return "color-profile";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocal() {
/* 104 */     return getAttributeNS(null, "local");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocal(String local) throws DOMException {
/* 111 */     setAttributeNS(null, "local", local);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 118 */     return getAttributeNS(null, "name");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) throws DOMException {
/* 125 */     setAttributeNS(null, "name", name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getRenderingIntent() {
/* 133 */     Attr attr = getAttributeNodeNS(null, "rendering-intent");
/* 134 */     if (attr == null) {
/* 135 */       return 1;
/*     */     }
/* 137 */     String val = attr.getValue();
/* 138 */     switch (val.length()) {
/*     */       case 4:
/* 140 */         if (val.equals("auto")) {
/* 141 */           return 1;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 10:
/* 146 */         if (val.equals("perceptual")) {
/* 147 */           return 2;
/*     */         }
/* 149 */         if (val.equals("saturate")) {
/* 150 */           return 4;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 21:
/* 155 */         if (val.equals("absolute-colorimetric")) {
/* 156 */           return 5;
/*     */         }
/* 158 */         if (val.equals("relative-colorimetric"))
/* 159 */           return 3; 
/*     */         break;
/*     */     } 
/* 162 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingIntent(short renderingIntent) throws DOMException {
/* 170 */     switch (renderingIntent) {
/*     */       case 1:
/* 172 */         setAttributeNS(null, "rendering-intent", "auto");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 177 */         setAttributeNS(null, "rendering-intent", "perceptual");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 182 */         setAttributeNS(null, "rendering-intent", "relative-colorimetric");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 187 */         setAttributeNS(null, "rendering-intent", "saturate");
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 192 */         setAttributeNS(null, "rendering-intent", "absolute-colorimetric");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 202 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 209 */     return (Node)new SVGOMColorProfileElement();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMColorProfileElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */