package com.sarality.app.datastore.contact;

import java.io.IOException;
import java.io.InputStream;

import com.sarality.app.datastore.contact.ContactPhotoType.PhotoType;

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

/**
 * Gets the Actual photo of the contact
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ContactPhotoLoader extends AsyncTask<ContactPhotoType, Void, Bitmap> {

  private final Uri baseUri = Contacts.CONTENT_URI;
  private final Context context;
  private ImageView imageView;

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
  protected Bitmap doInBackground(ContactPhotoType... contactPhotoType) {

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
    Uri displayPhotoUri = Uri.withAppendedPath(contactUri, Contacts.Photo.DISPLAY_PHOTO);

    try {
      AssetFileDescriptor fd = context.getContentResolver().openAssetFileDescriptor(displayPhotoUri, "r");
      InputStream input;
      input = fd.createInputStream();
      return BitmapFactory.decodeStream(input);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;

  }
}
