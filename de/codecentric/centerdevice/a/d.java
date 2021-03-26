/*    */ package de.codecentric.centerdevice.a;
/*    */ 
/*    */ import com.sun.glass.ui.Application;
/*    */ import com.sun.glass.ui.Menu;
/*    */ import com.sun.glass.ui.MenuBar;
/*    */ import com.sun.glass.ui.MenuItem;
/*    */ import com.sun.javafx.menu.MenuBase;
/*    */ import com.sun.javafx.tk.TKSystemMenu;
/*    */ import com.sun.javafx.tk.Toolkit;
/*    */ import de.codecentric.centerdevice.e.b;
/*    */ import java.lang.invoke.MethodHandle;
/*    */ import java.lang.invoke.MethodHandles;
/*    */ import java.lang.reflect.Field;
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
/*    */ {
/*    */   private static final String a = "glassSystemMenuBar";
/*    */   private static final String b = "insertMenu";
/*    */   private static final int c = 0;
/* 30 */   private TKSystemMenu d = b(); private MethodHandle e;
/*    */   public d() throws ReflectiveOperationException {
/* 32 */     Field glassSystemMenuBar = b.a(this.d, "glassSystemMenuBar");
/* 33 */     this.f = MethodHandles.lookup().unreflectGetter(glassSystemMenuBar);
/* 34 */     this.g = MethodHandles.lookup().unreflectSetter(glassSystemMenuBar);
/*    */     
/* 36 */     this.e = b.a(this.d, "insertMenu", new Class[] { Menu.class, MenuBase.class, int.class });
/*    */   }
/*    */   private MethodHandle f; private MethodHandle g;
/*    */   private static TKSystemMenu b() {
/* 40 */     return Toolkit.getToolkit().getSystemMenu();
/*    */   }
/*    */   
/*    */   public void a(MenuBase menu) throws Throwable {
/* 44 */     MenuBar glassSystemMenuBar = a();
/* 45 */     if (glassSystemMenuBar == null) {
/* 46 */       c();
/*    */     } else {
/* 48 */       a(glassSystemMenuBar, 0);
/*    */     } 
/* 50 */     a(menu, 0);
/*    */   }
/*    */   
/*    */   private void a(MenuBase menu, int index) throws Throwable {
/* 54 */     this.e.invoke(this.d, null, menu, index);
/*    */   }
/*    */   
/*    */   private void c() throws Throwable {
/* 58 */     this.g.invoke(this.d, Application.GetApplication().createMenuBar());
/*    */   }
/*    */   
/*    */   public MenuBar a() throws Throwable {
/* 62 */     return this.f.invoke(this.d);
/*    */   }
/*    */   
/*    */   protected void a(MenuBar bar) throws Throwable {
/* 66 */     this.g.invoke(this.d, bar);
/*    */   }
/*    */   
/*    */   public void a(MenuBar glassSystemMenuBar, int index) {
/* 70 */     if (glassSystemMenuBar.getMenus().size() <= index) {
/*    */       return;
/*    */     }
/* 73 */     a(glassSystemMenuBar.getMenus().get(index));
/* 74 */     glassSystemMenuBar.remove(index);
/*    */   }
/*    */   
/*    */   private void a(Menu menu) {
/* 78 */     for (int i = menu.getItems().size() - 1; i >= 0; i--) {
/* 79 */       Object o = menu.getItems().get(i);
/*    */       
/* 81 */       if (o instanceof MenuItem) {
/* 82 */         ((MenuItem)o).setCallback(null);
/* 83 */       } else if (o instanceof Menu) {
/* 84 */         a((Menu)o);
/*    */       } 
/*    */     } 
/* 87 */     menu.setEventHandler(null);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */