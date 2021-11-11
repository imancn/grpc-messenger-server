package ir.imancn.messenger.repository;


import ir.imancn.messenger.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    List<MessageEntity> getAllByFromIdAndToId(String fromId, String toId);

    @Query("select toId from MessageEntity where fromId = :userId")
    List<String> getToIdByFromId(@Param("userId") String toId);

    @Query("select fromId from MessageEntity where toId = :userId")
    List<String> getFromIdByToId(@Param("userId") String toId);
}
