/*      */ package org.apache.batik.apps.rasterizer;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.File;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.StringTokenizer;
/*      */ import org.apache.batik.parser.ClockHandler;
/*      */ import org.apache.batik.parser.ClockParser;
/*      */ import org.apache.batik.parser.ParseException;
/*      */ import org.apache.batik.transcoder.Transcoder;
/*      */ import org.apache.batik.util.ApplicationSecurityEnforcer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   implements SVGConverterController
/*      */ {
/*      */   public static final String RASTERIZER_SECURITY_POLICY = "org/apache/batik/apps/rasterizer/resources/rasterizer.policy";
/*      */   
/*      */   public static abstract class AbstractOptionHandler
/*      */     implements OptionHandler
/*      */   {
/*      */     public void handleOption(String[] optionValues, SVGConverter c) {
/*   92 */       int nOptions = (optionValues != null) ? optionValues.length : 0;
/*   93 */       if (nOptions != getOptionValuesLength()) {
/*   94 */         throw new IllegalArgumentException();
/*      */       }
/*      */       
/*   97 */       safeHandleOption(optionValues, c);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract void safeHandleOption(String[] param1ArrayOfString, SVGConverter param1SVGConverter);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class NoValueOptionHandler
/*      */     extends AbstractOptionHandler
/*      */   {
/*      */     public void safeHandleOption(String[] optionValues, SVGConverter c) {
/*  111 */       handleOption(c);
/*      */     }
/*      */     
/*      */     public int getOptionValuesLength() {
/*  115 */       return 0;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract void handleOption(SVGConverter param1SVGConverter);
/*      */   }
/*      */ 
/*      */   
/*      */   public static abstract class SingleValueOptionHandler
/*      */     extends AbstractOptionHandler
/*      */   {
/*      */     public void safeHandleOption(String[] optionValues, SVGConverter c) {
/*  128 */       handleOption(optionValues[0], c);
/*      */     }
/*      */     
/*      */     public int getOptionValuesLength() {
/*  132 */       return 1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract void handleOption(String param1String, SVGConverter param1SVGConverter);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class FloatOptionHandler
/*      */     extends SingleValueOptionHandler
/*      */   {
/*      */     public void handleOption(String optionValue, SVGConverter c) {
/*      */       try {
/*  147 */         handleOption(Float.parseFloat(optionValue), c);
/*  148 */       } catch (NumberFormatException e) {
/*  149 */         throw new IllegalArgumentException();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract void handleOption(float param1Float, SVGConverter param1SVGConverter);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class TimeOptionHandler
/*      */     extends FloatOptionHandler
/*      */   {
/*      */     public void handleOption(String optionValue, final SVGConverter c) {
/*      */       try {
/*  165 */         ClockParser p = new ClockParser(false);
/*  166 */         p.setClockHandler(new ClockHandler() {
/*      */               public void clockValue(float v) {
/*  168 */                 Main.TimeOptionHandler.this.handleOption(v, c);
/*      */               }
/*      */             });
/*  171 */         p.parse(optionValue);
/*  172 */       } catch (ParseException e) {
/*  173 */         throw new IllegalArgumentException();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract void handleOption(float param1Float, SVGConverter param1SVGConverter);
/*      */   }
/*      */ 
/*      */   
/*      */   public static abstract class RectangleOptionHandler
/*      */     extends SingleValueOptionHandler
/*      */   {
/*      */     public void handleOption(String optionValue, SVGConverter c) {
/*  187 */       Rectangle2D r = parseRect(optionValue);
/*  188 */       if (r == null) {
/*  189 */         throw new IllegalArgumentException();
/*      */       }
/*  191 */       handleOption(r, c);
/*      */     }
/*      */     
/*      */     public abstract void handleOption(Rectangle2D param1Rectangle2D, SVGConverter param1SVGConverter);
/*      */     
/*      */     public Rectangle2D.Float parseRect(String rectValue) {
/*  197 */       Rectangle2D.Float rect = null;
/*  198 */       if (rectValue != null) {
/*  199 */         if (!rectValue.toLowerCase().endsWith("f")) {
/*  200 */           rectValue = rectValue + "f";
/*      */         }
/*      */         
/*  203 */         StringTokenizer st = new StringTokenizer(rectValue, ",");
/*  204 */         if (st.countTokens() == 4) {
/*  205 */           String xStr = st.nextToken();
/*  206 */           String yStr = st.nextToken();
/*  207 */           String wStr = st.nextToken();
/*  208 */           String hStr = st.nextToken();
/*  209 */           float x = Float.NaN, y = Float.NaN, w = Float.NaN, h = Float.NaN;
/*      */           try {
/*  211 */             x = Float.parseFloat(xStr);
/*  212 */             y = Float.parseFloat(yStr);
/*  213 */             w = Float.parseFloat(wStr);
/*  214 */             h = Float.parseFloat(hStr);
/*  215 */           } catch (NumberFormatException numberFormatException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  220 */           if (!Float.isNaN(x) && !Float.isNaN(y) && !Float.isNaN(w) && w > 0.0F && !Float.isNaN(h) && h > 0.0F)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  227 */             rect = new Rectangle2D.Float(x, y, w, h);
/*      */           }
/*      */         } 
/*      */       } 
/*  231 */       return rect;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class ColorOptionHandler
/*      */     extends SingleValueOptionHandler
/*      */   {
/*      */     public void handleOption(String optionValue, SVGConverter c) {
/*  242 */       Color color = parseARGB(optionValue);
/*  243 */       if (color == null) {
/*  244 */         throw new IllegalArgumentException();
/*      */       }
/*  246 */       handleOption(color, c);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public abstract void handleOption(Color param1Color, SVGConverter param1SVGConverter);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Color parseARGB(String argbVal) {
/*  258 */       Color c = null;
/*  259 */       if (argbVal != null) {
/*  260 */         StringTokenizer st = new StringTokenizer(argbVal, ".");
/*  261 */         if (st.countTokens() == 4) {
/*  262 */           String aStr = st.nextToken();
/*  263 */           String rStr = st.nextToken();
/*  264 */           String gStr = st.nextToken();
/*  265 */           String bStr = st.nextToken();
/*  266 */           int a = -1, r = -1, g = -1, b = -1;
/*      */           try {
/*  268 */             a = Integer.parseInt(aStr);
/*  269 */             r = Integer.parseInt(rStr);
/*  270 */             g = Integer.parseInt(gStr);
/*  271 */             b = Integer.parseInt(bStr);
/*  272 */           } catch (NumberFormatException numberFormatException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  278 */           if (a >= 0 && a <= 255 && r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  285 */             c = new Color(r, g, b, a);
/*      */           }
/*      */         } 
/*      */       } 
/*  289 */       return c;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  298 */   public static String USAGE = Messages.formatMessage("Main.usage", null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  309 */   public static String CL_OPTION_OUTPUT = Messages.get("Main.cl.option.output", "-d");
/*      */ 
/*      */   
/*  312 */   public static String CL_OPTION_OUTPUT_DESCRIPTION = Messages.get("Main.cl.option.output.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  318 */   public static String CL_OPTION_MIME_TYPE = Messages.get("Main.cl.option.mime.type", "-m");
/*      */ 
/*      */   
/*  321 */   public static String CL_OPTION_MIME_TYPE_DESCRIPTION = Messages.get("Main.cl.option.mime.type.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  327 */   public static String CL_OPTION_WIDTH = Messages.get("Main.cl.option.width", "-w");
/*      */ 
/*      */   
/*  330 */   public static String CL_OPTION_WIDTH_DESCRIPTION = Messages.get("Main.cl.option.width.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  336 */   public static String CL_OPTION_HEIGHT = Messages.get("Main.cl.option.height", "-h");
/*      */ 
/*      */   
/*  339 */   public static String CL_OPTION_HEIGHT_DESCRIPTION = Messages.get("Main.cl.option.height.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  345 */   public static String CL_OPTION_MAX_WIDTH = Messages.get("Main.cl.option.max.width", "-maxw");
/*      */ 
/*      */   
/*  348 */   public static String CL_OPTION_MAX_WIDTH_DESCRIPTION = Messages.get("Main.cl.option.max.width.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  354 */   public static String CL_OPTION_MAX_HEIGHT = Messages.get("Main.cl.option.max.height", "-maxh");
/*      */ 
/*      */   
/*  357 */   public static String CL_OPTION_MAX_HEIGHT_DESCRIPTION = Messages.get("Main.cl.option.max.height.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  364 */   public static String CL_OPTION_AOI = Messages.get("Main.cl.option.aoi", "-a");
/*      */ 
/*      */   
/*  367 */   public static String CL_OPTION_AOI_DESCRIPTION = Messages.get("Main.cl.option.aoi.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  373 */   public static String CL_OPTION_BACKGROUND_COLOR = Messages.get("Main.cl.option.background.color", "-bg");
/*      */ 
/*      */   
/*  376 */   public static String CL_OPTION_BACKGROUND_COLOR_DESCRIPTION = Messages.get("Main.cl.option.background.color.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  383 */   public static String CL_OPTION_MEDIA_TYPE = Messages.get("Main.cl.option.media.type", "-cssMedia");
/*      */ 
/*      */   
/*  386 */   public static String CL_OPTION_MEDIA_TYPE_DESCRIPTION = Messages.get("Main.cl.option.media.type.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  393 */   public static String CL_OPTION_DEFAULT_FONT_FAMILY = Messages.get("Main.cl.option.default.font.family", "-font-family");
/*      */ 
/*      */   
/*  396 */   public static String CL_OPTION_DEFAULT_FONT_FAMILY_DESCRIPTION = Messages.get("Main.cl.option.default.font.family.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  403 */   public static String CL_OPTION_ALTERNATE_STYLESHEET = Messages.get("Main.cl.option.alternate.stylesheet", "-cssAlternate");
/*      */ 
/*      */   
/*  406 */   public static String CL_OPTION_ALTERNATE_STYLESHEET_DESCRIPTION = Messages.get("Main.cl.option.alternate.stylesheet.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  413 */   public static String CL_OPTION_VALIDATE = Messages.get("Main.cl.option.validate", "-validate");
/*      */ 
/*      */   
/*  416 */   public static String CL_OPTION_VALIDATE_DESCRIPTION = Messages.get("Main.cl.option.validate.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  423 */   public static String CL_OPTION_ONLOAD = Messages.get("Main.cl.option.onload", "-onload");
/*      */ 
/*      */   
/*  426 */   public static String CL_OPTION_ONLOAD_DESCRIPTION = Messages.get("Main.cl.option.onload.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  433 */   public static String CL_OPTION_SNAPSHOT_TIME = Messages.get("Main.cl.option.snapshot.time", "-snapshotTime");
/*      */ 
/*      */   
/*  436 */   public static String CL_OPTION_SNAPSHOT_TIME_DESCRIPTION = Messages.get("Main.cl.option.snapshot.time.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  443 */   public static String CL_OPTION_LANGUAGE = Messages.get("Main.cl.option.language", "-lang");
/*      */ 
/*      */   
/*  446 */   public static String CL_OPTION_LANGUAGE_DESCRIPTION = Messages.get("Main.cl.option.language.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  452 */   public static String CL_OPTION_USER_STYLESHEET = Messages.get("Main.cl.option.user.stylesheet", "-cssUser");
/*      */ 
/*      */   
/*  455 */   public static String CL_OPTION_USER_STYLESHEET_DESCRIPTION = Messages.get("Main.cl.option.user.stylesheet.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  461 */   public static String CL_OPTION_DPI = Messages.get("Main.cl.option.dpi", "-dpi");
/*      */ 
/*      */   
/*  464 */   public static String CL_OPTION_DPI_DESCRIPTION = Messages.get("Main.cl.option.dpi.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  470 */   public static String CL_OPTION_QUALITY = Messages.get("Main.cl.option.quality", "-q");
/*      */ 
/*      */   
/*  473 */   public static String CL_OPTION_QUALITY_DESCRIPTION = Messages.get("Main.cl.option.quality.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  479 */   public static String CL_OPTION_INDEXED = Messages.get("Main.cl.option.indexed", "-indexed");
/*      */ 
/*      */   
/*  482 */   public static String CL_OPTION_INDEXED_DESCRIPTION = Messages.get("Main.cl.option.indexed.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  488 */   public static String CL_OPTION_ALLOWED_SCRIPTS = Messages.get("Main.cl.option.allowed.scripts", "-scripts");
/*      */ 
/*      */   
/*  491 */   public static String CL_OPTION_ALLOWED_SCRIPTS_DESCRIPTION = Messages.get("Main.cl.option.allowed.scripts.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  498 */   public static String CL_OPTION_CONSTRAIN_SCRIPT_ORIGIN = Messages.get("Main.cl.option.constrain.script.origin", "-anyScriptOrigin");
/*      */ 
/*      */   
/*  501 */   public static String CL_OPTION_CONSTRAIN_SCRIPT_ORIGIN_DESCRIPTION = Messages.get("Main.cl.option.constrain.script.origin.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  507 */   public static String CL_OPTION_SECURITY_OFF = Messages.get("Main.cl.option.security.off", "-scriptSecurityOff");
/*      */ 
/*      */   
/*  510 */   public static String CL_OPTION_SECURITY_OFF_DESCRIPTION = Messages.get("Main.cl.option.security.off.description", "No description");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  517 */   protected static Map optionMap = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  523 */   protected static Map mimeTypeMap = new HashMap<Object, Object>();
/*      */   protected List args;
/*      */   public static final String ERROR_NOT_ENOUGH_OPTION_VALUES = "Main.error.not.enough.option.values";
/*      */   public static final String ERROR_ILLEGAL_ARGUMENT = "Main.error.illegal.argument";
/*      */   public static final String ERROR_WHILE_CONVERTING_FILES = "Main.error.while.converting.files";
/*      */   
/*      */   static {
/*  530 */     mimeTypeMap.put("image/jpg", DestinationType.JPEG);
/*  531 */     mimeTypeMap.put("image/jpeg", DestinationType.JPEG);
/*  532 */     mimeTypeMap.put("image/jpe", DestinationType.JPEG);
/*  533 */     mimeTypeMap.put("image/png", DestinationType.PNG);
/*  534 */     mimeTypeMap.put("application/pdf", DestinationType.PDF);
/*  535 */     mimeTypeMap.put("image/tiff", DestinationType.TIFF);
/*      */     
/*  537 */     optionMap.put(CL_OPTION_OUTPUT, new SingleValueOptionHandler()
/*      */         {
/*      */           public void handleOption(String optionValue, SVGConverter c)
/*      */           {
/*  541 */             c.setDst(new File(optionValue));
/*      */           }
/*      */           public String getOptionDescription() {
/*  544 */             return Main.CL_OPTION_OUTPUT_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  548 */     optionMap.put(CL_OPTION_MIME_TYPE, new SingleValueOptionHandler()
/*      */         {
/*      */           public void handleOption(String optionValue, SVGConverter c)
/*      */           {
/*  552 */             DestinationType dstType = (DestinationType)Main.mimeTypeMap.get(optionValue);
/*      */ 
/*      */             
/*  555 */             if (dstType == null) {
/*  556 */               throw new IllegalArgumentException();
/*      */             }
/*      */             
/*  559 */             c.setDestinationType(dstType);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  563 */             return Main.CL_OPTION_MIME_TYPE_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  567 */     optionMap.put(CL_OPTION_WIDTH, new FloatOptionHandler()
/*      */         {
/*      */           public void handleOption(float optionValue, SVGConverter c)
/*      */           {
/*  571 */             if (optionValue <= 0.0F) {
/*  572 */               throw new IllegalArgumentException();
/*      */             }
/*      */             
/*  575 */             c.setWidth(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  579 */             return Main.CL_OPTION_WIDTH_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  583 */     optionMap.put(CL_OPTION_HEIGHT, new FloatOptionHandler()
/*      */         {
/*      */           public void handleOption(float optionValue, SVGConverter c)
/*      */           {
/*  587 */             if (optionValue <= 0.0F) {
/*  588 */               throw new IllegalArgumentException();
/*      */             }
/*      */             
/*  591 */             c.setHeight(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  595 */             return Main.CL_OPTION_HEIGHT_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  599 */     optionMap.put(CL_OPTION_MAX_WIDTH, new FloatOptionHandler()
/*      */         {
/*      */           public void handleOption(float optionValue, SVGConverter c)
/*      */           {
/*  603 */             if (optionValue <= 0.0F) {
/*  604 */               throw new IllegalArgumentException();
/*      */             }
/*      */             
/*  607 */             c.setMaxWidth(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  611 */             return Main.CL_OPTION_MAX_WIDTH_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  615 */     optionMap.put(CL_OPTION_MAX_HEIGHT, new FloatOptionHandler()
/*      */         {
/*      */           public void handleOption(float optionValue, SVGConverter c)
/*      */           {
/*  619 */             if (optionValue <= 0.0F) {
/*  620 */               throw new IllegalArgumentException();
/*      */             }
/*      */             
/*  623 */             c.setMaxHeight(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  627 */             return Main.CL_OPTION_MAX_HEIGHT_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  631 */     optionMap.put(CL_OPTION_AOI, new RectangleOptionHandler()
/*      */         {
/*      */           public void handleOption(Rectangle2D optionValue, SVGConverter c)
/*      */           {
/*  635 */             c.setArea(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  639 */             return Main.CL_OPTION_AOI_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  643 */     optionMap.put(CL_OPTION_BACKGROUND_COLOR, new ColorOptionHandler()
/*      */         {
/*      */           public void handleOption(Color optionValue, SVGConverter c)
/*      */           {
/*  647 */             c.setBackgroundColor(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  651 */             return Main.CL_OPTION_BACKGROUND_COLOR_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  655 */     optionMap.put(CL_OPTION_MEDIA_TYPE, new SingleValueOptionHandler()
/*      */         {
/*      */           public void handleOption(String optionValue, SVGConverter c)
/*      */           {
/*  659 */             c.setMediaType(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  663 */             return Main.CL_OPTION_MEDIA_TYPE_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  667 */     optionMap.put(CL_OPTION_DEFAULT_FONT_FAMILY, new SingleValueOptionHandler()
/*      */         {
/*      */           public void handleOption(String optionValue, SVGConverter c)
/*      */           {
/*  671 */             c.setDefaultFontFamily(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  675 */             return Main.CL_OPTION_DEFAULT_FONT_FAMILY_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  679 */     optionMap.put(CL_OPTION_ALTERNATE_STYLESHEET, new SingleValueOptionHandler()
/*      */         {
/*      */           public void handleOption(String optionValue, SVGConverter c)
/*      */           {
/*  683 */             c.setAlternateStylesheet(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  687 */             return Main.CL_OPTION_ALTERNATE_STYLESHEET_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  691 */     optionMap.put(CL_OPTION_USER_STYLESHEET, new SingleValueOptionHandler()
/*      */         {
/*      */           public void handleOption(String optionValue, SVGConverter c)
/*      */           {
/*  695 */             c.setUserStylesheet(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  699 */             return Main.CL_OPTION_USER_STYLESHEET_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  703 */     optionMap.put(CL_OPTION_LANGUAGE, new SingleValueOptionHandler()
/*      */         {
/*      */           public void handleOption(String optionValue, SVGConverter c)
/*      */           {
/*  707 */             c.setLanguage(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  711 */             return Main.CL_OPTION_LANGUAGE_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  715 */     optionMap.put(CL_OPTION_DPI, new FloatOptionHandler()
/*      */         {
/*      */           public void handleOption(float optionValue, SVGConverter c)
/*      */           {
/*  719 */             if (optionValue <= 0.0F) {
/*  720 */               throw new IllegalArgumentException();
/*      */             }
/*      */             
/*  723 */             c.setPixelUnitToMillimeter(2.54F / optionValue * 10.0F);
/*      */           }
/*      */ 
/*      */           
/*      */           public String getOptionDescription() {
/*  728 */             return Main.CL_OPTION_DPI_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  732 */     optionMap.put(CL_OPTION_QUALITY, new FloatOptionHandler()
/*      */         {
/*      */           public void handleOption(float optionValue, SVGConverter c)
/*      */           {
/*  736 */             if (optionValue <= 0.0F || optionValue >= 1.0F) {
/*  737 */               throw new IllegalArgumentException();
/*      */             }
/*      */             
/*  740 */             c.setQuality(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  744 */             return Main.CL_OPTION_QUALITY_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  748 */     optionMap.put(CL_OPTION_INDEXED, new FloatOptionHandler()
/*      */         {
/*      */           public void handleOption(float optionValue, SVGConverter c)
/*      */           {
/*  752 */             if (optionValue != 1.0F && optionValue != 2.0F && optionValue != 4.0F && optionValue != 8.0F)
/*      */             {
/*      */ 
/*      */               
/*  756 */               throw new IllegalArgumentException();
/*      */             }
/*  758 */             c.setIndexed((int)optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  762 */             return Main.CL_OPTION_INDEXED_DESCRIPTION;
/*      */           }
/*      */         });
/*  765 */     optionMap.put(CL_OPTION_VALIDATE, new NoValueOptionHandler()
/*      */         {
/*      */           public void handleOption(SVGConverter c) {
/*  768 */             c.setValidate(true);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  772 */             return Main.CL_OPTION_VALIDATE_DESCRIPTION;
/*      */           }
/*      */         });
/*  775 */     optionMap.put(CL_OPTION_ONLOAD, new NoValueOptionHandler()
/*      */         {
/*      */           public void handleOption(SVGConverter c) {
/*  778 */             c.setExecuteOnload(true);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  782 */             return Main.CL_OPTION_ONLOAD_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  786 */     optionMap.put(CL_OPTION_SNAPSHOT_TIME, new TimeOptionHandler()
/*      */         {
/*      */           public void handleOption(float optionValue, SVGConverter c)
/*      */           {
/*  790 */             c.setExecuteOnload(true);
/*  791 */             c.setSnapshotTime(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  795 */             return Main.CL_OPTION_SNAPSHOT_TIME_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  799 */     optionMap.put(CL_OPTION_ALLOWED_SCRIPTS, new SingleValueOptionHandler()
/*      */         {
/*      */           public void handleOption(String optionValue, SVGConverter c)
/*      */           {
/*  803 */             c.setAllowedScriptTypes(optionValue);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  807 */             return Main.CL_OPTION_ALLOWED_SCRIPTS_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  811 */     optionMap.put(CL_OPTION_CONSTRAIN_SCRIPT_ORIGIN, new NoValueOptionHandler()
/*      */         {
/*      */           public void handleOption(SVGConverter c) {
/*  814 */             c.setConstrainScriptOrigin(false);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  818 */             return Main.CL_OPTION_CONSTRAIN_SCRIPT_ORIGIN_DESCRIPTION;
/*      */           }
/*      */         });
/*      */     
/*  822 */     optionMap.put(CL_OPTION_SECURITY_OFF, new NoValueOptionHandler()
/*      */         {
/*      */           public void handleOption(SVGConverter c) {
/*  825 */             c.setSecurityOff(true);
/*      */           }
/*      */           
/*      */           public String getOptionDescription() {
/*  829 */             return Main.CL_OPTION_SECURITY_OFF_DESCRIPTION;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public static final String MESSAGE_ABOUT_TO_TRANSCODE = "Main.message.about.to.transcode";
/*      */   public static final String MESSAGE_ABOUT_TO_TRANSCODE_SOURCE = "Main.message.about.to.transcode.source";
/*      */   public static final String MESSAGE_CONVERSION_FAILED = "Main.message.conversion.failed";
/*      */   public static final String MESSAGE_CONVERSION_SUCCESS = "Main.message.conversion.success";
/*      */   
/*      */   public Main(String[] args) {
/*  841 */     this.args = new ArrayList();
/*  842 */     for (String arg : args) {
/*  843 */       this.args.add(arg);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void error(String errorCode, Object[] errorArgs) {
/*  849 */     System.err.println(Messages.formatMessage(errorCode, errorArgs));
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
/*      */   public void execute() {
/*  877 */     SVGConverter c = new SVGConverter(this);
/*      */     
/*  879 */     List<String> sources = new ArrayList();
/*      */     
/*  881 */     int nArgs = this.args.size();
/*  882 */     for (int i = 0; i < nArgs; i++) {
/*  883 */       String v = this.args.get(i);
/*  884 */       OptionHandler optionHandler = (OptionHandler)optionMap.get(v);
/*  885 */       if (optionHandler == null) {
/*      */         
/*  887 */         sources.add(v);
/*      */       }
/*      */       else {
/*      */         
/*  891 */         int nOptionArgs = optionHandler.getOptionValuesLength();
/*  892 */         if (i + nOptionArgs >= nArgs) {
/*  893 */           error("Main.error.not.enough.option.values", new Object[] { v, optionHandler.getOptionDescription() });
/*      */           
/*      */           return;
/*      */         } 
/*  897 */         String[] optionValues = new String[nOptionArgs];
/*  898 */         for (int j = 0; j < nOptionArgs; j++) {
/*  899 */           optionValues[j] = this.args.get(1 + i + j);
/*      */         }
/*  901 */         i += nOptionArgs;
/*      */         
/*      */         try {
/*  904 */           optionHandler.handleOption(optionValues, c);
/*  905 */         } catch (IllegalArgumentException e) {
/*  906 */           e.printStackTrace();
/*  907 */           error("Main.error.illegal.argument", new Object[] { v, optionHandler.getOptionDescription(), toString(optionValues) });
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  917 */     ApplicationSecurityEnforcer securityEnforcer = new ApplicationSecurityEnforcer(getClass(), "org/apache/batik/apps/rasterizer/resources/rasterizer.policy");
/*      */ 
/*      */ 
/*      */     
/*  921 */     securityEnforcer.enforceSecurity(!c.getSecurityOff());
/*      */     
/*  923 */     String[] expandedSources = expandSources(sources);
/*      */     
/*  925 */     c.setSources(expandedSources);
/*      */     
/*  927 */     validateConverterConfig(c);
/*      */     
/*  929 */     if (expandedSources == null || expandedSources.length < 1) {
/*  930 */       System.out.println(USAGE);
/*  931 */       System.out.flush();
/*  932 */       securityEnforcer.enforceSecurity(false);
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/*  937 */       c.execute();
/*  938 */     } catch (SVGConverterException e) {
/*  939 */       error("Main.error.while.converting.files", new Object[] { e.getMessage() });
/*      */     } finally {
/*      */       
/*  942 */       System.out.flush();
/*  943 */       securityEnforcer.enforceSecurity(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected String toString(String[] v) {
/*  948 */     StringBuffer sb = new StringBuffer();
/*  949 */     int n = (v != null) ? v.length : 0;
/*  950 */     for (int i = 0; i < n; i++) {
/*  951 */       sb.append(v[i]);
/*  952 */       sb.append(' ');
/*      */     } 
/*      */     
/*  955 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void validateConverterConfig(SVGConverter c) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String[] expandSources(List sources) {
/*  970 */     List<String> expandedSources = new ArrayList();
/*  971 */     for (Object source : sources) {
/*  972 */       String v = (String)source;
/*  973 */       File f = new File(v);
/*  974 */       if (f.exists() && f.isDirectory()) {
/*  975 */         File[] fl = f.listFiles(new SVGConverter.SVGFileFilter());
/*  976 */         for (File aFl : fl)
/*  977 */           expandedSources.add(aFl.getPath()); 
/*      */         continue;
/*      */       } 
/*  980 */       expandedSources.add(v);
/*      */     } 
/*      */ 
/*      */     
/*  984 */     String[] s = new String[expandedSources.size()];
/*  985 */     expandedSources.toArray(s);
/*  986 */     return s;
/*      */   }
/*      */   
/*      */   public static void main(String[] args) {
/*  990 */     (new Main(args)).execute();
/*  991 */     System.exit(0);
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
/*      */   public boolean proceedWithComputedTask(Transcoder transcoder, Map hints, List sources, List dest) {
/* 1013 */     System.out.println(Messages.formatMessage("Main.message.about.to.transcode", new Object[] { "" + sources.size() }));
/*      */     
/* 1015 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean proceedWithSourceTranscoding(SVGConverterSource source, File dest) {
/* 1020 */     System.out.print(Messages.formatMessage("Main.message.about.to.transcode.source", new Object[] { source.toString(), dest.toString() }));
/*      */ 
/*      */     
/* 1023 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean proceedOnSourceTranscodingFailure(SVGConverterSource source, File dest, String errorCode) {
/* 1029 */     System.out.println(Messages.formatMessage("Main.message.conversion.failed", new Object[] { errorCode }));
/*      */ 
/*      */     
/* 1032 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onSourceTranscodingSuccess(SVGConverterSource source, File dest) {
/* 1037 */     System.out.println(Messages.formatMessage("Main.message.conversion.success", null));
/*      */   }
/*      */   
/*      */   public static interface OptionHandler {
/*      */     void handleOption(String[] param1ArrayOfString, SVGConverter param1SVGConverter);
/*      */     
/*      */     int getOptionValuesLength();
/*      */     
/*      */     String getOptionDescription();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */