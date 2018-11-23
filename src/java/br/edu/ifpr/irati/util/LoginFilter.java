package br.edu.ifpr.irati.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpr.irati.mb.PTDMB;
import br.edu.ifpr.irati.mb.UsuarioMB;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void destroy() {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        //Captura o ManagedBean chamado “usuarioMB”
        UsuarioMB usuarioMB = (UsuarioMB) ((HttpServletRequest) request)
                .getSession().getAttribute("usuarioMB");

        if (usuarioMB == null || !usuarioMB.isLogado()) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/index.xhtml");
        } else {
            //Caso ele esteja logado, apenas deixamos 
            //que o fluxo continue
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
