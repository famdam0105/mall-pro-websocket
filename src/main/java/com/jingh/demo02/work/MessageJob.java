package com.jingh.demo02.work;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jingh.demo02.pojo.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;

@Slf4j
@Component
public class MessageJob {

        @Autowired
        WsService wsService;

        /**
         * 每20s发送
         */
        //@Scheduled(cron = "0/20 * * * * *")
        public void run(){
            try {
                wsService.broadcastMsg("我是x号");
                //wsService.broadcastMsg("自动生成消息 "  + LocalDateTime.now().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Scheduled(cron = "20 * * * * *")
        public void run1(){
            ObjectMapper mapper = new ObjectMapper();
            People people = People.builder().name("huang").age(18).sex("男").comment("热爱新奇的东西").hobby("篮球").build();
            try {
                String json = mapper.writeValueAsString(people);
                wsService.broadcastMsg(json);
            } catch (Exception e) {
                e.printStackTrace();
            }

//            try {
//                wsService.broadcastMsg("我是x号");
//                //wsService.broadcastMsg("自动生成消息 "  + LocalDateTime.now().toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }


//        @Scheduled(cron = "25 * * * * *")
//        public void run2(){
//            try {
//                wsService.broadcastMsg("我是y号");
//                //wsService.broadcastMsg("自动生成消息 "  + LocalDateTime.now().toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Scheduled(cron = "30 * * * * *")
//        public void run3(){
//            try {
//                wsService.broadcastMsg("我是z号");
//                //wsService.broadcastMsg("自动生成消息 "  + LocalDateTime.now().toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
}
