package com.sarality.app.extract;

/**
 * Interface for all classes that process the events generated from a Message.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface MessageEventProcessor<M, E> {

  /**
   * Process generated events.
   *
   * @param eventList Message and the events generated from it.
   * @return {@code true} if the ecents were processed, {@code false} otherwise.
   */
  public boolean processEvents(MessageEventList<M, E> eventList);
}
