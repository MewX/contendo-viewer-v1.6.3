/*     */ package org.apache.batik.dom;
/*     */ 
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.xml.XMLUtilities;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.Text;
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
/*     */ public abstract class AbstractText
/*     */   extends AbstractCharacterData
/*     */   implements Text
/*     */ {
/*     */   public Text splitText(int offset) throws DOMException {
/*  45 */     if (isReadonly()) {
/*  46 */       throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(getNodeType()), getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  51 */     String v = getNodeValue();
/*  52 */     if (offset < 0 || offset >= v.length()) {
/*  53 */       throw createDOMException((short)1, "offset", new Object[] { Integer.valueOf(offset) });
/*     */     }
/*     */ 
/*     */     
/*  57 */     Node n = getParentNode();
/*  58 */     if (n == null) {
/*  59 */       throw createDOMException((short)1, "need.parent", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*  63 */     String t1 = v.substring(offset);
/*  64 */     Text t = createTextNode(t1);
/*  65 */     Node ns = getNextSibling();
/*  66 */     if (ns != null) {
/*  67 */       n.insertBefore(t, ns);
/*     */     } else {
/*  69 */       n.appendChild(t);
/*     */     } 
/*  71 */     setNodeValue(v.substring(0, offset));
/*  72 */     return t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node getPreviousLogicallyAdjacentTextNode(Node n) {
/*  80 */     Node p = n.getPreviousSibling();
/*  81 */     Node parent = n.getParentNode();
/*     */ 
/*     */     
/*  84 */     while (p == null && parent != null && parent.getNodeType() == 5) {
/*  85 */       p = parent;
/*  86 */       parent = p.getParentNode();
/*  87 */       p = p.getPreviousSibling();
/*     */     } 
/*  89 */     while (p != null && p.getNodeType() == 5) {
/*  90 */       p = p.getLastChild();
/*     */     }
/*  92 */     if (p == null) {
/*  93 */       return null;
/*     */     }
/*  95 */     int nt = p.getNodeType();
/*  96 */     if (nt == 3 || nt == 4) {
/*  97 */       return p;
/*     */     }
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node getNextLogicallyAdjacentTextNode(Node n) {
/* 107 */     Node p = n.getNextSibling();
/* 108 */     Node parent = n.getParentNode();
/*     */ 
/*     */     
/* 111 */     while (p == null && parent != null && parent.getNodeType() == 5) {
/* 112 */       p = parent;
/* 113 */       parent = p.getParentNode();
/* 114 */       p = p.getNextSibling();
/*     */     } 
/* 116 */     while (p != null && p.getNodeType() == 5) {
/* 117 */       p = p.getFirstChild();
/*     */     }
/* 119 */     if (p == null) {
/* 120 */       return null;
/*     */     }
/* 122 */     int nt = p.getNodeType();
/* 123 */     if (nt == 3 || nt == 4) {
/* 124 */       return p;
/*     */     }
/* 126 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWholeText() {
/* 133 */     StringBuffer sb = new StringBuffer();
/* 134 */     Node n = this;
/* 135 */     for (; n != null; 
/* 136 */       n = getPreviousLogicallyAdjacentTextNode(n)) {
/* 137 */       sb.insert(0, n.getNodeValue());
/*     */     }
/* 139 */     n = getNextLogicallyAdjacentTextNode(this);
/* 140 */     for (; n != null; 
/* 141 */       n = getNextLogicallyAdjacentTextNode(n)) {
/* 142 */       sb.append(n.getNodeValue());
/*     */     }
/* 144 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isElementContentWhitespace() {
/* 152 */     int len = this.nodeValue.length();
/* 153 */     for (int i = 0; i < len; i++) {
/* 154 */       if (!XMLUtilities.isXMLSpace(this.nodeValue.charAt(i))) {
/* 155 */         return false;
/*     */       }
/*     */     } 
/* 158 */     Node p = getParentNode();
/* 159 */     if (p.getNodeType() == 1) {
/* 160 */       String sp = XMLSupport.getXMLSpace((Element)p);
/* 161 */       return !sp.equals("preserve");
/*     */     } 
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Text replaceWholeText(String s) throws DOMException {
/* 170 */     Node n = getPreviousLogicallyAdjacentTextNode(this);
/* 171 */     for (; n != null; 
/* 172 */       n = getPreviousLogicallyAdjacentTextNode(n)) {
/* 173 */       AbstractNode an = (AbstractNode)n;
/* 174 */       if (an.isReadonly()) {
/* 175 */         throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(n.getNodeType()), n.getNodeName() });
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     n = getNextLogicallyAdjacentTextNode(this);
/* 183 */     for (; n != null; 
/* 184 */       n = getNextLogicallyAdjacentTextNode(n)) {
/* 185 */       AbstractNode an = (AbstractNode)n;
/* 186 */       if (an.isReadonly()) {
/* 187 */         throw createDOMException((short)7, "readonly.node", new Object[] { Integer.valueOf(n.getNodeType()), n.getNodeName() });
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     Node parent = getParentNode();
/* 195 */     Node node1 = getPreviousLogicallyAdjacentTextNode(this);
/* 196 */     for (; node1 != null; 
/* 197 */       node1 = getPreviousLogicallyAdjacentTextNode(node1)) {
/* 198 */       parent.removeChild(node1);
/*     */     }
/* 200 */     node1 = getNextLogicallyAdjacentTextNode(this);
/* 201 */     for (; node1 != null; 
/* 202 */       node1 = getNextLogicallyAdjacentTextNode(node1)) {
/* 203 */       parent.removeChild(node1);
/*     */     }
/* 205 */     if (isReadonly()) {
/* 206 */       Text t = createTextNode(s);
/* 207 */       parent.replaceChild(t, this);
/* 208 */       return t;
/*     */     } 
/* 210 */     setNodeValue(s);
/* 211 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTextContent() {
/* 218 */     if (isElementContentWhitespace()) {
/* 219 */       return "";
/*     */     }
/* 221 */     return getNodeValue();
/*     */   }
/*     */   
/*     */   protected abstract Text createTextNode(String paramString);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/AbstractText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */