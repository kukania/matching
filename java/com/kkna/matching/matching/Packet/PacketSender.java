package com.kkna.matching.matching.Packet;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created by Kim on 2017-01-04.
 */
public class PacketSender {
    /************ Singleton attribute & operation start ***************/
    private static PacketSender packetSender;
    public static PacketSender getInstance(){
        if(packetSender == null)   packetSender = new PacketSender();
        return packetSender;
    }
    protected PacketSender(){
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

    public void sendPacket(Packet packet){
        Log.d("PacketSender", "pushPacket called");

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
        private static final String urlStr = "http://52.79.164.243";

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("PacketSender", "Start Thread");

            //HttpConnection 처리 코드 추가 시작 ********************************************
            Log.d("PacketSender", "HttpConnection start");
            try{
                URL Url = new URL(urlStr); // URL화 한다.
                HttpURLConnection conn = (HttpURLConnection) Url.openConnection(); // URL을 연결한 객체 생성.
                conn.setRequestMethod("POST"); // get방식 통신
                conn.setDoOutput(true); // 쓰기모드 지정
                conn.setDoInput(true); // 읽기모드 지정
                conn.setUseCaches(false); // 캐싱데이터를 받을지 안받을지
                conn.setDefaultUseCaches(false); // 캐싱데이터 디폴트 값 설정

                OutputStream os = conn.getOutputStream(); // 서버로 보내기 위한 출력 스트림
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); // UTF-8로 전송

                //packetQueue에 있는 데이터 전송
                Iterator<Packet> iter = packetQueue.iterator();
                while(iter.hasNext()){
                    Packet p = iter.next();
                    bw.write(p.toString());
                }

                bw.flush();
                bw.close();
                os.close();

                InputStream is = conn.getInputStream(); //input스트림 개방
                StringBuilder builder = new StringBuilder(); //문자열을 담기 위한 객체
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8")); //문자열 셋 세팅
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line+ "\n");
                }

                String result = builder.toString();
                Log.d("result", result);

                //---------------- packetQueue에 있는 상태 변화 ---------------------//


            }catch(MalformedURLException | ProtocolException exception) {
                exception.printStackTrace();
            }catch(IOException io){
                io.printStackTrace();
            }
            //HttpConnection 처리 코드 추가 끝 ********************************************

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //callback 처리
            Iterator<Packet> iter = packetQueue.iterator();
            while(iter.hasNext()){
                iter.next().callback();
            }

            try {
                afterQueueSemaphore.acquire();      //afterQueueSemaphore LOCK
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!afterQueue.isEmpty()){               //afterQueue가 비어있는지 확인 후에.,..
                packetQueue.addAll(afterQueue);
                afterQueue.clear();
                afterQueueSemaphore.release();      //afterQueue가 비어있지 않을 때, afterQueueSemaphore UNLOCK

                //딜레이를 넣는다면 여기다!!

                new HttpThread().execute();
                return;
            }else {
                afterQueueSemaphore.release();      //afterQueue가 비어있을 때, afterQueueSemaphore UNLOCK
                threadSemaphore.release();
                Log.d("PacketSender", "End Thread");
            }
        }
    }
}