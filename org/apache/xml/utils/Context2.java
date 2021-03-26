/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
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
/*     */ final class Context2
/*     */ {
/* 448 */   private static final Enumeration EMPTY_ENUMERATION = (new Vector()).elements();
/*     */ 
/*     */   
/*     */   Hashtable prefixTable;
/*     */   
/*     */   Hashtable uriTable;
/*     */   
/*     */   Hashtable elementNameTable;
/*     */   
/*     */   Hashtable attributeNameTable;
/*     */   
/* 459 */   String defaultNS = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 465 */   private Vector declarations = null;
/*     */   private boolean tablesDirty = false;
/* 467 */   private Context2 parent = null;
/* 468 */   private Context2 child = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Context2(Context2 parent) {
/* 475 */     if (parent == null) {
/*     */       
/* 477 */       this.prefixTable = new Hashtable();
/* 478 */       this.uriTable = new Hashtable();
/* 479 */       this.elementNameTable = null;
/* 480 */       this.attributeNameTable = null;
/*     */     } else {
/*     */       
/* 483 */       setParent(parent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Context2 getChild() {
/* 493 */     return this.child;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Context2 getParent() {
/* 502 */     return this.parent;
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
/*     */   void setParent(Context2 parent) {
/* 514 */     this.parent = parent;
/* 515 */     parent.child = this;
/* 516 */     this.declarations = null;
/* 517 */     this.prefixTable = parent.prefixTable;
/* 518 */     this.uriTable = parent.uriTable;
/* 519 */     this.elementNameTable = parent.elementNameTable;
/* 520 */     this.attributeNameTable = parent.attributeNameTable;
/* 521 */     this.defaultNS = parent.defaultNS;
/* 522 */     this.tablesDirty = false;
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
/*     */   void declarePrefix(String prefix, String uri) {
/* 536 */     if (!this.tablesDirty) {
/* 537 */       copyTables();
/*     */     }
/* 539 */     if (this.declarations == null) {
/* 540 */       this.declarations = new Vector();
/*     */     }
/*     */     
/* 543 */     prefix = prefix.intern();
/* 544 */     uri = uri.intern();
/* 545 */     if ("".equals(prefix)) {
/* 546 */       if ("".equals(uri)) {
/* 547 */         this.defaultNS = null;
/*     */       } else {
/* 549 */         this.defaultNS = uri;
/*     */       } 
/*     */     } else {
/* 552 */       this.prefixTable.put(prefix, uri);
/* 553 */       this.uriTable.put(uri, prefix);
/*     */     } 
/* 555 */     this.declarations.addElement(prefix);
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
/*     */   String[] processName(String qName, boolean isAttribute) {
/*     */     Hashtable hashtable;
/* 576 */     if (isAttribute) {
/* 577 */       if (this.elementNameTable == null)
/* 578 */         this.elementNameTable = new Hashtable(); 
/* 579 */       hashtable = this.elementNameTable;
/*     */     } else {
/* 581 */       if (this.attributeNameTable == null)
/* 582 */         this.attributeNameTable = new Hashtable(); 
/* 583 */       hashtable = this.attributeNameTable;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 589 */     String[] name = (String[])hashtable.get(qName);
/* 590 */     if (name != null) {
/* 591 */       return name;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 596 */     name = new String[3];
/* 597 */     int index = qName.indexOf(':');
/*     */ 
/*     */ 
/*     */     
/* 601 */     if (index == -1) {
/* 602 */       if (isAttribute || this.defaultNS == null) {
/* 603 */         name[0] = "";
/*     */       } else {
/* 605 */         name[0] = this.defaultNS;
/*     */       } 
/* 607 */       name[1] = qName.intern();
/* 608 */       name[2] = name[1];
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 613 */       String str1, prefix = qName.substring(0, index);
/* 614 */       String local = qName.substring(index + 1);
/*     */       
/* 616 */       if ("".equals(prefix)) {
/* 617 */         str1 = this.defaultNS;
/*     */       } else {
/* 619 */         str1 = (String)this.prefixTable.get(prefix);
/*     */       } 
/* 621 */       if (str1 == null) {
/* 622 */         return null;
/*     */       }
/* 624 */       name[0] = str1;
/* 625 */       name[1] = local.intern();
/* 626 */       name[2] = qName.intern();
/*     */     } 
/*     */ 
/*     */     
/* 630 */     hashtable.put(name[2], name);
/* 631 */     this.tablesDirty = true;
/* 632 */     return name;
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
/*     */   String getURI(String prefix) {
/* 646 */     if ("".equals(prefix))
/* 647 */       return this.defaultNS; 
/* 648 */     if (this.prefixTable == null) {
/* 649 */       return null;
/*     */     }
/* 651 */     return (String)this.prefixTable.get(prefix);
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
/*     */   String getPrefix(String uri) {
/* 668 */     if (this.uriTable == null) {
/* 669 */       return null;
/*     */     }
/* 671 */     return (String)this.uriTable.get(uri);
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
/*     */   Enumeration getDeclaredPrefixes() {
/* 684 */     if (this.declarations == null) {
/* 685 */       return EMPTY_ENUMERATION;
/*     */     }
/* 687 */     return this.declarations.elements();
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
/*     */   Enumeration getPrefixes() {
/* 703 */     if (this.prefixTable == null) {
/* 704 */       return EMPTY_ENUMERATION;
/*     */     }
/* 706 */     return this.prefixTable.keys();
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
/*     */   private void copyTables() {
/* 729 */     this.prefixTable = (Hashtable)this.prefixTable.clone();
/* 730 */     this.uriTable = (Hashtable)this.uriTable.clone();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 737 */     if (this.elementNameTable != null)
/* 738 */       this.elementNameTable = new Hashtable(); 
/* 739 */     if (this.attributeNameTable != null)
/* 740 */       this.attributeNameTable = new Hashtable(); 
/* 741 */     this.tablesDirty = true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/Context2.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */