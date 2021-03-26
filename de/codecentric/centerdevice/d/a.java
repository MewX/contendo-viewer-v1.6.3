/*    */ package de.codecentric.centerdevice.d;
/*    */ 
/*    */ import de.codecentric.centerdevice.e.c;
/*    */ import javafx.collections.ListChangeListener;
/*    */ import javafx.scene.control.MenuBar;
/*    */ import javafx.stage.Stage;
/*    */ 
/*    */ public class a
/*    */   implements ListChangeListener<Stage>
/*    */ {
/*    */   private static MenuBar a;
/* 12 */   private static a b = null;
/*    */   
/*    */   public static void a(MenuBar menuBar) {
/* 15 */     a = menuBar;
/*    */     
/* 17 */     if (b == null) {
/* 18 */       b = new a();
/* 19 */       c.f().addListener(b);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void a() {
/* 24 */     if (b != null) {
/* 25 */       c.f().removeListener(b);
/* 26 */       b = null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onChanged(ListChangeListener.Change<? extends Stage> stageChanges) {
/* 35 */     while (stageChanges.next())
/* 36 */       stageChanges.getAddedSubList().forEach(stage -> de.codecentric.centerdevice.e.a.a(stage, a)); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */