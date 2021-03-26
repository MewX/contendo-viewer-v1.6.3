/*     */ package net.a.a.d;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import net.a.a.d;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public final class c
/*     */ {
/*     */   public static final String a = "image/svg+xml";
/*     */   public static final String b = "svg";
/*     */   private static final String c = "Unsupported output type: ";
/*     */   private static final int d = 255;
/*     */   
/*     */   private static final class a
/*     */   {
/*  61 */     private static final c a = new c();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private static final Log e = LogFactory.getLog(c.class);
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
/*     */   public static c a() {
/*  85 */     return a.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static c b() {
/*  94 */     return a();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension a(File paramFile1, File paramFile2, String paramString) throws IOException {
/* 113 */     net.a.a.c.c c1 = new net.a.a.c.c(net.a.a.c.c.a());
/* 114 */     return a(paramFile1, paramFile2, paramString, (net.a.a.c)c1);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension a(File paramFile1, File paramFile2, String paramString, net.a.a.c paramc) throws IOException {
/*     */     try {
/* 137 */       Document document = d.a(paramFile1);
/* 138 */       return a(document, paramFile2, paramString, paramc);
/* 139 */     } catch (SAXException sAXException) {
/* 140 */       e.error("Failed to parse file:" + paramFile1, sAXException);
/* 141 */       return null;
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
/*     */   public Dimension a(Node paramNode, File paramFile, String paramString, net.a.a.c paramc) throws IOException {
/* 167 */     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(paramFile));
/*     */     
/* 169 */     Dimension dimension = a(paramNode, bufferedOutputStream, paramString, paramc);
/*     */     
/* 171 */     if (dimension == null) {
/* 172 */       if (!paramFile.delete()) {
/* 173 */         e.debug("Could not delete " + paramFile);
/*     */       }
/*     */     } else {
/*     */       
/*     */       try {
/* 178 */         bufferedOutputStream.close();
/* 179 */       } catch (IOException iOException) {
/* 180 */         e.debug(iOException);
/*     */       } 
/*     */     } 
/* 183 */     return dimension;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public e.a a(Node paramNode, String paramString, net.a.a.c paramc) {
/* 205 */     e e = f.a().c(paramString);
/* 206 */     e.a a = null;
/* 207 */     if (e != null) {
/* 208 */       a = e.a(paramNode, paramc);
/*     */     }
/* 210 */     if (a == null) {
/* 211 */       e.fatal("Unsupported output type: " + paramString);
/*     */     }
/*     */     
/* 214 */     return a;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension a(Node paramNode, OutputStream paramOutputStream, String paramString, net.a.a.c paramc) throws IOException {
/* 239 */     e e = f.a().c(paramString);
/* 240 */     Dimension dimension = null;
/* 241 */     if (e == null) {
/* 242 */       e.fatal("Unsupported output type: " + paramString);
/*     */     } else {
/*     */       
/*     */       try {
/* 246 */         dimension = e.a(paramNode, paramc, paramOutputStream);
/* 247 */       } catch (IOException iOException) {
/* 248 */         e.fatal("Failed to process: " + iOException.getMessage(), iOException);
/*     */       } 
/*     */     } 
/*     */     
/* 252 */     return dimension;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension a(String paramString1, OutputStream paramOutputStream, String paramString2, net.a.a.c paramc) throws IOException {
/* 275 */     Dimension dimension = null;
/*     */     
/*     */     try {
/* 278 */       Document document = d.a(paramString1);
/* 279 */       dimension = a(document, paramOutputStream, paramString2, paramc);
/* 280 */     } catch (SAXException sAXException) {
/* 281 */       e.error("SAXException converting:" + paramString1, sAXException);
/* 282 */       dimension = null;
/* 283 */     } catch (ParserConfigurationException parserConfigurationException) {
/* 284 */       e.error("ParserConfigurationException converting:" + paramString1, parserConfigurationException);
/*     */       
/* 286 */       dimension = null;
/*     */     } 
/*     */     
/* 289 */     return dimension;
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
/*     */   public BufferedImage a(Node paramNode, net.a.a.c paramc) throws IOException {
/* 305 */     return a(paramNode, paramc, 2);
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
/*     */   
/*     */   public BufferedImage a(Node paramNode, net.a.a.c paramc, int paramInt) throws IOException {
/*     */     Color color;
/* 323 */     BufferedImage bufferedImage1 = new BufferedImage(1, 1, paramInt);
/* 324 */     Graphics2D graphics2D1 = (Graphics2D)bufferedImage1.getGraphics();
/*     */     
/* 326 */     net.a.a.g.c c1 = new net.a.a.g.c(paramNode, paramc, graphics2D1);
/*     */     
/* 328 */     int i = Math.max(1, (int)Math.ceil(c1.a()));
/* 329 */     int j = (int)Math.ceil(c1.b());
/* 330 */     int k = Math.max(1, (int)Math.ceil(c1.c()) + j);
/*     */ 
/*     */     
/* 333 */     BufferedImage bufferedImage2 = new BufferedImage(i, k, paramInt);
/* 334 */     Graphics2D graphics2D2 = bufferedImage2.createGraphics();
/*     */ 
/*     */     
/* 337 */     if (bufferedImage2.getColorModel().hasAlpha()) {
/* 338 */       color = new Color(255, 255, 255, 0);
/*     */     } else {
/*     */       
/* 341 */       color = Color.WHITE;
/*     */     } 
/* 343 */     graphics2D2.setColor(color);
/* 344 */     graphics2D2.fillRect(0, 0, i, k);
/* 345 */     graphics2D2.setColor(Color.black);
/*     */     
/* 347 */     c1.a(graphics2D2, 0.0F, j);
/* 348 */     return bufferedImage2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/d/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */