package com.challenge.tenpo.filter;

import com.challenge.tenpo.rest.dto.HistoryInformationDTO;
import com.challenge.tenpo.rest.service.HistoryEndpointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@Slf4j
@AllArgsConstructor
public class HistoryEndpointsFilter extends OncePerRequestFilter {

    private HistoryEndpointService historyEndpointService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        LocalDateTime time = LocalDateTime.now();
        filterChain.doFilter(request, responseWrapper);
        byte[] responseArray = responseWrapper.getContentAsByteArray();
        String responseStr = new String(responseArray, responseWrapper.getCharacterEncoding());
        HttpMethod httpMethod = HttpMethod.valueOf(requestWrapper.getMethod());
        HttpStatus responseStatus = HttpStatus.valueOf(responseWrapper.getStatus());
        historyEndpointService.saveHistory(HistoryInformationDTO.builder().content(responseStr).method(httpMethod).status(responseStatus).executedTime(time).endpointUrl(request.getRequestURI()).build());
        responseWrapper.copyBodyToResponse();

    }
}
