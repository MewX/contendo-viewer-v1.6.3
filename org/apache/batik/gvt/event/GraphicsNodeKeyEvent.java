/*     */ package org.apache.batik.gvt.event;
/*     */ 
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
/*     */ public class GraphicsNodeKeyEvent
/*     */   extends GraphicsNodeInputEvent
/*     */ {
/*     */   static final int KEY_FIRST = 400;
/*     */   public static final int KEY_TYPED = 400;
/*     */   public static final int KEY_PRESSED = 401;
/*     */   public static final int KEY_RELEASED = 402;
/*     */   protected int keyCode;
/*     */   protected char keyChar;
/*     */   protected int keyLocation;
/*     */   
/*     */   public GraphicsNodeKeyEvent(GraphicsNode source, int id, long when, int modifiers, int lockState, int keyCode, char keyChar, int keyLocation) {
/*  89 */     super(source, id, when, modifiers, lockState);
/*  90 */     this.keyCode = keyCode;
/*  91 */     this.keyChar = keyChar;
/*  92 */     this.keyLocation = keyLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKeyCode() {
/*  99 */     return this.keyCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getKeyChar() {
/* 107 */     return this.keyChar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKeyLocation() {
/* 114 */     return this.keyLocation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeKeyEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */