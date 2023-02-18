package xyz.gabear.springbootflow.flow;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Servlet3.0 中引入了异步 Servlet，对应的 Tomcat 版本是 7.0.x
 * Servlet3.1 中又引入了非阻塞 IO ，对应的 Tomcat 版本是 8.0.x
 */
public class ServletDemo {
    @WebServlet(urlPatterns = "/sync")
    public class SyncServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            long start = System.currentTimeMillis();
            printLog(request, response);
            System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        }

        private void printLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response.getWriter().write("ok");
        }
    }


    @WebServlet(urlPatterns = "/async", asyncSupported = true)
    public class AsyncServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            long start = System.currentTimeMillis();
            AsyncContext asyncContext = request.startAsync();
            CompletableFuture.runAsync(() -> printLog(asyncContext, asyncContext.getRequest(), asyncContext.getResponse()));
            System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        }

        private void printLog(AsyncContext asyncContext, ServletRequest request, ServletResponse response) {
            try {
                Thread.sleep(3000);
                response.getWriter().write("ok");
                asyncContext.complete();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
