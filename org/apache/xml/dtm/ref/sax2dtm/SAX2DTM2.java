/*      */ package org.apache.xml.dtm.ref.sax2dtm;
/*      */ 
/*      */ import java.util.Vector;
/*      */ import javax.xml.transform.Source;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.dtm.DTMAxisIterator;
/*      */ import org.apache.xml.dtm.DTMException;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBase;
/*      */ import org.apache.xml.dtm.ref.DTMDefaultBaseIterators;
/*      */ import org.apache.xml.dtm.ref.ExtendedType;
/*      */ import org.apache.xml.res.XMLMessages;
/*      */ import org.apache.xml.serializer.SerializationHandler;
/*      */ import org.apache.xml.utils.FastStringBuffer;
/*      */ import org.apache.xml.utils.XMLString;
/*      */ import org.apache.xml.utils.XMLStringDefault;
/*      */ import org.apache.xml.utils.XMLStringFactory;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.SAXException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SAX2DTM2
/*      */   extends SAX2DTM
/*      */ {
/*      */   private int[] m_exptype_map0;
/*      */   private int[] m_nextsib_map0;
/*      */   private int[] m_firstch_map0;
/*      */   private int[] m_parent_map0;
/*      */   private int[][] m_exptype_map;
/*      */   private int[][] m_nextsib_map;
/*      */   private int[][] m_firstch_map;
/*      */   private int[][] m_parent_map;
/*      */   protected ExtendedType[] m_extendedTypes;
/*      */   protected Vector m_values;
/*      */   
/*      */   public final class ChildrenIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     private final SAX2DTM2 this$0;
/*      */     
/*      */     public ChildrenIterator(SAX2DTM2 this$0) {
/*   69 */       super(this$0); this.this$0 = this$0;
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
/*   87 */       if (node == 0)
/*   88 */         node = this.this$0.getDocument(); 
/*   89 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/*   91 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*   92 */         this._currentNode = (node == -1) ? -1 : this.this$0._firstch2(this.this$0.makeNodeIdentity(node));
/*      */ 
/*      */         
/*   95 */         return resetPosition();
/*      */       } 
/*      */       
/*   98 */       return (DTMAxisIterator)this;
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
/*  109 */       if (this._currentNode != -1) {
/*  110 */         int node = this._currentNode;
/*  111 */         this._currentNode = this.this$0._nextsib2(node);
/*  112 */         return returnNode(this.this$0.makeNodeHandle(node));
/*      */       } 
/*      */       
/*  115 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   public final class ParentIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
/*      */     private int _nodeType;
/*      */     private final SAX2DTM2 this$0;
/*      */     
/*      */     public ParentIterator(SAX2DTM2 this$0) {
/*  124 */       super(this$0); this.this$0 = this$0;
/*      */ 
/*      */ 
/*      */       
/*  128 */       this._nodeType = -1;
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
/*  141 */       if (node == 0)
/*  142 */         node = this.this$0.getDocument(); 
/*  143 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/*  145 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*      */         
/*  147 */         if (node != -1) {
/*  148 */           this._currentNode = this.this$0._parent2(this.this$0.makeNodeIdentity(node));
/*      */         } else {
/*  150 */           this._currentNode = -1;
/*      */         } 
/*  152 */         return resetPosition();
/*      */       } 
/*      */       
/*  155 */       return (DTMAxisIterator)this;
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
/*  171 */       this._nodeType = type;
/*      */       
/*  173 */       return (DTMAxisIterator)this;
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
/*  184 */       int result = this._currentNode;
/*  185 */       if (result == -1) {
/*  186 */         return -1;
/*      */       }
/*      */       
/*  189 */       if (this._nodeType == -1) {
/*  190 */         this._currentNode = -1;
/*  191 */         return returnNode(this.this$0.makeNodeHandle(result));
/*      */       } 
/*  193 */       if (this._nodeType >= 14) {
/*  194 */         if (this._nodeType == this.this$0._exptype2(result)) {
/*  195 */           this._currentNode = -1;
/*  196 */           return returnNode(this.this$0.makeNodeHandle(result));
/*      */         }
/*      */       
/*      */       }
/*  200 */       else if (this._nodeType == this.this$0._type2(result)) {
/*  201 */         this._currentNode = -1;
/*  202 */         return returnNode(this.this$0.makeNodeHandle(result));
/*      */       } 
/*      */ 
/*      */       
/*  206 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedChildrenIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedChildrenIterator(SAX2DTM2 this$0, int nodeType) {
/*  229 */       super(this$0); this.this$0 = this$0;
/*  230 */       this._nodeType = nodeType;
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
/*  244 */       if (node == 0)
/*  245 */         node = this.this$0.getDocument(); 
/*  246 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/*  248 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*  249 */         this._currentNode = (node == -1) ? -1 : this.this$0._firstch2(this.this$0.makeNodeIdentity(((DTMAxisIteratorBase)this)._startNode));
/*      */ 
/*      */ 
/*      */         
/*  253 */         return resetPosition();
/*      */       } 
/*      */       
/*  256 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  266 */       int node = this._currentNode;
/*  267 */       if (node == -1) {
/*  268 */         return -1;
/*      */       }
/*  270 */       int nodeType = this._nodeType;
/*      */       
/*  272 */       if (nodeType != 1) {
/*  273 */         while (node != -1 && this.this$0._exptype2(node) != nodeType) {
/*  274 */           node = this.this$0._nextsib2(node);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  284 */         while (node != -1) {
/*  285 */           int eType = this.this$0._exptype2(node);
/*  286 */           if (eType >= 14) {
/*      */             break;
/*      */           }
/*  289 */           node = this.this$0._nextsib2(node);
/*      */         } 
/*      */       } 
/*      */       
/*  293 */       if (node == -1) {
/*  294 */         this._currentNode = -1;
/*  295 */         return -1;
/*      */       } 
/*  297 */       this._currentNode = this.this$0._nextsib2(node);
/*  298 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNodeByPosition(int position) {
/*  308 */       if (position <= 0) {
/*  309 */         return -1;
/*      */       }
/*  311 */       int node = this._currentNode;
/*  312 */       int pos = 0;
/*      */       
/*  314 */       int nodeType = this._nodeType;
/*  315 */       if (nodeType != 1) {
/*  316 */         while (node != -1) {
/*      */           
/*  318 */           pos++;
/*  319 */           if (this.this$0._exptype2(node) == nodeType && pos == position) {
/*  320 */             return this.this$0.makeNodeHandle(node);
/*      */           }
/*      */           
/*  323 */           node = this.this$0._nextsib2(node);
/*      */         } 
/*  325 */         return -1;
/*      */       } 
/*      */       
/*  328 */       while (node != -1) {
/*      */         
/*  330 */         pos++;
/*  331 */         if (this.this$0._exptype2(node) >= 14 && pos == position) {
/*  332 */           return this.this$0.makeNodeHandle(node);
/*      */         }
/*  334 */         node = this.this$0._nextsib2(node);
/*      */       } 
/*  336 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class TypedRootIterator
/*      */     extends DTMDefaultBaseIterators.RootIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedRootIterator(SAX2DTM2 this$0, int nodeType) {
/*  359 */       super(this$0); this.this$0 = this$0;
/*  360 */       this._nodeType = nodeType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  370 */       if (((DTMAxisIteratorBase)this)._startNode == ((DTMDefaultBaseIterators.InternalAxisIteratorBase)this)._currentNode) {
/*  371 */         return -1;
/*      */       }
/*  373 */       int node = ((DTMAxisIteratorBase)this)._startNode;
/*  374 */       int expType = this.this$0._exptype2(this.this$0.makeNodeIdentity(node));
/*      */       
/*  376 */       ((DTMDefaultBaseIterators.InternalAxisIteratorBase)this)._currentNode = node;
/*      */       
/*  378 */       if (this._nodeType >= 14) {
/*  379 */         if (this._nodeType == expType) {
/*  380 */           return returnNode(node);
/*      */         
/*      */         }
/*      */       }
/*  384 */       else if (expType < 14) {
/*  385 */         if (expType == this._nodeType) {
/*  386 */           return returnNode(node);
/*      */         
/*      */         }
/*      */       }
/*  390 */       else if (this.this$0.m_extendedTypes[expType].getNodeType() == this._nodeType) {
/*  391 */         return returnNode(node);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  396 */       return -1;
/*      */     } }
/*      */   
/*      */   public class FollowingSiblingIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
/*      */     private final SAX2DTM2 this$0;
/*      */     
/*      */     public FollowingSiblingIterator(SAX2DTM2 this$0) {
/*  403 */       super(this$0); this.this$0 = this$0;
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
/*  417 */       if (node == 0)
/*  418 */         node = this.this$0.getDocument(); 
/*  419 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/*  421 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*  422 */         this._currentNode = this.this$0.makeNodeIdentity(node);
/*      */         
/*  424 */         return resetPosition();
/*      */       } 
/*      */       
/*  427 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  437 */       this._currentNode = (this._currentNode == -1) ? -1 : this.this$0._nextsib2(this._currentNode);
/*      */       
/*  439 */       return returnNode(this.this$0.makeNodeHandle(this._currentNode));
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
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedFollowingSiblingIterator(SAX2DTM2 this$0, int type) {
/*  460 */       super(this$0); this.this$0 = this$0;
/*  461 */       this._nodeType = type;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  471 */       if (this._currentNode == -1) {
/*  472 */         return -1;
/*      */       }
/*      */       
/*  475 */       int node = this._currentNode;
/*  476 */       int nodeType = this._nodeType;
/*      */       
/*  478 */       if (nodeType != 1) { do {  }
/*  479 */         while ((node = this.this$0._nextsib2(node)) != -1 && this.this$0._exptype2(node) != nodeType); }
/*      */       else { do {
/*      */         
/*  482 */         } while ((node = this.this$0._nextsib2(node)) != -1 && this.this$0._exptype2(node) < 14); }
/*      */ 
/*      */       
/*  485 */       this._currentNode = node;
/*      */       
/*  487 */       return (node == -1) ? -1 : returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */   }
/*      */   
/*      */   public final class AttributeIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     private final SAX2DTM2 this$0;
/*      */     
/*      */     public AttributeIterator(SAX2DTM2 this$0) {
/*  497 */       super(this$0); this.this$0 = this$0;
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
/*  513 */       if (node == 0)
/*  514 */         node = this.this$0.getDocument(); 
/*  515 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/*  517 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*  518 */         this._currentNode = this.this$0.getFirstAttributeIdentity(this.this$0.makeNodeIdentity(node));
/*      */         
/*  520 */         return resetPosition();
/*      */       } 
/*      */       
/*  523 */       return (DTMAxisIterator)this;
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
/*  534 */       int node = this._currentNode;
/*      */       
/*  536 */       if (node != -1) {
/*  537 */         this._currentNode = this.this$0.getNextAttributeIdentity(node);
/*  538 */         return returnNode(this.this$0.makeNodeHandle(node));
/*      */       } 
/*      */       
/*  541 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedAttributeIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedAttributeIterator(SAX2DTM2 this$0, int nodeType) {
/*  561 */       super(this$0); this.this$0 = this$0;
/*  562 */       this._nodeType = nodeType;
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
/*  577 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/*  579 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*      */         
/*  581 */         this._currentNode = this.this$0.getTypedAttribute(node, this._nodeType);
/*      */         
/*  583 */         return resetPosition();
/*      */       } 
/*      */       
/*  586 */       return (DTMAxisIterator)this;
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
/*  597 */       int node = this._currentNode;
/*      */ 
/*      */ 
/*      */       
/*  601 */       this._currentNode = -1;
/*      */       
/*  603 */       return returnNode(node);
/*      */     } }
/*      */   
/*      */   public class PrecedingSiblingIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase { protected int _startNodeID;
/*      */     private final SAX2DTM2 this$0;
/*      */     
/*      */     public PrecedingSiblingIterator(SAX2DTM2 this$0) {
/*  610 */       super(this$0); this.this$0 = this$0;
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
/*  625 */       return true;
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
/*  639 */       if (node == 0)
/*  640 */         node = this.this$0.getDocument(); 
/*  641 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/*  643 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*  644 */         node = this._startNodeID = this.this$0.makeNodeIdentity(node);
/*      */         
/*  646 */         if (node == -1) {
/*      */           
/*  648 */           this._currentNode = node;
/*  649 */           return resetPosition();
/*      */         } 
/*      */         
/*  652 */         int type = this.this$0._type2(node);
/*  653 */         if (2 == type || 13 == type) {
/*      */ 
/*      */           
/*  656 */           this._currentNode = node;
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  661 */           this._currentNode = this.this$0._parent2(node);
/*  662 */           if (-1 != this._currentNode) {
/*  663 */             this._currentNode = this.this$0._firstch2(this._currentNode);
/*      */           } else {
/*  665 */             this._currentNode = node;
/*      */           } 
/*      */         } 
/*  668 */         return resetPosition();
/*      */       } 
/*      */       
/*  671 */       return (DTMAxisIterator)this;
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
/*  682 */       if (this._currentNode == this._startNodeID || this._currentNode == -1)
/*      */       {
/*  684 */         return -1;
/*      */       }
/*      */ 
/*      */       
/*  688 */       int node = this._currentNode;
/*  689 */       this._currentNode = this.this$0._nextsib2(node);
/*      */       
/*  691 */       return returnNode(this.this$0.makeNodeHandle(node));
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
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedPrecedingSiblingIterator(SAX2DTM2 this$0, int type) {
/*  714 */       super(this$0); this.this$0 = this$0;
/*  715 */       this._nodeType = type;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  725 */       int node = this._currentNode;
/*      */       
/*  727 */       int nodeType = this._nodeType;
/*  728 */       int startNodeID = this._startNodeID;
/*      */       
/*  730 */       if (nodeType != 1) {
/*  731 */         while (node != -1 && node != startNodeID && this.this$0._exptype2(node) != nodeType) {
/*  732 */           node = this.this$0._nextsib2(node);
/*      */         }
/*      */       } else {
/*      */         
/*  736 */         while (node != -1 && node != startNodeID && this.this$0._exptype2(node) < 14) {
/*  737 */           node = this.this$0._nextsib2(node);
/*      */         }
/*      */       } 
/*      */       
/*  741 */       if (node == -1 || node == startNodeID) {
/*  742 */         this._currentNode = -1;
/*  743 */         return -1;
/*      */       } 
/*      */       
/*  746 */       this._currentNode = this.this$0._nextsib2(node);
/*  747 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLast() {
/*  756 */       if (((DTMAxisIteratorBase)this)._last != -1) {
/*  757 */         return ((DTMAxisIteratorBase)this)._last;
/*      */       }
/*  759 */       setMark();
/*      */       
/*  761 */       int node = this._currentNode;
/*  762 */       int nodeType = this._nodeType;
/*  763 */       int startNodeID = this._startNodeID;
/*      */       
/*  765 */       int last = 0;
/*  766 */       if (nodeType != 1) {
/*  767 */         while (node != -1 && node != startNodeID) {
/*  768 */           if (this.this$0._exptype2(node) == nodeType) {
/*  769 */             last++;
/*      */           }
/*  771 */           node = this.this$0._nextsib2(node);
/*      */         } 
/*      */       } else {
/*      */         
/*  775 */         while (node != -1 && node != startNodeID) {
/*  776 */           if (this.this$0._exptype2(node) >= 14) {
/*  777 */             last++;
/*      */           }
/*  779 */           node = this.this$0._nextsib2(node);
/*      */         } 
/*      */       } 
/*      */       
/*  783 */       gotoMark();
/*      */       
/*  785 */       return ((DTMAxisIteratorBase)this)._last = last;
/*      */     } }
/*      */   
/*      */   public class PrecedingIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase { private final int _maxAncestors = 8;
/*      */     protected int[] _stack;
/*      */     protected int _sp;
/*      */     protected int _oldsp;
/*      */     
/*      */     public PrecedingIterator(SAX2DTM2 this$0) {
/*  794 */       super(this$0); this.this$0 = this$0;
/*      */ 
/*      */ 
/*      */       
/*  798 */       this._maxAncestors = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  804 */       this._stack = new int[8];
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected int _markedsp;
/*      */ 
/*      */     
/*      */     protected int _markedNode;
/*      */     
/*      */     protected int _markedDescendant;
/*      */     
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */     
/*      */     public boolean isReverse() {
/*  820 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator cloneIterator() {
/*  830 */       ((DTMAxisIteratorBase)this)._isRestartable = false;
/*      */ 
/*      */ 
/*      */       
/*  834 */       try { PrecedingIterator clone = (PrecedingIterator)clone();
/*  835 */         int[] stackCopy = new int[this._stack.length];
/*  836 */         System.arraycopy(this._stack, 0, stackCopy, 0, this._stack.length);
/*      */         
/*  838 */         clone._stack = stackCopy;
/*      */ 
/*      */         
/*  841 */         return (DTMAxisIterator)clone; } catch (CloneNotSupportedException e)
/*      */       
/*      */       { 
/*      */         
/*  845 */         throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null)); }
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
/*  860 */       if (node == 0)
/*  861 */         node = this.this$0.getDocument(); 
/*  862 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/*  864 */         node = this.this$0.makeNodeIdentity(node);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  869 */         if (this.this$0._type2(node) == 2) {
/*  870 */           node = this.this$0._parent2(node);
/*      */         }
/*  872 */         ((DTMAxisIteratorBase)this)._startNode = node; int index;
/*  873 */         this._stack[index = 0] = node;
/*      */         
/*  875 */         int parent = node;
/*  876 */         while ((parent = this.this$0._parent2(parent)) != -1) {
/*      */           
/*  878 */           if (++index == this._stack.length) {
/*      */             
/*  880 */             int[] stack = new int[index * 2];
/*  881 */             System.arraycopy(this._stack, 0, stack, 0, index);
/*  882 */             this._stack = stack;
/*      */           } 
/*  884 */           this._stack[index] = parent;
/*      */         } 
/*      */         
/*  887 */         if (index > 0) {
/*  888 */           index--;
/*      */         }
/*  890 */         this._currentNode = this._stack[index];
/*      */         
/*  892 */         this._oldsp = this._sp = index;
/*      */         
/*  894 */         return resetPosition();
/*      */       } 
/*      */       
/*  897 */       return (DTMAxisIterator)this;
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
/*  910 */       this._currentNode++; for (; this._sp >= 0; this._currentNode++) {
/*      */         
/*  912 */         if (this._currentNode < this._stack[this._sp]) {
/*      */           
/*  914 */           int type = this.this$0._type2(this._currentNode);
/*  915 */           if (type != 2 && type != 13) {
/*  916 */             return returnNode(this.this$0.makeNodeHandle(this._currentNode));
/*      */           }
/*      */         } else {
/*  919 */           this._sp--;
/*      */         } 
/*  921 */       }  return -1;
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
/*  935 */       this._sp = this._oldsp;
/*      */       
/*  937 */       return resetPosition();
/*      */     }
/*      */     
/*      */     public void setMark() {
/*  941 */       this._markedsp = this._sp;
/*  942 */       this._markedNode = this._currentNode;
/*  943 */       this._markedDescendant = this._stack[0];
/*      */     }
/*      */     
/*      */     public void gotoMark() {
/*  947 */       this._sp = this._markedsp;
/*  948 */       this._currentNode = this._markedNode;
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
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedPrecedingIterator(SAX2DTM2 this$0, int type) {
/*  970 */       super(this$0); this.this$0 = this$0;
/*  971 */       this._nodeType = type;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/*  981 */       int node = this._currentNode;
/*  982 */       int nodeType = this._nodeType;
/*      */       
/*  984 */       if (nodeType >= 14) {
/*      */         while (true) {
/*  986 */           node++;
/*      */           
/*  988 */           if (this._sp < 0) {
/*  989 */             node = -1;
/*      */             break;
/*      */           } 
/*  992 */           if (node >= this._stack[this._sp]) {
/*  993 */             if (--this._sp < 0) {
/*  994 */               node = -1; break;
/*      */             } 
/*      */             continue;
/*      */           } 
/*  998 */           if (this.this$0._exptype2(node) == nodeType) {
/*      */             break;
/*      */           }
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*      */         while (true) {
/*      */           
/* 1007 */           node++;
/*      */           
/* 1009 */           if (this._sp < 0) {
/* 1010 */             node = -1;
/*      */             break;
/*      */           } 
/* 1013 */           if (node >= this._stack[this._sp]) {
/* 1014 */             if (--this._sp < 0) {
/* 1015 */               node = -1;
/*      */               break;
/*      */             } 
/*      */             continue;
/*      */           } 
/* 1020 */           int expType = this.this$0._exptype2(node);
/* 1021 */           if ((expType < 14) ? (
/* 1022 */             expType == nodeType) : (
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1027 */             this.this$0.m_extendedTypes[expType].getNodeType() == nodeType)) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1035 */       this._currentNode = node;
/*      */       
/* 1037 */       return (node == -1) ? -1 : returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class FollowingIterator
/*      */     extends DTMDefaultBaseIterators.InternalAxisIteratorBase
/*      */   {
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */     
/*      */     public FollowingIterator(SAX2DTM2 this$0) {
/* 1049 */       super(this$0); this.this$0 = this$0;
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
/* 1064 */       if (node == 0)
/* 1065 */         node = this.this$0.getDocument(); 
/* 1066 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         int i;
/* 1068 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*      */ 
/*      */ 
/*      */         
/* 1072 */         node = this.this$0.makeNodeIdentity(node);
/*      */ 
/*      */         
/* 1075 */         int type = this.this$0._type2(node);
/*      */         
/* 1077 */         if (2 == type || 13 == type) {
/*      */           
/* 1079 */           node = this.this$0._parent2(node);
/* 1080 */           i = this.this$0._firstch2(node);
/*      */           
/* 1082 */           if (-1 != i) {
/* 1083 */             this._currentNode = this.this$0.makeNodeHandle(i);
/* 1084 */             return resetPosition();
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*      */         do {
/* 1090 */           i = this.this$0._nextsib2(node);
/*      */           
/* 1092 */           if (-1 != i)
/* 1093 */             continue;  node = this.this$0._parent2(node);
/*      */         }
/* 1095 */         while (-1 == i && -1 != node);
/*      */         
/* 1097 */         this._currentNode = this.this$0.makeNodeHandle(i);
/*      */ 
/*      */         
/* 1100 */         return resetPosition();
/*      */       } 
/*      */       
/* 1103 */       return (DTMAxisIterator)this;
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
/* 1114 */       int node = this._currentNode;
/*      */ 
/*      */       
/* 1117 */       int current = this.this$0.makeNodeIdentity(node);
/*      */ 
/*      */       
/*      */       while (true) {
/* 1121 */         current++;
/*      */         
/* 1123 */         int type = this.this$0._type2(current);
/* 1124 */         if (-1 == type) {
/* 1125 */           this._currentNode = -1;
/* 1126 */           return returnNode(node);
/*      */         } 
/*      */         
/* 1129 */         if (2 == type || 13 == type)
/*      */           continue;  break;
/*      */       } 
/* 1132 */       this._currentNode = this.this$0.makeNodeHandle(current);
/* 1133 */       return returnNode(node);
/*      */     }
/*      */   }
/*      */ 
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
/*      */     
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedFollowingIterator(SAX2DTM2 this$0, int type) {
/* 1155 */       super(this$0); this.this$0 = this$0;
/* 1156 */       this._nodeType = type;
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
/*      */     public int next() {
/* 1170 */       int i, nodeType = this._nodeType;
/* 1171 */       int currentNodeID = this.this$0.makeNodeIdentity(this._currentNode);
/*      */       
/* 1173 */       if (nodeType >= 14) {
/*      */         do {
/* 1175 */           int type; i = currentNodeID;
/* 1176 */           int current = i;
/*      */           
/*      */           do {
/* 1179 */             current++;
/* 1180 */             type = this.this$0._type2(current);
/*      */           }
/* 1182 */           while (type != -1 && (2 == type || 13 == type));
/*      */           
/* 1184 */           currentNodeID = (type != -1) ? current : -1;
/*      */         }
/* 1186 */         while (i != -1 && this.this$0._exptype2(i) != nodeType);
/*      */       } else {
/*      */         do {
/*      */           int k;
/* 1190 */           i = currentNodeID;
/* 1191 */           int j = i;
/*      */           
/*      */           do {
/* 1194 */             j++;
/* 1195 */             k = this.this$0._type2(j);
/*      */           }
/* 1197 */           while (k != -1 && (2 == k || 13 == k));
/*      */           
/* 1199 */           currentNodeID = (k != -1) ? j : -1;
/*      */         
/*      */         }
/* 1202 */         while (i != -1 && this.this$0._exptype2(i) != nodeType && this.this$0._type2(i) != nodeType);
/*      */       } 
/*      */       
/* 1205 */       this._currentNode = this.this$0.makeNodeHandle(currentNodeID);
/* 1206 */       return (i == -1) ? -1 : returnNode(this.this$0.makeNodeHandle(i));
/*      */     } }
/*      */   
/*      */   public class AncestorIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase { private static final int m_blocksize = 32;
/*      */     int[] m_ancestors;
/*      */     int m_size;
/*      */     
/*      */     public AncestorIterator(SAX2DTM2 this$0) {
/* 1214 */       super(this$0); this.this$0 = this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1220 */       this.m_ancestors = new int[32];
/*      */ 
/*      */       
/* 1223 */       this.m_size = 0;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     int m_ancestorsPos;
/*      */ 
/*      */     
/*      */     int m_markedPos;
/*      */ 
/*      */     
/*      */     int m_realStartNode;
/*      */     
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */     
/*      */     public int getStartNode() {
/* 1240 */       return this.m_realStartNode;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final boolean isReverse() {
/* 1250 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator cloneIterator() {
/* 1260 */       ((DTMAxisIteratorBase)this)._isRestartable = false;
/*      */ 
/*      */ 
/*      */       
/* 1264 */       try { AncestorIterator clone = (AncestorIterator)clone();
/*      */         
/* 1266 */         ((DTMAxisIteratorBase)clone)._startNode = ((DTMAxisIteratorBase)this)._startNode;
/*      */ 
/*      */         
/* 1269 */         return (DTMAxisIterator)clone; } catch (CloneNotSupportedException e)
/*      */       
/*      */       { 
/*      */         
/* 1273 */         throw new DTMException(XMLMessages.createXMLMessage("ER_ITERATOR_CLONE_NOT_SUPPORTED", null)); }
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
/* 1288 */       if (node == 0)
/* 1289 */         node = this.this$0.getDocument(); 
/* 1290 */       this.m_realStartNode = node;
/*      */       
/* 1292 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/* 1294 */         int nodeID = this.this$0.makeNodeIdentity(node);
/* 1295 */         this.m_size = 0;
/*      */         
/* 1297 */         if (nodeID == -1) {
/* 1298 */           this._currentNode = -1;
/* 1299 */           this.m_ancestorsPos = 0;
/* 1300 */           return (DTMAxisIterator)this;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1305 */         if (!((DTMAxisIteratorBase)this)._includeSelf) {
/* 1306 */           nodeID = this.this$0._parent2(nodeID);
/* 1307 */           node = this.this$0.makeNodeHandle(nodeID);
/*      */         } 
/*      */         
/* 1310 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*      */         
/* 1312 */         while (nodeID != -1) {
/*      */           
/* 1314 */           if (this.m_size >= this.m_ancestors.length) {
/*      */             
/* 1316 */             int[] newAncestors = new int[this.m_size * 2];
/* 1317 */             System.arraycopy(this.m_ancestors, 0, newAncestors, 0, this.m_ancestors.length);
/* 1318 */             this.m_ancestors = newAncestors;
/*      */           } 
/*      */           
/* 1321 */           this.m_ancestors[this.m_size++] = node;
/* 1322 */           nodeID = this.this$0._parent2(nodeID);
/* 1323 */           node = this.this$0.makeNodeHandle(nodeID);
/*      */         } 
/*      */         
/* 1326 */         this.m_ancestorsPos = this.m_size - 1;
/*      */         
/* 1328 */         this._currentNode = (this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1;
/*      */ 
/*      */ 
/*      */         
/* 1332 */         return resetPosition();
/*      */       } 
/*      */       
/* 1335 */       return (DTMAxisIterator)this;
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
/* 1347 */       this.m_ancestorsPos = this.m_size - 1;
/*      */       
/* 1349 */       this._currentNode = (this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1;
/*      */ 
/*      */       
/* 1352 */       return resetPosition();
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
/* 1363 */       int next = this._currentNode;
/*      */       
/* 1365 */       int pos = --this.m_ancestorsPos;
/*      */       
/* 1367 */       this._currentNode = (pos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1;
/*      */ 
/*      */       
/* 1370 */       return returnNode(next);
/*      */     }
/*      */     
/*      */     public void setMark() {
/* 1374 */       this.m_markedPos = this.m_ancestorsPos;
/*      */     }
/*      */     
/*      */     public void gotoMark() {
/* 1378 */       this.m_ancestorsPos = this.m_markedPos;
/* 1379 */       this._currentNode = (this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1;
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
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedAncestorIterator(SAX2DTM2 this$0, int type) {
/* 1400 */       super(this$0); this.this$0 = this$0;
/* 1401 */       this._nodeType = type;
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
/* 1415 */       if (node == 0)
/* 1416 */         node = this.this$0.getDocument(); 
/* 1417 */       this.m_realStartNode = node;
/*      */       
/* 1419 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/* 1421 */         int nodeID = this.this$0.makeNodeIdentity(node);
/* 1422 */         this.m_size = 0;
/*      */         
/* 1424 */         if (nodeID == -1) {
/* 1425 */           this._currentNode = -1;
/* 1426 */           this.m_ancestorsPos = 0;
/* 1427 */           return (DTMAxisIterator)this;
/*      */         } 
/*      */         
/* 1430 */         int nodeType = this._nodeType;
/*      */         
/* 1432 */         if (!((DTMAxisIteratorBase)this)._includeSelf) {
/* 1433 */           nodeID = this.this$0._parent2(nodeID);
/* 1434 */           node = this.this$0.makeNodeHandle(nodeID);
/*      */         } 
/*      */         
/* 1437 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*      */         
/* 1439 */         if (nodeType >= 14) {
/* 1440 */           while (nodeID != -1) {
/* 1441 */             int eType = this.this$0._exptype2(nodeID);
/*      */             
/* 1443 */             if (eType == nodeType) {
/* 1444 */               if (this.m_size >= this.m_ancestors.length) {
/*      */                 
/* 1446 */                 int[] newAncestors = new int[this.m_size * 2];
/* 1447 */                 System.arraycopy(this.m_ancestors, 0, newAncestors, 0, this.m_ancestors.length);
/* 1448 */                 this.m_ancestors = newAncestors;
/*      */               } 
/* 1450 */               this.m_ancestors[this.m_size++] = this.this$0.makeNodeHandle(nodeID);
/*      */             } 
/* 1452 */             nodeID = this.this$0._parent2(nodeID);
/*      */           } 
/*      */         } else {
/*      */           
/* 1456 */           while (nodeID != -1) {
/* 1457 */             int eType = this.this$0._exptype2(nodeID);
/*      */             
/* 1459 */             if ((eType < 14 && eType == nodeType) || (eType >= 14 && this.this$0.m_extendedTypes[eType].getNodeType() == nodeType)) {
/*      */ 
/*      */               
/* 1462 */               if (this.m_size >= this.m_ancestors.length) {
/*      */                 
/* 1464 */                 int[] newAncestors = new int[this.m_size * 2];
/* 1465 */                 System.arraycopy(this.m_ancestors, 0, newAncestors, 0, this.m_ancestors.length);
/* 1466 */                 this.m_ancestors = newAncestors;
/*      */               } 
/* 1468 */               this.m_ancestors[this.m_size++] = this.this$0.makeNodeHandle(nodeID);
/*      */             } 
/* 1470 */             nodeID = this.this$0._parent2(nodeID);
/*      */           } 
/*      */         } 
/* 1473 */         this.m_ancestorsPos = this.m_size - 1;
/*      */         
/* 1475 */         this._currentNode = (this.m_ancestorsPos >= 0) ? this.m_ancestors[this.m_ancestorsPos] : -1;
/*      */ 
/*      */ 
/*      */         
/* 1479 */         return resetPosition();
/*      */       } 
/*      */       
/* 1482 */       return (DTMAxisIterator)this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNodeByPosition(int position) {
/* 1490 */       if (position > 0 && position <= this.m_size) {
/* 1491 */         return this.m_ancestors[position - 1];
/*      */       }
/*      */       
/* 1494 */       return -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLast() {
/* 1502 */       return this.m_size;
/*      */     } }
/*      */   
/*      */   public class DescendantIterator extends DTMDefaultBaseIterators.InternalAxisIteratorBase {
/*      */     private final SAX2DTM2 this$0;
/*      */     
/*      */     public DescendantIterator(SAX2DTM2 this$0) {
/* 1509 */       super(this$0); this.this$0 = this$0;
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
/* 1523 */       if (node == 0)
/* 1524 */         node = this.this$0.getDocument(); 
/* 1525 */       if (((DTMAxisIteratorBase)this)._isRestartable) {
/*      */         
/* 1527 */         node = this.this$0.makeNodeIdentity(node);
/* 1528 */         ((DTMAxisIteratorBase)this)._startNode = node;
/*      */         
/* 1530 */         if (((DTMAxisIteratorBase)this)._includeSelf) {
/* 1531 */           node--;
/*      */         }
/* 1533 */         this._currentNode = node;
/*      */         
/* 1535 */         return resetPosition();
/*      */       } 
/*      */       
/* 1538 */       return (DTMAxisIterator)this;
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
/*      */     protected final boolean isDescendant(int identity) {
/* 1557 */       return (this.this$0._parent2(identity) >= ((DTMAxisIteratorBase)this)._startNode || ((DTMAxisIteratorBase)this)._startNode == identity);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1567 */       int startNode = ((DTMAxisIteratorBase)this)._startNode;
/* 1568 */       if (startNode == -1) {
/* 1569 */         return -1;
/*      */       }
/*      */       
/* 1572 */       if (((DTMAxisIteratorBase)this)._includeSelf && this._currentNode + 1 == startNode) {
/* 1573 */         return returnNode(this.this$0.makeNodeHandle(++this._currentNode));
/*      */       }
/* 1575 */       int node = this._currentNode;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1580 */       if (startNode == 0) {
/*      */         int eType; int type;
/*      */         do {
/* 1583 */           node++;
/* 1584 */           eType = this.this$0._exptype2(node);
/*      */           
/* 1586 */           if (-1 == eType) {
/* 1587 */             this._currentNode = -1;
/* 1588 */             return -1;
/*      */           }
/*      */         
/*      */         }
/* 1592 */         while (eType == 3 || (type = this.this$0.m_extendedTypes[eType].getNodeType()) == 2 || type == 13);
/*      */       } else {
/*      */         int i;
/*      */         do {
/* 1596 */           node++;
/* 1597 */           i = this.this$0._type2(node);
/*      */           
/* 1599 */           if (-1 == i || !isDescendant(node)) {
/* 1600 */             this._currentNode = -1;
/* 1601 */             return -1;
/*      */           }
/*      */         
/* 1604 */         } while (2 == i || 3 == i || 13 == i);
/*      */       } 
/*      */       
/* 1607 */       this._currentNode = node;
/* 1608 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DTMAxisIterator reset() {
/* 1618 */       boolean temp = ((DTMAxisIteratorBase)this)._isRestartable;
/*      */       
/* 1620 */       ((DTMAxisIteratorBase)this)._isRestartable = true;
/*      */       
/* 1622 */       setStartNode(this.this$0.makeNodeHandle(((DTMAxisIteratorBase)this)._startNode));
/*      */       
/* 1624 */       ((DTMAxisIteratorBase)this)._isRestartable = temp;
/*      */       
/* 1626 */       return (DTMAxisIterator)this;
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
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedDescendantIterator(SAX2DTM2 this$0, int nodeType) {
/* 1647 */       super(this$0); this.this$0 = this$0;
/* 1648 */       this._nodeType = nodeType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next() {
/* 1658 */       int startNode = ((DTMAxisIteratorBase)this)._startNode;
/* 1659 */       if (((DTMAxisIteratorBase)this)._startNode == -1) {
/* 1660 */         return -1;
/*      */       }
/*      */       
/* 1663 */       int node = this._currentNode;
/*      */ 
/*      */       
/* 1666 */       int nodeType = this._nodeType;
/*      */       
/* 1668 */       if (nodeType != 1) {
/*      */         int expType;
/*      */         
/*      */         do {
/* 1672 */           node++;
/* 1673 */           expType = this.this$0._exptype2(node);
/*      */           
/* 1675 */           if (-1 == expType || (this.this$0._parent2(node) < startNode && startNode != node)) {
/* 1676 */             this._currentNode = -1;
/* 1677 */             return -1;
/*      */           }
/*      */         
/* 1680 */         } while (expType != nodeType);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1685 */       else if (startNode == 0) {
/*      */         int i;
/*      */         
/*      */         do {
/* 1689 */           node++;
/* 1690 */           i = this.this$0._exptype2(node);
/*      */           
/* 1692 */           if (-1 == i) {
/* 1693 */             this._currentNode = -1;
/* 1694 */             return -1;
/*      */           }
/*      */         
/* 1697 */         } while (i < 14 || this.this$0.m_extendedTypes[i].getNodeType() != 1);
/*      */       } else {
/*      */         int i;
/*      */ 
/*      */         
/*      */         do {
/* 1703 */           node++;
/* 1704 */           i = this.this$0._exptype2(node);
/*      */           
/* 1706 */           if (-1 == i || (this.this$0._parent2(node) < startNode && startNode != node)) {
/* 1707 */             this._currentNode = -1;
/* 1708 */             return -1;
/*      */           }
/*      */         
/*      */         }
/* 1712 */         while (i < 14 || this.this$0.m_extendedTypes[i].getNodeType() != 1);
/*      */       } 
/*      */       
/* 1715 */       this._currentNode = node;
/* 1716 */       return returnNode(this.this$0.makeNodeHandle(node));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final class TypedSingletonIterator
/*      */     extends DTMDefaultBaseIterators.SingletonIterator
/*      */   {
/*      */     private final int _nodeType;
/*      */ 
/*      */ 
/*      */     
/*      */     private final SAX2DTM2 this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypedSingletonIterator(SAX2DTM2 this$0, int nodeType) {
/* 1736 */       super(this$0); this.this$0 = this$0;
/* 1737 */       this._nodeType = nodeType;
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
/* 1748 */       int result = ((DTMDefaultBaseIterators.InternalAxisIteratorBase)this)._currentNode;
/* 1749 */       if (result == -1) {
/* 1750 */         return -1;
/*      */       }
/* 1752 */       ((DTMDefaultBaseIterators.InternalAxisIteratorBase)this)._currentNode = -1;
/*      */       
/* 1754 */       if (this._nodeType >= 14) {
/* 1755 */         if (this.this$0._exptype2(this.this$0.makeNodeIdentity(result)) == this._nodeType) {
/* 1756 */           return returnNode(result);
/*      */         
/*      */         }
/*      */       }
/* 1760 */       else if (this.this$0._type2(this.this$0.makeNodeIdentity(result)) == this._nodeType) {
/* 1761 */         return returnNode(result);
/*      */       } 
/*      */ 
/*      */       
/* 1765 */       return -1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1803 */   private int m_valueIndex = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private int m_maxNodeIndex;
/*      */ 
/*      */ 
/*      */   
/*      */   protected int m_SHIFT;
/*      */ 
/*      */   
/*      */   protected int m_MASK;
/*      */ 
/*      */   
/*      */   protected int m_blocksize;
/*      */ 
/*      */   
/*      */   protected static final int TEXT_LENGTH_BITS = 10;
/*      */ 
/*      */   
/*      */   protected static final int TEXT_OFFSET_BITS = 21;
/*      */ 
/*      */   
/*      */   protected static final int TEXT_LENGTH_MAX = 1023;
/*      */ 
/*      */   
/*      */   protected static final int TEXT_OFFSET_MAX = 2097151;
/*      */ 
/*      */   
/*      */   protected boolean m_buildIdIndex = true;
/*      */ 
/*      */   
/*      */   private static final String EMPTY_STR = "";
/*      */ 
/*      */   
/* 1838 */   private static final XMLString EMPTY_XML_STR = (XMLString)new XMLStringDefault("");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SAX2DTM2(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing) {
/* 1849 */     this(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, 512, true, true, false);
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
/*      */   public SAX2DTM2(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing, int blocksize, boolean usePrevsib, boolean buildIdIndex, boolean newNameTable) {
/* 1866 */     super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, blocksize, usePrevsib, newNameTable);
/*      */ 
/*      */     
/*      */     int shift;
/*      */     
/* 1871 */     for (shift = 0; (blocksize >>>= 1) != 0; shift++);
/*      */     
/* 1873 */     this.m_blocksize = 1 << shift;
/* 1874 */     this.m_SHIFT = shift;
/* 1875 */     this.m_MASK = this.m_blocksize - 1;
/*      */     
/* 1877 */     this.m_buildIdIndex = buildIdIndex;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1882 */     this.m_values = new Vector(32, 512);
/*      */     
/* 1884 */     this.m_maxNodeIndex = 65536;
/*      */ 
/*      */     
/* 1887 */     this.m_exptype_map0 = ((DTMDefaultBase)this).m_exptype.getMap0();
/* 1888 */     this.m_nextsib_map0 = ((DTMDefaultBase)this).m_nextsib.getMap0();
/* 1889 */     this.m_firstch_map0 = ((DTMDefaultBase)this).m_firstch.getMap0();
/* 1890 */     this.m_parent_map0 = ((DTMDefaultBase)this).m_parent.getMap0();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int _exptype(int identity) {
/* 1901 */     return ((DTMDefaultBase)this).m_exptype.elementAt(identity);
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
/*      */   public final int _exptype2(int identity) {
/* 1924 */     if (identity < this.m_blocksize) {
/* 1925 */       return this.m_exptype_map0[identity];
/*      */     }
/* 1927 */     return this.m_exptype_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
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
/*      */   public final int _nextsib2(int identity) {
/* 1940 */     if (identity < this.m_blocksize) {
/* 1941 */       return this.m_nextsib_map0[identity];
/*      */     }
/* 1943 */     return this.m_nextsib_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
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
/*      */   public final int _firstch2(int identity) {
/* 1956 */     if (identity < this.m_blocksize) {
/* 1957 */       return this.m_firstch_map0[identity];
/*      */     }
/* 1959 */     return this.m_firstch_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
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
/*      */   public final int _parent2(int identity) {
/* 1972 */     if (identity < this.m_blocksize) {
/* 1973 */       return this.m_parent_map0[identity];
/*      */     }
/* 1975 */     return this.m_parent_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
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
/*      */   public final int _type2(int identity) {
/*      */     int i;
/* 1988 */     if (identity < this.m_blocksize) {
/* 1989 */       i = this.m_exptype_map0[identity];
/*      */     } else {
/* 1991 */       i = this.m_exptype_map[identity >>> this.m_SHIFT][identity & this.m_MASK];
/*      */     } 
/* 1993 */     if (-1 != i) {
/* 1994 */       return this.m_extendedTypes[i].getNodeType();
/*      */     }
/* 1996 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getExpandedTypeID2(int nodeHandle) {
/* 2007 */     int nodeID = makeNodeIdentity(nodeHandle);
/*      */ 
/*      */ 
/*      */     
/* 2011 */     if (nodeID != -1) {
/* 2012 */       if (nodeID < this.m_blocksize) {
/* 2013 */         return this.m_exptype_map0[nodeID];
/*      */       }
/* 2015 */       return this.m_exptype_map[nodeID >>> this.m_SHIFT][nodeID & this.m_MASK];
/*      */     } 
/*      */     
/* 2018 */     return -1;
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
/*      */   public final int _exptype2Type(int exptype) {
/* 2031 */     if (-1 != exptype) {
/* 2032 */       return this.m_extendedTypes[exptype].getNodeType();
/*      */     }
/* 2034 */     return -1;
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
/*      */   public int getIdForNamespace(String uri) {
/* 2047 */     int index = this.m_values.indexOf(uri);
/* 2048 */     if (index < 0) {
/*      */       
/* 2050 */       this.m_values.addElement(uri);
/* 2051 */       return this.m_valueIndex++;
/*      */     } 
/*      */     
/* 2054 */     return index;
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
/*      */   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
/* 2086 */     charactersFlush();
/*      */     
/* 2088 */     int exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(uri, localName, 1);
/*      */     
/* 2090 */     int prefixIndex = (qName.length() != localName.length()) ? this.m_valuesOrPrefixes.stringToIndex(qName) : 0;
/*      */ 
/*      */     
/* 2093 */     int elemNode = addNode(1, exName, this.m_parents.peek(), this.m_previous, prefixIndex, true);
/*      */ 
/*      */     
/* 2096 */     if (((DTMDefaultBase)this).m_indexing) {
/* 2097 */       indexNode(exName, elemNode);
/*      */     }
/* 2099 */     this.m_parents.push(elemNode);
/*      */     
/* 2101 */     int startDecls = this.m_contextIndexes.peek();
/* 2102 */     int nDecls = this.m_prefixMappings.size();
/*      */ 
/*      */     
/* 2105 */     if (!this.m_pastFirstElement) {
/*      */ 
/*      */       
/* 2108 */       String prefix = "xml";
/* 2109 */       String declURL = "http://www.w3.org/XML/1998/namespace";
/* 2110 */       exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(null, prefix, 13);
/* 2111 */       this.m_values.addElement(declURL);
/* 2112 */       int val = this.m_valueIndex++;
/* 2113 */       addNode(13, exName, elemNode, -1, val, false);
/*      */       
/* 2115 */       this.m_pastFirstElement = true;
/*      */     } 
/*      */     
/* 2118 */     for (int i = startDecls; i < nDecls; i += 2) {
/*      */       
/* 2120 */       String str = this.m_prefixMappings.elementAt(i);
/*      */       
/* 2122 */       if (str != null) {
/*      */ 
/*      */         
/* 2125 */         String declURL = this.m_prefixMappings.elementAt(i + 1);
/*      */         
/* 2127 */         exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(null, str, 13);
/*      */         
/* 2129 */         this.m_values.addElement(declURL);
/* 2130 */         int val = this.m_valueIndex++;
/*      */         
/* 2132 */         addNode(13, exName, elemNode, -1, val, false);
/*      */       } 
/*      */     } 
/* 2135 */     int n = attributes.getLength();
/*      */     
/* 2137 */     for (int j = 0; j < n; j++) {
/*      */       byte b;
/* 2139 */       String attrUri = attributes.getURI(j);
/* 2140 */       String attrQName = attributes.getQName(j);
/* 2141 */       String valString = attributes.getValue(j);
/*      */ 
/*      */ 
/*      */       
/* 2145 */       String attrLocalName = attributes.getLocalName(j);
/*      */       
/* 2147 */       if (null != attrQName && (attrQName.equals("xmlns") || attrQName.startsWith("xmlns:"))) {
/*      */ 
/*      */ 
/*      */         
/* 2151 */         String str = getPrefix(attrQName, attrUri);
/* 2152 */         if (declAlreadyDeclared(str)) {
/*      */           continue;
/*      */         }
/* 2155 */         b = 13;
/*      */       }
/*      */       else {
/*      */         
/* 2159 */         b = 2;
/*      */         
/* 2161 */         if (this.m_buildIdIndex && attributes.getType(j).equalsIgnoreCase("ID")) {
/* 2162 */           setIDAttribute(valString, elemNode);
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2167 */       if (null == valString) {
/* 2168 */         valString = "";
/*      */       }
/* 2170 */       this.m_values.addElement(valString);
/* 2171 */       int val = this.m_valueIndex++;
/*      */       
/* 2173 */       if (attrLocalName.length() != attrQName.length()) {
/*      */ 
/*      */         
/* 2176 */         prefixIndex = this.m_valuesOrPrefixes.stringToIndex(attrQName);
/*      */         
/* 2178 */         int dataIndex = this.m_data.size();
/*      */         
/* 2180 */         this.m_data.addElement(prefixIndex);
/* 2181 */         this.m_data.addElement(val);
/*      */         
/* 2183 */         val = -dataIndex;
/*      */       } 
/*      */       
/* 2186 */       exName = ((DTMDefaultBase)this).m_expandedNameTable.getExpandedTypeID(attrUri, attrLocalName, b);
/* 2187 */       addNode(b, exName, elemNode, -1, val, false);
/*      */       
/*      */       continue;
/*      */     } 
/* 2191 */     if (null != ((DTMDefaultBase)this).m_wsfilter) {
/*      */       
/* 2193 */       short wsv = ((DTMDefaultBase)this).m_wsfilter.getShouldStripSpace(makeNodeHandle(elemNode), (DTM)this);
/* 2194 */       boolean shouldStrip = (3 == wsv) ? getShouldStripWhitespace() : ((2 == wsv));
/*      */ 
/*      */ 
/*      */       
/* 2198 */       pushShouldStripWhitespace(shouldStrip);
/*      */     } 
/*      */     
/* 2201 */     this.m_previous = -1;
/*      */     
/* 2203 */     this.m_contextIndexes.push(this.m_prefixMappings.size());
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
/*      */   public void endElement(String uri, String localName, String qName) throws SAXException {
/* 2232 */     charactersFlush();
/*      */ 
/*      */ 
/*      */     
/* 2236 */     this.m_contextIndexes.quickPop(1);
/*      */ 
/*      */     
/* 2239 */     int topContextIndex = this.m_contextIndexes.peek();
/* 2240 */     if (topContextIndex != this.m_prefixMappings.size()) {
/* 2241 */       this.m_prefixMappings.setSize(topContextIndex);
/*      */     }
/*      */     
/* 2244 */     this.m_previous = this.m_parents.pop();
/*      */     
/* 2246 */     popShouldStripWhitespace();
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
/*      */   public void comment(char[] ch, int start, int length) throws SAXException {
/* 2264 */     if (this.m_insideDTD) {
/*      */       return;
/*      */     }
/* 2267 */     charactersFlush();
/*      */ 
/*      */ 
/*      */     
/* 2271 */     this.m_values.addElement(new String(ch, start, length));
/* 2272 */     int dataIndex = this.m_valueIndex++;
/*      */     
/* 2274 */     this.m_previous = addNode(8, 8, this.m_parents.peek(), this.m_previous, dataIndex, false);
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
/*      */   public void startDocument() throws SAXException {
/* 2288 */     int doc = addNode(9, 9, -1, -1, 0, true);
/*      */ 
/*      */ 
/*      */     
/* 2292 */     this.m_parents.push(doc);
/* 2293 */     this.m_previous = -1;
/*      */     
/* 2295 */     this.m_contextIndexes.push(this.m_prefixMappings.size());
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
/*      */   public void endDocument() throws SAXException {
/* 2307 */     super.endDocument();
/*      */ 
/*      */ 
/*      */     
/* 2311 */     ((DTMDefaultBase)this).m_exptype.addElement(-1);
/* 2312 */     ((DTMDefaultBase)this).m_parent.addElement(-1);
/* 2313 */     ((DTMDefaultBase)this).m_nextsib.addElement(-1);
/* 2314 */     ((DTMDefaultBase)this).m_firstch.addElement(-1);
/*      */ 
/*      */     
/* 2317 */     this.m_extendedTypes = ((DTMDefaultBase)this).m_expandedNameTable.getExtendedTypes();
/* 2318 */     this.m_exptype_map = ((DTMDefaultBase)this).m_exptype.getMap();
/* 2319 */     this.m_nextsib_map = ((DTMDefaultBase)this).m_nextsib.getMap();
/* 2320 */     this.m_firstch_map = ((DTMDefaultBase)this).m_firstch.getMap();
/* 2321 */     this.m_parent_map = ((DTMDefaultBase)this).m_parent.getMap();
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
/*      */   protected final int addNode(int type, int expandedTypeID, int parentIndex, int previousSibling, int dataOrPrefix, boolean canHaveFirstChild) {
/* 2342 */     int nodeIndex = ((DTMDefaultBase)this).m_size++;
/*      */ 
/*      */ 
/*      */     
/* 2346 */     if (nodeIndex == this.m_maxNodeIndex) {
/*      */       
/* 2348 */       addNewDTMID(nodeIndex);
/* 2349 */       this.m_maxNodeIndex += 65536;
/*      */     } 
/*      */     
/* 2352 */     ((DTMDefaultBase)this).m_firstch.addElement(-1);
/* 2353 */     ((DTMDefaultBase)this).m_nextsib.addElement(-1);
/* 2354 */     ((DTMDefaultBase)this).m_parent.addElement(parentIndex);
/* 2355 */     ((DTMDefaultBase)this).m_exptype.addElement(expandedTypeID);
/* 2356 */     this.m_dataOrQName.addElement(dataOrPrefix);
/*      */     
/* 2358 */     if (((DTMDefaultBase)this).m_prevsib != null) {
/* 2359 */       ((DTMDefaultBase)this).m_prevsib.addElement(previousSibling);
/*      */     }
/*      */     
/* 2362 */     if (this.m_locator != null && this.m_useSourceLocationProperty) {
/* 2363 */       setSourceLocation();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2370 */     switch (type) {
/*      */       
/*      */       case 13:
/* 2373 */         declareNamespaceInContext(parentIndex, nodeIndex);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/* 2387 */         return nodeIndex;
/*      */     } 
/*      */     if (-1 != previousSibling) {
/*      */       ((DTMDefaultBase)this).m_nextsib.setElementAt(nodeIndex, previousSibling);
/*      */     }
/*      */     if (-1 != parentIndex)
/*      */       ((DTMDefaultBase)this).m_firstch.setElementAt(nodeIndex, parentIndex); 
/*      */   }
/*      */   
/*      */   protected final void charactersFlush() {
/* 2397 */     if (this.m_textPendingStart >= 0) {
/*      */       
/* 2399 */       int length = this.m_chars.size() - this.m_textPendingStart;
/* 2400 */       boolean doStrip = false;
/*      */       
/* 2402 */       if (getShouldStripWhitespace())
/*      */       {
/* 2404 */         doStrip = this.m_chars.isWhitespace(this.m_textPendingStart, length);
/*      */       }
/*      */       
/* 2407 */       if (doStrip) {
/* 2408 */         this.m_chars.setLength(this.m_textPendingStart);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 2414 */       else if (length <= 1023 && this.m_textPendingStart <= 2097151) {
/*      */         
/* 2416 */         this.m_previous = addNode(this.m_coalescedTextType, 3, this.m_parents.peek(), this.m_previous, length + (this.m_textPendingStart << 10), false);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2426 */         int dataIndex = this.m_data.size();
/* 2427 */         this.m_previous = addNode(this.m_coalescedTextType, 3, this.m_parents.peek(), this.m_previous, -dataIndex, false);
/*      */ 
/*      */         
/* 2430 */         this.m_data.addElement(this.m_textPendingStart);
/* 2431 */         this.m_data.addElement(length);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2436 */       this.m_textPendingStart = -1;
/* 2437 */       this.m_textType = this.m_coalescedTextType = 3;
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
/*      */   public void processingInstruction(String target, String data) throws SAXException {
/* 2461 */     charactersFlush();
/*      */     
/* 2463 */     int dataIndex = this.m_data.size();
/* 2464 */     this.m_previous = addNode(7, 7, this.m_parents.peek(), this.m_previous, -dataIndex, false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2469 */     this.m_data.addElement(this.m_valuesOrPrefixes.stringToIndex(target));
/* 2470 */     this.m_values.addElement(data);
/* 2471 */     this.m_data.addElement(this.m_valueIndex++);
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
/*      */   public final int getFirstAttribute(int nodeHandle) {
/* 2485 */     int nodeID = makeNodeIdentity(nodeHandle);
/*      */     
/* 2487 */     if (nodeID == -1) {
/* 2488 */       return -1;
/*      */     }
/* 2490 */     int type = _type2(nodeID);
/*      */     
/* 2492 */     if (1 == type) {
/*      */       
/*      */       do {
/*      */ 
/*      */         
/* 2497 */         nodeID++;
/*      */         
/* 2499 */         type = _type2(nodeID);
/*      */         
/* 2501 */         if (type == 2)
/*      */         {
/* 2503 */           return makeNodeHandle(nodeID);
/*      */         }
/* 2505 */       } while (13 == type);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2512 */     return -1;
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
/*      */   protected int getFirstAttributeIdentity(int identity) {
/* 2524 */     if (identity == -1) {
/* 2525 */       return -1;
/*      */     }
/* 2527 */     int type = _type2(identity);
/*      */     
/* 2529 */     if (1 == type) {
/*      */       
/*      */       do {
/*      */ 
/*      */         
/* 2534 */         identity++;
/*      */ 
/*      */         
/* 2537 */         type = _type2(identity);
/*      */         
/* 2539 */         if (type == 2)
/*      */         {
/* 2541 */           return identity;
/*      */         }
/* 2543 */       } while (13 == type);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2550 */     return -1;
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
/*      */   protected int getNextAttributeIdentity(int identity) {
/*      */     int type;
/*      */     do {
/* 2568 */       identity++;
/* 2569 */       type = _type2(identity);
/*      */       
/* 2571 */       if (type == 2)
/* 2572 */         return identity; 
/* 2573 */     } while (type == 13);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2578 */     return -1;
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
/*      */   protected final int getTypedAttribute(int nodeHandle, int attType) {
/* 2595 */     int nodeID = makeNodeIdentity(nodeHandle);
/*      */     
/* 2597 */     if (nodeID == -1) {
/* 2598 */       return -1;
/*      */     }
/* 2600 */     int type = _type2(nodeID);
/*      */     
/* 2602 */     if (1 == type)
/*      */     {
/*      */       while (true) {
/*      */ 
/*      */         
/* 2607 */         nodeID++;
/* 2608 */         int expType = _exptype2(nodeID);
/*      */         
/* 2610 */         if (expType != -1) {
/* 2611 */           type = this.m_extendedTypes[expType].getNodeType();
/*      */         } else {
/* 2613 */           return -1;
/*      */         } 
/* 2615 */         if (type == 2) {
/*      */           
/* 2617 */           if (expType == attType) return makeNodeHandle(nodeID);  continue;
/*      */         } 
/* 2619 */         if (13 != type) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2626 */     return -1;
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
/*      */   public String getLocalName(int nodeHandle) {
/* 2641 */     int expType = _exptype(makeNodeIdentity(nodeHandle));
/*      */     
/* 2643 */     if (expType == 7) {
/*      */       
/* 2645 */       int dataIndex = _dataOrQName(makeNodeIdentity(nodeHandle));
/* 2646 */       dataIndex = this.m_data.elementAt(-dataIndex);
/* 2647 */       return this.m_valuesOrPrefixes.indexToString(dataIndex);
/*      */     } 
/*      */     
/* 2650 */     return ((DTMDefaultBase)this).m_expandedNameTable.getLocalName(expType);
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
/*      */   public final String getNodeNameX(int nodeHandle) {
/* 2665 */     int nodeID = makeNodeIdentity(nodeHandle);
/* 2666 */     int eType = _exptype2(nodeID);
/*      */     
/* 2668 */     if (eType == 7) {
/*      */       
/* 2670 */       int dataIndex = _dataOrQName(nodeID);
/* 2671 */       dataIndex = this.m_data.elementAt(-dataIndex);
/* 2672 */       return this.m_valuesOrPrefixes.indexToString(dataIndex);
/*      */     } 
/*      */     
/* 2675 */     ExtendedType extType = this.m_extendedTypes[eType];
/*      */     
/* 2677 */     if (extType.getNamespace().length() == 0)
/*      */     {
/* 2679 */       return extType.getLocalName();
/*      */     }
/*      */ 
/*      */     
/* 2683 */     int qnameIndex = this.m_dataOrQName.elementAt(nodeID);
/*      */     
/* 2685 */     if (qnameIndex == 0) {
/* 2686 */       return extType.getLocalName();
/*      */     }
/* 2688 */     if (qnameIndex < 0) {
/*      */       
/* 2690 */       qnameIndex = -qnameIndex;
/* 2691 */       qnameIndex = this.m_data.elementAt(qnameIndex);
/*      */     } 
/*      */     
/* 2694 */     return this.m_valuesOrPrefixes.indexToString(qnameIndex);
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
/*      */   public String getNodeName(int nodeHandle) {
/* 2712 */     int nodeID = makeNodeIdentity(nodeHandle);
/* 2713 */     int eType = _exptype2(nodeID);
/*      */     
/* 2715 */     ExtendedType extType = this.m_extendedTypes[eType];
/* 2716 */     if (extType.getNamespace().length() == 0) {
/*      */       
/* 2718 */       int type = extType.getNodeType();
/*      */       
/* 2720 */       String localName = extType.getLocalName();
/* 2721 */       if (type == 13) {
/*      */         
/* 2723 */         if (localName.length() == 0) {
/* 2724 */           return "xmlns";
/*      */         }
/* 2726 */         return "xmlns:" + localName;
/*      */       } 
/* 2728 */       if (type == 7) {
/*      */         
/* 2730 */         int dataIndex = _dataOrQName(nodeID);
/* 2731 */         dataIndex = this.m_data.elementAt(-dataIndex);
/* 2732 */         return this.m_valuesOrPrefixes.indexToString(dataIndex);
/*      */       } 
/* 2734 */       if (localName.length() == 0)
/*      */       {
/* 2736 */         return SAX2DTM.m_fixednames[type];
/*      */       }
/*      */       
/* 2739 */       return localName;
/*      */     } 
/*      */ 
/*      */     
/* 2743 */     int qnameIndex = this.m_dataOrQName.elementAt(nodeID);
/*      */     
/* 2745 */     if (qnameIndex == 0) {
/* 2746 */       return extType.getLocalName();
/*      */     }
/* 2748 */     if (qnameIndex < 0) {
/*      */       
/* 2750 */       qnameIndex = -qnameIndex;
/* 2751 */       qnameIndex = this.m_data.elementAt(qnameIndex);
/*      */     } 
/*      */     
/* 2754 */     return this.m_valuesOrPrefixes.indexToString(qnameIndex);
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
/*      */   public XMLString getStringValue(int nodeHandle) {
/* 2777 */     int identity = makeNodeIdentity(nodeHandle);
/* 2778 */     if (identity == -1) {
/* 2779 */       return EMPTY_XML_STR;
/*      */     }
/* 2781 */     int type = _type2(identity);
/*      */     
/* 2783 */     if (type == 1 || type == 9) {
/*      */       
/* 2785 */       int startNode = identity;
/* 2786 */       identity = _firstch2(identity);
/* 2787 */       if (-1 != identity) {
/*      */         
/* 2789 */         int offset = -1;
/* 2790 */         int length = 0;
/*      */ 
/*      */         
/*      */         do {
/* 2794 */           type = _exptype2(identity);
/*      */           
/* 2796 */           if (type == 3 || type == 4) {
/*      */             
/* 2798 */             int i = this.m_dataOrQName.elementAt(identity);
/* 2799 */             if (i > 0) {
/*      */               
/* 2801 */               if (-1 == offset)
/*      */               {
/* 2803 */                 offset = i >>> 10;
/*      */               }
/*      */               
/* 2806 */               length += i & 0x3FF;
/*      */             }
/*      */             else {
/*      */               
/* 2810 */               if (-1 == offset)
/*      */               {
/* 2812 */                 offset = this.m_data.elementAt(-i);
/*      */               }
/*      */               
/* 2815 */               length += this.m_data.elementAt(-i + 1);
/*      */             } 
/*      */           } 
/*      */           
/* 2819 */           identity++;
/* 2820 */         } while (_parent2(identity) >= startNode);
/*      */         
/* 2822 */         if (length > 0) {
/*      */           
/* 2824 */           if (((DTMDefaultBase)this).m_xstrf != null) {
/* 2825 */             return ((DTMDefaultBase)this).m_xstrf.newstr(this.m_chars, offset, length);
/*      */           }
/* 2827 */           return (XMLString)new XMLStringDefault(this.m_chars.getString(offset, length));
/*      */         } 
/*      */         
/* 2830 */         return EMPTY_XML_STR;
/*      */       } 
/*      */       
/* 2833 */       return EMPTY_XML_STR;
/*      */     } 
/* 2835 */     if (3 == type || 4 == type) {
/*      */       
/* 2837 */       int i = this.m_dataOrQName.elementAt(identity);
/* 2838 */       if (i > 0) {
/*      */         
/* 2840 */         if (((DTMDefaultBase)this).m_xstrf != null) {
/* 2841 */           return ((DTMDefaultBase)this).m_xstrf.newstr(this.m_chars, i >>> 10, i & 0x3FF);
/*      */         }
/*      */         
/* 2844 */         return (XMLString)new XMLStringDefault(this.m_chars.getString(i >>> 10, i & 0x3FF));
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2849 */       if (((DTMDefaultBase)this).m_xstrf != null) {
/* 2850 */         return ((DTMDefaultBase)this).m_xstrf.newstr(this.m_chars, this.m_data.elementAt(-i), this.m_data.elementAt(-i + 1));
/*      */       }
/*      */       
/* 2853 */       return (XMLString)new XMLStringDefault(this.m_chars.getString(this.m_data.elementAt(-i), this.m_data.elementAt(-i + 1)));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2859 */     int dataIndex = this.m_dataOrQName.elementAt(identity);
/*      */     
/* 2861 */     if (dataIndex < 0) {
/*      */       
/* 2863 */       dataIndex = -dataIndex;
/* 2864 */       dataIndex = this.m_data.elementAt(dataIndex + 1);
/*      */     } 
/*      */     
/* 2867 */     if (((DTMDefaultBase)this).m_xstrf != null) {
/* 2868 */       return ((DTMDefaultBase)this).m_xstrf.newstr(this.m_values.elementAt(dataIndex));
/*      */     }
/* 2870 */     return (XMLString)new XMLStringDefault(this.m_values.elementAt(dataIndex));
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
/*      */   public final String getStringValueX(int nodeHandle) {
/* 2890 */     int identity = makeNodeIdentity(nodeHandle);
/* 2891 */     if (identity == -1) {
/* 2892 */       return "";
/*      */     }
/* 2894 */     int type = _type2(identity);
/*      */     
/* 2896 */     if (type == 1 || type == 9) {
/*      */       
/* 2898 */       int startNode = identity;
/* 2899 */       identity = _firstch2(identity);
/* 2900 */       if (-1 != identity) {
/*      */         
/* 2902 */         int offset = -1;
/* 2903 */         int length = 0;
/*      */ 
/*      */         
/*      */         do {
/* 2907 */           type = _exptype2(identity);
/*      */           
/* 2909 */           if (type == 3 || type == 4) {
/*      */             
/* 2911 */             int i = this.m_dataOrQName.elementAt(identity);
/* 2912 */             if (i > 0) {
/*      */               
/* 2914 */               if (-1 == offset)
/*      */               {
/* 2916 */                 offset = i >>> 10;
/*      */               }
/*      */               
/* 2919 */               length += i & 0x3FF;
/*      */             }
/*      */             else {
/*      */               
/* 2923 */               if (-1 == offset)
/*      */               {
/* 2925 */                 offset = this.m_data.elementAt(-i);
/*      */               }
/*      */               
/* 2928 */               length += this.m_data.elementAt(-i + 1);
/*      */             } 
/*      */           } 
/*      */           
/* 2932 */           identity++;
/* 2933 */         } while (_parent2(identity) >= startNode);
/*      */         
/* 2935 */         if (length > 0)
/*      */         {
/* 2937 */           return this.m_chars.getString(offset, length);
/*      */         }
/*      */         
/* 2940 */         return "";
/*      */       } 
/*      */       
/* 2943 */       return "";
/*      */     } 
/* 2945 */     if (3 == type || 4 == type) {
/*      */       
/* 2947 */       int i = this.m_dataOrQName.elementAt(identity);
/* 2948 */       if (i > 0)
/*      */       {
/* 2950 */         return this.m_chars.getString(i >>> 10, i & 0x3FF);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2955 */       return this.m_chars.getString(this.m_data.elementAt(-i), this.m_data.elementAt(-i + 1));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2961 */     int dataIndex = this.m_dataOrQName.elementAt(identity);
/*      */     
/* 2963 */     if (dataIndex < 0) {
/*      */       
/* 2965 */       dataIndex = -dataIndex;
/* 2966 */       dataIndex = this.m_data.elementAt(dataIndex + 1);
/*      */     } 
/*      */     
/* 2969 */     return this.m_values.elementAt(dataIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValue() {
/* 2978 */     int child = _firstch2(0);
/* 2979 */     if (child == -1) return "";
/*      */ 
/*      */     
/* 2982 */     if (_exptype2(child) == 3 && _nextsib2(child) == -1) {
/*      */       
/* 2984 */       int dataIndex = this.m_dataOrQName.elementAt(child);
/* 2985 */       if (dataIndex > 0) {
/* 2986 */         return this.m_chars.getString(dataIndex >>> 10, dataIndex & 0x3FF);
/*      */       }
/* 2988 */       return this.m_chars.getString(this.m_data.elementAt(-dataIndex), this.m_data.elementAt(-dataIndex + 1));
/*      */     } 
/*      */ 
/*      */     
/* 2992 */     return getStringValueX(getDocument());
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
/*      */   public final void dispatchCharactersEvents(int nodeHandle, ContentHandler ch, boolean normalize) throws SAXException {
/* 3020 */     int identity = makeNodeIdentity(nodeHandle);
/*      */     
/* 3022 */     if (identity == -1) {
/*      */       return;
/*      */     }
/* 3025 */     int type = _type2(identity);
/*      */     
/* 3027 */     if (type == 1 || type == 9) {
/*      */       
/* 3029 */       int startNode = identity;
/* 3030 */       identity = _firstch2(identity);
/* 3031 */       if (-1 != identity) {
/*      */         
/* 3033 */         int offset = -1;
/* 3034 */         int length = 0;
/*      */ 
/*      */         
/*      */         do {
/* 3038 */           type = _exptype2(identity);
/*      */           
/* 3040 */           if (type == 3 || type == 4) {
/*      */             
/* 3042 */             int dataIndex = this.m_dataOrQName.elementAt(identity);
/*      */             
/* 3044 */             if (dataIndex > 0) {
/*      */               
/* 3046 */               if (-1 == offset)
/*      */               {
/* 3048 */                 offset = dataIndex >>> 10;
/*      */               }
/*      */               
/* 3051 */               length += dataIndex & 0x3FF;
/*      */             }
/*      */             else {
/*      */               
/* 3055 */               if (-1 == offset)
/*      */               {
/* 3057 */                 offset = this.m_data.elementAt(-dataIndex);
/*      */               }
/*      */               
/* 3060 */               length += this.m_data.elementAt(-dataIndex + 1);
/*      */             } 
/*      */           } 
/*      */           
/* 3064 */           identity++;
/* 3065 */         } while (_parent2(identity) >= startNode);
/*      */         
/* 3067 */         if (length > 0)
/*      */         {
/* 3069 */           if (normalize) {
/* 3070 */             this.m_chars.sendNormalizedSAXcharacters(ch, offset, length);
/*      */           } else {
/* 3072 */             this.m_chars.sendSAXcharacters(ch, offset, length);
/*      */           } 
/*      */         }
/*      */       } 
/* 3076 */     } else if (3 == type || 4 == type) {
/*      */       
/* 3078 */       int dataIndex = this.m_dataOrQName.elementAt(identity);
/*      */       
/* 3080 */       if (dataIndex > 0) {
/*      */         
/* 3082 */         if (normalize) {
/* 3083 */           this.m_chars.sendNormalizedSAXcharacters(ch, dataIndex >>> 10, dataIndex & 0x3FF);
/*      */         } else {
/*      */           
/* 3086 */           this.m_chars.sendSAXcharacters(ch, dataIndex >>> 10, dataIndex & 0x3FF);
/*      */         
/*      */         }
/*      */       
/*      */       }
/* 3091 */       else if (normalize) {
/* 3092 */         this.m_chars.sendNormalizedSAXcharacters(ch, this.m_data.elementAt(-dataIndex), this.m_data.elementAt(-dataIndex + 1));
/*      */       } else {
/*      */         
/* 3095 */         this.m_chars.sendSAXcharacters(ch, this.m_data.elementAt(-dataIndex), this.m_data.elementAt(-dataIndex + 1));
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 3101 */       int dataIndex = this.m_dataOrQName.elementAt(identity);
/*      */       
/* 3103 */       if (dataIndex < 0) {
/*      */         
/* 3105 */         dataIndex = -dataIndex;
/* 3106 */         dataIndex = this.m_data.elementAt(dataIndex + 1);
/*      */       } 
/*      */       
/* 3109 */       String str = this.m_values.elementAt(dataIndex);
/*      */       
/* 3111 */       if (normalize) {
/* 3112 */         FastStringBuffer.sendNormalizedSAXcharacters(str.toCharArray(), 0, str.length(), ch);
/*      */       } else {
/*      */         
/* 3115 */         ch.characters(str.toCharArray(), 0, str.length());
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
/*      */   public String getNodeValue(int nodeHandle) {
/* 3131 */     int identity = makeNodeIdentity(nodeHandle);
/* 3132 */     int type = _type2(identity);
/*      */     
/* 3134 */     if (type == 3 || type == 4) {
/*      */       
/* 3136 */       int i = _dataOrQName(identity);
/* 3137 */       if (i > 0)
/*      */       {
/* 3139 */         return this.m_chars.getString(i >>> 10, i & 0x3FF);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3144 */       return this.m_chars.getString(this.m_data.elementAt(-i), this.m_data.elementAt(-i + 1));
/*      */     } 
/*      */ 
/*      */     
/* 3148 */     if (1 == type || 11 == type || 9 == type)
/*      */     {
/*      */       
/* 3151 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 3155 */     int dataIndex = this.m_dataOrQName.elementAt(identity);
/*      */     
/* 3157 */     if (dataIndex < 0) {
/*      */       
/* 3159 */       dataIndex = -dataIndex;
/* 3160 */       dataIndex = this.m_data.elementAt(dataIndex + 1);
/*      */     } 
/*      */     
/* 3163 */     return this.m_values.elementAt(dataIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void copyTextNode(int nodeID, SerializationHandler handler) throws SAXException {
/* 3173 */     if (nodeID != -1) {
/* 3174 */       int dataIndex = this.m_dataOrQName.elementAt(nodeID);
/* 3175 */       if (dataIndex > 0) {
/* 3176 */         this.m_chars.sendSAXcharacters((ContentHandler)handler, dataIndex >>> 10, dataIndex & 0x3FF);
/*      */       }
/*      */       else {
/*      */         
/* 3180 */         this.m_chars.sendSAXcharacters((ContentHandler)handler, this.m_data.elementAt(-dataIndex), this.m_data.elementAt(-dataIndex + 1));
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
/*      */   protected final String copyElement(int nodeID, int exptype, SerializationHandler handler) throws SAXException {
/*      */     String str1;
/* 3198 */     ExtendedType extType = this.m_extendedTypes[exptype];
/* 3199 */     String uri = extType.getNamespace();
/* 3200 */     String name = extType.getLocalName();
/*      */     
/* 3202 */     if (uri.length() == 0) {
/* 3203 */       handler.startElement(name);
/* 3204 */       return name;
/*      */     } 
/*      */     
/* 3207 */     int qnameIndex = this.m_dataOrQName.elementAt(nodeID);
/*      */     
/* 3209 */     if (qnameIndex == 0) {
/* 3210 */       handler.startElement(name);
/* 3211 */       handler.namespaceAfterStartElement("", uri);
/* 3212 */       return name;
/*      */     } 
/*      */     
/* 3215 */     if (qnameIndex < 0) {
/* 3216 */       qnameIndex = -qnameIndex;
/* 3217 */       qnameIndex = this.m_data.elementAt(qnameIndex);
/*      */     } 
/*      */     
/* 3220 */     String qName = this.m_valuesOrPrefixes.indexToString(qnameIndex);
/* 3221 */     handler.startElement(qName);
/* 3222 */     int prefixIndex = qName.indexOf(':');
/*      */     
/* 3224 */     if (prefixIndex > 0) {
/* 3225 */       str1 = qName.substring(0, prefixIndex);
/*      */     } else {
/*      */       
/* 3228 */       str1 = null;
/*      */     } 
/* 3230 */     handler.namespaceAfterStartElement(str1, uri);
/* 3231 */     return qName;
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
/*      */   protected final void copyNS(int nodeID, SerializationHandler handler, boolean inScope) throws SAXException {
/* 3247 */     int node = makeNodeHandle(nodeID);
/* 3248 */     for (int current = getFirstNamespaceNode(node, inScope); current != -1; 
/* 3249 */       current = getNextNamespaceNode(node, current, inScope)) {
/* 3250 */       handler.namespaceAfterStartElement(getNodeNameX(current), getNodeValue(current));
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
/*      */   protected final void copyAttributes(int nodeID, SerializationHandler handler) throws SAXException {
/* 3264 */     for (int current = getFirstAttributeIdentity(nodeID); current != -1; current = getNextAttributeIdentity(current)) {
/* 3265 */       int eType = _exptype2(current);
/* 3266 */       copyAttribute(current, eType, handler);
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
/*      */   protected final void copyAttribute(int nodeID, int exptype, SerializationHandler handler) throws SAXException {
/* 3291 */     ExtendedType extType = this.m_extendedTypes[exptype];
/* 3292 */     String uri = extType.getNamespace();
/* 3293 */     String localName = extType.getLocalName();
/*      */     
/* 3295 */     String prefix = null;
/* 3296 */     String qname = null;
/* 3297 */     int dataIndex = _dataOrQName(nodeID);
/* 3298 */     int valueIndex = dataIndex;
/* 3299 */     if (uri.length() != 0) {
/* 3300 */       if (dataIndex <= 0) {
/* 3301 */         int prefixIndex = this.m_data.elementAt(-dataIndex);
/* 3302 */         valueIndex = this.m_data.elementAt(-dataIndex + 1);
/* 3303 */         qname = this.m_valuesOrPrefixes.indexToString(prefixIndex);
/* 3304 */         int colonIndex = qname.indexOf(':');
/* 3305 */         if (colonIndex > 0) {
/* 3306 */           prefix = qname.substring(0, colonIndex);
/*      */         }
/*      */       } 
/* 3309 */       handler.namespaceAfterStartElement(prefix, uri);
/*      */     } 
/*      */     
/* 3312 */     String nodeName = (prefix != null) ? qname : localName;
/* 3313 */     String nodeValue = this.m_values.elementAt(valueIndex);
/*      */     
/* 3315 */     handler.addAttribute(nodeName, nodeValue);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/sax2dtm/SAX2DTM2.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */