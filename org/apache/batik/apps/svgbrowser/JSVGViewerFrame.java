/*      */ package org.apache.batik.apps.svgbrowser;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.FileDialog;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ComponentAdapter;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseMotionAdapter;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.NoninvertibleTransformException;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.BufferedImageOp;
/*      */ import java.awt.print.PrinterException;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FilenameFilter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.Reader;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.MalformedURLException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.MissingResourceException;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Vector;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.ActionMap;
/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.InputMap;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JDialog;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRadioButtonMenuItem;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JSeparator;
/*      */ import javax.swing.JToolBar;
/*      */ import javax.swing.JWindow;
/*      */ import javax.swing.KeyStroke;
/*      */ import javax.swing.filechooser.FileFilter;
/*      */ import javax.swing.text.Document;
/*      */ import org.apache.batik.anim.dom.SVGOMDocument;
/*      */ import org.apache.batik.bridge.DefaultExternalResourceSecurity;
/*      */ import org.apache.batik.bridge.DefaultScriptSecurity;
/*      */ import org.apache.batik.bridge.EmbededExternalResourceSecurity;
/*      */ import org.apache.batik.bridge.EmbededScriptSecurity;
/*      */ import org.apache.batik.bridge.ExternalResourceSecurity;
/*      */ import org.apache.batik.bridge.NoLoadExternalResourceSecurity;
/*      */ import org.apache.batik.bridge.NoLoadScriptSecurity;
/*      */ import org.apache.batik.bridge.RelaxedExternalResourceSecurity;
/*      */ import org.apache.batik.bridge.RelaxedScriptSecurity;
/*      */ import org.apache.batik.bridge.ScriptSecurity;
/*      */ import org.apache.batik.bridge.UpdateManager;
/*      */ import org.apache.batik.bridge.UpdateManagerEvent;
/*      */ import org.apache.batik.bridge.UpdateManagerListener;
/*      */ import org.apache.batik.dom.StyleSheetProcessingInstruction;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.ext.swing.JAffineTransformChooser;
/*      */ import org.apache.batik.swing.JSVGCanvas;
/*      */ import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
/*      */ import org.apache.batik.swing.gvt.GVTTreeRendererListener;
/*      */ import org.apache.batik.swing.gvt.Overlay;
/*      */ import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
/*      */ import org.apache.batik.swing.svg.GVTTreeBuilderListener;
/*      */ import org.apache.batik.swing.svg.LinkActivationEvent;
/*      */ import org.apache.batik.swing.svg.LinkActivationListener;
/*      */ import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
/*      */ import org.apache.batik.swing.svg.SVGDocumentLoaderListener;
/*      */ import org.apache.batik.swing.svg.SVGFileFilter;
/*      */ import org.apache.batik.swing.svg.SVGLoadEventDispatcherEvent;
/*      */ import org.apache.batik.swing.svg.SVGLoadEventDispatcherListener;
/*      */ import org.apache.batik.swing.svg.SVGUserAgent;
/*      */ import org.apache.batik.transcoder.TranscoderInput;
/*      */ import org.apache.batik.transcoder.TranscoderOutput;
/*      */ import org.apache.batik.transcoder.image.ImageTranscoder;
/*      */ import org.apache.batik.transcoder.image.JPEGTranscoder;
/*      */ import org.apache.batik.transcoder.image.PNGTranscoder;
/*      */ import org.apache.batik.transcoder.image.TIFFTranscoder;
/*      */ import org.apache.batik.transcoder.print.PrintTranscoder;
/*      */ import org.apache.batik.transcoder.svg2svg.SVGTranscoder;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.apache.batik.util.Platform;
/*      */ import org.apache.batik.util.Service;
/*      */ import org.apache.batik.util.gui.JErrorPane;
/*      */ import org.apache.batik.util.gui.LocationBar;
/*      */ import org.apache.batik.util.gui.MemoryMonitor;
/*      */ import org.apache.batik.util.gui.URIChooser;
/*      */ import org.apache.batik.util.gui.resource.ActionMap;
/*      */ import org.apache.batik.util.gui.resource.JComponentModifier;
/*      */ import org.apache.batik.util.gui.resource.MenuFactory;
/*      */ import org.apache.batik.util.gui.resource.MissingListenerException;
/*      */ import org.apache.batik.util.gui.resource.ToolBarFactory;
/*      */ import org.apache.batik.util.gui.xmleditor.XMLDocument;
/*      */ import org.apache.batik.util.gui.xmleditor.XMLTextEditor;
/*      */ import org.apache.batik.util.resources.ResourceManager;
/*      */ import org.apache.batik.xml.XMLUtilities;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.css.ViewCSS;
/*      */ import org.w3c.dom.svg.SVGDocument;
/*      */ import org.w3c.dom.svg.SVGSVGElement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JSVGViewerFrame
/*      */   extends JFrame
/*      */   implements UpdateManagerListener, GVTTreeRendererListener, GVTTreeBuilderListener, LinkActivationListener, SVGDocumentLoaderListener, SVGLoadEventDispatcherListener, ActionMap
/*      */ {
/*      */   private static String EOL;
/*      */   
/*      */   static {
/*      */     try {
/*  172 */       EOL = System.getProperty("line.separator", "\n");
/*  173 */     } catch (SecurityException e) {
/*  174 */       EOL = "\n";
/*      */     } 
/*      */   }
/*      */   protected static boolean priorJDK1_4 = true; protected static final String JDK_1_4_PRESENCE_TEST_CLASS = "java.util.logging.LoggingPermission"; public static final String RESOURCES = "org.apache.batik.apps.svgbrowser.resources.GUI"; public static final String ABOUT_ACTION = "AboutAction"; public static final String OPEN_ACTION = "OpenAction"; public static final String OPEN_LOCATION_ACTION = "OpenLocationAction"; public static final String NEW_WINDOW_ACTION = "NewWindowAction"; public static final String RELOAD_ACTION = "ReloadAction"; public static final String SAVE_AS_ACTION = "SaveAsAction"; public static final String BACK_ACTION = "BackAction"; public static final String FORWARD_ACTION = "ForwardAction"; public static final String FULL_SCREEN_ACTION = "FullScreenAction"; public static final String PRINT_ACTION = "PrintAction"; public static final String EXPORT_AS_JPG_ACTION = "ExportAsJPGAction"; public static final String EXPORT_AS_PNG_ACTION = "ExportAsPNGAction"; public static final String EXPORT_AS_TIFF_ACTION = "ExportAsTIFFAction"; public static final String PREFERENCES_ACTION = "PreferencesAction"; public static final String CLOSE_ACTION = "CloseAction"; public static final String VIEW_SOURCE_ACTION = "ViewSourceAction"; public static final String EXIT_ACTION = "ExitAction"; public static final String RESET_TRANSFORM_ACTION = "ResetTransformAction"; public static final String ZOOM_IN_ACTION = "ZoomInAction"; public static final String ZOOM_OUT_ACTION = "ZoomOutAction"; public static final String PREVIOUS_TRANSFORM_ACTION = "PreviousTransformAction";
/*      */   public static final String NEXT_TRANSFORM_ACTION = "NextTransformAction";
/*      */   public static final String USE_STYLESHEET_ACTION = "UseStylesheetAction";
/*      */   public static final String PLAY_ACTION = "PlayAction";
/*      */   public static final String PAUSE_ACTION = "PauseAction";
/*      */   public static final String STOP_ACTION = "StopAction";
/*      */   public static final String MONITOR_ACTION = "MonitorAction";
/*      */   public static final String DOM_VIEWER_ACTION = "DOMViewerAction";
/*      */   public static final String SET_TRANSFORM_ACTION = "SetTransformAction";
/*      */   public static final String FIND_DIALOG_ACTION = "FindDialogAction";
/*      */   public static final String THUMBNAIL_DIALOG_ACTION = "ThumbnailDialogAction";
/*      */   public static final String FLUSH_ACTION = "FlushAction";
/*      */   public static final String TOGGLE_DEBUGGER_ACTION = "ToggleDebuggerAction";
/*      */   
/*      */   static {
/*      */     try {
/*  193 */       Class.forName("java.util.logging.LoggingPermission");
/*  194 */       priorJDK1_4 = false;
/*  195 */     } catch (ClassNotFoundException classNotFoundException) {}
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  243 */   public static final Cursor WAIT_CURSOR = new Cursor(3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  249 */   public static final Cursor DEFAULT_CURSOR = new Cursor(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  255 */   public static final String PROPERTY_OS_NAME = Resources.getString("JSVGViewerFrame.property.os.name");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  261 */   public static final String PROPERTY_OS_NAME_DEFAULT = Resources.getString("JSVGViewerFrame.property.os.name.default");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  268 */   public static final String PROPERTY_OS_WINDOWS_PREFIX = Resources.getString("JSVGViewerFrame.property.os.windows.prefix");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final String OPEN_TITLE = "Open.title";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Vector handlers;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  284 */   protected static SquiggleInputHandler defaultHandler = new SVGInputHandler();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  296 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.GUI", Locale.getDefault());
/*  297 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*      */ 
/*      */   
/*      */   protected Application application;
/*      */ 
/*      */   
/*      */   protected Canvas svgCanvas;
/*      */ 
/*      */   
/*      */   protected JPanel svgCanvasPanel;
/*      */ 
/*      */   
/*      */   protected JWindow window;
/*      */ 
/*      */   
/*      */   protected static JFrame memoryMonitorFrame;
/*      */ 
/*      */ 
/*      */   
/*      */   protected class Canvas
/*      */     extends JSVGCanvas
/*      */   {
/*      */     public Canvas(SVGUserAgent ua, boolean eventsEnabled, boolean selectableText) {
/*  320 */       super(ua, eventsEnabled, selectableText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getRhinoInterpreter() {
/*  327 */       if (this.bridgeContext == null) {
/*  328 */         return null;
/*      */       }
/*  330 */       return this.bridgeContext.getInterpreter("text/ecmascript");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected class JSVGViewerDOMViewerController
/*      */       implements DOMViewerController
/*      */     {
/*      */       public boolean canEdit() {
/*  341 */         return (JSVGViewerFrame.Canvas.this.getUpdateManager() != null);
/*      */       }
/*      */       
/*      */       public ElementOverlayManager createSelectionManager() {
/*  345 */         if (canEdit()) {
/*  346 */           return new ElementOverlayManager(JSVGViewerFrame.Canvas.this);
/*      */         }
/*  348 */         return null;
/*      */       }
/*      */       
/*      */       public Document getDocument() {
/*  352 */         return (Document)JSVGViewerFrame.Canvas.this.svgDocument;
/*      */       }
/*      */       
/*      */       public void performUpdate(Runnable r) {
/*  356 */         if (canEdit()) {
/*  357 */           JSVGViewerFrame.Canvas.this.getUpdateManager().getUpdateRunnableQueue().invokeLater(r);
/*      */         } else {
/*  359 */           r.run();
/*      */         } 
/*      */       }
/*      */       
/*      */       public void removeSelectionOverlay(Overlay selectionOverlay) {
/*  364 */         JSVGViewerFrame.Canvas.this.getOverlays().remove(selectionOverlay);
/*      */       }
/*      */       
/*      */       public void selectNode(Node node) {
/*  368 */         JSVGViewerFrame.DOMViewerAction dViewerAction = (JSVGViewerFrame.DOMViewerAction)JSVGViewerFrame.this.getAction("DOMViewerAction");
/*      */         
/*  370 */         dViewerAction.openDOMViewer();
/*  371 */         JSVGViewerFrame.this.domViewer.selectNode(node);
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
/*  394 */   protected File currentPath = new File("");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  399 */   protected File currentSavePath = new File("");
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  404 */   protected BackAction backAction = new BackAction();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  409 */   protected ForwardAction forwardAction = new ForwardAction();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  414 */   protected PlayAction playAction = new PlayAction();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  419 */   protected PauseAction pauseAction = new PauseAction();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  424 */   protected StopAction stopAction = new StopAction();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  429 */   protected PreviousTransformAction previousTransformAction = new PreviousTransformAction();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  435 */   protected NextTransformAction nextTransformAction = new NextTransformAction();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  441 */   protected UseStylesheetAction useStylesheetAction = new UseStylesheetAction();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean debug;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean autoAdjust = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean managerStopped;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  462 */   protected SVGUserAgent userAgent = new UserAgent();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SVGDocument svgDocument;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected URIChooser uriChooser;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected DOMViewer domViewer;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected FindDialog findDialog;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ThumbnailDialog thumbnailDialog;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JAffineTransformChooser.Dialog transformDialog;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LocationBar locationBar;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected StatusBar statusBar;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String title;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected LocalHistory localHistory;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  517 */   protected TransformHistory transformHistory = new TransformHistory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String alternateStyleSheet;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Debugger debugger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map listeners;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long time;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispose() {
/*  840 */     hideDebugger();
/*  841 */     this.svgCanvas.dispose();
/*  842 */     super.dispose();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDebug(boolean b) {
/*  849 */     this.debug = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoAdjust(boolean b) {
/*  856 */     this.autoAdjust = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSVGCanvas getJSVGCanvas() {
/*  863 */     return this.svgCanvas;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static File makeAbsolute(File f) {
/*  870 */     if (!f.isAbsolute()) {
/*  871 */       return f.getAbsoluteFile();
/*      */     }
/*  873 */     return f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showDebugger() {
/*  880 */     if (this.debugger == null && Debugger.isPresent) {
/*  881 */       this.debugger = new Debugger(this, this.locationBar.getText());
/*  882 */       this.debugger.initialize();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void hideDebugger() {
/*  890 */     if (this.debugger != null) {
/*  891 */       this.debugger.clearAllBreakpoints();
/*  892 */       this.debugger.go();
/*  893 */       this.debugger.dispose();
/*  894 */       this.debugger = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class Debugger
/*      */   {
/*      */     protected static boolean isPresent;
/*      */ 
/*      */     
/*      */     protected static Class debuggerClass;
/*      */ 
/*      */     
/*      */     protected static Class contextFactoryClass;
/*      */ 
/*      */     
/*      */     protected static final int CLEAR_ALL_BREAKPOINTS_METHOD = 0;
/*      */ 
/*      */     
/*      */     protected static final int GO_METHOD = 1;
/*      */ 
/*      */     
/*      */     protected static final int SET_EXIT_ACTION_METHOD = 2;
/*      */ 
/*      */     
/*      */     protected static final int ATTACH_TO_METHOD = 3;
/*      */ 
/*      */     
/*      */     protected static final int DETACH_METHOD = 4;
/*      */ 
/*      */     
/*      */     protected static final int DISPOSE_METHOD = 5;
/*      */ 
/*      */     
/*      */     protected static final int GET_DEBUG_FRAME_METHOD = 6;
/*      */ 
/*      */     
/*      */     protected static Constructor debuggerConstructor;
/*      */ 
/*      */     
/*      */     protected static Method[] debuggerMethods;
/*      */ 
/*      */     
/*      */     protected static Class rhinoInterpreterClass;
/*      */ 
/*      */     
/*      */     protected static Method getContextFactoryMethod;
/*      */     
/*      */     protected Object debuggerInstance;
/*      */     
/*      */     protected JSVGViewerFrame svgFrame;
/*      */ 
/*      */     
/*      */     static {
/*      */       
/*  950 */       try { Class<?> dc = Class.forName("org.mozilla.javascript.tools.debugger.Main");
/*      */         
/*  952 */         Class<?> cfc = Class.forName("org.mozilla.javascript.ContextFactory");
/*      */         
/*  954 */         rhinoInterpreterClass = Class.forName("org.apache.batik.script.rhino.RhinoInterpreter");
/*      */         
/*  956 */         debuggerConstructor = dc.getConstructor(new Class[] { String.class });
/*      */         
/*  958 */         debuggerMethods = new Method[] { dc.getMethod("clearAllBreakpoints", (Class[])null), dc.getMethod("go", (Class[])null), dc.getMethod("setExitAction", new Class[] { Runnable.class }), dc.getMethod("attachTo", new Class[] { cfc }), dc.getMethod("detach", (Class[])null), dc.getMethod("dispose", (Class[])null), dc.getMethod("getDebugFrame", (Class[])null) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  967 */         getContextFactoryMethod = rhinoInterpreterClass.getMethod("getContextFactory", (Class[])null);
/*      */ 
/*      */         
/*  970 */         debuggerClass = dc;
/*  971 */         isPresent = true; }
/*  972 */       catch (ClassNotFoundException classNotFoundException) {  }
/*  973 */       catch (NoSuchMethodException noSuchMethodException) {  }
/*  974 */       catch (SecurityException securityException) {}
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
/*      */     public Debugger(JSVGViewerFrame frame, String url) {
/*  992 */       this.svgFrame = frame;
/*      */       try {
/*  994 */         this.debuggerInstance = debuggerConstructor.newInstance(new Object[] { "JavaScript Debugger - " + url });
/*      */       }
/*  996 */       catch (IllegalAccessException iae) {
/*  997 */         throw new RuntimeException(iae.getMessage());
/*  998 */       } catch (InvocationTargetException ite) {
/*  999 */         ite.printStackTrace();
/* 1000 */         throw new RuntimeException(ite.getMessage());
/* 1001 */       } catch (InstantiationException ie) {
/* 1002 */         throw new RuntimeException(ie.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setDocumentURL(String url) {
/* 1010 */       getDebugFrame().setTitle("JavaScript Debugger - " + url);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void initialize() {
/* 1021 */       JFrame debugGui = getDebugFrame();
/* 1022 */       JMenuBar menuBar = debugGui.getJMenuBar();
/* 1023 */       JMenu menu = menuBar.getMenu(0);
/* 1024 */       menu.getItem(0).setEnabled(false);
/* 1025 */       menu.getItem(1).setEnabled(false);
/* 1026 */       menu.getItem(3).setText(Resources.getString("Close.text"));
/*      */       
/* 1028 */       menu.getItem(3).setAccelerator(KeyStroke.getKeyStroke(87, 2));
/*      */ 
/*      */       
/* 1031 */       debugGui.setSize(600, 460);
/* 1032 */       debugGui.pack();
/* 1033 */       setExitAction(new Runnable() {
/*      */             public void run() {
/* 1035 */               JSVGViewerFrame.Debugger.this.svgFrame.hideDebugger(); }
/*      */           });
/* 1037 */       WindowAdapter wa = new WindowAdapter() {
/*      */           public void windowClosing(WindowEvent e) {
/* 1039 */             JSVGViewerFrame.Debugger.this.svgFrame.hideDebugger(); }
/*      */         };
/* 1041 */       debugGui.addWindowListener(wa);
/* 1042 */       debugGui.setVisible(true);
/* 1043 */       attach();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void attach() {
/* 1050 */       Object interpreter = this.svgFrame.svgCanvas.getRhinoInterpreter();
/* 1051 */       if (interpreter != null) {
/* 1052 */         attachTo(getContextFactory(interpreter));
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected JFrame getDebugFrame() {
/*      */       try {
/* 1061 */         return (JFrame)debuggerMethods[6].invoke(this.debuggerInstance, (Object[])null);
/*      */       }
/* 1063 */       catch (InvocationTargetException ite) {
/* 1064 */         throw new RuntimeException(ite.getMessage());
/* 1065 */       } catch (IllegalAccessException iae) {
/* 1066 */         throw new RuntimeException(iae.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void setExitAction(Runnable r) {
/*      */       try {
/* 1075 */         debuggerMethods[2].invoke(this.debuggerInstance, new Object[] { r });
/*      */       }
/* 1077 */       catch (InvocationTargetException ite) {
/* 1078 */         throw new RuntimeException(ite.getMessage());
/* 1079 */       } catch (IllegalAccessException iae) {
/* 1080 */         throw new RuntimeException(iae.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void attachTo(Object contextFactory) {
/*      */       try {
/* 1089 */         debuggerMethods[3].invoke(this.debuggerInstance, new Object[] { contextFactory });
/*      */       }
/* 1091 */       catch (InvocationTargetException ite) {
/* 1092 */         throw new RuntimeException(ite.getMessage());
/* 1093 */       } catch (IllegalAccessException iae) {
/* 1094 */         throw new RuntimeException(iae.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void detach() {
/*      */       try {
/* 1103 */         debuggerMethods[4].invoke(this.debuggerInstance, (Object[])null);
/*      */       }
/* 1105 */       catch (InvocationTargetException ite) {
/* 1106 */         throw new RuntimeException(ite.getMessage());
/* 1107 */       } catch (IllegalAccessException iae) {
/* 1108 */         throw new RuntimeException(iae.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void go() {
/*      */       try {
/* 1117 */         debuggerMethods[1].invoke(this.debuggerInstance, (Object[])null);
/*      */       }
/* 1119 */       catch (InvocationTargetException ite) {
/* 1120 */         throw new RuntimeException(ite.getMessage());
/* 1121 */       } catch (IllegalAccessException iae) {
/* 1122 */         throw new RuntimeException(iae.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void clearAllBreakpoints() {
/*      */       try {
/* 1131 */         debuggerMethods[0].invoke(this.debuggerInstance, (Object[])null);
/*      */       }
/* 1133 */       catch (InvocationTargetException ite) {
/* 1134 */         throw new RuntimeException(ite.getMessage());
/* 1135 */       } catch (IllegalAccessException iae) {
/* 1136 */         throw new RuntimeException(iae.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void dispose() {
/*      */       try {
/* 1145 */         debuggerMethods[5].invoke(this.debuggerInstance, (Object[])null);
/*      */       }
/* 1147 */       catch (InvocationTargetException ite) {
/* 1148 */         throw new RuntimeException(ite.getMessage());
/* 1149 */       } catch (IllegalAccessException iae) {
/* 1150 */         throw new RuntimeException(iae.getMessage());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Object getContextFactory(Object rhinoInterpreter) {
/*      */       try {
/* 1160 */         return getContextFactoryMethod.invoke(rhinoInterpreter, (Object[])null);
/*      */       }
/* 1162 */       catch (InvocationTargetException ite) {
/* 1163 */         throw new RuntimeException(ite.getMessage());
/* 1164 */       } catch (IllegalAccessException iae) {
/* 1165 */         throw new RuntimeException(iae.getMessage());
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class AboutAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1178 */       AboutDialog dlg = new AboutDialog(JSVGViewerFrame.this);
/*      */       
/* 1180 */       dlg.setSize(dlg.getPreferredSize());
/* 1181 */       dlg.setLocationRelativeTo(JSVGViewerFrame.this);
/* 1182 */       dlg.setVisible(true);
/* 1183 */       dlg.toFront();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class OpenAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1195 */       File f = null;
/* 1196 */       if (Platform.isOSX) {
/* 1197 */         FileDialog fileDialog = new FileDialog(JSVGViewerFrame.this, Resources.getString("Open.title"));
/*      */ 
/*      */         
/* 1200 */         fileDialog.setFilenameFilter(new FilenameFilter() {
/*      */               public boolean accept(File dir, String name) {
/* 1202 */                 for (Object o : JSVGViewerFrame.getHandlers()) {
/* 1203 */                   SquiggleInputHandler handler = (SquiggleInputHandler)o;
/*      */                   
/* 1205 */                   if (handler.accept(new File(dir, name))) {
/* 1206 */                     return true;
/*      */                   }
/*      */                 } 
/* 1209 */                 return false;
/*      */               }
/*      */             });
/* 1212 */         fileDialog.setVisible(true);
/* 1213 */         String filename = fileDialog.getFile();
/* 1214 */         if (fileDialog != null) {
/* 1215 */           String dirname = fileDialog.getDirectory();
/* 1216 */           f = new File(dirname, filename);
/*      */         } 
/*      */       } else {
/* 1219 */         JFileChooser fileChooser = null;
/*      */ 
/*      */ 
/*      */         
/* 1223 */         String os = System.getProperty(JSVGViewerFrame.PROPERTY_OS_NAME, JSVGViewerFrame.PROPERTY_OS_NAME_DEFAULT);
/* 1224 */         SecurityManager sm = System.getSecurityManager();
/*      */         
/* 1226 */         if (JSVGViewerFrame.priorJDK1_4 && sm != null && os.indexOf(JSVGViewerFrame.PROPERTY_OS_WINDOWS_PREFIX) != -1) {
/* 1227 */           fileChooser = new JFileChooser(JSVGViewerFrame.makeAbsolute(JSVGViewerFrame.this.currentPath), new WindowsAltFileSystemView());
/*      */         } else {
/*      */           
/* 1230 */           fileChooser = new JFileChooser(JSVGViewerFrame.makeAbsolute(JSVGViewerFrame.this.currentPath));
/*      */         } 
/*      */         
/* 1233 */         fileChooser.setFileHidingEnabled(false);
/* 1234 */         fileChooser.setFileSelectionMode(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1240 */         for (Object o : JSVGViewerFrame.getHandlers()) {
/* 1241 */           SquiggleInputHandler handler = (SquiggleInputHandler)o;
/*      */           
/* 1243 */           fileChooser.addChoosableFileFilter(new SquiggleInputHandlerFilter(handler));
/*      */         } 
/*      */ 
/*      */         
/* 1247 */         int choice = fileChooser.showOpenDialog(JSVGViewerFrame.this);
/* 1248 */         if (choice == 0) {
/* 1249 */           f = fileChooser.getSelectedFile();
/* 1250 */           JSVGViewerFrame.this.currentPath = f;
/*      */         } 
/*      */       } 
/*      */       
/* 1254 */       if (f != null) {
/*      */         try {
/* 1256 */           String furl = f.toURI().toURL().toString();
/* 1257 */           JSVGViewerFrame.this.showSVGDocument(furl);
/* 1258 */         } catch (MalformedURLException ex) {
/* 1259 */           if (JSVGViewerFrame.this.userAgent != null) {
/* 1260 */             JSVGViewerFrame.this.userAgent.displayError(ex);
/*      */           }
/*      */         } 
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showSVGDocument(String uri) {
/*      */     try {
/* 1272 */       ParsedURL purl = new ParsedURL(uri);
/*      */       
/* 1274 */       SquiggleInputHandler handler = getInputHandler(purl);
/*      */       
/* 1276 */       handler.handle(purl, this);
/*      */     }
/* 1278 */     catch (Exception e) {
/* 1279 */       if (this.userAgent != null) {
/* 1280 */         this.userAgent.displayError(e);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SquiggleInputHandler getInputHandler(ParsedURL purl) throws IOException {
/* 1290 */     Iterator<SquiggleInputHandler> iter = getHandlers().iterator();
/* 1291 */     SquiggleInputHandler handler = null;
/*      */     
/* 1293 */     while (iter.hasNext()) {
/* 1294 */       SquiggleInputHandler curHandler = iter.next();
/*      */       
/* 1296 */       if (curHandler.accept(purl)) {
/* 1297 */         handler = curHandler;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 1303 */     if (handler == null) {
/* 1304 */       handler = defaultHandler;
/*      */     }
/*      */     
/* 1307 */     return handler;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static Vector getHandlers() {
/* 1315 */     if (handlers != null) {
/* 1316 */       return handlers;
/*      */     }
/*      */     
/* 1319 */     handlers = new Vector();
/* 1320 */     registerHandler(new SVGInputHandler());
/*      */     
/* 1322 */     Iterator<SquiggleInputHandler> iter = Service.providers(SquiggleInputHandler.class);
/* 1323 */     while (iter.hasNext()) {
/* 1324 */       SquiggleInputHandler handler = iter.next();
/*      */ 
/*      */       
/* 1327 */       registerHandler(handler);
/*      */     } 
/*      */     
/* 1330 */     return handlers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void registerHandler(SquiggleInputHandler handler) {
/* 1339 */     Vector<SquiggleInputHandler> handlers = getHandlers();
/* 1340 */     handlers.addElement(handler);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class OpenLocationAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1349 */       if (JSVGViewerFrame.this.uriChooser == null) {
/* 1350 */         JSVGViewerFrame.this.uriChooser = new URIChooser(JSVGViewerFrame.this);
/* 1351 */         JSVGViewerFrame.this.uriChooser.setFileFilter((FileFilter)new SVGFileFilter());
/* 1352 */         JSVGViewerFrame.this.uriChooser.pack();
/* 1353 */         Rectangle fr = JSVGViewerFrame.this.getBounds();
/* 1354 */         Dimension sd = JSVGViewerFrame.this.uriChooser.getSize();
/* 1355 */         JSVGViewerFrame.this.uriChooser.setLocation(fr.x + (fr.width - sd.width) / 2, fr.y + (fr.height - sd.height) / 2);
/*      */       } 
/*      */       
/* 1358 */       if (JSVGViewerFrame.this.uriChooser.showDialog() == 0) {
/* 1359 */         String s = JSVGViewerFrame.this.uriChooser.getText();
/* 1360 */         if (s == null)
/* 1361 */           return;  int i = s.indexOf('#');
/* 1362 */         String t = "";
/* 1363 */         if (i != -1) {
/* 1364 */           t = s.substring(i + 1);
/* 1365 */           s = s.substring(0, i);
/*      */         } 
/* 1367 */         if (!s.equals("")) {
/* 1368 */           File f = new File(s);
/* 1369 */           if (f.exists()) {
/* 1370 */             if (f.isDirectory()) {
/* 1371 */               s = null;
/*      */             } else {
/*      */               try {
/* 1374 */                 s = f.getCanonicalPath();
/* 1375 */                 if (s.startsWith("/")) {
/* 1376 */                   s = "file:" + s;
/*      */                 } else {
/* 1378 */                   s = "file:/" + s;
/*      */                 } 
/* 1380 */               } catch (IOException iOException) {}
/*      */             } 
/*      */           }
/*      */           
/* 1384 */           if (s != null) {
/* 1385 */             if (JSVGViewerFrame.this.svgDocument != null) {
/* 1386 */               ParsedURL docPURL = new ParsedURL(JSVGViewerFrame.this.svgDocument.getURL());
/*      */               
/* 1388 */               ParsedURL purl = new ParsedURL(docPURL, s);
/* 1389 */               String fi = JSVGViewerFrame.this.svgCanvas.getFragmentIdentifier();
/* 1390 */               if (docPURL.equals(purl) && t.equals(fi)) {
/*      */                 return;
/*      */               }
/*      */             } 
/* 1394 */             if (t.length() != 0) {
/* 1395 */               s = s + '#' + t;
/*      */             }
/*      */             
/* 1398 */             JSVGViewerFrame.this.showSVGDocument(s);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class NewWindowAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1411 */       JSVGViewerFrame vf = JSVGViewerFrame.this.application.createAndShowJSVGViewerFrame();
/*      */ 
/*      */       
/* 1414 */       vf.autoAdjust = JSVGViewerFrame.this.autoAdjust;
/* 1415 */       vf.debug = JSVGViewerFrame.this.debug;
/* 1416 */       vf.svgCanvas.setProgressivePaint(JSVGViewerFrame.this.svgCanvas.getProgressivePaint());
/* 1417 */       vf.svgCanvas.setDoubleBufferedRendering(JSVGViewerFrame.this.svgCanvas.getDoubleBufferedRendering());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class PreferencesAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1428 */       JSVGViewerFrame.this.application.showPreferenceDialog(JSVGViewerFrame.this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class CloseAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1438 */       JSVGViewerFrame.this.application.closeJSVGViewerFrame(JSVGViewerFrame.this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class ReloadAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1448 */       if ((e.getModifiers() & 0x1) == 1) {
/* 1449 */         JSVGViewerFrame.this.svgCanvas.flushImageCache();
/*      */       }
/* 1451 */       if (JSVGViewerFrame.this.svgDocument != null) {
/* 1452 */         JSVGViewerFrame.this.localHistory.reload();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class BackAction
/*      */     extends AbstractAction
/*      */     implements JComponentModifier
/*      */   {
/* 1462 */     List components = new LinkedList();
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 1465 */       if (JSVGViewerFrame.this.localHistory.canGoBack()) {
/* 1466 */         JSVGViewerFrame.this.localHistory.back();
/*      */       }
/*      */     }
/*      */     
/*      */     public void addJComponent(JComponent c) {
/* 1471 */       this.components.add(c);
/* 1472 */       c.setEnabled(false);
/*      */     }
/*      */     
/*      */     protected void update() {
/* 1476 */       boolean b = JSVGViewerFrame.this.localHistory.canGoBack();
/* 1477 */       for (Object component : this.components) {
/* 1478 */         ((JComponent)component).setEnabled(b);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class ForwardAction
/*      */     extends AbstractAction
/*      */     implements JComponentModifier
/*      */   {
/* 1488 */     List components = new LinkedList();
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 1491 */       if (JSVGViewerFrame.this.localHistory.canGoForward()) {
/* 1492 */         JSVGViewerFrame.this.localHistory.forward();
/*      */       }
/*      */     }
/*      */     
/*      */     public void addJComponent(JComponent c) {
/* 1497 */       this.components.add(c);
/* 1498 */       c.setEnabled(false);
/*      */     }
/*      */     
/*      */     protected void update() {
/* 1502 */       boolean b = JSVGViewerFrame.this.localHistory.canGoForward();
/* 1503 */       for (Object component : this.components) {
/* 1504 */         ((JComponent)component).setEnabled(b);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class PrintAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1515 */       if (JSVGViewerFrame.this.svgDocument != null) {
/* 1516 */         final SVGDocument doc = JSVGViewerFrame.this.svgDocument;
/* 1517 */         (new Thread() {
/*      */             public void run() {
/* 1519 */               String uri = doc.getURL();
/* 1520 */               String fragment = JSVGViewerFrame.this.svgCanvas.getFragmentIdentifier();
/* 1521 */               if (fragment != null) {
/* 1522 */                 uri = uri + '#' + fragment;
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1529 */               PrintTranscoder pt = new PrintTranscoder();
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1534 */               if (JSVGViewerFrame.this.application.getXMLParserClassName() != null) {
/* 1535 */                 pt.addTranscodingHint(JPEGTranscoder.KEY_XML_PARSER_CLASSNAME, JSVGViewerFrame.this.application.getXMLParserClassName());
/*      */               }
/*      */ 
/*      */ 
/*      */               
/* 1540 */               pt.addTranscodingHint(PrintTranscoder.KEY_SHOW_PAGE_DIALOG, Boolean.TRUE);
/*      */ 
/*      */ 
/*      */               
/* 1544 */               pt.addTranscodingHint(PrintTranscoder.KEY_SHOW_PRINTER_DIALOG, Boolean.TRUE);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1550 */               pt.transcode(new TranscoderInput(uri), null);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               try {
/* 1556 */                 pt.print();
/* 1557 */               } catch (PrinterException ex) {
/* 1558 */                 JSVGViewerFrame.this.userAgent.displayError(ex);
/*      */               } 
/*      */             }
/*      */           }).start();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class SaveAsAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1574 */       JFileChooser fileChooser = new JFileChooser(JSVGViewerFrame.makeAbsolute(JSVGViewerFrame.this.currentSavePath));
/* 1575 */       fileChooser.setDialogTitle(JSVGViewerFrame.resources.getString("SaveAs.title"));
/* 1576 */       fileChooser.setFileHidingEnabled(false);
/* 1577 */       fileChooser.setFileSelectionMode(0);
/* 1578 */       fileChooser.addChoosableFileFilter(new JSVGViewerFrame.ImageFileFilter(".svg"));
/*      */       
/* 1580 */       int choice = fileChooser.showSaveDialog(JSVGViewerFrame.this);
/* 1581 */       if (choice != 0) {
/*      */         return;
/*      */       }
/* 1584 */       File f = fileChooser.getSelectedFile();
/*      */ 
/*      */       
/* 1587 */       SVGOptionPanel sop = SVGOptionPanel.showDialog(JSVGViewerFrame.this);
/*      */       
/* 1589 */       final boolean useXMLBase = sop.getUseXMLBase();
/* 1590 */       final boolean prettyPrint = sop.getPrettyPrint();
/* 1591 */       sop = null;
/*      */       
/* 1593 */       final SVGDocument svgDoc = JSVGViewerFrame.this.svgCanvas.getSVGDocument();
/* 1594 */       if (svgDoc == null)
/*      */         return; 
/* 1596 */       JSVGViewerFrame.this.statusBar.setMessage(JSVGViewerFrame.resources.getString("Message.saveAs"));
/* 1597 */       JSVGViewerFrame.this.currentSavePath = f;
/* 1598 */       OutputStreamWriter w = null;
/*      */       try {
/* 1600 */         OutputStream tos = null;
/* 1601 */         tos = new FileOutputStream(f);
/* 1602 */         tos = new BufferedOutputStream(tos);
/* 1603 */         w = new OutputStreamWriter(tos, "utf-8");
/* 1604 */       } catch (Exception ex) {
/* 1605 */         JSVGViewerFrame.this.userAgent.displayError(ex);
/*      */         
/*      */         return;
/*      */       } 
/* 1609 */       final OutputStreamWriter writer = w;
/*      */       
/* 1611 */       final Runnable doneRun = new Runnable() {
/*      */           public void run() {
/* 1613 */             String doneStr = JSVGViewerFrame.resources.getString("Message.done");
/* 1614 */             JSVGViewerFrame.this.statusBar.setMessage(doneStr);
/*      */           }
/*      */         };
/* 1617 */       Runnable r = new Runnable()
/*      */         {
/*      */           public void run() {
/*      */             try {
/* 1621 */               writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
/*      */               
/* 1623 */               writer.write(JSVGViewerFrame.EOL);
/*      */               
/* 1625 */               Node fc = svgDoc.getFirstChild();
/* 1626 */               if (fc.getNodeType() != 10) {
/*      */ 
/*      */                 
/* 1629 */                 writer.write("<!DOCTYPE svg PUBLIC '");
/* 1630 */                 writer.write("-//W3C//DTD SVG 1.0//EN");
/* 1631 */                 writer.write("' '");
/* 1632 */                 writer.write("http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd");
/* 1633 */                 writer.write("'>");
/* 1634 */                 writer.write(JSVGViewerFrame.EOL);
/* 1635 */                 writer.write(JSVGViewerFrame.EOL);
/*      */               } 
/* 1637 */               SVGSVGElement sVGSVGElement = svgDoc.getRootElement();
/* 1638 */               boolean doXMLBase = useXMLBase;
/* 1639 */               if (sVGSVGElement.hasAttributeNS("http://www.w3.org/XML/1998/namespace", "base"))
/*      */               {
/* 1641 */                 doXMLBase = false;
/*      */               }
/* 1643 */               if (doXMLBase) {
/* 1644 */                 sVGSVGElement.setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:base", svgDoc.getURL());
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1650 */               if (prettyPrint) {
/* 1651 */                 SVGTranscoder trans = new SVGTranscoder();
/* 1652 */                 trans.transcode(new TranscoderInput((Document)svgDoc), new TranscoderOutput(writer));
/*      */               } else {
/*      */                 
/* 1655 */                 DOMUtilities.writeDocument((Document)svgDoc, writer);
/*      */               } 
/*      */               
/* 1658 */               writer.close();
/*      */               
/* 1660 */               if (doXMLBase) {
/* 1661 */                 sVGSVGElement.removeAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:base");
/*      */               }
/*      */ 
/*      */               
/* 1665 */               if (EventQueue.isDispatchThread()) {
/* 1666 */                 doneRun.run();
/*      */               } else {
/* 1668 */                 EventQueue.invokeLater(doneRun);
/*      */               } 
/* 1670 */             } catch (Exception ex) {
/* 1671 */               JSVGViewerFrame.this.userAgent.displayError(ex);
/*      */             } 
/*      */           }
/*      */         };
/*      */       
/* 1676 */       UpdateManager um = JSVGViewerFrame.this.svgCanvas.getUpdateManager();
/* 1677 */       if (um != null && um.isRunning()) {
/* 1678 */         um.getUpdateRunnableQueue().invokeLater(r);
/*      */       } else {
/* 1680 */         r.run();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class ExportAsJPGAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1691 */       JFileChooser fileChooser = new JFileChooser(JSVGViewerFrame.makeAbsolute(JSVGViewerFrame.this.currentSavePath));
/*      */       
/* 1693 */       fileChooser.setDialogTitle(JSVGViewerFrame.resources.getString("ExportAsJPG.title"));
/* 1694 */       fileChooser.setFileHidingEnabled(false);
/* 1695 */       fileChooser.setFileSelectionMode(0);
/*      */       
/* 1697 */       fileChooser.addChoosableFileFilter(new JSVGViewerFrame.ImageFileFilter(".jpg"));
/*      */       
/* 1699 */       int choice = fileChooser.showSaveDialog(JSVGViewerFrame.this);
/* 1700 */       if (choice == 0) {
/* 1701 */         float quality = JPEGOptionPanel.showDialog(JSVGViewerFrame.this);
/*      */ 
/*      */         
/* 1704 */         final File f = fileChooser.getSelectedFile();
/* 1705 */         BufferedImage buffer = JSVGViewerFrame.this.svgCanvas.getOffScreen();
/* 1706 */         if (buffer != null) {
/* 1707 */           JSVGViewerFrame.this.statusBar.setMessage(JSVGViewerFrame.resources.getString("Message.exportAsJPG"));
/*      */ 
/*      */ 
/*      */           
/* 1711 */           int w = buffer.getWidth();
/* 1712 */           int h = buffer.getHeight();
/* 1713 */           final JPEGTranscoder trans = new JPEGTranscoder();
/* 1714 */           if (JSVGViewerFrame.this.application.getXMLParserClassName() != null) {
/* 1715 */             jPEGTranscoder.addTranscodingHint(JPEGTranscoder.KEY_XML_PARSER_CLASSNAME, JSVGViewerFrame.this.application.getXMLParserClassName());
/*      */           }
/*      */ 
/*      */           
/* 1719 */           jPEGTranscoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, Float.valueOf(quality));
/*      */ 
/*      */           
/* 1722 */           final BufferedImage img = jPEGTranscoder.createImage(w, h);
/*      */ 
/*      */           
/* 1725 */           Graphics2D g2d = img.createGraphics();
/* 1726 */           g2d.setColor(Color.white);
/* 1727 */           g2d.fillRect(0, 0, w, h);
/* 1728 */           g2d.drawImage(buffer, (BufferedImageOp)null, 0, 0);
/* 1729 */           (new Thread() {
/*      */               public void run() {
/*      */                 try {
/* 1732 */                   JSVGViewerFrame.this.currentSavePath = f;
/* 1733 */                   OutputStream ostream = new BufferedOutputStream(new FileOutputStream(f));
/*      */                   
/* 1735 */                   trans.writeImage(img, new TranscoderOutput(ostream));
/* 1736 */                   ostream.close();
/* 1737 */                 } catch (Exception exception) {}
/* 1738 */                 JSVGViewerFrame.this.statusBar.setMessage(JSVGViewerFrame.resources.getString("Message.done"));
/*      */               }
/*      */             }).start();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class ExportAsPNGAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1753 */       JFileChooser fileChooser = new JFileChooser(JSVGViewerFrame.makeAbsolute(JSVGViewerFrame.this.currentSavePath));
/*      */       
/* 1755 */       fileChooser.setDialogTitle(JSVGViewerFrame.resources.getString("ExportAsPNG.title"));
/* 1756 */       fileChooser.setFileHidingEnabled(false);
/* 1757 */       fileChooser.setFileSelectionMode(0);
/*      */       
/* 1759 */       fileChooser.addChoosableFileFilter(new JSVGViewerFrame.ImageFileFilter(".png"));
/*      */       
/* 1761 */       int choice = fileChooser.showSaveDialog(JSVGViewerFrame.this);
/* 1762 */       if (choice == 0) {
/* 1763 */         boolean isIndexed = PNGOptionPanel.showDialog(JSVGViewerFrame.this);
/*      */         
/* 1765 */         final File f = fileChooser.getSelectedFile();
/* 1766 */         BufferedImage buffer = JSVGViewerFrame.this.svgCanvas.getOffScreen();
/* 1767 */         if (buffer != null) {
/* 1768 */           JSVGViewerFrame.this.statusBar.setMessage(JSVGViewerFrame.resources.getString("Message.exportAsPNG"));
/*      */ 
/*      */ 
/*      */           
/* 1772 */           int w = buffer.getWidth();
/* 1773 */           int h = buffer.getHeight();
/* 1774 */           final PNGTranscoder trans = new PNGTranscoder();
/* 1775 */           if (JSVGViewerFrame.this.application.getXMLParserClassName() != null) {
/* 1776 */             pNGTranscoder.addTranscodingHint(JPEGTranscoder.KEY_XML_PARSER_CLASSNAME, JSVGViewerFrame.this.application.getXMLParserClassName());
/*      */           }
/*      */ 
/*      */           
/* 1780 */           pNGTranscoder.addTranscodingHint(PNGTranscoder.KEY_FORCE_TRANSPARENT_WHITE, Boolean.TRUE);
/*      */ 
/*      */           
/* 1783 */           if (isIndexed) {
/* 1784 */             pNGTranscoder.addTranscodingHint(PNGTranscoder.KEY_INDEXED, Integer.valueOf(8));
/*      */           }
/*      */           
/* 1787 */           final BufferedImage img = pNGTranscoder.createImage(w, h);
/*      */ 
/*      */           
/* 1790 */           Graphics2D g2d = img.createGraphics();
/* 1791 */           g2d.drawImage(buffer, (BufferedImageOp)null, 0, 0);
/* 1792 */           (new Thread() {
/*      */               public void run() {
/*      */                 try {
/* 1795 */                   JSVGViewerFrame.this.currentSavePath = f;
/* 1796 */                   OutputStream ostream = new BufferedOutputStream(new FileOutputStream(f));
/*      */                   
/* 1798 */                   trans.writeImage(img, new TranscoderOutput(ostream));
/*      */                   
/* 1800 */                   ostream.close();
/* 1801 */                 } catch (Exception exception) {}
/* 1802 */                 JSVGViewerFrame.this.statusBar.setMessage(JSVGViewerFrame.resources.getString("Message.done"));
/*      */               }
/*      */             }).start();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class ExportAsTIFFAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1817 */       JFileChooser fileChooser = new JFileChooser(JSVGViewerFrame.makeAbsolute(JSVGViewerFrame.this.currentSavePath));
/*      */       
/* 1819 */       fileChooser.setDialogTitle(JSVGViewerFrame.resources.getString("ExportAsTIFF.title"));
/* 1820 */       fileChooser.setFileHidingEnabled(false);
/* 1821 */       fileChooser.setFileSelectionMode(0);
/*      */       
/* 1823 */       fileChooser.addChoosableFileFilter(new JSVGViewerFrame.ImageFileFilter(".tiff"));
/*      */       
/* 1825 */       int choice = fileChooser.showSaveDialog(JSVGViewerFrame.this);
/* 1826 */       if (choice == 0) {
/* 1827 */         final File f = fileChooser.getSelectedFile();
/* 1828 */         BufferedImage buffer = JSVGViewerFrame.this.svgCanvas.getOffScreen();
/* 1829 */         if (buffer != null) {
/* 1830 */           JSVGViewerFrame.this.statusBar.setMessage(JSVGViewerFrame.resources.getString("Message.exportAsTIFF"));
/*      */ 
/*      */ 
/*      */           
/* 1834 */           int w = buffer.getWidth();
/* 1835 */           int h = buffer.getHeight();
/* 1836 */           final TIFFTranscoder trans = new TIFFTranscoder();
/* 1837 */           if (JSVGViewerFrame.this.application.getXMLParserClassName() != null) {
/* 1838 */             tIFFTranscoder.addTranscodingHint(JPEGTranscoder.KEY_XML_PARSER_CLASSNAME, JSVGViewerFrame.this.application.getXMLParserClassName());
/*      */           }
/*      */ 
/*      */           
/* 1842 */           final BufferedImage img = tIFFTranscoder.createImage(w, h);
/*      */ 
/*      */           
/* 1845 */           Graphics2D g2d = img.createGraphics();
/* 1846 */           g2d.drawImage(buffer, (BufferedImageOp)null, 0, 0);
/* 1847 */           (new Thread() {
/*      */               public void run() {
/*      */                 try {
/* 1850 */                   JSVGViewerFrame.this.currentSavePath = f;
/* 1851 */                   OutputStream ostream = new BufferedOutputStream(new FileOutputStream(f));
/*      */                   
/* 1853 */                   trans.writeImage(img, new TranscoderOutput(ostream));
/*      */                   
/* 1855 */                   ostream.close();
/* 1856 */                 } catch (Exception exception) {}
/* 1857 */                 JSVGViewerFrame.this.statusBar.setMessage(JSVGViewerFrame.resources.getString("Message.done"));
/*      */               }
/*      */             }).start();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class ViewSourceAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1872 */       if (JSVGViewerFrame.this.svgDocument == null) {
/*      */         return;
/*      */       }
/*      */       
/* 1876 */       final ParsedURL u = new ParsedURL(JSVGViewerFrame.this.svgDocument.getURL());
/*      */       
/* 1878 */       final JFrame fr = new JFrame(u.toString());
/* 1879 */       fr.setSize(JSVGViewerFrame.resources.getInteger("ViewSource.width"), JSVGViewerFrame.resources.getInteger("ViewSource.height"));
/*      */       
/* 1881 */       final XMLTextEditor ta = new XMLTextEditor();
/*      */       
/* 1883 */       ta.setFont(new Font("monospaced", 0, 12));
/*      */       
/* 1885 */       JScrollPane scroll = new JScrollPane();
/* 1886 */       scroll.getViewport().add((Component)ta);
/* 1887 */       scroll.setVerticalScrollBarPolicy(22);
/*      */       
/* 1889 */       fr.getContentPane().add(scroll, "Center");
/*      */       
/* 1891 */       (new Thread() {
/*      */           public void run() {
/* 1893 */             char[] buffer = new char[4096];
/*      */             
/*      */             try {
/* 1896 */               XMLDocument xMLDocument = new XMLDocument();
/*      */               
/* 1898 */               ParsedURL purl = new ParsedURL(JSVGViewerFrame.this.svgDocument.getURL());
/* 1899 */               InputStream is = u.openStream(JSVGViewerFrame.this.getInputHandler(purl).getHandledMimeTypes());
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1904 */               Reader in = XMLUtilities.createXMLDocumentReader(is);
/*      */               int len;
/* 1906 */               while ((len = in.read(buffer, 0, buffer.length)) != -1) {
/* 1907 */                 xMLDocument.insertString(xMLDocument.getLength(), new String(buffer, 0, len), null);
/*      */               }
/*      */ 
/*      */               
/* 1911 */               ta.setDocument((Document)xMLDocument);
/* 1912 */               ta.setEditable(false);
/*      */               
/* 1914 */               fr.setVisible(true);
/* 1915 */             } catch (Exception ex) {
/* 1916 */               JSVGViewerFrame.this.userAgent.displayError(ex);
/*      */             } 
/*      */           }
/*      */         }).start();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class FlushAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 1929 */       JSVGViewerFrame.this.svgCanvas.flush();
/*      */       
/* 1931 */       JSVGViewerFrame.this.svgCanvas.setRenderingTransform(JSVGViewerFrame.this.svgCanvas.getRenderingTransform());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class ToggleDebuggerAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public ToggleDebuggerAction() {
/* 1940 */       super("Toggle Debugger Action");
/*      */     }
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 1944 */       if (JSVGViewerFrame.this.debugger == null) {
/* 1945 */         JSVGViewerFrame.this.showDebugger();
/*      */       } else {
/* 1947 */         JSVGViewerFrame.this.hideDebugger();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class PreviousTransformAction
/*      */     extends AbstractAction
/*      */     implements JComponentModifier
/*      */   {
/* 1957 */     List components = new LinkedList();
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 1960 */       if (JSVGViewerFrame.this.transformHistory.canGoBack()) {
/* 1961 */         JSVGViewerFrame.this.transformHistory.back();
/* 1962 */         update();
/* 1963 */         JSVGViewerFrame.this.nextTransformAction.update();
/* 1964 */         JSVGViewerFrame.this.svgCanvas.setRenderingTransform(JSVGViewerFrame.this.transformHistory.currentTransform());
/*      */       } 
/*      */     }
/*      */     
/*      */     public void addJComponent(JComponent c) {
/* 1969 */       this.components.add(c);
/* 1970 */       c.setEnabled(false);
/*      */     }
/*      */     
/*      */     protected void update() {
/* 1974 */       boolean b = JSVGViewerFrame.this.transformHistory.canGoBack();
/* 1975 */       for (Object component : this.components) {
/* 1976 */         ((JComponent)component).setEnabled(b);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class NextTransformAction
/*      */     extends AbstractAction
/*      */     implements JComponentModifier
/*      */   {
/* 1986 */     List components = new LinkedList();
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 1989 */       if (JSVGViewerFrame.this.transformHistory.canGoForward()) {
/* 1990 */         JSVGViewerFrame.this.transformHistory.forward();
/* 1991 */         update();
/* 1992 */         JSVGViewerFrame.this.previousTransformAction.update();
/* 1993 */         JSVGViewerFrame.this.svgCanvas.setRenderingTransform(JSVGViewerFrame.this.transformHistory.currentTransform());
/*      */       } 
/*      */     }
/*      */     
/*      */     public void addJComponent(JComponent c) {
/* 1998 */       this.components.add(c);
/* 1999 */       c.setEnabled(false);
/*      */     }
/*      */     
/*      */     protected void update() {
/* 2003 */       boolean b = JSVGViewerFrame.this.transformHistory.canGoForward();
/* 2004 */       for (Object component : this.components) {
/* 2005 */         ((JComponent)component).setEnabled(b);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class UseStylesheetAction
/*      */     extends AbstractAction
/*      */     implements JComponentModifier
/*      */   {
/* 2016 */     List components = new LinkedList();
/*      */ 
/*      */ 
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {}
/*      */ 
/*      */     
/*      */     public void addJComponent(JComponent c) {
/* 2024 */       this.components.add(c);
/* 2025 */       c.setEnabled(false);
/*      */     }
/*      */     
/*      */     protected void update() {
/* 2029 */       JSVGViewerFrame.this.alternateStyleSheet = null;
/* 2030 */       Iterator<JComponent> it = this.components.iterator();
/* 2031 */       SVGDocument doc = JSVGViewerFrame.this.svgCanvas.getSVGDocument();
/* 2032 */       while (it.hasNext()) {
/* 2033 */         JComponent stylesheetMenu = it.next();
/* 2034 */         stylesheetMenu.removeAll();
/* 2035 */         stylesheetMenu.setEnabled(false);
/*      */         
/* 2037 */         ButtonGroup buttonGroup = new ButtonGroup();
/*      */         
/* 2039 */         Node n = doc.getFirstChild();
/* 2040 */         for (; n != null && n.getNodeType() != 1; 
/* 2041 */           n = n.getNextSibling()) {
/* 2042 */           if (n instanceof StyleSheetProcessingInstruction) {
/*      */             
/* 2044 */             StyleSheetProcessingInstruction sspi = (StyleSheetProcessingInstruction)n;
/* 2045 */             HashMap<String, String> attrs = sspi.getPseudoAttributes();
/* 2046 */             final String title = attrs.get("title");
/* 2047 */             String alt = attrs.get("alternate");
/* 2048 */             if (title != null && "yes".equals(alt)) {
/*      */               
/* 2050 */               JRadioButtonMenuItem button = new JRadioButtonMenuItem(title);
/*      */               
/* 2052 */               button.addActionListener(new ActionListener()
/*      */                   {
/*      */                     public void actionPerformed(ActionEvent e)
/*      */                     {
/* 2056 */                       SVGOMDocument doc = (SVGOMDocument)JSVGViewerFrame.this.svgCanvas.getSVGDocument();
/* 2057 */                       doc.clearViewCSS();
/* 2058 */                       JSVGViewerFrame.this.alternateStyleSheet = title;
/* 2059 */                       JSVGViewerFrame.this.svgCanvas.setSVGDocument((SVGDocument)doc);
/*      */                     }
/*      */                   });
/*      */               
/* 2063 */               buttonGroup.add(button);
/* 2064 */               stylesheetMenu.add(button);
/* 2065 */               stylesheetMenu.setEnabled(true);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class PlayAction
/*      */     extends AbstractAction
/*      */     implements JComponentModifier
/*      */   {
/* 2078 */     List components = new LinkedList();
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 2081 */       JSVGViewerFrame.this.svgCanvas.resumeProcessing();
/*      */     }
/*      */     
/*      */     public void addJComponent(JComponent c) {
/* 2085 */       this.components.add(c);
/* 2086 */       c.setEnabled(false);
/*      */     }
/*      */     
/*      */     public void update(boolean enabled) {
/* 2090 */       for (Object component : this.components) {
/* 2091 */         ((JComponent)component).setEnabled(enabled);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class PauseAction
/*      */     extends AbstractAction
/*      */     implements JComponentModifier
/*      */   {
/* 2101 */     List components = new LinkedList();
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 2104 */       JSVGViewerFrame.this.svgCanvas.suspendProcessing();
/*      */     }
/*      */     
/*      */     public void addJComponent(JComponent c) {
/* 2108 */       this.components.add(c);
/* 2109 */       c.setEnabled(false);
/*      */     }
/*      */     
/*      */     public void update(boolean enabled) {
/* 2113 */       for (Object component : this.components) {
/* 2114 */         ((JComponent)component).setEnabled(enabled);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public class StopAction
/*      */     extends AbstractAction
/*      */     implements JComponentModifier
/*      */   {
/* 2124 */     List components = new LinkedList();
/*      */     
/*      */     public void actionPerformed(ActionEvent e) {
/* 2127 */       JSVGViewerFrame.this.svgCanvas.stopProcessing();
/*      */     }
/*      */     
/*      */     public void addJComponent(JComponent c) {
/* 2131 */       this.components.add(c);
/* 2132 */       c.setEnabled(false);
/*      */     }
/*      */     
/*      */     public void update(boolean enabled) {
/* 2136 */       for (Object component : this.components) {
/* 2137 */         ((JComponent)component).setEnabled(enabled);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class SetTransformAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 2148 */       if (JSVGViewerFrame.this.transformDialog == null) {
/* 2149 */         JSVGViewerFrame.this.transformDialog = JAffineTransformChooser.createDialog(JSVGViewerFrame.this, JSVGViewerFrame.resources.getString("SetTransform.title"));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2155 */       AffineTransform txf = JSVGViewerFrame.this.transformDialog.showDialog();
/* 2156 */       if (txf != null) {
/* 2157 */         AffineTransform at = JSVGViewerFrame.this.svgCanvas.getRenderingTransform();
/* 2158 */         if (at == null) {
/* 2159 */           at = new AffineTransform();
/*      */         }
/*      */         
/* 2162 */         txf.concatenate(at);
/* 2163 */         JSVGViewerFrame.this.svgCanvas.setRenderingTransform(txf);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class MonitorAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 2174 */       if (JSVGViewerFrame.memoryMonitorFrame == null) {
/* 2175 */         JSVGViewerFrame.memoryMonitorFrame = (JFrame)new MemoryMonitor();
/* 2176 */         Rectangle fr = JSVGViewerFrame.this.getBounds();
/* 2177 */         Dimension md = JSVGViewerFrame.memoryMonitorFrame.getSize();
/* 2178 */         JSVGViewerFrame.memoryMonitorFrame.setLocation(fr.x + (fr.width - md.width) / 2, fr.y + (fr.height - md.height) / 2);
/*      */       } 
/*      */       
/* 2181 */       JSVGViewerFrame.memoryMonitorFrame.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class FindDialogAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 2191 */       if (JSVGViewerFrame.this.findDialog == null) {
/* 2192 */         JSVGViewerFrame.this.findDialog = new FindDialog(JSVGViewerFrame.this, JSVGViewerFrame.this.svgCanvas);
/* 2193 */         JSVGViewerFrame.this.findDialog.setGraphicsNode(JSVGViewerFrame.this.svgCanvas.getGraphicsNode());
/* 2194 */         JSVGViewerFrame.this.findDialog.pack();
/* 2195 */         Rectangle fr = JSVGViewerFrame.this.getBounds();
/* 2196 */         Dimension td = JSVGViewerFrame.this.findDialog.getSize();
/* 2197 */         JSVGViewerFrame.this.findDialog.setLocation(fr.x + (fr.width - td.width) / 2, fr.y + (fr.height - td.height) / 2);
/*      */       } 
/*      */       
/* 2200 */       JSVGViewerFrame.this.findDialog.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class ThumbnailDialogAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 2210 */       if (JSVGViewerFrame.this.thumbnailDialog == null) {
/* 2211 */         JSVGViewerFrame.this.thumbnailDialog = new ThumbnailDialog(JSVGViewerFrame.this, JSVGViewerFrame.this.svgCanvas);
/*      */         
/* 2213 */         JSVGViewerFrame.this.thumbnailDialog.pack();
/* 2214 */         Rectangle fr = JSVGViewerFrame.this.getBounds();
/* 2215 */         Dimension td = JSVGViewerFrame.this.thumbnailDialog.getSize();
/* 2216 */         JSVGViewerFrame.this.thumbnailDialog.setLocation(fr.x + (fr.width - td.width) / 2, fr.y + (fr.height - td.height) / 2);
/*      */       } 
/*      */       
/* 2219 */       JSVGViewerFrame.this.thumbnailDialog.setInteractionEnabled(!JSVGViewerFrame.this.svgCanvas.getDisableInteractions());
/*      */       
/* 2221 */       JSVGViewerFrame.this.thumbnailDialog.setVisible(true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class FullScreenAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 2232 */       if (JSVGViewerFrame.this.window == null || !JSVGViewerFrame.this.window.isVisible()) {
/* 2233 */         if (JSVGViewerFrame.this.window == null) {
/* 2234 */           JSVGViewerFrame.this.window = new JWindow(JSVGViewerFrame.this);
/* 2235 */           Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
/* 2236 */           JSVGViewerFrame.this.window.setSize(size);
/*      */         } 
/*      */         
/* 2239 */         JSVGViewerFrame.this.svgCanvas.getParent().remove((Component)JSVGViewerFrame.this.svgCanvas);
/* 2240 */         JSVGViewerFrame.this.window.getContentPane().add((Component)JSVGViewerFrame.this.svgCanvas);
/* 2241 */         JSVGViewerFrame.this.window.setVisible(true);
/* 2242 */         JSVGViewerFrame.this.window.toFront();
/* 2243 */         JSVGViewerFrame.this.svgCanvas.requestFocus();
/*      */       } else {
/*      */         
/* 2246 */         JSVGViewerFrame.this.svgCanvas.getParent().remove((Component)JSVGViewerFrame.this.svgCanvas);
/* 2247 */         JSVGViewerFrame.this.svgCanvasPanel.add((Component)JSVGViewerFrame.this.svgCanvas, "Center");
/* 2248 */         JSVGViewerFrame.this.window.setVisible(false);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class DOMViewerAction
/*      */     extends AbstractAction
/*      */   {
/*      */     public void actionPerformed(ActionEvent e) {
/* 2262 */       openDOMViewer();
/*      */     }
/*      */     
/*      */     public void openDOMViewer() {
/* 2266 */       if (JSVGViewerFrame.this.domViewer == null || JSVGViewerFrame.this.domViewer.isDisplayable()) {
/* 2267 */         JSVGViewerFrame.this.svgCanvas.getClass(); JSVGViewerFrame.this.domViewer = new DOMViewer(new JSVGViewerFrame.Canvas.JSVGViewerDOMViewerController(JSVGViewerFrame.this.svgCanvas));
/*      */         
/* 2269 */         Rectangle fr = JSVGViewerFrame.this.getBounds();
/* 2270 */         Dimension td = JSVGViewerFrame.this.domViewer.getSize();
/* 2271 */         JSVGViewerFrame.this.domViewer.setLocation(fr.x + (fr.width - td.width) / 2, fr.y + (fr.height - td.height) / 2);
/*      */       } 
/*      */       
/* 2274 */       JSVGViewerFrame.this.domViewer.setVisible(true);
/*      */     }
/*      */     
/*      */     public DOMViewer getDOMViewer() {
/* 2278 */       return JSVGViewerFrame.this.domViewer;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSVGViewerFrame(Application app) {
/* 2287 */     this.listeners = new HashMap<Object, Object>(); this.application = app; addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) { JSVGViewerFrame.this.application.closeJSVGViewerFrame(JSVGViewerFrame.this); } }
/*      */       ); this.svgCanvas = new Canvas(this.userAgent, true, true) { Dimension screenSize; public Dimension getPreferredSize() { Dimension s = super.getPreferredSize(); if (s.width > this.screenSize.width) s.width = this.screenSize.width;  if (s.height > this.screenSize.height) s.height = this.screenSize.height;  return s; } public void setMySize(Dimension d) { setPreferredSize(d); invalidate(); if (JSVGViewerFrame.this.autoAdjust) { JSVGViewerFrame.this.setExtendedState(JSVGViewerFrame.this.getExtendedState() & 0xFFFFFFF9); JSVGViewerFrame.this.pack(); }  } public void setDisableInteractions(boolean b) { super.setDisableInteractions(b); ((Action)JSVGViewerFrame.this.listeners.get("SetTransformAction")).setEnabled(!b); if (JSVGViewerFrame.this.thumbnailDialog != null) JSVGViewerFrame.this.thumbnailDialog.setInteractionEnabled(!b);  } }
/*      */       ; ActionMap map = this.svgCanvas.getActionMap(); map.put("FullScreenAction", new FullScreenAction()); InputMap imap = this.svgCanvas.getInputMap(0); KeyStroke key = KeyStroke.getKeyStroke(122, 0); imap.put(key, "FullScreenAction"); this.svgCanvas.setDoubleBufferedRendering(true); this.listeners.put("AboutAction", new AboutAction()); this.listeners.put("OpenAction", new OpenAction()); this.listeners.put("OpenLocationAction", new OpenLocationAction()); this.listeners.put("NewWindowAction", new NewWindowAction()); this.listeners.put("ReloadAction", new ReloadAction()); this.listeners.put("SaveAsAction", new SaveAsAction()); this.listeners.put("BackAction", this.backAction); this.listeners.put("ForwardAction", this.forwardAction); this.listeners.put("PrintAction", new PrintAction()); this.listeners.put("ExportAsJPGAction", new ExportAsJPGAction()); this.listeners.put("ExportAsPNGAction", new ExportAsPNGAction()); this.listeners.put("ExportAsTIFFAction", new ExportAsTIFFAction()); this.listeners.put("PreferencesAction", new PreferencesAction()); this.listeners.put("CloseAction", new CloseAction()); this.listeners.put("ExitAction", this.application.createExitAction(this)); this.listeners.put("ViewSourceAction", new ViewSourceAction()); ActionMap cMap = this.svgCanvas.getActionMap(); this.listeners.put("ResetTransformAction", cMap.get("ResetTransform")); this.listeners.put("ZoomInAction", cMap.get("ZoomIn")); this.listeners.put("ZoomOutAction", cMap.get("ZoomOut")); this.listeners.put("PreviousTransformAction", this.previousTransformAction); key = KeyStroke.getKeyStroke(75, 2); imap.put(key, this.previousTransformAction); this.listeners.put("NextTransformAction", this.nextTransformAction); key = KeyStroke.getKeyStroke(76, 2); imap.put(key, this.nextTransformAction); this.listeners.put("UseStylesheetAction", this.useStylesheetAction); this.listeners.put("PlayAction", this.playAction); this.listeners.put("PauseAction", this.pauseAction); this.listeners.put("StopAction", this.stopAction); this.listeners.put("MonitorAction", new MonitorAction()); this.listeners.put("DOMViewerAction", new DOMViewerAction()); this.listeners.put("SetTransformAction", new SetTransformAction()); this.listeners.put("FindDialogAction", new FindDialogAction()); this.listeners.put("ThumbnailDialogAction", new ThumbnailDialogAction()); this.listeners.put("FlushAction", new FlushAction()); this.listeners.put("ToggleDebuggerAction", new ToggleDebuggerAction()); JPanel p = null; try { MenuFactory mf = new MenuFactory(bundle, this); JMenuBar mb = mf.createJMenuBar("MenuBar", this.application.getUISpecialization()); setJMenuBar(mb); this.localHistory = new LocalHistory(mb, this); String[] uri = this.application.getVisitedURIs(); for (String anUri : uri) { if (anUri != null && !"".equals(anUri)) this.localHistory.update(anUri);  }  p = new JPanel(new BorderLayout()); ToolBarFactory tbf = new ToolBarFactory(bundle, this); JToolBar tb = tbf.createJToolBar("ToolBar"); tb.setFloatable(false); getContentPane().add(p, "North"); p.add(tb, "North"); p.add(new JSeparator(), "Center"); p.add((Component)(this.locationBar = new LocationBar()), "South"); this.locationBar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); } catch (MissingResourceException e) { System.out.println(e.getMessage()); System.exit(0); }  this.svgCanvasPanel = new JPanel(new BorderLayout()); this.svgCanvasPanel.setBorder(BorderFactory.createEtchedBorder()); this.svgCanvasPanel.add((Component)this.svgCanvas, "Center"); p = new JPanel(new BorderLayout()); p.add(this.svgCanvasPanel, "Center"); p.add(this.statusBar = new StatusBar(), "South"); getContentPane().add(p, "Center"); this.svgCanvas.addSVGDocumentLoaderListener(this); this.svgCanvas.addGVTTreeBuilderListener(this); this.svgCanvas.addSVGLoadEventDispatcherListener(this); this.svgCanvas.addGVTTreeRendererListener(this); this.svgCanvas.addLinkActivationListener(this); this.svgCanvas.addUpdateManagerListener(this); this.svgCanvas.addMouseMotionListener(new MouseMotionAdapter() { public void mouseMoved(MouseEvent e) { if (JSVGViewerFrame.this.svgDocument == null) { JSVGViewerFrame.this.statusBar.setXPosition(e.getX()); JSVGViewerFrame.this.statusBar.setYPosition(e.getY()); } else { try { AffineTransform at = JSVGViewerFrame.this.svgCanvas.getViewBoxTransform(); if (at != null) { at = at.createInverse(); Point2D p2d = at.transform(new Point2D.Float(e.getX(), e.getY()), null); JSVGViewerFrame.this.statusBar.setXPosition((float)p2d.getX()); JSVGViewerFrame.this.statusBar.setYPosition((float)p2d.getY()); return; }  } catch (NoninvertibleTransformException noninvertibleTransformException) {} JSVGViewerFrame.this.statusBar.setXPosition(e.getX()); JSVGViewerFrame.this.statusBar.setYPosition(e.getY()); }  } }
/*      */       ); this.svgCanvas.addMouseListener(new MouseAdapter() { public void mouseExited(MouseEvent e) { Dimension dim = JSVGViewerFrame.this.svgCanvas.getSize(); if (JSVGViewerFrame.this.svgDocument == null) { JSVGViewerFrame.this.statusBar.setWidth(dim.width); JSVGViewerFrame.this.statusBar.setHeight(dim.height); } else { try { AffineTransform at = JSVGViewerFrame.this.svgCanvas.getViewBoxTransform(); if (at != null) { at = at.createInverse(); Point2D o = at.transform(new Point2D.Float(0.0F, 0.0F), null); Point2D p2d = at.transform(new Point2D.Float(dim.width, dim.height), null); JSVGViewerFrame.this.statusBar.setWidth((float)(p2d.getX() - o.getX())); JSVGViewerFrame.this.statusBar.setHeight((float)(p2d.getY() - o.getY())); return; }  } catch (NoninvertibleTransformException noninvertibleTransformException) {} JSVGViewerFrame.this.statusBar.setWidth(dim.width); JSVGViewerFrame.this.statusBar.setHeight(dim.height); }  } }
/*      */       ); this.svgCanvas.addComponentListener(new ComponentAdapter() { public void componentResized(ComponentEvent e) { Dimension dim = JSVGViewerFrame.this.svgCanvas.getSize(); if (JSVGViewerFrame.this.svgDocument == null) { JSVGViewerFrame.this.statusBar.setWidth(dim.width); JSVGViewerFrame.this.statusBar.setHeight(dim.height); } else { try { AffineTransform at = JSVGViewerFrame.this.svgCanvas.getViewBoxTransform(); if (at != null) { at = at.createInverse(); Point2D o = at.transform(new Point2D.Float(0.0F, 0.0F), null); Point2D p2d = at.transform(new Point2D.Float(dim.width, dim.height), null); JSVGViewerFrame.this.statusBar.setWidth((float)(p2d.getX() - o.getX())); JSVGViewerFrame.this.statusBar.setHeight((float)(p2d.getY() - o.getY())); return; }  } catch (NoninvertibleTransformException noninvertibleTransformException) {} JSVGViewerFrame.this.statusBar.setWidth(dim.width); JSVGViewerFrame.this.statusBar.setHeight(dim.height); }  } }
/*      */       ); this.locationBar.addActionListener(new AbstractAction() {
/*      */           public void actionPerformed(ActionEvent e) { String st = JSVGViewerFrame.this.locationBar.getText().trim(); int i = st.indexOf('#'); String t = ""; if (i != -1) { t = st.substring(i + 1); st = st.substring(0, i); }  if (st.equals("")) return;  try { File f = new File(st); if (f.exists()) { if (f.isDirectory()) return;  try { st = f.getCanonicalPath(); if (st.startsWith("/")) { st = "file:" + st; } else { st = "file:/" + st; }  } catch (IOException iOException) {} }  } catch (SecurityException securityException) {} String fi = JSVGViewerFrame.this.svgCanvas.getFragmentIdentifier(); if (JSVGViewerFrame.this.svgDocument != null) { ParsedURL docPURL = new ParsedURL(JSVGViewerFrame.this.svgDocument.getURL()); ParsedURL purl = new ParsedURL(docPURL, st); fi = (fi == null) ? "" : fi; if (docPURL.equals(purl) && t.equals(fi)) return;  }  if (t.length() != 0)
/*      */               st = st + '#' + t;  JSVGViewerFrame.this.locationBar.setText(st); JSVGViewerFrame.this.locationBar.addToHistory(st); JSVGViewerFrame.this.showSVGDocument(st); }
/*      */         });
/* 2296 */   } public Action getAction(String key) throws MissingListenerException { Action result = (Action)this.listeners.get(key);
/*      */ 
/*      */ 
/*      */     
/* 2300 */     if (result == null) {
/* 2301 */       throw new MissingListenerException("Can't find action.", "org.apache.batik.apps.svgbrowser.resources.GUI", key);
/*      */     }
/* 2303 */     return result; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void documentLoadingStarted(SVGDocumentLoaderEvent e) {
/* 2314 */     String msg = resources.getString("Message.documentLoad");
/* 2315 */     if (this.debug) {
/* 2316 */       System.out.println(msg);
/* 2317 */       this.time = System.currentTimeMillis();
/*      */     } 
/* 2319 */     this.statusBar.setMainMessage(msg);
/* 2320 */     this.stopAction.update(true);
/* 2321 */     this.svgCanvas.setCursor(WAIT_CURSOR);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
/* 2329 */     if (this.debug) {
/* 2330 */       System.out.print(resources.getString("Message.documentLoadTime"));
/* 2331 */       System.out.println((System.currentTimeMillis() - this.time) + " ms");
/*      */     } 
/*      */     
/* 2334 */     setSVGDocument(e.getSVGDocument(), e.getSVGDocument().getURL(), e.getSVGDocument().getTitle());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSVGDocument(SVGDocument svgDocument, String svgDocumentURL, String svgDocumentTitle) {
/* 2345 */     this.svgDocument = svgDocument;
/*      */     
/* 2347 */     if (this.domViewer != null) {
/* 2348 */       if (this.domViewer.isVisible() && svgDocument != null) {
/* 2349 */         this.domViewer.setDocument((Document)svgDocument, (ViewCSS)svgDocument.getDocumentElement());
/*      */       } else {
/*      */         
/* 2352 */         this.domViewer.dispose();
/* 2353 */         this.domViewer = null;
/*      */       } 
/*      */     }
/* 2356 */     this.stopAction.update(false);
/* 2357 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/* 2358 */     String s = svgDocumentURL;
/* 2359 */     this.locationBar.setText(s);
/* 2360 */     if (this.debugger != null) {
/* 2361 */       this.debugger.detach();
/* 2362 */       this.debugger.setDocumentURL(s);
/*      */     } 
/* 2364 */     if (this.title == null) {
/* 2365 */       this.title = getTitle();
/*      */     }
/*      */     
/* 2368 */     String dt = svgDocumentTitle;
/* 2369 */     if (dt.length() != 0) {
/* 2370 */       setTitle(this.title + ": " + dt);
/*      */     } else {
/* 2372 */       int i = s.lastIndexOf("/");
/* 2373 */       if (i == -1)
/* 2374 */         i = s.lastIndexOf("\\"); 
/* 2375 */       if (i == -1) {
/* 2376 */         setTitle(this.title + ": " + s);
/*      */       } else {
/* 2378 */         setTitle(this.title + ": " + s.substring(i + 1));
/*      */       } 
/*      */     } 
/*      */     
/* 2382 */     this.localHistory.update(s);
/* 2383 */     this.application.addVisitedURI(s);
/* 2384 */     this.backAction.update();
/* 2385 */     this.forwardAction.update();
/*      */     
/* 2387 */     this.transformHistory = new TransformHistory();
/* 2388 */     this.previousTransformAction.update();
/* 2389 */     this.nextTransformAction.update();
/*      */     
/* 2391 */     this.useStylesheetAction.update();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void documentLoadingCancelled(SVGDocumentLoaderEvent e) {
/* 2398 */     String msg = resources.getString("Message.documentCancelled");
/* 2399 */     if (this.debug) {
/* 2400 */       System.out.println(msg);
/*      */     }
/* 2402 */     this.statusBar.setMainMessage("");
/* 2403 */     this.statusBar.setMessage(msg);
/* 2404 */     this.stopAction.update(false);
/* 2405 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void documentLoadingFailed(SVGDocumentLoaderEvent e) {
/* 2412 */     String msg = resources.getString("Message.documentFailed");
/* 2413 */     if (this.debug) {
/* 2414 */       System.out.println(msg);
/*      */     }
/* 2416 */     this.statusBar.setMainMessage("");
/* 2417 */     this.statusBar.setMessage(msg);
/* 2418 */     this.stopAction.update(false);
/* 2419 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtBuildStarted(GVTTreeBuilderEvent e) {
/* 2429 */     String msg = resources.getString("Message.treeBuild");
/* 2430 */     if (this.debug) {
/* 2431 */       System.out.println(msg);
/* 2432 */       this.time = System.currentTimeMillis();
/*      */     } 
/* 2434 */     this.statusBar.setMainMessage(msg);
/* 2435 */     this.stopAction.update(true);
/* 2436 */     this.svgCanvas.setCursor(WAIT_CURSOR);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtBuildCompleted(GVTTreeBuilderEvent e) {
/* 2443 */     if (this.debug) {
/* 2444 */       System.out.print(resources.getString("Message.treeBuildTime"));
/* 2445 */       System.out.println((System.currentTimeMillis() - this.time) + " ms");
/*      */     } 
/* 2447 */     if (this.findDialog != null) {
/* 2448 */       if (this.findDialog.isVisible()) {
/* 2449 */         this.findDialog.setGraphicsNode(this.svgCanvas.getGraphicsNode());
/*      */       } else {
/* 2451 */         this.findDialog.dispose();
/* 2452 */         this.findDialog = null;
/*      */       } 
/*      */     }
/* 2455 */     this.stopAction.update(false);
/* 2456 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/* 2457 */     this.svgCanvas.setSelectionOverlayXORMode(this.application.isSelectionOverlayXORMode());
/*      */     
/* 2459 */     this.svgCanvas.requestFocus();
/* 2460 */     if (this.debugger != null) {
/* 2461 */       this.debugger.attach();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtBuildCancelled(GVTTreeBuilderEvent e) {
/* 2469 */     String msg = resources.getString("Message.treeCancelled");
/* 2470 */     if (this.debug) {
/* 2471 */       System.out.println(msg);
/*      */     }
/* 2473 */     this.statusBar.setMainMessage("");
/* 2474 */     this.statusBar.setMessage(msg);
/* 2475 */     this.stopAction.update(false);
/* 2476 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/* 2477 */     this.svgCanvas.setSelectionOverlayXORMode(this.application.isSelectionOverlayXORMode());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtBuildFailed(GVTTreeBuilderEvent e) {
/* 2485 */     String msg = resources.getString("Message.treeFailed");
/* 2486 */     if (this.debug) {
/* 2487 */       System.out.println(msg);
/*      */     }
/* 2489 */     this.statusBar.setMainMessage("");
/* 2490 */     this.statusBar.setMessage(msg);
/* 2491 */     this.stopAction.update(false);
/* 2492 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/* 2493 */     this.svgCanvas.setSelectionOverlayXORMode(this.application.isSelectionOverlayXORMode());
/*      */     
/* 2495 */     if (this.autoAdjust) {
/* 2496 */       pack();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void svgLoadEventDispatchStarted(SVGLoadEventDispatcherEvent e) {
/* 2506 */     String msg = resources.getString("Message.onload");
/* 2507 */     if (this.debug) {
/* 2508 */       System.out.println(msg);
/* 2509 */       this.time = System.currentTimeMillis();
/*      */     } 
/* 2511 */     this.stopAction.update(true);
/* 2512 */     this.statusBar.setMainMessage(msg);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void svgLoadEventDispatchCompleted(SVGLoadEventDispatcherEvent e) {
/* 2519 */     if (this.debug) {
/* 2520 */       System.out.print(resources.getString("Message.onloadTime"));
/* 2521 */       System.out.println((System.currentTimeMillis() - this.time) + " ms");
/*      */     } 
/* 2523 */     this.stopAction.update(false);
/* 2524 */     this.statusBar.setMainMessage("");
/* 2525 */     this.statusBar.setMessage(resources.getString("Message.done"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void svgLoadEventDispatchCancelled(SVGLoadEventDispatcherEvent e) {
/* 2532 */     String msg = resources.getString("Message.onloadCancelled");
/* 2533 */     if (this.debug) {
/* 2534 */       System.out.println(msg);
/*      */     }
/* 2536 */     this.stopAction.update(false);
/* 2537 */     this.statusBar.setMainMessage("");
/* 2538 */     this.statusBar.setMessage(msg);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void svgLoadEventDispatchFailed(SVGLoadEventDispatcherEvent e) {
/* 2545 */     String msg = resources.getString("Message.onloadFailed");
/* 2546 */     if (this.debug) {
/* 2547 */       System.out.println(msg);
/*      */     }
/* 2549 */     this.stopAction.update(false);
/* 2550 */     this.statusBar.setMainMessage("");
/* 2551 */     this.statusBar.setMessage(msg);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtRenderingPrepare(GVTTreeRendererEvent e) {
/* 2560 */     if (this.debug) {
/* 2561 */       String msg = resources.getString("Message.treeRenderingPrep");
/* 2562 */       System.out.println(msg);
/* 2563 */       this.time = System.currentTimeMillis();
/*      */     } 
/* 2565 */     this.stopAction.update(true);
/* 2566 */     this.svgCanvas.setCursor(WAIT_CURSOR);
/* 2567 */     this.statusBar.setMainMessage(resources.getString("Message.treeRendering"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtRenderingStarted(GVTTreeRendererEvent e) {
/* 2574 */     if (this.debug) {
/* 2575 */       String msg = resources.getString("Message.treeRenderingPrepTime");
/* 2576 */       System.out.print(msg);
/* 2577 */       System.out.println((System.currentTimeMillis() - this.time) + " ms");
/* 2578 */       this.time = System.currentTimeMillis();
/* 2579 */       msg = resources.getString("Message.treeRenderingStart");
/* 2580 */       System.out.println(msg);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
/* 2589 */     if (this.debug) {
/* 2590 */       String msg = resources.getString("Message.treeRenderingTime");
/* 2591 */       System.out.print(msg);
/* 2592 */       System.out.println((System.currentTimeMillis() - this.time) + " ms");
/*      */     } 
/* 2594 */     this.statusBar.setMainMessage("");
/* 2595 */     this.statusBar.setMessage(resources.getString("Message.done"));
/* 2596 */     if (!this.svgCanvas.isDynamic() || this.managerStopped) {
/* 2597 */       this.stopAction.update(false);
/*      */     }
/* 2599 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/*      */     
/* 2601 */     this.transformHistory.update(this.svgCanvas.getRenderingTransform());
/* 2602 */     this.previousTransformAction.update();
/* 2603 */     this.nextTransformAction.update();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtRenderingCancelled(GVTTreeRendererEvent e) {
/* 2610 */     String msg = resources.getString("Message.treeRenderingCancelled");
/* 2611 */     if (this.debug) {
/* 2612 */       System.out.println(msg);
/*      */     }
/* 2614 */     this.statusBar.setMainMessage("");
/* 2615 */     this.statusBar.setMessage(msg);
/* 2616 */     if (!this.svgCanvas.isDynamic()) {
/* 2617 */       this.stopAction.update(false);
/*      */     }
/* 2619 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void gvtRenderingFailed(GVTTreeRendererEvent e) {
/* 2626 */     String msg = resources.getString("Message.treeRenderingFailed");
/* 2627 */     if (this.debug) {
/* 2628 */       System.out.println(msg);
/*      */     }
/* 2630 */     this.statusBar.setMainMessage("");
/* 2631 */     this.statusBar.setMessage(msg);
/* 2632 */     if (!this.svgCanvas.isDynamic()) {
/* 2633 */       this.stopAction.update(false);
/*      */     }
/* 2635 */     this.svgCanvas.setCursor(DEFAULT_CURSOR);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void linkActivated(LinkActivationEvent e) {
/* 2644 */     String s = e.getReferencedURI();
/* 2645 */     if (this.svgDocument != null) {
/* 2646 */       ParsedURL docURL = new ParsedURL(this.svgDocument.getURL());
/* 2647 */       ParsedURL url = new ParsedURL(docURL, s);
/* 2648 */       if (!url.sameFile(docURL)) {
/*      */         return;
/*      */       }
/*      */       
/* 2652 */       if (s.indexOf('#') != -1) {
/* 2653 */         this.localHistory.update(s);
/* 2654 */         this.locationBar.setText(s);
/* 2655 */         if (this.debugger != null) {
/* 2656 */           this.debugger.detach();
/* 2657 */           this.debugger.setDocumentURL(s);
/*      */         } 
/* 2659 */         this.application.addVisitedURI(s);
/* 2660 */         this.backAction.update();
/* 2661 */         this.forwardAction.update();
/*      */         
/* 2663 */         this.transformHistory = new TransformHistory();
/* 2664 */         this.previousTransformAction.update();
/* 2665 */         this.nextTransformAction.update();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void managerStarted(UpdateManagerEvent e) {
/* 2676 */     if (this.debug) {
/* 2677 */       String msg = resources.getString("Message.updateManagerStarted");
/* 2678 */       System.out.println(msg);
/*      */     } 
/* 2680 */     this.managerStopped = false;
/* 2681 */     this.playAction.update(false);
/* 2682 */     this.pauseAction.update(true);
/* 2683 */     this.stopAction.update(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void managerSuspended(UpdateManagerEvent e) {
/* 2690 */     if (this.debug) {
/* 2691 */       String msg = resources.getString("Message.updateManagerSuspended");
/* 2692 */       System.out.println(msg);
/*      */     } 
/* 2694 */     this.playAction.update(true);
/* 2695 */     this.pauseAction.update(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void managerResumed(UpdateManagerEvent e) {
/* 2702 */     if (this.debug) {
/* 2703 */       String msg = resources.getString("Message.updateManagerResumed");
/* 2704 */       System.out.println(msg);
/*      */     } 
/* 2706 */     this.playAction.update(false);
/* 2707 */     this.pauseAction.update(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void managerStopped(UpdateManagerEvent e) {
/* 2714 */     if (this.debug) {
/* 2715 */       String msg = resources.getString("Message.updateManagerStopped");
/* 2716 */       System.out.println(msg);
/*      */     } 
/* 2718 */     this.managerStopped = true;
/* 2719 */     this.playAction.update(false);
/* 2720 */     this.pauseAction.update(false);
/* 2721 */     this.stopAction.update(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateStarted(UpdateManagerEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateCompleted(UpdateManagerEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateFailed(UpdateManagerEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class UserAgent
/*      */     implements SVGUserAgent
/*      */   {
/*      */     public void displayError(String message) {
/* 2757 */       if (JSVGViewerFrame.this.debug) {
/* 2758 */         System.err.println(message);
/*      */       }
/* 2760 */       JOptionPane pane = new JOptionPane(message, 0);
/*      */       
/* 2762 */       JDialog dialog = pane.createDialog(JSVGViewerFrame.this, "ERROR");
/* 2763 */       dialog.setModal(false);
/* 2764 */       dialog.setVisible(true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayError(Exception ex) {
/* 2771 */       if (JSVGViewerFrame.this.debug) {
/* 2772 */         ex.printStackTrace();
/*      */       }
/* 2774 */       JErrorPane pane = new JErrorPane(ex, 0);
/* 2775 */       JDialog dialog = pane.createDialog(JSVGViewerFrame.this, "ERROR");
/* 2776 */       dialog.setModal(false);
/* 2777 */       dialog.setVisible(true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void displayMessage(String message) {
/* 2785 */       JSVGViewerFrame.this.statusBar.setMessage(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void showAlert(String message) {
/* 2792 */       JSVGViewerFrame.this.svgCanvas.showAlert(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String showPrompt(String message) {
/* 2799 */       return JSVGViewerFrame.this.svgCanvas.showPrompt(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String showPrompt(String message, String defaultValue) {
/* 2806 */       return JSVGViewerFrame.this.svgCanvas.showPrompt(message, defaultValue);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean showConfirm(String message) {
/* 2813 */       return JSVGViewerFrame.this.svgCanvas.showConfirm(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelUnitToMillimeter() {
/* 2820 */       return 0.26458332F;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getPixelToMM() {
/* 2829 */       return getPixelUnitToMillimeter();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getDefaultFontFamily() {
/* 2837 */       return JSVGViewerFrame.this.application.getDefaultFontFamily();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getMediumFontSize() {
/* 2845 */       return 228.59999F / 72.0F * getPixelUnitToMillimeter();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getLighterFontWeight(float f) {
/* 2853 */       int weight = (int)((f + 50.0F) / 100.0F) * 100;
/* 2854 */       switch (weight) { case 100:
/* 2855 */           return 100.0F;
/* 2856 */         case 200: return 100.0F;
/* 2857 */         case 300: return 200.0F;
/* 2858 */         case 400: return 300.0F;
/* 2859 */         case 500: return 400.0F;
/* 2860 */         case 600: return 400.0F;
/* 2861 */         case 700: return 400.0F;
/* 2862 */         case 800: return 400.0F;
/* 2863 */         case 900: return 400.0F; }
/*      */       
/* 2865 */       throw new IllegalArgumentException("Bad Font Weight: " + f);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getBolderFontWeight(float f) {
/* 2874 */       int weight = (int)((f + 50.0F) / 100.0F) * 100;
/* 2875 */       switch (weight) { case 100:
/* 2876 */           return 600.0F;
/* 2877 */         case 200: return 600.0F;
/* 2878 */         case 300: return 600.0F;
/* 2879 */         case 400: return 600.0F;
/* 2880 */         case 500: return 600.0F;
/* 2881 */         case 600: return 700.0F;
/* 2882 */         case 700: return 800.0F;
/* 2883 */         case 800: return 900.0F;
/* 2884 */         case 900: return 900.0F; }
/*      */       
/* 2886 */       throw new IllegalArgumentException("Bad Font Weight: " + f);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getLanguages() {
/* 2895 */       return JSVGViewerFrame.this.application.getLanguages();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getUserStyleSheetURI() {
/* 2903 */       return JSVGViewerFrame.this.application.getUserStyleSheetURI();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getXMLParserClassName() {
/* 2910 */       return JSVGViewerFrame.this.application.getXMLParserClassName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isXMLParserValidating() {
/* 2918 */       return JSVGViewerFrame.this.application.isXMLParserValidating();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getMedia() {
/* 2925 */       return JSVGViewerFrame.this.application.getMedia();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getAlternateStyleSheet() {
/* 2932 */       return JSVGViewerFrame.this.alternateStyleSheet;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void openLink(String uri, boolean newc) {
/* 2941 */       if (newc) {
/* 2942 */         JSVGViewerFrame.this.application.openLink(uri);
/*      */       } else {
/* 2944 */         JSVGViewerFrame.this.showSVGDocument(uri);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean supportExtension(String s) {
/* 2953 */       return false;
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
/*      */     public void handleElement(Element elt, Object data) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ScriptSecurity getScriptSecurity(String scriptType, ParsedURL scriptURL, ParsedURL docURL) {
/* 2975 */       if (!JSVGViewerFrame.this.application.canLoadScriptType(scriptType)) {
/* 2976 */         return (ScriptSecurity)new NoLoadScriptSecurity(scriptType);
/*      */       }
/* 2978 */       switch (JSVGViewerFrame.this.application.getAllowedScriptOrigin()) {
/*      */         case 1:
/* 2980 */           return (ScriptSecurity)new RelaxedScriptSecurity(scriptType, scriptURL, docURL);
/*      */ 
/*      */         
/*      */         case 2:
/* 2984 */           return (ScriptSecurity)new DefaultScriptSecurity(scriptType, scriptURL, docURL);
/*      */ 
/*      */         
/*      */         case 4:
/* 2988 */           return (ScriptSecurity)new EmbededScriptSecurity(scriptType, scriptURL, docURL);
/*      */       } 
/*      */ 
/*      */       
/* 2992 */       return (ScriptSecurity)new NoLoadScriptSecurity(scriptType);
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
/*      */     public void checkLoadScript(String scriptType, ParsedURL scriptURL, ParsedURL docURL) throws SecurityException {
/* 3018 */       ScriptSecurity s = getScriptSecurity(scriptType, scriptURL, docURL);
/*      */ 
/*      */ 
/*      */       
/* 3022 */       if (s != null) {
/* 3023 */         s.checkLoadScript();
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
/*      */     public ExternalResourceSecurity getExternalResourceSecurity(ParsedURL resourceURL, ParsedURL docURL) {
/* 3041 */       switch (JSVGViewerFrame.this.application.getAllowedExternalResourceOrigin()) {
/*      */         case 1:
/* 3043 */           return (ExternalResourceSecurity)new RelaxedExternalResourceSecurity(resourceURL, docURL);
/*      */         
/*      */         case 2:
/* 3046 */           return (ExternalResourceSecurity)new DefaultExternalResourceSecurity(resourceURL, docURL);
/*      */         
/*      */         case 4:
/* 3049 */           return (ExternalResourceSecurity)new EmbededExternalResourceSecurity(resourceURL);
/*      */       } 
/* 3051 */       return (ExternalResourceSecurity)new NoLoadExternalResourceSecurity();
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
/*      */     public void checkLoadExternalResource(ParsedURL resourceURL, ParsedURL docURL) throws SecurityException {
/* 3074 */       ExternalResourceSecurity s = getExternalResourceSecurity(resourceURL, docURL);
/*      */ 
/*      */       
/* 3077 */       if (s != null) {
/* 3078 */         s.checkLoadExternalResource();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ImageFileFilter
/*      */     extends FileFilter
/*      */   {
/*      */     protected String extension;
/*      */ 
/*      */     
/*      */     public ImageFileFilter(String extension) {
/* 3092 */       this.extension = extension;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean accept(File f) {
/* 3100 */       boolean accept = false;
/* 3101 */       String fileName = null;
/* 3102 */       if (f != null) {
/* 3103 */         if (f.isDirectory()) {
/* 3104 */           accept = true;
/*      */         } else {
/* 3106 */           fileName = f.getPath().toLowerCase();
/* 3107 */           if (fileName.endsWith(this.extension)) {
/* 3108 */             accept = true;
/*      */           }
/*      */         } 
/*      */       }
/* 3112 */       return accept;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getDescription() {
/* 3119 */       return this.extension;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/JSVGViewerFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */