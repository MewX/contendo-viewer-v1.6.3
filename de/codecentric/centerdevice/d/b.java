/*    */ package de.codecentric.centerdevice.d;
/*    */ 
/*    */ import de.codecentric.centerdevice.e.c;
/*    */ import java.lang.ref.WeakReference;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import javafx.beans.Observable;
/*    */ import javafx.beans.binding.Bindings;
/*    */ import javafx.collections.FXCollections;
/*    */ import javafx.collections.ListChangeListener;
/*    */ import javafx.collections.ObservableList;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.scene.control.CheckMenuItem;
/*    */ import javafx.scene.control.Menu;
/*    */ import javafx.scene.control.MenuItem;
/*    */ import javafx.stage.Stage;
/*    */ import javafx.stage.Window;
/*    */ 
/*    */ public class b implements ListChangeListener<Stage> {
/*    */   private final WeakReference<Menu> a;
/*    */   private final Map<Stage, CheckMenuItem> b;
/*    */   private final ObservableList<Stage> c;
/*    */   
/*    */   public b(Menu windowMenu) {
/* 27 */     this.a = new WeakReference<>(windowMenu);
/* 28 */     this.b = new HashMap<>();
/*    */     
/* 30 */     a((List<? extends Stage>)c.f());
/*    */     
/* 32 */     this.c = FXCollections.observableArrayList(stage -> new Observable[] { (Observable)stage.focusedProperty() });
/* 33 */     Bindings.bindContent((List)this.c, c.f());
/*    */     
/* 35 */     this.c.addListener(c -> a());
/*    */   }
/*    */   
/*    */   private void a() {
/* 39 */     Optional<Stage> focusedStage = this.c.stream().filter(Window::isFocused).findFirst();
/* 40 */     this.b.entrySet().forEach(entry -> ((CheckMenuItem)entry.getValue()).setSelected(
/* 41 */           (focusedStage.isPresent() && ((Stage)focusedStage.get()).equals(entry.getKey()))));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onChanged(ListChangeListener.Change<? extends Stage> c) {
/* 46 */     while (c.next()) {
/* 47 */       a(c.getAddedSubList(), c.getRemoved());
/*    */     }
/*    */   }
/*    */   
/*    */   private void a(List<? extends Stage> add, List<? extends Stage> remove) {
/* 52 */     b(remove);
/* 53 */     a(add);
/*    */   }
/*    */   
/*    */   private void a(List<? extends Stage> add) {
/* 57 */     Menu menu = this.a.get();
/* 58 */     if (add != null && menu != null) {
/* 59 */       add.forEach(stage -> b(stage, menu));
/*    */     }
/*    */   }
/*    */   
/*    */   private void b(List<? extends Stage> remove) {
/* 64 */     Menu menu = this.a.get();
/* 65 */     if (remove != null && menu != null) {
/* 66 */       remove.forEach(stage -> a(stage, menu));
/*    */     }
/*    */   }
/*    */   
/*    */   private void a(Stage stage, Menu menu) {
/* 71 */     MenuItem menuItem = (MenuItem)this.b.get(stage);
/* 72 */     if (menuItem != null) {
/* 73 */       menu.getItems().remove(menuItem);
/*    */     }
/*    */   }
/*    */   
/*    */   private void b(Stage stage, Menu menu) {
/* 78 */     CheckMenuItem item = new CheckMenuItem(stage.getTitle());
/* 79 */     item.setOnAction(event -> stage.toFront());
/* 80 */     this.b.put(stage, item);
/* 81 */     menu.getItems().add(item);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/d/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */