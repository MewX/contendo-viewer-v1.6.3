/*      */ package org.apache.pdfbox.text;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.LineNumberReader;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.text.Bidi;
/*      */ import java.text.Normalizer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.SortedMap;
/*      */ import java.util.SortedSet;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TreeMap;
/*      */ import java.util.TreeSet;
/*      */ import java.util.regex.Pattern;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.pdmodel.PDDocument;
/*      */ import org.apache.pdfbox.pdmodel.PDPage;
/*      */ import org.apache.pdfbox.pdmodel.PDPageTree;
/*      */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*      */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
/*      */ import org.apache.pdfbox.pdmodel.interactive.pagenavigation.PDThreadBead;
/*      */ import org.apache.pdfbox.util.QuickSort;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PDFTextStripper
/*      */   extends LegacyPDFStreamEngine
/*      */ {
/*   63 */   private static float defaultIndentThreshold = 2.0F;
/*   64 */   private static float defaultDropThreshold = 2.5F;
/*      */   
/*      */   private static final boolean useCustomQuickSort;
/*   67 */   private static final Log LOG = LogFactory.getLog(PDFTextStripper.class); protected final String LINE_SEPARATOR = System.getProperty("line.separator"); private String lineSeparator = this.LINE_SEPARATOR; private String wordSeparator = " "; private String paragraphStart = ""; private String paragraphEnd = ""; private String pageStart = ""; private String pageEnd = this.LINE_SEPARATOR; private String articleStart = ""; private String articleEnd = ""; private int currentPageNo = 0; private int startPage = 1; private int endPage = Integer.MAX_VALUE; private PDOutlineItem startBookmark = null; private int startBookmarkPageNumber = -1; private int endBookmarkPageNumber = -1; private PDOutlineItem endBookmark = null; private boolean suppressDuplicateOverlappingText = true; private boolean shouldSeparateByBeads = true; private boolean sortByPosition = false; private boolean addMoreFormatting = false; private float indentThreshold = defaultIndentThreshold; private float dropThreshold = defaultDropThreshold; private float spacingTolerance = 0.5F; private float averageCharTolerance = 0.3F; private List<PDRectangle> beadRectangles = null; protected ArrayList<List<TextPosition>> charactersByArticle = new ArrayList<List<TextPosition>>(); private Map<String, TreeMap<Float, TreeSet<Float>>> characterListMapping = new HashMap<String, TreeMap<Float, TreeSet<Float>>>(); protected PDDocument document; protected Writer output; private boolean inParagraph; private static final float END_OF_LAST_TEXT_X_RESET_VALUE = -1.0F; private static final float MAX_Y_FOR_LINE_RESET_VALUE = -3.4028235E38F; private static final float EXPECTED_START_OF_NEXT_WORD_X_RESET_VALUE = -3.4028235E38F; private static final float MAX_HEIGHT_FOR_LINE_RESET_VALUE = -1.0F; private static final float MIN_Y_TOP_FOR_LINE_RESET_VALUE = 3.4028235E38F; private static final float LAST_WORD_SPACING_RESET_VALUE = -1.0F; private static final String[] LIST_ITEM_EXPRESSIONS; private List<Pattern> listOfPatterns; private static Map<Character, Character> MIRRORING_CHAR_MAP; public String getText(PDDocument doc) throws IOException { StringWriter outputStream = new StringWriter(); writeText(doc, outputStream); return outputStream.toString(); } private void resetEngine() { this.currentPageNo = 0; this.document = null; if (this.charactersByArticle != null)
/*      */       this.charactersByArticle.clear();  if (this.characterListMapping != null)
/*      */       this.characterListMapping.clear();  } public void writeText(PDDocument doc, Writer outputStream) throws IOException { resetEngine(); this.document = doc; this.output = outputStream; if (getAddMoreFormatting()) { this.paragraphEnd = this.lineSeparator; this.pageStart = this.lineSeparator; this.articleStart = this.lineSeparator; this.articleEnd = this.lineSeparator; }  startDocument(this.document); processPages(this.document.getPages()); endDocument(this.document); }
/*      */   protected void processPages(PDPageTree pages) throws IOException { PDPage startBookmarkPage = (this.startBookmark == null) ? null : this.startBookmark.findDestinationPage(this.document); if (startBookmarkPage != null) { this.startBookmarkPageNumber = pages.indexOf(startBookmarkPage) + 1; } else { this.startBookmarkPageNumber = -1; }  PDPage endBookmarkPage = (this.endBookmark == null) ? null : this.endBookmark.findDestinationPage(this.document); if (endBookmarkPage != null) { this.endBookmarkPageNumber = pages.indexOf(endBookmarkPage) + 1; } else { this.endBookmarkPageNumber = -1; }  if (this.startBookmarkPageNumber == -1 && this.startBookmark != null && this.endBookmarkPageNumber == -1 && this.endBookmark != null && this.startBookmark.getCOSObject() == this.endBookmark.getCOSObject()) { this.startBookmarkPageNumber = 0; this.endBookmarkPageNumber = 0; }  for (PDPage page : pages) { this.currentPageNo++; if (page.hasContents())
/*      */         processPage(page);  }
/*      */      }
/*      */   protected void startDocument(PDDocument document) throws IOException {}
/*      */   protected void endDocument(PDDocument document) throws IOException {}
/*   75 */   static { String strDrop = null, strIndent = null;
/*      */     
/*      */     try {
/*   78 */       String className = PDFTextStripper.class.getSimpleName().toLowerCase();
/*   79 */       String prop = className + ".indent";
/*   80 */       strIndent = System.getProperty(prop);
/*   81 */       prop = className + ".drop";
/*   82 */       strDrop = System.getProperty(prop);
/*      */     }
/*   84 */     catch (SecurityException securityException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   89 */     if (strIndent != null && strIndent.length() > 0) {
/*      */       
/*      */       try {
/*      */         
/*   93 */         defaultIndentThreshold = Float.parseFloat(strIndent);
/*      */       }
/*   95 */       catch (NumberFormatException numberFormatException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  100 */     if (strDrop != null && strDrop.length() > 0) {
/*      */       
/*      */       try {
/*      */         
/*  104 */         defaultDropThreshold = Float.parseFloat(strDrop);
/*      */       }
/*  106 */       catch (NumberFormatException numberFormatException) {}
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
/*  117 */     boolean is16orLess = false;
/*      */     
/*      */     try {
/*  120 */       String version = System.getProperty("java.specification.version");
/*  121 */       StringTokenizer st = new StringTokenizer(version, ".");
/*  122 */       int majorVersion = Integer.parseInt(st.nextToken());
/*  123 */       int minorVersion = 0;
/*  124 */       if (st.hasMoreTokens())
/*      */       {
/*  126 */         minorVersion = Integer.parseInt(st.nextToken());
/*      */       }
/*  128 */       is16orLess = (majorVersion == 1 && minorVersion <= 6);
/*      */     }
/*  130 */     catch (SecurityException securityException) {
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  135 */     catch (NumberFormatException numberFormatException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  140 */     useCustomQuickSort = !is16orLess;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1643 */     LIST_ITEM_EXPRESSIONS = new String[] { "\\.", "\\d+\\.", "\\[\\d+\\]", "\\d+\\)", "[A-Z]\\.", "[a-z]\\.", "[A-Z]\\)", "[a-z]\\)", "[IVXL]+\\.", "[ivxl]+\\." };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1838 */     MIRRORING_CHAR_MAP = new HashMap<Character, Character>();
/*      */ 
/*      */ 
/*      */     
/* 1842 */     String path = "/org/apache/pdfbox/resources/text/BidiMirroring.txt";
/* 1843 */     InputStream input = PDFTextStripper.class.getResourceAsStream(path);
/*      */ 
/*      */     
/* 1846 */     try { if (input != null)
/*      */       {
/* 1848 */         parseBidiFile(input);
/*      */       }
/*      */       else
/*      */       {
/* 1852 */         LOG.warn("Could not find '" + path + "', mirroring char map will be empty: ");
/*      */       }
/*      */        }
/* 1855 */     catch (IOException e)
/*      */     
/* 1857 */     { LOG.warn("Could not parse BidiMirroring.txt, mirroring char map will be empty: " + e
/* 1858 */           .getMessage()); }
/*      */     finally
/*      */     
/*      */     { 
/*      */       
/*      */       try { 
/* 1864 */         input.close(); }
/*      */       
/* 1866 */       catch (IOException e)
/*      */       
/* 1868 */       { LOG.error("Could not close BidiMirroring.txt ", e); }  }  } public void processPage(PDPage page) throws IOException { if (this.currentPageNo >= this.startPage && this.currentPageNo <= this.endPage && (this.startBookmarkPageNumber == -1 || this.currentPageNo >= this.startBookmarkPageNumber) && (this.endBookmarkPageNumber == -1 || this.currentPageNo <= this.endBookmarkPageNumber)) { startPage(page); int numberOfArticleSections = 1; if (this.shouldSeparateByBeads) { fillBeadRectangles(page); numberOfArticleSections += this.beadRectangles.size() * 2; }  int originalSize = this.charactersByArticle.size(); this.charactersByArticle.ensureCapacity(numberOfArticleSections); int lastIndex = Math.max(numberOfArticleSections, originalSize); for (int i = 0; i < lastIndex; i++) { if (i < originalSize) { ((List)this.charactersByArticle.get(i)).clear(); } else if (numberOfArticleSections < originalSize) { this.charactersByArticle.remove(i); } else { this.charactersByArticle.add(new ArrayList<TextPosition>()); }  }  this.characterListMapping.clear(); super.processPage(page); writePage(); endPage(page); }  } private void fillBeadRectangles(PDPage page) { this.beadRectangles = new ArrayList<PDRectangle>(); for (PDThreadBead bead : page.getThreadBeads()) { if (bead == null) { this.beadRectangles.add(null); continue; }  PDRectangle rect = bead.getRectangle(); PDRectangle mediaBox = page.getMediaBox(); float upperRightY = mediaBox.getUpperRightY() - rect.getLowerLeftY(); float lowerLeftY = mediaBox.getUpperRightY() - rect.getUpperRightY(); rect.setLowerLeftY(lowerLeftY); rect.setUpperRightY(upperRightY); PDRectangle cropBox = page.getCropBox(); if (cropBox.getLowerLeftX() != 0.0F || cropBox.getLowerLeftY() != 0.0F) { rect.setLowerLeftX(rect.getLowerLeftX() - cropBox.getLowerLeftX()); rect.setLowerLeftY(rect.getLowerLeftY() - cropBox.getLowerLeftY()); rect.setUpperRightX(rect.getUpperRightX() - cropBox.getLowerLeftX()); rect.setUpperRightY(rect.getUpperRightY() - cropBox.getLowerLeftY()); }  this.beadRectangles.add(rect); }  } protected void startArticle() throws IOException { startArticle(true); }
/*      */   protected void startArticle(boolean isLTR) throws IOException { this.output.write(getArticleStart()); }
/*      */   protected void endArticle() throws IOException { this.output.write(getArticleEnd()); }
/*      */   protected void startPage(PDPage page) throws IOException {}
/*      */   protected void endPage(PDPage page) throws IOException {}
/*      */   protected void writePage() throws IOException { float maxYForLine = -3.4028235E38F; float minYTopForLine = Float.MAX_VALUE; float endOfLastTextX = -1.0F; float lastWordSpacing = -1.0F; float maxHeightForLine = -1.0F; PositionWrapper lastPosition = null; PositionWrapper lastLineStartPosition = null; boolean startOfPage = true; if (this.charactersByArticle.size() > 0) writePageStart();  for (List<TextPosition> textList : this.charactersByArticle) { if (getSortByPosition()) { TextPositionComparator comparator = new TextPositionComparator(); if (useCustomQuickSort) { QuickSort.sort(textList, comparator); } else { Collections.sort(textList, comparator); }  }  startArticle(); boolean startOfArticle = true; List<LineItem> line = new ArrayList<LineItem>(); Iterator<TextPosition> textIter = textList.iterator(); float previousAveCharWidth = -1.0F; while (textIter.hasNext()) { float positionX, positionY, positionWidth, positionHeight, deltaSpace, averageCharWidth; TextPosition position = textIter.next(); PositionWrapper current = new PositionWrapper(position); String characterValue = position.getUnicode(); if (lastPosition != null && (position.getFont() != lastPosition.getTextPosition().getFont() || position.getFontSize() != lastPosition.getTextPosition().getFontSize())) previousAveCharWidth = -1.0F;  if (getSortByPosition()) { positionX = position.getXDirAdj(); positionY = position.getYDirAdj(); positionWidth = position.getWidthDirAdj(); positionHeight = position.getHeightDir(); } else { positionX = position.getX(); positionY = position.getY(); positionWidth = position.getWidth(); positionHeight = position.getHeight(); }  int wordCharCount = (position.getIndividualWidths()).length; float wordSpacing = position.getWidthOfSpace(); if (wordSpacing == 0.0F || Float.isNaN(wordSpacing)) { deltaSpace = Float.MAX_VALUE; } else if (lastWordSpacing < 0.0F) { deltaSpace = wordSpacing * getSpacingTolerance(); } else { deltaSpace = (wordSpacing + lastWordSpacing) / 2.0F * getSpacingTolerance(); }  if (previousAveCharWidth < 0.0F) { averageCharWidth = positionWidth / wordCharCount; } else { averageCharWidth = (previousAveCharWidth + positionWidth / wordCharCount) / 2.0F; }  float deltaCharWidth = averageCharWidth * getAverageCharTolerance(); float expectedStartOfNextWordX = -3.4028235E38F; if (endOfLastTextX != -1.0F) expectedStartOfNextWordX = endOfLastTextX + Math.min(deltaSpace, deltaCharWidth);  if (lastPosition != null) { if (startOfArticle) { lastPosition.setArticleStart(); startOfArticle = false; }  if (!overlap(positionY, positionHeight, maxYForLine, maxHeightForLine)) { writeLine(normalize(line)); line.clear(); lastLineStartPosition = handleLineSeparation(current, lastPosition, lastLineStartPosition, maxHeightForLine); expectedStartOfNextWordX = -3.4028235E38F; maxYForLine = -3.4028235E38F; maxHeightForLine = -1.0F; minYTopForLine = Float.MAX_VALUE; }  if (expectedStartOfNextWordX != -3.4028235E38F && expectedStartOfNextWordX < positionX && lastPosition.getTextPosition().getUnicode() != null && !lastPosition.getTextPosition().getUnicode().endsWith(" ")) line.add(LineItem.getWordSeparator());  }  if (positionY >= maxYForLine) maxYForLine = positionY;  endOfLastTextX = positionX + positionWidth; if (characterValue != null) { if (startOfPage && lastPosition == null) writeParagraphStart();  line.add(new LineItem(position)); }  maxHeightForLine = Math.max(maxHeightForLine, positionHeight); minYTopForLine = Math.min(minYTopForLine, positionY - positionHeight); lastPosition = current; if (startOfPage) { lastPosition.setParagraphStart(); lastPosition.setLineStart(); lastLineStartPosition = lastPosition; startOfPage = false; }  lastWordSpacing = wordSpacing; previousAveCharWidth = averageCharWidth; }  if (line.size() > 0) { writeLine(normalize(line)); writeParagraphEnd(); }  endArticle(); }  writePageEnd(); }
/*      */   private boolean overlap(float y1, float height1, float y2, float height2) { return (within(y1, y2, 0.1F) || (y2 <= y1 && y2 >= y1 - height1) || (y1 <= y2 && y1 >= y2 - height2)); }
/*      */   protected void writeLineSeparator() throws IOException { this.output.write(getLineSeparator()); }
/*      */   protected void writeWordSeparator() throws IOException { this.output.write(getWordSeparator()); }
/*      */   protected void writeCharacters(TextPosition text) throws IOException { this.output.write(text.getUnicode()); }
/*      */   protected void writeString(String text, List<TextPosition> textPositions) throws IOException { writeString(text); }
/*      */   protected void writeString(String text) throws IOException { this.output.write(text); }
/*      */   private boolean within(float first, float second, float variance) { return (second < first + variance && second > first - variance); }
/* 1881 */   private static void parseBidiFile(InputStream inputStream) throws IOException { LineNumberReader rd = new LineNumberReader(new InputStreamReader(inputStream));
/*      */ 
/*      */     
/*      */     while (true)
/* 1885 */     { String s = rd.readLine();
/* 1886 */       if (s == null) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/* 1891 */       int comment = s.indexOf('#');
/* 1892 */       if (comment != -1)
/*      */       {
/* 1894 */         s = s.substring(0, comment);
/*      */       }
/*      */       
/* 1897 */       if (s.length() < 2) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */       
/* 1902 */       StringTokenizer st = new StringTokenizer(s, ";");
/* 1903 */       int nFields = st.countTokens();
/* 1904 */       Character[] fields = new Character[nFields];
/* 1905 */       for (int i = 0; i < nFields; i++)
/*      */       {
/* 1907 */         fields[i] = Character.valueOf((char)Integer.parseInt(st.nextToken().trim(), 16));
/*      */       }
/*      */       
/* 1910 */       if (fields.length == 2)
/*      */       {
/*      */         
/* 1913 */         MIRRORING_CHAR_MAP.put(fields[0], fields[1]); }  }  }
/*      */   protected void processTextPosition(TextPosition text) { boolean showCharacter = true; if (this.suppressDuplicateOverlappingText) { showCharacter = false; String textCharacter = text.getUnicode(); float textX = text.getX(); float textY = text.getY(); TreeMap<Float, TreeSet<Float>> sameTextCharacters = this.characterListMapping.get(textCharacter); if (sameTextCharacters == null) { sameTextCharacters = new TreeMap<Float, TreeSet<Float>>(); this.characterListMapping.put(textCharacter, sameTextCharacters); }  boolean suppressCharacter = false; float tolerance = text.getWidth() / textCharacter.length() / 3.0F; SortedMap<Float, TreeSet<Float>> xMatches = sameTextCharacters.subMap(Float.valueOf(textX - tolerance), Float.valueOf(textX + tolerance)); for (TreeSet<Float> xMatch : xMatches.values()) { SortedSet<Float> yMatches = xMatch.subSet(Float.valueOf(textY - tolerance), Float.valueOf(textY + tolerance)); if (!yMatches.isEmpty()) { suppressCharacter = true; break; }  }  if (!suppressCharacter) { TreeSet<Float> ySet = sameTextCharacters.get(Float.valueOf(textX)); if (ySet == null) { ySet = new TreeSet<Float>(); sameTextCharacters.put(Float.valueOf(textX), ySet); }  ySet.add(Float.valueOf(textY)); showCharacter = true; }  }  if (showCharacter) { int articleDivisionIndex, foundArticleDivisionIndex = -1; int notFoundButFirstLeftAndAboveArticleDivisionIndex = -1; int notFoundButFirstLeftArticleDivisionIndex = -1; int notFoundButFirstAboveArticleDivisionIndex = -1; float x = text.getX(); float y = text.getY(); if (this.shouldSeparateByBeads) { for (int i = 0; i < this.beadRectangles.size() && foundArticleDivisionIndex == -1; i++) { PDRectangle rect = this.beadRectangles.get(i); if (rect != null) { if (rect.contains(x, y)) { foundArticleDivisionIndex = i * 2 + 1; } else if ((x < rect.getLowerLeftX() || y < rect.getUpperRightY()) && notFoundButFirstLeftAndAboveArticleDivisionIndex == -1) { notFoundButFirstLeftAndAboveArticleDivisionIndex = i * 2; } else if (x < rect.getLowerLeftX() && notFoundButFirstLeftArticleDivisionIndex == -1) { notFoundButFirstLeftArticleDivisionIndex = i * 2; } else if (y < rect.getUpperRightY() && notFoundButFirstAboveArticleDivisionIndex == -1) { notFoundButFirstAboveArticleDivisionIndex = i * 2; }  } else { foundArticleDivisionIndex = 0; }  }  } else { foundArticleDivisionIndex = 0; }  if (foundArticleDivisionIndex != -1) { articleDivisionIndex = foundArticleDivisionIndex; } else if (notFoundButFirstLeftAndAboveArticleDivisionIndex != -1) { articleDivisionIndex = notFoundButFirstLeftAndAboveArticleDivisionIndex; } else if (notFoundButFirstLeftArticleDivisionIndex != -1) { articleDivisionIndex = notFoundButFirstLeftArticleDivisionIndex; } else if (notFoundButFirstAboveArticleDivisionIndex != -1) { articleDivisionIndex = notFoundButFirstAboveArticleDivisionIndex; } else { articleDivisionIndex = this.charactersByArticle.size() - 1; }  List<TextPosition> textList = this.charactersByArticle.get(articleDivisionIndex); if (textList.isEmpty()) { textList.add(text); } else { TextPosition previousTextPosition = textList.get(textList.size() - 1); if (text.isDiacritic() && previousTextPosition.contains(text)) { previousTextPosition.mergeDiacritic(text); } else if (previousTextPosition.isDiacritic() && text.contains(previousTextPosition)) { text.mergeDiacritic(previousTextPosition); textList.remove(textList.size() - 1); textList.add(text); } else { textList.add(text); }  }  }  }
/*      */   public int getStartPage() { return this.startPage; }
/*      */   public void setStartPage(int startPageValue) { this.startPage = startPageValue; }
/*      */   public int getEndPage() { return this.endPage; }
/*      */   public void setEndPage(int endPageValue) { this.endPage = endPageValue; }
/*      */   public void setLineSeparator(String separator) { this.lineSeparator = separator; }
/*      */   public String getLineSeparator() { return this.lineSeparator; }
/*      */   public String getWordSeparator() { return this.wordSeparator; }
/*      */   public void setWordSeparator(String separator) { this.wordSeparator = separator; }
/*      */   public boolean getSuppressDuplicateOverlappingText() { return this.suppressDuplicateOverlappingText; }
/* 1924 */   protected int getCurrentPageNo() { return this.currentPageNo; } protected Writer getOutput() { return this.output; } protected List<List<TextPosition>> getCharactersByArticle() { return this.charactersByArticle; } public void setSuppressDuplicateOverlappingText(boolean suppressDuplicateOverlappingTextValue) { this.suppressDuplicateOverlappingText = suppressDuplicateOverlappingTextValue; } public boolean getSeparateByBeads() { return this.shouldSeparateByBeads; } private WordWithTextPositions createWord(String word, List<TextPosition> wordPositions) { return new WordWithTextPositions(normalizeWord(word), wordPositions); }
/*      */   public void setShouldSeparateByBeads(boolean aShouldSeparateByBeads) { this.shouldSeparateByBeads = aShouldSeparateByBeads; }
/*      */   public PDOutlineItem getEndBookmark() { return this.endBookmark; }
/*      */   public void setEndBookmark(PDOutlineItem aEndBookmark) { this.endBookmark = aEndBookmark; }
/*      */   public PDOutlineItem getStartBookmark() { return this.startBookmark; }
/*      */   public void setStartBookmark(PDOutlineItem aStartBookmark) { this.startBookmark = aStartBookmark; }
/*      */   public boolean getAddMoreFormatting() { return this.addMoreFormatting; }
/*      */   public void setAddMoreFormatting(boolean newAddMoreFormatting) { this.addMoreFormatting = newAddMoreFormatting; }
/*      */   public boolean getSortByPosition() { return this.sortByPosition; }
/*      */   public void setSortByPosition(boolean newSortByPosition) { this.sortByPosition = newSortByPosition; }
/*      */   public float getSpacingTolerance() { return this.spacingTolerance; }
/*      */   public void setSpacingTolerance(float spacingToleranceValue) { this.spacingTolerance = spacingToleranceValue; }
/* 1936 */   public float getAverageCharTolerance() { return this.averageCharTolerance; } public void setAverageCharTolerance(float averageCharToleranceValue) { this.averageCharTolerance = averageCharToleranceValue; } public float getIndentThreshold() { return this.indentThreshold; } public void setIndentThreshold(float indentThresholdValue) { this.indentThreshold = indentThresholdValue; } public float getDropThreshold() { return this.dropThreshold; } public void setDropThreshold(float dropThresholdValue) { this.dropThreshold = dropThresholdValue; } public String getParagraphStart() { return this.paragraphStart; } public void setParagraphStart(String s) { this.paragraphStart = s; } public String getParagraphEnd() { return this.paragraphEnd; } public void setParagraphEnd(String s) { this.paragraphEnd = s; } public String getPageStart() { return this.pageStart; } public void setPageStart(String pageStartValue) { this.pageStart = pageStartValue; } public String getPageEnd() { return this.pageEnd; } public void setPageEnd(String pageEndValue) { this.pageEnd = pageEndValue; } public String getArticleStart() { return this.articleStart; } public void setArticleStart(String articleStartValue) { this.articleStart = articleStartValue; } public String getArticleEnd() { return this.articleEnd; } public void setArticleEnd(String articleEndValue) { this.articleEnd = articleEndValue; } private PositionWrapper handleLineSeparation(PositionWrapper current, PositionWrapper lastPosition, PositionWrapper lastLineStartPosition, float maxHeightForLine) throws IOException { current.setLineStart(); isParagraphSeparation(current, lastPosition, lastLineStartPosition, maxHeightForLine); lastLineStartPosition = current; if (current.isParagraphStart()) { if (lastPosition.isArticleStart()) { if (lastPosition.isLineStart()) writeLineSeparator();  writeParagraphStart(); } else { writeLineSeparator(); writeParagraphSeparator(); }  } else { writeLineSeparator(); }  return lastLineStartPosition; } private String normalizeWord(String word) { StringBuilder builder = null;
/* 1937 */     int p = 0;
/* 1938 */     int q = 0;
/* 1939 */     int strLength = word.length();
/* 1940 */     for (; q < strLength; q++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1947 */       char c = word.charAt(q);
/* 1948 */       if (('ﬀ' <= c && c <= '﷿') || ('ﹰ' <= c && c <= '﻿')) {
/*      */         
/* 1950 */         if (builder == null)
/*      */         {
/* 1952 */           builder = new StringBuilder(strLength * 2);
/*      */         }
/* 1954 */         builder.append(word.substring(p, q));
/*      */ 
/*      */ 
/*      */         
/* 1958 */         if (c == 'ﷲ' && q > 0 && (word
/* 1959 */           .charAt(q - 1) == 'ا' || word.charAt(q - 1) == 'ﺍ')) {
/*      */           
/* 1961 */           builder.append("لله");
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1966 */           builder.append(
/* 1967 */               Normalizer.normalize(word.substring(q, q + 1), Normalizer.Form.NFKC).trim());
/*      */         } 
/* 1969 */         p = q + 1;
/*      */       } 
/*      */     } 
/* 1972 */     if (builder == null)
/*      */     {
/* 1974 */       return handleDirection(word);
/*      */     }
/*      */ 
/*      */     
/* 1978 */     builder.append(word.substring(p, q));
/* 1979 */     return handleDirection(builder.toString()); } private void isParagraphSeparation(PositionWrapper position, PositionWrapper lastPosition, PositionWrapper lastLineStartPosition, float maxHeightForLine) { boolean result = false; if (lastLineStartPosition == null) { result = true; } else { float yGap = Math.abs(position.getTextPosition().getYDirAdj() - lastPosition.getTextPosition().getYDirAdj()); float newYVal = multiplyFloat(getDropThreshold(), maxHeightForLine); float xGap = position.getTextPosition().getXDirAdj() - lastLineStartPosition.getTextPosition().getXDirAdj(); float newXVal = multiplyFloat(getIndentThreshold(), position.getTextPosition().getWidthOfSpace()); float positionWidth = multiplyFloat(0.25F, position.getTextPosition().getWidth()); if (yGap > newYVal) { result = true; } else if (xGap > newXVal) { if (!lastLineStartPosition.isParagraphStart()) { result = true; } else { position.setHangingIndent(); }  } else if (xGap < -position.getTextPosition().getWidthOfSpace()) { if (!lastLineStartPosition.isParagraphStart()) result = true;  } else if (Math.abs(xGap) < positionWidth) { if (lastLineStartPosition.isHangingIndent()) { position.setHangingIndent(); } else if (lastLineStartPosition.isParagraphStart()) { Pattern liPattern = matchListItemPattern(lastLineStartPosition); if (liPattern != null) { Pattern currentPattern = matchListItemPattern(position); if (liPattern == currentPattern) result = true;  }  }  }  }  if (result) position.setParagraphStart();  } private float multiplyFloat(float value1, float value2) { return Math.round(value1 * value2 * 1000.0F) / 1000.0F; } protected void writeParagraphSeparator() throws IOException { writeParagraphEnd(); writeParagraphStart(); } protected void writeParagraphStart() throws IOException { if (this.inParagraph) { writeParagraphEnd(); this.inParagraph = false; }  this.output.write(getParagraphStart()); this.inParagraph = true; }
/*      */   protected void writeParagraphEnd() throws IOException { if (!this.inParagraph) writeParagraphStart();  this.output.write(getParagraphEnd()); this.inParagraph = false; }
/*      */   protected void writePageStart() throws IOException { this.output.write(getPageStart()); }
/*      */   protected void writePageEnd() throws IOException { this.output.write(getPageEnd()); }
/*      */   private Pattern matchListItemPattern(PositionWrapper pw) { TextPosition tp = pw.getTextPosition(); String txt = tp.getUnicode(); return matchPattern(txt, getListItemPatterns()); }
/*      */   public PDFTextStripper() throws IOException { this.listOfPatterns = null; }
/*      */   protected void setListItemPatterns(List<Pattern> patterns) { this.listOfPatterns = patterns; }
/*      */   protected List<Pattern> getListItemPatterns() { if (this.listOfPatterns == null) { this.listOfPatterns = new ArrayList<Pattern>(); for (String expression : LIST_ITEM_EXPRESSIONS) { Pattern p = Pattern.compile(expression); this.listOfPatterns.add(p); }  }  return this.listOfPatterns; }
/*      */   protected static Pattern matchPattern(String string, List<Pattern> patterns) { for (Pattern p : patterns) { if (p.matcher(string).matches()) return p;  }  return null; }
/*      */   private void writeLine(List<WordWithTextPositions> line) throws IOException { int numberOfStrings = line.size(); for (int i = 0; i < numberOfStrings; i++) { WordWithTextPositions word = line.get(i); writeString(word.getText(), word.getTextPositions()); if (i < numberOfStrings - 1) writeWordSeparator();  }  }
/*      */   private List<WordWithTextPositions> normalize(List<LineItem> line) { List<WordWithTextPositions> normalized = new LinkedList<WordWithTextPositions>(); StringBuilder lineBuilder = new StringBuilder(); List<TextPosition> wordPositions = new ArrayList<TextPosition>(); for (LineItem item : line) lineBuilder = normalizeAdd(normalized, lineBuilder, wordPositions, item);  if (lineBuilder.length() > 0) normalized.add(createWord(lineBuilder.toString(), wordPositions));  return normalized; }
/*      */   private String handleDirection(String word) { Bidi bidi = new Bidi(word, -2); if (!bidi.isMixed() && bidi.getBaseLevel() == 0) return word;  int runCount = bidi.getRunCount(); byte[] levels = new byte[runCount]; Integer[] runs = new Integer[runCount]; for (int i = 0; i < runCount; i++) { levels[i] = (byte)bidi.getRunLevel(i); runs[i] = Integer.valueOf(i); }  Bidi.reorderVisually(levels, 0, (Object[])runs, 0, runCount); StringBuilder result = new StringBuilder(); for (int j = 0; j < runCount; j++) { int index = runs[j].intValue(); int start = bidi.getRunStart(index); int end = bidi.getRunLimit(index); int level = levels[index]; if ((level & 0x1) != 0) { while (--end >= start) { char character = word.charAt(end); if (Character.isMirrored(word.codePointAt(end))) { if (MIRRORING_CHAR_MAP.containsKey(Character.valueOf(character))) { result.append(MIRRORING_CHAR_MAP.get(Character.valueOf(character))); continue; }  result.append(character); continue; }  result.append(character); }  } else { result.append(word, start, end); }  }  return result.toString(); }
/* 1991 */   private StringBuilder normalizeAdd(List<WordWithTextPositions> normalized, StringBuilder lineBuilder, List<TextPosition> wordPositions, LineItem item) { if (item.isWordSeparator()) {
/*      */       
/* 1993 */       normalized.add(
/* 1994 */           createWord(lineBuilder.toString(), new ArrayList<TextPosition>(wordPositions)));
/* 1995 */       lineBuilder = new StringBuilder();
/* 1996 */       wordPositions.clear();
/*      */     }
/*      */     else {
/*      */       
/* 2000 */       TextPosition text = item.getTextPosition();
/* 2001 */       lineBuilder.append(text.getUnicode());
/* 2002 */       wordPositions.add(text);
/*      */     } 
/* 2004 */     return lineBuilder; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class LineItem
/*      */   {
/* 2012 */     public static LineItem WORD_SEPARATOR = new LineItem();
/*      */     private final TextPosition textPosition;
/*      */     
/*      */     public static LineItem getWordSeparator() {
/* 2016 */       return WORD_SEPARATOR;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private LineItem() {
/* 2023 */       this.textPosition = null;
/*      */     }
/*      */ 
/*      */     
/*      */     LineItem(TextPosition textPosition) {
/* 2028 */       this.textPosition = textPosition;
/*      */     }
/*      */ 
/*      */     
/*      */     public TextPosition getTextPosition() {
/* 2033 */       return this.textPosition;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isWordSeparator() {
/* 2038 */       return (this.textPosition == null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class WordWithTextPositions
/*      */   {
/*      */     String text;
/*      */ 
/*      */     
/*      */     List<TextPosition> textPositions;
/*      */ 
/*      */ 
/*      */     
/*      */     WordWithTextPositions(String word, List<TextPosition> positions) {
/* 2055 */       this.text = word;
/* 2056 */       this.textPositions = positions;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getText() {
/* 2061 */       return this.text;
/*      */     }
/*      */ 
/*      */     
/*      */     public List<TextPosition> getTextPositions() {
/* 2066 */       return this.textPositions;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static final class PositionWrapper
/*      */   {
/*      */     private boolean isLineStart = false;
/*      */ 
/*      */     
/*      */     private boolean isParagraphStart = false;
/*      */ 
/*      */     
/*      */     private boolean isPageBreak = false;
/*      */ 
/*      */     
/*      */     private boolean isHangingIndent = false;
/*      */ 
/*      */     
/*      */     private boolean isArticleStart = false;
/*      */     
/* 2088 */     private TextPosition position = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     PositionWrapper(TextPosition position) {
/* 2097 */       this.position = position;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TextPosition getTextPosition() {
/* 2107 */       return this.position;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isLineStart() {
/* 2112 */       return this.isLineStart;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setLineStart() {
/* 2120 */       this.isLineStart = true;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isParagraphStart() {
/* 2125 */       return this.isParagraphStart;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setParagraphStart() {
/* 2133 */       this.isParagraphStart = true;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isArticleStart() {
/* 2138 */       return this.isArticleStart;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setArticleStart() {
/* 2146 */       this.isArticleStart = true;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isPageBreak() {
/* 2151 */       return this.isPageBreak;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setPageBreak() {
/* 2159 */       this.isPageBreak = true;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isHangingIndent() {
/* 2164 */       return this.isHangingIndent;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setHangingIndent() {
/* 2172 */       this.isHangingIndent = true;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/text/PDFTextStripper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */