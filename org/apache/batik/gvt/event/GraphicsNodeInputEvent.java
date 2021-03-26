/*     */ package org.apache.batik.gvt.event;
/*     */ 
/*     */ import java.awt.event.InputEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class GraphicsNodeInputEvent
/*     */   extends GraphicsNodeEvent
/*     */ {
/*     */   public static final int SHIFT_MASK = 1;
/*     */   public static final int CTRL_MASK = 2;
/*     */   public static final int META_MASK = 4;
/*     */   public static final int ALT_MASK = 8;
/*     */   public static final int ALT_GRAPH_MASK = 32;
/*     */   public static final int BUTTON1_MASK = 1024;
/*     */   public static final int BUTTON2_MASK = 2048;
/*     */   public static final int BUTTON3_MASK = 4096;
/*     */   public static final int CAPS_LOCK_MASK = 1;
/*     */   public static final int NUM_LOCK_MASK = 2;
/*     */   public static final int SCROLL_LOCK_MASK = 4;
/*     */   public static final int KANA_LOCK_MASK = 8;
/*     */   long when;
/*     */   int modifiers;
/*     */   int lockState;
/*     */   
/*     */   protected GraphicsNodeInputEvent(GraphicsNode source, int id, long when, int modifiers, int lockState) {
/* 121 */     super(source, id);
/* 122 */     this.when = when;
/* 123 */     this.modifiers = modifiers;
/* 124 */     this.lockState = lockState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNodeInputEvent(GraphicsNode source, InputEvent evt, int lockState) {
/* 135 */     super(source, evt.getID());
/* 136 */     this.when = evt.getWhen();
/* 137 */     this.modifiers = evt.getModifiers();
/* 138 */     this.lockState = lockState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShiftDown() {
/* 145 */     return ((this.modifiers & 0x1) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isControlDown() {
/* 152 */     return ((this.modifiers & 0x2) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMetaDown() {
/* 159 */     return AWTEventDispatcher.isMetaDown(this.modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAltDown() {
/* 166 */     return ((this.modifiers & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAltGraphDown() {
/* 173 */     return ((this.modifiers & 0x20) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getWhen() {
/* 180 */     return this.when;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getModifiers() {
/* 187 */     return this.modifiers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLockState() {
/* 194 */     return this.lockState;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/event/GraphicsNodeInputEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */