package com.o4.microservices.service.commands;

import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.EventType;
import com.o4.microservices.dto.comments.Comment;
import com.o4.microservices.service.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.o4.microservices.util.JsonUtils.convert;

@Slf4j
@Component
public class CommentUpdatedCommand extends EventCommand {

    public CommentUpdatedCommand(DataRepository repository) {
        super(repository);
    }

    @Override
    public void execute(BusEvent event) {
        Comment comment = convert(event.getData(), Comment.class);

        Comment existing = repository.getPosts()
                .get(comment.getPostId())
                .getComments()
                .stream().filter(c -> c.getId().equals(comment.getId()))
                .findFirst().orElse(null);

        if (null != existing) {
            existing.setTitle(comment.getTitle());
            existing.setStatus(comment.getStatus());

            log.warn("Comment updated with new status: {}", comment.getStatus() );
        }
    }

    @Override
    public EventType getType() {
        return EventType.COMMENT_UPDATED;
    }
}
