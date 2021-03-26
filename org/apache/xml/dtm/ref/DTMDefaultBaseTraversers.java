/*      */ package org.apache.xml.dtm.ref;
/*      */ 
/*      */ import javax.xml.transform.Source;
/*      */ import org.apache.xml.dtm.Axis;
/*      */ import org.apache.xml.dtm.DTMAxisTraverser;
/*      */ import org.apache.xml.dtm.DTMException;
/*      */ import org.apache.xml.dtm.DTMManager;
/*      */ import org.apache.xml.dtm.DTMWSFilter;
/*      */ import org.apache.xml.res.XMLMessages;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class DTMDefaultBaseTraversers
/*      */   extends DTMDefaultBase
/*      */ {
/*      */   public DTMDefaultBaseTraversers(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing) {
/*   61 */     super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing);
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
/*      */   public DTMDefaultBaseTraversers(DTMManager mgr, Source source, int dtmIdentity, DTMWSFilter whiteSpaceFilter, XMLStringFactory xstringfactory, boolean doIndexing, int blocksize, boolean usePrevsib, boolean newNameTable) {
/*   90 */     super(mgr, source, dtmIdentity, whiteSpaceFilter, xstringfactory, doIndexing, blocksize, usePrevsib, newNameTable); } public DTMAxisTraverser getAxisTraverser(int axis) { AncestorTraverser ancestorTraverser;
/*      */     AttributeTraverser attributeTraverser;
/*      */     ChildTraverser childTraverser;
/*      */     DescendantTraverser descendantTraverser;
/*      */     FollowingSiblingTraverser followingSiblingTraverser;
/*      */     NamespaceTraverser namespaceTraverser;
/*      */     NamespaceDeclsTraverser namespaceDeclsTraverser;
/*      */     ParentTraverser parentTraverser;
/*      */     PrecedingTraverser precedingTraverser;
/*      */     PrecedingSiblingTraverser precedingSiblingTraverser;
/*      */     SelfTraverser selfTraverser;
/*      */     AllFromRootTraverser allFromRootTraverser;
/*      */     AllFromNodeTraverser allFromNodeTraverser;
/*      */     PrecedingAndAncestorTraverser precedingAndAncestorTraverser;
/*      */     DescendantFromRootTraverser descendantFromRootTraverser;
/*      */     DescendantOrSelfFromRootTraverser descendantOrSelfFromRootTraverser;
/*      */     RootTraverser rootTraverser;
/*  107 */     if (null == this.m_traversers) {
/*      */       
/*  109 */       this.m_traversers = new DTMAxisTraverser[Axis.names.length];
/*  110 */       DTMAxisTraverser traverser = null;
/*      */     }
/*      */     else {
/*      */       
/*  114 */       DTMAxisTraverser dTMAxisTraverser = this.m_traversers[axis];
/*      */       
/*  116 */       if (dTMAxisTraverser != null) {
/*  117 */         return dTMAxisTraverser;
/*      */       }
/*      */     } 
/*  120 */     switch (axis) {
/*      */       
/*      */       case 0:
/*  123 */         ancestorTraverser = new AncestorTraverser();
/*      */         break;
/*      */       case 1:
/*  126 */         ancestorTraverser = new AncestorOrSelfTraverser();
/*      */         break;
/*      */       case 2:
/*  129 */         attributeTraverser = new AttributeTraverser();
/*      */         break;
/*      */       case 3:
/*  132 */         childTraverser = new ChildTraverser();
/*      */         break;
/*      */       case 4:
/*  135 */         descendantTraverser = new DescendantTraverser();
/*      */         break;
/*      */       case 5:
/*  138 */         descendantTraverser = new DescendantOrSelfTraverser();
/*      */         break;
/*      */       case 6:
/*  141 */         descendantTraverser = new FollowingTraverser();
/*      */         break;
/*      */       case 7:
/*  144 */         followingSiblingTraverser = new FollowingSiblingTraverser();
/*      */         break;
/*      */       case 9:
/*  147 */         namespaceTraverser = new NamespaceTraverser();
/*      */         break;
/*      */       case 8:
/*  150 */         namespaceDeclsTraverser = new NamespaceDeclsTraverser();
/*      */         break;
/*      */       case 10:
/*  153 */         parentTraverser = new ParentTraverser();
/*      */         break;
/*      */       case 11:
/*  156 */         precedingTraverser = new PrecedingTraverser();
/*      */         break;
/*      */       case 12:
/*  159 */         precedingSiblingTraverser = new PrecedingSiblingTraverser();
/*      */         break;
/*      */       case 13:
/*  162 */         selfTraverser = new SelfTraverser();
/*      */         break;
/*      */       case 16:
/*  165 */         allFromRootTraverser = new AllFromRootTraverser();
/*      */         break;
/*      */       case 14:
/*  168 */         allFromNodeTraverser = new AllFromNodeTraverser();
/*      */         break;
/*      */       case 15:
/*  171 */         precedingAndAncestorTraverser = new PrecedingAndAncestorTraverser();
/*      */         break;
/*      */       case 17:
/*  174 */         descendantFromRootTraverser = new DescendantFromRootTraverser();
/*      */         break;
/*      */       case 18:
/*  177 */         descendantOrSelfFromRootTraverser = new DescendantOrSelfFromRootTraverser();
/*      */         break;
/*      */       case 19:
/*  180 */         rootTraverser = new RootTraverser();
/*      */         break;
/*      */       case 20:
/*  183 */         return null;
/*      */       default:
/*  185 */         throw new DTMException(XMLMessages.createXMLMessage("ER_UNKNOWN_AXIS_TYPE", new Object[] { Integer.toString(axis) }));
/*      */     } 
/*      */     
/*  188 */     if (null == rootTraverser) {
/*  189 */       throw new DTMException(XMLMessages.createXMLMessage("ER_AXIS_TRAVERSER_NOT_SUPPORTED", new Object[] { Axis.names[axis] }));
/*      */     }
/*      */ 
/*      */     
/*  193 */     this.m_traversers[axis] = rootTraverser;
/*      */     
/*  195 */     return rootTraverser; }
/*      */ 
/*      */   
/*      */   private class AncestorTraverser extends DTMAxisTraverser { private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private AncestorTraverser(DTMDefaultBaseTraversers this$0) {
/*  201 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int next(int context, int current) {
/*  214 */       return DTMDefaultBaseTraversers.this.getParent(current);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/*  230 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current);
/*      */       
/*  232 */       while (-1 != (current = DTMDefaultBaseTraversers.this.m_parent.elementAt(current))) {
/*      */         
/*  234 */         if (DTMDefaultBaseTraversers.this.m_exptype.elementAt(current) == expandedTypeID) {
/*  235 */           return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */         }
/*      */       } 
/*  238 */       return -1;
/*      */     } }
/*      */   
/*      */   private class AncestorOrSelfTraverser extends AncestorTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private AncestorOrSelfTraverser(DTMDefaultBaseTraversers this$0) {
/*  245 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int first(int context) {
/*  259 */       return context;
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
/*      */     public int first(int context, int expandedTypeID) {
/*  276 */       return (DTMDefaultBaseTraversers.this.getExpandedTypeID(context) == expandedTypeID) ? context : next(context, context, expandedTypeID);
/*      */     }
/*      */   }
/*      */   
/*      */   private class AttributeTraverser extends DTMAxisTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private AttributeTraverser(DTMDefaultBaseTraversers this$0) {
/*  284 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int next(int context, int current) {
/*  297 */       return (context == current) ? DTMDefaultBaseTraversers.this.getFirstAttribute(context) : DTMDefaultBaseTraversers.this.getNextAttribute(current);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/*  314 */       current = (context == current) ? DTMDefaultBaseTraversers.this.getFirstAttribute(context) : DTMDefaultBaseTraversers.this.getNextAttribute(current);
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/*  319 */         if (DTMDefaultBaseTraversers.this.getExpandedTypeID(current) == expandedTypeID) {
/*  320 */           return current;
/*      */         }
/*  322 */       } while (-1 != (current = DTMDefaultBaseTraversers.this.getNextAttribute(current)));
/*      */       
/*  324 */       return -1;
/*      */     } }
/*      */   
/*      */   private class ChildTraverser extends DTMAxisTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private ChildTraverser(DTMDefaultBaseTraversers this$0) {
/*  331 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     protected int getNextIndexed(int axisRoot, int nextPotential, int expandedTypeID) {
/*  350 */       int nsIndex = DTMDefaultBaseTraversers.this.m_expandedNameTable.getNamespaceID(expandedTypeID);
/*  351 */       int lnIndex = DTMDefaultBaseTraversers.this.m_expandedNameTable.getLocalNameID(expandedTypeID);
/*      */ 
/*      */       
/*      */       while (true) {
/*  355 */         int nextID = DTMDefaultBaseTraversers.this.findElementFromIndex(nsIndex, lnIndex, nextPotential);
/*      */         
/*  357 */         if (-2 != nextID) {
/*      */           
/*  359 */           int parentID = DTMDefaultBaseTraversers.this.m_parent.elementAt(nextID);
/*      */ 
/*      */           
/*  362 */           if (parentID == axisRoot) {
/*  363 */             return nextID;
/*      */           }
/*      */ 
/*      */           
/*  367 */           if (parentID < axisRoot) {
/*  368 */             return -1;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           do {
/*  377 */             parentID = DTMDefaultBaseTraversers.this.m_parent.elementAt(parentID);
/*  378 */             if (parentID < axisRoot) {
/*  379 */               return -1;
/*      */             }
/*  381 */           } while (parentID > axisRoot);
/*      */ 
/*      */           
/*  384 */           nextPotential = nextID + 1;
/*      */           
/*      */           continue;
/*      */         } 
/*  388 */         DTMDefaultBaseTraversers.this.nextNode();
/*      */         
/*  390 */         if (DTMDefaultBaseTraversers.this.m_nextsib.elementAt(axisRoot) != -2) {
/*      */           break;
/*      */         }
/*      */       } 
/*  394 */       return -1;
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
/*      */     public int first(int context) {
/*  411 */       return DTMDefaultBaseTraversers.this.getFirstChild(context);
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
/*      */     public int first(int context, int expandedTypeID) {
/*  432 */       int identity = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */       
/*  434 */       int firstMatch = getNextIndexed(identity, DTMDefaultBaseTraversers.this._firstch(identity), expandedTypeID);
/*      */ 
/*      */       
/*  437 */       return DTMDefaultBaseTraversers.this.makeNodeHandle(firstMatch);
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
/*      */     
/*      */     public int next(int context, int current) {
/*  463 */       return DTMDefaultBaseTraversers.this.getNextSibling(current);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/*  479 */       current = DTMDefaultBaseTraversers.this._nextsib(DTMDefaultBaseTraversers.this.makeNodeIdentity(current));
/*  480 */       for (; -1 != current; 
/*  481 */         current = DTMDefaultBaseTraversers.this._nextsib(current)) {
/*      */         
/*  483 */         if (DTMDefaultBaseTraversers.this.m_exptype.elementAt(current) == expandedTypeID) {
/*  484 */           return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */         }
/*      */       } 
/*  487 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   private abstract class IndexedDTMAxisTraverser extends DTMAxisTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private IndexedDTMAxisTraverser(DTMDefaultBaseTraversers this$0) {
/*  495 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     protected final boolean isIndexed(int expandedTypeID) {
/*  510 */       return (DTMDefaultBaseTraversers.this.m_indexing && 1 == DTMDefaultBaseTraversers.this.m_expandedNameTable.getType(expandedTypeID));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int getNextIndexed(int axisRoot, int nextPotential, int expandedTypeID) {
/*  554 */       int nsIndex = DTMDefaultBaseTraversers.this.m_expandedNameTable.getNamespaceID(expandedTypeID);
/*  555 */       int lnIndex = DTMDefaultBaseTraversers.this.m_expandedNameTable.getLocalNameID(expandedTypeID);
/*      */ 
/*      */       
/*      */       while (true) {
/*  559 */         int next = DTMDefaultBaseTraversers.this.findElementFromIndex(nsIndex, lnIndex, nextPotential);
/*      */         
/*  561 */         if (-2 != next) {
/*      */           
/*  563 */           if (isAfterAxis(axisRoot, next)) {
/*  564 */             return -1;
/*      */           }
/*      */           
/*  567 */           return next;
/*      */         } 
/*  569 */         if (axisHasBeenProcessed(axisRoot)) {
/*      */           break;
/*      */         }
/*  572 */         DTMDefaultBaseTraversers.this.nextNode();
/*      */       } 
/*      */       
/*  575 */       return -1;
/*      */     }
/*      */     protected abstract boolean isAfterAxis(int param1Int1, int param1Int2);
/*      */     protected abstract boolean axisHasBeenProcessed(int param1Int); }
/*      */   private class DescendantTraverser extends IndexedDTMAxisTraverser { private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private DescendantTraverser(DTMDefaultBaseTraversers this$0) {
/*  582 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     protected int getFirstPotential(int identity) {
/*  594 */       return identity + 1;
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
/*      */     protected boolean axisHasBeenProcessed(int axisRoot) {
/*  607 */       return (DTMDefaultBaseTraversers.this.m_nextsib.elementAt(axisRoot) != -2);
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
/*      */     protected int getSubtreeRoot(int handle) {
/*  620 */       return DTMDefaultBaseTraversers.this.makeNodeIdentity(handle);
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
/*      */     protected boolean isDescendant(int subtreeRootIdentity, int identity) {
/*  637 */       return (DTMDefaultBaseTraversers.this._parent(identity) >= subtreeRootIdentity);
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
/*      */     protected boolean isAfterAxis(int axisRoot, int identity) {
/*      */       do {
/*  657 */         if (identity == axisRoot)
/*  658 */           return false; 
/*  659 */         identity = DTMDefaultBaseTraversers.this.m_parent.elementAt(identity);
/*      */       }
/*  661 */       while (identity >= axisRoot);
/*      */       
/*  663 */       return true;
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
/*      */     public int first(int context, int expandedTypeID) {
/*  683 */       if (isIndexed(expandedTypeID)) {
/*      */         
/*  685 */         int identity = getSubtreeRoot(context);
/*  686 */         int firstPotential = getFirstPotential(identity);
/*      */         
/*  688 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(getNextIndexed(identity, firstPotential, expandedTypeID));
/*      */       } 
/*      */       
/*  691 */       return next(context, context, expandedTypeID);
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
/*      */     public int next(int context, int current) {
/*  705 */       int subtreeRootIdent = getSubtreeRoot(context);
/*      */       
/*  707 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) + 1;
/*      */       while (true) {
/*  709 */         int type = DTMDefaultBaseTraversers.this._type(current);
/*      */         
/*  711 */         if (!isDescendant(subtreeRootIdent, current)) {
/*  712 */           return -1;
/*      */         }
/*  714 */         if (2 == type || 13 == type) {
/*      */           current++; continue;
/*      */         } 
/*  717 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int next(int context, int current, int expandedTypeID) {
/*  734 */       int subtreeRootIdent = getSubtreeRoot(context);
/*      */       
/*  736 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) + 1;
/*      */       
/*  738 */       if (isIndexed(expandedTypeID))
/*      */       {
/*  740 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(getNextIndexed(subtreeRootIdent, current, expandedTypeID));
/*      */       }
/*      */ 
/*      */       
/*      */       while (true) {
/*  745 */         int exptype = DTMDefaultBaseTraversers.this._exptype(current);
/*      */         
/*  747 */         if (!isDescendant(subtreeRootIdent, current)) {
/*  748 */           return -1;
/*      */         }
/*  750 */         if (exptype != expandedTypeID) {
/*      */           current++; continue;
/*      */         } 
/*  753 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */       } 
/*      */     } }
/*      */ 
/*      */   
/*      */   private class DescendantOrSelfTraverser extends DescendantTraverser { private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private DescendantOrSelfTraverser(DTMDefaultBaseTraversers this$0) {
/*  761 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     protected int getFirstPotential(int identity) {
/*  774 */       return identity;
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
/*      */     public int first(int context) {
/*  788 */       return context;
/*      */     } }
/*      */   
/*      */   private class AllFromNodeTraverser extends DescendantOrSelfTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private AllFromNodeTraverser(DTMDefaultBaseTraversers this$0) {
/*  795 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int next(int context, int current) {
/*  809 */       int subtreeRootIdent = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */       
/*  811 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) + 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  818 */       DTMDefaultBaseTraversers.this._exptype(current);
/*      */       
/*  820 */       if (!isDescendant(subtreeRootIdent, current)) {
/*  821 */         return -1;
/*      */       }
/*  823 */       return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */     }
/*      */   }
/*      */   
/*      */   private class FollowingTraverser extends DescendantTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private FollowingTraverser(DTMDefaultBaseTraversers this$0) {
/*  831 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int first(int context) {
/*      */       int i;
/*  844 */       context = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */ 
/*      */       
/*  847 */       int type = DTMDefaultBaseTraversers.this._type(context);
/*      */       
/*  849 */       if (2 == type || 13 == type) {
/*      */         
/*  851 */         context = DTMDefaultBaseTraversers.this._parent(context);
/*  852 */         i = DTMDefaultBaseTraversers.this._firstch(context);
/*      */         
/*  854 */         if (-1 != i) {
/*  855 */           return DTMDefaultBaseTraversers.this.makeNodeHandle(i);
/*      */         }
/*      */       } 
/*      */       
/*      */       do {
/*  860 */         i = DTMDefaultBaseTraversers.this._nextsib(context);
/*      */         
/*  862 */         if (-1 != i)
/*  863 */           continue;  context = DTMDefaultBaseTraversers.this._parent(context);
/*      */       }
/*  865 */       while (-1 == i && -1 != context);
/*      */       
/*  867 */       return DTMDefaultBaseTraversers.this.makeNodeHandle(i);
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
/*      */     public int first(int context, int expandedTypeID) {
/*  884 */       int i, type = DTMDefaultBaseTraversers.this.getNodeType(context);
/*      */       
/*  886 */       if (2 == type || 13 == type) {
/*      */         
/*  888 */         context = DTMDefaultBaseTraversers.this.getParent(context);
/*  889 */         i = DTMDefaultBaseTraversers.this.getFirstChild(context);
/*      */         
/*  891 */         if (-1 != i) {
/*      */           
/*  893 */           if (DTMDefaultBaseTraversers.this.getExpandedTypeID(i) == expandedTypeID) {
/*  894 */             return i;
/*      */           }
/*  896 */           return next(context, i, expandedTypeID);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*      */       do {
/*  902 */         i = DTMDefaultBaseTraversers.this.getNextSibling(context);
/*      */         
/*  904 */         if (-1 == i) {
/*  905 */           context = DTMDefaultBaseTraversers.this.getParent(context);
/*      */         } else {
/*      */           
/*  908 */           if (DTMDefaultBaseTraversers.this.getExpandedTypeID(i) == expandedTypeID) {
/*  909 */             return i;
/*      */           }
/*  911 */           return next(context, i, expandedTypeID);
/*      */         }
/*      */       
/*  914 */       } while (-1 == i && -1 != context);
/*      */       
/*  916 */       return i;
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
/*      */     public int next(int context, int current) {
/*  930 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current);
/*      */ 
/*      */       
/*      */       while (true) {
/*  934 */         current++;
/*      */ 
/*      */         
/*  937 */         int type = DTMDefaultBaseTraversers.this._type(current);
/*      */         
/*  939 */         if (-1 == type) {
/*  940 */           return -1;
/*      */         }
/*  942 */         if (2 == type || 13 == type)
/*      */           continue;  break;
/*      */       } 
/*  945 */       return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/*  962 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current);
/*      */ 
/*      */       
/*      */       while (true) {
/*  966 */         current++;
/*      */         
/*  968 */         int etype = DTMDefaultBaseTraversers.this._exptype(current);
/*      */         
/*  970 */         if (-1 == etype) {
/*  971 */           return -1;
/*      */         }
/*  973 */         if (etype != expandedTypeID)
/*      */           continue;  break;
/*      */       } 
/*  976 */       return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */     }
/*      */   }
/*      */   
/*      */   private class FollowingSiblingTraverser extends DTMAxisTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private FollowingSiblingTraverser(DTMDefaultBaseTraversers this$0) {
/*  984 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int next(int context, int current) {
/*  997 */       return DTMDefaultBaseTraversers.this.getNextSibling(current);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1013 */       while (-1 != (current = DTMDefaultBaseTraversers.this.getNextSibling(current))) {
/*      */         
/* 1015 */         if (DTMDefaultBaseTraversers.this.getExpandedTypeID(current) == expandedTypeID) {
/* 1016 */           return current;
/*      */         }
/*      */       } 
/* 1019 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   private class NamespaceDeclsTraverser extends DTMAxisTraverser { private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private NamespaceDeclsTraverser(DTMDefaultBaseTraversers this$0) {
/* 1026 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int next(int context, int current) {
/* 1040 */       return (context == current) ? DTMDefaultBaseTraversers.this.getFirstNamespaceNode(context, false) : DTMDefaultBaseTraversers.this.getNextNamespaceNode(context, current, false);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1058 */       current = (context == current) ? DTMDefaultBaseTraversers.this.getFirstNamespaceNode(context, false) : DTMDefaultBaseTraversers.this.getNextNamespaceNode(context, current, false);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/* 1064 */         if (DTMDefaultBaseTraversers.this.getExpandedTypeID(current) == expandedTypeID) {
/* 1065 */           return current;
/*      */         }
/*      */       }
/* 1068 */       while (-1 != (current = DTMDefaultBaseTraversers.this.getNextNamespaceNode(context, current, false)));
/*      */       
/* 1070 */       return -1;
/*      */     } }
/*      */   
/*      */   private class NamespaceTraverser extends DTMAxisTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private NamespaceTraverser(DTMDefaultBaseTraversers this$0) {
/* 1077 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int next(int context, int current) {
/* 1091 */       return (context == current) ? DTMDefaultBaseTraversers.this.getFirstNamespaceNode(context, true) : DTMDefaultBaseTraversers.this.getNextNamespaceNode(context, current, true);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1109 */       current = (context == current) ? DTMDefaultBaseTraversers.this.getFirstNamespaceNode(context, true) : DTMDefaultBaseTraversers.this.getNextNamespaceNode(context, current, true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/* 1115 */         if (DTMDefaultBaseTraversers.this.getExpandedTypeID(current) == expandedTypeID) {
/* 1116 */           return current;
/*      */         }
/*      */       }
/* 1119 */       while (-1 != (current = DTMDefaultBaseTraversers.this.getNextNamespaceNode(context, current, true)));
/*      */       
/* 1121 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   private class ParentTraverser extends DTMAxisTraverser { private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private ParentTraverser(DTMDefaultBaseTraversers this$0) {
/* 1128 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int first(int context) {
/* 1144 */       return DTMDefaultBaseTraversers.this.getParent(context);
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
/*      */     public int first(int current, int expandedTypeID) {
/* 1164 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current);
/*      */       
/* 1166 */       while (-1 != (current = DTMDefaultBaseTraversers.this.m_parent.elementAt(current))) {
/*      */         
/* 1168 */         if (DTMDefaultBaseTraversers.this.m_exptype.elementAt(current) == expandedTypeID) {
/* 1169 */           return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */         }
/*      */       } 
/* 1172 */       return -1;
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
/*      */     public int next(int context, int current) {
/* 1187 */       return -1;
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1205 */       return -1;
/*      */     } }
/*      */   
/*      */   private class PrecedingTraverser extends DTMAxisTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private PrecedingTraverser(DTMDefaultBaseTraversers this$0) {
/* 1212 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     protected boolean isAncestor(int contextIdent, int currentIdent) {
/* 1228 */       for (contextIdent = DTMDefaultBaseTraversers.this.m_parent.elementAt(contextIdent); -1 != contextIdent; 
/* 1229 */         contextIdent = DTMDefaultBaseTraversers.this.m_parent.elementAt(contextIdent)) {
/*      */         
/* 1231 */         if (contextIdent == currentIdent) {
/* 1232 */           return true;
/*      */         }
/*      */       } 
/* 1235 */       return false;
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
/*      */     public int next(int context, int current) {
/* 1249 */       int subtreeRootIdent = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */       
/* 1251 */       for (current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) - 1; current >= 0; ) {
/*      */         
/* 1253 */         short type = DTMDefaultBaseTraversers.this._type(current);
/*      */         
/* 1255 */         if (2 == type || 13 == type || isAncestor(subtreeRootIdent, current)) {
/*      */           current--;
/*      */           continue;
/*      */         } 
/* 1259 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */       } 
/*      */       
/* 1262 */       return -1;
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1278 */       int subtreeRootIdent = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */       
/* 1280 */       for (current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) - 1; current >= 0; ) {
/*      */         
/* 1282 */         int exptype = DTMDefaultBaseTraversers.this.m_exptype.elementAt(current);
/*      */         
/* 1284 */         if (exptype != expandedTypeID || isAncestor(subtreeRootIdent, current)) {
/*      */           current--;
/*      */           continue;
/*      */         } 
/* 1288 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */       } 
/*      */       
/* 1291 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   private class PrecedingAndAncestorTraverser extends DTMAxisTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private PrecedingAndAncestorTraverser(DTMDefaultBaseTraversers this$0) {
/* 1299 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int next(int context, int current) {
/* 1313 */       int subtreeRootIdent = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */       
/* 1315 */       for (current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) - 1; current >= 0; ) {
/*      */         
/* 1317 */         short type = DTMDefaultBaseTraversers.this._type(current);
/*      */         
/* 1319 */         if (2 == type || 13 == type) {
/*      */           current--; continue;
/*      */         } 
/* 1322 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */       } 
/*      */       
/* 1325 */       return -1;
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1341 */       int subtreeRootIdent = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */       
/* 1343 */       for (current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) - 1; current >= 0; ) {
/*      */         
/* 1345 */         int exptype = DTMDefaultBaseTraversers.this.m_exptype.elementAt(current);
/*      */         
/* 1347 */         if (exptype != expandedTypeID) {
/*      */           current--; continue;
/*      */         } 
/* 1350 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */       } 
/*      */       
/* 1353 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   private class PrecedingSiblingTraverser extends DTMAxisTraverser { private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private PrecedingSiblingTraverser(DTMDefaultBaseTraversers this$0) {
/* 1360 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int next(int context, int current) {
/* 1373 */       return DTMDefaultBaseTraversers.this.getPreviousSibling(current);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1389 */       while (-1 != (current = DTMDefaultBaseTraversers.this.getPreviousSibling(current))) {
/*      */         
/* 1391 */         if (DTMDefaultBaseTraversers.this.getExpandedTypeID(current) == expandedTypeID) {
/* 1392 */           return current;
/*      */         }
/*      */       } 
/* 1395 */       return -1;
/*      */     } }
/*      */   
/*      */   private class SelfTraverser extends DTMAxisTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private SelfTraverser(DTMDefaultBaseTraversers this$0) {
/* 1402 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int first(int context) {
/* 1416 */       return context;
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
/*      */     public int first(int context, int expandedTypeID) {
/* 1433 */       return (DTMDefaultBaseTraversers.this.getExpandedTypeID(context) == expandedTypeID) ? context : -1;
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
/*      */     public int next(int context, int current) {
/* 1446 */       return -1;
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1461 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   private class AllFromRootTraverser extends AllFromNodeTraverser { private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private AllFromRootTraverser(DTMDefaultBaseTraversers this$0) {
/* 1468 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int first(int context) {
/* 1480 */       return DTMDefaultBaseTraversers.this.getDocumentRoot(context);
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
/*      */     public int first(int context, int expandedTypeID) {
/* 1493 */       return (DTMDefaultBaseTraversers.this.getExpandedTypeID(DTMDefaultBaseTraversers.this.getDocumentRoot(context)) == expandedTypeID) ? context : next(context, context, expandedTypeID);
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
/*      */     public int next(int context, int current) {
/* 1508 */       int subtreeRootIdent = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */       
/* 1510 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) + 1;
/*      */ 
/*      */       
/* 1513 */       int type = DTMDefaultBaseTraversers.this._type(current);
/* 1514 */       if (type == -1) {
/* 1515 */         return -1;
/*      */       }
/* 1517 */       return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1534 */       int subtreeRootIdent = DTMDefaultBaseTraversers.this.makeNodeIdentity(context);
/*      */       
/* 1536 */       current = DTMDefaultBaseTraversers.this.makeNodeIdentity(current) + 1;
/*      */       while (true) {
/* 1538 */         int exptype = DTMDefaultBaseTraversers.this._exptype(current);
/*      */         
/* 1540 */         if (exptype == -1) {
/* 1541 */           return -1;
/*      */         }
/* 1543 */         if (exptype != expandedTypeID) {
/*      */           current++; continue;
/*      */         } 
/* 1546 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(current);
/*      */       } 
/*      */     } }
/*      */   
/*      */   private class RootTraverser extends AllFromRootTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private RootTraverser(DTMDefaultBaseTraversers this$0) {
/* 1554 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     public int first(int context, int expandedTypeID) {
/* 1567 */       int root = DTMDefaultBaseTraversers.this.getDocumentRoot(context);
/* 1568 */       return (DTMDefaultBaseTraversers.this.getExpandedTypeID(root) == expandedTypeID) ? root : -1;
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
/*      */     public int next(int context, int current) {
/* 1582 */       return -1;
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
/*      */     public int next(int context, int current, int expandedTypeID) {
/* 1597 */       return -1;
/*      */     }
/*      */   }
/*      */   
/*      */   private class DescendantOrSelfFromRootTraverser extends DescendantTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private DescendantOrSelfFromRootTraverser(DTMDefaultBaseTraversers this$0) {
/* 1605 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     protected int getFirstPotential(int identity) {
/* 1618 */       return identity;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int getSubtreeRoot(int handle) {
/* 1629 */       return DTMDefaultBaseTraversers.this.makeNodeIdentity(DTMDefaultBaseTraversers.this.getDocument());
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
/*      */     public int first(int context) {
/* 1641 */       return DTMDefaultBaseTraversers.this.getDocumentRoot(context);
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
/*      */     public int first(int context, int expandedTypeID) {
/* 1660 */       if (isIndexed(expandedTypeID)) {
/*      */         
/* 1662 */         int identity = 0;
/* 1663 */         int firstPotential = getFirstPotential(identity);
/*      */         
/* 1665 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(getNextIndexed(identity, firstPotential, expandedTypeID));
/*      */       } 
/*      */       
/* 1668 */       int root = first(context);
/* 1669 */       return next(root, root, expandedTypeID);
/*      */     }
/*      */   }
/*      */   
/*      */   private class DescendantFromRootTraverser extends DescendantTraverser {
/*      */     private final DTMDefaultBaseTraversers this$0;
/*      */     
/*      */     private DescendantFromRootTraverser(DTMDefaultBaseTraversers this$0) {
/* 1677 */       DTMDefaultBaseTraversers.this = DTMDefaultBaseTraversers.this;
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
/*      */     protected int getFirstPotential(int identity) {
/* 1690 */       return DTMDefaultBaseTraversers.this._firstch(0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int getSubtreeRoot(int handle) {
/* 1700 */       return 0;
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
/*      */     public int first(int context) {
/* 1712 */       return DTMDefaultBaseTraversers.this.makeNodeHandle(DTMDefaultBaseTraversers.this._firstch(0));
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
/*      */     public int first(int context, int expandedTypeID) {
/* 1731 */       if (isIndexed(expandedTypeID)) {
/*      */         
/* 1733 */         int identity = 0;
/* 1734 */         int firstPotential = getFirstPotential(identity);
/*      */         
/* 1736 */         return DTMDefaultBaseTraversers.this.makeNodeHandle(getNextIndexed(identity, firstPotential, expandedTypeID));
/*      */       } 
/*      */       
/* 1739 */       int root = DTMDefaultBaseTraversers.this.getDocumentRoot(context);
/* 1740 */       return next(root, root, expandedTypeID);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMDefaultBaseTraversers.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */