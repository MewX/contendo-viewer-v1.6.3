/*    */ package de.codecentric.centerdevice.e;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import javafx.collections.ObservableList;
/*    */ import javafx.scene.Group;
/*    */ import javafx.scene.Node;
/*    */ import javafx.scene.Parent;
/*    */ import javafx.scene.Scene;
/*    */ import javafx.scene.control.Menu;
/*    */ import javafx.scene.control.MenuBar;
/*    */ import javafx.scene.layout.Pane;
/*    */ import javafx.stage.Stage;
/*    */ 
/*    */ public class a {
/*    */   public static MenuBar a(List<Menu> menus) {
/* 19 */     MenuBar bar = new MenuBar();
/* 20 */     bar.setUseSystemMenuBar(true);
/* 21 */     bar.getMenus().addAll(menus);
/* 22 */     return bar;
/*    */   }
/*    */   
/*    */   public static void a(ObservableList<Node> children) {
/* 26 */     children.removeAll((Collection)children.stream().filter(node -> node instanceof MenuBar).collect(Collectors.toList()));
/*    */   }
/*    */   
/*    */   public static void a(Stage stage, MenuBar menuBar) {
/* 30 */     Scene scene = stage.getScene();
/* 31 */     if (scene != null) {
/* 32 */       ObservableList<Node> children = a(scene.getRoot());
/* 33 */       if (children != null) {
/* 34 */         a(children, menuBar);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private static ObservableList<Node> a(Parent parent) {
/* 40 */     if (parent instanceof Pane)
/* 41 */       return ((Pane)parent).getChildren(); 
/* 42 */     if (parent instanceof Group) {
/* 43 */       return ((Group)parent).getChildren();
/*    */     }
/*    */     
/* 46 */     return null;
/*    */   }
/*    */   
/*    */   public static void a(Pane pane, MenuBar menuBar) {
/* 50 */     a(pane.getChildren(), menuBar);
/*    */   }
/*    */   
/*    */   private static void a(ObservableList<Node> children, MenuBar menuBar) {
/* 54 */     b(children, a(a(menuBar)));
/*    */   }
/*    */   
/*    */   private static void b(ObservableList<Node> children, MenuBar createMenuBar) {
/* 58 */     a(children);
/* 59 */     children.add(createMenuBar);
/*    */   }
/*    */   
/*    */   private static List<Menu> a(MenuBar bar) {
/* 63 */     if (bar.getMenus().size() <= 1) {
/* 64 */       return new ArrayList<>();
/*    */     }
/* 66 */     return bar.getMenus().subList(1, bar.getMenus().size());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/e/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */