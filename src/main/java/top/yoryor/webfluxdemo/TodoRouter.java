package top.yoryor.webfluxdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Created by yaoyao on 2017/9/30 下午3:31.
 */
@Configuration
public class TodoRouter {
    private TodoHandler todoHandler;

    public TodoRouter(TodoHandler todoHandler) {
        this.todoHandler = todoHandler;
    }

    @Bean
    public RouterFunction<?> route(TodoHandler todoHandler) {
        return RouterFunctions.route(GET("/todo"), todoHandler::listTodos)
                .andRoute(GET("/todo/{id}"), todoHandler::getTodo)
                .andRoute(DELETE("/todo/{id}"), todoHandler::deleteTodo)
                .andRoute(POST("/todo").and(accept(APPLICATION_JSON)), todoHandler::newTodo);
    }
}
