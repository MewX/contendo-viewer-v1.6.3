/*      */ package org.apache.xml.utils;
/*      */ 
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xml.dtm.ref.DTMNodeProxy;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.DOMImplementation;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentType;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Entity;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.Text;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DOMHelper
/*      */ {
/*      */   public static Document createDocument() {
/*      */     
/*   81 */     try { DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
/*      */       
/*   83 */       dfactory.setNamespaceAware(true);
/*   84 */       dfactory.setValidating(true);
/*      */       
/*   86 */       DocumentBuilder docBuilder = dfactory.newDocumentBuilder();
/*   87 */       Document outNode = docBuilder.newDocument();
/*      */       
/*   89 */       return outNode; } catch (ParserConfigurationException pce)
/*      */     
/*      */     { 
/*      */       
/*   93 */       throw new RuntimeException(XMLMessages.createXMLMessage("ER_CREATEDOCUMENT_NOT_SUPPORTED", null)); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldStripSourceNode(Node textNode) throws TransformerException {
/*  118 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUniqueID(Node node) {
/*  148 */     return "N" + Integer.toHexString(node.hashCode()).toUpperCase();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNodeAfter(Node node1, Node node2) {
/*  171 */     if (node1 == node2 || isNodeTheSame(node1, node2)) {
/*  172 */       return true;
/*      */     }
/*      */     
/*  175 */     boolean isNodeAfter = true;
/*      */     
/*  177 */     Node parent1 = getParentOfNode(node1);
/*  178 */     Node parent2 = getParentOfNode(node2);
/*      */ 
/*      */     
/*  181 */     if (parent1 == parent2 || isNodeTheSame(parent1, parent2)) {
/*      */       
/*  183 */       if (null != parent1) {
/*  184 */         isNodeAfter = isNodeAfterSibling(parent1, node1, node2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  210 */       int nParents1 = 2, nParents2 = 2;
/*      */       
/*  212 */       while (parent1 != null) {
/*      */         
/*  214 */         nParents1++;
/*      */         
/*  216 */         parent1 = getParentOfNode(parent1);
/*      */       } 
/*      */       
/*  219 */       while (parent2 != null) {
/*      */         
/*  221 */         nParents2++;
/*      */         
/*  223 */         parent2 = getParentOfNode(parent2);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  228 */       Node startNode1 = node1, startNode2 = node2;
/*      */ 
/*      */ 
/*      */       
/*  232 */       if (nParents1 < nParents2) {
/*      */ 
/*      */         
/*  235 */         int adjust = nParents2 - nParents1;
/*      */         
/*  237 */         for (int i = 0; i < adjust; i++)
/*      */         {
/*  239 */           startNode2 = getParentOfNode(startNode2);
/*      */         }
/*      */       }
/*  242 */       else if (nParents1 > nParents2) {
/*      */ 
/*      */         
/*  245 */         int adjust = nParents1 - nParents2;
/*      */         
/*  247 */         for (int i = 0; i < adjust; i++)
/*      */         {
/*  249 */           startNode1 = getParentOfNode(startNode1);
/*      */         }
/*      */       } 
/*      */       
/*  253 */       Node prevChild1 = null, prevChild2 = null;
/*      */ 
/*      */       
/*  256 */       while (null != startNode1) {
/*      */         
/*  258 */         if (startNode1 == startNode2 || isNodeTheSame(startNode1, startNode2)) {
/*      */           
/*  260 */           if (null == prevChild1) {
/*      */ 
/*      */ 
/*      */             
/*  264 */             isNodeAfter = (nParents1 < nParents2);
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */ 
/*      */           
/*  271 */           isNodeAfter = isNodeAfterSibling(startNode1, prevChild1, prevChild2);
/*      */ 
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */ 
/*      */         
/*  279 */         prevChild1 = startNode1;
/*  280 */         startNode1 = getParentOfNode(startNode1);
/*  281 */         prevChild2 = startNode2;
/*  282 */         startNode2 = getParentOfNode(startNode2);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  294 */     return isNodeAfter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isNodeTheSame(Node node1, Node node2) {
/*  306 */     if (node1 instanceof DTMNodeProxy && node2 instanceof DTMNodeProxy) {
/*  307 */       return ((DTMNodeProxy)node1).equals(node2);
/*      */     }
/*  309 */     return (node1 == node2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isNodeAfterSibling(Node parent, Node child1, Node child2) {
/*  330 */     boolean isNodeAfterSibling = false;
/*  331 */     short child1type = child1.getNodeType();
/*  332 */     short child2type = child2.getNodeType();
/*      */     
/*  334 */     if (2 != child1type && 2 == child2type) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  339 */       isNodeAfterSibling = false;
/*      */     }
/*  341 */     else if (2 == child1type && 2 != child2type) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  346 */       isNodeAfterSibling = true;
/*      */     }
/*  348 */     else if (2 == child1type) {
/*      */       
/*  350 */       NamedNodeMap children = parent.getAttributes();
/*  351 */       int nNodes = children.getLength();
/*  352 */       boolean found1 = false, found2 = false;
/*      */ 
/*      */       
/*  355 */       for (int i = 0; i < nNodes; i++)
/*      */       {
/*  357 */         Node child = children.item(i);
/*      */         
/*  359 */         if (child1 == child || isNodeTheSame(child1, child))
/*      */         {
/*  361 */           if (found2) {
/*      */             
/*  363 */             isNodeAfterSibling = false;
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/*  368 */           found1 = true;
/*      */         }
/*  370 */         else if (child2 == child || isNodeTheSame(child2, child))
/*      */         {
/*  372 */           if (found1) {
/*      */             
/*  374 */             isNodeAfterSibling = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/*  379 */           found2 = true;
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/*  396 */       Node child = parent.getFirstChild();
/*  397 */       boolean found1 = false, found2 = false;
/*      */       
/*  399 */       while (null != child) {
/*      */ 
/*      */ 
/*      */         
/*  403 */         if (child1 == child || isNodeTheSame(child1, child)) {
/*      */           
/*  405 */           if (found2) {
/*      */             
/*  407 */             isNodeAfterSibling = false;
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/*  412 */           found1 = true;
/*      */         }
/*  414 */         else if (child2 == child || isNodeTheSame(child2, child)) {
/*      */           
/*  416 */           if (found1) {
/*      */             
/*  418 */             isNodeAfterSibling = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/*  423 */           found2 = true;
/*      */         } 
/*      */         
/*  426 */         child = child.getNextSibling();
/*      */       } 
/*      */     } 
/*      */     
/*  430 */     return isNodeAfterSibling;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getLevel(Node n) {
/*  448 */     short level = 1;
/*      */     
/*  450 */     while (null != (n = getParentOfNode(n)))
/*      */     {
/*  452 */       level = (short)(level + 1);
/*      */     }
/*      */     
/*  455 */     return level;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceForPrefix(String prefix, Element namespaceContext) {
/*  481 */     Node parent = namespaceContext;
/*  482 */     String namespace = null;
/*      */     
/*  484 */     if (prefix.equals("xml")) {
/*      */       
/*  486 */       namespace = "http://www.w3.org/XML/1998/namespace";
/*      */     }
/*  488 */     else if (prefix.equals("xmlns")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  495 */       namespace = "http://www.w3.org/2000/xmlns/";
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  500 */       String declname = (prefix == "") ? "xmlns" : ("xmlns:" + prefix);
/*      */ 
/*      */       
/*      */       int type;
/*      */ 
/*      */       
/*  506 */       while (null != parent && null == namespace && ((type = parent.getNodeType()) == 1 || type == 5)) {
/*      */ 
/*      */         
/*  509 */         if (type == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  520 */           Attr attr = ((Element)parent).getAttributeNode(declname);
/*  521 */           if (attr != null) {
/*      */             
/*  523 */             namespace = attr.getNodeValue();
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  528 */         parent = getParentOfNode(parent);
/*      */       } 
/*      */     } 
/*      */     
/*  532 */     return namespace;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  538 */   Hashtable m_NSInfos = new Hashtable();
/*      */ 
/*      */ 
/*      */   
/*  542 */   protected static final NSInfo m_NSInfoUnProcWithXMLNS = new NSInfo(false, true);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  547 */   protected static final NSInfo m_NSInfoUnProcWithoutXMLNS = new NSInfo(false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  552 */   protected static final NSInfo m_NSInfoUnProcNoAncestorXMLNS = new NSInfo(false, false, 2);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  557 */   protected static final NSInfo m_NSInfoNullWithXMLNS = new NSInfo(true, true);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  562 */   protected static final NSInfo m_NSInfoNullWithoutXMLNS = new NSInfo(true, false);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  567 */   protected static final NSInfo m_NSInfoNullNoAncestorXMLNS = new NSInfo(true, false, 2);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  572 */   protected Vector m_candidateNoAncestorXMLNS = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceOfNode(Node n) {
/*      */     String str;
/*      */     boolean bool;
/*      */     NSInfo nSInfo;
/*  592 */     short ntype = n.getNodeType();
/*      */     
/*  594 */     if (2 != ntype) {
/*      */       
/*  596 */       Object nsObj = this.m_NSInfos.get(n);
/*      */       
/*  598 */       nSInfo = (nsObj == null) ? null : (NSInfo)nsObj;
/*  599 */       bool = (nSInfo == null) ? false : nSInfo.m_hasProcessedNS;
/*      */     }
/*      */     else {
/*      */       
/*  603 */       bool = false;
/*  604 */       nSInfo = null;
/*      */     } 
/*      */     
/*  607 */     if (bool) {
/*      */       
/*  609 */       str = nSInfo.m_namespace;
/*      */     } else {
/*      */       String str1;
/*      */       
/*  613 */       str = null;
/*      */       
/*  615 */       String nodeName = n.getNodeName();
/*  616 */       int indexOfNSSep = nodeName.indexOf(':');
/*      */ 
/*      */       
/*  619 */       if (2 == ntype) {
/*      */         
/*  621 */         if (indexOfNSSep > 0)
/*      */         {
/*  623 */           str1 = nodeName.substring(0, indexOfNSSep);
/*      */ 
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */           
/*  630 */           return str;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  635 */         str1 = (indexOfNSSep >= 0) ? nodeName.substring(0, indexOfNSSep) : "";
/*      */       } 
/*      */ 
/*      */       
/*  639 */       boolean ancestorsHaveXMLNS = false;
/*  640 */       boolean nHasXMLNS = false;
/*      */       
/*  642 */       if (str1.equals("xml")) {
/*      */         
/*  644 */         str = "http://www.w3.org/XML/1998/namespace";
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  649 */         Node parent = n;
/*      */         
/*  651 */         while (null != parent && null == str) {
/*      */           
/*  653 */           if (null != nSInfo && nSInfo.m_ancestorHasXMLNSAttrs == 2) {
/*      */             break;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  660 */           int parentType = parent.getNodeType();
/*      */           
/*  662 */           if (null == nSInfo || nSInfo.m_hasXMLNSAttrs) {
/*      */             
/*  664 */             boolean elementHasXMLNS = false;
/*      */             
/*  666 */             if (parentType == 1) {
/*      */               
/*  668 */               NamedNodeMap nnm = parent.getAttributes();
/*      */               
/*  670 */               for (int i = 0; i < nnm.getLength(); i++) {
/*      */                 
/*  672 */                 Node attr = nnm.item(i);
/*  673 */                 String aname = attr.getNodeName();
/*      */                 
/*  675 */                 if (aname.charAt(0) == 'x') {
/*      */                   
/*  677 */                   boolean isPrefix = aname.startsWith("xmlns:");
/*      */                   
/*  679 */                   if (aname.equals("xmlns") || isPrefix) {
/*      */                     
/*  681 */                     if (n == parent) {
/*  682 */                       nHasXMLNS = true;
/*      */                     }
/*  684 */                     elementHasXMLNS = true;
/*  685 */                     ancestorsHaveXMLNS = true;
/*      */                     
/*  687 */                     String p = isPrefix ? aname.substring(6) : "";
/*      */                     
/*  689 */                     if (p.equals(str1)) {
/*      */                       
/*  691 */                       str = attr.getNodeValue();
/*      */                       
/*      */                       break;
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */             
/*  700 */             if (2 != parentType && null == nSInfo && n != parent) {
/*      */ 
/*      */               
/*  703 */               nSInfo = elementHasXMLNS ? m_NSInfoUnProcWithXMLNS : m_NSInfoUnProcWithoutXMLNS;
/*      */ 
/*      */               
/*  706 */               this.m_NSInfos.put(parent, nSInfo);
/*      */             } 
/*      */           } 
/*      */           
/*  710 */           if (2 == parentType) {
/*      */             
/*  712 */             parent = getParentOfNode(parent);
/*      */           }
/*      */           else {
/*      */             
/*  716 */             this.m_candidateNoAncestorXMLNS.addElement(parent);
/*  717 */             this.m_candidateNoAncestorXMLNS.addElement(nSInfo);
/*      */             
/*  719 */             parent = parent.getParentNode();
/*      */           } 
/*      */           
/*  722 */           if (null != parent) {
/*      */             
/*  724 */             Object nsObj = this.m_NSInfos.get(parent);
/*      */             
/*  726 */             nSInfo = (nsObj == null) ? null : (NSInfo)nsObj;
/*      */           } 
/*      */         } 
/*      */         
/*  730 */         int nCandidates = this.m_candidateNoAncestorXMLNS.size();
/*      */         
/*  732 */         if (nCandidates > 0) {
/*      */           
/*  734 */           if (false == ancestorsHaveXMLNS && null == parent)
/*      */           {
/*  736 */             for (int i = 0; i < nCandidates; i += 2) {
/*      */               
/*  738 */               Object candidateInfo = this.m_candidateNoAncestorXMLNS.elementAt(i + 1);
/*      */ 
/*      */               
/*  741 */               if (candidateInfo == m_NSInfoUnProcWithoutXMLNS) {
/*      */                 
/*  743 */                 this.m_NSInfos.put(this.m_candidateNoAncestorXMLNS.elementAt(i), m_NSInfoUnProcNoAncestorXMLNS);
/*      */               
/*      */               }
/*  746 */               else if (candidateInfo == m_NSInfoNullWithoutXMLNS) {
/*      */                 
/*  748 */                 this.m_NSInfos.put(this.m_candidateNoAncestorXMLNS.elementAt(i), m_NSInfoNullNoAncestorXMLNS);
/*      */               } 
/*      */             } 
/*      */           }
/*      */ 
/*      */           
/*  754 */           this.m_candidateNoAncestorXMLNS.removeAllElements();
/*      */         } 
/*      */       } 
/*      */       
/*  758 */       if (2 != ntype)
/*      */       {
/*  760 */         if (null == str) {
/*      */           
/*  762 */           if (ancestorsHaveXMLNS) {
/*      */             
/*  764 */             if (nHasXMLNS) {
/*  765 */               this.m_NSInfos.put(n, m_NSInfoNullWithXMLNS);
/*      */             } else {
/*  767 */               this.m_NSInfos.put(n, m_NSInfoNullWithoutXMLNS);
/*      */             } 
/*      */           } else {
/*      */             
/*  771 */             this.m_NSInfos.put(n, m_NSInfoNullNoAncestorXMLNS);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  776 */           this.m_NSInfos.put(n, new NSInfo(str, nHasXMLNS));
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  781 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalNameOfNode(Node n) {
/*  796 */     String qname = n.getNodeName();
/*  797 */     int index = qname.indexOf(':');
/*      */     
/*  799 */     return (index < 0) ? qname : qname.substring(index + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getExpandedElementName(Element elem) {
/*  817 */     String namespace = getNamespaceOfNode(elem);
/*      */     
/*  819 */     return (null != namespace) ? (namespace + ":" + getLocalNameOfNode(elem)) : getLocalNameOfNode(elem);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getExpandedAttributeName(Attr attr) {
/*  839 */     String namespace = getNamespaceOfNode(attr);
/*      */     
/*  841 */     return (null != namespace) ? (namespace + ":" + getLocalNameOfNode(attr)) : getLocalNameOfNode(attr);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isIgnorableWhitespace(Text node) {
/*  868 */     boolean isIgnorable = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  876 */     return isIgnorable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getRoot(Node node) {
/*  890 */     Node root = null;
/*      */     
/*  892 */     while (node != null) {
/*      */       
/*  894 */       root = node;
/*  895 */       node = getParentOfNode(node);
/*      */     } 
/*      */     
/*  898 */     return root;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getRootNode(Node n) {
/*  921 */     int nt = n.getNodeType();
/*  922 */     return (9 == nt || 11 == nt) ? n : n.getOwnerDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNamespaceNode(Node n) {
/*  939 */     if (2 == n.getNodeType()) {
/*      */       
/*  941 */       String attrName = n.getNodeName();
/*      */       
/*  943 */       return (attrName.startsWith("xmlns:") || attrName.equals("xmlns"));
/*      */     } 
/*      */     
/*  946 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Node getParentOfNode(Node node) throws RuntimeException {
/*      */     Node node1;
/*  978 */     short nodeType = node.getNodeType();
/*      */     
/*  980 */     if (2 == nodeType) {
/*      */       
/*  982 */       Document doc = node.getOwnerDocument();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1000 */       DOMImplementation impl = doc.getImplementation();
/* 1001 */       if (impl != null && impl.hasFeature("Core", "2.0")) {
/*      */         
/* 1003 */         Node parent = ((Attr)node).getOwnerElement();
/* 1004 */         return parent;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1009 */       Element rootElem = doc.getDocumentElement();
/*      */       
/* 1011 */       if (null == rootElem)
/*      */       {
/* 1013 */         throw new RuntimeException(XMLMessages.createXMLMessage("ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT", null));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1019 */       node1 = locateAttrParent(rootElem, node);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1024 */       node1 = node.getParentNode();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1032 */     return node1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element getElementByID(String id, Document doc) {
/* 1055 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnparsedEntityURI(String name, Document doc) {
/* 1096 */     String url = "";
/* 1097 */     DocumentType doctype = doc.getDoctype();
/*      */     
/* 1099 */     if (null != doctype) {
/*      */       
/* 1101 */       NamedNodeMap entities = doctype.getEntities();
/* 1102 */       if (null == entities)
/* 1103 */         return url; 
/* 1104 */       Entity entity = (Entity)entities.getNamedItem(name);
/* 1105 */       if (null == entity) {
/* 1106 */         return url;
/*      */       }
/* 1108 */       String notationName = entity.getNotationName();
/*      */       
/* 1110 */       if (null != notationName) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1121 */         url = entity.getSystemId();
/*      */         
/* 1123 */         if (null == url)
/*      */         {
/* 1125 */           url = entity.getPublicId();
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1135 */     return url;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node locateAttrParent(Element elem, Node attr) {
/* 1162 */     Node parent = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1169 */     Attr check = elem.getAttributeNode(attr.getNodeName());
/* 1170 */     if (check == attr) {
/* 1171 */       parent = elem;
/*      */     }
/* 1173 */     if (null == parent)
/*      */     {
/* 1175 */       for (Node node = elem.getFirstChild(); null != node; 
/* 1176 */         node = node.getNextSibling()) {
/*      */         
/* 1178 */         if (1 == node.getNodeType()) {
/*      */           
/* 1180 */           parent = locateAttrParent((Element)node, attr);
/*      */           
/* 1182 */           if (null != parent) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/* 1188 */     return parent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1195 */   protected Document m_DOMFactory = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDOMFactory(Document domFactory) {
/* 1207 */     this.m_DOMFactory = domFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Document getDOMFactory() {
/* 1219 */     if (null == this.m_DOMFactory)
/*      */     {
/* 1221 */       this.m_DOMFactory = createDocument();
/*      */     }
/*      */     
/* 1224 */     return this.m_DOMFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getNodeData(Node node) {
/*      */     String s;
/* 1241 */     FastStringBuffer buf = StringBufferPool.get();
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1246 */       getNodeData(node, buf);
/*      */       
/* 1248 */       s = (buf.length() > 0) ? buf.toString() : "";
/*      */     }
/*      */     finally {
/*      */       
/* 1252 */       StringBufferPool.free(buf);
/*      */     } 
/*      */     
/* 1255 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void getNodeData(Node node, FastStringBuffer buf) {
/*      */     Node child;
/* 1278 */     switch (node.getNodeType()) {
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 9:
/*      */       case 11:
/* 1284 */         for (child = node.getFirstChild(); null != child; 
/* 1285 */           child = child.getNextSibling())
/*      */         {
/* 1287 */           getNodeData(child, buf);
/*      */         }
/*      */         break;
/*      */       
/*      */       case 3:
/*      */       case 4:
/* 1293 */         buf.append(node.getNodeValue());
/*      */         break;
/*      */       case 2:
/* 1296 */         buf.append(node.getNodeValue());
/*      */         break;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/DOMHelper.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */