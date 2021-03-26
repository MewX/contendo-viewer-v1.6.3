/*     */ package org.apache.batik.gvt.event;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.geom.Point2D;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphicsNodeMouseEvent
/*     */   extends GraphicsNodeInputEvent
/*     */ {
/*     */   static final int MOUSE_FIRST = 500;
/*     */   public static final int MOUSE_CLICKED = 500;
/*     */   public static final int MOUSE_PRESSED = 501;
/*     */   public static final int MOUSE_RELEASED = 502;
/*     */   public static final int MOUSE_MOVED = 503;
/*     */   public static final int MOUSE_ENTERED = 504;
/*     */   public static final int MOUSE_EXITED = 505;
/*     */   public static final int MOUSE_DRAGGED = 506;
/*     */   float x;
/*     */   float y;
/*     */   int clientX;
/*     */   int clientY;
/*     */   int screenX;
/*     */   int screenY;
/*     */   int clickCount;
/*     */   int button;
/* 119 */   GraphicsNode relatedNode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNodeMouseEvent(GraphicsNode source, int id, long when, int modifiers, int lockState, int button, float x, float y, int clientX, int clientY, int screenX, int screenY, int clickCount, GraphicsNode relatedNode) {
/* 144 */     super(source, id, when, modifiers, lockState);
/* 145 */     this.button = button;
/* 146 */     this.x = x;
/* 147 */     this.y = y;
/* 148 */     this.clientX = clientX;
/* 149 */     this.clientY = clientY;
/* 150 */     this.screenX = screenX;
/* 151 */     this.screenY = screenY;
/* 152 */     this.clickCount = clickCount;
/* 153 */     this.relatedNode = relatedNode;
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
/*     */   public GraphicsNodeMouseEvent(GraphicsNode source, MouseEvent evt, int button, int lockState) {
/* 166 */     super(source, evt, lockState);
/* 167 */     this.button = button;
/* 168 */     this.x = evt.getX();
/* 169 */     this.y = evt.getY();
/* 170 */     this.clickCount = evt.getClickCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getButton() {
/* 177 */     return this.button;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/* 186 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/* 194 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getClientX() {
/* 203 */     return this.clientX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getClientY() {
/* 211 */     return this.clientY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getScreenX() {
/* 220 */     return this.screenX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getScreenY() {
/* 228 */     return this.screenY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point getScreenPoint() {
/* 236 */     return new Point(this.screenX, this.screenY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point getClientPoint() {
/* 244 */     return new Point(this.clientX, this.clientY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getPoint2D() {
/* 252 */     return new Point2D.Float(this.x, this.y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClickCount() {
/* 260 */     return this.clickCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getRelatedNode() {
/* 271 */     return this.relatedNode;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeMouseEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */