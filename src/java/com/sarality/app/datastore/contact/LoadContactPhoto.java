package com.sarality.app.datastore.contact;

import java.io.InputStream;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract.Contacts;
import android.widget.ImageView;

/**
 * Gets the Actual photo of the contact
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class LoadContactPhoto extends AsyncTask<Long, Void, Bitmap> {

  private final Uri baseUri = Contacts.CONTENT_URI;
  private final Context context;
  private ImageView imageView;

  /**
   * Constructor
   * 
   * @param context : Application context
   */
  public LoadContactPhoto(Context context, ImageView imageView) {
    this.context = context;
    this.imageView = imageView;
  }

  @Override
  protected Bitmap doInBackground(Long... contactId) {
    InputStream input = Contacts.openContactPhotoInputStream(context.getContentResolver(),
        ContentUris.withAppendedId(baseUri, contactId[0]));
    return BitmapFactory.decodeStream(input);
  }

  @Override
  protected void onPostExecute(Bitmap contactPhoto) {
    if (contactPhoto != null) {
      imageView.setImageBitmap(contactPhoto);
    }
  }

}
