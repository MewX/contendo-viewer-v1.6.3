/*     */ package jp.cssj.sakae.b.c;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Image;
/*     */ import java.awt.MediaTracker;
/*     */ import java.awt.Paint;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import jp.cssj.sakae.b.a.b;
/*     */ import jp.cssj.sakae.b.b.a.b;
/*     */ import jp.cssj.sakae.b.b.a.c;
/*     */ import jp.cssj.sakae.b.b.b;
/*     */ import jp.cssj.sakae.c.a.b;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.c.c.b;
/*     */ import jp.cssj.sakae.c.c.d;
/*     */ import jp.cssj.sakae.c.c.e;
/*     */ import jp.cssj.sakae.c.c.f;
/*     */ import jp.cssj.sakae.c.c.g;
/*     */ import jp.cssj.sakae.c.c.h;
/*     */ import jp.cssj.sakae.c.c.i;
/*     */ import org.apache.batik.ext.awt.LinearGradientPaint;
/*     */ import org.apache.batik.ext.awt.MultipleGradientPaint;
/*     */ import org.apache.batik.ext.awt.RadialGradientPaint;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class a
/*     */ {
/*  55 */   private static final Logger a = Logger.getLogger(a.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static e a(Paint paint) {
/*  62 */     if (paint instanceof Color) {
/*  63 */       return (e)a((Color)paint);
/*     */     }
/*  65 */     if (paint instanceof GradientPaint) {
/*  66 */       GradientPaint gpaint = (GradientPaint)paint;
/*  67 */       if (gpaint.isCyclic()) {
/*  68 */         return null;
/*     */       }
/*  70 */       return (e)new d(gpaint.getPoint1().getX(), gpaint.getPoint1().getY(), gpaint.getPoint2().getX(), gpaint
/*  71 */           .getPoint2().getY(), new double[] { 0.0D, 1.0D }, new b[] {
/*  72 */             a(gpaint.getColor1()), a(gpaint.getColor2()) }, new AffineTransform());
/*     */     } 
/*     */     
/*  75 */     if (paint instanceof RadialGradientPaint) {
/*  76 */       RadialGradientPaint gpaint = (RadialGradientPaint)paint;
/*  77 */       float[] fs = gpaint.getFractions();
/*  78 */       double[] fractions = new double[fs.length];
/*  79 */       for (int i = 0; i < fs.length; i++) {
/*  80 */         fractions[i] = fs[i];
/*     */       }
/*  82 */       Color[] cs = gpaint.getColors();
/*  83 */       b[] colors = new b[cs.length];
/*  84 */       for (int j = 0; j < cs.length; j++) {
/*  85 */         colors[j] = a(cs[j]);
/*     */       }
/*  87 */       return (e)new i(gpaint.getCenterPoint().getX(), gpaint.getCenterPoint().getY(), gpaint
/*  88 */           .getRadius(), gpaint.getFocusPoint().getX(), gpaint.getFocusPoint().getY(), fractions, colors, gpaint
/*  89 */           .getTransform());
/*     */     } 
/*  91 */     if (paint instanceof LinearGradientPaint) {
/*  92 */       LinearGradientPaint gpaint = (LinearGradientPaint)paint;
/*  93 */       if (gpaint.getCycleMethod() != LinearGradientPaint.NO_CYCLE) {
/*  94 */         return null;
/*     */       }
/*  96 */       float[] fs = gpaint.getFractions();
/*  97 */       double[] fractions = new double[fs.length];
/*  98 */       for (int i = 0; i < fs.length; i++) {
/*  99 */         fractions[i] = fs[i];
/*     */       }
/* 101 */       Color[] cs = gpaint.getColors();
/* 102 */       b[] colors = new b[cs.length];
/* 103 */       for (int j = 0; j < cs.length; j++) {
/* 104 */         colors[j] = a(cs[j]);
/*     */       }
/* 106 */       return (e)new d(gpaint.getStartPoint().getX(), gpaint.getStartPoint().getY(), gpaint
/* 107 */           .getEndPoint().getX(), gpaint.getEndPoint().getY(), fractions, colors, gpaint.getTransform());
/*     */     } 
/* 109 */     if (paint instanceof TexturePaint) {
/* 110 */       TexturePaint tpaint = (TexturePaint)paint;
/* 111 */       Rectangle2D r = tpaint.getAnchorRect();
/* 112 */       AffineTransform at = AffineTransform.getTranslateInstance(r.getX(), r.getY());
/* 113 */       BufferedImage image = tpaint.getImage();
/* 114 */       at.scale(r.getWidth() / image.getWidth(), r.getHeight() / image.getHeight());
/* 115 */       return (e)new f((b)new b(image), at);
/*     */     } 
/* 117 */     return null;
/*     */   }
/*     */   
/*     */   public static Paint a(d gradient) {
/* 121 */     double[] fs = gradient.g();
/* 122 */     float[] fractions = new float[fs.length];
/* 123 */     for (int i = 0; i < fs.length; i++) {
/* 124 */       fractions[i] = (float)fs[i];
/*     */     }
/* 126 */     b[] cs = gradient.f();
/* 127 */     Color[] colors = new Color[cs.length];
/* 128 */     for (int j = 0; j < cs.length; j++) {
/* 129 */       colors[j] = a(cs[j]);
/*     */     }
/* 131 */     return (Paint)new LinearGradientPaint(new Point2D.Double(gradient.a(), gradient.c()), new Point2D.Double(gradient
/* 132 */           .d(), gradient.e()), fractions, colors, MultipleGradientPaint.NO_CYCLE, MultipleGradientPaint.SRGB, gradient
/* 133 */         .h());
/*     */   }
/*     */   
/*     */   public static Paint a(i gradient) {
/* 137 */     double[] fs = gradient.h();
/* 138 */     float[] fractions = new float[fs.length];
/* 139 */     for (int j = 0; j < fs.length; j++) {
/* 140 */       fractions[j] = (float)fs[j];
/*     */     }
/* 142 */     b[] cs = gradient.g();
/* 143 */     Color[] colors = new Color[cs.length];
/* 144 */     for (int k = 0; k < cs.length; k++) {
/* 145 */       colors[k] = a(cs[k]);
/*     */     }
/* 147 */     return (Paint)new RadialGradientPaint(new Point2D.Double(gradient.a(), gradient.c()), 
/* 148 */         (float)gradient.d(), new Point2D.Double(gradient.f(), gradient.e()), fractions, colors, MultipleGradientPaint.NO_CYCLE, MultipleGradientPaint.SRGB, gradient
/* 149 */         .i());
/*     */   }
/*     */   
/*     */   public static b a(Color color) {
/* 153 */     float r = (short)color.getRed() / 255.0F;
/* 154 */     float g = (short)color.getGreen() / 255.0F;
/* 155 */     float b = (short)color.getBlue() / 255.0F;
/* 156 */     if (color.getAlpha() == 255) {
/* 157 */       return (b)h.b(r, g, b);
/*     */     }
/* 159 */     float f1 = (short)color.getAlpha() / 255.0F;
/* 160 */     return (b)g.b(r, g, b, f1);
/*     */   }
/*     */   
/*     */   public static Color a(b color) {
/* 164 */     return new Color(color.d(), color.e(), color.f(), color.g());
/*     */   }
/*     */   public static Paint a(f pattern, b gc) {
/*     */     BufferedImage bimage;
/* 168 */     b image = pattern.c();
/* 169 */     double width = image.a();
/* 170 */     double height = image.b();
/*     */     
/* 172 */     if (image instanceof b) {
/* 173 */       bimage = ((b)image).d();
/*     */     } else {
/* 175 */       bimage = new BufferedImage((int)width, (int)height, 2);
/* 176 */       Graphics2D bg = (Graphics2D)bimage.getGraphics();
/* 177 */       image.a((b)new b(bg, gc.a()));
/*     */     } 
/* 179 */     return new TexturePaint(bimage, new Rectangle2D.Double(0.0D, 0.0D, width, height));
/*     */   }
/*     */   
/*     */   public static void a(Graphics2D g, BufferedImage image, double x, double y, double w, double h) {
/* 183 */     g.drawImage(image, new AffineTransform(w / image.getWidth(), 0.0D, 0.0D, h / image.getHeight(), x, y), null);
/*     */   }
/*     */   
/*     */   public static final String a(b ffe) {
/* 187 */     if (ffe.a()) {
/* 188 */       switch (ffe.b()) {
/*     */         case 3:
/* 190 */           return "SansSerif";
/*     */         
/*     */         case 4:
/* 193 */           return "SansSerif";
/*     */         
/*     */         case 5:
/* 196 */           return "Monospaced";
/*     */         case 2:
/* 198 */           return "SansSerif";
/*     */         case 1:
/* 200 */           return "Serif";
/*     */       } 
/*     */       
/* 203 */       throw new IllegalStateException();
/*     */     } 
/*     */     
/* 206 */     return ffe.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Font[] a(h fontStyle) {
/* 211 */     Map<TextAttribute, Object> atts = new HashMap<>();
/* 212 */     a(atts, fontStyle);
/* 213 */     Font[] fonts = new Font[fontStyle.d().b()];
/* 214 */     for (int i = 0; i < fonts.length; i++) {
/* 215 */       atts.put(TextAttribute.FAMILY, a(fontStyle.d().a(i)));
/* 216 */       fonts[i] = new Font((Map)atts);
/*     */     } 
/* 218 */     return fonts;
/*     */   }
/*     */   public static final void a(Map<TextAttribute, Object> atts, h fontStyle) {
/*     */     Float weight, posture;
/* 222 */     atts.put(TextAttribute.SIZE, new Float(fontStyle.e()));
/*     */ 
/*     */     
/* 225 */     switch (fontStyle.b()) {
/*     */       case 100:
/* 227 */         weight = TextAttribute.WEIGHT_EXTRA_LIGHT;
/*     */         break;
/*     */       case 200:
/* 230 */         weight = TextAttribute.WEIGHT_LIGHT;
/*     */         break;
/*     */       case 300:
/* 233 */         weight = TextAttribute.WEIGHT_DEMILIGHT;
/*     */         break;
/*     */       case 400:
/* 236 */         weight = TextAttribute.WEIGHT_REGULAR;
/*     */         break;
/*     */       case 500:
/* 239 */         weight = TextAttribute.WEIGHT_SEMIBOLD;
/*     */         break;
/*     */       case 600:
/* 242 */         weight = TextAttribute.WEIGHT_DEMIBOLD;
/*     */         break;
/*     */       case 700:
/* 245 */         weight = TextAttribute.WEIGHT_BOLD;
/*     */         break;
/*     */       case 800:
/* 248 */         weight = TextAttribute.WEIGHT_EXTRABOLD;
/*     */         break;
/*     */       case 900:
/* 251 */         weight = TextAttribute.WEIGHT_ULTRABOLD;
/*     */         break;
/*     */       default:
/* 254 */         throw new IllegalStateException();
/*     */     } 
/* 256 */     atts.put(TextAttribute.WEIGHT, weight);
/*     */ 
/*     */     
/* 259 */     switch (fontStyle.c()) {
/*     */       case 1:
/* 261 */         posture = TextAttribute.POSTURE_REGULAR;
/*     */         break;
/*     */       case 2:
/*     */       case 3:
/* 265 */         posture = TextAttribute.POSTURE_OBLIQUE;
/*     */         break;
/*     */       default:
/* 268 */         throw new IllegalStateException();
/*     */     } 
/* 270 */     atts.put(TextAttribute.POSTURE, posture);
/*     */   }
/*     */   
/* 273 */   private static Map<String, String> b = null;
/*     */   
/*     */   private static void a() {
/* 276 */     if (b == null) {
/* 277 */       b = new HashMap<>();
/* 278 */       Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
/* 279 */       for (int i = 0; i < fonts.length; i++) {
/* 280 */         String name = fonts[i].getFontName();
/* 281 */         a.fine(name);
/* 282 */         b.put(jp.cssj.sakae.c.a.a.a.a(name), name);
/*     */       } 
/* 284 */       b = Collections.unmodifiableMap(b);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static synchronized boolean a(String fontName) {
/* 289 */     a();
/* 290 */     return b.containsKey(jp.cssj.sakae.c.a.a.a.a(fontName));
/*     */   }
/*     */   
/*     */   public static synchronized String b(String fontName) {
/* 294 */     a();
/* 295 */     return b.get(jp.cssj.sakae.c.a.a.a.a(fontName));
/*     */   }
/*     */   
/*     */   public static BufferedImage a(ImageReader reader, ImageInputStream imageIn) throws IOException {
/*     */     try {
/* 300 */       String type = reader.getFormatName();
/* 301 */       BufferedImage buffer = null;
/* 302 */       if (type.equalsIgnoreCase("png")) {
/*     */         
/*     */         try {
/* 305 */           c c = new c(new b(imageIn), new b());
/* 306 */           buffer = new BufferedImage(c.getWidth(), c.getHeight(), 2);
/* 307 */           ((Graphics2D)buffer.getGraphics()).drawRenderedImage((RenderedImage)c, new AffineTransform());
/* 308 */         } catch (Throwable e) {
/* 309 */           imageIn.seek(0L);
/*     */         } 
/*     */       }
/* 312 */       if (buffer == null) {
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 317 */           if ("JPEG".equalsIgnoreCase(reader.getFormatName())) {
/* 318 */             for (int i = 0; i < 100; i++) {
/* 319 */               if (imageIn.read() == 255 && 
/* 320 */                 imageIn.read() == 216) {
/* 321 */                 if (imageIn.read() == 255 && 
/* 322 */                   imageIn.read() == 219) {
/* 323 */                   throw new Exception("読み込めないJPEGです");
/*     */                 }
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */             
/* 330 */             imageIn.seek(0L);
/*     */           } 
/*     */           
/* 333 */           reader.setInput(imageIn);
/* 334 */           buffer = reader.read(0);
/* 335 */         } catch (Throwable e1) {
/* 336 */           a.log(Level.FINE, "loadImage", e1);
/*     */ 
/*     */           
/*     */           try {
/* 340 */             imageIn.seek(0L);
/* 341 */             ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 342 */             byte[] buff = new byte[8192]; int len;
/* 343 */             for (len = imageIn.read(buff); len != -1; len = imageIn.read(buff)) {
/* 344 */               out.write(buff, 0, len);
/*     */             }
/* 346 */             Image image = Toolkit.getDefaultToolkit().createImage(out.toByteArray());
/* 347 */             MediaTracker tracker = new MediaTracker(new Canvas());
/* 348 */             tracker.addImage(image, 0);
/* 349 */             tracker.waitForAll();
/* 350 */             buffer = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
/*     */             
/* 352 */             buffer.getGraphics().drawImage(image, 0, 0, null);
/* 353 */           } catch (IOException ioe) {
/* 354 */             throw ioe;
/* 355 */           } catch (Throwable e2) {
/* 356 */             IOException ioe = new IOException(e2.getMessage());
/* 357 */             ioe.initCause(e2);
/* 358 */             throw ioe;
/*     */           } 
/*     */         } 
/*     */       }
/* 362 */       return buffer;
/*     */     } finally {
/* 364 */       reader.dispose();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/b/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */