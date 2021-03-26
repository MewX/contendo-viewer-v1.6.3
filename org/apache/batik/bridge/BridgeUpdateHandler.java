package org.apache.batik.bridge;

import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
import org.apache.batik.css.engine.CSSEngineEvent;
import org.w3c.dom.events.MutationEvent;

public interface BridgeUpdateHandler {
  void handleDOMAttrModifiedEvent(MutationEvent paramMutationEvent);
  
  void handleDOMNodeInsertedEvent(MutationEvent paramMutationEvent);
  
  void handleDOMNodeRemovedEvent(MutationEvent paramMutationEvent);
  
  void handleDOMCharacterDataModified(MutationEvent paramMutationEvent);
  
  void handleCSSEngineEvent(CSSEngineEvent paramCSSEngineEvent);
  
  void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue paramAnimatedLiveAttributeValue);
  
  void handleOtherAnimationChanged(String paramString);
  
  void dispose();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/BridgeUpdateHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */