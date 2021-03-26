/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGImageElement;
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
/*     */ public class SVGOMImageElement
/*     */   extends SVGURIReferenceGraphicsElement
/*     */   implements SVGImageElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  47 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGURIReferenceGraphicsElement.xmlTraitInformation);
/*     */     
/*  49 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  51 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  53 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  55 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
/*     */     
/*  57 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(5); static {
/*  66 */     attributeInitializer.addAttribute(null, null, "preserveAspectRatio", "xMidYMid meet");
/*     */ 
/*     */     
/*  69 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  72 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  74 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "embed");
/*     */     
/*  76 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength x;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength y;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength width;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength height;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedPreserveAspectRatio preserveAspectRatio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMImageElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMImageElement(String prefix, AbstractDocument owner) {
/* 117 */     super(prefix, owner);
/* 118 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 125 */     super.initializeAllLiveAttributes();
/* 126 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 133 */     this.x = createLiveAnimatedLength(null, "x", "0", (short)2, false);
/*     */ 
/*     */     
/* 136 */     this.y = createLiveAnimatedLength(null, "y", "0", (short)1, false);
/*     */ 
/*     */     
/* 139 */     this.width = createLiveAnimatedLength(null, "width", null, (short)2, true);
/*     */ 
/*     */ 
/*     */     
/* 143 */     this.height = createLiveAnimatedLength(null, "height", null, (short)1, true);
/*     */ 
/*     */ 
/*     */     
/* 147 */     this.preserveAspectRatio = createLiveAnimatedPreserveAspectRatio();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 154 */     return "image";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 161 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 168 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 175 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 182 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedPreserveAspectRatio getPreserveAspectRatio() {
/* 189 */     return this.preserveAspectRatio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 197 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 204 */     return (Node)new SVGOMImageElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 211 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMImageElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */