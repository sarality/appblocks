package com.sarality.app.datastore.contact;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract.Contacts;
import android.widget.ImageView;

import com.sarality.app.datastore.contact.ContactPhotoRequest.PhotoType;
import com.sarality.app.datastore.db.SqliteTable;

/**
 * Gets the Actual photo of the contact
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ContactPhotoLoader extends AsyncTask<ContactPhotoRequest, Void, Bitmap> {

  private final Uri baseUri = Contacts.CONTENT_URI;
  private final Context context;
  private ImageView imageView;
  private static final Logger logger = LoggerFactory.getLogger(SqliteTable.class);

  /**
   * Constructor
   * 
   * @param context : Application context
   */
  public ContactPhotoLoader(Context context, ImageView imageView) {
    this.context = context;
    this.imageView = imageView;
  }

  @Override
  protected Bitmap doInBackground(ContactPhotoRequest... contactPhotoType) {

    Uri contactUri = ContentUris.withAppendedId(baseUri, contactPhotoType[0].getContactId());

    if (contactPhotoType[0].getPhotoType() == PhotoType.THUMBNAIL) {
      return getThumbnailPhoto(contactUri);
    } else if (contactPhotoType[0].getPhotoType() == PhotoType.FULL_SIZE) {
      return getFullSizePhoto(contactUri);
    }
    return null;
  }

  @Override
  protected void onPostExecute(Bitmap contactPhoto) {
    if (contactPhoto != null) {
      imageView.setImageBitmap(contactPhoto);
    }
  }

  private Bitmap getThumbnailPhoto(Uri contactUri) {
    InputStream input = Contacts.openContactPhotoInputStream(context.getContentResolver(), contactUri);
    return BitmapFactory.decodeStream(input);
  }

  @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
  private Bitmap getFullSizePhoto(Uri contactUri) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      Uri displayPhotoUri = Uri.withAppendedPath(contactUri, Contacts.Photo.DISPLAY_PHOTO);

      try {
        AssetFileDescriptor fd = context.getContentResolver().openAssetFileDescriptor(displayPhotoUri, "r");
        InputStream input;
        input = fd.createInputStream();
        return BitmapFactory.decodeStream(input);

      } catch (IOException e) {
        logger.error("Could not get the photoStream.", e);
        e.printStackTrace();
      }
    }
    return null;
  }
}
