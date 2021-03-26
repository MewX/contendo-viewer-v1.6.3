/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.filter.Mask;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface GraphicsNode
/*     */ {
/*     */   public static final int VISIBLE_PAINTED = 0;
/*     */   public static final int VISIBLE_FILL = 1;
/*     */   public static final int VISIBLE_STROKE = 2;
/*     */   public static final int VISIBLE = 3;
/*     */   public static final int PAINTED = 4;
/*     */   public static final int FILL = 5;
/*     */   public static final int STROKE = 6;
/*     */   public static final int ALL = 7;
/*     */   public static final int NONE = 8;
/* 102 */   public static final AffineTransform IDENTITY = new AffineTransform();
/*     */   
/*     */   WeakReference getWeakReference();
/*     */   
/*     */   int getPointerEventType();
/*     */   
/*     */   void setPointerEventType(int paramInt);
/*     */   
/*     */   void setTransform(AffineTransform paramAffineTransform);
/*     */   
/*     */   AffineTransform getTransform();
/*     */   
/*     */   AffineTransform getInverseTransform();
/*     */   
/*     */   AffineTransform getGlobalTransform();
/*     */   
/*     */   void setComposite(Composite paramComposite);
/*     */   
/*     */   Composite getComposite();
/*     */   
/*     */   void setVisible(boolean paramBoolean);
/*     */   
/*     */   boolean isVisible();
/*     */   
/*     */   void setClip(ClipRable paramClipRable);
/*     */   
/*     */   ClipRable getClip();
/*     */   
/*     */   void setRenderingHint(RenderingHints.Key paramKey, Object paramObject);
/*     */   
/*     */   void setRenderingHints(Map paramMap);
/*     */   
/*     */   void setRenderingHints(RenderingHints paramRenderingHints);
/*     */   
/*     */   RenderingHints getRenderingHints();
/*     */   
/*     */   void setMask(Mask paramMask);
/*     */   
/*     */   Mask getMask();
/*     */   
/*     */   void setFilter(Filter paramFilter);
/*     */   
/*     */   Filter getFilter();
/*     */   
/*     */   Filter getGraphicsNodeRable(boolean paramBoolean);
/*     */   
/*     */   Filter getEnableBackgroundGraphicsNodeRable(boolean paramBoolean);
/*     */   
/*     */   void paint(Graphics2D paramGraphics2D);
/*     */   
/*     */   void primitivePaint(Graphics2D paramGraphics2D);
/*     */   
/*     */   CompositeGraphicsNode getParent();
/*     */   
/*     */   RootGraphicsNode getRoot();
/*     */   
/*     */   Rectangle2D getBounds();
/*     */   
/*     */   Rectangle2D getTransformedBounds(AffineTransform paramAffineTransform);
/*     */   
/*     */   Rectangle2D getPrimitiveBounds();
/*     */   
/*     */   Rectangle2D getTransformedPrimitiveBounds(AffineTransform paramAffineTransform);
/*     */   
/*     */   Rectangle2D getGeometryBounds();
/*     */   
/*     */   Rectangle2D getTransformedGeometryBounds(AffineTransform paramAffineTransform);
/*     */   
/*     */   Rectangle2D getSensitiveBounds();
/*     */   
/*     */   Rectangle2D getTransformedSensitiveBounds(AffineTransform paramAffineTransform);
/*     */   
/*     */   boolean contains(Point2D paramPoint2D);
/*     */   
/*     */   boolean intersects(Rectangle2D paramRectangle2D);
/*     */   
/*     */   GraphicsNode nodeHitAt(Point2D paramPoint2D);
/*     */   
/*     */   Shape getOutline();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/GraphicsNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */