package space.indietech;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JWTSecurityFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(JWTSecurityFilter.class);

	private static final String NO_SECURITY_TOKEN = "No security token";
	private static final String TOKEN_HEADER = "token";

	private static Set<String> loggedInUsers = new HashSet<>();
	
	static {
		loggedInUsers.add("mauricio");
		loggedInUsers.add("fundatec");
		loggedInUsers.add("admin");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getRequestURI().equals("/calculadora")) {
			String token = request.getHeader(TOKEN_HEADER);

			if (token == null) {
				LOGGER.warn(NO_SECURITY_TOKEN);
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, NO_SECURITY_TOKEN);
			} else {
				try {
					String usuario = TokenParser.parse(token, "usuario");
					validateUser(usuario);
				} catch (Exception e) {
					LOGGER.warn(e.getMessage());
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
					return;
				}
				chain.doFilter(req, res);
			}
		} else if(request.getRequestURI().equals("/calculadora/admin")){
			String token = request.getHeader(TOKEN_HEADER);

			if (token == null) {
				LOGGER.warn(NO_SECURITY_TOKEN);
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, NO_SECURITY_TOKEN);
			} else {
				try {
					String usuario = TokenParser.parse(token, "usuario");
					validateAdm(usuario);
				} catch (Exception e) {
					LOGGER.warn(e.getMessage());
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
					return;
				}
				chain.doFilter(req, res);
			}
		}
	}

	private void validateAdm(String usuario) {
		if(!loggedInUsers.contains(usuario) || !usuario.equals("admin")) {
			throw new RuntimeException("Usuario invalido");
		}
		
	}

	private void validateUser(String usuario) {
		if (!loggedInUsers.contains(usuario)) {
			throw new RuntimeException("Usuario invalido");
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
