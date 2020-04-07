package limitless.android.androiddevelopment.Data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import limitless.android.androiddevelopment.Model.SongModel;
import limitless.android.androiddevelopment.Model.PhotoModel;
import limitless.android.androiddevelopment.Model.VideoModel;

public class Data {

    public static List<PhotoModel> getAllPhotos(Context context, String sort){
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.HEIGHT,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DESCRIPTION,
                MediaStore.Images.Media.LATITUDE,
                MediaStore.Images.Media.LONGITUDE
        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, sort);
        if (cursor != null){
            if (cursor.getCount() > 0 && cursor.moveToFirst()){
                List<PhotoModel> list = new ArrayList<>();
                int id = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                int height = cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT);
                int width = cursor.getColumnIndex(MediaStore.Images.Media.WIDTH);
                int size = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);
                int dateAdded = cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED);
                int title = cursor.getColumnIndex(MediaStore.Images.Media.TITLE);
                int data = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                int des = cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION);
                int latitude = cursor.getColumnIndex(MediaStore.Images.Media.LATITUDE);
                int longitude = cursor.getColumnIndex(MediaStore.Images.Media.LONGITUDE);
                do {
                    list.add(new PhotoModel(
                            cursor.getInt(id),
                            cursor.getInt(height),
                            cursor.getInt(width),
                            cursor.getLong(size),
                            cursor.getLong(dateAdded),
                            cursor.getString(title),
                            cursor.getString(data),
                            cursor.getString(des),
                            cursor.getString(latitude),
                            cursor.getString(longitude)
                    ));
                }while (cursor.moveToNext());
                cursor.close();
                return list;
            }
            cursor.close();
        }
        return null;
    }

    public static List<SongModel> getAllSongs(Context context, String sort){
        if (context == null)
            return null;
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns._ID,
                MediaStore.Audio.AudioColumns.ALBUM_ID,
                MediaStore.Audio.AudioColumns.ARTIST_ID,
                MediaStore.Audio.AudioColumns.DURATION,
                MediaStore.Audio.AudioColumns.SIZE,
                MediaStore.Audio.AudioColumns.DATE_ADDED
        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, sort);
        if (cursor == null)
            return null;
        if (cursor.getCount() <= 0 || ! cursor.moveToFirst())
            return null;
        List<SongModel> list = new ArrayList<>();
        int title = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
        int data = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
        int album = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
        int artist = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
        int id = cursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID);
        int albumId = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID);
        int artistId = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST_ID);
        int duration = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
        int size = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE);
        int dateAdded = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATE_ADDED);
        do {
            list.add(new SongModel(
                    cursor.getString(title),
                    cursor.getString(data),
                    cursor.getString(album),
                    cursor.getString(artist),
                    cursor.getInt(id),
                    cursor.getInt(albumId),
                    cursor.getInt(artistId),
                    cursor.getInt(duration),
                    cursor.getLong(size),
                    cursor.getLong(dateAdded)
            ));
        }while (cursor.moveToNext());
        cursor.close();
        return list;
    }

    public static List<VideoModel> getAllVideos(ContentResolver cr, String sort){
        if (cr == null)
            return null;
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Video.VideoColumns.TITLE,
                MediaStore.Video.VideoColumns.DATA,
                MediaStore.Video.VideoColumns.ALBUM,
                MediaStore.Video.VideoColumns.ARTIST,
                MediaStore.Video.VideoColumns.CATEGORY,
                MediaStore.Video.VideoColumns.RESOLUTION,
                MediaStore.Video.VideoColumns._ID,
                MediaStore.Video.VideoColumns.WIDTH,
                MediaStore.Video.VideoColumns.HEIGHT,
                MediaStore.Video.VideoColumns.DURATION,
                MediaStore.Video.VideoColumns.SIZE,
                MediaStore.Video.VideoColumns.DATE_ADDED
        };
        Cursor cursor = cr.query(uri, projection, null, null, sort);
        if (cursor == null)
            return null;
        if (cursor.getCount() <= 0 || ! cursor.moveToFirst())
            return null;
        List<VideoModel> list = new ArrayList<>();
        int title = cursor.getColumnIndex(MediaStore.Video.VideoColumns.TITLE);
        int data = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
        int album = cursor.getColumnIndex(MediaStore.Video.VideoColumns.ALBUM);
        int artist = cursor.getColumnIndex(MediaStore.Video.VideoColumns.ARTIST);
        int category = cursor.getColumnIndex(MediaStore.Video.VideoColumns.CATEGORY);
        int resolution = cursor.getColumnIndex(MediaStore.Video.VideoColumns.RESOLUTION);
        int id = cursor.getColumnIndex(MediaStore.Video.VideoColumns._ID);
        int width = cursor.getColumnIndex(MediaStore.Video.VideoColumns.WIDTH);
        int height = cursor.getColumnIndex(MediaStore.Video.VideoColumns.HEIGHT);
        int duration = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION);
        int size = cursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE);
        int dataAdded = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATE_ADDED);
        do {
            list.add(new VideoModel(
                    cursor.getString(title),
                    cursor.getString(data),
                    cursor.getString(album),
                    cursor.getString(artist),
                    cursor.getString(category),
                    cursor.getString(resolution),
                    cursor.getInt(id),
                    cursor.getInt(width),
                    cursor.getInt(height),
                    cursor.getInt(duration),
                    cursor.getLong(size),
                    cursor.getLong(dataAdded)
            ));
        }while (cursor.moveToNext());
        cursor.close();
        return list;
    }

    public static Bitmap getAudioCoverPhoto(String data) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(data);
        byte[] bytes = mmr.getEmbeddedPicture();
        if (bytes != null)
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    public static Bitmap getVideoCoverPhoto(ContentResolver contentResolver, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 95;
        options.inSampleSize = 1;
        return MediaStore.Video.Thumbnails.getThumbnail(contentResolver, i, MediaStore.Video.Thumbnails.MINI_KIND, options);
    }

}
