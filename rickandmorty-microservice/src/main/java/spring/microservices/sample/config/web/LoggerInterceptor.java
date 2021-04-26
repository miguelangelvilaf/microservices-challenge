package spring.microservices.sample.config.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Logger interceptor.
 *
 * @author Miguel A. Vila
 */
@Slf4j
public final class LoggerInterceptor implements HandlerInterceptor {

    private final ServerPortService serverPortService;

    public LoggerInterceptor(ServerPortService serverPortService) {
        this.serverPortService = serverPortService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        long requestStartTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", requestStartTime);
        int port = serverPortService.getPort();
        log.info("[preHandle][{}][{}] - {} - {}",
            request,
            handler,
            request.getMethod().concat(" ").concat(String.valueOf(port)).concat(" ").concat(request.getRequestURI()).concat(getParameters(request)),
            getRemoteAddr(request));
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        long requestStartTime = (Long) request.getAttribute("requestStartTime");
        long requestEndTime = System.currentTimeMillis();
        log.info("[afterCompletion][{}][{}][exception: {}] - {} - {}ms",
            request,
            handler,
            ex,
            HttpStatus.valueOf(response.getStatus()),
            requestEndTime - requestStartTime);
    }

    private String getParameters(HttpServletRequest request) {
        StringBuilder posted = new StringBuilder();
        Enumeration<?> e = request.getParameterNames();
        while (e != null && e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            } else {
                posted.append("?");
            }
            String curr = (String) e.nextElement();
            posted.append(curr).append("=");
            if (curr.contains("password")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }
        return posted.toString();
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-Forwarded-For");
        String ipAddr = (ipFromHeader != null && ipFromHeader.length() > 0) ? ipFromHeader : request.getRemoteAddr();
        if (ipAddr != null && !ipAddr.equals("")) {
            return ipAddr;
        }
        return Strings.EMPTY;
    }

}
