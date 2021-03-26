/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAElement;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
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
/*     */ public class SVGOMAElement
/*     */   extends SVGURIReferenceGraphicsElement
/*     */   implements SVGAElement
/*     */ {
/*  46 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(4); static {
/*  47 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  50 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  52 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "replace");
/*     */     
/*  54 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onRequest");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGURIReferenceGraphicsElement.xmlTraitInformation);
/*     */     
/*  65 */     t.put(null, "target", new TraitInformation(true, 16));
/*     */     
/*  67 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedString target;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAElement() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMAElement(String prefix, AbstractDocument owner) {
/*  87 */     super(prefix, owner);
/*  88 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  95 */     super.initializeAllLiveAttributes();
/*  96 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 103 */     this.target = createLiveAnimatedString(null, "target");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 110 */     return "a";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getTarget() {
/* 117 */     return this.target;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 125 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 132 */     return (Node)new SVGOMAElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 139 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */