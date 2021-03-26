/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.text.CharacterIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.RootGraphicsNode;
/*     */ import org.apache.batik.gvt.Selectable;
/*     */ import org.apache.batik.gvt.Selector;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeChangeEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeChangeListener;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeKeyEvent;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeMouseEvent;
/*     */ import org.apache.batik.gvt.event.SelectionEvent;
/*     */ import org.apache.batik.gvt.event.SelectionListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConcreteTextSelector
/*     */   implements Selector
/*     */ {
/*     */   private ArrayList listeners;
/*     */   private GraphicsNode selectionNode;
/*     */   private RootGraphicsNode selectionNodeRoot;
/*     */   
/*     */   public void mouseClicked(GraphicsNodeMouseEvent evt) {
/*  54 */     checkSelectGesture((GraphicsNodeEvent)evt);
/*     */   }
/*     */   
/*     */   public void mouseDragged(GraphicsNodeMouseEvent evt) {
/*  58 */     checkSelectGesture((GraphicsNodeEvent)evt);
/*     */   }
/*     */   
/*     */   public void mouseEntered(GraphicsNodeMouseEvent evt) {
/*  62 */     checkSelectGesture((GraphicsNodeEvent)evt);
/*     */   }
/*     */   
/*     */   public void mouseExited(GraphicsNodeMouseEvent evt) {
/*  66 */     checkSelectGesture((GraphicsNodeEvent)evt);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseMoved(GraphicsNodeMouseEvent evt) {}
/*     */   
/*     */   public void mousePressed(GraphicsNodeMouseEvent evt) {
/*  73 */     checkSelectGesture((GraphicsNodeEvent)evt);
/*     */   }
/*     */   
/*     */   public void mouseReleased(GraphicsNodeMouseEvent evt) {
/*  77 */     checkSelectGesture((GraphicsNodeEvent)evt);
/*     */   }
/*     */   
/*     */   public void keyPressed(GraphicsNodeKeyEvent evt) {
/*  81 */     report((GraphicsNodeEvent)evt, "keyPressed");
/*     */   }
/*     */   
/*     */   public void keyReleased(GraphicsNodeKeyEvent evt) {
/*  85 */     report((GraphicsNodeEvent)evt, "keyReleased");
/*     */   }
/*     */   
/*     */   public void keyTyped(GraphicsNodeKeyEvent evt) {
/*  89 */     report((GraphicsNodeEvent)evt, "keyTyped");
/*     */   }
/*     */ 
/*     */   
/*     */   public void changeStarted(GraphicsNodeChangeEvent gnce) {}
/*     */   
/*     */   public void changeCompleted(GraphicsNodeChangeEvent gnce) {
/*  96 */     if (this.selectionNode == null) {
/*     */       return;
/*     */     }
/*  99 */     Shape newShape = ((Selectable)this.selectionNode).getHighlightShape();
/*     */     
/* 101 */     dispatchSelectionEvent(new SelectionEvent(getSelection(), 1, newShape));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelection(Mark begin, Mark end) {
/* 108 */     TextNode node = begin.getTextNode();
/* 109 */     if (node != end.getTextNode()) {
/* 110 */       throw new RuntimeException("Markers not from same TextNode");
/*     */     }
/* 112 */     node.setSelection(begin, end);
/* 113 */     this.selectionNode = (GraphicsNode)node;
/* 114 */     this.selectionNodeRoot = node.getRoot();
/* 115 */     Object selection = getSelection();
/* 116 */     Shape shape = node.getHighlightShape();
/* 117 */     dispatchSelectionEvent(new SelectionEvent(selection, 2, shape));
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearSelection() {
/* 122 */     if (this.selectionNode == null) {
/*     */       return;
/*     */     }
/* 125 */     dispatchSelectionEvent(new SelectionEvent(null, 3, null));
/*     */     
/* 127 */     this.selectionNode = null;
/* 128 */     this.selectionNodeRoot = null;
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
/*     */   protected void checkSelectGesture(GraphicsNodeEvent evt) {
/* 140 */     GraphicsNodeMouseEvent mevt = null;
/* 141 */     if (evt instanceof GraphicsNodeMouseEvent) {
/* 142 */       mevt = (GraphicsNodeMouseEvent)evt;
/*     */     }
/*     */     
/* 145 */     GraphicsNode source = evt.getGraphicsNode();
/* 146 */     if (isDeselectGesture(evt)) {
/* 147 */       if (this.selectionNode != null) {
/* 148 */         this.selectionNodeRoot.removeTreeGraphicsNodeChangeListener((GraphicsNodeChangeListener)this);
/*     */       }
/* 150 */       clearSelection();
/* 151 */     } else if (mevt != null) {
/*     */       
/* 153 */       Point2D p = mevt.getPoint2D();
/*     */       
/* 155 */       if (source instanceof Selectable && isSelectStartGesture(evt)) {
/*     */         
/* 157 */         if (this.selectionNode != source) {
/* 158 */           if (this.selectionNode != null) {
/* 159 */             this.selectionNodeRoot.removeTreeGraphicsNodeChangeListener((GraphicsNodeChangeListener)this);
/*     */           }
/*     */           
/* 162 */           this.selectionNode = source;
/* 163 */           if (source != null) {
/* 164 */             this.selectionNodeRoot = source.getRoot();
/* 165 */             this.selectionNodeRoot.addTreeGraphicsNodeChangeListener((GraphicsNodeChangeListener)this);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 170 */         ((Selectable)source).selectAt(p.getX(), p.getY());
/* 171 */         dispatchSelectionEvent(new SelectionEvent(null, 4, null));
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 176 */       else if (isSelectEndGesture(evt)) {
/* 177 */         if (this.selectionNode == source) {
/* 178 */           ((Selectable)source).selectTo(p.getX(), p.getY());
/*     */         }
/* 180 */         Object oldSelection = getSelection();
/* 181 */         if (this.selectionNode != null)
/*     */         {
/* 183 */           Shape newShape = ((Selectable)this.selectionNode).getHighlightShape();
/* 184 */           dispatchSelectionEvent(new SelectionEvent(oldSelection, 2, newShape));
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 189 */       else if (isSelectContinueGesture(evt)) {
/*     */         
/* 191 */         if (this.selectionNode == source) {
/* 192 */           boolean result = ((Selectable)source).selectTo(p.getX(), p.getY());
/*     */           
/* 194 */           if (result) {
/* 195 */             Shape newShape = ((Selectable)this.selectionNode).getHighlightShape();
/*     */ 
/*     */             
/* 198 */             dispatchSelectionEvent(new SelectionEvent(null, 1, newShape));
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 204 */       else if (source instanceof Selectable && isSelectAllGesture(evt)) {
/*     */         
/* 206 */         if (this.selectionNode != source) {
/* 207 */           if (this.selectionNode != null) {
/* 208 */             this.selectionNodeRoot.removeTreeGraphicsNodeChangeListener((GraphicsNodeChangeListener)this);
/*     */           }
/*     */           
/* 211 */           this.selectionNode = source;
/* 212 */           if (source != null) {
/* 213 */             this.selectionNodeRoot = source.getRoot();
/* 214 */             this.selectionNodeRoot.addTreeGraphicsNodeChangeListener((GraphicsNodeChangeListener)this);
/*     */           } 
/*     */         } 
/*     */         
/* 218 */         ((Selectable)source).selectAll(p.getX(), p.getY());
/* 219 */         Object oldSelection = getSelection();
/* 220 */         Shape newShape = ((Selectable)source).getHighlightShape();
/*     */         
/* 222 */         dispatchSelectionEvent(new SelectionEvent(oldSelection, 2, newShape));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isDeselectGesture(GraphicsNodeEvent evt) {
/* 231 */     return (evt.getID() == 500 && ((GraphicsNodeMouseEvent)evt).getClickCount() == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isSelectStartGesture(GraphicsNodeEvent evt) {
/* 236 */     return (evt.getID() == 501);
/*     */   }
/*     */   
/*     */   private boolean isSelectEndGesture(GraphicsNodeEvent evt) {
/* 240 */     return (evt.getID() == 502);
/*     */   }
/*     */   
/*     */   private boolean isSelectContinueGesture(GraphicsNodeEvent evt) {
/* 244 */     return (evt.getID() == 506);
/*     */   }
/*     */   
/*     */   private boolean isSelectAllGesture(GraphicsNodeEvent evt) {
/* 248 */     return (evt.getID() == 500 && ((GraphicsNodeMouseEvent)evt).getClickCount() == 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getSelection() {
/* 256 */     Object value = null;
/* 257 */     if (this.selectionNode instanceof Selectable) {
/* 258 */       value = ((Selectable)this.selectionNode).getSelection();
/*     */     }
/* 260 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 267 */     return (getSelection() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispatchSelectionEvent(SelectionEvent e) {
/* 274 */     if (this.listeners != null) {
/* 275 */       Iterator<SelectionListener> iter = this.listeners.iterator();
/* 276 */       switch (e.getID()) {
/*     */         case 2:
/* 278 */           while (iter.hasNext()) {
/* 279 */             ((SelectionListener)iter.next()).selectionDone(e);
/*     */           }
/*     */           break;
/*     */         case 1:
/* 283 */           while (iter.hasNext()) {
/* 284 */             ((SelectionListener)iter.next()).selectionChanged(e);
/*     */           }
/*     */           break;
/*     */         case 3:
/* 288 */           while (iter.hasNext()) {
/* 289 */             ((SelectionListener)iter.next()).selectionCleared(e);
/*     */           }
/*     */           break;
/*     */         case 4:
/* 293 */           while (iter.hasNext()) {
/* 294 */             ((SelectionListener)iter.next()).selectionStarted(e);
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSelectionListener(SelectionListener l) {
/* 306 */     if (this.listeners == null) {
/* 307 */       this.listeners = new ArrayList();
/*     */     }
/* 309 */     this.listeners.add(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSelectionListener(SelectionListener l) {
/* 317 */     if (this.listeners != null) {
/* 318 */       this.listeners.remove(l);
/*     */     }
/*     */   }
/*     */   
/*     */   private void report(GraphicsNodeEvent evt, String message) {
/* 323 */     GraphicsNode source = evt.getGraphicsNode();
/* 324 */     String label = "(non-text node)";
/* 325 */     if (source instanceof TextNode) {
/*     */       
/* 327 */       CharacterIterator iter = ((TextNode)source).getAttributedCharacterIterator();
/*     */       
/* 329 */       char[] cbuff = new char[iter.getEndIndex()];
/* 330 */       if (cbuff.length > 0) cbuff[0] = iter.first(); 
/* 331 */       for (int i = 1; i < cbuff.length; i++) {
/* 332 */         cbuff[i] = iter.next();
/*     */       }
/* 334 */       label = new String(cbuff);
/*     */     } 
/* 336 */     System.out.println("Mouse " + message + " in " + label);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/ConcreteTextSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */