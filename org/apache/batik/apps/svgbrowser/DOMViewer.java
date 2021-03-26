/*      */ package org.apache.batik.apps.svgbrowser;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Component;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JSplitPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextArea;
/*      */ import javax.swing.JToggleButton;
/*      */ import javax.swing.JToolBar;
/*      */ import javax.swing.JTree;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.event.TreeSelectionEvent;
/*      */ import javax.swing.event.TreeSelectionListener;
/*      */ import javax.swing.table.AbstractTableModel;
/*      */ import javax.swing.tree.DefaultMutableTreeNode;
/*      */ import javax.swing.tree.DefaultTreeCellRenderer;
/*      */ import javax.swing.tree.DefaultTreeModel;
/*      */ import javax.swing.tree.MutableTreeNode;
/*      */ import javax.swing.tree.TreeNode;
/*      */ import javax.swing.tree.TreePath;
/*      */ import org.apache.batik.anim.dom.SVGOMDocument;
/*      */ import org.apache.batik.anim.dom.XBLOMContentElement;
/*      */ import org.apache.batik.bridge.svg12.ContentManager;
/*      */ import org.apache.batik.bridge.svg12.DefaultXBLManager;
/*      */ import org.apache.batik.dom.AbstractDocument;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.dom.util.SAXDocumentFactory;
/*      */ import org.apache.batik.dom.xbl.NodeXBL;
/*      */ import org.apache.batik.dom.xbl.XBLManager;
/*      */ import org.apache.batik.util.XMLResourceDescriptor;
/*      */ import org.apache.batik.util.gui.DropDownComponent;
/*      */ import org.apache.batik.util.gui.resource.ActionMap;
/*      */ import org.apache.batik.util.gui.resource.ButtonFactory;
/*      */ import org.apache.batik.util.gui.resource.MissingListenerException;
/*      */ import org.apache.batik.util.resources.ResourceManager;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentFragment;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.css.CSSStyleDeclaration;
/*      */ import org.w3c.dom.css.ViewCSS;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.events.EventListener;
/*      */ import org.w3c.dom.events.EventTarget;
/*      */ import org.w3c.dom.events.MutationEvent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class DOMViewer
/*      */   extends JFrame
/*      */   implements ActionMap
/*      */ {
/*      */   protected static final String RESOURCE = "org.apache.batik.apps.svgbrowser.resources.DOMViewerMessages";
/*  141 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.DOMViewerMessages", Locale.getDefault());
/*  142 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  148 */   protected Map listeners = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ButtonFactory buttonFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Panel panel;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean showWhitespace = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isCapturingClickEnabled;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected DOMViewerController domViewerController;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ElementOverlayManager elementOverlayManager;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isElementOverlayEnabled;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected HistoryBrowserInterface historyBrowserInterface;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canEdit = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JToggleButton overlayButton;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DOMViewer(DOMViewerController controller) {
/*  205 */     super(resources.getString("Frame.title"));
/*  206 */     setSize(resources.getInteger("Frame.width"), resources.getInteger("Frame.height"));
/*      */ 
/*      */     
/*  209 */     this.domViewerController = controller;
/*      */     
/*  211 */     this.elementOverlayManager = this.domViewerController.createSelectionManager();
/*  212 */     if (this.elementOverlayManager != null) {
/*  213 */       this.elementOverlayManager.setController(new DOMViewerElementOverlayController());
/*      */     }
/*      */     
/*  216 */     this.historyBrowserInterface = new HistoryBrowserInterface(new HistoryBrowser.DocumentCommandController(controller));
/*      */ 
/*      */ 
/*      */     
/*  220 */     this.listeners.put("CloseButtonAction", new CloseButtonAction());
/*  221 */     this.listeners.put("UndoButtonAction", new UndoButtonAction());
/*  222 */     this.listeners.put("RedoButtonAction", new RedoButtonAction());
/*  223 */     this.listeners.put("CapturingClickButtonAction", new CapturingClickButtonAction());
/*      */     
/*  225 */     this.listeners.put("OverlayButtonAction", new OverlayButtonAction());
/*      */ 
/*      */     
/*  228 */     this.panel = new Panel();
/*  229 */     getContentPane().add(this.panel);
/*      */     
/*  231 */     JPanel p = new JPanel(new BorderLayout());
/*  232 */     JCheckBox cb = new JCheckBox(resources.getString("ShowWhitespaceCheckbox.text"));
/*      */     
/*  234 */     cb.setSelected(this.showWhitespace);
/*  235 */     cb.addItemListener(new ItemListener() {
/*      */           public void itemStateChanged(ItemEvent ie) {
/*  237 */             DOMViewer.this.setShowWhitespace((ie.getStateChange() == 1));
/*      */           }
/*      */         });
/*  240 */     p.add(cb, "West");
/*  241 */     p.add(getButtonFactory().createJButton("CloseButton"), "East");
/*      */     
/*  243 */     getContentPane().add(p, "South");
/*      */ 
/*      */     
/*  246 */     Document document = this.domViewerController.getDocument();
/*  247 */     if (document != null)
/*      */     {
/*      */ 
/*      */       
/*  251 */       this.panel.setDocument(document, (ViewCSS)null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShowWhitespace(boolean state) {
/*  259 */     this.showWhitespace = state;
/*  260 */     if (this.panel.document != null) {
/*  261 */       this.panel.setDocument(this.panel.document);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocument(Document doc) {
/*  268 */     this.panel.setDocument(doc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocument(Document doc, ViewCSS view) {
/*  275 */     this.panel.setDocument(doc, view);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canEdit() {
/*  284 */     return (this.domViewerController.canEdit() && this.canEdit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEditable(boolean canEdit) {
/*  294 */     this.canEdit = canEdit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void selectNode(Node node) {
/*  304 */     this.panel.selectNode(node);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetHistory() {
/*  311 */     this.historyBrowserInterface.getHistoryBrowser().resetHistory();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ButtonFactory getButtonFactory() {
/*  318 */     if (this.buttonFactory == null) {
/*  319 */       this.buttonFactory = new ButtonFactory(bundle, this);
/*      */     }
/*  321 */     return this.buttonFactory;
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
/*      */   public Action getAction(String key) throws MissingListenerException {
/*  335 */     return (Action)this.listeners.get(key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addChangesToHistory() {
/*  343 */     this.historyBrowserInterface.performCurrentCompoundCommand();
/*      */   }
/*      */ 
/*      */   
/*      */   protected class CloseButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/*  351 */       if (DOMViewer.this.panel.attributePanel.panelHiding()) {
/*  352 */         DOMViewer.this.panel.tree.setSelectionRow(0);
/*  353 */         DOMViewer.this.dispose();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class UndoButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/*  363 */       DOMViewer.this.addChangesToHistory();
/*  364 */       DOMViewer.this.historyBrowserInterface.getHistoryBrowser().undo();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class RedoButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/*  373 */       DOMViewer.this.addChangesToHistory();
/*  374 */       DOMViewer.this.historyBrowserInterface.getHistoryBrowser().redo();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class CapturingClickButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/*  384 */       JToggleButton btn = (JToggleButton)e.getSource();
/*  385 */       DOMViewer.this.isCapturingClickEnabled = btn.isSelected();
/*  386 */       if (!DOMViewer.this.isCapturingClickEnabled) {
/*  387 */         btn.setToolTipText(DOMViewer.resources.getString("CapturingClickButton.tooltip"));
/*      */       } else {
/*      */         
/*  390 */         btn.setToolTipText(DOMViewer.resources.getString("CapturingClickButton.disableText"));
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void toggleOverlay() {
/*  400 */     this.isElementOverlayEnabled = this.overlayButton.isSelected();
/*  401 */     if (!this.isElementOverlayEnabled) {
/*  402 */       this.overlayButton.setToolTipText(resources.getString("OverlayButton.tooltip"));
/*      */     } else {
/*      */       
/*  405 */       this.overlayButton.setToolTipText(resources.getString("OverlayButton.disableText"));
/*      */     } 
/*      */ 
/*      */     
/*  409 */     if (this.elementOverlayManager != null) {
/*  410 */       this.elementOverlayManager.setOverlayEnabled(this.isElementOverlayEnabled);
/*  411 */       this.elementOverlayManager.repaint();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class OverlayButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/*  421 */       DOMViewer.this.toggleOverlay();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DOMViewerNodePickerController
/*      */     implements NodePickerController
/*      */   {
/*      */     public boolean isEditable() {
/*  432 */       return DOMViewer.this.canEdit();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean canEdit(Element el) {
/*  438 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DOMViewerDOMDocumentTreeController
/*      */     implements DOMDocumentTreeController
/*      */   {
/*      */     public boolean isDNDSupported() {
/*  451 */       return DOMViewer.this.canEdit();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class DOMViewerElementOverlayController
/*      */     implements ElementOverlayController
/*      */   {
/*      */     public boolean isOverlayEnabled() {
/*  462 */       return (DOMViewer.this.canEdit() && DOMViewer.this.isElementOverlayEnabled);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class Panel
/*      */     extends JPanel
/*      */   {
/*      */     public static final String NODE_INSERTED = "DOMNodeInserted";
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final String NODE_REMOVED = "DOMNodeRemoved";
/*      */ 
/*      */ 
/*      */     
/*      */     public static final String ATTRIBUTE_MODIFIED = "DOMAttrModified";
/*      */ 
/*      */ 
/*      */     
/*      */     public static final String CHAR_DATA_MODIFIED = "DOMCharacterDataModified";
/*      */ 
/*      */ 
/*      */     
/*      */     protected Document document;
/*      */ 
/*      */ 
/*      */     
/*      */     protected EventListener nodeInsertion;
/*      */ 
/*      */ 
/*      */     
/*      */     protected EventListener nodeRemoval;
/*      */ 
/*      */ 
/*      */     
/*      */     protected EventListener attrModification;
/*      */ 
/*      */ 
/*      */     
/*      */     protected EventListener charDataModification;
/*      */ 
/*      */ 
/*      */     
/*      */     protected EventListener capturingListener;
/*      */ 
/*      */ 
/*      */     
/*      */     protected ViewCSS viewCSS;
/*      */ 
/*      */ 
/*      */     
/*      */     protected DOMDocumentTree tree;
/*      */ 
/*      */ 
/*      */     
/*      */     protected JSplitPane splitPane;
/*      */ 
/*      */ 
/*      */     
/*  526 */     protected JPanel rightPanel = new JPanel(new BorderLayout());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  531 */     protected JTable propertiesTable = new JTable();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  536 */     protected NodePickerPanel attributePanel = new NodePickerPanel(new DOMViewer.DOMViewerNodePickerController());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected GridBagConstraints attributePanelLayout;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected GridBagConstraints propertiesTableLayout;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JPanel elementPanel;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected CharacterPanel characterDataPanel;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JTextArea documentInfo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JPanel documentInfoPanel;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class CharacterPanel
/*      */       extends JPanel
/*      */     {
/*      */       protected Node node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  716 */       protected JTextArea textArea = new JTextArea();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public CharacterPanel(BorderLayout layout) {
/*  723 */         super(layout);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public JTextArea getTextArea() {
/*  732 */         return this.textArea;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setTextArea(JTextArea textArea) {
/*  742 */         this.textArea = textArea;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Node getNode() {
/*  751 */         return this.node;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setNode(Node node) {
/*  761 */         this.node = node;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Panel() {
/*  833 */       super(new BorderLayout()); this.attributePanel.addListener(new NodePickerPanel.NodePickerAdapter() { public void updateElement(NodePickerPanel.NodePickerEvent event) { String result = event.getResult(); Element targetElement = (Element)event.getContextNode(); Element newElem = wrapAndParse(result, targetElement); DOMViewer.this.addChangesToHistory(); AbstractCompoundCommand cmd = DOMViewer.this.historyBrowserInterface.createNodeChangedCommand(newElem); Node parent = targetElement.getParentNode(); Node nextSibling = targetElement.getNextSibling(); cmd.addCommand(DOMViewer.this.historyBrowserInterface.createRemoveChildCommand(parent, targetElement)); cmd.addCommand(DOMViewer.this.historyBrowserInterface.createInsertChildCommand(parent, nextSibling, newElem)); DOMViewer.this.historyBrowserInterface.performCompoundUpdateCommand(cmd); DOMViewer.Panel.this.attributePanel.setPreviewElement(newElem); } public void addNewElement(NodePickerPanel.NodePickerEvent event) { String result = event.getResult(); Element targetElement = (Element)event.getContextNode(); Element newElem = wrapAndParse(result, targetElement); DOMViewer.this.addChangesToHistory(); DOMViewer.this.historyBrowserInterface.appendChild(targetElement, newElem); DOMViewer.Panel.this.attributePanel.setPreviewElement(newElem); } private Element wrapAndParse(String toParse, Node startingNode) { Map<Object, Object> prefixMap = new HashMap<Object, Object>(); int j = 0; Node currentNode = startingNode; for (; currentNode != null; currentNode = currentNode.getParentNode()) { NamedNodeMap nMap = currentNode.getAttributes(); for (int i = 0; nMap != null && i < nMap.getLength(); i++) { Attr atr = (Attr)nMap.item(i); String prefix = atr.getPrefix(); String localName = atr.getLocalName(); String namespaceURI = atr.getValue(); if (prefix != null && prefix.equals("xmlns")) { String attrName = "xmlns:" + localName; if (!prefixMap.containsKey(attrName)) prefixMap.put(attrName, namespaceURI);  }  if ((j != 0 || currentNode == DOMViewer.Panel.this.document.getDocumentElement()) && atr.getNodeName().equals("xmlns") && !prefixMap.containsKey("xmlns")) prefixMap.put("xmlns", atr.getNodeValue());  }  j++; }  Document doc = DOMViewer.this.panel.document; SAXDocumentFactory df = new SAXDocumentFactory(doc.getImplementation(), XMLResourceDescriptor.getXMLParserClassName()); URL urlObj = null; if (doc instanceof SVGOMDocument) urlObj = ((SVGOMDocument)doc).getURLObject();  String uri = (urlObj == null) ? "" : urlObj.toString(); Node node = DOMUtilities.parseXML(toParse, doc, uri, prefixMap, "svg", df); return (Element)node.getFirstChild(); } private void selectNewNode(final Element elem) { DOMViewer.this.domViewerController.performUpdate(new Runnable() { public void run() { DOMViewer.Panel.this.selectNode(elem); } }); } }
/*  834 */         ); this.attributePanelLayout = new GridBagConstraints(); this.attributePanelLayout.gridx = 1; this.attributePanelLayout.gridy = 1; this.attributePanelLayout.gridheight = 2; this.attributePanelLayout.weightx = 1.0D; this.attributePanelLayout.weighty = 1.0D; this.attributePanelLayout.fill = 1; this.propertiesTableLayout = new GridBagConstraints(); this.propertiesTableLayout.gridx = 1; this.propertiesTableLayout.gridy = 3; this.propertiesTableLayout.weightx = 1.0D; this.propertiesTableLayout.weighty = 1.0D; this.propertiesTableLayout.fill = 1; this.elementPanel = new JPanel(new GridBagLayout()); JScrollPane pane2 = new JScrollPane(); pane2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 0, 2, 2), BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), DOMViewer.resources.getString("CSSValuesPanel.title")), BorderFactory.createLoweredBevelBorder()))); pane2.getViewport().add(this.propertiesTable); this.elementPanel.add(this.attributePanel, this.attributePanelLayout); this.elementPanel.add(pane2, this.propertiesTableLayout); this.characterDataPanel = new CharacterPanel(new BorderLayout()); this.characterDataPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 0, 2, 2), BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), DOMViewer.resources.getString("CDataPanel.title")), BorderFactory.createLoweredBevelBorder()))); JScrollPane pane = new JScrollPane(); JTextArea textArea = new JTextArea(); this.characterDataPanel.setTextArea(textArea); pane.getViewport().add(textArea); this.characterDataPanel.add(pane); textArea.setEditable(true); textArea.addFocusListener(new FocusAdapter() { public void focusLost(FocusEvent e) { if (DOMViewer.this.canEdit()) { Node contextNode = DOMViewer.Panel.this.characterDataPanel.getNode(); String newValue = DOMViewer.Panel.this.characterDataPanel.getTextArea().getText(); switch (contextNode.getNodeType()) { case 3: case 4: case 8: DOMViewer.this.addChangesToHistory(); DOMViewer.this.historyBrowserInterface.setNodeValue(contextNode, newValue); break; }  }  } }); this.documentInfo = new JTextArea(); this.documentInfoPanel = new JPanel(new BorderLayout()); this.documentInfoPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 0, 2, 2), BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), DOMViewer.resources.getString("DocumentInfoPanel.title")), BorderFactory.createLoweredBevelBorder()))); pane = new JScrollPane(); pane.getViewport().add(this.documentInfo); this.documentInfoPanel.add(pane); this.documentInfo.setEditable(false); setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), DOMViewer.resources.getString("DOMViewerPanel.title")));
/*      */ 
/*      */ 
/*      */       
/*  838 */       JToolBar tb = new JToolBar(DOMViewer.resources.getString("DOMViewerToolbar.name"));
/*      */       
/*  840 */       tb.setFloatable(false);
/*      */ 
/*      */       
/*  843 */       JButton undoButton = DOMViewer.this.getButtonFactory().createJToolbarButton("UndoButton");
/*  844 */       undoButton.setDisabledIcon(new ImageIcon(getClass().getResource(DOMViewer.resources.getString("UndoButton.disabledIcon"))));
/*      */ 
/*      */       
/*  847 */       DropDownComponent undoDD = new DropDownComponent(undoButton);
/*  848 */       undoDD.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
/*  849 */       undoDD.setMaximumSize(new Dimension(44, 25));
/*  850 */       undoDD.setPreferredSize(new Dimension(44, 25));
/*  851 */       tb.add((Component)undoDD);
/*  852 */       DropDownHistoryModel.UndoPopUpMenuModel undoModel = new DropDownHistoryModel.UndoPopUpMenuModel(undoDD.getPopupMenu(), DOMViewer.this.historyBrowserInterface);
/*      */       
/*  854 */       undoDD.getPopupMenu().setModel(undoModel);
/*      */ 
/*      */       
/*  857 */       JButton redoButton = DOMViewer.this.getButtonFactory().createJToolbarButton("RedoButton");
/*  858 */       redoButton.setDisabledIcon(new ImageIcon(getClass().getResource(DOMViewer.resources.getString("RedoButton.disabledIcon"))));
/*      */ 
/*      */       
/*  861 */       DropDownComponent redoDD = new DropDownComponent(redoButton);
/*  862 */       redoDD.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
/*  863 */       redoDD.setMaximumSize(new Dimension(44, 25));
/*  864 */       redoDD.setPreferredSize(new Dimension(44, 25));
/*  865 */       tb.add((Component)redoDD);
/*  866 */       DropDownHistoryModel.RedoPopUpMenuModel redoModel = new DropDownHistoryModel.RedoPopUpMenuModel(redoDD.getPopupMenu(), DOMViewer.this.historyBrowserInterface);
/*      */       
/*  868 */       redoDD.getPopupMenu().setModel(redoModel);
/*      */ 
/*      */       
/*  871 */       JToggleButton capturingClickButton = DOMViewer.this.getButtonFactory().createJToolbarToggleButton("CapturingClickButton");
/*      */       
/*  873 */       capturingClickButton.setEnabled(true);
/*  874 */       capturingClickButton.setPreferredSize(new Dimension(32, 25));
/*  875 */       tb.add(capturingClickButton);
/*      */ 
/*      */       
/*  878 */       DOMViewer.this.overlayButton = DOMViewer.this.getButtonFactory().createJToolbarToggleButton("OverlayButton");
/*      */       
/*  880 */       DOMViewer.this.overlayButton.setEnabled(true);
/*  881 */       DOMViewer.this.overlayButton.setPreferredSize(new Dimension(32, 25));
/*  882 */       tb.add(DOMViewer.this.overlayButton);
/*      */ 
/*      */       
/*  885 */       add(tb, "North");
/*      */ 
/*      */ 
/*      */       
/*  889 */       TreeNode root = new DefaultMutableTreeNode(DOMViewer.resources.getString("EmptyDocument.text"));
/*      */       
/*  891 */       this.tree = new DOMDocumentTree(root, new DOMViewer.DOMViewerDOMDocumentTreeController());
/*      */       
/*  893 */       this.tree.setCellRenderer(new NodeRenderer());
/*  894 */       this.tree.putClientProperty("JTree.lineStyle", "Angled");
/*      */       
/*  896 */       this.tree.addListener(new DOMDocumentTree.DOMDocumentTreeAdapter()
/*      */           {
/*      */             public void dropCompleted(DOMDocumentTree.DOMDocumentTreeEvent event) {
/*  899 */               DOMDocumentTree.DropCompletedInfo info = (DOMDocumentTree.DropCompletedInfo)event.getSource();
/*      */ 
/*      */               
/*  902 */               DOMViewer.this.addChangesToHistory();
/*      */               
/*  904 */               AbstractCompoundCommand cmd = DOMViewer.this.historyBrowserInterface.createNodesDroppedCommand(info.getChildren());
/*      */ 
/*      */               
/*  907 */               int n = info.getChildren().size();
/*  908 */               for (int i = 0; i < n; i++) {
/*  909 */                 Node node = info.getChildren().get(i);
/*      */ 
/*      */                 
/*  912 */                 if (!DOMUtilities.isAnyNodeAncestorOf(info.getChildren(), node))
/*      */                 {
/*  914 */                   cmd.addCommand(DOMViewer.this.historyBrowserInterface.createInsertChildCommand(info.getParent(), info.getSibling(), node));
/*      */                 }
/*      */               } 
/*      */ 
/*      */               
/*  919 */               DOMViewer.this.historyBrowserInterface.performCompoundUpdateCommand(cmd);
/*      */             }
/*      */           });
/*  922 */       this.tree.addTreeSelectionListener(new DOMTreeSelectionListener());
/*  923 */       this.tree.addMouseListener(new TreePopUpListener());
/*  924 */       JScrollPane treePane = new JScrollPane();
/*  925 */       treePane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 2, 2, 0), BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), DOMViewer.resources.getString("DOMViewer.title")), BorderFactory.createLoweredBevelBorder())));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  932 */       treePane.getViewport().add(this.tree);
/*  933 */       this.splitPane = new JSplitPane(1, true, treePane, this.rightPanel);
/*      */ 
/*      */ 
/*      */       
/*  937 */       int loc = DOMViewer.resources.getInteger("SplitPane.dividerLocation");
/*  938 */       this.splitPane.setDividerLocation(loc);
/*  939 */       add(this.splitPane);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setDocument(Document doc) {
/*  946 */       setDocument(doc, (ViewCSS)null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setDocument(Document doc, ViewCSS view) {
/*  953 */       if (this.document != null) {
/*  954 */         if (this.document != doc) {
/*  955 */           removeDomMutationListeners(this.document);
/*  956 */           addDomMutationListeners(doc);
/*  957 */           removeCapturingListener(this.document);
/*  958 */           addCapturingListener(doc);
/*      */         } 
/*      */       } else {
/*      */         
/*  962 */         addDomMutationListeners(doc);
/*  963 */         addCapturingListener(doc);
/*      */       } 
/*  965 */       DOMViewer.this.resetHistory();
/*  966 */       this.document = doc;
/*  967 */       this.viewCSS = view;
/*  968 */       TreeNode root = createTree(doc, DOMViewer.this.showWhitespace);
/*  969 */       ((DefaultTreeModel)this.tree.getModel()).setRoot(root);
/*  970 */       if (this.rightPanel.getComponentCount() != 0) {
/*  971 */         this.rightPanel.remove(0);
/*  972 */         this.splitPane.revalidate();
/*  973 */         this.splitPane.repaint();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void addDomMutationListeners(Document doc) {
/*  984 */       EventTarget target = (EventTarget)doc;
/*  985 */       this.nodeInsertion = new NodeInsertionHandler();
/*  986 */       target.addEventListener("DOMNodeInserted", this.nodeInsertion, true);
/*  987 */       this.nodeRemoval = new NodeRemovalHandler();
/*  988 */       target.addEventListener("DOMNodeRemoved", this.nodeRemoval, true);
/*  989 */       this.attrModification = new AttributeModificationHandler();
/*  990 */       target.addEventListener("DOMAttrModified", this.attrModification, true);
/*      */       
/*  992 */       this.charDataModification = new CharDataModificationHandler();
/*  993 */       target.addEventListener("DOMCharacterDataModified", this.charDataModification, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void removeDomMutationListeners(Document doc) {
/* 1004 */       if (doc != null) {
/* 1005 */         EventTarget target = (EventTarget)doc;
/* 1006 */         target.removeEventListener("DOMNodeInserted", this.nodeInsertion, true);
/* 1007 */         target.removeEventListener("DOMNodeRemoved", this.nodeRemoval, true);
/* 1008 */         target.removeEventListener("DOMAttrModified", this.attrModification, true);
/*      */         
/* 1010 */         target.removeEventListener("DOMCharacterDataModified", this.charDataModification, true);
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
/*      */     protected void addCapturingListener(Document doc) {
/* 1023 */       EventTarget target = (EventTarget)doc.getDocumentElement();
/* 1024 */       this.capturingListener = new CapturingClickHandler();
/* 1025 */       target.addEventListener("click", this.capturingListener, true);
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
/*      */     protected void removeCapturingListener(Document doc) {
/* 1037 */       if (doc != null) {
/* 1038 */         EventTarget target = (EventTarget)doc.getDocumentElement();
/* 1039 */         target.removeEventListener("click", this.capturingListener, true);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class NodeInsertionHandler
/*      */       implements EventListener
/*      */     {
/*      */       public void handleEvent(final Event evt) {
/* 1050 */         Runnable runnable = new Runnable() {
/*      */             public void run() {
/* 1052 */               MutationEvent mevt = (MutationEvent)evt;
/* 1053 */               Node targetNode = (Node)mevt.getTarget();
/* 1054 */               DefaultMutableTreeNode parentNode = DOMViewer.Panel.this.findNode(DOMViewer.Panel.this.tree, targetNode.getParentNode());
/*      */               
/* 1056 */               DefaultMutableTreeNode insertedNode = (DefaultMutableTreeNode)DOMViewer.Panel.this.createTree(targetNode, DOMViewer.this.showWhitespace);
/*      */ 
/*      */               
/* 1059 */               DefaultTreeModel model = (DefaultTreeModel)DOMViewer.Panel.this.tree.getModel();
/*      */               
/* 1061 */               DefaultMutableTreeNode newParentNode = (DefaultMutableTreeNode)DOMViewer.Panel.this.createTree(targetNode.getParentNode(), DOMViewer.this.showWhitespace);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1066 */               int index = DOMViewer.Panel.NodeInsertionHandler.this.findIndexToInsert(parentNode, newParentNode);
/* 1067 */               if (index != -1) {
/* 1068 */                 model.insertNodeInto(insertedNode, parentNode, index);
/*      */               }
/*      */               
/* 1071 */               DOMViewer.Panel.this.attributePanel.updateOnDocumentChange(mevt.getType(), targetNode);
/*      */             }
/*      */           };
/*      */         
/* 1075 */         DOMViewer.Panel.this.refreshGUI(runnable);
/* 1076 */         DOMViewer.Panel.this.registerDocumentChange((MutationEvent)evt);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected int findIndexToInsert(DefaultMutableTreeNode parentNode, DefaultMutableTreeNode newParentNode) {
/* 1092 */         int index = -1;
/* 1093 */         if (parentNode == null || newParentNode == null) {
/* 1094 */           return index;
/*      */         }
/*      */         
/* 1097 */         Enumeration<TreeNode> oldChildren = parentNode.children();
/* 1098 */         Enumeration<TreeNode> newChildren = newParentNode.children();
/* 1099 */         int count = 0;
/* 1100 */         while (oldChildren.hasMoreElements()) {
/*      */           
/* 1102 */           DefaultMutableTreeNode currentOldChild = (DefaultMutableTreeNode)oldChildren.nextElement();
/*      */           
/* 1104 */           DefaultMutableTreeNode currentNewChild = (DefaultMutableTreeNode)newChildren.nextElement();
/*      */           
/* 1106 */           Node oldChild = ((DOMViewer.NodeInfo)currentOldChild.getUserObject()).getNode();
/*      */           
/* 1108 */           Node newChild = ((DOMViewer.NodeInfo)currentNewChild.getUserObject()).getNode();
/*      */           
/* 1110 */           if (oldChild != newChild) {
/* 1111 */             return count;
/*      */           }
/* 1113 */           count++;
/*      */         } 
/* 1115 */         return count;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected class NodeRemovalHandler
/*      */       implements EventListener
/*      */     {
/*      */       public void handleEvent(final Event evt) {
/* 1125 */         Runnable runnable = new Runnable() {
/*      */             public void run() {
/* 1127 */               MutationEvent mevt = (MutationEvent)evt;
/* 1128 */               Node targetNode = (Node)mevt.getTarget();
/* 1129 */               DefaultMutableTreeNode treeNode = DOMViewer.Panel.this.findNode(DOMViewer.Panel.this.tree, targetNode);
/*      */               
/* 1131 */               DefaultTreeModel model = (DefaultTreeModel)DOMViewer.Panel.this.tree.getModel();
/*      */               
/* 1133 */               if (treeNode != null) {
/* 1134 */                 model.removeNodeFromParent(treeNode);
/*      */               }
/* 1136 */               DOMViewer.Panel.this.attributePanel.updateOnDocumentChange(mevt.getType(), targetNode);
/*      */             }
/*      */           };
/*      */         
/* 1140 */         DOMViewer.Panel.this.refreshGUI(runnable);
/* 1141 */         DOMViewer.Panel.this.registerDocumentChange((MutationEvent)evt);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected class AttributeModificationHandler
/*      */       implements EventListener
/*      */     {
/*      */       public void handleEvent(final Event evt) {
/* 1151 */         Runnable runnable = new Runnable() {
/*      */             public void run() {
/* 1153 */               MutationEvent mevt = (MutationEvent)evt;
/* 1154 */               Element targetElement = (Element)mevt.getTarget();
/*      */               
/* 1156 */               DefaultTreeModel model = (DefaultTreeModel)DOMViewer.Panel.this.tree.getModel();
/*      */ 
/*      */               
/* 1159 */               model.nodeChanged(DOMViewer.Panel.this.findNode(DOMViewer.Panel.this.tree, targetElement));
/* 1160 */               DOMViewer.Panel.this.attributePanel.updateOnDocumentChange(mevt.getType(), targetElement);
/*      */             }
/*      */           };
/*      */         
/* 1164 */         DOMViewer.Panel.this.refreshGUI(runnable);
/* 1165 */         DOMViewer.Panel.this.registerDocumentChange((MutationEvent)evt);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected class CharDataModificationHandler
/*      */       implements EventListener
/*      */     {
/*      */       public void handleEvent(final Event evt) {
/* 1174 */         Runnable runnable = new Runnable() {
/*      */             public void run() {
/* 1176 */               MutationEvent mevt = (MutationEvent)evt;
/* 1177 */               Node targetNode = (Node)mevt.getTarget();
/* 1178 */               if (DOMViewer.Panel.this.characterDataPanel.getNode() == targetNode) {
/* 1179 */                 DOMViewer.Panel.this.characterDataPanel.getTextArea().setText(targetNode.getNodeValue());
/*      */                 
/* 1181 */                 DOMViewer.Panel.this.attributePanel.updateOnDocumentChange(mevt.getType(), targetNode);
/*      */               } 
/*      */             }
/*      */           };
/*      */         
/* 1186 */         DOMViewer.Panel.this.refreshGUI(runnable);
/* 1187 */         if (DOMViewer.Panel.this.characterDataPanel.getNode() == evt.getTarget()) {
/* 1188 */           DOMViewer.Panel.this.registerDocumentChange((MutationEvent)evt);
/*      */         }
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
/*      */     protected void refreshGUI(Runnable runnable) {
/* 1201 */       if (DOMViewer.this.canEdit()) {
/*      */         try {
/* 1203 */           SwingUtilities.invokeAndWait(runnable);
/* 1204 */         } catch (InterruptedException e) {
/* 1205 */           e.printStackTrace();
/* 1206 */         } catch (InvocationTargetException e) {
/* 1207 */           e.printStackTrace();
/*      */         } 
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
/*      */     protected void registerNodeInserted(MutationEvent mevt) {
/* 1220 */       Node targetNode = (Node)mevt.getTarget();
/* 1221 */       DOMViewer.this.historyBrowserInterface.addToCurrentCompoundCommand(DOMViewer.this.historyBrowserInterface.createNodeInsertedCommand(targetNode.getParentNode(), targetNode.getNextSibling(), targetNode));
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
/*      */     protected void registerNodeRemoved(MutationEvent mevt) {
/* 1236 */       Node targetNode = (Node)mevt.getTarget();
/* 1237 */       DOMViewer.this.historyBrowserInterface.addToCurrentCompoundCommand(DOMViewer.this.historyBrowserInterface.createNodeRemovedCommand(mevt.getRelatedNode(), targetNode.getNextSibling(), targetNode));
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
/*      */     protected void registerAttributeAdded(MutationEvent mevt) {
/* 1253 */       Element targetElement = (Element)mevt.getTarget();
/* 1254 */       DOMViewer.this.historyBrowserInterface.addToCurrentCompoundCommand(DOMViewer.this.historyBrowserInterface.createAttributeAddedCommand(targetElement, mevt.getAttrName(), mevt.getNewValue(), null));
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
/*      */     protected void registerAttributeRemoved(MutationEvent mevt) {
/* 1272 */       Element targetElement = (Element)mevt.getTarget();
/* 1273 */       DOMViewer.this.historyBrowserInterface.addToCurrentCompoundCommand(DOMViewer.this.historyBrowserInterface.createAttributeRemovedCommand(targetElement, mevt.getAttrName(), mevt.getPrevValue(), null));
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
/*      */     protected void registerAttributeModified(MutationEvent mevt) {
/* 1290 */       Element targetElement = (Element)mevt.getTarget();
/* 1291 */       DOMViewer.this.historyBrowserInterface.addToCurrentCompoundCommand(DOMViewer.this.historyBrowserInterface.createAttributeModifiedCommand(targetElement, mevt.getAttrName(), mevt.getPrevValue(), mevt.getNewValue(), null));
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
/*      */     protected void registerAttributeChanged(MutationEvent mevt) {
/* 1308 */       switch (mevt.getAttrChange()) {
/*      */         case 2:
/* 1310 */           registerAttributeAdded(mevt);
/*      */           return;
/*      */         case 3:
/* 1313 */           registerAttributeRemoved(mevt);
/*      */           return;
/*      */         case 1:
/* 1316 */           registerAttributeModified(mevt);
/*      */           return;
/*      */       } 
/* 1319 */       registerAttributeModified(mevt);
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
/*      */     protected void registerCharDataModified(MutationEvent mevt) {
/* 1332 */       Node targetNode = (Node)mevt.getTarget();
/* 1333 */       DOMViewer.this.historyBrowserInterface.addToCurrentCompoundCommand(DOMViewer.this.historyBrowserInterface.createCharDataModifiedCommand(targetNode, mevt.getPrevValue(), mevt.getNewValue()));
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
/*      */     protected boolean shouldRegisterDocumentChange() {
/* 1356 */       return (DOMViewer.this.canEdit() && DOMViewer.this.historyBrowserInterface.getHistoryBrowser().getState() == 4);
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
/*      */     protected void registerDocumentChange(MutationEvent mevt) {
/* 1371 */       if (shouldRegisterDocumentChange()) {
/* 1372 */         String type = mevt.getType();
/* 1373 */         if (type.equals("DOMNodeInserted")) {
/* 1374 */           registerNodeInserted(mevt);
/* 1375 */         } else if (type.equals("DOMNodeRemoved")) {
/* 1376 */           registerNodeRemoved(mevt);
/* 1377 */         } else if (type.equals("DOMAttrModified")) {
/* 1378 */           registerAttributeChanged(mevt);
/* 1379 */         } else if (type.equals("DOMCharacterDataModified")) {
/* 1380 */           registerCharDataModified(mevt);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected class CapturingClickHandler
/*      */       implements EventListener
/*      */     {
/*      */       public void handleEvent(Event evt) {
/* 1390 */         if (DOMViewer.this.isCapturingClickEnabled) {
/* 1391 */           Element targetElement = (Element)evt.getTarget();
/* 1392 */           DOMViewer.Panel.this.selectNode(targetElement);
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected MutableTreeNode createTree(Node node, boolean showWhitespace) {
/* 1403 */       DefaultMutableTreeNode result = new DefaultMutableTreeNode(new DOMViewer.NodeInfo(node));
/* 1404 */       Node n = node.getFirstChild();
/* 1405 */       for (; n != null; 
/* 1406 */         n = n.getNextSibling()) {
/* 1407 */         if (!showWhitespace && n instanceof org.w3c.dom.Text) {
/* 1408 */           String txt = n.getNodeValue();
/* 1409 */           if (txt.trim().length() == 0)
/*      */             continue; 
/*      */         } 
/* 1412 */         result.add(createTree(n, showWhitespace)); continue;
/*      */       } 
/* 1414 */       if (node instanceof NodeXBL) {
/* 1415 */         Element shadowTree = ((NodeXBL)node).getXblShadowTree();
/* 1416 */         if (shadowTree != null) {
/* 1417 */           DefaultMutableTreeNode shadowNode = new DefaultMutableTreeNode(new DOMViewer.ShadowNodeInfo(shadowTree));
/*      */ 
/*      */           
/* 1420 */           shadowNode.add(createTree(shadowTree, showWhitespace));
/* 1421 */           result.add(shadowNode);
/*      */         } 
/*      */       } 
/* 1424 */       if (node instanceof XBLOMContentElement) {
/* 1425 */         AbstractDocument doc = (AbstractDocument)node.getOwnerDocument();
/*      */         
/* 1427 */         XBLManager xm = doc.getXBLManager();
/* 1428 */         if (xm instanceof DefaultXBLManager) {
/* 1429 */           DefaultMutableTreeNode selectedContentNode = new DefaultMutableTreeNode(new DOMViewer.ContentNodeInfo(node));
/*      */           
/* 1431 */           DefaultXBLManager dxm = (DefaultXBLManager)xm;
/* 1432 */           ContentManager cm = dxm.getContentManager(node);
/* 1433 */           if (cm != null) {
/* 1434 */             NodeList nl = cm.getSelectedContent((XBLOMContentElement)node);
/*      */             
/* 1436 */             for (int i = 0; i < nl.getLength(); i++) {
/* 1437 */               selectedContentNode.add(createTree(nl.item(i), showWhitespace));
/*      */             }
/*      */             
/* 1440 */             result.add(selectedContentNode);
/*      */           } 
/*      */         } 
/*      */       } 
/* 1444 */       return result;
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
/*      */     protected DefaultMutableTreeNode findNode(JTree theTree, Node node) {
/* 1458 */       DefaultMutableTreeNode root = (DefaultMutableTreeNode)theTree.getModel().getRoot();
/*      */       
/* 1460 */       Enumeration<TreeNode> treeNodes = root.breadthFirstEnumeration();
/* 1461 */       while (treeNodes.hasMoreElements()) {
/* 1462 */         DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)treeNodes.nextElement();
/*      */         
/* 1464 */         DOMViewer.NodeInfo userObject = (DOMViewer.NodeInfo)currentNode.getUserObject();
/* 1465 */         if (userObject.getNode() == node) {
/* 1466 */           return currentNode;
/*      */         }
/*      */       } 
/* 1469 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void selectNode(final Node targetNode) {
/* 1479 */       SwingUtilities.invokeLater(new Runnable() {
/*      */             public void run() {
/* 1481 */               DefaultMutableTreeNode node = DOMViewer.Panel.this.findNode(DOMViewer.Panel.this.tree, targetNode);
/* 1482 */               if (node != null) {
/* 1483 */                 TreeNode[] path = node.getPath();
/* 1484 */                 TreePath tp = new TreePath((Object[])path);
/*      */                 
/* 1486 */                 DOMViewer.Panel.this.tree.setSelectionPath(tp);
/*      */ 
/*      */                 
/* 1489 */                 DOMViewer.Panel.this.tree.scrollPathToVisible(tp);
/*      */               } 
/*      */             }
/*      */           });
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class TreePopUpListener
/*      */       extends MouseAdapter
/*      */     {
/*      */       protected JPopupMenu treePopupMenu;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public TreePopUpListener() {
/* 1509 */         this.treePopupMenu = new JPopupMenu();
/*      */ 
/*      */         
/* 1512 */         this.treePopupMenu.add(DOMViewer.Panel.this.createTemplatesMenu(DOMViewer.resources.getString("ContextMenuItem.insertNewNode")));
/*      */ 
/*      */ 
/*      */         
/* 1516 */         JMenuItem item = new JMenuItem(DOMViewer.resources.getString("ContextMenuItem.createNewElement"));
/*      */         
/* 1518 */         this.treePopupMenu.add(item);
/* 1519 */         item.addActionListener(new DOMViewer.Panel.TreeNodeAdder());
/*      */ 
/*      */         
/* 1522 */         item = new JMenuItem(DOMViewer.resources.getString("ContextMenuItem.removeSelection"));
/*      */         
/* 1524 */         item.addActionListener(new DOMViewer.Panel.TreeNodeRemover());
/* 1525 */         this.treePopupMenu.add(item);
/*      */       }
/*      */ 
/*      */       
/*      */       public void mouseReleased(MouseEvent e) {
/* 1530 */         if (e.isPopupTrigger() && e.getClickCount() == 1 && 
/* 1531 */           DOMViewer.Panel.this.tree.getSelectionPaths() != null) {
/* 1532 */           showPopUp(e);
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public void mousePressed(MouseEvent e) {
/* 1539 */         JTree sourceTree = (JTree)e.getSource();
/* 1540 */         TreePath targetPath = sourceTree.getPathForLocation(e.getX(), e.getY());
/*      */         
/* 1542 */         if (!e.isControlDown() && !e.isShiftDown()) {
/* 1543 */           sourceTree.setSelectionPath(targetPath);
/*      */         } else {
/* 1545 */           sourceTree.addSelectionPath(targetPath);
/*      */         } 
/*      */         
/* 1548 */         if (e.isPopupTrigger() && e.getClickCount() == 1) {
/* 1549 */           showPopUp(e);
/*      */         }
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private void showPopUp(MouseEvent e) {
/* 1558 */         if (DOMViewer.this.canEdit()) {
/* 1559 */           TreePath path = DOMViewer.Panel.this.tree.getPathForLocation(e.getX(), e.getY());
/*      */           
/* 1561 */           if (path != null && path.getPathCount() > 1) {
/* 1562 */             this.treePopupMenu.show((Component)e.getSource(), e.getX(), e.getY());
/*      */           }
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class TreeNodeAdder
/*      */       implements ActionListener
/*      */     {
/*      */       public void actionPerformed(ActionEvent e) {
/* 1576 */         NodePickerPanel.NameEditorDialog nameEditorDialog = new NodePickerPanel.NameEditorDialog(DOMViewer.this);
/*      */         
/* 1578 */         nameEditorDialog.setLocationRelativeTo(DOMViewer.this);
/* 1579 */         int results = nameEditorDialog.showDialog();
/* 1580 */         if (results == 0) {
/* 1581 */           Element elementToAdd = DOMViewer.Panel.this.document.createElementNS("http://www.w3.org/2000/svg", nameEditorDialog.getResults());
/*      */ 
/*      */           
/* 1584 */           if (DOMViewer.Panel.this.rightPanel.getComponentCount() != 0) {
/* 1585 */             DOMViewer.Panel.this.rightPanel.remove(0);
/*      */           }
/* 1587 */           DOMViewer.Panel.this.rightPanel.add(DOMViewer.Panel.this.elementPanel);
/*      */ 
/*      */           
/* 1590 */           TreePath[] treePaths = DOMViewer.Panel.this.tree.getSelectionPaths();
/* 1591 */           if (treePaths != null) {
/* 1592 */             TreePath treePath = treePaths[treePaths.length - 1];
/* 1593 */             DefaultMutableTreeNode node = (DefaultMutableTreeNode)treePath.getLastPathComponent();
/*      */             
/* 1595 */             DOMViewer.NodeInfo nodeInfo = (DOMViewer.NodeInfo)node.getUserObject();
/*      */             
/* 1597 */             DOMViewer.Panel.this.attributePanel.enterAddNewElementMode(elementToAdd, nodeInfo.getNode());
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class NodeTemplateParser
/*      */       implements ActionListener
/*      */     {
/*      */       protected String toParse;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected short nodeType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public NodeTemplateParser(String toParse, short nodeType) {
/* 1626 */         this.toParse = toParse;
/* 1627 */         this.nodeType = nodeType; } public void actionPerformed(ActionEvent e) { URL urlObj; String uri;
/*      */         Map<Object, Object> prefixes;
/*      */         SAXDocumentFactory df;
/*      */         DocumentFragment documentFragment;
/* 1631 */         Node nodeToAdd = null;
/* 1632 */         switch (this.nodeType) {
/*      */           case 1:
/* 1634 */             urlObj = null;
/* 1635 */             if (DOMViewer.Panel.this.document instanceof SVGOMDocument) {
/* 1636 */               urlObj = ((SVGOMDocument)DOMViewer.Panel.this.document).getURLObject();
/*      */             }
/* 1638 */             uri = (urlObj == null) ? "" : urlObj.toString();
/* 1639 */             prefixes = new HashMap<Object, Object>();
/* 1640 */             prefixes.put("xmlns", "http://www.w3.org/2000/svg");
/*      */             
/* 1642 */             prefixes.put("xmlns:xlink", "http://www.w3.org/1999/xlink");
/*      */ 
/*      */             
/* 1645 */             df = new SAXDocumentFactory(DOMViewer.Panel.this.document.getImplementation(), XMLResourceDescriptor.getXMLParserClassName());
/*      */ 
/*      */             
/* 1648 */             documentFragment = (DocumentFragment)DOMUtilities.parseXML(this.toParse, DOMViewer.Panel.this.document, uri, prefixes, "svg", df);
/*      */ 
/*      */             
/* 1651 */             nodeToAdd = documentFragment.getFirstChild();
/*      */             break;
/*      */           case 3:
/* 1654 */             nodeToAdd = DOMViewer.Panel.this.document.createTextNode(this.toParse);
/*      */             break;
/*      */           case 8:
/* 1657 */             nodeToAdd = DOMViewer.Panel.this.document.createComment(this.toParse);
/*      */             break;
/*      */           case 4:
/* 1660 */             nodeToAdd = DOMViewer.Panel.this.document.createCDATASection(this.toParse);
/*      */             break;
/*      */         } 
/*      */         
/* 1664 */         TreePath[] treePaths = DOMViewer.Panel.this.tree.getSelectionPaths();
/* 1665 */         if (treePaths != null) {
/* 1666 */           TreePath treePath = treePaths[treePaths.length - 1];
/* 1667 */           DefaultMutableTreeNode node = (DefaultMutableTreeNode)treePath.getLastPathComponent();
/*      */           
/* 1669 */           DOMViewer.NodeInfo nodeInfo = (DOMViewer.NodeInfo)node.getUserObject();
/*      */           
/* 1671 */           DOMViewer.this.addChangesToHistory();
/*      */           
/* 1673 */           DOMViewer.this.historyBrowserInterface.appendChild(nodeInfo.getNode(), nodeToAdd);
/*      */         }  }
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
/*      */     protected JMenu createTemplatesMenu(String name) {
/* 1687 */       NodeTemplates templates = new NodeTemplates();
/* 1688 */       JMenu submenu = new JMenu(name);
/*      */ 
/*      */       
/* 1691 */       HashMap<Object, Object> menuMap = new HashMap<Object, Object>();
/* 1692 */       ArrayList categoriesList = templates.getCategories();
/* 1693 */       int n = categoriesList.size();
/* 1694 */       for (Object aCategoriesList : categoriesList) {
/* 1695 */         String category = aCategoriesList.toString();
/* 1696 */         JMenu currentMenu = new JMenu(category);
/* 1697 */         submenu.add(currentMenu);
/*      */         
/* 1699 */         menuMap.put(category, currentMenu);
/*      */       } 
/*      */ 
/*      */       
/* 1703 */       ArrayList<?> values = new ArrayList(templates.getNodeTemplatesMap().values());
/*      */       
/* 1705 */       Collections.sort(values, new Comparator() {
/*      */             public int compare(Object o1, Object o2) {
/* 1707 */               NodeTemplates.NodeTemplateDescriptor n1 = (NodeTemplates.NodeTemplateDescriptor)o1;
/* 1708 */               NodeTemplates.NodeTemplateDescriptor n2 = (NodeTemplates.NodeTemplateDescriptor)o2;
/* 1709 */               return n1.getName().compareTo(n2.getName());
/*      */             }
/*      */           });
/* 1712 */       for (Object value : values) {
/* 1713 */         NodeTemplates.NodeTemplateDescriptor desc = (NodeTemplates.NodeTemplateDescriptor)value;
/*      */         
/* 1715 */         String toParse = desc.getXmlValue();
/* 1716 */         short nodeType = desc.getType();
/* 1717 */         String nodeCategory = desc.getCategory();
/* 1718 */         JMenuItem currentItem = new JMenuItem(desc.getName());
/* 1719 */         currentItem.addActionListener(new NodeTemplateParser(toParse, nodeType));
/*      */         
/* 1721 */         JMenu currentSubmenu = (JMenu)menuMap.get(nodeCategory);
/* 1722 */         currentSubmenu.add(currentItem);
/*      */       } 
/* 1724 */       return submenu;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected class TreeNodeRemover
/*      */       implements ActionListener
/*      */     {
/*      */       public void actionPerformed(ActionEvent e) {
/* 1733 */         DOMViewer.this.addChangesToHistory();
/*      */         
/* 1735 */         AbstractCompoundCommand cmd = DOMViewer.this.historyBrowserInterface.createRemoveSelectedTreeNodesCommand(null);
/*      */         
/* 1737 */         TreePath[] treePaths = DOMViewer.Panel.this.tree.getSelectionPaths();
/* 1738 */         for (int i = 0; treePaths != null && i < treePaths.length; i++) {
/* 1739 */           TreePath treePath = treePaths[i];
/* 1740 */           DefaultMutableTreeNode node = (DefaultMutableTreeNode)treePath.getLastPathComponent();
/*      */           
/* 1742 */           DOMViewer.NodeInfo nodeInfo = (DOMViewer.NodeInfo)node.getUserObject();
/* 1743 */           if (DOMUtilities.isParentOf(nodeInfo.getNode(), nodeInfo.getNode().getParentNode()))
/*      */           {
/* 1745 */             cmd.addCommand(DOMViewer.this.historyBrowserInterface.createRemoveChildCommand(nodeInfo.getNode().getParentNode(), nodeInfo.getNode()));
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1750 */         DOMViewer.this.historyBrowserInterface.performCompoundUpdateCommand(cmd);
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
/*      */     protected class DOMTreeSelectionListener
/*      */       implements TreeSelectionListener
/*      */     {
/*      */       public void valueChanged(TreeSelectionEvent ev) {
/* 1765 */         if (DOMViewer.this.elementOverlayManager != null) {
/* 1766 */           handleElementSelection(ev);
/*      */         }
/*      */ 
/*      */         
/* 1770 */         DefaultMutableTreeNode mtn = (DefaultMutableTreeNode)DOMViewer.Panel.this.tree.getLastSelectedPathComponent();
/*      */ 
/*      */         
/* 1773 */         if (mtn == null) {
/*      */           return;
/*      */         }
/*      */         
/* 1777 */         if (DOMViewer.Panel.this.rightPanel.getComponentCount() != 0) {
/* 1778 */           DOMViewer.Panel.this.rightPanel.remove(0);
/*      */         }
/*      */         
/* 1781 */         Object nodeInfo = mtn.getUserObject();
/* 1782 */         if (nodeInfo instanceof DOMViewer.NodeInfo) {
/* 1783 */           Node node = ((DOMViewer.NodeInfo)nodeInfo).getNode();
/* 1784 */           switch (node.getNodeType()) {
/*      */             case 9:
/* 1786 */               DOMViewer.Panel.this.documentInfo.setText(createDocumentText((Document)node));
/*      */               
/* 1788 */               DOMViewer.Panel.this.rightPanel.add(DOMViewer.Panel.this.documentInfoPanel);
/*      */               break;
/*      */             case 1:
/* 1791 */               DOMViewer.Panel.this.propertiesTable.setModel(new DOMViewer.Panel.NodeCSSValuesModel(node));
/* 1792 */               DOMViewer.Panel.this.attributePanel.promptForChanges();
/* 1793 */               DOMViewer.Panel.this.attributePanel.setPreviewElement((Element)node);
/* 1794 */               DOMViewer.Panel.this.rightPanel.add(DOMViewer.Panel.this.elementPanel);
/*      */               break;
/*      */             case 3:
/*      */             case 4:
/*      */             case 8:
/* 1799 */               DOMViewer.Panel.this.characterDataPanel.setNode(node);
/* 1800 */               DOMViewer.Panel.this.characterDataPanel.getTextArea().setText(node.getNodeValue());
/*      */               
/* 1802 */               DOMViewer.Panel.this.rightPanel.add(DOMViewer.Panel.this.characterDataPanel);
/*      */               break;
/*      */           } 
/*      */         } 
/* 1806 */         DOMViewer.Panel.this.splitPane.revalidate();
/* 1807 */         DOMViewer.Panel.this.splitPane.repaint();
/*      */       }
/*      */       
/*      */       protected String createDocumentText(Document doc) {
/* 1811 */         StringBuffer sb = new StringBuffer();
/* 1812 */         sb.append("Nodes: ");
/* 1813 */         sb.append(nodeCount(doc));
/* 1814 */         return sb.toString();
/*      */       }
/*      */       
/*      */       protected int nodeCount(Node node) {
/* 1818 */         int result = 1;
/* 1819 */         Node n = node.getFirstChild();
/* 1820 */         for (; n != null; 
/* 1821 */           n = n.getNextSibling()) {
/* 1822 */           result += nodeCount(n);
/*      */         }
/* 1824 */         return result;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected void handleElementSelection(TreeSelectionEvent ev) {
/* 1834 */         TreePath[] paths = ev.getPaths();
/* 1835 */         for (TreePath path : paths) {
/* 1836 */           DefaultMutableTreeNode mtn = (DefaultMutableTreeNode)path.getLastPathComponent();
/*      */           
/* 1838 */           Object nodeInfo = mtn.getUserObject();
/* 1839 */           if (nodeInfo instanceof DOMViewer.NodeInfo) {
/* 1840 */             Node node = ((DOMViewer.NodeInfo)nodeInfo).getNode();
/* 1841 */             if (node.getNodeType() == 1) {
/* 1842 */               if (ev.isAddedPath(path)) {
/* 1843 */                 DOMViewer.this.elementOverlayManager.addElement((Element)node);
/*      */               } else {
/*      */                 
/* 1846 */                 DOMViewer.this.elementOverlayManager.removeElement((Element)node);
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/* 1852 */         DOMViewer.this.elementOverlayManager.repaint();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class NodeRenderer
/*      */       extends DefaultTreeCellRenderer
/*      */     {
/*      */       protected ImageIcon elementIcon;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected ImageIcon commentIcon;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected ImageIcon piIcon;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected ImageIcon textIcon;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public NodeRenderer() {
/* 1886 */         String s = DOMViewer.resources.getString("Element.icon");
/* 1887 */         this.elementIcon = new ImageIcon(getClass().getResource(s));
/* 1888 */         s = DOMViewer.resources.getString("Comment.icon");
/* 1889 */         this.commentIcon = new ImageIcon(getClass().getResource(s));
/* 1890 */         s = DOMViewer.resources.getString("PI.icon");
/* 1891 */         this.piIcon = new ImageIcon(getClass().getResource(s));
/* 1892 */         s = DOMViewer.resources.getString("Text.icon");
/* 1893 */         this.textIcon = new ImageIcon(getClass().getResource(s));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
/* 1906 */         super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
/*      */         
/* 1908 */         switch (getNodeType(value)) {
/*      */           case 1:
/* 1910 */             setIcon(this.elementIcon);
/*      */             break;
/*      */           case 8:
/* 1913 */             setIcon(this.commentIcon);
/*      */             break;
/*      */           case 7:
/* 1916 */             setIcon(this.piIcon);
/*      */             break;
/*      */           case 3:
/*      */           case 4:
/* 1920 */             setIcon(this.textIcon);
/*      */             break;
/*      */         } 
/* 1923 */         return this;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected short getNodeType(Object value) {
/* 1931 */         DefaultMutableTreeNode mtn = (DefaultMutableTreeNode)value;
/* 1932 */         Object obj = mtn.getUserObject();
/* 1933 */         if (obj instanceof DOMViewer.NodeInfo) {
/* 1934 */           Node node = ((DOMViewer.NodeInfo)obj).getNode();
/* 1935 */           return node.getNodeType();
/*      */         } 
/* 1937 */         return -1;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class NodeCSSValuesModel
/*      */       extends AbstractTableModel
/*      */     {
/*      */       protected Node node;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected CSSStyleDeclaration style;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       protected List propertyNames;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public NodeCSSValuesModel(Node n) {
/* 1965 */         this.node = n;
/* 1966 */         if (DOMViewer.Panel.this.viewCSS != null) {
/* 1967 */           this.style = DOMViewer.Panel.this.viewCSS.getComputedStyle((Element)n, null);
/* 1968 */           this.propertyNames = new ArrayList();
/* 1969 */           if (this.style != null) {
/* 1970 */             for (int i = 0; i < this.style.getLength(); i++) {
/* 1971 */               this.propertyNames.add(this.style.item(i));
/*      */             }
/* 1973 */             Collections.sort(this.propertyNames);
/*      */           } 
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public String getColumnName(int col) {
/* 1982 */         if (col == 0) {
/* 1983 */           return DOMViewer.resources.getString("CSSValuesTable.column1");
/*      */         }
/* 1985 */         return DOMViewer.resources.getString("CSSValuesTable.column2");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getColumnCount() {
/* 1993 */         return 2;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public int getRowCount() {
/* 2000 */         if (this.style == null) {
/* 2001 */           return 0;
/*      */         }
/* 2003 */         return this.style.getLength();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public boolean isCellEditable(int row, int col) {
/* 2010 */         return false;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public Object getValueAt(int row, int col) {
/* 2017 */         String prop = this.propertyNames.get(row);
/* 2018 */         if (col == 0) {
/* 2019 */           return prop;
/*      */         }
/* 2021 */         return this.style.getPropertyValue(prop);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class NodeInfo
/*      */   {
/*      */     protected Node node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NodeInfo(Node n) {
/* 2041 */       this.node = n;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Node getNode() {
/* 2048 */       return this.node;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2055 */       if (this.node instanceof Element) {
/* 2056 */         Element e = (Element)this.node;
/* 2057 */         String id = e.getAttribute("id");
/* 2058 */         if (id.length() != 0) {
/* 2059 */           return this.node.getNodeName() + " \"" + id + "\"";
/*      */         }
/*      */       } 
/* 2062 */       return this.node.getNodeName();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ShadowNodeInfo
/*      */     extends NodeInfo
/*      */   {
/*      */     public ShadowNodeInfo(Node n) {
/* 2075 */       super(n);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2082 */       return "shadow tree";
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ContentNodeInfo
/*      */     extends NodeInfo
/*      */   {
/*      */     public ContentNodeInfo(Node n) {
/* 2096 */       super(n);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String toString() {
/* 2103 */       return "selected content";
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/DOMViewer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */