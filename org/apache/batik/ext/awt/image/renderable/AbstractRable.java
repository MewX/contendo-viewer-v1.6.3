/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.PadRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.RenderedImageCachableRed;
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
/*     */ public abstract class AbstractRable
/*     */   implements Filter
/*     */ {
/*     */   protected Vector srcs;
/*  53 */   protected Map props = new HashMap<Object, Object>();
/*  54 */   protected long stamp = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRable() {
/*  63 */     this.srcs = new Vector();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRable(Filter src) {
/*  73 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRable(Filter src, Map props) {
/*  83 */     init(src, props);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRable(List srcs) {
/*  94 */     this(srcs, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRable(List srcs, Map props) {
/* 105 */     init(srcs, props);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void touch() {
/* 113 */     this.stamp++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getTimeStamp() {
/* 121 */     return this.stamp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(Filter src) {
/* 130 */     touch();
/*     */     
/* 132 */     this.srcs = new Vector(1);
/* 133 */     if (src != null) {
/* 134 */       this.srcs.add(src);
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
/*     */   protected void init(Filter src, Map props) {
/* 148 */     init(src);
/* 149 */     if (props != null) {
/* 150 */       this.props.putAll(props);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(List<?> srcs) {
/* 161 */     touch();
/* 162 */     this.srcs = new Vector(srcs);
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
/*     */   protected void init(List srcs, Map props) {
/* 174 */     init(srcs);
/* 175 */     if (props != null)
/* 176 */       this.props.putAll(props); 
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 180 */     Rectangle2D bounds = null;
/* 181 */     if (this.srcs.size() != 0) {
/* 182 */       Iterator<Filter> i = this.srcs.iterator();
/* 183 */       Filter src = i.next();
/* 184 */       bounds = (Rectangle2D)src.getBounds2D().clone();
/*     */       
/* 186 */       while (i.hasNext()) {
/* 187 */         src = i.next();
/* 188 */         Rectangle2D r = src.getBounds2D();
/* 189 */         Rectangle2D.union(bounds, r, bounds);
/*     */       } 
/*     */     } 
/* 192 */     return bounds;
/*     */   }
/*     */   
/*     */   public Vector getSources() {
/* 196 */     return this.srcs;
/*     */   }
/*     */   
/*     */   public RenderedImage createDefaultRendering() {
/* 200 */     return createScaledRendering(100, 100, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderedImage createScaledRendering(int w, int h, RenderingHints hints) {
/* 205 */     float sX = w / getWidth();
/* 206 */     float sY = h / getHeight();
/* 207 */     float scale = Math.min(sX, sY);
/*     */     
/* 209 */     AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
/* 210 */     RenderContext rc = new RenderContext(at, hints);
/*     */     
/* 212 */     float dX = getWidth() * scale - w;
/* 213 */     float dY = getHeight() * scale - h;
/*     */     
/* 215 */     RenderedImage ri = createRendering(rc);
/* 216 */     CachableRed cr = RenderedImageCachableRed.wrap(ri);
/* 217 */     return (RenderedImage)new PadRed(cr, new Rectangle((int)(dX / 2.0F), (int)(dY / 2.0F), w, h), PadMode.ZERO_PAD, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMinX() {
/* 222 */     return (float)getBounds2D().getX();
/*     */   }
/*     */   public float getMinY() {
/* 225 */     return (float)getBounds2D().getY();
/*     */   }
/*     */   public float getWidth() {
/* 228 */     return (float)getBounds2D().getWidth();
/*     */   }
/*     */   public float getHeight() {
/* 231 */     return (float)getBounds2D().getHeight();
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 235 */     Object ret = this.props.get(name);
/* 236 */     if (ret != null) return ret; 
/* 237 */     for (Object src : this.srcs) {
/* 238 */       RenderableImage ri = (RenderableImage)src;
/* 239 */       ret = ri.getProperty(name);
/* 240 */       if (ret != null) return ret; 
/*     */     } 
/* 242 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 246 */     Set keys = this.props.keySet();
/* 247 */     Iterator<String> iter = keys.iterator();
/* 248 */     String[] ret = new String[keys.size()];
/* 249 */     int i = 0;
/* 250 */     while (iter.hasNext()) {
/* 251 */       ret[i++] = iter.next();
/*     */     }
/*     */     
/* 254 */     iter = this.srcs.iterator();
/* 255 */     while (iter.hasNext()) {
/* 256 */       RenderableImage ri = (RenderableImage)iter.next();
/* 257 */       String[] srcProps = ri.getPropertyNames();
/* 258 */       if (srcProps.length != 0) {
/* 259 */         String[] tmp = new String[ret.length + srcProps.length];
/* 260 */         System.arraycopy(ret, 0, tmp, 0, ret.length);
/* 261 */         System.arraycopy(tmp, ret.length, srcProps, 0, srcProps.length);
/* 262 */         ret = tmp;
/*     */       } 
/*     */     } 
/*     */     
/* 266 */     return ret;
/*     */   }
/*     */   public boolean isDynamic() {
/* 269 */     return false;
/*     */   }
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle2D outputRgn) {
/* 273 */     if (srcIndex < 0 || srcIndex > this.srcs.size()) {
/* 274 */       throw new IndexOutOfBoundsException("Nonexistant source requested.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 279 */     Rectangle2D srect = (Rectangle2D)outputRgn.clone();
/* 280 */     Rectangle2D bounds = getBounds2D();
/*     */ 
/*     */     
/* 283 */     if (!bounds.intersects(srect)) {
/* 284 */       return new Rectangle2D.Float();
/*     */     }
/* 286 */     Rectangle2D.intersect(srect, bounds, srect);
/* 287 */     return srect;
/*     */   }
/*     */ 
/*     */   
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle2D inputRgn) {
/* 292 */     if (srcIndex < 0 || srcIndex > this.srcs.size()) {
/* 293 */       throw new IndexOutOfBoundsException("Nonexistant source requested.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 298 */     Rectangle2D drect = (Rectangle2D)inputRgn.clone();
/* 299 */     Rectangle2D bounds = getBounds2D();
/*     */ 
/*     */     
/* 302 */     if (!bounds.intersects(drect)) {
/* 303 */       return new Rectangle2D.Float();
/*     */     }
/* 305 */     Rectangle2D.intersect(drect, bounds, drect);
/* 306 */     return drect;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/AbstractRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */