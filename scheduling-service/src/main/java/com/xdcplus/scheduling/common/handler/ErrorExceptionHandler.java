package com.xdcplus.scheduling.common.handler;

import com.xdcplus.scheduling.common.provider.ResponseProvider;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 * 异常处理
 *
 * @author Rong.Jia
 * @date 2021/08/18 13:17:11
 */
public class ErrorExceptionHandler extends DefaultErrorWebExceptionHandler {

	public ErrorExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                 ErrorProperties errorProperties, ApplicationContext applicationContext) {
		super(errorAttributes, resourceProperties, errorProperties, applicationContext);
	}

	/**
	 * 获取异常属性
	 */
	@Override
	protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		int code = 500;
		Throwable error = super.getError(request);
		if (error instanceof NotFoundException) {
			code = 404;
		}
		if (error instanceof ResponseStatusException) {
			code = ((ResponseStatusException) error).getStatus().value();
		}
		return ResponseProvider.response(code, this.buildMessage(request, error));
	}

	/**
	 * 指定响应处理方法为JSON处理的方法
	 *
	 * @param errorAttributes
	 */
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}

	/**
	 * 构建异常信息
	 *
	 * @param request
	 * @param ex
	 * @return
	 */
	private String buildMessage(ServerRequest request, Throwable ex) {
//		String uri = request.uri().toString();
//		if (uri.endsWith("doc.html")) {
//			return "[Swagger聚合网关] 已迁移至 [blade-swagger] 服务，请开启 [blade-swagger] 服务并访问 [http://127.0.0.1:18000/doc.html]";
//		}
		StringBuilder message = new StringBuilder("Failed to handle request [");
		message.append(request.methodName());
		message.append(" ");
		message.append(request.uri());
		message.append("]");
		if (ex != null) {
			message.append(": ");
			message.append(ex.getMessage());
		}
		return message.toString();
	}

}
