/*     */ package net.a.a.g;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.a.a.b;
/*     */ import net.a.a.c.d;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.views.AbstractView;
/*     */ import org.w3c.dom.views.DocumentView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   implements g, EventListener, AbstractView
/*     */ {
/*     */   private static final Log b;
/*     */   private final h c;
/*     */   private final Map<Node, d> d;
/*     */   private final net.a.a.c e;
/*     */   private final Graphics2D f;
/*     */   
/*     */   static {
/*  56 */     b = LogFactory.getLog(c.class);
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
/*     */   public c(Node paramNode, net.a.a.c paramc, Graphics2D paramGraphics2D) {
/*  81 */     if (!a && paramNode == null) throw new AssertionError("Node must not be null"); 
/*  82 */     if (!a && paramc == null) throw new AssertionError("LayoutContext must not be null"); 
/*  83 */     if (paramNode instanceof h) {
/*  84 */       this.c = (h)paramNode;
/*     */     } else {
/*  86 */       this.c = (h)b.a().a(paramNode, true, true);
/*     */     } 
/*     */     
/*  89 */     if (paramGraphics2D == null) {
/*  90 */       BufferedImage bufferedImage = new BufferedImage(1, 1, 2);
/*     */       
/*  92 */       this.f = (Graphics2D)bufferedImage.getGraphics();
/*     */     } else {
/*  94 */       this.f = paramGraphics2D;
/*     */     } 
/*  96 */     this.e = paramc;
/*  97 */     this.d = new HashMap<Node, d>();
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
/*     */   public static net.a.a.e.b.a a(net.a.a.e.b.a parama, Node paramNode1, Node paramNode2) {
/* 121 */     net.a.a.e.b.a a1 = b.a().a(paramNode2);
/*     */ 
/*     */     
/* 124 */     if (paramNode2.getParentNode().getParentNode() == null) {
/* 125 */       return a1;
/*     */     }
/* 127 */     Node node1 = parama.importNode(a1.getDocumentElement(), true);
/*     */     
/* 129 */     ArrayList<Integer> arrayList = new ArrayList();
/* 130 */     Node node2 = paramNode1;
/*     */     
/* 132 */     while (node2.getParentNode() != null) {
/* 133 */       byte b = 0;
/* 134 */       while (node2.getPreviousSibling() != null) {
/* 135 */         node2 = node2.getPreviousSibling();
/* 136 */         b--;
/*     */       } 
/* 138 */       arrayList.add(Integer.valueOf(-b));
/* 139 */       node2 = node2.getParentNode();
/*     */     } 
/*     */     
/* 142 */     node2 = parama.getDocumentElement();
/* 143 */     for (int i = arrayList.size() - 2; i > 0; i--) {
/* 144 */       node2 = node2.getChildNodes().item(((Integer)arrayList.get(i)).intValue());
/*     */     }
/*     */     
/* 147 */     Node node3 = node2.getChildNodes().item(((Integer)arrayList.get(0)).intValue());
/*     */     
/* 149 */     b.debug("replace " + node3.getNodeName() + " with " + node1
/* 150 */         .getNodeName() + " under " + node2
/* 151 */         .getNodeName());
/*     */     
/* 153 */     node2.insertBefore(node1, node3);
/* 154 */     node2.removeChild(node3);
/*     */     
/* 156 */     return parama;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentView getDocument() {
/* 162 */     return this.c;
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
/*     */   public void a(Graphics2D paramGraphics2D, float paramFloat1, float paramFloat2) {
/* 178 */     e();
/* 179 */     RenderingHints renderingHints = paramGraphics2D.getRenderingHints();
/* 180 */     if (((Boolean)this.e.a(d.h)).booleanValue()) {
/* 181 */       renderingHints.add(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
/*     */     }
/*     */     
/* 184 */     renderingHints.add(new RenderingHints(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE));
/*     */     
/* 186 */     renderingHints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
/*     */     
/* 188 */     paramGraphics2D.setRenderingHints(renderingHints);
/*     */     
/* 190 */     boolean bool = ((Boolean)this.e
/* 191 */       .a(d.g)).booleanValue();
/* 192 */     a(this.c, paramGraphics2D, paramFloat1, paramFloat2, bool);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(i parami, Graphics2D paramGraphics2D, float paramFloat1, float paramFloat2, boolean paramBoolean) {
/* 199 */     d d = a(parami);
/* 200 */     if (paramBoolean) {
/* 201 */       float f1 = paramFloat1;
/* 202 */       float f2 = paramFloat1 + d.d(f.c);
/* 203 */       float f3 = paramFloat2 - d.b(f.c);
/* 204 */       float f4 = paramFloat2 + d.c(f.c);
/* 205 */       paramGraphics2D.setColor(Color.BLUE);
/* 206 */       paramGraphics2D.draw(new Line2D.Float(f1, f3, f2, f3));
/* 207 */       paramGraphics2D.draw(new Line2D.Float(f1, f3, f1, f4));
/* 208 */       paramGraphics2D.draw(new Line2D.Float(f2, f3, f2, f4));
/* 209 */       paramGraphics2D.draw(new Line2D.Float(f1, f4, f2, f4));
/* 210 */       paramGraphics2D.setColor(Color.RED);
/* 211 */       paramGraphics2D.draw(new Line2D.Float(f1, paramFloat2, f2, paramFloat2));
/*     */     } 
/* 213 */     for (b b : d.e()) {
/* 214 */       b.a(paramFloat1, paramFloat2, paramGraphics2D);
/*     */     }
/*     */     
/* 217 */     for (i i1 : parami.c()) {
/* 218 */       d d1 = a(i1);
/* 219 */       a(i1, paramGraphics2D, paramFloat1 + d1
/* 220 */           .f(f.c), paramFloat2 + d1
/* 221 */           .g(f.c), paramBoolean);
/*     */     } 
/*     */   }
/*     */   
/*     */   private d e() {
/* 226 */     return a(this.c, f.c, this.e);
/*     */   }
/*     */ 
/*     */   
/*     */   private d a(i parami, f paramf, net.a.a.c paramc) {
/* 231 */     d d = a(parami);
/*     */     
/* 233 */     if (parami instanceof EventTarget) {
/* 234 */       EventTarget eventTarget = (EventTarget)parami;
/* 235 */       eventTarget.addEventListener("DOMSubtreeModified", this, false);
/* 236 */       eventTarget.addEventListener("MOEvent", this, false);
/*     */     } 
/*     */     
/* 239 */     if (f.a.equals(d.a())) {
/* 240 */       f f1 = f.c;
/* 241 */       byte b = 0;
/* 242 */       for (i i1 : parami.b()) {
/* 243 */         d d1 = a(i1, f.b, parami
/* 244 */             .a(b, paramc));
/* 245 */         b++;
/* 246 */         if (f.b.equals(d1.a())) {
/* 247 */           f1 = f.b;
/*     */         }
/*     */       } 
/* 250 */       parami.b(this, d, f1, paramc);
/*     */     } 
/* 252 */     if (f.b.equals(d.a()) && f.c
/* 253 */       .equals(paramf)) {
/* 254 */       byte b = 0;
/* 255 */       for (i i1 : parami.b()) {
/* 256 */         a(i1, f.c, parami
/* 257 */             .a(b, paramc));
/* 258 */         b++;
/*     */       } 
/* 260 */       parami.a(this, d, paramc);
/*     */     } 
/* 262 */     return d;
/*     */   }
/*     */ 
/*     */   
/*     */   public d a(i parami) {
/* 267 */     if (parami == null) {
/* 268 */       return null;
/*     */     }
/* 270 */     d d = this.d.get(parami);
/* 271 */     if (d == null) {
/* 272 */       d = new e();
/* 273 */       this.d.put(parami, d);
/*     */     } 
/* 275 */     return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float a() {
/* 282 */     d d = e();
/* 283 */     return d.d(f.c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float b() {
/* 290 */     d d = e();
/* 291 */     return d.b(f.c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float c() {
/* 298 */     d d = e();
/* 299 */     return d.c(f.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public Graphics2D d() {
/* 304 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleEvent(Event paramEvent) {
/* 309 */     EventTarget eventTarget = paramEvent.getCurrentTarget();
/* 310 */     if (eventTarget instanceof i) {
/* 311 */       i i = (i)eventTarget;
/* 312 */       d d = a(i);
/* 313 */       d.a(f.a);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class a
/*     */   {
/*     */     private final Node a;
/*     */     
/*     */     private final Rectangle2D b;
/*     */ 
/*     */     
/*     */     private a(Node param1Node, Rectangle2D param1Rectangle2D) {
/* 327 */       this.a = param1Node;
/* 328 */       this.b = param1Rectangle2D;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Node a() {
/* 335 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Rectangle2D b() {
/* 342 */       return this.b;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 348 */       StringBuilder stringBuilder = new StringBuilder();
/* 349 */       stringBuilder.append(this.a).append('/').append(this.b);
/* 350 */       return stringBuilder.toString();
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
/*     */   public List<a> a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 370 */     e();
/* 371 */     LinkedList<a> linkedList = new LinkedList();
/* 372 */     a(paramFloat1, paramFloat2, paramFloat3, paramFloat4, this.c, linkedList);
/* 373 */     return linkedList;
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
/*     */   private void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Node paramNode, List<a> paramList) {
/* 396 */     if (paramNode instanceof i) {
/* 397 */       d d = this.d.get(paramNode);
/*     */ 
/*     */       
/* 400 */       f f = d.a();
/*     */ 
/*     */       
/* 403 */       float f1 = d.f(f) + paramFloat3;
/*     */       
/* 405 */       float f2 = d.g(f) + paramFloat4 - d.b(f);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 410 */       Rectangle2D.Float float_ = new Rectangle2D.Float(f1, f2, d.d(f), d.b(f) + d.c(f));
/*     */ 
/*     */       
/* 413 */       if (float_.contains(paramFloat1, paramFloat2)) {
/* 414 */         paramList.add(new a(paramNode, float_));
/*     */ 
/*     */         
/* 417 */         NodeList nodeList = paramNode.getChildNodes();
/* 418 */         for (byte b = 0; b < nodeList.getLength(); b++) {
/* 419 */           a(paramFloat1, paramFloat2, f1, f2 + d
/* 420 */               .b(f), nodeList.item(b), paramList);
/*     */         }
/*     */       } 
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
/*     */   public Rectangle2D a(float paramFloat1, float paramFloat2, i parami) {
/*     */     Rectangle2D.Float float_;
/* 444 */     e();
/* 445 */     d d = this.d.get(parami);
/*     */     
/* 447 */     if (d == null) {
/* 448 */       float_ = null;
/*     */     } else {
/* 450 */       i i1 = parami;
/* 451 */       float f1 = d.f(f.c) + paramFloat1;
/*     */       
/* 453 */       float f2 = d.g(f.c) + paramFloat2 - d.b(f.c);
/* 454 */       while (i1.getParentNode() instanceof i) {
/* 455 */         i1 = (i)i1.getParentNode();
/* 456 */         d d1 = this.d.get(i1);
/* 457 */         f1 += d1.f(f.c);
/* 458 */         f2 += d1.g(f.c);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 463 */       float_ = new Rectangle2D.Float(f1, f2, d.d(f.c), d.b(f.c) + d.c(f.c));
/*     */     } 
/* 465 */     return float_;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/g/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */