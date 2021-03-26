/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.extension.PrefixableStylableExtensionElement;
/*    */ import org.w3c.dom.Node;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColorSwitchElement
/*    */   extends PrefixableStylableExtensionElement
/*    */   implements BatikExtConstants
/*    */ {
/*    */   protected ColorSwitchElement() {}
/*    */   
/*    */   public ColorSwitchElement(String prefix, AbstractDocument owner) {
/* 47 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 54 */     return "colorSwitch";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNamespaceURI() {
/* 61 */     return "http://xml.apache.org/batik/ext";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 68 */     return (Node)new ColorSwitchElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/ColorSwitchElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */