/*      */ package org.apache.xpath;
/*      */ 
/*      */ import org.apache.xml.utils.DOM2Helper;
/*      */ import org.apache.xpath.axes.ContextNodeList;
/*      */ import org.apache.xpath.res.XPATHMessages;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.traversal.NodeFilter;
/*      */ import org.w3c.dom.traversal.NodeIterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NodeSet
/*      */   implements Cloneable, ContextNodeList, NodeList, NodeIterator
/*      */ {
/*      */   public NodeSet() {
/*   66 */     this.m_blocksize = 32;
/*   67 */     this.m_mapSize = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeSet(int blocksize) {
/*   77 */     this.m_blocksize = blocksize;
/*   78 */     this.m_mapSize = 0;
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
/*      */   public NodeSet(NodeList nodelist) {
/*   90 */     this(32);
/*      */     
/*   92 */     addNodes(nodelist);
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
/*      */   public NodeSet(NodeSet nodelist) {
/*  104 */     this(32);
/*      */     
/*  106 */     addNodes(nodelist);
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
/*      */   public NodeSet(NodeIterator ni) {
/*  118 */     this(32);
/*      */     
/*  120 */     addNodes(ni);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeSet(Node node) {
/*  131 */     this(32);
/*      */     
/*  133 */     addNode(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node getRoot() {
/*  142 */     return null;
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
/*      */   public NodeIterator cloneWithReset() throws CloneNotSupportedException {
/*  158 */     NodeSet clone = (NodeSet)clone();
/*      */     
/*  160 */     clone.reset();
/*      */     
/*  162 */     return clone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset() {
/*  170 */     this.m_next = 0;
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
/*      */   public int getWhatToShow() {
/*  187 */     return -17;
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
/*      */   public NodeFilter getFilter() {
/*  205 */     return null;
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
/*      */   public boolean getExpandEntityReferences() {
/*  226 */     return true;
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
/*      */   public Node nextNode() throws DOMException {
/*  242 */     if (this.m_next < size()) {
/*      */       
/*  244 */       Node next = elementAt(this.m_next);
/*      */       
/*  246 */       this.m_next++;
/*      */       
/*  248 */       return next;
/*      */     } 
/*      */     
/*  251 */     return null;
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
/*      */   public Node previousNode() throws DOMException {
/*  268 */     if (!this.m_cacheNodes) {
/*  269 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_CANNOT_ITERATE", null));
/*      */     }
/*      */     
/*  272 */     if (this.m_next - 1 > 0) {
/*      */       
/*  274 */       this.m_next--;
/*      */       
/*  276 */       return elementAt(this.m_next);
/*      */     } 
/*      */     
/*  279 */     return null;
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
/*      */   public void detach() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFresh() {
/*  305 */     return (this.m_next == 0);
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
/*      */   public void runTo(int index) {
/*  323 */     if (!this.m_cacheNodes) {
/*  324 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_CANNOT_INDEX", null));
/*      */     }
/*      */     
/*  327 */     if (index >= 0 && this.m_next < this.m_firstFree) {
/*  328 */       this.m_next = index;
/*      */     } else {
/*  330 */       this.m_next = this.m_firstFree - 1;
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
/*      */   public Node item(int index) {
/*  348 */     runTo(index);
/*      */     
/*  350 */     return elementAt(index);
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
/*      */   public int getLength() {
/*  364 */     runTo(-1);
/*      */     
/*  366 */     return size();
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
/*      */   public void addNode(Node n) {
/*  380 */     if (!this.m_mutable) {
/*  381 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  383 */     addElement(n);
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
/*      */   public void insertNode(Node n, int pos) {
/*  398 */     if (!this.m_mutable) {
/*  399 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  401 */     insertElementAt(n, pos);
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
/*      */   public void removeNode(Node n) {
/*  414 */     if (!this.m_mutable) {
/*  415 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  417 */     removeElement(n);
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
/*      */   public void addNodes(NodeList nodelist) {
/*  432 */     if (!this.m_mutable) {
/*  433 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  435 */     if (null != nodelist) {
/*      */       
/*  437 */       int nChildren = nodelist.getLength();
/*      */       
/*  439 */       for (int i = 0; i < nChildren; i++) {
/*      */         
/*  441 */         Node obj = nodelist.item(i);
/*      */         
/*  443 */         if (null != obj)
/*      */         {
/*  445 */           addElement(obj);
/*      */         }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addNodes(NodeSet ns) {
/*  472 */     if (!this.m_mutable) {
/*  473 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  475 */     addNodes(ns);
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
/*      */   public void addNodes(NodeIterator iterator) {
/*  489 */     if (!this.m_mutable) {
/*  490 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  492 */     if (null != iterator) {
/*      */       Node obj;
/*      */ 
/*      */       
/*  496 */       while (null != (obj = iterator.nextNode()))
/*      */       {
/*  498 */         addElement(obj);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addNodesInDocOrder(NodeList nodelist, XPathContext support) {
/*  517 */     if (!this.m_mutable) {
/*  518 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  520 */     int nChildren = nodelist.getLength();
/*      */     
/*  522 */     for (int i = 0; i < nChildren; i++) {
/*      */       
/*  524 */       Node node = nodelist.item(i);
/*      */       
/*  526 */       if (null != node)
/*      */       {
/*  528 */         addNodeInDocOrder(node, support);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addNodesInDocOrder(NodeIterator iterator, XPathContext support) {
/*  545 */     if (!this.m_mutable) {
/*  546 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*      */     
/*      */     Node node;
/*  550 */     while (null != (node = iterator.nextNode()))
/*      */     {
/*  552 */       addNodeInDocOrder(node, support);
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
/*      */   private boolean addNodesInDocOrder(int start, int end, int testIndex, NodeList nodelist, XPathContext support) {
/*  573 */     if (!this.m_mutable) {
/*  574 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  576 */     boolean foundit = false;
/*      */     
/*  578 */     Node node = nodelist.item(testIndex);
/*      */     int i;
/*  580 */     for (i = end; i >= start; i--) {
/*      */       
/*  582 */       Node child = elementAt(i);
/*      */       
/*  584 */       if (child == node) {
/*      */         
/*  586 */         i = -2;
/*      */         
/*      */         break;
/*      */       } 
/*      */       
/*  591 */       if (!DOM2Helper.isNodeAfter(node, child)) {
/*      */         
/*  593 */         insertElementAt(node, i + 1);
/*      */         
/*  595 */         testIndex--;
/*      */         
/*  597 */         if (testIndex > 0) {
/*      */           
/*  599 */           boolean foundPrev = addNodesInDocOrder(0, i, testIndex, nodelist, support);
/*      */ 
/*      */           
/*  602 */           if (!foundPrev)
/*      */           {
/*  604 */             addNodesInDocOrder(i, size() - 1, testIndex, nodelist, support);
/*      */           }
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/*  612 */     if (i == -1)
/*      */     {
/*  614 */       insertElementAt(node, 0);
/*      */     }
/*      */     
/*  617 */     return foundit;
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
/*      */   public int addNodeInDocOrder(Node node, boolean test, XPathContext support) {
/*  636 */     if (!this.m_mutable) {
/*  637 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  639 */     int insertIndex = -1;
/*      */     
/*  641 */     if (test) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  647 */       int size = size();
/*      */       int i;
/*  649 */       for (i = size - 1; i >= 0; i--) {
/*      */         
/*  651 */         Node child = elementAt(i);
/*      */         
/*  653 */         if (child == node) {
/*      */           
/*  655 */           i = -2;
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/*  660 */         if (!DOM2Helper.isNodeAfter(node, child)) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  666 */       if (i != -2)
/*      */       {
/*  668 */         insertIndex = i + 1;
/*      */         
/*  670 */         insertElementAt(node, insertIndex);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  675 */       insertIndex = size();
/*      */       
/*  677 */       boolean foundit = false;
/*      */       
/*  679 */       for (int i = 0; i < insertIndex; i++) {
/*      */         
/*  681 */         if (item(i).equals(node)) {
/*      */           
/*  683 */           foundit = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  689 */       if (!foundit) {
/*  690 */         addElement(node);
/*      */       }
/*      */     } 
/*      */     
/*  694 */     return insertIndex;
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
/*      */   public int addNodeInDocOrder(Node node, XPathContext support) {
/*  713 */     if (!this.m_mutable) {
/*  714 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  716 */     return addNodeInDocOrder(node, true, support);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  722 */   protected transient int m_next = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentPos() {
/*  734 */     return this.m_next;
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
/*      */   public void setCurrentPos(int i) {
/*  746 */     if (!this.m_cacheNodes) {
/*  747 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_CANNOT_INDEX", null));
/*      */     }
/*      */     
/*  750 */     this.m_next = i;
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
/*      */   public Node getCurrentNode() {
/*  763 */     if (!this.m_cacheNodes) {
/*  764 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_CANNOT_INDEX", null));
/*      */     }
/*      */     
/*  767 */     int saved = this.m_next;
/*  768 */     Node n = (this.m_next < this.m_firstFree) ? elementAt(this.m_next) : null;
/*  769 */     this.m_next = saved;
/*  770 */     return n;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient boolean m_mutable = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected transient boolean m_cacheNodes = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getShouldCacheNodes() {
/*  788 */     return this.m_cacheNodes;
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
/*      */   public void setShouldCacheNodes(boolean b) {
/*  805 */     if (!isFresh()) {
/*  806 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_CANNOT_CALL_SETSHOULDCACHENODE", null));
/*      */     }
/*      */     
/*  809 */     this.m_cacheNodes = b;
/*  810 */     this.m_mutable = true;
/*      */   }
/*      */ 
/*      */   
/*  814 */   private transient int m_last = 0;
/*      */   private int m_blocksize;
/*      */   
/*      */   public int getLast() {
/*  818 */     return this.m_last;
/*      */   }
/*      */   Node[] m_map;
/*      */   
/*      */   public void setLast(int last) {
/*  823 */     this.m_last = last;
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
/*  836 */   protected int m_firstFree = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int m_mapSize;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/*  852 */     NodeSet clone = (NodeSet)super.clone();
/*      */     
/*  854 */     if (null != this.m_map && this.m_map == clone.m_map) {
/*      */       
/*  856 */       clone.m_map = new Node[this.m_map.length];
/*      */       
/*  858 */       System.arraycopy(this.m_map, 0, clone.m_map, 0, this.m_map.length);
/*      */     } 
/*      */     
/*  861 */     return clone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  871 */     return this.m_firstFree;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addElement(Node value) {
/*  881 */     if (!this.m_mutable) {
/*  882 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/*  884 */     if (this.m_firstFree + 1 >= this.m_mapSize)
/*      */     {
/*  886 */       if (null == this.m_map) {
/*      */         
/*  888 */         this.m_map = new Node[this.m_blocksize];
/*  889 */         this.m_mapSize = this.m_blocksize;
/*      */       }
/*      */       else {
/*      */         
/*  893 */         this.m_mapSize += this.m_blocksize;
/*      */         
/*  895 */         Node[] newMap = new Node[this.m_mapSize];
/*      */         
/*  897 */         System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*      */         
/*  899 */         this.m_map = newMap;
/*      */       } 
/*      */     }
/*      */     
/*  903 */     this.m_map[this.m_firstFree] = value;
/*      */     
/*  905 */     this.m_firstFree++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void push(Node value) {
/*  916 */     int ff = this.m_firstFree;
/*      */     
/*  918 */     if (ff + 1 >= this.m_mapSize)
/*      */     {
/*  920 */       if (null == this.m_map) {
/*      */         
/*  922 */         this.m_map = new Node[this.m_blocksize];
/*  923 */         this.m_mapSize = this.m_blocksize;
/*      */       }
/*      */       else {
/*      */         
/*  927 */         this.m_mapSize += this.m_blocksize;
/*      */         
/*  929 */         Node[] newMap = new Node[this.m_mapSize];
/*      */         
/*  931 */         System.arraycopy(this.m_map, 0, newMap, 0, ff + 1);
/*      */         
/*  933 */         this.m_map = newMap;
/*      */       } 
/*      */     }
/*      */     
/*  937 */     this.m_map[ff] = value;
/*      */ 
/*      */ 
/*      */     
/*  941 */     this.m_firstFree = ++ff;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Node pop() {
/*  952 */     this.m_firstFree--;
/*      */     
/*  954 */     Node n = this.m_map[this.m_firstFree];
/*      */     
/*  956 */     this.m_map[this.m_firstFree] = null;
/*      */     
/*  958 */     return n;
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
/*      */   public final Node popAndTop() {
/*  970 */     this.m_firstFree--;
/*      */     
/*  972 */     this.m_map[this.m_firstFree] = null;
/*      */     
/*  974 */     return (this.m_firstFree == 0) ? null : this.m_map[this.m_firstFree - 1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void popQuick() {
/*  983 */     this.m_firstFree--;
/*      */     
/*  985 */     this.m_map[this.m_firstFree] = null;
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
/*      */   public final Node peepOrNull() {
/*  997 */     return (null != this.m_map && this.m_firstFree > 0) ? this.m_map[this.m_firstFree - 1] : null;
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
/*      */   public final void pushPair(Node v1, Node v2) {
/* 1012 */     if (null == this.m_map) {
/*      */       
/* 1014 */       this.m_map = new Node[this.m_blocksize];
/* 1015 */       this.m_mapSize = this.m_blocksize;
/*      */ 
/*      */     
/*      */     }
/* 1019 */     else if (this.m_firstFree + 2 >= this.m_mapSize) {
/*      */       
/* 1021 */       this.m_mapSize += this.m_blocksize;
/*      */       
/* 1023 */       Node[] newMap = new Node[this.m_mapSize];
/*      */       
/* 1025 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree);
/*      */       
/* 1027 */       this.m_map = newMap;
/*      */     } 
/*      */ 
/*      */     
/* 1031 */     this.m_map[this.m_firstFree] = v1;
/* 1032 */     this.m_map[this.m_firstFree + 1] = v2;
/* 1033 */     this.m_firstFree += 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void popPair() {
/* 1044 */     this.m_firstFree -= 2;
/* 1045 */     this.m_map[this.m_firstFree] = null;
/* 1046 */     this.m_map[this.m_firstFree + 1] = null;
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
/*      */   public final void setTail(Node n) {
/* 1058 */     this.m_map[this.m_firstFree - 1] = n;
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
/*      */   public final void setTailSub1(Node n) {
/* 1070 */     this.m_map[this.m_firstFree - 2] = n;
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
/*      */   public final Node peepTail() {
/* 1082 */     return this.m_map[this.m_firstFree - 1];
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
/*      */   public final Node peepTailSub1() {
/* 1094 */     return this.m_map[this.m_firstFree - 2];
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
/*      */   public void insertElementAt(Node value, int at) {
/* 1108 */     if (!this.m_mutable) {
/* 1109 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/* 1111 */     if (null == this.m_map) {
/*      */       
/* 1113 */       this.m_map = new Node[this.m_blocksize];
/* 1114 */       this.m_mapSize = this.m_blocksize;
/*      */     }
/* 1116 */     else if (this.m_firstFree + 1 >= this.m_mapSize) {
/*      */       
/* 1118 */       this.m_mapSize += this.m_blocksize;
/*      */       
/* 1120 */       Node[] newMap = new Node[this.m_mapSize];
/*      */       
/* 1122 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*      */       
/* 1124 */       this.m_map = newMap;
/*      */     } 
/*      */     
/* 1127 */     if (at <= this.m_firstFree - 1)
/*      */     {
/* 1129 */       System.arraycopy(this.m_map, at, this.m_map, at + 1, this.m_firstFree - at);
/*      */     }
/*      */     
/* 1132 */     this.m_map[at] = value;
/*      */     
/* 1134 */     this.m_firstFree++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendNodes(NodeSet nodes) {
/* 1145 */     int nNodes = nodes.size();
/*      */     
/* 1147 */     if (null == this.m_map) {
/*      */       
/* 1149 */       this.m_mapSize = nNodes + this.m_blocksize;
/* 1150 */       this.m_map = new Node[this.m_mapSize];
/*      */     }
/* 1152 */     else if (this.m_firstFree + nNodes >= this.m_mapSize) {
/*      */       
/* 1154 */       this.m_mapSize += nNodes + this.m_blocksize;
/*      */       
/* 1156 */       Node[] newMap = new Node[this.m_mapSize];
/*      */       
/* 1158 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + nNodes);
/*      */       
/* 1160 */       this.m_map = newMap;
/*      */     } 
/*      */     
/* 1163 */     System.arraycopy(nodes.m_map, 0, this.m_map, this.m_firstFree, nNodes);
/*      */     
/* 1165 */     this.m_firstFree += nNodes;
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
/*      */   public void removeAllElements() {
/* 1177 */     if (null == this.m_map) {
/*      */       return;
/*      */     }
/* 1180 */     for (int i = 0; i < this.m_firstFree; i++)
/*      */     {
/* 1182 */       this.m_map[i] = null;
/*      */     }
/*      */     
/* 1185 */     this.m_firstFree = 0;
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
/*      */   public boolean removeElement(Node s) {
/* 1201 */     if (!this.m_mutable) {
/* 1202 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/* 1204 */     if (null == this.m_map) {
/* 1205 */       return false;
/*      */     }
/* 1207 */     for (int i = 0; i < this.m_firstFree; i++) {
/*      */       
/* 1209 */       Node node = this.m_map[i];
/*      */       
/* 1211 */       if (null != node && node.equals(s)) {
/*      */         
/* 1213 */         if (i < this.m_firstFree - 1) {
/* 1214 */           System.arraycopy(this.m_map, i + 1, this.m_map, i, this.m_firstFree - i - 1);
/*      */         }
/* 1216 */         this.m_firstFree--;
/* 1217 */         this.m_map[this.m_firstFree] = null;
/*      */         
/* 1219 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 1223 */     return false;
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
/*      */   public void removeElementAt(int i) {
/* 1237 */     if (null == this.m_map) {
/*      */       return;
/*      */     }
/* 1240 */     if (i >= this.m_firstFree)
/* 1241 */       throw new ArrayIndexOutOfBoundsException(i + " >= " + this.m_firstFree); 
/* 1242 */     if (i < 0) {
/* 1243 */       throw new ArrayIndexOutOfBoundsException(i);
/*      */     }
/* 1245 */     if (i < this.m_firstFree - 1) {
/* 1246 */       System.arraycopy(this.m_map, i + 1, this.m_map, i, this.m_firstFree - i - 1);
/*      */     }
/* 1248 */     this.m_firstFree--;
/* 1249 */     this.m_map[this.m_firstFree] = null;
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
/*      */   public void setElementAt(Node node, int index) {
/* 1264 */     if (!this.m_mutable) {
/* 1265 */       throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESET_NOT_MUTABLE", null));
/*      */     }
/* 1267 */     if (null == this.m_map) {
/*      */       
/* 1269 */       this.m_map = new Node[this.m_blocksize];
/* 1270 */       this.m_mapSize = this.m_blocksize;
/*      */     } 
/*      */     
/* 1273 */     this.m_map[index] = node;
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
/*      */   public Node elementAt(int i) {
/* 1286 */     if (null == this.m_map) {
/* 1287 */       return null;
/*      */     }
/* 1289 */     return this.m_map[i];
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
/*      */   public boolean contains(Node s) {
/* 1301 */     runTo(-1);
/*      */     
/* 1303 */     if (null == this.m_map) {
/* 1304 */       return false;
/*      */     }
/* 1306 */     for (int i = 0; i < this.m_firstFree; i++) {
/*      */       
/* 1308 */       Node node = this.m_map[i];
/*      */       
/* 1310 */       if (null != node && node.equals(s)) {
/* 1311 */         return true;
/*      */       }
/*      */     } 
/* 1314 */     return false;
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
/*      */   public int indexOf(Node elem, int index) {
/* 1330 */     runTo(-1);
/*      */     
/* 1332 */     if (null == this.m_map) {
/* 1333 */       return -1;
/*      */     }
/* 1335 */     for (int i = index; i < this.m_firstFree; i++) {
/*      */       
/* 1337 */       Node node = this.m_map[i];
/*      */       
/* 1339 */       if (null != node && node.equals(elem)) {
/* 1340 */         return i;
/*      */       }
/*      */     } 
/* 1343 */     return -1;
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
/*      */   public int indexOf(Node elem) {
/* 1358 */     runTo(-1);
/*      */     
/* 1360 */     if (null == this.m_map) {
/* 1361 */       return -1;
/*      */     }
/* 1363 */     for (int i = 0; i < this.m_firstFree; i++) {
/*      */       
/* 1365 */       Node node = this.m_map[i];
/*      */       
/* 1367 */       if (null != node && node.equals(elem)) {
/* 1368 */         return i;
/*      */       }
/*      */     } 
/* 1371 */     return -1;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/NodeSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */