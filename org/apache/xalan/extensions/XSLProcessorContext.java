/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.serialize.SerializerUtils;
/*     */ import org.apache.xalan.templates.Stylesheet;
/*     */ import org.apache.xalan.transformer.ClonerToResultTree;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.axes.DescendantIterator;
/*     */ import org.apache.xpath.axes.OneStepIterator;
/*     */ import org.apache.xpath.objects.XBoolean;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XNumber;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.objects.XRTreeFrag;
/*     */ import org.apache.xpath.objects.XString;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public class XSLProcessorContext
/*     */ {
/*     */   private TransformerImpl transformer;
/*     */   private Stylesheet stylesheetTree;
/*     */   private DTM sourceTree;
/*     */   private int sourceNode;
/*     */   private QName mode;
/*     */   
/*     */   public XSLProcessorContext(TransformerImpl transformer, Stylesheet stylesheetTree) {
/*  69 */     this.transformer = transformer;
/*  70 */     this.stylesheetTree = stylesheetTree;
/*     */     
/*  72 */     XPathContext xctxt = transformer.getXPathContext();
/*  73 */     this.mode = transformer.getMode();
/*  74 */     this.sourceNode = xctxt.getCurrentNode();
/*  75 */     this.sourceTree = xctxt.getDTM(this.sourceNode);
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
/*     */   public TransformerImpl getTransformer() {
/*  88 */     return this.transformer;
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
/*     */   public Stylesheet getStylesheet() {
/* 101 */     return this.stylesheetTree;
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
/*     */   public Node getSourceTree() {
/* 114 */     return this.sourceTree.getNode(this.sourceTree.getDocumentRoot(this.sourceNode));
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
/*     */   public Node getContextNode() {
/* 127 */     return this.sourceTree.getNode(this.sourceNode);
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
/*     */   public QName getMode() {
/* 140 */     return this.mode;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void outputToResultTree(Stylesheet stylesheetTree, Object obj) throws TransformerException, MalformedURLException, FileNotFoundException, IOException {
/*     */     try {
/*     */       XString xString;
/*     */       String s;
/*     */       DTMIterator nl;
/*     */       int pos;
/* 164 */       SerializationHandler rtreeHandler = this.transformer.getResultTreeHandler();
/* 165 */       XPathContext xctxt = this.transformer.getXPathContext();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 172 */       if (obj instanceof XObject) {
/*     */         
/* 174 */         XObject value = (XObject)obj;
/*     */       }
/* 176 */       else if (obj instanceof String) {
/*     */         
/* 178 */         xString = new XString((String)obj);
/*     */       }
/* 180 */       else if (obj instanceof Boolean) {
/*     */         
/* 182 */         XBoolean xBoolean = new XBoolean(((Boolean)obj).booleanValue());
/*     */       }
/* 184 */       else if (obj instanceof Double) {
/*     */         
/* 186 */         XNumber xNumber = new XNumber(((Double)obj).doubleValue());
/*     */       }
/* 188 */       else if (obj instanceof DocumentFragment) {
/*     */         
/* 190 */         int handle = xctxt.getDTMHandleFromNode((DocumentFragment)obj);
/*     */         
/* 192 */         XRTreeFrag xRTreeFrag = new XRTreeFrag(handle, xctxt);
/*     */       }
/* 194 */       else if (obj instanceof DTM) {
/*     */         
/* 196 */         DTM dtm = (DTM)obj;
/* 197 */         DescendantIterator descendantIterator = new DescendantIterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 203 */         descendantIterator.setRoot(dtm.getDocument(), xctxt);
/* 204 */         XNodeSet xNodeSet = new XNodeSet((DTMIterator)descendantIterator);
/*     */       }
/* 206 */       else if (obj instanceof DTMAxisIterator) {
/*     */         
/* 208 */         DTMAxisIterator iter = (DTMAxisIterator)obj;
/* 209 */         OneStepIterator oneStepIterator = new OneStepIterator(iter, -1);
/* 210 */         XNodeSet xNodeSet = new XNodeSet((DTMIterator)oneStepIterator);
/*     */       }
/* 212 */       else if (obj instanceof DTMIterator) {
/*     */         
/* 214 */         XNodeSet xNodeSet = new XNodeSet((DTMIterator)obj);
/*     */       }
/* 216 */       else if (obj instanceof NodeIterator) {
/*     */         
/* 218 */         XNodeSet xNodeSet = new XNodeSet((DTMIterator)new NodeSetDTM((NodeIterator)obj, xctxt));
/*     */       }
/* 220 */       else if (obj instanceof Node) {
/*     */         
/* 222 */         XNodeSet xNodeSet = new XNodeSet(xctxt.getDTMHandleFromNode((Node)obj), xctxt.getDTMManager());
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 228 */         xString = new XString(obj.toString());
/*     */       } 
/*     */       
/* 231 */       int type = xString.getType();
/*     */ 
/*     */       
/* 234 */       switch (type) {
/*     */         
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/* 239 */           s = xString.str();
/*     */           
/* 241 */           rtreeHandler.characters(s.toCharArray(), 0, s.length());
/*     */           break;
/*     */         
/*     */         case 4:
/* 245 */           nl = xString.iter();
/*     */ 
/*     */ 
/*     */           
/* 249 */           while (-1 != (pos = nl.nextNode())) {
/*     */             
/* 251 */             DTM dtm = nl.getDTM(pos);
/* 252 */             int top = pos;
/*     */             
/* 254 */             while (-1 != pos) {
/*     */               
/* 256 */               rtreeHandler.flushPending();
/* 257 */               ClonerToResultTree.cloneToResultTree(pos, dtm.getNodeType(pos), dtm, rtreeHandler, true);
/*     */ 
/*     */               
/* 260 */               int nextNode = dtm.getFirstChild(pos);
/*     */               
/* 262 */               while (-1 == nextNode) {
/*     */                 
/* 264 */                 if (1 == dtm.getNodeType(pos))
/*     */                 {
/* 266 */                   rtreeHandler.endElement("", "", dtm.getNodeName(pos));
/*     */                 }
/*     */                 
/* 269 */                 if (top == pos) {
/*     */                   break;
/*     */                 }
/* 272 */                 nextNode = dtm.getNextSibling(pos);
/*     */                 
/* 274 */                 if (-1 == nextNode) {
/*     */                   
/* 276 */                   pos = dtm.getParent(pos);
/*     */                   
/* 278 */                   if (top == pos) {
/*     */                     
/* 280 */                     if (1 == dtm.getNodeType(pos))
/*     */                     {
/* 282 */                       rtreeHandler.endElement("", "", dtm.getNodeName(pos));
/*     */                     }
/*     */                     
/* 285 */                     nextNode = -1;
/*     */                     
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               
/* 292 */               pos = nextNode;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 5:
/* 297 */           SerializerUtils.outputResultTreeFragment(rtreeHandler, (XObject)xString, this.transformer.getXPathContext());
/*     */           break;
/*     */       } 
/*     */     
/* 301 */     } catch (SAXException se) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 306 */       throw new TransformerException(se);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/XSLProcessorContext.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */