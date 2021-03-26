/*    */ package org.apache.batik.css.engine.sac;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.w3c.css.sac.ElementSelector;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractElementSelector
/*    */   implements ExtendedSelector, ElementSelector
/*    */ {
/*    */   protected String namespaceURI;
/*    */   protected String localName;
/*    */   
/*    */   protected AbstractElementSelector(String uri, String name) {
/* 50 */     this.namespaceURI = uri;
/* 51 */     this.localName = name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 59 */     if (obj == null || obj.getClass() != getClass()) {
/* 60 */       return false;
/*    */     }
/* 62 */     AbstractElementSelector s = (AbstractElementSelector)obj;
/* 63 */     return (s.namespaceURI.equals(this.namespaceURI) && s.localName.equals(this.localName));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNamespaceURI() {
/* 72 */     return this.namespaceURI;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 80 */     return this.localName;
/*    */   }
/*    */   
/*    */   public void fillAttributeSet(Set attrSet) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/AbstractElementSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */