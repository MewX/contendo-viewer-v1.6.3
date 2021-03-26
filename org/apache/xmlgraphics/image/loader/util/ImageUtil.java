/*     */ package org.apache.xmlgraphics.image.loader.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.xmlgraphics.image.loader.ImageProcessingHints;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSource;
/*     */ import org.apache.xmlgraphics.io.XmlSourceUtil;
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
/*     */ public final class ImageUtil
/*     */ {
/*     */   @Deprecated
/*     */   public static InputStream getInputStream(Source src) {
/*  57 */     return XmlSourceUtil.getInputStream(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ImageInputStream getImageInputStream(Source src) {
/*  66 */     if (src instanceof ImageSource) {
/*  67 */       return ((ImageSource)src).getImageInputStream();
/*     */     }
/*  69 */     return null;
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
/*     */   public static InputStream needInputStream(Source src) {
/*  82 */     return XmlSourceUtil.needInputStream(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ImageInputStream needImageInputStream(Source src) {
/*  93 */     if (src instanceof ImageSource) {
/*  94 */       ImageSource isrc = (ImageSource)src;
/*  95 */       if (isrc.getImageInputStream() == null) {
/*  96 */         throw new IllegalArgumentException("ImageInputStream is null/cleared on ImageSource");
/*     */       }
/*     */       
/*  99 */       return isrc.getImageInputStream();
/*     */     } 
/* 101 */     throw new IllegalArgumentException("Source must be an ImageSource");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasInputStream(Source src) {
/* 111 */     return (XmlSourceUtil.hasInputStream(src) || hasImageInputStream(src));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static boolean hasReader(Source src) {
/* 122 */     return XmlSourceUtil.hasReader(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasImageInputStream(Source src) {
/* 131 */     return (getImageInputStream(src) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void removeStreams(Source src) {
/* 142 */     XmlSourceUtil.removeStreams(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void closeQuietly(Source src) {
/* 153 */     XmlSourceUtil.closeQuietly(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ImageInputStream ignoreFlushing(final ImageInputStream in) {
/* 163 */     return (ImageInputStream)Proxy.newProxyInstance(in.getClass().getClassLoader(), new Class[] { ImageInputStream.class }, new InvocationHandler()
/*     */         {
/*     */           
/*     */           public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
/*     */           {
/* 168 */             String methodName = method.getName();
/*     */             
/* 170 */             if (!methodName.startsWith("flush")) {
/*     */               try {
/* 172 */                 return method.invoke(in, args);
/* 173 */               } catch (InvocationTargetException ite) {
/* 174 */                 throw ite.getCause();
/*     */               } 
/*     */             }
/* 177 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   private static final byte[] GZIP_MAGIC = new byte[] { 31, -117 };
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String PAGE_INDICATOR = "page=";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isGZIPCompressed(InputStream in) throws IOException {
/* 197 */     if (!in.markSupported()) {
/* 198 */       throw new IllegalArgumentException("InputStream must support mark()!");
/*     */     }
/* 200 */     byte[] data = new byte[2];
/* 201 */     in.mark(2);
/* 202 */     in.read(data);
/* 203 */     in.reset();
/* 204 */     return (data[0] == GZIP_MAGIC[0] && data[1] == GZIP_MAGIC[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InputStream decorateMarkSupported(InputStream in) {
/* 213 */     if (in.markSupported()) {
/* 214 */       return in;
/*     */     }
/* 216 */     return new BufferedInputStream(in);
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
/*     */   public static InputStream autoDecorateInputStream(InputStream in) throws IOException {
/* 228 */     in = decorateMarkSupported(in);
/* 229 */     if (isGZIPCompressed(in)) {
/* 230 */       return new GZIPInputStream(in);
/*     */     }
/* 232 */     return in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map getDefaultHints(ImageSessionContext session) {
/* 241 */     Map<Object, Object> hints = new HashMap<Object, Object>();
/* 242 */     hints.put(ImageProcessingHints.SOURCE_RESOLUTION, Float.valueOf(session.getParentContext().getSourceResolution()));
/*     */     
/* 244 */     hints.put(ImageProcessingHints.TARGET_RESOLUTION, Float.valueOf(session.getTargetResolution()));
/*     */     
/* 246 */     hints.put(ImageProcessingHints.IMAGE_SESSION_CONTEXT, session);
/* 247 */     return hints;
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
/*     */   public static Integer getPageIndexFromURI(String uri) {
/* 267 */     if (uri.indexOf('#') < 0) {
/* 268 */       return null;
/*     */     }
/*     */     try {
/* 271 */       URI u = new URI(uri);
/* 272 */       String fragment = u.getFragment();
/* 273 */       if (fragment != null) {
/* 274 */         int pos = fragment.indexOf("page=");
/* 275 */         if (pos >= 0) {
/* 276 */           pos += "page=".length();
/* 277 */           StringBuffer sb = new StringBuffer();
/* 278 */           while (pos < fragment.length()) {
/* 279 */             char c = fragment.charAt(pos);
/* 280 */             if (c >= '0' && c <= '9') {
/* 281 */               sb.append(c);
/*     */ 
/*     */ 
/*     */               
/* 285 */               pos++;
/*     */             } 
/* 287 */           }  if (sb.length() > 0) {
/* 288 */             int pageIndex = Integer.parseInt(sb.toString()) - 1;
/* 289 */             pageIndex = Math.max(0, pageIndex);
/* 290 */             return Integer.valueOf(pageIndex);
/*     */           } 
/*     */         } 
/*     */       } 
/* 294 */     } catch (URISyntaxException e) {
/* 295 */       throw new IllegalArgumentException("URI is invalid: " + e.getLocalizedMessage());
/*     */     } 
/*     */     
/* 298 */     return null;
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
/*     */   public static int needPageIndexFromURI(String uri) {
/* 315 */     Integer res = getPageIndexFromURI(uri);
/* 316 */     if (res != null) {
/* 317 */       return res.intValue();
/*     */     }
/* 319 */     return 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/util/ImageUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */