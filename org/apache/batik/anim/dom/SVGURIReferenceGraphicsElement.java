/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.util.DoublyIndexedTable;
/*    */ import org.w3c.dom.svg.SVGAnimatedString;
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
/*    */ 
/*    */ public abstract class SVGURIReferenceGraphicsElement
/*    */   extends SVGGraphicsElement
/*    */ {
/*    */   protected static DoublyIndexedTable xmlTraitInformation;
/*    */   protected SVGOMAnimatedString href;
/*    */   
/*    */   static {
/* 41 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*    */     
/* 43 */     t.put("http://www.w3.org/1999/xlink", "href", new TraitInformation(true, 10));
/*    */     
/* 45 */     xmlTraitInformation = t;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SVGURIReferenceGraphicsElement() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SVGURIReferenceGraphicsElement(String prefix, AbstractDocument owner) {
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGURIReferenceGraphicsElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */