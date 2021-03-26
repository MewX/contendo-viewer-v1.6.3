/*      */ package org.apache.batik.apps.svgbrowser;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Component;
/*      */ import java.awt.Frame;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.Insets;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.io.IOException;
/*      */ import java.io.StringReader;
/*      */ import java.util.EventListener;
/*      */ import java.util.EventObject;
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Vector;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.event.DocumentEvent;
/*      */ import javax.swing.event.DocumentListener;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import javax.swing.event.TableModelEvent;
/*      */ import javax.swing.event.TableModelListener;
/*      */ import javax.swing.table.DefaultTableModel;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import org.apache.batik.dom.AbstractNode;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.util.gui.resource.ActionMap;
/*      */ import org.apache.batik.util.gui.resource.ButtonFactory;
/*      */ import org.apache.batik.util.gui.resource.MissingListenerException;
/*      */ import org.apache.batik.util.gui.xmleditor.XMLTextEditor;
/*      */ import org.apache.batik.util.resources.ResourceManager;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.xml.sax.ErrorHandler;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NodePickerPanel
/*      */   extends JPanel
/*      */   implements ActionMap
/*      */ {
/*      */   private static final int VIEW_MODE = 1;
/*      */   private static final int EDIT_MODE = 2;
/*      */   private static final int ADD_NEW_ELEMENT = 3;
/*      */   private static final String RESOURCES = "org.apache.batik.apps.svgbrowser.resources.NodePickerPanelMessages";
/*  119 */   private static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.NodePickerPanelMessages", Locale.getDefault());
/*  120 */   private static ResourceManager resources = new ResourceManager(bundle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JTable attributesTable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private TableModelListener tableModelListener;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JScrollPane attributePane;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JPanel attributesPanel;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ButtonFactory buttonFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JButton addButton;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JButton removeButton;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JLabel attributesLabel;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JButton applyButton;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JButton resetButton;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JPanel choosePanel;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SVGInputPanel svgInputPanel;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JLabel isWellFormedLabel;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JLabel svgInputPanelNameLabel;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean shouldProcessUpdate = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Element previewElement;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Element clonedElement;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Node parentElement;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int mode;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isDirty;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  233 */   private EventListenerList eventListeners = new EventListenerList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private NodePickerController controller;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  244 */   private Map listeners = new HashMap<Object, Object>(10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodePickerPanel(NodePickerController controller) {
/*  253 */     super(new GridBagLayout());
/*  254 */     this.controller = controller;
/*  255 */     initialize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initialize() {
/*  263 */     addButtonActions();
/*      */ 
/*      */     
/*  266 */     GridBagConstraints grid = new GridBagConstraints();
/*  267 */     grid.gridx = 1;
/*  268 */     grid.gridy = 1;
/*  269 */     grid.anchor = 18;
/*  270 */     grid.fill = 0;
/*  271 */     grid.insets = new Insets(5, 5, 0, 5);
/*  272 */     this.attributesLabel = new JLabel();
/*  273 */     String attributesLabelValue = resources.getString("AttributesTable.name");
/*      */     
/*  275 */     this.attributesLabel.setText(attributesLabelValue);
/*  276 */     add(this.attributesLabel, grid);
/*      */     
/*  278 */     grid.gridx = 1;
/*  279 */     grid.gridy = 2;
/*  280 */     grid.gridwidth = 2;
/*  281 */     grid.weightx = 1.0D;
/*  282 */     grid.weighty = 0.3D;
/*  283 */     grid.fill = 1;
/*  284 */     grid.anchor = 10;
/*  285 */     grid.insets = new Insets(0, 0, 0, 5);
/*  286 */     add(getAttributesPanel(), grid);
/*      */     
/*  288 */     grid.weightx = 0.0D;
/*  289 */     grid.weighty = 0.0D;
/*  290 */     grid.gridwidth = 1;
/*  291 */     grid.gridx = 1;
/*  292 */     grid.gridy = 3;
/*  293 */     grid.anchor = 18;
/*  294 */     grid.fill = 0;
/*  295 */     grid.insets = new Insets(0, 5, 0, 5);
/*  296 */     this.svgInputPanelNameLabel = new JLabel();
/*  297 */     String svgInputLabelValue = resources.getString("InputPanelLabel.name");
/*  298 */     this.svgInputPanelNameLabel.setText(svgInputLabelValue);
/*  299 */     add(this.svgInputPanelNameLabel, grid);
/*      */     
/*  301 */     grid.gridx = 1;
/*  302 */     grid.gridy = 4;
/*  303 */     grid.gridwidth = 2;
/*  304 */     grid.weightx = 1.0D;
/*  305 */     grid.weighty = 1.0D;
/*  306 */     grid.fill = 1;
/*  307 */     grid.anchor = 10;
/*  308 */     grid.insets = new Insets(0, 5, 0, 10);
/*  309 */     add(getSvgInputPanel(), grid);
/*      */     
/*  311 */     grid.weightx = 0.0D;
/*  312 */     grid.weighty = 0.0D;
/*  313 */     grid.gridwidth = 1;
/*  314 */     grid.gridx = 1;
/*  315 */     grid.gridy = 5;
/*  316 */     grid.anchor = 18;
/*  317 */     grid.fill = 0;
/*  318 */     grid.insets = new Insets(5, 5, 0, 5);
/*  319 */     this.isWellFormedLabel = new JLabel();
/*  320 */     String isWellFormedLabelVal = resources.getString("IsWellFormedLabel.wellFormed");
/*      */     
/*  322 */     this.isWellFormedLabel.setText(isWellFormedLabelVal);
/*  323 */     add(this.isWellFormedLabel, grid);
/*      */     
/*  325 */     grid.weightx = 0.0D;
/*  326 */     grid.weighty = 0.0D;
/*  327 */     grid.gridwidth = 1;
/*  328 */     grid.gridx = 2;
/*  329 */     grid.gridy = 5;
/*  330 */     grid.anchor = 13;
/*  331 */     grid.insets = new Insets(0, 0, 0, 5);
/*  332 */     add(getChoosePanel(), grid);
/*      */ 
/*      */     
/*  335 */     enterViewMode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ButtonFactory getButtonFactory() {
/*  342 */     if (this.buttonFactory == null) {
/*  343 */       this.buttonFactory = new ButtonFactory(bundle, this);
/*      */     }
/*  345 */     return this.buttonFactory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addButtonActions() {
/*  352 */     this.listeners.put("ApplyButtonAction", new ApplyButtonAction());
/*  353 */     this.listeners.put("ResetButtonAction", new ResetButtonAction());
/*  354 */     this.listeners.put("AddButtonAction", new AddButtonAction());
/*  355 */     this.listeners.put("RemoveButtonAction", new RemoveButtonAction());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JButton getAddButton() {
/*  362 */     if (this.addButton == null) {
/*  363 */       this.addButton = getButtonFactory().createJButton("AddButton");
/*  364 */       this.addButton.addFocusListener(new NodePickerEditListener());
/*      */     } 
/*  366 */     return this.addButton;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JButton getRemoveButton() {
/*  373 */     if (this.removeButton == null) {
/*  374 */       this.removeButton = getButtonFactory().createJButton("RemoveButton");
/*  375 */       this.removeButton.addFocusListener(new NodePickerEditListener());
/*      */     } 
/*  377 */     return this.removeButton;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JButton getApplyButton() {
/*  384 */     if (this.applyButton == null) {
/*  385 */       this.applyButton = getButtonFactory().createJButton("ApplyButton");
/*      */     }
/*  387 */     return this.applyButton;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JButton getResetButton() {
/*  394 */     if (this.resetButton == null) {
/*  395 */       this.resetButton = getButtonFactory().createJButton("ResetButton");
/*      */     }
/*  397 */     return this.resetButton;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JPanel getAttributesPanel() {
/*  404 */     if (this.attributesPanel == null) {
/*  405 */       this.attributesPanel = new JPanel(new GridBagLayout());
/*      */       
/*  407 */       GridBagConstraints g11 = new GridBagConstraints();
/*  408 */       g11.gridx = 1;
/*  409 */       g11.gridy = 1;
/*  410 */       g11.fill = 1;
/*  411 */       g11.anchor = 10;
/*  412 */       g11.weightx = 4.0D;
/*  413 */       g11.weighty = 1.0D;
/*  414 */       g11.gridheight = 5;
/*  415 */       g11.gridwidth = 2;
/*  416 */       g11.insets = new Insets(5, 5, 5, 0);
/*      */       
/*  418 */       GridBagConstraints g12 = new GridBagConstraints();
/*  419 */       g12.gridx = 3;
/*  420 */       g12.gridy = 1;
/*  421 */       g12.fill = 2;
/*  422 */       g12.anchor = 11;
/*  423 */       g12.insets = new Insets(5, 20, 0, 5);
/*  424 */       g12.weightx = 1.0D;
/*      */       
/*  426 */       GridBagConstraints g32 = new GridBagConstraints();
/*  427 */       g32.gridx = 3;
/*  428 */       g32.gridy = 3;
/*  429 */       g32.fill = 2;
/*  430 */       g32.anchor = 11;
/*  431 */       g32.insets = new Insets(5, 20, 0, 5);
/*  432 */       g32.weightx = 1.0D;
/*      */       
/*  434 */       this.attributesTable = new JTable();
/*  435 */       this.attributesTable.setModel(new AttributesTableModel(10, 2));
/*  436 */       this.tableModelListener = new AttributesTableModelListener();
/*  437 */       this.attributesTable.getModel().addTableModelListener(this.tableModelListener);
/*      */       
/*  439 */       this.attributesTable.addFocusListener(new NodePickerEditListener());
/*  440 */       this.attributePane = new JScrollPane();
/*  441 */       this.attributePane.getViewport().add(this.attributesTable);
/*      */       
/*  443 */       this.attributesPanel.add(this.attributePane, g11);
/*  444 */       this.attributesPanel.add(getAddButton(), g12);
/*  445 */       this.attributesPanel.add(getRemoveButton(), g32);
/*      */     } 
/*  447 */     return this.attributesPanel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SVGInputPanel getSvgInputPanel() {
/*  454 */     if (this.svgInputPanel == null) {
/*  455 */       this.svgInputPanel = new SVGInputPanel();
/*  456 */       this.svgInputPanel.getNodeXmlArea().getDocument().addDocumentListener(new XMLAreaListener());
/*      */       
/*  458 */       this.svgInputPanel.getNodeXmlArea().addFocusListener(new NodePickerEditListener());
/*      */     } 
/*      */     
/*  461 */     return this.svgInputPanel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JPanel getChoosePanel() {
/*  468 */     if (this.choosePanel == null) {
/*  469 */       this.choosePanel = new JPanel(new GridBagLayout());
/*      */       
/*  471 */       GridBagConstraints g11 = new GridBagConstraints();
/*  472 */       g11.gridx = 1;
/*  473 */       g11.gridy = 1;
/*  474 */       g11.weightx = 0.5D;
/*  475 */       g11.anchor = 17;
/*  476 */       g11.fill = 2;
/*  477 */       g11.insets = new Insets(5, 5, 5, 5);
/*      */       
/*  479 */       GridBagConstraints g12 = new GridBagConstraints();
/*  480 */       g12.gridx = 2;
/*  481 */       g12.gridy = 1;
/*  482 */       g12.weightx = 0.5D;
/*  483 */       g12.anchor = 13;
/*  484 */       g12.fill = 2;
/*  485 */       g12.insets = new Insets(5, 5, 5, 5);
/*      */       
/*  487 */       this.choosePanel.add(getApplyButton(), g11);
/*  488 */       this.choosePanel.add(getResetButton(), g12);
/*      */     } 
/*  490 */     return this.choosePanel;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getResults() {
/*  498 */     return getSvgInputPanel().getNodeXmlArea().getText();
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
/*      */   private void updateViewAfterSvgInput(Element referentElement, Element elementToUpdate) {
/*  512 */     if (referentElement != null) {
/*  513 */       String isWellFormedLabelVal = resources.getString("IsWellFormedLabel.wellFormed");
/*      */       
/*  515 */       this.isWellFormedLabel.setText(isWellFormedLabelVal);
/*  516 */       getApplyButton().setEnabled(true);
/*  517 */       this.attributesTable.setEnabled(true);
/*  518 */       updateElementAttributes(elementToUpdate, referentElement);
/*  519 */       this.shouldProcessUpdate = false;
/*  520 */       updateAttributesTable(elementToUpdate);
/*  521 */       this.shouldProcessUpdate = true;
/*      */     } else {
/*  523 */       String isWellFormedLabelVal = resources.getString("IsWellFormedLabel.notWellFormed");
/*      */       
/*  525 */       this.isWellFormedLabel.setText(isWellFormedLabelVal);
/*  526 */       getApplyButton().setEnabled(false);
/*  527 */       this.attributesTable.setEnabled(false);
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
/*      */   private void updateElementAttributes(Element elem, Element referentElement) {
/*  542 */     removeAttributes(elem);
/*      */ 
/*      */     
/*  545 */     NamedNodeMap newNodeMap = referentElement.getAttributes();
/*  546 */     for (int i = newNodeMap.getLength() - 1; i >= 0; i--) {
/*  547 */       Node newAttr = newNodeMap.item(i);
/*  548 */       String qualifiedName = newAttr.getNodeName();
/*  549 */       String attributeValue = newAttr.getNodeValue();
/*  550 */       String prefix = DOMUtilities.getPrefix(qualifiedName);
/*  551 */       String namespaceURI = getNamespaceURI(prefix);
/*  552 */       elem.setAttributeNS(namespaceURI, qualifiedName, attributeValue);
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
/*      */   private void updateElementAttributes(Element element, AttributesTableModel tableModel) {
/*  569 */     removeAttributes(element);
/*      */ 
/*      */ 
/*      */     
/*  573 */     for (int i = 0; i < tableModel.getRowCount(); i++) {
/*  574 */       String newAttrName = (String)tableModel.getAttrNameAt(i);
/*  575 */       String newAttrValue = (String)tableModel.getAttrValueAt(i);
/*  576 */       if (newAttrName != null && newAttrName.length() > 0) {
/*      */         String namespaceURI;
/*  578 */         if (newAttrName.equals("xmlns")) {
/*  579 */           namespaceURI = "http://www.w3.org/2000/xmlns/";
/*      */         } else {
/*  581 */           String prefix = DOMUtilities.getPrefix(newAttrName);
/*  582 */           namespaceURI = getNamespaceURI(prefix);
/*      */         } 
/*  584 */         if (newAttrValue != null) {
/*  585 */           element.setAttributeNS(namespaceURI, newAttrName, newAttrValue);
/*      */         } else {
/*      */           
/*  588 */           element.setAttributeNS(namespaceURI, newAttrName, "");
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
/*      */   private void removeAttributes(Element element) {
/*  601 */     NamedNodeMap oldNodeMap = element.getAttributes();
/*  602 */     int n = oldNodeMap.getLength();
/*  603 */     for (int i = n - 1; i >= 0; i--) {
/*  604 */       element.removeAttributeNode((Attr)oldNodeMap.item(i));
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
/*      */   private String getNamespaceURI(String prefix) {
/*  618 */     String namespaceURI = null;
/*  619 */     if (prefix != null) {
/*  620 */       if (prefix.equals("xmlns")) {
/*  621 */         namespaceURI = "http://www.w3.org/2000/xmlns/";
/*      */       
/*      */       }
/*  624 */       else if (this.mode == 2) {
/*  625 */         AbstractNode n = (AbstractNode)this.previewElement;
/*  626 */         namespaceURI = n.lookupNamespaceURI(prefix);
/*  627 */       } else if (this.mode == 3) {
/*  628 */         AbstractNode n = (AbstractNode)this.parentElement;
/*  629 */         namespaceURI = n.lookupNamespaceURI(prefix);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  634 */     return namespaceURI;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateAttributesTable(Element elem) {
/*  645 */     NamedNodeMap map = elem.getAttributes();
/*  646 */     AttributesTableModel tableModel = (AttributesTableModel)this.attributesTable.getModel();
/*      */     
/*      */     int i;
/*  649 */     for (i = tableModel.getRowCount() - 1; i >= 0; i--) {
/*  650 */       String attrName = (String)tableModel.getValueAt(i, 0);
/*  651 */       String newAttrValue = "";
/*  652 */       if (attrName != null) {
/*  653 */         newAttrValue = elem.getAttributeNS((String)null, attrName);
/*      */       }
/*  655 */       if (attrName == null || newAttrValue.length() == 0) {
/*  656 */         tableModel.removeRow(i);
/*      */       }
/*  658 */       if (newAttrValue.length() > 0) {
/*  659 */         tableModel.setValueAt(newAttrValue, i, 1);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  664 */     for (i = 0; i < map.getLength(); i++) {
/*  665 */       Node attr = map.item(i);
/*  666 */       String attrName = attr.getNodeName();
/*  667 */       String attrValue = attr.getNodeValue();
/*  668 */       if (tableModel.getValueForName(attrName) == null) {
/*  669 */         Vector<String> rowData = new Vector();
/*  670 */         rowData.add(attrName);
/*  671 */         rowData.add(attrValue);
/*  672 */         tableModel.addRow(rowData);
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
/*      */   private void updateNodeXmlArea(Node node) {
/*  684 */     getSvgInputPanel().getNodeXmlArea().setText(DOMUtilities.getXML(node));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Element getPreviewElement() {
/*  693 */     return this.previewElement;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPreviewElement(Element elem) {
/*  704 */     if (this.previewElement != elem && this.isDirty && 
/*  705 */       !promptForChanges()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  710 */     this.previewElement = elem;
/*  711 */     enterViewMode();
/*      */     
/*  713 */     updateNodeXmlArea(elem);
/*  714 */     updateAttributesTable(elem);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean panelHiding() {
/*  722 */     return (!this.isDirty || promptForChanges());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getMode() {
/*  731 */     return this.mode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enterViewMode() {
/*  738 */     if (this.mode != 1) {
/*  739 */       this.mode = 1;
/*      */       
/*  741 */       getApplyButton().setEnabled(false);
/*  742 */       getResetButton().setEnabled(false);
/*      */       
/*  744 */       getRemoveButton().setEnabled(true);
/*  745 */       getAddButton().setEnabled(true);
/*      */       
/*  747 */       String isWellFormedLabelVal = resources.getString("IsWellFormedLabel.wellFormed");
/*      */       
/*  749 */       this.isWellFormedLabel.setText(isWellFormedLabelVal);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enterEditMode() {
/*  757 */     if (this.mode != 2) {
/*  758 */       this.mode = 2;
/*  759 */       this.clonedElement = (Element)this.previewElement.cloneNode(true);
/*      */ 
/*      */       
/*  762 */       getApplyButton().setEnabled(true);
/*  763 */       getResetButton().setEnabled(true);
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
/*      */   public void enterAddNewElementMode(Element newElement, Node parent) {
/*  776 */     if (this.mode != 3) {
/*  777 */       this.mode = 3;
/*  778 */       this.previewElement = newElement;
/*  779 */       this.clonedElement = (Element)newElement.cloneNode(true);
/*  780 */       this.parentElement = parent;
/*      */       
/*  782 */       updateNodeXmlArea(newElement);
/*      */       
/*  784 */       getApplyButton().setEnabled(true);
/*  785 */       getResetButton().setEnabled(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateOnDocumentChange(String mutationEventType, Node targetNode) {
/*  795 */     if (this.mode == 1 && 
/*  796 */       isShowing() && shouldUpdate(mutationEventType, targetNode, getPreviewElement()))
/*      */     {
/*      */ 
/*      */       
/*  800 */       setPreviewElement(getPreviewElement());
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
/*      */   private boolean shouldUpdate(String mutationEventType, Node affectedNode, Node currentNode) {
/*  817 */     if (mutationEventType.equals("DOMNodeInserted")) {
/*  818 */       if (DOMUtilities.isAncestorOf(currentNode, affectedNode)) {
/*  819 */         return true;
/*      */       }
/*  821 */     } else if (mutationEventType.equals("DOMNodeRemoved")) {
/*  822 */       if (DOMUtilities.isAncestorOf(currentNode, affectedNode)) {
/*  823 */         return true;
/*      */       }
/*  825 */     } else if (mutationEventType.equals("DOMAttrModified")) {
/*  826 */       if (DOMUtilities.isAncestorOf(currentNode, affectedNode) || currentNode == affectedNode)
/*      */       {
/*  828 */         return true;
/*      */       }
/*  830 */     } else if (mutationEventType.equals("DOMCharDataModified") && 
/*  831 */       DOMUtilities.isAncestorOf(currentNode, affectedNode)) {
/*  832 */       return true;
/*      */     } 
/*      */     
/*  835 */     return false;
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
/*      */   private Element parseXml(String xmlString) {
/*  847 */     Document doc = null;
/*  848 */     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*      */     
/*  850 */     try { DocumentBuilder parser = factory.newDocumentBuilder();
/*      */       
/*  852 */       parser.setErrorHandler(new ErrorHandler()
/*      */           {
/*      */             public void error(SAXParseException exception) throws SAXException {}
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void fatalError(SAXParseException exception) throws SAXException {}
/*      */ 
/*      */ 
/*      */             
/*      */             public void warning(SAXParseException exception) throws SAXException {}
/*      */           });
/*  865 */       doc = parser.parse(new InputSource(new StringReader(xmlString))); }
/*  866 */     catch (ParserConfigurationException parserConfigurationException) {  }
/*  867 */     catch (SAXException sAXException) {  }
/*  868 */     catch (IOException iOException) {}
/*      */     
/*  870 */     if (doc != null) {
/*  871 */       return doc.getDocumentElement();
/*      */     }
/*  873 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEditable(boolean editable) {
/*  883 */     getSvgInputPanel().getNodeXmlArea().setEditable(editable);
/*  884 */     getResetButton().setEnabled(editable);
/*  885 */     getApplyButton().setEnabled(editable);
/*  886 */     getAddButton().setEnabled(editable);
/*  887 */     getRemoveButton().setEnabled(editable);
/*  888 */     this.attributesTable.setEnabled(editable);
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
/*      */   private boolean isANodePickerComponent(Component component) {
/*  900 */     return (SwingUtilities.getAncestorOfClass(NodePickerPanel.class, component) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean promptForChanges() {
/*  909 */     if (getApplyButton().isEnabled() && isElementModified()) {
/*  910 */       String confirmString = resources.getString("ConfirmDialog.message");
/*  911 */       int option = JOptionPane.showConfirmDialog(getSvgInputPanel(), confirmString);
/*      */       
/*  913 */       if (option == 0)
/*  914 */       { getApplyButton().doClick(); }
/*  915 */       else { if (option == 2) {
/*  916 */           return false;
/*      */         }
/*  918 */         getResetButton().doClick(); }
/*      */     
/*      */     } else {
/*  921 */       getResetButton().doClick();
/*      */     } 
/*  923 */     this.isDirty = false;
/*  924 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isElementModified() {
/*  933 */     if (getMode() == 2) {
/*  934 */       return !DOMUtilities.getXML(this.previewElement).equals(getSvgInputPanel().getNodeXmlArea().getText());
/*      */     }
/*  936 */     if (getMode() == 3) {
/*  937 */       return true;
/*      */     }
/*  939 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected class NodePickerEditListener
/*      */     extends FocusAdapter
/*      */   {
/*      */     public void focusGained(FocusEvent e) {
/*  948 */       if (NodePickerPanel.this.getMode() == 1) {
/*  949 */         NodePickerPanel.this.enterEditMode();
/*      */       }
/*  951 */       NodePickerPanel.this.setEditable((NodePickerPanel.this.controller.isEditable() && NodePickerPanel.this.controller.canEdit(NodePickerPanel.this.previewElement)));
/*      */       
/*  953 */       NodePickerPanel.this.isDirty = NodePickerPanel.this.isElementModified();
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
/*      */   protected class XMLAreaListener
/*      */     implements DocumentListener
/*      */   {
/*      */     public void changedUpdate(DocumentEvent e) {
/*  973 */       NodePickerPanel.this.isDirty = NodePickerPanel.this.isElementModified();
/*      */     }
/*      */     
/*      */     public void insertUpdate(DocumentEvent e) {
/*  977 */       updateNodePicker(e);
/*  978 */       NodePickerPanel.this.isDirty = NodePickerPanel.this.isElementModified();
/*      */     }
/*      */     
/*      */     public void removeUpdate(DocumentEvent e) {
/*  982 */       updateNodePicker(e);
/*  983 */       NodePickerPanel.this.isDirty = NodePickerPanel.this.isElementModified();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void updateNodePicker(DocumentEvent e) {
/*  993 */       if (NodePickerPanel.this.getMode() == 2) {
/*  994 */         NodePickerPanel.this.updateViewAfterSvgInput(NodePickerPanel.this.parseXml(NodePickerPanel.this.svgInputPanel.getNodeXmlArea().getText()), NodePickerPanel.this.clonedElement);
/*      */       
/*      */       }
/*  997 */       else if (NodePickerPanel.this.getMode() == 3) {
/*  998 */         NodePickerPanel.this.updateViewAfterSvgInput(NodePickerPanel.this.parseXml(NodePickerPanel.this.svgInputPanel.getNodeXmlArea().getText()), NodePickerPanel.this.previewElement);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class AttributesTableModelListener
/*      */     implements TableModelListener
/*      */   {
/*      */     public void tableChanged(TableModelEvent e) {
/* 1011 */       if (e.getType() == 0 && NodePickerPanel.this.shouldProcessUpdate) {
/* 1012 */         updateNodePicker(e);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void updateNodePicker(TableModelEvent e) {
/* 1023 */       if (NodePickerPanel.this.getMode() == 2) {
/* 1024 */         NodePickerPanel.this.updateElementAttributes(NodePickerPanel.this.clonedElement, (NodePickerPanel.AttributesTableModel)e.getSource());
/*      */         
/* 1026 */         NodePickerPanel.this.updateNodeXmlArea(NodePickerPanel.this.clonedElement);
/* 1027 */       } else if (NodePickerPanel.this.getMode() == 3) {
/* 1028 */         NodePickerPanel.this.updateElementAttributes(NodePickerPanel.this.previewElement, (NodePickerPanel.AttributesTableModel)e.getSource());
/*      */         
/* 1030 */         NodePickerPanel.this.updateNodeXmlArea(NodePickerPanel.this.previewElement);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class ApplyButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1040 */       NodePickerPanel.this.isDirty = false;
/* 1041 */       String xmlAreaText = NodePickerPanel.this.getResults();
/* 1042 */       if (NodePickerPanel.this.getMode() == 2) {
/* 1043 */         NodePickerPanel.this.fireUpdateElement(new NodePickerPanel.NodePickerEvent(NodePickerPanel.this, xmlAreaText, NodePickerPanel.this.previewElement, 1));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1049 */       else if (NodePickerPanel.this.getMode() == 3) {
/* 1050 */         NodePickerPanel.this.fireAddNewElement(new NodePickerPanel.NodePickerEvent(NodePickerPanel.this, xmlAreaText, NodePickerPanel.this.parentElement, 2));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1057 */       NodePickerPanel.this.enterViewMode();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class ResetButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1066 */       NodePickerPanel.this.isDirty = false;
/* 1067 */       NodePickerPanel.this.setPreviewElement(NodePickerPanel.this.getPreviewElement());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class AddButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1076 */       if (NodePickerPanel.this.getMode() == 1) {
/* 1077 */         NodePickerPanel.this.enterEditMode();
/*      */       }
/* 1079 */       DefaultTableModel model = (DefaultTableModel)NodePickerPanel.this.attributesTable.getModel();
/*      */       
/* 1081 */       NodePickerPanel.this.shouldProcessUpdate = false;
/* 1082 */       model.addRow((Vector)null);
/* 1083 */       NodePickerPanel.this.shouldProcessUpdate = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected class RemoveButtonAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1092 */       if (NodePickerPanel.this.getMode() == 1) {
/* 1093 */         NodePickerPanel.this.enterEditMode();
/*      */       }
/*      */       
/* 1096 */       Element contextElement = NodePickerPanel.this.clonedElement;
/* 1097 */       if (NodePickerPanel.this.getMode() == 3) {
/* 1098 */         contextElement = NodePickerPanel.this.previewElement;
/*      */       }
/* 1100 */       DefaultTableModel model = (DefaultTableModel)NodePickerPanel.this.attributesTable.getModel();
/*      */       
/* 1102 */       int[] selectedRows = NodePickerPanel.this.attributesTable.getSelectedRows();
/* 1103 */       for (int selectedRow : selectedRows) {
/* 1104 */         String attrName = (String)model.getValueAt(selectedRow, 0);
/* 1105 */         if (attrName != null) {
/* 1106 */           String prefix = DOMUtilities.getPrefix(attrName);
/* 1107 */           String localName = DOMUtilities.getLocalName(attrName);
/* 1108 */           String namespaceURI = NodePickerPanel.this.getNamespaceURI(prefix);
/* 1109 */           contextElement.removeAttributeNS(namespaceURI, localName);
/*      */         } 
/*      */       } 
/* 1112 */       NodePickerPanel.this.shouldProcessUpdate = false;
/* 1113 */       NodePickerPanel.this.updateAttributesTable(contextElement);
/* 1114 */       NodePickerPanel.this.shouldProcessUpdate = true;
/* 1115 */       NodePickerPanel.this.updateNodeXmlArea(contextElement);
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
/*      */   public Action getAction(String key) throws MissingListenerException {
/* 1128 */     return (Action)this.listeners.get(key);
/*      */   }
/*      */ 
/*      */   
/*      */   public static class AttributesTableModel
/*      */     extends DefaultTableModel
/*      */   {
/*      */     public AttributesTableModel(int rowCount, int columnCount) {
/* 1136 */       super(rowCount, columnCount);
/*      */     }
/*      */     
/*      */     public String getColumnName(int column) {
/* 1140 */       if (column == 0) {
/* 1141 */         return NodePickerPanel.resources.getString("AttributesTable.column1");
/*      */       }
/* 1143 */       return NodePickerPanel.resources.getString("AttributesTable.column2");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getValueForName(Object attrName) {
/* 1154 */       for (int i = 0; i < getRowCount(); i++) {
/* 1155 */         if (getValueAt(i, 0) != null && getValueAt(i, 0).equals(attrName))
/*      */         {
/* 1157 */           return getValueAt(i, 1);
/*      */         }
/*      */       } 
/* 1160 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getAttrNameAt(int i) {
/* 1167 */       return getValueAt(i, 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getAttrValueAt(int i) {
/* 1174 */       return getValueAt(i, 1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getRow(Object attrName) {
/* 1182 */       for (int i = 0; i < getRowCount(); i++) {
/* 1183 */         if (getValueAt(i, 0) != null && getValueAt(i, 0).equals(attrName))
/*      */         {
/* 1185 */           return i;
/*      */         }
/*      */       } 
/* 1188 */       return -1;
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
/*      */   public void fireUpdateElement(NodePickerEvent event) {
/* 1200 */     Object[] listeners = this.eventListeners.getListenerList();
/*      */     
/* 1202 */     int length = listeners.length;
/* 1203 */     for (int i = 0; i < length; i += 2) {
/* 1204 */       if (listeners[i] == NodePickerListener.class) {
/* 1205 */         ((NodePickerListener)listeners[i + 1]).updateElement(event);
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
/*      */   public void fireAddNewElement(NodePickerEvent event) {
/* 1218 */     Object[] listeners = this.eventListeners.getListenerList();
/* 1219 */     int length = listeners.length;
/* 1220 */     for (int i = 0; i < length; i += 2) {
/* 1221 */       if (listeners[i] == NodePickerListener.class) {
/* 1222 */         ((NodePickerListener)listeners[i + 1]).addNewElement(event);
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
/*      */   public void addListener(NodePickerListener listener) {
/* 1235 */     this.eventListeners.add(NodePickerListener.class, listener);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class NodePickerEvent
/*      */     extends EventObject
/*      */   {
/*      */     public static final int EDIT_ELEMENT = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ADD_NEW_ELEMENT = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int type;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String result;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Node contextNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NodePickerEvent(Object source, String result, Node contextNode, int type) {
/* 1275 */       super(source);
/* 1276 */       this.result = result;
/* 1277 */       this.contextNode = contextNode;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getResult() {
/* 1286 */       return this.result;
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
/*      */     public Node getContextNode() {
/* 1299 */       return this.contextNode;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getType() {
/* 1308 */       return this.type;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface NodePickerListener
/*      */     extends EventListener
/*      */   {
/*      */     void updateElement(NodePickerPanel.NodePickerEvent param1NodePickerEvent);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void addNewElement(NodePickerPanel.NodePickerEvent param1NodePickerEvent);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class NodePickerAdapter
/*      */     implements NodePickerListener
/*      */   {
/*      */     public void addNewElement(NodePickerPanel.NodePickerEvent event) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void updateElement(NodePickerPanel.NodePickerEvent event) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class SVGInputPanel
/*      */     extends JPanel
/*      */   {
/*      */     protected XMLTextEditor nodeXmlArea;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SVGInputPanel() {
/* 1353 */       super(new BorderLayout());
/* 1354 */       add(new JScrollPane((Component)getNodeXmlArea()));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected XMLTextEditor getNodeXmlArea() {
/* 1363 */       if (this.nodeXmlArea == null) {
/*      */         
/* 1365 */         this.nodeXmlArea = new XMLTextEditor();
/* 1366 */         this.nodeXmlArea.setEditable(true);
/*      */       } 
/* 1368 */       return this.nodeXmlArea;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class NameEditorDialog
/*      */     extends JDialog
/*      */     implements ActionMap
/*      */   {
/*      */     public static final int OK_OPTION = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int CANCEL_OPTION = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected static final String RESOURCES = "org.apache.batik.apps.svgbrowser.resources.NameEditorDialogMessages";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1403 */     protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.NameEditorDialogMessages", Locale.getDefault());
/* 1404 */     protected static ResourceManager resources = new ResourceManager(bundle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int returnCode;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JPanel mainPanel;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ButtonFactory buttonFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JLabel nodeNameLabel;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JTextField nodeNameField;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JButton okButton;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JButton cancelButton;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1445 */     protected Map listeners = new HashMap<Object, Object>(10);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NameEditorDialog(Frame frame) {
/* 1454 */       super(frame, true);
/* 1455 */       setResizable(false);
/* 1456 */       setModal(true);
/* 1457 */       initialize();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void initialize() {
/* 1464 */       setSize(resources.getInteger("Dialog.width"), resources.getInteger("Dialog.height"));
/*      */       
/* 1466 */       setTitle(resources.getString("Dialog.title"));
/* 1467 */       addButtonActions();
/* 1468 */       setContentPane(getMainPanel());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ButtonFactory getButtonFactory() {
/* 1475 */       if (this.buttonFactory == null) {
/* 1476 */         this.buttonFactory = new ButtonFactory(bundle, this);
/*      */       }
/* 1478 */       return this.buttonFactory;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void addButtonActions() {
/* 1485 */       this.listeners.put("OKButtonAction", new OKButtonAction());
/* 1486 */       this.listeners.put("CancelButtonAction", new CancelButtonAction());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int showDialog() {
/* 1495 */       setVisible(true);
/* 1496 */       return this.returnCode;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JButton getOkButton() {
/* 1505 */       if (this.okButton == null) {
/* 1506 */         this.okButton = getButtonFactory().createJButton("OKButton");
/* 1507 */         getRootPane().setDefaultButton(this.okButton);
/*      */       } 
/* 1509 */       return this.okButton;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JButton getCancelButton() {
/* 1518 */       if (this.cancelButton == null) {
/* 1519 */         this.cancelButton = getButtonFactory().createJButton("CancelButton");
/*      */       }
/* 1521 */       return this.cancelButton;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JPanel getMainPanel() {
/* 1530 */       if (this.mainPanel == null) {
/* 1531 */         this.mainPanel = new JPanel(new GridBagLayout());
/*      */         
/* 1533 */         GridBagConstraints gridBag = new GridBagConstraints();
/* 1534 */         gridBag.gridx = 1;
/* 1535 */         gridBag.gridy = 1;
/* 1536 */         gridBag.fill = 0;
/* 1537 */         gridBag.insets = new Insets(5, 5, 5, 5);
/* 1538 */         this.mainPanel.add(getNodeNameLabel(), gridBag);
/*      */         
/* 1540 */         gridBag.gridx = 2;
/* 1541 */         gridBag.weightx = 1.0D;
/* 1542 */         gridBag.weighty = 1.0D;
/* 1543 */         gridBag.fill = 2;
/* 1544 */         gridBag.anchor = 10;
/* 1545 */         this.mainPanel.add(getNodeNameField(), gridBag);
/*      */         
/* 1547 */         gridBag.gridx = 1;
/* 1548 */         gridBag.gridy = 2;
/* 1549 */         gridBag.weightx = 0.0D;
/* 1550 */         gridBag.weighty = 0.0D;
/* 1551 */         gridBag.anchor = 13;
/* 1552 */         gridBag.fill = 2;
/* 1553 */         this.mainPanel.add(getOkButton(), gridBag);
/*      */         
/* 1555 */         gridBag.gridx = 2;
/* 1556 */         gridBag.gridy = 2;
/* 1557 */         gridBag.anchor = 13;
/* 1558 */         this.mainPanel.add(getCancelButton(), gridBag);
/*      */       } 
/* 1560 */       return this.mainPanel;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public JLabel getNodeNameLabel() {
/* 1569 */       if (this.nodeNameLabel == null) {
/* 1570 */         this.nodeNameLabel = new JLabel();
/* 1571 */         this.nodeNameLabel.setText(resources.getString("Dialog.label"));
/*      */       } 
/* 1573 */       return this.nodeNameLabel;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JTextField getNodeNameField() {
/* 1582 */       if (this.nodeNameField == null) {
/* 1583 */         this.nodeNameField = new JTextField();
/*      */       }
/* 1585 */       return this.nodeNameField;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getResults() {
/* 1594 */       return this.nodeNameField.getText();
/*      */     }
/*      */ 
/*      */     
/*      */     protected class OKButtonAction
/*      */       extends AbstractAction
/*      */     {
/*      */       public void actionPerformed(ActionEvent e) {
/* 1602 */         NodePickerPanel.NameEditorDialog.this.returnCode = 0;
/* 1603 */         NodePickerPanel.NameEditorDialog.this.dispose();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected class CancelButtonAction
/*      */       extends AbstractAction
/*      */     {
/*      */       public void actionPerformed(ActionEvent e) {
/* 1613 */         NodePickerPanel.NameEditorDialog.this.returnCode = 1;
/* 1614 */         NodePickerPanel.NameEditorDialog.this.dispose();
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
/*      */     public Action getAction(String key) throws MissingListenerException {
/* 1627 */       return (Action)this.listeners.get(key);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/NodePickerPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */