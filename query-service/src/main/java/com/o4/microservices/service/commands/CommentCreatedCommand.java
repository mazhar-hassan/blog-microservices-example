package com.o4.microservices.service.commands;

import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.EventType;
import com.o4.microservices.dto.comments.Comment;
import com.o4.microservices.service.DataRepository;
import org.springframework.stereotype.Component;

import static com.o4.microservices.util.JsonUtils.convert;

@Component
public class CommentCreatedCommand extends EventCommand {

    public CommentCreatedCommand(DataRepository repository) {
        super(repository);
    }

    @Override
    public void execute(BusEvent event) {
        Comment comment = convert(event.getData(), Comment.class);

        repository.getPosts()
                .get(comment.getPostId())
                .getComments()
                .add(comment);
    }

    @Override
    public EventType getType() {
        return EventType.COMMENT_CREATED;
    }
}
