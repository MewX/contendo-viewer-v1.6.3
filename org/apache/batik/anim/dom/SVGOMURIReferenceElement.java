/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.util.DoublyIndexedTable;
/*    */ import org.w3c.dom.svg.SVGAnimatedString;
/*    */ import org.w3c.dom.svg.SVGURIReference;
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
/*    */ public abstract class SVGOMURIReferenceElement
/*    */   extends SVGOMElement
/*    */   implements SVGURIReference
/*    */ {
/*    */   protected static DoublyIndexedTable xmlTraitInformation;
/*    */   protected SVGOMAnimatedString href;
/*    */   
/*    */   static {
/* 42 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMElement.xmlTraitInformation);
/*    */     
/* 44 */     t.put("http://www.w3.org/1999/xlink", "href", new TraitInformation(true, 10));
/*    */     
/* 46 */     xmlTraitInformation = t;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SVGOMURIReferenceElement() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SVGOMURIReferenceElement(String prefix, AbstractDocument owner) {
/* 66 */     super(prefix, owner);
/* 67 */     initializeLiveAttributes();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void initializeAllLiveAttributes() {
/* 74 */     super.initializeAllLiveAttributes();
/* 75 */     initializeLiveAttributes();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void initializeLiveAttributes() {
/* 82 */     this.href = createLiveAnimatedString("http://www.w3.org/1999/xlink", "href");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGAnimatedString getHref() {
/* 90 */     return this.href;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected DoublyIndexedTable getTraitInformationTable() {
/* 97 */     return xmlTraitInformation;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMURIReferenceElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */