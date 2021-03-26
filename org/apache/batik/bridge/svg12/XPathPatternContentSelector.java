/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.batik.anim.dom.XBLOMContentElement;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class XPathPatternContentSelector
/*     */   extends AbstractContentSelector
/*     */ {
/*  46 */   protected NSPrefixResolver prefixResolver = new NSPrefixResolver();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XPath xpath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected XPathContext context;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SelectedNodes selectedContent;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String expression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPathPatternContentSelector(ContentManager cm, XBLOMContentElement content, Element bound, String selector) {
/*  75 */     super(cm, content, bound);
/*  76 */     this.expression = selector;
/*  77 */     parse();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parse() {
/*  84 */     this.context = new XPathContext();
/*     */     try {
/*  86 */       this.xpath = new XPath(this.expression, null, this.prefixResolver, 1);
/*  87 */     } catch (TransformerException te) {
/*  88 */       AbstractDocument doc = (AbstractDocument)this.contentElement.getOwnerDocument();
/*     */       
/*  90 */       throw doc.createXPathException((short)51, "xpath.invalid.expression", new Object[] { this.expression, te.getMessage() });
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
/*     */   public NodeList getSelectedContent() {
/* 102 */     if (this.selectedContent == null) {
/* 103 */       this.selectedContent = new SelectedNodes();
/*     */     }
/* 105 */     return this.selectedContent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean update() {
/* 116 */     if (this.selectedContent == null) {
/* 117 */       this.selectedContent = new SelectedNodes();
/* 118 */       return true;
/*     */     } 
/* 120 */     parse();
/* 121 */     return this.selectedContent.update();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class SelectedNodes
/*     */     implements NodeList
/*     */   {
/* 133 */     protected ArrayList nodes = new ArrayList(10);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SelectedNodes() {
/* 139 */       update();
/*     */     }
/*     */     
/*     */     protected boolean update() {
/* 143 */       ArrayList<E> oldNodes = (ArrayList)this.nodes.clone();
/* 144 */       this.nodes.clear();
/* 145 */       Node n = XPathPatternContentSelector.this.boundElement.getFirstChild();
/* 146 */       for (; n != null; 
/* 147 */         n = n.getNextSibling()) {
/* 148 */         update(n);
/*     */       }
/* 150 */       int nodesSize = this.nodes.size();
/* 151 */       if (oldNodes.size() != nodesSize) {
/* 152 */         return true;
/*     */       }
/* 154 */       for (int i = 0; i < nodesSize; i++) {
/* 155 */         if (oldNodes.get(i) != this.nodes.get(i)) {
/* 156 */           return true;
/*     */         }
/*     */       } 
/* 159 */       return false;
/*     */     }
/*     */     
/*     */     protected boolean descendantSelected(Node n) {
/* 163 */       n = n.getFirstChild();
/* 164 */       while (n != null) {
/* 165 */         if (XPathPatternContentSelector.this.isSelected(n) || descendantSelected(n)) {
/* 166 */           return true;
/*     */         }
/* 168 */         n = n.getNextSibling();
/*     */       } 
/* 170 */       return false;
/*     */     }
/*     */     
/*     */     protected void update(Node n) {
/* 174 */       if (!XPathPatternContentSelector.this.isSelected(n)) {
/*     */         try {
/* 176 */           double matchScore = XPathPatternContentSelector.this.xpath.execute(XPathPatternContentSelector.this.context, n, XPathPatternContentSelector.this.prefixResolver).num();
/*     */           
/* 178 */           if (matchScore != Double.NEGATIVE_INFINITY) {
/* 179 */             if (!descendantSelected(n)) {
/* 180 */               this.nodes.add(n);
/*     */             }
/*     */           } else {
/* 183 */             n = n.getFirstChild();
/* 184 */             while (n != null) {
/* 185 */               update(n);
/* 186 */               n = n.getNextSibling();
/*     */             } 
/*     */           } 
/* 189 */         } catch (TransformerException te) {
/* 190 */           AbstractDocument doc = (AbstractDocument)XPathPatternContentSelector.this.contentElement.getOwnerDocument();
/*     */           
/* 192 */           throw doc.createXPathException((short)51, "xpath.error", new Object[] { this.this$0.expression, te.getMessage() });
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node item(int index) {
/* 204 */       if (index < 0 || index >= this.nodes.size()) {
/* 205 */         return null;
/*     */       }
/* 207 */       return this.nodes.get(index);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getLength() {
/* 214 */       return this.nodes.size();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class NSPrefixResolver
/*     */     implements PrefixResolver
/*     */   {
/*     */     public String getBaseIdentifier() {
/* 227 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getNamespaceForPrefix(String prefix) {
/* 234 */       return XPathPatternContentSelector.this.contentElement.lookupNamespaceURI(prefix);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getNamespaceForPrefix(String prefix, Node context) {
/* 242 */       return XPathPatternContentSelector.this.contentElement.lookupNamespaceURI(prefix);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean handlesNullPrefixes() {
/* 249 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/XPathPatternContentSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */