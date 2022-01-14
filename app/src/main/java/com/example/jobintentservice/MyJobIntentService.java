package com.example.jobintentservice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class MyJobIntentService extends JobIntentService {

    public static final String TAG = "My JobIntentService";
    private static final int UNIQUE_JOB_ID=19978;

    //called from wherever we are starting this service e.g in MainActivity
    public static void enqueueWork(Context ct,Intent serviceIntent){
        enqueueWork(ct,MyJobIntentService.class,UNIQUE_JOB_ID,serviceIntent);
    }

    /**
     * Called serially for each work dispatched to and processed by the service.  This
     * method is called on a background thread, so you can do long blocking operations
     * here.  Upon returning, that work will be considered complete and either the next
     * pending work dispatched here or the overall service destroyed now that it has
     * nothing else to do.     *
     * @param intent The intent describing the work to now be processed.
     */
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        for(int i=0;i<10;i++){
            Log.d(TAG,"Service at " + i + "\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * This will be called if the JobScheduler has decided to stop this job.  The job for
     * this service does not have any constraints specified, so this will only generally happen
     * if the service exceeds the job's maximum execution time.
     *
     * @return True to indicate to the JobManager whether you'd like to reschedule this work,
     * false to drop this and all following work. Regardless of the value returned, your service
     * must stop executing or the system will ultimately kill it.  The default implementation
     * returns true, and that is most likely what you want to return as well (so no work gets
     * lost).
     */
    @Override
    public boolean onStopCurrentWork() {
        Log.d(TAG,"Work stopped");
        return super.onStopCurrentWork();
    }


}
