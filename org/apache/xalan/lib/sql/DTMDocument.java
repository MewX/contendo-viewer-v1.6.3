/*      */ package org.apache.xalan.lib.sql;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import javax.xml.transform.SourceLocator;
/*      */ import org.apache.xml.dtm.DTMAxisIterator;
/*      */ import org.apache.xml.dtm.DTMAxisTraverser;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBase;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBaseIterators;
/*      */ import org.apache.xml.utils.FastStringBuffer;
/*      */ import org.apache.xml.utils.StringBufferPool;
/*      */ import org.apache.xml.utils.SuballocatedIntVector;
/*      */ import org.apache.xml.utils.XMLString;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.DTDHandler;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.ext.DeclHandler;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DTMDocument
/*      */   extends DTMDefaultBaseIterators
/*      */ {
/*      */   private boolean DEBUG = false;
/*      */   protected static final String S_NAMESPACE = "http://xml.apache.org/xalan/SQLExtension";
/*      */   protected static final String S_ATTRIB_NOT_SUPPORTED = "Not Supported";
/*      */   protected static final String S_ISTRUE = "true";
/*      */   protected static final String S_ISFALSE = "false";
/*      */   protected static final String S_DOCUMENT = "#root";
/*      */   protected static final String S_TEXT_NODE = "#text";
/*      */   protected static final String S_ELEMENT_NODE = "#element";
/*   96 */   protected int m_Document_TypeID = 0;
/*      */ 
/*      */   
/*   99 */   protected int m_TextNode_TypeID = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  105 */   protected ObjectArray m_ObjectArray = new ObjectArray();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SuballocatedIntVector m_attribute;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int m_DocumentIdx;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMDocument(DTMManager mgr, int ident) {
/*  130 */     super(mgr, null, ident, null, mgr.getXMLStringFactory(), true);
/*      */ 
/*      */     
/*  133 */     this.m_attribute = new SuballocatedIntVector(512);
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
/*      */   private int allocateNodeObject(Object o) {
/*  146 */     ((DTMDefaultBase)this).m_size++;
/*  147 */     return this.m_ObjectArray.append(o);
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
/*      */   protected int addElementWithData(Object o, int level, int extendedType, int parent, int prevsib) {
/*  160 */     int elementIdx = addElement(level, extendedType, parent, prevsib);
/*      */     
/*  162 */     int data = allocateNodeObject(o);
/*  163 */     ((DTMDefaultBase)this).m_firstch.setElementAt(data, elementIdx);
/*      */     
/*  165 */     ((DTMDefaultBase)this).m_exptype.setElementAt(this.m_TextNode_TypeID, data);
/*      */     
/*  167 */     ((DTMDefaultBase)this).m_parent.setElementAt(elementIdx, data);
/*      */     
/*  169 */     ((DTMDefaultBase)this).m_prevsib.setElementAt(-1, data);
/*  170 */     ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, data);
/*  171 */     this.m_attribute.setElementAt(-1, data);
/*  172 */     ((DTMDefaultBase)this).m_firstch.setElementAt(-1, data);
/*      */     
/*  174 */     return elementIdx;
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
/*      */   protected int addElement(int level, int extendedType, int parent, int prevsib) {
/*  186 */     int node = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  191 */     try { node = allocateNodeObject("#element");
/*      */       
/*  193 */       ((DTMDefaultBase)this).m_exptype.setElementAt(extendedType, node);
/*  194 */       ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, node);
/*  195 */       ((DTMDefaultBase)this).m_prevsib.setElementAt(prevsib, node);
/*      */       
/*  197 */       ((DTMDefaultBase)this).m_parent.setElementAt(parent, node);
/*  198 */       ((DTMDefaultBase)this).m_firstch.setElementAt(-1, node);
/*      */       
/*  200 */       this.m_attribute.setElementAt(-1, node);
/*      */       
/*  202 */       if (prevsib != -1) {
/*      */ 
/*      */ 
/*      */         
/*  206 */         if (((DTMDefaultBase)this).m_nextsib.elementAt(prevsib) != -1) {
/*  207 */           ((DTMDefaultBase)this).m_nextsib.setElementAt(((DTMDefaultBase)this).m_nextsib.elementAt(prevsib), node);
/*      */         }
/*      */         
/*  210 */         ((DTMDefaultBase)this).m_nextsib.setElementAt(node, prevsib);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  218 */       if (parent != -1 && ((DTMDefaultBase)this).m_prevsib.elementAt(node) == -1)
/*      */       {
/*  220 */         ((DTMDefaultBase)this).m_firstch.setElementAt(node, parent); }  } catch (Exception e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  225 */       error("Error in addElement: " + e.getMessage()); }
/*      */ 
/*      */     
/*  228 */     return node;
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
/*      */   protected int addAttributeToNode(Object o, int extendedType, int pnode) {
/*  244 */     int attrib = -1;
/*  245 */     int prevsib = -1;
/*  246 */     int lastattrib = -1;
/*  247 */     int value = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  252 */     try { attrib = allocateNodeObject(o);
/*      */       
/*  254 */       this.m_attribute.setElementAt(-1, attrib);
/*  255 */       ((DTMDefaultBase)this).m_exptype.setElementAt(extendedType, attrib);
/*      */ 
/*      */ 
/*      */       
/*  259 */       ((DTMDefaultBase)this).m_nextsib.setElementAt(-1, attrib);
/*  260 */       ((DTMDefaultBase)this).m_prevsib.setElementAt(-1, attrib);
/*      */ 
/*      */ 
/*      */       
/*  264 */       ((DTMDefaultBase)this).m_parent.setElementAt(pnode, attrib);
/*  265 */       ((DTMDefaultBase)this).m_firstch.setElementAt(-1, attrib);
/*      */       
/*  267 */       if (this.m_attribute.elementAt(pnode) != -1) {
/*      */ 
/*      */ 
/*      */         
/*  271 */         lastattrib = this.m_attribute.elementAt(pnode);
/*  272 */         ((DTMDefaultBase)this).m_nextsib.setElementAt(lastattrib, attrib);
/*  273 */         ((DTMDefaultBase)this).m_prevsib.setElementAt(attrib, lastattrib);
/*      */       } 
/*      */ 
/*      */       
/*  277 */       this.m_attribute.setElementAt(attrib, pnode); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  281 */       error("Error in addAttributeToNode: " + e.getMessage()); }
/*      */ 
/*      */     
/*  284 */     return attrib;
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
/*      */   protected void cloneAttributeFromNode(int toNode, int fromNode) {
/*      */     
/*  300 */     try { if (this.m_attribute.elementAt(toNode) != -1)
/*      */       {
/*  302 */         error("Cloneing Attributes, where from Node already had addtibures assigned");
/*      */       }
/*      */       
/*  305 */       this.m_attribute.setElementAt(this.m_attribute.elementAt(fromNode), toNode); } catch (Exception e)
/*      */     
/*      */     { 
/*      */       
/*  309 */       error("Cloning attributes"); }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstAttribute(int parm1) {
/*  320 */     if (this.DEBUG) System.out.println("getFirstAttribute(" + parm1 + ")"); 
/*  321 */     int nodeIdx = makeNodeIdentity(parm1);
/*  322 */     if (nodeIdx != -1) {
/*      */       
/*  324 */       int attribIdx = this.m_attribute.elementAt(nodeIdx);
/*  325 */       return makeNodeHandle(attribIdx);
/*      */     } 
/*  327 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeValue(int parm1) {
/*  336 */     if (this.DEBUG) System.out.println("getNodeValue(" + parm1 + ")");
/*      */ 
/*      */     
/*  339 */     try { Object o = this.m_ObjectArray.getAt(makeNodeIdentity(parm1));
/*  340 */       if (o != null && o != "#element")
/*      */       {
/*  342 */         return o.toString();
/*      */       }
/*      */ 
/*      */       
/*  346 */       return ""; } catch (Exception e)
/*      */     
/*      */     { 
/*      */ 
/*      */       
/*  351 */       error("Getting String Value");
/*  352 */       return null; }
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
/*      */   public XMLString getStringValue(int nodeHandle) {
/*  368 */     int nodeIdx = makeNodeIdentity(nodeHandle);
/*  369 */     if (this.DEBUG) System.out.println("getStringValue(" + nodeIdx + ")");
/*      */     
/*  371 */     Object o = this.m_ObjectArray.getAt(nodeIdx);
/*  372 */     if (o == "#element") {
/*      */       String s;
/*  374 */       FastStringBuffer buf = StringBufferPool.get();
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  379 */         getNodeData(nodeIdx, buf);
/*      */         
/*  381 */         s = (buf.length() > 0) ? buf.toString() : "";
/*      */       }
/*      */       finally {
/*      */         
/*  385 */         StringBufferPool.free(buf);
/*      */       } 
/*      */       
/*  388 */       return ((DTMDefaultBase)this).m_xstrf.newstr(s);
/*      */     } 
/*  390 */     if (o != null)
/*      */     {
/*  392 */       return ((DTMDefaultBase)this).m_xstrf.newstr(o.toString());
/*      */     }
/*      */     
/*  395 */     return ((DTMDefaultBase)this).m_xstrf.emptystr();
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
/*      */   protected void getNodeData(int nodeIdx, FastStringBuffer buf) {
/*  422 */     for (int child = _firstch(nodeIdx); child != -1; child = _nextsib(child)) {
/*      */       
/*  424 */       Object o = this.m_ObjectArray.getAt(child);
/*  425 */       if (o == "#element") {
/*  426 */         getNodeData(child, buf);
/*  427 */       } else if (o != null) {
/*  428 */         buf.append(o.toString());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextAttribute(int parm1) {
/*  441 */     int nodeIdx = makeNodeIdentity(parm1);
/*  442 */     if (this.DEBUG) System.out.println("getNextAttribute(" + nodeIdx + ")"); 
/*  443 */     if (nodeIdx != -1) return makeNodeHandle(((DTMDefaultBase)this).m_nextsib.elementAt(nodeIdx)); 
/*  444 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getNumberOfNodes() {
/*  453 */     if (this.DEBUG) System.out.println("getNumberOfNodes()"); 
/*  454 */     return ((DTMDefaultBase)this).m_size;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean nextNode() {
/*  462 */     if (this.DEBUG) System.out.println("nextNode()"); 
/*  463 */     return false;
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
/*      */   protected void createExpandedNameTable() {
/*  475 */     this.m_Document_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "#root", 9);
/*      */ 
/*      */     
/*  478 */     this.m_TextNode_TypeID = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "#text", 3);
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
/*      */   public void dumpDTM() {
/*      */     try {
/*  491 */       File f = new File("DTMDump.txt");
/*  492 */       System.err.println("Dumping... " + f.getAbsolutePath());
/*  493 */       PrintStream ps = new PrintStream(new FileOutputStream(f)); do {
/*      */       
/*  495 */       } while (nextNode());
/*      */       
/*  497 */       int nRecords = ((DTMDefaultBase)this).m_size;
/*      */       
/*  499 */       ps.println("Total nodes: " + nRecords);
/*      */       
/*  501 */       for (int i = 0; i < nRecords; i++) {
/*      */         String typestring;
/*  503 */         ps.println("=========== " + i + " ===========");
/*  504 */         ps.println("NodeName: " + getNodeName(makeNodeHandle(i)));
/*  505 */         ps.println("NodeNameX: " + getNodeNameX(makeNodeHandle(i)));
/*  506 */         ps.println("LocalName: " + getLocalName(makeNodeHandle(i)));
/*  507 */         ps.println("NamespaceURI: " + getNamespaceURI(makeNodeHandle(i)));
/*  508 */         ps.println("Prefix: " + getPrefix(makeNodeHandle(i)));
/*      */         
/*  510 */         int exTypeID = getExpandedTypeID(makeNodeHandle(i));
/*      */         
/*  512 */         ps.println("Expanded Type ID: " + Integer.toHexString(exTypeID));
/*      */ 
/*      */         
/*  515 */         int type = getNodeType(makeNodeHandle(i));
/*      */ 
/*      */         
/*  518 */         switch (type) {
/*      */           
/*      */           case 2:
/*  521 */             typestring = "ATTRIBUTE_NODE";
/*      */             break;
/*      */           case 4:
/*  524 */             typestring = "CDATA_SECTION_NODE";
/*      */             break;
/*      */           case 8:
/*  527 */             typestring = "COMMENT_NODE";
/*      */             break;
/*      */           case 11:
/*  530 */             typestring = "DOCUMENT_FRAGMENT_NODE";
/*      */             break;
/*      */           case 9:
/*  533 */             typestring = "DOCUMENT_NODE";
/*      */             break;
/*      */           case 10:
/*  536 */             typestring = "DOCUMENT_NODE";
/*      */             break;
/*      */           case 1:
/*  539 */             typestring = "ELEMENT_NODE";
/*      */             break;
/*      */           case 6:
/*  542 */             typestring = "ENTITY_NODE";
/*      */             break;
/*      */           case 5:
/*  545 */             typestring = "ENTITY_REFERENCE_NODE";
/*      */             break;
/*      */           case 13:
/*  548 */             typestring = "NAMESPACE_NODE";
/*      */             break;
/*      */           case 12:
/*  551 */             typestring = "NOTATION_NODE";
/*      */             break;
/*      */           case -1:
/*  554 */             typestring = "NULL";
/*      */             break;
/*      */           case 7:
/*  557 */             typestring = "PROCESSING_INSTRUCTION_NODE";
/*      */             break;
/*      */           case 3:
/*  560 */             typestring = "TEXT_NODE";
/*      */             break;
/*      */           default:
/*  563 */             typestring = "Unknown!";
/*      */             break;
/*      */         } 
/*      */         
/*  567 */         ps.println("Type: " + typestring);
/*      */         
/*  569 */         int firstChild = _firstch(i);
/*      */         
/*  571 */         if (-1 == firstChild) {
/*  572 */           ps.println("First child: DTM.NULL");
/*  573 */         } else if (-2 == firstChild) {
/*  574 */           ps.println("First child: NOTPROCESSED");
/*      */         } else {
/*  576 */           ps.println("First child: " + firstChild);
/*      */         } 
/*  578 */         int prevSibling = _prevsib(i);
/*      */         
/*  580 */         if (-1 == prevSibling) {
/*  581 */           ps.println("Prev sibling: DTM.NULL");
/*  582 */         } else if (-2 == prevSibling) {
/*  583 */           ps.println("Prev sibling: NOTPROCESSED");
/*      */         } else {
/*  585 */           ps.println("Prev sibling: " + prevSibling);
/*      */         } 
/*  587 */         int nextSibling = _nextsib(i);
/*      */         
/*  589 */         if (-1 == nextSibling) {
/*  590 */           ps.println("Next sibling: DTM.NULL");
/*  591 */         } else if (-2 == nextSibling) {
/*  592 */           ps.println("Next sibling: NOTPROCESSED");
/*      */         } else {
/*  594 */           ps.println("Next sibling: " + nextSibling);
/*      */         } 
/*  596 */         int parent = _parent(i);
/*      */         
/*  598 */         if (-1 == parent) {
/*  599 */           ps.println("Parent: DTM.NULL");
/*  600 */         } else if (-2 == parent) {
/*  601 */           ps.println("Parent: NOTPROCESSED");
/*      */         } else {
/*  603 */           ps.println("Parent: " + parent);
/*      */         } 
/*  605 */         int level = _level(i);
/*      */         
/*  607 */         ps.println("Level: " + level);
/*  608 */         ps.println("Node Value: " + getNodeValue(i));
/*  609 */         ps.println("String Value: " + getStringValue(i));
/*      */         
/*  611 */         ps.println("First Attribute Node: " + this.m_attribute.elementAt(i));
/*      */       }
/*      */     
/*      */     }
/*      */     catch (IOException ioe) {
/*      */       
/*  617 */       ioe.printStackTrace(System.err);
/*  618 */       System.exit(-1);
/*      */     } 
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
/*      */   protected static void dispatchNodeData(Node node, ContentHandler ch, int depth) throws SAXException {
/*      */     Node child;
/*      */     String str;
/*  649 */     switch (node.getNodeType()) {
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 9:
/*      */       case 11:
/*  655 */         for (child = node.getFirstChild(); null != child; 
/*  656 */           child = child.getNextSibling())
/*      */         {
/*  658 */           dispatchNodeData(child, ch, depth + 1);
/*      */         }
/*      */         break;
/*      */       
/*      */       case 7:
/*      */       case 8:
/*  664 */         if (0 != depth) {
/*      */           break;
/*      */         }
/*      */       
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*  671 */         str = node.getNodeValue();
/*  672 */         if (ch instanceof CharacterNodeHandler) {
/*      */           
/*  674 */           ((CharacterNodeHandler)ch).characters(node);
/*      */           
/*      */           break;
/*      */         } 
/*  678 */         ch.characters(str.toCharArray(), 0, str.length());
/*      */         break;
/*      */     } 
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
/*      */   public void setProperty(String property, Object value) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SourceLocator getSourceLocatorFor(int node) {
/*  716 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getNextNodeIdentity(int parm1) {
/*  725 */     if (this.DEBUG) System.out.println("getNextNodeIdenty(" + parm1 + ")"); 
/*  726 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAttributeNode(int parm1, String parm2, String parm3) {
/*  737 */     if (this.DEBUG)
/*      */     {
/*  739 */       System.out.println("getAttributeNode(" + parm1 + "," + parm2 + "," + parm3 + ")");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  745 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalName(int parm1) {
/*  755 */     int exID = getExpandedTypeID(parm1);
/*      */     
/*  757 */     if (this.DEBUG) {
/*      */       
/*  759 */       this.DEBUG = false;
/*  760 */       System.out.print("getLocalName(" + parm1 + ") -> ");
/*  761 */       System.out.println("..." + getLocalNameFromExpandedNameID(exID));
/*  762 */       this.DEBUG = true;
/*      */     } 
/*      */     
/*  765 */     return getLocalNameFromExpandedNameID(exID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeName(int parm1) {
/*  775 */     int exID = getExpandedTypeID(parm1);
/*  776 */     if (this.DEBUG) {
/*      */       
/*  778 */       this.DEBUG = false;
/*  779 */       System.out.print("getLocalName(" + parm1 + ") -> ");
/*  780 */       System.out.println("..." + getLocalNameFromExpandedNameID(exID));
/*  781 */       this.DEBUG = true;
/*      */     } 
/*  783 */     return getLocalNameFromExpandedNameID(exID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAttributeSpecified(int parm1) {
/*  792 */     if (this.DEBUG) System.out.println("isAttributeSpecified(" + parm1 + ")"); 
/*  793 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUnparsedEntityURI(String parm1) {
/*  802 */     if (this.DEBUG) System.out.println("getUnparsedEntityURI(" + parm1 + ")"); 
/*  803 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTDHandler getDTDHandler() {
/*  811 */     if (this.DEBUG) System.out.println("getDTDHandler()"); 
/*  812 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPrefix(int parm1) {
/*  821 */     if (this.DEBUG) System.out.println("getPrefix(" + parm1 + ")"); 
/*  822 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityResolver getEntityResolver() {
/*  830 */     if (this.DEBUG) System.out.println("getEntityResolver()"); 
/*  831 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentTypeDeclarationPublicIdentifier() {
/*  839 */     if (this.DEBUG) System.out.println("get_DTD_PubId()"); 
/*  840 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LexicalHandler getLexicalHandler() {
/*  848 */     if (this.DEBUG) System.out.println("getLexicalHandler()"); 
/*  849 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean needsTwoThreads() {
/*  856 */     if (this.DEBUG) System.out.println("needsTwoThreads()"); 
/*  857 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContentHandler getContentHandler() {
/*  865 */     if (this.DEBUG) System.out.println("getContentHandler()"); 
/*  866 */     return null;
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
/*      */   public void dispatchToEvents(int parm1, ContentHandler parm2) throws SAXException {
/*  879 */     if (this.DEBUG)
/*      */     {
/*  881 */       System.out.println("dispathcToEvents(" + parm1 + "," + parm2 + ")");
/*      */     }
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
/*      */   public String getNamespaceURI(int parm1) {
/*  895 */     if (this.DEBUG) System.out.println("getNamespaceURI(" + parm1 + ")"); 
/*  896 */     return "";
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
/*      */   public void dispatchCharactersEvents(int nodeHandle, ContentHandler ch, boolean normalize) throws SAXException {
/*  910 */     if (this.DEBUG)
/*      */     {
/*  912 */       System.out.println("dispatchCharacterEvents(" + nodeHandle + "," + ch + "," + normalize + ")");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  918 */     if (normalize) {
/*      */       
/*  920 */       XMLString str = getStringValue(nodeHandle);
/*  921 */       str = str.fixWhiteSpace(true, true, false);
/*  922 */       str.dispatchCharactersEvents(ch);
/*      */     }
/*      */     else {
/*      */       
/*  926 */       int type = getNodeType(nodeHandle);
/*  927 */       Node node = getNode(nodeHandle);
/*  928 */       dispatchNodeData(node, ch, 0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean supportsPreStripping() {
/*  938 */     if (this.DEBUG) System.out.println("supportsPreStripping()"); 
/*  939 */     return super.supportsPreStripping();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int _exptype(int parm1) {
/*  948 */     if (this.DEBUG) System.out.println("_exptype(" + parm1 + ")"); 
/*  949 */     return super._exptype(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SuballocatedIntVector findNamespaceContext(int parm1) {
/*  958 */     if (this.DEBUG) System.out.println("SuballocatedIntVector(" + parm1 + ")"); 
/*  959 */     return super.findNamespaceContext(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int _prevsib(int parm1) {
/*  968 */     if (this.DEBUG) System.out.println("_prevsib(" + parm1 + ")"); 
/*  969 */     return super._prevsib(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected short _type(int parm1) {
/*  979 */     if (this.DEBUG) System.out.println("_type(" + parm1 + ")"); 
/*  980 */     return super._type(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getNode(int parm1) {
/*  989 */     if (this.DEBUG) System.out.println("getNode(" + parm1 + ")"); 
/*  990 */     return super.getNode(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPreviousSibling(int parm1) {
/*  999 */     if (this.DEBUG) System.out.println("getPrevSib(" + parm1 + ")"); 
/* 1000 */     return super.getPreviousSibling(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentStandalone(int parm1) {
/* 1009 */     if (this.DEBUG) System.out.println("getDOcStandAlone(" + parm1 + ")"); 
/* 1010 */     return super.getDocumentStandalone(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNodeNameX(int parm1) {
/* 1019 */     if (this.DEBUG) System.out.println("getNodeNameX(" + parm1 + ")");
/*      */     
/* 1021 */     return getNodeName(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFeature(String parm1, boolean parm2) {
/* 1032 */     if (this.DEBUG)
/*      */     {
/* 1034 */       System.out.println("setFeature(" + parm1 + "," + parm2 + ")");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1039 */     super.setFeature(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int _parent(int parm1) {
/* 1048 */     if (this.DEBUG) System.out.println("_parent(" + parm1 + ")"); 
/* 1049 */     return super._parent(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void indexNode(int parm1, int parm2) {
/* 1059 */     if (this.DEBUG) System.out.println("indexNode(" + parm1 + "," + parm2 + ")"); 
/* 1060 */     super.indexNode(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getShouldStripWhitespace() {
/* 1068 */     if (this.DEBUG) System.out.println("getShouldStripWS()"); 
/* 1069 */     return super.getShouldStripWhitespace();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void popShouldStripWhitespace() {
/* 1077 */     if (this.DEBUG) System.out.println("popShouldStripWS()"); 
/* 1078 */     super.popShouldStripWhitespace();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNodeAfter(int parm1, int parm2) {
/* 1088 */     if (this.DEBUG) System.out.println("isNodeAfter(" + parm1 + "," + parm2 + ")"); 
/* 1089 */     return super.isNodeAfter(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNamespaceType(int parm1) {
/* 1098 */     if (this.DEBUG) System.out.println("getNamespaceType(" + parm1 + ")"); 
/* 1099 */     return super.getNamespaceType(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int _level(int parm1) {
/* 1108 */     if (this.DEBUG) System.out.println("_level(" + parm1 + ")"); 
/* 1109 */     return super._level(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void pushShouldStripWhitespace(boolean parm1) {
/* 1119 */     if (this.DEBUG) System.out.println("push_ShouldStripWS(" + parm1 + ")"); 
/* 1120 */     super.pushShouldStripWhitespace(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentVersion(int parm1) {
/* 1129 */     if (this.DEBUG) System.out.println("getDocVer(" + parm1 + ")"); 
/* 1130 */     return super.getDocumentVersion(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSupported(String parm1, String parm2) {
/* 1140 */     if (this.DEBUG) System.out.println("isSupported(" + parm1 + "," + parm2 + ")"); 
/* 1141 */     return super.isSupported(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setShouldStripWhitespace(boolean parm1) {
/* 1151 */     if (this.DEBUG) System.out.println("set_ShouldStripWS(" + parm1 + ")"); 
/* 1152 */     super.setShouldStripWhitespace(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void ensureSizeOfIndex(int parm1, int parm2) {
/* 1163 */     if (this.DEBUG) System.out.println("ensureSizeOfIndex(" + parm1 + "," + parm2 + ")"); 
/* 1164 */     super.ensureSizeOfIndex(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void ensureSize(int parm1) {
/* 1173 */     if (this.DEBUG) System.out.println("ensureSize(" + parm1 + ")");
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
/*      */   public String getDocumentEncoding(int parm1) {
/* 1186 */     if (this.DEBUG) System.out.println("getDocumentEncoding(" + parm1 + ")"); 
/* 1187 */     return super.getDocumentEncoding(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendChild(int parm1, boolean parm2, boolean parm3) {
/* 1198 */     if (this.DEBUG)
/*      */     {
/* 1200 */       System.out.println("appendChild(" + parm1 + "," + parm2 + "," + parm3 + ")");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1206 */     super.appendChild(parm1, parm2, parm3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getLevel(int parm1) {
/* 1215 */     if (this.DEBUG) System.out.println("getLevel(" + parm1 + ")"); 
/* 1216 */     return super.getLevel(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentBaseURI() {
/* 1224 */     if (this.DEBUG) System.out.println("getDocBaseURI()"); 
/* 1225 */     return super.getDocumentBaseURI();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextNamespaceNode(int parm1, int parm2, boolean parm3) {
/* 1236 */     if (this.DEBUG)
/*      */     {
/* 1238 */       System.out.println("getNextNamesapceNode(" + parm1 + "," + parm2 + "," + parm3 + ")");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1244 */     return super.getNextNamespaceNode(parm1, parm2, parm3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendTextChild(String parm1) {
/* 1253 */     if (this.DEBUG) System.out.println("appendTextChild(" + parm1 + ")"); 
/* 1254 */     super.appendTextChild(parm1);
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
/*      */   protected int findGTE(int[] parm1, int parm2, int parm3, int parm4) {
/* 1266 */     if (this.DEBUG)
/*      */     {
/* 1268 */       System.out.println("findGTE(" + parm1 + "," + parm2 + "," + parm3 + ")");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1274 */     return super.findGTE(parm1, parm2, parm3, parm4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstNamespaceNode(int parm1, boolean parm2) {
/* 1284 */     if (this.DEBUG) System.out.println("getFirstNamespaceNode()"); 
/* 1285 */     return super.getFirstNamespaceNode(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getStringValueChunkCount(int parm1) {
/* 1294 */     if (this.DEBUG) System.out.println("getStringChunkCount(" + parm1 + ")"); 
/* 1295 */     return super.getStringValueChunkCount(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLastChild(int parm1) {
/* 1304 */     if (this.DEBUG) System.out.println("getLastChild(" + parm1 + ")"); 
/* 1305 */     return super.getLastChild(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasChildNodes(int parm1) {
/* 1314 */     if (this.DEBUG) System.out.println("hasChildNodes(" + parm1 + ")"); 
/* 1315 */     return super.hasChildNodes(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getNodeType(int parm1) {
/* 1324 */     if (this.DEBUG) {
/*      */       
/* 1326 */       this.DEBUG = false;
/* 1327 */       System.out.print("getNodeType(" + parm1 + ") ");
/* 1328 */       int exID = getExpandedTypeID(parm1);
/* 1329 */       String name = getLocalNameFromExpandedNameID(exID);
/* 1330 */       System.out.println(".. Node name [" + name + "]" + "[" + getNodeType(parm1) + "]");
/*      */ 
/*      */ 
/*      */       
/* 1334 */       this.DEBUG = true;
/*      */     } 
/*      */     
/* 1337 */     return super.getNodeType(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCharacterElementContentWhitespace(int parm1) {
/* 1346 */     if (this.DEBUG) System.out.println("isCharacterElementContentWhitespace(" + parm1 + ")"); 
/* 1347 */     return super.isCharacterElementContentWhitespace(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFirstChild(int parm1) {
/* 1356 */     if (this.DEBUG) System.out.println("getFirstChild(" + parm1 + ")"); 
/* 1357 */     return super.getFirstChild(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentSystemIdentifier(int parm1) {
/* 1366 */     if (this.DEBUG) System.out.println("getDocSysID(" + parm1 + ")"); 
/* 1367 */     return super.getDocumentSystemIdentifier(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void declareNamespaceInContext(int parm1, int parm2) {
/* 1377 */     if (this.DEBUG) System.out.println("declareNamespaceContext(" + parm1 + "," + parm2 + ")"); 
/* 1378 */     super.declareNamespaceInContext(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNamespaceFromExpandedNameID(int parm1) {
/* 1387 */     if (this.DEBUG) {
/*      */       
/* 1389 */       this.DEBUG = false;
/* 1390 */       System.out.print("getNamespaceFromExpandedNameID(" + parm1 + ")");
/* 1391 */       System.out.println("..." + super.getNamespaceFromExpandedNameID(parm1));
/* 1392 */       this.DEBUG = true;
/*      */     } 
/* 1394 */     return super.getNamespaceFromExpandedNameID(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalNameFromExpandedNameID(int parm1) {
/* 1403 */     if (this.DEBUG) {
/*      */       
/* 1405 */       this.DEBUG = false;
/* 1406 */       System.out.print("getLocalNameFromExpandedNameID(" + parm1 + ")");
/* 1407 */       System.out.println("..." + super.getLocalNameFromExpandedNameID(parm1));
/* 1408 */       this.DEBUG = true;
/*      */     } 
/* 1410 */     return super.getLocalNameFromExpandedNameID(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpandedTypeID(int parm1) {
/* 1419 */     if (this.DEBUG) System.out.println("getExpandedTypeID(" + parm1 + ")"); 
/* 1420 */     return super.getExpandedTypeID(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDocument() {
/* 1428 */     if (this.DEBUG) System.out.println("getDocument()"); 
/* 1429 */     return super.getDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int findInSortedSuballocatedIntVector(SuballocatedIntVector parm1, int parm2) {
/* 1440 */     if (this.DEBUG)
/*      */     {
/* 1442 */       System.out.println("findInSortedSubAlloctedVector(" + parm1 + "," + parm2 + ")");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1447 */     return super.findInSortedSuballocatedIntVector(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDocumentAllDeclarationsProcessed(int parm1) {
/* 1456 */     if (this.DEBUG) System.out.println("isDocumentAllDeclProc(" + parm1 + ")"); 
/* 1457 */     return super.isDocumentAllDeclarationsProcessed(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void error(String parm1) {
/* 1466 */     if (this.DEBUG) System.out.println("error(" + parm1 + ")"); 
/* 1467 */     super.error(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int _firstch(int parm1) {
/* 1477 */     if (this.DEBUG) System.out.println("_firstch(" + parm1 + ")"); 
/* 1478 */     return super._firstch(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getOwnerDocument(int parm1) {
/* 1487 */     if (this.DEBUG) System.out.println("getOwnerDoc(" + parm1 + ")"); 
/* 1488 */     return super.getOwnerDocument(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int _nextsib(int parm1) {
/* 1497 */     if (this.DEBUG) System.out.println("_nextSib(" + parm1 + ")"); 
/* 1498 */     return super._nextsib(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNextSibling(int parm1) {
/* 1507 */     if (this.DEBUG) System.out.println("getNextSibling(" + parm1 + ")"); 
/* 1508 */     return super.getNextSibling(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDocumentAllDeclarationsProcessed() {
/* 1517 */     if (this.DEBUG) System.out.println("getDocAllDeclProc()"); 
/* 1518 */     return super.getDocumentAllDeclarationsProcessed();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getParent(int parm1) {
/* 1527 */     if (this.DEBUG) System.out.println("getParent(" + parm1 + ")"); 
/* 1528 */     return super.getParent(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getExpandedTypeID(String parm1, String parm2, int parm3) {
/* 1539 */     if (this.DEBUG) System.out.println("getExpandedTypeID()"); 
/* 1540 */     return super.getExpandedTypeID(parm1, parm2, parm3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentBaseURI(String parm1) {
/* 1549 */     if (this.DEBUG) System.out.println("setDocBaseURI()"); 
/* 1550 */     super.setDocumentBaseURI(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char[] getStringValueChunk(int parm1, int parm2, int[] parm3) {
/* 1561 */     if (this.DEBUG)
/*      */     {
/* 1563 */       System.out.println("getStringChunkValue(" + parm1 + "," + parm2 + ")");
/*      */     }
/*      */ 
/*      */     
/* 1567 */     return super.getStringValueChunk(parm1, parm2, parm3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisTraverser getAxisTraverser(int parm1) {
/* 1576 */     if (this.DEBUG) System.out.println("getAxixTraverser(" + parm1 + ")"); 
/* 1577 */     return super.getAxisTraverser(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getTypedAxisIterator(int parm1, int parm2) {
/* 1587 */     if (this.DEBUG) System.out.println("getTypedAxisIterator(" + parm1 + "," + parm2 + ")"); 
/* 1588 */     return super.getTypedAxisIterator(parm1, parm2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DTMAxisIterator getAxisIterator(int parm1) {
/* 1597 */     if (this.DEBUG) System.out.println("getAxisIterator(" + parm1 + ")"); 
/* 1598 */     return super.getAxisIterator(parm1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getElementById(String parm1) {
/* 1606 */     if (this.DEBUG) System.out.println("getElementByID(" + parm1 + ")"); 
/* 1607 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DeclHandler getDeclHandler() {
/* 1615 */     if (this.DEBUG) System.out.println("getDeclHandler()"); 
/* 1616 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErrorHandler getErrorHandler() {
/* 1624 */     if (this.DEBUG) System.out.println("getErrorHandler()"); 
/* 1625 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDocumentTypeDeclarationSystemIdentifier() {
/* 1633 */     if (this.DEBUG) System.out.println("get_DTD-SID()"); 
/* 1634 */     return null;
/*      */   }
/*      */   
/*      */   public static interface CharacterNodeHandler {
/*      */     void characters(Node param1Node) throws SAXException;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/sql/DTMDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */