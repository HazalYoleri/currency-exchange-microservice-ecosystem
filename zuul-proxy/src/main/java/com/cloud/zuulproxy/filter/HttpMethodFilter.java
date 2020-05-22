package com.cloud.zuulproxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;

@Component
public class HttpMethodFilter extends ZuulFilter {


  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    if (request.getMethod().equals(HttpMethod.DELETE)) {
      ctx.setResponseStatusCode(400);
      ctx.setResponseBody("Delete is not supported");
      ctx.setSendZuulResponse(false);
    }
    return null;
  }
}
