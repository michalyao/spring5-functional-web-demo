package top.yoryor.webfluxdemo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yaoyao on 2017/9/30 下午3:05.
 */
@Data
@Document(collection = "todos")
public class Todo {
    @Id
    private String id;
    private String title;
    private boolean done;
}
