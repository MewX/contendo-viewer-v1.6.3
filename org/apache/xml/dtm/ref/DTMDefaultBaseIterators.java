/*      */ package org.apache.xml.dtm.ref;
/*      */ 
/*      */ import javax.xml.transform.Source;
/*      */ import org.apache.xml.dtm.Axis;
/*      */ import org.apache.xml.dtm.DTMAxisIterator;
/*      */ import org.apache.xml.dtm.DTMAxisTraverser;
/*      */ import org.apache.xml.dtm.DTMException;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ import org.apache.xml.utils.NodeVector;
/*      */ import org.apache.xml.utils.XMLStringFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class DTMDefaultBaseIterators
/*      */   extends DTMDefaultBaseTraversers
/*      */ {
/*      */   public DTMDefaultBaseIterators(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing) {
/*   56 */     super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing);
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
/*      */   public DTMDefaultBaseIterators(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing, int blocksize, boolean usePrevsib, boolean newNameTable) {
/*   85 */     super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, blocksize, usePrevsib, newNameTable);
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
/*      */   public DTMAxisIterator getTypedAxisIterator(int axis, int type) {
/*  103 */     DTMAxisIterator iterator = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  119 */     switch (axis) {
/*      */       
/*      */       case 13:
/*  122 */         iterator = new TypedSingletonIterator(this, type);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  165 */         return iterator;case 3: iterator = new TypedChildrenIterator(this, type); return iterator;case 10: return (new ParentIterator(this)).setNodeType(type);case 0: return new TypedAncestorIterator(this, type);case 1: return (new TypedAncestorIterator(this, type)).includeSelf();case 2: return new TypedAttributeIterator(this, type);case 4: iterator = new TypedDescendantIterator(this, type); return iterator;case 5: iterator = (new TypedDescendantIterator(this, type)).includeSelf(); return iterator;case 6: iterator = new TypedFollowingIterator(this, type); return iterator;case 11: iterator = new TypedPrecedingIterator(this, type); return iterator;case 7: iterator = new TypedFollowingSiblingIterator(this, type); return iterator;case 12: iterator = new TypedPrecedingSiblingIterator(this, type); return iterator;case 9: iterator = new TypedNamespaceIterator(this, type); return iterator;case 19: iterator = new TypedRootIterator(this, type); return iterator;
/*      */     } 
/*      */     throw new DTMException(XMLMessages.createXMLMessage("ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED", new Object[] { Axis.names[axis] }));
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
/*      */   public DTMAxisIterator getAxisIterator(int axis) {
/*  181 */     DTMAxisIterator iterator = null;
/*      */     
/*  183 */     switch (axis) {
/*      */       
/*      */       case 13:
/*  186 */         iterator = new SingletonIterator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  228 */         return iterator;case 3: iterator = new ChildrenIterator(this); return iterator;case 10: return new ParentIterator(this);case 0: return new AncestorIterator(this);case 1: return (new AncestorIterator(this)).includeSelf();case 2: return new AttributeIterator(this);case 4: iterator = new DescendantIterator(this); return iterator;case 5: iterator = (new DescendantIterator(this)).includeSelf(); return iterator;case 6: iterator = new FollowingIterator(this); return iterator;case 11: iterator = new PrecedingIterator(this); return iterator;case 7: iterator = new FollowingSiblingIterator(this); return iterator;case 12: iterator = new PrecedingSiblingIterator(this); return iterator;case 9: iterator = new NamespaceIterator(this); return iterator;case 19: iterator = new RootIterator(this); return iterator;
/*      */     } 
/*      */     throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_AXIS_NOT_IMPLEMENTED", new Object[] { Axis.names[axis] }));
/*      */   }
/*      */   
/*      */   public abstract class InternalAxisIteratorBase
/*      */     extends DTMAxisIteratorBase {
/*      */     protected int _currentNode;
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public InternalAxisIteratorBase(DTMDefaultBaseIterators this$0) {
/*  239 */       this.this$0 = this$0;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setMark() {
/*  264 */       this._markedNode = this._currentNode;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void gotoMark() {
/*  274 */       this._currentNode = this._markedNode;
/*      */     }
/*      */   }
/*      */   
/*      */   public final class ChildrenIterator extends InternalAxisIteratorBase {
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public ChildrenIterator(DTMDefaultBaseIterators this$0) {
/*  282 */       super(this$0); this.this$0 = this$0;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  300 */       if (node == 0)
/*  301 */         node = this.this$0.getDocument(); 
/*  302 */       if (this._isRestartable) {
/*      */         
/*  304 */         this._startNode = node;
/*  305 */         this._currentNode = (node == -1) ? -1 : this.this$0._firstch(this.this$0.makeNodeIdentity(node));
/*      */ 
/*      */         
/*  308 */         return resetPosition();
/*      */       } 
/*      */       
/*  311 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  322 */       if (this._currentNode != -1) {
/*  323 */         int node = this._currentNode;
/*  324 */         this._currentNode = this.this$0._nextsib(node);
/*  325 */         return returnNode(this.this$0.makeNodeHandle(node));
/*      */       } 
/*      */       
/*  328 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   public final class ParentIterator extends InternalAxisIteratorBase {
/*      */     private int _nodeType;
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public ParentIterator(DTMDefaultBaseIterators this$0) {
/*  337 */       super(this$0); this.this$0 = this$0;
/*      */ 
/*      */ 
/*      */       
/*  341 */       this._nodeType = -1;
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
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  354 */       if (node == 0)
/*  355 */         node = this.this$0.getDocument(); 
/*  356 */       if (this._isRestartable) {
/*      */         
/*  358 */         this._startNode = node;
/*  359 */         this._currentNode = this.this$0.getParent(node);
/*      */         
/*  361 */         return resetPosition();
/*      */       } 
/*      */       
/*  364 */       return this;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setNodeType(int type) {
/*  380 */       this._nodeType = type;
/*      */       
/*  382 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  393 */       int result = this._currentNode;
/*      */       
/*  395 */       if (this._nodeType >= 14) {
/*  396 */         if (this._nodeType != this.this$0.getExpandedTypeID(this._currentNode)) {
/*  397 */           result = -1;
/*      */         }
/*  399 */       } else if (this._nodeType != -1 && 
/*  400 */         this._nodeType != this.this$0.getNodeType(this._currentNode)) {
/*  401 */         result = -1;
/*      */       } 
/*      */ 
/*      */       
/*  405 */       this._currentNode = -1;
/*      */       
/*  407 */       return returnNode(result);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedChildrenIterator
/*      */     extends InternalAxisIteratorBase
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedChildrenIterator(DTMDefaultBaseIterators this$0, int nodeType) {
/*  430 */       super(this$0); this.this$0 = this$0;
/*  431 */       this._nodeType = nodeType;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  445 */       if (node == 0)
/*  446 */         node = this.this$0.getDocument(); 
/*  447 */       if (this._isRestartable) {
/*      */         
/*  449 */         this._startNode = node;
/*  450 */         this._currentNode = (node == -1) ? -1 : this.this$0._firstch(this.this$0.makeNodeIdentity(this._startNode));
/*      */ 
/*      */ 
/*      */         
/*  454 */         return resetPosition();
/*      */       } 
/*      */       
/*  457 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  468 */       int node = this._currentNode;
/*      */       
/*  470 */       int nodeType = this._nodeType;
/*      */       
/*  472 */       if (nodeType >= 14) {
/*  473 */         while (node != -1 && this.this$0._exptype(node) != nodeType) {
/*  474 */           node = this.this$0._nextsib(node);
/*      */         }
/*      */       } else {
/*  477 */         while (node != -1) {
/*  478 */           int eType = this.this$0._exptype(node);
/*  479 */           if ((eType < 14) ? (
/*  480 */             eType == nodeType) : (
/*      */ 
/*      */             
/*  483 */             this.this$0.m_expandedNameTable.getType(eType) == nodeType)) {
/*      */             break;
/*      */           }
/*  486 */           node = this.this$0._nextsib(node);
/*      */         } 
/*      */       } 
/*      */       
/*  490 */       if (node == -1) {
/*  491 */         this._currentNode = -1;
/*  492 */         return -1;
/*      */       } 
/*  494 */       this._currentNode = this.this$0._nextsib(node);
/*  495 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class NamespaceChildrenIterator
/*      */     extends InternalAxisIteratorBase
/*      */   {
/*      */     private final int _nsType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NamespaceChildrenIterator(DTMDefaultBaseIterators this$0, int type) {
/*  521 */       super(this$0); this.this$0 = this$0;
/*  522 */       this._nsType = type;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  536 */       if (node == 0)
/*  537 */         node = this.this$0.getDocument(); 
/*  538 */       if (this._isRestartable) {
/*      */         
/*  540 */         this._startNode = node;
/*  541 */         this._currentNode = (node == -1) ? -1 : -2;
/*      */         
/*  543 */         return resetPosition();
/*      */       } 
/*      */       
/*  546 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  556 */       if (this._currentNode != -1) {
/*  557 */         int node = (-2 == this._currentNode) ? this.this$0._firstch(this.this$0.makeNodeIdentity(this._startNode)) : this.this$0._nextsib(this._currentNode);
/*      */ 
/*      */         
/*  560 */         for (; node != -1; 
/*  561 */           node = this.this$0._nextsib(node)) {
/*  562 */           if (this.this$0.m_expandedNameTable.getNamespaceID(this.this$0._exptype(node)) == this._nsType) {
/*  563 */             this._currentNode = node;
/*      */             
/*  565 */             return returnNode(node);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  570 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class NamespaceIterator
/*      */     extends InternalAxisIteratorBase
/*      */   {
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NamespaceIterator(DTMDefaultBaseIterators this$0) {
/*  588 */       super(this$0);
/*      */       this.this$0 = this$0;
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
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  602 */       if (node == 0)
/*  603 */         node = this.this$0.getDocument(); 
/*  604 */       if (this._isRestartable) {
/*      */         
/*  606 */         this._startNode = node;
/*  607 */         this._currentNode = this.this$0.getFirstNamespaceNode(node, true);
/*      */         
/*  609 */         return resetPosition();
/*      */       } 
/*      */       
/*  612 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  623 */       int node = this._currentNode;
/*      */       
/*  625 */       if (-1 != node) {
/*  626 */         this._currentNode = this.this$0.getNextNamespaceNode(this._startNode, node, true);
/*      */       }
/*  628 */       return returnNode(node);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class TypedNamespaceIterator
/*      */     extends NamespaceIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedNamespaceIterator(DTMDefaultBaseIterators this$0, int nodeType) {
/*  650 */       super(this$0); this.this$0 = this$0;
/*  651 */       this._nodeType = nodeType;
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
/*      */     public int next() {
/*  663 */       int node = this._currentNode;
/*  664 */       for (; node != -1; 
/*  665 */         node = this.this$0.getNextNamespaceNode(this._startNode, node, true)) {
/*  666 */         if (this.this$0.getExpandedTypeID(node) == this._nodeType || this.this$0.getNodeType(node) == this._nodeType || this.this$0.getNamespaceType(node) == this._nodeType) {
/*      */ 
/*      */           
/*  669 */           this._currentNode = node;
/*      */           
/*  671 */           return returnNode(node);
/*      */         } 
/*      */       } 
/*      */       
/*  675 */       return this._currentNode = -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class RootIterator
/*      */     extends InternalAxisIteratorBase
/*      */   {
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public RootIterator(DTMDefaultBaseIterators this$0) {
/*  693 */       super(this$0);
/*      */       this.this$0 = this$0;
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
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  707 */       if (this._isRestartable) {
/*      */         
/*  709 */         this._startNode = this.this$0.getDocumentRoot(node);
/*  710 */         this._currentNode = -1;
/*      */         
/*  712 */         return resetPosition();
/*      */       } 
/*      */       
/*  715 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  725 */       if (this._startNode == this._currentNode) {
/*  726 */         return -1;
/*      */       }
/*  728 */       this._currentNode = this._startNode;
/*      */       
/*  730 */       return returnNode(this._startNode);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class TypedRootIterator
/*      */     extends RootIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedRootIterator(DTMDefaultBaseIterators this$0, int nodeType) {
/*  751 */       super(this$0); this.this$0 = this$0;
/*  752 */       this._nodeType = nodeType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  762 */       if (this._startNode == this._currentNode) {
/*  763 */         return -1;
/*      */       }
/*  765 */       int nodeType = this._nodeType;
/*  766 */       int node = this._startNode;
/*  767 */       int expType = this.this$0.getExpandedTypeID(node);
/*      */       
/*  769 */       this._currentNode = node;
/*      */       
/*  771 */       if (nodeType >= 14) {
/*  772 */         if (nodeType == expType) {
/*  773 */           return returnNode(node);
/*      */         }
/*      */       }
/*  776 */       else if (expType < 14) {
/*  777 */         if (expType == nodeType) {
/*  778 */           return returnNode(node);
/*      */         }
/*      */       }
/*  781 */       else if (this.this$0.m_expandedNameTable.getType(expType) == nodeType) {
/*  782 */         return returnNode(node);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  787 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class NamespaceAttributeIterator
/*      */     extends InternalAxisIteratorBase
/*      */   {
/*      */     private final int _nsType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NamespaceAttributeIterator(DTMDefaultBaseIterators this$0, int nsType) {
/*  810 */       super(this$0);
/*      */       this.this$0 = this$0;
/*  812 */       this._nsType = nsType;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  826 */       if (node == 0)
/*  827 */         node = this.this$0.getDocument(); 
/*  828 */       if (this._isRestartable) {
/*      */         
/*  830 */         this._startNode = node;
/*  831 */         this._currentNode = this.this$0.getFirstNamespaceNode(node, false);
/*      */         
/*  833 */         return resetPosition();
/*      */       } 
/*      */       
/*  836 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  847 */       int node = this._currentNode;
/*      */       
/*  849 */       if (-1 != node) {
/*  850 */         this._currentNode = this.this$0.getNextNamespaceNode(this._startNode, node, false);
/*      */       }
/*  852 */       return returnNode(node);
/*      */     } }
/*      */   
/*      */   public class FollowingSiblingIterator extends InternalAxisIteratorBase {
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public FollowingSiblingIterator(DTMDefaultBaseIterators this$0) {
/*  859 */       super(this$0); this.this$0 = this$0;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  873 */       if (node == 0)
/*  874 */         node = this.this$0.getDocument(); 
/*  875 */       if (this._isRestartable) {
/*      */         
/*  877 */         this._startNode = node;
/*  878 */         this._currentNode = this.this$0.makeNodeIdentity(node);
/*      */         
/*  880 */         return resetPosition();
/*      */       } 
/*      */       
/*  883 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  893 */       this._currentNode = (this._currentNode == -1) ? -1 : this.this$0._nextsib(this._currentNode);
/*      */       
/*  895 */       return returnNode(this.this$0.makeNodeHandle(this._currentNode));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedFollowingSiblingIterator
/*      */     extends FollowingSiblingIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedFollowingSiblingIterator(DTMDefaultBaseIterators this$0, int type) {
/*  916 */       super(this$0); this.this$0 = this$0;
/*  917 */       this._nodeType = type;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  927 */       if (this._currentNode == -1) {
/*  928 */         return -1;
/*      */       }
/*      */       
/*  931 */       int node = this._currentNode;
/*      */       
/*  933 */       int nodeType = this._nodeType;
/*      */       
/*  935 */       if (nodeType >= 14) {
/*      */         do {
/*  937 */           node = this.this$0._nextsib(node);
/*  938 */         } while (node != -1 && this.this$0._exptype(node) != nodeType);
/*      */       } else {
/*  940 */         while ((node = this.this$0._nextsib(node)) != -1) {
/*  941 */           int eType = this.this$0._exptype(node);
/*  942 */           if ((eType < 14) ? (
/*  943 */             eType == nodeType) : (
/*      */ 
/*      */             
/*  946 */             this.this$0.m_expandedNameTable.getType(eType) == nodeType)) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  952 */       this._currentNode = node;
/*      */       
/*  954 */       return (this._currentNode == -1) ? -1 : returnNode(this.this$0.makeNodeHandle(this._currentNode));
/*      */     }
/*      */   }
/*      */   
/*      */   public final class AttributeIterator
/*      */     extends InternalAxisIteratorBase {
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public AttributeIterator(DTMDefaultBaseIterators this$0) {
/*  963 */       super(this$0); this.this$0 = this$0;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/*  979 */       if (node == 0)
/*  980 */         node = this.this$0.getDocument(); 
/*  981 */       if (this._isRestartable) {
/*      */         
/*  983 */         this._startNode = node;
/*  984 */         this._currentNode = this.this$0.getFirstAttributeIdentity(this.this$0.makeNodeIdentity(node));
/*      */         
/*  986 */         return resetPosition();
/*      */       } 
/*      */       
/*  989 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1000 */       int node = this._currentNode;
/*      */       
/* 1002 */       if (node != -1) {
/* 1003 */         this._currentNode = this.this$0.getNextAttributeIdentity(node);
/* 1004 */         return returnNode(this.this$0.makeNodeHandle(node));
/*      */       } 
/*      */       
/* 1007 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedAttributeIterator
/*      */     extends InternalAxisIteratorBase
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedAttributeIterator(DTMDefaultBaseIterators this$0, int nodeType) {
/* 1027 */       super(this$0); this.this$0 = this$0;
/* 1028 */       this._nodeType = nodeType;
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
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1043 */       if (this._isRestartable) {
/*      */         
/* 1045 */         this._startNode = node;
/*      */         
/* 1047 */         this._currentNode = this.this$0.getTypedAttribute(node, this._nodeType);
/*      */         
/* 1049 */         return resetPosition();
/*      */       } 
/*      */       
/* 1052 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1063 */       int node = this._currentNode;
/*      */ 
/*      */ 
/*      */       
/* 1067 */       this._currentNode = -1;
/*      */       
/* 1069 */       return returnNode(node);
/*      */     } }
/*      */   
/*      */   public class PrecedingSiblingIterator extends InternalAxisIteratorBase { protected int _startNodeID;
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public PrecedingSiblingIterator(DTMDefaultBaseIterators this$0) {
/* 1076 */       super(this$0); this.this$0 = this$0;
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
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isReverse() {
/* 1091 */       return true;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1105 */       if (node == 0)
/* 1106 */         node = this.this$0.getDocument(); 
/* 1107 */       if (this._isRestartable) {
/*      */         
/* 1109 */         this._startNode = node;
/* 1110 */         node = this._startNodeID = this.this$0.makeNodeIdentity(node);
/*      */         
/* 1112 */         if (node == -1) {
/*      */           
/* 1114 */           this._currentNode = node;
/* 1115 */           return resetPosition();
/*      */         } 
/*      */         
/* 1118 */         int type = this.this$0.m_expandedNameTable.getType(this.this$0._exptype(node));
/* 1119 */         if (2 == type || 13 == type) {
/*      */ 
/*      */           
/* 1122 */           this._currentNode = node;
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1127 */           this._currentNode = this.this$0._parent(node);
/* 1128 */           if (-1 != this._currentNode) {
/* 1129 */             this._currentNode = this.this$0._firstch(this._currentNode);
/*      */           } else {
/* 1131 */             this._currentNode = node;
/*      */           } 
/*      */         } 
/* 1134 */         return resetPosition();
/*      */       } 
/*      */       
/* 1137 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1148 */       if (this._currentNode == this._startNodeID || this._currentNode == -1)
/*      */       {
/* 1150 */         return -1;
/*      */       }
/*      */ 
/*      */       
/* 1154 */       int node = this._currentNode;
/* 1155 */       this._currentNode = this.this$0._nextsib(node);
/*      */       
/* 1157 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedPrecedingSiblingIterator
/*      */     extends PrecedingSiblingIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedPrecedingSiblingIterator(DTMDefaultBaseIterators this$0, int type) {
/* 1180 */       super(this$0); this.this$0 = this$0;
/* 1181 */       this._nodeType = type;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1191 */       int node = this._currentNode;
/*      */ 
/*      */       
/* 1194 */       int nodeType = this._nodeType;
/* 1195 */       int startID = this._startNodeID;
/*      */       
/* 1197 */       if (nodeType >= 14) {
/* 1198 */         while (node != -1 && node != startID && this.this$0._exptype(node) != nodeType) {
/* 1199 */           node = this.this$0._nextsib(node);
/*      */         }
/*      */       } else {
/* 1202 */         while (node != -1 && node != startID) {
/* 1203 */           int expType = this.this$0._exptype(node);
/* 1204 */           if ((expType < 14) ? (
/* 1205 */             expType == nodeType) : (
/*      */ 
/*      */ 
/*      */             
/* 1209 */             this.this$0.m_expandedNameTable.getType(expType) == nodeType)) {
/*      */             break;
/*      */           }
/*      */           
/* 1213 */           node = this.this$0._nextsib(node);
/*      */         } 
/*      */       } 
/*      */       
/* 1217 */       if (node == -1 || node == this._startNodeID) {
/* 1218 */         this._currentNode = -1;
/* 1219 */         return -1;
/*      */       } 
/* 1221 */       this._currentNode = this.this$0._nextsib(node);
/* 1222 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     } }
/*      */   public class PrecedingIterator extends InternalAxisIteratorBase { private final int _maxAncestors = 8; protected int[] _stack; protected int _sp;
/*      */     protected int _oldsp;
/*      */     protected int _markedsp;
/*      */     protected int _markedNode;
/*      */     protected int _markedDescendant;
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public PrecedingIterator(DTMDefaultBaseIterators this$0) {
/* 1232 */       super(this$0); this.this$0 = this$0;
/*      */ 
/*      */ 
/*      */       
/* 1236 */       this._maxAncestors = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1242 */       this._stack = new int[8];
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isReverse() {
/* 1258 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator cloneIterator() {
/* 1268 */       this._isRestartable = false;
/*      */ 
/*      */ 
/*      */       
/* 1272 */       try { PrecedingIterator clone = (PrecedingIterator)clone();
/* 1273 */         int[] stackCopy = new int[this._stack.length];
/* 1274 */         System.arraycopy(this._stack, 0, stackCopy, 0, this._stack.length);
/*      */         
/* 1276 */         clone._stack = stackCopy;
/*      */ 
/*      */         
/* 1279 */         return clone; } catch (CloneNotSupportedException e)
/*      */       
/*      */       { 
/*      */         
/* 1283 */         throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null)); }
/*      */     
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1298 */       if (node == 0)
/* 1299 */         node = this.this$0.getDocument(); 
/* 1300 */       if (this._isRestartable) {
/*      */         
/* 1302 */         node = this.this$0.makeNodeIdentity(node);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1307 */         if (this.this$0._type(node) == 2) {
/* 1308 */           node = this.this$0._parent(node);
/*      */         }
/* 1310 */         this._startNode = node; int index;
/* 1311 */         this._stack[index = 0] = node;
/*      */ 
/*      */ 
/*      */         
/* 1315 */         int parent = node;
/* 1316 */         while ((parent = this.this$0._parent(parent)) != -1) {
/*      */           
/* 1318 */           if (++index == this._stack.length) {
/*      */             
/* 1320 */             int[] stack = new int[index + 4];
/* 1321 */             System.arraycopy(this._stack, 0, stack, 0, index);
/* 1322 */             this._stack = stack;
/*      */           } 
/* 1324 */           this._stack[index] = parent;
/*      */         } 
/* 1326 */         if (index > 0) {
/* 1327 */           index--;
/*      */         }
/* 1329 */         this._currentNode = this._stack[index];
/*      */         
/* 1331 */         this._oldsp = this._sp = index;
/*      */         
/* 1333 */         return resetPosition();
/*      */       } 
/*      */       
/* 1336 */       return this;
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
/*      */     
/*      */     public int next() {
/* 1349 */       this._currentNode++;
/* 1350 */       for (; this._sp >= 0; 
/* 1351 */         this._currentNode++) {
/*      */         
/* 1353 */         if (this._currentNode < this._stack[this._sp]) {
/*      */           
/* 1355 */           if (this.this$0._type(this._currentNode) != 2 && this.this$0._type(this._currentNode) != 13)
/*      */           {
/* 1357 */             return returnNode(this.this$0.makeNodeHandle(this._currentNode));
/*      */           }
/*      */         } else {
/* 1360 */           this._sp--;
/*      */         } 
/* 1362 */       }  return -1;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator reset() {
/* 1376 */       this._sp = this._oldsp;
/*      */       
/* 1378 */       return resetPosition();
/*      */     }
/*      */     
/*      */     public void setMark() {
/* 1382 */       this._markedsp = this._sp;
/* 1383 */       this._markedNode = this._currentNode;
/* 1384 */       this._markedDescendant = this._stack[0];
/*      */     }
/*      */     
/*      */     public void gotoMark() {
/* 1388 */       this._sp = this._markedsp;
/* 1389 */       this._currentNode = this._markedNode;
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedPrecedingIterator
/*      */     extends PrecedingIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedPrecedingIterator(DTMDefaultBaseIterators this$0, int type) {
/* 1411 */       super(this$0); this.this$0 = this$0;
/* 1412 */       this._nodeType = type;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1422 */       int node = this._currentNode;
/* 1423 */       int nodeType = this._nodeType;
/*      */       
/* 1425 */       if (nodeType >= 14) {
/*      */         while (true) {
/* 1427 */           node++;
/*      */           
/* 1429 */           if (this._sp < 0) {
/* 1430 */             node = -1; break;
/*      */           } 
/* 1432 */           if (node >= this._stack[this._sp]) {
/* 1433 */             if (--this._sp < 0) {
/* 1434 */               node = -1; break;
/*      */             }  continue;
/*      */           } 
/* 1437 */           if (this.this$0._exptype(node) == nodeType) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/*      */         while (true) {
/*      */           
/* 1445 */           node++;
/*      */           
/* 1447 */           if (this._sp < 0) {
/* 1448 */             node = -1; break;
/*      */           } 
/* 1450 */           if (node >= this._stack[this._sp]) {
/* 1451 */             if (--this._sp < 0) {
/* 1452 */               node = -1; break;
/*      */             } 
/*      */             continue;
/*      */           } 
/* 1456 */           int expType = this.this$0._exptype(node);
/* 1457 */           if ((expType < 14) ? (
/* 1458 */             expType == nodeType) : (
/*      */ 
/*      */ 
/*      */             
/* 1462 */             this.this$0.m_expandedNameTable.getType(expType) == nodeType)) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1470 */       this._currentNode = node;
/*      */       
/* 1472 */       return (node == -1) ? -1 : returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class FollowingIterator
/*      */     extends InternalAxisIteratorBase
/*      */   {
/*      */     DTMAxisTraverser m_traverser;
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public FollowingIterator(DTMDefaultBaseIterators this$0) {
/* 1484 */       super(this$0); this.this$0 = this$0;
/* 1485 */       this.m_traverser = this$0.getAxisTraverser(6);
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1499 */       if (node == 0)
/* 1500 */         node = this.this$0.getDocument(); 
/* 1501 */       if (this._isRestartable) {
/*      */         
/* 1503 */         this._startNode = node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1510 */         this._currentNode = this.m_traverser.first(node);
/*      */ 
/*      */         
/* 1513 */         return resetPosition();
/*      */       } 
/*      */       
/* 1516 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1527 */       int node = this._currentNode;
/*      */       
/* 1529 */       this._currentNode = this.m_traverser.next(this._startNode, this._currentNode);
/*      */       
/* 1531 */       return returnNode(node);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedFollowingIterator
/*      */     extends FollowingIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedFollowingIterator(DTMDefaultBaseIterators this$0, int type) {
/* 1551 */       super(this$0); this.this$0 = this$0;
/* 1552 */       this._nodeType = type;
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
/*      */     public int next() {
/*      */       int node;
/*      */       do {
/* 1566 */         node = this._currentNode;
/*      */         
/* 1568 */         this._currentNode = this.m_traverser.next(this._startNode, this._currentNode);
/*      */ 
/*      */       
/*      */       }
/* 1572 */       while (node != -1 && this.this$0.getExpandedTypeID(node) != this._nodeType && this.this$0.getNodeType(node) != this._nodeType);
/*      */       
/* 1574 */       return (node == -1) ? -1 : returnNode(node);
/*      */     } }
/*      */   public class AncestorIterator extends InternalAxisIteratorBase { NodeVector m_ancestors; int m_ancestorsPos;
/*      */     int m_markedPos;
/*      */     int m_realStartNode;
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public AncestorIterator(DTMDefaultBaseIterators this$0) {
/* 1582 */       super(this$0); this.this$0 = this$0;
/*      */       
/* 1584 */       this.m_ancestors = new NodeVector();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getStartNode() {
/* 1602 */       return this.m_realStartNode;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final boolean isReverse() {
/* 1612 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator cloneIterator() {
/* 1622 */       this._isRestartable = false;
/*      */ 
/*      */ 
/*      */       
/* 1626 */       try { AncestorIterator clone = (AncestorIterator)clone();
/*      */         
/* 1628 */         clone._startNode = this._startNode;
/*      */ 
/*      */         
/* 1631 */         return clone; } catch (CloneNotSupportedException e)
/*      */       
/*      */       { 
/*      */         
/* 1635 */         throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null)); }
/*      */     
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1650 */       if (node == 0)
/* 1651 */         node = this.this$0.getDocument(); 
/* 1652 */       this.m_realStartNode = node;
/*      */       
/* 1654 */       if (this._isRestartable) {
/*      */         
/* 1656 */         int nodeID = this.this$0.makeNodeIdentity(node);
/*      */         
/* 1658 */         if (!this._includeSelf && node != -1) {
/* 1659 */           nodeID = this.this$0._parent(nodeID);
/* 1660 */           node = this.this$0.makeNodeHandle(nodeID);
/*      */         } 
/*      */         
/* 1663 */         this._startNode = node;
/*      */         
/* 1665 */         while (nodeID != -1) {
/* 1666 */           this.m_ancestors.addElement(node);
/* 1667 */           nodeID = this.this$0._parent(nodeID);
/* 1668 */           node = this.this$0.makeNodeHandle(nodeID);
/*      */         } 
/* 1670 */         this.m_ancestorsPos = this.m_ancestors.size() - 1;
/*      */         
/* 1672 */         this._currentNode = (this.m_ancestorsPos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1;
/*      */ 
/*      */ 
/*      */         
/* 1676 */         return resetPosition();
/*      */       } 
/*      */       
/* 1679 */       return this;
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
/*      */     public DTMAxisIterator reset() {
/* 1691 */       this.m_ancestorsPos = this.m_ancestors.size() - 1;
/*      */       
/* 1693 */       this._currentNode = (this.m_ancestorsPos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1;
/*      */ 
/*      */       
/* 1696 */       return resetPosition();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1707 */       int next = this._currentNode;
/*      */       
/* 1709 */       int pos = --this.m_ancestorsPos;
/*      */       
/* 1711 */       this._currentNode = (pos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1;
/*      */ 
/*      */       
/* 1714 */       return returnNode(next);
/*      */     }
/*      */     
/*      */     public void setMark() {
/* 1718 */       this.m_markedPos = this.m_ancestorsPos;
/*      */     }
/*      */     
/*      */     public void gotoMark() {
/* 1722 */       this.m_ancestorsPos = this.m_markedPos;
/* 1723 */       this._currentNode = (this.m_ancestorsPos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1;
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedAncestorIterator
/*      */     extends AncestorIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedAncestorIterator(DTMDefaultBaseIterators this$0, int type) {
/* 1744 */       super(this$0); this.this$0 = this$0;
/* 1745 */       this._nodeType = type;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1759 */       if (node == 0)
/* 1760 */         node = this.this$0.getDocument(); 
/* 1761 */       this.m_realStartNode = node;
/*      */       
/* 1763 */       if (this._isRestartable) {
/*      */         
/* 1765 */         int nodeID = this.this$0.makeNodeIdentity(node);
/* 1766 */         int nodeType = this._nodeType;
/*      */         
/* 1768 */         if (!this._includeSelf && node != -1) {
/* 1769 */           nodeID = this.this$0._parent(nodeID);
/*      */         }
/*      */         
/* 1772 */         this._startNode = node;
/*      */         
/* 1774 */         if (nodeType >= 14) {
/* 1775 */           while (nodeID != -1) {
/* 1776 */             int eType = this.this$0._exptype(nodeID);
/*      */             
/* 1778 */             if (eType == nodeType) {
/* 1779 */               this.m_ancestors.addElement(this.this$0.makeNodeHandle(nodeID));
/*      */             }
/* 1781 */             nodeID = this.this$0._parent(nodeID);
/*      */           } 
/*      */         } else {
/* 1784 */           while (nodeID != -1) {
/* 1785 */             int eType = this.this$0._exptype(nodeID);
/*      */             
/* 1787 */             if ((eType >= 14 && this.this$0.m_expandedNameTable.getType(eType) == nodeType) || (eType < 14 && eType == nodeType))
/*      */             {
/*      */               
/* 1790 */               this.m_ancestors.addElement(this.this$0.makeNodeHandle(nodeID));
/*      */             }
/* 1792 */             nodeID = this.this$0._parent(nodeID);
/*      */           } 
/*      */         } 
/* 1795 */         this.m_ancestorsPos = this.m_ancestors.size() - 1;
/*      */         
/* 1797 */         this._currentNode = (this.m_ancestorsPos >= 0) ? this.m_ancestors.elementAt(this.m_ancestorsPos) : -1;
/*      */ 
/*      */ 
/*      */         
/* 1801 */         return resetPosition();
/*      */       } 
/*      */       
/* 1804 */       return this;
/*      */     } }
/*      */   
/*      */   public class DescendantIterator extends InternalAxisIteratorBase {
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */     
/*      */     public DescendantIterator(DTMDefaultBaseIterators this$0) {
/* 1811 */       super(this$0); this.this$0 = this$0;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 1825 */       if (node == 0)
/* 1826 */         node = this.this$0.getDocument(); 
/* 1827 */       if (this._isRestartable) {
/*      */         
/* 1829 */         node = this.this$0.makeNodeIdentity(node);
/* 1830 */         this._startNode = node;
/*      */         
/* 1832 */         if (this._includeSelf) {
/* 1833 */           node--;
/*      */         }
/* 1835 */         this._currentNode = node;
/*      */         
/* 1837 */         return resetPosition();
/*      */       } 
/*      */       
/* 1840 */       return this;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean isDescendant(int identity) {
/* 1859 */       return (this.this$0._parent(identity) >= this._startNode || this._startNode == identity);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*      */       int type;
/* 1869 */       if (this._startNode == -1) {
/* 1870 */         return -1;
/*      */       }
/*      */       
/* 1873 */       if (this._includeSelf && this._currentNode + 1 == this._startNode) {
/* 1874 */         return returnNode(this.this$0.makeNodeHandle(++this._currentNode));
/*      */       }
/* 1876 */       int node = this._currentNode;
/*      */ 
/*      */       
/*      */       do {
/* 1880 */         node++;
/* 1881 */         type = this.this$0._type(node);
/*      */         
/* 1883 */         if (-1 == type || !isDescendant(node)) {
/* 1884 */           this._currentNode = -1;
/* 1885 */           return -1;
/*      */         }
/*      */       
/* 1888 */       } while (2 == type || 3 == type || 13 == type);
/*      */       
/* 1890 */       this._currentNode = node;
/* 1891 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator reset() {
/* 1901 */       boolean temp = this._isRestartable;
/*      */       
/* 1903 */       this._isRestartable = true;
/*      */       
/* 1905 */       setStartNode(this.this$0.makeNodeHandle(this._startNode));
/*      */       
/* 1907 */       this._isRestartable = temp;
/*      */       
/* 1909 */       return this;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedDescendantIterator
/*      */     extends DescendantIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedDescendantIterator(DTMDefaultBaseIterators this$0, int nodeType) {
/* 1930 */       super(this$0); this.this$0 = this$0;
/* 1931 */       this._nodeType = nodeType;
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
/*      */     public int next() {
/*      */       int type;
/* 1944 */       if (this._startNode == -1) {
/* 1945 */         return -1;
/*      */       }
/*      */       
/* 1948 */       int node = this._currentNode;
/*      */ 
/*      */       
/*      */       do {
/* 1952 */         node++;
/* 1953 */         type = this.this$0._type(node);
/*      */         
/* 1955 */         if (-1 == type || !isDescendant(node)) {
/* 1956 */           this._currentNode = -1;
/* 1957 */           return -1;
/*      */         }
/*      */       
/* 1960 */       } while (type != this._nodeType && this.this$0._exptype(node) != this._nodeType);
/*      */       
/* 1962 */       this._currentNode = node;
/* 1963 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class NthDescendantIterator
/*      */     extends DescendantIterator
/*      */   {
/*      */     int _pos;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NthDescendantIterator(DTMDefaultBaseIterators this$0, int pos) {
/* 1984 */       super(this$0); this.this$0 = this$0;
/* 1985 */       this._pos = pos;
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
/*      */     
/*      */     public int next() {
/*      */       int node;
/* 1999 */       while ((node = super.next()) != -1) {
/*      */         
/* 2001 */         node = this.this$0.makeNodeIdentity(node);
/*      */         
/* 2003 */         int parent = this.this$0._parent(node);
/* 2004 */         int child = this.this$0._firstch(parent);
/* 2005 */         int pos = 0;
/*      */ 
/*      */         
/*      */         do {
/* 2009 */           int type = this.this$0._type(child);
/*      */           
/* 2011 */           if (1 != type)
/* 2012 */             continue;  pos++;
/*      */         }
/* 2014 */         while (pos < this._pos && (child = this.this$0._nextsib(child)) != -1);
/*      */         
/* 2016 */         if (node == child) {
/* 2017 */           return node;
/*      */         }
/*      */       } 
/* 2020 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class SingletonIterator
/*      */     extends InternalAxisIteratorBase
/*      */   {
/*      */     private boolean _isConstant;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */     
/*      */     public SingletonIterator() {
/* 2039 */       this(-2147483648, false);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SingletonIterator(int node) {
/* 2050 */       this(node, false);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SingletonIterator(int node, boolean constant) {
/* 2061 */       DTMDefaultBaseIterators.this = DTMDefaultBaseIterators.this;
/* 2062 */       this._currentNode = this._startNode = node;
/* 2063 */       this._isConstant = constant;
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
/*      */ 
/*      */     
/*      */     public DTMAxisIterator setStartNode(int node) {
/* 2077 */       if (node == 0)
/* 2078 */         node = DTMDefaultBaseIterators.this.getDocument(); 
/* 2079 */       if (this._isConstant) {
/*      */         
/* 2081 */         this._currentNode = this._startNode;
/*      */         
/* 2083 */         return resetPosition();
/*      */       } 
/* 2085 */       if (this._isRestartable) {
/*      */         
/* 2087 */         if (this._currentNode == Integer.MIN_VALUE)
/*      */         {
/* 2089 */           this._currentNode = this._startNode = node;
/*      */         }
/*      */         
/* 2092 */         return resetPosition();
/*      */       } 
/*      */       
/* 2095 */       return this;
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
/*      */     public DTMAxisIterator reset() {
/* 2107 */       if (this._isConstant) {
/*      */         
/* 2109 */         this._currentNode = this._startNode;
/*      */         
/* 2111 */         return resetPosition();
/*      */       } 
/*      */ 
/*      */       
/* 2115 */       boolean temp = this._isRestartable;
/*      */       
/* 2117 */       this._isRestartable = true;
/*      */       
/* 2119 */       setStartNode(this._startNode);
/*      */       
/* 2121 */       this._isRestartable = temp;
/*      */ 
/*      */       
/* 2124 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 2135 */       int result = this._currentNode;
/*      */       
/* 2137 */       this._currentNode = -1;
/*      */       
/* 2139 */       return returnNode(result);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedSingletonIterator
/*      */     extends SingletonIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final DTMDefaultBaseIterators this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedSingletonIterator(DTMDefaultBaseIterators this$0, int nodeType) {
/* 2159 */       super(this$0); this.this$0 = this$0;
/* 2160 */       this._nodeType = nodeType;
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
/*      */     public int next() {
/* 2172 */       int result = this._currentNode;
/* 2173 */       int nodeType = this._nodeType;
/*      */       
/* 2175 */       this._currentNode = -1;
/*      */       
/* 2177 */       if (nodeType >= 14) {
/* 2178 */         if (this.this$0.getExpandedTypeID(result) == nodeType) {
/* 2179 */           return returnNode(result);
/*      */         }
/*      */       }
/* 2182 */       else if (this.this$0.getNodeType(result) == nodeType) {
/* 2183 */         return returnNode(result);
/*      */       } 
/*      */ 
/*      */       
/* 2187 */       return -1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMDefaultBaseIterators.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */