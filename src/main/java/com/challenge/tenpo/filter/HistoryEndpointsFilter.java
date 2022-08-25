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
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        filterChain.doFilter(requestWrapper, responseWrapper);
        HistoryInformationDTO historyInformationDTO = buildHistoryInformation(requestWrapper, responseWrapper, time);
        historyEndpointService.saveHistory(historyInformationDTO);
        responseWrapper.copyBodyToResponse();

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.contains("swagger") || path.contains("docs");
    }

    private HistoryInformationDTO buildHistoryInformation(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, LocalDateTime time) throws UnsupportedEncodingException {
        byte[] responseArray = responseWrapper.getContentAsByteArray();
        String responseStr = new String(responseArray, responseWrapper.getCharacterEncoding());
        HttpMethod httpMethod = HttpMethod.valueOf(requestWrapper.getMethod());
        HttpStatus responseStatus = HttpStatus.valueOf(responseWrapper.getStatus());
        return HistoryInformationDTO.builder().content(responseStr).method(httpMethod).status(responseStatus).executedTime(time).endpointUrl(requestWrapper.getRequestURI()).build();
    }
}
