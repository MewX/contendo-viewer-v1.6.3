package org.apache.batik.w3c.dom.events;

import org.w3c.dom.Node;
import org.w3c.dom.events.MutationEvent;

public interface MutationNameEvent extends MutationEvent {
  String getPrevNamespaceURI();
  
  String getPrevNodeName();
  
  void initMutationNameEvent(String paramString1, boolean paramBoolean1, boolean paramBoolean2, Node paramNode, String paramString2, String paramString3);
  
  void initMutationNameEventNS(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, Node paramNode, String paramString3, String paramString4);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/w3c/dom/events/MutationNameEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */