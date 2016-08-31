package com.webview;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HttpService {

//    @GET("http://www.axmag.com/download/pdfurl-guide.pdf")
    @GET("download/pdfurl-guide.pdf")
    public Call<ResponseBody> downloadPdf();
}
