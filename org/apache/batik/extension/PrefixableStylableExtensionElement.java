/*    */ package org.apache.batik.extension;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.dom.util.DOMUtilities;
/*    */ import org.w3c.dom.DOMException;
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
/*    */ public abstract class PrefixableStylableExtensionElement
/*    */   extends StylableExtensionElement
/*    */ {
/* 37 */   protected String prefix = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected PrefixableStylableExtensionElement() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PrefixableStylableExtensionElement(String prefix, AbstractDocument owner) {
/* 52 */     super(prefix, owner);
/* 53 */     setPrefix(prefix);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNodeName() {
/* 60 */     return (this.prefix == null || this.prefix.equals("")) ? getLocalName() : (this.prefix + ':' + getLocalName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPrefix(String prefix) throws DOMException {
/* 68 */     if (isReadonly()) {
/* 69 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 74 */     if (prefix != null && !prefix.equals("") && !DOMUtilities.isValidName(prefix))
/*    */     {
/*    */       
/* 77 */       throw createDOMException((short)5, "prefix", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), prefix });
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 84 */     this.prefix = prefix;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/PrefixableStylableExtensionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */