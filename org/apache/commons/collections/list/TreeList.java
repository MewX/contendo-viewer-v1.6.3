/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.OrderedIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeList
/*     */   extends AbstractList
/*     */ {
/*     */   private AVLNode root;
/*     */   private int size;
/*     */   
/*     */   public TreeList() {}
/*     */   
/*     */   public TreeList(Collection coll) {
/*  86 */     addAll(coll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get(int index) {
/*  97 */     checkInterval(index, 0, size() - 1);
/*  98 */     return this.root.get(index).getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 107 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 117 */     return listIterator(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator listIterator() {
/* 127 */     return listIterator(0);
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
/*     */   public ListIterator listIterator(int fromIndex) {
/* 139 */     checkInterval(fromIndex, 0, size());
/* 140 */     return new TreeListIterator(this, fromIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int indexOf(Object object) {
/* 150 */     if (this.root == null) {
/* 151 */       return -1;
/*     */     }
/* 153 */     return this.root.indexOf(object, this.root.relativePosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contains(Object object) {
/* 162 */     return (indexOf(object) >= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 172 */     Object[] array = new Object[size()];
/* 173 */     if (this.root != null) {
/* 174 */       this.root.toArray(array, this.root.relativePosition);
/*     */     }
/* 176 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int index, Object obj) {
/* 187 */     this.modCount++;
/* 188 */     checkInterval(index, 0, size());
/* 189 */     if (this.root == null) {
/* 190 */       this.root = new AVLNode(index, obj, null, null);
/*     */     } else {
/* 192 */       this.root = this.root.insert(index, obj);
/*     */     } 
/* 194 */     this.size++;
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
/*     */   public Object set(int index, Object obj) {
/* 206 */     checkInterval(index, 0, size() - 1);
/* 207 */     AVLNode node = this.root.get(index);
/* 208 */     Object result = node.value;
/* 209 */     node.setValue(obj);
/* 210 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(int index) {
/* 220 */     this.modCount++;
/* 221 */     checkInterval(index, 0, size() - 1);
/* 222 */     Object result = get(index);
/* 223 */     this.root = this.root.remove(index);
/* 224 */     this.size--;
/* 225 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 232 */     this.modCount++;
/* 233 */     this.root = null;
/* 234 */     this.size = 0;
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
/*     */   private void checkInterval(int index, int startIndex, int endIndex) {
/* 247 */     if (index < startIndex || index > endIndex) {
/* 248 */       throw new IndexOutOfBoundsException("Invalid index:" + index + ", size=" + size());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class AVLNode
/*     */   {
/*     */     private AVLNode left;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean leftIsPrevious;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private AVLNode right;
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean rightIsNext;
/*     */ 
/*     */ 
/*     */     
/*     */     private int height;
/*     */ 
/*     */ 
/*     */     
/*     */     private int relativePosition;
/*     */ 
/*     */ 
/*     */     
/*     */     private Object value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private AVLNode(int relativePosition, Object obj, AVLNode rightFollower, AVLNode leftFollower) {
/* 290 */       this.relativePosition = relativePosition;
/* 291 */       this.value = obj;
/* 292 */       this.rightIsNext = true;
/* 293 */       this.leftIsPrevious = true;
/* 294 */       this.right = rightFollower;
/* 295 */       this.left = leftFollower;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Object getValue() {
/* 304 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void setValue(Object obj) {
/* 313 */       this.value = obj;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     AVLNode get(int index) {
/* 321 */       int indexRelativeToMe = index - this.relativePosition;
/*     */       
/* 323 */       if (indexRelativeToMe == 0) {
/* 324 */         return this;
/*     */       }
/*     */       
/* 327 */       AVLNode nextNode = (indexRelativeToMe < 0) ? getLeftSubTree() : getRightSubTree();
/* 328 */       if (nextNode == null) {
/* 329 */         return null;
/*     */       }
/* 331 */       return nextNode.get(indexRelativeToMe);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int indexOf(Object object, int index) {
/* 338 */       if (getLeftSubTree() != null) {
/* 339 */         int result = this.left.indexOf(object, index + this.left.relativePosition);
/* 340 */         if (result != -1) {
/* 341 */           return result;
/*     */         }
/*     */       } 
/* 344 */       if ((this.value == null) ? (this.value == object) : this.value.equals(object)) {
/* 345 */         return index;
/*     */       }
/* 347 */       if (getRightSubTree() != null) {
/* 348 */         return this.right.indexOf(object, index + this.right.relativePosition);
/*     */       }
/* 350 */       return -1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void toArray(Object[] array, int index) {
/* 360 */       array[index] = this.value;
/* 361 */       if (getLeftSubTree() != null) {
/* 362 */         this.left.toArray(array, index + this.left.relativePosition);
/*     */       }
/* 364 */       if (getRightSubTree() != null) {
/* 365 */         this.right.toArray(array, index + this.right.relativePosition);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     AVLNode next() {
/* 375 */       if (this.rightIsNext || this.right == null) {
/* 376 */         return this.right;
/*     */       }
/* 378 */       return this.right.min();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     AVLNode previous() {
/* 387 */       if (this.leftIsPrevious || this.left == null) {
/* 388 */         return this.left;
/*     */       }
/* 390 */       return this.left.max();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     AVLNode insert(int index, Object obj) {
/* 401 */       int indexRelativeToMe = index - this.relativePosition;
/*     */       
/* 403 */       if (indexRelativeToMe <= 0) {
/* 404 */         return insertOnLeft(indexRelativeToMe, obj);
/*     */       }
/* 406 */       return insertOnRight(indexRelativeToMe, obj);
/*     */     }
/*     */ 
/*     */     
/*     */     private AVLNode insertOnLeft(int indexRelativeToMe, Object obj) {
/* 411 */       AVLNode ret = this;
/*     */       
/* 413 */       if (getLeftSubTree() == null) {
/* 414 */         setLeft(new AVLNode(-1, obj, this, this.left), null);
/*     */       } else {
/* 416 */         setLeft(this.left.insert(indexRelativeToMe, obj), null);
/*     */       } 
/*     */       
/* 419 */       if (this.relativePosition >= 0) {
/* 420 */         this.relativePosition++;
/*     */       }
/* 422 */       ret = balance();
/* 423 */       recalcHeight();
/* 424 */       return ret;
/*     */     }
/*     */     
/*     */     private AVLNode insertOnRight(int indexRelativeToMe, Object obj) {
/* 428 */       AVLNode ret = this;
/*     */       
/* 430 */       if (getRightSubTree() == null) {
/* 431 */         setRight(new AVLNode(1, obj, this.right, this), null);
/*     */       } else {
/* 433 */         setRight(this.right.insert(indexRelativeToMe, obj), null);
/*     */       } 
/* 435 */       if (this.relativePosition < 0) {
/* 436 */         this.relativePosition--;
/*     */       }
/* 438 */       ret = balance();
/* 439 */       recalcHeight();
/* 440 */       return ret;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private AVLNode getLeftSubTree() {
/* 448 */       return this.leftIsPrevious ? null : this.left;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private AVLNode getRightSubTree() {
/* 455 */       return this.rightIsNext ? null : this.right;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private AVLNode max() {
/* 464 */       return (getRightSubTree() == null) ? this : this.right.max();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private AVLNode min() {
/* 473 */       return (getLeftSubTree() == null) ? this : this.left.min();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     AVLNode remove(int index) {
/* 483 */       int indexRelativeToMe = index - this.relativePosition;
/*     */       
/* 485 */       if (indexRelativeToMe == 0) {
/* 486 */         return removeSelf();
/*     */       }
/* 488 */       if (indexRelativeToMe > 0) {
/* 489 */         setRight(this.right.remove(indexRelativeToMe), this.right.right);
/* 490 */         if (this.relativePosition < 0) {
/* 491 */           this.relativePosition++;
/*     */         }
/*     */       } else {
/* 494 */         setLeft(this.left.remove(indexRelativeToMe), this.left.left);
/* 495 */         if (this.relativePosition > 0) {
/* 496 */           this.relativePosition--;
/*     */         }
/*     */       } 
/* 499 */       recalcHeight();
/* 500 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode removeMax() {
/* 504 */       if (getRightSubTree() == null) {
/* 505 */         return removeSelf();
/*     */       }
/* 507 */       setRight(this.right.removeMax(), this.right.right);
/* 508 */       if (this.relativePosition < 0) {
/* 509 */         this.relativePosition++;
/*     */       }
/* 511 */       recalcHeight();
/* 512 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode removeMin() {
/* 516 */       if (getLeftSubTree() == null) {
/* 517 */         return removeSelf();
/*     */       }
/* 519 */       setLeft(this.left.removeMin(), this.left.left);
/* 520 */       if (this.relativePosition > 0) {
/* 521 */         this.relativePosition--;
/*     */       }
/* 523 */       recalcHeight();
/* 524 */       return balance();
/*     */     }
/*     */     
/*     */     private AVLNode removeSelf() {
/* 528 */       if (getRightSubTree() == null && getLeftSubTree() == null)
/* 529 */         return null; 
/* 530 */       if (getRightSubTree() == null) {
/* 531 */         if (this.relativePosition > 0) {
/* 532 */           this.left.relativePosition += this.relativePosition + ((this.relativePosition > 0) ? 0 : 1);
/*     */         }
/* 534 */         this.left.max().setRight(null, this.right);
/* 535 */         return this.left;
/*     */       } 
/* 537 */       if (getLeftSubTree() == null) {
/* 538 */         this.right.relativePosition += this.relativePosition - ((this.relativePosition < 0) ? 0 : 1);
/* 539 */         this.right.min().setLeft(null, this.left);
/* 540 */         return this.right;
/*     */       } 
/*     */       
/* 543 */       if (heightRightMinusLeft() > 0) {
/* 544 */         AVLNode rightMin = this.right.min();
/* 545 */         this.value = rightMin.value;
/* 546 */         if (this.leftIsPrevious) {
/* 547 */           this.left = rightMin.left;
/*     */         }
/* 549 */         this.right = this.right.removeMin();
/* 550 */         if (this.relativePosition < 0) {
/* 551 */           this.relativePosition++;
/*     */         }
/*     */       } else {
/* 554 */         AVLNode leftMax = this.left.max();
/* 555 */         this.value = leftMax.value;
/* 556 */         if (this.rightIsNext) {
/* 557 */           this.right = leftMax.right;
/*     */         }
/* 559 */         this.left = this.left.removeMax();
/* 560 */         if (this.relativePosition > 0) {
/* 561 */           this.relativePosition--;
/*     */         }
/*     */       } 
/* 564 */       recalcHeight();
/* 565 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private AVLNode balance() {
/* 573 */       switch (heightRightMinusLeft()) {
/*     */         case -1:
/*     */         case 0:
/*     */         case 1:
/* 577 */           return this;
/*     */         case -2:
/* 579 */           if (this.left.heightRightMinusLeft() > 0) {
/* 580 */             setLeft(this.left.rotateLeft(), null);
/*     */           }
/* 582 */           return rotateRight();
/*     */         case 2:
/* 584 */           if (this.right.heightRightMinusLeft() < 0) {
/* 585 */             setRight(this.right.rotateRight(), null);
/*     */           }
/* 587 */           return rotateLeft();
/*     */       } 
/* 589 */       throw new RuntimeException("tree inconsistent!");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getOffset(AVLNode node) {
/* 597 */       if (node == null) {
/* 598 */         return 0;
/*     */       }
/* 600 */       return node.relativePosition;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int setOffset(AVLNode node, int newOffest) {
/* 607 */       if (node == null) {
/* 608 */         return 0;
/*     */       }
/* 610 */       int oldOffset = getOffset(node);
/* 611 */       node.relativePosition = newOffest;
/* 612 */       return oldOffset;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void recalcHeight() {
/* 619 */       this.height = Math.max((getLeftSubTree() == null) ? -1 : (getLeftSubTree()).height, (getRightSubTree() == null) ? -1 : (getRightSubTree()).height) + 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int getHeight(AVLNode node) {
/* 628 */       return (node == null) ? -1 : node.height;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int heightRightMinusLeft() {
/* 635 */       return getHeight(getRightSubTree()) - getHeight(getLeftSubTree());
/*     */     }
/*     */     
/*     */     private AVLNode rotateLeft() {
/* 639 */       AVLNode newTop = this.right;
/* 640 */       AVLNode movedNode = getRightSubTree().getLeftSubTree();
/*     */       
/* 642 */       int newTopPosition = this.relativePosition + getOffset(newTop);
/* 643 */       int myNewPosition = -newTop.relativePosition;
/* 644 */       int movedPosition = getOffset(newTop) + getOffset(movedNode);
/*     */       
/* 646 */       setRight(movedNode, newTop);
/* 647 */       newTop.setLeft(this, null);
/*     */       
/* 649 */       setOffset(newTop, newTopPosition);
/* 650 */       setOffset(this, myNewPosition);
/* 651 */       setOffset(movedNode, movedPosition);
/* 652 */       return newTop;
/*     */     }
/*     */     
/*     */     private AVLNode rotateRight() {
/* 656 */       AVLNode newTop = this.left;
/* 657 */       AVLNode movedNode = getLeftSubTree().getRightSubTree();
/*     */       
/* 659 */       int newTopPosition = this.relativePosition + getOffset(newTop);
/* 660 */       int myNewPosition = -newTop.relativePosition;
/* 661 */       int movedPosition = getOffset(newTop) + getOffset(movedNode);
/*     */       
/* 663 */       setLeft(movedNode, newTop);
/* 664 */       newTop.setRight(this, null);
/*     */       
/* 666 */       setOffset(newTop, newTopPosition);
/* 667 */       setOffset(this, myNewPosition);
/* 668 */       setOffset(movedNode, movedPosition);
/* 669 */       return newTop;
/*     */     }
/*     */     
/*     */     private void setLeft(AVLNode node, AVLNode previous) {
/* 673 */       this.leftIsPrevious = (node == null);
/* 674 */       this.left = this.leftIsPrevious ? previous : node;
/* 675 */       recalcHeight();
/*     */     }
/*     */     
/*     */     private void setRight(AVLNode node, AVLNode next) {
/* 679 */       this.rightIsNext = (node == null);
/* 680 */       this.right = this.rightIsNext ? next : node;
/* 681 */       recalcHeight();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 740 */       return "AVLNode(" + this.relativePosition + "," + ((this.left != null) ? 1 : 0) + "," + this.value + "," + ((getRightSubTree() != null) ? 1 : 0) + ", faedelung " + this.rightIsNext + " )";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class TreeListIterator
/*     */     implements ListIterator, OrderedIterator
/*     */   {
/*     */     protected final TreeList parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected TreeList.AVLNode next;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int nextIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected TreeList.AVLNode current;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int currentIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int expectedModCount;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected TreeListIterator(TreeList parent, int fromIndex) throws IndexOutOfBoundsException {
/* 789 */       this.parent = parent;
/* 790 */       this.expectedModCount = parent.modCount;
/* 791 */       this.next = (parent.root == null) ? null : parent.root.get(fromIndex);
/* 792 */       this.nextIndex = fromIndex;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void checkModCount() {
/* 803 */       if (this.parent.modCount != this.expectedModCount) {
/* 804 */         throw new ConcurrentModificationException();
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 809 */       return (this.nextIndex < this.parent.size());
/*     */     }
/*     */     
/*     */     public Object next() {
/* 813 */       checkModCount();
/* 814 */       if (!hasNext()) {
/* 815 */         throw new NoSuchElementException("No element at index " + this.nextIndex + ".");
/*     */       }
/* 817 */       if (this.next == null) {
/* 818 */         this.next = this.parent.root.get(this.nextIndex);
/*     */       }
/* 820 */       Object value = this.next.getValue();
/* 821 */       this.current = this.next;
/* 822 */       this.currentIndex = this.nextIndex++;
/* 823 */       this.next = this.next.next();
/* 824 */       return value;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 828 */       return (this.nextIndex > 0);
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 832 */       checkModCount();
/* 833 */       if (!hasPrevious()) {
/* 834 */         throw new NoSuchElementException("Already at start of list.");
/*     */       }
/* 836 */       if (this.next == null) {
/* 837 */         this.next = this.parent.root.get(this.nextIndex - 1);
/*     */       } else {
/* 839 */         this.next = this.next.previous();
/*     */       } 
/* 841 */       Object value = this.next.getValue();
/* 842 */       this.current = this.next;
/* 843 */       this.currentIndex = --this.nextIndex;
/* 844 */       return value;
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 848 */       return this.nextIndex;
/*     */     }
/*     */     
/*     */     public int previousIndex() {
/* 852 */       return nextIndex() - 1;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 856 */       checkModCount();
/* 857 */       if (this.current == null) {
/* 858 */         throw new IllegalStateException();
/*     */       }
/* 860 */       this.parent.remove(this.currentIndex);
/* 861 */       this.current = null;
/* 862 */       this.currentIndex = -1;
/* 863 */       this.nextIndex--;
/* 864 */       this.expectedModCount++;
/*     */     }
/*     */     
/*     */     public void set(Object obj) {
/* 868 */       checkModCount();
/* 869 */       if (this.current == null) {
/* 870 */         throw new IllegalStateException();
/*     */       }
/* 872 */       this.current.setValue(obj);
/*     */     }
/*     */     
/*     */     public void add(Object obj) {
/* 876 */       checkModCount();
/* 877 */       this.parent.add(this.nextIndex, obj);
/* 878 */       this.current = null;
/* 879 */       this.currentIndex = -1;
/* 880 */       this.nextIndex++;
/* 881 */       this.expectedModCount++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/TreeList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */