/*      */ package org.apache.batik.apps.svgbrowser;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.CardLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.FileDialog;
/*      */ import java.awt.FlowLayout;
/*      */ import java.awt.Font;
/*      */ import java.awt.Frame;
/*      */ import java.awt.Insets;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.KeyAdapter;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.StringTokenizer;
/*      */ import javax.swing.AbstractButton;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.DefaultListModel;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.JToggleButton;
/*      */ import javax.swing.JToolBar;
/*      */ import javax.swing.LookAndFeel;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.event.ListDataEvent;
/*      */ import javax.swing.event.ListDataListener;
/*      */ import javax.swing.event.ListSelectionEvent;
/*      */ import javax.swing.event.ListSelectionListener;
/*      */ import org.apache.batik.ext.swing.GridBagConstants;
/*      */ import org.apache.batik.ext.swing.JGridBagPanel;
/*      */ import org.apache.batik.util.Platform;
/*      */ import org.apache.batik.util.PreferenceManager;
/*      */ import org.apache.batik.util.gui.CSSMediaPanel;
/*      */ import org.apache.batik.util.gui.LanguageDialog;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PreferenceDialog
/*      */   extends JDialog
/*      */   implements GridBagConstants
/*      */ {
/*      */   public static final int OK_OPTION = 0;
/*      */   public static final int CANCEL_OPTION = 1;
/*      */   public static final String PREFERENCE_KEY_TITLE_PREFIX = "PreferenceDialog.title.";
/*      */   public static final String PREFERENCE_KEY_TITLE_DIALOG = "PreferenceDialog.title.dialog";
/*      */   public static final String PREFERENCE_KEY_LABEL_RENDERING_OPTIONS = "PreferenceDialog.label.rendering.options";
/*      */   public static final String PREFERENCE_KEY_LABEL_ANIMATION_RATE_LIMITING = "PreferenceDialog.label.animation.rate.limiting";
/*      */   public static final String PREFERENCE_KEY_LABEL_OTHER_OPTIONS = "PreferenceDialog.label.other.options";
/*      */   public static final String PREFERENCE_KEY_LABEL_ENABLE_DOUBLE_BUFFERING = "PreferenceDialog.label.enable.double.buffering";
/*      */   public static final String PREFERENCE_KEY_LABEL_SHOW_RENDERING = "PreferenceDialog.label.show.rendering";
/*      */   public static final String PREFERENCE_KEY_LABEL_AUTO_ADJUST_WINDOW = "PreferenceDialog.label.auto.adjust.window";
/*      */   public static final String PREFERENCE_KEY_LABEL_SELECTION_XOR_MODE = "PreferenceDialog.label.selection.xor.mode";
/*      */   public static final String PREFERENCE_KEY_LABEL_ANIMATION_LIMIT_CPU = "PreferenceDialog.label.animation.limit.cpu";
/*      */   public static final String PREFERENCE_KEY_LABEL_PERCENT = "PreferenceDialog.label.percent";
/*      */   public static final String PREFERENCE_KEY_LABEL_ANIMATION_LIMIT_FPS = "PreferenceDialog.label.animation.limit.fps";
/*      */   public static final String PREFERENCE_KEY_LABEL_FPS = "PreferenceDialog.label.fps";
/*      */   public static final String PREFERENCE_KEY_LABEL_ANIMATION_LIMIT_UNLIMITED = "PreferenceDialog.label.animation.limit.unlimited";
/*      */   public static final String PREFERENCE_KEY_LABEL_SHOW_DEBUG_TRACE = "PreferenceDialog.label.show.debug.trace";
/*      */   public static final String PREFERENCE_KEY_LABEL_IS_XML_PARSER_VALIDATING = "PreferenceDialog.label.is.xml.parser.validating";
/*      */   public static final String PREFERENCE_KEY_LABEL_GRANT_SCRIPTS_ACCESS_TO = "PreferenceDialog.label.grant.scripts.access.to";
/*      */   public static final String PREFERENCE_KEY_LABEL_LOAD_SCRIPTS = "PreferenceDialog.label.load.scripts";
/*      */   public static final String PREFERENCE_KEY_LABEL_ALLOWED_SCRIPT_ORIGIN = "PreferenceDialog.label.allowed.script.origin";
/*      */   public static final String PREFERENCE_KEY_LABEL_ALLOWED_RESOURCE_ORIGIN = "PreferenceDialog.label.allowed.resource.origin";
/*      */   public static final String PREFERENCE_KEY_LABEL_ENFORCE_SECURE_SCRIPTING = "PreferenceDialog.label.enforce.secure.scripting";
/*      */   public static final String PREFERENCE_KEY_LABEL_FILE_SYSTEM = "PreferenceDialog.label.file.system";
/*      */   public static final String PREFERENCE_KEY_LABEL_ALL_NETWORK = "PreferenceDialog.label.all.network";
/*      */   public static final String PREFERENCE_KEY_LABEL_JAVA_JAR_FILES = "PreferenceDialog.label.java.jar.files";
/*      */   public static final String PREFERENCE_KEY_LABEL_ECMASCRIPT = "PreferenceDialog.label.ecmascript";
/*      */   public static final String PREFERENCE_KEY_LABEL_ORIGIN_ANY = "PreferenceDialog.label.origin.any";
/*      */   public static final String PREFERENCE_KEY_LABEL_ORIGIN_DOCUMENT = "PreferenceDialog.label.origin.document";
/*      */   public static final String PREFERENCE_KEY_LABEL_ORIGIN_EMBEDDED = "PreferenceDialog.label.origin.embedded";
/*      */   public static final String PREFERENCE_KEY_LABEL_ORIGIN_NONE = "PreferenceDialog.label.origin.none";
/*      */   public static final String PREFERENCE_KEY_LABEL_USER_STYLESHEET = "PreferenceDialog.label.user.stylesheet";
/*      */   public static final String PREFERENCE_KEY_LABEL_CSS_MEDIA_TYPES = "PreferenceDialog.label.css.media.types";
/*      */   public static final String PREFERENCE_KEY_LABEL_ENABLE_USER_STYLESHEET = "PreferenceDialog.label.enable.user.stylesheet";
/*      */   public static final String PREFERENCE_KEY_LABEL_BROWSE = "PreferenceDialog.label.browse";
/*      */   public static final String PREFERENCE_KEY_LABEL_ADD = "PreferenceDialog.label.add";
/*      */   public static final String PREFERENCE_KEY_LABEL_REMOVE = "PreferenceDialog.label.remove";
/*      */   public static final String PREFERENCE_KEY_LABEL_CLEAR = "PreferenceDialog.label.clear";
/*      */   public static final String PREFERENCE_KEY_LABEL_HTTP_PROXY = "PreferenceDialog.label.http.proxy";
/*      */   public static final String PREFERENCE_KEY_LABEL_HOST = "PreferenceDialog.label.host";
/*      */   public static final String PREFERENCE_KEY_LABEL_PORT = "PreferenceDialog.label.port";
/*      */   public static final String PREFERENCE_KEY_LABEL_COLON = "PreferenceDialog.label.colon";
/*      */   public static final String PREFERENCE_KEY_BROWSE_TITLE = "PreferenceDialog.BrowseWindow.title";
/*      */   public static final String PREFERENCE_KEY_LANGUAGES = "preference.key.languages";
/*      */   public static final String PREFERENCE_KEY_IS_XML_PARSER_VALIDATING = "preference.key.is.xml.parser.validating";
/*      */   public static final String PREFERENCE_KEY_USER_STYLESHEET = "preference.key.user.stylesheet";
/*      */   public static final String PREFERENCE_KEY_USER_STYLESHEET_ENABLED = "preference.key.user.stylesheet.enabled";
/*      */   public static final String PREFERENCE_KEY_SHOW_RENDERING = "preference.key.show.rendering";
/*      */   public static final String PREFERENCE_KEY_AUTO_ADJUST_WINDOW = "preference.key.auto.adjust.window";
/*      */   public static final String PREFERENCE_KEY_ENABLE_DOUBLE_BUFFERING = "preference.key.enable.double.buffering";
/*      */   public static final String PREFERENCE_KEY_SHOW_DEBUG_TRACE = "preference.key.show.debug.trace";
/*      */   public static final String PREFERENCE_KEY_SELECTION_XOR_MODE = "preference.key.selection.xor.mode";
/*      */   public static final String PREFERENCE_KEY_PROXY_HOST = "preference.key.proxy.host";
/*      */   public static final String PREFERENCE_KEY_CSS_MEDIA = "preference.key.cssmedia";
/*      */   public static final String PREFERENCE_KEY_DEFAULT_FONT_FAMILY = "preference.key.default.font.family";
/*      */   public static final String PREFERENCE_KEY_PROXY_PORT = "preference.key.proxy.port";
/*      */   public static final String PREFERENCE_KEY_ENFORCE_SECURE_SCRIPTING = "preference.key.enforce.secure.scripting";
/*      */   public static final String PREFERENCE_KEY_GRANT_SCRIPT_FILE_ACCESS = "preference.key.grant.script.file.access";
/*      */   public static final String PREFERENCE_KEY_GRANT_SCRIPT_NETWORK_ACCESS = "preference.key.grant.script.network.access";
/*      */   public static final String PREFERENCE_KEY_LOAD_ECMASCRIPT = "preference.key.load.ecmascript";
/*      */   public static final String PREFERENCE_KEY_LOAD_JAVA = "preference.key.load.java.script";
/*      */   public static final String PREFERENCE_KEY_ALLOWED_SCRIPT_ORIGIN = "preference.key.allowed.script.origin";
/*      */   public static final String PREFERENCE_KEY_ALLOWED_EXTERNAL_RESOURCE_ORIGIN = "preference.key.allowed.external.resource.origin";
/*      */   public static final String PREFERENCE_KEY_ANIMATION_RATE_LIMITING_MODE = "preference.key.animation.rate.limiting.mode";
/*      */   public static final String PREFERENCE_KEY_ANIMATION_RATE_LIMITING_CPU = "preference.key.animation.rate.limiting.cpu";
/*      */   public static final String PREFERENCE_KEY_ANIMATION_RATE_LIMITING_FPS = "preference.key.animation.rate.limiting.fps";
/*      */   public static final String LABEL_OK = "PreferenceDialog.label.ok";
/*      */   public static final String LABEL_CANCEL = "PreferenceDialog.label.cancel";
/*      */   protected PreferenceManager model;
/*      */   protected JConfigurationPanel configurationPanel;
/*      */   protected JCheckBox userStylesheetEnabled;
/*      */   protected JLabel userStylesheetLabel;
/*      */   protected JTextField userStylesheet;
/*      */   protected JButton userStylesheetBrowse;
/*      */   protected JCheckBox showRendering;
/*      */   protected JCheckBox autoAdjustWindow;
/*      */   protected JCheckBox enableDoubleBuffering;
/*      */   protected JCheckBox showDebugTrace;
/*      */   protected JCheckBox selectionXorMode;
/*      */   protected JCheckBox isXMLParserValidating;
/*      */   protected JRadioButton animationLimitUnlimited;
/*      */   protected JRadioButton animationLimitCPU;
/*      */   protected JRadioButton animationLimitFPS;
/*      */   protected JLabel animationLimitCPULabel;
/*      */   protected JLabel animationLimitFPSLabel;
/*      */   protected JTextField animationLimitCPUAmount;
/*      */   protected JTextField animationLimitFPSAmount;
/*      */   protected JCheckBox enforceSecureScripting;
/*      */   protected JCheckBox grantScriptFileAccess;
/*      */   protected JCheckBox grantScriptNetworkAccess;
/*      */   protected JCheckBox loadJava;
/*      */   protected JCheckBox loadEcmascript;
/*      */   protected JComboBox allowedScriptOrigin;
/*      */   protected JComboBox allowedResourceOrigin;
/*      */   protected JList mediaList;
/*      */   protected JButton mediaListRemoveButton;
/*      */   protected JButton mediaListClearButton;
/*      */   protected JTextField host;
/*      */   protected JTextField port;
/*      */   protected LanguageDialog.Panel languagePanel;
/*  348 */   protected DefaultListModel mediaListModel = new DefaultListModel();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int returnCode;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean isMetalSteel() {
/*  359 */     if (!UIManager.getLookAndFeel().getName().equals("Metal")) {
/*  360 */       return false;
/*      */     }
/*      */     try {
/*  363 */       LookAndFeel laf = UIManager.getLookAndFeel();
/*  364 */       laf.getClass().getMethod("getCurrentTheme", new Class[0]);
/*  365 */       return false;
/*  366 */     } catch (Exception exception) {
/*      */       
/*  368 */       return true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PreferenceDialog(Frame owner, PreferenceManager model) {
/*  375 */     super(owner, true);
/*      */     
/*  377 */     if (model == null) {
/*  378 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  381 */     this.model = model;
/*  382 */     buildGUI();
/*  383 */     initializeGUI();
/*  384 */     pack();
/*      */     
/*  386 */     addWindowListener(new WindowAdapter() {
/*      */           public void windowClosing(WindowEvent e) {
/*  388 */             if (Platform.isOSX) {
/*  389 */               PreferenceDialog.this.savePreferences();
/*      */             }
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PreferenceManager getPreferenceManager() {
/*  399 */     return this.model;
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
/*      */   protected void initializeGUI() {
/*  412 */     this.enableDoubleBuffering.setSelected(this.model.getBoolean("preference.key.enable.double.buffering"));
/*      */     
/*  414 */     this.showRendering.setSelected(this.model.getBoolean("preference.key.show.rendering"));
/*      */     
/*  416 */     this.autoAdjustWindow.setSelected(this.model.getBoolean("preference.key.auto.adjust.window"));
/*      */     
/*  418 */     this.selectionXorMode.setSelected(this.model.getBoolean("preference.key.selection.xor.mode"));
/*      */ 
/*      */     
/*  421 */     switch (this.model.getInteger("preference.key.animation.rate.limiting.mode")) {
/*      */       case 0:
/*  423 */         this.animationLimitUnlimited.setSelected(true);
/*      */         break;
/*      */       case 2:
/*  426 */         this.animationLimitFPS.setSelected(true);
/*      */         break;
/*      */       
/*      */       default:
/*  430 */         this.animationLimitCPU.setSelected(true);
/*      */         break;
/*      */     } 
/*  433 */     float f = this.model.getFloat("preference.key.animation.rate.limiting.cpu");
/*  434 */     if (f <= 0.0F || f > 100.0F) {
/*  435 */       f = 85.0F;
/*      */     } else {
/*  437 */       f *= 100.0F;
/*      */     } 
/*  439 */     if ((int)f == f) {
/*  440 */       this.animationLimitCPUAmount.setText(Integer.toString((int)f));
/*      */     } else {
/*  442 */       this.animationLimitCPUAmount.setText(Float.toString(f));
/*      */     } 
/*  444 */     f = this.model.getFloat("preference.key.animation.rate.limiting.fps");
/*  445 */     if (f <= 0.0F) {
/*  446 */       f = 10.0F;
/*      */     }
/*  448 */     if ((int)f == f) {
/*  449 */       this.animationLimitFPSAmount.setText(Integer.toString((int)f));
/*      */     } else {
/*  451 */       this.animationLimitFPSAmount.setText(Float.toString(f));
/*      */     } 
/*      */     
/*  454 */     this.showDebugTrace.setSelected(this.model.getBoolean("preference.key.show.debug.trace"));
/*      */     
/*  456 */     this.isXMLParserValidating.setSelected(this.model.getBoolean("preference.key.is.xml.parser.validating"));
/*      */ 
/*      */ 
/*      */     
/*  460 */     this.enforceSecureScripting.setSelected(this.model.getBoolean("preference.key.enforce.secure.scripting"));
/*      */     
/*  462 */     this.grantScriptFileAccess.setSelected(this.model.getBoolean("preference.key.grant.script.file.access"));
/*      */     
/*  464 */     this.grantScriptNetworkAccess.setSelected(this.model.getBoolean("preference.key.grant.script.network.access"));
/*      */     
/*  466 */     this.loadJava.setSelected(this.model.getBoolean("preference.key.load.java.script"));
/*      */     
/*  468 */     this.loadEcmascript.setSelected(this.model.getBoolean("preference.key.load.ecmascript"));
/*      */ 
/*      */     
/*  471 */     int i = this.model.getInteger("preference.key.allowed.script.origin");
/*  472 */     switch (i) {
/*      */       case 1:
/*  474 */         this.allowedScriptOrigin.setSelectedIndex(0);
/*      */         break;
/*      */       case 2:
/*  477 */         this.allowedScriptOrigin.setSelectedIndex(1);
/*      */         break;
/*      */       case 4:
/*  480 */         this.allowedScriptOrigin.setSelectedIndex(2);
/*      */         break;
/*      */       default:
/*  483 */         this.allowedScriptOrigin.setSelectedIndex(3);
/*      */         break;
/*      */     } 
/*      */     
/*  487 */     i = this.model.getInteger("preference.key.allowed.external.resource.origin");
/*  488 */     switch (i) {
/*      */       case 1:
/*  490 */         this.allowedResourceOrigin.setSelectedIndex(0);
/*      */         break;
/*      */       case 2:
/*  493 */         this.allowedResourceOrigin.setSelectedIndex(1);
/*      */         break;
/*      */       case 4:
/*  496 */         this.allowedResourceOrigin.setSelectedIndex(2);
/*      */         break;
/*      */       default:
/*  499 */         this.allowedResourceOrigin.setSelectedIndex(3);
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  504 */     this.languagePanel.setLanguages(this.model.getString("preference.key.languages"));
/*      */ 
/*      */     
/*  507 */     String s = this.model.getString("preference.key.cssmedia");
/*  508 */     this.mediaListModel.removeAllElements();
/*  509 */     StringTokenizer st = new StringTokenizer(s, " ");
/*  510 */     while (st.hasMoreTokens()) {
/*  511 */       this.mediaListModel.addElement(st.nextToken());
/*      */     }
/*      */     
/*  514 */     this.userStylesheet.setText(this.model.getString("preference.key.user.stylesheet"));
/*  515 */     boolean b = this.model.getBoolean("preference.key.user.stylesheet.enabled");
/*  516 */     this.userStylesheetEnabled.setSelected(b);
/*      */ 
/*      */     
/*  519 */     this.host.setText(this.model.getString("preference.key.proxy.host"));
/*  520 */     this.port.setText(this.model.getString("preference.key.proxy.port"));
/*      */ 
/*      */     
/*  523 */     b = this.enableDoubleBuffering.isSelected();
/*  524 */     this.showRendering.setEnabled(b);
/*      */     
/*  526 */     b = this.animationLimitCPU.isSelected();
/*  527 */     this.animationLimitCPUAmount.setEnabled(b);
/*  528 */     this.animationLimitCPULabel.setEnabled(b);
/*      */     
/*  530 */     b = this.animationLimitFPS.isSelected();
/*  531 */     this.animationLimitFPSAmount.setEnabled(b);
/*  532 */     this.animationLimitFPSLabel.setEnabled(b);
/*      */     
/*  534 */     b = this.enforceSecureScripting.isSelected();
/*  535 */     this.grantScriptFileAccess.setEnabled(b);
/*  536 */     this.grantScriptNetworkAccess.setEnabled(b);
/*      */     
/*  538 */     b = this.userStylesheetEnabled.isSelected();
/*  539 */     this.userStylesheetLabel.setEnabled(b);
/*  540 */     this.userStylesheet.setEnabled(b);
/*  541 */     this.userStylesheetBrowse.setEnabled(b);
/*      */     
/*  543 */     this.mediaListRemoveButton.setEnabled(!this.mediaList.isSelectionEmpty());
/*  544 */     this.mediaListClearButton.setEnabled(!this.mediaListModel.isEmpty());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void savePreferences() {
/*      */     float f;
/*  551 */     this.model.setString("preference.key.languages", this.languagePanel.getLanguages());
/*      */     
/*  553 */     this.model.setString("preference.key.user.stylesheet", this.userStylesheet.getText());
/*      */     
/*  555 */     this.model.setBoolean("preference.key.user.stylesheet.enabled", this.userStylesheetEnabled.isSelected());
/*      */     
/*  557 */     this.model.setBoolean("preference.key.show.rendering", this.showRendering.isSelected());
/*      */     
/*  559 */     this.model.setBoolean("preference.key.auto.adjust.window", this.autoAdjustWindow.isSelected());
/*      */     
/*  561 */     this.model.setBoolean("preference.key.enable.double.buffering", this.enableDoubleBuffering.isSelected());
/*      */     
/*  563 */     this.model.setBoolean("preference.key.show.debug.trace", this.showDebugTrace.isSelected());
/*      */     
/*  565 */     this.model.setBoolean("preference.key.selection.xor.mode", this.selectionXorMode.isSelected());
/*      */     
/*  567 */     this.model.setBoolean("preference.key.is.xml.parser.validating", this.isXMLParserValidating.isSelected());
/*      */     
/*  569 */     this.model.setBoolean("preference.key.enforce.secure.scripting", this.enforceSecureScripting.isSelected());
/*      */     
/*  571 */     this.model.setBoolean("preference.key.grant.script.file.access", this.grantScriptFileAccess.isSelected());
/*      */     
/*  573 */     this.model.setBoolean("preference.key.grant.script.network.access", this.grantScriptNetworkAccess.isSelected());
/*      */     
/*  575 */     this.model.setBoolean("preference.key.load.java.script", this.loadJava.isSelected());
/*      */     
/*  577 */     this.model.setBoolean("preference.key.load.ecmascript", this.loadEcmascript.isSelected());
/*      */ 
/*      */     
/*  580 */     switch (this.allowedScriptOrigin.getSelectedIndex()) {
/*      */       case 0:
/*  582 */         i = 1;
/*      */         break;
/*      */       case 1:
/*  585 */         i = 2;
/*      */         break;
/*      */       case 2:
/*  588 */         i = 4;
/*      */         break;
/*      */       
/*      */       default:
/*  592 */         i = 8;
/*      */         break;
/*      */     } 
/*  595 */     this.model.setInteger("preference.key.allowed.script.origin", i);
/*  596 */     switch (this.allowedResourceOrigin.getSelectedIndex()) {
/*      */       case 0:
/*  598 */         i = 1;
/*      */         break;
/*      */       case 1:
/*  601 */         i = 2;
/*      */         break;
/*      */       case 2:
/*  604 */         i = 4;
/*      */         break;
/*      */       
/*      */       default:
/*  608 */         i = 8;
/*      */         break;
/*      */     } 
/*  611 */     this.model.setInteger("preference.key.allowed.external.resource.origin", i);
/*  612 */     int i = 1;
/*  613 */     if (this.animationLimitFPS.isSelected()) {
/*  614 */       i = 2;
/*  615 */     } else if (this.animationLimitUnlimited.isSelected()) {
/*  616 */       i = 0;
/*      */     } 
/*  618 */     this.model.setInteger("preference.key.animation.rate.limiting.mode", i);
/*      */     
/*      */     try {
/*  621 */       f = Float.parseFloat(this.animationLimitCPUAmount.getText()) / 100.0F;
/*  622 */       if (f <= 0.0F || f >= 1.0F) {
/*  623 */         f = 0.85F;
/*      */       }
/*  625 */     } catch (NumberFormatException numberFormatException) {
/*  626 */       f = 0.85F;
/*      */     } 
/*  628 */     this.model.setFloat("preference.key.animation.rate.limiting.cpu", f);
/*      */     try {
/*  630 */       f = Float.parseFloat(this.animationLimitFPSAmount.getText());
/*  631 */       if (f <= 0.0F) {
/*  632 */         f = 15.0F;
/*      */       }
/*  634 */     } catch (NumberFormatException numberFormatException) {
/*  635 */       f = 15.0F;
/*      */     } 
/*  637 */     this.model.setFloat("preference.key.animation.rate.limiting.fps", f);
/*  638 */     this.model.setString("preference.key.proxy.host", this.host.getText());
/*      */     
/*  640 */     this.model.setString("preference.key.proxy.port", this.port.getText());
/*      */     
/*  642 */     StringBuffer sb = new StringBuffer();
/*  643 */     Enumeration<String> e = this.mediaListModel.elements();
/*  644 */     while (e.hasMoreElements()) {
/*  645 */       sb.append(e.nextElement());
/*  646 */       sb.append(' ');
/*      */     } 
/*  648 */     this.model.setString("preference.key.cssmedia", sb.toString());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void buildGUI() {
/*  655 */     JPanel panel = new JPanel(new BorderLayout());
/*      */     
/*  657 */     this.configurationPanel = new JConfigurationPanel();
/*  658 */     addConfigPanel("general", buildGeneralPanel());
/*  659 */     addConfigPanel("security", buildSecurityPanel());
/*  660 */     addConfigPanel("language", buildLanguagePanel());
/*  661 */     addConfigPanel("stylesheet", buildStylesheetPanel());
/*  662 */     addConfigPanel("network", buildNetworkPanel());
/*      */     
/*  664 */     panel.add(this.configurationPanel);
/*      */     
/*  666 */     if (!Platform.isOSX) {
/*  667 */       setTitle(Resources.getString("PreferenceDialog.title.dialog"));
/*  668 */       panel.add(buildButtonsPanel(), "South");
/*      */     } 
/*  670 */     setResizable(false);
/*      */     
/*  672 */     getContentPane().add(panel);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addConfigPanel(String id, JPanel c) {
/*  679 */     String name = Resources.getString("PreferenceDialog.title." + id);
/*  680 */     ImageIcon icon1 = new ImageIcon(PreferenceDialog.class.getResource("resources/icon-" + id + ".png"));
/*      */ 
/*      */     
/*  683 */     ImageIcon icon2 = new ImageIcon(PreferenceDialog.class.getResource("resources/icon-" + id + "-dark.png"));
/*      */ 
/*      */     
/*  686 */     this.configurationPanel.addPanel(name, icon1, icon2, c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JPanel buildButtonsPanel() {
/*  693 */     JPanel p = new JPanel(new FlowLayout(2));
/*  694 */     JButton okButton = new JButton(Resources.getString("PreferenceDialog.label.ok"));
/*  695 */     JButton cancelButton = new JButton(Resources.getString("PreferenceDialog.label.cancel"));
/*  696 */     p.add(okButton);
/*  697 */     p.add(cancelButton);
/*      */     
/*  699 */     okButton.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/*  701 */             PreferenceDialog.this.setVisible(false);
/*  702 */             PreferenceDialog.this.returnCode = 0;
/*  703 */             PreferenceDialog.this.savePreferences();
/*  704 */             PreferenceDialog.this.dispose();
/*      */           }
/*      */         });
/*      */     
/*  708 */     cancelButton.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/*  710 */             PreferenceDialog.this.setVisible(false);
/*  711 */             PreferenceDialog.this.returnCode = 1;
/*  712 */             PreferenceDialog.this.dispose();
/*      */           }
/*      */         });
/*      */     
/*  716 */     addKeyListener(new KeyAdapter() {
/*      */           public void keyPressed(KeyEvent e) {
/*  718 */             switch (e.getKeyCode()) {
/*      */               case 27:
/*  720 */                 PreferenceDialog.this.returnCode = 1;
/*      */                 break;
/*      */               case 10:
/*  723 */                 PreferenceDialog.this.returnCode = 0;
/*      */                 break;
/*      */               default:
/*      */                 return;
/*      */             } 
/*  728 */             PreferenceDialog.this.setVisible(false);
/*  729 */             PreferenceDialog.this.dispose();
/*      */           }
/*      */         });
/*      */     
/*  733 */     return p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JPanel buildGeneralPanel() {
/*  740 */     JGridBagPanel.InsetsManager im = new JGridBagPanel.InsetsManager() {
/*  741 */         protected Insets i1 = new Insets(5, 5, 0, 0);
/*  742 */         protected Insets i2 = new Insets(5, 0, 0, 0);
/*  743 */         protected Insets i3 = new Insets(0, 5, 0, 0);
/*  744 */         protected Insets i4 = new Insets(0, 0, 0, 0);
/*      */         
/*      */         public Insets getInsets(int x, int y) {
/*  747 */           if (y == 4 || y == 9) {
/*  748 */             return (x == 0) ? this.i2 : this.i1;
/*      */           }
/*  750 */           return (x == 0) ? this.i4 : this.i3;
/*      */         }
/*      */       };
/*      */     
/*  754 */     JGridBagPanel p = new JGridBagPanel(im);
/*  755 */     p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
/*      */     
/*  757 */     JLabel renderingLabel = new JLabel(Resources.getString("PreferenceDialog.label.rendering.options"));
/*  758 */     this.enableDoubleBuffering = new JCheckBox(Resources.getString("PreferenceDialog.label.enable.double.buffering"));
/*  759 */     this.enableDoubleBuffering.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent evt) {
/*  761 */             PreferenceDialog.this.showRendering.setEnabled(PreferenceDialog.this.enableDoubleBuffering.isSelected());
/*      */           }
/*      */         });
/*  764 */     this.showRendering = new JCheckBox(Resources.getString("PreferenceDialog.label.show.rendering"));
/*  765 */     Insets in = this.showRendering.getMargin();
/*  766 */     this.showRendering.setMargin(new Insets(in.top, in.left + 24, in.bottom, in.right));
/*  767 */     this.selectionXorMode = new JCheckBox(Resources.getString("PreferenceDialog.label.selection.xor.mode"));
/*  768 */     this.autoAdjustWindow = new JCheckBox(Resources.getString("PreferenceDialog.label.auto.adjust.window"));
/*  769 */     JLabel animLabel = new JLabel(Resources.getString("PreferenceDialog.label.animation.rate.limiting"));
/*  770 */     this.animationLimitCPU = new JRadioButton(Resources.getString("PreferenceDialog.label.animation.limit.cpu"));
/*  771 */     JPanel cpuPanel = new JPanel();
/*  772 */     cpuPanel.setLayout(new FlowLayout(3, 3, 0));
/*  773 */     cpuPanel.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
/*  774 */     this.animationLimitCPUAmount = new JTextField();
/*  775 */     this.animationLimitCPUAmount.setPreferredSize(new Dimension(40, 20));
/*  776 */     cpuPanel.add(this.animationLimitCPUAmount);
/*  777 */     this.animationLimitCPULabel = new JLabel(Resources.getString("PreferenceDialog.label.percent"));
/*  778 */     cpuPanel.add(this.animationLimitCPULabel);
/*  779 */     this.animationLimitFPS = new JRadioButton(Resources.getString("PreferenceDialog.label.animation.limit.fps"));
/*  780 */     JPanel fpsPanel = new JPanel();
/*  781 */     fpsPanel.setLayout(new FlowLayout(3, 3, 0));
/*  782 */     fpsPanel.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));
/*  783 */     this.animationLimitFPSAmount = new JTextField();
/*  784 */     this.animationLimitFPSAmount.setPreferredSize(new Dimension(40, 20));
/*  785 */     fpsPanel.add(this.animationLimitFPSAmount);
/*  786 */     this.animationLimitFPSLabel = new JLabel(Resources.getString("PreferenceDialog.label.fps"));
/*  787 */     fpsPanel.add(this.animationLimitFPSLabel);
/*  788 */     this.animationLimitUnlimited = new JRadioButton(Resources.getString("PreferenceDialog.label.animation.limit.unlimited"));
/*  789 */     ButtonGroup g = new ButtonGroup();
/*  790 */     g.add(this.animationLimitCPU);
/*  791 */     g.add(this.animationLimitFPS);
/*  792 */     g.add(this.animationLimitUnlimited);
/*  793 */     ActionListener l = new ActionListener() {
/*      */         public void actionPerformed(ActionEvent evt) {
/*  795 */           boolean b = PreferenceDialog.this.animationLimitCPU.isSelected();
/*  796 */           PreferenceDialog.this.animationLimitCPUAmount.setEnabled(b);
/*  797 */           PreferenceDialog.this.animationLimitCPULabel.setEnabled(b);
/*  798 */           b = PreferenceDialog.this.animationLimitFPS.isSelected();
/*  799 */           PreferenceDialog.this.animationLimitFPSAmount.setEnabled(b);
/*  800 */           PreferenceDialog.this.animationLimitFPSLabel.setEnabled(b);
/*      */         }
/*      */       };
/*  803 */     this.animationLimitCPU.addActionListener(l);
/*  804 */     this.animationLimitFPS.addActionListener(l);
/*  805 */     this.animationLimitUnlimited.addActionListener(l);
/*  806 */     JLabel otherLabel = new JLabel(Resources.getString("PreferenceDialog.label.other.options"));
/*  807 */     this.showDebugTrace = new JCheckBox(Resources.getString("PreferenceDialog.label.show.debug.trace"));
/*  808 */     this.isXMLParserValidating = new JCheckBox(Resources.getString("PreferenceDialog.label.is.xml.parser.validating"));
/*      */     
/*  810 */     p.add(renderingLabel, 0, 0, 1, 1, 13, 0, 0.0D, 0.0D);
/*  811 */     p.add(this.enableDoubleBuffering, 1, 0, 1, 1, 17, 0, 0.0D, 0.0D);
/*  812 */     p.add(this.showRendering, 1, 1, 1, 1, 17, 0, 0.0D, 0.0D);
/*  813 */     p.add(this.autoAdjustWindow, 1, 2, 1, 1, 17, 0, 0.0D, 0.0D);
/*  814 */     p.add(this.selectionXorMode, 1, 3, 1, 1, 17, 0, 0.0D, 0.0D);
/*  815 */     p.add(animLabel, 0, 4, 1, 1, 13, 0, 0.0D, 0.0D);
/*  816 */     p.add(this.animationLimitCPU, 1, 4, 1, 1, 17, 0, 0.0D, 0.0D);
/*  817 */     p.add(cpuPanel, 1, 5, 1, 1, 17, 0, 0.0D, 0.0D);
/*  818 */     p.add(this.animationLimitFPS, 1, 6, 1, 1, 17, 0, 0.0D, 0.0D);
/*  819 */     p.add(fpsPanel, 1, 7, 1, 1, 17, 0, 0.0D, 0.0D);
/*  820 */     p.add(this.animationLimitUnlimited, 1, 8, 1, 1, 17, 0, 0.0D, 0.0D);
/*  821 */     p.add(otherLabel, 0, 9, 1, 1, 13, 0, 0.0D, 0.0D);
/*  822 */     p.add(this.showDebugTrace, 1, 9, 1, 1, 17, 0, 0.0D, 0.0D);
/*  823 */     p.add(this.isXMLParserValidating, 1, 10, 1, 1, 17, 0, 0.0D, 0.0D);
/*      */     
/*  825 */     return (JPanel)p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JPanel buildSecurityPanel() {
/*  832 */     JGridBagPanel.InsetsManager im = new JGridBagPanel.InsetsManager() {
/*  833 */         protected Insets i1 = new Insets(5, 5, 0, 0);
/*  834 */         protected Insets i2 = new Insets(5, 0, 0, 0);
/*  835 */         protected Insets i3 = new Insets(0, 5, 0, 0);
/*  836 */         protected Insets i4 = new Insets(0, 0, 0, 0);
/*      */         
/*      */         public Insets getInsets(int x, int y) {
/*  839 */           if (y == 1 || y == 3 || y == 5 || y == 6) {
/*  840 */             return (x == 0) ? this.i2 : this.i1;
/*      */           }
/*  842 */           return (x == 0) ? this.i4 : this.i3;
/*      */         }
/*      */       };
/*      */     
/*  846 */     JGridBagPanel p = new JGridBagPanel(im);
/*  847 */     p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
/*      */     
/*  849 */     this.enforceSecureScripting = new JCheckBox(Resources.getString("PreferenceDialog.label.enforce.secure.scripting"));
/*  850 */     this.enforceSecureScripting.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/*  852 */             boolean b = PreferenceDialog.this.enforceSecureScripting.isSelected();
/*  853 */             PreferenceDialog.this.grantScriptFileAccess.setEnabled(b);
/*  854 */             PreferenceDialog.this.grantScriptNetworkAccess.setEnabled(b);
/*      */           }
/*      */         });
/*      */     
/*  858 */     JLabel grantScript = new JLabel(Resources.getString("PreferenceDialog.label.grant.scripts.access.to"));
/*  859 */     grantScript.setVerticalAlignment(1);
/*  860 */     grantScript.setOpaque(true);
/*  861 */     this.grantScriptFileAccess = new JCheckBox(Resources.getString("PreferenceDialog.label.file.system"));
/*  862 */     this.grantScriptNetworkAccess = new JCheckBox(Resources.getString("PreferenceDialog.label.all.network"));
/*      */     
/*  864 */     JLabel loadScripts = new JLabel(Resources.getString("PreferenceDialog.label.load.scripts"));
/*  865 */     loadScripts.setVerticalAlignment(1);
/*  866 */     this.loadJava = new JCheckBox(Resources.getString("PreferenceDialog.label.java.jar.files"));
/*  867 */     this.loadEcmascript = new JCheckBox(Resources.getString("PreferenceDialog.label.ecmascript"));
/*      */     
/*  869 */     String[] origins = { Resources.getString("PreferenceDialog.label.origin.any"), Resources.getString("PreferenceDialog.label.origin.document"), Resources.getString("PreferenceDialog.label.origin.embedded"), Resources.getString("PreferenceDialog.label.origin.none") };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  875 */     JLabel scriptOriginLabel = new JLabel(Resources.getString("PreferenceDialog.label.allowed.script.origin"));
/*  876 */     this.allowedScriptOrigin = new JComboBox<String>(origins);
/*  877 */     JLabel resourceOriginLabel = new JLabel(Resources.getString("PreferenceDialog.label.allowed.resource.origin"));
/*  878 */     this.allowedResourceOrigin = new JComboBox<String>(origins);
/*      */     
/*  880 */     p.add(this.enforceSecureScripting, 1, 0, 1, 1, 17, 0, 1.0D, 0.0D);
/*  881 */     p.add(grantScript, 0, 1, 1, 1, 13, 0, 1.0D, 0.0D);
/*  882 */     p.add(this.grantScriptFileAccess, 1, 1, 1, 1, 17, 0, 1.0D, 0.0D);
/*  883 */     p.add(this.grantScriptNetworkAccess, 1, 2, 1, 1, 17, 0, 1.0D, 0.0D);
/*  884 */     p.add(loadScripts, 0, 3, 1, 1, 13, 0, 1.0D, 0.0D);
/*  885 */     p.add(this.loadJava, 1, 3, 1, 1, 17, 0, 1.0D, 0.0D);
/*  886 */     p.add(this.loadEcmascript, 1, 4, 1, 1, 17, 0, 1.0D, 0.0D);
/*  887 */     p.add(scriptOriginLabel, 0, 5, 1, 1, 13, 0, 1.0D, 0.0D);
/*  888 */     p.add(this.allowedScriptOrigin, 1, 5, 1, 1, 17, 0, 1.0D, 0.0D);
/*  889 */     p.add(resourceOriginLabel, 0, 6, 1, 1, 13, 0, 1.0D, 0.0D);
/*  890 */     p.add(this.allowedResourceOrigin, 1, 6, 1, 1, 17, 0, 1.0D, 0.0D);
/*      */     
/*  892 */     return (JPanel)p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JPanel buildLanguagePanel() {
/*  899 */     JPanel p = new JPanel();
/*  900 */     p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
/*  901 */     this.languagePanel = new LanguageDialog.Panel();
/*  902 */     this.languagePanel.setBorder(BorderFactory.createEmptyBorder());
/*  903 */     Color c = UIManager.getColor("Window.background");
/*  904 */     this.languagePanel.getComponent(0).setBackground(c);
/*  905 */     this.languagePanel.getComponent(1).setBackground(c);
/*  906 */     p.add((Component)this.languagePanel);
/*  907 */     return p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JPanel buildStylesheetPanel() {
/*  914 */     JGridBagPanel.InsetsManager im = new JGridBagPanel.InsetsManager() {
/*  915 */         protected Insets i1 = new Insets(5, 5, 0, 0);
/*  916 */         protected Insets i2 = new Insets(5, 0, 0, 0);
/*  917 */         protected Insets i3 = new Insets(0, 5, 0, 0);
/*  918 */         protected Insets i4 = new Insets(0, 0, 0, 0);
/*      */         
/*      */         public Insets getInsets(int x, int y) {
/*  921 */           if (y >= 1 && y <= 5) {
/*  922 */             return (x == 0) ? this.i2 : this.i1;
/*      */           }
/*  924 */           return (x == 0) ? this.i4 : this.i3;
/*      */         }
/*      */       };
/*      */     
/*  928 */     JGridBagPanel p = new JGridBagPanel(im);
/*  929 */     p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
/*      */     
/*  931 */     this.userStylesheetEnabled = new JCheckBox(Resources.getString("PreferenceDialog.label.enable.user.stylesheet"));
/*  932 */     this.userStylesheetEnabled.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/*  934 */             boolean b = PreferenceDialog.this.userStylesheetEnabled.isSelected();
/*  935 */             PreferenceDialog.this.userStylesheetLabel.setEnabled(b);
/*  936 */             PreferenceDialog.this.userStylesheet.setEnabled(b);
/*  937 */             PreferenceDialog.this.userStylesheetBrowse.setEnabled(b);
/*      */           }
/*      */         });
/*      */     
/*  941 */     this.userStylesheetLabel = new JLabel(Resources.getString("PreferenceDialog.label.user.stylesheet"));
/*  942 */     this.userStylesheet = new JTextField();
/*  943 */     this.userStylesheetBrowse = new JButton(Resources.getString("PreferenceDialog.label.browse"));
/*  944 */     this.userStylesheetBrowse.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/*  946 */             File f = null;
/*  947 */             if (Platform.isOSX) {
/*  948 */               FileDialog fileDialog = new FileDialog((Frame)PreferenceDialog.this.getOwner(), Resources.getString("PreferenceDialog.BrowseWindow.title"));
/*      */ 
/*      */ 
/*      */               
/*  952 */               fileDialog.setVisible(true);
/*  953 */               String filename = fileDialog.getFile();
/*  954 */               if (filename != null) {
/*  955 */                 String dirname = fileDialog.getDirectory();
/*  956 */                 f = new File(dirname, filename);
/*      */               } 
/*      */             } else {
/*  959 */               JFileChooser fileChooser = new JFileChooser(new File("."));
/*  960 */               fileChooser.setDialogTitle(Resources.getString("PreferenceDialog.BrowseWindow.title"));
/*      */               
/*  962 */               fileChooser.setFileHidingEnabled(false);
/*      */               
/*  964 */               int choice = fileChooser.showOpenDialog(PreferenceDialog.this);
/*      */               
/*  966 */               if (choice == 0) {
/*  967 */                 f = fileChooser.getSelectedFile();
/*      */               }
/*      */             } 
/*  970 */             if (f != null) {
/*      */               try {
/*  972 */                 PreferenceDialog.this.userStylesheet.setText(f.getCanonicalPath());
/*  973 */               } catch (IOException iOException) {}
/*      */             }
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  979 */     JLabel mediaLabel = new JLabel(Resources.getString("PreferenceDialog.label.css.media.types"));
/*  980 */     mediaLabel.setVerticalAlignment(1);
/*  981 */     this.mediaList = new JList();
/*  982 */     this.mediaList.setSelectionMode(0);
/*  983 */     this.mediaList.setModel(this.mediaListModel);
/*  984 */     this.mediaList.addListSelectionListener(new ListSelectionListener() {
/*      */           public void valueChanged(ListSelectionEvent e) {
/*  986 */             PreferenceDialog.this.updateMediaListButtons();
/*      */           }
/*      */         });
/*  989 */     this.mediaListModel.addListDataListener(new ListDataListener() {
/*      */           public void contentsChanged(ListDataEvent e) {
/*  991 */             PreferenceDialog.this.updateMediaListButtons();
/*      */           }
/*      */           public void intervalAdded(ListDataEvent e) {
/*  994 */             PreferenceDialog.this.updateMediaListButtons();
/*      */           }
/*      */           public void intervalRemoved(ListDataEvent e) {
/*  997 */             PreferenceDialog.this.updateMediaListButtons();
/*      */           }
/*      */         });
/* 1000 */     JScrollPane scrollPane = new JScrollPane();
/* 1001 */     scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
/* 1002 */     scrollPane.getViewport().add(this.mediaList);
/*      */     
/* 1004 */     JButton addButton = new JButton(Resources.getString("PreferenceDialog.label.add"));
/* 1005 */     addButton.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1007 */             CSSMediaPanel.AddMediumDialog dialog = new CSSMediaPanel.AddMediumDialog(PreferenceDialog.this);
/*      */             
/* 1009 */             dialog.pack();
/* 1010 */             dialog.setVisible(true);
/*      */             
/* 1012 */             if (dialog.getReturnCode() == 1 || dialog.getMedium() == null) {
/*      */               return;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1018 */             String medium = dialog.getMedium().trim();
/* 1019 */             if (medium.length() == 0 || PreferenceDialog.this.mediaListModel.contains(medium)) {
/*      */               return;
/*      */             }
/*      */             
/* 1023 */             int i = 0;
/* 1024 */             for (; i < PreferenceDialog.this.mediaListModel.size() && medium != null; 
/* 1025 */               i++) {
/* 1026 */               String s = PreferenceDialog.this.mediaListModel.getElementAt(i);
/* 1027 */               int c = medium.compareTo(s);
/* 1028 */               if (c == 0) {
/* 1029 */                 medium = null;
/* 1030 */               } else if (c < 0) {
/* 1031 */                 PreferenceDialog.this.mediaListModel.insertElementAt(medium, i);
/* 1032 */                 medium = null;
/*      */               } 
/*      */             } 
/* 1035 */             if (medium != null) {
/* 1036 */               PreferenceDialog.this.mediaListModel.addElement(medium);
/*      */             }
/*      */           }
/*      */         });
/*      */     
/* 1041 */     this.mediaListRemoveButton = new JButton(Resources.getString("PreferenceDialog.label.remove"));
/* 1042 */     this.mediaListRemoveButton.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1044 */             int index = PreferenceDialog.this.mediaList.getSelectedIndex();
/* 1045 */             PreferenceDialog.this.mediaList.clearSelection();
/* 1046 */             if (index >= 0) {
/* 1047 */               PreferenceDialog.this.mediaListModel.removeElementAt(index);
/*      */             }
/*      */           }
/*      */         });
/*      */     
/* 1052 */     this.mediaListClearButton = new JButton(Resources.getString("PreferenceDialog.label.clear"));
/* 1053 */     this.mediaListClearButton.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent e) {
/* 1055 */             PreferenceDialog.this.mediaList.clearSelection();
/* 1056 */             PreferenceDialog.this.mediaListModel.removeAllElements();
/*      */           }
/*      */         });
/*      */     
/* 1060 */     p.add(this.userStylesheetEnabled, 1, 0, 2, 1, 17, 0, 0.0D, 0.0D);
/* 1061 */     p.add(this.userStylesheetLabel, 0, 1, 1, 1, 13, 0, 0.0D, 0.0D);
/* 1062 */     p.add(this.userStylesheet, 1, 1, 1, 1, 17, 2, 1.0D, 0.0D);
/* 1063 */     p.add(this.userStylesheetBrowse, 2, 1, 1, 1, 17, 2, 0.0D, 0.0D);
/* 1064 */     p.add(mediaLabel, 0, 2, 1, 1, 13, 3, 0.0D, 0.0D);
/* 1065 */     p.add(scrollPane, 1, 2, 1, 4, 17, 1, 1.0D, 1.0D);
/* 1066 */     p.add(new JPanel(), 2, 2, 1, 1, 17, 1, 0.0D, 1.0D);
/* 1067 */     p.add(addButton, 2, 3, 1, 1, 16, 2, 0.0D, 0.0D);
/* 1068 */     p.add(this.mediaListRemoveButton, 2, 4, 1, 1, 16, 2, 0.0D, 0.0D);
/* 1069 */     p.add(this.mediaListClearButton, 2, 5, 1, 1, 16, 2, 0.0D, 0.0D);
/*      */     
/* 1071 */     return (JPanel)p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateMediaListButtons() {
/* 1078 */     this.mediaListRemoveButton.setEnabled(!this.mediaList.isSelectionEmpty());
/* 1079 */     this.mediaListClearButton.setEnabled(!this.mediaListModel.isEmpty());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JPanel buildNetworkPanel() {
/* 1086 */     JGridBagPanel p = new JGridBagPanel();
/* 1087 */     p.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
/*      */     
/* 1089 */     JLabel proxyLabel = new JLabel(Resources.getString("PreferenceDialog.label.http.proxy"));
/* 1090 */     JLabel hostLabel = new JLabel(Resources.getString("PreferenceDialog.label.host"));
/* 1091 */     JLabel portLabel = new JLabel(Resources.getString("PreferenceDialog.label.port"));
/* 1092 */     JLabel colonLabel = new JLabel(Resources.getString("PreferenceDialog.label.colon"));
/* 1093 */     Font f = hostLabel.getFont();
/* 1094 */     float size = f.getSize2D() * 0.85F;
/* 1095 */     f = f.deriveFont(size);
/* 1096 */     hostLabel.setFont(f);
/* 1097 */     portLabel.setFont(f);
/* 1098 */     this.host = new JTextField();
/* 1099 */     this.host.setPreferredSize(new Dimension(200, 20));
/* 1100 */     this.port = new JTextField();
/* 1101 */     this.port.setPreferredSize(new Dimension(40, 20));
/*      */     
/* 1103 */     p.add(proxyLabel, 0, 0, 1, 1, 13, 0, 0.0D, 0.0D);
/* 1104 */     p.add(this.host, 1, 0, 1, 1, 17, 2, 0.0D, 0.0D);
/* 1105 */     p.add(colonLabel, 2, 0, 1, 1, 17, 0, 0.0D, 0.0D);
/* 1106 */     p.add(this.port, 3, 0, 1, 1, 17, 2, 0.0D, 0.0D);
/* 1107 */     p.add(hostLabel, 1, 1, 1, 1, 17, 0, 0.0D, 0.0D);
/* 1108 */     p.add(portLabel, 3, 1, 1, 1, 17, 0, 0.0D, 0.0D);
/*      */     
/* 1110 */     return (JPanel)p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int showDialog() {
/* 1117 */     if (Platform.isOSX) {
/*      */       
/* 1119 */       this.returnCode = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1123 */       this.returnCode = 1;
/*      */     } 
/* 1125 */     pack();
/* 1126 */     setVisible(true);
/* 1127 */     return this.returnCode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class JConfigurationPanel
/*      */     extends JPanel
/*      */   {
/*      */     protected JToolBar toolbar;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JPanel panel;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected CardLayout layout;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected ButtonGroup group;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1158 */     protected int page = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public JConfigurationPanel() {
/* 1164 */       this.toolbar = new JToolBar();
/* 1165 */       this.toolbar.setFloatable(false);
/* 1166 */       this.toolbar.setLayout(new FlowLayout(3, 0, 0));
/* 1167 */       this.toolbar.add(new JToolBar.Separator(new Dimension(8, 8)));
/* 1168 */       if (Platform.isOSX || PreferenceDialog.isMetalSteel()) {
/* 1169 */         this.toolbar.setBackground(new Color(248, 248, 248));
/*      */       }
/* 1171 */       this.toolbar.setOpaque(true);
/* 1172 */       this.panel = new JPanel();
/* 1173 */       this.layout = Platform.isOSX ? new ResizingCardLayout() : new CardLayout();
/* 1174 */       this.group = new ButtonGroup();
/* 1175 */       setLayout(new BorderLayout());
/* 1176 */       this.panel.setLayout(this.layout);
/* 1177 */       add(this.toolbar, "North");
/* 1178 */       add(this.panel);
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
/*      */     public void addPanel(String text, Icon icon, Icon icon2, JPanel p) {
/* 1190 */       JToggleButton button = new JToggleButton(text, icon);
/* 1191 */       button.setVerticalTextPosition(3);
/* 1192 */       button.setHorizontalTextPosition(0);
/* 1193 */       button.setContentAreaFilled(false);
/*      */ 
/*      */       
/*      */       try {
/* 1197 */         AbstractButton.class.getMethod("setIconTextGap", new Class[] { int.class }).invoke(button, new Object[] { Integer.valueOf(0) });
/*      */       
/*      */       }
/* 1200 */       catch (Exception exception) {}
/*      */       
/* 1202 */       button.setPressedIcon(icon2);
/* 1203 */       this.group.add(button);
/* 1204 */       this.toolbar.add(button);
/* 1205 */       this.toolbar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
/* 1206 */       button.addItemListener(new ItemListener() {
/*      */             public void itemStateChanged(ItemEvent e) {
/* 1208 */               JToggleButton b = (JToggleButton)e.getSource();
/* 1209 */               switch (e.getStateChange()) {
/*      */                 case 1:
/* 1211 */                   PreferenceDialog.JConfigurationPanel.this.select(b);
/*      */                   break;
/*      */                 case 2:
/* 1214 */                   PreferenceDialog.JConfigurationPanel.this.unselect(b);
/*      */                   break;
/*      */               } 
/*      */             }
/*      */           });
/* 1219 */       if (this.panel.getComponentCount() == 0) {
/* 1220 */         button.setSelected(true);
/* 1221 */         this.page = 0;
/*      */       } else {
/* 1223 */         unselect(button);
/*      */       } 
/* 1225 */       this.panel.add(p, text.intern());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected int getComponentIndex(Component c) {
/* 1232 */       Container p = c.getParent();
/* 1233 */       int count = p.getComponentCount();
/* 1234 */       for (int i = 0; i < count; i++) {
/* 1235 */         if (p.getComponent(i) == c) {
/* 1236 */           return i;
/*      */         }
/*      */       } 
/* 1239 */       return -1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void select(JToggleButton b) {
/* 1247 */       b.setOpaque(true);
/* 1248 */       b.setBackground(Platform.isOSX ? new Color(216, 216, 216) : UIManager.getColor("List.selectionBackground"));
/*      */ 
/*      */       
/* 1251 */       b.setForeground(UIManager.getColor("List.selectionForeground"));
/* 1252 */       b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(160, 160, 160)), BorderFactory.createEmptyBorder(4, 3, 4, 3)));
/*      */ 
/*      */ 
/*      */       
/* 1256 */       this.layout.show(this.panel, b.getText().intern());
/* 1257 */       this.page = getComponentIndex(b) - 1;
/* 1258 */       if (Platform.isOSX) {
/* 1259 */         PreferenceDialog.this.setTitle(b.getText());
/*      */       }
/* 1261 */       PreferenceDialog.this.pack();
/* 1262 */       this.panel.grabFocus();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void unselect(JToggleButton b) {
/* 1270 */       b.setOpaque(false);
/* 1271 */       b.setBackground((Color)null);
/* 1272 */       b.setForeground(UIManager.getColor("Button.foreground"));
/* 1273 */       b.setBorder(BorderFactory.createEmptyBorder(5, 4, 5, 4));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class ResizingCardLayout
/*      */       extends CardLayout
/*      */     {
/*      */       public ResizingCardLayout() {
/* 1286 */         super(0, 0);
/*      */       }
/*      */       
/*      */       public Dimension preferredLayoutSize(Container parent) {
/* 1290 */         Dimension d = super.preferredLayoutSize(parent);
/* 1291 */         if (PreferenceDialog.JConfigurationPanel.this.page != -1) {
/* 1292 */           Dimension cur = PreferenceDialog.JConfigurationPanel.this.panel.getComponent(PreferenceDialog.JConfigurationPanel.this.page).getPreferredSize();
/* 1293 */           d = new Dimension((int)d.getWidth(), (int)cur.getHeight());
/*      */         } 
/*      */         
/* 1296 */         return d;
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/PreferenceDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */