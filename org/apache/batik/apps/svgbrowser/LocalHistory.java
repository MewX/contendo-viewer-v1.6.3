/*     */ package org.apache.batik.apps.svgbrowser;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JRadioButtonMenuItem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LocalHistory
/*     */ {
/*     */   protected JSVGViewerFrame svgFrame;
/*     */   protected JMenu menu;
/*     */   protected int index;
/*  58 */   protected List visitedURIs = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected int currentURI = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   protected ButtonGroup group = new ButtonGroup();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected ActionListener actionListener = new RadioListener();
/*     */ 
/*     */ 
/*     */   
/*     */   protected int state;
/*     */ 
/*     */   
/*     */   protected static final int STABLE_STATE = 0;
/*     */ 
/*     */   
/*     */   protected static final int BACK_PENDING_STATE = 1;
/*     */ 
/*     */   
/*     */   protected static final int FORWARD_PENDING_STATE = 2;
/*     */ 
/*     */   
/*     */   protected static final int RELOAD_PENDING_STATE = 3;
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalHistory(JMenuBar mb, JSVGViewerFrame svgFrame) {
/*  94 */     this.svgFrame = svgFrame;
/*     */ 
/*     */     
/*  97 */     int mc = mb.getMenuCount();
/*  98 */     for (int i = 0; i < mc; i++) {
/*  99 */       JMenu m = mb.getMenu(i);
/* 100 */       int ic = m.getItemCount();
/* 101 */       for (int j = 0; j < ic; j++) {
/* 102 */         JMenuItem mi = m.getItem(j);
/* 103 */         if (mi != null) {
/* 104 */           String s = mi.getText();
/* 105 */           if ("@@@".equals(s)) {
/* 106 */             this.menu = m;
/* 107 */             this.index = j;
/* 108 */             m.remove(j);
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 114 */     throw new IllegalArgumentException("No '@@@' marker found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void back() {
/* 122 */     update();
/* 123 */     this.state = 1;
/* 124 */     this.currentURI -= 2;
/* 125 */     this.svgFrame.showSVGDocument(this.visitedURIs.get(this.currentURI + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canGoBack() {
/* 132 */     return (this.currentURI > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forward() {
/* 140 */     update();
/* 141 */     this.state = 2;
/* 142 */     this.svgFrame.showSVGDocument(this.visitedURIs.get(this.currentURI + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canGoForward() {
/* 149 */     return (this.currentURI < this.visitedURIs.size() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reload() {
/* 156 */     update();
/* 157 */     this.state = 3;
/* 158 */     this.currentURI--;
/* 159 */     this.svgFrame.showSVGDocument(this.visitedURIs.get(this.currentURI + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(String uri) {
/* 167 */     if (this.currentURI < -1) {
/* 168 */       throw new IllegalStateException("Unexpected currentURI:" + this.currentURI);
/*     */     }
/* 170 */     this.state = 0;
/* 171 */     if (++this.currentURI < this.visitedURIs.size()) {
/* 172 */       if (!this.visitedURIs.get(this.currentURI).equals(uri)) {
/* 173 */         int len = this.menu.getItemCount();
/* 174 */         for (int j = len - 1; j >= this.index + this.currentURI + 1; j--) {
/* 175 */           JMenuItem jMenuItem1 = this.menu.getItem(j);
/* 176 */           this.group.remove(jMenuItem1);
/* 177 */           this.menu.remove(j);
/*     */         } 
/* 179 */         this.visitedURIs = this.visitedURIs.subList(0, this.currentURI + 1);
/*     */       } 
/* 181 */       JMenuItem jMenuItem = this.menu.getItem(this.index + this.currentURI);
/* 182 */       this.group.remove(jMenuItem);
/* 183 */       this.menu.remove(this.index + this.currentURI);
/* 184 */       this.visitedURIs.set(this.currentURI, uri);
/*     */     } else {
/* 186 */       if (this.visitedURIs.size() >= 15) {
/* 187 */         this.visitedURIs.remove(0);
/* 188 */         JMenuItem jMenuItem = this.menu.getItem(this.index);
/* 189 */         this.group.remove(jMenuItem);
/* 190 */         this.menu.remove(this.index);
/* 191 */         this.currentURI--;
/*     */       } 
/* 193 */       this.visitedURIs.add(uri);
/*     */     } 
/*     */ 
/*     */     
/* 197 */     String text = uri;
/* 198 */     int i = uri.lastIndexOf('/');
/* 199 */     if (i == -1) {
/* 200 */       i = uri.lastIndexOf('\\');
/*     */     }
/*     */     
/* 203 */     if (i != -1) {
/* 204 */       text = uri.substring(i + 1);
/*     */     }
/*     */     
/* 207 */     JMenuItem mi = new JRadioButtonMenuItem(text);
/* 208 */     mi.setToolTipText(uri);
/* 209 */     mi.setActionCommand(uri);
/* 210 */     mi.addActionListener(this.actionListener);
/* 211 */     this.group.add(mi);
/* 212 */     mi.setSelected(true);
/* 213 */     this.menu.insert(mi, this.index + this.currentURI);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void update() {
/* 220 */     switch (this.state) {
/*     */       case 1:
/* 222 */         this.currentURI += 2;
/*     */         break;
/*     */       case 3:
/* 225 */         this.currentURI++;
/*     */         break;
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
/*     */   protected class RadioListener
/*     */     implements ActionListener
/*     */   {
/*     */     public void actionPerformed(ActionEvent e) {
/* 243 */       String uri = e.getActionCommand();
/* 244 */       LocalHistory.this.currentURI = getItemIndex((JMenuItem)e.getSource()) - 1;
/* 245 */       LocalHistory.this.svgFrame.showSVGDocument(uri);
/*     */     }
/*     */     
/*     */     public int getItemIndex(JMenuItem item) {
/* 249 */       int ic = LocalHistory.this.menu.getItemCount();
/* 250 */       for (int i = LocalHistory.this.index; i < ic; i++) {
/* 251 */         if (LocalHistory.this.menu.getItem(i) == item) {
/* 252 */           return i - LocalHistory.this.index;
/*     */         }
/*     */       } 
/* 255 */       throw new IllegalArgumentException("MenuItem is not from my menu!");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/LocalHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */