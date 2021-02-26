package io.solo.http_header_space_reproducer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    NetServer server = vertx.createNetServer();
    server.connectHandler(socket -> {
      socket.handler(buffer -> {

        String rawRequest = new String(buffer.getBytes());
        System.out.println("Request: " + rawRequest);

        String rawResponse = "HTTP/1.1 201 Created\r\nDate: Fri, 26 Feb 2021 16:42:58 GMT\r\nStrict-Transport-Security: max-age=86400\r\nset-Cookie: WC_FWD_OVERRIDE=1; path=/;\r\nX-Powered-By: Servlet/3.0\r\nContent-Length: 23737\r\nSet-Cookie: JSESSIONID=0000em7jE5YPiJUE3mCoDKwDUsq:Host08Server1; Expires=Sat, 27-Feb-21 16:42:58 GMT; Path=/; HttpOnly\r\nSet-Cookie: WC_PERSISTENT=CutLXAaQLiPJi1OsstGC0JdJGzM%3D%0A%3B2021-02-26+08%3A42%3A58.952_1614357778952-1359_0; Expires=Mon, 12-Apr-21 16:42:58 GMT; Path=/\r\nExpires: Thu, 01 Dec 1994 16:00:00 GMT\r\nCache-Control: no-cache=\"set-cookie, set-cookie2\"\r\nVary: Accept-Encoding\r\nX-UA-Compatible: IE=edge\r\nX-Content-Type-Options: nosniff\r\nX-XSS-Protection: 1; mode=block\r\nContent-Security-Policy: base-uri  'self'\r\nConnection: close\r\nContent-Type: application/json\r\nContent-Language: en-US\r\nSet-Cookie: visid_incap_1975662=kl7fMx4MQAGiGwXqxvWbDRMlOWAAAAAAQUIPAAAAAAD3/m7r1wrwxTwRPEr5N0dp; expires=Sat, 26 Feb 2022 14:00:58 GMT; HttpOnly; path=/; Domain=.services.commerce.mattel.com\r\nSet-Cookie: incap_ses_8074_1975662=8OqEYKjIExUn7IEvUJwMcBMlOWAAAAAA2iWdeyyzdZAdG+ymp2seyg==; path=/; Domain=.services.commerce.mattel.com\r\nSet-Cookie: ___utmvmNpVuRNwpB=ZdbANqjWFSZ; path=/; Max-Age=900\r\nSet-Cookie: ___utmvaNpVuRNwpB=OIO\u0001yXJc; path=/; Max-Age=900\r\nSet-Cookie: ___utmvbNpVuRNwpB=pZI\r\n    XlsOLalm: ttA; path=/; Max-Age=900\r\nX-CDN: Imperva\r\nX-Iinfo: 13-136739837-136739852 NNNN CT(74 74 0) RT(1614357778384 68) q(0 0 2 -1) r(5 6) U5\r\n\r\n{}";

        if(rawRequest.contains("&nospace=true")) {
          // No spaces in front of "XlsOLalm: ttA; path=/;"
          rawResponse = "HTTP/1.1 201 Created\r\nDate: Fri, 26 Feb 2021 16:42:58 GMT\r\nStrict-Transport-Security: max-age=86400\r\nset-Cookie: WC_FWD_OVERRIDE=1; path=/;\r\nX-Powered-By: Servlet/3.0\r\nContent-Length: 23737\r\nSet-Cookie: JSESSIONID=0000em7jE5YPiJUE3mCoDKwDUsq:Host08Server1; Expires=Sat, 27-Feb-21 16:42:58 GMT; Path=/; HttpOnly\r\nSet-Cookie: WC_PERSISTENT=CutLXAaQLiPJi1OsstGC0JdJGzM%3D%0A%3B2021-02-26+08%3A42%3A58.952_1614357778952-1359_0; Expires=Mon, 12-Apr-21 16:42:58 GMT; Path=/\r\nExpires: Thu, 01 Dec 1994 16:00:00 GMT\r\nCache-Control: no-cache=\"set-cookie, set-cookie2\"\r\nVary: Accept-Encoding\r\nX-UA-Compatible: IE=edge\r\nX-Content-Type-Options: nosniff\r\nX-XSS-Protection: 1; mode=block\r\nContent-Security-Policy: base-uri  'self'\r\nConnection: close\r\nContent-Type: application/json\r\nContent-Language: en-US\r\nSet-Cookie: visid_incap_1975662=kl7fMx4MQAGiGwXqxvWbDRMlOWAAAAAAQUIPAAAAAAD3/m7r1wrwxTwRPEr5N0dp; expires=Sat, 26 Feb 2022 14:00:58 GMT; HttpOnly; path=/; Domain=.services.commerce.mattel.com\r\nSet-Cookie: incap_ses_8074_1975662=8OqEYKjIExUn7IEvUJwMcBMlOWAAAAAA2iWdeyyzdZAdG+ymp2seyg==; path=/; Domain=.services.commerce.mattel.com\r\nSet-Cookie: ___utmvmNpVuRNwpB=ZdbANqjWFSZ; path=/; Max-Age=900\r\nSet-Cookie: ___utmvaNpVuRNwpB=OIO\u0001yXJc; path=/; Max-Age=900\r\nSet-Cookie: ___utmvbNpVuRNwpB=pZI\r\nXlsOLalm: ttA; path=/; Max-Age=900\r\nX-CDN: Imperva\r\nX-Iinfo: 13-136739837-136739852 NNNN CT(74 74 0) RT(1614357778384 68) q(0 0 2 -1) r(5 6) U5\r\n\r\n{}";
        }

        System.out.println("Response: " + rawRequest);

        socket.write(rawResponse);
        socket.close();
      });
    });

    server.listen(8080, "0.0.0.0", res -> {
      if (res.succeeded()) {
        System.out.println("Server is now listening!");
      } else {
        System.out.println("Failed to bind!");
      }
    });
  }
}
