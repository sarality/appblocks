package com.sarality.app.extract;

/**
 * Interface for all classes tha extract an event from a Message.
 *
 * @param <E> Type of event being being extracted.
 * @param <M> Type of message from which the event is extracted.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface MessageEventExtractor<M, E> {

  /**
   * Extract the Event from the Message.
   * <p/>
   * The caller should first call
   * @return The extracted event. Returns null if no message could be extracted.
   */
  public MessageEventList<M, E> extract(M message);
}
