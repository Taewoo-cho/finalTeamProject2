package com.bitc.wub.controller;

import com.bitc.wub.dto.RoomDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatController {
    //	채팅방 리스트
    List<RoomDto> roomList = new ArrayList<RoomDto>();
    static int roomNumber = 0; // 채팅방 번호를 정적 변수로 사용하여 클래스 전체에서 공유함

    /**
     * 채팅 페이지 접속
     * @return : 채팅방 템플릿
     * @throws Exception
     */
    @RequestMapping("/chat")
    public ModelAndView chat() throws Exception {
        ModelAndView mv = new ModelAndView("chat");
        return mv;
    }

    /**
     * 채팅방 리스트
     * @return : 채팅방 리스트 View 템플릿
     */
    @RequestMapping("/room")
    public ModelAndView room() {
        ModelAndView mv = new ModelAndView("room");
        return mv;
    }

    /**
     * 채팅방 생성하기
     * 채팅방 생성 시 채팅방 이름과 번호를 받아옴
     * @param params : 새로 생성한 채팅방의 정보
     * @return : 생성된 전체 채팅방 정보
     */
    @RequestMapping("/createRoom")
    public @ResponseBody
    List<RoomDto> createRoom(@RequestParam HashMap<Object, Object> params){
        String roomName = (String) params.get("roomName"); // 클라이언트에서 전송한 데이터 중 방 이름 받아오기
//		받아온 이름이 빈값이 아닌지 확인
        if(roomName != null && !roomName.trim().equals("")) {
//			RoomDto 타입의 객체 변수 선언
            RoomDto room = new RoomDto();
//			정적 변수로 선언된 채팅방 번호값을 증가시켜 등록
            room.setRoomNumber(++roomNumber);
//			채팅방 이름 설정
            room.setRoomName(roomName);
//			전역 변수로 선언된 채팅방 리스트에 정보 저장
            roomList.add(room);
        }
        return roomList;
    }

    /**
     * 방 정보 가져오기
     * @param params : 클라이언트에서 선택한 방 정보
     * @return : 개설된 채팅방 리스트
     */
    @RequestMapping("/getRoom")
    public @ResponseBody List<RoomDto> getRoom(@RequestParam HashMap<Object, Object> params){
        return roomList;
    }


    /**
     * 지정한 채팅방으로 이동
     * @param params : 지정한 채팅방 정보
     * @return : View 템플릿
     */
    @RequestMapping("/moveChating")
    public ModelAndView chating(@RequestParam HashMap<Object, Object> params) {
        ModelAndView mv = new ModelAndView();
//		클라이언트에서 전송한 채팅방 번호를 받아옴
        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

//		람다식을 사용하여 필요한 정보를 가져옴
//		가져온 정보를 리스트 형태로 변경
        List<RoomDto> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
//		데이터가 존재하면 view 템플릿을 char로, 없으면 view 템플릿을 room
        if(new_list != null && new_list.size() > 0) {
//			ModelAndView 클래스 타입의 객체 mv에 데이터를 입력함
            mv.addObject("roomName", params.get("roomName"));
            mv.addObject("roomNumber", params.get("roomNumber"));
            mv.setViewName("chat");
        }else {
            mv.setViewName("room");
        }
        return mv;
    }
}

