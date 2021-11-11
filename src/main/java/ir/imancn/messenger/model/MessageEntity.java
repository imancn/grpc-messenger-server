package ir.imancn.messenger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity @Table(name = "message")
@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fromId;
    private String toId;
    private String date;
    private String body;
}
