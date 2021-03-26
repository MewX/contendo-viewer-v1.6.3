/*      */ package org.apache.batik.apps.svgbrowser;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.lang.reflect.InvocationHandler;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Proxy;
/*      */ import java.net.Authenticator;
/*      */ import java.net.URLDecoder;
/*      */ import java.net.URLEncoder;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.Vector;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JProgressBar;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.plaf.FontUIResource;
/*      */ import org.apache.batik.swing.JSVGCanvas;
/*      */ import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
/*      */ import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
/*      */ import org.apache.batik.swing.gvt.GVTTreeRendererListener;
/*      */ import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
/*      */ import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
/*      */ import org.apache.batik.swing.svg.GVTTreeBuilderListener;
/*      */ import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
/*      */ import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
/*      */ import org.apache.batik.swing.svg.SVGDocumentLoaderListener;
/*      */ import org.apache.batik.util.ApplicationSecurityEnforcer;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.apache.batik.util.Platform;
/*      */ import org.apache.batik.util.XMLResourceDescriptor;
/*      */ import org.apache.batik.util.resources.ResourceManager;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Main
/*      */   implements Application
/*      */ {
/*      */   public static final String UNKNOWN_SCRIPT_TYPE_LOAD_KEY_EXTENSION = ".load";
/*      */   public static final String PROPERTY_USER_HOME = "user.home";
/*      */   public static final String PROPERTY_JAVA_SECURITY_POLICY = "java.security.policy";
/*      */   public static final String BATIK_CONFIGURATION_SUBDIRECTORY = ".batik";
/*      */   public static final String SQUIGGLE_CONFIGURATION_FILE = "preferences.xml";
/*      */   public static final String SQUIGGLE_POLICY_FILE = "__svgbrowser.policy";
/*      */   public static final String POLICY_GRANT_SCRIPT_NETWORK_ACCESS = "grant {\n  permission java.net.SocketPermission \"*\", \"listen, connect, resolve, accept\";\n};\n\n";
/*      */   public static final String POLICY_GRANT_SCRIPT_FILE_ACCESS = "grant {\n  permission java.io.FilePermission \"<<ALL FILES>>\", \"read\";\n};\n\n";
/*      */   public static final String PREFERENCE_KEY_VISITED_URI_LIST = "preference.key.visited.uri.list";
/*      */   public static final String PREFERENCE_KEY_VISITED_URI_LIST_LENGTH = "preference.key.visited.uri.list.length";
/*      */   public static final String URI_SEPARATOR = " ";
/*      */   public static final String DEFAULT_DEFAULT_FONT_FAMILY = "Arial, Helvetica, sans-serif";
/*      */   public static final String SVG_INITIALIZATION = "resources/init.svg";
/*      */   protected String svgInitializationURI;
/*      */   public static final String RESOURCES = "org.apache.batik.apps.svgbrowser.resources.Main";
/*      */   public static final String SQUIGGLE_SECURITY_POLICY = "org/apache/batik/apps/svgbrowser/resources/svgbrowser.policy";
/*      */   
/*      */   public static void main(String[] args) {
/*  164 */     new Main(args);
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
/*  189 */   protected static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.apps.svgbrowser.resources.Main", Locale.getDefault());
/*  190 */   protected static ResourceManager resources = new ResourceManager(bundle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   protected static ImageIcon frameIcon = new ImageIcon(Main.class.getResource(resources.getString("Frame.icon")));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XMLPreferenceManager preferenceManager;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int MAX_VISITED_URIS = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  212 */   protected Vector lastVisited = new Vector();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  217 */   protected int maxVisitedURIs = 10;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String[] arguments;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean overrideSecurityPolicy = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ApplicationSecurityEnforcer securityEnforcer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  241 */   protected Map handlers = new HashMap<Object, Object>();
/*      */   public Main(String[] args) {
/*  243 */     this.handlers.put("-font-size", new FontSizeHandler());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  249 */     this.viewerFrames = new LinkedList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  266 */     this.arguments = args;
/*      */     
/*  268 */     if (Platform.isOSX) {
/*  269 */       this.uiSpecialization = "OSX";
/*      */ 
/*      */       
/*  272 */       System.setProperty("apple.laf.useScreenMenuBar", "true");
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  277 */         Class<?> clazz1 = Class.forName("com.apple.eawt.Application");
/*  278 */         Class<?> ApplicationListener = Class.forName("com.apple.eawt.ApplicationListener");
/*      */         
/*  280 */         Class<?> ApplicationEvent = Class.forName("com.apple.eawt.ApplicationEvent");
/*      */ 
/*      */         
/*  283 */         Method getApplication = clazz1.getMethod("getApplication", new Class[0]);
/*      */         
/*  285 */         Method addApplicationListener = clazz1.getMethod("addApplicationListener", new Class[] { ApplicationListener });
/*      */ 
/*      */         
/*  288 */         final Method setHandled = ApplicationEvent.getMethod("setHandled", new Class[] { boolean.class });
/*      */ 
/*      */         
/*  291 */         Method setEnabledPreferencesMenu = clazz1.getMethod("setEnabledPreferencesMenu", new Class[] { boolean.class });
/*      */ 
/*      */ 
/*      */         
/*  295 */         InvocationHandler listenerHandler = new InvocationHandler()
/*      */           {
/*      */             public Object invoke(Object proxy, Method method, Object[] args) {
/*  298 */               String name = method.getName();
/*  299 */               if (name.equals("handleAbout")) {
/*  300 */                 JSVGViewerFrame relativeTo = Main.this.viewerFrames.get(0);
/*      */                 
/*  302 */                 AboutDialog dlg = new AboutDialog(relativeTo);
/*      */                 
/*  304 */                 dlg.setSize(dlg.getPreferredSize());
/*  305 */                 dlg.setLocationRelativeTo(relativeTo);
/*  306 */                 dlg.setVisible(true);
/*  307 */                 dlg.toFront();
/*  308 */               } else if (name.equals("handlePreferences")) {
/*  309 */                 JSVGViewerFrame relativeTo = Main.this.viewerFrames.get(0);
/*      */                 
/*  311 */                 Main.this.showPreferenceDialog(relativeTo);
/*  312 */               } else if (!name.equals("handleQuit")) {
/*      */ 
/*      */                 
/*  315 */                 return null;
/*      */               } 
/*      */               try {
/*  318 */                 setHandled.invoke(args[0], new Object[] { Boolean.TRUE });
/*      */               }
/*  320 */               catch (Exception exception) {}
/*      */               
/*  322 */               return null;
/*      */             }
/*      */           };
/*      */         
/*  326 */         Object application = getApplication.invoke(null, (Object[])null);
/*  327 */         setEnabledPreferencesMenu.invoke(application, new Object[] { Boolean.TRUE });
/*      */         
/*  329 */         Object listener = Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[] { ApplicationListener }, listenerHandler);
/*      */ 
/*      */ 
/*      */         
/*  333 */         addApplicationListener.invoke(application, new Object[] { listener });
/*      */       }
/*  335 */       catch (Exception ex) {
/*  336 */         ex.printStackTrace();
/*  337 */         this.uiSpecialization = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  344 */     Map<Object, Object> defaults = new HashMap<Object, Object>(11);
/*      */     
/*  346 */     defaults.put("preference.key.languages", Locale.getDefault().getLanguage());
/*      */     
/*  348 */     defaults.put("preference.key.show.rendering", Boolean.FALSE);
/*      */     
/*  350 */     defaults.put("preference.key.auto.adjust.window", Boolean.TRUE);
/*      */     
/*  352 */     defaults.put("preference.key.selection.xor.mode", Boolean.FALSE);
/*      */     
/*  354 */     defaults.put("preference.key.enable.double.buffering", Boolean.TRUE);
/*      */     
/*  356 */     defaults.put("preference.key.show.debug.trace", Boolean.FALSE);
/*      */     
/*  358 */     defaults.put("preference.key.proxy.host", "");
/*      */     
/*  360 */     defaults.put("preference.key.proxy.port", "");
/*      */     
/*  362 */     defaults.put("preference.key.cssmedia", "screen");
/*      */     
/*  364 */     defaults.put("preference.key.default.font.family", "Arial, Helvetica, sans-serif");
/*      */     
/*  366 */     defaults.put("preference.key.is.xml.parser.validating", Boolean.FALSE);
/*      */     
/*  368 */     defaults.put("preference.key.enforce.secure.scripting", Boolean.TRUE);
/*      */     
/*  370 */     defaults.put("preference.key.grant.script.file.access", Boolean.FALSE);
/*      */     
/*  372 */     defaults.put("preference.key.grant.script.network.access", Boolean.FALSE);
/*      */     
/*  374 */     defaults.put("preference.key.load.java.script", Boolean.TRUE);
/*      */     
/*  376 */     defaults.put("preference.key.load.ecmascript", Boolean.TRUE);
/*      */     
/*  378 */     defaults.put("preference.key.allowed.script.origin", Integer.valueOf(2));
/*      */     
/*  380 */     defaults.put("preference.key.allowed.external.resource.origin", Integer.valueOf(1));
/*      */     
/*  382 */     defaults.put("preference.key.visited.uri.list", "");
/*      */     
/*  384 */     defaults.put("preference.key.visited.uri.list.length", Integer.valueOf(10));
/*      */     
/*  386 */     defaults.put("preference.key.animation.rate.limiting.mode", Integer.valueOf(1));
/*      */     
/*  388 */     defaults.put("preference.key.animation.rate.limiting.cpu", Float.valueOf(0.75F));
/*      */     
/*  390 */     defaults.put("preference.key.animation.rate.limiting.fps", Float.valueOf(10.0F));
/*      */     
/*  392 */     defaults.put("preference.key.user.stylesheet.enabled", Boolean.TRUE);
/*      */ 
/*      */     
/*  395 */     this.securityEnforcer = new ApplicationSecurityEnforcer(getClass(), "org/apache/batik/apps/svgbrowser/resources/svgbrowser.policy");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  401 */       this.preferenceManager = new XMLPreferenceManager("preferences.xml", defaults);
/*      */       
/*  403 */       String dir = System.getProperty("user.home");
/*  404 */       File f = new File(dir, ".batik");
/*  405 */       f.mkdir();
/*  406 */       XMLPreferenceManager.setPreferenceDirectory(f.getCanonicalPath());
/*  407 */       this.preferenceManager.load();
/*  408 */       setPreferences();
/*  409 */       initializeLastVisited();
/*  410 */       Authenticator.setDefault(new JAuthenticator());
/*  411 */     } catch (Exception e) {
/*  412 */       e.printStackTrace();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  418 */     final AboutDialog initDialog = new AboutDialog();
/*  419 */     ((BorderLayout)initDialog.getContentPane().getLayout()).setVgap(8);
/*  420 */     final JProgressBar pb = new JProgressBar(0, 3);
/*  421 */     initDialog.getContentPane().add(pb, "South");
/*      */ 
/*      */     
/*  424 */     Dimension ss = initDialog.getToolkit().getScreenSize();
/*  425 */     Dimension ds = initDialog.getPreferredSize();
/*      */     
/*  427 */     initDialog.setLocation((ss.width - ds.width) / 2, (ss.height - ds.height) / 2);
/*      */ 
/*      */     
/*  430 */     initDialog.setSize(ds);
/*  431 */     initDialog.setVisible(true);
/*      */     
/*  433 */     final JSVGViewerFrame v = new JSVGViewerFrame(this);
/*  434 */     JSVGCanvas c = v.getJSVGCanvas();
/*  435 */     c.addSVGDocumentLoaderListener((SVGDocumentLoaderListener)new SVGDocumentLoaderAdapter() {
/*      */           public void documentLoadingStarted(SVGDocumentLoaderEvent e) {
/*  437 */             pb.setValue(1);
/*      */           }
/*      */           public void documentLoadingCompleted(SVGDocumentLoaderEvent e) {
/*  440 */             pb.setValue(2);
/*      */           }
/*      */         });
/*  443 */     c.addGVTTreeBuilderListener((GVTTreeBuilderListener)new GVTTreeBuilderAdapter() {
/*      */           public void gvtBuildCompleted(GVTTreeBuilderEvent e) {
/*  445 */             pb.setValue(3);
/*      */           }
/*      */         });
/*  448 */     c.addGVTTreeRendererListener((GVTTreeRendererListener)new GVTTreeRendererAdapter() {
/*      */           public void gvtRenderingCompleted(GVTTreeRendererEvent e) {
/*  450 */             initDialog.dispose();
/*  451 */             v.dispose();
/*  452 */             System.gc();
/*  453 */             Main.this.run();
/*      */           }
/*      */         });
/*      */     
/*  457 */     c.setSize(100, 100);
/*  458 */     this.svgInitializationURI = Main.class.getResource("resources/init.svg").toString();
/*  459 */     c.loadSVGDocument(this.svgInitializationURI);
/*      */   }
/*      */   
/*      */   protected List viewerFrames;
/*      */   protected PreferenceDialog preferenceDialog;
/*      */   protected String uiSpecialization;
/*      */   
/*      */   public void installCustomPolicyFile() throws IOException {
/*  467 */     String securityPolicyProperty = System.getProperty("java.security.policy");
/*      */ 
/*      */     
/*  470 */     if (this.overrideSecurityPolicy || securityPolicyProperty == null || "".equals(securityPolicyProperty)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  476 */       ParsedURL policyURL = new ParsedURL(this.securityEnforcer.getPolicyURL());
/*      */ 
/*      */       
/*  479 */       String dir = System.getProperty("user.home");
/*  480 */       File batikConfigDir = new File(dir, ".batik");
/*  481 */       File policyFile = new File(batikConfigDir, "__svgbrowser.policy");
/*      */ 
/*      */       
/*  484 */       Reader r = new BufferedReader(new InputStreamReader(policyURL.openStream()));
/*  485 */       Writer w = new FileWriter(policyFile);
/*      */       
/*  487 */       char[] buf = new char[1024];
/*  488 */       int n = 0;
/*  489 */       while ((n = r.read(buf, 0, buf.length)) != -1) {
/*  490 */         w.write(buf, 0, n);
/*      */       }
/*      */       
/*  493 */       r.close();
/*      */ 
/*      */ 
/*      */       
/*  497 */       boolean grantScriptNetworkAccess = this.preferenceManager.getBoolean("preference.key.grant.script.network.access");
/*      */ 
/*      */       
/*  500 */       boolean grantScriptFileAccess = this.preferenceManager.getBoolean("preference.key.grant.script.file.access");
/*      */ 
/*      */ 
/*      */       
/*  504 */       if (grantScriptNetworkAccess) {
/*  505 */         w.write("grant {\n  permission java.net.SocketPermission \"*\", \"listen, connect, resolve, accept\";\n};\n\n");
/*      */       }
/*      */       
/*  508 */       if (grantScriptFileAccess) {
/*  509 */         w.write("grant {\n  permission java.io.FilePermission \"<<ALL FILES>>\", \"read\";\n};\n\n");
/*      */       }
/*      */       
/*  512 */       w.close();
/*      */ 
/*      */ 
/*      */       
/*  516 */       this.overrideSecurityPolicy = true;
/*      */       
/*  518 */       System.setProperty("java.security.policy", policyFile.toURI().toURL().toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void run() {
/*      */     try {
/*  529 */       int i = 0;
/*      */       
/*  531 */       for (; i < this.arguments.length; i++) {
/*  532 */         OptionHandler oh = (OptionHandler)this.handlers.get(this.arguments[i]);
/*  533 */         if (oh == null) {
/*      */           break;
/*      */         }
/*  536 */         i = oh.handleOption(i);
/*      */       } 
/*      */       
/*  539 */       JSVGViewerFrame frame = createAndShowJSVGViewerFrame();
/*  540 */       while (i < this.arguments.length) {
/*  541 */         if (this.arguments[i].length() == 0) {
/*  542 */           i++;
/*      */           
/*      */           continue;
/*      */         } 
/*  546 */         File file = new File(this.arguments[i]);
/*  547 */         String uri = null;
/*      */         
/*      */         try {
/*  550 */           if (file.canRead()) {
/*  551 */             uri = file.toURI().toURL().toString();
/*      */           }
/*  553 */         } catch (SecurityException securityException) {}
/*      */ 
/*      */ 
/*      */         
/*  557 */         if (uri == null) {
/*  558 */           uri = this.arguments[i];
/*  559 */           ParsedURL purl = null;
/*  560 */           purl = new ParsedURL(this.arguments[i]);
/*      */           
/*  562 */           if (!purl.complete())
/*      */           {
/*  564 */             uri = null;
/*      */           }
/*      */         } 
/*  567 */         if (uri != null) {
/*  568 */           if (frame == null) {
/*  569 */             frame = createAndShowJSVGViewerFrame();
/*      */           }
/*  571 */           frame.showSVGDocument(uri);
/*  572 */           frame = null;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  583 */           JOptionPane.showMessageDialog(frame, resources.getString("Error.skipping.file") + this.arguments[i]);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  588 */         i++;
/*      */       } 
/*  590 */     } catch (Exception e) {
/*  591 */       e.printStackTrace();
/*  592 */       printUsage();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void printUsage() {
/*  600 */     System.out.println();
/*      */     
/*  602 */     System.out.println(resources.getString("Command.header"));
/*  603 */     System.out.println(resources.getString("Command.syntax"));
/*  604 */     System.out.println();
/*  605 */     System.out.println(resources.getString("Command.options"));
/*  606 */     for (Object o : this.handlers.keySet()) {
/*  607 */       String s = (String)o;
/*  608 */       System.out.println(((OptionHandler)this.handlers.get(s)).getDescription());
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
/*      */   protected class FontSizeHandler
/*      */     implements OptionHandler
/*      */   {
/*      */     public int handleOption(int i) {
/*  633 */       int size = Integer.parseInt(Main.this.arguments[++i]);
/*      */       
/*  635 */       Font font = new Font("Dialog", 0, size);
/*  636 */       FontUIResource fontRes = new FontUIResource(font);
/*  637 */       UIManager.put("CheckBox.font", fontRes);
/*  638 */       UIManager.put("PopupMenu.font", fontRes);
/*  639 */       UIManager.put("TextPane.font", fontRes);
/*  640 */       UIManager.put("MenuItem.font", fontRes);
/*  641 */       UIManager.put("ComboBox.font", fontRes);
/*  642 */       UIManager.put("Button.font", fontRes);
/*  643 */       UIManager.put("Tree.font", fontRes);
/*  644 */       UIManager.put("ScrollPane.font", fontRes);
/*  645 */       UIManager.put("TabbedPane.font", fontRes);
/*  646 */       UIManager.put("EditorPane.font", fontRes);
/*  647 */       UIManager.put("TitledBorder.font", fontRes);
/*  648 */       UIManager.put("Menu.font", fontRes);
/*  649 */       UIManager.put("TextArea.font", fontRes);
/*  650 */       UIManager.put("OptionPane.font", fontRes);
/*  651 */       UIManager.put("DesktopIcon.font", fontRes);
/*  652 */       UIManager.put("MenuBar.font", fontRes);
/*  653 */       UIManager.put("ToolBar.font", fontRes);
/*  654 */       UIManager.put("RadioButton.font", fontRes);
/*  655 */       UIManager.put("RadioButtonMenuItem.font", fontRes);
/*  656 */       UIManager.put("ToggleButton.font", fontRes);
/*  657 */       UIManager.put("ToolTip.font", fontRes);
/*  658 */       UIManager.put("ProgressBar.font", fontRes);
/*  659 */       UIManager.put("TableHeader.font", fontRes);
/*  660 */       UIManager.put("Panel.font", fontRes);
/*  661 */       UIManager.put("List.font", fontRes);
/*  662 */       UIManager.put("ColorChooser.font", fontRes);
/*  663 */       UIManager.put("PasswordField.font", fontRes);
/*  664 */       UIManager.put("TextField.font", fontRes);
/*  665 */       UIManager.put("Table.font", fontRes);
/*  666 */       UIManager.put("Label.font", fontRes);
/*  667 */       UIManager.put("InternalFrameTitlePane.font", fontRes);
/*  668 */       UIManager.put("CheckBoxMenuItem.font", fontRes);
/*      */       
/*  670 */       return i;
/*      */     }
/*      */     public String getDescription() {
/*  673 */       return Main.resources.getString("Command.font-size");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public JSVGViewerFrame createAndShowJSVGViewerFrame() {
/*  683 */     JSVGViewerFrame mainFrame = new JSVGViewerFrame(this);
/*  684 */     mainFrame.setSize(resources.getInteger("Frame.width"), resources.getInteger("Frame.height"));
/*      */     
/*  686 */     mainFrame.setIconImage(frameIcon.getImage());
/*  687 */     mainFrame.setTitle(resources.getString("Frame.title"));
/*  688 */     mainFrame.setVisible(true);
/*  689 */     this.viewerFrames.add(mainFrame);
/*  690 */     setPreferences(mainFrame);
/*  691 */     return mainFrame;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeJSVGViewerFrame(JSVGViewerFrame f) {
/*  698 */     f.getJSVGCanvas().stopProcessing();
/*  699 */     this.viewerFrames.remove(f);
/*  700 */     if (this.viewerFrames.size() == 0) {
/*  701 */       System.exit(0);
/*      */     }
/*  703 */     f.dispose();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Action createExitAction(JSVGViewerFrame vf) {
/*  710 */     return new AbstractAction() {
/*      */         public void actionPerformed(ActionEvent e) {
/*  712 */           System.exit(0);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void openLink(String url) {
/*  721 */     JSVGViewerFrame f = createAndShowJSVGViewerFrame();
/*  722 */     f.getJSVGCanvas().loadSVGDocument(url);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getXMLParserClassName() {
/*  729 */     return XMLResourceDescriptor.getXMLParserClassName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isXMLParserValidating() {
/*  737 */     return this.preferenceManager.getBoolean("preference.key.is.xml.parser.validating");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void showPreferenceDialog(JSVGViewerFrame f) {
/*  745 */     if (this.preferenceDialog == null) {
/*  746 */       this.preferenceDialog = new PreferenceDialog(f, this.preferenceManager);
/*      */     }
/*  748 */     if (this.preferenceDialog.showDialog() == 0) {
/*      */       try {
/*  750 */         this.preferenceManager.save();
/*  751 */         setPreferences();
/*  752 */       } catch (Exception exception) {}
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void setPreferences() throws IOException {
/*  758 */     for (Object viewerFrame : this.viewerFrames) {
/*  759 */       setPreferences((JSVGViewerFrame)viewerFrame);
/*      */     }
/*      */     
/*  762 */     System.setProperty("proxyHost", this.preferenceManager.getString("preference.key.proxy.host"));
/*      */     
/*  764 */     System.setProperty("proxyPort", this.preferenceManager.getString("preference.key.proxy.port"));
/*      */ 
/*      */     
/*  767 */     installCustomPolicyFile();
/*      */     
/*  769 */     this.securityEnforcer.enforceSecurity(this.preferenceManager.getBoolean("preference.key.enforce.secure.scripting"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setPreferences(JSVGViewerFrame vf) {
/*      */     float pc, fps;
/*  777 */     boolean db = this.preferenceManager.getBoolean("preference.key.enable.double.buffering");
/*      */     
/*  779 */     vf.getJSVGCanvas().setDoubleBufferedRendering(db);
/*  780 */     boolean sr = this.preferenceManager.getBoolean("preference.key.show.rendering");
/*      */     
/*  782 */     vf.getJSVGCanvas().setProgressivePaint(sr);
/*  783 */     boolean d = this.preferenceManager.getBoolean("preference.key.show.debug.trace");
/*      */     
/*  785 */     vf.setDebug(d);
/*  786 */     boolean aa = this.preferenceManager.getBoolean("preference.key.auto.adjust.window");
/*      */     
/*  788 */     vf.setAutoAdjust(aa);
/*  789 */     boolean dd = this.preferenceManager.getBoolean("preference.key.selection.xor.mode");
/*      */     
/*  791 */     vf.getJSVGCanvas().setSelectionOverlayXORMode(dd);
/*  792 */     int al = this.preferenceManager.getInteger("preference.key.animation.rate.limiting.mode");
/*      */     
/*  794 */     if (al < 0 || al > 2) {
/*  795 */       al = 1;
/*      */     }
/*  797 */     switch (al) {
/*      */       case 0:
/*  799 */         vf.getJSVGCanvas().setAnimationLimitingNone();
/*      */         break;
/*      */       case 1:
/*  802 */         pc = this.preferenceManager.getFloat("preference.key.animation.rate.limiting.cpu");
/*      */         
/*  804 */         if (pc <= 0.0F || pc > 1.0F) {
/*  805 */           pc = 0.75F;
/*      */         }
/*  807 */         vf.getJSVGCanvas().setAnimationLimitingCPU(pc);
/*      */         break;
/*      */       
/*      */       case 2:
/*  811 */         fps = this.preferenceManager.getFloat("preference.key.animation.rate.limiting.fps");
/*      */         
/*  813 */         if (fps <= 0.0F) {
/*  814 */           fps = 10.0F;
/*      */         }
/*  816 */         vf.getJSVGCanvas().setAnimationLimitingFPS(fps);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLanguages() {
/*  826 */     String s = this.preferenceManager.getString("preference.key.languages");
/*      */     
/*  828 */     return (s == null) ? Locale.getDefault().getLanguage() : s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUserStyleSheetURI() {
/*  838 */     boolean enabled = this.preferenceManager.getBoolean("preference.key.user.stylesheet.enabled");
/*      */     
/*  840 */     String ssPath = this.preferenceManager.getString("preference.key.user.stylesheet");
/*      */     
/*  842 */     if (!enabled || ssPath.length() == 0) {
/*  843 */       return null;
/*      */     }
/*      */     try {
/*  846 */       File f = new File(ssPath);
/*  847 */       if (f.exists()) {
/*  848 */         return f.toURI().toURL().toString();
/*      */       }
/*  850 */     } catch (IOException iOException) {}
/*      */ 
/*      */     
/*  853 */     return ssPath;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDefaultFontFamily() {
/*  861 */     return this.preferenceManager.getString("preference.key.default.font.family");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMedia() {
/*  870 */     String s = this.preferenceManager.getString("preference.key.cssmedia");
/*      */     
/*  872 */     return (s == null) ? "screen" : s;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSelectionOverlayXORMode() {
/*  880 */     return this.preferenceManager.getBoolean("preference.key.selection.xor.mode");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canLoadScriptType(String scriptType) {
/*  889 */     if ("text/ecmascript".equals(scriptType) || "application/ecmascript".equals(scriptType) || "text/javascript".equals(scriptType) || "application/javascript".equals(scriptType))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  895 */       return this.preferenceManager.getBoolean("preference.key.load.ecmascript");
/*      */     }
/*  897 */     if ("application/java-archive".equals(scriptType)) {
/*  898 */       return this.preferenceManager.getBoolean("preference.key.load.java.script");
/*      */     }
/*      */     
/*  901 */     return this.preferenceManager.getBoolean(scriptType + ".load");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAllowedScriptOrigin() {
/*  911 */     int ret = this.preferenceManager.getInteger("preference.key.allowed.script.origin");
/*      */ 
/*      */     
/*  914 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAllowedExternalResourceOrigin() {
/*  923 */     int ret = this.preferenceManager.getInteger("preference.key.allowed.external.resource.origin");
/*      */ 
/*      */     
/*  926 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addVisitedURI(String uri) {
/*  933 */     if (this.svgInitializationURI.equals(uri)) {
/*      */       return;
/*      */     }
/*      */     
/*  937 */     int maxVisitedURIs = this.preferenceManager.getInteger("preference.key.visited.uri.list.length");
/*      */ 
/*      */ 
/*      */     
/*  941 */     if (maxVisitedURIs < 0) {
/*  942 */       maxVisitedURIs = 0;
/*      */     }
/*      */     
/*  945 */     if (this.lastVisited.contains(uri)) {
/*  946 */       this.lastVisited.removeElement(uri);
/*      */     }
/*      */     
/*  949 */     while (this.lastVisited.size() > 0 && this.lastVisited.size() > maxVisitedURIs - 1) {
/*  950 */       this.lastVisited.removeElementAt(0);
/*      */     }
/*      */     
/*  953 */     if (maxVisitedURIs > 0) {
/*  954 */       this.lastVisited.addElement(uri);
/*      */     }
/*      */ 
/*      */     
/*  958 */     StringBuffer lastVisitedBuffer = new StringBuffer(this.lastVisited.size() * 8);
/*      */     
/*  960 */     for (Object aLastVisited : this.lastVisited) {
/*      */       try {
/*  962 */         lastVisitedBuffer.append(URLEncoder.encode(aLastVisited.toString(), Charset.defaultCharset().name()));
/*      */       
/*      */       }
/*  965 */       catch (UnsupportedEncodingException e) {
/*  966 */         throw new RuntimeException(e);
/*      */       } 
/*  968 */       lastVisitedBuffer.append(" ");
/*      */     } 
/*      */     
/*  971 */     this.preferenceManager.setString("preference.key.visited.uri.list", lastVisitedBuffer.toString());
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  976 */       this.preferenceManager.save();
/*  977 */     } catch (Exception exception) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getVisitedURIs() {
/*  986 */     String[] visitedURIs = new String[this.lastVisited.size()];
/*  987 */     this.lastVisited.toArray((Object[])visitedURIs);
/*  988 */     return visitedURIs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUISpecialization() {
/*  995 */     return this.uiSpecialization;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initializeLastVisited() {
/* 1002 */     String lastVisitedStr = this.preferenceManager.getString("preference.key.visited.uri.list");
/*      */ 
/*      */     
/* 1005 */     StringTokenizer st = new StringTokenizer(lastVisitedStr, " ");
/*      */ 
/*      */ 
/*      */     
/* 1009 */     int n = st.countTokens();
/*      */     
/* 1011 */     int maxVisitedURIs = this.preferenceManager.getInteger("preference.key.visited.uri.list.length");
/*      */ 
/*      */ 
/*      */     
/* 1015 */     if (n > maxVisitedURIs) {
/* 1016 */       n = maxVisitedURIs;
/*      */     }
/*      */     
/* 1019 */     for (int i = 0; i < n; i++) {
/*      */       try {
/* 1021 */         this.lastVisited.addElement(URLDecoder.decode(st.nextToken(), Charset.defaultCharset().name()));
/*      */       }
/* 1023 */       catch (UnsupportedEncodingException e) {
/* 1024 */         throw new RuntimeException(e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static interface OptionHandler {
/*      */     int handleOption(int param1Int);
/*      */     
/*      */     String getDescription();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */