package leeco.hellogithub.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

public class ActProviderMain extends BaseActProvider {


    private void doRegister() {
        ContentResolver contentResolver = getContentResolver();

        ContentValues contentValues = new ContentValues();
        Uri  uri = null;

        contentResolver.insert(uri,contentValues);
    }

}
