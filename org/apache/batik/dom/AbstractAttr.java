/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.TypeInfo;
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
/*     */ public abstract class AbstractAttr
/*     */   extends AbstractParentNode
/*     */   implements Attr
/*     */ {
/*     */   protected String nodeName;
/*     */   protected boolean unspecified;
/*     */   protected boolean isIdAttr;
/*     */   protected AbstractElement ownerElement;
/*     */   protected TypeInfo typeInfo;
/*     */   
/*     */   protected AbstractAttr() {}
/*     */   
/*     */   protected AbstractAttr(String name, AbstractDocument owner) throws DOMException {
/*  77 */     this.ownerDocument = owner;
/*  78 */     if (owner.getStrictErrorChecking() && !DOMUtilities.isValidName(name)) {
/*  79 */       throw createDOMException((short)5, "xml.name", new Object[] { name });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNodeName(String v) {
/*  89 */     this.nodeName = v;
/*  90 */     this.isIdAttr = this.ownerDocument.isId(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  98 */     return this.nodeName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getNodeType() {
/* 106 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeValue() throws DOMException {
/* 114 */     Node first = getFirstChild();
/* 115 */     if (first == null) {
/* 116 */       return "";
/*     */     }
/* 118 */     Node n = first.getNextSibling();
/* 119 */     if (n == null) {
/* 120 */       return first.getNodeValue();
/*     */     }
/* 122 */     StringBuffer result = new StringBuffer(first.getNodeValue());
/*     */     while (true) {
/* 124 */       result.append(n.getNodeValue());
/* 125 */       n = n.getNextSibling();
/* 126 */       if (n == null) {
/* 127 */         return result.toString();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNodeValue(String nodeValue) throws DOMException {
/* 134 */     if (isReadonly()) {
/* 135 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     String s = getNodeValue();
/*     */     
/*     */     Node n;
/*     */     
/* 145 */     while ((n = getFirstChild()) != null) {
/* 146 */       removeChild(n);
/*     */     }
/*     */     
/* 149 */     String val = (nodeValue == null) ? "" : nodeValue;
/*     */ 
/*     */     
/* 152 */     n = getOwnerDocument().createTextNode(val);
/* 153 */     appendChild(n);
/*     */     
/* 155 */     if (this.ownerElement != null) {
/* 156 */       this.ownerElement.fireDOMAttrModifiedEvent(this.nodeName, this, s, val, (short)1);
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
/*     */   public String getName() {
/* 169 */     return getNodeName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSpecified() {
/* 177 */     return !this.unspecified;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpecified(boolean v) {
/* 184 */     this.unspecified = !v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/* 192 */     return getNodeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(String value) throws DOMException {
/* 199 */     setNodeValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwnerElement(AbstractElement v) {
/* 206 */     this.ownerElement = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element getOwnerElement() {
/* 213 */     return this.ownerElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeInfo getSchemaTypeInfo() {
/* 220 */     if (this.typeInfo == null) {
/* 221 */       this.typeInfo = new AttrTypeInfo();
/*     */     }
/* 223 */     return this.typeInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isId() {
/* 230 */     return this.isIdAttr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsId(boolean isId) {
/* 237 */     this.isIdAttr = isId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void nodeAdded(Node n) {
/* 244 */     setSpecified(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void nodeToBeRemoved(Node n) {
/* 251 */     setSpecified(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node export(Node n, AbstractDocument d) {
/* 258 */     super.export(n, d);
/* 259 */     AbstractAttr aa = (AbstractAttr)n;
/* 260 */     aa.nodeName = this.nodeName;
/* 261 */     aa.unspecified = false;
/* 262 */     aa.isIdAttr = d.isId(aa);
/* 263 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepExport(Node n, AbstractDocument d) {
/* 270 */     super.deepExport(n, d);
/* 271 */     AbstractAttr aa = (AbstractAttr)n;
/* 272 */     aa.nodeName = this.nodeName;
/* 273 */     aa.unspecified = false;
/* 274 */     aa.isIdAttr = d.isId(aa);
/* 275 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node copyInto(Node n) {
/* 283 */     super.copyInto(n);
/* 284 */     AbstractAttr aa = (AbstractAttr)n;
/* 285 */     aa.nodeName = this.nodeName;
/* 286 */     aa.unspecified = this.unspecified;
/* 287 */     aa.isIdAttr = this.isIdAttr;
/* 288 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node deepCopyInto(Node n) {
/* 296 */     super.deepCopyInto(n);
/* 297 */     AbstractAttr aa = (AbstractAttr)n;
/* 298 */     aa.nodeName = this.nodeName;
/* 299 */     aa.unspecified = this.unspecified;
/* 300 */     aa.isIdAttr = this.isIdAttr;
/* 301 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkChildType(Node n, boolean replace) {
/* 308 */     switch (n.getNodeType()) {
/*     */       case 3:
/*     */       case 5:
/*     */       case 11:
/*     */         return;
/*     */     } 
/* 314 */     throw createDOMException((short)3, "child.type", new Object[] { Integer.valueOf(getNodeType()), getNodeName(), Integer.valueOf(n.getNodeType()), n.getNodeName() });
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
/*     */   protected void fireDOMSubtreeModifiedEvent() {
/* 328 */     AbstractDocument doc = getCurrentDocument();
/* 329 */     if (doc.getEventsEnabled()) {
/* 330 */       super.fireDOMSubtreeModifiedEvent();
/* 331 */       if (getOwnerElement() != null) {
/* 332 */         ((AbstractElement)getOwnerElement()).fireDOMSubtreeModifiedEvent();
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
/*     */   public class AttrTypeInfo
/*     */     implements TypeInfo
/*     */   {
/*     */     public String getTypeNamespace() {
/* 347 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getTypeName() {
/* 354 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isDerivedFrom(String ns, String name, int method) {
/* 361 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */