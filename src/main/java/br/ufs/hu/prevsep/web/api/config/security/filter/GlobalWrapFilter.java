package br.ufs.hu.prevsep.web.api.config.security.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Esta classe envelopa a requisição numa classe que permite que a mesma seja lida várias vezes.
 * Isso permite que o corpo da requisição seja logado pela classe {@link EventLogFilter}.
 *
 * A criação desse filtro se fez nessária devido à natureza do próprio Servlet request, que é uma Stream de dados, e,
 * dessa forma, só pode ser lida uma única vez.
 *
 * Essta classe faz o cache da requisição numa variável e a retorna sempre que necessário, através da sobrescrita de
 * alguns métodos da classe {@link ServletRequest} e {@link java.io.InputStream}.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalWrapFilter implements Filter {

    /**
     * O filtro apenas evelopa a requisição e passa a mesma em frente na cadeia.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MultiReadRequest wrapper = new MultiReadRequest((HttpServletRequest) request);
        chain.doFilter(wrapper, response);
    }

    /**
     * Classe envelope para a requisição HTTP.
     */
    static class MultiReadRequest extends HttpServletRequestWrapper {

        private final String requestBody;

        /**
         * Evelopa a requisição, lendo o body da requisição e salvando o conteúdo na variável 'requestBody'.
         * @param request A requisição que será envelopada
         * @throws IOException Caso ocorra um erro lendo o corpo da requisição
         */
        public MultiReadRequest(HttpServletRequest request) throws IOException {
            super(request);
            requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        }

        /**
         * Método sobrescrito para que sempre que solicitada a abertura da Stream de dados da requisição,
         * seja criada uma nova Stream com os dados do atributo 'requestBody'
         *
         * @return A Stream de dados que representa o body da requisição e pode ser lida várias vezes
         */
        @Override
        public ServletInputStream getInputStream() {
            // Cria uma nova Stream a partir dos dados em cache
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes());

            return new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return byteArrayInputStream.available() == 0;
                }

                @Override
                public boolean isReady() {
                    return true;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() {
                    return byteArrayInputStream.read();
                }
            };
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(this.getInputStream(), StandardCharsets.UTF_8));
        }
    }
}