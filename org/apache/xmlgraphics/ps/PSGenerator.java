/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Stack;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.xmlgraphics.java2d.color.ColorUtil;
/*     */ import org.apache.xmlgraphics.java2d.color.ColorWithAlternatives;
/*     */ import org.apache.xmlgraphics.ps.dsc.ResourceTracker;
/*     */ import org.apache.xmlgraphics.util.DoubleFormatUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PSGenerator
/*     */   implements PSCommandMap
/*     */ {
/*     */   public static final int DEFAULT_LANGUAGE_LEVEL = 3;
/*     */   @Deprecated
/*  64 */   public static final Object ATEND = DSCConstants.ATEND;
/*     */ 
/*     */   
/*     */   public static final char LF = '\n';
/*     */   
/*     */   private static final String IDENTITY_H = "Identity-H";
/*     */   
/*  71 */   private Log log = LogFactory.getLog(getClass());
/*     */   private OutputStream out;
/*  73 */   private int psLevel = 3;
/*     */   private boolean commentsEnabled = true;
/*     */   private boolean compactMode = true;
/*  76 */   private PSCommandMap commandMap = PSProcSets.STD_COMMAND_MAP;
/*     */   
/*  78 */   private Stack<PSState> graphicsStateStack = new Stack<PSState>();
/*     */   
/*     */   private PSState currentState;
/*  81 */   private StringBuffer doubleBuffer = new StringBuffer(16);
/*     */   
/*  83 */   private StringBuffer tempBuffer = new StringBuffer(256);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean identityHEmbedded;
/*     */ 
/*     */ 
/*     */   
/*     */   private PSResource procsetCIDInitResource;
/*     */ 
/*     */ 
/*     */   
/*     */   private PSResource identityHCMapResource;
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceTracker resTracker;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCompactMode() {
/* 106 */     return this.compactMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompactMode(boolean value) {
/* 117 */     this.compactMode = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCommentsEnabled() {
/* 126 */     return this.commentsEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommentsEnabled(boolean value) {
/* 135 */     this.commentsEnabled = value;
/*     */   }
/*     */   
/*     */   private void resetGraphicsState() {
/* 139 */     if (!this.graphicsStateStack.isEmpty()) {
/* 140 */       throw new IllegalStateException("Graphics state stack should be empty at this point");
/*     */     }
/* 142 */     this.currentState = new PSState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream getOutputStream() {
/* 150 */     return this.out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPSLevel() {
/* 158 */     return this.psLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPSLevel(int level) {
/* 166 */     this.psLevel = level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Source resolveURI(String uri) {
/* 177 */     return new StreamSource(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void newLine() throws IOException {
/* 186 */     this.out.write(10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatDouble(double value) {
/* 196 */     this.doubleBuffer.setLength(0);
/* 197 */     DoubleFormatUtil.formatDouble(value, 3, 3, this.doubleBuffer);
/* 198 */     return this.doubleBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatDouble5(double value) {
/* 208 */     this.doubleBuffer.setLength(0);
/* 209 */     DoubleFormatUtil.formatDouble(value, 5, 5, this.doubleBuffer);
/* 210 */     return this.doubleBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(String cmd) throws IOException {
/* 224 */     this.out.write(cmd.getBytes("US-ASCII"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int n) throws IOException {
/* 234 */     write(Integer.toString(n));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeln(String cmd) throws IOException {
/* 244 */     write(cmd);
/* 245 */     newLine();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void commentln(String comment) throws IOException {
/* 256 */     if (isCommentsEnabled()) {
/* 257 */       writeln(comment);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String mapCommand(String command) {
/* 263 */     if (isCompactMode()) {
/* 264 */       return this.commandMap.mapCommand(command);
/*     */     }
/* 266 */     return command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeByteArr(byte[] cmd) throws IOException {
/* 277 */     this.out.write(cmd);
/* 278 */     newLine();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/* 288 */     this.out.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void escapeChar(char c, StringBuffer target) {
/* 298 */     switch (c) {
/*     */       case '\n':
/* 300 */         target.append("\\n");
/*     */         return;
/*     */       case '\r':
/* 303 */         target.append("\\r");
/*     */         return;
/*     */       case '\t':
/* 306 */         target.append("\\t");
/*     */         return;
/*     */       case '\b':
/* 309 */         target.append("\\b");
/*     */         return;
/*     */       case '\f':
/* 312 */         target.append("\\f");
/*     */         return;
/*     */       case '\\':
/* 315 */         target.append("\\\\");
/*     */         return;
/*     */       case '(':
/* 318 */         target.append("\\(");
/*     */         return;
/*     */       case ')':
/* 321 */         target.append("\\)");
/*     */         return;
/*     */     } 
/* 324 */     if (c > 'ÿ') {
/*     */       
/* 326 */       target.append('?');
/* 327 */     } else if (c < ' ' || c > '') {
/* 328 */       target.append('\\');
/*     */       
/* 330 */       target.append((char)(48 + (c >> 6)));
/* 331 */       target.append((char)(48 + (c >> 3) % 8));
/* 332 */       target.append((char)(48 + c % 8));
/*     */     } else {
/*     */       
/* 335 */       target.append(c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String convertStringToDSC(String text) {
/* 347 */     return convertStringToDSC(text, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String convertRealToDSC(float value) {
/* 356 */     return Float.toString(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String convertStringToDSC(String text, boolean forceParentheses) {
/* 367 */     if (text == null || text.length() == 0) {
/* 368 */       return "()";
/*     */     }
/* 370 */     int initialSize = text.length();
/* 371 */     initialSize += initialSize / 2;
/* 372 */     StringBuffer sb = new StringBuffer(initialSize);
/* 373 */     if (text.indexOf(' ') >= 0 || forceParentheses) {
/* 374 */       sb.append('(');
/* 375 */       for (int j = 0; j < text.length(); j++) {
/* 376 */         char c = text.charAt(j);
/* 377 */         escapeChar(c, sb);
/*     */       } 
/* 379 */       sb.append(')');
/* 380 */       return sb.toString();
/*     */     } 
/* 382 */     for (int i = 0; i < text.length(); i++) {
/* 383 */       char c = text.charAt(i);
/* 384 */       escapeChar(c, sb);
/*     */     } 
/* 386 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDSCComment(String name) throws IOException {
/* 399 */     writeln("%%" + name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDSCComment(String name, Object param) throws IOException {
/* 413 */     writeDSCComment(name, new Object[] { param });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDSCComment(String name, Object[] params) throws IOException {
/* 428 */     this.tempBuffer.setLength(0);
/* 429 */     this.tempBuffer.append("%%");
/* 430 */     this.tempBuffer.append(name);
/* 431 */     if (params != null && params.length > 0) {
/* 432 */       this.tempBuffer.append(": ");
/* 433 */       for (int i = 0; i < params.length; i++) {
/* 434 */         if (i > 0) {
/* 435 */           this.tempBuffer.append(" ");
/*     */         }
/*     */         
/* 438 */         if (params[i] instanceof String) {
/* 439 */           this.tempBuffer.append(convertStringToDSC((String)params[i]));
/* 440 */         } else if (params[i] == DSCConstants.ATEND) {
/* 441 */           this.tempBuffer.append(DSCConstants.ATEND);
/* 442 */         } else if (params[i] instanceof Double) {
/* 443 */           this.tempBuffer.append(formatDouble(((Double)params[i]).doubleValue()));
/* 444 */         } else if (params[i] instanceof Number) {
/* 445 */           this.tempBuffer.append(params[i].toString());
/* 446 */         } else if (params[i] instanceof Date) {
/* 447 */           DateFormat datef = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
/* 448 */           this.tempBuffer.append(convertStringToDSC(datef.format((Date)params[i])));
/* 449 */         } else if (params[i] instanceof PSResource) {
/* 450 */           this.tempBuffer.append(((PSResource)params[i]).getResourceSpecification());
/*     */         } else {
/* 452 */           throw new IllegalArgumentException("Unsupported parameter type: " + params[i].getClass().getName());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 457 */     writeln(this.tempBuffer.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveGraphicsState() throws IOException {
/* 466 */     writeln(mapCommand("gsave"));
/*     */     
/* 468 */     PSState state = new PSState(this.currentState, false);
/* 469 */     this.graphicsStateStack.push(this.currentState);
/* 470 */     this.currentState = state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean restoreGraphicsState() throws IOException {
/* 479 */     if (this.graphicsStateStack.size() > 0) {
/* 480 */       writeln(mapCommand("grestore"));
/* 481 */       this.currentState = this.graphicsStateStack.pop();
/* 482 */       return true;
/*     */     } 
/* 484 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PSState getCurrentState() {
/* 494 */     return this.currentState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showPage() throws IOException {
/* 502 */     writeln("showpage");
/* 503 */     resetGraphicsState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void concatMatrix(double a, double b, double c, double d, double e, double f) throws IOException {
/* 519 */     AffineTransform at = new AffineTransform(a, b, c, d, e, f);
/* 520 */     concatMatrix(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void concatMatrix(double[] matrix) throws IOException {
/* 530 */     concatMatrix(matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatMatrix(AffineTransform at) {
/* 541 */     double[] matrix = new double[6];
/* 542 */     at.getMatrix(matrix);
/* 543 */     return "[" + formatDouble5(matrix[0]) + " " + formatDouble5(matrix[1]) + " " + formatDouble5(matrix[2]) + " " + formatDouble5(matrix[3]) + " " + formatDouble5(matrix[4]) + " " + formatDouble5(matrix[5]) + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void concatMatrix(AffineTransform at) throws IOException {
/* 557 */     getCurrentState().concatMatrix(at);
/* 558 */     writeln(formatMatrix(at) + " " + mapCommand("concat"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatRectangleToArray(Rectangle2D rect) {
/* 567 */     return "[" + formatDouble(rect.getX()) + " " + formatDouble(rect.getY()) + " " + formatDouble(rect.getWidth()) + " " + formatDouble(rect.getHeight()) + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void defineRect(double x, double y, double w, double h) throws IOException {
/* 583 */     writeln(formatDouble(x) + " " + formatDouble(y) + " " + formatDouble(w) + " " + formatDouble(h) + " re");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useLineCap(int linecap) throws IOException {
/* 596 */     if (getCurrentState().useLineCap(linecap)) {
/* 597 */       writeln(linecap + " " + mapCommand("setlinecap"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useLineJoin(int linejoin) throws IOException {
/* 607 */     if (getCurrentState().useLineJoin(linejoin)) {
/* 608 */       writeln(linejoin + " " + mapCommand("setlinejoin"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useMiterLimit(float miterlimit) throws IOException {
/* 618 */     if (getCurrentState().useMiterLimit(miterlimit)) {
/* 619 */       writeln(miterlimit + " " + mapCommand("setmiterlimit"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useLineWidth(double width) throws IOException {
/* 629 */     if (getCurrentState().useLineWidth(width)) {
/* 630 */       writeln(formatDouble(width) + " " + mapCommand("setlinewidth"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useDash(String pattern) throws IOException {
/* 640 */     if (pattern == null) {
/* 641 */       pattern = "[] 0";
/*     */     }
/* 643 */     if (getCurrentState().useDash(pattern)) {
/* 644 */       writeln(pattern + " " + mapCommand("setdash"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void useRGBColor(Color col) throws IOException {
/* 656 */     useColor(col);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useColor(Color col) throws IOException {
/* 665 */     if (getCurrentState().useColor(col)) {
/* 666 */       writeln(convertColorToPS(col));
/*     */     }
/*     */   }
/*     */   
/*     */   private String convertColorToPS(Color color) {
/* 671 */     StringBuffer codeBuffer = new StringBuffer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 678 */     boolean established = false;
/* 679 */     if (color instanceof ColorWithAlternatives) {
/* 680 */       ColorWithAlternatives colExt = (ColorWithAlternatives)color;
/*     */       
/* 682 */       Color[] alt = colExt.getAlternativeColors();
/* 683 */       for (int i = 0, c = alt.length; i < c; i++) {
/* 684 */         Color col = alt[i];
/* 685 */         established = establishColorFromColor(codeBuffer, col);
/* 686 */         if (established) {
/*     */           break;
/*     */         }
/*     */       } 
/* 690 */       if (this.log.isDebugEnabled() && alt.length > 0) {
/* 691 */         this.log.debug("None of the alternative colors are supported. Using fallback: " + color);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 697 */     if (!established) {
/* 698 */       established = establishColorFromColor(codeBuffer, color);
/*     */     }
/* 700 */     if (!established) {
/* 701 */       establishFallbackRGB(codeBuffer, color);
/*     */     }
/*     */     
/* 704 */     return codeBuffer.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean establishColorFromColor(StringBuffer codeBuffer, Color color) {
/* 709 */     float[] comps = color.getColorComponents(null);
/* 710 */     if (color.getColorSpace().getType() == 9) {
/*     */       
/* 712 */       writeSetColor(codeBuffer, comps, "setcmykcolor");
/* 713 */       return true;
/*     */     } 
/* 715 */     return false;
/*     */   }
/*     */   
/*     */   private void writeSetColor(StringBuffer codeBuffer, float[] comps, String command) {
/* 719 */     for (int i = 0, c = comps.length; i < c; i++) {
/* 720 */       if (i > 0) {
/* 721 */         codeBuffer.append(" ");
/*     */       }
/* 723 */       codeBuffer.append(formatDouble(comps[i]));
/*     */     } 
/* 725 */     codeBuffer.append(" ").append(mapCommand(command));
/*     */   }
/*     */   
/*     */   private void establishFallbackRGB(StringBuffer codeBuffer, Color color) {
/*     */     float[] comps;
/* 730 */     if (color.getColorSpace().isCS_sRGB()) {
/* 731 */       comps = color.getColorComponents(null);
/*     */     } else {
/* 733 */       if (this.log.isDebugEnabled()) {
/* 734 */         this.log.debug("Converting color to sRGB as a fallback: " + color);
/*     */       }
/* 736 */       ColorSpace sRGB = ColorSpace.getInstance(1000);
/* 737 */       comps = color.getColorComponents(sRGB, null);
/*     */     } 
/* 739 */     assert comps.length == 3;
/* 740 */     boolean gray = ColorUtil.isGray(color);
/* 741 */     if (gray) {
/* 742 */       comps = new float[] { comps[0] };
/*     */     }
/* 744 */     writeSetColor(codeBuffer, comps, gray ? "setgray" : "setrgbcolor");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useFont(String name, float size) throws IOException {
/* 754 */     if (getCurrentState().useFont(name, size))
/* 755 */       writeln(name + " " + formatDouble(size) + " F"); 
/*     */   }
/*     */   
/*     */   public PSGenerator(OutputStream out) {
/* 759 */     this.resTracker = new ResourceTracker();
/*     */     this.out = out;
/*     */     resetGraphicsState();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceTracker getResourceTracker() {
/* 766 */     return this.resTracker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResourceTracker(ResourceTracker resTracker) {
/* 774 */     this.resTracker = resTracker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void notifyStartNewPage() {
/* 784 */     getResourceTracker().notifyStartNewPage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void notifyResourceUsage(PSResource res, boolean needed) {
/* 795 */     getResourceTracker().notifyResourceUsageOnPage(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void writeResources(boolean pageLevel) throws IOException {
/* 808 */     getResourceTracker().writeResources(pageLevel, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isResourceSupplied(PSResource res) {
/* 819 */     return getResourceTracker().isResourceSupplied(res);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean embedIdentityH() throws IOException {
/* 831 */     if (this.identityHEmbedded) {
/* 832 */       return false;
/*     */     }
/* 834 */     this.resTracker.registerNeededResource(getProcsetCIDInitResource());
/* 835 */     writeDSCComment("BeginDocument", "Identity-H");
/* 836 */     InputStream cmap = PSGenerator.class.getResourceAsStream("Identity-H");
/*     */     try {
/* 838 */       IOUtils.copyLarge(cmap, this.out);
/*     */     } finally {
/* 840 */       IOUtils.closeQuietly(cmap);
/*     */     } 
/* 842 */     writeDSCComment("EndDocument");
/* 843 */     this.resTracker.registerSuppliedResource(getIdentityHCMapResource());
/* 844 */     this.identityHEmbedded = true;
/* 845 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PSResource getIdentityHCMapResource() {
/* 856 */     if (this.identityHCMapResource == null) {
/* 857 */       this.identityHCMapResource = new PSResource("cmap", "Identity-H");
/*     */     }
/* 859 */     return this.identityHCMapResource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PSResource getProcsetCIDInitResource() {
/* 869 */     if (this.procsetCIDInitResource == null) {
/* 870 */       this.procsetCIDInitResource = new PSResource("procset", "CIDInit");
/*     */     }
/* 872 */     return this.procsetCIDInitResource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void includeProcsetCIDInitResource() throws IOException {
/* 882 */     writeDSCComment("IncludeResource", getProcsetCIDInitResource());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */