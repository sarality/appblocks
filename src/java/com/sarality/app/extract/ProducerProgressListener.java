package com.sarality.app.extract;

/**
 * Interface for all classes that listen to the progress of a Producer.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ProducerProgressListener {

  public void onProgress(int maxProgress, int progress);

}
