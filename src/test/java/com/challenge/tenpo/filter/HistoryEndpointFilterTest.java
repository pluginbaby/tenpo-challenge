package com.challenge.tenpo.filter;

import com.challenge.tenpo.filter.HistoryEndpointsFilter;
import com.challenge.tenpo.rest.dto.HistoryInformationDTO;
import com.challenge.tenpo.rest.service.HistoryEndpointService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HistoryEndpointFilterTest {

    @InjectMocks
    @Spy
    private HistoryEndpointsFilter historyEndpointsFilter;

    @Mock
    private HistoryEndpointService historyEndpointService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;


    @Test
    public void testFilterSuccess () throws ServletException, IOException {
        when(response.getCharacterEncoding()).thenReturn("UTF-8");
        when(request.getMethod()).thenReturn(HttpMethod.POST.name());
        when(response.getStatus()).thenReturn(201);
        when(request.getRequestURI()).thenReturn("/api");
        Mockito.doNothing().when(filterChain).doFilter(any(ContentCachingRequestWrapper.class), any(ContentCachingResponseWrapper.class));
        historyEndpointsFilter.doFilter(request,response,filterChain);
        verify(historyEndpointService).saveHistory(any(HistoryInformationDTO.class));
    }

    @Test
    public void shouldNotFilterSuccess () throws ServletException {
        when(request.getRequestURI()).thenReturn("/swagger-ui/index.html");
        boolean result = historyEndpointsFilter.shouldNotFilter(request);
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldNotFilterSwaggerSuccess () throws ServletException {
        when(request.getRequestURI()).thenReturn("/v3/api-docs/swagger-config");
        boolean result = historyEndpointsFilter.shouldNotFilter(request);
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldNotFilterDocsSuccess () throws ServletException {
        when(request.getRequestURI()).thenReturn("/v3/api-docs");
        boolean result = historyEndpointsFilter.shouldNotFilter(request);
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldNotFilterMockSuccess () throws ServletException {
        when(request.getRequestURI()).thenReturn("/mock/percentage");
        boolean result = historyEndpointsFilter.shouldNotFilter(request);
        Assertions.assertFalse(result);
    }

}
