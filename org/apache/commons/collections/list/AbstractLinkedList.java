/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public abstract class AbstractLinkedList
/*     */   implements List
/*     */ {
/*     */   protected transient Node header;
/*     */   protected transient int size;
/*     */   protected transient int modCount;
/*     */   
/*     */   protected AbstractLinkedList() {}
/*     */   
/*     */   protected AbstractLinkedList(Collection coll) {
/*  88 */     init();
/*  89 */     addAll(coll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/*  99 */     this.header = createHeaderNode();
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 104 */     return this.size;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 108 */     return (size() == 0);
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 112 */     Node node = getNode(index, false);
/* 113 */     return node.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 118 */     return listIterator();
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 122 */     return new LinkedListIterator(this, 0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int fromIndex) {
/* 126 */     return new LinkedListIterator(this, fromIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public int indexOf(Object value) {
/* 131 */     int i = 0;
/* 132 */     for (Node node = this.header.next; node != this.header; node = node.next) {
/* 133 */       if (isEqualValue(node.getValue(), value)) {
/* 134 */         return i;
/*     */       }
/* 136 */       i++;
/*     */     } 
/* 138 */     return -1;
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object value) {
/* 142 */     int i = this.size - 1;
/* 143 */     for (Node node = this.header.previous; node != this.header; node = node.previous) {
/* 144 */       if (isEqualValue(node.getValue(), value)) {
/* 145 */         return i;
/*     */       }
/* 147 */       i--;
/*     */     } 
/* 149 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean contains(Object value) {
/* 153 */     return (indexOf(value) != -1);
/*     */   }
/*     */   
/*     */   public boolean containsAll(Collection coll) {
/* 157 */     Iterator it = coll.iterator();
/* 158 */     while (it.hasNext()) {
/* 159 */       if (!contains(it.next())) {
/* 160 */         return false;
/*     */       }
/*     */     } 
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/* 168 */     return toArray(new Object[this.size]);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray(Object[] array) {
/* 173 */     if (array.length < this.size) {
/* 174 */       Class componentType = array.getClass().getComponentType();
/* 175 */       array = (Object[])Array.newInstance(componentType, this.size);
/*     */     } 
/*     */     
/* 178 */     int i = 0;
/* 179 */     for (Node node = this.header.next; node != this.header; node = node.next, i++) {
/* 180 */       array[i] = node.getValue();
/*     */     }
/*     */     
/* 183 */     if (array.length > this.size) {
/* 184 */       array[this.size] = null;
/*     */     }
/* 186 */     return array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List subList(int fromIndexInclusive, int toIndexExclusive) {
/* 197 */     return new LinkedSubList(this, fromIndexInclusive, toIndexExclusive);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object value) {
/* 202 */     addLast(value);
/* 203 */     return true;
/*     */   }
/*     */   
/*     */   public void add(int index, Object value) {
/* 207 */     Node node = getNode(index, true);
/* 208 */     addNodeBefore(node, value);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 212 */     return addAll(this.size, coll);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 216 */     Node node = getNode(index, true);
/* 217 */     for (Iterator itr = coll.iterator(); itr.hasNext(); ) {
/* 218 */       Object value = itr.next();
/* 219 */       addNodeBefore(node, value);
/*     */     } 
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object remove(int index) {
/* 226 */     Node node = getNode(index, false);
/* 227 */     Object oldValue = node.getValue();
/* 228 */     removeNode(node);
/* 229 */     return oldValue;
/*     */   }
/*     */   
/*     */   public boolean remove(Object value) {
/* 233 */     for (Node node = this.header.next; node != this.header; node = node.next) {
/* 234 */       if (isEqualValue(node.getValue(), value)) {
/* 235 */         removeNode(node);
/* 236 */         return true;
/*     */       } 
/*     */     } 
/* 239 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 243 */     boolean modified = false;
/* 244 */     Iterator it = iterator();
/* 245 */     while (it.hasNext()) {
/* 246 */       if (coll.contains(it.next())) {
/* 247 */         it.remove();
/* 248 */         modified = true;
/*     */       } 
/*     */     } 
/* 251 */     return modified;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 256 */     boolean modified = false;
/* 257 */     Iterator it = iterator();
/* 258 */     while (it.hasNext()) {
/* 259 */       if (!coll.contains(it.next())) {
/* 260 */         it.remove();
/* 261 */         modified = true;
/*     */       } 
/*     */     } 
/* 264 */     return modified;
/*     */   }
/*     */   
/*     */   public Object set(int index, Object value) {
/* 268 */     Node node = getNode(index, false);
/* 269 */     Object oldValue = node.getValue();
/* 270 */     updateNode(node, value);
/* 271 */     return oldValue;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 275 */     removeAllNodes();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getFirst() {
/* 280 */     Node node = this.header.next;
/* 281 */     if (node == this.header) {
/* 282 */       throw new NoSuchElementException();
/*     */     }
/* 284 */     return node.getValue();
/*     */   }
/*     */   
/*     */   public Object getLast() {
/* 288 */     Node node = this.header.previous;
/* 289 */     if (node == this.header) {
/* 290 */       throw new NoSuchElementException();
/*     */     }
/* 292 */     return node.getValue();
/*     */   }
/*     */   
/*     */   public boolean addFirst(Object o) {
/* 296 */     addNodeAfter(this.header, o);
/* 297 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addLast(Object o) {
/* 301 */     addNodeBefore(this.header, o);
/* 302 */     return true;
/*     */   }
/*     */   
/*     */   public Object removeFirst() {
/* 306 */     Node node = this.header.next;
/* 307 */     if (node == this.header) {
/* 308 */       throw new NoSuchElementException();
/*     */     }
/* 310 */     Object oldValue = node.getValue();
/* 311 */     removeNode(node);
/* 312 */     return oldValue;
/*     */   }
/*     */   
/*     */   public Object removeLast() {
/* 316 */     Node node = this.header.previous;
/* 317 */     if (node == this.header) {
/* 318 */       throw new NoSuchElementException();
/*     */     }
/* 320 */     Object oldValue = node.getValue();
/* 321 */     removeNode(node);
/* 322 */     return oldValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 327 */     if (obj == this) {
/* 328 */       return true;
/*     */     }
/* 330 */     if (!(obj instanceof List)) {
/* 331 */       return false;
/*     */     }
/* 333 */     List other = (List)obj;
/* 334 */     if (other.size() != size()) {
/* 335 */       return false;
/*     */     }
/* 337 */     ListIterator it1 = listIterator();
/* 338 */     ListIterator it2 = other.listIterator();
/* 339 */     while (it1.hasNext() && it2.hasNext()) {
/* 340 */       Object o1 = it1.next();
/* 341 */       Object o2 = it2.next();
/* 342 */       if ((o1 == null) ? (o2 == null) : o1.equals(o2))
/* 343 */         continue;  return false;
/*     */     } 
/* 345 */     return (!it1.hasNext() && !it2.hasNext());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 349 */     int hashCode = 1;
/* 350 */     Iterator it = iterator();
/* 351 */     while (it.hasNext()) {
/* 352 */       Object obj = it.next();
/* 353 */       hashCode = 31 * hashCode + ((obj == null) ? 0 : obj.hashCode());
/*     */     } 
/* 355 */     return hashCode;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 359 */     if (size() == 0) {
/* 360 */       return "[]";
/*     */     }
/* 362 */     StringBuffer buf = new StringBuffer(16 * size());
/* 363 */     buf.append("[");
/*     */     
/* 365 */     Iterator it = iterator();
/* 366 */     boolean hasNext = it.hasNext();
/* 367 */     while (hasNext) {
/* 368 */       Object value = it.next();
/* 369 */       buf.append((value == this) ? "(this Collection)" : value);
/* 370 */       hasNext = it.hasNext();
/* 371 */       if (hasNext) {
/* 372 */         buf.append(", ");
/*     */       }
/*     */     } 
/* 375 */     buf.append("]");
/* 376 */     return buf.toString();
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
/*     */   protected boolean isEqualValue(Object value1, Object value2) {
/* 390 */     return (value1 == value2 || (value1 != null && value1.equals(value2)));
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
/*     */   protected void updateNode(Node node, Object value) {
/* 402 */     node.setValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node createHeaderNode() {
/* 413 */     return new Node();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node createNode(Object value) {
/* 424 */     return new Node(value);
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
/*     */   protected void addNodeBefore(Node node, Object value) {
/* 439 */     Node newNode = createNode(value);
/* 440 */     addNode(newNode, node);
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
/*     */   protected void addNodeAfter(Node node, Object value) {
/* 455 */     Node newNode = createNode(value);
/* 456 */     addNode(newNode, node.next);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addNode(Node nodeToInsert, Node insertBeforeNode) {
/* 467 */     nodeToInsert.next = insertBeforeNode;
/* 468 */     nodeToInsert.previous = insertBeforeNode.previous;
/* 469 */     insertBeforeNode.previous.next = nodeToInsert;
/* 470 */     insertBeforeNode.previous = nodeToInsert;
/* 471 */     this.size++;
/* 472 */     this.modCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeNode(Node node) {
/* 482 */     node.previous.next = node.next;
/* 483 */     node.next.previous = node.previous;
/* 484 */     this.size--;
/* 485 */     this.modCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeAllNodes() {
/* 492 */     this.header.next = this.header;
/* 493 */     this.header.previous = this.header;
/* 494 */     this.size = 0;
/* 495 */     this.modCount++;
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
/*     */   protected Node getNode(int index, boolean endMarkerAllowed) throws IndexOutOfBoundsException {
/*     */     Node node;
/* 510 */     if (index < 0) {
/* 511 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") less than zero.");
/*     */     }
/*     */     
/* 514 */     if (!endMarkerAllowed && index == this.size) {
/* 515 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") is the size of the list.");
/*     */     }
/*     */     
/* 518 */     if (index > this.size) {
/* 519 */       throw new IndexOutOfBoundsException("Couldn't get the node: index (" + index + ") greater than the size of the " + "list (" + this.size + ").");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 525 */     if (index < this.size / 2) {
/*     */       
/* 527 */       node = this.header.next;
/* 528 */       for (int currentIndex = 0; currentIndex < index; currentIndex++) {
/* 529 */         node = node.next;
/*     */       }
/*     */     } else {
/*     */       
/* 533 */       node = this.header;
/* 534 */       for (int currentIndex = this.size; currentIndex > index; currentIndex--) {
/* 535 */         node = node.previous;
/*     */       }
/*     */     } 
/* 538 */     return node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator createSubListIterator(LinkedSubList subList) {
/* 548 */     return createSubListListIterator(subList, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ListIterator createSubListListIterator(LinkedSubList subList, int fromIndex) {
/* 558 */     return new LinkedSubListIterator(subList, fromIndex);
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
/*     */   protected void doWriteObject(ObjectOutputStream outputStream) throws IOException {
/* 570 */     outputStream.writeInt(size());
/* 571 */     for (Iterator itr = iterator(); itr.hasNext();) {
/* 572 */       outputStream.writeObject(itr.next());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doReadObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
/* 583 */     init();
/* 584 */     int size = inputStream.readInt();
/* 585 */     for (int i = 0; i < size; i++) {
/* 586 */       add(inputStream.readObject());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class Node
/*     */   {
/*     */     protected Node previous;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Node next;
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object value;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Node() {
/* 611 */       this.previous = this;
/* 612 */       this.next = this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Node(Object value) {
/* 622 */       this.value = value;
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
/*     */     protected Node(Node previous, Node next, Object value) {
/* 634 */       this.previous = previous;
/* 635 */       this.next = next;
/* 636 */       this.value = value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Object getValue() {
/* 646 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setValue(Object value) {
/* 656 */       this.value = value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Node getPreviousNode() {
/* 666 */       return this.previous;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setPreviousNode(Node previous) {
/* 676 */       this.previous = previous;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Node getNextNode() {
/* 686 */       return this.next;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void setNextNode(Node next) {
/* 696 */       this.next = next;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class LinkedListIterator
/*     */     implements ListIterator, OrderedIterator
/*     */   {
/*     */     protected final AbstractLinkedList parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected AbstractLinkedList.Node next;
/*     */ 
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
/*     */     
/*     */     protected AbstractLinkedList.Node current;
/*     */ 
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
/*     */ 
/*     */     
/*     */     protected LinkedListIterator(AbstractLinkedList parent, int fromIndex) throws IndexOutOfBoundsException {
/* 746 */       this.parent = parent;
/* 747 */       this.expectedModCount = parent.modCount;
/* 748 */       this.next = parent.getNode(fromIndex, true);
/* 749 */       this.nextIndex = fromIndex;
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
/* 760 */       if (this.parent.modCount != this.expectedModCount) {
/* 761 */         throw new ConcurrentModificationException();
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
/*     */     protected AbstractLinkedList.Node getLastNodeReturned() throws IllegalStateException {
/* 773 */       if (this.current == null) {
/* 774 */         throw new IllegalStateException();
/*     */       }
/* 776 */       return this.current;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 780 */       return (this.next != this.parent.header);
/*     */     }
/*     */     
/*     */     public Object next() {
/* 784 */       checkModCount();
/* 785 */       if (!hasNext()) {
/* 786 */         throw new NoSuchElementException("No element at index " + this.nextIndex + ".");
/*     */       }
/* 788 */       Object value = this.next.getValue();
/* 789 */       this.current = this.next;
/* 790 */       this.next = this.next.next;
/* 791 */       this.nextIndex++;
/* 792 */       return value;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 796 */       return (this.next.previous != this.parent.header);
/*     */     }
/*     */     
/*     */     public Object previous() {
/* 800 */       checkModCount();
/* 801 */       if (!hasPrevious()) {
/* 802 */         throw new NoSuchElementException("Already at start of list.");
/*     */       }
/* 804 */       this.next = this.next.previous;
/* 805 */       Object value = this.next.getValue();
/* 806 */       this.current = this.next;
/* 807 */       this.nextIndex--;
/* 808 */       return value;
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 812 */       return this.nextIndex;
/*     */     }
/*     */ 
/*     */     
/*     */     public int previousIndex() {
/* 817 */       return nextIndex() - 1;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 821 */       checkModCount();
/* 822 */       this.parent.removeNode(getLastNodeReturned());
/* 823 */       this.current = null;
/* 824 */       this.nextIndex--;
/* 825 */       this.expectedModCount++;
/*     */     }
/*     */     
/*     */     public void set(Object obj) {
/* 829 */       checkModCount();
/* 830 */       getLastNodeReturned().setValue(obj);
/*     */     }
/*     */     
/*     */     public void add(Object obj) {
/* 834 */       checkModCount();
/* 835 */       this.parent.addNodeBefore(this.next, obj);
/* 836 */       this.current = null;
/* 837 */       this.nextIndex++;
/* 838 */       this.expectedModCount++;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class LinkedSubListIterator
/*     */     extends LinkedListIterator
/*     */   {
/*     */     protected final AbstractLinkedList.LinkedSubList sub;
/*     */ 
/*     */ 
/*     */     
/*     */     protected LinkedSubListIterator(AbstractLinkedList.LinkedSubList sub, int startIndex) {
/* 853 */       super(sub.parent, startIndex + sub.offset);
/* 854 */       this.sub = sub;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 858 */       return (nextIndex() < this.sub.size);
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 862 */       return (previousIndex() >= 0);
/*     */     }
/*     */     
/*     */     public int nextIndex() {
/* 866 */       return super.nextIndex() - this.sub.offset;
/*     */     }
/*     */     
/*     */     public void add(Object obj) {
/* 870 */       super.add(obj);
/* 871 */       this.sub.expectedModCount = this.parent.modCount;
/* 872 */       this.sub.size++;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 876 */       super.remove();
/* 877 */       this.sub.expectedModCount = this.parent.modCount;
/* 878 */       this.sub.size--;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class LinkedSubList
/*     */     extends AbstractList
/*     */   {
/*     */     private AbstractLinkedList parent;
/*     */     
/*     */     private int offset;
/*     */     
/*     */     private int size;
/*     */     
/*     */     private int expectedModCount;
/*     */ 
/*     */     
/*     */     protected LinkedSubList(AbstractLinkedList parent, int fromIndex, int toIndex) {
/* 897 */       if (fromIndex < 0) {
/* 898 */         throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
/*     */       }
/* 900 */       if (toIndex > parent.size()) {
/* 901 */         throw new IndexOutOfBoundsException("toIndex = " + toIndex);
/*     */       }
/* 903 */       if (fromIndex > toIndex) {
/* 904 */         throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
/*     */       }
/* 906 */       this.parent = parent;
/* 907 */       this.offset = fromIndex;
/* 908 */       this.size = toIndex - fromIndex;
/* 909 */       this.expectedModCount = parent.modCount;
/*     */     }
/*     */     
/*     */     public int size() {
/* 913 */       checkModCount();
/* 914 */       return this.size;
/*     */     }
/*     */     
/*     */     public Object get(int index) {
/* 918 */       rangeCheck(index, this.size);
/* 919 */       checkModCount();
/* 920 */       return this.parent.get(index + this.offset);
/*     */     }
/*     */     
/*     */     public void add(int index, Object obj) {
/* 924 */       rangeCheck(index, this.size + 1);
/* 925 */       checkModCount();
/* 926 */       this.parent.add(index + this.offset, obj);
/* 927 */       this.expectedModCount = this.parent.modCount;
/* 928 */       this.size++;
/* 929 */       this.modCount++;
/*     */     }
/*     */     
/*     */     public Object remove(int index) {
/* 933 */       rangeCheck(index, this.size);
/* 934 */       checkModCount();
/* 935 */       Object result = this.parent.remove(index + this.offset);
/* 936 */       this.expectedModCount = this.parent.modCount;
/* 937 */       this.size--;
/* 938 */       this.modCount++;
/* 939 */       return result;
/*     */     }
/*     */     
/*     */     public boolean addAll(Collection coll) {
/* 943 */       return addAll(this.size, coll);
/*     */     }
/*     */     
/*     */     public boolean addAll(int index, Collection coll) {
/* 947 */       rangeCheck(index, this.size + 1);
/* 948 */       int cSize = coll.size();
/* 949 */       if (cSize == 0) {
/* 950 */         return false;
/*     */       }
/*     */       
/* 953 */       checkModCount();
/* 954 */       this.parent.addAll(this.offset + index, coll);
/* 955 */       this.expectedModCount = this.parent.modCount;
/* 956 */       this.size += cSize;
/* 957 */       this.modCount++;
/* 958 */       return true;
/*     */     }
/*     */     
/*     */     public Object set(int index, Object obj) {
/* 962 */       rangeCheck(index, this.size);
/* 963 */       checkModCount();
/* 964 */       return this.parent.set(index + this.offset, obj);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 968 */       checkModCount();
/* 969 */       Iterator it = iterator();
/* 970 */       while (it.hasNext()) {
/* 971 */         it.next();
/* 972 */         it.remove();
/*     */       } 
/*     */     }
/*     */     
/*     */     public Iterator iterator() {
/* 977 */       checkModCount();
/* 978 */       return this.parent.createSubListIterator(this);
/*     */     }
/*     */     
/*     */     public ListIterator listIterator(int index) {
/* 982 */       rangeCheck(index, this.size + 1);
/* 983 */       checkModCount();
/* 984 */       return this.parent.createSubListListIterator(this, index);
/*     */     }
/*     */     
/*     */     public List subList(int fromIndexInclusive, int toIndexExclusive) {
/* 988 */       return new LinkedSubList(this.parent, fromIndexInclusive + this.offset, toIndexExclusive + this.offset);
/*     */     }
/*     */     
/*     */     protected void rangeCheck(int index, int beyond) {
/* 992 */       if (index < 0 || index >= beyond) {
/* 993 */         throw new IndexOutOfBoundsException("Index '" + index + "' out of bounds for size '" + this.size + "'");
/*     */       }
/*     */     }
/*     */     
/*     */     protected void checkModCount() {
/* 998 */       if (this.parent.modCount != this.expectedModCount)
/* 999 */         throw new ConcurrentModificationException(); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/list/AbstractLinkedList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */