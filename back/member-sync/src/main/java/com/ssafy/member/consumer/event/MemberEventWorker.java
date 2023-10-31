package com.ssafy.member.consumer.event;

import com.ssafy.member.consumer.mapper.MemberMapper;
import com.ssafy.member.db.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Component
public class MemberEventWorker {
    private final Map<String, Consumer<MemberEvent>> eventWorker;
    private final MemberEntityRepository memberEntityRepository;

    @Autowired
    public MemberEventWorker(MemberEntityRepository memberEntityRepository) {
        this.eventWorker = new HashMap<>();
        this.memberEntityRepository = memberEntityRepository;
        MemberMapper memberMapper = MemberMapper.INSTANCE;

        this.eventWorker.put("c", memberEvent->
                memberEntityRepository.save(memberMapper.toEntity(memberEvent.after)));
        this.eventWorker.put("d", memberEvent->
                memberEntityRepository.delete(memberMapper.toEntity(memberEvent.before)));
        this.eventWorker.put("u", memberEvent->
                memberEntityRepository.update(memberMapper.toEntity(memberEvent.before),memberMapper.toEntity(memberEvent.after)));
    }

    public void work(MemberEvent memberEvent){
        Optional.ofNullable(eventWorker.get(memberEvent.getOp()))
                .ifPresent((consumer)->{consumer.accept(memberEvent);});
    }
}
