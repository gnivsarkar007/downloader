package com.gaurav.ubertest.loading.download;

import android.graphics.Bitmap;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.LruCache;
import android.widget.ImageView;
import com.gaurav.ubertest.R;
import com.gaurav.ubertest.loading.request.Request;
import com.gaurav.ubertest.loading.request.RequestCreator;
import com.gaurav.ubertest.util.HandlerProvider;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;

public class ImageDownloader extends Downloader<Bitmap> {
	private static ImageDownloader instance;

	private WeakHashMap<String, WeakReference<Bitmap>> bitmapCache;
	private WeakHashMap<WeakReference<ImageView>, String> imageViewCache;
	private LruCache<String ,WeakReference<ImageView>> urlImageViewMap;
	private @DrawableRes int defaultRes = R.drawable.ic_search_black_24dp;

  @VisibleForTesting
  public ImageDownloader(ExecutorService service) {
    super(service);
	}

	@Override public HandlerProvider setHandlerProvider(final HandlerProvider provider) {
		return handlerProvider = provider;
	}

	public static ImageDownloader get() {
		if (instance == null) {
			synchronized (ImageDownloader.class) {
				if (instance == null) {
					instance = new ImageDownloader(DownloaderExecutor.get());
					instance.imageViewCache = new WeakHashMap<>();
					instance.bitmapCache = new WeakHashMap<>();
					instance.urlImageViewMap = new LruCache<>(30);
					instance.setHandlerProvider(new HandlerProvider.MainThreadHandlerProvider());
				}
			}
		}
		return instance;
	}

	public void loadImage(ImageView imageView,String url){

		WeakReference<ImageView> target = new WeakReference<>(imageView);
		String oldUrl = imageViewCache.put(target,url);
		urlImageViewMap.put(url,target);
		if(oldUrl != null){
			handlerProvider
					.provideHandler()
					.removeCallbacksAndMessages(oldUrl); // cancel response reciept for an older url
		}

		WeakReference<Bitmap> currentBitmap = bitmapCache.get(url);
		if(currentBitmap != null && currentBitmap.get() != null){
			setBitmapOnImageView(imageView,currentBitmap.get(),url);
			return;
		}

		imageView.setImageResource(defaultRes);

    Request.Callback<Bitmap> callback = new Request.Callback<Bitmap>() {
      @Override public void onDataLoaded(final Bitmap data, final String url) {
        cacheAndSetBitmap(url, data);
      }

      @Override public void error(final String errMsg) {

      }
    };

    submit(createRequest(url, RequestCreator.Source.FLICKR, callback));
  }

  public Request<Bitmap> createRequest(
      @NonNull String url,
			@NonNull RequestCreator.Source source,
			Request.Callback<Bitmap> targetCallback) {

    return new RequestCreator(handlerProvider).createRequest(
        source,
        url, targetCallback
    );
  }

	private void cacheAndSetBitmap(String url,Bitmap bitmap){
		bitmapCache.put(url,new WeakReference<Bitmap>(bitmap));
		WeakReference<ImageView> target = urlImageViewMap.get(url);
		ImageView imageView = target.get();
		String currentUrl = imageViewCache.get(target);
		if(imageView != null && url.equals(currentUrl)){
			setBitmapOnImageView(imageView,bitmap,url);
		}
	}

	private void setBitmapOnImageView(ImageView imageView,Bitmap bitmap,String tag){
		Runnable callback = () -> {
			imageView.setImageBitmap(bitmap);
		};
		Message message = Message.obtain(handlerProvider.provideHandler(), callback);
		message.obj = tag;
		handlerProvider.provideHandler().sendMessage(message);
	}
}
