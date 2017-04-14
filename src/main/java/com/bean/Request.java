package com.bean;

import com.helper.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 10.04.2017.
 */
public class Request {

    private String method;
    private String path;
    private String httpVer;
    private String host;
    private String connection;
    private String cacheControl;
    private String userAgent;
    private String accept;
    private String acceptEncoding;
    private String acceptLanguage;
    private int contentLength;
    private String contentType;
    private String body;
    private int book_id;

    //  private String _requestLine;
    //  private Hashtable<String, String> _requestHeaders = new Hashtable<String, String>();
    //  private StringBuffer _messagetBody = new StringBuffer();

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpVer() {
        return httpVer;
    }

    public void setHttpVer(String httpVer) {
        this.httpVer = httpVer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getBook_id() {
        return book_id;
    }

    public Request(BufferedReader br) throws IOException {

        parseRequest(br);

    }

    private void parseRequest(BufferedReader br) throws IOException {

        List<String> headerValue = new ArrayList<String>();
        String strForReadHeader = "";

        while ((strForReadHeader = br.readLine()) != null) {
            headerValue.add(strForReadHeader);
            if (strForReadHeader.isEmpty()) {
                break;
            }
        }


        if (getContentLength(headerValue) > 0) {
            headerValue.add(getPostBody(br, contentLength));

        }
        for (String value : headerValue) {
            if (value.startsWith(Config.get())) {

                method = Config.get();

                path = getValueOfHeaderString(value, 2, " ");

                if (path.contains("=")) {
                    String[] query = path.split("=");
                    this.book_id = Integer.parseInt(query[1]);
                }



                httpVer = getValueOfHeaderString(value, 1, " ");


            } else if (value.startsWith(Config.post())) {

                method = Config.post();
                path = getValueOfHeaderString(value, 2, " ");
                httpVer = getValueOfHeaderString(value, 1, " ");

            } else if (value.startsWith(Config.put())) {

                method = Config.put();
                path = getValueOfHeaderString(value, 2, " ");
                httpVer = getValueOfHeaderString(value, 1, " ");

            } else if (value.startsWith(Config.host())) {

                host = getValueOfHeaderString(value, 1, Config.splitter());

            } else if (value.startsWith(Config.conn())) {

                connection = getValueOfHeaderString(value, 1, Config.splitter());

            } else if (value.startsWith(Config.cache())) {

                cacheControl = getValueOfHeaderString(value, 1, Config.splitter());

            } else if (value.startsWith(Config.user_agent())) {

                userAgent = getValueOfHeaderString(value, 1, Config.splitter());

            } else if (value.startsWith(Config.accept())) {

                accept = getValueOfHeaderString(value, 1, Config.splitter());

            } else if (value.startsWith(Config.accept_encoding())) {

                acceptEncoding = getValueOfHeaderString(value, 1, Config.splitter());

            } else if (value.startsWith(Config.accept_lang())) {

                acceptLanguage = getValueOfHeaderString(value, 1, Config.splitter());

            } else if (value.startsWith(Config.content_length())) {

                contentLength = Integer.parseInt(getValueOfHeaderString(value, 1, Config.splitter()));

            } else if (value.startsWith(Config.content_type())) {

                contentType = getValueOfHeaderString(value, 1, Config.splitter());
            }


        }
        if (contentLength > 0) {
            body = headerValue.get(headerValue.size() - 1);
        }
    }

    private String getPostBody(BufferedReader br, int contentLength) throws IOException {
        StringWriter postRequest = new StringWriter();
        char[] buffer = new char[1024];
        int charToWrite = 0;
        while ((charToWrite = br.read(buffer)) != -1) {
            postRequest.write(buffer, 0, charToWrite);
            if (charToWrite == contentLength) {
                break;
            }
        }
        return postRequest.toString();

    }

    private int getContentLength(List<String> headerValue) {
        int contentLength = 0;
        for (String header : headerValue) {

            if (header.contains(Config.content_length())) {

                contentLength = Integer.parseInt(getValueOfHeaderString(header, 1, ":"));
            }
        }
        return contentLength;
    }

    public String getValueOfHeaderString(String sourceString, int flag, String delim) {
        String value = "";
        String[] pair = sourceString.split(delim);
        System.out.println();


        for (String val : pair) {
            System.out.print(val + "-->");

        }
        if (pair.length > 1) {
            value = pair[pair.length - flag].trim();
        }
        return value;
    }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", httpVer='" + httpVer + '\'' +
                ", host='" + host + '\'' +
                ", connection='" + connection + '\'' +
                ", cacheControl='" + cacheControl + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", accept='" + accept + '\'' +
                ", acceptEncoding='" + acceptEncoding + '\'' +
                ", acceptLanguage='" + acceptLanguage + '\'' +
                ", contentLength=" + contentLength +
                ", contentType='" + contentType + '\'' +
                ", body='" + body + '\'' +

                '}';
    }
/*
        //setRequestLine(br.readLine());
        String header = br.readLine();
        while (header.length() > 0) {
            appendHeaderParameter(header);
            header = br.readLine();
        }

        String bodyLine = br.readLine();
        while (bodyLine != null) {
            appendMessageBody(bodyLine);
            bodyLine = br.readLine();
        }

    }


    private void appendHeaderParameter(String header)  {
        int idx = header.indexOf(":");

        _requestHeaders.put(header.substring(0, idx), header.substring(idx + 1, header.length()));
    }


    public String getMessageBody() {
        return _messagetBody.toString();
    }

    private void appendMessageBody(String bodyLine) {
        _messagetBody.append(bodyLine).append("\r\n");
    }

    public String getHeaderParam(String headerName) {
        return _requestHeaders.get(headerName);
    }*/
}







