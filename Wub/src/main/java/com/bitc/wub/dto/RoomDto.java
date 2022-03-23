package com.bitc.wub.dto;

import lombok.Data;

//채팅방의 기본 정보 객체
@Data
public class RoomDto {
    private int roomNumber;
    private String roomName;

    @Override
    public String toString() {
        return "Room[roomNumber=" + roomNumber + ",roomName=" + roomName + "]";
    }
}
