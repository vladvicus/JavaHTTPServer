package com.bean;

import com.helper.Config;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class Response {

    private OutputStream os;
    private String version;
    private String statusCode;
    private String server;
    private String date;
    private String contentType;
    private String contentLength;
    private String body;
    static final String HTML_START =
            "<html>" +
                    "<title>HTTP Server in java</title>" +
                    "<body>";

    static final String HTML_END =
            "</body>" +
                    "</html>";
    public Response(OutputStream outputStream) {

        this.os = outputStream;
    }

    public void write() throws IOException {
        String response = "";
        Map<String, String> responseMap = new LinkedHashMap<String, String>();

       responseMap.put("", statusCode);

        responseMap.put(Config.server(), Config.server_value());

        if (!contentType.isEmpty()) {
            responseMap.put(Config.content_type(), contentType + "\r\n");
        }

        if (!contentLength.isEmpty()) {
            responseMap.put(Config.content_length(), contentLength + "\r\n");
        }



        if (!body.isEmpty()) {
            responseMap.put(Config.body(),body);
        }

        for (Map.Entry<String, String> pair : responseMap.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue();
            if (key.equals(Config.body())) {
                response += value;
            } else
                response += key + value;
        }
        os.write(response.getBytes());

    }

    @Override
    public String toString() {
        return "Response{" +
                "os=" + os +
                ", version='" + version + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", server='" + server + '\'' +
                ", date='" + date + '\'' +
                ", contentType='" + contentType + '\'' +
                ", contentLength='" + contentLength + '\'' +
                ", body='" + body + '\'' +

                '}';
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    private String connection;



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}

