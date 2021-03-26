/*    */ package net.a.a.i;
/*    */ 
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.util.List;
/*    */ import net.a.a.g.c;
/*    */ import org.w3c.dom.Node;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class d
/*    */   implements MouseListener
/*    */ {
/*    */   private final b a;
/*    */   
/*    */   public d(b paramb) {
/* 48 */     this.a = paramb;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void mouseClicked(MouseEvent paramMouseEvent) {
/* 58 */     e e = this.a.l();
/* 59 */     List<c.a> list = e.a(this.a, paramMouseEvent.getX(), paramMouseEvent.getY());
/* 60 */     if (list != null && list.size() > 0) {
/* 61 */       Node node = ((c.a)list.get(list.size() - 1)).a();
/* 62 */       this.a.a().a(node);
/*    */     } 
/*    */   }
/*    */   
/*    */   public final void mousePressed(MouseEvent paramMouseEvent) {}
/*    */   
/*    */   public final void mouseReleased(MouseEvent paramMouseEvent) {}
/*    */   
/*    */   public final void mouseEntered(MouseEvent paramMouseEvent) {}
/*    */   
/*    */   public final void mouseExited(MouseEvent paramMouseEvent) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/i/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */