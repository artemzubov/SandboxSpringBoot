package ru.spb.zubov.repos;

import org.springframework.data.repository.CrudRepository;
import ru.spb.zubov.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);

}