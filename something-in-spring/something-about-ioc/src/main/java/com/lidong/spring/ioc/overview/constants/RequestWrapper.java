package com.lidong.spring.ioc.overview.constants;

import com.alibaba.dubbo.common.utils.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author ls J
 * @date 2020/5/8 10:50
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    /**
     * request body
     */
    private final String body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        body = getPostData(request);
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.ISO_8859_1));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

    private String getPostData(HttpServletRequest request) {
        BufferedReader br = null;
        try (InputStream is = request.getInputStream()) {
            if (is != null) {
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1));
                return IOUtils.read(br);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
