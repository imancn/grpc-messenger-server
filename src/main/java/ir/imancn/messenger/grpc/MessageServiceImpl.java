package ir.imancn.messenger.grpc;

import io.grpc.stub.StreamObserver;
import ir.imancn.messenger.*;
import ir.imancn.messenger.dto.MessageDTO;
import ir.imancn.messenger.model.MessageEntity;
import ir.imancn.messenger.repository.MessageRepository;
import net.devh.boot.grpc.server.service.GrpcService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@GrpcService
public class MessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void send(MessageInfo request, StreamObserver<MessageResponse> responseObserver) {
        MessageResponse.Builder messageResponse = MessageResponse.newBuilder();
        try {
            messageRepository.save(new MessageDTO(request).toMessageEntity());
            messageResponse.setStatus(200);
        } catch (Exception ex) {
            messageResponse.setStatus(500);
        } finally {
            responseObserver.onNext(messageResponse.build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getMessages(Chat request, StreamObserver<Messages> responseObserver){
        Messages.Builder messages = Messages.newBuilder();
        List<MessageEntity> messageEntityList = messageRepository.getAllByFromIdAndToId(request.getFromId(), request.getToId());
        for (MessageEntity messageEntity : messageEntityList)
            messages.addMessage(new MessageDTO(messageEntity).toMessageInfo());
        responseObserver.onNext(messages.build());
        responseObserver.onCompleted();
    }

    @Override
    public void openChats(UserInfo request, StreamObserver<Users> responseObserver) {
        Set<String> userIds = new HashSet<>(messageRepository.getToIdByFromId(request.getUserId()));
        userIds.addAll(messageRepository.getFromIdByToId(request.getUserId()));
        Users.Builder builder = Users.newBuilder().addAllUser(userIds);
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}


