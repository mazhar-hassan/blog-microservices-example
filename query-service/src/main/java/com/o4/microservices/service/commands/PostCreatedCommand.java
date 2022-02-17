package com.o4.microservices.service.commands;

import com.o4.microservices.dto.BlogPost;
import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.EventType;
import com.o4.microservices.dto.posts.Post;
import com.o4.microservices.service.DataRepository;
import com.o4.microservices.util.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PostCreatedCommand extends EventCommand {

    public PostCreatedCommand(DataRepository repository) {
        super(repository);
    }

    @Override
    public void execute(BusEvent event) {
        Post post = JsonUtils.convert(event.getData(), Post.class);
        repository.addPost(map(post));
    }

    private BlogPost map(Post post) {
        BlogPost entity = new BlogPost();
        entity.setId(post.getId());
        entity.setTitle(post.getTitle());
        entity.setComments(new ArrayList<>());

        return entity;
    }

    @Override
    public EventType getType() {
        return EventType.POST_CREATED;
    }
}
