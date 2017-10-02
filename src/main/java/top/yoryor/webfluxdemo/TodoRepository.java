package top.yoryor.webfluxdemo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by yaoyao on 2017/9/30 下午3:06.
 */
public interface TodoRepository extends ReactiveMongoRepository<Todo, String> {

}
