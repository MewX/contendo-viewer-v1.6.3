/*      */ package org.apache.batik.apps.svgbrowser;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.datatransfer.DataFlavor;
/*      */ import java.awt.datatransfer.Transferable;
/*      */ import java.awt.datatransfer.UnsupportedFlavorException;
/*      */ import java.awt.dnd.Autoscroll;
/*      */ import java.awt.dnd.DragGestureEvent;
/*      */ import java.awt.dnd.DragGestureListener;
/*      */ import java.awt.dnd.DragGestureRecognizer;
/*      */ import java.awt.dnd.DragSource;
/*      */ import java.awt.dnd.DragSourceDragEvent;
/*      */ import java.awt.dnd.DragSourceDropEvent;
/*      */ import java.awt.dnd.DragSourceEvent;
/*      */ import java.awt.dnd.DragSourceListener;
/*      */ import java.awt.dnd.DropTarget;
/*      */ import java.awt.dnd.DropTargetContext;
/*      */ import java.awt.dnd.DropTargetDragEvent;
/*      */ import java.awt.dnd.DropTargetDropEvent;
/*      */ import java.awt.dnd.DropTargetEvent;
/*      */ import java.awt.dnd.DropTargetListener;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.EventListener;
/*      */ import java.util.EventObject;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRootPane;
/*      */ import javax.swing.JTree;
/*      */ import javax.swing.JViewport;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.Timer;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import javax.swing.tree.DefaultMutableTreeNode;
/*      */ import javax.swing.tree.TreeNode;
/*      */ import javax.swing.tree.TreePath;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.w3c.dom.Node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DOMDocumentTree
/*      */   extends JTree
/*      */   implements Autoscroll
/*      */ {
/*   77 */   protected EventListenerList eventListeners = new EventListenerList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   82 */   protected Insets autoscrollInsets = new Insets(20, 20, 20, 20);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   87 */   protected Insets scrollUnits = new Insets(25, 25, 25, 25);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected DOMDocumentTreeController controller;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMDocumentTree(TreeNode root, DOMDocumentTreeController controller) {
/*  103 */     super(root);
/*  104 */     this.controller = controller;
/*  105 */     new TreeDragSource(this, 3);
/*  106 */     new DropTarget(this, new TreeDropTargetListener(this));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class TreeDragSource
/*      */     implements DragGestureListener, DragSourceListener
/*      */   {
/*      */     protected DragSource source;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected DragGestureRecognizer recognizer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected DOMDocumentTree.TransferableTreeNode transferable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected DOMDocumentTree sourceTree;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TreeDragSource(DOMDocumentTree tree, int actions) {
/*  146 */       this.sourceTree = tree;
/*  147 */       this.source = new DragSource();
/*  148 */       this.recognizer = this.source.createDefaultDragGestureRecognizer(this.sourceTree, actions, this);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void dragGestureRecognized(DragGestureEvent dge) {
/*  154 */       if (!DOMDocumentTree.this.controller.isDNDSupported()) {
/*      */         return;
/*      */       }
/*  157 */       TreePath[] paths = this.sourceTree.getSelectionPaths();
/*      */       
/*  159 */       if (paths == null) {
/*      */         return;
/*      */       }
/*  162 */       ArrayList<Node> nodeList = new ArrayList();
/*  163 */       for (TreePath path : paths) {
/*      */         
/*  165 */         if (path.getPathCount() > 1) {
/*  166 */           DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
/*      */           
/*  168 */           Node associatedNode = DOMDocumentTree.this.getDomNodeFromTreeNode(node);
/*  169 */           if (associatedNode != null) {
/*  170 */             nodeList.add(associatedNode);
/*      */           }
/*      */         } 
/*      */       } 
/*  174 */       if (nodeList.isEmpty()) {
/*      */         return;
/*      */       }
/*  177 */       this.transferable = new DOMDocumentTree.TransferableTreeNode(new DOMDocumentTree.TransferData(nodeList));
/*      */ 
/*      */       
/*  180 */       this.source.startDrag(dge, null, this.transferable, this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dragEnter(DragSourceDragEvent dsde) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dragExit(DragSourceEvent dse) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dragOver(DragSourceDragEvent dsde) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dropActionChanged(DragSourceDragEvent dsde) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dragDropEnd(DragSourceDropEvent dsde) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class TreeDropTargetListener
/*      */     implements DropTargetListener
/*      */   {
/*      */     private static final int BEFORE = 1;
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int AFTER = 2;
/*      */ 
/*      */ 
/*      */     
/*      */     private static final int CURRENT = 3;
/*      */ 
/*      */ 
/*      */     
/*      */     private DOMDocumentTree.TransferData transferData;
/*      */ 
/*      */     
/*      */     private Component originalGlassPane;
/*      */ 
/*      */     
/*  233 */     private int visualTipOffset = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  238 */     private int visualTipThickness = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int positionIndicator;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Point startPoint;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Point endPoint;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  259 */     protected JPanel visualTipGlassPane = new JPanel() {
/*      */         public void paint(Graphics g) {
/*  261 */           g.setColor(UIManager.getColor("Tree.selectionBackground"));
/*  262 */           if (DOMDocumentTree.TreeDropTargetListener.this.startPoint == null || DOMDocumentTree.TreeDropTargetListener.this.endPoint == null) {
/*      */             return;
/*      */           }
/*  265 */           int x1 = DOMDocumentTree.TreeDropTargetListener.this.startPoint.x;
/*  266 */           int x2 = DOMDocumentTree.TreeDropTargetListener.this.endPoint.x;
/*  267 */           int y1 = DOMDocumentTree.TreeDropTargetListener.this.startPoint.y;
/*      */ 
/*      */           
/*  270 */           int start = -DOMDocumentTree.TreeDropTargetListener.this.visualTipThickness / 2;
/*  271 */           start += (DOMDocumentTree.TreeDropTargetListener.this.visualTipThickness % 2 == 0) ? 1 : 0;
/*  272 */           for (int i = start; i <= DOMDocumentTree.TreeDropTargetListener.this.visualTipThickness / 2; i++) {
/*  273 */             g.drawLine(x1 + 2, y1 + i, x2 - 2, y1 + i);
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Timer expandControlTimer;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  287 */     private int expandTimeout = 1500;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private TreePath dragOverTreePath;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private TreePath treePathToExpand;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TreeDropTargetListener(DOMDocumentTree tree) {
/*  303 */       addOnAutoscrollListener(tree);
/*      */     }
/*      */     
/*      */     public void dragEnter(DropTargetDragEvent dtde) {
/*  307 */       JTree tree = (JTree)dtde.getDropTargetContext().getComponent();
/*  308 */       JRootPane rootPane = tree.getRootPane();
/*      */       
/*  310 */       this.originalGlassPane = rootPane.getGlassPane();
/*  311 */       rootPane.setGlassPane(this.visualTipGlassPane);
/*  312 */       this.visualTipGlassPane.setOpaque(false);
/*  313 */       this.visualTipGlassPane.setVisible(true);
/*  314 */       updateVisualTipLine(tree, null);
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  319 */         Transferable transferable = (new DropTargetDropEvent(dtde.getDropTargetContext(), dtde.getLocation(), 0, 0)).getTransferable();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  324 */         DataFlavor[] flavors = transferable.getTransferDataFlavors();
/*  325 */         for (DataFlavor flavor : flavors) {
/*  326 */           if (transferable.isDataFlavorSupported(flavor)) {
/*  327 */             this.transferData = (DOMDocumentTree.TransferData)transferable.getTransferData(flavor);
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*  332 */       } catch (UnsupportedFlavorException e) {
/*  333 */         e.printStackTrace();
/*  334 */       } catch (IOException e) {
/*  335 */         e.printStackTrace();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void dragOver(DropTargetDragEvent dtde) {
/*  340 */       JTree tree = (JTree)dtde.getDropTargetContext().getComponent();
/*  341 */       TreeNode targetTreeNode = getNode(dtde);
/*  342 */       if (targetTreeNode != null) {
/*      */         
/*  344 */         updatePositionIndicator(dtde);
/*  345 */         Point p = dtde.getLocation();
/*  346 */         TreePath currentPath = tree.getPathForLocation(p.x, p.y);
/*  347 */         TreePath parentPath = getParentPathForPosition(currentPath);
/*  348 */         TreeNode parentNode = getNodeForPath(parentPath);
/*  349 */         TreePath nextSiblingPath = getSiblingPathForPosition(currentPath);
/*      */         
/*  351 */         TreeNode nextSiblingNode = getNodeForPath(nextSiblingPath);
/*  352 */         Node potentialParent = DOMDocumentTree.this.getDomNodeFromTreeNode((DefaultMutableTreeNode)parentNode);
/*      */         
/*  354 */         Node potentialSibling = DOMDocumentTree.this.getDomNodeFromTreeNode((DefaultMutableTreeNode)nextSiblingNode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  362 */         if (DOMUtilities.canAppendAny(this.transferData.getNodeList(), potentialParent) && !this.transferData.getNodeList().contains(potentialSibling)) {
/*      */ 
/*      */ 
/*      */           
/*  366 */           dtde.acceptDrag(dtde.getDropAction());
/*      */           
/*  368 */           updateVisualTipLine(tree, currentPath);
/*      */           
/*  370 */           this.dragOverTreePath = currentPath;
/*  371 */           if (!tree.isExpanded(currentPath)) {
/*  372 */             scheduleExpand(currentPath, tree);
/*      */           }
/*      */         } else {
/*  375 */           dtde.rejectDrag();
/*      */         } 
/*      */       } else {
/*  378 */         dtde.rejectDrag();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void dropActionChanged(DropTargetDragEvent dtde) {}
/*      */     
/*      */     public void drop(DropTargetDropEvent dtde) {
/*  386 */       Point p = dtde.getLocation();
/*  387 */       DropTargetContext dtc = dtde.getDropTargetContext();
/*  388 */       JTree tree = (JTree)dtc.getComponent();
/*      */       
/*  390 */       setOriginalGlassPane(tree);
/*      */       
/*  392 */       this.dragOverTreePath = null;
/*      */       
/*  394 */       TreePath currentPath = tree.getPathForLocation(p.x, p.y);
/*  395 */       DefaultMutableTreeNode parent = (DefaultMutableTreeNode)getNodeForPath(getParentPathForPosition(currentPath));
/*      */ 
/*      */       
/*  398 */       Node dropTargetNode = DOMDocumentTree.this.getDomNodeFromTreeNode(parent);
/*  399 */       DefaultMutableTreeNode sibling = (DefaultMutableTreeNode)getNodeForPath(getSiblingPathForPosition(currentPath));
/*      */ 
/*      */       
/*  402 */       Node siblingNode = DOMDocumentTree.this.getDomNodeFromTreeNode(sibling);
/*  403 */       if (this.transferData != null) {
/*  404 */         ArrayList nodelist = DOMDocumentTree.this.getNodeListForParent(this.transferData.getNodeList(), dropTargetNode);
/*      */ 
/*      */         
/*  407 */         DOMDocumentTree.this.fireDropCompleted(new DOMDocumentTree.DOMDocumentTreeEvent(new DOMDocumentTree.DropCompletedInfo(dropTargetNode, siblingNode, nodelist)));
/*      */ 
/*      */ 
/*      */         
/*  411 */         dtde.dropComplete(true);
/*      */         return;
/*      */       } 
/*  414 */       dtde.rejectDrop();
/*      */     }
/*      */     
/*      */     public void dragExit(DropTargetEvent dte) {
/*  418 */       setOriginalGlassPane((JTree)dte.getDropTargetContext().getComponent());
/*      */ 
/*      */       
/*  421 */       this.dragOverTreePath = null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void updatePositionIndicator(DropTargetDragEvent dtde) {
/*  431 */       Point p = dtde.getLocation();
/*  432 */       DropTargetContext dtc = dtde.getDropTargetContext();
/*  433 */       JTree tree = (JTree)dtc.getComponent();
/*      */       
/*  435 */       TreePath currentPath = tree.getPathForLocation(p.x, p.y);
/*  436 */       Rectangle bounds = tree.getPathBounds(currentPath);
/*      */       
/*  438 */       if (p.y <= bounds.y + this.visualTipOffset) {
/*  439 */         this.positionIndicator = 1;
/*      */       
/*      */       }
/*  442 */       else if (p.y >= bounds.y + bounds.height - this.visualTipOffset) {
/*  443 */         this.positionIndicator = 2;
/*      */       }
/*      */       else {
/*      */         
/*  447 */         this.positionIndicator = 3;
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
/*      */     
/*      */     private TreePath getParentPathForPosition(TreePath currentPath) {
/*  465 */       if (currentPath == null) {
/*  466 */         return null;
/*      */       }
/*  468 */       TreePath parentPath = null;
/*  469 */       if (this.positionIndicator == 2) {
/*  470 */         parentPath = currentPath.getParentPath();
/*  471 */       } else if (this.positionIndicator == 1) {
/*  472 */         parentPath = currentPath.getParentPath();
/*  473 */       } else if (this.positionIndicator == 3) {
/*  474 */         parentPath = currentPath;
/*      */       } 
/*  476 */       return parentPath;
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
/*      */     private TreePath getSiblingPathForPosition(TreePath currentPath) {
/*  488 */       TreePath parentPath = getParentPathForPosition(currentPath);
/*  489 */       TreePath nextSiblingPath = null;
/*  490 */       if (this.positionIndicator == 2) {
/*  491 */         TreeNode parentNode = getNodeForPath(parentPath);
/*  492 */         TreeNode currentNode = getNodeForPath(currentPath);
/*  493 */         if (parentPath != null && parentNode != null && currentNode != null)
/*      */         {
/*  495 */           int siblingIndex = parentNode.getIndex(currentNode) + 1;
/*  496 */           if (parentNode.getChildCount() > siblingIndex) {
/*  497 */             nextSiblingPath = parentPath.pathByAddingChild(parentNode.getChildAt(siblingIndex));
/*      */           }
/*      */         }
/*      */       
/*      */       }
/*  502 */       else if (this.positionIndicator == 1) {
/*  503 */         nextSiblingPath = currentPath;
/*  504 */       } else if (this.positionIndicator == 3) {
/*  505 */         nextSiblingPath = null;
/*      */       } 
/*  507 */       return nextSiblingPath;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private TreeNode getNodeForPath(TreePath path) {
/*  518 */       if (path == null || path.getLastPathComponent() == null) {
/*  519 */         return null;
/*      */       }
/*  521 */       return (TreeNode)path.getLastPathComponent();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private TreeNode getNode(DropTargetDragEvent dtde) {
/*  532 */       Point p = dtde.getLocation();
/*  533 */       DropTargetContext dtc = dtde.getDropTargetContext();
/*  534 */       JTree tree = (JTree)dtc.getComponent();
/*  535 */       TreePath path = tree.getPathForLocation(p.x, p.y);
/*  536 */       if (path == null || path.getLastPathComponent() == null) {
/*  537 */         return null;
/*      */       }
/*  539 */       return (TreeNode)path.getLastPathComponent();
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
/*      */     private void updateVisualTipLine(JTree tree, TreePath path) {
/*  552 */       if (path == null) {
/*  553 */         this.startPoint = null;
/*  554 */         this.endPoint = null;
/*      */       } else {
/*  556 */         Rectangle bounds = tree.getPathBounds(path);
/*  557 */         if (this.positionIndicator == 1) {
/*  558 */           this.startPoint = bounds.getLocation();
/*  559 */           this.endPoint = new Point(this.startPoint.x + bounds.width, this.startPoint.y);
/*      */         }
/*  561 */         else if (this.positionIndicator == 2) {
/*  562 */           this.startPoint = new Point(bounds.x, bounds.y + bounds.height);
/*  563 */           this.endPoint = new Point(this.startPoint.x + bounds.width, this.startPoint.y);
/*      */           
/*  565 */           this.positionIndicator = 2;
/*  566 */         } else if (this.positionIndicator == 3) {
/*  567 */           this.startPoint = null;
/*  568 */           this.endPoint = null;
/*      */         } 
/*  570 */         if (this.startPoint != null && this.endPoint != null) {
/*  571 */           this.startPoint = SwingUtilities.convertPoint(tree, this.startPoint, this.visualTipGlassPane);
/*      */           
/*  573 */           this.endPoint = SwingUtilities.convertPoint(tree, this.endPoint, this.visualTipGlassPane);
/*      */         } 
/*      */       } 
/*      */       
/*  577 */       this.visualTipGlassPane.getRootPane().repaint();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void addOnAutoscrollListener(DOMDocumentTree tree) {
/*  587 */       tree.addListener(new DOMDocumentTree.DOMDocumentTreeAdapter()
/*      */           {
/*      */             public void onAutoscroll(DOMDocumentTree.DOMDocumentTreeEvent event)
/*      */             {
/*  591 */               DOMDocumentTree.TreeDropTargetListener.this.startPoint = null;
/*  592 */               DOMDocumentTree.TreeDropTargetListener.this.endPoint = null;
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setOriginalGlassPane(JTree tree) {
/*  604 */       JRootPane rootPane = tree.getRootPane();
/*  605 */       rootPane.setGlassPane(this.originalGlassPane);
/*  606 */       this.originalGlassPane.setVisible(false);
/*  607 */       rootPane.repaint();
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
/*      */     private void scheduleExpand(TreePath treePath, JTree tree) {
/*  621 */       if (treePath != this.treePathToExpand) {
/*  622 */         getExpandTreeTimer(tree).stop();
/*  623 */         this.treePathToExpand = treePath;
/*  624 */         getExpandTreeTimer(tree).start();
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
/*      */     private Timer getExpandTreeTimer(final JTree tree) {
/*  636 */       if (this.expandControlTimer == null) {
/*  637 */         this.expandControlTimer = new Timer(this.expandTimeout, new ActionListener()
/*      */             {
/*      */               
/*      */               public void actionPerformed(ActionEvent arg0)
/*      */               {
/*  642 */                 if (DOMDocumentTree.TreeDropTargetListener.this.treePathToExpand != null && DOMDocumentTree.TreeDropTargetListener.this.treePathToExpand == DOMDocumentTree.TreeDropTargetListener.this.dragOverTreePath)
/*      */                 {
/*  644 */                   tree.expandPath(DOMDocumentTree.TreeDropTargetListener.this.treePathToExpand);
/*      */                 }
/*  646 */                 DOMDocumentTree.TreeDropTargetListener.this.getExpandTreeTimer(tree).stop();
/*      */               }
/*      */             });
/*      */       }
/*  650 */       return this.expandControlTimer;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class TransferableTreeNode
/*      */     implements Transferable
/*      */   {
/*  662 */     protected static final DataFlavor NODE_FLAVOR = new DataFlavor(DOMDocumentTree.TransferData.class, "TransferData");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  668 */     protected static final DataFlavor[] FLAVORS = new DataFlavor[] { NODE_FLAVOR, DataFlavor.stringFlavor };
/*      */ 
/*      */ 
/*      */     
/*      */     protected DOMDocumentTree.TransferData data;
/*      */ 
/*      */ 
/*      */     
/*      */     public TransferableTreeNode(DOMDocumentTree.TransferData data) {
/*  677 */       this.data = data;
/*      */     }
/*      */     
/*      */     public synchronized DataFlavor[] getTransferDataFlavors() {
/*  681 */       return FLAVORS;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isDataFlavorSupported(DataFlavor flavor) {
/*  692 */       for (DataFlavor FLAVOR : FLAVORS) {
/*  693 */         if (flavor.equals(FLAVOR)) {
/*  694 */           return true;
/*      */         }
/*      */       } 
/*  697 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized Object getTransferData(DataFlavor flavor) {
/*  708 */       if (!isDataFlavorSupported(flavor)) {
/*  709 */         return null;
/*      */       }
/*  711 */       if (flavor.equals(NODE_FLAVOR))
/*  712 */         return this.data; 
/*  713 */       if (flavor.equals(DataFlavor.stringFlavor)) {
/*  714 */         return this.data.getNodesAsXML();
/*      */       }
/*  716 */       return null;
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
/*      */   public static class TransferData
/*      */   {
/*      */     protected ArrayList nodeList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TransferData(ArrayList nodeList) {
/*  738 */       this.nodeList = nodeList;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ArrayList getNodeList() {
/*  747 */       return this.nodeList;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getNodesAsXML() {
/*  755 */       String toReturn = "";
/*  756 */       for (Object aNodeList : this.nodeList) {
/*  757 */         Node node = (Node)aNodeList;
/*  758 */         toReturn = toReturn + DOMUtilities.getXML(node);
/*      */       } 
/*  760 */       return toReturn;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void autoscroll(Point point) {
/*  767 */     JViewport viewport = (JViewport)SwingUtilities.getAncestorOfClass(JViewport.class, this);
/*      */ 
/*      */     
/*  770 */     if (viewport == null) {
/*      */       return;
/*      */     }
/*      */     
/*  774 */     Point viewportPos = viewport.getViewPosition();
/*  775 */     int viewHeight = (viewport.getExtentSize()).height;
/*  776 */     int viewWidth = (viewport.getExtentSize()).width;
/*      */ 
/*      */     
/*  779 */     if (point.y - viewportPos.y < this.autoscrollInsets.top) {
/*      */       
/*  781 */       viewport.setViewPosition(new Point(viewportPos.x, Math.max(viewportPos.y - this.scrollUnits.top, 0)));
/*      */ 
/*      */       
/*  784 */       fireOnAutoscroll(new DOMDocumentTreeEvent(this));
/*  785 */     } else if (viewportPos.y + viewHeight - point.y < this.autoscrollInsets.bottom) {
/*      */ 
/*      */       
/*  788 */       viewport.setViewPosition(new Point(viewportPos.x, Math.min(viewportPos.y + this.scrollUnits.bottom, getHeight() - viewHeight)));
/*      */ 
/*      */ 
/*      */       
/*  792 */       fireOnAutoscroll(new DOMDocumentTreeEvent(this));
/*  793 */     } else if (point.x - viewportPos.x < this.autoscrollInsets.left) {
/*      */       
/*  795 */       viewport.setViewPosition(new Point(Math.max(viewportPos.x - this.scrollUnits.left, 0), viewportPos.y));
/*      */ 
/*      */       
/*  798 */       fireOnAutoscroll(new DOMDocumentTreeEvent(this));
/*  799 */     } else if (viewportPos.x + viewWidth - point.x < this.autoscrollInsets.right) {
/*      */ 
/*      */       
/*  802 */       viewport.setViewPosition(new Point(Math.min(viewportPos.x + this.scrollUnits.right, getWidth() - viewWidth), viewportPos.y));
/*      */ 
/*      */ 
/*      */       
/*  806 */       fireOnAutoscroll(new DOMDocumentTreeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Insets getAutoscrollInsets() {
/*  811 */     int topAndBottom = getHeight();
/*  812 */     int leftAndRight = getWidth();
/*  813 */     return new Insets(topAndBottom, leftAndRight, topAndBottom, leftAndRight);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DOMDocumentTreeEvent
/*      */     extends EventObject
/*      */   {
/*      */     public DOMDocumentTreeEvent(Object source) {
/*  825 */       super(source);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface DOMDocumentTreeListener
/*      */     extends EventListener
/*      */   {
/*      */     void dropCompleted(DOMDocumentTree.DOMDocumentTreeEvent param1DOMDocumentTreeEvent);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void onAutoscroll(DOMDocumentTree.DOMDocumentTreeEvent param1DOMDocumentTreeEvent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DOMDocumentTreeAdapter
/*      */     implements DOMDocumentTreeListener
/*      */   {
/*      */     public void dropCompleted(DOMDocumentTree.DOMDocumentTreeEvent event) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void onAutoscroll(DOMDocumentTree.DOMDocumentTreeEvent event) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addListener(DOMDocumentTreeListener listener) {
/*  871 */     this.eventListeners.add(DOMDocumentTreeListener.class, listener);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fireDropCompleted(DOMDocumentTreeEvent event) {
/*  881 */     Object[] listeners = this.eventListeners.getListenerList();
/*  882 */     int length = listeners.length;
/*  883 */     for (int i = 0; i < length; i += 2) {
/*  884 */       if (listeners[i] == DOMDocumentTreeListener.class) {
/*  885 */         ((DOMDocumentTreeListener)listeners[i + 1]).dropCompleted(event);
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
/*      */   public void fireOnAutoscroll(DOMDocumentTreeEvent event) {
/*  898 */     Object[] listeners = this.eventListeners.getListenerList();
/*  899 */     int length = listeners.length;
/*  900 */     for (int i = 0; i < length; i += 2) {
/*  901 */       if (listeners[i] == DOMDocumentTreeListener.class) {
/*  902 */         ((DOMDocumentTreeListener)listeners[i + 1]).onAutoscroll(event);
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
/*      */   public static class DropCompletedInfo
/*      */   {
/*      */     protected Node parent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ArrayList children;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node sibling;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DropCompletedInfo(Node parent, Node sibling, ArrayList children) {
/*  936 */       this.parent = parent;
/*  937 */       this.sibling = sibling;
/*  938 */       this.children = children;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ArrayList getChildren() {
/*  947 */       return this.children;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node getParent() {
/*  956 */       return this.parent;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node getSibling() {
/*  965 */       return this.sibling;
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
/*      */   protected Node getDomNodeFromTreeNode(DefaultMutableTreeNode treeNode) {
/*  979 */     if (treeNode == null) {
/*  980 */       return null;
/*      */     }
/*  982 */     if (treeNode.getUserObject() instanceof DOMViewer.NodeInfo) {
/*  983 */       return ((DOMViewer.NodeInfo)treeNode.getUserObject()).getNode();
/*      */     }
/*  985 */     return null;
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
/*      */   protected ArrayList getNodeListForParent(ArrayList potentialChildren, Node parentNode) {
/* 1000 */     ArrayList<Node> children = new ArrayList();
/* 1001 */     int n = potentialChildren.size();
/* 1002 */     for (Object aPotentialChildren : potentialChildren) {
/* 1003 */       Node node = (Node)aPotentialChildren;
/* 1004 */       if (DOMUtilities.canAppend(node, parentNode)) {
/* 1005 */         children.add(node);
/*      */       }
/*      */     } 
/* 1008 */     return children;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/DOMDocumentTree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */