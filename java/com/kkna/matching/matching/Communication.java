package com.kkna.matching.matching;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created by Kim on 2017-01-04.
 */
public class Communication {
    /************ Singleton attribute & operation start ***************/
    private static Communication communication;
    public static Communication getInstance(){
        if(communication == null)   communication = new Communication();
        return communication;
    }
    protected Communication(){
        packetQueue = new LinkedList<Packet>();
        afterQueue = new LinkedList<Packet>();
        afterQueueSemaphore = new Semaphore(1);
        threadSemaphore = new Semaphore(1);
        httpThread = new HttpThread();
    }
    /************ Singleton attribute & operation end *****************/
    //Concurrent한 접근은 Thread와 AfterQueue에 대해서만 처리해주면 된다.
    private HttpThread httpThread;
    private Semaphore afterQueueSemaphore;
    private Semaphore threadSemaphore;
    private Queue<Packet> packetQueue;
    private Queue<Packet> afterQueue;

    public void pushPacket(Packet packet){
        Log.d("Communication", "pushPacket called");

        if(threadSemaphore.tryAcquire()) {
            packetQueue.add(packet);
            httpThread = new HttpThread();
            httpThread.execute();
        }else {
            try {
                afterQueueSemaphore.acquire();
                afterQueue.add(packet);
                afterQueueSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class HttpThread extends AsyncTask<Void, Void, Void>{
        private static final String urlStr = "aaa";

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("Communication", "Start Thread");

            try {
                afterQueueSemaphore.acquire();      //afterQueueSemaphore LOCK
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(!afterQueue.isEmpty()){
                packetQueue.addAll(afterQueue);
                afterQueue.clear();
                afterQueueSemaphore.release();      //afterQueue가 비어있지 않을 때, afterQueueSemaphore UNLOCK

                //HttpConnection 처리 코드 추가 시작 ********************************************
                int size = packetQueue.size();
                for(int i = 0; i < size; i++){
                    Log.d("Communication", "Test : " + packetQueue.remove().test);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //HttpConnection 처리 코드 추가 끝 ********************************************

                try {
                    afterQueueSemaphore.acquire();      //afterQueueSemaphore LOCK
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            afterQueueSemaphore.release();      //afterQueue가 비어있을 때, afterQueueSemaphore UNLOCK

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            threadSemaphore.release();
            Log.d("Communication", "End Thread");
        }
    }
}