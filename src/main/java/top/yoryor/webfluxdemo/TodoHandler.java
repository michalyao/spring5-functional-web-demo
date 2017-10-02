package top.yoryor.webfluxdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by yaoyao on 2017/9/30 下午3:04.
 */
@Service
public class TodoHandler {
    private TodoRepository todoRepository;

    public TodoHandler(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Mono<ServerResponse> listTodos(ServerRequest request) {
        final Flux<Todo> todoAll = todoRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(todoAll, Todo.class);
    }

    public Mono<ServerResponse> newTodo(ServerRequest request) {
        final Mono<Todo> todoMono = request.body(BodyExtractors.toMono(Todo.class));
        final Mono<Void> savedMono = todoMono.flatMap(todoRepository::save).thenEmpty(Mono.empty());
        return ServerResponse.ok().build(savedMono);
    }

    public Mono<ServerResponse> getTodo(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Todo> todoMono = todoRepository.findById(id);
        return todoMono.flatMap(todo ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(todo)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deleteTodo(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Void> deleteMono = todoRepository.deleteById(id);
        return ServerResponse.ok().build(deleteMono);
    }
}
