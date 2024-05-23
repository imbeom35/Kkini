package com.kkini.core.domain.notification.controller;

import com.kkini.core.domain.notification.dto.response.NotificationListResponseDto;
import com.kkini.core.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kkini.core.global.response.Response.OK;

@RestController
@CrossOrigin
@Tag(name = "Noticifation", description = "알림 관리 API")
@Slf4j
@RequestMapping("/api/noti")
public class NotificationController {

    @Operation(summary = "알림 리스트", description = "나에게 온 전체 알림 리스트를 확인합니다.")
    @GetMapping("/list")
    public Response<List<NotificationListResponseDto>> getNotificationList(){
        log.debug("현재 알림을 불러옵니다.");
        List<NotificationListResponseDto> list = null;
        log.debug("{}", list);

        return OK(list);
    }

    @Operation(summary = "알림 읽음 처리", description = "나에게 온 알림을 읽음 처리합니다.")
    @PutMapping("/{notiId}")
    public Response<Void> readNotification(@PathVariable("notiId") Long notiId){
        log.debug("알림 읽음 처리");
        log.debug("{}", notiId);

        return OK(null);
    }

    @Operation(summary = "알림 삭제 처리", description = "나에게 온 알림을 삭제 처리합니다.")
    @DeleteMapping("/{notiId}}")
    public Response<Void> deleteNotification(@PathVariable("notiId") Long notiId){
        log.debug("알림 삭제 처리");
        log.debug("{}", notiId);

        return OK(null);
    }

    @Operation(summary = "알림 읽음 처리", description = "나에게 온 알림을 전체 읽음 처리합니다.")
    @PutMapping("/all")
    public Response<Void> readAllNotification(){
        log.debug("알림 읽음 처리");

        return OK(null);
    }

    @Operation(summary = "알림 삭제 처리", description = "나에게 온 알림을 삭제 처리합니다.")
    @DeleteMapping("/all")
    public Response<Void> deleteAllNotification(){
        log.debug("알림 삭제 처리");

        return OK(null);
    }
}
