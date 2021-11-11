package ir.imancn.messenger.dto;

import ir.imancn.messenger.MessageInfo;
import ir.imancn.messenger.model.MessageEntity;
import lombok.Data;

@Data
public class MessageDTO {
    private String fromId;
    private String toId;
    private String date;
    private String body;

    public MessageDTO(MessageInfo messageInfo){
         this.fromId = messageInfo.getFromId();
         this.toId = messageInfo.getToId();
         this.date = messageInfo.getDate();
         this.body = messageInfo.getBody();
    }

    public MessageDTO(MessageEntity messageEntity){
         this.fromId = messageEntity.getFromId();
         this.toId = messageEntity.getToId();
         this.date = messageEntity.getDate();
         this.body = messageEntity.getBody();
    }
    public MessageInfo toMessageInfo(){
        return MessageInfo.newBuilder()
                .setFromId(this.fromId)
                .setToId(this.toId)
                .setDate(this.date)
                .setBody(this.body)
                .build();
    }

    public MessageEntity toMessageEntity() {
        return new MessageEntity(
                null,
                this.fromId,
                this.toId,
                this.date,
                this.body
        );
    }

}
