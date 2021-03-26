/*     */ package org.apache.batik.util.gui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import java.util.EventListener;
/*     */ import java.util.EventObject;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.event.EventListenerList;
/*     */ import javax.swing.plaf.basic.BasicButtonUI;
/*     */ import org.apache.batik.util.resources.ResourceManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DropDownComponent
/*     */   extends JPanel
/*     */ {
/*     */   private JButton mainButton;
/*     */   private JButton dropDownButton;
/*     */   private Icon enabledDownArrow;
/*     */   private Icon disabledDownArrow;
/*     */   private ScrollablePopupMenu popupMenu;
/*     */   private boolean isDropDownEnabled;
/*     */   
/*     */   public DropDownComponent(JButton mainButton) {
/*  97 */     super(new BorderLayout());
/*     */ 
/*     */     
/* 100 */     this.popupMenu = getPopupMenu();
/*     */     
/* 102 */     this.mainButton = mainButton;
/* 103 */     add(this.mainButton, "West");
/* 104 */     this.mainButton.setMaximumSize(new Dimension(24, 24));
/* 105 */     this.mainButton.setPreferredSize(new Dimension(24, 24));
/*     */ 
/*     */     
/* 108 */     this.enabledDownArrow = new SmallDownArrow();
/* 109 */     this.disabledDownArrow = new SmallDisabledDownArrow();
/* 110 */     this.dropDownButton = new JButton(this.disabledDownArrow);
/* 111 */     this.dropDownButton.setBorderPainted(false);
/* 112 */     this.dropDownButton.setDisabledIcon(this.disabledDownArrow);
/* 113 */     this.dropDownButton.addMouseListener(new DropDownListener());
/* 114 */     this.dropDownButton.setMaximumSize(new Dimension(18, 24));
/* 115 */     this.dropDownButton.setMinimumSize(new Dimension(18, 10));
/* 116 */     this.dropDownButton.setPreferredSize(new Dimension(18, 10));
/* 117 */     this.dropDownButton.setFocusPainted(false);
/* 118 */     add(this.dropDownButton, "East");
/*     */     
/* 120 */     setEnabled(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ScrollablePopupMenu getPopupMenu() {
/* 129 */     if (this.popupMenu == null) {
/* 130 */       this.popupMenu = new ScrollablePopupMenu(this);
/* 131 */       this.popupMenu.setEnabled(false);
/*     */ 
/*     */       
/* 134 */       this.popupMenu.addPropertyChangeListener("enabled", new PropertyChangeListener()
/*     */           {
/*     */             public void propertyChange(PropertyChangeEvent evt)
/*     */             {
/* 138 */               DropDownComponent.this.setEnabled(((Boolean)evt.getNewValue()).booleanValue());
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 144 */       this.popupMenu.addListener(new ScrollablePopupMenuAdapter()
/*     */           {
/*     */             public void itemsWereAdded(DropDownComponent.ScrollablePopupMenuEvent ev)
/*     */             {
/* 148 */               DropDownComponent.this.updateMainButtonTooltip(ev.getDetails());
/*     */             }
/*     */             
/*     */             public void itemsWereRemoved(DropDownComponent.ScrollablePopupMenuEvent ev) {
/* 152 */               DropDownComponent.this.updateMainButtonTooltip(ev.getDetails());
/*     */             }
/*     */           });
/*     */     } 
/* 156 */     return this.popupMenu;
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean enable) {
/* 160 */     this.isDropDownEnabled = enable;
/* 161 */     this.mainButton.setEnabled(enable);
/* 162 */     this.dropDownButton.setEnabled(enable);
/* 163 */     this.dropDownButton.setIcon(enable ? this.enabledDownArrow : this.disabledDownArrow);
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/* 167 */     return this.isDropDownEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateMainButtonTooltip(String newTooltip) {
/* 177 */     this.mainButton.setToolTipText(newTooltip);
/*     */   }
/*     */   
/*     */   private class DropDownListener
/*     */     extends MouseAdapter {
/*     */     private DropDownListener() {}
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 185 */       if (DropDownComponent.this.popupMenu.isShowing() && DropDownComponent.this.isDropDownEnabled) {
/* 186 */         DropDownComponent.this.popupMenu.setVisible(false);
/* 187 */       } else if (DropDownComponent.this.isDropDownEnabled) {
/* 188 */         DropDownComponent.this.popupMenu.showMenu((Component)e.getSource(), DropDownComponent.this);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseEntered(MouseEvent ev) {
/* 193 */       DropDownComponent.this.dropDownButton.setBorderPainted(true);
/*     */     }
/*     */     public void mouseExited(MouseEvent ev) {
/* 196 */       DropDownComponent.this.dropDownButton.setBorderPainted(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SmallDownArrow
/*     */     implements Icon
/*     */   {
/* 208 */     protected Color arrowColor = Color.black;
/*     */     
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {
/* 211 */       g.setColor(this.arrowColor);
/* 212 */       g.drawLine(x, y, x + 4, y);
/* 213 */       g.drawLine(x + 1, y + 1, x + 3, y + 1);
/* 214 */       g.drawLine(x + 2, y + 2, x + 2, y + 2);
/*     */     }
/*     */     
/*     */     public int getIconWidth() {
/* 218 */       return 6;
/*     */     }
/*     */     
/*     */     public int getIconHeight() {
/* 222 */       return 4;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private SmallDownArrow() {}
/*     */   }
/*     */ 
/*     */   
/*     */   private static class SmallDisabledDownArrow
/*     */     extends SmallDownArrow
/*     */   {
/*     */     public SmallDisabledDownArrow() {
/* 235 */       this.arrowColor = new Color(140, 140, 140);
/*     */     }
/*     */     
/*     */     public void paintIcon(Component c, Graphics g, int x, int y) {
/* 239 */       super.paintIcon(c, g, x, y);
/* 240 */       g.setColor(Color.white);
/* 241 */       g.drawLine(x + 3, y + 2, x + 4, y + 1);
/* 242 */       g.drawLine(x + 3, y + 3, x + 5, y + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface ScrollablePopupMenuItem
/*     */   {
/*     */     void setSelected(boolean param1Boolean);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean isSelected();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     String getText();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void setText(String param1String);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void setEnabled(boolean param1Boolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DefaultScrollablePopupMenuItem
/*     */     extends JButton
/*     */     implements ScrollablePopupMenuItem
/*     */   {
/* 299 */     public static final Color MENU_HIGHLIGHT_BG_COLOR = UIManager.getColor("MenuItem.selectionBackground");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 305 */     public static final Color MENU_HIGHLIGHT_FG_COLOR = UIManager.getColor("MenuItem.selectionForeground");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     public static final Color MENUITEM_BG_COLOR = UIManager.getColor("MenuItem.background");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     public static final Color MENUITEM_FG_COLOR = UIManager.getColor("MenuItem.foreground");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private DropDownComponent.ScrollablePopupMenu parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DefaultScrollablePopupMenuItem(DropDownComponent.ScrollablePopupMenu parent, String text) {
/* 330 */       super(text);
/* 331 */       this.parent = parent;
/* 332 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void init() {
/* 339 */       setUI(BasicButtonUI.createUI(this));
/* 340 */       setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 20));
/* 341 */       setMenuItemDefaultColors();
/* 342 */       setAlignmentX(0.0F);
/* 343 */       setSelected(false);
/*     */       
/* 345 */       addMouseListener(new MouseAdapter()
/*     */           {
/*     */             public void mouseEntered(MouseEvent e)
/*     */             {
/* 349 */               if (DropDownComponent.DefaultScrollablePopupMenuItem.this.isEnabled()) {
/* 350 */                 DropDownComponent.DefaultScrollablePopupMenuItem.this.setSelected(true);
/* 351 */                 DropDownComponent.DefaultScrollablePopupMenuItem.this.parent.selectionChanged(DropDownComponent.DefaultScrollablePopupMenuItem.this, true);
/*     */               } 
/*     */             }
/*     */ 
/*     */             
/*     */             public void mouseExited(MouseEvent e) {
/* 357 */               if (DropDownComponent.DefaultScrollablePopupMenuItem.this.isEnabled()) {
/* 358 */                 DropDownComponent.DefaultScrollablePopupMenuItem.this.setSelected(false);
/* 359 */                 DropDownComponent.DefaultScrollablePopupMenuItem.this.parent.selectionChanged(DropDownComponent.DefaultScrollablePopupMenuItem.this, false);
/*     */               } 
/*     */             }
/*     */ 
/*     */             
/*     */             public void mouseClicked(MouseEvent e) {
/* 365 */               DropDownComponent.DefaultScrollablePopupMenuItem.this.parent.processItemClicked();
/*     */             }
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void setMenuItemDefaultColors() {
/* 374 */       setBackground(MENUITEM_BG_COLOR);
/* 375 */       setForeground(MENUITEM_FG_COLOR);
/*     */     }
/*     */     
/*     */     public void setSelected(boolean selected) {
/* 379 */       super.setSelected(selected);
/* 380 */       if (selected) {
/* 381 */         setBackground(MENU_HIGHLIGHT_BG_COLOR);
/* 382 */         setForeground(MENU_HIGHLIGHT_FG_COLOR);
/*     */       } else {
/* 384 */         setMenuItemDefaultColors();
/*     */       } 
/*     */     }
/*     */     
/*     */     public String getText() {
/* 389 */       return super.getText();
/*     */     }
/*     */     
/*     */     public void setText(String text) {
/* 393 */       super.setText(text);
/*     */     }
/*     */     
/*     */     public void setEnabled(boolean b) {
/* 397 */       super.setEnabled(b);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface ScrollablePopupMenuModel
/*     */   {
/*     */     String getFooterText();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void processItemClicked();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void processBeforeShowed();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void processAfterShowed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ScrollablePopupMenu
/*     */     extends JPopupMenu
/*     */   {
/*     */     private static final String RESOURCES = "org.apache.batik.util.gui.resources.ScrollablePopupMenuMessages";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 451 */     private static ResourceBundle bundle = ResourceBundle.getBundle("org.apache.batik.util.gui.resources.ScrollablePopupMenuMessages", Locale.getDefault());
/* 452 */     private static ResourceManager resources = new ResourceManager(bundle);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 458 */     private JPanel menuPanel = new JPanel();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private JScrollPane scrollPane;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 468 */     private int preferredHeight = resources.getInteger("PreferredHeight");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private DropDownComponent.ScrollablePopupMenuModel model;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private JComponent ownerComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private DropDownComponent.ScrollablePopupMenuItem footer;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 488 */     private EventListenerList eventListeners = new EventListenerList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ScrollablePopupMenu(JComponent owner) {
/* 498 */       setLayout(new BorderLayout());
/* 499 */       this.menuPanel.setLayout(new GridLayout(0, 1));
/* 500 */       this.ownerComponent = owner;
/* 501 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void init() {
/* 508 */       removeAll();
/* 509 */       this.scrollPane = new JScrollPane();
/* 510 */       this.scrollPane.setViewportView(this.menuPanel);
/* 511 */       this.scrollPane.setBorder((Border)null);
/* 512 */       int minWidth = resources.getInteger("ScrollPane.minWidth");
/* 513 */       int minHeight = resources.getInteger("ScrollPane.minHeight");
/* 514 */       int maxWidth = resources.getInteger("ScrollPane.maxWidth");
/* 515 */       int maxHeight = resources.getInteger("ScrollPane.maxHeight");
/* 516 */       this.scrollPane.setMinimumSize(new Dimension(minWidth, minHeight));
/* 517 */       this.scrollPane.setMaximumSize(new Dimension(maxWidth, maxHeight));
/* 518 */       this.scrollPane.setHorizontalScrollBarPolicy(31);
/*     */       
/* 520 */       add(this.scrollPane, "Center");
/* 521 */       addFooter(new DropDownComponent.DefaultScrollablePopupMenuItem(this, ""));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void showMenu(Component invoker, Component refComponent) {
/* 533 */       this.model.processBeforeShowed();
/*     */       
/* 535 */       Point abs = new Point(0, refComponent.getHeight());
/* 536 */       SwingUtilities.convertPointToScreen(abs, refComponent);
/* 537 */       setLocation(abs);
/* 538 */       setInvoker(invoker);
/* 539 */       setVisible(true);
/* 540 */       revalidate();
/* 541 */       repaint();
/*     */       
/* 543 */       this.model.processAfterShowed();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void add(DropDownComponent.ScrollablePopupMenuItem menuItem, int index, int oldSize, int newSize) {
/* 554 */       this.menuPanel.add((Component)menuItem, index);
/* 555 */       if (oldSize == 0) {
/* 556 */         setEnabled(true);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void remove(DropDownComponent.ScrollablePopupMenuItem menuItem, int oldSize, int newSize) {
/* 568 */       this.menuPanel.remove((Component)menuItem);
/* 569 */       if (newSize == 0) {
/* 570 */         setEnabled(false);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getPreferredWidth() {
/* 580 */       Component[] components = this.menuPanel.getComponents();
/* 581 */       int maxWidth = 0;
/* 582 */       for (Component component : components) {
/* 583 */         int currentWidth = (component.getPreferredSize()).width;
/* 584 */         if (maxWidth < currentWidth) {
/* 585 */           maxWidth = currentWidth;
/*     */         }
/*     */       } 
/* 588 */       int footerWidth = (((Component)this.footer).getPreferredSize()).width;
/* 589 */       if (footerWidth > maxWidth) {
/* 590 */         maxWidth = footerWidth;
/*     */       }
/* 592 */       int widthOffset = 30;
/* 593 */       return maxWidth + widthOffset;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getPreferredHeight() {
/* 602 */       if ((this.scrollPane.getPreferredSize()).height < this.preferredHeight) {
/* 603 */         int heightOffset = 10;
/* 604 */         return (this.scrollPane.getPreferredSize()).height + (((Component)this.footer).getPreferredSize()).height + heightOffset;
/*     */       } 
/*     */ 
/*     */       
/* 608 */       return this.preferredHeight + (((Component)this.footer).getPreferredSize()).height;
/*     */     }
/*     */ 
/*     */     
/*     */     public Dimension getPreferredSize() {
/* 613 */       return new Dimension(getPreferredWidth(), getPreferredHeight());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void selectionChanged(DropDownComponent.ScrollablePopupMenuItem targetItem, boolean wasSelected) {
/* 621 */       Component[] comps = this.menuPanel.getComponents();
/* 622 */       int n = comps.length;
/*     */       
/* 624 */       if (!wasSelected) {
/* 625 */         for (int i = n - 1; i >= 0; i--) {
/* 626 */           DropDownComponent.ScrollablePopupMenuItem item = (DropDownComponent.ScrollablePopupMenuItem)comps[i];
/* 627 */           item.setSelected(wasSelected);
/*     */         } 
/*     */       } else {
/* 630 */         for (Component comp : comps) {
/* 631 */           DropDownComponent.ScrollablePopupMenuItem item = (DropDownComponent.ScrollablePopupMenuItem)comp;
/* 632 */           if (item == targetItem) {
/*     */             break;
/*     */           }
/* 635 */           item.setSelected(true);
/*     */         } 
/*     */       } 
/* 638 */       this.footer.setText(this.model.getFooterText() + getSelectedItemsCount());
/* 639 */       repaint();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setModel(DropDownComponent.ScrollablePopupMenuModel model) {
/* 649 */       this.model = model;
/* 650 */       this.footer.setText(model.getFooterText());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DropDownComponent.ScrollablePopupMenuModel getModel() {
/* 659 */       return this.model;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getSelectedItemsCount() {
/* 668 */       int selectionCount = 0;
/* 669 */       Component[] components = this.menuPanel.getComponents();
/* 670 */       for (Component component : components) {
/* 671 */         DropDownComponent.ScrollablePopupMenuItem item = (DropDownComponent.ScrollablePopupMenuItem)component;
/* 672 */         if (item.isSelected()) {
/* 673 */           selectionCount++;
/*     */         }
/*     */       } 
/* 676 */       return selectionCount;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void processItemClicked() {
/* 683 */       this.footer.setText(this.model.getFooterText() + Character.MIN_VALUE);
/* 684 */       setVisible(false);
/* 685 */       this.model.processItemClicked();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JComponent getOwner() {
/* 693 */       return this.ownerComponent;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void addFooter(DropDownComponent.ScrollablePopupMenuItem footer) {
/* 700 */       this.footer = footer;
/* 701 */       this.footer.setEnabled(false);
/* 702 */       add((Component)this.footer, "South");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DropDownComponent.ScrollablePopupMenuItem getFooter() {
/* 710 */       return this.footer;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addListener(DropDownComponent.ScrollablePopupMenuListener listener) {
/* 720 */       this.eventListeners.add(DropDownComponent.ScrollablePopupMenuListener.class, listener);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void fireItemsWereAdded(DropDownComponent.ScrollablePopupMenuEvent event) {
/* 731 */       Object[] listeners = this.eventListeners.getListenerList();
/* 732 */       int length = listeners.length;
/* 733 */       for (int i = 0; i < length; i += 2) {
/* 734 */         if (listeners[i] == DropDownComponent.ScrollablePopupMenuListener.class) {
/* 735 */           ((DropDownComponent.ScrollablePopupMenuListener)listeners[i + 1]).itemsWereAdded(event);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void fireItemsWereRemoved(DropDownComponent.ScrollablePopupMenuEvent event) {
/* 749 */       Object[] listeners = this.eventListeners.getListenerList();
/* 750 */       int length = listeners.length;
/* 751 */       for (int i = 0; i < length; i += 2) {
/* 752 */         if (listeners[i] == DropDownComponent.ScrollablePopupMenuListener.class) {
/* 753 */           ((DropDownComponent.ScrollablePopupMenuListener)listeners[i + 1]).itemsWereRemoved(event);
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
/*     */   public static class ScrollablePopupMenuEvent
/*     */     extends EventObject
/*     */   {
/*     */     public static final int ITEMS_ADDED = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int ITEMS_REMOVED = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int itemNumber;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String details;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ScrollablePopupMenuEvent(Object source, int type, int itemNumber, String details) {
/* 800 */       super(source);
/* 801 */       initEvent(type, itemNumber, details);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void initEvent(int type, int itemNumber, String details) {
/* 808 */       this.type = type;
/* 809 */       this.itemNumber = itemNumber;
/* 810 */       this.details = details;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getDetails() {
/* 819 */       return this.details;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getItemNumber() {
/* 828 */       return this.itemNumber;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getType() {
/* 837 */       return this.type;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface ScrollablePopupMenuListener extends EventListener {
/*     */     void itemsWereAdded(DropDownComponent.ScrollablePopupMenuEvent param1ScrollablePopupMenuEvent);
/*     */     
/*     */     void itemsWereRemoved(DropDownComponent.ScrollablePopupMenuEvent param1ScrollablePopupMenuEvent);
/*     */   }
/*     */   
/*     */   public static class ScrollablePopupMenuAdapter implements ScrollablePopupMenuListener {
/*     */     public void itemsWereAdded(DropDownComponent.ScrollablePopupMenuEvent ev) {}
/*     */     
/*     */     public void itemsWereRemoved(DropDownComponent.ScrollablePopupMenuEvent ev) {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/gui/DropDownComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */