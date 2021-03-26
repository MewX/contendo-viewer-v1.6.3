/*      */ package org.apache.batik.apps.svgbrowser;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.w3c.dom.Element;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class HistoryBrowserInterface
/*      */ {
/*      */   private static final String ATTRIBUTE_ADDED_COMMAND = "Attribute added: ";
/*      */   private static final String ATTRIBUTE_REMOVED_COMMAND = "Attribute removed: ";
/*      */   private static final String ATTRIBUTE_MODIFIED_COMMAND = "Attribute modified: ";
/*      */   private static final String NODE_INSERTED_COMMAND = "Node inserted: ";
/*      */   private static final String NODE_REMOVED_COMMAND = "Node removed: ";
/*      */   private static final String CHAR_DATA_MODIFIED_COMMAND = "Node value changed: ";
/*      */   private static final String OUTER_EDIT_COMMAND = "Document changed outside DOM Viewer";
/*      */   private static final String COMPOUND_TREE_NODE_DROP = "Node moved";
/*      */   private static final String REMOVE_SELECTED_NODES = "Nodes removed";
/*      */   protected HistoryBrowser historyBrowser;
/*      */   protected AbstractCompoundCommand currentCompoundCommand;
/*      */   
/*      */   public HistoryBrowserInterface(HistoryBrowser.CommandController commandController) {
/*   82 */     this.historyBrowser = new HistoryBrowser(commandController);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCommmandController(HistoryBrowser.CommandController newCommandController) {
/*   92 */     this.historyBrowser.setCommandController(newCommandController);
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
/*      */   public CompoundUpdateCommand createCompoundUpdateCommand(String commandName) {
/*  105 */     CompoundUpdateCommand cmd = new CompoundUpdateCommand(commandName);
/*  106 */     return cmd;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CompoundUpdateCommand createNodeChangedCommand(Node node) {
/*  116 */     return new CompoundUpdateCommand(getNodeChangedCommandName(node));
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
/*      */   public CompoundUpdateCommand createNodesDroppedCommand(ArrayList nodes) {
/*  128 */     return new CompoundUpdateCommand("Node moved");
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
/*      */   public CompoundUpdateCommand createRemoveSelectedTreeNodesCommand(ArrayList nodes) {
/*  141 */     return new CompoundUpdateCommand("Nodes removed");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void performCompoundUpdateCommand(UndoableCommand command) {
/*  151 */     this.historyBrowser.addCommand(command);
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
/*      */   public static class CompoundUpdateCommand
/*      */     extends AbstractCompoundCommand
/*      */   {
/*      */     public CompoundUpdateCommand(String commandName) {
/*  166 */       setName(commandName);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public HistoryBrowser getHistoryBrowser() {
/*  176 */     return this.historyBrowser;
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
/*      */   public void nodeInserted(Node newParent, Node newSibling, Node contextNode) {
/*  193 */     this.historyBrowser.addCommand(createNodeInsertedCommand(newParent, newSibling, contextNode));
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
/*      */   public NodeInsertedCommand createNodeInsertedCommand(Node newParent, Node newSibling, Node contextNode) {
/*  210 */     return new NodeInsertedCommand("Node inserted: " + getBracketedNodeName(contextNode), newParent, newSibling, contextNode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class NodeInsertedCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Node newSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node newParent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node contextNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NodeInsertedCommand(String commandName, Node parent, Node sibling, Node contextNode) {
/*  240 */       setName(commandName);
/*  241 */       this.newParent = parent;
/*  242 */       this.contextNode = contextNode;
/*  243 */       this.newSibling = sibling;
/*      */     }
/*      */ 
/*      */     
/*      */     public void execute() {}
/*      */     
/*      */     public void undo() {
/*  250 */       this.newParent.removeChild(this.contextNode);
/*      */     }
/*      */     
/*      */     public void redo() {
/*  254 */       if (this.newSibling != null) {
/*  255 */         this.newParent.insertBefore(this.contextNode, this.newSibling);
/*      */       } else {
/*  257 */         this.newParent.appendChild(this.contextNode);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/*  262 */       if (this.newParent == null || this.contextNode == null) {
/*  263 */         return false;
/*      */       }
/*  265 */       return true;
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
/*      */   public void nodeRemoved(Node oldParent, Node oldSibling, Node contextNode) {
/*  280 */     this.historyBrowser.addCommand(createNodeRemovedCommand(oldParent, oldSibling, contextNode));
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
/*      */   public NodeRemovedCommand createNodeRemovedCommand(Node oldParent, Node oldSibling, Node contextNode) {
/*  297 */     return new NodeRemovedCommand("Node removed: " + getBracketedNodeName(contextNode), oldParent, oldSibling, contextNode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class NodeRemovedCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Node oldSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node oldParent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node contextNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NodeRemovedCommand(String commandName, Node oldParent, Node oldSibling, Node contextNode) {
/*  327 */       setName(commandName);
/*  328 */       this.oldParent = oldParent;
/*  329 */       this.contextNode = contextNode;
/*  330 */       this.oldSibling = oldSibling;
/*      */     }
/*      */ 
/*      */     
/*      */     public void execute() {}
/*      */     
/*      */     public void undo() {
/*  337 */       if (this.oldSibling != null) {
/*  338 */         this.oldParent.insertBefore(this.contextNode, this.oldSibling);
/*      */       } else {
/*  340 */         this.oldParent.appendChild(this.contextNode);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void redo() {
/*  345 */       this.oldParent.removeChild(this.contextNode);
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/*  349 */       if (this.oldParent == null || this.contextNode == null) {
/*  350 */         return false;
/*      */       }
/*  352 */       return true;
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
/*      */   public void attributeAdded(Element contextElement, String attributeName, String newAttributeValue, String namespaceURI) {
/*  370 */     this.historyBrowser.addCommand(createAttributeAddedCommand(contextElement, attributeName, newAttributeValue, namespaceURI));
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
/*      */   public AttributeAddedCommand createAttributeAddedCommand(Element contextElement, String attributeName, String newAttributeValue, String namespaceURI) {
/*  392 */     return new AttributeAddedCommand("Attribute added: " + getBracketedNodeName(contextElement), contextElement, attributeName, newAttributeValue, namespaceURI);
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
/*      */   public static class AttributeAddedCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Element contextElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String attributeName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String newValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String namespaceURI;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AttributeAddedCommand(String commandName, Element contextElement, String attributeName, String newAttributeValue, String namespaceURI) {
/*  441 */       setName(commandName);
/*  442 */       this.contextElement = contextElement;
/*  443 */       this.attributeName = attributeName;
/*  444 */       this.newValue = newAttributeValue;
/*  445 */       this.namespaceURI = namespaceURI;
/*      */     }
/*      */ 
/*      */     
/*      */     public void execute() {}
/*      */     
/*      */     public void undo() {
/*  452 */       this.contextElement.removeAttributeNS(this.namespaceURI, this.attributeName);
/*      */     }
/*      */     
/*      */     public void redo() {
/*  456 */       this.contextElement.setAttributeNS(this.namespaceURI, this.attributeName, this.newValue);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean shouldExecute() {
/*  461 */       if (this.contextElement == null || this.attributeName.length() == 0) {
/*  462 */         return false;
/*      */       }
/*  464 */       return true;
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
/*      */   public void attributeRemoved(Element contextElement, String attributeName, String prevAttributeValue, String namespaceURI) {
/*  484 */     this.historyBrowser.addCommand(createAttributeRemovedCommand(contextElement, attributeName, prevAttributeValue, namespaceURI));
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
/*      */   public AttributeRemovedCommand createAttributeRemovedCommand(Element contextElement, String attributeName, String prevAttributeValue, String namespaceURI) {
/*  506 */     return new AttributeRemovedCommand("Attribute removed: " + getBracketedNodeName(contextElement), contextElement, attributeName, prevAttributeValue, namespaceURI);
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
/*      */   public static class AttributeRemovedCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Element contextElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String attributeName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String prevValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String namespaceURI;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AttributeRemovedCommand(String commandName, Element contextElement, String attributeName, String prevAttributeValue, String namespaceURI) {
/*  555 */       setName(commandName);
/*  556 */       this.contextElement = contextElement;
/*  557 */       this.attributeName = attributeName;
/*  558 */       this.prevValue = prevAttributeValue;
/*  559 */       this.namespaceURI = namespaceURI;
/*      */     }
/*      */ 
/*      */     
/*      */     public void execute() {}
/*      */     
/*      */     public void undo() {
/*  566 */       this.contextElement.setAttributeNS(this.namespaceURI, this.attributeName, this.prevValue);
/*      */     }
/*      */ 
/*      */     
/*      */     public void redo() {
/*  571 */       this.contextElement.removeAttributeNS(this.namespaceURI, this.attributeName);
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/*  575 */       if (this.contextElement == null || this.attributeName.length() == 0) {
/*  576 */         return false;
/*      */       }
/*  578 */       return true;
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
/*      */   public void attributeModified(Element contextElement, String attributeName, String prevAttributeValue, String newAttributeValue, String namespaceURI) {
/*  601 */     this.historyBrowser.addCommand(createAttributeModifiedCommand(contextElement, attributeName, prevAttributeValue, newAttributeValue, namespaceURI));
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
/*      */   public AttributeModifiedCommand createAttributeModifiedCommand(Element contextElement, String attributeName, String prevAttributeValue, String newAttributeValue, String namespaceURI) {
/*  627 */     return new AttributeModifiedCommand("Attribute modified: " + getBracketedNodeName(contextElement), contextElement, attributeName, prevAttributeValue, newAttributeValue, namespaceURI);
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
/*      */   public static class AttributeModifiedCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Element contextElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String attributeName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String prevAttributeValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String newAttributeValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String namespaceURI;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AttributeModifiedCommand(String commandName, Element contextElement, String attributeName, String prevAttributeValue, String newAttributeValue, String namespaceURI) {
/*  685 */       setName(commandName);
/*  686 */       this.contextElement = contextElement;
/*  687 */       this.attributeName = attributeName;
/*  688 */       this.prevAttributeValue = prevAttributeValue;
/*  689 */       this.newAttributeValue = newAttributeValue;
/*  690 */       this.namespaceURI = namespaceURI;
/*      */     }
/*      */ 
/*      */     
/*      */     public void execute() {}
/*      */     
/*      */     public void undo() {
/*  697 */       this.contextElement.setAttributeNS(this.namespaceURI, this.attributeName, this.prevAttributeValue);
/*      */     }
/*      */ 
/*      */     
/*      */     public void redo() {
/*  702 */       this.contextElement.setAttributeNS(this.namespaceURI, this.attributeName, this.newAttributeValue);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean shouldExecute() {
/*  707 */       if (this.contextElement == null || this.attributeName.length() == 0) {
/*  708 */         return false;
/*      */       }
/*  710 */       return true;
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
/*      */   public void charDataModified(Node contextNode, String oldValue, String newValue) {
/*  726 */     this.historyBrowser.addCommand(createCharDataModifiedCommand(contextNode, oldValue, newValue));
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
/*      */   public CharDataModifiedCommand createCharDataModifiedCommand(Node contextNode, String oldValue, String newValue) {
/*  744 */     return new CharDataModifiedCommand("Node value changed: " + getBracketedNodeName(contextNode), contextNode, oldValue, newValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class CharDataModifiedCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Node contextNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String oldValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String newValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public CharDataModifiedCommand(String commandName, Node contextNode, String oldValue, String newValue) {
/*  783 */       setName(commandName);
/*  784 */       this.contextNode = contextNode;
/*  785 */       this.oldValue = oldValue;
/*  786 */       this.newValue = newValue;
/*      */     }
/*      */ 
/*      */     
/*      */     public void execute() {}
/*      */     
/*      */     public void undo() {
/*  793 */       this.contextNode.setNodeValue(this.oldValue);
/*      */     }
/*      */     
/*      */     public void redo() {
/*  797 */       this.contextNode.setNodeValue(this.newValue);
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/*  801 */       if (this.contextNode == null) {
/*  802 */         return false;
/*      */       }
/*  804 */       return true;
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
/*      */   public void appendChild(Node parent, Node child) {
/*  819 */     this.historyBrowser.addCommand(createAppendChildCommand(parent, child));
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
/*      */   public AppendChildCommand createAppendChildCommand(Node parent, Node child) {
/*  834 */     return new AppendChildCommand(getAppendChildCommandName(parent, child), parent, child);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class AppendChildCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Node oldParentNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node oldNextSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node parentNode;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node childNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public AppendChildCommand(String commandName, Node parentNode, Node childNode) {
/*  869 */       setName(commandName);
/*  870 */       this.oldParentNode = childNode.getParentNode();
/*  871 */       this.oldNextSibling = childNode.getNextSibling();
/*  872 */       this.parentNode = parentNode;
/*  873 */       this.childNode = childNode;
/*      */     }
/*      */     
/*      */     public void execute() {
/*  877 */       this.parentNode.appendChild(this.childNode);
/*      */     }
/*      */     
/*      */     public void undo() {
/*  881 */       if (this.oldParentNode != null) {
/*  882 */         this.oldParentNode.insertBefore(this.childNode, this.oldNextSibling);
/*      */       } else {
/*  884 */         this.parentNode.removeChild(this.childNode);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void redo() {
/*  889 */       execute();
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/*  893 */       if (this.parentNode == null || this.childNode == null) {
/*  894 */         return false;
/*      */       }
/*  896 */       return true;
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
/*      */   public void insertChildBefore(Node parent, Node sibling, Node child) {
/*  911 */     if (sibling == null) {
/*  912 */       this.historyBrowser.addCommand(createAppendChildCommand(parent, child));
/*      */     } else {
/*  914 */       this.historyBrowser.addCommand(createInsertNodeBeforeCommand(parent, sibling, child));
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
/*      */   public UndoableCommand createInsertChildCommand(Node parent, Node sibling, Node child) {
/*  935 */     if (sibling == null) {
/*  936 */       return createAppendChildCommand(parent, child);
/*      */     }
/*  938 */     return createInsertNodeBeforeCommand(parent, sibling, child);
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
/*      */   public InsertNodeBeforeCommand createInsertNodeBeforeCommand(Node parent, Node sibling, Node child) {
/*  955 */     return new InsertNodeBeforeCommand(getInsertBeforeCommandName(parent, child, sibling), parent, sibling, child);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class InsertNodeBeforeCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Node oldParent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node oldNextSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node newNextSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node parent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node child;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public InsertNodeBeforeCommand(String commandName, Node parent, Node sibling, Node child) {
/*  997 */       setName(commandName);
/*  998 */       this.oldParent = child.getParentNode();
/*  999 */       this.oldNextSibling = child.getNextSibling();
/* 1000 */       this.parent = parent;
/* 1001 */       this.child = child;
/* 1002 */       this.newNextSibling = sibling;
/*      */     }
/*      */     
/*      */     public void execute() {
/* 1006 */       if (this.newNextSibling != null) {
/* 1007 */         this.parent.insertBefore(this.child, this.newNextSibling);
/*      */       } else {
/* 1009 */         this.parent.appendChild(this.child);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void undo() {
/* 1017 */       if (this.oldParent != null) {
/* 1018 */         this.oldParent.insertBefore(this.child, this.oldNextSibling);
/*      */       } else {
/* 1020 */         this.parent.removeChild(this.child);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void redo() {
/* 1025 */       execute();
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/* 1029 */       if (this.parent == null || this.child == null) {
/* 1030 */         return false;
/*      */       }
/* 1032 */       return true;
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
/*      */   public void replaceChild(Node parent, Node newChild, Node oldChild) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ReplaceChildCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Node oldParent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node oldNextSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node newNextSibling;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node parent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node child;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ReplaceChildCommand(String commandName, Node parent, Node sibling, Node child) {
/* 1092 */       setName(commandName);
/* 1093 */       this.oldParent = child.getParentNode();
/* 1094 */       this.oldNextSibling = child.getNextSibling();
/* 1095 */       this.parent = parent;
/* 1096 */       this.child = child;
/* 1097 */       this.newNextSibling = sibling;
/*      */     }
/*      */     
/*      */     public void execute() {
/* 1101 */       if (this.newNextSibling != null) {
/* 1102 */         this.parent.insertBefore(this.child, this.newNextSibling);
/*      */       } else {
/* 1104 */         this.parent.appendChild(this.child);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void undo() {
/* 1109 */       if (this.oldParent != null) {
/* 1110 */         this.oldParent.insertBefore(this.child, this.oldNextSibling);
/*      */       } else {
/* 1112 */         this.parent.removeChild(this.child);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void redo() {
/* 1117 */       execute();
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/* 1121 */       if (this.parent == null || this.child == null) {
/* 1122 */         return false;
/*      */       }
/* 1124 */       return true;
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
/*      */   public void removeChild(Node parent, Node child) {
/* 1137 */     this.historyBrowser.addCommand(createRemoveChildCommand(parent, child));
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
/*      */   public RemoveChildCommand createRemoveChildCommand(Node parent, Node child) {
/* 1151 */     return new RemoveChildCommand(getRemoveChildCommandName(parent, child), parent, child);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class RemoveChildCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Node parentNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Node childNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int indexInChildrenArray;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public RemoveChildCommand(String commandName, Node parentNode, Node childNode) {
/* 1181 */       setName(commandName);
/* 1182 */       this.parentNode = parentNode;
/* 1183 */       this.childNode = childNode;
/*      */     }
/*      */     
/*      */     public void execute() {
/* 1187 */       this.indexInChildrenArray = DOMUtilities.getChildIndex(this.childNode, this.parentNode);
/*      */       
/* 1189 */       this.parentNode.removeChild(this.childNode);
/*      */     }
/*      */     
/*      */     public void undo() {
/* 1193 */       Node refChild = this.parentNode.getChildNodes().item(this.indexInChildrenArray);
/*      */       
/* 1195 */       this.parentNode.insertBefore(this.childNode, refChild);
/*      */     }
/*      */     
/*      */     public void redo() {
/* 1199 */       this.parentNode.removeChild(this.childNode);
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/* 1203 */       if (this.parentNode == null || this.childNode == null) {
/* 1204 */         return false;
/*      */       }
/* 1206 */       return true;
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
/*      */   public void setNodeValue(Node contextNode, String newValue) {
/* 1219 */     this.historyBrowser.addCommand(createChangeNodeValueCommand(contextNode, newValue));
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
/*      */   public ChangeNodeValueCommand createChangeNodeValueCommand(Node contextNode, String newValue) {
/* 1234 */     return new ChangeNodeValueCommand(getChangeNodeValueCommandName(contextNode, newValue), contextNode, newValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ChangeNodeValueCommand
/*      */     extends AbstractUndoableCommand
/*      */   {
/*      */     protected Node contextNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected String newValue;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ChangeNodeValueCommand(String commandName, Node contextNode, String newValue) {
/* 1260 */       setName(commandName);
/* 1261 */       this.contextNode = contextNode;
/* 1262 */       this.newValue = newValue;
/*      */     }
/*      */     
/*      */     public void execute() {
/* 1266 */       String oldNodeValue = this.contextNode.getNodeValue();
/* 1267 */       this.contextNode.setNodeValue(this.newValue);
/* 1268 */       this.newValue = oldNodeValue;
/*      */     }
/*      */     
/*      */     public void undo() {
/* 1272 */       execute();
/*      */     }
/*      */     
/*      */     public void redo() {
/* 1276 */       execute();
/*      */     }
/*      */     
/*      */     public boolean shouldExecute() {
/* 1280 */       if (this.contextNode == null) {
/* 1281 */         return false;
/*      */       }
/* 1283 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AbstractCompoundCommand getCurrentCompoundCommand() {
/* 1293 */     if (this.currentCompoundCommand == null) {
/* 1294 */       this.currentCompoundCommand = createCompoundUpdateCommand("Document changed outside DOM Viewer");
/*      */     }
/*      */     
/* 1297 */     return this.currentCompoundCommand;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToCurrentCompoundCommand(AbstractUndoableCommand cmd) {
/* 1307 */     getCurrentCompoundCommand().addCommand(cmd);
/*      */     
/* 1309 */     this.historyBrowser.fireDoCompoundEdit(new HistoryBrowser.HistoryBrowserEvent(getCurrentCompoundCommand()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void performCurrentCompoundCommand() {
/* 1317 */     if (getCurrentCompoundCommand().getCommandNumber() > 0) {
/* 1318 */       this.historyBrowser.addCommand(getCurrentCompoundCommand());
/*      */       
/* 1320 */       this.historyBrowser.fireCompoundEditPerformed(new HistoryBrowser.HistoryBrowserEvent(this.currentCompoundCommand));
/*      */ 
/*      */       
/* 1323 */       this.currentCompoundCommand = null;
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
/*      */   private String getNodeAsString(Node node) {
/* 1337 */     String id = "";
/* 1338 */     if (node.getNodeType() == 1) {
/* 1339 */       Element e = (Element)node;
/* 1340 */       id = e.getAttributeNS(null, "id");
/*      */     } 
/* 1342 */     if (id.length() != 0) {
/* 1343 */       return node.getNodeName() + " \"" + id + "\"";
/*      */     }
/* 1345 */     return node.getNodeName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getBracketedNodeName(Node node) {
/* 1356 */     return "(" + getNodeAsString(node) + ")";
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
/*      */   private String getAppendChildCommandName(Node parentNode, Node childNode) {
/* 1369 */     return "Append " + getNodeAsString(childNode) + " to " + getNodeAsString(parentNode);
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
/*      */   private String getInsertBeforeCommandName(Node parentNode, Node childNode, Node siblingNode) {
/* 1386 */     return "Insert " + getNodeAsString(childNode) + " to " + getNodeAsString(parentNode) + " before " + getNodeAsString(siblingNode);
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
/*      */   private String getRemoveChildCommandName(Node parent, Node child) {
/* 1401 */     return "Remove " + getNodeAsString(child) + " from " + getNodeAsString(parent);
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
/*      */   private String getChangeNodeValueCommandName(Node contextNode, String newValue) {
/* 1416 */     return "Change " + getNodeAsString(contextNode) + " value to " + newValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getNodeChangedCommandName(Node node) {
/* 1425 */     return "Node " + getNodeAsString(node) + " changed";
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/HistoryBrowserInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */