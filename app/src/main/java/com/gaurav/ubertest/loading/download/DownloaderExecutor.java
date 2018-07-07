package com.gaurav.ubertest.loading.download;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.gaurav.ubertest.loading.request.Request;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;
import static android.os.Process.setThreadPriority;

/**
 * Created by gauravn on 04/07/18.
 */

public class DownloaderExecutor extends ThreadPoolExecutor {

  private static final int DEFAULT_THREAD_COUNT = 3;

  private static DownloaderExecutor instance;

  public static DownloaderExecutor get() {
    if (instance == null) {
      synchronized (DataDownloader.class) {
        if (instance == null) {

          instance = new DownloaderExecutor();
        }
      }
    }
    return instance;
  }

  @VisibleForTesting
  public DownloaderExecutor() {
    super(DEFAULT_THREAD_COUNT, DEFAULT_THREAD_COUNT, 0, TimeUnit.MILLISECONDS,
        new PriorityBlockingQueue<>(), new RequestThreadFactory()
    );
  }

  @NonNull @Override public Future<?> submit(final Runnable task) {
    RequestFutureTask requestFutureTask = new RequestFutureTask((Request) task);
    execute(requestFutureTask);
    return requestFutureTask;
  }

  @VisibleForTesting
  public static final class RequestFutureTask extends FutureTask<Request>
      implements Comparable<RequestFutureTask> {
    private final Request request;

    public RequestFutureTask(Request request) {
      super(request, null);
      this.request = request;
    }

    @Override
    public int compareTo(@NonNull RequestFutureTask other) {
      Request.Priority p1 = request.getPriority();
      Request.Priority p2 = other.request.getPriority();

      return p2.ordinal() - p1.ordinal();
    }
  }


  static class RequestThreadFactory implements ThreadFactory {
    @Override public Thread newThread(@NonNull Runnable r) {
      return new RequestThread(r);
    }
  }

  private static class RequestThread extends Thread {
    RequestThread(Runnable r) {
      super(r);
    }

    @Override public void run() {
      setThreadPriority(THREAD_PRIORITY_BACKGROUND);
      super.run();
    }
  }

}
