/*      */ package org.apache.batik.apps.rasterizer;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.File;
/*      */ import java.io.FileFilter;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.apache.batik.transcoder.Transcoder;
/*      */ import org.apache.batik.transcoder.TranscoderInput;
/*      */ import org.apache.batik.transcoder.TranscoderOutput;
/*      */ import org.apache.batik.transcoder.image.ImageTranscoder;
/*      */ import org.apache.batik.transcoder.image.JPEGTranscoder;
/*      */ import org.apache.batik.transcoder.image.PNGTranscoder;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SVGConverter
/*      */ {
/*      */   public static final String ERROR_NO_SOURCES_SPECIFIED = "SVGConverter.error.no.sources.specified";
/*      */   public static final String ERROR_CANNOT_COMPUTE_DESTINATION = "SVGConverter.error.cannot.compute.destination";
/*      */   public static final String ERROR_CANNOT_USE_DST_FILE = "SVGConverter.error.cannot.use.dst.file";
/*      */   public static final String ERROR_CANNOT_ACCESS_TRANSCODER = "SVGConverter.error.cannot.access.transcoder";
/*      */   public static final String ERROR_SOURCE_SAME_AS_DESTINATION = "SVGConverter.error.source.same.as.destination";
/*      */   public static final String ERROR_CANNOT_READ_SOURCE = "SVGConverter.error.cannot.read.source";
/*      */   public static final String ERROR_CANNOT_OPEN_SOURCE = "SVGConverter.error.cannot.open.source";
/*      */   public static final String ERROR_OUTPUT_NOT_WRITEABLE = "SVGConverter.error.output.not.writeable";
/*      */   public static final String ERROR_CANNOT_OPEN_OUTPUT_FILE = "SVGConverter.error.cannot.open.output.file";
/*      */   public static final String ERROR_UNABLE_TO_CREATE_OUTPUT_DIR = "SVGConverter.error.unable.to.create.output.dir";
/*      */   public static final String ERROR_WHILE_RASTERIZING_FILE = "SVGConverter.error.while.rasterizing.file";
/*      */   protected static final String SVG_EXTENSION = ".svg";
/*      */   protected static final float DEFAULT_QUALITY = -1.0F;
/*      */   protected static final float MAXIMUM_QUALITY = 0.99F;
/*  198 */   protected static final DestinationType DEFAULT_RESULT_TYPE = DestinationType.PNG;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static final float DEFAULT_WIDTH = -1.0F;
/*      */ 
/*      */   
/*      */   protected static final float DEFAULT_HEIGHT = -1.0F;
/*      */ 
/*      */   
/*  208 */   protected DestinationType destinationType = DEFAULT_RESULT_TYPE;
/*      */ 
/*      */   
/*  211 */   protected float height = -1.0F;
/*      */ 
/*      */   
/*  214 */   protected float width = -1.0F;
/*      */ 
/*      */   
/*  217 */   protected float maxHeight = -1.0F;
/*      */ 
/*      */   
/*  220 */   protected float maxWidth = -1.0F;
/*      */ 
/*      */   
/*  223 */   protected float quality = -1.0F;
/*      */ 
/*      */   
/*  226 */   protected int indexed = -1;
/*      */ 
/*      */   
/*  229 */   protected Rectangle2D area = null;
/*      */ 
/*      */   
/*  232 */   protected String language = null;
/*      */ 
/*      */   
/*  235 */   protected String userStylesheet = null;
/*      */ 
/*      */   
/*  238 */   protected float pixelUnitToMillimeter = -1.0F;
/*      */ 
/*      */   
/*      */   protected boolean validate = false;
/*      */ 
/*      */   
/*      */   protected boolean executeOnload = false;
/*      */ 
/*      */   
/*  247 */   protected float snapshotTime = Float.NaN;
/*      */ 
/*      */   
/*  250 */   protected String allowedScriptTypes = null;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean constrainScriptOrigin = true;
/*      */ 
/*      */   
/*      */   protected boolean securityOff = false;
/*      */ 
/*      */   
/*  260 */   protected List sources = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected File dst;
/*      */ 
/*      */ 
/*      */   
/*  269 */   protected Color backgroundColor = null;
/*      */ 
/*      */   
/*  272 */   protected String mediaType = null;
/*      */ 
/*      */   
/*  275 */   protected String defaultFontFamily = null;
/*      */ 
/*      */   
/*  278 */   protected String alternateStylesheet = null;
/*      */ 
/*      */   
/*  281 */   protected List files = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected SVGConverterController controller;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGConverter() {
/*  294 */     this(new DefaultSVGConverterController());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGConverter(SVGConverterController controller) {
/*  301 */     if (controller == null) {
/*  302 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  305 */     this.controller = controller;
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
/*      */   public void setDestinationType(DestinationType destinationType) {
/*  317 */     if (destinationType == null) {
/*  318 */       throw new IllegalArgumentException();
/*      */     }
/*  320 */     this.destinationType = destinationType;
/*      */   }
/*      */   
/*      */   public DestinationType getDestinationType() {
/*  324 */     return this.destinationType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHeight(float height) {
/*  333 */     this.height = height;
/*      */   }
/*      */   
/*      */   public float getHeight() {
/*  337 */     return this.height;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWidth(float width) {
/*  346 */     this.width = width;
/*      */   }
/*      */   
/*      */   public float getWidth() {
/*  350 */     return this.width;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxHeight(float height) {
/*  359 */     this.maxHeight = height;
/*      */   }
/*      */   
/*      */   public float getMaxHeight() {
/*  363 */     return this.maxHeight;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxWidth(float width) {
/*  372 */     this.maxWidth = width;
/*      */   }
/*      */   
/*      */   public float getMaxWidth() {
/*  376 */     return this.maxWidth;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQuality(float quality) throws IllegalArgumentException {
/*  385 */     if (quality >= 1.0F) {
/*  386 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/*  389 */     this.quality = quality;
/*      */   }
/*      */   
/*      */   public float getQuality() {
/*  393 */     return this.quality;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIndexed(int bits) throws IllegalArgumentException {
/*  401 */     this.indexed = bits;
/*      */   }
/*      */   
/*      */   public int getIndexed() {
/*  405 */     return this.indexed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLanguage(String language) {
/*  414 */     this.language = language;
/*      */   }
/*      */   
/*      */   public String getLanguage() {
/*  418 */     return this.language;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserStylesheet(String userStylesheet) {
/*  425 */     this.userStylesheet = userStylesheet;
/*      */   }
/*      */   
/*      */   public String getUserStylesheet() {
/*  429 */     return this.userStylesheet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPixelUnitToMillimeter(float pixelUnitToMillimeter) {
/*  439 */     this.pixelUnitToMillimeter = pixelUnitToMillimeter;
/*      */   }
/*      */   
/*      */   public float getPixelUnitToMillimeter() {
/*  443 */     return this.pixelUnitToMillimeter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setArea(Rectangle2D area) {
/*  453 */     this.area = area;
/*      */   }
/*      */   
/*      */   public Rectangle2D getArea() {
/*  457 */     return this.area;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSources(String[] sources) {
/*  468 */     if (sources == null) {
/*  469 */       this.sources = null;
/*      */     } else {
/*      */       
/*  472 */       this.sources = new ArrayList();
/*  473 */       for (String source : sources) {
/*  474 */         if (source != null) {
/*  475 */           this.sources.add(source);
/*      */         }
/*      */       } 
/*      */       
/*  479 */       if (this.sources.size() == 0) {
/*  480 */         this.sources = null;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public List getSources() {
/*  486 */     return this.sources;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDst(File dst) {
/*  494 */     this.dst = dst;
/*      */   }
/*      */   
/*      */   public File getDst() {
/*  498 */     return this.dst;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackgroundColor(Color backgroundColor) {
/*  507 */     this.backgroundColor = backgroundColor;
/*      */   }
/*      */   
/*      */   public Color getBackgroundColor() {
/*  511 */     return this.backgroundColor;
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
/*      */   public void setMediaType(String mediaType) {
/*  523 */     this.mediaType = mediaType;
/*      */   }
/*      */   
/*      */   public String getMediaType() {
/*  527 */     return this.mediaType;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDefaultFontFamily(String defaultFontFamily) {
/*  536 */     this.defaultFontFamily = defaultFontFamily;
/*      */   }
/*      */   
/*      */   public String getDefaultFontFamily() {
/*  540 */     return this.defaultFontFamily;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAlternateStylesheet(String alternateStylesheet) {
/*  550 */     this.alternateStylesheet = alternateStylesheet;
/*      */   }
/*      */   
/*      */   public String getAlternateStylesheet() {
/*  554 */     return this.alternateStylesheet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setValidate(boolean validate) {
/*  562 */     this.validate = validate;
/*      */   }
/*      */   
/*      */   public boolean getValidate() {
/*  566 */     return this.validate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExecuteOnload(boolean b) {
/*  576 */     this.executeOnload = b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getExecuteOnload() {
/*  585 */     return this.executeOnload;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSnapshotTime(float t) {
/*  594 */     this.snapshotTime = t;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSnapshotTime() {
/*  601 */     return this.snapshotTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllowedScriptTypes(String allowedScriptTypes) {
/*  610 */     this.allowedScriptTypes = allowedScriptTypes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getAllowedScriptTypes() {
/*  619 */     return this.allowedScriptTypes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConstrainScriptOrigin(boolean constrainScriptOrigin) {
/*  627 */     this.constrainScriptOrigin = constrainScriptOrigin;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getConstrainScriptOrigin() {
/*  635 */     return this.constrainScriptOrigin;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSecurityOff(boolean securityOff) {
/*  642 */     this.securityOff = securityOff;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getSecurityOff() {
/*  649 */     return this.securityOff;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isFile(File f) {
/*  658 */     if (f.exists()) {
/*  659 */       return f.isFile();
/*      */     }
/*  661 */     if (f.toString().toLowerCase().endsWith(this.destinationType.getExtension())) {
/*  662 */       return true;
/*      */     }
/*      */ 
/*      */     
/*  666 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void execute() throws SVGConverterException {
/*  677 */     List<SVGConverterSource> sources = computeSources();
/*      */ 
/*      */     
/*  680 */     List<File> dstFiles = null;
/*  681 */     if (sources.size() == 1 && this.dst != null && isFile(this.dst)) {
/*  682 */       dstFiles = new ArrayList();
/*  683 */       dstFiles.add(this.dst);
/*      */     } else {
/*      */       
/*  686 */       dstFiles = computeDstFiles(sources);
/*      */     } 
/*      */ 
/*      */     
/*  690 */     Transcoder transcoder = this.destinationType.getTranscoder();
/*  691 */     if (transcoder == null) {
/*  692 */       throw new SVGConverterException("SVGConverter.error.cannot.access.transcoder", new Object[] { this.destinationType.toString() }, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  698 */     Map hints = computeTranscodingHints();
/*  699 */     transcoder.setTranscodingHints(hints);
/*      */ 
/*      */     
/*  702 */     if (!this.controller.proceedWithComputedTask(transcoder, hints, sources, dstFiles)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  710 */     for (int i = 0; i < sources.size(); i++) {
/*      */       
/*  712 */       SVGConverterSource currentFile = sources.get(i);
/*      */       
/*  714 */       File outputFile = dstFiles.get(i);
/*      */       
/*  716 */       createOutputDir(outputFile);
/*  717 */       transcode(currentFile, outputFile, transcoder);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List computeDstFiles(List sources) throws SVGConverterException {
/*  728 */     List<File> dstFiles = new ArrayList();
/*  729 */     if (this.dst != null) {
/*  730 */       if (this.dst.exists() && this.dst.isFile()) {
/*  731 */         throw new SVGConverterException("SVGConverter.error.cannot.use.dst.file");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  738 */       int n = sources.size();
/*  739 */       for (Object source : sources) {
/*  740 */         SVGConverterSource src = (SVGConverterSource)source;
/*      */         
/*  742 */         File outputName = new File(this.dst.getPath(), getDestinationFile(src.getName()));
/*      */         
/*  744 */         dstFiles.add(outputName);
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  753 */       int n = sources.size();
/*  754 */       for (Object source : sources) {
/*  755 */         SVGConverterSource src = (SVGConverterSource)source;
/*  756 */         if (!(src instanceof SVGConverterFileSource)) {
/*  757 */           throw new SVGConverterException("SVGConverter.error.cannot.compute.destination", new Object[] { src });
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  762 */         SVGConverterFileSource fs = (SVGConverterFileSource)src;
/*  763 */         File outputName = new File(fs.getFile().getParent(), getDestinationFile(src.getName()));
/*      */         
/*  765 */         dstFiles.add(outputName);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  770 */     return dstFiles;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected List computeSources() throws SVGConverterException {
/*  779 */     List<SVGConverterFileSource> sources = new ArrayList();
/*      */ 
/*      */     
/*  782 */     if (this.sources == null) {
/*  783 */       throw new SVGConverterException("SVGConverter.error.no.sources.specified");
/*      */     }
/*      */     
/*  786 */     int n = this.sources.size();
/*  787 */     for (Object source : this.sources) {
/*  788 */       String sourceString = (String)source;
/*  789 */       File file = new File(sourceString);
/*  790 */       if (file.exists()) {
/*  791 */         sources.add(new SVGConverterFileSource(file)); continue;
/*      */       } 
/*  793 */       String[] fileNRef = getFileNRef(sourceString);
/*  794 */       file = new File(fileNRef[0]);
/*  795 */       if (file.exists()) {
/*  796 */         sources.add(new SVGConverterFileSource(file, fileNRef[1])); continue;
/*      */       } 
/*  798 */       sources.add(new SVGConverterURLSource(sourceString));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  803 */     return sources;
/*      */   }
/*      */   
/*      */   public String[] getFileNRef(String fileName) {
/*  807 */     int n = fileName.lastIndexOf('#');
/*  808 */     String[] result = { fileName, "" };
/*  809 */     if (n > -1) {
/*  810 */       result[0] = fileName.substring(0, n);
/*  811 */       if (n + 1 < fileName.length()) {
/*  812 */         result[1] = fileName.substring(n + 1);
/*      */       }
/*      */     } 
/*      */     
/*  816 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Map computeTranscodingHints() {
/*  827 */     Map<Object, Object> map = new HashMap<Object, Object>();
/*      */ 
/*      */     
/*  830 */     if (this.area != null) {
/*  831 */       map.put(ImageTranscoder.KEY_AOI, this.area);
/*      */     }
/*      */ 
/*      */     
/*  835 */     if (this.quality > 0.0F) {
/*  836 */       map.put(JPEGTranscoder.KEY_QUALITY, Float.valueOf(this.quality));
/*      */     }
/*      */ 
/*      */     
/*  840 */     if (this.indexed != -1) {
/*  841 */       map.put(PNGTranscoder.KEY_INDEXED, Integer.valueOf(this.indexed));
/*      */     }
/*      */ 
/*      */     
/*  845 */     if (this.backgroundColor != null) {
/*  846 */       map.put(ImageTranscoder.KEY_BACKGROUND_COLOR, this.backgroundColor);
/*      */     }
/*      */ 
/*      */     
/*  850 */     if (this.height > 0.0F) {
/*  851 */       map.put(ImageTranscoder.KEY_HEIGHT, Float.valueOf(this.height));
/*      */     }
/*  853 */     if (this.width > 0.0F) {
/*  854 */       map.put(ImageTranscoder.KEY_WIDTH, Float.valueOf(this.width));
/*      */     }
/*      */ 
/*      */     
/*  858 */     if (this.maxHeight > 0.0F) {
/*  859 */       map.put(ImageTranscoder.KEY_MAX_HEIGHT, Float.valueOf(this.maxHeight));
/*      */     }
/*  861 */     if (this.maxWidth > 0.0F) {
/*  862 */       map.put(ImageTranscoder.KEY_MAX_WIDTH, Float.valueOf(this.maxWidth));
/*      */     }
/*      */ 
/*      */     
/*  866 */     if (this.mediaType != null) {
/*  867 */       map.put(ImageTranscoder.KEY_MEDIA, this.mediaType);
/*      */     }
/*      */ 
/*      */     
/*  871 */     if (this.defaultFontFamily != null) {
/*  872 */       map.put(ImageTranscoder.KEY_DEFAULT_FONT_FAMILY, this.defaultFontFamily);
/*      */     }
/*      */ 
/*      */     
/*  876 */     if (this.alternateStylesheet != null) {
/*  877 */       map.put(ImageTranscoder.KEY_ALTERNATE_STYLESHEET, this.alternateStylesheet);
/*      */     }
/*      */ 
/*      */     
/*  881 */     if (this.userStylesheet != null) {
/*      */       String str;
/*      */       try {
/*  884 */         URL userDir = (new File(System.getProperty("user.dir"))).toURI().toURL();
/*  885 */         str = (new ParsedURL(userDir, this.userStylesheet)).toString();
/*  886 */       } catch (Exception e) {
/*  887 */         str = this.userStylesheet;
/*      */       } 
/*  889 */       map.put(ImageTranscoder.KEY_USER_STYLESHEET_URI, str);
/*      */     } 
/*      */ 
/*      */     
/*  893 */     if (this.language != null) {
/*  894 */       map.put(ImageTranscoder.KEY_LANGUAGE, this.language);
/*      */     }
/*      */ 
/*      */     
/*  898 */     if (this.pixelUnitToMillimeter > 0.0F) {
/*  899 */       map.put(ImageTranscoder.KEY_PIXEL_UNIT_TO_MILLIMETER, Float.valueOf(this.pixelUnitToMillimeter));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  904 */     if (this.validate) {
/*  905 */       map.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, Boolean.TRUE);
/*      */     }
/*      */ 
/*      */     
/*  909 */     if (this.executeOnload) {
/*  910 */       map.put(ImageTranscoder.KEY_EXECUTE_ONLOAD, Boolean.TRUE);
/*      */     }
/*      */ 
/*      */     
/*  914 */     if (!Float.isNaN(this.snapshotTime)) {
/*  915 */       map.put(ImageTranscoder.KEY_SNAPSHOT_TIME, Float.valueOf(this.snapshotTime));
/*      */     }
/*      */ 
/*      */     
/*  919 */     if (this.allowedScriptTypes != null) {
/*  920 */       map.put(ImageTranscoder.KEY_ALLOWED_SCRIPT_TYPES, this.allowedScriptTypes);
/*      */     }
/*      */ 
/*      */     
/*  924 */     if (!this.constrainScriptOrigin) {
/*  925 */       map.put(ImageTranscoder.KEY_CONSTRAIN_SCRIPT_ORIGIN, Boolean.FALSE);
/*      */     }
/*      */     
/*  928 */     return map;
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
/*      */   protected void transcode(SVGConverterSource inputFile, File outputFile, Transcoder transcoder) throws SVGConverterException {
/*  943 */     TranscoderInput input = null;
/*  944 */     TranscoderOutput output = null;
/*  945 */     OutputStream outputStream = null;
/*      */     
/*  947 */     if (!this.controller.proceedWithSourceTranscoding(inputFile, outputFile)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/*  953 */       if (inputFile.isSameAs(outputFile.getPath())) {
/*  954 */         throw new SVGConverterException("SVGConverter.error.source.same.as.destination", true);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  959 */       if (!inputFile.isReadable()) {
/*  960 */         throw new SVGConverterException("SVGConverter.error.cannot.read.source", new Object[] { inputFile.getName() });
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  965 */         InputStream in = inputFile.openStream();
/*  966 */         in.close();
/*  967 */       } catch (IOException ioe) {
/*  968 */         throw new SVGConverterException("SVGConverter.error.cannot.open.source", new Object[] { inputFile.getName(), ioe.toString() });
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  973 */       input = new TranscoderInput(inputFile.getURI());
/*      */ 
/*      */       
/*  976 */       if (!isWriteable(outputFile)) {
/*  977 */         throw new SVGConverterException("SVGConverter.error.output.not.writeable", new Object[] { outputFile.getName() });
/*      */       }
/*      */       
/*      */       try {
/*  981 */         outputStream = new FileOutputStream(outputFile);
/*  982 */       } catch (FileNotFoundException fnfe) {
/*  983 */         throw new SVGConverterException("SVGConverter.error.cannot.open.output.file", new Object[] { outputFile.getName() });
/*      */       } 
/*      */ 
/*      */       
/*  987 */       output = new TranscoderOutput(outputStream);
/*  988 */     } catch (SVGConverterException e) {
/*  989 */       boolean proceed = this.controller.proceedOnSourceTranscodingFailure(inputFile, outputFile, e.getErrorCode());
/*      */       
/*  991 */       if (proceed) {
/*  992 */         e.printStackTrace();
/*      */         return;
/*      */       } 
/*  995 */       throw e;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1000 */     boolean success = false;
/*      */     try {
/* 1002 */       transcoder.transcode(input, output);
/* 1003 */       success = true;
/* 1004 */     } catch (Exception te) {
/* 1005 */       te.printStackTrace();
/*      */       try {
/* 1007 */         outputStream.flush();
/* 1008 */         outputStream.close();
/* 1009 */       } catch (IOException iOException) {}
/*      */ 
/*      */ 
/*      */       
/* 1013 */       boolean proceed = this.controller.proceedOnSourceTranscodingFailure(inputFile, outputFile, "SVGConverter.error.while.rasterizing.file");
/*      */ 
/*      */       
/* 1016 */       if (!proceed) {
/* 1017 */         throw new SVGConverterException("SVGConverter.error.while.rasterizing.file", new Object[] { outputFile.getName(), te.getMessage() });
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1025 */       outputStream.flush();
/* 1026 */       outputStream.close();
/* 1027 */     } catch (IOException ioe) {
/*      */       return;
/*      */     } 
/*      */     
/* 1031 */     if (success) {
/* 1032 */       this.controller.onSourceTranscodingSuccess(inputFile, outputFile);
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
/*      */   protected String getDestinationFile(String file) {
/* 1052 */     String newSuffix = this.destinationType.getExtension();
/*      */ 
/*      */     
/* 1055 */     String oldName = file;
/*      */     
/* 1057 */     int suffixStart = oldName.lastIndexOf('.');
/* 1058 */     String dest = null;
/* 1059 */     if (suffixStart != -1) {
/*      */       
/* 1061 */       dest = oldName.substring(0, suffixStart) + newSuffix;
/*      */     } else {
/*      */       
/* 1064 */       dest = oldName + newSuffix;
/*      */     } 
/*      */     
/* 1067 */     return dest;
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
/*      */   protected void createOutputDir(File output) throws SVGConverterException {
/* 1081 */     boolean success = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1087 */     String parentDir = output.getParent();
/* 1088 */     if (parentDir != null) {
/* 1089 */       File outputDir = new File(output.getParent());
/* 1090 */       if (!outputDir.exists()) {
/*      */         
/* 1092 */         success = outputDir.mkdirs();
/*      */       }
/* 1094 */       else if (!outputDir.isDirectory()) {
/*      */ 
/*      */         
/* 1097 */         success = outputDir.mkdirs();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1102 */     if (!success) {
/* 1103 */       throw new SVGConverterException("SVGConverter.error.unable.to.create.output.dir");
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
/*      */   protected boolean isWriteable(File file) {
/* 1115 */     if (file.exists()) {
/*      */       
/* 1117 */       if (!file.canWrite()) {
/* 1118 */         return false;
/*      */       }
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/* 1125 */         file.createNewFile();
/* 1126 */       } catch (IOException ioe) {
/* 1127 */         return false;
/*      */       } 
/*      */     } 
/* 1130 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class SVGFileFilter
/*      */     implements FileFilter
/*      */   {
/*      */     public static final String SVG_EXTENSION = ".svg";
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean accept(File file) {
/* 1144 */       if (file != null && file.getName().toLowerCase().endsWith(".svg")) {
/* 1145 */         return true;
/*      */       }
/*      */       
/* 1148 */       return false;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/SVGConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */